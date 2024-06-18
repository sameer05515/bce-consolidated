app.factory('eventGroupMgmtServices', [
	'$http',
	'$routeParams',
	'topicMgmtAppConfig',
	function($http, $routeParams, topicMgmtAppConfig) {

		var eventGroupMgmtServices = {};



        eventGroupMgmtServices.eventGroupListItems=[
            { id: 1, completed: true, name: 'India', date: '2014-10-00' },
            { id: 2, completed: true, name: 'World', date: '2014-10-01' },
            { id: 3, completed: true, name: 'Personal', date: '2014-10-02' },
            { id: 4, completed: true, name: 'Office', date: '2014-10-02' }
        ];

        eventGroupMgmtServices.eventListItems=[
            { id: 1, eventGroupId: 1, name: 'India-History', date: '2014-10-00' },
            { id: 2, eventGroupId: 1, name: 'India-Current-Affairs', date: '2014-10-01' },
            { id: 3, eventGroupId: 2, name: 'India-Important-Dates', date: '2014-10-02' },
            { id: 4, eventGroupId: 1, name: 'World-History', date: '2014-10-00' },
            { id: 5, eventGroupId: 1, name: 'World-Current-Affairs', date: '2014-10-01' },
            { id: 6, eventGroupId: 2, name: 'World-Important-Dates', date: '2014-10-02' },
            { id: 7, eventGroupId: 1, name: 'Personal-Important-Dates', date: '2014-10-01' },
            { id: 8, eventGroupId: 1, name: 'Office-Important-Dates', date: '2014-10-01' }
        ];

		/**Fetch all Words list*/

		eventGroupMgmtServices.fetchAllWordList = function() {

			return $http.get(topicMgmtAppConfig.wordMeaningService + "/words/");
		};

		/**Fetch total Words count*/

		eventGroupMgmtServices.fetchTotalWordCount = function() {

			return $http.get(topicMgmtAppConfig.wordMeaningService + "/words/count");
		};
		
		eventGroupMgmtServices.maxTopicMgmtRatingValue=
			topicMgmtAppConfig.maxTopicMgmtRatingValue;

		return eventGroupMgmtServices;

	} ]);