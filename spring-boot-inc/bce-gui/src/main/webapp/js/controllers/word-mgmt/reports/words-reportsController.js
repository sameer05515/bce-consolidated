//addWordController

app.controller("words-reportsController", function (
	$scope,
	$http,
	$log,
	$location,
	topicMgmtAppConfig,
	TopicManagementServices,
	$uibModal
) {
	$scope.wordReportObj = {
		isError: false,
		data: [
			{ count: 2, lastUpdatedOn: "09-Jul-2020" },
			{ count: 1, lastUpdatedOn: "07-Jul-2020" },
			{ count: 5, lastUpdatedOn: "15-Jun-2020" },
			{ count: 5, lastUpdatedOn: "14-Jun-2020" },
			{ count: 49994, lastUpdatedOn: "09-Jan-2019" },
			{ count: 8102, lastUpdatedOn: "08-Jan-2019" },
		],
		message: "Success",
		timestamp: "2020-07-10T13:18:04.888+0000",
		status: 200,
	};

	/** METADATA Start */
	$scope.settings = {
		showAddNewWordSection: false,
		showEditWordSection: false,
		showSearchedItemsData: false,
		showAllWordsInPagination: true,
		sectionNames: {
			AddNewWord: "AddNewWord",
			SearchedWordItemData: "SearchedWordItemData",
		},
		wordTypes: [
			"noun",
			"pronoun",
			"adjective",
			"verb",
			"adverb",
			"preposition",
			"composition",
			"interrogation",
		],
	};

	$scope.stylesData = {
		templateParentDescriptionStyle: {
			padding: "5px",
		},
		templateSearchedWordDataDescriptionStyle: {
			color: "green",
			"font-size": "15px",
			padding: "5px",
			"border-style": "solid",
			"border-color": "green",
		},
	};

	$scope.getWordReportLastUpdated = function () {
		var urrrlll =
			"http://127.0.0.1:8090/word-meaning-db-backup-service/reports/datewise/LastUpdated";
		$scope.dynamicQueryResponse = {};
		$scope.dynamicTableData = [];
		$scope.showDynamicTableData = false;
		$scope.showDynamicResponseError = false;
		$http({
			method: "GET",
			url: urrrlll,
			//data : $scope.dynamicQueryRequestObject
		})
			.success(function (data) {
				// $scope.dynamicQueryResponse = data;
				$scope.dynamicTableData = data.data;
				$scope.showDynamicTableData = true;
				$scope.showDynamicResponseError = false;
			})
			.error(function (data, status) {
				//alert("Error : "+data.message +"status"+data.status);
				$scope.dynamicQueryResponse = data;
				$scope.showDynamicResponseError = true;
			});
	};

	$scope.getWordReportCreatedOn = function () {
		var urrrlll =
			"http://127.0.0.1:8090/word-meaning-db-backup-service/reports/datewise/LastUpdated?columnName=CREATEDON";
		$scope.dynamicQueryResponse = {};
		$scope.dynamicTableDataCreatedOn = [];
		$scope.showDynamicTableData = false;
		$scope.showDynamicResponseError = false;
		$http({
			method: "GET",
			url: urrrlll,
			//data : $scope.dynamicQueryRequestObject
		})
			.success(function (data) {
				// $scope.dynamicQueryResponse = data;
				$scope.dynamicTableDataCreatedOn = data.data;
				$scope.showDynamicTableData = true;
				$scope.showDynamicResponseError = false;
			})
			.error(function (data, status) {
				//alert("Error : "+data.message +"status"+data.status);
				$scope.dynamicQueryResponse = data;
				$scope.showDynamicResponseError = true;
			});
	};

	$scope.getWordReportReadOn = function () {
		var urrrlll =
			"http://127.0.0.1:8090/word-meaning-db-backup-service/reports/datewise/LastUpdated?columnName=READON";
		$scope.dynamicQueryResponse = {};
		$scope.dynamicTableDataReadOn = [];
		$scope.showDynamicTableData = false;
		$scope.showDynamicResponseError = false;
		$http({
			method: "GET",
			url: urrrlll,
			//data : $scope.dynamicQueryRequestObject
		})
			.success(function (data) {
				//$scope.dynamicQueryResponse = data;
				$scope.dynamicTableDataReadOn = data.data;
				$scope.showDynamicTableData = true;
				$scope.showDynamicResponseError = false;
			})
			.error(function (data, status) {
				//alert("Error : "+data.message +"status"+data.status);
				$scope.dynamicQueryResponse = data;
				$scope.showDynamicResponseError = true;
			});
	};

	/** Topics History*/

	$scope.getTopicsReportLastUpdated = function () {
		var urrrlll =
			"http://127.0.0.1:8090/word-meaning-db-backup-service/reports/datewise/LastUpdated";
		$scope.dynamicQueryResponse = {};
		$scope.dynamicTableDataTopicsLastUpdated = [];
		$scope.showDynamicTableData = false;
		$scope.showDynamicResponseError = false;
		TopicManagementServices.fetchTopicHistory('update')
			.success(function (data) {
				// $scope.dynamicQueryResponse = data;
				$scope.dynamicTableDataTopicsLastUpdated = data;
				$scope.showDynamicTableData = true;
				$scope.showDynamicResponseError = false;
			})
			.error(function (data, status) {
				//alert("Error : "+data.message +"status"+data.status);
				$scope.dynamicQueryResponse = data;
				$scope.showDynamicResponseError = true;
			});
	};

	$scope.getTopicsReportCreatedOn = function () {
		var urrrlll =
			"http://127.0.0.1:8090/word-meaning-db-backup-service/reports/datewise/LastUpdated?columnName=CREATEDON";
		$scope.dynamicQueryResponse = {};
		$scope.dynamicTableDataTopicsCreatedOn = [];
		$scope.showDynamicTableData = false;
		$scope.showDynamicResponseError = false;
		TopicManagementServices.fetchTopicHistory('create')
			.success(function (data) {
				// $scope.dynamicQueryResponse = data;
				$scope.dynamicTableDataTopicsCreatedOn = data;
				$scope.showDynamicTableData = true;
				$scope.showDynamicResponseError = false;
			})
			.error(function (data, status) {
				//alert("Error : "+data.message +"status"+data.status);
				$scope.dynamicQueryResponse = data;
				$scope.showDynamicResponseError = true;
			});
	};

	$scope.getTopicsReportReadOn = function () {
		var urrrlll =
			"http://127.0.0.1:8090/word-meaning-db-backup-service/reports/datewise/LastUpdated?columnName=READON";
		$scope.dynamicQueryResponse = {};
		$scope.dynamicTableDataTopicsReadOn = [];
		$scope.showDynamicTableData = false;
		$scope.showDynamicResponseError = false;
		TopicManagementServices.fetchTopicHistory('read')
			.success(function (data) {
				//$scope.dynamicQueryResponse = data;
				$scope.dynamicTableDataTopicsReadOn = data;
				$scope.showDynamicTableData = true;
				$scope.showDynamicResponseError = false;
			})
			.error(function (data, status) {
				//alert("Error : "+data.message +"status"+data.status);
				$scope.dynamicQueryResponse = data;
				$scope.showDynamicResponseError = true;
			});
	};

	$scope.init = function () {
		$scope.getWordReportLastUpdated();
		$scope.getWordReportCreatedOn();
		$scope.getWordReportReadOn();

		$scope.getTopicsReportReadOn();
		$scope.getTopicsReportCreatedOn();
		$scope.getTopicsReportLastUpdated();
	};

	$scope.init();
});
