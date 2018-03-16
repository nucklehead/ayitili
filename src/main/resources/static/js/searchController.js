app.controller('searchController', function ($scope, $http, $filter) {

    $scope.inputSearch = "";
    $scope.results = [];

    $scope.search = function () {
        $http.get("/search?text=" + $scope.inputSearch).then(function (response) {
            $scope.results = response.data;
        });
    };

    $scope.gotoResult = function(item, model, label, event){
        var link = ".";
        switch (model["@class"].split(".").pop()){
            case "Page":
                link = "/page/" + model.formatedName;
                break;
            case "Tag":
                break;
            case "DBImage":
                link = "/images/" + model.id + ".jpg";
                break;
            case "Book":
                link = "/books/" + model.id;
                break;
            case "Nav":
                link = model.link;
                break;
            case "Banner":
                link = "/";
                break;
        }
        window.location.href = link;
    };

    $scope.getTemplate = function(model, query){
        var uibTypeaheadHighlight = $filter("uibTypeaheadHighlight");
        var imageId = "";
        var title = "";
        var text = "";

        switch (model["@class"].split(".").pop()){
            case "Page":
                title = model.name;
                imageId = model.thumbnail.id;
                break;
            case "Tag":
                title = model.name;
                break;
            case "DBImage":
                title = model.filename;
                imageId = model.id;
                break;
            case "Book":
                title = model.title;
                text = model.summary;
                imageId = model.image.id;
                break;
            case "Nav":
                title = model.text;
                text = model.link;
                break;
            case "Banner":
                title = model.header;
                text = model.text;
                imageId = model.image.id;
                break;
            default: return "";
        }
        var htmlTemplate =
            '<div class="card">\n' +
            '    <div class="card-body">\n' +
            '        <h5 class="card-title">' + uibTypeaheadHighlight(title, query) + '</h5>\n' +
            (imageId.length === 0 ? '' : '    <img src="/images/' + imageId + '.jpg" width="200">\n') +
            '        <p class="card-text">' + uibTypeaheadHighlight(text, query) + '</p>\n' +
            '    </div>\n' +
            '</div>';
        return htmlTemplate;
    }


});