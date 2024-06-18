//createViewController

app.controller('createViewController', function($scope,$http,$log,$location,topicMgmtAppConfig) {

  $scope.viewObj={"title":"","description":""};
  
  var initialViewObj={};
  
  $scope.isDirty = function () {
      // do your logic and return 'true' to display the prompt, or 'false' otherwise.

	  var isThereSomeChange=!angular.equals(initialViewObj, $scope.viewObj);
	  $log.log("--Comparing data to check if there is any change?");
	  $log.log("$scope.groupObj : " + $scope.viewObj);
	  $log.log("initialGroupObj : " + initialViewObj);
	  $log.log("isThereSomeChange : " + isThereSomeChange);
	  //return isThereSomeChange;
	  //return angular.equals(initialViewObj, $scope.viewObj);
      return true;
  };
  
  $scope.saveViewObj=function(){
	  
	  var urrrlll=topicMgmtAppConfig.restServices+"/views";
		$http(
				{
					method : 'POST',					
					url :urrrlll,					
					data : $scope.viewObj				
				})
				.success(function(data) {					
					//alert("Success : "+data.message);
					$location.path('/views-list');
				})
				.error(function(data,status) { 
					alert("Error : "+data.message +"status"+data.status);
				});
  };

});
