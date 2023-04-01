//topicHomeController

app.controller('eventGroupMgmtController', function ($scope, $http, $location,
    $log, topicMgmtAppConfig, TopicManagementServices, eventGroupMgmtServices) {


    $scope.eventGroupListItems=eventGroupMgmtServices.eventGroupListItems;
    $scope.eventListItems=eventGroupMgmtServices.eventListItems;

    $scope.topicObj = {
        "title": "",
        "description": "",
        "personal": false,
        "rating": 1
    };

    var initialTopicObj = {};

    $scope.maxRatingValue = TopicManagementServices.maxTopicMgmtRatingValue;


    $scope.onInit = function () {
        console.log('starting home controller!');
    };

});
