//createGroupController

app.controller('createGroupController', function($scope,$http,$log,$location,topicMgmtAppConfig) {

  $scope.groupObj={"title":"","description":""};
  
  var initialGroupObj={};
  
  $scope.isDirty = function () {
      // do your logic and return 'true' to display the prompt, or 'false' otherwise.

	  var isThereSomeChange=!angular.equals(initialGroupObj, $scope.groupObj);
	  $log.log("--Comparing data to check if there is any change?");
	  $log.log("$scope.groupObj : " + $scope.groupObj);
	  $log.log("initialGroupObj : " + initialGroupObj);
	  $log.log("isThereSomeChange : " + isThereSomeChange);
	  //return isThereSomeChange;
      return true;
  };
  
  $scope.saveGroupObj=function(){
	  
	  var urrrlll=topicMgmtAppConfig.restServices+"/groups";
		$http(
				{
					method : 'POST',					
					url :urrrlll,					
					data : $scope.groupObj				
				})
				.success(function(data) {					
					//alert("Success : "+data.message);
					$location.path('/groups-list');
				})
				.error(function(data,status) { 
					alert("Error : "+data.message +"status"+data.status);
				});
  };

});
