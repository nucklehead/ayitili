app.controller('editController', function ($scope, $http, $window) {
    var pageFormDropdowns = $("form select");
    pageFormDropdowns.each(function (idx, dropdown) {
        if(dropdown.name.endsWith("Ids")){
            var endpoint = "/api/" + dropdown.name.replace("Ids", "");
            $http.get(endpoint).then(function (response) {
                $scope[dropdown.name] = response.data;
            });
        }
    });

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