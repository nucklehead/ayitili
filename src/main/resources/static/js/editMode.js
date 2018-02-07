app.controller('editController', function ($scope, $http, $window) {

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
});