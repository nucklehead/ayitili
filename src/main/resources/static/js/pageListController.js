app.controller('pageTableController', function ($scope, $http, $window, $compile, DTOptionsBuilder, DTColumnBuilder) {
    var tableLanguage = {
        "sEmptyTable":     "Pa gen done pou tab sa a",
        "sInfo":           "Nap montre _START_ jiska _END_ nan _TOTAL_ atik",
        "sInfoEmpty":      "Nap montre  0 jiska 0 nan 0 atik",
        "sInfoFiltered":   "(triye nan _MAX_ atik an total)",
        "sInfoPostFix":    "",
        "sInfoThousands":  ",",
        "sLengthMenu":     "Motre _MENU_ atik",
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
    $scope.setState = setState;
    $scope.dtInstance = {};
    $scope.pages = {};
    $scope.dtOptions = DTOptionsBuilder.fromSource('/api/page')
        .withLanguage(tableLanguage)
        .withPaginationType('full_numbers')
        .withOption('createdRow', createdRow)
        .withOption('rowCallback', rowCallback);;
    $scope.dtColumns = [
        DTColumnBuilder.newColumn('name').withTitle('Non'),
        DTColumnBuilder.newColumn('formatedName').withTitle('Lyen')
            .renderWith(formatedNameToLink),
        DTColumnBuilder.newColumn("thumbnail").withTitle('Apèsi').notSortable()
            .renderWith(imagesHtml),
        DTColumnBuilder.newColumn(null).withTitle('Aktive/Deaktive').notSortable()
            .renderWith(actionsHtml)
    ];

    function setState(page, state) {
        var action = state? "/activate" : "/deactivate";
        var message = state? "aktive" : "dezaktive";
        $http.post("/api/page/" + page.id + action).then(
            function(response) {
                $scope.message = 'Paj ki rele "' + page.name + '" ' + message + ".";
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
        $scope.pages[data.id] = data;
        if(data.active){
            return '<button class="btn btn-danger" ng-click="setState(pages[\'' + data.id + '\'], false)">' +
                '   <span class="fa fa-power-off"></span>' +
                '</button>';
        }
        return '<button class="btn btn-success" ng-click="setState(pages[\'' + data.id + '\'], true)">' +
            '   <span class="fa fa-power-off"></span>' +
            '</button>';
    }

    function imagesHtml(thumbnail, type, full, meta) {
        return '<a data-toggle="modal" data-target="#page-render-modal">' +
            '<img src="/images/' + thumbnail.id + '.jpg" class="img-thumbnail">' +
            '</a>';
    }

    function formatedNameToLink(formatedName, type, full, meta) {
        return "/api/page/" + formatedName;
    }

    function rowCallback(nRow, page, iDisplayIndex, iDisplayIndexFull) {
        $('td', nRow).unbind('click');
        $('td', nRow).bind('click', function () {
            $scope.$apply(function () {
                $scope.preview = "/images/" + page.thumbnail.id + ".jpg";
            });
        });
        return nRow;
    }
});