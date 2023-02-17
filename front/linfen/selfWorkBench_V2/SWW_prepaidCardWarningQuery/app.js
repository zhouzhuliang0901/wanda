var app = angular.module("terminalApp", ["ngAnimate", "ui.router"]);
app.value("appData", {});
app.filter('check', ['$sce', function($sce) {
	return function(text,waringId) {
		let result = "";
		if(waringId == "3"){
			result = filterByInfo(addReason3,text);
		}else if(waringId == "1" || waringId == "2"){
			result = filterByInfo(addReason12,text);
		}
		return result;
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
	
})