var app = angular.module("Arklicense", ["ngAnimate", "ui.router"]);
app.value("appData", {});
app.run(["$rootScope", '$log', "$location", function($rootScope, $log, $location) {
	$rootScope.$on("$viewContentLoaded", function(event, toState) {
		$rootScope.isMain = ($location.path() === "/main"); //是不是主页面
		try {
			$rootScope.controllerName = toState.controller;
		} catch(error) {
			$rootScope.controllerName = 'main';
		}

	});
}]);
app.filter("hideTel", function() {
	return function(text, num) {
		num = num > 3 && num < 20 ? num : 1; //设置默认替换长度
		text = text + "";
		var newText = text.substring(0, num) +
			text.substring(num, text.length - num).replace(/\d/g, "*") + text.substring(text.length - num, text.length);
		return newText;
	}
})
app.factory("appConfig", function($http, $interval, $location, $rootScope) {
	var data = {};
//	var $ConfigUrl = "http://180.169.7.194:8080/ac-product/aci/certflow/";// 万达接口
//	var $gdUrl = "http://180.169.7.194:8080/ac-product/acceptance/";// 贵都接口

	var $ConfigUrl = "http://10.5.99.200/aci/certflow/";// 万达接口
	var $gdUrl = "http://10.5.99.200/acceptance/";// 贵都接口
	
	var $stMachineId = $.config.get("uniqueId") || "12-12-12-12";// 00-E2-69-1F-8D-3D
	var _timer = null;
	var _http = function(method, data, callback, error) {
		var httpConfig = angular.merge({
			stMachineId: $stMachineId,
		}, data);
		$.ajax({
			type:"get",
			url:$ConfigUrl + method,
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: httpConfig,
			success:function(dataJsonp) {
				callback && callback(dataJsonp);
			},
			error:function(err) {
				$.log.debug("err:" + JSON.stringify(err))

				error && error(err);
				console.log(err);
			}
		});
//		$http.jsonp($ConfigUrl + method, {
//				params: httpConfig
//			})
//			.success(function(dataJsonp) {
//				callback && callback(dataJsonp);
//			})
//			.error(function(err) {
//				$.log.debug("err:" + JSON.stringify(err))
//
//				error && error(err);
//				console.log(err);
//			})

	};
	var _recognition = function(data, callback, error) {
		var httpConfig = angular.merge({
			account: "cuihuibin",
			password: "qwer"
		}, data);
		$http({
				method: "post",
				url: "http://localhost:8080/ac-product/witnesscheck/pairVerify.do",
				data: httpConfig
			})
			.success(function(data) {
				var _data = data.replace(/&quot;/g, '"');
				var dataJson = JSON.parse(_data);
				callback && callback(dataJson);
			})
			.error(function(err) {
				error && error(err);
				console.log(err);
			});
	};
	var sarks = [
		//自定义柜号
		"01",
		"02",
		"03",
		"04",
		"05",
		"06",
		"07",
		"08",
		"09",
		"10",
		"11",
		"12",
		"13",
		"14",
		"15",
		"16",
		"17",
		"18",
		"19",
		"20",
		"21",
		"22",
		"23",
		"24",
		"25",
		"26",
		"27",
		"28",
		"29",
		"30",
		"31",
		"32",
		"33",
		"34",
		"35",
		"36",
		"37",
		"38",
		"39",
		"40",
		"41",
		"42",
		"43",
		"44",
		"45",
		"46",
		"47",
		"48",
		"49",
		"50",
		"51",
		"52",
		"53",
		"54",
		"55",
		"56",
		"57",
		"58",
		"59",
		"60"
		
	];
	var personnel = [
	{
		Name: "邹天奇",
		Code: "430426199804106174",
		Tel: "18692067056"
	},
	{
		Name: "刘题丰",
		Code: "430181199812190634",
		Tel: "18692206922"
	}
	];
	var useSark = [];
	var preUrl = "http://130.129.1.169:8080/ac-product";
	var getResideQuantity = function() {
		_http("getCertFlowOnUse.do", {}, function(res) {
//			console.log(res.cabList);
			$rootScope.resideQuantity = 60 - res.cabList.length;
			for(var i = 0; i < res.cabList.length; i++) {
				useSark.push(res.cabList[i]);
//				console.log(useSark);
			}
			console.log("初始化非空柜子："+useSark.toString());
		});
	}
	getResideQuantity();
	return {
		httpUrl: $ConfigUrl, //接口地址
		gdHttpUrl: $gdUrl, // 贵都接口地址
		stMachineId: $stMachineId, //证照柜设备识别码
		http: _http, //封装jsonp接口
		recognition: _recognition, //人证核验接口
		sark: useSark, //使用的柜子
		initSark: sarks, //初始柜子编号
		personnel: personnel, //工作人员信息
		preUrl: preUrl, //获取身份证头像base64地址
		getResideQuantity: getResideQuantity //获取所有柜子状态
	}
});
app.directive("headHolder", function() {
	return {
		restrict: "E",
		templateUrl: "views/head.html",
		terminal: true,
		controller: function($scope, $rootScope, $http, appConfig, appData, $location, $interval) {
			$rootScope.resideQuantity = 60;

			$interval(function() {
				$rootScope.resideQuantity = appData.resideQuantity || $rootScope.resideQuantity;
				if(appData.resideQuantity < 1 || $rootScope.resideQuantity < 1) {
					$rootScope.resideQuantity = 0;
				}
			}, 1000);
			$scope.goHome = function() {
				$.device.qrCodeClose();
				$.device.Camera_Hide();
				$interval.cancel($rootScope.timer);
				$location.path("/main").search({});
			};
		}
	}
});