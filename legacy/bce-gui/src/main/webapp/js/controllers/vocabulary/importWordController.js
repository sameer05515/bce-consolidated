//addWordController

app.controller('importWordController', function($scope,$http,$log,$location,topicMgmtAppConfig,WordMeaningServices) {
	$scope.wordObj={"title":"my word",
			"meaning":[{"value":"meaning1"}],
			"example":[{"value":"example1"}]};
	
	
	$scope.addNewMeaning = function() {
		var newItemNo = $scope.wordObj.meaning.length+1;
		$scope.wordObj.meaning.push({"value":""});
	};

	$scope.removeMeaning = function() {
		var lastItem = $scope.wordObj.meaning.length-1;
		$scope.wordObj.meaning.splice(lastItem);
	};
	
	//removeExample()
	$scope.addNewExample = function() {
		var newItemNo = $scope.wordObj.example.length+1;
		$scope.wordObj.example.push({"value":""});
	};

	$scope.removeExample = function() {
		var lastItem = $scope.wordObj.example.length-1;
		$scope.wordObj.example.splice(lastItem);
	};
	
	$scope.savewordObj=function(){
		
	};
});