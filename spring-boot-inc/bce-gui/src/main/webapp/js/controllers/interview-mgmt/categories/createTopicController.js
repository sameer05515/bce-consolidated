//createTopicController

app.controller('createTopicController', function($scope,$http,$location,$log,topicMgmtAppConfig) {

  $scope.topicObj={"title":"","description":"", "personal":"false"};
  
  var initialTopicObj={};
  
  $scope.isDirty = function () {
      // do your logic and return 'true' to display the prompt, or 'false' otherwise.

	  var isThereSomeChange=!angular.equals(initialTopicObj, $scope.topicObj);
	  $log.log("--Comparing data to check if there is any change?");
	  $log.log("$scope.groupObj : " + angular.toJson($scope.topicObj));
	  $log.log("initialGroupObj : " + angular.toJson(initialTopicObj));
	  $log.log("isThereSomeChange : " + isThereSomeChange);
	  //return isThereSomeChange;
	  //return angular.equals(initialTopicObj, $scope.topicObj);
      return true;
  };
  
  $scope.saveTopicObj=function(){
	  
	  var urrrlll=topicMgmtAppConfig.restServices+"/topics";
		$http(
				{
					method : 'POST',					
					url :urrrlll,					
					data : $scope.topicObj				
				})
				.success(function(data) {					
					//alert("Success : "+data.message);
					$location.path('/topics-list');
				})
				.error(function(data,status) { 
					alert("Error : "+data.message +"status"+data.status);
				});
  };

});
