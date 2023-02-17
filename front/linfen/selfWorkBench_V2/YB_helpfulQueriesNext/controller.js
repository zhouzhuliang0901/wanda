app.controller('loginType', function($state, $scope, appData, $location, appFactory) {
	$scope.operation = "请选择登录方式";
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("");
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
					console.log("token1:"+appData.token)
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

	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
			// appData.licenseNumber = '310228198808070818';
			// appData.licenseName = '陈雷';
			// $scope.getTokenSNO(recognition_base64_photo, recognition_base64_photo);
		} else {
			layer.msg("没有获取到")
		}
	}
	// $scope.idcardLogin();
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
app.controller("info", function($scope, $state, appData, $sce,$timeout) {
	$scope.isLoading = false;
	appData.funName = "帮困登记缴费进度查询";
	$scope.funName = appData.funName;
	$scope.idCard = appData.licenseNumber;
	$scope.concel = 'false';
	$scope.nextText = "查询"
	$(".form_datetime").datetimepicker({
		startView: 'decade',
		minView: 'decade',
		format: "yyyy", //显示日期格式
		maxViewMode: 2,
		minViewMode:2,
		autoclose: true,
		// todayBtn: true,
		language: 'zh-CN',
		pickerPosition: 'bottom-left',
	});
	$scope.alertConfirm = function(){
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		$state.go('loginType');
	}
	$scope.nextStep = function() {
		//提交参数集合
		$scope.isLoading = true;
		$scope.applicationInfo = {
			"jfnd": document.getElementById('year').value,
			"sfzh":$scope.idCard
	}
	console.log($scope.applicationInfo)
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + '/selfapi/medicalInsurance/YBCOMQ70',
			// url: "http://localhost:8080/ac-self/selfapi/medicalInsurance/YBCOMQ70",
			dataType: "json",
			data: {
				json: JSON.stringify($scope.applicationInfo)
			},
			success: function(dataJson) {
				console.log(dataJson);
				if (dataJson.msg.body !=null && dataJson.msg.body != "") {
						appData.zffs = dataJson.msg.body.zffs
						appData.jfzt = dataJson.msg.body.jfzt
						$state.go("submit")
					} else {
						$scope.isLoading = false;
						$scope.isAlert = true;
						$scope.msg = "未查到您的信息！";
					}
			}
		});
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});
app.controller("submit", function($scope, $state, appData, $sce, appFactory) {
	$scope.funName = appData.funName;
	$scope.zffs = appData.zffs
	$scope.jfzt = appData.jfzt
	$scope.nextText = "返回首页";
//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: $scope.funName,
			Number: '',
		}
	}
	recordUsingHistory('医保服务', '办理', $scope.funName, appData.licenseName, appData.licenseNumber, '', '', JSON.stringify($scope.jsonStr));
	trackEventForAffairs('','办理','上海市医疗保障局',appData.licenseName,appData.licenseNumber,'')
	$scope.goHome = function() {
		$.device.GoHome();
	}
});