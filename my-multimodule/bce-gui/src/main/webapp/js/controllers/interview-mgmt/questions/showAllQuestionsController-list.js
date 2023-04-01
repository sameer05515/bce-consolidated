//viewAllTopicsController-list

app
		.controller(
				'showAllQuestionsController-list',
				function($scope, $http, $log, topicMgmtAppConfig,
						InterviewManagementServices) {

					/** Variable Declaration start */
					var counter = 1;
					$scope.topic = {};

					$scope.topicObj = {
						"catID" : "",
						"catgoryName" : null
					};
					$scope.topicsList = [];
					$scope.showPrivateTopics = false;
					$scope.showCreateNewSection = false;

					/** Variable Declaration end ################################ */

					/** ##################################################################################################### */

					/** Method Declaration start */

					$scope.showCreateSection = function() {
						$scope.showCreateNewSection = true;
						$scope.topicObj = {
							"catID" : "",
							"catgoryName" : ""
						};
						$log.log("$scope.showCreateNewSection : "
								+ $scope.showCreateNewSection);
					};

					$scope.hideCreateSection = function() {
						$scope.showCreateNewSection = false;
						$scope.topicObj.catgoryName = null;
						$log.log("$scope.showCreateNewSection : "
								+ $scope.showCreateNewSection);
					};

					$scope.fetchTopicList = function() {
						InterviewManagementServices.fetchCategoriesList()
								.success(function(data) {
									// alert("Success : "+data);
									$scope.topicsList = data;

									$scope.next();
								}).error(function(data) {
									$log.log("Error : " + data);
								});
					};

					$scope.saveCategory = function() {
						InterviewManagementServices.saveCategory(
								$scope.topicObj).success(function(data) {
							$log.log("Success : " + data);

							$scope.fetchTopicList();
						}).error(function(data) {
							$log.log("Error : " + data);
						});
					};

					$scope.next = function() {
						$scope.topic = $scope.topicsList[counter];
						counter = (counter >= $scope.topicsList.length - 1) ? 0
								: (counter + 1);

					};

					$scope.previous = function() {
						$scope.topic = $scope.topicsList[counter];
						counter = (counter == 0) ? ($scope.topicsList.length - 1)
								: (counter - 1);

					};

					$scope.filterPrivateTopics = function(value) {

						// var showValue=$scope.showPrivateTopics;
						// $log.log("value.personal : " + value.personal + "
						// $scope.showPrivateTopics : "+showValue);
						// return (showValue||!value.personal);

						return true;

					};

					// //////////////////////
					$scope.propertyName = 'dateLastModified';
					$scope.reverse = true;
					// $scope.friends = friends;

					$scope.sortBy = function(propertyName) {
						$scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse
								: false;
						$scope.propertyName = propertyName;
					};

					/** Method Declaration end ################################ */

					/** ##################################################################################################### */

					/** Initial Method start */

					$scope.fetchTopicList();

					/** Initial Method end ################################ */

				});
