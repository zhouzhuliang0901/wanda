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
app.filter('to_replace', ['$sce', function($sce) {
	return function(text) {
		console.log(text);
		if(text == "良好") {
			return "image/good.png";
		} else if(text == "一般"){
			return "image/normal.png";
		}else if(text == "较差"){
			return "image/bad.png";
		}
	};
}]);
//自定义指令repeatFinish --------- 防止滑动时触发点击事件
app.directive('repeatFinish', function() {
	return {
		link: function(scope, element, attr) {
			if(scope.$last == true) {
				var scroll = new BScroll('.wrapper', {
					scrollX: false,
					scrollY: true,
					scrollbar: true,
					click: true,
					tap: true,
					preventDefault: false,
					checkDOMChanges: true,
				})
				$('.wrapper').find("div").on('tap', function() {});
			}
		}
	}
});
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