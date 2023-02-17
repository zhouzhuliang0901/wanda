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
app.run(function($rootScope, $log, $location) {
	$rootScope.$on("$viewContentLoaded", function(event, toState) {
		$rootScope.goAppHistoryBack = function() {
			$.device.Camera_Hide();
			$.device.idCardClose();
			$.device.qrCodeClose();
			if($location.path().indexOf(".main") !== -1) {
				$location.path("/main");
			} else {
				window.history.go(-1);
			}
		};
	});
});
app.factory("appFactory", function($http, $rootScope, $interval, $timeout) {
	var url = "http://172.16.125.53:8080/ac-product"
	var product = function(data, name, item, start, end, code, archivescode, affairscode, archivesname, needflag, callback, complete, callback1, error) {
		var queryLicense = $.ajax({
			url: $.getConfigMsg.preUrl + "/aci/autoterminal/dzzz/queryCertBaseData.do",
			type: "post",
			dataType: "jsonp",
			timeout: 5000,
			jsonp: "jsonpCallback",
			data: {
				certNo: data, //"340881199303145313" 
				type: "0", //licenseType ,// 
				catMainCode: code, //"310196646654500"//
				machineId: $.config.get('uniqueId'),
				itemName: item,
				itemCode: "",
				businessCode: "",
				name: name,
				startDay: start,
				endDay: end,
			},
			success: function(dataJsonp) {
				try {
					$.ajax({
						url: $.getConfigMsg.preUrl + '/aci/workPlatform/elderlyCard/uploadArchiveInfo.do',
						type: "post",
						dataType: "json",
						data: {
							archivescode: archivescode,
							affairscode: affairscode,
							archivesname: archivesname,
							needflag: needflag,
							img: url + dataJsonp[0].pictureUrl
						},
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
			},
			complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
				　　　　
				if(status == 'timeout') { //超时,status还有success,error等值的情况
					　　　　　
					queryLicense.abort();　　　　
				}
				complete && complete(status);　　
			}
		});
	}

	return {
		pro_fetch: product
	}
})