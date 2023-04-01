//viewAllGroupsController-list

app
		.controller(
				'showAllGroupsController-list',
				function($scope, $http, topicMgmtAppConfig, TopicManagementServices) {

					var counter = 1;
					$scope.group = {};

					$scope.groupObj = {
						"title" : "my title",
						"description" : ""
					};
					$scope.groupsList = [];
					$scope.fetchGroupList = function() {
						// var urrrlll=topicMgmtAppConfig.restServices+"/groups";
						// $http(
						// {
						// method : 'GET',
						// url :urrrlll
						// })
						TopicManagementServices.fetchGroupList().success(
								function(data) {
									// alert("Success : "+data);
									$scope.groupsList = data;

									$scope.next();
								}).error(function(data) {
							alert("Error : " + data);
						});
					};

					$scope.next = function() {
						$scope.group = $scope.groupsList[counter];
						counter = (counter >= $scope.groupsList.length - 1) ? 0
								: (counter + 1);

					};

					$scope.previous = function() {
						$scope.group = $scope.groupsList[counter];
						counter = (counter == 0) ? ($scope.groupsList.length - 1)
								: (counter - 1);

					};

					$scope.fetchGroupList();

					// //////////////////////
					$scope.propertyName = 'dateLastModified';
					$scope.reverse = true;
					// $scope.friends = friends;

					$scope.sortBy = function(propertyName) {
						$scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse
								: false;
						$scope.propertyName = propertyName;
					};

				});
