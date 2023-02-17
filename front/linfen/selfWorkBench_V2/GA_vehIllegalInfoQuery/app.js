var app = angular.module("terminalApp", ["ngAnimate", "ui.router"]);
app.value("appData", {});
app.filter('to_trusted', ['$sce', function($sce) {
	return function(str) {
		//将timestamp转为yyyy-mm-dd
		let date = new Date(str);
		let year = date.getFullYear();
		let month = date.getMonth() + 1;
		let day = date.getDate();
		month = month < 10 ? "0" + month : month;
		day = day < 10 ? "0" + day : day;
		str = year + '-' + month + '-' + day;
		return str;
	};
}]).filter('to_split', ['$sce', function($sce) {
	return function(str) {
		if(str){
			str = str.split(":")[1];
		}
		return str;
	};
}]);
app.run(function($rootScope, $log, $location, $state) {
	$rootScope.$on("$viewContentLoaded", function(event, toState) {
		$(function() {
			$(".tabBotbox1inner1").perfectScrollbar();
			$(".main2").perfectScrollbar();
		});
		$rootScope.customMain = function() {
			try {
				window.external.URL_CLOSE();
			} catch(e) {
				//TODO handle the exception
			}
			$location.path("/main");
		}
		$rootScope.goAppHistoryBack = function() {
			$.device.Camera_Hide();
			$.device.idCardClose();
			$.device.qrCodeClose();
			try {
				window.external.URL_CLOSE();
			} catch(e) {
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
app.factory("appFactory", function($http, $rootScope) {
	var product = function(tokenSNO, callback, error) {
		$.ajax({
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getAccessToken.do",
			type: "post",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				tokenSNO: $rootScope.tokenSNO,
			},
			success: function(res) {
				callback && callback(res);
			},
			error: function(err) {},
		})
	}
	return {
		pro_fetch: product
	}
})