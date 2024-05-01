//viewAllTopicsController-list

app
		.controller(
				'showAllCategoriesController-list',
				function($scope, $http, $log, topicMgmtAppConfig,
						InterviewManagementServices) {

					/** Variable Declaration start */
					var counter = 1;
					$scope.topic = {};

					$scope.topicObj = {
						"catID" : "",
						"catgoryName" : null,
						"rating" : 1
					};
					
					$scope.updateCategoryObj = {
							"catID" : "",
							"catgoryName" : null,
							"rating" : 1
						};
					
					$scope.topicsList = [];
					$scope.showPrivateTopics = false;
					$scope.showCategoryQuestions=false;
					$scope.showCategoryQuestionAnswers=false;
					$scope.showCreateNewCategorySection = false;
					
					$scope.maxRatingValue=InterviewManagementServices.maxInterviewMgmtRatingValue;

					/** Variable Declaration end ################################ */

					/** ##################################################################################################### */

					/** Method Declaration start */

					$scope.showCreateSection = function() {
						$scope.showCreateNewCategorySection = true;
						$scope.topicObj = {
							"catID" : "",
							"catgoryName" : "",
							"rating" : 1
						};
						$log.log("$scope.showCreateNewCategorySection : "
								+ $scope.showCreateNewCategorySection);
					};

					$scope.hideCreateSection = function() {
						$scope.showCreateNewCategorySection = false;
						$scope.topicObj.catgoryName = null;
						$log.log("$scope.showCreateNewCategorySection : "
								+ $scope.showCreateNewCategorySection);
					};
					
					$scope.saveCategory = function() {
						InterviewManagementServices.saveCategory(
								$scope.topicObj).success(function(data) {
							$log.log("Success : " + data);

							$scope.hideCreateSection();
							$scope.fetchTopicList();
						}).error(function(data) {
							$log.log("Error : " + data);
						});
					};
					
					//////////////////////////
					
//					$scope.showEditCategory = function(selectedCategory) {
//						
//						angular.forEach($scope.topicsList,function(categoryyy,key){
//							categoryyy.showEditCategorySection=false;
//						});
//						
//						selectedCategory.showEditCategorySection = true;
//
//						$scope.updateCategoryObj = {
//								"catID" : selectedCategory.catID,
//								"catgoryName" : selectedCategory.catgoryName,
//								"rating" : selectedCategory.rating
//						};
//
//						$log.log("selectedCategory.showEditCategorySection : "
//								+ selectedCategory.showEditCategorySection);
//					};
//					
//					$scope.hideEditCategory = function(selectedCategory) {
//						selectedCategory.showEditCategorySection = false;
//						$scope.updateCategoryObj = {
//							"catID" : "",
//							"catgoryName" : null,
//							"rating" : 1
//						};
//						$log.log("selectedCategory.showEditCategorySection : "
//								+ selectedCategory.showEditCategorySection);
//					};
//					
//					$scope.editCategory = function() {
//						$log.log("Going to update : "
//								+ angular.toJson($scope.updateCategoryObj));
//						InterviewManagementServices.updateCategory(
//								$scope.updateCategoryObj).success(
//								function(data) {
//									// $log.log("Success : " + data);
//									$log.log("Success : "
//											+ angular.toJson(data));
//
//									$scope.fetchTopicList();
//									$scope.hideEditCategory();
//								}).error(function(data) {
//							$log.log("Error : " + data);
//						});
//					};
					
					

					$scope.fetchTopicList = function() {
						
						$scope.topicsList = [];
						
						InterviewManagementServices.fetchCategoriesList()
								.success(function(data) {

									//$scope.topicsList = data;
									
//									for (var i=0; i<data.length; i++){
//										if(axis.isObject(data[i])){
//											data[i].showEditCategorySection=false;
//											data[i].showAddQuestionSection=false;
//											$scope.topicsList.push(data[i]);
//										}
//										
//									}
									
									angular.forEach(data,function(categoryyy,key){
										categoryyy.showEditCategorySection=false;
										categoryyy.showAddQuestionSection=false;
										this.push(categoryyy);
									},$scope.topicsList)
									
									$log
									.log("Successfully fetched category list "
											+ " : "
											+ angular
													.toJson($scope.topicsList));

									//$scope.next();
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
					$scope.propertyName = 'dateCreated';
					$scope.reverse = true;
					// $scope.friends = friends;

					$scope.sortBy = function(propertyName) {
						$scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse
								: false;
						$scope.propertyName = propertyName;
					};
					
					$scope.getSelectedRating = function (rating) {
				        console.log(rating);
				    }

					/** Method Declaration end ################################ */

					/** ##################################################################################################### */

					/** Initial Method start */

					$scope.fetchTopicList();

					/** Initial Method end ################################ */

				});
