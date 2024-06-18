app.factory('EventGroupManagementServices', [
	'$http', '$log',
	'$routeParams',
	'topicMgmtAppConfig',
	function ($http, $log, $routeParams, topicMgmtAppConfig) {

		var EventGroupManagementServices = {};

		/**  Event  */

		EventGroupManagementServices.getAllEvent = function () {
			$log.log("fetchAllEvent is  being called");
			var req = {
				method: 'GET',
				url: topicMgmtAppConfig.eventGroupManagementService + "event-logging/event/all",
				headers: {
					'Content-Type': 'application/json'
				},
				//data : angular.fromJson(categoryObj)
			};
			return $http(req);
		};

		EventGroupManagementServices.getEventById = function (id) {
			$log.log("getEventById is  being called");
			var req = {
				method: 'GET',
				url: topicMgmtAppConfig.eventGroupManagementService + "event-logging/event/" + id,
				headers: {
					'Content-Type': 'application/json'
				},
				//data : angular.fromJson(categoryObj)
			};
			return $http(req);
		};

		EventGroupManagementServices.addEvent = function (eventObj) {
			$log.log("addEvent is  being called");
			var req = {
				method: 'POST',
				url: topicMgmtAppConfig.eventGroupManagementService + "event-logging/event/",
				headers: {
					'Content-Type': 'application/json'
				},
				data: angular.fromJson(eventObj)
			};
			return $http(req);
		};

		EventGroupManagementServices.updateEventById = function (id, eventObj) {
			$log.log("updateEventById is  being called");
			var req = {
				method: 'PUT',
				url: topicMgmtAppConfig.eventGroupManagementService + "event-logging/event/" + id,
				headers: {
					'Content-Type': 'application/json'
				},
				data: angular.fromJson(eventObj)
			};
			return $http(req);
		};

		/**  Label  */

		EventGroupManagementServices.getAllLabel = function () {
			$log.log("getAllLabel is  being called");
			var req = {
				method: 'GET',
				url: topicMgmtAppConfig.eventGroupManagementService + "event-logging/label/all",
				headers: {
					'Content-Type': 'application/json'
				},
				//data : angular.fromJson(categoryObj)
			};
			return $http(req);
		};

		EventGroupManagementServices.getLabelById = function (id) {
			$log.log("getEventById is  being called");
			var req = {
				method: 'GET',
				url: topicMgmtAppConfig.eventGroupManagementService + "event-logging/label/" + id,
				headers: {
					'Content-Type': 'application/json'
				},
				//data : angular.fromJson(categoryObj)
			};
			return $http(req);
		};

		EventGroupManagementServices.addLabel = function (labelObj) {
			$log.log("addLabel is  being called");
			var req = {
				method: 'POST',
				url: topicMgmtAppConfig.eventGroupManagementService + "event-logging/label/",
				headers: {
					'Content-Type': 'application/json'
				},
				data: angular.fromJson(labelObj)
			};
			return $http(req);
		};

		EventGroupManagementServices.updateLabelById = function (id, labelObj) {
			$log.log("updateLabelById is  being called");
			var req = {
				method: 'PUT',
				url: topicMgmtAppConfig.eventGroupManagementService + "event-logging/label/" + id,
				headers: {
					'Content-Type': 'application/json'
				},
				data: angular.fromJson(labelObj)
			};
			return $http(req);
		};

		/**  Event-Label-Relation  */

		// 		app.post('event-logging/event-label-relaion/',relEventLoggingApi.addEventLabelRelation);
		// app.get('event-logging/event-label-relaion/all',relEventLoggingApi.allEventLabelRelation);
		// app.get('event-logging/event-label-relaion/event/:event_id/label/:label_id',relEventLoggingApi.getEventLabelRelationById);
		// app.get('event-logging/event-label-relaion/event/:event_id',relEventLoggingApi.getEventLabelRelationByEventId);
		// app.get('event-logging/event-label-relaion/label/:label_id',relEventLoggingApi.getEventLabelRelationByLabelId);
		// app.put('event-logging/event-label-relaion/event/:event_id/label/:label_id',relEventLoggingApi.updateEventLabelRelationById);

		EventGroupManagementServices.getAllEventLabelRelation = function () {
			$log.log("getAllEventLabelRelation is  being called");
			var req = {
				method: 'GET',
				url: topicMgmtAppConfig.eventGroupManagementService + "event-logging/event-label-relaion/all",
				headers: {
					'Content-Type': 'application/json'
				},
				//data : angular.fromJson(categoryObj)
			};
			return $http(req);
		};

		EventGroupManagementServices.getEventLabelRelationById = function (event_id, label_id) {
			$log.log("getEventLabelRelationById is  being called");
			var req = {
				method: 'GET',
				url: topicMgmtAppConfig.eventGroupManagementService + "event-logging/event-label-relaion/event/" + event_id + "/label/" + label_id,
				headers: {
					'Content-Type': 'application/json'
				},
				//data : angular.fromJson(categoryObj)
			};
			return $http(req);
		};

		EventGroupManagementServices.getEventLabelRelationByEventId = function (id) {
			$log.log("getEventLabelRelationByEventId is  being called");
			var req = {
				method: 'GET',
				url: topicMgmtAppConfig.eventGroupManagementService + "event-logging/event-label-relaion/event/" + event_id,
				headers: {
					'Content-Type': 'application/json'
				},
				//data : angular.fromJson(categoryObj)
			};
			return $http(req);
		};

		EventGroupManagementServices.getEventLabelRelationByLabelId = function (id) {
			$log.log("getEventLabelRelationByLabelId is  being called");
			var req = {
				method: 'GET',
				url: topicMgmtAppConfig.eventGroupManagementService + "event-logging/event-label-relaion/label/" + label_id,
				headers: {
					'Content-Type': 'application/json'
				},
				//data : angular.fromJson(categoryObj)
			};
			return $http(req);
		};

		EventGroupManagementServices.addEventLabelRelation = function (eventLabelRelationObj) {
			$log.log("addEventLabelRelation is  being called");
			var req = {
				method: 'POST',
				url: topicMgmtAppConfig.eventGroupManagementService + "event-logging/event-label-relaion/",
				headers: {
					'Content-Type': 'application/json'
				},
				data: angular.fromJson(eventLabelRelationObj)
			};
			return $http(req);
		};



		EventGroupManagementServices.updateEventLabelRelationById = function (event_id, label_id, eventLabelRelationObj) {
			$log.log("updateEventLabelRelationById is  being called");
			var req = {
				method: 'PUT',
				url: topicMgmtAppConfig.eventGroupManagementService + "event-logging/event-label-relaion/event/" + event_id + "/label/" + label_id,
				headers: {
					'Content-Type': 'application/json'
				},
				data: angular.fromJson(eventLabelRelationObj)
			};
			return $http(req);
		};


		//EventGroupManagementServices.maxInterviewMgmtRatingValue=10;
		//topicMgmtAppConfig.maxInterviewMgmtRatingValue;

		return EventGroupManagementServices;

	}]);