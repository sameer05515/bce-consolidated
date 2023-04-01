//showAllWordsController

app.controller('showAllWordsController', function($scope,$http, $log,$location,topicMgmtAppConfig,WordMeaningServices) {
	$scope.wordObj={"title":"my word",
			"meaning":["meaning1"],
			"example":["example 1"]};
	
	$scope.wordList=[];
	$scope.showList = true;
	$scope.filteredItems = [];
	
	$scope.showTopicsList = function() {
		$scope.showList = !$scope.showList;
		$log.log("$scope.showList : "+$scope.showList);
		if ($scope.showList) {
			document.getElementById("topicDetailsDiv").classList
			.add("col-lg-8");
			document.getElementById("topicDetailsDiv").classList
			.remove("col-lg-12");
		} else {
			document.getElementById("topicDetailsDiv").classList
			.add("col-lg-12");
			document.getElementById("topicDetailsDiv").classList
			.remove("col-lg-8");
		}
	};
	
	
	$scope.fetchAllWordList=function(){
		$scope.wordList=[];
		
		// WordMeaningServices
		// .fetchAllWordList()
		$http.get(topicMgmtAppConfig.myPagesService+'data/json/word-khajana/khajana-transformed.json')
		.success(
				function(data) {
					// alert("Success : "+data);
					$scope.wordList = data;
					// $scope.sortBy($scope.propertyName);

					$log
					.log("Successfully fetched category list "
							+ " : "
							+ angular
							.toJson($scope.wordList));

				}).error(function(data) {
					$log.log("Error : " + data);
					// alert("Error : " + data);
				});
		
	};
	
	
	$scope.init=function(){
		$scope.fetchAllWordList();
	};
	
	$scope.init();
});