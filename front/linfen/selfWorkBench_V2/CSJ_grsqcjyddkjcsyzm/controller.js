function removeAnimate(ele) {
	$(ele).css({
		"transform": "translateY(0px)",
		"top": 0
	}).removeClass('transformto')
}

function addAnimate(ele) {
	$(ele).addClass('transformto').on("animationend", function() {
		$(ele).removeClass('transformto')
	})
}
app.controller("guideline", function($scope, $state, appData, $http, $timeout, $rootScope) {
//	$.getConfigMsg.preUrlSelf = "http://180.169.7.194:8081/ac-self"
	$scope.funName = appData.funName = "个人申请出具异地贷款缴存使用证明";
	$scope.prevStep = function() {
		window.location.href = '../CSJ_allItem/index.html';
	}
	//sign = 1 代表从长三角进入
	$scope.nextStep = function() {
		$state.go('loginType');
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: true,
			bounce: true,
			click: true,
			taps: true,
			preventDefault: false,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
	addAnimate($('.main2'))
});
app.controller('loginType', function($state, $scope, appData) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.operation = "请选择登录方式";
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("guideline");
	}
});
app.controller('login', function($scope, $http, $state, appData) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go("loginType");
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
			$scope.operation = "扫码登录";
			break;
	}
//	$.getConfigMsg.preUrlSelf = "http://180.169.7.194:8081/ac-self"
	//是否可以申请证明
	$scope.canOpenDifLoanProof = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/changjiangDelta/canOpenDifLoanProof.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				name: appData.licenseName,
				idCard: appData.licenseNumber,
			},
			success: function(res) {
				console.log(res);
				if(res.resultCode == "200") {
					$state.go('info');
				} else {
					$scope.isAlert = true;
					$scope.msg = res.resultMessage;
					$scope.alertConfirm = function() {
						$state.go('loginType');
					}
				}
			},
			erroe: function(err) {
				console.log(err);
			}
		});
	}

	//跳转页面
	$scope.nextStep = function() {
//		$scope.tokenType = "token";
//		$scope.token = function() {
//			$state.go('info');
$scope.canOpenDifLoanProof();
//		}
	}
//	$scope.idcardLogin = function() {
//		appData.licenseNumber = "320721199408210016";
//		appData.licenseName = "张子昱";
//		//					$state.go('info');
//		$scope.nextStep();
//
//		//		$scope.canOpenDifLoanProof();
//	}
//	$scope.idcardLogin();
	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
			appData.VALIDENDDAY = info.ValidtermOfEnd;
			appData.VALIDSTARTDAY = info.ValidtermOfStart;
			appData.Address = info.Address;
			appData.nation = info.People;
			if(appData.nation.lastIndexOf('族') < 0) {
				appData.nation = appData.nation + '族';
			}
		} else {
			layer.msg("没有获取到")
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		$scope.canOpenDifLoanProof();
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}

	$scope.citizenLogin = function(info) {
		function ClearBr(key) {
			key = key.replace(/\+/g, "-");
			key = key.replace(/\#/g, ",");
			return key;
		}
		if(appData.qrCodeType == "suishenma") {
			appData.licenseName = info.zwdtsw_name;
			appData.licenseNumber = info.zwdtsw_cert_id;
//			appData.encrypt_identity = ClearBr(info.encrypt_identity);
			$scope.nextStep();
		} else {
			var idcardInfo = info.result.data;
			appData.licenseName = idcardInfo.realname;
			appData.VALIDENDDAY = idcardInfo.VALIDENDDAY;
			appData.VALIDSTARTDAY = idcardInfo.VALIDSTARTDAY;
			$scope.nextStep();
		}
	}
})
app.controller('info', function($state, $scope, appData, $rootScope, $timeout, $http) {
	console.log(appData.licenseName);
	$scope.operation = "请选择查询条件";
	$scope.funName = appData.funName;
	$scope.stName = appData.licenseName;
	$scope.stIdCard = appData.licenseNumber;
	$scope.stIdCardType = "中华人民共和国居民身份证";
	$scope.licenseNumber = appData.encrypt_identity || appData.licenseNumber;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoading = false;
	$scope.nextText = "提交";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$scope.isLoading = false;
	}
	$scope.jcdprovinceList = getProvince(countyOrg, 'province');
	$scope.dkdprovinceList = getProvince(countyOrgDK, 'province');

	$scope.$watch('jcdProvince', function(val) {
		var jcdCity = filterByInfo(countyOrg, val, 'province');
		$scope.jcdCityList = getProvince(jcdCity, 'city');
	})

	$scope.$watch('jcdCity', function(val) {
		var jcdCounty = filterByInfo(countyOrg, val, 'city');
		$scope.jcdCountyList = jcdCounty;
	})

	$scope.$watch('dkdProvince', function(val) {
		var dkdCity = filterByInfo(countyOrgDK, val, 'province');
		$scope.dkdCityList = getProvince(dkdCity, 'city');
	})

	$scope.$watch('dkdCity', function(val) {
		var dkdCounty = filterByInfo(countyOrgDK, val, 'city');
		$scope.dkdCountyList = dkdCounty;
	})

	//提交
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.jcdProvince)) {
				$scope.isAlert = true;
				$scope.msg = "请选择缴存地信息（省）";
				return
			}
			if(isBlank($scope.jcdCity)) {
				$scope.isAlert = true;
				$scope.msg = "请选择缴存地信息（市）";
				return
			}
			if(isBlank($scope.jcdCounty)) {
				$scope.isAlert = true;
				$scope.msg = "请选择缴存地信息（中心）";
				return
			}
			if(isBlank($scope.dkdProvince)) {
				$scope.isAlert = true;
				$scope.msg = "请选择贷款地信息（省）";
				return
			}
			if(isBlank($scope.dkdCity)) {
				$scope.isAlert = true;
				$scope.msg = "请选择贷款地信息（市）";
				return
			}
			if(isBlank($scope.dkdCounty)) {
				$scope.isAlert = true;
				$scope.msg = "请选择贷款地信息（中心）";
				return
			}
		} while (condFlag);
		condFlag = true;

		$scope.infoParams = {
			name: encodeURI(appData.licenseName),
			idCard: appData.licenseNumber,
			dkdzxmc: encodeURI($scope.dkdCounty.orgName),
			dkdsf: encodeURI($scope.dkdProvince),
			dkdcs: encodeURI($scope.dkdCity),
			jcdsf: encodeURI($scope.jcdProvince),
			jcdcs: encodeURI($scope.jcdCity),
			jcdzxmc: encodeURI($scope.jcdCounty.orgName),
		}

		//保存接口
		$scope.save = function() {
			$.ajax({
				type: "get",
				url: $.getConfigMsg.preUrlSelf + "/selfapi/changjiangDelta/save.do",
				dataType: "jsonp",
				jsonp: "jsonpCallback",
				data: {
					"applyNo": "CSJ002200000468149",
					"itemCode": "312050534000",
					"taskHandleItem": "",
					"itemName": appData.funName,
					"targetType": encodeURI("个人"),
					"targetName": encodeURI(appData.licenseName),
					"targetNo": appData.licenseNumber,
					"userId": "",
					"username": encodeURI(appData.licenseName),
					"licenseType": "身份证",
					"licenseNo": appData.licenseNumber,
					"mobile": "",
					"departCode": "",
					"departName": "",
					"source": "网上申请",
					"submitType": "",
					"tokenSNO": appData.tokenSNO || $rootScope.tokenSNO,
					"districtCode": "",
					"info": JSON.stringify($scope.infoParams)
				},
				success: function(res) {
					console.log(res);
					if(res.isSuccess == true) {
						appData.applyNo = res.applyNo;
						$state.go('submit');
					} else {
						$scope.isAlert = true;
						$scope.msg = res.msg;
					}
				},
				error: function(err) {
					$scope.isAlert = true;
					$scope.msg = "保存接口异常,请稍候再试";
				},
			});
		}
		//提交参数
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/changjiangDelta/applyDifPlaceLoan.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				name: encodeURI(appData.licenseName),
				idCard: appData.licenseNumber,
				dkdzxmc: encodeURI($scope.dkdCounty.orgName),
				dkdsf: encodeURI($scope.dkdProvince),
				dkdcs: encodeURI($scope.dkdCity),
				jcdsf: encodeURI($scope.jcdProvince),
				jcdcs: encodeURI($scope.jcdCity),
				jcdzxmc: encodeURI($scope.jcdCounty.orgName),
			},
			success: function(res) {
				console.log(res);
				$scope.isLoading = false;
				if(res.isSuccess == true) {
					$scope.save();
				} else {
					$scope.isAlert = true;
					$scope.msg = res.resultMessage;
				}
			},
			error: function(err) {
				$scope.isAlert = true;
				$scope.msg = "提交接口异常,请稍候再试";
			},
		});
	}
});
app.controller('submit', function($state, $scope, appData) {
	$scope.itemName = appData.funName;
	$scope.applyNo = appData.applyNo;
	$scope.licenseName = appData.licenseName;
	$scope.date = getCurrentDate(2);
	$scope.nextText = "返回首页";
	$scope.sumbit = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/changjiangDelta/submit.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				applyNo: appData.applyNo,
			},
			success: function(res) {
				console.log(res);
			},
			error: function(err) {
				$scope.isAlert = true;
				$scope.msg = "提交接口异常,请稍候再试";
			},
		});
	}
	$scope.sumbit();
	//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: appData.funName,
			Number: $scope.applyNo,
		}
	}
	recordUsingHistory('长三角服务', '办理', appData.funName, appData.licenseName, appData.licenseNumber, '', $scope.applyNo, JSON.stringify($scope.jsonStr));
	//行为分析(办理)
	trackEventForAffairs($scope.applyNo, appData.funName, "长三角服务", appData.licenseName, appData.licenseNumber, '');
	$scope.goHome = function() {
		$.device.GoHome();
	}
});