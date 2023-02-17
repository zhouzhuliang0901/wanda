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
	$scope.funName = appData.funName = "婚姻登记档案查询";
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
		case "cloud":
			$scope.operation = "扫码登录";
			break;
	}

	//test 跳过核验
	// $scope.idcardLogin = function() {
	//     appData.licenseName = "邹天奇";
	//     appData.licenseNumber = "430426199804106174";
	//     $state.go('info');
	// }
	// $scope.idcardLogin();

	//跳转页面
	$scope.nextStep = function() {
		$state.go('info');
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
	$scope.stSex = ((parseInt($scope.licenseNumber.substring(16, 17)) % 2) == 0) ? "女" : "男";
	if($scope.stSex == "男") {
		$scope.maleName = appData.licenseName;
		$scope.maleIdCardNum = appData.licenseNumber;
	} else {
		$scope.femaleName = appData.licenseName;
		$scope.femaleIdCardNum = appData.licenseNumber;
	}
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoading = false;
	$scope.expressTrue = false;
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
	$scope.expressList = express;
	$scope.change1 = function(index, item) {
		$scope.current1 = index;
		$scope.useGoal = item.name;
	}
	$scope.change = function(index, item) {
		$scope.current = index;
		appFactory.getAddressInfo(item.name, '', function(result) {
			$scope.provinceList = result;
		});
		$scope.countyList = "";
		$scope.ascceptanceArea = item.name;
	}

	$scope.chooseExpress = function(index, item) {
		$scope.current2 = index;
		if(index != 0) {
			$scope.expressTrue = true;
		} else {
			$scope.expressTrue = false;
		}
	}
	$scope.$watch('province', function(val) {
		if(val) {
			appFactory.getAddressInfo('', val.cityId, function(result) {
				$scope.countyList = result;
			});
		}
	})

	//获取用户id
	$scope.getUserInfoByAccessToken = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getUserInfoByAccessToken.do",
			dataType: "json",
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
		//      $scope.getUserInfoByAccessToken();
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

	//监听日期控件 变化
	$scope.nextStep = function() {
		$scope.isLoding = true;
		// 表单参数数组,接口会用到
		var params = form2arr($("#form").serialize(), $(".in"));
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
			if(isBlank($('#stSsxxDate').val())) {
				$scope.isAlert = true;
				$scope.msg = "请选择婚姻登记时间";
				return
			}
			if(isBlank($scope.maleName)) {
				$scope.isAlert = true;
				$scope.msg = "请填写男方姓名";
				return
			}
			if(isBlank($scope.maleIdCardNum)) {
				$scope.isAlert = true;
				$scope.msg = "请填写男方证件号";
				return
			}
			if(isBlank($scope.femaleName)) {
				$scope.isAlert = true;
				$scope.msg = "请填写女方姓名";
				return
			}
			if(isBlank($scope.femaleIdCardNum)) {
				$scope.isAlert = true;
				$scope.msg = "请填写女方证件号";
				return
			}
			if(isBlank(params.expressMode)) {
				$scope.isAlert = true;
				$scope.msg = "请选择递送方式";
				return
			}
			if($scope.expressTrue) {
				if(isBlank($scope.addressee)) {
					$scope.isAlert = true;
					$scope.msg = "请填写收件人";
					return
				}
				if(isBlank($scope.mailAddress)) {
					$scope.isAlert = true;
					$scope.msg = "请填写邮件地址";
					return
				}
				if(isBlank($scope.mailNum)) {
					$scope.isAlert = true;
					$scope.msg = "请填写邮政编码";
					return
				}
			}
		} while (condFlag);
		condFlag = true;
		var basicParams = {
			ApplyNo: '',
			sheng: params.ascceptanceArea,
			ApplyerPageCode: params.stIdCard,
			Username: params.stName,
			userId: appData.zwdtsw_user_id,
			Mobile: params.stMobile,
			UsePurpose: params.useGoal,
			ReceiptDepartCode: $scope.county.archivesId,
			ReceiptOrganName: params.county,
			ArchivesType: '01', // 婚姻登记档案
			UseWay: $scope.expressid,
			ApplyDate: getCurrentDate(2),
		}
		var detailParams = {
			Year: $('#stSsxxDate').val(),
			Man: params.maleName,
			Woman: params.femaleName,
			Manidenfication: params.maleIdCardNum || '',
			Womanidenfication: params.femaleIdCardNum || ''
		}
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/archivesForCSJ/CSJArchiveApply.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				basicParams: encodeURI(JSON.stringify(basicParams)),
				detailParams: encodeURI(JSON.stringify(detailParams)),
				Addressee: encodeURI(params.addressee || ''),
				Mailingaddress: encodeURI(params.mailAddress || ''),
				yzbm: params.mailNum || '',
				itemCode: '3120901560000'
			},
			success: function(dataJson) {
				$scope.isLoding = false;
				try {
					if(dataJson.code == '200') {
						//提交参数集合
						appData.applyNo = dataJson.ApplyNo;
						$state.go('submit');
					} else {
						$scope.isAlert = true;
						$scope.msg = "结果提交失败";
					}
				} catch (e) {
					$scope.isAlert = true;
					$scope.msg = "提交接口异常,请稍候再试";
				}
			},
			error: function(err) {
				$scope.isLoding = false;
				$scope.isAlert = true;
				$scope.msg = "结果提交失败";
			}
		});
	}
});
app.controller('submit', function($state, $scope, appData) {
	$scope.itemName = appData.funName;
	$scope.applyNo = appData.applyNo;
	$scope.licenseName = appData.licenseName || "邹天奇";
	$scope.date = getCurrentDate(2);
	$scope.nextText = "返回首页";
	//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: appData.funName,
			Number: $scope.applyNo,
		}
	}
	recordUsingHistory('长三角服务', '办理', appData.funName, appData.licenseName, appData.licenseNumber, '', $scope.applyNo, JSON.stringify($scope.jsonStr));
	// 行为分析(办理)
	trackEventForAffairs('', appData.funName, "上海市档案局", appData.licenseName, appData.licenseNumber, '');

	$scope.goHome = function() {
		$.device.GoHome();
	}
});