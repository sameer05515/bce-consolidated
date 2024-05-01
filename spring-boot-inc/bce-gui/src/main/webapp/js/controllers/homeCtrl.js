app.controller('homeCtrl', function ($scope, $http) {

    $scope.sectionInfoArr = [];


    $scope.loadSectionIfo = function () {
        $http.get({
            method: 'POST',
            url: 'data/sectionInfo.json'
        })
        .success(function(data){
            console.log(data);
            $scope.sectionInfoArr=data;
        })

    }

    $scope.onError = function (error) {
        console.log(error);
    }

    $scope.onInit = function () {
        $scope.loadSectionIfo();
    }

    $scope.onInit();

});
