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
			//			$location.path("/main");
		};
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
				//				$state.go("loginType1");
				window.history.go(-1);
			}
			try {
				window.external.URL_CLOSE();
			} catch(e) {
				//TODO handle the exception
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
				dataType: "jsonp",
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
									callback && callback(dataJson);
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