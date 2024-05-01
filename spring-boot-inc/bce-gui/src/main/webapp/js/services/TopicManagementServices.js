app.factory('TopicManagementServices', [
	'$http',
	'$routeParams',
	'topicMgmtAppConfig',
	function ($http, $routeParams, topicMgmtAppConfig) {

		var TopicManagementServices = {};

		TopicManagementServices.getStudents = function (a, b) {

			return a + b;
		};

		/**Fetch all Topics, Groups, Views list*/

		TopicManagementServices.fetchGroupList = function () {

			return $http.get(topicMgmtAppConfig.restServices + "/groups");
		};

		TopicManagementServices.fetchTopicList = function () {

			return $http.get(topicMgmtAppConfig.restServices + "/topics");
		};

		TopicManagementServices.fetchViewList = function () {

			return $http.get(topicMgmtAppConfig.restServices + "/views");
		};

		/**Fetch Topic , Group, View object on basis of given ID*/

		TopicManagementServices.fetchTopicObj = function (topicId) {

			return $http.get(topicMgmtAppConfig.restServices + "/topics" + "/"
				+ topicId);
		};

		TopicManagementServices.fetchTopicHistory = function (action) {
			//http://127.0.0.1:8090/RestServices/rest/topics/history/update
			return $http.get(topicMgmtAppConfig.restServices + "/topics" + "/history/" + action);

		};

		TopicManagementServices.fetchTopicReads = function (topicId, action) {

			return $http.get(topicMgmtAppConfig.restServices + "/topics" + "/"
				+ topicId + "/history/" + action);
		};

		TopicManagementServices.saveTopicReads = function (topicId, action) {

			var req = {
				method: 'PUT',
				url: topicMgmtAppConfig.restServices + "/topics/" + topicId +
					"/mark/" + action,
				headers: {
					'Content-Type': 'application/json'
				}/*,
					data : angular.fromJson(topicGroupRelationResource)*/
			};

			return $http(req);
		};

		/**Save, Fetch TopicGroupRelation */

		TopicManagementServices.saveTopicGroupRelationList = function (
			topicGroupRelationResource) {

			var req = {
				method: 'POST',
				url: topicMgmtAppConfig.restServices + "/tgservices",
				headers: {
					'Content-Type': 'application/json'
				},
				data: angular.fromJson(topicGroupRelationResource)
			};

			return $http(req);
		};

		TopicManagementServices.fetchTopicGroupRelationListForTopic = function (
			topicGroupRelationResource) {

			var req = {
				method: 'GET',
				url: topicMgmtAppConfig.restServices + "/tgservices/topics",
				headers: {
					'Content-Type': 'application/json'
				},
				params: angular.fromJson(topicGroupRelationResource)
			};

			return $http(req);
		};

		TopicManagementServices.fetchTopicGroupRelationListForGroup = function (
			topicGroupRelationResource) {

			var req = {
				method: 'GET',
				url: topicMgmtAppConfig.restServices + "/tgservices/groups",
				headers: {
					'Content-Type': 'application/json'
				},
				params: angular.fromJson(topicGroupRelationResource)
			};

			return $http(req);
		};

		/**Save, Fetch GroupViewRelation */

		TopicManagementServices.saveGroupViewRelationList = function (
			groupViewRelationResource) {

			var req = {
				method: 'POST',
				url: topicMgmtAppConfig.restServices + "/gvservices",
				headers: {
					'Content-Type': 'application/json'
				},
				data: angular.fromJson(groupViewRelationResource)
			};

			return $http(req);
		};

		TopicManagementServices.fetchGroupViewRelationListForGroup = function (
			groupViewRelationResource) {

			var req = {
				method: 'GET',
				url: topicMgmtAppConfig.restServices + "/gvservices/groups",
				headers: {
					'Content-Type': 'application/json'
				},
				params: angular.fromJson(groupViewRelationResource)
			};

			return $http(req);
		};

		TopicManagementServices.fetchGroupViewRelationListForView = function (
			groupViewRelationResource) {

			var req = {
				method: 'GET',
				url: topicMgmtAppConfig.restServices + "/gvservices/views",
				headers: {
					'Content-Type': 'application/json'
				},
				params: angular.fromJson(groupViewRelationResource)
			};

			return $http(req);
		};

		/*
		 * $http.get('/someUrl', config).then(successCallback,
		 * errorCallback);
		 * 
		 * $http.post('/someUrl', data, config).then(successCallback,
		 * errorCallback);
		 */

		/**
		 * var req = { method: 'POST', url: 'http://example.com', headers: {
		 * 'Content-Type': undefined }, data: { test: 'test' } }
		 * 
		 * $http(req).then(function(){...}, function(){...});
		 */

		TopicManagementServices.next = function (list, counter) {
			// $scope.topic=list[counter];
			counter = (counter >= list.length - 1) ? 0 : (counter + 1);
			return counter;

		};

		TopicManagementServices.previous = function (list, counter) {
			// $scope.topic=$scope.topicsList[counter];
			counter = (counter == 0) ? (list.length - 1) : (counter - 1);
			return counter;

		};

		TopicManagementServices.maxTopicMgmtRatingValue =
			topicMgmtAppConfig.maxTopicMgmtRatingValue;

		return TopicManagementServices;

	}]);