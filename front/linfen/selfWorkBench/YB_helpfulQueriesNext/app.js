var app = angular.module("terminalApp", ["ngAnimate", "ui.router"]);
app.value("appData", {});
app.filter('to_trusted', ['$sce', function($sce) {
	return function(text) {
		console.log(text);
		if(text) {
			return text.split("邮编：")[1];
		} else {
			return text
		}
	};
}]);
app.run(function($rootScope, $log, $location, $state) {

	$rootScope.$on("$viewContentLoaded", function(event, toState) {
		$rootScope.GoHome = function() {
			console.log(111)
		};
		$(function() {
			$(".tabBotbox1inner1").perfectScrollbar();
			$(".main2").perfectScrollbar();
		});
		$rootScope.goAppHistoryBack = function() {};
	});
});
app.factory("appFactory", function($http, $rootScope) {

		return {}

})
// app.controller ("myProController", [ "$scope", function ( $scope ) {
// 	//滚动到底部触发事件
// 	var pageSize = 0;
// 	$scope.loadMore = function () {
// 		// pageSize++;
// 		// for(var i = 0; i < pageSize; i++){
// 		// 	obj.age = i;
// 		// 	$scope.users.push(obj)
// 		// }
// 		console.log ("滚动到底出发!!");
// 		console.info(pageSize);
// 	};
// } ]);
// //滚动指令
// app.directive ('whenScrolled', function () {
// 	return function ( scope, elm, attr ) {
// 		// 内层DIV的滚动加载
// 		var raw = elm[ 0 ];
// 		elm.bind ('scroll', function () {
// 			if ( raw.scrollTop + raw.offsetHeight >= raw.scrollHeight ) {
// 				scope.$apply (attr.whenScrolled);
// 			}
// 		});
// 	};
// });