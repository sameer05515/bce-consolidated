//viewAllViewsController-list

app
		.controller(
				'showAllViewsController-list',
				function($scope, $http, topicMgmtAppConfig) {

					var counter = 1;
					$scope.view = {};

					$scope.viewObj = {
						"title" : "my title",
						"description" : ""
					};
					$scope.viewsList = [];
					$scope.fetchViewList = function() {
						var urrrlll = topicMgmtAppConfig.restServices + "/views";
						$http({
							method : 'GET',
							url : urrrlll
						}).success(function(data) {
							// alert("Success : "+data);
							$scope.viewsList = data;

							$scope.next();
						}).error(function(data) {
							alert("Error : " + data);
						});
					};

					$scope.next = function() {
						$scope.view = $scope.viewsList[counter];
						counter = (counter >= $scope.viewsList.length - 1) ? 0
								: (counter + 1);

					};

					$scope.previous = function() {
						$scope.view = $scope.viewsList[counter];
						counter = (counter == 0) ? ($scope.viewsList.length - 1)
								: (counter - 1);

					};

					$scope.fetchViewList();

					// //////////////////////
					$scope.propertyName = 'dateLastModified';
					$scope.reverse = true;
					// $scope.friends = friends;

					$scope.sortBy = function(propertyName) {
						$scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse
								: false;
						$scope.propertyName = propertyName;
					};

				});
