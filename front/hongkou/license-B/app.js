var app = angular.module("Arklicense", ["ngAnimate", "ui.router", ]);
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
app.factory("appConfig", function($http, $interval, $location,$rootScope) {
	var data = {};
	// var $ConfigUrl = "http://31.0.178.74:8100/ac/aci/certflow/"; //证照柜测试接口地址
	var $ConfigUrl = "https://yjssb.shhk.gov.cn/ac-product/aci/certification/"; //证照柜接口地址
	//柜子标识
	var $stMachineId = "B";
	var _timer = null;
	var _http = function(method, data, callback, error) {
		var httpConfig = angular.merge({
			ST_CERT_EQUIPMENT_NO: $stMachineId,
			ST_CERT_IFICATION_TYPE:1,
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
				url: "http://hengshui.5uban.com/xhac/aci/witnesscheck/pairVerify.do",
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
		"30"
	];
	var personnel = [
// 		{
// 			Name: "邹天奇",
// 			Code: "430426199804106174"
// 		},
// 		{
// 			Name: "陈云翔",
// 			Code: "310105197805313613"
// 		},
// 		{
// 			Name: "陈雷",
// 			Code: "310228198808070818"
// 		},
// 		{
// 			Name: "刘鹏",
// 			Code: "421022199401152434"
// 		}
	];
	var useSark = [];
	
	var preUrl = "http://hengshui.5uban.com/ac";
	var getResideQuantity = function() {
		
		_http("queryCertIficationInfo.do", {}, function(res) {
			$rootScope.resideQuantity = 30 - res.length;
			for(var i=0;i<res.length;i++){
				useSark.push(res[i].stCertIficationNo);
			}
			console.log("appConfig count!")
		});
	}
	var getPersonList = function(){
		var pConfig = {
			jsonpCallback: "JSON_CALLBACK"
		}
		$http.jsonp($ConfigUrl+"certIficationStorageperMissions.do",{
			params:pConfig,
		}).success(function(dataJsonp) {
			for(var i=0;i<dataJsonp.length;i++){
				personnel.push({
					Name:dataJsonp[i].stUserName,
					Code:dataJsonp[i].stCertNo
				})
			}
		})
		.error(function(err) {
			$.log.debug("err:" + JSON.stringify(err))
		})
	}
	getResideQuantity();
	getPersonList();
	console.log(useSark);
	console.log(personnel);
	return {
		httpUrl: $ConfigUrl, //接口地址
		stMachineId: $stMachineId, //证照柜设备识别码
		http: _http, //封装jsonp接口
		recognition: _recognition, //人证核验接口
		sark: useSark, //使用的柜子
		initSark: sarks, //初始柜子编号
		personnel: personnel, //工作人员信息
		preUrl: preUrl, //获取身份证头像base64地址
		getResideQuantity:getResideQuantity
	}
});
app.directive("headHolder", function() {
	return {
		restrict: "E",
		templateUrl: "views/head.html",
		terminal: true,
		controller: function($scope,$rootScope, $http, appConfig, appData, $location, $interval) {
			$rootScope.resideQuantity = 30;

			$interval(function() {
				$rootScope.resideQuantity = appData.resideQuantity || $rootScope.resideQuantity;
				if(appData.resideQuantity < 1 || $rootScope.resideQuantity < 1) {
					$rootScope.resideQuantity = 0;
				}
			}, 1000);
			$scope.goHome = function() {
				$.device.qrCodeClose();
				$.device.Camera_Hide();
				$location.path("/main").search({});
			};
		}
	}
});