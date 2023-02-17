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
	
	var getMedicalDictionaries = function(type, parentKey,callback, error) {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/medical/getMedicalDictionaries.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				type: type,
				parentKey:parentKey,
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
		//获取医保居住字典
		getMedicalDictionaries(1, "",function(dataJson) {
			$rootScope.YBjzList = dataJson;
		});
		//获取医保户籍字典
		getMedicalDictionaries(0, "",function(dataJson) {
			$rootScope.YBhjList = dataJson;
		});
	}
	all();
	var product = function(idCard,name, code,start,end,callback, error) {
		$.ajax({
			type: "get",
			url:$.getConfigMsg.preUrlSelf + "/selfapi/DZCert/getCertOriginalData.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				identNo: idCard,
				catMainCode: code,
				machineId: $.config.get('uniqueId'),
				itemName: "办理居民医保受理新增登记",
				itemCode: "312000361000",
				businessCode: "",
				name: name,
				startDay:start,
				endDay: end,
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
	return {
		pro_fetch: product,
		getMedicalDictionaries:getMedicalDictionaries
	}
})