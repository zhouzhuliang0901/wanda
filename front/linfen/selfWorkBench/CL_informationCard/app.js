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

	var uploadFile = function(idCard, name, start, end, code, archivesCode, archivesName, callback, callback1) {
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
				itemName: encodeURI("听力、言语残疾人信息卡套餐服务申请"),
				itemCode: "CL0033",
				businessCode: "",
				startDay: start,
				endDay: end,
			},
			success: function(dataJsonp) {
				try {
					let formdata = new FormData();
					formdata.append("archivescode", archivesCode);
					formdata.append("affairscode", "CL0033");
					formdata.append("archivesname", archivesName);
					formdata.append("needflag", "0");
					formdata.append("img", dataJsonp[0].str);
					formdata.append("attachtype", "image/jpg");
					$.ajax({
						url: $.getConfigMsg.preUrlSelf + '/selfapi/civilService/uploadArchiveInfo.do',
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
	return {
		upload_file: uploadFile
	}
})