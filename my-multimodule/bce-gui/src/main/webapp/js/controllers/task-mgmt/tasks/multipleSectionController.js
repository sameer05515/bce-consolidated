app.controller('multipleSectionController', function($scope) {
  $scope.msg = "Hello from main controller";
  $scope.hi= function(){console.log('hi');};
});

app.controller('ctrlA', function($scope) {
  $scope.v = "Hello from controller A";
});

app.controller('ctrlB', function($scope) {
  $scope.v = "Hello from controller B";
});