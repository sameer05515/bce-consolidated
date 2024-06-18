app.controller('links-list-controller', function($scope, $http, $log,
		$location, topicMgmtAppConfig, LinkManagementServices) {

	$scope.LinksList = [];

	$scope.fetchLinkList = function() {
		var urrrlll = topicMgmtAppConfig.linkMgmtService + "links/";
		$http({
			method : "GET",
			url : urrrlll
		}).success(function(data) {
			// alert("Success : "+data);
			$scope.LinksList = data;
			// $scope.fetchTopicReads();
			// $scope.sortBy($scope.propertyName);
		}).error(function(data) {
			// alert("Error : " + data);
			$log.log("Error : " + data);
		});
	};

	$scope.init = function() {
		$scope.fetchLinkList();
		// $scope.showTopicsList();

		// if ($scope.filteredItems.length > 0) {
		// $scope.topic = $scope.filteredItems[$scope.counterrr];
		// $scope.setSelected($scope.topic.id);
		// }
	};

	$scope.init();

});