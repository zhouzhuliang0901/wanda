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
	$scope.funName = appData.funName = "知青档案查询";
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
app.controller('info', function($state, $scope, appData, $timeout, appFactory, $http) {
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
	$(".form_datetime").datetimepicker({
		format: "yyyy-mm-dd", //显示日期格式
		autoclose: true,
		todayBtn: true,
		minView: "month", //只选择到天自动关闭
		language: 'zh-CN',
		pickerPosition: 'bottom-left',
	});
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$scope.isLoading = false;
	}
	$scope.ascceptanceAreaList = province;
	$scope.useGoalList = useGoal;
	$scope.expressModeList = expressMode;
	$scope.change = function(type, index, item) {
		if(type == "useGoal") {
			$scope.current1 = index;
			$scope.useGoal = item.name;
		} else if(type == "ascceptanceArea") {
			$scope.current = index;
			appFactory.address_info(item.name, '', function(dataJson) {
				$scope.provinceList = dataJson;
			});
			$scope.countyList = "";
			$scope.ascceptanceArea = item.name;
		} else if(type == "expressMode") {
			$scope.current2 = index;
			$scope.expressModeId = item.id;
			$scope.expressMode = item.name
		}
	}
	//获取用户id
	$scope.getUserInfoByAccessToken = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getUserInfoByAccessToken.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				accessToken: appData.token
			},
			success: function(dataJson) {
				console.log(dataJson);
				if(dataJson.zwdtsw_user_id != undefined && dataJson.zwdtsw_user_id != null && dataJson.zwdtsw_user_id != "") {
					appData.zwdtsw_user_id = dataJson.zwdtsw_user_id;
				} else {
					$scope.isAlert = true;
					$scope.msg = dataJson.ERROR || '未获得对应的用户标识！';
					$scope.alertConfirm = function() {
						$state.go("loginType");
					}
				}
			},
			error: function(err) {
				console.log("getUserInfoByAccessToken err");
				$scope.isAlert = true;
				$scope.msg = '未获得对应的用户标识！';
				$scope.alertConfirm = function() {
					$state.go("loginType");
				}
			}
		});
	}
	if(appData.zwdtsw_user_id) {} else {
		$scope.getUserInfoByAccessToken();
	}
	$scope.$watch('province', function(val) {
		if(val) {
			appFactory.address_info('', val.cityId, function(dataJson) {
				$scope.countyList = dataJson;
			})
		}
	})

	//提交
	$scope.nextStep = function() {
		$scope.paramsInfo = form2arr($('#infoParams').serialize(), $(".in"));
		console.log($scope.paramsInfo);
		var condFlag = false;
		do {
			if(!isPhoneAvailable($scope.stMobile)) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的手机号";
				return
			}
			if(isBlank($scope.useGoal)) {
				$scope.isAlert = true;
				$scope.msg = "请选择利用目的";
				return
			}
			if(isBlank($scope.ascceptanceArea)) {
				$scope.isAlert = true;
				$scope.msg = "请选择受理地区";
				return
			}
			if(isBlank($scope.province)) {
				$scope.isAlert = true;
				$scope.msg = "请选择受理地点市";
				return
			}
			if(isBlank($scope.county)) {
				$scope.isAlert = true;
				$scope.msg = "请选择受理地点区";
				return
			}
			if(isBlank($scope.expressMode)) {
				$scope.isAlert = true;
				$scope.msg = "请选择递送方式";
				return
			}
			if(($scope.current2 == 1 || $scope.current2 == 2) && isBlank($scope.stReceiptName)) {
				$scope.isAlert = true;
				$scope.msg = "请输入收件人姓名";
				return
			}
			if(($scope.current2 == 1 || $scope.current2 == 2) && isBlank($scope.stReceiptAddress)) {
				$scope.isAlert = true;
				$scope.msg = "请输入邮政地址";
				return
			}
			if(($scope.current2 == 1 || $scope.current2 == 2) && isBlank($scope.stPostCode)) {
				$scope.isAlert = true;
				$scope.msg = "请输入邮政编码";
				return
			}
		} while (condFlag);
		condFlag = true;
		//提交参数
		let basicParams = {
			ApplyNo: "",
			sheng: $scope.paramsInfo.ascceptanceArea,
			ApplyerPageCode: $scope.paramsInfo.stIdCard,
			Username: $scope.paramsInfo.stName,
			userId: appData.zwdtsw_user_id,
			Mobile: $scope.paramsInfo.stMobile,
			UsePurpose: $scope.paramsInfo.useGoal,
			ReceiptDepartCode: $scope.county.archivesId,
			ReceiptOrganName: $scope.paramsInfo.county,
			ArchivesType: "03", //知青档案
			UseWay: $scope.expressModeId,
			Addressee: $scope.paramsInfo.stReceiptName,
			Mailingaddress: $scope.paramsInfo.stReceiptAddress,
			yzbm: $scope.paramsInfo.stPostCode,
			ApplyDate: getCurrentDate(2)
		};
		let detailParams = {
			Name: $scope.paramsInfo.stName,
			Sex: IdCard($scope.paramsInfo.stIdCard, 2),
			Countrysideaddress: $scope.paramsInfo.stSsxxAddress || "",
			Waygoingtime: $scope.paramsInfo.stSsxxDate || "",
		}
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/archivesForCSJ/CSJArchiveApply.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				basicParams: encodeURI(JSON.stringify(basicParams)),
				detailParams: encodeURI(JSON.stringify(detailParams))
			},
			success: function(res) {
				console.log(res);
				$scope.isLoading = false;
				if(res.code == "200") {
					$state.go('submit');
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
	recordUsingHistory('长三角服务', '办理', $scope.funName, appData.licenseName, appData.licenseNumber, '', $scope.applyNo, JSON.stringify($scope.jsonStr));
	//行为分析(办理)
	trackEventForAffairs($scope.applyNo, $scope.funName, "长三角服务", appData.licenseName, appData.licenseNumber, '');
	$scope.goHome = function() {
		$.device.GoHome();
	}
});