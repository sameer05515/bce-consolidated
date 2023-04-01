//questionsSingleView

app
.controller(
		'questionsSingleViewController',
		function($scope, $http, $log, topicMgmtAppConfig,
				InterviewManagementServices) {

			$scope.topicObj = {
					"title" : "my title",
					"description" : ""
			};
			$scope.topicsList = [];
			$scope.topic = {};
			var counter = 1;
			$scope.filteredItems = [];
			$scope.showList = true;
			$scope.counterrr = 0;
			
			$scope.categoryList=[];
			
			$scope.showPersonalQuestions=false;
			
			$scope.filterPrivateQuestions=function(value){
				
				var showValue=$scope.showPersonalQuestions;
				$log.log("value.personal : " + value.personal + " $scope.showPrivateTopics : "+showValue);
				return (showValue||!value.personal);
			};
			
			///////////
			$scope.fetchCategoryList = function(){
				InterviewManagementServices.fetchCategoriesList()
				.success(function(data) {
					
					angular.forEach(data,function(categoryyy,key){
						categoryyy.showEditCategorySection=false;
						categoryyy.showAddQuestionSection=false;
						this.push(categoryyy);
					},$scope.categoryList)
					
					$log
					.log("Successfully fetched category list - questionsSingleViewController "
							+ " : "
							+ angular
									.toJson($scope.categoryList));

					//$scope.next();
				}).error(function(data) {
					$log.log("Error : " + data);
				});
			};
			//////////

			$scope.showTopicsList = function() {
				$scope.showList = !$scope.showList;
				if ($scope.showList) {
					document.getElementById('topicDetailsDiv').classList
					.add('col-lg-8');
					document.getElementById('topicDetailsDiv').classList
					.remove('col-lg-12');
				} else {
					document.getElementById('topicDetailsDiv').classList
					.add('col-lg-12');
					document.getElementById('topicDetailsDiv').classList
					.remove('col-lg-8');
				}
			};

			$scope.fetchTopicList = function() {
				InterviewManagementServices
				.fetchAnswersJson()
				.success(
						function(data) {
							// alert("Success : "+data);
							$scope.topicsList = data;
							// $scope.sortBy($scope.propertyName);

							$log
							.log("Successfully fetched category list "
									+ " : "
									+ angular
									.toJson($scope.topicsList));

						}).error(function(data) {
							$log.log("Error : " + data);
							// alert("Error : " + data);
						});
			};
			
			$scope.fetchQuestionForCategory = function() {
				InterviewManagementServices
				.fetchAnswersJson()
				.success(
						function(data) {
							// alert("Success : "+data);
							$scope.topicsList = data;
							// $scope.sortBy($scope.propertyName);

							$log
							.log("Successfully fetched category list "
									+ " : "
									+ angular
									.toJson($scope.topicsList));

						}).error(function(data) {
							$log.log("Error : " + data);
							// alert("Error : " + data);
						});
			};

			$scope.idSelectedVote = null;
			$scope.setSelected = function(idSelectedVote) {
				$scope.idSelectedVote = idSelectedVote;
			};

			$scope.showAt = function(indexVal) {
				$scope.counterrr = indexVal;
				$scope.topic = $scope.filteredItems[$scope.counterrr];
				$scope.setSelected($scope.topic.id);
				// $scope.counterrr = ($scope.counterrr >=
				// $scope.filteredItems.length -
				// 1) ? 0
				// : ($scope.counterrr + 1);
			};
			
			$scope.reload = function() {
				
				$scope.showAt($scope.counterrr);
			};

			$scope.next = function() {
				console.log("Inside next function=>>>> $scope.counterrr == "
						+ $scope.counterrr);
				$scope.counterrr = ($scope.counterrr >= $scope.filteredItems.length - 1) ? 0
						: ($scope.counterrr + 1);
				$scope.topic = $scope.filteredItems[$scope.counterrr];
				$scope.setSelected($scope.topic.id);
			};

			$scope.previous = function() {
				$scope.counterrr = ($scope.counterrr == 0) ? ($scope.filteredItems.length - 1)
						: ($scope.counterrr - 1);
				$scope.topic = $scope.filteredItems[$scope.counterrr];
				$scope.setSelected($scope.topic.id);
			};

			// ///////////////////////////////////////////////////
			$scope.timerStarted = false;
			$scope.timerID = null;

			/*
			 * var nextAltFn= function(){ //$scope.next();
			 * console.log("aaaaaaa=>>>>"); };
			 */

			$scope.slideShowStart = function() {
				$scope.timerStarted = true;
				$scope.timerID = setTimeout(function nextAltFn() {
					$scope.next();
					console.log("aaaaaaa=>>>> $scope.counterrr == "
							+ $scope.counterrr);
					$scope.timerID = setTimeout(nextAltFn, 5000);
				}, 5000);
				console
				.log("Timer started : timerID "
						+ $scope.timerID);
				console.log("timerStarted " + $scope.timerStarted);
			};

			$scope.slideShowCancel = function() {
				$scope.timerStarted = false;
				clearTimeout($scope.timerID);
				$scope.timerID = null;

				console
				.log("Timer stopped : timerID "
						+ $scope.timerID);
				console.log("timerStarted " + $scope.timerStarted);
			};
			// //////////////////////////////////////////////////

			// ///////////////////////

			// Initialization
			$scope.onKeyDownResult = "";
			$scope.onKeyUpResult = "";
			$scope.onKeyPressResult = "";

			// Utility functions

			var getKeyboardEventResult = function(keyEvent,
					keyEventDesc) {
				return keyEventDesc
				+ " (keyCode: "
				+ (window.event ? keyEvent.keyCode
						: keyEvent.which) + ")";
			};

			// Event handlers
			$scope.onKeyDown = function($event) {
				$scope.onKeyDownResult = getKeyboardEventResult($event,
				"Key down");
			};

			$scope.onKeyUp = function($event) {
				$scope.onKeyUpResult = getKeyboardEventResult($event,
						"Key up");
			};

			$scope.onKeyPress = function($event) {
				$scope.onKeyPressResult = getKeyboardEventResult(
						$event, "Key press");
			};
			
			$scope.makeTopicPrivate=function(linkedCategoryID,questionIdToMakePrivate){
				$log.log("Going to make private : id : " + questionIdToMakePrivate);
				
				InterviewManagementServices
				.markPrivate(linkedCategoryID,questionIdToMakePrivate)
				.success(
						function(data) {
							// alert("Success : "+data);
//							$scope.topicsList = data;
							// $scope.sortBy($scope.propertyName);

							$log
							.log("Successfully makeTopicPrivate read for linkedCategoryID : "+linkedCategoryID
									+ " : questionForTopicRead : "
									+ questionIdToMakePrivate);

						}).error(function(data) {
							$log.log("Error : " + data);
							// alert("Error : " + data);
						});
			};
			
			$scope.saveTopicReads=function(linkedCategoryID,questionForTopicRead){
				$log.log("Going to mark read : id : " + questionForTopicRead);
				InterviewManagementServices
				.markRead(linkedCategoryID,questionForTopicRead)
				.success(
						function(data) {
							// alert("Success : "+data);
//							$scope.topicsList = data;
							// $scope.sortBy($scope.propertyName);

							$log
							.log("Successfully marked read for linkedCategoryID : "+linkedCategoryID
									+ " : questionForTopicRead : "
									+ questionForTopicRead);

						}).error(function(data) {
							$log.log("Error : " + data);
							// alert("Error : " + data);
						});
			};

			// ////////////////////////

			// //////////////////////
			$scope.propertyName = 'categoryId';
			$scope.reverse = true;
			// $scope.friends = friends;

			$scope.sortBy = function(propertyName) {
				$scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse
						: false;
				$scope.propertyName = propertyName;

				$scope.showAt(1);
			};

			$scope.init = function() {
				$scope.fetchTopicList();
				$scope.showTopicsList();
				$scope.fetchCategoryList();
			};

			$scope.init();

		});
