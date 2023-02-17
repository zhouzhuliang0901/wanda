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
	http://zwdt.huangpuqu.sh.cn:8080/ac
	// var __product = "http://hengshui.5uban.com/xhac/aci/autoterminal/";
	var __product = "";
	var qumethod = function(str){
		switch(str){
			case "黄浦区":
				__product = "http://zwdt.huangpuqu.sh.cn:8080/ac/aci/autoterminal/";
				break;
			case "杨浦区":
				__product = "http://zwdtyp.sh.gov.cn:8088/ac/aci/autoterminal/";
				break;
			case "浦东新区":
				__product = "http://zwdtpd.sh.gov.cn:8082/ac/aci/autoterminal/";
				break;
			case "嘉定区":
				__product = "http://218.202.254.222/aci/autoterminal/";
				break;
			case "普陀区":
				__product = "";
				break;
			case "静安区":
				__product = "http://xzfwzx.jingan.gov.cn:8080/ac/aci/autoterminal/";
				break;
		}
	}
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
		pro_fetch: product,
		qumethod:qumethod
	}
})