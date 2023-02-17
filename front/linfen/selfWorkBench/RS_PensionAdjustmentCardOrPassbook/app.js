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
			if($state.$current.name == "main") {
				$.device.GoHome();
			} else {
				$location.path("/main");
			}
		};
	});
});
app.factory("appFactory", function($http, $rootScope) {
	var product = function(code, serverType, applyNo, paramStr, callback, error) {
		$http.get($.getConfigMsg.preUrlSelf+"/selfapi/pensionAdjustment/RSunifiedInterface.do", {
			params: {
				code: code,
				serverType: serverType,
				applyNo: applyNo,
				paramStr: paramStr,
			}
		}).success(function(dataJson) {
			callback && callback(dataJson);
		}).error(function(err) {
			callback && callback(err);
		});
	};

	return {
		pro_fetch: product
	}
})