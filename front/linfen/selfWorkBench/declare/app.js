var app = angular.module("declareApp", ["ng", "ngRoute"]);
app.factory('data', function() {
	return {};
});
app.filter('to_trusted', ['$sce', function($sce) {
	return function(text) {
		text = text.replace(/nbsp&/ig, "");
		return $sce.trustAsHtml(text);
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
					preventDefault: false,
					checkDOMChanges: true,
					click: true,
					tap: true
				})
				$('.wrapper').find("a").on('tap', function() {});
			}
		}
	}
});
app.factory("appFactory", function($http, $rootScope, $interval, $timeout) {
	var __product = "http://172.16.125.53:8080/ac-product/aci/autoterminal/";
	var _http = function(method, data, callback, error) {
		var httpConfig = angular.merge({
			jsonpCallback: "JSON_CALLBACK"
		}, data);

		$http.jsonp(__url + method, {
				params: httpConfig
			})
			.success(function(dataJsonp) {
				callback && callback(dataJsonp);
			})
			.error(function(err) {
				$.log.debug("err:" + JSON.stringify(err))
				err && err(err);
			})
	};
	var product = function(method, data, callback, error) {
		var httpConfig = angular.merge({
			jsonpCallback: "JSON_CALLBACK"
		}, data);
		$http.jsonp(__product + method, {
				params: httpConfig
			})
			.success(function(dataJsonp) {
				callback && callback(dataJsonp);
			})
			.error(function(err) {
				$.log.debug("err:" + JSON.stringify(err))
				error && error(err);
			})
	}
	return {
		jsonp: _http,
		pro_fetch: product
	}
})