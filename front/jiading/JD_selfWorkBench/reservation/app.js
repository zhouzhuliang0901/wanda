var app = angular.module("myApp", ["ui.router"]);
//创建服务，在服务中共享控制器中的数据
app.factory('instance', function() {
	return {};
});
app.value("appData", {});
//封装组件
//模板底部按钮
app.directive("btnbtn", [function() {
	return {
		templateUrl: 'template/btn-tab.html',
	}
}]);
app.directive("altfooter", [function() {
	return {
		templateUrl: '../libs/views/footer1.html',
	}
}]);
//模板
app.directive("sidetoadd", [function() {
	return {
		templateUrl: 'template/side.html',
	}
}]);
app.factory("appFactory", function($http, $rootScope) {
	var __url = "http://hengshui.5uban.com/xhac/aci/declare/";
	//嘉定内网
	var __product = "http://10.237.16.72/aci/autoterminal/";
	//嘉定外网
//	var __product = "http://218.202.254.222/aci/autoterminal/";
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
		// $.ajax({
		// 	url:__product + method,
		// 	type:"get",
		// 	dataType:'jsonp',
		// 	jsonp:"jsonpCallback",
		// 	data:data,
		// 	success:function(dataJsonp){
		// 		callback && callback(dataJsonp);
		// 	},
		// 	error:function(err){
		// 		alert(err)
		// 		$.log.debug("err:" + JSON.stringify(err))
		// 		error && error(err);
		// 	}
		// })
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
});
app.directive('repeatDone', function() {
	return {
		link: function(scope, element, attrs) {
			if(scope.$last) { // 这个判断意味着最后一个 OK
				scope.$eval(attrs.repeatDone) // 执行绑定的表达式
			}
		}
	}
})