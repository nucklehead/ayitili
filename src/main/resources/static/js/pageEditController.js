app.filter('spaceToDash', function () {
    return function (input) {
        if(input !== undefined && input !== null){
            return input.replace(/ /g, '-');
        }
        return "";
    };
});
app.controller('pageEditController', function ($scope, $http, $window, $timeout) {
    $scope.rendering = false;
    $scope.update = "";
    $scope.currentPage = {
        bodyRows:[
            ["Egzanp tèx.", "Egzanp tèx."],
            ["Egzanp tèx.", "Egzanp tèx."],
            ["Egzanp tèx.", "Egzanp tèx."]
        ]
    };

    $timeout(function () {
        if($scope.pageId){
            $http.get("/api/page/" + $scope.pageId).then(
                function(response) {
                    $scope.currentPage = response.data;
                    if($scope.currentPage.bodyRows.length === 0){
                        $scope.addRow(0);
                    }
                },
                function(data) {
                    // Handle error here
                });
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
        formData.set("formatedName", pageSimpleName);
        formData.append("formThumbnail", dataURItoBlob($scope.preview), pageSimpleName + ".png");
        $scope.currentPage.bodyRows.forEach(function (row, rowIndex) {

            row.forEach(function (col, colIndex) {
                row[colIndex] = col.replace(/,/g, window.btoa(pageSimpleName))
            });
            formData.append("bodyRows", row);
        });
        if($scope.currentPage.id){
            path = path + '/' + $scope.currentPage.id
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
            $("#success-alert").removeAttr('hidden');
            $window.location.href = "/page/" + response.data.name +"/edit";
        },
        function(error, status) {
            $scope.error = error;
            $("#error-alert").removeAttr('hidden');
        });
        $scope.formMethod = "post"
    };

    $scope.updatePreview = function () {
        $scope.rendering = true;

        $timeout(function () {
            html2canvas(document.querySelector("#page-render"), {
                backgroundColor: null,
                removeContainer: false,
                async: true
            }).then(function (canvas) {
                $scope.preview = canvas.toDataURL('image/png');
                $scope.rendering = false;
                $scope.$apply();
                console.log("Paj la paret.");
            });
        }, 0);

    };
    $scope.updatePreview();

    function dataURItoBlob(dataURI) {
        var byteString = atob(dataURI.split(',')[1]);
        var ab = new ArrayBuffer(byteString.length);
        var ia = new Uint8Array(ab);
        for (var i = 0; i < byteString.length; i++) { ia[i] = byteString.charCodeAt(i); }
        return new Blob([ab], { type: 'image/png' });
    }

    function updateComponent(rowIndex, columnIndex, name) {
        var content = $scope.currentPage.bodyRows[rowIndex][columnIndex];
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
                $scope.update = $scope.update + "<br>Liy " + rowIndex + ", Kolòn " + columnIndex + " fini.";
                $("#update-alert").removeAttr('hidden');
                if($scope.currentPage.bodyRows[rowIndex +1] && $scope.currentPage.bodyRows[rowIndex + 1][columnIndex + 1]){
                    updateComponent(rowIndex + 1, columnIndex + 1, name);
                }
            },
            function(error, status) {
                // Handle error here
                $scope.error = error;
                $("#error-alert").removeAttr('hidden');
            });

    };


});