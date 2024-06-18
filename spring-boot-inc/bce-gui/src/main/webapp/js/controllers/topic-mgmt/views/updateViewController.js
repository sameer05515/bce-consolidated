//updateViewController

app.controller('updateViewController', function($scope,$http,$log,$location,$routeParams,topicMgmtAppConfig) {

  $scope.viewObj={"title":"my title","description":""};
  
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
  
  $scope.fetchViewObj=function(){	  
	  var urrrlll=topicMgmtAppConfig.restServices+"/views";
		$http(
				{
					method : 'GET',					
					url :urrrlll+"/"+$routeParams.id
				})
				.success(function(data) {
					//alert("Success : "+data);
					$scope.viewObj=data;
					initialViewObj=data;
				}).error(function(data) {
					alert("Error : "+data);
				});	  
  };
  
  $scope.updateViewObj=function(){
	  var urrrlll=topicMgmtAppConfig.restServices+"/views";
		$http(
				{
					method : 'PUT',					
					url :urrrlll,
					data: $scope.viewObj
				})
				.success(function(data) {
					//alert("Success : "+data.message);					
					$location.path('/views-list/'+$routeParams.id);
				}).error(function(data) {							
					alert("Error : "+data.message);
				});
	  //////////////////
	  
  };
  
  
  $scope.fetchViewObj();
  
  

});
