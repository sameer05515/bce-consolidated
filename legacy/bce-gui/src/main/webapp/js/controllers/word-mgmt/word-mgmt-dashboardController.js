//addWordController

app
	.controller(
		'word-mgmt-dashboardController',
		function ($scope, $http, $log, $location, topicMgmtAppConfig, WordMeaningManagementServices, $uibModal) {
			$scope.wordObj = {
				"_id": "5b6f9e8c89c9d3246c9dea72",
				"word": "zephyr",
				"type": "noun",
				"createdDate": "2018-12-31T16:55:54.439+0000",
				"lastModified": "2018-12-31T16:55:54.388+0000",
				"lastRead": "2018-12-31T16:55:54.439+0000",
				"tags": ["1036-words-list"],
				"readHistory": ["2018-12-31T16:55:54.439+0000"],
				"meanings": [{
					"id": 1,
					"meaning": "a gentle breeze"
				}],
				"examples": [{
					"id": 1,
					"example": "If not for the zephyrs that were blowing and cooling us, our room would've been unbearably hot."
				}]
			};
			$scope.types = ["noun", "pronoun", "adjective", "verb"];

			/** METADATA Start */
			$scope.settings = {
				"showAddNewWordSection": false,
				"showEditWordSection": false,
				"showSearchedItemsData": false,
				"showAllWordsInPagination": true,
				"sectionNames": {
					"AddNewWord": "AddNewWord",
					"SearchedWordItemData": "SearchedWordItemData"
				},
				"wordTypes": ["noun", "pronoun", "adjective", "verb", "adverb", "preposition", "composition", "interrogation"]
			};

			$scope.stylesData = {
				"templateParentDescriptionStyle": {

					"padding": "5px"

				},
				"templateSearchedWordDataDescriptionStyle": {
					"color": "green",
					"font-size": "15px",
					"padding": "5px",
					"border-style": "solid",
					"border-color": "green"
				}
			};

			$scope.pageFormData = {
				searchText: "",
				lastSearchedWord: "",
				templateAddWordData: {
					"id": 0,
					"word": "",
					"unique_name": "",
					"type": "",
					"details": "",
					"created_on": "",
					"updated_on": "",
					"last_read": "",
					"meanings": [],
					"examples": []
				},
				templateEditWordData: {
					"id": 431,
					"word": "zephyr",
					"unique_name": "zephyr",
					"type": "noun",
					"details": "&#x3C;div class=&#x22;lW8rQd&#x22;&#x3E;&#x3C;div class=&#x22;vpx4Fd&#x22;&#x3E;&#x3C;div class=&#x22;pgRvse vdBwhd&#x22;&#x3E;&#x3C;em&#x3E;noun&#x3C;/em&#x3E;&#x3C;/div&#x3E;&#x3C;div class=&#x22;xpdxpnd vk_gy&#x22; data-mh=&#x22;-1&#x22; aria-hidden=&#x22;true&#x22;&#x3E;&#x26;nbsp;&#x3C;/div&#x3E;&#x3C;/div&#x3E;&#x3C;/div&#x3E;&#x3C;ol class=&#x22;eQJLDd&#x22;&#x3E;&#x3C;li&#x3E;&#x3C;div class=&#x22;vmod&#x22;&#x3E;&#x3C;div class=&#x22;thODed Uekwlc XpoqFe&#x22;&#x3E;&#x3C;div data-topic=&#x22;&#x22;&#x3E;&#x3C;div&#x3E;1.&#x3C;/div&#x3E;&#x3C;div&#x3E;&#x3C;div class=&#x22;QIclbb XpoqFe&#x22;&#x3E;&#x3C;div&#x3E;&#x3C;span class=&#x22;mQo3nc hsL7ld&#x22;&#x3E;LITERARY&#x3C;/span&#x3E;&#x3C;/div&#x3E;&#x3C;div data-dobid=&#x22;dfn&#x22;&#x3E;a soft gentle breeze.&#x3C;/div&#x3E;&#x3C;/div&#x3E;&#x3C;/div&#x3E;&#x3C;/div&#x3E;&#x3C;div&#x3E;&#x26;nbsp;&#x3C;/div&#x3E;&#x3C;/div&#x3E;&#x3C;/div&#x3E;&#x3C;/li&#x3E;&#x3C;li&#x3E;&#x3C;div class=&#x22;vmod&#x22;&#x3E;&#x3C;div class=&#x22;thODed Uekwlc XpoqFe&#x22;&#x3E;&#x3C;div data-topic=&#x22;&#x22;&#x3E;&#x3C;div&#x3E;2.&#x3C;/div&#x3E;&#x3C;div&#x3E;&#x3C;div class=&#x22;QIclbb XpoqFe&#x22;&#x3E;&#x3C;div&#x3E;&#x3C;span class=&#x22;mQo3nc hsL7ld&#x22;&#x3E;HISTORICAL&#x3C;/span&#x3E;&#x3C;/div&#x3E;&#x3C;div data-dobid=&#x22;dfn&#x22;&#x3E;a fine cotton gingham.&#x3C;/div&#x3E;&#x3C;/div&#x3E;&#x3C;/div&#x3E;&#x3C;/div&#x3E;&#x3C;/div&#x3E;&#x3C;/div&#x3E;&#x3C;/li&#x3E;&#x3C;/ol&#x3E;",
					"created_on": "2019-01-08T18:24:15.000+0000",
					"updated_on": "2019-01-08T18:24:15.000+0000",
					"last_read": "2019-01-08T18:24:15.000+0000",
					"meanings": [],
					"examples": []
				},
				pagedData: {
					"pageSize": 1000,
					"pageNo": 0,
					"enablePrevButton": false,
					"enableNextButton": true,
					"selectedWordDataItem": {},
					items: []
				},
				searchedItems: [],

				criteriaObj: {
					"lastReadOnDaysBack": 10,
					"lastUpdatedOnDaysBack": 0,
					"createdOnDaysBack": 0,
					"pageSize": 1000,
					"slideshowDuration": 5000,
					"selectedOrderByDate": "createdOn",
					"selectedOrderByDateDirection": "ASC"
				}
			};

			$scope.filteredItems = [];
			$scope.showList = true;
			$scope.counterrr = 0;

			$scope.exportWordMgmt = function () {
				$log.log("Going to call export service : ");

				$http(
					{
						method: "GET",
						url: topicMgmtAppConfig.wordMeaningDbBackupService
							+ 'export',
						//data : angular.toJson($scope.pageFormData.templateAddWordData)
					})
					.success(
						function (data) {
							//											if (data.status == "200") {
							//												$scope.pageFormData.templateAddWordData = data.data;
							////												$scope.pageFormData.lastSearchedWord = searchTxt;
							////												$scope
							////														.showSection($scope.settings.sectionNames.SearchedWordItemData);
							//											} else if (data.status == "fail") {
							//												alert("Error : Message " + data.message + " data.status : " + data.status);
							//											}
						}).error(function (data) {
							alert("Error : " + data.message + "status" + data.status);
						});
			};


			$scope.showSection = function (sectionName, selection = true) {
				// alert('settings.showAddNewWordSection'+$scope.settings.showAddNewWordSection);

				$log.log("Going to show section : " + angular.toJson(sectionName));
				if (sectionName != null) {
					if (sectionName === $scope.settings.sectionNames.AddNewWord) {
						if (selection) {
							$scope.settings.showAddNewWordSection = true;
							$scope.settings.showSearchedItemsData = false;
						} else {

						}

						$scope.templateAddWordData = {
							"id": 0,
							"word": "",
							"unique_name": "",
							"type": "",
							"details": "",
							"created_on": "",
							"updated_on": "",
							"last_read": "",
							"meanings": [],
							"examples": []
						};

					} else if (sectionName === $scope.settings.sectionNames.SearchedWordItemData) {
						$scope.settings.showAddNewWordSection = false;
						$scope.settings.showSearchedItemsData = true;
					} else {
						$log.log("invalid section name provided "
							+ sectionName);
					}
				} else {
					$log.log("Empty or null section name provided "
						+ sectionName);
				}
			};

			$scope.saveWordObj = function (wordObject) {

				$log.log("$scope.groupObj : " + angular.toJson($scope.pageFormData.templateAddWordData));
				$http(
					{
						method: "POST",
						url: topicMgmtAppConfig.wordMeaningDbBackupService
							+ 'words',
						data: angular.toJson($scope.pageFormData.templateAddWordData)
					})
					.success(
						function (data) {
							if (data.status == "200") {
								$scope.pageFormData.templateAddWordData = data.data;
								//												$scope.pageFormData.lastSearchedWord = searchTxt;
								//												$scope
								//														.showSection($scope.settings.sectionNames.SearchedWordItemData);
								$scope.settings.showAddNewWordSection = false;
								$scope.settings.showSearchedItemsData = true;
							} else if (data.status == "fail") {
								alert("Error : Message " + data.message + " data.status : " + data.status);
							}
						}).error(function (data) {
							alert("Error : " + data.message + "status" + data.status);
						});
			};

			$scope.updateWord = function (wordObjectForEdit) {
				if (wordObjectForEdit) {
					$scope.pageFormData.templateEditWordData = wordObjectForEdit;
					$log.log("Will open a modal popup to edit word : " + angular.toJson($scope.pageFormData.templateEditWordData));
					$scope.settings.showEditWordSection = true;
					$scope.titleOfEditWordDialog = "Edit word : " + $scope.pageFormData.templateEditWordData.word;
					//							$scope.pageFormData.templateEditWordData=wordObjectForEdit;

					$("#editWordDialog").dialog({
						//			                    width: 500,
						//			                    height: 200,
						title: $scope.titleOfEditWordDialog + ">>>",
						//								minWidth: 600,
						//			                     minHeight: 'auto',
						resize: "auto",
						resizable: true,
						autoOpen: true,
						dialogClass: 'no-close contentAlertDisplay',
						closeX: false,
						modal: true,
						closeOnEscape: false

					});
				}

			};

			$scope.updateWordObj = function (wordObjectForEdit) {
				//if(wordObjectForEdit){
				//$scope.pageFormData.templateEditWordData=$scope.pageFormData.templateEditWordData;
				$log.log("going to edit word : " + angular.toJson($scope.pageFormData.templateEditWordData));
				$http(
					{
						method: "PUT",
						url: topicMgmtAppConfig.wordMeaningDbBackupService
							+ 'words',
						data: angular.toJson($scope.pageFormData.templateEditWordData)
					})
					.success(
						function (data) {

							$log.log("Recieved data : Success : " + angular.toJson(data));
							if (data.status == "200") {
								$scope.pageFormData.templateAddWordData = data.data;
								//$('#editWordDialog').dialog('close');
								$scope.pageFormData.pagedData.selectedWordDataItem.word = $scope.pageFormData.templateAddWordData.word;
								$scope.pageFormData.pagedData.selectedWordDataItem.unique_name = $scope.pageFormData.templateAddWordData.unique_name;
								$scope.pageFormData.pagedData.selectedWordDataItem.type = $scope.pageFormData.templateAddWordData.type;
								$scope.pageFormData.pagedData.selectedWordDataItem.details = $scope.pageFormData.templateAddWordData.details;
								$scope.pageFormData.pagedData.selectedWordDataItem.created_on = $scope.pageFormData.templateAddWordData.created_on;
								$scope.pageFormData.pagedData.selectedWordDataItem.updated_on = $scope.pageFormData.templateAddWordData.updated_on;
								$scope.pageFormData.pagedData.selectedWordDataItem.last_read = $scope.pageFormData.templateAddWordData.last_read;
								$scope.pageFormData.pagedData.selectedWordDataItem.id = $scope.pageFormData.templateAddWordData.id;

								$scope.editSelectedWord = true;
								$scope.fetchWordReads();

							} else if (data.status == "fail") {
								alert("Error : Message " + data.message + " data.status : " + data.status);
							}
						}).error(function (data) {
							$log.log("Recieved data : Fail : " + angular.toJson(data));
							alert("Error : " + data.message + "status" + data.status);
						});

			};



			$scope.search = function (searchTxt) {

				if (!searchTxt) {
					$log.log("searchTxt is empty : " + searchTxt);
					return;
				}

				$scope.pageFormData.searchedItems = [];

				$http(
					{
						method: "GET",
						url: topicMgmtAppConfig.wordMeaningDbBackupService
							+ 'words',
						params: {
							name: searchTxt
						}
					})
					.success(
						function (data) {
							if (data.status == "200") {
								//$scope.pageFormData.searchedItems = data.data;
								//$scope.pageFormData.lastSearchedWord = searchTxt;
								// $scope
								// 	.showSection($scope.settings.sectionNames.SearchedWordItemData);


								$scope.pageFormData.pagedData.items = data.data;
								$scope.pageFormData.lastSearchedWord = searchTxt;
								if ($scope.pageFormData.pagedData.items.length <= 0) {
									$scope.pageFormData.pagedData.items.enableNextButton = false;
								} else {
									$scope.pageFormData.pagedData.items.enableNextButton = true;
								}
								$scope.showAt(0);
								$scope
									.showSection($scope.pageFormData.pagedData.items);
							} else if (data.status == "fail") {
								alert("Error : Message " + data.message + " data.status : " + data.status);
							}
						}).error(function (data) {
							alert("Error : " + data);
						});
			};

			$scope.findPagedData = function (directionIndex = 0) {
				$scope.pageFormData.pagedData.items = [];
				if (directionIndex <= 0 && ($scope.pageFormData.pagedData.pageNo + directionIndex) <= 0) {
					$scope.pageFormData.pagedData.pageNo = 0;
					$scope.pageFormData.pagedData.enablePrevButton = false;
				} else {
					$scope.pageFormData.pagedData.pageNo = $scope.pageFormData.pagedData.pageNo + directionIndex;
					$scope.pageFormData.pagedData.enablePrevButton = true;
				}
				$http(
					{
						method: "GET",
						url: topicMgmtAppConfig.wordMeaningDbBackupService
							+ 'findPagedData',
						params: {
							pageNo: $scope.pageFormData.pagedData.pageNo,
							pageSize: $scope.pageFormData.pagedData.pageSize
						}
					})
					.success(
						function (data) {
							if (data.status == "200") {
								$scope.pageFormData.pagedData.items = data.data;
								//$scope.pageFormData.lastSearchedWord = searchTxt;
								if ($scope.pageFormData.pagedData.items.length <= 0) {
									$scope.pageFormData.pagedData.items.enableNextButton = false;
								} else {
									$scope.pageFormData.pagedData.items.enableNextButton = true;
								}
								$scope.showAt(0);
								$scope
									.showSection($scope.pageFormData.pagedData.items);
							} else if (data.status == "fail") {
								alert("Error : Message " + data.message + " data.status : " + data.status);
							}
						}).error(function (data) {
							alert("Error : " + data);
						});
			};

			$scope.showTopicsList = function () {
				$scope.showList = !$scope.showList;
				if ($scope.showList) {
					document.getElementById('topicDetailsDiv').classList
						.add('col-lg-8');
					document.getElementById('topicDetailsDiv').classList
						.remove('col-lg-12');
				} else {
					document.getElementById('topicDetailsDiv').classList
						.add('col-lg-12');
					document.getElementById('topicDetailsDiv').classList
						.remove('col-lg-8');
				}
			};

			$scope.idSelectedVote = null;
			$scope.setSelected = function (idSelectedVote) {
				$scope.idSelectedVote = idSelectedVote;
			};

			$scope.scrollToGivenId = function (idToScroll) {
				const elmnt1 = document.getElementById(idToScroll);
				elmnt1.scrollIntoView();
				//$log.log('id : '+ idToScroll +'   elmnt1 : '+elmnt1);
			};

			$scope.showAt = function (indexVal) {

				// angular.forEach($scope.filteredItems, function (value, key) {
				//     //console.log("name : "+value.name );
				// 	value.autofocusWord=false;
				// 	this.push(value);
				// });

				$log.log("loading data for : " + indexVal);
				$scope.counterrr = indexVal;
				//$scope.filteredItems[$scope.counterrr].autofocusWord=true;
				$scope.pageFormData.pagedData.selectedWordDataItem = $scope.filteredItems[$scope.counterrr];
				//$scope.pageFormData.pagedData.selectedWordDataItem.autofocusWord=true;



				$log.log("going to load word : id : " + angular.toJson($scope.pageFormData.pagedData.selectedWordDataItem.id));
				if ($scope.pageFormData.pagedData.selectedWordDataItem.id) {
					$scope.setSelected($scope.pageFormData.pagedData.selectedWordDataItem.id);
					$scope.fetchWordReads();
				}



				//						let loadFilteredItems=new Promise(function(resolve, reject) {
				//								$scope.pageFormData.pagedData.selectedWordDataItem = $scope.filteredItems[$scope.counterrr];
				//						});						
				//						loadFilteredItems.then(function(){
				//							$log.log("going to load word : " + angular.toJson($scope.pageFormData.pagedData.selectedWordDataItem));
				//							if($scope.pageFormData.pagedData.selectedWordDataItem.id){
				//								$scope.setSelected($scope.pageFormData.pagedData.selectedWordDataItem.id);
				//							}
				//						});


				// $scope.counterrr = ($scope.counterrr >=
				// $scope.filteredItems.length -
				// 1) ? 0
				// : ($scope.counterrr + 1);
			};

			// ////////// Marking and Fetching reads - start

			$scope.editSelectedWord = true;
			$scope.fetchWordReads = function () {
				$scope.pageFormData.pagedData.selectedWordDataItem.wordReads = {};
				WordMeaningManagementServices
					.fetchWordReads($scope.pageFormData.pagedData.selectedWordDataItem.id).success(
						function (data) {
							// alert("Success : "+data);
							$scope.pageFormData.pagedData.selectedWordDataItem.wordReads = data.data;

							$scope.isToday($scope.pageFormData.pagedData.selectedWordDataItem.wordReads.word.last_read);

							$scope.editSelectedWord = true;
							$scope.scrollToGivenId('word-' + $scope.counterrr);
							// $scope.sortBy($scope.propertyName);
						}).error(function (data) {
							// alert("Error : " + data);
							$log.log("Error : " + data);
						});
			};

			$scope.saveWordReads = function () {
				WordMeaningManagementServices.saveWordReads($scope.pageFormData.pagedData.selectedWordDataItem.id)
					.success(function (data) {
						// alert("Success : "+data);
						// $scope.topicsReads = data;
						$scope.fetchWordReads();
						// $scope.sortBy($scope.propertyName);
					}).error(function (data) {
						alert("Error : " + data);
					});
			};

			$scope.reload = function () {

				$scope.showAt($scope.counterrr);
			};

			$scope.isAlreadyReadToday = false;
			$scope.isToday = function (someDateStr) {
				const someDate = new Date(someDateStr);
				const today = new Date();
				console.log('someDate : ' + someDate);

				$scope.isAlreadyReadToday = (someDate.getDate() == today.getDate() &&
					someDate.getMonth() == today.getMonth() &&
					someDate.getFullYear() == today.getFullYear());

				console.log('someDateStr : ' + someDateStr);
				console.log('someDate : ' + someDate);
				console.log('today : ' + today);
				console.log('$scope.isAlreadyReadToday : ' + $scope.isAlreadyReadToday);

				return $scope.isAlreadyReadToday;
				// return someDate.getDate() == today.getDate() &&
				//   someDate.getMonth() == today.getMonth() &&
				//   someDate.getFullYear() == today.getFullYear();
			};

			$scope.next = function () {
				console.log("Inside next function=>>>> $scope.counterrr == "
					+ $scope.counterrr);
				$scope.counterrr = ($scope.counterrr >= $scope.filteredItems.length - 1) ? 0
					: ($scope.counterrr + 1);
				$scope.pageFormData.pagedData.selectedWordDataItem = $scope.filteredItems[$scope.counterrr];
				$scope.setSelected($scope.pageFormData.pagedData.selectedWordDataItem.id);
				$scope.fetchWordReads();


			};

			$scope.previous = function () {
				$scope.counterrr = ($scope.counterrr == 0) ? ($scope.filteredItems.length - 1)
					: ($scope.counterrr - 1);
				$scope.pageFormData.pagedData.selectedWordDataItem = $scope.filteredItems[$scope.counterrr];
				$scope.setSelected($scope.pageFormData.pagedData.selectedWordDataItem.id);
				$scope.fetchWordReads();


			};

			// ///////////////////////////////////////////////////
			$scope.timerStarted = false;
			$scope.timerID = null;

			/*
			 * var nextAltFn= function(){ //$scope.next();
			 * console.log("aaaaaaa=>>>>"); };
			 */

			$scope.slideShowStart = function () {
				$scope.timerStarted = true;
				$scope.timerID = setTimeout(function nextAltFn() {
					$scope.next();
					console.log("aaaaaaa=>>>> $scope.counterrr == "
						+ $scope.counterrr);
					$scope.timerID = setTimeout(nextAltFn, 5000);
				}, 5000);
				console
					.log("Timer started : timerID "
						+ $scope.timerID);
				console.log("timerStarted " + $scope.timerStarted);
			};

			$scope.slideShowCancel = function () {
				$scope.timerStarted = false;
				clearTimeout($scope.timerID);
				$scope.timerID = null;

				console
					.log("Timer stopped : timerID "
						+ $scope.timerID);
				console.log("timerStarted " + $scope.timerStarted);
			};

			$scope.init = function () {
				$scope.findPagedData();
			};

			$scope.init();

		});