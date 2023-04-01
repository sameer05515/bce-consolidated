//updateTaskController

app.controller('updateTaskController', function($scope, $http, $log,
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
			$scope.topicObj = data.data;
			initialTopicObj = data.data;
		}).error(function(data) {
			alert("Error : " + data);
		});
	};

	$scope.updateTopicObj = function() {
		var urrrlll = topicMgmtAppConfig.taskMgmtService + "/tasks/"+$routeParams.id;
		$log.log(angular.toJson($scope.topicObj));
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

	$scope.environmentsData = [];
	$scope.activityTypesData=[];
	$scope.boxsData=[];
	$scope.frequenciesData=[];
	$scope.performedByData=[];
	$scope.sequencesData=[];
	$scope.statusData=[];
	$scope.taskPriorityGroupData=[];

	$scope.fetchConfigurations = function () {
		$http({
			method: 'GET',
			url: topicMgmtAppConfig.taskMgmtService + "/data/environments.json",
			data: $scope.topicObj
		}).success(function (data) {
			$scope.environmentsData = data;
		}).error(function (data, status) {
			alert("Error : " + data);
		});

		$http({
			method: 'GET',
			url: topicMgmtAppConfig.taskMgmtService + "/data/activity-type.json",
			data: $scope.topicObj
		}).success(function (data) {
			$scope.activityTypesData = data;
		}).error(function (data, status) {
			alert("Error : " + data);
		});

		$http({
			method: 'GET',
			url: topicMgmtAppConfig.taskMgmtService + "/data/box.json",
			data: $scope.topicObj
		}).success(function (data) {
			$scope.boxsData = data;
		}).error(function (data, status) {
			alert("Error : " + data);
		});

		$http({
			method: 'GET',
			url: topicMgmtAppConfig.taskMgmtService + "/data/frequency.json",
			data: $scope.topicObj
		}).success(function (data) {
			$scope.frequenciesData = data;
		}).error(function (data, status) {
			alert("Error : " + data);
		});

		$http({
			method: 'GET',
			url: topicMgmtAppConfig.taskMgmtService + "/data/performed-by.json",
			data: $scope.topicObj
		}).success(function (data) {
			$scope.performedByData = data;
		}).error(function (data, status) {
			alert("Error : " + data);
		});

		$http({
			method: 'GET',
			url: topicMgmtAppConfig.taskMgmtService + "/data/sequence.json",
			data: $scope.topicObj
		}).success(function (data) {
			$scope.sequencesData = data;
		}).error(function (data, status) {
			alert("Error : " + data);
		});

		$http({
			method: 'GET',
			url: topicMgmtAppConfig.taskMgmtService + "/data/status.json",
			data: $scope.topicObj
		}).success(function (data) {
			$scope.statusData = data;
		}).error(function (data, status) {
			alert("Error : " + data);
		});

		$http({
			method: 'GET',
			url: topicMgmtAppConfig.taskMgmtService + "/data/task-priority-group.json",
			data: $scope.topicObj
		}).success(function (data) {
			$scope.taskPriorityGroupData = data;
		}).error(function (data, status) {
			alert("Error : " + data);
		});

	};
	
	$scope.init = function () {

		promis=new Promise((resolve,reject)=>{
			$scope.fetchConfigurations();
			resolve('sussess');
		}) .then(result => $scope.fetchTopicObj())
		

		
	};

	$scope.init();

	

});
