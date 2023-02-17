app.controller('guideline', function($state, $scope, appData, $location) {
	$scope.funName = "上海市学生事务中心存档证明";
	appData.funName = $scope.funName;
	$scope.guideline = "1、该服务由.上海市学生事务中心提供，您的查询结果根据现有实际档案情况更新，查询结果仅供参考，以实际档案情况为准;</p>" +
		"<p>2、若您查询的档案身份信息不全或有误，暂无法通过网上查询，请您致电上海市学生事务中心64829191或来中心档案室窗口查询办理;</p>" +
		"<p>3、您的档案保存情况展示后，请仔细核对信息，如有疑问可致电上海市学生事务中心64829191或来中心档案室窗口查询办理;</p>" +
		"<p>4、目前档案保存情况可打印，打印后的查询结果附带公章，与上海市学生事务中心出具的存档证明具有同等作用。</p>" +
		"<p>5、学校档案寄达中心后，一般需要10个工作日整理录入系统，高峰时期将适当延长时间，请您安排好时间查询办理档案业务。</p>"
	$scope.nextStep = function(type) {
		$state.go("loginType");
	}
	$scope.prevStep = function() {
		//window.location.href = "../index.html";
		$.device.GoHome();
	}
});
app.controller('loginType', function($state, $scope, appData, $location) {
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
	$scope.funName = appData.funName;
	$scope.isAlert = false;
	$scope.msg = "";
	$scope.nextLink = ""; // 下一步标识符
	$scope.loginType = appData.loginType;
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
					appData.tokenSNO = res.tokenSNO;
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
			appData.encrypt_identity = ClearBr(info.encrypt_identity);
			$scope.getAccessToken(appData.tokenSNO);
		} else {
			var idcardInfo = info.result.data;
			appData.licenseName = idcardInfo.realname;
			appData.licenseNumber = idcardInfo.idcard;
			appData.VALIDENDDAY = idcardInfo.VALIDENDDAY;
			appData.VALIDSTARTDAY = idcardInfo.VALIDSTARTDAY;
			$scope.getTokenSNO(photo, photo);
			try {
				$scope.$apply();
			} catch(e) {}
		}
	}
});

app.controller('info', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.funName = appData.funName;
	$scope.licenseNumber = appData.encrypt_identity || appData.licenseNumber;
	$scope.nextText = "提交"
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$state.go("loginType");
	}
	//个人信息
	$scope.stName = appData.licenseName;
	$scope.stIdCard = appData.licenseNumber;
	$scope.stSex = ((parseInt(appData.licenseNumber.substring(16, 17)) % 2) == 0) ? "女" : "男";
	$scope.dtBirth = (appData.licenseNumber).substring(6, 10) + "-" + (appData.licenseNumber).substring(10, 12) + "-" + (appData.licenseNumber).substring(12, 14);
	$scope.stMobile = appData.stMobile || "";
	$scope.nextStep = function() {
		appData.stSex = $scope.stSex;
		appData.dtBirth = $scope.dtBirth;
		appData.stMobile = $scope.stMobile;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/educationCommission/queryProofFiling.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				sfzh: appData.licenseNumber
			},
			success: function(dataJson) {
				console.log(dataJson);
				if(dataJson.clzt == "success") {
					$state.go("submit");
				} else if(dataJson.clzt == "error") {
					$scope.isAlert = true;
					$scope.msg = dataJson.clxx;
					$scope.alertConfirm = function() {
						$state.go("loginType");
					}
				}
			},
			error: function(err) {
				$scope.isAlert = true;
				$scope.msg = "未查询到档案信息";
				$scope.alertConfirm = function() {
					$state.go("loginType");
				}
			}
		});
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}
});
app.controller("submit", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName;
	$scope.configUrl = $.getConfigMsg.preUrl;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.nextText = "打印";
	$scope.isLoading = true;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.eLicense = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/dzzz/queryCertBaseData.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				certNo: appData.encrypt_identity || appData.licenseNumber, //"340881199303145313" || 
				type: $scope.licenseType, //"0" ||
				catMainCode: "312003001000500",
				machineId: $.config.get('uniqueId') || "test",
				itemName: "",
				itemCode: "",
				businessCode: "",
				name: appData.licenseName,
				startDay: appData.VALIDSTARTDAY,
				endDay: appData.VALIDENDDAY,
			},
			success: function(dataJson) {
				if(dataJson) {
					$scope.previewImg = $scope.configUrl + dataJson[0].pictureUrlForBytes;
				} else {
					$scope.isAlert = true;
					$scope.msg = "未查询到档案信息";
					$scope.alertConfirm = function() {
						$state.go("main");
					}
				}
			},
			error: function(err) {
				$scope.isAlert = true;
				$scope.msg = "未查询到档案信息";
				$scope.alertConfirm = function() {
					$state.go("main");
				}
			}
		});
	}
	$scope.eLicense();
	//图片预览
	$scope.isAllScreen = false;
	$scope.isPrint = false;
	$scope.view = true;
	//图片显示
	var viewer = new Viewer(document.getElementById('jq22'), {
		url: 'data-original',
		toolbar: false,
		//		button: false
	});
	$scope.show = function() {
		viewer.show();
		$scope.view = false;
	}
	$scope.hide = function() {
		viewer.hide();
		$scope.view = true;
	}
	$scope.close = function() {
		$scope.isAllScreen = false;
	};
	//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: $scope.funName,
			Number: $scope.applyNo,
		}
	}
	recordUsingHistory('教委服务', '办理', $scope.funName, appData.licenseName, appData.licenseNumber, appData.stMobile, $scope.applyNo, JSON.stringify($scope.jsonStr));
	//行为分析(办理)
	trackEventForAffairs($scope.applyNo, $scope.funName, "上海市教育委员会", appData.licenseName, appData.licenseNumber, appData.stMobile);
	$scope.prevStep = function() {
		$state.go("info");
	}
	$scope.nextStep = function() {

	}
});