var app = angular.module("Arklicense", ["ngAnimate", "ui.router"]);
app.value("appData", {});
app.run(["$rootScope", '$log', "$location", function($rootScope, $log, $location) {
	$rootScope.$on("$viewContentLoaded", function(event, toState) {
		$rootScope.isMain = ($location.path() === "/main"); //是不是主页面
		try {
			$rootScope.controllerName = toState.controller;
		} catch (error) {
			$rootScope.controllerName = 'main';
		}

	});
}]);
app.filter("hideTel", function() {
	return function(text, num) {
		num = num > 3 && num < 20 ? num : 1; //设置默认替换长度
		text = text + "";
		var newText = text.substring(0, num) +
			text.substring(num, text.length - num).replace(/\d/g, "*") + text.substring(text.length - num,
				text.length);
		return newText;
	}
})
app.factory("appConfig", function($http, $interval, $location, $rootScope) {
	var data = {};
	var $ConfigUrl = "http://180.169.7.197:8080/ac-self-api/selfapi/certCabinet/";
	//	var $ConfigUrl = "http://117.184.33.148:8080/ac-product/selfapi/certCabinet/";
	var $stMachineId = $.config.get("uniqueId") || "12-12-12-12-12";
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
	if ($.config.get('licenseCabinetVersion') == "old") {
		let $stCabinetNos = '01,02,03,04,05,06,07,08,09,10,11,12'
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
			"12"
		];
	} else if ($.config.get('licenseCabinetVersion') == 'hangxin36' || $.config.get('licenseCabinetVersion') == 'hangxin36(2)') {
		let $stCabinetNos =
			'01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36'
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
		];
	} else if ($.config.get('licenseCabinetVersion') == 'hangxin60') {
		let $stCabinetNos =
			'01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60'
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
			"60",
		];
	}  else if ($.config.get('licenseCabinetVersion') == 'xige') {
		let $stCabinetNos =
			'01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30'
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
		];
	}else {
		let $stCabinetNos = '01,02,03,04,05,06,07,08,09,10,11,12'
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
			"12"
		];
	}
	// 工作人员信息列表
	var personnel = [{
			Name: "李宜锋",
			Code: "530103197707032939",
			Tel: "13381510365",
			Mac: '08-1F-71-31-89-BE'
		},
		{
			Name: "李金鹏",
			Code: "41108119960815155X",
			Tel: "15560213236"
		},
		{
			Name: "吴杰",
			Code: "511902200101100131",
			Tel: "15221250495"
		},
		{
			Name: "马文骏",
			Code: "310110198307093238",
			Tel: "13916696617"
		},
		{
			Name: "邹天奇",
			Code: "430426199804106174",
			Tel: "18692067056",
			Mac: '12-12-12-12-12'
		},
		{
			Name: "陈雷",
			Code: "310228198808070818",
			Tel: "18017070753"
		},
		{
			Name: "郭戌",
			Code: "330621198805270014",
			Tel: "18049748672"
		},
		{
			Name: "谢敏",
			Code: "310108198609011064",
			Tel: "13818795888"
		},
		{
			Name: "陆茵",
			Code: "310113197701127525",
			Tel: "13761686114",
			Mac: '34-7F-16-C0-D9-0D'
		},
		{
			Name: "吴珺",
			Code: "310107198610223981",
			Tel: "13754010243"
		},
		{
			Name: "徐静怡",
			Code: "310107199007310081",
			Tel: "15901872233"
		},
		{
			Name: "胡瞬骏",
			Code: "310104199002164813",
			Tel: "15921507497"
		},
		{
			Name: "袁菁",
			Code: "310107198008012022",
			Tel: "13917745788"
		},
		{
			Name: "李宜锋",
			Code: "530103197707032939",
			Tel: "021-56523016",
			Mac: '08-1F-71-31-89-BE'
		},
		{
			Name: "施芝雯",
			Code: "310107198809153028",
			Tel: ""
		},
		{
			Name: "张建华",
			Code: "310108198705220632",
			Tel: ""
		},
		{
			Name: "陈寅",
			Code: "310107197612085477",
			Tel: "13761607884",
			Mac: '50-2B-73-A9-13-B8'
		},
		{
			Name: "张岚",
			Code: "310106198305294023",
			Tel: "13817638841",
			Mac: '68-ED-A4-29-69-5B'
		},
		{
			Name: "关欣",
			Code: "310102198407191628",
			Tel: "13818867016",
			Mac: ''
		},
		{
			Name: "王耀申",
			Code: "310103198012283217",
			Tel: "13917393550",
			Mac: '68-ED-A4-40-FA-CF'
		},
		{
			Name: "徐宗浩",
			Code: "310103198409185033",
			Tel: "13761387537",
			Mac: ''
		},
		{
			Name: "宋燕琳",
			Code: "310103198401277080",
			Tel: "13701913106",
			Mac: ''
		},
		{
			Name: "卞洪霞",
			Code: "310103197705280823",
			Tel: "15801882230",
			Mac: ''
		}, 
		{
			Name: "朱玉麟",
			Code: "31010719821213043X",
			Tel: "13918185922",
			Mac: '68-ED-A4-40-FA-89'
		},
		{
			Name: "郭书桓",
			Code: "412823200204032411",
			Tel: "17839690838",
			Mac: ''
		},
		{
			Name: "李俊",
			Code: "310103198209165011",
			Tel: "13381773811",
			Mac: ''
		}, {

			Name: "施珍怡",
			Code: "310103199207265021",
			Tel: "13816355718",
			Mac: ''
		},
		{ 
			Name: "赵晓光",
			Code: "31010719821030095X",
			Tel: "13524656847",
			Mac: ''
		},
		{
			Name: "陆瑜豪",
			Code: "310226199606224518",
			Tel: "18049885236",
			Mac: '20-0D-B0-1E-51-01'

		},
		{
			Name: "宋佳",
			Code: "310115198812285646",
			Tel: "13636318926",
			Mac: '68-ED-A4-5C-33-82'
		},
		{
			Name: "沈宇晶",
			Code: "310115198208205822",
			Tel: "13916453605",
			Mac: ''

		},
		{
			Name: "范莉",
			Code: "320223198112151126",
			Tel: "13916485731",
			Mac: ''

		},
		{
			Name: "蒋薇琼",
			Code: "310103198701027024",
			Tel: "13801758257",
			Mac: '68-ED-A4-5C-32-5A'
		},
		{
			Name: "戚长鸣",
			Code: "310103198201116039",
			Tel: "13817276362",
			Mac: ''

		},
		{
			Name: "俞潞琪",
			Code: "310230197507076674",
			Tel: "13801796918",
			Mac: ''

		},
		{
			Name: "钱丽萍",
			Code: "310225197902282442",
			Tel: "13817053113",
			Mac: '68-ED-A4-5C-34-94'

		},
		{
			Name: "阙雯琦",
			Code: "310106198702071624",
			Tel: "13816635177",
			Mac: ''

		},
		{
			Name: "陈雯",
			Code: "340223198611164646",
			Tel: "13816152036",
			Mac: ''

		},
		{
			Name: "范沁",
			Code: "310107198411154426",
			Tel: "13764247688",
			Mac: '68-ED-A4-5C-34-86'

		}
	];

	//初始化证照柜柜号
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
	//	initCabinetNos()

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
			for (var i = 0; i < res.length; i++) {
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
			$scope.saveDeviceInfo = function() {
				$location.path("/saveDeviceInfo").search({});
			}

		}
	}
});
