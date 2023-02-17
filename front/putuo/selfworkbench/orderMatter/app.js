var app = angular.module("terminalApp", ["ngAnimate", "ui.router"]);
app.value("data", {});
app.run(function($rootScope, $log, $location, $state) {
	$rootScope.$on("$viewContentLoaded", function(event, toState) {
//		$(function() {
//			$(".tabBotbox1inner1").perfectScrollbar();
//			$(".main2").perfectScrollbar();
//		});
		$rootScope.goAppHistoryBack = function() {
			$.device.Camera_Hide();
			$.device.idCardClose();
			$.device.qrCodeClose();
			$.device.officeClose();
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
	//var __product = "http://117.184.33.148:8080/ac-product/aci/autoterminal/";
	var __product = "http://zwdt.huangpuqu.sh.cn:8080/ac/aci/autoterminal/";
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
	var idCardInfo = function(data,name,start,end,callback, error){
		var httpConfig = {
			jsonpCallback: "JSON_CALLBACK",
			catMainCode:"310105109000100",
			identNo:data,
			machineId:$.config.get('uniqueId'),
			itemName:"",
			itemCode:"",
			businessCode:"",
			name:name,
			startDay:start,
			endDay:end
		};

		$http.jsonp("http://31.1.137.251:8080/ac-product/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do", {
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
	var addressInfo = function(data,name,start,end,callback, error){
		var httpConfig = {
			jsonpCallback: "JSON_CALLBACK",
			catMainCode:"310105109000100",//身份证
			identNo:data,
			machineId:$.config.get('uniqueId'),
			itemName:"",
			itemCode:"",
			businessCode:"",
			name: name,
			startDay:start,
			endDay:end
		};

		$http.jsonp("http://31.1.137.251:8080/ac-product/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do", {
				params: httpConfig
			})
			.success(function(dataJsonp) {
				callback && callback(dataJsonp);
			})
			.error(function(err) {
				$.log.debug("err:" + JSON.stringify(err))
				err && err(err);
				console.log(err);
			})
	}
	
	var addressInfo = function(data,name,start,end,callback, error){
		var httpConfig = {
			jsonpCallback: "JSON_CALLBACK",
			catMainCode:"310105105000100",//户口本
			identNo:data,
			machineId:$.config.get('uniqueId'),
			itemName:"",
			itemCode:"",
			businessCode:"",
			name:name,
			startDay:start,
			endDay:end
		};

		$http.jsonp("http://31.1.137.251:8080/ac-product/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do", {
				params: httpConfig
			})
			.success(function(dataJsonp) {
				callback && callback(dataJsonp);
			})
			.error(function(err) {
				$.log.debug("err:" + JSON.stringify(err))
				err && err(err);
				console.log(err);
			})
	}
	
	var marryInfo = function(data,name,start,end,callback, error){
		var httpConfig = {
			jsonpCallback: "JSON_CALLBACK",
			catMainCode:"310105127000101",//结婚证
			identNo:data,
			machineId:$.config.get('uniqueId'),
			itemName:"",
			itemCode:"",
			businessCode:"",
			name:name,
			startDay:start,
			endDay:end
		};

		$http.jsonp("http://31.1.137.251:8080/ac-product/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do", {
				params: httpConfig
			})
			.success(function(dataJsonp) {
				callback && callback(dataJsonp);
			})
			.error(function(err) {
				$.log.debug("err:" + JSON.stringify(err))
				err && err(err);
				console.log(err);
			})
	}
	var birthInfo = function(data,name,start,end,callback, error){
		var httpConfig = {
			jsonpCallback: "JSON_CALLBACK",
			catMainCode:"314910001000500",//出生证
			identNo:data,
			machineId:$.config.get('uniqueId'),
			itemName:"",
			itemCode:"",
			businessCode:"",
			name: data.idCardName,
			startDay:start,
			endDay:end
		};

		$http.jsonp("http://31.1.137.251:8080/ac-product/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do", {
				params: httpConfig
			})
			.success(function(dataJsonp) {
				callback && callback(dataJsonp);
			})
			.error(function(err) {
				$.log.debug("err:" + JSON.stringify(err))
				err && err(err);
				console.log(err);
			})
	}
	var selfInfo = function(data,name,start,end,callback, error){
		var httpConfig = {
			jsonpCallback: "JSON_CALLBACK",
			catMainCode:"310192914039400",//独生子女父母光荣证
			identNo:data,
			machineId:$.config.get('uniqueId'),
			itemName:"",
			itemCode:"",
			businessCode:"",
			name: name,
			startDay:start,
			endDay:end
		};

		$http.jsonp("http://31.1.137.251:8080/ac-product/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do", {
				params: httpConfig
			})
			.success(function(dataJsonp) {
				callback && callback(dataJsonp);
			})
			.error(function(err) {
				$.log.debug("err:" + JSON.stringify(err))
				err && err(err);
				console.log(err);
			})
	}
	
	var hospitalInfo = function(data,name,start,end,callback, error){
		var httpConfig = {
			jsonpCallback: "JSON_CALLBACK",
			catMainCode:"314910001000500",//出生医学证明
			identNo:data,
			machineId:$.config.get('uniqueId'),
			itemName:"",
			itemCode:"",
			businessCode:"",
			name: name,
			startDay:start,
			endDay:end
		};

		$http.jsonp("http://31.1.137.251:8080/ac-product/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do", {
				params: httpConfig
			})
			.success(function(dataJsonp) {
				callback && callback(dataJsonp);
			})
			.error(function(err) {
				$.log.debug("err:" + JSON.stringify(err))
				err && err(err);
				console.log(err);
			})
	}
	
		return {
		jsonp: _http,
		pro_fetch: product,
		idCardInfo:idCardInfo,
		addressInfo:addressInfo,
		marryInfo:marryInfo,
		birthInfo:birthInfo,
		selfInfo:selfInfo,
		hospitalInfo:hospitalInfo
	}
})