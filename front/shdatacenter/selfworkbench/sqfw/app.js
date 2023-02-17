var app = angular.module("declareApp", ["ng", "ngRoute"]);
app.factory('data', function() {
	return {
		isUpload: []
	};
});
app.filter('to_trusted', ['$sce', function($sce) {
	return function(text) {
		return $sce.trustAsHtml(text);
	};
}]);
app.factory("appFactory", function($http, $rootScope,$interval,$timeout) {
	var product = function(data, code,archivescode,affairscode,archivesname,needflag ,callback, complete,callback1,error) {
		var queryLicense = $.ajax({
			url: $.getConfigMsg.preUrl + "/aci/autoterminal/dzzz/queryCertBaseData.do",
			type: "post",
			dataType: "jsonp",
			timeout:5000,
			jsonp: "jsonpCallback",
			data: {
				certNo: data, //"340881199303145313" 
				type: "0", //licenseType ,// 
				catMainCode: code //"310196646654500"//
			},
			success: function(dataJsonp) {
				try{
					$.ajax({
						url: $.getConfigMsg.preUrl +'aci/workPlatform/elderlyCard/uploadArchiveInfo.do',
						type: "post",
						dataType: "json",
						data: {
							archivescode: archivescode,
							affairscode: affairscode,
							archivesname: archivesname,
							needflag: needflag,
							img: $.getConfigMsg.preUrl + dataJsonp[0].pictureUrl
						},
						success: function(dataJsonp1) {
							$.log.debug("success:" + JSON.stringify(dataJsonp1))
							callback1 && callback1(dataJsonp1);
						},
						error: function(err) {
							$.log.debug("err:" + JSON.stringify(err))
						}
					});
				}catch(e){}
				callback && callback(dataJsonp);
			},
			error: function(err) {
				$.log.debug("err:" + JSON.stringify(err))
			},complete : function(XMLHttpRequest,status){ //请求完成后最终执行参数
		　　　　if(status=='timeout'){//超时,status还有success,error等值的情况
		 　　　　　queryLicense .abort();
		　　　　}
				complete && complete(status);
		　　}
		});
	}
	var time = function(){
		var maxCountDown = 60;
		var minTime = 10;
		$rootScope.timer = null;
		$rootScope.timeCount = function() {
			$interval.cancel($rootScope.timer);
			$rootScope.timer = $interval(function() {
				maxCountDown--;
				if(maxCountDown < 1) {
					$interval.cancel($rootScope.timer);
					$rootScope.isAlert = true;
					$rootScope.msg = "是否返回首页？";
					$rootScope.alertConfirm = function(){
						window.external.URL_CLOSE();
						$.device.cmCaptureHide();
						$.device.Camera_Hide();
						$.device.qrCodeClose();
						$.device.GoHome();
					}
					$rootScope.alertCancel = function(){
						$rootScope.isAlert = false;
						$rootScope.resetCountDown();
					}
					$rootScope.timeCount = function() {
						$interval.cancel($rootScope.timer);
						$rootScope.timer = $interval(function() {
							minTime--;
							if(minTime < 1) {
								try{
									window.external.URL_CLOSE();
								}catch(e){}
								$.device.cmCaptureHide();
								$.device.Camera_Hide();
								$.device.qrCodeClose();
								$.device.GoHome();
							}
						}, 1000);
					}
					$rootScope.timeCount();
				}
			}, 1000);
		}
		$rootScope.timeCount();
		$rootScope.resetCountDown = function() {
			$interval.cancel($rootScope.timer);
			$timeout(function() {
				maxCountDown = 60;
			});
			$timeout(function() {
				$rootScope.timeCount();
			}, 5000);
		};
		window.addEventListener("click", $rootScope.resetCountDown);
		window.addEventListener("touchstart", $rootScope.resetCountDown);
		window.addEventListener("input", $rootScope.resetCountDown);
		return maxCountDown;
	}
	return {
		pro_fetch: product,
		runtime:time
	}
})