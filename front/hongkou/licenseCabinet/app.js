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
//	var $ConfigUrl = "http://180.169.7.194:8081/ac-self/selfapi/certCabinet/";
	var $ConfigUrl = "https://yjssb.shhk.gov.cn/ac-product/selfapi/certCabinet/";
	var $stMachineId = $.config.get("uniqueId") || "00-E2-69-1F-8D-3D";
	var _timer = null;
	var _http = function(method, data, callback, error) {
		var httpConfig = angular.merge({
			stMachineId: $stMachineId,
			jsonpCallback: "JSON_CALLBACK"
		}, data);
		$http.jsonp($ConfigUrl + method, {
				params: httpConfig
			})
			.success(function(dataJsonp) {
				callback && callback(dataJsonp);
			})
			.error(function(err) {
				$.log.debug("err:" + JSON.stringify(err))
				error && error(err);
				console.log(err);
			})

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
	//自定义柜号
	var sarks = [
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
		"30"
	];
	// 工作人员信息列表
	var personnel = [
		{
			Name: "查理多",
			Code: "310109198509201027",
			Tel: "13636359915"
		},
		{
			Name: "董丽丽",
			Code: "310109198112190040",
			Tel: "13901857953"
		},
		{
			Name: "李金鹏",
			Code: "41108119960815155X",
			Tel: "15560213236"
		},
		{
			Name: "马文骏",
			Code: "310110198307093238",
			Tel: "13916696617"
		},
		{
			Name: "邹天奇",
			Code: "430426199804106174",
			Tel: "18692067056"
		},
		{
			Name: "李华熙",
			Code: "520222199406140030",
			Tel: "18586469816"
		},
		{
			Name: "陈雷",
			Code: "310228198808070818",
			Tel: "18017070753"
		},
		{
			Name: "谢敏",
			Code: "310108198609011064",
			Tel: "13818795888"
		},
		{
			Name: "李超强",
			Code: "3101104198911240014",
			Tel: "15821131336"
		}
	];

	//初始化证照柜柜号
	let $stCabinetNos = '01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30'
	let initCabinetNos = function() {
		_http(
			'initCabinet.do', {
				stMachineId: $stMachineId,
				stCabinetNos: $stCabinetNos
			},
			function(dataJsonp) {
				//				console.log(dataJsonp)
			},
			function(err) {
				console.log(err)
			}
		)
	}
	initCabinetNos()

	//查询空柜子列表
	let getEmptyCabinet = function(callback) {
		_http(
			'getEmptyCabinet.do', {
				stMachineId: $stMachineId
			},
			function(dataJsonp) {
				callback && callback(dataJsonp)
			},
			function(err) {
				console.log(err)
			}
		)
	}

	var usedSark = [];
	var preUrl = "http://130.129.1.169:8080/ac-product";
	var getResideQuantity = function() {
		_http("getCertFlowOnUse.do", {}, function(res) {
			$rootScope.resideQuantity = 36 - res.cabList.length;
			for(var i = 0; i < res.length; i++) {
				usedSark.push(res[i]);
			}
		});
	}
	//	getResideQuantity();

	return {
		httpUrl: $ConfigUrl, //接口地址
		stMachineId: $stMachineId, //证照柜设备识别码
		http: _http, //封装jsonp接口
		recognition: _recognition, //人证核验接口
		sark: usedSark, //已使用的柜子
		initSark: sarks, //初始柜子编号
		personnel: personnel, //工作人员信息
		preUrl: preUrl, //获取身份证头像base64地址
		getResideQuantity: getResideQuantity, //获取所有柜子状态
		getEmptyCabinet: getEmptyCabinet // 空柜子查询函数
	}
});
app.directive("headHolder", function() {
	return {
		restrict: "E",
		templateUrl: "views/head.html",
		terminal: true,
		controller: function($scope, $rootScope, $http, appConfig, appData, $location, $interval) {
			appConfig.getEmptyCabinet(function(res) {
				console.log(res.data)
				$rootScope.resideQuantityList = res.data
				$rootScope.resideQuantity = res.data.length
//				$scope.$apply()
			})
			$scope.goHome = function() {
				$.device.qrCodeClose();
				$.device.Camera_Hide();
				$interval.cancel($rootScope.timer);
				$location.path("/main").search({});
			};
		}
	}
});