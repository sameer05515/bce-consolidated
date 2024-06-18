app.factory('LinkManagementServices', [
	'$http',
	'$routeParams',
	'topicMgmtAppConfig',
	function($http, $routeParams, topicMgmtAppConfig) {

		var LinkManagementServices = {};

		LinkManagementServices.getStudents = function(a, b) {

			return a + b;
		};

		/**Fetch all Topics, Groups, Views list*/

		LinkManagementServices.fetchLinksList = function() {

			return $http.get(topicMgmtAppConfig.linkMgmtService + "/links/");
		};

		LinkManagementServices.fetchTagList = function() {

			return $http.get(topicMgmtAppConfig.linkMgmtService + "/tags/");
		};

		LinkManagementServices.fetchLinkGroupList = function() {

			return $http.get(topicMgmtAppConfig.linkMgmtService + "/linkgroups/");
		};

		/**Fetch Topic , Group, View object on basis of given ID*/

		LinkManagementServices.fetchLinkObj = function(linkId) {

			return $http.get(topicMgmtAppConfig.linkMgmtService + "/links" + "/"
					+ linkId);
		};

		LinkManagementServices.fetchTagObj = function(tagId) {

			return $http.get(topicMgmtAppConfig.linkMgmtService + "/tags" + "/"
					+ tagId);
		};

		LinkManagementServices.fetchLinkGroupObj = function(linkGroupId) {

			return $http.get(topicMgmtAppConfig.linkMgmtService + "/linkgroups" + "/"
					+ linkGroupId);
		};



		/** ############## SAVE ############## */

		LinkManagementServices.saveLinkObj = function(
				linkObj) {

			var req = {
					method : 'POST',
					url : topicMgmtAppConfig.linkMgmtService + "/links/",
					headers : {
						'Content-Type' : 'application/json'
					},
					data : angular.fromJson(linkObj)
			};

			return $http(req);
		};
		
		
		LinkManagementServices.updateLinkObj = function(
				linkId,linkObj) {

			var req = {
					method : 'PUT',
					url : topicMgmtAppConfig.linkMgmtService + "/links/"+linkId,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : angular.fromJson(linkObj)
			};

			return $http(req);
		};

		/**Save, Fetch TopicGroupRelation */



		LinkManagementServices.next = function(list, counter) {
			// $scope.topic=list[counter];
			counter = (counter >= list.length - 1) ? 0 : (counter + 1);
			return counter;

		};

		LinkManagementServices.previous = function(list, counter) {
			// $scope.topic=$scope.topicsList[counter];
			counter = (counter == 0) ? (list.length - 1) : (counter - 1);
			return counter;

		};

		LinkManagementServices.maxLinkMgmtRatingValue=
			topicMgmtAppConfig.maxLinkMgmtRatingValue;

		return LinkManagementServices;

	} ]);