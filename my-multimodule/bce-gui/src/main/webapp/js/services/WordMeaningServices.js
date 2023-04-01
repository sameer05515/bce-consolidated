app.factory('WordMeaningServices', [
	'$http',
	'$routeParams',
	'topicMgmtAppConfig',
	function($http, $routeParams, topicMgmtAppConfig) {

		var WordMeaningServices = {};



		/**Fetch all Words list*/

		WordMeaningServices.fetchAllWordList = function() {

			return $http.get(topicMgmtAppConfig.wordMeaningService + "/words/");
		};

		/**Fetch total Words count*/

		WordMeaningServices.fetchTotalWordCount = function() {

			return $http.get(topicMgmtAppConfig.wordMeaningService + "/words/count");
		};





		/**Fetch word on basis of given ID*/

		WordMeaningServices.fetchWordById = function(wordId) {

			return $http.get(topicMgmtAppConfig.wordMeaningService + "/words" + "/"
					+ wordId);
		};

		WordMeaningServices.saveWord = function(word) {

//			return $http.get(topicMgmtAppConfig.wordMeaningService + "/words" + "/"
//			+ wordId);

			var req = {
					method : 'POST',
					url : topicMgmtAppConfig.wordMeaningService + "/words/",
					headers : {
						'Content-Type' : 'application/json'
					},
					data : angular.fromJson(word)
			};

			return $http(req);
		};
		
		WordMeaningServices.saveWords = function(wordList) {

//			return $http.get(topicMgmtAppConfig.wordMeaningService + "/words" + "/"
//			+ wordId);

			var req = {
					method : 'POST',
					url : topicMgmtAppConfig.wordMeaningService + "/words/multi",
					headers : {
						'Content-Type' : 'application/json'
					},
					data : angular.fromJson(wordList)
			};

			return $http(req);
		};
		
		WordMeaningServices.updateWord = function(word,wordId) {

//			return $http.get(topicMgmtAppConfig.wordMeaningService + "/words" + "/"
//			+ wordId);

			var req = {
					method : 'PUT',
					url : topicMgmtAppConfig.wordMeaningService + "/words"+"/"+wordId,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : angular.fromJson(word)
			};

			return $http(req);
		};
		
		
		WordMeaningServices.deleteWord = function(wordId) {

//			return $http.get(topicMgmtAppConfig.wordMeaningService + "/words" + "/"
//			+ wordId);

			var req = {
					method : 'DELETE',
					url : topicMgmtAppConfig.wordMeaningService + "/words"+"/"+wordId,
					headers : {
						'Content-Type' : 'application/json'
					}
			};

			return $http(req);
		};








		WordMeaningServices.next = function(list, counter) {
			// $scope.topic=list[counter];
			counter = (counter >= list.length - 1) ? 0 : (counter + 1);
			return counter;

		};

		WordMeaningServices.previous = function(list, counter) {
			// $scope.topic=$scope.topicsList[counter];
			counter = (counter == 0) ? (list.length - 1) : (counter - 1);
			return counter;

		};

		WordMeaningServices.maxTopicMgmtRatingValue=
			topicMgmtAppConfig.maxTopicMgmtRatingValue;

		return WordMeaningServices;

	} ]);