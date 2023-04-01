app.factory('TasksManagementServices', [
		'$http',
		'$routeParams',
		'topicMgmtAppConfig',
		function($http, $routeParams, topicMgmtAppConfig) {

			var TasksManagementServices = {};

			TasksManagementServices.getStudents = function(a, b) {

				return a + b;
			};
			
			/**Fetch all Topics, Groups, Views list*/

			TasksManagementServices.fetchAllTasksList = function() {

				return $http.get(topicMgmtAppConfig.taskMgmtService + "/tasks");
			};

			
			
			
			
			/**Fetch Tasks , Group, View object on basis of given ID*/

			TasksManagementServices.fetchTopicObj = function(topicId) {

				return $http.get(topicMgmtAppConfig.taskMgmtService + "/tasks" + "/"
						+ topicId);
			};
			
			
			
			
			
			
			

			TasksManagementServices.next = function(list, counter) {
				// $scope.topic=list[counter];
				counter = (counter >= list.length - 1) ? 0 : (counter + 1);
				return counter;

			};

			TasksManagementServices.previous = function(list, counter) {
				// $scope.topic=$scope.topicsList[counter];
				counter = (counter == 0) ? (list.length - 1) : (counter - 1);
				return counter;

			};
			
			TasksManagementServices.maxTopicMgmtRatingValue=
				topicMgmtAppConfig.maxTopicMgmtRatingValue;

			return TasksManagementServices;

		} ]);