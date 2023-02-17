var app = angular.module("terminalApp", ["ngAnimate", "ui.router"]);
app.value("appData", {});
app.filter('to_date', ['$sce', function($sce) {
	//将yyyymmdd转为yyyy-mm-dd
	return function formatDateCustom(str,type) {
		if(str!=null && str!="" &&str!=undefined) {
			if(type=='license'){
				str = str.replace(/\年|月/g,'-');
				str = str.replace(/\日/g,'');
			}else if(type == "queryeles"){
				str = str.substring(0,4) + '-' + str.substring(4, 6) + '-' + str.substring(6, 8)
			}
			return str;
		}
	}
}]);
app.run(function($rootScope, $log, $location, $state) {
	$rootScope.$on("$viewContentLoaded", function(event, toState) {
		$(function() {
			$(".tabBotbox1inner1").perfectScrollbar();
			$(".main2").perfectScrollbar();
		});
		$rootScope.goAppHistoryBack = function() {
//			$.device.Camera_Hide();
//			$.device.idCardClose();
//			$.device.qrCodeClose();
//			if($state.$current.name == "main") {
//				$.device.GoHome();
//			} else {
//				$location.path("/main");
//			}
		};
	});
});
//自定义指令repeatFinish --------- 防止滑动时触发点击事件
app.directive('repeatFinish', function() {
	return {
		link: function(scope, element, attr) {
			if(scope.$last == true) {
				var scroll = new BScroll('.wrapper', {
					scrollX: false,
					scrollY: true,
					scrollbar: true,
					//					preventDefault: false,
					//					checkDOMChanges: true,
					click: true,
					tap: true
				})
				$('.wrapper').find("a").on('tap', function() {});
			}
		}
	}
});
app.factory("appFactory", function($http, $rootScope) {
	var __url = "http://10.81.16.56:8080/ac-product/aci/declare/";
	var __product = "http://10.81.16.56:8080/ac-product/aci/autoterminal/";
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
		// 	dataType:'json',
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
})