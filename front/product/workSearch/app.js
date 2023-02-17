var app = angular.module("terminalApp", ["ngAnimate", "ui.router"]);
app.value("appData", {});
app.run(function($rootScope, $log, $location, $state) {
	$rootScope.$on("$viewContentLoaded", function(event, toState) {
		$(function() {
			$(".tabBotbox1inner1").perfectScrollbar();
			$(".main2").perfectScrollbar();
		});
		$rootScope.goAppHistoryBack = function() {
			$.device.Camera_Hide();
			$.device.idCardClose();
			$.device.qrCodeClose();
			if($state.$current.name.indexOf(".main") !== -1) {
				$location.path("/main");
			} else {
				window.history.go(-1);
			}
		};
	});
});
app.factory("appFactory", function($http, $rootScope) {
	var __url = "http://hengshui.5uban.com/xhac/aci/declare/";
	var __product = "http://218.202.254.222/aci/autoterminal/";
	var r__product = "http://10.2.101.72:8080/ac-self-api/selfapi";
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
		$.log.debug("data:" + JSON.stringify(data))
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
		$.log.debug("httpConfig:" + JSON.stringify(httpConfig))
		$http.jsonp(__product + method, {
				params: httpConfig
			})
			.success(function(dataJsonp) {
				$.log.debug("dataJsonp:" + JSON.stringify(dataJsonp))
				callback && callback(dataJsonp);
			})
			.error(function(err) {
				$.log.debug("err:" + JSON.stringify(err))
				error && error(err);
			})
	}
	var r_product = function(method, data, callback, error) {
		$.log.debug("data:" + JSON.stringify(data))
		var httpConfig = angular.merge({
			jsonpCallback: "JSON_CALLBACK"
		}, data);
		$.log.debug("httpConfig:" + JSON.stringify(httpConfig))
		$http.jsonp(r__product + method, {
				params: httpConfig
			})
			.success(function(dataJsonp) {
				$.log.debug("dataJsonp:" + JSON.stringify(dataJsonp))
				callback && callback(dataJsonp);
			})
			.error(function(err) {
				$.log.debug("err:" + JSON.stringify(err))
				error && error(err);
			})
		}
	return {
		jsonp: _http,
		pro_fetch: product,
		r_pro_fetch: r_product
	}
})