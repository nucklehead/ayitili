app.controller('formController', function ($scope, $http, $window) {

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
            if(response.data.redirectPath){
                $window.location.href = response.data.redirectPath;
            }
        },
        function(data) {
            // Handle error here
        });
        $scope.formMethod = "post"
    };
});