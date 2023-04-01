//eventController

app.controller('eventController', function ($scope, $http, $location,
    $log, topicMgmtAppConfig, TopicManagementServices, eventGroupMgmtServices,
    EventGroupManagementServices) {


    $scope.eventGroupListItems = eventGroupMgmtServices.eventGroupListItems;
    $scope.eventListItems = eventGroupMgmtServices.eventListItems;
    $scope.eventList = [];

    $scope.topicObj = {
        "title": "",
        "description": "",
        "personal": false,
        "rating": 1
    };

    $scope.getAllEvent = function () {
        EventGroupManagementServices.getAllEvent()
            .success(function (data) {
                $scope.eventList = data.data.results;
                $log.log('All Event : ' + angular
                    .toJson(data));
            })
            .error(function (data) {
                $log.log("Error : " + angular
                    .toJson(data));
            });
    };

    var initialTopicObj = {};

    $scope.maxRatingValue = TopicManagementServices.maxTopicMgmtRatingValue;


    $scope.onInit = function () {

        console.log('starting event controller-->>>>>>>>>>>>>>>>>>>>>!');
        $scope.getAllEvent();
    }();

    //$scope.onInit();

});
