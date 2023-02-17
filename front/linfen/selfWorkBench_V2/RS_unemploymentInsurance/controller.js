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
app.controller('loginType', function($state, $scope, appData, $location, appFactory) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	trackEvent($(".headName").text());
	$scope.operation = "请选择登录方式";
	appData.funName = "失业保险金申领";
	appData.itemCode = "3120W0747000";
	appData.xsbb = false;
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		// window.location.href="../aSocial/index.html#";
		$.device.GoHome();
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.isLoding = true;
	appData.sign = "token";
	$scope.alertConfirm = function() {
		$state.go("loginType");
	}
	switch($scope.loginType) {
		case "idcard":
			$scope.operation = "身份证登录";
			break;
		case "cloud":
			$scope.operation = "随申办登录";
			break;
	}
		//获取token ------2、比对成功后，根据tokenSNO获取access_token
	$scope.getAccessToken = function(tokenSNO) {
		var rec = $.ajax({
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getAccessToken.do",
			type: "post",
			dataType: "json",
			jsonp: "jsonpCallback",
			timeout: 5000,
			data: {
				tokenSNO: tokenSNO,
			},
			success: function(res) {
				console.log(res);
				if(res.SUCCESS === true) {
					appData.token = res.accessToken;
					$state.go("info");
				} else {
					$scope.isAlert = true;
					$scope.msg = "数据加载异常,请重试";
					$scope.alertConfirm = function() {
						$state.go("loginType");
					}
				}
			},
			error: function(err) {
				console.log(err);
			},
			complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
				if(status === 'timeout' || status === 'error') { //超时,status还有success,error等值的情况
					rec.abort();
				}
			}
		})
	}
	//获取token ------1、两照对比获取tokenSNO
	$scope.getTokenSNO = function(face, photograph) {
		var idCardPhoto = face;
		var capturePhoto = photograph;
		var rec = $.ajax({
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getTokenSNO.do",
			type: "post",
			dataType: "json",
			//			jsonp: "jsonpCallback",
			data: {
				name: encodeURI(appData.licenseName),
				idCard: appData.licenseNumber,
				facePhoto: capturePhoto,
				copyIDPhoto: idCardPhoto
			},
			success: function(res) {
				console.log(res);
				if(res.SUCCESS === true && res.verify === 1) {
					$scope.getAccessToken(res.tokenSNO);
				} else {
					$scope.isAlert = true;
					$scope.msg = "数据加载异常,请重试";
					$scope.alertConfirm = function() {
						$state.go("loginType");
					}
				}
			},
			error: function(err) {
				console.log(err);
			},
			complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
				if(status === 'timeout' || status === 'error') { //超时,status还有success,error等值的情况
					rec.abort();
				}
			}
		})
	}
	// $scope.idcardLogin = function(){
	// 	appData.licenseNumber = '320721199408210016';
	// 	appData.licenseName = '张子昱';
	// 	$scope.getTokenSNO(recognition_base64_photo, recognition_base64_photo);
	// }
	// $scope.idcardLogin();
	$scope.idcardLogin = function(info, images) {
		if(info) {
					$scope.faceImage = images;
					appData.licenseNumber = info.Number;
					appData.licenseName = info.Name;
					appData.VALIDENDDAY = info.ValidtermOfEnd;
					appData.VALIDSTARTDAY = info.ValidtermOfStart;
					$scope.loginType = 'recognition';
		} else {
			layer.msg("没有获取到")
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		appData.img = img;
		$state.go("info");
	}

	$scope.citizenLogin = function(info) {
		let idcardInfo = info.result.data;
		appData.licenseName = idcardInfo.realname;
		appData.licenseNumber = idcardInfo.idcard;
		$scope.getTokenSNO(recognition_base64_photo, recognition_base64_photo);
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.alertCancel = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}

	$scope.citizenLogin = function(info) {
		if(appData.qrCodeType == "suishenma") {
			appData.licenseName = info.zwdtsw_name;
			appData.licenseNumber = info.zwdtsw_cert_id;
			$scope.getAccessToken(appData.tokenSNO);
		} else {
			var idcardInfo = info.result.data;
			appData.licenseName = idcardInfo.realname;
			appData.licenseNumber = idcardInfo.idcard;
			$scope.getTokenSNO(photo, photo);
			try {
				$scope.$apply();
			} catch(e) {}
		}
	}
});
app.controller('info', function($state, $scope, appData, $location, appFactory, $timeout, $rootScope,$http) {
	$scope.isLoading = true;
	appData.xsbb = true;
	$scope.getUserInfo = function() {
		$scope.applicationInfo = {
			"Cetf_id":appData.licenseNumber,
			"Psnl_name":encodeURI(appData.licenseName)
		}
		// console.log(JSON.stringify($scope.applicationInfo))
		$.ajax({
			type: "POST",
			url: $.getConfigMsg.preUrlSelf + '/selfapi/UnemploymentRegistration/LD0610Q1',
			// url: "http://localhost:8080/ac-self/selfapi/UnemploymentRegistration/LD0610Q1",
			dataType: 'json',
			data: {
				json: JSON.stringify($scope.applicationInfo)
			},
			success: function(dataJson) {
				console.log("返回参数"+ (dataJson))
				console.log(dataJson.msg.body)
				if(!isBlank(dataJson)) {
					$scope.head = dataJson.msg.head;
					$scope.body = dataJson.msg.body;
					if(!isBlank($scope.head)) {
						if($scope.head.rst.buscode == "000000") {
								appData.Per_khly = dataJson.msg.body.per_khly,
								appData.Per_khyh = dataJson.msg.body.per_khyh,
								appData.Per_lxdh = dataJson.msg.body.per_lxdh,
								appData.Per_lxdz = dataJson.msg.body.per_lxdz,
								appData.Per_yb = dataJson.msg.body.per_yb,
								appData.Per_yhzh = dataJson.msg.body.per_yhzh,
								$scope.stName = appData.licenseName,
								$scope.stIdCard = appData.licenseNumber,
								$scope.stMobile = appData.Per_lxdh,
								$scope.bankNumber = appData.Per_yhzh,
								$scope.bankListName = bankName(appData.Per_khyh,bankList),
								$scope.isLoading = false
						} else {
							$scope.isAlert = true;
							$scope.msg =$scope.head.rst.errmsg.replace(/\[.*?\]/g,'');
							$scope.alertConfirm = function() {
								$state.go("loginType");
							}
						}
					} else {
						$scope.isAlert = true;
						$scope.msg = "查询接口异常,请稍后再试";
					}
				} else {
					$scope.isAlert = true;
					$scope.msg = "查询接口异常,请稍后再试";
				}
			},
			error: function(err) {
				$scope.isAlert = true;
				$scope.msg = "查询接口异常,请稍后再试";
			}
		});
	}
	$scope.getUserInfo();
	$scope.funName = appData.funName;
	$scope.nextText = "提交"
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//银行信息
	$scope.bankList = bankList;
	//获取用户id
	$scope.getUserInfoByAccessToken = function() {
		console.log("token:"+appData.token);
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getUserInfoByAccessToken.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				accessToken: appData.token
			},
			success: function(dataJson) {
				console.log("ssssuid"+dataJson.zwdtsw_user_id);
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
	//办件入库
	$scope.submitInfo = function(result){
		$http.get($.getConfigMsg.preUrlSelf + "/selfapi/pensionAdjustment/saveItemInfo.do", {
			params: {
				userId: appData.zwdtsw_user_id,
				itemCode: encodeURI(appData.itemCode),
				itemName: encodeURI(appData.funName),
				username: encodeURI(appData.licenseName),
				mobile: $scope.stMobile,
				idCardNo: appData.licenseNumber,
				result: result
			}
		}).success(function(dataJson) {
			if(dataJson.code == "200") {
				appData.applyNo = dataJson.data.applyNo;
				if(result == "1") {
					$state.go("submit");
				} else {
					// $state.go("loginType");
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
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.stIdCard)) {
				$scope.isAlert = true;
				$scope.msg = "请填写证件号码！";
				return;
			}
			if(isBlank($scope.bankListName)) {
				$scope.isAlert = true;
				$scope.msg = "请选择开户银行！";
				return;
			}
			if(isBlank($scope.bankNumber)) {
				$scope.isAlert = true;
				$scope.msg = "请输入银行账号";
				return;
			}
		} while (condFlag);
		//提交参数集合
		condFlag = true;
		$scope.isLoading = true;
		$scope.applicationInfo1 = {
			"psnl_name":$scope.stName,
			"cetf_id":$scope.stIdCard,
			"per_khyh":$scope.bankListName.key,
			"per_yhzh":JSON.stringify($scope.bankNumber),
			"per_khly":"2",
			"per_lxdz":appData.Per_lxdz,
			"per_yb":appData.Per_yb || "",
			"per_lxdh":$scope.stMobile,
	}
	console.log("提交参数："+JSON.stringify($scope.applicationInfo1))
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/UnemploymentRegistration/LD0610S1",
			// url: "http://localhost:8080/ac-self/selfapi/UnemploymentRegistration/LD0610S1",
			dataType: "json",
			data: {
				json: JSON.stringify($scope.applicationInfo1)
			},
			success: function(dataJson) {
				console.log(dataJson);
				$scope.isLoading = false;
				if(dataJson.msg.head.rst.buscode == "000000") {
					$scope.isAlert = true;
					$scope.msg = "提交成功";
					$scope.submitInfo("1");
				} else {
					$scope.isAlert = true;
					$scope.msg = "提交失败，请确认填写信息！";
					$scope.alertConfirm = function(){
						$scope.submitInfo("0");
						$state.go("loginType");
					}
					// $scope.msg =dataJson.msg.head.rst.errmsg;
				}
			},
			error: function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "提交失败，请重试";
				return;
				console.log("sendYwtbApplyInfo err");
			}
		});
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			bounce: true,
			click: true,
			taps: true,
			hScroll: false,
			checkDOMChanges: true,
		});
	};
	$scope.isScroll();
});
app.controller("submit", function($scope, $state, appData, $sce, appFactory) {
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