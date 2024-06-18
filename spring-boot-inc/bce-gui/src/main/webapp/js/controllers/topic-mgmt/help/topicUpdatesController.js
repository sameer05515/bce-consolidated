//topicUpdatesController

//viewTopicController-list

app
		.controller(
				'topicUpdatesController',
				function($scope/* , $http */, $location, $routeParams,
						topicMgmtAppConfig, TopicManagementServices) {

					/** Variable Declaration start */
					var topic = {};
					topic.title = "Topic site updates";

					topic.topicGroupViewRelationCURD = [

							{
								"date" : "24/Oct/2016",
								"description" : "one hibernate property added so that lazy loading should stopped, as it was creating issues"
							},
							{
								"date" : "13/Oct/2016",
								"description" : "topic group relation created , rest services created, page decoration is pending"
							}

					];

					topic.topicGroupViewCURD = [
							{
								"date" : "25/Oct/2016",
								"description" : "size of buttons created using bootstrap is now adjuted using btn-xs class"
							},
							{
								"date" : "25/Oct/2016",
								"description" : "sorting of groups and views on basis of title, creaton date, last modification date is created"
							},
							{
								"date" : "13/Oct/2016",
								"description" : "sorting of topics on basis of title, creaton date, last modification date is created. same for groups and views is pending"
							}

					];

					$scope.topic = topic;
					/** Variable Declaration end ################################ */

					/** ##################################################################################################### */

					/** Method Declaration start */

					/** Method Declaration end ################################ */

					/** ##################################################################################################### */

					/** Initial Method start */

					/** Initial Method end ################################ */

				});
