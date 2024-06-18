//taskConfigurationController

app.controller('taskConfigurationController', function($scope, $http, $log,
		$location, $routeParams, topicMgmtAppConfig,TasksManagementServices) {

	$scope.topicObj = {
			"title":"",
			"environment":"",
			"taskDescription" : "",
			"frequency" : "",
			"activityType" : "",
			"box" : "",
			"sequence":"",
			"remarks":"",
			"routineScheduleDescription":"",
			"status":"",
			"scheduleDate":"",
			"endDate":"",
			"performedBy":"",
			"taskPriorityGroup":"",
			"nextOccurance":"",
			"highestPoint":"",
			"pointsEarnedToday":"",
			"whatIfNotDoing":"",
			"rating":1
		};

	var initialTopicObj = {};

	$scope.isDirty = function() {
		// do your logic and return 'true' to display the prompt, or 'false'
		// otherwise.

		var isThereSomeChange = !angular.equals(initialTopicObj,
				$scope.topicObj);
		$log.log("--Comparing data to check if there is any change?");
		$log.log("$scope.groupObj : " + angular.toJson($scope.topicObj));
		$log.log("initialGroupObj : " + angular.toJson(initialTopicObj));
		$log.log("isThereSomeChange : " + isThereSomeChange);
		// return isThereSomeChange;
		// return angular.equals(initialTopicObj, $scope.topicObj);
		return true;
	};

	$scope.fetchTopicObj = function() {
		TasksManagementServices.fetchTopicObj($routeParams.id)
		.success(function(data) {
			// alert(angular.toJson(data));
			$scope.topicObj = data;
			initialTopicObj = data;
		}).error(function(data) {
			alert("Error : " + data);
		});
	};

	$scope.updateTopicObj = function() {
		var urrrlll = topicMgmtAppConfig.taskMgmtService + "/tasks/"+$routeParams.id;
		$http({
			method : 'PUT',
			url : urrrlll,
			data : $scope.topicObj
		}).success(function(data) {
			// alert($scope.topicObj.personal);
			$location.path('/tasks-list/' + $routeParams.id);
		}).error(function(data) {
			alert("Error : " + data.message);
		});
		// ////////////////

	};
	
	$scope.getSelectedRating = function (rating) {
        console.log(rating);
    };

	$scope.fetchTopicObj();

});
