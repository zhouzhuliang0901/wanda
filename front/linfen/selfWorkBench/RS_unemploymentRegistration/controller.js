app.controller('selectList', function($state, $scope, appData, $location, appFactory) {
	$scope.operation = "请选择功能";
	$scope.stuffName1 = perjsonStr4;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		window.location.href="../aSocial/index.html#";
	}
	$scope.getMatterCon = function(itemName, marriageType) {
		appData.funName = itemName;//事项名称
		appData.marriageType = marriageType;//事项类别
		$state.go("loginType")
		
	};
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: true,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true,
		});
	};
	$scope.isScroll();
});
app.controller('loginType', function($state, $scope, appData, $location, appFactory) {
	$scope.operation = "请选择登录方式";
	appData.itemCode = "310750019000";
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("selectList");
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
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			timeout: 5000,
			data: {
				tokenSNO: tokenSNO,
			},
			success: function(res) {
				console.log(res);
				if(res.SUCCESS === true) {
					appData.token = res.accessToken;
					switch(appData.marriageType) {
						case "bssydj":
							$state.go("info1")
							break;
						case "bssydjxd":
							$state.go("info2")
							break;
						case "lhsydj":
							$state.go("info3")
							break;
					}
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
				name: appData.licenseName,
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
			// $scope.faceImage = images;
			// $scope.loginType = 'recognition';
			// appData.licenseNumber = info.Number;
			// appData.licenseName = info.Name;
					$scope.faceImage = images;
					appData.licenseNumber = info.Number;
					appData.licenseName = info.Name;
					appData.VALIDENDDAY = info.ValidtermOfEnd;
					appData.VALIDSTARTDAY = info.ValidtermOfStart;
					$scope.loginType = 'recognition';
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
		switch(appData.marriageType) {
			case "bssydj":
				$state.go("info1")
				break;
			case "bssydjxd":
				$state.go("info2")
				break;
			case "lhsydj":
				$state.go("info3")
				break;
		}
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
app.controller('info1', function($state, $scope, appData, $location, appFactory, $timeout, $rootScope) {
	// $scope.isLoading = true;
	$scope.funName = "本市户籍失业登记";
	$scope.getUserInfo = function() {
		$scope.applicationInfo = {
			"cert_id":appData.licenseNumber,
			"person_name":appData.licenseName
		}
		// $scope.applicationInfo = {
		// 	"person_name":"杨海东",
		// 	"cert_id":"310113197910144137"
		// }	
		$.ajax({
			type: "POST",
			url: $.getConfigMsg.preUrlSelf + '/selfapi/UnemploymentRegistration/LD0009Q3',
			// url: "http://localhost:8080/ac-self/selfapi/UnemploymentRegistration/LD0009Q3",
			dataType: 'json',
			data: {
				json: JSON.stringify($scope.applicationInfo)
			},
			success: function(dataJson) {
				console.log("返回参数"+ JSON.stringify(dataJson))
				console.log(dataJson.msg.body)
				if(!isBlank(dataJson)) {
					$scope.head = dataJson.msg.head;
					$scope.body = dataJson.msg.body;
					if(!isBlank($scope.head)) {
						if($scope.head.rst.buscode == "000000") {
							$scope.jzdqx_name = dataJson.msg.body.jzdqx_name,
							$scope.hjqx2_id = dataJson.msg.body.hjqx2_id,
							$scope.hjdz = dataJson.msg.body.hjdz,
							$scope.person_name = dataJson.msg.body.person_name,
							$scope.pid = dataJson.msg.body.pid,
							$scope.swbz = dataJson.msg.body.swbz,
							$scope.zzmm_name = dataJson.msg.body.zzmm_name,
							$scope.lxdz = dataJson.msg.body.lxdz,
							$scope.hjjd_id = dataJson.msg.body.hjjd_id,
							$scope.ldzt_name = dataJson.msg.body.ldzt_name,
							$scope.xb_name = dataJson.msg.body.xb_name,
							$scope.csny = dataJson.msg.body.csny,
							$scope.mqdaszd = dataJson.msg.body.mqdaszd,
							$scope.wqslbz = dataJson.msg.body.wqslbz,
							$scope.mz_name = dataJson.msg.body.mz_name,
							$scope.hjqx_name = dataJson.msg.body.hjqx_name,
							$scope.whcd_name = dataJson.msg.body.whcd_name,
							$scope.jzdjd_name = dataJson.msg.body.jzdjd_name,
							$scope.hjjd_name = dataJson.msg.body.hjjd_name,
							$scope.isLoading = false
						} else {
							$scope.isAlert = true;
							$scope.msg = $scope.head.rst.errmsg;
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
	$scope.nextText = "提交"
	$scope.isShow1 = false;
	$scope.isShow2 = false;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	// $scope.isLoading = true;
	$scope.healthList = healthList//健康状况
	$scope.disabilityList = disabilityList//残疾列表
	$scope.intentionList = intentionList//求职意向
	$scope.unemploymentList = unemploymentList
	$scope.unemployTagList = unemployTagList//失业保险标志
	$scope.nowIntentionList = nowIntentionList//目前意向
	$scope.stName = appData.licenseName;
	$scope.stIdCard = appData.licenseNumber;
	//获取用户id
	$scope.getUserInfoByAccessToken = function() {
		console.log("token:"+appData.token);
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getUserInfoByAccessToken.do",
			dataType: "jsonp",
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
			$.ajax({
				type: "post",
				url: $.getConfigMsg.preUrl + "/selfapi/pensionAdjustment/saveItemInfo.do",
				dataType: "json",
				// jsonp: "jsonpCallback",
				data: {
					userId: appData.zwdtsw_user_id,//用户ID
					itemCode: encodeURI(appData.itemCode),
					itemName: encodeURI(appData.funName),
					username: encodeURI(appData.licenseName),
					idCardNo: appData.licenseNumber,
					mobile:$scope.stMobile,
					result: result
				},
				success: function(dataJson) {
					if(dataJson.code == "200") {
						appData.applyNo = dataJson.data.applyNo;
						$state.go("submit");
					} else {
						$scope.isLoading = false;
						appData.applyNo = "";
						$scope.isAlert = true;
						$scope.msg = "办件信息同步失败,请稍后再试";
					}
				},
				error: function(err) {
					$scope.isLoading = false;
					$scope.isAlert = true;
					$scope.msg = "办件信息同步异常,请稍后再试";
				}
			});
	}
	//监听
	$timeout(function() {
		selectBlur();
		$scope.$watch("healthListName", function(val) {
			if(val) {
				if(val.val=="残疾"){
					$scope.isShow1 = true;
				}else{
					$scope.isShow1 = false;
				}
				
			}
		});
		$scope.$watch("unemploymentListName", function(val) {
			if(val) {
				if(val.val=="其他"){
					$scope.isShow2 = true;
				}else{
					$scope.isShow2 = false;
				}
				
			}
		});
	}, 100);
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.healthListName)) {
				$scope.isAlert = true;
				$scope.msg = "请选择健康状况！";
				return;
			}
			if(isBlank($scope.unemploymentListName)) {
				$scope.isAlert = true;
				$scope.msg = "请选择失业原因！";
				return;
			}
			if(isBlank($scope.unTagListName)) {
				$scope.isAlert = true;
				$scope.msg = "请选择失业保险金标志！";
				return;
			}
			if(isBlank($scope.intentionListName)) {
				$scope.isAlert = true;
				$scope.msg = "请选择求职意向！";
				return;
			}
			if(!isPhoneAvailable($scope.stMobile)) {
				$scope.isAlert = true;
				$scope.msg = "请填写正确的联系电话";
				return;
			}
		} while (condFlag);
		//提交参数集合
		condFlag = true;
		$scope.isLoading = true;
		$scope.applicationInfo = {
			"Pid":JSON.stringify($scope.pid),//个人标识
			"person_name":appData.licenseName,
			"cetf_id":appData.licenseNumber,
			"Mqyx_id":$scope.nowIntentionListName.key,//目前意向
			"Czygh":"0000000003",//  操作员工号
			"Bz":"",//  备注
			"Lrqx_id":$scope.hjqx2_id,// 录入区县
			"Lrjd_id":$scope.hjjd_id,//  录入街道
			"Jyzzrq":"2020720",//  就业终止日期
			"Syrylb_id":"9",//失业人员类别
			"Swbz":$scope.swbz,//死亡标志
			"Jkzk_id":$scope.healthListName.key,//健康状况 
			"Cjdj_id":$scope.isShow1 ? $scope.disabilityListName.key: "",//残疾等级
			"Sjhm":$scope.stMobile,//  手机
			"Sysj":getNowFormatDate2(),//  失业时间  默认当天
			"Sydjd":"1",//  失业登记地
			"Syyy":$scope.unemploymentListName.key,//  失业原因
			"Qtyy":$scope.isShow2 ? $scope.otherST:"",//其他原因
			"Slsbjbz":$scope.unTagListName.key, //申领失业保险金标志 
			"Qzyx":$scope.intentionListName.val,//  求职意向
			"Qtsm":"", //  其他说明
			"Mqqk_id":"21", //  目前情况
			"Dcfs_id":"4" //  调查方式
	}
	console.log($scope.applicationInfo)
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + '/selfapi/UnemploymentRegistration/LD0009S3',
			// url: "http://localhost:8080/ac-self/selfapi/UnemploymentRegistration/LD0009S3",
			dataType: "json",
			data: {
				json: JSON.stringify($scope.applicationInfo)
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
					// $scope.msg = "提交失败请重试！";
					$scope.msg =dataJson.msg.head.rst.errmsg;
					$scope.submitInfo("0");
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
app.controller('info2', function($state, $scope, appData, $location, appFactory, $timeout, $rootScope) {
	// $scope.isLoading = true;
	$scope.funName = "本市户籍失业登记续登";
	$scope.getUserInfo = function() {
		$scope.applicationInfo = {
			"cert_id":appData.licenseNumber,
			"person_name":appData.licenseName
		}
		// $scope.applicationInfo = {
		// 	"person_name":"潘佳菁",
		// 	"cert_id":"31022919860429164X"
		// }	
		$.ajax({
			type: "POST",
			 url: $.getConfigMsg.preUrlSelf + '/selfapi/UnemploymentRegistration/LD0009Q3',
			// url: "http://localhost:8080/ac-self/selfapi/UnemploymentRegistration/LD0009Q3",
			dataType: 'json',
			// jsonp: "jsonpCallback",
			data: {
				json: JSON.stringify($scope.applicationInfo)
			},
			success: function(dataJson) {
				console.log("返回参数"+ JSON.stringify(dataJson))
				console.log(dataJson.msg.body)
				if(!isBlank(dataJson)) {
					$scope.head = dataJson.msg.head;
					$scope.body = dataJson.msg.body;
					if(!isBlank($scope.head)) {
						if($scope.head.rst.buscode == "000000") {
							$scope.jzdqx_name = dataJson.msg.body.jzdqx_name,
							$scope.hjqx2_id = dataJson.msg.body.hjqx2_id,
							$scope.hjdz = dataJson.msg.body.hjdz,
							$scope.person_name = dataJson.msg.body.person_name,
							$scope.pid = dataJson.msg.body.pid,
							$scope.swbz = dataJson.msg.body.swbz,
							$scope.zzmm_name = dataJson.msg.body.zzmm_name,
							$scope.lxdz = dataJson.msg.body.lxdz,
							$scope.hjjd_id = dataJson.msg.body.hjjd_id,
							$scope.ldzt_name = dataJson.msg.body.ldzt_name,
							$scope.xb_name = dataJson.msg.body.xb_name,
							$scope.csny = dataJson.msg.body.csny,
							$scope.mqdaszd = dataJson.msg.body.mqdaszd,
							$scope.wqslbz = dataJson.msg.body.wqslbz,
							$scope.mz_name = dataJson.msg.body.mz_name,
							$scope.hjqx_name = dataJson.msg.body.hjqx_name,
							$scope.whcd_name = dataJson.msg.body.whcd_name,
							$scope.jzdjd_name = dataJson.msg.body.jzdjd_name,
							$scope.hjjd_name = dataJson.msg.body.hjjd_name,
							// $scope.bankListName = bankName(appData.Per_khyh,bankList),
							$scope.isLoading = false
						} else {
							$scope.isAlert = true;
							$scope.msg = $scope.head.rst.errmsg;
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
	$scope.nextText = "提交"
	$scope.isShow1 = false;
	$scope.isShow2 = false;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	// $scope.isLoading = true;
	$scope.healthList = healthList//健康状况
	$scope.disabilityList = disabilityList//残疾列表
	$scope.intentionList = intentionList//求职意向
	$scope.unemploymentList = unemploymentList
	$scope.unemployTagList = unemployTagList//失业保险标志
	$scope.nowIntentionList = nowIntentionList//目前意向
	$scope.stName = appData.licenseName;
	$scope.stIdCard = appData.licenseNumber;
		//获取用户id
	$scope.getUserInfoByAccessToken = function() {
		console.log("token:"+appData.token);
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getUserInfoByAccessToken.do",
			dataType: "jsonp",
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
			$.ajax({
				type: "post",
				url: $.getConfigMsg.preUrl + "/selfapi/pensionAdjustment/saveItemInfo.do",
				dataType: "json",
				// jsonp: "jsonpCallback",
				data: {
					userId: appData.zwdtsw_user_id,//用户ID
					itemCode: encodeURI(appData.itemCode),
					itemName: encodeURI(appData.funName),
					username: encodeURI(appData.licenseName),
					idCardNo: appData.licenseNumber,
					mobile:$scope.stMobile,
					result: result
				},
				success: function(dataJson) {
					if(dataJson.code == "200") {
						appData.applyNo = dataJson.data.applyNo;
						$state.go("submit");
					} else {
						$scope.isLoading = false;
						appData.applyNo = "";
						$scope.isAlert = true;
						$scope.msg = "办件信息同步失败,请稍后再试";
					}
				},
				error: function(err) {
					$scope.isLoading = false;
					$scope.isAlert = true;
					$scope.msg = "办件信息同步异常,请稍后再试";
				}
			});
	}
	//监听
	$timeout(function() {
		selectBlur();
		$scope.$watch("healthListName", function(val) {
			if(val) {
				if(val.val=="残疾"){
					$scope.isShow1 = true;
				}else{
					$scope.isShow1 = false;
				}
				
			}
		});
		$scope.$watch("unemploymentListName", function(val) {
			if(val) {
				if(val.val=="其他"){
					$scope.isShow2 = true;
				}else{
					$scope.isShow2 = false;
				}
				
			}
		});
	}, 100);
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.healthListName)) {
				$scope.isAlert = true;
				$scope.msg = "请选择健康状况！";
				return;
			}
			if(isBlank($scope.unemploymentListName)) {
				$scope.isAlert = true;
				$scope.msg = "请选择失业原因！";
				return;
			}
			if(isBlank($scope.unTagListName)) {
				$scope.isAlert = true;
				$scope.msg = "请选择失业保险金标志！";
				return;
			}
			if(isBlank($scope.intentionListName)) {
				$scope.isAlert = true;
				$scope.msg = "请选择求职意向！";
				return;
			}
			if(!isPhoneAvailable($scope.stMobile)) {
				$scope.isAlert = true;
				$scope.msg = "请填写正确的联系电话";
				return;
			}
		} while (condFlag);
		//提交参数集合
		condFlag = true;
		$scope.isLoading = true;
		$scope.applicationInfo = {
			"Pid":JSON.stringify($scope.pid),//个人标识
			"person_name":appData.licenseName,
			"cetf_id":appData.licenseNumber,
			"Mqyx_id":$scope.nowIntentionListName.key,//目前意向
			"Czygh":"0000000003",//  操作员工号
			"Bz":"",//  备注
			"Lrqx_id":$scope.hjqx2_id,// 录入区县
			"Lrjd_id":$scope.hjjd_id,//  录入街道
			"Jyzzrq":"2020720",//  就业终止日期
			"Syrylb_id":"9",//失业人员类别
			"Swbz":$scope.swbz,//死亡标志
			"Jkzk_id":$scope.healthListName.key,//健康状况 
			"Cjdj_id":$scope.isShow1 ? $scope.disabilityListName.key: "",//  残疾等级
			"Sjhm":$scope.stMobile,//  手机
			"Sysj":getNowFormatDate2(),//  失业时间  默认当天
			"Sydjd":"1",//  失业登记地
			"Syyy":$scope.unemploymentListName.key,//  失业原因
			"Qtyy":$scope.isShow2 ? $scope.otherST:"",//其他原因
			"Slsbjbz":$scope.unTagListName.key, //申领失业保险金标志 
			"Qzyx":$scope.intentionListName.val,//  求职意向
			"Qtsm":"", //  其他说明
			"Mqqk_id":"21", //  目前情况
			"Dcfs_id":"4" //  调查方式
	}
	console.log($scope.applicationInfo)
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + '/selfapi/UnemploymentRegistration/LD0009S3',
			// url: "http://localhost:8080/ac-self/selfapi/UnemploymentRegistration/LD0009S3",
			dataType: "json",
			data: {
				json: JSON.stringify($scope.applicationInfo)
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
					// $scope.msg = "提交失败请重试！";
					$scope.msg =dataJson.msg.head.rst.errmsg;
					$scope.submitInfo("0");
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
app.controller('info3', function($state, $scope, appData, $location, appFactory, $timeout, $rootScope) {
	$scope.funName = "来沪人员失业登记";
	// $scope.isLoading = true;
	// appData.licenseNumber = "412827196811282021";
	// appData.licenseName  = "宋新丽";
	$scope.getUserInfo = function() {
		$scope.applicationInfo = {
			"cetf_id":appData.licenseNumber,
			"person_name":appData.licenseName
		}	
		$.ajax({
			type: "POST",
			 url: $.getConfigMsg.preUrlSelf + '/selfapi/UnemploymentRegistration/LD0009Q1',
			// url: "http://localhost:8080/ac-self/selfapi/UnemploymentRegistration/LD0009Q1",
			dataType: 'json',
			// jsonp: "jsonpCallback",
			data: {
				json: JSON.stringify($scope.applicationInfo)
			},
			success: function(dataJson) {
				console.log("返回参数"+ JSON.stringify(dataJson))
				console.log(dataJson.msg.body)

				if(!isBlank(dataJson)) {
					$scope.head = dataJson.msg.head;
					$scope.body = dataJson.msg.body;
					if(!isBlank($scope.head)) {
						if($scope.head.rst.buscode == "000000") {
							$scope.zzmm_id = (dataJson.msg.body.zzmm_id=="")?"null" : dataJson.msg.body.zzmm_id,//政治面貌
							$scope.hjd_id = (dataJson.msg.body.hjd_id=="") ?"null" : dataJson.msg.body.hjd_id,//户籍地
							$scope.hjdz = (dataJson.msg.body.hjdz=="") ?"null" : dataJson.msg.body.hjdz,//
							$scope.last_dwmc = (dataJson.msg.body.last_dwmc=="") ?"null" : dataJson.msg.body.last_dwmc,//单位名称
							$scope.ldscbh = (dataJson.msg.body.ldscbh=="") ?"null" : dataJson.msg.body.ldscbh,//PID
							$scope.mz_id =  (dataJson.msg.body.mz_id=="") ?"null" : dataJson.msg.body.mz_id,//民族
							$scope.xm =(dataJson.msg.body.xm=="") ?"null" : dataJson.msg.body.xm,//姓名
							$scope.whcd_id =(dataJson.msg.body.whcd_id=="") ?"null" : dataJson.msg.body.whcd_id,//文化程度
							$scope.zyjn = (dataJson.msg.body.zyjn=="") ?"null" : dataJson.msg.body.zyjn,//职业技能
							$scope.last_jyksrq =(dataJson.msg.body.last_jyksrq=="") ?"null" : dataJson.msg.body.last_jyksrq,//就业开始日期
							$scope.last_jyjsrq = (dataJson.msg.body.last_jyjsrq=="") ?"null" : dataJson.msg.body.last_jyjsrq,//就业结束日期
							$scope.last_cid = (dataJson.msg.body.last_cid=="") ?"null" : dataJson.msg.body.last_cid,//单位标识
							$scope.jzzbh = (dataJson.msg.body.jzzbh=="")? "null" : dataJson.msg.body.jzzbh,//居住证编号
							$scope.isLoading = false
						} else {
							$scope.isAlert = true;
							$scope.msg = $scope.head.rst.errmsg;
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
	$scope.nextText = "提交"
	$scope.isShow1 = false;
	$scope.isShow2 = false;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	// $scope.isLoading = true;
	$scope.healthList = healthList//健康状况
	$scope.disabilityList = disabilityList//残疾列表
	$scope.intentionList = intentionList//求职意向
	$scope.unemploymentList = unemploymentList
	$scope.unemployTagList = unemployTagList//失业保险标志
	$scope.nowIntentionList = nowIntentionList//目前意向
	$scope.stName = appData.licenseName;//办理人姓名
	$scope.stIdCard = appData.licenseNumber;//办理人身份证号码
	//获取用户id
	$scope.getUserInfoByAccessToken = function() {
		console.log("token:"+appData.token);
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getUserInfoByAccessToken.do",
			dataType: "jsonp",
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
			$.ajax({
				type: "post",
				url: $.getConfigMsg.preUrl + "/selfapi/pensionAdjustment/saveItemInfo.do",
				dataType: "json",
				// jsonp: "jsonpCallback",
				data: {
					userId: appData.zwdtsw_user_id,//用户ID
					itemCode: encodeURI(appData.itemCode),
					itemName: encodeURI(appData.funName),
					username: encodeURI(appData.licenseName),
					idCardNo: appData.licenseNumber,
					mobile:$scope.stMobile,
					result: result
				},
				success: function(dataJson) {
					if(dataJson.code == "200") {
						appData.applyNo = dataJson.data.applyNo;
						$state.go("submit");
					} else {
						$scope.isLoading = false;
						appData.applyNo = "";
						$scope.isAlert = true;
						$scope.msg = "办件信息同步失败,请稍后再试";
					}
				},
				error: function(err) {
					$scope.isLoading = false;
					$scope.isAlert = true;
					$scope.msg = "办件信息同步异常,请稍后再试";
				}
			});
	}
	$timeout(function() {//选中特定选项 通过ng-show来展现被隐藏的下拉框
		selectBlur();
		$scope.$watch("healthListName", function(val) {
			if(val) {
				if(val.val=="残疾"){
					$scope.isShow1 = true;
				}else{
					$scope.isShow1 = false;
				}
				
			}
		});
		$scope.$watch("unemploymentListName", function(val) {
			if(val) {
				if(val.val=="其他"){
					$scope.isShow2 = true;
				}else{
					$scope.isShow2 = false;
				}
				
			}
		});
	}, 100);
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.healthListName)) {
				$scope.isAlert = true;
				$scope.msg = "请选择健康状况！";
				return;
			}
			if(isBlank($scope.unemploymentListName)) {
				$scope.isAlert = true;
				$scope.msg = "请选择失业原因！";
				return;
			}
			if(isBlank($scope.unTagListName)) {
				$scope.isAlert = true;
				$scope.msg = "请选择失业保险金标志！";
				return;
			}
			if(isBlank($scope.intentionListName)) {
				$scope.isAlert = true;
				$scope.msg = "请选择求职意向！";
				return;
			}
			if(!isPhoneAvailable($scope.stMobile)) {
				$scope.isAlert = true;
				$scope.msg = "请填写正确的联系电话";
				return;
			}
		} while (condFlag);
		//提交参数集合
		condFlag = true;
		$scope.isLoading = true;
		$scope.applicationInfo1 = {
			"pid":JSON.stringify($scope.ldscbh),//个人标识
			"Cetf_id":appData.licenseNumber,//证件编号
			"jzzbh":$scope.jzzbh,//证件编号
			"xm":$scope.xm, //姓名
			"xb":((parseInt(appData.licenseNumber.substring(16, 17)) % 2) == 0) ? "2" : "1",//性别
			"mz_id":$scope.mz_id,// 民族
			"csrq":(appData.licenseNumber).substring(6, 14),//  出生日期
			"whcd_id":$scope.whcd_id,//  文化程度
			"zzmm_id":$scope.zzmm_id,//  政治面貌
			"jzddz":"不明",//  居住地地址
			"lxdh1":$scope.stMobile,//  联系电话1
			"lxdh2":$scope.stMobile,//  联系电话2
			"yzbm":"null",// 邮政编码
			"hjd_id":$scope.hjd_id,//  户籍地
			"hjdz":"不明",//  户籍地址
			"zyjn":$scope.zyjn,//  专业技能
			"gzksrq":$scope.last_jyksrq,//  工作开始日期
			"gzjsrq":$scope.last_jyjsrq,//  工作结束日期
			"dwmc":$scope.last_dwmc,//  工作单位名称
			"cid":$scope.last_cid,//  工作单位标识
			"bz":"null",//  备注
			"lxjd":"null",//  联系街道
			"user_id":"0000000003",//  操作员工号
			"JKZK_ID":$scope.healthListName.key,//健康状况 
			"Cjdj_id":$scope.isShow1 ? $scope.disabilityListName.key: "null",//  残疾等级
			"LXDH2":$scope.stMobile,//  手机号
			"SYSJ":$scope.last_jyjsrq,//  失业时间 默认当天
			"SYDJD":"2",// 失业登记地
			"SYYY":$scope.unemploymentListName.key,//  失业原因
			"QTYY":$scope.isShow2 ? $scope.otherST:"null",//其他原因
			"SLSBJBZ":$scope.unTagListName.key, //申领失业保险金标志 
			"QZYX":$scope.intentionListName.val,//  求职意向
			"QTSM":"null",//  其他说明
			// "JZZBZ":"1",//  其他说明
			
	}
	console.log("返回参数"+ JSON.stringify(	$scope.applicationInfo1))
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + '/selfapi/UnemploymentRegistration/LD0009S1',
			// url: "http://localhost:8080/ac-self/selfapi/UnemploymentRegistration/LD0009S1",
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
					// $scope.msg = "提交失败请重试！";
					$scope.msg =dataJson.msg.head.rst.errmsg;
					$scope.submitInfo("0");
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
	$scope.goHome = function() {
		$.device.GoHome();
	}
});