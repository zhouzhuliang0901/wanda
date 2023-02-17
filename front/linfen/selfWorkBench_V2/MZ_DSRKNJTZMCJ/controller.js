app.controller("main", function($scope, $state, appData, $sce, appFactory) {
	$scope.stuffName = [{
		"stuffName": "低收入困难家庭证明出具",
		"type": "dsrkn",
		"ywlx": "MZ0109_02",
		"img": "../libs/common/images/newIcon/JG.png",
	}];
	$scope.operation = $scope.stuffName[0].stuffName;
	$scope.choiceType = function(type, name, ywlx) {
		appData.funName = name;
		appData.type = type;
		appData.ywlx = ywlx;
		$state.go("loginType");
	}
//	$scope.isScroll = function() {
//		new iScroll("wrapper", {
//			vScrollbar: true,
//			hScrollbar: false,
//			bounce: true,
//			click: true,
//			taps: true,
//			hScroll: false,
//			preventDefault: false,
//			checkDOMChanges: true,
//		});
//	};
//	$scope.isScroll();
});
app.controller('loginType', function($state, $scope, appData, $location) {
	$scope.operation = "请选择登录方式";
	if(appData.type == "" || appData.type == undefined) {
		appData.type = $location.search().type;
	}
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory) {
	if(appData.loginType == 'idcard') {
		$scope.operation = '请刷身份证';
	} else if(appData.loginType == 'cloud') {
		$scope.operation = '请刷随申办';
	} else {
		$scope.operation = '请刷身份证';
	}
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
			$scope.loginType = 'recognition';

//			appData.licenseNumber = '310228198808070818';
//			appData.licenseName = '陈雷';
//			$scope.getTokenSNO(photo, photo);

		} else {
			layer.msg("很抱歉，未获取到相关信息，请重试。");
		}
	}
//	$scope.idcardLogin();

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
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//个人信息
	$scope.isLoading = true;
	$scope.stName = appData.licenseName;
	$scope.stIdCard = appData.licenseNumber;
	$scope.stSex = ((parseInt(appData.licenseNumber.substring(16, 17)) % 2) == 0) ? "女" : "男";
	$scope.dtBirth = (appData.licenseNumber).substring(6, 10) + "-" + (appData.licenseNumber).substring(10, 12) + "-" + (appData.licenseNumber).substring(12, 14);
	$scope.nationList = nations;
	$scope.stMobile = appData.stMobile || "";
	$scope.hjProvinceList = filterByName($rootScope.allList, "");
	$scope.jzProvinceList = filterByName($rootScope.allList, "");
	console.log($scope.hjProvinceList[0]);
	$scope.hjProvince = $scope.hjProvinceList[0];
	$scope.jzProvince = $scope.jzProvinceList[0];
	$scope.$watch("nation", function(val) {
		console.log(val.shortname);
	});

	//监听省市 区域变化
	$timeout(function() {
		$scope.$watch("hjProvince", function(val) {
			if(val) {
				$scope.hjCityList = filterByName($rootScope.allList, val.key);
				$scope.hjCity = $scope.hjCityList[0];
				if(val.value == "上海市") {
					$scope.hjActive = false;
				} else {
					$scope.hjActive = true;
				}
			}
		});
		$scope.$watch("jzProvince", function(val) {
			if(val) {
				$scope.jzCityList = filterByName($rootScope.allList, val.key);
				$scope.jzCity = $scope.jzCityList[0];
				if(val.value == "上海市") {
					$scope.jzActive = false;
				} else {
					$scope.jzActive = true;
				}
			}
		});
		$scope.$watch("hjCity", function(val) {
			if(val) {
				$scope.hjCountyList = $rootScope.ShangHaiList;
				//				$scope.hjCounty = $scope.hjCountyList[0];
			}
		});
		$scope.$watch("hjCounty", function(val) {
			if(val) {
				$scope.hjStreetList = filterByName($rootScope.ShangHaiStreetList, val.key);
				//				$scope.hjStreet = $scope.hjStreetList[0];
			}
		});
		$scope.$watch("jzCity", function(val) {
			if(val) {
				$scope.jzCountyList = $rootScope.ShangHaiList;
				//				$scope.jzCounty = $scope.jzCountyList[0];
			}
		});
		$scope.$watch("jzCounty", function(val) {
			if(val) {
				$scope.jzStreetList = filterByName($rootScope.ShangHaiStreetList, val.key);
				//				$scope.jzStreet = $scope.jzStreetList[0];
			}
		});
	}, 100);

	//获取办事人居民信息
	$scope.getApplicantInfo = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/civilService/getApplicantInfo.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				idType: 1,
				idNo: appData.licenseNumber,
			},
			success: function(dataJson) {
				console.log(dataJson);
				//test赋值
				$scope.isLoading = false;
				$scope.hjProvince = appData.hjProvince || filterByInfo($rootScope.allList, dataJson.data.hjprovince);
				$scope.hjCity = appData.hjCity || filterByInfo($rootScope.allList, dataJson.data.hjcity);
				$scope.hjCounty = appData.hjCounty || filterByInfo($rootScope.ShangHaiList, dataJson.data.hjregion);
				$scope.hjStreet = appData.hjStreet || filterByInfo($rootScope.ShangHaiStreetList, dataJson.data.hjneighborhood);
				$scope.jzProvince = appData.jzProvince || filterByInfo($rootScope.allList, dataJson.data.jzprovince);
				$scope.jzCity = appData.jzCity || filterByInfo($rootScope.allList, dataJson.data.jzcity);
				$scope.jzCounty = appData.jzCounty || filterByInfo($rootScope.ShangHaiList, dataJson.data.jzregion);
				$scope.jzStreet = appData.jzStreet || filterByInfo($rootScope.ShangHaiStreetList, dataJson.data.jzneighborhood);
			},
			error: function(err) {}
		});
	}
	$timeout(function() {
		$scope.getApplicantInfo();
	}, 200);
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
		$scope.getUserInfoByAccessToken();
	}
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.nation)) {
				$scope.isAlert = true;
				$scope.msg = "请选择民族！";
				return;
			}
			if(!isPhoneAvailable($scope.stMobile)) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确联系电话！";
				return;
			}
			if(isBlank($scope.hjProvince)) {
				$scope.isAlert = true;
				$scope.msg = "请选择户籍地址（省）！";
				return;
			}
			if(isBlank($scope.hjCity)) {
				$scope.isAlert = true;
				$scope.msg = "请选择户籍地址（市）！";
				return;
			}
			if($scope.hjActive == false) {
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
			}
			if(isBlank($scope.jzProvince)) {
				$scope.isAlert = true;
				$scope.msg = "请选择居住地址（省）！";
				return;
			}
			if(isBlank($scope.jzCity)) {
				$scope.isAlert = true;
				$scope.msg = "请选择居住地址（市）！";
				return;
			}
			if($scope.jzActive == false) {
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
			}
			if($scope.hjProvince.value != "上海市" && $scope.jzProvince.value != "上海市") {
				$scope.isAlert = true;
				$scope.msg = "此业务申请需满足户籍地、居住地中至少一项为上海的条件";
				return;
			}
		} while (condFlag);
		//提交参数集合
		condFlag = true;
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
		$state.go("infoChoose");
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}
});

app.controller('infoChoose', function($state, $scope, appData, $location, $rootScope, $timeout) {
	$scope.funName = appData.funName;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	if(appData.type == "zdshbz") {
		$scope.licnenseUseList = zdshbzlicenseUse;
	} else if(appData.type == "dsrkn") {
		$scope.licnenseUseList = dsrknlicenseUse;
	} else if(appData.type == "dlshbt") {
		$scope.licnenseUseList = dlshbtlicenseUse;
	} else if(appData.type == "tkry") {
		$scope.licnenseUseList = tkrylicenseUse;
	} else if(appData.type == "ybzc") {
		$scope.licnenseUseList = ybzclicenseUse;
	}
	$scope.countryList = $rootScope.ShangHaiList;
	$scope.change = function(name, index, id) {
		$scope.current = null;
		$scope.current = index;
		$scope.useName = name;
		if(id == "08") {
			$scope.isUnit = false;
			$scope.isShow = false;
		} else if(id == "09") {
			$scope.isUnit = false;
			$scope.isShow = true;
		} else {
			$scope.isUnit = true;
			$scope.isShow = false;
		}
	}
	$timeout(function() {
		$scope.$watch("county", function(val) {
			if(val) {
				$scope.streetList = filterByName($rootScope.ShangHaiStreetList, val.key);
			}
		});
		$scope.$watch("street", function(val) {
			$scope.unitList = [{
				"key": "00000000",
				"value": "住房保障办公室",
				"parent-key": "0000000",
			}]
		})
	}, 100);
	$scope.prevStep = function() {
		$state.go("info");
	}
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if($scope.isUnit == true) {
				if(isBlank($scope.unitName)) {
					$scope.isAlert = true;
					$scope.msg = "请输入发往单位名称！";
					return;
				}
			}
			if(isBlank($scope.useName)) {
				$scope.isAlert = true;
				$scope.msg = "请选择出证用途！";
				return;
			}
			if($scope.isShow == true) {
				if(isBlank($scope.county)) {
					$scope.isAlert = true;
					$scope.msg = "请输入发往单位所属区！";
					return;
				}
				if(isBlank($scope.street)) {
					$scope.isAlert = true;
					$scope.msg = "请输入发往单位所属街道！";
					return;
				}
				if(isBlank($scope.unit)) {
					$scope.isAlert = true;
					$scope.msg = "请输入发往单位名称！";
					return;
				}
			}
		} while (condFlag);
		appData.useLicense = document.getElementsByClassName('in')[0].innerText;
		appData.unitName = $scope.unitName || "";
		if($scope.isShow == true) {
			appData.county = $scope.county;
			appData.street = $scope.street;
			appData.unit = $scope.unit.value;
		}
		$state.go("pickUpMethod");
	}
});
app.controller('pickUpMethod', function($state, $scope, appData, $location, $rootScope, $timeout) {
	$scope.funName = appData.funName;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.express = "";
	$scope.isLoading = false;
	$scope.center = "";
	$scope.nextText = "提交";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//取件方式
	$scope.methodList = pickUpMethod;
	$scope.centerProvinceList = $rootScope.ShangHaiList;
	$scope.provinceList = filterByName($rootScope.allList, "");
	//个人信息
	$scope.stReceiptName = appData.licenseName;
	$scope.stReceiptMobile = appData.stMobile;
	//物流方式
	$scope.expressMethodList = [{
		"id": "01",
		"name": "EMS"
	}]
	$scope.change = function(name, index, id) {
		$scope.current = null;
		$scope.current = index;
		$scope.useName = name;
		if(id == "02") {
			$scope.express = true;
			$scope.center = false;
			$scope.Province = appData.jzProvince;
			$scope.City = appData.jzCity;
			if(!isBlank(appData.jzCounty)) {
				$scope.County = appData.jzCounty;
			}
		} else {
			$scope.center = true;
			$scope.express = false;
			if(appData.hjCounty) {
				$scope.centerProvince = appData.hjCounty;
				$scope.centerStreet = filterByInfo($rootScope.centerList, (appData.hjStreet.key + "01"));
			} else if(appData.jzCounty) {
				$scope.centerProvince = appData.jzCounty;
				$scope.centerStreet = filterByInfo($rootScope.centerList, (appData.jzStreet.key + "01"));
			}
		}
	}
	//监听省市区 区域联动
	$timeout(function() {
		$scope.$watch("centerProvince", function(val) {
			if(val) {
				$scope.centerStreetList = filterByName($rootScope.centerList, val.key);
				if(val.value == "上海市") {
					$scope.active = false;
				} else {
					$scope.active = true;
				}
			}
		});
		$scope.$watch("Province", function(val) {
			console.log(val);
			if(val) {
				$scope.cityList = filterByName($rootScope.allList, val.key);
				if(val.value == "上海市") {
					$scope.active = false;
				} else {
					$scope.active = true;
				}
			}
		});
		$scope.$watch("City", function(val) {
			if(val) {
				$scope.countyList = $rootScope.ShangHaiList;
			}
		});
	}, 100)

	//获取统一审批编码
	$scope.getApplyNoByItemNo = function(code) {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf+'/selfapi/workPlatform/getApplyNoByItemNo.do',
			dataType: 'json',
//			jsonp: "jsonpCallback",
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
	$scope.getApplyNoByItemNo(appData.ywlx);
	$scope.prevStep = function() {
		$state.go("infoChoose");
	}
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			//快递取件
			if($scope.express == true) {
				if(isBlank($scope.stReceiptName)) {
					$scope.isAlert = true;
					$scope.msg = "请输入收件人姓名！";
					return;
				}
				if(!isPhoneAvailable($scope.stReceiptMobile)) {
					$scope.isAlert = true;
					$scope.msg = "请输入收件人正确联系电话！";
					return;
				}
				if(isBlank($scope.Province)) {
					$scope.isAlert = true;
					$scope.msg = "请输入收件地址（省）！";
					return;
				}
				if(isBlank($scope.City)) {
					$scope.isAlert = true;
					$scope.msg = "请输入收件地址（市）！";
					return;
				}
				if($scope.active == false) {
					if(isBlank($scope.County)) {
						$scope.isAlert = true;
						$scope.msg = "请输入收件地址（区）！";
						return;
					}
				}
				if(isBlank($scope.stReceiptAddress)) {
					$scope.isAlert = true;
					$scope.msg = "请输入收件详细地址！";
					return;
				}
				if(isBlank($scope.stPostCode)&&($scope.stPostCode.length!=6)) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确收件地址邮编！";
					return;
				}
			}
			//受理中心取件
			if($scope.center == true) {
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
			}
		} while (condFlag);
		$scope.isLoading = true;
		//提交
		if($scope.center == true) {
			appData.recievertype = "0";
			appData.recieverProvinceKey = "";
			appData.recieverCityKey = "";
			if(!isBlank($scope.centerStreet)) {
				appData.stCenter = $scope.centerStreet.value;
				appData.stCenterKey = $scope.centerStreet.key;
			}
		}
		if($scope.express == true) {
			appData.recievertype = "1";
			appData.recieverProvinceKey = $scope.Province.key;
			appData.recieverCityKey = $scope.City.key;
			if(!isBlank(appData.hjStreet)) {
				var stDomicileSCObj = filterByInfo($rootScope.centerList, (appData.hjStreet.key + "01"));
			}
			if(!isBlank(appData.jzStreet)) {
				var stLiveSCObj = filterByInfo($rootScope.centerList, (appData.jzStreet.key + "01"));
			}
			// 【快递】受理中心
			var stCenter = "";
			// 户籍地(省)
			var stDomicileP = appData.hjProvince.value;
			// 户籍地(区)
			if(!isBlank(appData.hjCounty)) {
				var stDomicileB = appData.hjCounty.value;
			} else {
				var stDomicileB = "";
			}
			// 户籍地(街道受理中心): 根据户籍街道找受理中心，可能为空
			if(!isBlank(stDomicileSCObj)) {
				var stDomicileSC = stDomicileSCObj.value;
			} else {
				var stDomicileSC = "";
			}
			// 居住地(省)
			var stLiveP = appData.jzProvince.value;
			// 居住地（区）
			if(!isBlank(appData.jzCounty)) {
				var stLiveB = appData.jzCounty.value || "";
			} else {
				var stLiveB = "";
			}
			// 居住地(街道受理中心): 根据居住街道找受理中心，可能为空
			if(!isBlank(stLiveSCObj)) {
				var stLiveSC = stLiveSCObj.value || "";
			} else {
				var stLiveSC = "";
			}
			//户籍地所在区的任一受理中心
			if(!isBlank(appData.hjCounty)) {
				var stDomicileRandomSC = filterByNameRandom($rootScope.centerList, appData.hjCounty.key).value;
			} else {
				var stDomicileRandomSC = "";
			}
			//居住地所在区的任一受理中心
			if(!isBlank(appData.jzCounty)) {
				var stLiveRandomSC = filterByNameRandom($rootScope.centerList, appData.jzCounty.key).value;
			} else {
				var stLiveRandomSC = "";
			}
			//全市任一受理中心
			var random = parseInt(Math.random() * ($rootScope.centerList.length));
			var stRandomSC = $rootScope.centerList[random].value || "";

			// 【户籍地】=上海
			if(null != stDomicileP && stDomicileP != "" && stDomicileP == "上海市") {
				// 【户籍街道受理中心】!= 空
				if(null != stDomicileSC && stDomicileSC != "") {
					stCenter = stDomicileSC;
					// 【居住地】是否为上海
				} else {
					// 【居住地】=上海
					if(null != stLiveP && stLiveP != "" && stLiveP == "上海市") {
						// 【居住街道受理中心】!= 空
						if(null != stLiveSC && stLiveSC != "") {
							stCenter = stDomicileSC;
							// 【居住街道受理中心】= 空
						} else {
							stCenter = stDomicileRandomSC;
						}
						// 【居住地】！=上海
					} else {
						stCenter = stDomicileRandomSC;
					}
				}
				// 【户籍地】!= 上海
			} else {
				//居住地=上海
				if(null != stLiveP && stLiveP != "" && stLiveP == "上海市") {
					// 【居住街道受理中心】!= 空
					if(null != stLiveSC && stLiveSC != "") {
						stCenter = stLiveSC;
						// 【居住街道受理中心】= 空
					} else {
						stCenter = stLiveRandomSC;
					}
					// 【居住地】!=上海
				} else {
					stCenter = stRandomSC;
				}
			}
			appData.stCenter = stCenter;
			appData.stCenterKey = filterGetValueByInfo($rootScope.centerList, stCenter).key;
			console.log(stCenter);
			console.log(appData.stCenterKey);
		}
		if(!isBlank(appData.hjStreet)) {
			appData.hjStreetKey = appData.hjStreet.key;
			appData.hjStreetValue = appData.hjStreet.value;
		}
		if(!isBlank(appData.jzStreet)) {
			appData.jzStreetKey = appData.jzStreet.key;
			appData.jzStreetValue = appData.jzStreet.value;
		}
		if(!isBlank(appData.hjCounty)) {
			appData.hjCountyKey = appData.hjCounty.key;
			appData.hjCountyValue = appData.hjCounty.value;
		}
		if(!isBlank(appData.jzCounty)) {
			appData.jzCountyKey = appData.jzCounty.key;
			appData.jzCountyValue = appData.jzCounty.value;
		}
		if(!isBlank($scope.County)) {
			appData.receiptCountyKey = $scope.County.key;
			appData.receiptCountyValue = $scope.County.value;
		}
		if(appData.useLicense == "廉租住房") {
			$scope.formdata = {
				"application": appData.useLicense,
				"unitAll": appData.unitName,
				"unit1": appData.county.key,
				"unit2": appData.street.key,
				"unit3": appData.unit,
				"archivesdata": [],
				"display": {
					"出证用途": appData.useLicense,
					"发往单位": appData.unitName || appData.unit,
					"发往单位所属区": appData.county.value,
					"发往单位所属街道": appData.street.value,
				}
			}
		} else {
			$scope.formdata = {
				"application": appData.useLicense,
				"unit": appData.unitName,
				"archivesdata": [],
				"display": {
					"发往单位": appData.unitName,
					"出证用途": appData.useLicense
				}
			}
		}
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
				"itemcode": "312000379000",
				"suid": appData.applyNo,
				"subtime": new Date(),
				"suborgancode": appData.stCenterKey,
				"suborganname": appData.stCenter,
				"recievertype": appData.recievertype,
				"recievername": $scope.stReceiptName || "",
				"recieverphone": $scope.stReceiptMobile || "",
				"recieverprov": appData.recieverProvinceKey || "",
				"recievercity": appData.recieverCityKey || "",
				"recieverarea": appData.receiptCountyKey || "",
				"recieveraddress": $scope.stReceiptAddress || "",
				"recieverzipcode": $scope.stPostCode || "",
				"logistics": "0",
				"sendtype": "1",
				"sendname": "",
				"sendphone": "",
				"sendprov": "",
				"sendcity": "",
				"sendarea": "",
				"sendaddress": "",
				"sendzipcode": "",
				"sendlogistics": "",
				"wt_userid": appData.zwdtsw_user_id,
				"formdata": $scope.formdata
			}
		}
		console.log($scope.applicationInfo);
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/civilService/sendYwtbApplyInfo.do",
			dataType: "json",
			data: {
				jsonStr: encodeURI(JSON.stringify($scope.applicationInfo))
			},
			success: function(dataJson) {
				console.log(dataJson);
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
				console.log("sendYwtbApplyInfo err");
			}
		});
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
	recordUsingHistory('民政服务', '办理', $scope.funName, appData.licenseName, appData.licenseNumber, appData.stMobile, $scope.applyNo, JSON.stringify($scope.jsonStr));
	trackEventForAffairs($scope.applyNo, $scope.funName, "上海市民政局", appData.licenseName, appData.licenseNumber, appData.stMobile);
	var date = new Date();
	var month = date.getMonth() + 1;
	$scope.date = date.getFullYear() + "年" + month + "月" + date.getDate() + "日" + date.getHours() + "时" + date.getMinutes() + "分";
	$scope.nextText = "返回首页";
	$scope.nextStep = function() {
		$.device.GoHome();
	}
});