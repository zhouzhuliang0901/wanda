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
	appData.funName = "来沪人员灵活就业登记中止";
	appData.itemNo = $scope.itemNo = "312090049000";
	$scope.stuffName = appData.funName;
	$scope.info = "<p>本事项办理需符合以下条件：</p>" +
		"<p>1、	本人办理</p>" +
		"<p>2、	来沪人员</p>" +
		"<p>3、	男性16~60岁或女性16~55岁</p>";
	
	$scope.prevStep = function(){
		window.location.href="../aSocial/index.html#/flexibleEmployment";
	}
	//获取统一审批编码
	$scope.getApplyNoByItemNo = function(code) {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + '/aci/workPlatform/elderlyCard/getApplyNoByItemNo.do',
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data: {
				itemCode: code
			},
			success: function(dataJson) {
				if(dataJson.success === true) {
					appData.applyNo = dataJson.aplyNo;
				} else {
					appData.applyNo = "";
				}
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	$scope.getApplyNoByItemNo(appData.itemCode);
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

	//判断是否符合办理条件
	$scope.handleCondition = function() {
		$scope.paramStr = {
			cetf_id: appData.licenseNumber
		}
		appFactory.pro_fetch("LD0019Q1", "ld", appData.applyNo, JSON.stringify($scope.paramStr), function(dataJson) {
			console.log(dataJson);
			$scope.isLoading = false;
			if(!isBlank(dataJson)) {
				$scope.head = dataJson.data.msg.head;
				$scope.body = dataJson.data.msg.body;
				if(!isBlank($scope.head)) {
					if($scope.head.rst.buscode == "000000" && !isBlank($scope.body.hylb_id)) {
						appData.hylbResult = $scope.body;
						$state.go("info");
					} else {
						$scope.isAlert = true;
						$scope.msg = "您未做过灵活就业登记！，不能办理该业务";
					}
				} else {
					$scope.isAlert = true;
					$scope.msg = "您未做过灵活就业登记，不能办理该业务";
				}
			} else {
				$scope.isAlert = true;
				$scope.msg = "查询接口异常,请稍后再试";
			}
		}, function(err) {
			$scope.isLoading = false;
			$scope.isAlert = true;
			$scope.msg = "查询接口异常,请稍后再试";
		});
	}

	//test 跳过核验
	//	$scope.idcardLogin = function() {
	//		appData.licenseNumber = "332522197006010310";
	//		appData.licenseName = "陈崇海";
	//		$scope.handleCondition();
	//	}
	//	$scope.idcardLogin();

	//跳转页面
	$scope.nextStep = function() {
		$scope.tokenType = "token";
		$scope.token = function() {
			$scope.handleCondition();
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
		$scope.handleCondition();
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
app.controller('info', function($state, $scope, appData, appFactory, $http) {
	$scope.operation = "请填写基本信息";
	$scope.nextText = "提交";
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$(".form_datetime").datetimepicker({
		format: "yyyy-mm-dd", //显示日期格式
		autoclose: true,
		todayBtn: true,
		minView: "month", //只选择到天自动关闭
		language: 'zh-CN',
		pickerPosition: 'bottom-left',
		initialDate: new Date(),
	});
	$(".form_datetime").datetimepicker("setDate", new Date());
	//接口获取信息预填
	$scope.stName = appData.licenseName;
	$scope.stIdCard = appData.licenseNumber;
	if(!isBlank(appData.hylbResult)) {
		$scope.jyzt = appData.hylbResult.jyzt;
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

	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.stopDate)){
				$scope.isAlert = true;
				$scope.msg = "请选择就业中止时间";
				return;
			}
		} while (condFlag);
		//提交参数集合
		condFlag = true;
		//办件信息同步接口
		$scope.saveItemInfo = function(result) {
			$http.get($.getConfigMsg.preUrlSelf + "/selfapi/pensionAdjustment/saveItemInfo.do", {
				params: {
					userId: appData.zwdtsw_user_id,
					itemCode: encodeURI(appData.itemCode),
					itemName: encodeURI(appData.funName),
					username: encodeURI(appData.licenseName),
					mobile: "",
					idCardNo: appData.licenseNumber,
					result: result
				}
			}).success(function(dataJson) {
				if(dataJson.code == "200") {
					appData.applyNo = dataJson.data.applyNo;
					if(result == "1") {
						$state.go("submit");
					} else {
						$state.go("guideline");
					}
				} else {
					$scope.isLoading = false;
					appData.applyNo = "";
					$scope.isAlert = true;
					$scope.msg = "办件信息同步失败,请稍后再试";
				}
			}).error(function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "办件信息同步异常,请稍后再试";
			});
		}
		//人社不见面统一 -- 发放形式保存接口
		$scope.saveApplyInfo = function() {
			$scope.isLoading = true;
			$scope.saveParamStr = {
				czdm: "Z",
				jyzzrq: $("#stopDate").val(),
				cetf_id: appData.licenseNumber,
				userid: ""
			}
			console.log($scope.saveParamStr);
			appFactory.pro_fetch("LD0019S3", "ld", appData.applyNo, encodeURI(JSON.stringify($scope.saveParamStr)), function(dataJson) {
				console.log(dataJson);
				if(!isBlank(dataJson)) {
					$scope.head = dataJson.data.msg.head;
					$scope.body = dataJson.data.msg.body;
					if(!isBlank($scope.head)) {
						if($scope.head.rst.buscode == "000000") {
							$scope.saveResult = $scope.body;
							$scope.saveItemInfo("1");
						} else {
							$scope.isAlert = true;
							$scope.msg = $scope.head.rst.errmsg;
							$scope.alertConfirm = function() {
								$scope.isAlert = false;
								$scope.saveItemInfo("0");
							}
						}
					} else {
						$scope.isLoading = false;
						$scope.isAlert = true;
						$scope.msg = "保存接口异常,请稍后再试";
					}
				} else {
					$scope.isLoading = false;
					$scope.isAlert = true;
					$scope.msg = "保存接口异常,请稍后再试";
				}
			}, function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "保存接口异常,请稍后再试";
			});
		}
		$scope.saveApplyInfo();
	}

});
app.controller('submit', function($state, $scope, appData) {
	$scope.funName = appData.funName;
	$scope.applyNo = appData.applyNo;
	$scope.nextText = "返回首页";
	//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: $scope.funName,
			Number: $scope.applyNo,
		}
	}
	recordUsingHistory('人社服务', '办理', $scope.funName, appData.licenseName, appData.licenseNumber, '', $scope.applyNo, JSON.stringify($scope.jsonStr));
	//行为分析(办理)
	trackEventForAffairs($scope.applyNo, $scope.funName, "上海市人力资源社会保障局", appData.licenseName, appData.licenseNumber, '');
	$scope.goHome = function() {
		$.device.GoHome();
	}
});