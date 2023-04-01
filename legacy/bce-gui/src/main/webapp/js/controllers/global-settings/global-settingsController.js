app.controller(
				'global-settingsController',
				function($scope, $http, $log, $location, topicMgmtAppConfig,$uibModal) {
					
		$scope.pageFormData={
				"wordMeaningDbBackupSettings":{
					"pagedData":{
						"pageSize":topicMgmtAppConfig.wordMeaningDbBackupSettings.pagedData.pageSize
					}
				}
		};
					
		//$scope.pageFormData.wordMeaningDbBackupSettings.pagedData.pageSize=topicMgmtAppConfig.wordMeaningDbBackupSettings.pagedData.pageSize;
		
		$scope.savePageSize=function(pSize){
			topicMgmtAppConfig.wordMeaningDbBackupSettings.pagedData.pageSize=$scope.pageFormData.wordMeaningDbBackupSettings.pagedData.pageSize;
		};
				
});