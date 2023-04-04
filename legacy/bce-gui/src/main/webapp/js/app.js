var app = angular.module('topicMgmtApp', [ 'ngRoute', 'ngAnimate',
		'angularTrix', 'ngSanitize','ngTouch','ui.bootstrap' ]);

app.constant("topicMgmtAppConfig", {
	"restServices" : "http://127.0.0.1:8081/RestServices/rest",
	"interviewMgmtServices":"http://127.0.0.1:8081/interview-mgmt/rest",
	"maxInterviewMgmtRatingValue":"10",
	"maxTopicMgmtRatingValue":"10",
	"maxLinkMgmtRatingValue":"10",
//	"taskMgmtService":"http://127.0.0.1:8090/task-mgmt-service/",
	"taskMgmtService":"http://127.0.0.1:8090/task-mgmt",
	"wordMeaningService":"http://127.0.0.1:8090/word-meaning-mongodb-services/",
	"linkMgmtService":"http://127.0.0.1:8090/link-mgmt-mongodb-services/",
	"wordMeaningDbBackupService":"http://127.0.0.1:8090/word-meaning-db-backup-service/",
	"myPagesService":"http://127.0.0.1:8090/my-pages/",
	"eventGroupManagementService":"http://127.0.0.1:3000/",
	"wordMeaningDbBackupSettings":{
		"pagedData":{
			"pageSize":500
		}
	},
	"interviewMgmtServiceControls":{
		"showCategoryQuestionAnswers":true,
		
	}
});



app.directive('checkList', function() {
	return {
		scope : {
			list : '=checkList',
			value : '@'
		},
		link : function(scope, elem, attrs) {
			var handler = function(setup) {
				var checked = elem.prop('checked');
				var index = scope.list.indexOf(scope.value);

				if (checked && index == -1) {
					if (setup)
						elem.prop('checked', false);
					else
						scope.list.push(scope.value);
				} else if (!checked && index != -1) {
					if (setup)
						elem.prop('checked', true);
					else
						scope.list.splice(index, 1);
				}
			};

			var setupHandler = handler.bind(null, true);
			var changeHandler = handler.bind(null, false);

			elem.bind('change', function() {
				scope.$apply(changeHandler);
			});
			scope.$watch('list', setupHandler, true);
		}
	};
})

/**
 * @name confirmOnExit
 * 
 * @description Prompts user while he navigating away from the current route
 *              (or, as long as this directive is not destroyed) if any unsaved
 *              form changes present.
 * 
 * @element Attribute
 * @scope
 * @param confirmOnExit
 *            Scope function which will be called on window refresh/close or
 *            AngularS $route change to decide whether to display the prompt or
 *            not.
 * @param confirmMessageWindow
 *            Custom message to display before browser refresh or closed.
 * @param confirmMessageRoute
 *            Custom message to display before navigating to other route.
 * @param confirmMessage
 *            Custom message to display when above specific message is not set.
 * 
 * @example Usage: Example Controller: (using controllerAs syntax in this
 *          example)
 * 
 * angular.module('AppModule', []).controller('pageCtrl', [function () {
 * this.isDirty = function () { // do your logic and return 'true' to display
 * the prompt, or 'false' otherwise. return true; }; }]);
 * 
 * Template:
 * 
 * <div confirm-on-exit="pageCtrl.isDirty()" confirm-message-window="All your
 * changes will be lost." confirm-message-route="All your changes will be lost.
 * Are you sure you want to do this?">
 * 
 * @see http://stackoverflow.com/a/28905954/340290
 * 
 * @author Manikanta G
 */

app.directive('confirmOnExit',
		function() {
			return {
				scope : {
					confirmOnExit : '&',
					confirmMessageWindow : '@',
					confirmMessageRoute : '@',
					confirmMessage : '@'
				},
				link : function($scope, elem, attrs) {
					window.onbeforeunload = function() {
						if ($scope.confirmOnExit()) {
							return $scope.confirmMessageWindow
									|| $scope.confirmMessage;
						}
					}
					var $locationChangeStartUnbind = $scope.$on(
							'$locationChangeStart', function(event, next,
									current) {
								if ($scope.confirmOnExit()) {
									if (!confirm($scope.confirmMessageRoute
											|| $scope.confirmMessage)) {
										event.preventDefault();
									}
								}
							});

					$scope.$on('$destroy', function() {
						window.onbeforeunload = null;
						$locationChangeStartUnbind();
					});
				}
			};
		});

/**
 * The animation applied on an ng-view directive takes place when a user
 * switches between views of an AngularJS application. As listed in the table
 * above, we can animate when a view enters or leaves. It is not necessary to
 * handle both of these cases; we can animate the one that seems necessary.
 * 
 * Following is an animation that induces some visual effect when a view is
 * entering:
 */

app.animation('.view-slide-in', function() {
	return {
		enter : function(element, done) {
			element.css({
				opacity : 0.5,
				//position : "relative",
				top : "50px",
				left : "50px"
			}).animate({
				top : 0,
				left : 0,
				opacity : 1
			}, 500, done);
		}
	};
});

app.directive("formatDate", function(){
	return {
	 require: 'ngModel',
	  link: function(scope, elem, attr, modelCtrl) {
		modelCtrl.$formatters.push(function(modelValue){
		  return new Date(modelValue);
		})
	  }
	}
  })

// app.directive('customAutofocus', function() {
// 	return{
// 		   restrict: 'A',
  
// 		   link: function(scope, element, attrs){
// 			 scope.$watch(function(){
// 			   return scope.$eval(attrs.customAutofocus);
// 			   },function (newValue){
// 				 if (newValue === true){
// 					 element[0].focus();//use focus function instead of autofocus attribute to avoid cross browser problem. And autofocus should only be used to mark an element to be focused when page loads.
// 				 }
// 			 });
// 		   }
// 	   };
//   })


//app.directive('starRating', function () {
//    return {
//        restrict: 'A',
//        /*template: '<ul class="rating">' +
//            '<li ng-repeat="star in stars" ng-class="star" ng-click="toggle($index)">' +
//            '\u2605' +
//            '</li>' +
//            '</ul>',*/
//        templateUrl: "js/directives/templates/star-rating.html",
//        scope: {
//            ratingValue: '=',
//            max: '=',
//            editStarRating: "=",
//            onRatingSelected: '&'
//        },
//        link: function (scope, elem, attrs) {
//
//            var updateStars = function () {
//                scope.stars = [];
//                for (var i = 0; i < scope.max; i++) {
//                    scope.stars.push({
//                        filled: i < scope.ratingValue
//                    });
//                }
//            };
//
//            scope.toggle = function (index) {
//            	if(!scope.editStarRating){
//            		return;
//            	}
//                scope.ratingValue = index + 1;
//                scope.onRatingSelected({
//                    rating: index + 1
//                });
//            };
//
//            scope.$watch('ratingValue', function (oldVal, newVal) {
//                if (newVal) {
//                    updateStars();
//                }
//            });
//        }
//    }
//});
