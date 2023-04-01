//viewTopicController-list

app
		.controller(
				'showTopicController',
				function($scope/* , $http */, $log, $location, $routeParams,
						topicMgmtAppConfig, TopicManagementServices) {

					/** Variable Declaration start */
					$scope.topic = {};

					/*
					 * $scope.topicObj = { "title" : "my title", "description" : "" };
					 */

					var counter = 1;

					$scope.topicObj = {
						"title" : "my title",
						"description" : ""
					};
					$scope.topicsList = [];

					$scope.groupsList = [];

					$scope.topicGroupsRelationList = [];

					$scope.checked_groups = [];
					
					$scope.maxRatingValue=TopicManagementServices.maxTopicMgmtRatingValue;
					
					/** Variable Declaration end ################################ */

					/** ##################################################################################################### */

					/** Method Declaration start */

					$scope.fetchTopicObj = function() {

						TopicManagementServices.fetchTopicObj($routeParams.id)
								.success(function(data) {
									// $log.log("Success : "+data);
									$scope.topicObj = data;
									$scope.topic = $scope.topicObj;
								}).error(function(data) {
									$log.log("Error : " + data);
								});
					};

					$scope.fetchTopicList = function() {

						TopicManagementServices.fetchTopicList().success(
								function(data) {
									// $log.log("Success : "+data);
									$scope.topicsList = data;

									$scope.next();
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

					$scope.fetchGroupList = function() {
						$scope.groupsList = [];
						TopicManagementServices.fetchGroupList().success(
								function(data) {
									$scope.groupsList = data;
									// $scope.fetchAndRemoveExistingRelations();
								});
					};

					$scope.resetGroupsAndShowTopicGroupRelations = function() {
						$scope.groupsList = [];
						$scope.checked_groups = [];
					};

					$scope.fetchTopicGroupRelationList = function() {

						var topicGroupRelationResource = {};
						topicGroupRelationResource.id = [];
						$scope.topicGroupsRelationList = [];
						// topicGroupRelationResource.groupIdList=[];

						topicGroupRelationResource.id.push($routeParams.id);

						TopicManagementServices
								.fetchTopicGroupRelationListForTopic(
										topicGroupRelationResource)
								.success(
										function(data) {
											$log.log("data : "
													+ angular.toJson(data));
											$log
													.log("successfully fetched relations for given topic id : "
															+ $routeParams.id
															+ ", !!!");
											$scope.topicGroupsRelationList = data;
										})
								.error(
										function(data) {

											$log
													.log("Error in saving relations!!!"
															+ data);
										});

					};
					
					$scope.filterAlreadyAddedGroup = function(value) {
					    var tgrObj = $scope.topicGroupsRelationList;
					    for (var i = 0; i < tgrObj.length; i++) {
					        // Search every object in the job.data array for a match. 
					        // If found return false to remove this object from the results
					        if (tgrObj[i].groups.id === value.id) {
					            return false;
					        }
					    }
					    // Otherwise return true to include it
					    return true;
					};

					/*
					 * $scope.fetchAndRemoveExistingRelations= function() {
					 * 
					 * var topicGroupRelationResource = {};
					 * topicGroupRelationResource.id = [];
					 * $scope.topicGroupsRelationList=[];
					 * 
					 * $scope.groupsListAfterRemoveExistingRelations = [];
					 * 
					 * topicGroupRelationResource.id.push($routeParams.id);
					 * 
					 * TopicManagementServices .fetchTopicGroupRelationList(
					 * topicGroupRelationResource) .success( function(data) {
					 * $log.log("data : " + angular.toJson(data)); $log
					 * .log("successfully fetched relations for given topic id :
					 * "+$routeParams.id+", !!!");
					 * $scope.topicGroupsRelationList = data;
					 * 
					 * for (var tObj in $scope.groupsList){
					 * 
					 * var groupNotAdded=true; for(var tgrObj in
					 * $scope.topicGroupsRelationList ){ if(tObj.id ==
					 * tgrObj.groups.id){ groupNotAdded=false; } }
					 * 
					 * if(groupNotAdded){
					 * $scope.groupsListAfterRemoveExistingRelations.push(tObj); } }
					 * 
					 * 
					 * }).error(function(data) {
					 * 
					 * $log.log("Error in saving relations!!!"+data); }); };
					 */

					$scope.saveTopicGroupRelationList = function() {

						if ($scope.checked_groups.length <= 0) {
							$log.log("Please select any group first!!!");
							return;
						}

						$log.log($scope.checked_groups + "");

						var topicGroupRelationResource = {};
						topicGroupRelationResource.topicIdList = [];
						topicGroupRelationResource.groupIdList = [];

						topicGroupRelationResource.topicIdList
								.push($routeParams.id);

						for (var i = 0; i < $scope.checked_groups.length; i++) {
							var objeee = angular
									.fromJson($scope.checked_groups[i]);
							$log.log("Vlaue is : " + objeee.id + "");
							topicGroupRelationResource.groupIdList
									.push(objeee.id);
						}

						$log.log(""
								+ angular.toJson(topicGroupRelationResource)
								+ "");

						TopicManagementServices
								.saveTopicGroupRelationList(
										topicGroupRelationResource)
								.success(
										function(data) {
											$log.log("data : "
													+ angular.toJson(data));
											$log
													.log("successfully saved relations, now fetching will be started soon!!!");
											$scope
													.fetchTopicGroupRelationList();
										}).error(function(data) {

									$log.log("Error in saving relations!!!");
								});

						$scope.resetGroupsAndShowTopicGroupRelations();

						// $log.log("Topic Group relations will be soon
						// saved!!!");

					};

					/** Method Declaration end ################################ */

					/** ##################################################################################################### */

					/** Initial Method start */

					$scope.fetchTopicObj();

					$scope.fetchTopicGroupRelationList();

					/** Initial Method end ################################ */

				});
