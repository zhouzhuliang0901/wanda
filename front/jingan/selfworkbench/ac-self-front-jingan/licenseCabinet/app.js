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
app.filter("hideTel",function(){
	return function(text,num){
		num = num>3&&num<20?num:1;//设置默认替换长度
		text = text+"";
		var newText = text.substring(0,num)
		+text.substring(num,text.length-num).replace(/\d/g,"*")+text.substring(text.length-num,text.length);
		return newText;
	}
})
app.factory("appConfig", function($http, $interval, $location,$rootScope) {
	var data = {};
	//var $ConfigUrl = "http://zwdt.huangpuqu.sh.cn:8080/ac/aci/certflow/"; //证照柜测试接口地址
	var $ConfigUrl = "http://101.230.224.65:8080/ac/aci/certflow/"; //证照柜测试外网接口地址
	//var $ConfigUrl = "http://31.0.161.7:8100/ac/aci/certflow/"; //证照柜接口地址
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
				//url: "http://zwdt.huangpuqu.sh.cn:8080/ac/aci/witnesscheck/pairVerify.do",
				url: "http://zwdt.huangpuqu.sh.cn:8080/ac/aci/witnesscheck/pairVerify.do",//外网地址
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
		"05"
		
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
			Name: "刘俊",
			Code: "31011419830906089X"
		},
			{
			Name: "柏金坪",
			Code: "320831199503150013"
		},
		{
			Name: "陈云翔",
			Code: "310105197805313613"
		},
		{
			Name: "刘超寅",
			Code: "310104198607210857"
		},
		{
			Name: "李潜",
			Code: "430524199103278670"
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
		}
	];
	var useSark = [];
	var preUrl = "http://zwdt.huangpuqu.sh.cn:8080/ac";//外网地址
	//var preUrl = "http://zwdt.huangpuqu.sh.cn:8080/ac";
	useSark = useSark.concat(sarks);
	var getResideQuantity = function() {
		_http("getOnUse.do", {}, function(res) {
			$rootScope.resideQuantity = 5 - res.cabList.length;
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
		controller: function($scope,$rootScope, $http, appConfig, appData, $location, $interval,$state) {
			$rootScope.resideQuantity = 5;

			$interval(function() {
				$rootScope.resideQuantity = appData.resideQuantity || $rootScope.resideQuantity;
				if(appData.resideQuantity < 1 || $rootScope.resideQuantity < 1) {
					$rootScope.resideQuantity = 0;
				}
			}, 1000);
			$scope.goHome = function() {
				$.device.Camera_UnLink();
				$.device.qrCodeClose();
				$.device.Camera_Hide();
				$interval.cancel($rootScope.timer);
				$location.path("/main").search({});
				if($state.$current.name == "main"){
					$.device.GoHome();
				}else{
				$location.path("/main").search({});
				}
			};
		}
	}
});