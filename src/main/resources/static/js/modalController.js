app.controller('modalController', function ($scope, $http, $window) {
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
});