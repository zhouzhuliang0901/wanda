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
app.factory("appConfig", function($http, $interval, $location,$rootScope) {
	var data = {};
	// var $ConfigUrl = "http://31.0.178.74:8100/ac/aci/certflow/"; //证照柜测试接口地址
	var $ConfigUrl = "http://zwdtyp.sh.gov.cn:8088/ac/aci/certflow/"; //证照柜接口地址
	var $stMachineId = $.config.get("uniqueId") || "12-12-12-12";
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
				url: "http://zwdtyp.sh.gov.cn:8088/ac/aci/witnesscheck/pairVerify.do",
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
	var personnel = [{
			Name: "任永辉",
			Code: "412702199806178415"
		},

		{
			Name: "谷玉鑫",
			Code: "430104199805131211"
		},
		{
			Name: "张彦添",
			Code: "341021199012239767"
		},
		{
			Name: "张建军",
			Code: "362330199307205799"
		},
		{
			Name: "陈云翔",
			Code: "310105197805313613"
		},
		{
			Name: "欧阳智耀",
			Code: "310103198806104014"
		},
		{
			Name: "张志辉",
			Code: "310103199109024056"
		},
		{
			Name: "陈萍萍",
			Code: "310104198408154020"
		},
		{
			Name: "李佳",
			Code: "321102198102240022"
		},
		{
			Name: "邵俊",
			Code: "310104198212042430"
		},
		{
			Name: "张冲",
			Code: "372923198810290093"
		},
		{
			Name: "姚蕾",
			Code: "342530199109011325"
		},
		{
			Name: "王红粉 ",
			Code: "320482199308273307"
		},
		{
			Name: "向宣漳",
			Code: "431224199212245602"
		},
		{
			Name: "陈洁云",
			Code: "652722198911100025"
		},
		{
			Name: "张丽媛",
			Code: "310105199209071629"
		},
		{
			Name: "邹天奇",
			Code: "430426199804106174"
		}
	];
	var useSark = [];
	var preUrl = "http://hengshui.5uban.com/ac";
	useSark = useSark.concat(sarks);
	var getResideQuantity = function() {
		_http("getOnUse.do", {}, function(res) {
			$rootScope.resideQuantity = 30 - res.cabList.length;
//			console.log("appConfig count!")
		});
	}
	getResideQuantity();
	return {
		httpUrl: $ConfigUrl, //接口地址
		stMachineId: $stMachineId, //证照柜设备识别码
		http: _http, //封装jsonp接口
		recognition: _recognition, //人证核验接口
		sark: useSark, //使用的柜子
		initSark: sarks, //初始柜子编号
		personnel: personnel, //工作人员信息
		preUrl: preUrl, //获取身份证头像base64地址
		getResideQuantity:getResideQuantity//获取所有柜子状态
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