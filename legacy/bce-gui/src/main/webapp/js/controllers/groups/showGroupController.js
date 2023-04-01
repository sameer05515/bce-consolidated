//viewGroupController-list

app
		.controller(
				'showGroupController',
				function($scope, $http, $log, $location, $routeParams,
						topicMgmtAppConfig, TopicManagementServices) {

					$scope.group = {};

					// /////////////////////////////////////////////
					$scope.groupObj = {
						"title" : "my title",
						"description" : ""
					};

					$scope.fetchGroupObj = function() {
						var urrrlll = topicMgmtAppConfig.restServices + "/groups";
						$http({
							method : 'GET',
							url : urrrlll + "/" + $routeParams.id
						}).success(function(data) {
							// alert("Success : "+data);
							$scope.groupObj = data;
							$scope.group = $scope.groupObj;
						}).error(function(data) {
							alert("Error : " + data);
						});
					};
					// ///////////////////////////////////////////////

					var counter = 1;

					$scope.groupObj = {
						"title" : "my title",
						"description" : ""
					};
					$scope.groupsList = [];

					$scope.topicsList = [];

					$scope.viewsList = [];

					$scope.topicGroupsRelationList = [];

					$scope.groupViewRelationList = [];

					$scope.checked_topic = [];

					$scope.checked_view = [];

					$scope.fetchGroupList = function() {
						var urrrlll = topicMgmtAppConfig.restServices + "/groups";
						$http({
							method : 'GET',
							url : urrrlll
						}).success(function(data) {
							// alert("Success : "+data);
							$scope.groupsList = data;

							// $scope.next();
						}).error(function(data) {
							alert("Error : " + data);
						});
					};

					$scope.fetchTopicList = function() {
						$scope.topicsList = [];
						TopicManagementServices.fetchTopicList().success(
								function(data) {
									// $log.log("Success : "+data);
									$scope.topicsList = data;

									// $scope.next();
								}).error(function(data) {
							$log.log("Error : " + data);
						});
					};

					$scope.fetchViewList = function() {
						$scope.viewsList = [];
						TopicManagementServices.fetchViewList().success(
								function(data) {
									// $log.log("Success : "+data);
									$scope.viewsList = data;

									// $scope.next();
								}).error(function(data) {
							$log.log("Error : " + data);
						});
					};

					$scope.fetchTopicGroupRelationList = function() {

						var topicGroupRelationResource = {};
						topicGroupRelationResource.id = [];
						$scope.topicGroupsRelationList = [];
						// topicGroupRelationResource.groupIdList=[];

						topicGroupRelationResource.id.push($routeParams.id);

						TopicManagementServices
								.fetchTopicGroupRelationListForGroup(
										topicGroupRelationResource)
								.success(
										function(data) {
											$log.log("data : "
													+ angular.toJson(data));
											$log
													.log("successfully fetched relations for given group id : "
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

					$scope.saveTopicGroupRelationList = function() {

						if ($scope.checked_topic.length <= 0) {
							$log.log("Please select any topic first!!!");
							return;
						}

						$log.log($scope.checked_topic + "");

						var topicGroupRelationResource = {};
						topicGroupRelationResource.topicIdList = [];
						topicGroupRelationResource.groupIdList = [];

						topicGroupRelationResource.groupIdList
								.push($routeParams.id);

						for (var i = 0; i < $scope.checked_topic.length; i++) {
							var objeee = angular
									.fromJson($scope.checked_topic[i]);
							$log.log("Vlaue is : " + objeee.id + "");
							topicGroupRelationResource.topicIdList
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

						$scope.resetTopicsAndShowTopicGroupRelations();

						// $log.log("Topic Group relations will be soon
						// saved!!!");

					};
					
					$scope.saveGroupViewRelationList=function(){
						if ($scope.checked_view.length <= 0) {
							$log.log("Please select any view first!!!");
							return;
						}
						
						$log.log($scope.checked_view + "");
						
						var groupViewRelationResource = {};						
						groupViewRelationResource.groupIdList = [];
						groupViewRelationResource.viewIdList = [];

						groupViewRelationResource.groupIdList
								.push($routeParams.id);

						for (var i = 0; i < $scope.checked_view.length; i++) {
							var objeee = angular
									.fromJson($scope.checked_view[i]);
							$log.log("Vlaue is : " + objeee.id + "");
							groupViewRelationResource.viewIdList
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
											$scope
													.fetchGroupViewRelationList();
										}).error(function(data) {

									$log.log("Error in saving relations!!!");
								});
						
						$scope.resetViewsAndShowGroupViewRelations();
					};
					
					$scope.fetchGroupViewRelationList= function(){
						var groupViewRelationResource = {};						
						groupViewRelationResource.id = [];
						$scope.groupViewRelationList = [];
						// topicGroupRelationResource.groupIdList=[];

						groupViewRelationResource.id.push($routeParams.id);

						TopicManagementServices
								.fetchGroupViewRelationListForGroup(
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
					
					$scope.resetTopicsAndShowTopicGroupRelations = function() {
						$scope.topicsList = [];
						$scope.checked_topic = [];
					};

					$scope.resetViewsAndShowGroupViewRelations = function() {
						$scope.viewsList = [];
						$scope.checked_view = [];
					};

					$scope.filterAlreadyAddedTopic = function(value) {
						var tgrObj = $scope.topicGroupsRelationList;
						for (var i = 0; i < tgrObj.length; i++) {
							// Search every object in the job.data array for a
							// match.
							// If found return false to remove this object from
							// the results
							if (tgrObj[i].topics.id === value.id) {
								return false;
							}
						}
						// Otherwise return true to include it
						return true;
					};

					$scope.filterAlreadyAddedView = function(value) {
						var grvObj = $scope.groupViewRelationList;
						for (var i = 0; i < grvObj.length; i++) {
							// Search every object in the job.data array for a
							// match.
							// If found return false to remove this object from
							// the results
							if (grvObj[i].views.id === value.id) {
								return false;
							}
						}
						// Otherwise return true to include it
						return true;
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

					$scope.init = function() {
						$scope.fetchGroupObj();
						$scope.fetchTopicGroupRelationList();
						$scope.fetchGroupViewRelationList();
					};

					$scope.init();

				});
