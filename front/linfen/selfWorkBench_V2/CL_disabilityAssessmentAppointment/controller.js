app.controller('index', function($state, $scope, appData, $location) {
	//appData.appointmentType预约类型 
	$scope.operation = "请选择预约类型";
	$scope.choiceLogin = function(type) {
		//cx查询 bg变更
		appData.AppointmentType = type;
		$state.go("indexhandle");
	}
});
app.controller('indexhandle', function($state, $scope, appData, $location) {
	$scope.operation = "请选择办理类型";
	$scope.choiceLogin = function(type) {
		appData.HandlingType = type
		//self本人登录 agent代办登录
		console.log(appData.HandlingType);
		$state.go("loginType");
	}
	$scope.prevStep = function() {
		$state.go("index");
	}

});
app.controller('loginType', function($state, $scope, appData, $location) {
	$scope.operation = "请选择登录方式";
	if (appData.HandlingType == 'agent') {
		$scope.operation = "请代办人登录方式";
	}
	$scope.funName = "残疾评定预约日期查询、变更";
	appData.funName = $scope.funName;
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("index");
	}
});
app.controller('login', function($scope, $http, $state, appData) {
	$scope.funName = appData.funName;
	$scope.isAlert = false;
	$scope.msg = "";
	$scope.loginType = appData.loginType;
	switch ($scope.loginType) {
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
	// 代办流程 信息
	appData.applicantloginData = {};
	//跳转页面
	$scope.nextStep = function() {
		$scope.tokenType = "token";
		$scope.token = function() {
			//代办页面跳转
			if (appData.HandlingType == 'agent') {
				$state.go("agentloginType");
			} else {
				// $state.go("info");
				$scope.checkForHandlingConditons();
			}
		}
	}
	//个人信息
	$scope.alertText = function(TextW, toing) {
		$scope.isAlert = true;
		$scope.msg = TextW;
		$scope.alertConfirm = function() {
			if (toing) $state.go(toing);
			$scope.isAlert = false;
		}
	}
	$scope.checkForHandlingConditons = function() {
		//申请人办理条件校验
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf +
				"/selfapi/applicationForDisabilit/checkForHandlingConditons.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				"idCard": appData.licenseNumber, //'310106193412071615'
			},
			success: function(dataJson) {
				console.log(dataJson);
				if (dataJson) {
					if (dataJson.code = '200') {
						if (dataJson.data[0]) {
							if (dataJson.data[0].isInAssessing != '是') {
								$scope.alertText("本人不存在办件", 'index');
							} else {
								if (appData.AppointmentType == 'cx') {
									$state.go("appointmentQuery");
								} else {
									$state.go("info");
								}
							}
						} else {
							$scope.alertText("接口异常data值出错!", 'index');
						}
					} else {
						$scope.alertText("接口异常状态值出错!", 'index');
					}
				} else {
					$scope.alertText("接口异常返回值出错!", 'index');
				}
			},
			error: function(err) {
				$scope.alertText("接口异常" + JSON.stringify(err), 'index');
			}
		});
	}
	$scope.idcardLogin = function(info, images) {
		if (info) {
			$scope.faceImage = images;
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
			appData.VALIDENDDAY = info.ValidtermOfEnd;
			appData.VALIDSTARTDAY = info.ValidtermOfStart;
			// applicantloginData
			appData.applicantloginData.licenseNumber = appData.licenseNumber;
			appData.applicantloginData.licenseName = appData.licenseName;
			appData.applicantloginData.VALIDENDDAY = appData.VALIDENDDAY;
			appData.applicantloginData.VALIDSTARTDAY = appData.VALIDSTARTDAY;
			$scope.loginType = 'recognition';
		} else {
			layer.msg("很抱歉，未获取到相关信息，请重试。");
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		// $state.go("info");
		if (appData.HandlingType == 'agent') {
			$state.go("agentloginType");
		} else {
			$scope.checkForHandlingConditons();
		}
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
		if (appData.qrCodeType == "suishenma") {
			appData.licenseName = info.zwdtsw_name;
			appData.licenseNumber = info.zwdtsw_cert_id;
			// applicantloginData
			appData.applicantloginData.licenseNumber = appData.licenseNumber;
			appData.applicantloginData.licenseName = appData.licenseName;
			appData.encrypt_identity = ClearBr(info.encrypt_identity);
			$scope.nextStep();
		} else {
			var idcardInfo = info.result.data;
			appData.licenseName = idcardInfo.realname;
			appData.licenseNumber = idcardInfo.idcard;
			appData.VALIDENDDAY = idcardInfo.VALIDENDDAY;
			appData.VALIDSTARTDAY = idcardInfo.VALIDSTARTDAY;
			// applicantloginData
			appData.applicantloginData.licenseNumber = appData.licenseNumber;
			appData.applicantloginData.licenseName = appData.licenseName;
			appData.applicantloginData.VALIDENDDAY = appData.VALIDENDDAY;
			appData.applicantloginData.VALIDSTARTDAY = appData.VALIDSTARTDAY;
			$scope.nextStep();
		}
	}
});
app.controller('agentloginType', function($state, $scope, appData, $location) {
	$scope.operation = "请刷被代办人身份证";

	$scope.funName = "残疾评定预约日期查询、变更";
	appData.funName = $scope.funName;
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("agentlogin");
	}
	$scope.prevStep = function() {
		$state.go("index");
	}
});
app.controller('agentlogin', function($scope, $http, $state, appData) {
	$scope.funName = appData.funName;
	$scope.isAlert = false;
	$scope.msg = "";
	$scope.loginType = appData.loginType;
	switch ($scope.loginType) {
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
			//代办页面跳转
			$state.go("info");
		}
	}
	//个人信息
	$scope.alertText = function(TextW, toing) {
		$scope.isAlert = true;
		$scope.msg = TextW;
		$scope.alertConfirm = function() {
			if (toing) $state.go(toing);
			$scope.isAlert = false;
		}
	}
	$scope.queryForAssessionAgentInfo = function() {
		//在办件办理人代办人信息查询
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf +
				"/selfapi/applicationForDisabilit/queryForAssessionAgentInfo.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				"idCard": appData.applicantloginData.licenseNumber, //'310106193412071615'
			},
			success: function(dataJson) {
				console.log(dataJson);
				if (dataJson.code = '200') {
					if (dataJson.data.length == 0) {
						$scope.alertText(dataJson.msg, 'index');
					} else {
						if (dataJson.data[0].isApplyedByMe == '否') {
							if (dataJson.data[0].agentIdCard != appData.licenseNumber) {
								$scope.alertText("代办人信息不匹配", 'index');
							} else {
								if (appData.AppointmentType == 'cx') {
									$state.go("appointmentQuery");
								} else {
									$state.go("info");
								}
							}
						} else {
							if (appData.HandlingType == 'agent') {
								$scope.alertText("请让本人前来办理", 'index');
							}
						}
					}
				} else {
					$scope.alertText("接口异常状态值出错!", 'index');
				}
			},
			error: function(err) {
				$scope.alertText("接口异常" + JSON.stringify(err), 'index');
			}
		});
	}
	$scope.idcardLogin = function(info, images) {
		if (info) {
			$scope.faceImage = images;
			appData.applicantloginData.licenseNumber = info.Number;
			appData.applicantloginData.licenseName = info.Name;
			appData.applicantloginData.VALIDENDDAY = info.ValidtermOfEnd;
			appData.applicantloginData.VALIDSTARTDAY = info.ValidtermOfStart;
			// $scope.loginType = 'recognition';
			$scope.queryForAssessionAgentInfo();
		} else {
			layer.msg("很抱歉，未获取到相关信息，请重试。");
		}
	}
	// $scope.idcardLogin = function(info, images) {
	// 	if (info) {
	// 		$scope.faceImage = images;
	// 		appData.applicantloginData.licenseNumber = info.Number;
	// 		appData.applicantloginData.licenseName = info.Name;
	// 		appData.applicantloginData.VALIDENDDAY = info.ValidtermOfEnd;
	// 		appData.applicantloginData.VALIDSTARTDAY = info.ValidtermOfStart;
	// 		// $scope.loginType = 'recognition';
	// 		$scope.queryForAssessionAgentInfo();
	// 	} else {
	// 		layer.msg("很抱歉，未获取到相关信息，请重试。");
	// 	}
	// }
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
		if (appData.qrCodeType == "suishenma") {
			appData.licenseName = info.zwdtsw_name;
			appData.licenseNumber = info.zwdtsw_cert_id;
			appData.encrypt_identity = ClearBr(info.encrypt_identity);
			$scope.nextStep();
		} else {
			var idcardInfo = info.result.data;
			appData.licenseName = idcardInfo.realname;
			appData.licenseNumber = idcardInfo.idcard;
			appData.VALIDENDDAY = idcardInfo.VALIDENDDAY;
			appData.VALIDSTARTDAY = idcardInfo.VALIDSTARTDAY;
			$scope.nextStep();
		}
	}
});
app.controller('info', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.funName = appData.funName;
	$scope.concel = "false";
	$scope.isAlert = false;
	// $scope.alertConfirm = function() {
	// 	$scope.isAlert = false;
	// }
	// 列表信息
	appData.UploadFileList = {
		"data": {
			"itemName": "残疾评定预约日期查询、变更(无)-无",
			"flag": true,
			"subItems": [],
			"stuffs": [{
				"certs": [{
					"certName": "中华人民共和国居民身份证",
					"certCode": "310105109000100"
				}],
				"stDesc": "",
				"stuffName": "监护人居民身份证",
				"isMust": "1",
				"stuffCode": "MATERIAL21D027894",
				'fileS': []
			}, {
				"certs": [{
					"certName": "中华人民共和国居民身份证",
					"certCode": "310105109000100"
				}],
				"stDesc": "",
				"stuffName": "委托人居民身份证",
				"isMust": "1",
				"stuffCode": "MATERIAL21D027893",
				'fileS': []
			}, {
				"certs": [{
					"certName": "中华人民共和国居民身份证",
					"certCode": "310105109000100"
				}],
				"stDesc": "",
				"stuffName": "申请人居民身份证",
				"isMust": "1",
				"stuffCode": "MATERIAL21D027892",
				'fileS': []
			}],
			"isApplyItem": true,
			"itemCode": "310000999000CL0000531200066000001"
		}
	}
	if (appData.HandlingType == 'self') {
		appData.UploadFileList = {
			"data": {
				"itemName": "残疾评定预约日期查询、变更(无)-无",
				"flag": true,
				"subItems": [],
				"stuffs": [{
					"certs": [{
						"certName": "中华人民共和国居民身份证",
						"certCode": "310105109000100"
					}],
					"stDesc": "",
					"stuffName": "申请人居民身份证",
					"isMust": "1",
					"stuffCode": "MATERIAL21D027892",
					'fileS': []
				}],
				"isApplyItem": true,
				"itemCode": "310000999000CL0000531200066000001"
			}
		}
	}
	$scope.alertText = function(TextW, toing) {
		$scope.isAlert = true;
		$scope.msg = TextW;
		$scope.alertConfirm = function() {
			if (toing) $state.go(toing);
			$scope.isAlert = false;
		}
	}
	$scope.stName = '';
	$scope.stIdCard = '';
	$scope.sthjAddress = '';
	$scope.stTelephone = '';
	$scope.stcjCtegory = '';
	$scope.nationList = [];
	$scope.nation = '';
	$scope.stYuAddress = '';
	$scope.nationTimeList = [];
	appData.saveAppointmentData = {}; //储存接口参数信息
	// $scope.queryForUpdateApointmentInfoDataS = {};
	// 处理预约医院信息
	$scope.hosptialInfoDataS = function(lis) {
		var newArr = [];
		var arrId = [];
		for (var i = 0; i < lis.length; i++) {
			if (arrId.indexOf(lis[i]['scheduledHospital']) == -1) {
				arrId.push(lis[i]['scheduledHospital']);
				newArr.push(lis[i]);
			}
		}
		// console.log(arrId);
		// console.log(newArr);
		return arrId;
	}
	$scope.queryForUpdateApointmentInfo = function() {
		//4.	残疾评定预约信息变更
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf +
				"/selfapi/applicationForDisabilit/queryForUpdateApointmentInfo.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				"idCard": appData.applicantloginData.licenseNumber, //'310106193412071615'
			},
			success: function(dataJson) {
				console.log(dataJson);
				if (dataJson.code = '200') {
					if (dataJson.data[0].isModifiable == '是') {
						$scope.data1 = dataJson.data[0];
						// $scope.queryForUpdateApointmentInfoDataS = $scope.data1;
						$scope.stName = $scope.data1.applicantName;
						$scope.stIdCard = $scope.data1.applicantIdCard;
						$scope.stTelephone = $scope.data1.applicationPhone;
						$scope.stcjCtegory = $scope.data1.applyDisabilityCategory;
						$scope.nationList = $scope.hosptialInfoDataS($scope.data1.hosptialName);
						// $scope.nationTimeList = $scope.data1.hosptialName;
						appData.saveAppointmentData.applyNo = $scope.data1.apply_no;
						appData.applyNo = $scope.data1.apply_no;
						appData.saveAppointmentData.idCard = $scope.data1.applicantIdCard;
						appData.saveAppointmentData.cjlb = $scope.data1.cjlb_id;
					} else {
						$scope.alertText("暂时不支持变更", 'index');
					}
				} else {
					$scope.alertText("接口异常状态值出错!", 'index');
				}
			},
			error: function(err) {
				$scope.alertText("接口异常" + JSON.stringify(err), 'index');
			}
		});
	}
	//监听
	$scope.nationTimeListClick = function(e) {
		$scope.stYuAddress = e.hospitalAddress;
		appData.saveAppointmentData.planId = e.plan_id;
		console.log($scope.stYuAddress)
	}
	$scope.nationListClick = function(e) {
		console.log(e, $scope.data1.hosptialName);
		var arrId = [];
		// $scope.nationTimeList = $scope.data1.hosptialName;
		for (var i = 0; i < $scope.data1.hosptialName.length; i++) {
			if ($scope.data1.hosptialName[i].scheduledHospital == e) {
				arrId.push($scope.data1.hosptialName[i]);
			}
		}
		$scope.nationTimeList = arrId;
	}
	$scope.queryForUpdateApointmentInfo();
	// 5.	评定预约信息更改保存
	$scope.saveAppointmentS = function() {
		if (!appData.saveAppointmentData.planId) {
			$scope.alertText("请选择预约医院和预约时间");
		}
		console.log(appData.saveAppointmentData)
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf +
				"/selfapi/applicationForDisabilit/saveAppointment.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: appData.saveAppointmentData,
			success: function(dataJson) {
				console.log(dataJson);
				if (dataJson.code == '200') {
					if (dataJson.msg == '预约更改成功') {
						$scope.saveApplyInfoS();
					} else {
						$scope.alertText(dataJson.msg);
					}
				}
			},
			error: function(err) {
				$scope.alertText("接口异常" + JSON.stringify(err), 'index');
			}
		});
	}
	//保存办件信息 保存办件库
	$scope.saveApplyInfoS = function() {
		$scope.saveApplyInfoData = {
			"applyNo": appData.applyNo,
			"itemCode": "312000660000",
			"taskHandleItem": "310000999000CL0000531200066000001",
			"itemName": "残疾评定预约日期查询、变更",
			"targetType": "个人",
			"targetName": appData.licenseNumber,
			"targetNo": appData.licenseName,
			"userId": "3872329923373322333",
			"username": appData.applicantloginData.licenseNumber,
			"licenseType": "身份证",
			"licenseNo": appData.applicantloginData.licenseNumber,
			"mobile": $scope.stTelephone,
			"departCode": "SHGASH",
			"departName": "自助机办理",
			"source": "自助机网上申请",
			"content": "变更残疾评定预约相关信息",
			"opTime": "",
			"districtCode": "SH00SH",
			"info": {}
		};
		console.log($scope.saveApplyInfoData);
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf +
				"/selfapi/deal/work/saveApplyInfo.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: $scope.saveApplyInfoData,
			success: function(dataJson) {
				console.log(dataJson);
				if (dataJson.isSuccess) {
					appData.saveApplyInfoApplyNo = dataJson.data.applyNo;
					$state.go("materialList");
				}
			},
			error: function(err) {
				$scope.alertText("接口异常" + JSON.stringify(err), 'index');
			}
		});
	}
	$scope.nextStep = function() {
		$scope.saveAppointmentS();
	}
	$scope.prevStep = function() {
		$state.go("index");
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
app.controller("takePhoto", function($scope, $state, appData, $sce, $rootScope, $timeout) {
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();
	$scope.funName = appData.funName;
	$scope.isAlert = false;
	$scope.concel = "false";
	// $scope.alertConfirm = function() {
	// 	$.device.cmCaptureHide(); // 关闭高拍仪
	// 	$state.go('materialList');
	// }
	$scope.alertText = function(TextW) {
		$scope.isAlert = true;
		$scope.msg = TextW;
		$scope.alertConfirm = function() {
			$state.go('materialList');
			$.device.cmCaptureHide();
			$scope.isAlert = false;
		}
	}
	$scope.finish = [];
	$scope.clzfInfo = '正面';
	$scope.prevText = "返回";
	$scope.nextText = "确认上传";
	$scope.isLoading = true;
	if (window.innerWidth <= 1600) {
		$.device.cmCaptureShow(430, 420, 380, 150);
	} else {
		$.device.cmCaptureShow(680, 530, 190, 300);
	}
	$.device.cmCaptureSelectRect(0, 0, 1920, 1920);
	$scope.isFinish = false;
	// 拍照
	var imgHTML = "";
	var imgIndex = 0;
	$scope.uploadApplyStuffs = function(datas) {
		$.ajax({
			url: $.getConfigMsg.preUrlSelf + '/selfapi/deal/work/uploadApplyStuffs.do',
			type: "post",
			dataType: "json",
			data: datas,
			success: function(result) {
				if (result.code == '200') {
					$scope.isLoading = true;
					appData.uploadStuffId = result.data.stuffId; //dataJson.appData.stuffId  ;
					appData.imgStr = 'data:image/png;base64,' + datas.file;
					$scope.finish.push({
						index: 0,
						stuffName: '身份证正面',
						img: appData.imgStr,
						status: 0,
						method: "高拍仪",
						uploadStuffId: result.data.stuffId
					});
					imgHTML += '<div class="img" id="' + result.data.stuffId +
						'"><img src="' + appData.imgStr +
						'" width="150" height="200" /></div>';
					$('.imgBox').html(imgHTML);
					$scope.isFinish = true;
					if (result.code == '200') {
						$scope.clzfInfo = '反面';
					}
				} else {
					$scope.alertText("上传失败：" + JSON.stringify(result));
				}

			},
			error: function(err) {
				$scope.isLoading = true;
				layer.msg("上传材料失败");
				$.device.cmCaptureHide(); // 关闭高拍仪
				$state.go("materialList");
			}
		});
	}

	$scope.uploadApplySupplementStuffs = function(datas) {
		$.ajax({
			// url: $.getConfigMsg.preUrlSelf + '/selfapi/deal/work/uploadApplySupplementStuffs.do',
			url: $.getConfigMsg.preUrlSelf + '/selfapi/deal/work/uploadApplyStuffs.do',
			type: "post",
			dataType: "json",
			data: datas,
			success: function(result) {
				if (result.code == '200') {
					$scope.isLoading = true;
					// appData.uploadStuffId = result.data.stuffId; //dataJson.appData.stuffId  ;
					appData.imgStr = 'data:image/png;base64,' + datas.file;
					$scope.finish.push({
						index: 1,
						stuffName: '身份证反面',
						img: appData.imgStr,
						status: 0,
						method: "高拍仪",
						uploadStuffId: result.data.stuffId
					});
					imgHTML += '<div class="img" id="' + result.data.stuffId +
						'"><img src="' + appData.imgStr +
						'" width="150" height="200" /></div>';
					$('.imgBox').html(imgHTML);
					$scope.isFinish = true;
					if (result.code == '200') {
						$scope.clzfInfo = '正面';
					}
				} else {
					$scope.alertText("上传失败：" + result.msg || JSON.stringify(result));
				}
			},
			error: function(err) {
				$scope.isLoading = true;
				layer.msg("上传材料失败");
				$.device.cmCaptureHide(); // 关闭高拍仪
				$state.go("materialList");
			}
		});
	}
	$scope.next = function() {
		$scope.isLoading = false;
		var scanImg = "";
		var scanImg1 = "";

		$.device.cmCaptureCaptureUrl(function(path) {
			scanImg = path;
		});
		$.device.cmCaptureCaptureBase64(function(val) {
			scanImg1 = val;
			if (scanImg1 == '') {
				$scope.isAlert = true;
				$scope.msg = "请聚焦并对准材料后再拍照";
				$scope.alertConfirm = function() {
					$scope.isAlert = false;
				}
			} else {
				var datas1 = {
					applyNo: appData.saveApplyInfoApplyNo,
					stuffCode: "stuff011",
					stuffId: "",
					stuffName: '',
					stuffType: '0',
					stuffStatus: '0',
					stuffSource: '',
					"file": scanImg1
				}
				var datas2 = {
					applyNo: appData.saveApplyInfoApplyNo,
					stuffCode: "stuff011",
					stuffSource: '',
					"file": scanImg1,
					stuffType: '0',
					stuffName: '',
					stuffId: appData.uploadStuffId,
					stuffStatus: '2',
				}
				console.log(datas1);
				if ($scope.clzfInfo == '正面') {
					$scope.uploadApplyStuffs(datas1);
				} else if ($scope.clzfInfo == '反面') {
					$scope.uploadApplySupplementStuffs(datas2);
				}
			}
		});
	};
	// 完成拍照
	$scope.finishUpload = function() {
		$(".next").attr("disabled", true);
		appData.UploadFileList.data.stuffs[appData.toUploadMaterialDataIndex].fileS = $scope.finish;
		appData.UploadFileList.data.stuffs[appData.toUploadMaterialDataIndex].upload3 = true;
		console.log(appData.UploadFileList);
		$scope.last(); // 关闭高拍仪
	};

	$scope.last = function() {
		$.device.cmCaptureHide(); // 关闭高拍仪
		$state.go("materialList");
	}
});
app.controller("materialList", function($scope, $http, $rootScope, appData, $state, appFactory) {
	$scope.nextText = "提交";
	$scope.funName = appData.funName;
	appData.toUploadMaterialData = [];
	//必传材料列表
	$scope.mustUpload = [];
	appData.isUpload = [];
	$scope.current = 0;
	$scope.MaterialList = appData.UploadFileList.data.stuffs;

	console.log($scope.MaterialList);
	// appData.applicantloginData = {};
	// appData.applicantloginData.licenseNumber = '310106193412071615';
	// appData.applicantloginData.licenseName = '沈嘉铭';
	// appData.applicantloginData.VALIDENDDAY = '2021-02-02';
	// appData.applicantloginData.VALIDSTARTDAY = '2041-02-02';
	// appData.applyNo = '2c9a25c778883e5f01797e29faa3300e';

	$scope.dzzzkUpload_file = function(licenseNumber, licenseName, VALIDENDDAY, VALIDSTARTDAY, isUploadIndex) {
		//上传材料
		appFactory.upload_file(licenseNumber, licenseName, VALIDENDDAY, VALIDSTARTDAY, '310105109000100',
			appData.applyNo,
			function(dataJson) {
				if (dataJson.data == "" || dataJson.data == null || dataJson.data == undefined) {
					layer.msg("未能从电子证照获取到身份证照上传");
				}
				appData.imgStr = $scope.imgUrl = "data:image/jpeg;base64," + dataJson.data[0].str;
			},
			function(dataJson1) {
				if (dataJson1.isSuccess == true) {
					try {
						if (appData.isUpload.length <= 0) {
							appData.isUpload.push({
								index: isUploadIndex,
								stuffName: "居民身份证",
								img: $scope.imgUrl,
								status: 1,
								method: "高拍仪"
							});
						}
					} catch (e) {}
				} else {
					layer.msg("未能从电子证照获取到身份证照上传");
				}
			});
	}
	$scope.dzzzkUpload_file(appData.applicantloginData.licenseNumber, appData.applicantloginData.licenseName,
		appData.applicantloginData.VALIDENDDAY, appData.applicantloginData.VALIDSTARTDAY, 2);
	if (appData.HandlingType == 'agent') {
		$scope.dzzzkUpload_file(appData.licenseNumber, appData.licenseName, appData.VALIDENDDAY, appData
			.VALIDSTARTDAY, 1);
	}
	console.log(appData.isUpload);
	// 材料上传
	$scope.toUploadMaterial = function(e, index) {

		appData.toUploadMaterialData = e;
		appData.toUploadMaterialDataIndex = index;
		$state.go("takePhoto");
	}
	//查看
	$scope.view = function(index) {
		//		appData.currentIndex = 0;
		appData.currentIndex = 0;
		// appData.view = appData.isUpload;
		console.log($scope.MaterialList[index].fileS);
		appData.view = $scope.MaterialList[index].fileS;
		$state.go("materialView");
	}

	$scope.prevStep = function() {
		$state.go("info");
	}
	//提交办件
	$scope.submit = function() {
		$state.go("submit");
	};
})
//材料显示
app.controller("materialView", function($scope, $state, $http, appData, appFactory) {
	$scope.stuffList = []; //所有图片的容器
	$scope.showImgList = []; //显示图片的容器
	$scope.currentPage = 1; //当前页
	$scope.totalPages = 1; //总页数
	$scope.funName = appData.funName;
	//当页显示图片
	$scope.currentList = function(current) {
		if (current === undefined) {
			current = $scope.currentPage;
		}
		$("#jq22 img").remove();
		$scope.currentImgIndex = null;
		$scope.startPos = (current - 1) * 3; //0 -3  3 - 6
		$scope.endPos = $scope.startPos + 3;
		console.log($scope.stuffList);
		$scope.showImgList = $scope.stuffList.slice($scope.startPos, $scope.endPos);
		console.log($scope.showImgList);
		$scope.totalPages = Math.ceil($scope.stuffList.length / 3);
		for (var i in $scope.showImgList) {
			$("#jq22").append('<img data-original="' + $scope.showImgList[i].img + '" src="' + $scope
				.showImgList[i].img + '" alt="">');
		}
		//图片显示
		var viewer = new Viewer(document.getElementById('jq22'), {
			url: 'data-original',
			//						toolbar:false,
			//						button:false
		});
	}

	$scope.$watch('$viewContentLoaded', function(newValue, oldValue) {
		console.log(appData.view);
		for (var i = 0; i < appData.view.length; i++) {
			$scope.stuffList.push(appData.view[i]);
			$scope.currentList();
			// if(appData.currentIndex == appData.view[i].index) {
			// 	$scope.stuffList.push(appData.view[i]);
			// 	$scope.currentList();
			// }
		}
		console.log($scope.stuffList);
		if ($scope.stuffList[0].method === "高拍仪") {
			$scope.scanShow = true;
			$scope.upanShow = false;
		} else if ($scope.stuffList[0].method === "U盘上传") {
			$scope.scanShow = false;
			$scope.upanShow = true;
		} else if ($scope.stuffList[0].method === "个人档案") {
			$scope.scanShow = true;
			$scope.upanShow = false;
		}
	});
	//下一页
	$scope.nextPage = function() {
		if ($scope.currentPage < $scope.totalPages) {
			++$scope.currentPage;
			$scope.currentList();
		}
	};
	//上一页
	$scope.prevPage = function() {
		if ($scope.currentPage > 1) {
			--$scope.currentPage;
			$scope.currentList();
			console.log($scope.showImgList);
		}
	};

	$scope.prev = function() {
		$state.go("materialList");
	}

	//打开文件
	$scope.open = function() {
		try {
			$.device.officeOpen($scope.stuffList[0].fileName);
		} catch (e) {
			layer.msg("未找到此文件");
		}
	}
});
app.controller("submit", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName;
	$scope.applyNo = appData.applyNo;
	$scope.itemName = appData.funName;
	//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: $scope.funName,
			Number: $scope.applyNo,
		}
	}

	$scope.submitApplyInfo = function() {
		//3.	残疾评定预约信息查询
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf +
				"/selfapi/selfapi/deal/work/submitApplyInfo.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				"applyNo": appData.saveApplyInfoApplyNo,
				"itemCode": '310000999000CL0000531200066000001',
				"departCode": 'SHGASH',
			},
			success: function(dataJson) {
				console.log(dataJson);
			},
			error: function(err) {
				$scope.alertText("接口异常" + JSON.stringify(err), 'index');
			}
		});
	}
	if (appData.AppointmentType == 'bg') {
		$scope.submitApplyInfo();
	}
	recordUsingHistory('残联服务', '办理', $scope.funName, appData.licenseName, appData.licenseNumber, appData
		.stMobile, $scope.applyNo, JSON.stringify($scope.jsonStr));
	//行为分析(办理)
	trackEventForAffairs($scope.applyNo, $scope.funName, "上海市残疾人联合会", appData.licenseName, appData
		.licenseNumber, appData.stMobile);
	var date = new Date();
	var month = date.getMonth() + 1;
	$scope.date = date.getFullYear() + "年" + month + "月" + date.getDate() + "日" + date.getHours() + "时" + date
		.getMinutes() + "分";
	$scope.nextText = "查看";
	$scope.nextStep = function() {
		// $.device.GoHome();
		$state.go("appointmentQuery");
	}
});
//增加一天
function addDate(time) {
	//加一天
	var timestamp = Date.parse(new Date(time));
	timestamp = timestamp / 1000;
	timestamp -= 86400; //加一天
	// var newTime =new Date(timestamp * 1000).format('yyyy-MM-dd hh:mm:ss');
	var newTime = new Date(timestamp * 1000).format('yyyy-MM-dd');
	newTime = newTime.replace('-', '年').replace('-', '月');
	return newTime + '日';
}
//日期格式
Date.prototype.format = function(format) {
	var date = {
		"M+": this.getMonth() + 1,
		"d+": this.getDate(),
		"h+": this.getHours(),
		"m+": this.getMinutes(),
		"s+": this.getSeconds(),
		"q+": Math.floor((this.getMonth() + 3) / 3),
		"S+": this.getMilliseconds()
	};
	if (/(y+)/i.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
	}
	for (var k in date) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ?
				date[k] : ("00" + date[k]).substr(("" + date[k]).length));
		}
	}
	return format;
}
//获取出生日期  性别   年龄
function IdCard(UUserCard, num) {
	if (num == 1) {
		//获取出生日期
		birth = UUserCard.substring(6, 10) + "-" + UUserCard.substring(10, 12) + "-" + UUserCard.substring(12, 14);
		return birth;
	}
	if (num == 2) {
		//获取性别
		if (parseInt(UUserCard.substr(16, 1)) % 2 == 1) {
			//男
			return "男";
		} else {
			//女
			return "女";
		}
	}
	if (num == 3) {
		//获取年龄
		var myDate = new Date();
		var month = myDate.getMonth() + 1;
		var day = myDate.getDate();
		var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1;
		if (UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12,
				14) <= day) {
			age++;
		}
		return age;
	}
}
app.controller("appointmentQuery", function($scope, $state, appData, $sce) {
	// appData.applicantloginData={}
	// appData.applicantloginData.licenseNumber="310109198312291532"
	// 预约查询
	$scope.ZLqueryForAssessionApointmentInfo = {};
	$scope.alertText = function(TextW, toing) {
		$scope.isAlert = true;
		$scope.msg = TextW;
		$scope.alertConfirm = function() {
			if (toing) $state.go(toing);
			$scope.isAlert = false;
		}
	}
	$scope.xingbie = IdCard(appData.applicantloginData.licenseNumber, 2);
	$scope.chushengriqi = IdCard(appData.applicantloginData.licenseNumber, 1);

	$scope.queryForAssessionApointmentInfo = function() {
		//3.	残疾评定预约信息查询
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf +
				"/selfapi/applicationForDisabilit/queryForAssessionApointmentInfo.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				"idCard": appData.applicantloginData.licenseNumber, //'310106193412071615'
			},
			success: function(dataJson) {

				$scope.ZLqueryForAssessionApointmentInfo = dataJson.data[0];
				$scope.ZLqueryForAssessionApointmentInfo.appointmentTimeSYTtest = addDate($scope
					.ZLqueryForAssessionApointmentInfo.appointmentTime)
				console.log($scope.ZLqueryForAssessionApointmentInfo);
			},
			error: function(err) {
				$scope.alertText("接口异常" + JSON.stringify(err), 'index');
			}
		});
	}
	$scope.queryForAssessionApointmentInfo();
	$scope.nextText = "打印";
	$scope.nextStep = function() {
		$scope.isShowPrint = "show";
		console.log("打印");
		var lodop = $.device.printGetLodop();
		// var lodop = getLodop();
		var style =
			"<style>table {text-align: center;width: 100%;height: 400px;font-size: 18px;border-collapse: collapse;}";
		style = style +
			".divzysxright{float: right;width: 650px;}.divzysxleft{float: left;margin-left: 20px;}";
		style = style + "table td{border:1px solid} table th{text-align:right;border:1px solid}</style>";
		var html = style + "<body>" + document.getElementsByClassName("printDiv2")[0].innerHTML + "</body>";
		lodop.ADD_PRINT_TEXT(50, 0, "100%", 50, '残疾评定（初评）预约单');
		lodop.SET_PRINT_STYLEA(0, "Alignment", 2);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		// lodop.ADD_PRINT_HTM(120, 80, 100, 20, '松江区');
		lodop.ADD_PRINT_HTM(150, 2, "100%", "100%", html);
		lodop.PRINT();
		// lodop.PREVIEW();
		// lodop.PRINT_DESIGN();
		setTimeout(function() {
			$state.go("index");
		}, 8000);
	}
});
