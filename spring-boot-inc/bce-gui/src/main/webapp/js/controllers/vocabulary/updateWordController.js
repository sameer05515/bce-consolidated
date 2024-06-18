//updateWordController

app.controller('updateWordController', function($scope,$log,$http,$location,$routeParams,topicMgmtAppConfig,WordMeaningServices) {
	$scope.wordObj={"title":"my word","type":"Verb",
			"meaning":["meaning1"],
			"example":["example 1"]};
	
	$scope.types = ["Noun", "Pronoun", "Adjective","Verb"];
	
	
	$scope.savewordObj=function(){
		
	};
});