function removeAnimate(ele) {
	$(ele).css({
		"transform": "translateY(0px)",
		"top": 0
	}).removeClass('transformto')
}

function addAnimate(ele) {
	$(ele).addClass('transformto');
}
app.controller('guideline', function($state, $scope, appData) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	appData.funName = $scope.funName = "崇明绿化树种推荐";
	appData.itemCode = "3120Q5034000";
	$scope.nextStep = function() {
		$state.go("loginType");
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
});
app.controller('loginType', function($state, $scope, appData,$http) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	appData.funName = $scope.funName = "崇明绿化树种推荐";
	var curWwwPath = window.document.location.hostname;
	var port = window.location.port;
	var protocol = window.location.protocol;
	appData.urlHost = protocol + "//" + curWwwPath + ":"+port+"/ac-product";
	appData.itemCode = "3120Q5034000";
	$scope.operation = "请选择登录方式";
	appData.xsbb = false;
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("guideline");
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory) {
	$scope.funName = appData.funName;
	$scope.isAlert = false;
	$scope.msg = "";
	$scope.nextLink = ""; // 下一步标识符
	$scope.loginType = appData.loginType;
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
			$state.go("info");
		}
	}
	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
			appData.VALIDENDDAY = info.ValidtermOfEnd;
			appData.VALIDSTARTDAY = info.ValidtermOfStart;
			$scope.loginType = 'recognition';
		} else {
			layer.msg("很抱歉，未获取到相关信息，请重试。");
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		$state.go("info");
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
		function ClearBr(key) {
			key = key.replace(/\+/g, "-");
			key = key.replace(/\#/g, ",");
			return key;
		}
		if(appData.qrCodeType == "suishenma") {
			appData.licenseName = info.zwdtsw_name;
			appData.licenseNumber = info.zwdtsw_cert_id;
			appData.zwdtsw_user_id = info.zwdtsw_user_id;
			appData.encrypt_identity = ClearBr(info.encrypt_identity);
			appData.zwdtsw_link_phone = info.zwdtsw_link_phone;
			$state.go("info");
		} else {
			var idcardInfo = info.result.data;
			appData.licenseName = idcardInfo.realname;
			appData.licenseNumber = idcardInfo.idcard;
			appData.VALIDENDDAY = idcardInfo.VALIDENDDAY;
			appData.VALIDSTARTDAY = idcardInfo.VALIDSTARTDAY;
			$state.go("info");
		}
	}
});
app.controller('info', function($state, $scope, appData, appFactory, $http) {
	$scope.operation = "请填写基本信息";
	var name = appData.itemName;
	$scope.itemName = name;
	$scope.isLoding = true;
	$scope.concel = 'false';
	$scope.documentTypeList = documentType;
	$scope.applyObjList = applyObj
	$scope.nextText = "提交";
	$scope.applyName = appData.licenseName;
	$scope.applyCardNo = appData.licenseNumber;
	$scope.mobile = appData.zwdtsw_link_phone;

	$scope.change = function(index, item, type) {
		switch(type) {
			case "1":
				$scope.current1 = index;
				$scope.applyObj = item.name;
				break;
			case "2":
				$scope.current2 = index;
				$scope.documentType = item.name;
				break;
		}
	}
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.itemName = appData.itemName;
	$scope.prevStep = function() {
		$location.path("/select");
	}
	// 保存数据
	$scope.flag = true;
	$scope.nextStep = function() {
		if(!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			if(isBlank($scope.applyObj)) {
				$scope.isAlert = true;
				$scope.msg = "请选择申请对象！";
				return;
			}
			if($('#applyName').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入申请人姓名！";
				return;
			}
			if($scope.current1 == 0) {
				if(isBlank($scope.documentType)) {
					$scope.isAlert = true;
					$scope.msg = "请选择申请人证件类型！";
					return;
				}
				if($('#applyCardNo').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请输入申请人证件号码！";
					return;
				}
			} else if($scope.current1 == 1) {
				if($('#targetNo').val().length < 17) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的社会统一信用代码！";
					return;
				}
				if($('#targetName').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请输入单位名称！";
					return;
				}
			}
			if(!isPhoneAvailable($('#mobile').val())) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的手机号！";
				return;
			}
		} while (condFlag);
		var fConfig = {
			'targetName':$('#targetName').val(),
			'targetNo':$('#targetNo').val(),
			'licenseNo':appData.licenseNumber||$('#applyCardNo').val(),
			'username':$scope.applyName,
			'mobile':$scope.mobile,
			'targetTypeName': $scope.applyObj,
			'applyNo': '',
			'itemCode': '11310230MB2F31269133120Q503400002', // data.itemTenNo	'0101220000-00-00-2'
			'itemName': '崇明绿化树种推荐',
			'userId': '',
			'source': '网上申请',
			'departCode':'SH00CM',
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(appData.urlHost + '/aci/declare/saveApply.do?', {
			params: fConfig
		}).success(function(dataJson) {
			$scope.flag = true;
			$scope.isLoding = false;
			appData.applyNo = dataJson.applyNo;
			$state.go("submit");
		}).error(function(e) {
			console.log(e)
		});
		$scope.flag = false;
	};
});
app.controller('submit', function($state, $scope, appData,$http) {
	$scope.funName = appData.funName;
	$scope.applyNo = appData.applyNo;
	$scope.nextText = "返回首页";
	$scope.submitApply = function() {
		$scope.finishData = {
			applyNo: appData.applyNo, // '751122018600008'
			subItemCodes: '', // data.itemId
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(appData.urlHost + '/aci/declare/submitApply.do', {
			params: $scope.finishData
		}).success(function(dataJson) {
			console.log(dataJson);
			$scope.print();
		}).error(function(e) {
			console.log(e)
		});
	}  
	$scope.submitApply();
	//	//模块使用记录
	//	$scope.jsonStr = {
	//		SUCCESS: "true",
	//		data: {
	//			name: $scope.funName,
	//			Number: $scope.applyNo,
	//		}
	//	}
	//	recordUsingHistory('人社服务', '办理', $scope.funName, appData.licenseName, appData.licenseNumber, '', $scope.applyNo, JSON.stringify($scope.jsonStr));
	//	//行为分析(办理)
	//	trackEventForAffairs($scope.applyNo, $scope.funName, "上海市人力资源社会保障局", appData.licenseName, appData.licenseNumber, '');
	$scope.goHome = function() {
		$.device.GoHome();
	}
});