function removeAnimate(ele) {
//	$(ele).css({
//		"transform": "translateY(0px)",
//		"top": 0
//	}).removeClass('transformto')
}

function addAnimate(ele) {
	$(ele).css({'margin-top':'300px','opacity':'0'});
	$(ele).animate({marginTop: '0',opacity:'1'}, 1000);
}
app.controller('main', function($state, $scope, appData, $http) {
	//removeAnimate($('.scrollBox2'));
	$scope.operation = appData.operation;
	appData.a = 1;
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	addAnimate($('.scrollBox2'))
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		appData.SwipeType = 'sbCard';
		$state.go('login');
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory, $timeout) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.isLoding = true;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$.state.go("main");
	}
	switch($scope.loginType) {
		case "idcard":
			$scope.operation = "身份证登录";
			break;
		case "ukey":
			$scope.loginBtn = true;
			$scope.operation = "ukey登录";
			break;
		case "cloud":
			$scope.operation = "随申办登录";
			break;
		case "sscard":
			$scope.operation = "社保卡登录";
			break;
	}

	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.licenseName = info.Name;
			appData.licenseNumber = info.Number;
		} else {
			layer.msg("很抱歉,没有获取到您的信息,请重试")
		}
	}

	$scope.getResult = function(img) {
		$scope.img = img;
		appData.img = img;
		$state.go("info");
	}

	$scope.prevStep = function() {
		$.device.Face_Close();
	}
	$scope.citizenLogin = function(info) {
		if(info) {
			if(appData.qrCodeType == "suishenma") {
				appData.licenseName = info.zwdtsw_name;
				appData.licenseNumber = info.zwdtsw_cert_id;
				$state.go("info");
			} else {
				var idcardInfo = info.result.data;
				appData.licenseName = idcardInfo.realname;
				appData.licenseNumber = idcardInfo.idcard;
				$state.go("info");
			}
		}
	}
	$scope.sscardLogin = function(info) {
		if(info) {
			if(info.Ssn != "" && info.Ssn != null && info.Ssn != undefined) {
				appData.licenseNumber = info.Ssn;
				appData.licenseName = info.PeopleName;
				appData.CardNo = info.CardNo;
				$.device.ssCardClose();
				$state.go("info");
			} else {
				$scope.isAlert = true;
				$scope.msg = "未读取到您的社保卡信息,请重试";
			}
		} else {
			layer.msg("没有获取到")
		}
	}
})
app.controller("info", function($scope, $state, appData, $sce, appFactory) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.isAlert = false;
	$scope.info = ""; // 存储查到的个人信息
	$scope.concel = "false";
	$scope.isLoading = false;
	$scope.getInfo = function() {
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/socialSecurityCard/getSocialSecurityCardInfo.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				zjhm: appData.licenseNumber,
				zjlx: "01", // 01:居民身份证
				sbkh: appData.CardNo || "",
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				var info = dataJson.body;
				if(!info.hasOwnProperty("xb")) {
					$scope.isAlert = true;
					$scope.msg = "未查询到代办人信息";
					$scope.alertConfirm = function() {
						$state.go("main");
						$scope.isContinue = false;
						return;
					}
				}
				$scope.info = info;
				// 性别
				$scope.sex = (info.xb == '2') ? '女' : '男';
				// 字典项查询
				$scope.nation = filterByInfo(nations, info.mz); // 民族
				$scope.country = filterByInfo(country, info.gj); // 国家
				$scope.cardType = filterByInfo(cardType, info.zjlx); // 证件类型
				$scope.status = filterByInfo(status, info.zt); // 状态
				$scope.area = filterByInfo(area, info.slqx); // 申领区县
				$scope.bank = filterByInfo(bank, info.fwyh); // 服务银行
				$scope.job = filterByInfo(job, info.zy); // 职业
				$scope.picture = 'data:image/jpg;base64,' + info.zp;
			},
			error: function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "访问失败";
				$.log.debug("err:" + JSON.stringify(err))
			}
		});

		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: "社保卡个人信息查询",
				Number: ""
			}
		}
		recordUsingHistory('人社服务', '查询', "社保卡个人信息查询", appData.licenseName, appData.licenseNumber, "", "", JSON.stringify($scope.jsonStr));
		//行为分析(查询)
		trackEventForQuery("社保卡个人信息查询", "", "查询", "上海市人力资源社会保障局", appData.licenseName, appData.licenseNumber, "");
	}
	$scope.getInfo();

	$scope.prevStep = function() {
		$state.go("loginType");
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false, // 垂直的
			hScrollbar: false, // 水平的
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});