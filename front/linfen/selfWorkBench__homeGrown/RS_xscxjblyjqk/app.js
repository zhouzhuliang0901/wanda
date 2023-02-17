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
	var __url ="http://117.184.33.148:8080/ac-product/aci/declare/";
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
	//查看外设数量
	var queryDevice = function(deviceName,callback){
		 $.ajax({
		 	url:"http://10.81.16.56:8088/ac-self-api/outdevicestatus/odeviceStatus/info.do",
		 	type:"get",
		 	dataType:'jsonp',
		 	jsonp:"jsonpCallback",
		 	data:{
		 		stDeviceId:$.config.get('MAC'),
		 		stOutDeviceCode:deviceName
		 	},
		 	success:function(dataJsonp){
		 		callback && callback(dataJsonp);
		 	},
		 	error:function(err){
		 		$.log.debug("err:" + JSON.stringify(err))
		 	}
		 })
	}
	
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
		queryDevice:queryDevice
	}
})