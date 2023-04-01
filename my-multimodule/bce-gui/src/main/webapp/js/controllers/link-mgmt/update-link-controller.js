app
		.controller(
				'update-link-controller',
				function($scope, $http, $log, $routeParams, $location,
						topicMgmtAppConfig, LinkManagementServices) {

					$scope.linkObj = {
						"url" : "",
						"linkDescription" : ""
					};

					var initialLinkObj = {};

					$scope.maxRatingValue = LinkManagementServices.maxLinkMgmtRatingValue;

					$scope.isDirty = function() {
						// do your logic and return 'true' to display the
						// prompt, or 'false'
						// otherwise.

						var isThereSomeChange = !angular.equals(initialLinkObj,
								$scope.linkObj);
						$log
								.log("--Comparing data to check if there is any change?");
						$log.log("$scope.linkObj : "
								+ angular.toJson($scope.linkObj));
						$log.log("initialLinkObj : "
								+ angular.toJson(initialLinkObj));
						$log.log("isThereSomeChange : " + isThereSomeChange);
						return true;
					};
					
					$scope.fetchLinkObj = function() {
						LinkManagementServices.fetchLinkObj($routeParams.id)
						.success(function(data) {
							// alert(angular.toJson(data));
							$scope.linkObj = data;
							initialLinkObj = data;
						}).error(function(data) {
							alert("Error : " + data);
						});
					};

					$scope.updateLinkObj = function() {
						LinkManagementServices.updateLinkObj($routeParams.id,$scope.linkObj)
						.success(function(data) {
							// alert($scope.topicObj.personal);
							$location.path('/links');
						}).error(function(data) {
							alert("Error : " + data.message);
						});
						// ////////////////

					};

					$scope.init = function() {
						$scope.fetchLinkObj();
						// $scope.showTopicsList();

						// if ($scope.filteredItems.length > 0) {
						// $scope.topic =
						// $scope.filteredItems[$scope.counterrr];
						// $scope.setSelected($scope.topic.id);
						// }
					};

					$scope.init();

				});