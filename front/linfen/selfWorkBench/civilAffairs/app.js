var app = angular.module("terminalApp", ["ngAnimate", "ui.router"]);
app.value("appData", {});
//自定义指令repeatFinish --------- 防止滑动时触发点击事件
app.directive('repeatFinish', function() {
	return {
		link: function(scope, element, attr) {
			//			if(scope.$last == true) {
			//				var scroll = new BScroll('.wrapper', {
			//					//					scrollX: false,
			//					//					scrollY: true,
			//					//					scrollbar: true,
			////					preventDefault: false,
			////					checkDOMChanges: true,
			//					click: true,
			//					tap: true
			//				})
			//				$('.wrapper').find("a").on('tap', function() {});
			//			}
		}
	}
});
app.run(function($rootScope, $log, $location, $state) {

	$rootScope.$on("$viewContentLoaded", function(event, toState) {
		$rootScope.GoHome = function() {
			console.log(111)
			//		$location.path("/main");
		};
		$(function() {
			$(".tabBotbox1inner1").perfectScrollbar();
			$(".main2").perfectScrollbar();
		});
		$rootScope.goAppHistoryBack = function() {

			//			$.device.Camera_Hide();
			//			$.device.idCardClose();
			//			$.device.qrCodeClose();
			//			if($state.$current.name.indexOf(".main") !== -1) {
			//				$location.path("/main");
			//			} else {
			//				window.history.go(-1);
			//			}
			//			try {
			//				window.external.URL_CLOSE();
			//			} catch(e) {
			//				//TODO handle the exception
			//			}
		};
	});
});
app.factory("appFactory", function($http, $rootScope) {
	var product = function(type, callback, error) {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/civilService/getCurrentDictionaries.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				type: type,
			},
			success: function(dataJson) {
				callback && callback(dataJson);
			},
			error: function(err) {
				callback && callback(err);
				$.log.debug("err:" + JSON.stringify(err))
			}
		});
	}
	var all = function() {
		//获取全国行政区划
		product(1, function(dataJson) {
			$rootScope.allList = dataJson;
		});
		//获取上海市区级编码
		product(2, function(dataJson) {
			$rootScope.ShangHaiList = dataJson;
		});
		//上海市街镇编码
		product(3, function(dataJson) {
			$rootScope.ShangHaiStreetList = dataJson;
		});
		//上海市受理中心
		product(4, function(dataJson) {
			$rootScope.centerList = dataJson;
			console.log(dataJson);
		});
	}
	all();
	return {
		pro_fetch: product
	}
})