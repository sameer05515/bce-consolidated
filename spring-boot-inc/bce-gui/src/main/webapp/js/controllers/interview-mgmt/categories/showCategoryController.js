//viewTopicController-list

app
		.controller(
				'showCategoryController',
				function($scope/* , $http */, $log, $location, $routeParams,
						topicMgmtAppConfig, InterviewManagementServices) {

					/** Variable Declaration start */
					$scope.retrievedCategory = {};
					$scope.showCategoryQuestionAnswers=true;

					/*
					 * $scope.retrievedCategoryObj = { "title" : "my title", "description" : "" };
					 */

					var counter = 1;

					$scope.retrievedCategoryObj = {
						"catID" : "",
						"catgoryName" : null,
						"rating" : 1
					};

					$scope.updateCategoryObj = {
						"catID" : "",
						"catgoryName" : null,
						"rating" : 1
					};

					$scope.questionObj = {
						"question" : null,
						"rating" : 1
					};
					$scope.topicsList = [];

					$scope.groupsList = [];

					$scope.topicGroupsRelationList = [];

					$scope.checked_groups = [];

					$scope.showEditCategorySection = false;

					$scope.showAddQuestionSection = false;

					$scope.maxRatingValue = InterviewManagementServices.maxInterviewMgmtRatingValue;
					/** Variable Declaration end ################################ */

					/** ##################################################################################################### */

					/** Method Declaration start */

					$scope.showAddQuestion = function() {
						$scope.showAddQuestionSection = true;
						$scope.questionObj = {
							"question" : null,
							"rating" : 1
						};
						$log.log("$scope.showAddQuestionSection : "
								+ $scope.showAddQuestionSection);
					};

					$scope.hideAddQuestion = function() {
						$scope.showAddQuestionSection = false;
						$scope.questionObj = {
							"question" : null,
							"rating" : 1
						};
						$log.log("$scope.showAddQuestionSection : "
								+ $scope.showAddQuestionSection);
					};

					$scope.saveQuestion = function() {
						$log.log("Going to update : "
								+ angular.toJson($scope.questionObj));
						InterviewManagementServices.saveQuestion(
								$scope.questionObj, $routeParams.id).success(
								function(data) {
									// $log.log("Success : " + data);
									$log.log("Success : "
											+ angular.toJson(data));

									$scope.fetchCategory();
									$scope.hideAddQuestion();
								}).error(function(data) {
							$log.log("Error : " + data);
						});
					};
					
					// ###############################
					$scope.markReadQuestion = function(questObj) {
						$log.log("Going to mark read : "
								+ angular.toJson(questObj));
						InterviewManagementServices.markReadQuestion(
								questionObj, $routeParams.id,questObj.questionID).success(
								function(data) {
									// $log.log("Success : " + data);
									$log.log("Success : "
											+ angular.toJson(data));

									$scope.fetchCategory();
									$scope.hideAddQuestion();
								}).error(function(data) {
							$log.log("Error : " + data);
						});
					};
					
					//###############################

					// ########################

					$scope.showEditCategory = function() {
						$scope.showEditCategorySection = true;

						$scope.updateCategoryObj = {
							"catID" : $scope.retrievedCategory.catID,
							"catgoryName" : $scope.retrievedCategory.catgoryName,
							"rating" : $scope.retrievedCategory.rating
						};

						$log.log("$scope.showEditCategorySection : "
								+ $scope.showEditCategorySection);
					};

					$scope.hideEditCategory = function() {
						$scope.showEditCategorySection = false;
						$scope.updateCategoryObj = {
							"catID" : "",
							"catgoryName" : null,
							"rating" : 1
						};
						$log.log("$scope.showEditCategorySection : "
								+ $scope.showEditCategorySection);
					};

					$scope.editCategory = function() {
						$log.log("Going to update : "
								+ angular.toJson($scope.updateCategoryObj));
						InterviewManagementServices.updateCategory(
								$scope.updateCategoryObj).success(
								function(data) {
									// $log.log("Success : " + data);
									$log.log("Success : "
											+ angular.toJson(data));

									$scope.fetchCategory();
									$scope.hideEditCategory();
								}).error(function(data) {
							$log.log("Error : " + data);
						});
					};

					$scope.fetchCategory = function() {

						InterviewManagementServices
								.fetchCategory($routeParams.id)
								.success(
										function(data) {
											// $log.log("Success : "+data);
											$scope.retrievedCategoryObj = data;
											$scope.retrievedCategory = $scope.retrievedCategoryObj;

											$log
													.log("Successfully fetched category for id "
															+ $routeParams.id
															+ " : "
															+ angular
																	.toJson(data));
										}).error(function(data) {
									$log.log("Error : " + data);
								});
					};

					// $scope.fetchTopicList = function() {
					//
					// InterviewManagementServices.fetchTopicList().success(
					// function(data) {
					// // $log.log("Success : "+data);
					// $scope.topicsList = data;
					//
					// $scope.next();
					// }).error(function(data) {
					// $log.log("Error : " + data);
					// });
					// };

					$scope.next = function() {
						$scope.retrievedCategory = $scope.topicsList[counter];
						counter = (counter >= $scope.topicsList.length - 1) ? 0
								: (counter + 1);

					};

					$scope.previous = function() {
						$scope.retrievedCategory = $scope.topicsList[counter];
						counter = (counter == 0) ? ($scope.topicsList.length - 1)
								: (counter - 1);

					};
					
					// //////////////////////
					$scope.propertyName = 'dateCreated';
					$scope.reverse = true;
					// $scope.friends = friends;

					$scope.sortBy = function(propertyName) {
						$scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse
								: false;
						$scope.propertyName = propertyName;
					};

					$scope.getSelectedRating = function(rating) {
						console.log(rating);
					}

					// $scope.fetchGroupList = function() {
					// $scope.groupsList = [];
					// InterviewManagementServices.fetchGroupList().success(
					// function(data) {
					// $scope.groupsList = data;
					// // $scope.fetchAndRemoveExistingRelations();
					// });
					// };

					// $scope.resetGroupsAndShowTopicGroupRelations = function()
					// {
					// $scope.groupsList = [];
					// $scope.checked_groups = [];
					// };

					// $scope.fetchTopicGroupRelationList = function() {
					//
					// var topicGroupRelationResource = {};
					// topicGroupRelationResource.id = [];
					// $scope.topicGroupsRelationList = [];
					// // topicGroupRelationResource.groupIdList=[];
					//
					// topicGroupRelationResource.id.push($routeParams.id);
					//
					// InterviewManagementServices
					// .fetchTopicGroupRelationListForTopic(
					// topicGroupRelationResource)
					// .success(
					// function(data) {
					// $log.log("data : "
					// + angular.toJson(data));
					// $log
					// .log("successfully fetched relations for given retrievedCategory id :
					// "
					// + $routeParams.id
					// + ", !!!");
					// $scope.topicGroupsRelationList = data;
					// })
					// .error(
					// function(data) {
					//
					// $log
					// .log("Error in saving relations!!!"
					// + data);
					// });
					//
					// };

					// $scope.filterAlreadyAddedGroup = function(value) {
					// var tgrObj = $scope.topicGroupsRelationList;
					// for (var i = 0; i < tgrObj.length; i++) {
					// // Search every object in the job.data array for a
					// // match.
					// // If found return false to remove this object from
					// // the results
					// if (tgrObj[i].groups.id === value.id) {
					// return false;
					// }
					// }
					// // Otherwise return true to include it
					// return true;
					// };

					// $scope.saveTopicGroupRelationList = function() {
					//
					// if ($scope.checked_groups.length <= 0) {
					// $log.log("Please select any group first!!!");
					// return;
					// }
					//
					// $log.log($scope.checked_groups + "");
					//
					// var topicGroupRelationResource = {};
					// topicGroupRelationResource.topicIdList = [];
					// topicGroupRelationResource.groupIdList = [];
					//
					// topicGroupRelationResource.topicIdList
					// .push($routeParams.id);
					//
					// for (var i = 0; i < $scope.checked_groups.length; i++) {
					// var objeee = angular
					// .fromJson($scope.checked_groups[i]);
					// $log.log("Vlaue is : " + objeee.id + "");
					// topicGroupRelationResource.groupIdList
					// .push(objeee.id);
					// }
					//
					// $log.log(""
					// + angular.toJson(topicGroupRelationResource)
					// + "");
					//
					// InterviewManagementServices
					// .saveTopicGroupRelationList(
					// topicGroupRelationResource)
					// .success(
					// function(data) {
					// $log.log("data : "
					// + angular.toJson(data));
					// $log
					// .log("successfully saved relations, now fetching will be
					// started
					// soon!!!");
					// $scope
					// .fetchTopicGroupRelationList();
					// }).error(function(data) {
					//
					// $log.log("Error in saving relations!!!");
					// });
					//
					// $scope.resetGroupsAndShowTopicGroupRelations();
					//
					// // $log.log("Topic Group relations will be soon
					// // saved!!!");
					//
					// };

					/** Method Declaration end ################################ */

					/** ##################################################################################################### */

					/** Initial Method start */

					$scope.fetchCategory();

					// $scope.fetchTopicGroupRelationList();

					/** Initial Method end ################################ */

				});
