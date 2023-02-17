var app = angular.module("terminalApp", ["ngAnimate", "ui.router"]);
app.value("appData", {});
app.run(function($rootScope, $log, $location, $state) {
	$rootScope.$on("$viewContentLoaded", function(event, toState) {
	});
});
app.factory("appFactory", function($http, $rootScope) {
	var __url = "http://hengshui.5uban.com/xhac/aci/declare/";
	var __product = "http://117.184.33.148:8080/ac-product/aci/autoterminal/";
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
	var idCardInfo = function(data,callback, error){
		var httpConfig = {
			jsonpCallback: "JSON_CALLBACK",
			catMainCode:"310105109000100",
			identNo:data
		};

		$http.jsonp("http://hengshui.5uban.com/xhac/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do", {
				params: httpConfig
			})
			.success(function(dataJsonp) {
				callback && callback(dataJsonp);
			})
			.error(function(err) {
				$.log.debug("err:" + JSON.stringify(err))
				err && err(err);
			})
	}
	var addressInfo = function(data,callback, error){
		var httpConfig = {
			jsonpCallback: "JSON_CALLBACK",
			catMainCode:"310105105000100",
			identNo:data
		};

		$http.jsonp("http://hengshui.5uban.com/xhac/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do", {
				params: httpConfig
			})
			.success(function(dataJsonp) {
				callback && callback(dataJsonp);
			})
			.error(function(err) {
				$.log.debug("err:" + JSON.stringify(err))
				err && err(err);
			})
	}
	return {
		jsonp: _http,
		pro_fetch: product,
		idCardInfo:idCardInfo,
		address:addressInfo
	}
})