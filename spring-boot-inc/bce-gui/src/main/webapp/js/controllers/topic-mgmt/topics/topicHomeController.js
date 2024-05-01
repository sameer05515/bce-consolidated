//topicHomeController

app.controller('topicHomeController', function($scope, $http, $location,
    $log, topicMgmtAppConfig,TopicManagementServices) {

$scope.topicObj = {
    "title" : "",
    "description" : "",
    "personal" : false,
    "rating" : 1
};

var initialTopicObj = {};

$scope.maxRatingValue=TopicManagementServices.maxTopicMgmtRatingValue;


$scope.onInit = function () {
    console.log('starting home controller!');
};

});
