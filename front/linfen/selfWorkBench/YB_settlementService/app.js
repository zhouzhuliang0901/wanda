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
	}
	all();
	var product = function(idCard, name, token, callback, error) {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/medicalInsurance/personalInfoQuery.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				indentNo: idCard,
				userName: name,
				mobile: "13433333333",
				type: 0,
				access_token: token
			},
			success: function(dataJson) {
				try {
					var zhh = dataJson[0].cbrxxs[0].cbrxx[0].zhh;
					$.ajax({
						type: "get",
						url: $.getConfigMsg.preUrl + "/aci/workPlatform/medicalInsurance/queryCardNo.do",
						dataType: "jsonp",
						jsonp: "jsonpCallback",
						data: {
							zhh:zhh
						},
						success: function(dataJson) {
							console.log(dataJson);
							callback && callback(dataJson);
						},
						error: function(err) {
							callback && callback(err);
							$.log.debug("err:" + JSON.stringify(err))
						}
					});
				} catch(e) {}
			},
			error: function(err) {
				callback && callback(err);
				$.log.debug("err:" + JSON.stringify(err))
			}
		});
	}
	return {
		pro_fetch: product
	}
})