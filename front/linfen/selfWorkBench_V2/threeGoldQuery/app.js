var app = angular.module("terminalApp", ["ngAnimate", "ui.router"]);
app.value("appData", {});
app.run(function($rootScope, $log, $location, $state) {
	$rootScope.$on("$viewContentLoaded", function(event, toState) {
		$(function() {
			$(".tabBotbox1inner1").perfectScrollbar();
			$(".main2").perfectScrollbar();
		});
		$rootScope.customMain = function(){
			try{
				window.external.URL_CLOSE();
			}catch(e){
				//TODO handle the exception
			}
			$location.path("/main");
		}
		$rootScope.goAppHistoryBack = function() {
			$.device.Camera_Hide();
			$.device.idCardClose();
			$.device.qrCodeClose();
			try{
				window.external.URL_CLOSE();
			}catch(e){
				//TODO handle the exception
			}
			if($state.$current.name=="main") {
				$.device.GoHome();
			} else {
				$location.path("/main");
			}
		};
	});
});
app.factory("appFactory", function($http, $rootScope) {
	//ext1拓展字段 暂时无用
	var product = function(idCard, name, ext1, callback, error) {
		var token = "";
		var getAccessToken = function() {
			var rec = $.ajax({
				url: $.getConfigMsg.preUrl + "/aci/workPlatform/getAccessToken.do",
				type: "post",
				dataType: "json",
				jsonp: "jsonpCallback",
				timeout: 5000,
				data: {
					tokenSNO: $rootScope.tokenSNO,
				},
				success: function(res) {
					if(res.SUCCESS === true) {
						token = res.accessToken;
						$.ajax({
							type: "get",
							url: $.getConfigMsg.preUrl + "/aci/workPlatform/medicalInsurance/personalInfoQuery.do",
							dataType: "json",
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
									callback && callback(dataJson[0].cbrxxs[0].cbrxx[0].zhh);
								} catch(e) {}
							},
							error: function(err) {
								callback && callback(err);
								$.log.debug("err:" + JSON.stringify(err))
							}
						});
					} else {
						return false;
					}
				},
				error: function(err) {},
			})
		}
		getAccessToken();
	}
	return {
		pro_fetch: product
	}
})