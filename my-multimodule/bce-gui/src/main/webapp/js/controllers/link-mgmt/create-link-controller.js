app
		.controller(
				'create-link-controller',
				function($scope, $http, $log, $location, topicMgmtAppConfig,
						LinkManagementServices) {
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

					$scope.saveLinkObj = function() {

						$log.log("going to save link : "
								+ angular.toJson($scope.linkObj));

						var urrrlll = topicMgmtAppConfig.linkMgmtService
								+ "links/";
						/*
						 * $http({ method : 'POST', url : urrrlll, data :
						 * $scope.linkObj })
						 */

						LinkManagementServices.saveLinkObj($scope.linkObj)
								.success(function(data) {
									// LinkManagementServices.saveTopicReads(data.new_topic_id);
									$location.path('/links');
								}).error(function(data, status) {
									// alert("Error : " + data.message +
									// "status" + data.status);
								});
					};

					$scope.getSelectedRating = function(rating) {
						console.log(rating);
					};
				});