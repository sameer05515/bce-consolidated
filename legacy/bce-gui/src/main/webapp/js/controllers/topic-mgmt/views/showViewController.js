//viewViewController-list

app
		.controller(
				'showViewController',
				function($scope,$log, $http, $location, $routeParams, topicMgmtAppConfig, TopicManagementServices) {

					$scope.view = {};

					// /////////////////////////////////////////////
					$scope.viewObj = {
						"title" : "my title",
						"description" : ""
					};

					$scope.groupsList = [];
					$scope.checked_group = [];

					$scope.groupViewRelationList = [];

					$scope.fetchViewObj = function() {
						var urrrlll = topicMgmtAppConfig.restServices + "/views";
						$http({
							method : 'GET',
							url : urrrlll + "/" + $routeParams.id
						}).success(function(data) {
							// alert("Success : "+data);
							$scope.viewObj = data;
							$scope.view = $scope.viewObj;
						}).error(function(data) {
							alert("Error : " + data);
						});
					};
					// ///////////////////////////////////////////////

					var counter = 1;

					$scope.viewObj = {
						"title" : "my title",
						"description" : ""
					};
					$scope.viewsList = [];

					$scope.groupViewRelationList = [];

					$scope.fetchViewList = function() {
						var urrrlll = topicMgmtAppConfig.restServices + "/views";
						$http({
							method : 'GET',
							url : urrrlll
						}).success(function(data) {
							// alert("Success : "+data);
							$scope.viewsList = data;

							$scope.next();
						}).error(function(data) {
							alert("Error : " + data);
						});
					};

					// //////////////////////////////
					
					$scope.fetchGroupList = function() {
						$scope.groupsList = [];
						TopicManagementServices.fetchGroupList().success(
								function(data) {
									$scope.groupsList = data;
									// $scope.fetchAndRemoveExistingRelations();
								});
					};

					$scope.saveGroupViewRelationList = function() {
						if ($scope.checked_group.length <= 0) {
							$log.log("Please select any group first!!!");
							return;
						}

						$log.log($scope.checked_group + "");

						var groupViewRelationResource = {};
						groupViewRelationResource.groupIdList = [];
						groupViewRelationResource.viewIdList = [];

						groupViewRelationResource.viewIdList
								.push($routeParams.id);

						for (var i = 0; i < $scope.checked_group.length; i++) {
							var objeee = angular
									.fromJson($scope.checked_group[i]);
							$log.log("Vlaue is : " + objeee.id + "");
							groupViewRelationResource.groupIdList
									.push(objeee.id);
						}

						$log.log("groupViewRelationResource : "
								+ angular.toJson(groupViewRelationResource)
								+ "");

						TopicManagementServices
								.saveGroupViewRelationList(
										groupViewRelationResource)
								.success(
										function(data) {
											$log.log("data : "
													+ angular.toJson(data));
											$log
													.log("successfully saved relations, now fetching will be started soon!!!");
											$scope.fetchGroupViewRelationList();
										}).error(function(data) {

									$log.log("Error in saving relations!!!");
								});

						$scope.resetGroupsAndShowGroupViewRelations();
					};

					$scope.fetchGroupViewRelationList = function() {
						var groupViewRelationResource = {};
						groupViewRelationResource.id = [];
						$scope.groupViewRelationList = [];
						// topicGroupRelationResource.groupIdList=[];

						groupViewRelationResource.id.push($routeParams.id);

						TopicManagementServices
								.fetchGroupViewRelationListForView(
										groupViewRelationResource)
								.success(
										function(data) {
											$log.log("data : "
													+ angular.toJson(data));
											$log
													.log("successfully fetched relations for given group id : "
															+ $routeParams.id
															+ ", !!!");
											$scope.groupViewRelationList = data;
										})
								.error(
										function(data) {

											$log
													.log("Error in saving relations!!!"
															+ data);
										});
					};

					$scope.filterAlreadyAddedGroup = function(value) {
						var grvObj = $scope.groupViewRelationList;
						for (var i = 0; i < grvObj.length; i++) {
							// Search every object in the job.data array for a
							// match.
							// If found return false to remove this object from
							// the results
							if (grvObj[i].groups.id === value.id) {
								return false;
							}
						}
						// Otherwise return true to include it
						return true;
					};

					$scope.resetGroupsAndShowGroupViewRelations = function() {
						$scope.groupsList = [];
						$scope.checked_group = [];
					};

					// /////////////////////////////

					$scope.next = function() {
						$scope.view = $scope.viewsList[counter];
						counter = (counter >= $scope.viewsList.length - 1) ? 0
								: (counter + 1);

					};

					$scope.previous = function() {
						$scope.view = $scope.viewsList[counter];
						counter = (counter == 0) ? ($scope.viewsList.length - 1)
								: (counter - 1);

					};

					$scope.init = function() {
						$scope.fetchViewObj();
						$scope.fetchGroupViewRelationList();
					};

					$scope.init();

				});
