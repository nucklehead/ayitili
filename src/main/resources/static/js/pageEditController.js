app.filter('spaceToDash', function () {
    return function (input) {
        if(input !== undefined && input !== null){
            return input.replace(/ /g, '-');
        }
        return "";
    };
});
app.controller('pageEditController', function ($scope, $http, $window, $timeout) {
    if(typeof(html2canvas) === "undefined"){
        html2canvas = function () {return $.Deferred().resolve().promise()}
    }
    $scope.rendering = false;
    $scope.update = "";
    $scope.currentPage = {
        bodyRows:[
            ["Egzanp tèx.", "Egzanp tèx."],
            ["Egzanp tèx.", "Egzanp tèx."],
            ["Egzanp tèx.", "Egzanp tèx."]
        ],
        tags: [],
        tagsFormJson: []
    };

    $timeout(function () {
        if($scope.pageId){
            $http.get("/api/page/" + $scope.pageId).then(
                function(response) {
                    $scope.currentPage = response.data;
                    if($scope.currentPage.bodyRows.length === 0){
                        $scope.addRow(0);
                    }
                    $scope.updatePreview();
                },
                function(data) {
                    // Handle error here
                });
        }
        else {
            $scope.updatePreview();
        }
    }, 0);

    $scope.components = {};

    $scope.editComponent = function(componentID) {
        var component = $('#' + componentID);
        component.summernote();
        $scope.components[componentID] = component.summernote('code');
    };

    $scope.saveComponent = function(rowIndex, columnIndex) {
        var componentID = rowIndex + '-' + columnIndex;
        var component = $('#' + componentID);
        var content = component.summernote('code');
    //    do http
    //    loading
        if($scope.currentPage.bodyRows[rowIndex] === undefined){
            $scope.currentPage.bodyRows[rowIndex] = [];
        }
        $scope.currentPage.bodyRows[rowIndex][columnIndex] = content;
        $scope.cancelEditComponent(componentID);
        $scope.updatePreview();
    };

    $scope.cancelEditComponent = function(componentID) {
        var component = $('#' + componentID);
        component.summernote('code', $scope.components[componentID]);
        component.summernote('destroy');
        $scope.components[componentID] = undefined;
    };

    $scope.deleteComponent = function(rowIndex, columnIndex) {
        $scope.currentPage.bodyRows[rowIndex].splice(columnIndex, columnIndex + 1);
        if($scope.currentPage.bodyRows[rowIndex].length === 0){
            $scope.currentPage.bodyRows.splice(rowIndex, rowIndex + 1);
        }
        $scope.updatePreview();
    };

    $scope.addRow = function(rowIndex) {
        $scope.currentPage.bodyRows.splice(rowIndex + 1, 0, ["Egzanp tèx."]);
        $scope.updatePreview();
    };

    $scope.addColumn = function(rowIndex, columnIndex) {
        $scope.currentPage.bodyRows[rowIndex].splice(columnIndex + 1, 0, "Egzanp tèx.");
        $scope.updatePreview();
    };

    $scope.inEditMode = function(componentID) {
        return $scope.components[componentID] !== undefined && $scope.components[componentID] !== null;
    };

    $scope.savePage = function(){
        var formId = 'pageNameForm';
        var path = '/api/page';
        var method = 'POST';
        var form = $("#" + formId)[0];
        var formData = new FormData(form);
        if (form.checkValidity() === false) {
            form.classList.add("was-validated");
            return;
        }
        var pageSimpleName = formData.get("formatedName").replace("/page/", "");
        var commaReplacer = Base64.encode(pageSimpleName);
        formData.set("formatedName", pageSimpleName);
        formData.append("formThumbnail", dataURItoBlob($scope.preview), pageSimpleName + ".png");
        $scope.currentPage.bodyRows.forEach(function (row, rowIndex) {
            row.forEach(function (col, colIndex) {
                row[colIndex] = col.replace(/,/g, commaReplacer)
            });
            formData.append("bodyRows", row);
        });

        $scope.currentPage.tagsFormJson = $scope.currentPage.tagsFormJson.map(function (tagFormJson) {
            return tagFormJson.replace(/,/g, commaReplacer);
        });

        formData.append('tagsFormJson', $scope.currentPage.tagsFormJson);
        if($scope.currentPage.id){
            path = path + '/' + $scope.currentPage.id;
            method = 'PUT';
        }
        formData.append('_method', method);
        $http({
            method: 'post',
            headers: {'Content-Type': undefined},
            url: path,
            data: formData,
            transformRequest: angular.identity
        }).then(
        function(response) {
            $("#success-alert").show();
            $window.location.href = "/page/" + response.data.formatedName +"/edit";
        },
        function(error, status) {
            $scope.error = error;
            $("#error-alert").show();
            $scope.currentPage.bodyRows.forEach(function (row, rowIndex) {
                row.forEach(function (col, colIndex) {
                    row[colIndex] = col.replace(new RegExp(commaReplacer,"g"), ",")
                });
            });
        });
        $scope.formMethod = "post"
    };

    $scope.updateComponent = function (rowIndex, columnIndex, name, id) {
        var rowKey = rowIndex;
        var columnKey = columnIndex;
        if(id)
        {
            $scope.componentID = "-" + id;
            rowKey = id.split("-")[0];
            columnKey = id.split("-")[1];
        }
        var content = $scope.currentPage.bodyRows[rowKey][columnKey];
        var formData = new FormData();
        formData.append("name", name);
        formData.append("rowIndex", rowIndex);
        formData.append("columnIndex", columnIndex);
        formData.append("component", content);
        formData.append('_method', "PUT");
        $http({
            method: 'post',
            headers: {'Content-Type': undefined},
            url: "/api/page/" + name + "/component",
            data: formData,
            transformRequest: angular.identity
        }).then(
            function(response) {
                $scope.update = $scope.update + "<br>Pati sa anrejistre.";
                $("#update-alert-" + id).show();
            },
            function(error, status) {
                // Handle error here
                $scope.error = error;
                $("#error-alert-" + id).show();
            });

    };

    $scope.hideAlert = function (alertID) {
        $('#' + alertID).hide();
    };

    $scope.updatePreview = function () {
        $scope.rendering = true;

        $timeout(function () {
            html2canvas(document.querySelector("#page-render"), {
                backgroundColor: null,
                removeContainer: false,
                async: true
            }).then(function (canvas) {
                if(canvas !== undefined){
                    $scope.preview = canvas.toDataURL('image/png');
                    $scope.rendering = false;
                    $scope.$apply();
                    console.log("Paj la paret.");
                }
            });
        }, 0);

    };

    $scope.updateTags = function () {
        var tag = null;
        if(typeof $scope.inputTag === 'object'){
            tag = $scope.inputTag;
        }
        else if($scope.inputTag.substr(-1).match(/[,\s\n]/)){
            tag = {name: $scope.inputTag.replace(/[,\s\n]/, "")};
        }
        else {
            return;
        }
        $scope.addTag(tag);
        $scope.inputTag = "";

    };

    $scope.addTag = function (tag) {
        if($scope.currentPage.tags.findIndex(function (taginList) {return taginList.name === tag.name}) === -1){
            $scope.currentPage.tags.push(tag);
            var newTagJson = JSON.stringify(tag);
            $scope.currentPage.tagsFormJson.push(newTagJson);
        }
    };

    $scope.removeTag = function (tag) {
        var tagIndex = $scope.currentPage.tags.findIndex(function (taginList) {return taginList.name === tag.name});
        if(tagIndex !== -1){
            $scope.currentPage.tags.splice(tagIndex, 1);
            $scope.currentPage.tagsFormJson.splice(tagIndex, 1);
        }
    };

    $scope.badgeClass = function (badgeName) {
        var firstCharacterAscii = badgeName.charCodeAt(0);
        var classNum = firstCharacterAscii%6;
        switch(classNum){
            case 0: return "badge-primary";
            case 1: return "badge-success";
            case 2: return "badge-danger";
            case 3: return "badge-warning";
            case 4: return "badge-info";
            case 5: return "badge-dark";
        }
    };

     $http.get("/api/tag").then(function (response) {
         $scope.tags = response.data;
    });


    function dataURItoBlob(dataURI) {
        var byteString = atob(dataURI.split(',')[1]);
        var ab = new ArrayBuffer(byteString.length);
        var ia = new Uint8Array(ab);
        for (var i = 0; i < byteString.length; i++) { ia[i] = byteString.charCodeAt(i); }
        return new Blob([ab], { type: 'image/png' });
    }

});