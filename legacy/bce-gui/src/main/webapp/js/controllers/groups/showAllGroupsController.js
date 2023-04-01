//viewAllGroupsController

app.controller('showAllGroupsController', function($scope,$http,topicMgmtAppConfig) {

  $scope.groupObj={"title":"my title","description":""};  
  $scope.groupsList={};  
  $scope.fetchGroupList=function(){	 
			var urrrlll=topicMgmtAppConfig.restServices+"/groups";
			$http(
				{
					method : 'GET',
					url :urrrlll
				})
				.success(function(data) {
					//alert("Success : "+data);
					$scope.groupsList=data;
				})
				.error(
				function(data) {
					alert("Error : "+data);
				});	 	  
		  };
  
  
  $scope.fetchGroupList();

});
