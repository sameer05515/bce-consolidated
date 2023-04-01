//updateTopicController

app.controller('updateTopicController', function($scope,$http,$log,$location,$routeParams,topicMgmtAppConfig) {

  $scope.topicObj={"title":"my title","description":"" , "personal":"false"};
  
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
  
  $scope.fetchTopicObj=function(){	  
	  var urrrlll=topicMgmtAppConfig.restServices+"/topics";
		$http(
				{
					method : 'GET',					
					url :urrrlll+"/"+$routeParams.id
				})
				.success(function(data) {
					//alert(angular.toJson(data));
					$scope.topicObj=data;
					initialTopicObj=data;
				}).error(function(data) {
					alert("Error : "+data);
				});	  
  };
  
  $scope.updateTopicObj=function(){
	  var urrrlll=topicMgmtAppConfig.restServices+"/topics";
		$http(
				{
					method : 'PUT',					
					url :urrrlll,
					data: $scope.topicObj
				})
				.success(function(data) {
					//alert($scope.topicObj.personal);					
					$location.path('/topics-list/'+$routeParams.id);
				}).error(function(data) {							
					alert("Error : "+data.message);
				});
	  //////////////////
	  
  };
  
  
  $scope.fetchTopicObj();
  
  

});
