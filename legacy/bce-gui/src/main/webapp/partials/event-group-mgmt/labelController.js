//labelController

app.controller('labelController', function ($scope, $http, $location,
    $log, topicMgmtAppConfig, TopicManagementServices, eventGroupMgmtServices,
    EventGroupManagementServices) {


    $scope.eventGroupListItems = eventGroupMgmtServices.eventGroupListItems;
    $scope.eventListItems = eventGroupMgmtServices.eventListItems;
    $scope.labelList = [];

    $scope.topicObj = {
        "title": "",
        "description": "",
        "personal": false,
        "rating": 1
    };

    $scope.views =
    {
        'ADD': 'ADD',
        'ADD_START': 'ADD_START',
        'EDIT': 'EDIT',
        'EDIT_START': 'EDIT_START',
        'VIEW': 'VIEW'
    };

    $scope.selectedView = $scope.views.ADD;

    $scope.filteredItems = [];

    $scope.counterrr = 0;

    $scope.getAllLabel = function () {
        EventGroupManagementServices.getAllLabel()
            .success(function (data) {
                $scope.labelList = data.data.results;
                $log.log('All Label : ' + angular
                    .toJson(data));
            })
            .error(function (data) {
                $log.log("Error : " + angular
                    .toJson(data));
            });
    };

    $scope.showAt = function (indexVal) {

    };

    var initialTopicObj = {};

    $scope.maxRatingValue = TopicManagementServices.maxTopicMgmtRatingValue;


    $scope.onInit = function () {

        console.log('starting label controller-->>>>>>>>>>>>>>>>>>>>>!');
        $scope.getAllLabel();
    }();

    //$scope.onInit();

});
