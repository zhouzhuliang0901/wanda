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
		$rootScope.goAppHistoryBack = function() {};
	});
});
app.factory("appFactory", function($http, $rootScope) {
	var product = function(data, name, item, start, end,applyNo,callback, error) {
		var queryLicense = $.ajax({
			url: $.getConfigMsg.preUrlSelf + "/selfapi/dzzz/queryCertBaseDatas.do",
			type: "post",
			dataType: "json",
			timeout: 5000,
			jsonp: "jsonpCallback",
			data: {
				certNo: data, //"340881199303145313"
				type: "0", //licenseType ,//
				catMainCode: "310105109000100", //身份证证照code//
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
					let formdata = new FormData();
					formdata.append("applyNo",applyNo);
					formdata.append("stuffCode", "stuff19n05805");
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
							callback && callback(dataJsonp, dataJsonp1);
						},
						error: function(err) {
							$.log.debug("err:" + JSON.stringify(err))
						}
					});
				} catch(e) {}
			},
			error: function(err) {
				$.log.debug("err:" + JSON.stringify(err))
			}
		});
	}
	var getDictionaries = function(type, callback, error) {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/lawyerPractice/queryDictionariesByGA.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				rootCode:"area",
				code: type,
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
	}
	var all = function() {
		//获取全国行政区划
		getDictionaries("", function(dataJson) {
			$rootScope.allList = dataJson.data;
		});
	}
	all();
	return {
		pro_fetch: product,
		getDictionaries:getDictionaries
	}
})