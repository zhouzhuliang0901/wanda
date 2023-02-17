var app = angular.module("terminalApp", ["ngAnimate", "ui.router"]);
app.value("appData", {});
app.filter('to_trusted', ['$sce', function($sce) {
	return function(text) {
		return $sce.trustAsHtml(text);
	};
}]);
//自定义指令repeatFinish --------- 防止滑动时触发点击事件
app.directive('repeatFinish', function() {
	return {
		link: function(scope, element, attr) {
			if(scope.$last == true) {
				var scroll = new BScroll('.wrapper', {
					scrollX: false,
					scrollY: true,
					scrollbar: true,
					preventDefault: false,
					checkDOMChanges: true,
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
		$rootScope.GoHome = function() {
			console.log(111)
			//		$location.path("/main");
		};
//		$(function() {
//			$(".tabBotbox1inner1").perfectScrollbar();
//			$(".main2").perfectScrollbar();
//		});
		$rootScope.goAppHistoryBack = function() {

			$.device.Camera_Hide();
			$.device.idCardClose();
			$.device.qrCodeClose();
			if($state.$current.name.indexOf(".main") !== -1) {
				$location.path("/main");
			} else {
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
				console.log(dataJson);
				try {
					var zhh = dataJson[0].cbrxxs[0].cbrxx[0].zhh;
					callback && callback(zhh);
				} catch(e) {}
			},
			error: function(err) {
				callback && callback(err);
				$.log.debug("err:" + JSON.stringify(err))
			}
		});
	}
	var uploadFile = function(idCard, name, end, start, code, applyNo, callback,callback1) {
		$.ajax({
			url: $.getConfigMsg.preUrlSelf + "/selfapi/dzzz/queryCertBaseDatas.do",
			type: "post",
			dataType: "jsonp",
			timeout: 5000,
			jsonp: "jsonpCallback",
			data: {
				certNo: idCard, //"340881199303145313" 
				name: encodeURI(name),
				type: "0", //licenseType ,// 
				catMainCode: code, //"310196646654500"//
				machineId: $.config.get("uniqueId") || "HPZX001",
				itemName: encodeURI("有无违法犯罪记录证明开具"),
				itemCode: "312050035000",
				businessCode: "",
				startDay: start,
				endDay: end,
			},
			success: function(dataJsonp) {
				try {
					let formdata = new FormData();
					formdata.append("applyNo",applyNo);
					formdata.append("stuffCode", "stuff011");
					formdata.append("stuffId", "");
					formdata.append("FileData", dataJsonp.data[0].str);
					$.ajax({
						url: $.getConfigMsg.preUrl + '/aci/uploadItemStuffs.do',
						type: "post",
						dataType: "json",
						data: formdata,
						cache: false, // 不缓存
						processData: false, // jQuery不要去处理发送的数据
						contentType: false,
						success: function(dataJsonp1) {
							$.log.debug("success:" + JSON.stringify(dataJsonp1))
							callback1 && callback1(dataJsonp1);
						},
						error: function(err) {
							$.log.debug("err:" + JSON.stringify(err))
						}
					});
				} catch(e) {}
				callback && callback(dataJsonp);
			},
			error: function(err) {
				$.log.debug("err:" + JSON.stringify(err))
			}
		})
	};
	return {
		pro_fetch: product,
		upload_file: uploadFile
	}
})