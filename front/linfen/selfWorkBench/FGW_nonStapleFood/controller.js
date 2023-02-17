app.controller("list", function($scope, $state, appData) {
	$scope.operation = "请选择办事事项";
	$scope.stuffName1 = perjsonStr1;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.getMatterCon = function(itemName, code, type) {
		appData.itemName = itemName;
		appData.code = code;
		appData.type = type;
		console.log(appData.type)
		if(code == "") {
			$scope.isAlert = true;
			$scope.msg = "暂未开放";
		} else if(code == "312090133000"){
			$state.go("selectList")
		}
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
app.controller("selectList", function($scope, $state, appData) {
	$scope.operation = "请选择功能";
	$scope.stuffName1 = perjsonStr4;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.getMatterCon = function(itemName, code, type) {
		appData.itemName = itemName;
		appData.code = code;
		appData.type = type;
		if(code == "") {
			$scope.isAlert = true;
			$scope.msg = "暂未开放";
		} else if(type == "bl"){//办理界面
			$state.go("guideline")
		}else if(type == "dy"){
			$state.go("loginType")
		}
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
app.controller("guideline", function($scope, $state, appData) {
	$scope.operation = "办事指南";
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.getMatterCon = function(itemName, code, type) {
		appData.itemName = itemName;
		appData.code = code;
		appData.type = type;
		if(code == "") {
			$scope.isAlert = true;
			$scope.msg = "暂未开放";
		} else {
			$state.go("loginType");
		}
	};
	$scope.prevStep = function() {
		$state.go("selectList");
	}
	$scope.nextStep = function() {
		$state.go("loginType");
	}
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
	$scope.funName = "副食品价格补贴发放银行卡维护";
	appData.funName = $scope.funName;
	appData.ywlx = "LS5014";
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
	// $scope.funName = appData.funName;
	// $scope.isAlert = false;
	// $scope.msg = "";
	// $scope.nextLink = ""; // 下一步标识符
	// $scope.loginType = appData.loginType;
	// switch($scope.loginType) {
	// 	case "idcard":
	// 		$scope.operation = "身份证登录";
	// 		break;
	// 	case "cloud":
	// 		$scope.operation = "随申办登录";
	// 		break;
	// }
	// //获取token ------2、比对成功后，根据tokenSNO获取access_token
	// $scope.getAccessToken = function(tokenSNO) {
	// 	var rec = $.ajax({
	// 		url: $.getConfigMsg.preUrl + "/aci/workPlatform/getAccessToken.do",
	// 		type: "post",
	// 		dataType: "jsonp",
	// 		jsonp: "jsonpCallback",
	// 		timeout: 5000,
	// 		data: {
	// 			tokenSNO: tokenSNO,
	// 		},
	// 		success: function(res) {
	// 			console.log(res);
	// 			if(res.SUCCESS === true) {
	// 				appData.token = res.accessToken;
	// 				console.log("token1:"+appData.token)
	// 				$state.go("info");
	// 			} else {
	// 				$scope.isAlert = true;
	// 				$scope.msg = "数据加载异常,请重试";
	// 				$scope.alertConfirm = function() {
	// 					$state.go("loginType");
	// 				}
	// 			}
	// 		},
	// 		error: function(err) {
	// 			console.log(err);
	// 		},
	// 		complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
	// 			if(status === 'timeout' || status === 'error') { //超时,status还有success,error等值的情况
	// 				rec.abort();　　　　
	// 			}　　
	// 		}
	// 	})
	// }
	// //获取token ------1、两照对比获取tokenSNO
	// $scope.getTokenSNO = function(face, photograph) {
	// 	var idCardPhoto = face;
	// 	var capturePhoto = photograph;
	// 	var rec = $.ajax({
	// 		url: $.getConfigMsg.preUrl + "/aci/workPlatform/getTokenSNO.do",
	// 		type: "post",
	// 		dataType: "json",
	// 		//			jsonp: "jsonpCallback",
	// 		data: {
	// 			name: appData.licenseName,
	// 			idCard: appData.licenseNumber,
	// 			facePhoto: capturePhoto,
	// 			copyIDPhoto: idCardPhoto
	// 		},
	// 		success: function(res) {
	// 			console.log(res);
	// 			if(res.SUCCESS === true && res.verify === 1) {
	// 				$scope.getAccessToken(res.tokenSNO);
	// 			} else {
	// 				$scope.isAlert = true;
	// 				$scope.msg = "数据加载异常,请重试";
	// 				$scope.alertConfirm = function() {
	// 					$state.go("loginType");
	// 				}
	// 			}
	// 		},
	// 		error: function(err) {
	// 			console.log(err);
	// 		},
	// 		complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
	// 			if(status === 'timeout' || status === 'error') { //超时,status还有success,error等值的情况
	// 				rec.abort();　　　　
	// 			}　　
	// 		}
	// 	})
	// }
	// // $scope.idcardLogin = function(info, images) {
	// // 	if(info) {
	// // 		$scope.faceImage = images;
	// // 		appData.licenseNumber = info.Number;
	// // 		appData.licenseName = info.Name;
	// // 		appData.VALIDENDDAY = info.ValidtermOfEnd;
	// // 		appData.VALIDSTARTDAY = info.ValidtermOfStart;
	// // 		$scope.loginType = 'recognition';
	// // 	} else {
	// // 		layer.msg("很抱歉，未获取到相关信息，请重试。");
	// // 	}
	// // }
	// // test跳过身份验证
	// 	$scope.idcardLogin = function(info, images) {
	// 		if(true) {
	// 			$scope.faceImage = images;
	// 			appData.licenseNumber = '310228198808070818';
	// 			appData.licenseName = '陈雷';
	// 			appData.VALIDENDDAY = "2029-05-13";
	// 			appData.VALIDSTARTDAY = "2019-05-13";
	// 			$scope.getTokenSNO(photo, photo);
	// 			$state.go("info");
	// 		} else {
	// 			layer.msg("很抱歉，未获取到相关信息，请重试。");
	// 		}
	// 	}
	// 	$scope.idcardLogin();
	// $scope.getResult = function(img) {
	// 	$scope.img = img;
	// 	$state.go("info");
	// }
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
app.controller('info', function($state, $scope, appData, $location, appFactory, $timeout, $rootScope) {
	$scope.funName = appData.funName;
	appData.isUpload = [];
	appData.listImg = [];
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.getAccessTokenByBank = function() {
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/BankCardIdentificationController/getToken.do",
			// url: "http://localhost:8080/ac-self/selfapi/BankCardIdentificationController/getToken.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			success: function(dataJson) {
				$scope.accessToken = dataJson.accessToken;
			},
			error: function(err) {
				console.log("调用接口失败！！");
			}
		});
	}
	$scope.getAccessTokenByBank();
	$scope.bankName = function() {
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/BankCardIdentificationController/identifyCard.do",
			// url: "http://localhost:8080/ac-self/selfapi/BankCardIdentificationController/identifyCard.do",
			dataType: "json",
			data: {
				accessToken: $scope.accessToken,
				cardNo: $scope.stIdCardType
			},
			success: function(dataJson) {
				if(dataJson.respCode=="00"){
					$scope.selectedName = dataJson.combiLabels[0].A001051.split("储蓄卡")[0];
					$scope.isLoading = false;
				}else{
					$scope.isAlert = true;
					$scope.isLoading = false;
					$scope.msg =  '银行卡号输入有误，请重新输入';
					// $scope.alertConfirm = function() {
					// 	$state.go("loginType");
					// }
				}
				
				
			},
			error: function(err) {
				console.log("调用接口失败！！");
			}
		});
	}
	//个人信息
	$scope.isLoading = true;
	$scope.stName = appData.licenseName;
	$scope.stIdCard = appData.licenseNumber;
	$scope.stSex = ((parseInt(appData.licenseNumber.substring(16, 17)) % 2) == 0) ? "女" : "男";
	$scope.dtBirth = (appData.licenseNumber).substring(6, 10) + "-" + (appData.licenseNumber).substring(10, 12) + "-" + (appData.licenseNumber).substring(12, 14);
	$scope.stMobile = appData.stMobile || "";
	$scope.nationList = nations;
	$scope.hjProvince = filterByInfo($rootScope.allList, "310000");
	$scope.jzProvince = filterByInfo($rootScope.allList, "310000");
	$scope.hjCityList = filterByName($rootScope.allList, '310000');
	$scope.jzCityList = filterByName($rootScope.allList, '310000');
	$scope.centerProvinceList = $rootScope.ShangHaiList;
	$scope.$watch("nation", function(val) {
		console.log(val.shortname);
	});
	//监听省市 区域变化
	$timeout(function() {
		selectBlur();
		$scope.$watch("stIdCardType", function(val) {
			if(luhnCheck(val)) {
				$scope.isLoading = true;
				$scope.bankName();
			}
		});
		$scope.$watch("hjCity", function(val) {
			if(val) {
				$scope.hjCountyList = $rootScope.ShangHaiList;
			}
		});
		$scope.$watch("hjCounty", function(val) {
			if(val) {
				$scope.hjStreetList = filterByName($rootScope.ShangHaiStreetList, val.key);
			}
		});
		$scope.$watch("jzCity", function(val) {
			if(val) {
				$scope.jzCountyList = $rootScope.ShangHaiList;
			}
		});
		$scope.$watch("jzCounty", function(val) {
			if(val) {
				$scope.jzStreetList = filterByName($rootScope.ShangHaiStreetList, val.key);
			}
		});
		$scope.$watch("centerProvince", function(val) {
			if(val) {
				$scope.centerStreetList = filterByName($rootScope.centerList, val.key);
			}
		});
	}, 100);
	//获取办事人居民信息
	$scope.getApplicantInfo = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/civilService/getApplicantInfo.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				idType: 1,
				idNo: appData.licenseNumber,
			},
			success: function(dataJson) {
				console.log(dataJson);
				//test赋值 
				$scope.isLoading = false;
				appData.stMobile = dataJson.cellno ||"";
				$scope.hjCity = appData.hjCity || filterByInfo($rootScope.allList, dataJson.data.hjcity);
				$scope.hjCounty = appData.hjCounty || filterByInfo($rootScope.ShangHaiList, dataJson.data.hjregion);
				$scope.hjStreet = appData.hjStreet || filterByInfo($rootScope.ShangHaiStreetList, dataJson.data.hjneighborhood);
				$scope.jzCity = appData.jzCity || filterByInfo($rootScope.allList, dataJson.data.jzcity);
				$scope.jzCounty = appData.jzCounty || filterByInfo($rootScope.ShangHaiList, dataJson.data.jzregion);
				$scope.jzStreet = appData.jzStreet || filterByInfo($rootScope.ShangHaiStreetList, dataJson.data.jzneighborhood);
			},
			error: function(err) {
				console.log(err)
			}
		});
	}
	$timeout(function() {
		$scope.getApplicantInfo();
	}, 200);
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
	// if(appData.zwdtsw_user_id) {
		
	// } else {
		
	// }
	$timeout(function() {
		$scope.getUserInfoByAccessToken();
	}, 200);

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
				console.log("统一审批编码："+ dataJson.aplyNo)
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
	$scope.getApplyNoByItemNo(appData.ywlx);
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(!isPhoneAvailable($scope.stMobile)) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确联系电话！";
				return;
			}
			if(isBlank($scope.hjCity)) {
				$scope.isAlert = true;
				$scope.msg = "请选择户籍地址（市）！";
				return;
			}
			if(isBlank($scope.hjCounty)) {
				$scope.isAlert = true;
				$scope.msg = "请选择户籍地址（区）！";
				return;
			}
			if(isBlank($scope.hjStreet)) {
				$scope.isAlert = true;
				$scope.msg = "请选择户籍地址（街道）！";
				return;
			}
			if(isBlank($scope.jzCity)) {
				$scope.isAlert = true;
				$scope.msg = "请选择居住地址（市）！";
				return;
			}
			if(isBlank($scope.jzCounty)) {
				$scope.isAlert = true;
				$scope.msg = "请选择居住地址（区）！";
				return;
			}
			if(isBlank($scope.jzStreet)) {
				$scope.isAlert = true;
				$scope.msg = "请选择居住地址（街道）！";
				return;
			}
			if(isBlank($scope.centerProvince)) {
				$scope.isAlert = true;
				$scope.msg = "请选择受理中心（区）！";
				return;
			}
			if(isBlank($scope.centerStreet)) {
				$scope.isAlert = true;
				$scope.msg = "请选择受理中心（街道社区）！";
				return;
			}
			if(isBlank($scope.stIdCardType)) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的银行卡号！";
				return;
			}
		} while (condFlag);
		//提交参数集合
		condFlag = true;
		$scope.isLoading = true;
		appData.stSex = $scope.stSex;
		appData.dtBirth = $scope.dtBirth;
		appData.stMobile = $scope.stMobile;
		appData.nation = $scope.nation;
		appData.hjProvince = $scope.hjProvince;
		appData.hjCity = $scope.hjCity;
		appData.hjCounty = $scope.hjCounty;
		appData.hjStreet = $scope.hjStreet;
		appData.jzProvince = $scope.jzProvince;
		appData.jzCity = $scope.jzCity;
		appData.jzCounty = $scope.jzCounty;
		appData.jzStreet = $scope.jzStreet;
		appData.stCenter = $scope.centerStreet;
		// $state.go("applyInfo");

		$scope.isLoading = true;
		$scope.applicationInfo = {
			"applicant": {
				"idtype": "1",
				"idno": appData.licenseNumber,
				"fullname": appData.licenseName,
				"sex": appData.stSex == "男" ? "1" : "2",
				"birthday": appData.dtBirth,
				"nationality": appData.nation.id,
				"cellno": appData.stMobile,
				"hjprovince": appData.hjProvince.key,
				"hjcity": appData.hjCity.key,
				"hjregion": appData.hjCountyKey || "",
				"hjneighborhood": appData.hjStreetKey || "",
				"jzprovince": appData.jzProvince.key,
				"jzcity": appData.jzCity.key,
				"jzregion": appData.jzCountyKey || "",
				"jzneighborhood": appData.jzStreetKey || ""
			},
			"ywtbAffairsapply": {
				"platform": "4",
				"affairscode": appData.ywlx,
				"affairsname": appData.funName,
				"itemcode": "312000311000",
				"suid": appData.applyNo,
				"subtime": new Date(),
				"suborgancode": appData.stCenter.key,
				"suborganname": appData.stCenter.value,
				"recievertype": appData.recievertype || "",
				"recievername": appData.stReceiptName || "",
				"recieverphone": appData.stReceiptMobile || "",
				"recieverprov": appData.recieverProvinceKey || "",
				"recievercity": appData.recieverCityKey || "",
				"recieverarea": appData.receiptCountyKey || "",
				"recieveraddress": appData.stReceiptAddress || "",
				"recieverzipcode": appData.stPostCode || "",
				"logistics": "",
				"sendtype": "",
				"sendname": "",
				"sendphone": "",
				"sendprov": "",
				"sendcity": "",
				"sendarea": "",
				"sendaddress": "",
				"sendzipcode": "",
				"sendlogistics": "",
				"wt_userid": appData.zwdtsw_user_id,
				"formdata": {
					"name":appData.licenseName,//姓名
					"no":appData.licenseNumber,//身份证号
					"sex": appData.stSex == "男" ? "1" : "2",//性别
					"linktel":$scope.stMobile,//电话号码
					"banktypename":$scope.selectedName,//银行航别
					"banknumber":$scope.stIdCardType,//银行卡号
					"display":{
					"姓名":appData.licenseName,//姓名
					"身份证号":appData.licenseNumber,//身份证号
					"性别": appData.stSex == "男" ? "1" : "2",//性别
					"联系电话":$scope.stMobile,//电话号码
					"银行行别":$scope.selectedName,//银行航别
					"银行卡号":$scope.stIdCardType,//银行卡号
					}
				}
			}
		}
		console.log($scope.applicationInfo);
				$.ajax({
					type: "post",
					url: $.getConfigMsg.preUrlSelf + "/selfapi/civilService/sendYwtbApplyInfo.do",
					dataType: "json",
					data: {
						jsonStr: JSON.stringify($scope.applicationInfo)
					},
					success: function(dataJson) {
						console.log(dataJson);
						$scope.isLoading = false;
						if(dataJson.code == "0") {
							$scope.isAlert = true;
							$scope.msg = "提交成功";
							$scope.alertConfirm = function() {
								$state.go("submit");
							}
						} else {
							$scope.isAlert = true;
							$scope.msg = dataJson.msg;
							return;
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
});

app.controller("submit", function($scope, $state, appData, $sce, appFactory) {
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
	recordUsingHistory('发改委服务', '办理', $scope.funName, appData.licenseName, appData.licenseNumber, appData.stMobile, $scope.applyNo, JSON.stringify($scope.jsonStr));
	//行为分析(办理)
	trackEventForAffairs($scope.applyNo,$scope.funName,"上海市发展和改革委员会",appData.licenseName, appData.licenseNumber,appData.stMobile);
	var date = new Date();
	var month = date.getMonth() + 1;
	$scope.date = date.getFullYear() + "年" + month + "月" + date.getDate() + "日" + date.getHours() + "时" + date.getMinutes() + "分";
	$scope.nextText = "返回首页";
	$scope.nextStep = function() {
		$.device.GoHome();
	}
});