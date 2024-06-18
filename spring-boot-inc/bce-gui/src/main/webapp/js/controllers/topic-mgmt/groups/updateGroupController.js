//updateGroupController

app.controller('updateGroupController', function($scope,$http,$log,$location,$routeParams,topicMgmtAppConfig) {

  $scope.groupObj={"title":"my title","description":""};
  
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
  
  $scope.fetchGroupObj=function(){	  
	  var urrrlll=topicMgmtAppConfig.restServices+"/groups";
		$http(
				{
					method : 'GET',					
					url :urrrlll+"/"+$routeParams.id
				})
				.success(function(data) {
					//alert("Success : "+data);
					$scope.groupObj=data;
					initialGroupObj=data;
				}).error(function(data) {
					alert("Error : "+data);
				});	  
  };
  
  $scope.updateGroupObj=function(){
	  var urrrlll=topicMgmtAppConfig.restServices+"/groups";
		$http(
				{
					method : 'PUT',					
					url :urrrlll,
					data: $scope.groupObj
				})
				.success(function(data) {
					//alert("Success : "+data.message);					
					$location.path('/groups-list/'+$routeParams.id);
				}).error(function(data) {							
					alert("Error : "+data.message);
				});
	  //////////////////
	  
  };
  
  
  $scope.fetchGroupObj();
  
  

});
