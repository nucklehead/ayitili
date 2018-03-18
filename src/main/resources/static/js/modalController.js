app.controller('modalController', function ($scope, $http, $window) {
    $scope.object = {};
    $scope.formMethod = "post";
    $scope.openModals = 0;
    $scope.showFormModal = function(modalId, method, object){
        $scope.formMethod = method;
        if(object != null){
            $scope.object = object;
            Object.entries($scope.object).forEach(function (KeyValue) {
                var value = KeyValue[1];
                if(value !== null && value.constructor === Array && value[0] !== undefined && value[0].constructor === Object){
                    $scope.object[modalId.replace("api-", "").replace("-edit", "") + "Ids"] = value;
                }
            });
        }
        $scope.showModal(modalId);
    };

    $scope.hideFormModal = function(modalId){
        $scope.object = {};
        $scope.hideModal(modalId);
    };

    $scope.showModal = function(modalId){
        var modal = $("#" + modalId);
        $scope.openModals = $scope.openModals + 1;
        modal.modal("show");
        var parentModal = modal.parents(".modal-content");
        if(parentModal.length){
            parentModal.css({ 'background-color': "rgba(255,255,255,0.7)"});
        }
    };

    $scope.hideModal = function(modalId){
        var modal = $("#" + modalId);
        $scope.openModals = $scope.openModals - 1;
        modal.modal("hide");
        if($scope.openModals > 0 ){
            var parentModal = modal.parents(".modal-content");
            if(parentModal.length){
                parentModal.css({ 'background-color': "rgba(255,255,255,1)"});
            }
            setTimeout(function() {
                $('body').addClass("modal-open");
            }, 500);
        }
    };
});