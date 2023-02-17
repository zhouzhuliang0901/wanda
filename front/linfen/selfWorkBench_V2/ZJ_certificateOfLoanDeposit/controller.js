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
app.controller("guideline", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.funName = appData.funName = "个人申请出具异地贷款缴存使用证明";
	$scope.prevStep = function() {
		window.location.href = '../ZJ_allItem/index.html';
	}
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
app.controller('login', function($scope, $http, $state, appData, appFactory) {
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
			$scope.operation = "随申办登录";
			break;
	}

	//跳转页面
	$scope.nextStep = function() {
		$scope.tokenType = "token";
		$scope.token = function() {
			$state.go('info');
		}
	}
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
		$state.go('info');
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
			appData.encrypt_identity = ClearBr(info.encrypt_identity);
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
app.controller('info', function($state, $scope, appData, $rootScope, $timeout, appFactory, $http) {
	$scope.funName = appData.funName;
	$scope.stName = appData.licenseName;
	$scope.stIdCard = appData.licenseNumber;
	$scope.stIdCardType = "居民身份证";
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoading = false;
	$scope.nextText = "提交";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$scope.isLoading = false;
	}
	$scope.province = province;
	//监听省市 区域变化
	$timeout(function() {
		selectBlur();
		$scope.$watch("personalAccount", function(val) {
			if(val) {
				$scope.city = filterByInfo(city, val.id, 'parentid');
				$scope.provinceName = val.shortname;
				console.log($scope.city);
			}
		});
		$scope.$watch("companyAccount", function(val) {
			if(val) {
				$scope.cityName = val.shortname;
				$.ajax({
					type: "get",
					url: $.getConfigMsg.preUrlSelf + "/selfapi/loanFromDifferentPlaces/queryAllManagerCenter.do",
					dataType: "json",
					jsonp: "jsonpCallback",
					data: {
						provinceName: encodeURI($scope.provinceName || ""),
						cityName: encodeURI($scope.cityName || ""),
						centerName: "",
					},
					success: function(res) {
						res = JSON.parse(res);
						$scope.centerDatas = res.data;
						console.log($scope.centerDatas);
					},
					error: function(err) {}
				});
			}
		});
	}, 100);
	$scope.$on('fundCenter', function(event, data) {
		$scope.fundCenter = data;
	})
	//提交
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.stName)) {
				$scope.isAlert = true;
				$scope.msg = "请输入申请人姓名";
				return
			}
			if(isBlank($scope.stIdCard)) {
				$scope.isAlert = true;
				$scope.msg = "请输入申请人身份证号";
				return
			}
			if(!isPhoneAvailable($scope.stMobile)) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的手机号码";
				return
			}
			if(isBlank($scope.fundCenter)) {
				$scope.isAlert = true;
				$scope.msg = "请选择公积金管理中心";
				return
			}
		} while (condFlag);
		condFlag = true;
		//提交参数
		let params = {
			idCard: $scope.stIdCard,
			name: encodeURI($scope.stName),
			phone: $scope.stMobile,
			provinceName: encodeURI($scope.provinceName || ""),
			cityName: encodeURI($scope.cityName || ""),
			centerName: encodeURI($scope.fundCenter || ""),
		};
		console.log(params);
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/loanFromDifferentPlaces/issueCertificate.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: params,
			success: function(res) {
				console.log(res);
				$scope.isLoading = false;
				if(res.code == "0") {
					$state.go('resultList');
					//模块使用记录
					$scope.jsonStr = {
						SUCCESS: "true",
						data: {
							name: $scope.funName,
							Number: $scope.applyNo,
						}
					}
					recordUsingHistory('住建委服务', '办理', $scope.funName, appData.licenseName, appData.licenseNumber, '', $scope.applyNo, JSON.stringify($scope.jsonStr));
					//行为分析(办理)
					trackEventForAffairs($scope.applyNo, $scope.funName, "长三角服务", appData.licenseName, appData.licenseNumber, '');
				} else {
					$scope.isAlert = true;
					$scope.msg = "提交失败,请确认信息是否有误";
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
	//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: $scope.funName,
			Number: $scope.applyNo,
		}
	}
	recordUsingHistory('住建委服务', '办理', $scope.funName, appData.licenseName, appData.licenseNumber, '', $scope.applyNo, JSON.stringify($scope.jsonStr));
	//行为分析(办理)
	trackEventForAffairs($scope.applyNo, $scope.funName, "长三角服务", appData.licenseName, appData.licenseNumber, '');
	$scope.goHome = function() {
		$.device.GoHome();
	}
});
app.controller('resultList', function($state, $scope, appData) {
	$scope.itemName = appData.funName;
	$scope.isLoading = true;
	$scope.queryCertificateList = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/loanFromDifferentPlaces/queryCertificateList.do",
			dataType: "json",
			data: {
				idCard: appData.licenseNumber,
				name: encodeURI(appData.licenseName)
			},
			success: function(res) {
				$scope.isLoading = false;
				$scope.recordList = res.data;
			},
			error: function(err) {}
		});
	}
	$scope.queryCertificateList();
	$scope.queryDetail = function(zmbh) {
		appData.zmbh = zmbh;
		$state.go('resultDetail');
	}
});
app.controller('resultDetail', function($state, $scope, appData) {
	$scope.itemName = appData.funName;
	$scope.licenseName = appData.licenseName;
	$scope.licenseNumber = appData.licenseNumber;
	$scope.nextText = "打印";
	$scope.isShowPrint = false;
	$scope.isLoading = true;
	$scope.queryCertificateDetail = function(queryType) {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/loanFromDifferentPlaces/queryCertificateDetail.do",
			dataType: "json",
			data: {
				idCard: appData.licenseNumber,
				name: encodeURI(appData.licenseName),
				certNo: encodeURI(appData.zmbh),
				queryType: queryType
			},
			success: function(res) {
				$scope.isLoading = false;
				$scope.detailInfo = res.data;
			},
			error: function(err) {}
		});
	}
	$scope.queryCertificateDetail('1');
	$scope.print = function() {
		$scope.isShowPrint = "show";
		$scope.path = "D:\\pdfPrint.pdf";
		$scope.name = encodeURI(encodeURI(appData.licenseName));
		$scope.zmbh = encodeURI(encodeURI(appData.zmbh));
		$scope.pdfBase64Url = $.getConfigMsg.preUrlSelf + "/selfapi/loanFromDifferentPlaces/queryCertificateDetail.do?" +
			"idCard=" + appData.licenseNumber + "&name=" + $scope.name + "&certNo=" + $scope.zmbh + "&queryType=2"
		$.device.urlPdfPrint($scope.pdfBase64Url, $scope.path, function() {}, '');
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});