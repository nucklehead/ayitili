app.controller('editController', function ($scope, $http) {
    // todo map clicked value to FOrm field
    $scope.object = {};
    $scope.formMethod = "post";
    $scope.showFormModal = function(modalId, method, object){
        $scope.formMethod = method;
        if(object != null){
            $scope.object = object;
        }
        $("#" + modalId).modal("show");
    };

    $scope.hideFormModal = function(modalId){
        $scope.object = {};
        $("#" + modalId).modal("hide");
    };

    $scope.showModal = function(modalId){
        $("#" + modalId).modal("show");
    };

    $scope.hideModal = function(modalId){
        $("#" + modalId).modal("hide");
    };

    $scope.delete = function(path){
        $http.delete(path).then(
            function(response) {
                var splitPath = path.split("/");
                var objectDivId = splitPath[splitPath.length -1];
                $("#" + objectDivId).remove();
            },
            function(data) {
                // Handle error here
            })
    };

    $scope.submitForm = function(formId, path){
        var formData = new FormData($("#" + formId)[0]);
        formData.append('_method', $scope.formMethod);
        formData.append("returnPath", window.location.href);
        if($scope.formMethod === "put"){
            path = path + "/" + $scope.object.id;
        }
        $http({
            method: 'post',
            headers: {'Content-Type': undefined},
            url: path,
            data: formData,
            transformRequest: angular.identity
        }).then(
        function(response) {
            $scope.hideFormModal(formId.replace("-form", ""));

        },
        function(data) {
            // Handle error here
        })
        $scope.formMethod = "post"
    };
});