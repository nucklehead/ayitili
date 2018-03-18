app.controller('dbImageableController', function ($scope, $http, $window, $timeout, $compile, DTOptionsBuilder, DTColumnBuilder) {
    var dateLanguage = {
      days: [
          "Dimanch",
          "Lendi",
          "Madi",
          "Mèkredi",
          "Jedi",
          "Vandredi",
          "Samdi",
      ],
      months: [
          "Janvye",
          "Fevriye",
          "Mas",
          "Avril",
          "Me",
          "Jen",
          "Jiyè",
          "Daout",
          "Septanm",
          "Oktòb",
          "Novanm",
          "Desanm",
      ],
    };
    var tableLanguage = {
        "sEmptyTable":     "Pa gen done pou tab sa a",
        "sInfo":           "Nap montre _START_ jiska _END_ nan _TOTAL_ atik",
        "sInfoEmpty":      "Nap montre  0 jiska 0 nan 0 atik",
        "sInfoFiltered":   "(triye nan _MAX_ atik an total)",
        "sInfoPostFix":    "",
        "sInfoThousands":  ",",
        "sLengthMenu":     "Mnotre _MENU_ atik",
        "sLoadingRecords": "Nap chaje...",
        "sProcessing":     "Nap pwosede...",
        "sSearch":         "Chache:",
        "sZeroRecords":    "Pa gen anyen ki koresponn a sa",
        "oPaginate": {
            "sFirst":    "Premye",
            "sLast":     "Dènye",
            "sNext":     "Pwochen",
            "sPrevious": "Anvan"
        },
        "oAria": {
            "sSortAscending":  ": aktive pou triye kolòn nan lòd",
            "sSortDescending": ": aktive pou triye kolòn alanvè"
        }
    };
    $scope.message = '';
    $scope.deleteImage = deleteImage;
    $scope.dtInstance = {};
    $scope.images = {};
    $scope.dtOptions = DTOptionsBuilder.fromSource('/images')
        .withLanguage(tableLanguage)
        .withPaginationType('full_numbers')
        .withOption('createdRow', createdRow)
        .withOption('drawCallback', drawCallback)
        .withOption('rowCallback', rowCallback);
    $scope.dtColumns = [
        DTColumnBuilder.newColumn('filename').withTitle('Non'),
        DTColumnBuilder.newColumn('uploadDate').withTitle('Dat')
            .renderWith(epochToReadable),
        DTColumnBuilder.newColumn('id').withTitle('Lyen')
            .renderWith(idToLink),
        DTColumnBuilder.newColumn("id").withTitle('Apèsi').notSortable()
            .renderWith(imagesHtml),
        DTColumnBuilder.newColumn(null).withTitle('Efase').notSortable()
            .renderWith(actionsHtml)
    ];

    function deleteImage(image) {
        $http.delete("/images/" + image.id ).then(
            function(response) {
                $scope.message = 'Imaj ki rele "' + image.filename + '" la efase.';
                $scope.dtInstance.reloadData();
            },
            function(data) {
                // Handle error here
                $scope.message = JSON.stringify(data);
            });

    }
    function createdRow(row, data, dataIndex) {
        // Recompiling so we can bind Angular directive to the DT
        $compile(angular.element(row).contents())($scope);
    }

    function actionsHtml(data, type, full, meta) {
        $scope.images[data.id] = data;
        return '<button class="btn btn-danger" ng-click="deleteImage(images[\'' + data.id + '\'])">' +
            '   <span class="fa fa-trash"></span>' +
            '</button>';
    }

    function imagesHtml(id, type, full, meta) {
        return '<a data-toggle="modal" data-target="#page-render-modal">' +
            '<img src="/images/' + id + '.jpg" class="img-thumbnail">' +
            '</a>';
    }

    function idToLink(id, type, full, meta) {
        return "<a href='/images/" + id + ".jpg'>/images/" + id + ".jpg</a>";
    }

    function epochToReadable(epoch, type, full, meta) {
        var date = new Date(epoch);
        return dateLanguage.days[date.getDay()] + " " +
            date.getDate() + " " +
            dateLanguage.months[date.getMonth()] + " " +
            date.getFullYear() + ", " +
            date.toLocaleTimeString();
    }

    function rowCallback(nRow, image, iDisplayIndex, iDisplayIndexFull) {
        $('td', nRow).unbind('click');
        $('td', nRow).bind('click', function () {
            $scope.$apply(function () {
                $scope.preview = "/images/" + image.id + ".jpg";
            });
        });
        return nRow;
    }

    function drawCallback(settings) {


    }

});