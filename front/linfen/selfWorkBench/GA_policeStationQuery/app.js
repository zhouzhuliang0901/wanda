var app = angular.module("terminalApp", ["ngAnimate", "ui.router"]);
app.value("appData", {});
app.filter('to_trusted', ['$sce', function($sce) {
	return function(text) {
		if(text == "市局") {
			return "上海市";
		} else if(text.indexOf("分局") != -1) {
			if(text == "浦东分局") {
				return text.split("分局")[0] + "新区";
			} else {
				return text.split("分局")[0] + "区";
			}
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
				$('.wrapper').find("a").on('tap', function() {});
			}
		}
	}
});
app.run(function($rootScope, $log, $location, $state) {
	$rootScope.$on("$viewContentLoaded", function(event, toState) {
		$rootScope.GoHome = function() {
			console.log(111)
		};
	});
});
app.factory("appFactory", function($http, $rootScope) {

})