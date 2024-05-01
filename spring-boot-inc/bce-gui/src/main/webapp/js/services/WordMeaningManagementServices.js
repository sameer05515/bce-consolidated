app.factory('WordMeaningManagementServices', [
    '$http',
    '$routeParams',
    'topicMgmtAppConfig',
    function ($http, $routeParams, topicMgmtAppConfig) {

        var WordMeaningManagementServices = {};

        /**Fetch Topic , Group, View object on basis of given ID*/

        WordMeaningManagementServices.fetchWordReads = function (wordId) {

            return $http.get(topicMgmtAppConfig.wordMeaningDbBackupService + "words" + "/"
                + wordId + "/reads");
        };

        WordMeaningManagementServices.saveWordReads = function (
            wordId) {

            var req = {
                method: 'PUT',
                url: topicMgmtAppConfig.wordMeaningDbBackupService + "words/" + wordId +
                    "/markread",
                headers: {
                    'Content-Type': 'application/json'
                }/*,
                data : angular.fromJson(topicGroupRelationResource)*/
            };

            return $http(req);
        };



        WordMeaningManagementServices.maxWordMeaningMgmtRatingValue =
            topicMgmtAppConfig.maxTopicMgmtRatingValue;

        return WordMeaningManagementServices;

    }]);