app.controller('choose', function($state, $scope, appData, $location, appFactory) {
	$scope.funName = "办理居民医保受理登记";
	appData.funName = $scope.funName;
	appData.itemCode = "312000361000";
	appData.ywlx = "RS2039_01";
	var date = new Date();
	$scope.year = date.getFullYear();
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//参保年度
	$scope.cbYearList = [{
		"name": $scope.year
	}, {
		"name": $scope.year - 1
	}]
	$scope.isShanghaiList = isShanghai;
	$scope.ageList = age;
	$scope.isSchoolList = isSchool;
	$scope.isCityList = isCity;
	$scope.isMaleList = isMale;
	$scope.isShanghaiChange = function(name, index, id) {
		$scope.current1 = index;
		$scope.currentAge = null;
		$scope.currentMale = null;
		$scope.stIsShanghai = name;
		$scope.ralist = id;
	}
	$scope.ageChange = function(name, index, id) {
		$scope.currentAge = index;
		$scope.currentSchool = null;
		$scope.stAge = name;
		$scope.radionlist = id;
	}
	$scope.isSchoolChange = function(name, index, id) {
		$scope.currentSchool = index;
		$scope.stSchool = name;
		$scope.radionlist = id;
	}
	$scope.isCityChange = function(name, index, id) {
		$scope.currentCity = index;
		$scope.stCity = name;
		$scope.ralist = id;
		if(index == '0') {
			appData.stHkxz = {
				"id": '0',
				"name": '非农业'
			}
		} else if(index == '1') {
			appData.stHkxz = {
				"id": '1',
				"name": '农业'
			}
		}
	}
	$scope.isMaleChange = function(name, index, id) {
		$scope.currentMale = index;
		$scope.stMale = name;
		$scope.radionlist = id;
	}
	$scope.cbYearChange = function(name, index, id) {
		$scope.currentCbYear = index;
		appData.stCbYear = name;
	}
	$scope.nextStep = function(type) {
		var flag = false;
		do {
			if(isBlank($scope.stIsShanghai)) {
				$scope.isAlert = true;
				$scope.msg = "请选择户籍所在地！";
				return;
			}
			if(isBlank(appData.stHkxz)) {
				$scope.isAlert = true;
				$scope.msg = "请选择是否城镇户籍！";
				return;
			}
			if(isBlank(appData.stCbYear)) {
				$scope.isAlert = true;
				$scope.msg = "请选择参保年度！";
				return;
			}
			if(isBlank($scope.stAge) && $scope.current1 == '0') {
				$scope.isAlert = true;
				$scope.msg = "请选择年龄段！";
				return;
			}
			if(isBlank($scope.stSchool) && $scope.currentAge == '0') {
				$scope.isAlert = true;
				$scope.msg = "请选择是否为在校(册)学生、在园(所)幼儿！";
				return;
			}
			//			if(isBlank($scope.stMale) && $scope.current1 == '1') {
			//				$scope.isAlert = true;
			//				$scope.msg = "请选择配偶是否为本市户籍人员！";
			//				return;
			//			}
			if($scope.ralist == "N" || $scope.radionlist == "N") {
				$scope.isAlert = true;
				$scope.msg = "此类人员暂时无法办理此事项！";
				return;
			}
			if($scope.ralist == "Y" && ($scope.radionlist == "104" || $scope.radionlist == "102")) {
				$scope.isAlert = true;
				$scope.msg = "此类人员暂时无法办理此事项！";
				return;
			}
			if($scope.ralist == "4") {
				$scope.isAlert = true;
				$scope.msg = "此类人员暂时无法办理此事项！";
				return;
			}
		} while (flag);
		switch($scope.ralist) {
			case "s1":
				appData.raListName = "本市城镇户籍"
				break;
			case "2":
				appData.raListName = "本市户籍"
				break;
			case "4":
				appData.raListName = "外省市户籍"
				break;
		}
		switch($scope.radionlist) {
			case "104":
				appData.radionlistName = "60~69周岁"
				break;
			case "102":
				appData.radionlistName = "70周岁以上"
				break;
			case "204":
				appData.radionlistName = "18周岁以下的非在校(册)学生、在园(所)幼儿"
				break;
			case "401":
				appData.radionlistName = "本市户籍人员的外省市户籍配偶"
				break;
		}
		appData.ralist = $scope.ralist;
		appData.radionlist = $scope.radionlist;
		console.log(appData.stCbYear);
		console.log(appData.stHkxz);
		$state.go("loginType");
	}
	$scope.prevStep = function() {
		window.location.href = "../medical/index.html";
	}
});
app.controller('loginType', function($state, $scope, appData, $location, appFactory) {
	$scope.operation = "请选择登录方式";
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("choose");
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

app.controller('info', function($state, $scope, appData, $location, appFactory, $timeout, $rootScope) {
	$scope.funName = appData.funName;
	$scope.licenseNumber = appData.encrypt_identity || appData.licenseNumber;
	appData.isUpload = [];
	appData.listImg = [];
	$scope.nextText = "提交"
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
	$scope.stMobile = appData.stMobile || "";
	$scope.nationList = nations;
	$scope.hjProvinceList = [{
		key: "310000",
		value: "上海市",
		parentKey: ""
	}];
	$scope.hjProvince = $scope.hjProvinceList[0];
	$scope.jzProvinceList = filterByName($rootScope.allList, "");
	$scope.centerProvinceList = $rootScope.ShangHaiList;
	$scope.$watch("nation", function(val) {
		console.log(val.shortname);
	});
	//监听省市 区域变化
	$timeout(function() {
		selectBlur();
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
					appData.YBjzCity = {
						'key': "09",
						'value': "上海市",
						'parentkey': ""
					}
					$scope.jzActive = false;
				} else {
					appData.YBjzCity = filterGetValueByInfo($rootScope.YBjzList, val.value);
					$scope.jzActive = true;
				}
				console.log(appData.YBjzCity);
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
				if(val.value == "浦东新区") {
					appData.YBhjCountyName = (val.value).split('新区')[0];
				} else {
					appData.YBhjCountyName = (val.value).split('区')[0];
				}
				appData.YBhjCounty = filterGetValueByInfo($rootScope.YBhjList, appData.YBhjCountyName);
			}
		});
		$scope.$watch("hjStreet", function(val) {
			if(val) {
				appFactory.getMedicalDictionaries('0', appData.YBhjCounty.key, function(dataJson) {
					$scope.YBhjStreetList = dataJson;
					appData.YBhjStreet = filterGetValueByInfo($scope.YBhjStreetList, val.value);
					console.log(appData.YBhjStreet);
				})
			}
		});
		$scope.$watch("jzCity", function(val) {
			if(val) {
				if($scope.jzActive == true) {
					appFactory.getMedicalDictionaries('1', appData.YBjzCity.key, function(dataJson) {
						$scope.YBjzCountyList = dataJson;
						appData.YBjzCounty = filterGetValueByInfo($scope.YBjzCountyList, val.value);
						console.log(appData.YBjzCounty);
					})
				}
				$scope.jzCountyList = $rootScope.ShangHaiList;
			}
		});
		$scope.$watch("jzCounty", function(val) {
			if(val) {
				if($scope.jzActive == false) {
					appFactory.getMedicalDictionaries('1', appData.YBjzCity.key, function(dataJson) {
						$scope.YBjzCountyList = dataJson;
						appData.YBjzCounty = filterGetValueByInfo($scope.YBjzCountyList, val.value);
						console.log(appData.YBjzCounty);
					})
				}
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
				$scope.hjCity = appData.hjCity || filterByInfo($rootScope.allList, dataJson.data.hjcity);
				$scope.hjCounty = appData.hjCounty || filterByInfo($rootScope.ShangHaiList, dataJson.data.hjregion);
				$scope.hjStreet = appData.hjStreet || filterByInfo($rootScope.ShangHaiStreetList, dataJson.data.hjneighborhood);
				$scope.jzProvince = appData.jzProvince || filterByInfo($rootScope.allList, dataJson.data.jzprovince);
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
					$scope.getUserInfoByAccessToken();
				} else {
					$scope.isAlert = true;
					$scope.msg = '未获得对应的用户标识！';
					$scope.alertConfirm = function() {
						$state.go("loginType");
					}
				}
			},
			error: function(err) {
				console.log(err);
			},
		})
	}
	if(appData.zwdtsw_user_id) {} else {
		$scope.getAccessToken(appData.tokenSNO);
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
			if(isBlank($scope.stHjAddress)) {
				$scope.isAlert = true;
				$scope.msg = "请输入户籍详细地址！";
				return;
			}
			if(isBlank($scope.stHjPostCode)) {
				$scope.isAlert = true;
				$scope.msg = "请输入户籍地邮编！";
				return;
			}
			if(isBlank($scope.stJzAddress)) {
				$scope.isAlert = true;
				$scope.msg = "请输入居住详细地址！";
				return;
			}
			if(isBlank($scope.stJzPostCode)) {
				$scope.isAlert = true;
				$scope.msg = "请输入居住地邮编！";
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
		appData.stPhoneNum = appData.stMobile;
		appData.stYbMobile = appData.stMobile;
		appData.stHjAddress = $scope.stHjAddress;
		appData.stHjPostCode = $scope.stHjPostCode;
		appData.stJzAddress = $scope.stJzAddress;
		appData.stJzPostCode = $scope.stJzPostCode;
		if(appData.radionlist == "401") {
			$scope.formdata = {
				"ralist": appData.ralist,
				"radionlist": appData.radionlist,
				"cbnd": appData.stCbYear,
				"djrxm": appData.licenseName,
				"djrsfzh": appData.licenseNumber,
				"djrjzzh": appData.djrjzzh,
				"djryxqx": appData.djryxqx,
				"djrqfrq": appData.djrqfrq,
				"qsxm": appData.qsxm,
				"qssfzh": appData.qssfzh,
				"qslxdh": appData.qslxdh,
				"djrhjsh": appData.YBhjCounty.key,
				"djrhjjd": appData.YBhjStreet.key,
				"djrhjdz": appData.stHjAddress,
				"djrhjyb": appData.stHjPostCode,
				"hjxz": appData.stHkxz.id,
				"djrjzs": appData.YBjzCity.key,
				"djrjzsh": appData.YBjzCounty.key,
				"djrjzdz": appData.stJzAddress,
				"djrjzyb": appData.stJzPostCode,
				"djrlxdh": appData.stPhoneNum,
				"djrlxsj": appData.stYbMobile,
				"archivesdata": [],
				"display": {
					"人员类别": appData.raListName,
					"人员细类": appData.radionlistName,
					"参保年度": appData.stCbYear,
					"姓名": appData.licenseName,
					"身份证号码": appData.licenseNumber,
					"居住证号": appData.djrjzzh,
					"居住证有效期限": appData.djryxqx,
					"居住证签发日期": appData.djrqfrq,
					"配偶或父母姓名": appData.qsxm,
					"身份证号": appData.qssfzh,
					"联系电话": appData.qslxdh,
					"户籍地址区县": appData.YBhjCounty.value,
					"户籍地址街道": appData.YBhjStreet.value,
					"户籍详细地址": appData.stHjAddress,
					"户籍地邮编": appData.stHjPostCode,
					"户口性质": appData.stHkxz.name,
					"居住地址省市": appData.YBjzCity.value,
					"居住地址区县": appData.YBjzCounty.value,
					"居住详细地址": appData.stJzAddress,
					"居住地邮编": appData.stJzPostCode,
					"联系电话": appData.stPhoneNum,
					"手机号码": appData.stYbMobile,
				}
			}
		} else {
			$scope.formdata = {
				"ralist": appData.ralist,
				"radionlist": appData.radionlist,
				"cbnd": appData.stCbYear,
				"djrxm": appData.licenseName,
				"djrsfzh": appData.licenseNumber,
				"djrhjsh": appData.YBhjCounty.key,
				"djrhjjd": appData.YBhjStreet.key,
				"djrhjdz": appData.stHjAddress,
				"djrhjyb": appData.stHjPostCode,
				"hjxz": appData.stHkxz.id,
				"djrjzs": appData.YBjzCity.key,
				"djrjzsh": appData.YBjzCounty.key,
				"djrjzdz": appData.stJzAddress,
				"djrjzyb": appData.stJzPostCode,
				"djrlxdh": appData.stPhoneNum,
				"djrlxsj": appData.stYbMobile,
				"archivesdata": [],
				"display": {
					"人员类别": appData.raListName,
					"人员细类": appData.radionlistName,
					"参保年度": appData.stCbYear,
					"姓名": appData.licenseName,
					"身份证号码": appData.licenseNumber,
					"户籍地址区县": appData.YBhjCounty.value,
					"户籍地址街道": appData.YBhjStreet.value,
					"户籍详细地址": appData.stHjAddress,
					"户籍地邮编": appData.stHjPostCode,
					"户口性质": appData.stHkxz.name,
					"居住地址省市": appData.YBjzCity.value,
					"居住地址区县": appData.YBjzCounty.value,
					"居住详细地址": appData.stJzAddress,
					"居住地邮编": appData.stJzPostCode,
					"联系电话": appData.stPhoneNum,
					"手机号码": appData.stYbMobile,
				}
			}
		}
		console.log($scope.formdata);
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
				"hjregion": appData.hjCounty.key || "",
				"hjneighborhood": appData.hjStreet.key || "",
				"jzprovince": appData.jzProvince.key,
				"jzcity": appData.jzCity.key,
				"jzregion": appData.jzCounty.key || "",
				"jzneighborhood": appData.jzStreet.key || ""
			},
			"ywtbAffairsapply": {
				"platform": "4",
				"affairscode": appData.ywlx,
				"affairsname": appData.funName,
				"itemcode": appData.itemCode,
				"suid": appData.applyNo,
				"subtime": new Date(),
				"suborgancode": appData.stCenter.key,
				"suborganname": appData.stCenter.value,
				"recievertype": "",
				"recievername": "",
				"recieverphone": "",
				"recieverprov": "",
				"recievercity": "",
				"recieverarea": "",
				"recieveraddress": "",
				"recieverzipcode": "",
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
				"formdata": $scope.formdata
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
app.controller("applyMaleInfo", function($scope, $state, appData, $sce, appFactory, $rootScope, $timeout) {
	$scope.funName = appData.funName;
	$(".form_datetime").datetimepicker({
		format: "yyyy-mm-dd", //显示日期格式
		autoclose: true,
		todayBtn: true,
		minView: "month", //只选择到天自动关闭
		language: 'zh-CN',
		pickerPosition: 'bottom-left',
	});
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		$state.go("info");
	}
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.djrjzzh)) {
				$scope.isAlert = true;
				$scope.msg = "请输入居住证号！";
				return;
			}
		} while (condFlag);
		appData.qsxm = $scope.qsxm;
		appData.qssfzh = $scope.qssfzh;
		appData.djrjzzh = $scope.djrjzzh;
		appData.qslxdh = $scope.qslxdh;
		appData.djryxqx = $("#djryxqx").val();
		appData.djrqfrq = $("#djrqfrq").val();
		$state.go("applyInfo");
	}
});
app.controller("applyInfo", function($scope, $state, appData, $sce, appFactory, $rootScope, $timeout) {
	$scope.funName = appData.funName;
	var date = new Date();
	$scope.year = date.getFullYear();
	$scope.nextText = "提交";
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//参保年度
	$scope.cbYearList = [{
		"name": $scope.year
	}, {
		"name": $scope.year - 1
	}]
	$scope.YBhjCityList = [{
		"key": "09",
		"value": "上海市",
		"parentKey": ""
	}]
	$scope.hkxzList = hkxz
	$scope.YBhjCity = $scope.YBhjCityList[0];
	$scope.YBjzCityList = $rootScope.YBJzList;
	$scope.stName = appData.licenseName;
	$scope.stIdCode = appData.licenseNumber;
	$scope.stPhoneNum = appData.stMobile;

	//监听省市 区域变化
	$timeout(function() {
		selectBlur();
		$scope.$watch("YBhjCity", function(val) {
			if(val) {
				appFactory.getMedicalDictionaries("0", "", function(dataJson) {
					$scope.YBhjCountyList = dataJson
				});
			}
		});
		$scope.$watch("YBhjCounty", function(val) {
			if(val) {
				appFactory.getMedicalDictionaries("0", val.key, function(dataJson) {
					$scope.YBhjStreetList = dataJson
				});
			}
		});
		$scope.$watch("YBjzCity", function(val) {
			if(val) {
				appFactory.getMedicalDictionaries("1", val.key, function(dataJson) {
					$scope.YBjzCountyList = dataJson
				});
			}
		});
	}, 100);

	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.stCbYear)) {
				$scope.isAlert = true;
				$scope.msg = "请选择参保年度！";
				return;
			}
			if(isBlank($scope.stHkxz)) {
				$scope.isAlert = true;
				$scope.msg = "请选择户口性质！";
				return;
			}
			if(isBlank($scope.YBhjCounty)) {
				$scope.isAlert = true;
				$scope.msg = "请选择户籍地址（区）！";
				return;
			}
			if(isBlank($scope.YBhjStreet)) {
				$scope.isAlert = true;
				$scope.msg = "请选择户籍地址（街道）！";
				return;
			}
			if(isBlank($scope.YBjzCity)) {
				$scope.isAlert = true;
				$scope.msg = "请选择居住地址（省/市）！";
				return;
			}
			if(isBlank($scope.YBjzCounty)) {
				$scope.isAlert = true;
				$scope.msg = "请选择居住地址（区/县）！";
				return;
			}
			if(isBlank($scope.stHjAddress)) {
				$scope.isAlert = true;
				$scope.msg = "请输入户籍详细地址！";
				return;
			}
			if(isBlank($scope.stHjPostCode)) {
				$scope.isAlert = true;
				$scope.msg = "请输入户籍地邮编！";
				return;
			}
			if(isBlank($scope.stJzAddress)) {
				$scope.isAlert = true;
				$scope.msg = "请输入居住详细地址！";
				return;
			}
			if(isBlank($scope.stJzPostCode)) {
				$scope.isAlert = true;
				$scope.msg = "请输入居住地邮编！";
				return;
			}
			if(!isPhoneAvailable($scope.stPhoneNum) && isBlank($scope.stPhoneNum)) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确联系电话！";
				return;
			}
		} while (condFlag);
		condFlag = true;
		appData.stCbYear = $scope.stCbYear;
		appData.stHkxz = $scope.stHkxz;
		appData.YBhjCity = $scope.YBhjCity;
		appData.YBhjCounty = $scope.YBhjCounty;
		appData.YBhjStreet = $scope.YBhjStreet;
		appData.YBjzCity = $scope.YBjzCity;
		appData.YBjzCounty = $scope.YBjzCounty;
		appData.stHjAddress = $scope.stHjAddress;
		appData.stHjPostCode = $scope.stHjPostCode;
		appData.stJzAddress = $scope.stJzAddress;
		appData.stJzPostCode = $scope.stJzPostCode;
		if(appData.radionlist == "401") {
			$scope.formdata = {
				"ralist": appData.ralist,
				"radionlist": appData.radionlist,
				"cbnd": appData.stCbYear,
				"djrxm": appData.licenseName,
				"djrsfzh": appData.licenseNumber,
				"djrjzzh": appData.djrjzzh,
				"djryxqx": appData.djryxqx,
				"djrqfrq": appData.djrqfrq,
				"qsxm": appData.qsxm,
				"qssfzh": appData.qssfzh,
				"qslxdh": appData.qslxdh,
				"djrhjsh": appData.YBhjCounty.key,
				"djrhjjd": appData.YBhjStreet.key,
				"djrhjdz": appData.stHjAddress,
				"djrhjyb": appData.stHjPostCode,
				"hjxz": appData.stHkxz.id,
				"djrjzs": appData.YBjzCity.key,
				"djrjzsh": appData.YBjzCounty.key,
				"djrjzdz": appData.stJzAddress,
				"djrjzyb": appData.stJzPostCode,
				"djrlxdh": appData.stPhoneNum,
				"djrlxsj": appData.stYbMobile,
				"archivesdata": [],
				"display": {
					"人员类别": appData.raListName,
					"人员细类": appData.radionlistName,
					"参保年度": appData.stCbYear,
					"姓名": appData.licenseName,
					"身份证号码": appData.licenseNumber,
					"居住证号": appData.djrjzzh,
					"居住证有效期限": appData.djryxqx,
					"居住证签发日期": appData.djrqfrq,
					"配偶或父母姓名": appData.qsxm,
					"身份证号": appData.qssfzh,
					"联系电话": appData.qslxdh,
					"户籍地址区县": appData.YBhjCounty.value,
					"户籍地址街道": appData.YBhjStreet.value,
					"户籍详细地址": appData.stHjAddress,
					"户籍地邮编": appData.stHjPostCode,
					"户口性质": appData.stHkxz.name,
					"居住地址省市": appData.YBjzCity.value,
					"居住地址区县": appData.YBjzCounty.value,
					"居住详细地址": appData.stJzAddress,
					"居住地邮编": appData.stJzPostCode,
					"联系电话": appData.stPhoneNum,
					"手机号码": appData.stYbMobile,
				}
			}
		} else {
			$scope.formdata = {
				"ralist": appData.ralist,
				"radionlist": appData.radionlist,
				"cbnd": appData.stCbYear.name,
				"djrxm": appData.licenseName,
				"djrsfzh": appData.licenseNumber,
				"djrhjsh": appData.YBhjCounty.key,
				"djrhjjd": appData.YBhjStreet.key,
				"djrhjdz": appData.stHjAddress,
				"djrhjyb": appData.stHjPostCode,
				"hjxz": appData.stHkxz.id,
				"djrjzs": appData.YBjzCity.key,
				"djrjzsh": appData.YBjzCounty.key,
				"djrjzdz": appData.stJzAddress,
				"djrjzyb": appData.stJzPostCode,
				"djrlxdh": appData.stPhoneNum,
				"djrlxsj": appData.stYbMobile,
				"archivesdata": [],
				"display": {
					"人员类别": appData.raListName,
					"人员细类": appData.radionlistName,
					"参保年度": appData.stCbYear.name,
					"姓名": appData.licenseName,
					"身份证号码": appData.licenseNumber,
					"户籍地址区县": appData.YBhjCounty.value,
					"户籍地址街道": appData.YBhjStreet.value,
					"户籍详细地址": appData.stHjAddress,
					"户籍地邮编": appData.stHjPostCode,
					"户口性质": appData.stHkxz.name,
					"居住地址省市": appData.YBjzCity.value,
					"居住地址区县": appData.YBjzCounty.value,
					"居住详细地址": appData.stJzAddress,
					"居住地邮编": appData.stJzPostCode,
					"联系电话": appData.stPhoneNum,
					"手机号码": appData.stYbMobile,
				}
			}
		}
		console.log($scope.formdata);
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
				"hjregion": appData.hjCounty.key || "",
				"hjneighborhood": appData.hjStreet.key || "",
				"jzprovince": appData.jzProvince.key,
				"jzcity": appData.jzCity.key,
				"jzregion": appData.jzCounty.key || "",
				"jzneighborhood": appData.jzStreet.key || ""
			},
			"ywtbAffairsapply": {
				"platform": "4",
				"affairscode": appData.ywlx,
				"affairsname": appData.funName,
				"itemcode": appData.itemCode,
				"suid": appData.applyNo,
				"subtime": new Date(),
				"suborgancode": appData.stCenter.key,
				"suborganname": appData.stCenter.value,
				"recievertype": "",
				"recievername": "",
				"recieverphone": "",
				"recieverprov": "",
				"recievercity": "",
				"recieverarea": "",
				"recieveraddress": "",
				"recieverzipcode": "",
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
				"formdata": $scope.formdata
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
	recordUsingHistory('医保服务', '办理', $scope.funName, appData.licenseName, appData.licenseNumber, appData.stMobile, $scope.applyNo, JSON.stringify($scope.jsonStr));
	trackEventForAffairs($scope.applyNo,'办理','上海市医疗保障局',appData.licenseName,appData.licenseNumber,appData.stMobile)
	var date = new Date();
	var month = date.getMonth() + 1;
	$scope.date = date.getFullYear() + "年" + month + "月" + date.getDate() + "日" + date.getHours() + "时" + date.getMinutes() + "分";
	$scope.nextText = "返回首页";
	$scope.nextStep = function() {
		$.device.GoHome();
	}
});