//viewAllTopicsController-list

app.controller('showAllTopicsController-list', function($scope,$http,$log,topicMgmtAppConfig,TopicManagementServices) {
	
	var counter=1;
	$scope.topic={};

  $scope.topicObj={"title":"my title","description":""};  
  $scope.topicsList=[];  
  $scope.showPrivateTopics=false;
  
  $scope.maxRatingValue=TopicManagementServices.maxTopicMgmtRatingValue;
  
  $scope.fetchTopicList=function(){	 
			var urrrlll=topicMgmtAppConfig.restServices+"/topics";
			$http(
				{
					method : 'GET',
					url :urrrlll
				})
				.success(function(data) {
					//alert("Success : "+data);
					$scope.topicsList=data;
					
					$scope.next();
				})
				.error(
				function(data) {
					alert("Error : "+data);
				});	 	  
		  };
		  
	$scope.next=function(){
		$scope.topic=$scope.topicsList[counter];
		counter=(counter>=$scope.topicsList.length-1)?0:(counter+1);
		
	};
	
	$scope.previous=function(){
		$scope.topic=$scope.topicsList[counter];
		counter=(counter==0)?($scope.topicsList.length-1):(counter-1);
		
	};
	
	$scope.filterPrivateTopics = function(value) {
//	    var tgrObj = $scope.topicGroupsRelationList;
//	    for (var i = 0; i < tgrObj.length; i++) {
//	        // Search every object in the job.data array for a match. 
//	        // If found return false to remove this object from the results
//	        if (tgrObj[i].groups.id === value.id) {
//	            return false;
//	        }
//	    }
//		return true;
		
		var showValue=$scope.showPrivateTopics;
		//$log.log("value.personal : " + value.personal + " $scope.showPrivateTopics : "+showValue);
		return (showValue||!value.personal);
		
		
	};
  
  
  $scope.fetchTopicList();
  
  ////////////////////////
  $scope.propertyName = 'dateLastModified';
  $scope.reverse = true;
  //$scope.friends = friends;

  $scope.sortBy = function(propertyName) {
    $scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse : false;
    $scope.propertyName = propertyName;
  };
  
  
  

});
