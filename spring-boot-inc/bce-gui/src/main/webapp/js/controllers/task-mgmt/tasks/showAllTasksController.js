//viewAllTopicsController

app.controller("showAllTopicsController", function(
	$scope,$log,
	$http,
	topicMgmtAppConfig,
	TopicManagementServices
) {
	$scope.topicObj = {
		title : "my title",
		description : ""
	};
	$scope.topicsList = [];
	$scope.topic = {};
	$scope.topicsReads={};
	//var $scope.counterrr = 1;
	$scope.counterrr = 0;
	$scope.filteredItems = [];
	$scope.showList = true;

	$scope.maxRatingValue = TopicManagementServices.maxTopicMgmtRatingValue;

	$scope.showTopicsList = function() {
		$scope.showList = !$scope.showList;
		if ($scope.showList) {
			document.getElementById("topicDetailsDiv").classList.add("col-lg-8");
			document.getElementById("topicDetailsDiv").classList.remove("col-lg-12");
		} else {
			document.getElementById("topicDetailsDiv").classList.add("col-lg-12");
			document.getElementById("topicDetailsDiv").classList.remove("col-lg-8");
		}
	};

	$scope.fetchTopicList = function() {
		var urrrlll = topicMgmtAppConfig.restServices + "/topics";
		$http({
			method : "GET",
			url : urrrlll
		})
			.success(function(data) {
				// alert("Success : "+data);
				$scope.topicsList = data;
				//$scope.fetchTopicReads();
			// $scope.sortBy($scope.propertyName);
			})
			.error(function(data) {
//				alert("Error : " + data);
				$log.log("Error : " + data);
			});
	};

	//////////// Marking and Fetching reads - start

	$scope.fetchTopicReads = function() {
		TopicManagementServices.fetchTopicReads($scope.topic.id)
			.success(function(data) {
				// alert("Success : "+data);
				$scope.topicsReads = data;
			// $scope.sortBy($scope.propertyName);
			})
			.error(function(data) {
				//alert("Error : " + data);
				$log.log("Error : " + data);
			});
	};
	
	$scope.saveTopicReads = function() {
		TopicManagementServices.saveTopicReads($scope.topic.id)
			.success(function(data) {
				// alert("Success : "+data);
				//$scope.topicsReads = data;
				$scope.fetchTopicReads();
			// $scope.sortBy($scope.propertyName);
			})
			.error(function(data) {
				alert("Error : " + data);
			});
	};

	//////////Marking and Fetching reads - end

	$scope.idSelectedVote = null;
	$scope.setSelected = function(idSelectedVote) {
		$scope.idSelectedVote = idSelectedVote;
	};

	$scope.showAt = function(indexVal) {
		$scope.counterrr = indexVal;
		$scope.topic = $scope.filteredItems[$scope.counterrr];
		$scope.setSelected($scope.topic.id);
		$scope.fetchTopicReads();
	// $scope.counterrr = ($scope.counterrr >= $scope.filteredItems.length -
	// 1) ? 0
	// : ($scope.counterrr + 1);
	};

	$scope.next = function() {
		$scope.counterrr = $scope.counterrr >= $scope.filteredItems.length - 1
			? 0
			: $scope.counterrr + 1;
		$scope.topic = $scope.filteredItems[$scope.counterrr];
		$scope.setSelected($scope.topic.id);
		$scope.fetchTopicReads();
	};

	$scope.previous = function() {
		$scope.counterrr = $scope.counterrr == 0
			? $scope.filteredItems.length - 1
			: $scope.counterrr - 1;
		$scope.topic = $scope.filteredItems[$scope.counterrr];
		$scope.setSelected($scope.topic.id);
		$scope.fetchTopicReads();
	};

	$scope.timerStarted = false;
	$scope.timerID = null;

	/* var nextAltFn= function(){
						//$scope.next();
						console.log("aaaaaaa=>>>>");
					}; */

	$scope.slideShowStart = function() {
		$scope.timerStarted = true;
		$scope.timerID = setTimeout(function nextAltFn() {
			$scope.next();
			console.log("aaaaaaa=>>>> $scope.counterrr == " + $scope.counterrr);
			$scope.timerID = setTimeout(nextAltFn, 5000);
		}, 5000);
		console.log("Timer started : timerID " + $scope.timerID);
		console.log("timerStarted " + $scope.timerStarted);
	};

	$scope.slideShowCancel = function() {
		$scope.timerStarted = false;
		clearTimeout($scope.timerID);
		$scope.timerID = null;

		console.log("Timer stopped : timerID " + $scope.timerID);
		console.log("timerStarted " + $scope.timerStarted);
	};

	// ///////////////////////

	// Initialization
	$scope.onKeyDownResult = "";
	$scope.onKeyUpResult = "";
	$scope.onKeyPressResult = "";

	// Utility functions

	var getKeyboardEventResult = function(keyEvent, keyEventDesc) {
		return (
			keyEventDesc +
			" (keyCode: " +
			(window.event ? keyEvent.keyCode : keyEvent.which) +
			")"
		);
	};

	// Event handlers
	$scope.onKeyDown = function($event) {
		$scope.onKeyDownResult = getKeyboardEventResult($event, "Key down");
	};

	$scope.onKeyUp = function($event) {
		$scope.onKeyUpResult = getKeyboardEventResult($event, "Key up");
	};

	$scope.onKeyPress = function($event) {
		$scope.onKeyPressResult = getKeyboardEventResult($event, "Key press");
	};

	// ////////////////////////

	// //////////////////////
	$scope.propertyName = "dateLastModified";
	$scope.reverse = true;
	// $scope.friends = friends;

	$scope.sortBy = function(propertyName) {
		$scope.reverse = $scope.propertyName === propertyName ? !$scope.reverse : false;
		$scope.propertyName = propertyName;

		$scope.showAt(1);
	};

	$scope.init = function() {
		$scope.fetchTopicList();
		$scope.showTopicsList();
		//alert('$scope.filteredItems.length'+$scope.filteredItems.length);
		if ($scope.filteredItems.length > 0) {
			$scope.topic = $scope.filteredItems[$scope.counterrr];
			$scope.setSelected($scope.topic.id);
		}
	};

	/* $scope.$watch('filteredItems', function (oldVal, newVal) {
						if (newVal.length>0) {
							$scope.counterrr=0;
							$scope.topic = $scope.filteredItems[$scope.counterrr];
							$scope.setSelected($scope.topic.id);
						}
					}); */

	$scope.init();
});