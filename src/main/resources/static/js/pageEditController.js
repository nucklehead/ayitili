app.filter('spaceToDash', function () {
    return function (input) {
        if(input !== undefined && input !== null){
            return input.replace(/ /g, '-');
        }
        return "";
    };
});
app.controller('pageEditController', function ($scope, $http, $window) {

    if($scope.pageId){
        $http.get("/api/page/" + $scope.pageId).then(
            function(response) {
                $scope.currentPage = response.data;
            },
            function(data) {
                // Handle error here
            });
    }

    $scope.currentPage = {
        bodyRows:[
            ["Egzanp tèx.", "Egzanp tèx."],
            ["Egzanp tèx.", "Egzanp tèx."],
            ["Egzanp tèx.", "Egzanp tèx."]
        ]
    };

    $scope.components = {};

    $scope.editComponent = function(componentID) {
        var component = $('#' + componentID);
        component.summernote();
        $scope.components[componentID] = component.summernote('code');
    };

    $scope.saveComponent = function(componentID) {
        var component = $('#' + componentID);
        var content = component.summernote('code');
    //    do http
        $scope.cancelEditComponent(componentID);
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
    };

    $scope.addRow = function(rowIndex) {
        $scope.currentPage.bodyRows.splice(rowIndex + 1, 0, ["Egzanp tèx."]);
    };

    $scope.addColumn = function(rowIndex, columnIndex) {
        $scope.currentPage.bodyRows[rowIndex].splice(columnIndex + 1, 0, "Egzanp tèx.");
    };

    $scope.inEditMode = function(componentID) {
        return $scope.components[componentID] !== undefined && $scope.components[componentID] !== null;
    };

    $scope.savePage = function(){
        var formId = 'pageNameForm';
        var path = '/api/page';
        var method = 'POST';
        var formData = new FormData($("#" + formId)[0]);
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
            $scope.currentPage['id'] = response.data.id;
            $("#success-alert").show();
        },
        function(error, status) {
            // Handle error here
            $scope.error = error;
            $("#error-alert").show();
        });
        $scope.formMethod = "post"
    };
});