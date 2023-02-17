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
		} else if(code == "310150127000"){
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
app.controller("selectList", function($scope, $state, appData) {
	$scope.operation = "请选择功能";
	$scope.stuffName1 = perjsonStr4;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.getMatterCon = function(itemName, marriageType, type) {
		appData.itemName = itemName;//事项名称
		appData.type = type;//预约&取消
		appData.marriageType = marriageType;//事项类别
		if(type == "yy"){//办理界面
			$state.go("guideline")
		}else if(type == "qx"){
			$state.go("queryAppointment")
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
	$scope.operation = "婚姻登记网上预约服务协议";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		$state.go("selectList");
	}
	$scope.nextStep = function() {
		if(!($scope.state == 1)){
			$scope.isAlert = true;
			$scope.msg = '请阅读协议并且点击';
			}else if(appData.marriageType == "jhdjyy"){
				$state.go("marriageInfo");
				$scope.isLoading = true;
			}else{
				$state.go("divorceInfo");
				$scope.isLoading = true;
			}
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
app.controller('queryAppointment', function($state, $scope, appData, $location, appFactory, $timeout, $rootScope) {
	$scope.funName ="查询婚姻登记预约";
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.nextText = "取消预约";
	$scope.idCardNumber = appData.licenseNumber
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//查询
	$scope.getMatterCon = function() {
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/CivilMarriage/mzMarriageEventObtainNew.do",
			// url: "http://localhost:8080/ac-self/selfapi/CivilMarriage/mzMarriageEventObtainNew.do",
			dataType: "json",
			data: {
				json :  JSON.stringify(
					{
						"number": $scope.idCardNumber,
						"txtApplyNo":$scope.applyNo
					}
				)
			},
			success: function(dataJson) {
				console.log(dataJson);
				
				$scope.isLoading = false;
				if(dataJson.isSuccess == true) {
					console.log("返回："+dataJson)
					appData.man_cardNumber = dataJson.man_cardNumber;
					appData.lady_cardNumber = dataJson.lady_cardNumber;
					appData.applyNo = dataJson.applyNo;
					$scope.isAlert = true;
					$scope.msg = "查询成功";
					$scope.alertConfirm = function() {
						$state.go("cancel");
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
	};
	$scope.nextStep = function() {
		var condFlag = false;
		$state.go("cancel");
		$scope.isLoading = true;
	}
	$scope.prevStep = function() {
		$state.go("selectList");
	}
});
app.controller('cancel', function($state, $scope, appData, $location, appFactory, $timeout, $rootScope) {
	$scope.funName ="取消婚姻登记预约";
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.manNumber = appData.man_cardNumber;
	$scope.ladyNumber = appData.lady_cardNumber;
	$scope.suCode = appData.applyNo;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//取消婚姻登记预约接口
	$scope.getMatterCon = function() {
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/CivilMarriage/mzMarriageEventObtainUpdateNew.do",
			// url: "http://localhost:8080/ac-self/selfapi/CivilMarriage/mzMarriageEventObtainUpdateNew.do",
			dataType: "json",
			data: {
				json :  JSON.stringify(
					{
						"txtApplyNo": $scope.suCode,
						"manCardNumber":$scope.manNumber,
						"ladyCardNumber":$scope.ladyNumber
					}
				)
			},
			success: function(dataJson) {
				console.log(dataJson);
				$scope.isLoading = false;
				if(dataJson.isSuccess == true) {
					$scope.isAlert = true;
					$scope.msg = "取消成功";
					// $scope.alertConfirm = function() {
					// 	$state.go("cancel");
					// }
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
	};
	$scope.nextStep = function() {
		var condFlag = false;
		$state.go("");
		$scope.isLoading = true;
	}
	$scope.prevStep = function() {
		$state.go("selectList");
	}
});
app.controller('loginType', function($state, $scope, appData, $location, appFactory) {
	$scope.operation = "请选择登录方式";
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function(){
		window.location.href = "../civilAffairs/index.html";
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
					console.log("token1:"+appData.token)
					$state.go("selectList");
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
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
					// $scope.faceImage = images;
					// appData.licenseNumber = info.Number;
					// appData.licenseName = info.Name;
					// appData.VALIDENDDAY = info.ValidtermOfEnd;
					// appData.VALIDSTARTDAY = info.ValidtermOfStart;
					// $scope.loginType = 'recognition';
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
		$state.go("selectList");
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
app.controller('marriageInfo', function($state, $scope, appData, $location, appFactory, $timeout, $rootScope) {
	$scope.funName ="结婚登记预约";
	appData.isUpload = [];
	appData.listImg = [];
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isShanghai1=true;
	$scope.isShanghai2=true;
	$scope.marriageLocationList = marriageAdressInfoList;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//个人信息
	$scope.hjCityList = filterByName($rootScope.allList,'');
	$scope.jzCityList = filterByName($rootScope.allList,'');
	$scope.centerProvinceList = $rootScope.ShangHaiList;
	//监听省市 区域变化
	$timeout(function() {
		selectBlur();
		$scope.$watch("hjCity", function(val) {
			if(val) {
				if(val.key =="310000"){
					$scope.isShanghai1=false;
					$scope.hjCountyList = $rootScope.ShangHaiList;
				}else{
					$scope.isShanghai1=true;
					appData.man_qx_name =""//男方区县名
					appData.man_qx_value =""//男方区县值
					appData.man_jd_name =""//男方街道名
					appData.man_jd_value =""//男方街道值
				}			
			}
		});
		$scope.$watch("hjCounty", function(val) {
			if(val) {
				$scope.hjStreetList = filterByName($rootScope.ShangHaiStreetList, val.key);
				appData.hjCountykey = val.key
				appData.man_qx_name = val.value//男方区县名
				appData.man_qx_value = val.key //男方区县值
			}
		});
		$scope.$watch("hjStreet", function(val) {
			if(val) {
				appData.man_jd_name = val.value //女方街道名
				appData.man_jd_value = val.key//女方街道值
			}
		});
		$scope.$watch("jzCity", function(val) {
			if(val) {
				if(val.key =="310000"){
					$scope.isShanghai2=false;
					$scope.jzCountyList = $rootScope.ShangHaiList;
				}else{
					$scope.isShanghai2=true;
					appData.lady_qx_name =""//女方区县名
					appData.lady_qx_value =""//女方区县值
					appDatalady_jd_name =""//女方街道名
					appData.lady_jd_value =""//女方街道值
				}
				
			}
		});
		$scope.$watch("jzCounty", function(val) {
			if(val) {
				$scope.jzStreetList = filterByName($rootScope.ShangHaiStreetList, val.key);
				appData.jzCountykey = val.key
				appData.lady_qx_name = val.value //女方区县名
				appData.lady_qx_value = val.key//女方区县值
			}
		});
		$scope.$watch("jzStreet", function(val) {
			if(val) {
				appData.lady_jd_name = val.value //女方街道名
				appData.lady_jd_value = val.key//女方街道值
			}
		});
		$scope.$watch("centerProvince", function(val) {
			if(val) {
				$scope.centerStreetList = filterByName($rootScope.centerList, val.key);
			}
		});
		$scope.$watch("centerStreet", function(val) {
			if(val) {
				 appData.departCode = val.key;
			}
		});
	}, 100);
	$scope.nextStep = function() {
		var condFlag = false;
		if($scope.isShanghai1 && $scope.isShanghai2){
		$scope.isAlert = true;
		$scope.msg = '双方至少有一方为上海户籍';
		}else{
			appData.man_ss_name = $scope.hjCity.value//男方省市名
			appData.man_ss_value = $scope.hjCity.key//男方省市值
			appData.man_ss_required = ($scope.hjCity.value == "上海市") ? true:""

			appData.lady_ss_name = $scope.jzCity.value//女方省市名
			appData.lady_ss_value = $scope.jzCity.key//女方省市名
			appData.lady_ss_required = ($scope.jzCity.value == "上海市") ? true:""
			$scope.locationList = [
				appData.hjCountykey || "",
				appData.jzCountykey || ""
			]
			appData.locationList = filterByInfoLocation($scope.marriageLocationList,$scope.locationList)
			$state.go("certification");
			$scope.isLoading = true;
		}
		
	}
	$scope.prevStep = function() {
		$state.go("guideline");
	}
});
app.controller('divorceInfo', function($state, $scope, appData, $location, appFactory, $timeout, $rootScope) {
	$scope.funName ="离魂登记预约";
	appData.isUpload = [];
	appData.listImg = [];
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isShanghai1=true;
	$scope.isShanghai2=true;
	$scope.marriageLocationList = marriageAdressInfoList;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//个人信息
	$scope.hjCityList = filterByName($rootScope.allList,'');
	$scope.jzCityList = filterByName($rootScope.allList,'');
	$scope.centerProvinceList = $rootScope.ShangHaiList;
	//监听省市 区域变化
	$timeout(function() {
		selectBlur();
		$scope.$watch("hjCity", function(val) {
			if(val) {
				if(val.key =="310000"){
					$scope.isShanghai1=false;
					$scope.hjCountyList = $rootScope.ShangHaiList;
				}else{
					$scope.isShanghai1=true;
					appData.man_qx_name =""//男方区县名
					appData.man_qx_value =""//男方区县值
					appData.man_jd_name =""//男方街道名
					appData.man_jd_value =""//男方街道值
				}			
			}
		});
		$scope.$watch("hjCounty", function(val) {
			if(val) {
				$scope.hjStreetList = filterByName($rootScope.ShangHaiStreetList, val.key);
				appData.hjCountykey = val.key
				appData.man_qx_name = val.value//男方区县名
				appData.man_qx_value = val.key //男方区县值
			}
		});
		$scope.$watch("hjStreet", function(val) {
			if(val) {
				appData.man_jd_name = val.value //女方街道名
				appData.man_jd_value = val.key//女方街道值
			}
		});
		$scope.$watch("jzCity", function(val) {
			if(val) {
				if(val.key =="310000"){
					$scope.isShanghai2=false;
					$scope.jzCountyList = $rootScope.ShangHaiList;
				}else{
					$scope.isShanghai2=true;
					appData.lady_qx_name =""//女方区县名
					appData.lady_qx_value =""//女方区县值
					appDatalady_jd_name =""//女方街道名
					appData.lady_jd_value =""//女方街道值
				}
				
			}
		});
		$scope.$watch("jzCounty", function(val) {
			if(val) {
				$scope.jzStreetList = filterByName($rootScope.ShangHaiStreetList, val.key);
				appData.jzCountykey = val.key
				appData.lady_qx_name = val.value //女方区县名
				appData.lady_qx_value = val.key//女方区县值
			}
		});
		$scope.$watch("jzStreet", function(val) {
			if(val) {
				appData.lady_jd_name = val.value //女方街道名
				appData.lady_jd_value = val.key//女方街道值
			}
		});
		$scope.$watch("centerProvince", function(val) {
			if(val) {
				$scope.centerStreetList = filterByName($rootScope.centerList, val.key);
			}
		});
		$scope.$watch("centerStreet", function(val) {
			if(val) {
				 appData.departCode = val.key;
			}
		});
	}, 100);
	$scope.nextStep = function() {
		var condFlag = false;
		$scope.isLoading = true;
		if($scope.isShanghai1 && $scope.isShanghai2){
		$scope.isAlert = true;
		$scope.msg = '双方至少有一方为上海户籍';
		}else{
			appData.man_ss_name = $scope.hjCity.value//男方省市名
			appData.man_ss_value = $scope.hjCity.key//男方省市值
			appData.man_ss_required = ($scope.hjCity.value == "上海市") ? true:""

			appData.lady_ss_name = $scope.jzCity.value//女方省市名
			appData.lady_ss_value = $scope.jzCity.key//女方省市名
			appData.lady_ss_required = ($scope.jzCity.value == "上海市") ? true:""
			$scope.locationList = [
				appData.hjCountykey || "",
				appData.jzCountykey || ""
			]
			appData.locationList = filterByInfoLocation($scope.marriageLocationList,$scope.locationList)
			$state.go("certification");
			$scope.isLoading = true;
		}
		
	}
	$scope.prevStep = function() {
		$state.go("guideline");
	}
});
app.controller('certification', function($state, $scope, appData,$timeout) {
	$scope.funName ="婚姻登记预约";
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoding = false;
	$scope.marriageType=appData.marriageType;
	$scope.addressList = appData.locationList;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//办证机关给出相应补充提示
	$timeout(function() {
		selectBlur();
		$scope.$watch("ddress", function(val) {
			if(val) {
					$scope.desc = val.desc;
					appData.organId = val.id
					appData.organName = val.name	
			}
		});
	}, 100);
	$scope.nextStep = function() {
		var condFlag = false;
		if($scope.marriageType == "jhdjyy"){
			$state.go("appointmentTime");
		}else{
			$state.go("divAppointmentTime");
		}
		
		$scope.isLoading = true;
	}
	$scope.prevStep = function() {
		$state.go("");
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
app.controller('appointmentTime', function($state, $scope, appData, $location,appFactory, $timeout, $rootScope) {
	$scope.funName ="结婚登记预约";
	appData.isUpload = [];
	appData.listImg = [];
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isSelectDate = true;
	$scope.json1 = {
		"organId": appData.organId
	}
	//民政局结婚预约日期
	$scope.getDayTime = function() {
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + '/selfapi/CivilMarriage/mzReserveDateQueryNew.do',
			// url: "http://localhost:8080/ac-self/selfapi/CivilMarriage/mzReserveDateQueryNew.do",
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data: {
				"json" : JSON.stringify($scope.json1)
			},
			success: function(dataJson) {
				$scope.appointmentDateList = dataJson.date;
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	$scope.getDayTime();

	// 民政局结婚预约时间段
	$scope.getOtherTime = function(selectDate) {
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + '/selfapi/CivilMarriage/mzReserveTimeQuery.do',
			// url: "http://localhost:8080/ac-self/selfapi/CivilMarriage/mzReserveTimeQuery.do",
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data: {
				json :  JSON.stringify(
					{
						"organId": appData.organId,
						"selectDate":selectDate
					}
				)
			},
			success: function(dataJson) {
				$scope.appointmentTimeList = filterBySeatFull(dataJson.result,"0");
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//监听选择的预约日期
		$timeout(function() {
			selectBlur();
			$scope.$watch("appDate", function(val) {
				if(val) {
					 if(val=="请选择预约日期"){
						$scope.isSelectDate = true;
					 }else{
						$scope.isSelectDate = false;
					 }	
					 $scope.getOtherTime(val);
				}
			});
			$scope.$watch("appTime", function(val) {
				if(val) {
					$scope.seatNumber = val.seatNum;
				}
			});
		}, 100);

	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.appDate)) {
				$scope.isAlert = true;
				$scope.msg = "请选择预约日期";
				return;
			}
			if(isBlank($scope.appTime)) {
				$scope.isAlert = true;
				$scope.msg = "请选择预约时间段";
				return;
			}					
		} while (condFlag);
		condFlag = true;
		appData.selectDateTimeText = $scope.appTime.dateTime;
		appData.selectDate = $scope.appDate
		appData.selectDateTime = $scope.appTime.dateTimeId
		$state.go("submitInfo");
		$scope.isLoading = true;
	}
	$scope.prevStep = function() {
		$state.go("");
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
app.controller('divAppointmentTime', function($state, $scope, appData, $location,appFactory, $timeout, $rootScope) {
	$scope.funName ="离婚登记预约";
	appData.isUpload = [];
	appData.listImg = [];
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isSelectDate = true;
	$scope.json1 = {
		"organId": appData.organId
	}
	//民政局离婚预约日期
	$scope.getDayTime = function() {
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + '/selfapi/CivilMarriage/mzLhReserveDateQueryNew.do',
			// url: "http://localhost:8080/ac-self/selfapi/CivilMarriage/mzLhReserveDateQueryNew.do",
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data: {
				"json" : JSON.stringify($scope.json1)
			},
			success: function(dataJson) {
				$scope.appointmentDateList = dataJson.date;
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	$scope.getDayTime();

	// 民政局离婚预约时间段
	$scope.getOtherTime = function(selectDate) {
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + '/selfapi/CivilMarriage/mzLhReserveTimeQuery.do',
			// url: "http://localhost:8080/ac-self/selfapi/CivilMarriage/mzLhReserveTimeQuery.do",
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data: {
				json :  JSON.stringify(
					{
						"organId": appData.organId,
						"selectDate":selectDate
					}
				)
			},
			success: function(dataJson) {
				$scope.appointmentTimeList = filterBySeatFull(dataJson.result,"0");
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//监听选择的预约日期
		$timeout(function() {
			selectBlur();
			$scope.$watch("appDate", function(val) {
				if(val) {
					 if(val=="请选择预约日期"){
						$scope.isSelectDate = true;
					 }else{
						$scope.isSelectDate = false;
					 }	
					 $scope.getOtherTime(val);
				}
			});
			$scope.$watch("appTime", function(val) {
				if(val) {
					$scope.seatNumber = val.seatNum;
				}
			});
		}, 100);

	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.appDate)) {
				$scope.isAlert = true;
				$scope.msg = "请选择预约日期";
				return;
			}
			if(isBlank($scope.appTime)) {
				$scope.isAlert = true;
				$scope.msg = "请选择预约时间段";
				return;
			}					
		} while (condFlag);
		condFlag = true;
		appData.selectDateTimeText = $scope.appTime.dateTime;
		appData.selectDate = $scope.appDate
		appData.selectDateTime = $scope.appTime.dateTimeId
		$state.go("divSubmitInfo");
		$scope.isLoading = true;
	}
	$scope.prevStep = function() {
		$state.go("");
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
app.controller('submitInfo', function($state, $scope, appData, $location, appFactory, $timeout, $rootScope) {
	$scope.pageName = "结婚登记预约";
	$scope.nextText = "提交";
	$scope.concel = "false";
	$scope.isAlert = false;
	if(((parseInt(appData.licenseNumber.substring(16, 17)) % 2) == 0)){
		$scope.ladyName = appData.licenseName;
		$scope.ladyCardNumber = appData.licenseNumber
		$scope.ladyBirthDay = (appData.licenseNumber).substring(6, 10) + "-" + (appData.licenseNumber).substring(10, 12) + "-" + (appData.licenseNumber).substring(12, 14);
	}else{
		$scope.manName = appData.licenseName;
		$scope.manCardNumber = appData.licenseNumber;
		$scope.manBirthDay = (appData.licenseNumber).substring(6, 10) + "-" + (appData.licenseNumber).substring(10, 12) + "-" + (appData.licenseNumber).substring(12, 14);
	}
	$scope.countryList = countryList;//国籍字典
	$scope.cardList = cardList;//证件类型字典
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
	});
	$scope.nextStep = function() {
		var condFlag = false;
		$scope.manBirthDay = document.getElementById('manBirthDay').value;
		$scope.ladyBirthDay = document.getElementById('ladyBirthDay').value;
		do {
			if(isBlank($scope.manCountry)) {
				$scope.isAlert = true;
				$scope.msg = "请选择男方国籍";
				return;
			}
			if(isBlank($scope.ladyCountry)) {
				$scope.isAlert = true;
				$scope.msg = "请选择女方国籍";
				return;
			}
			if(isBlank($scope.manName)) {
				$scope.isAlert = true;
				$scope.msg = "请填写男方姓名";
				return;
			}
			if(isBlank($scope.ladyName)) {
				$scope.isAlert = true;
				$scope.msg = "请填写女方姓名";
				return;
			}
			if(isBlank($scope.manStMobile)) {
				$scope.isAlert = true;
				$scope.msg = "请填写男方手机号";
				return;
			}
			if(!isPhoneAvailable($scope.manStMobile)) {
				$scope.isAlert = true;
				$scope.msg = "请检查男方手机号是否正确";
				return;
			}
			if(isBlank($scope.ladyStMobile)) {
				$scope.isAlert = true;
				$scope.msg = "请填写女方手机号";
				return;
			}
			if(!isPhoneAvailable($scope.ladyStMobile)) {
				$scope.isAlert = true;
				$scope.msg = "请检查女方手机号是否正确";
				return;
			}
			if(isBlank($scope.manCardType)) {
				$scope.isAlert = true;
				$scope.msg = "请选择男方证件类型";
				return;
			}
			if(isBlank($scope.ladyCardType)) {
				$scope.isAlert = true;
				$scope.msg = "请选择女方证件类型";
				return;
			}
			if(isBlank($scope.manCardNumber)) {
				$scope.isAlert = true;
				$scope.msg = "请填写男方证件编号！";
				return;
			}
			if(!isLength($scope.manCardNumber)) {
				$scope.isAlert = true;
				$scope.msg = "请检查男方证件编号是否正确！";
				return;
			}
			if(isBlank($scope.ladyCardNumber)) {
				$scope.isAlert = true;
				$scope.msg = "请填写女方证件编号";
				return;
				
			}
			if(!isLength($scope.ladyCardNumber)) {
				$scope.isAlert = true;
				$scope.msg = "请检查男方证件编号是否正确！";
				return;
			}
			if(isBlank($scope.manBirthDay)) {
				$scope.isAlert = true;
				$scope.msg = "请选择男方出生日期";
				return;
			}
			if(isBlank($scope.ladyBirthDay)) {
				$scope.isAlert = true;
				$scope.msg = "请选择女方出生日期";
				return;
			}	
			if(isBlank($scope.manAddress)) {
				$scope.isAlert = true;
				$scope.msg = "请填写男方户籍所在地";
				return;
			}
			if(isBlank($scope.ladyAddress)) {
				$scope.isAlert = true;
				$scope.msg = "请填写女方户籍所在地";
				return;
			}							
		} while (condFlag);
		//提交参数集合
		condFlag = true;
		$scope.isLoading = true;
	
		//判断涉外婚姻接口
		if($scope.manCountry.val =="中国" &&  $scope.ladyCountry.val=="中国"){
			console.log("内地结婚预约")
			appData.funName = "内地结婚预约";
			$scope.applicationInfo = {
				"data": {
				//  "access_token":appData.token,
				//  "departCode": appData.departCode || "",
				"access_token":"",
				"departCode":"",
				 "itemCode":"310150127000-01",
				  "info":{
						  "lady_nation": {
							  "name": "",
							  "value": ""
						  },
						  "lady_ss": {
							  "name": appData.lady_ss_name,
							  "value": appData.lady_ss_value,
							  "required": appData.lady_ss_required
						  },
						  "man_address":$scope.manAddress,
						  "man_name": $scope.manName,
						  "org": {
							  "name": appData.organName,
							  "value": appData.organId
						  },
						  "lady_marriage": {
							  "name": "",
							  "value": ""
						  },
						  "lady_nationality": {
							  "name": $scope.ladyCountry.val,
							  "value": $scope.ladyCountry.key
						  },
						  "lady_qx": {
							  "name": appData.lady_qx_name,
							  "value": appData.lady_qx_value
						  },
						  "selectDateTimeText":appData.selectDateTimeText,
						  "man_nation": {
							  "name": "",
							  "value": ""
						  },
						  "man_cardType": {
							  "name": $scope.manCardType.val,
							  "value": $scope.manCardType.key
						  },
						  "man_cardNumber": $scope.manCardNumber,
						  "lady_cardType": {
							  "name": $scope.ladyCardType.val,
							  "value": $scope.ladyCardType.key
						  },
						  "lady_cardNumber":  $scope.ladyCardNumber,
						  "man_nationality": {
							  "name": $scope.manCountry.val,
							  "value": $scope.manCountry.key
						  },
						  "man_marriage": {
							  "name": "",
							  "value": ""
						  },
						  "man_mobilePhone": $scope.manStMobile,
						  "lady_profession": {
							  "name": "",
							  "value": ""
						  },
						  "selectData": appData.selectDate,
						  "lady_education": {
							  "name": "",
							  "value": ""
						  },
						  "selectDateTime": appData.selectDateTime,
						  "lady_mobilePhone": $scope.ladyStMobile,
						  "lady_jd": {
							  "name": appData.lady_jd_name,
							  "value": appData.lady_jd_value
						  },
						  "man_education": {
							  "name": "",
							  "value": ""
						  },
						  "lady_address": $scope.ladyAddress,
						  "man_birthday": {
							//   "value": $scope.manBirthDay,
							//   "unix": Date.parse(new Date($scope.manBirthDay))
							"value":$scope.ladyBirthDay,
							"unix": Date.parse(new Date($scope.ladyBirthDay))
						  },
						  "man_qx": {
							  "name": appData.man_qx_name,
							  "value": appData.man_qx_value
						  },
						  "man_jd": {
							  "name": appData.man_jd_name,
							  "value": appData.man_jd_value
						  },
						  "lady_name": $scope.ladyName,
						  "man_profession": {
							  "name": "",
							  "value": ""
						  },
						  "checkbox": {
							  "items": ["1"],
							  "names": ["已阅"],
							  "value": "1"
						  },
						  "lady_birthday": {
							//   "value": $scope.ladyBirthDay,
							//   "unix": Date.parse(new Date($scope.ladyBirthDay))
							"value":$scope.manBirthDay,
							"unix": Date.parse(new Date($scope.manBirthDay))
						  },
						  "man_ss": {
							  "name": appData.man_ss_name,
							  "value": appData.man_ss_value,
							  "required": appData.man_ss_required
						  }
					  }
			  
			  }
		}
			$.ajax({
				type: "post",
				url: $.getConfigMsg.preUrlSelf + "/selfapi/CivilMarriage/saveMainlandMarriageRegistrationAppointmentNew.do",
				// url: "http://localhost:8080/ac-self/selfapi/CivilMarriage/saveMainlandMarriageRegistrationAppointmentNew.do",
				dataType: "json",
				data: {
					json: JSON.stringify($scope.applicationInfo)
				},
				success: function(dataJson) {
					console.log(dataJson);
					$scope.isLoading = false;
					if(dataJson.isSuccess == true) {
						appData.applyNo = dataJson.applyNo;//得到事项返回的办理编码
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
		}else{
			console.log("涉外结婚预约")
			appData.funName = "涉外结婚预约";
			$scope.applicationInfo = {
				"data": {
				//  "access_token":appData.token,
				//  "departCode": appData.departCode || "",
				"access_token":"",
				"departCode":"",
				 "itemCode":"310100258000-01",
				  "info":{
						  "lady_nation": {
							  "name": "",
							  "value": ""
						  },
						  "lady_ss": {
							  "name": appData.lady_ss_name,
							  "value": appData.lady_ss_value,
							  "required": appData.lady_ss_required
						  },
						  "man_address":$scope.manAddress,
						  "man_name": $scope.manName,
						  "org": {
							  "name": appData.organName,
							  "value": appData.organId
						  },
						  "lady_marriage": {
							  "name": "",
							  "value": ""
						  },
						  "lady_nationality": {
							  "name": $scope.ladyCountry.val,
							  "value": $scope.ladyCountry.key
						  },
						  "lady_qx": {
							  "name": appData.lady_qx_name,
							  "value": appData.lady_qx_value
						  },
						  "selectDateTimeText":appData.selectDateTimeText,
						  "man_nation": {
							  "name": "",
							  "value": ""
						  },
						  "man_cardType": {
							  "name": $scope.manCardType.val,
							  "value": $scope.manCardType.key
						  },
						  "man_cardNumber": $scope.manCardNumber,
						  "lady_cardType": {
							  "name": $scope.ladyCardType.val,
							  "value": $scope.ladyCardType.key
						  },
						  "lady_cardNumber":  $scope.ladyCardNumber,
						  "man_nationality": {
							  "name": $scope.manCountry.val,
							  "value": $scope.manCountry.key
						  },
						  "man_marriage": {
							  "name": "",
							  "value": ""
						  },
						  "man_mobilePhone": $scope.manStMobile,
						  "lady_profession": {
							  "name": "",
							  "value": ""
						  },
						  "selectData": appData.selectDate,
						  "lady_education": {
							  "name": "",
							  "value": ""
						  },
						  "selectDateTime": appData.selectDateTime,
						  "lady_mobilePhone": $scope.ladyStMobile,
						  "lady_jd": {
							  "name": appData.lady_jd_name,
							  "value": appData.lady_jd_value
						  },
						  "man_education": {
							  "name": "",
							  "value": ""
						  },
						  "lady_address": $scope.ladyAddress,
						  "man_birthday": {
							//   "value": $scope.manBirthDay,
							//   "unix": Date.parse(new Date($scope.manBirthDay))
							"value":$scope.manBirthDay,
							"unix": Date.parse(new Date($scope.manBirthDay))
						  },
						  "man_qx": {
							  "name": appData.man_qx_name,
							  "value": appData.man_qx_value
						  },
						  "man_jd": {
							  "name": appData.man_jd_name,
							  "value": appData.man_jd_value
						  },
						  "lady_name": $scope.ladyName,
						  "man_profession": {
							  "name": "",
							  "value": ""
						  },
						  "checkbox": {
							  "items": ["1"],
							  "names": ["已阅"],
							  "value": "1"
						  },
						  "lady_birthday": {
							//   "value": $scope.ladyBirthDay,
							//   "unix": Date.parse(new Date($scope.ladyBirthDay))
							"value":$scope.ladyBirthDay,
							"unix": Date.parse(new Date($scope.ladyBirthDay))
						  },
						  "man_ss": {
							  "name": appData.man_ss_name,
							  "value": appData.man_ss_value,
							  "required": appData.man_ss_required
						  }
					  }
			  
			  }
		}
			$.ajax({
				type: "post",
				url: $.getConfigMsg.preUrlSelf + "/selfapi/CivilMarriage/saveMarriageRegistrationInvolvingHongKongMacaoTaiwanOverseasChineseForeignersNew.do",
				// url: "http://localhost:8080/ac-self/selfapi/CivilMarriage/saveMarriageRegistrationInvolvingHongKongMacaoTaiwanOverseasChineseForeignersNew.do",
				dataType: "json",
				data: {
					json: JSON.stringify($scope.applicationInfo)
				},
				success: function(dataJson) {
					console.log(dataJson);
					$scope.isLoading = false;
					if(dataJson.isSuccess == true) {
						appData.applyNo = dataJson.applyNo;//得到事项返回的办理编码
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
		console.log(JSON.stringify($scope.applicationInfo));

	}
});
app.controller('divSubmitInfo', function($state, $scope, appData, $location, appFactory, $timeout, $rootScope) {
	$scope.pageName = "离婚登记预约";
	$scope.nextText = "提交";
	$scope.concel = "false";
	$scope.isAlert = false;
	if(((parseInt(appData.licenseNumber.substring(16, 17)) % 2) == 0)){
		$scope.ladyName = appData.licenseName;
		$scope.ladyCardNumber = appData.licenseNumber
		$scope.ladyBirthDay = (appData.licenseNumber).substring(6, 10) + "-" + (appData.licenseNumber).substring(10, 12) + "-" + (appData.licenseNumber).substring(12, 14);
	}else{
		$scope.manName = appData.licenseName;
		$scope.manCardNumber = appData.licenseNumber;
		$scope.manBirthDay = (appData.licenseNumber).substring(6, 10) + "-" + (appData.licenseNumber).substring(10, 12) + "-" + (appData.licenseNumber).substring(12, 14);
	}
	$scope.countryList = countryList;//国籍字典
	$scope.cardList = cardList;//证件类型字典
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
	});
	$scope.nextStep = function() {	
		var condFlag = false;
		$scope.manBirthDay = document.getElementById('manBirthDay').value;
		$scope.ladyBirthDay = document.getElementById('ladyBirthDay').value;
		do {
			if(isBlank($scope.manCountry)) {
				$scope.isAlert = true;
				$scope.msg = "请选择男方国籍";
				return;
			}
			if(isBlank($scope.ladyCountry)) {
				$scope.isAlert = true;
				$scope.msg = "请选择女方国籍";
				return;
			}
			if(isBlank($scope.manName)) {
				$scope.isAlert = true;
				$scope.msg = "请填写男方姓名";
				return;
			}
			if(isBlank($scope.ladyName)) {
				$scope.isAlert = true;
				$scope.msg = "请填写女方姓名";
				return;
			}
			if(isBlank($scope.manStMobile)) {
				$scope.isAlert = true;
				$scope.msg = "请填写男方手机号";
				return;
			}
			if(!isPhoneAvailable($scope.manStMobile)) {
				$scope.isAlert = true;
				$scope.msg = "请检查男方手机号是否正确";
				return;
			}
			if(isBlank($scope.ladyStMobile)) {
				$scope.isAlert = true;
				$scope.msg = "请填写女方手机号";
				return;
			}
			if(!isPhoneAvailable($scope.ladyStMobile)) {
				$scope.isAlert = true;
				$scope.msg = "请检查女方手机号是否正确";
				return;
			}
			if(isBlank($scope.manCardType)) {
				$scope.isAlert = true;
				$scope.msg = "请选择男方证件类型";
				return;
			}
			if(isBlank($scope.ladyCardType)) {
				$scope.isAlert = true;
				$scope.msg = "请选择女方证件类型";
				return;
			}
			if(isBlank($scope.manCardNumber)) {
				$scope.isAlert = true;
				$scope.msg = "请填写男方证件编号！";
				return;
			}
			if(!isLength($scope.manCardNumber)) {
				$scope.isAlert = true;
				$scope.msg = "请检查男方证件编号是否正确！";
				return;
			}
			if(isBlank($scope.ladyCardNumber)) {
				$scope.isAlert = true;
				$scope.msg = "请填写女方证件编号";
				return;
				
			}
			if(!isLength($scope.ladyCardNumber)) {
				$scope.isAlert = true;
				$scope.msg = "请检查男方证件编号是否正确！";
				return;
			}
			if(isBlank($scope.manBirthDay)) {
				$scope.isAlert = true;
				$scope.msg = "请选择男方出生日期";
				return;
			}
			if(isBlank($scope.ladyBirthDay)) {
				$scope.isAlert = true;
				$scope.msg = "请选择女方出生日期";
				return;
			}	
			if(isBlank($scope.manAddress)) {
				$scope.isAlert = true;
				$scope.msg = "请填写男方户籍所在地";
				return;
			}
			if(isBlank($scope.ladyAddress)) {
				$scope.isAlert = true;
				$scope.msg = "请填写女方户籍所在地";
				return;
			}
			if(isBlank($scope.divReasons)) {
				$scope.isAlert = true;
				$scope.msg = "请填写离婚原因";
				return;
			}					
		} while (condFlag);
		//提交参数集合
		condFlag = true;
		$scope.isLoading = true;
		

		//判断涉外婚姻接口
		if($scope.manCountry.val =="中国" &&  $scope.ladyCountry.val=="中国"){
			console.log("内地离婚预约")
			appData.funName = "内地离婚预约";
			$scope.applicationInfo = {
				"data": {
				//  "access_token":appData.token,
				//  "departCode": appData.departCode || "",
				"access_token":"",
				"departCode":"",
				 "itemCode":"310150127000-03",
				  "info":{
						  "lady_nation": {
							  "name": "",
							  "value": ""
						  },
						  "lady_ss": {
							  "name": appData.lady_ss_name,
							  "value": appData.lady_ss_value,
							  "required": appData.lady_ss_required
						  },
						  "man_address":$scope.manAddress,
						  "man_name": $scope.manName,
						  "org": {
							  "name": appData.organName,
							  "value": appData.organId
						  },
						  "lady_marriage": {
							  "name": "",
							  "value": ""
						  },
						  "lady_nationality": {
							  "name": $scope.ladyCountry.val,
							  "value": $scope.ladyCountry.key
						  },
						  "lady_qx": {
							  "name": appData.lady_qx_name,
							  "value": appData.lady_qx_value
						  },
						  "selectDateTimeText":appData.selectDateTimeText,
						  "man_nation": {
							  "name": "",
							  "value": ""
						  },
						  "man_cardType": {
							  "name": $scope.manCardType.val,
							  "value": $scope.manCardType.key
						  },
						  "man_cardNumber": $scope.manCardNumber,
						  "lady_cardType": {
							  "name": $scope.ladyCardType.val,
							  "value": $scope.ladyCardType.key
						  },
						  "lady_cardNumber":  $scope.ladyCardNumber,
						  "man_nationality": {
							  "name": $scope.manCountry.val,
							  "value": $scope.manCountry.key
						  },
						  "man_marriage": {
							  "name": "",
							  "value": ""
						  },
						  "man_mobilePhone": $scope.manStMobile,
						  "lady_profession": {
							  "name": "",
							  "value": ""
						  },
						  "selectData": appData.selectDate,
						  "lady_education": {
							  "name": "",
							  "value": ""
						  },
						  "selectDateTime": appData.selectDateTime,
						  "lady_mobilePhone": $scope.ladyStMobile,
						  "lady_jd": {
							  "name": appData.lady_jd_name,
							  "value": appData.lady_jd_value
						  },
						  "man_education": {
							  "name": "",
							  "value": ""
						  },
						  "lady_address": $scope.ladyAddress,
						  "man_birthday": {
							//   "value": $scope.manBirthDay,
							//   "unix": Date.parse(new Date($scope.manBirthDay))
							"value":$scope.ladyBirthDay,
							"unix": Date.parse(new Date($scope.ladyBirthDay))
						  },
						  "man_qx": {
							  "name": appData.man_qx_name,
							  "value": appData.man_qx_value
						  },
						  "man_jd": {
							  "name": appData.man_jd_name,
							  "value": appData.man_jd_value
						  },
						  "lady_name": $scope.ladyName,
						  "man_profession": {
							  "name": "",
							  "value": ""
						  },
						  "checkbox": {
							  "items": ["1"],
							  "names": ["已阅"],
							  "value": "1"
						  },
						  "lady_birthday": {
							//   "value": $scope.ladyBirthDay,
							//   "unix": Date.parse(new Date($scope.ladyBirthDay))
							"value":$scope.manBirthDay,
							"unix": Date.parse(new Date($scope.manBirthDay))
						  },
						  "man_ss": {
							  "name": appData.man_ss_name,
							  "value": appData.man_ss_value,
							  "required": appData.man_ss_required
						  }
					  }
			  
			  }
		}
			$.ajax({
				type: "post",
				url: $.getConfigMsg.preUrlSelf + "/selfapi/CivilMarriage/saveAppointmentDivorceRegistrationMainlandNew.do",
				// url: "http://localhost:8080/ac-self/selfapi/CivilMarriage/saveAppointmentDivorceRegistrationMainlandNew.do",
				dataType: "json",
				data: {
					json: JSON.stringify($scope.applicationInfo)
				},
				success: function(dataJson) {
					console.log(dataJson);
					$scope.isLoading = false;
					if(dataJson.isSuccess == true) {
						appData.applyNo = dataJson.applyNo;//得到事项返回的办理编码
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
		}else{
			console.log("涉外离婚预约")
			appData.funName = "涉外离婚预约";
			$scope.applicationInfo = {
				"data": {
				//  "access_token":appData.token,
				//  "departCode": appData.departCode || "",
				"access_token":"",
				"departCode":"",
				 "itemCode":"310100258000-02",
				  "info":{
						  "lady_nation": {
							  "name": "",
							  "value": ""
						  },
						  "lady_ss": {
							  "name": appData.lady_ss_name,
							  "value": appData.lady_ss_value,
							  "required": appData.lady_ss_required
						  },
						  "man_address":$scope.manAddress,
						  "man_name": $scope.manName,
						  "org": {
							  "name": appData.organName,
							  "value": appData.organId
						  },
						  "lady_marriage": {
							  "name": "",
							  "value": ""
						  },
						  "lady_nationality": {
							  "name": $scope.ladyCountry.val,
							  "value": $scope.ladyCountry.key
						  },
						  "lady_qx": {
							  "name": appData.lady_qx_name,
							  "value": appData.lady_qx_value
						  },
						  "selectDateTimeText":appData.selectDateTimeText,
						  "man_nation": {
							  "name": "",
							  "value": ""
						  },
						  "man_cardType": {
							  "name": $scope.manCardType.val,
							  "value": $scope.manCardType.key
						  },
						  "man_cardNumber": $scope.manCardNumber,
						  "lady_cardType": {
							  "name": $scope.ladyCardType.val,
							  "value": $scope.ladyCardType.key
						  },
						  "lady_cardNumber":  $scope.ladyCardNumber,
						  "man_nationality": {
							  "name": $scope.manCountry.val,
							  "value": $scope.manCountry.key
						  },
						  "man_marriage": {
							  "name": "",
							  "value": ""
						  },
						  "man_mobilePhone": $scope.manStMobile,
						  "lady_profession": {
							  "name": "",
							  "value": ""
						  },
						  "selectData": appData.selectDate,
						  "lady_education": {
							  "name": "",
							  "value": ""
						  },
						  "selectDateTime": appData.selectDateTime,
						  "lady_mobilePhone": $scope.ladyStMobile,
						  "lady_jd": {
							  "name": appData.lady_jd_name,
							  "value": appData.lady_jd_value
						  },
						  "man_education": {
							  "name": "",
							  "value": ""
						  },
						  "lady_address": $scope.ladyAddress,
						  "man_birthday": {
							"value":$scope.manBirthDay,
							"unix": Date.parse(new Date($scope.manBirthDay))
						  },
						  "man_qx": {
							  "name": appData.man_qx_name,
							  "value": appData.man_qx_value
						  },
						  "man_jd": {
							  "name": appData.man_jd_name,
							  "value": appData.man_jd_value
						  },
						  "lady_name": $scope.ladyName,
						  "man_profession": {
							  "name": "",
							  "value": ""
						  },
						  "checkbox": {
							  "items": ["1"],
							  "names": ["已阅"],
							  "value": "1"
						  },
						  "lady_birthday": {
							"value":$scope.ladyBirthDay,
							"unix": Date.parse(new Date($scope.ladyBirthDay))
						  },
						  "man_ss": {
							  "name": appData.man_ss_name,
							  "value": appData.man_ss_value,
							  "required": appData.man_ss_required
						  }
					  }
			  
			  }
		}
			$.ajax({
				type: "post",
				url: $.getConfigMsg.preUrlSelf + "/selfapi/CivilMarriage/saveDivorceRegistrationInvolvingHongKongMacaoTaiwanOverseasChineseForeignersNew.do",
				// url: "http://localhost:8080/ac-self/selfapi/CivilMarriage/saveDivorceRegistrationInvolvingHongKongMacaoTaiwanOverseasChineseForeignersNew.do",
				dataType: "json",
				data: {
					json: JSON.stringify($scope.applicationInfo)
				},
				success: function(dataJson) {
					console.log(dataJson);
					$scope.isLoading = false;
					if(dataJson.isSuccess == true) {
						appData.applyNo = dataJson.applyNo;//得到事项返回的办理编码
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
		console.log(JSON.stringify($scope.applicationInfo));

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
app.controller("submit", function($scope,appData, $location, $state, $rootScope, $interval, $timeout) {
	$scope.funName = appData.funName;
	$scope.applyNo = appData.applyNo;
	//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: $scope.funName,
			Number: $scope.applyNo,
		}
	}
	recordUsingHistory('民政服务', '办理', $scope.funName, appData.licenseName, appData.licenseNumber, appData.stMobile, $scope.applyNo, JSON.stringify($scope.jsonStr));
	//行为分析(办理)
	trackEventForAffairs($scope.applyNo, $scope.funName, "上海市民政局", appData.licenseName, appData.licenseNumber, appData.stMobile);
	var date = new Date();
	var month = date.getMonth() + 1;
	$scope.date = date.getFullYear() + "年" + month + "月" + date.getDate() + "日" + date.getHours() + "时" + date.getMinutes() + "分";
	$scope.nextText = "返回首页";
	$scope.nextStep = function() {
		$.device.GoHome();
	}
});