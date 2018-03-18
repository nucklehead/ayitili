app.value('duScrollBottomSpy', true);
app.controller('booksController', function ($scope, $http,  $document) {

    var spinnerOpts = {
        lines: 14, // The number of lines to draw
        length: 12, // The length of each line
        width: 15, // The line thickness
        radius: 58, // The radius of the inner circle
        scale: 2, // Scales overall size of the spinner
        corners: 1, // Corner roundness (0..1)
        color: '#000000', // CSS color or array of colors
        fadeColor: 'transparent', // CSS color or array of colors
        opacity: 0.2, // Opacity of the lines
        rotate: 0, // The rotation offset
        direction: 1, // 1: clockwise, -1: counterclockwise
        speed: 1, // Rounds per second
        trail: 60, // Afterglow percentage
        fps: 20, // Frames per second when using setTimeout() as a fallback in IE 9
        zIndex: 2e9, // The z-index (defaults to 2000000000)
        className: 'spinner', // The CSS class to assign to the spinner
        top: '50%', // Top position relative to parent
        left: '50%', // Left position relative to parent
        shadow: false, // Box-shadow for the lines
        position: 'absolute' // Element positioning
    };
    var target = $('#end-books')[0];
    var spinner = new Spinner(spinnerOpts).spin(target);

    $scope.books = {};
    $scope.pageSize = 8;
    $scope.pageSizeIncrement = 8;

    $scope.max = 5;
    $scope.isReadonly = false;

    $scope.hoveringOver = function(value, bookId) {
        $scope.books[bookId].overStar = value;
    };

    $scope.getClass = function(rate) {
        if(rate<2) return 'text-warning';
        else if(rate>=2 && rate<3) return 'text-info';
        else if(rate>=3) return 'text-success';
    };

    $scope.showRateLabel = function(overStar) {
        return overStar && !$scope.isReadonly;
    };

    $scope.getBooks = function () {
        spinner.spin();
        $http.get("/api/book/list?size=" + $scope.pageSize).then(function (response) {
            if(response.data.length > Object.keys($scope.books).length){
                $scope.pageSize = $scope.pageSize + $scope.pageSizeIncrement;
            }
            response.data.forEach(function (book) {
                book.rate = 2;
                $scope.books[book.id] = book;
            });
            spinner.stop();
        });
    };
    $scope.getBooks();
    $scope.search = function (inputSearch) {
        return $http.get("/book/search?text=" + inputSearch).then(function (response) {
            return response.data;
        });
    };

    $scope.$on('duScrollspy:becameActive', function($event, $element, $target){
        var hash = $element[0].hash;
        if (hash === '#end-books') {
            $scope.getBooks();
        }
    });



});