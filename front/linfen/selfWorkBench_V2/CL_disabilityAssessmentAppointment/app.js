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
	var uploadFile = function(idCard, name, end, start, code, applyNo, callback,callback1) {
		$.ajax({
			url: $.getConfigMsg.preUrlSelf + "/selfapi/dzzz/queryCertBaseDatas.do",
			type: "post",
			dataType: "json",
			timeout: 5000,
			jsonp: "jsonpCallback",
			data: {
				certNo: idCard, //"340881199303145313"
				name: encodeURI(name),
				type: "0", //licenseType ,//
				catMainCode: code, //"310196646654500"//
				machineId: $.config.get("uniqueId") || "HPZX001",
				itemName: encodeURI("残疾评定预约日期查询、变更"),
				itemCode: "310000999000CL0000531200066000001",
				businessCode: "",
				startDay: start,
				endDay: end,
			},
			success: function(dataJsonp) {
				try {
					try{
						var formdata = {
							"applyNo":applyNo,
							"stuffCode":"stuff011",
							"stuffId":"",
							"FileData":dataJsonp.data[0].str,
						};
						// let formdata = new FormData();
						// formdata.append("applyNo",applyNo);
						// formdata.append("stuffCode", "stuff011");
						// formdata.append("stuffId", "");
						// formdata.append("FileData", dataJsonp.data[0].str);
					}catch(e){
						callback1 && callback1({'data':''});
					}
					$.ajax({
						url: $.getConfigMsg.preUrlSelf + '/selfapi/uploadItemStuffs.do',
						type: "post",
						dataType: "json",
						data: formdata,
						// cache: false, // 不缓存
						// processData: false, // jQuery不要去处理发送的数据
						// contentType: false,
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
		upload_file: uploadFile
	}
})