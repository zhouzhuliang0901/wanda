var app = angular.module("terminalApp", ["ngAnimate", "ui.router"]);
app.value("appData", {});
app.filter('to_trusted', ['$sce', function($sce) {
	return function(text) {
		return $sce.trustAsHtml(text);
	};
}]);
app.run(function($rootScope, $log, $location, $state) {

	$rootScope.$on("$viewContentLoaded", function(event, toState) {
		$rootScope.GoHome = function() {
			console.log(111)
		};
		$(function() {
			$(".tabBotbox1inner1").perfectScrollbar();
			$(".main2").perfectScrollbar();
		});
		$rootScope.goAppHistoryBack = function() {};
	});
});
app.factory("appFactory", function($http, $rootScope) {

	var getDictionaries = function(type, callback, error) {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/civilService/getCurrentDictionaries.do",
			// url: $.getConfigMsg.preUrlSelf + "/selfapi/lawyerPractice/queryDictionariesByGA.do?",
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

	var getLocation = function(code, callback, error) {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/lawyerPractice/queryDictionariesByGA.do?",
			// url: $.getConfigMsg.preUrlSelf + "/selfapi/lawyerPractice/queryDictionariesByGA.do?",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				rootCode: "area",
				type:"0",
				code: code
			},
			success: function(dataJson) {
				console.log("进入方法")
				callback && callback(dataJson.data);
			},
			error: function(err) {
				callback && callback(err);
				$.log.debug("err:" + JSON.stringify(err))
			}
		});
		
	}
	var all = function() {
		//获取全国行政区划
		getDictionaries(1, function(dataJson) {
			$rootScope.allList = dataJson;
		});
		//获取上海市区级编码
		getDictionaries(2, function(dataJson) {
			$rootScope.ShangHaiList = dataJson;
		});
		//上海市街镇编码
		getDictionaries(3, function(dataJson) {
			$rootScope.ShangHaiStreetList = dataJson;
		});
		//上海市受理中心
		getDictionaries(4, function(dataJson) {
			$rootScope.centerList = dataJson;
		});
		//全国省市
//		getDictionaries(110000, function(dataJson) {
//			$rootScope.locationList = dataJson.data;
//		});
}
	all();
	return {}
})
//自定义指令repeatFinish --------- 防止滑动时触发点击事件
app.directive('repeatFinish', function() {
	return {
		link: function(scope, element, attr) {
			if(scope.$last == true) {
				var scroll = new BScroll('.wrapper', {
					//					scrollX: false,
					//					scrollY: true,
					//					scrollbar: true,
					click: true,
					tap: true
				})
				$('.wrapper').find("a").on('tap', function() {});
			}
		}
	}
});
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
			try{
				window.external.URL_CLOSE();
			}catch(e){
				//TODO handle the exception
			}
			if($state.$current.name == "main") {
				$.device.GoHome();
			} else {
				$location.path("/main");
			}
		};
	});
});