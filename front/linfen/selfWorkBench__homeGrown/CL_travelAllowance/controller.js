app.controller('loginType', function($state, $scope, appData, $location, appFactory) {
	$scope.operation = "请选择登录方式";
	$scope.funName = "上海市残疾人交通补贴申请";
	appData.funName = $scope.funName;
	appData.itemCode = "312000374000";
	appData.ywlx = "CL0054_01";
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		window.location.href = "../CL_allItem/index.html";
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
	$scope.stHjAddress = appData.stHjAddress;
	$scope.stJzAddress = appData.stJzAddress;
	$scope.stHjPostCode = appData.stHjPostCode;
	$scope.stJzPostCode = appData.stJzPostCode;
	$scope.centerProvince = appData.centerProvince;
	$scope.centerStreet = appData.stCenter;
	$scope.nationList = nations;
	$scope.nationList = changeListname($scope.nationList,"shortname")
	$scope.jzCityList = filterByName($rootScope.allList, "310000");
	$scope.jzCityList = changeListname($scope.jzCityList,"value");
	$scope.hjCityList = filterByName($rootScope.allList, "310000");
	$scope.hjCityList = changeListname($scope.hjCityList,"value");
	$scope.hjCity = $scope.hjCityList[0];
	$scope.jzCity = $scope.jzCityList[0];
	$scope.jzCitycu= $scope.jzCity.name;
	$scope.hjCitycu=$scope.hjCity.name;
	$scope.discardtypeList = discardtype;
	$scope.centerProvinceList = $rootScope.ShangHaiList;
	$scope.centerProvinceList = changeListname($scope.centerProvinceList,"value");
	$scope.$watch("nation", function(val) {
		console.log(val.shortname);
	});
	//监听省市 区域变化
	$timeout(function() {
		selectBlur();
		$scope.$watch("hjCity", function(val) {
			if(val) {
				$scope.hjCountyList = $rootScope.ShangHaiList;
				$scope.hjCountyList = changeListname($scope.hjCountyList,"value");
				$scope.hjCountycu = "";
			}
		});
		$scope.$watch("hjCounty", function(val) {
			if(val) {
				$scope.hjStreetList = filterByName($rootScope.ShangHaiStreetList, val.key);
				$scope.hjStreetList = changeListname($scope.hjStreetList,"value");
				$scope.hjStreetcu = "";
				$scope.hjCommcu="";
				appData.CLhjCounty = filterGetValueByInfo($rootScope.CLcountryList, val.value);
				console.log(appData.CLhjCounty);
			}
		});
		$scope.$watch("hjStreet", function(val) {
			if(val) {
				$scope.hjCommcu="";
				appFactory.getMedicalDictionaries(appData.CLhjCounty.key, function(dataJson) {
					$scope.CLhjStreetList = dataJson;
					appData.CLhjStreet = filterGetValueByInfo($scope.CLhjStreetList, val.value);
					appFactory.getMedicalDictionaries(appData.CLhjStreet.key, function(dataJson) {
						$scope.hjCommList = dataJson;
						$scope.hjCommList=changeListname($scope.hjCommList,"value");
						console.log($scope.hjCommList);
					})
					console.log(appData.CLhjStreet);
				})
			}
		});
		$scope.$watch("jzCity", function(val) {
			if(val) {
				$scope.jzCountyList = $rootScope.ShangHaiList;
				$scope.jzCountyList = changeListname($scope.jzCountyList,"value");
			}
		});
		$scope.$watch("jzCounty", function(val) {
			if(val) {
				$scope.jzStreetList = filterByName($rootScope.ShangHaiStreetList, val.key);
				$scope.jzStreetList = changeListname($scope.jzStreetList,"value");
				$scope.jzStreetcu="";
				$scope.jzCommcu="";
				appData.CLjzCounty = filterGetValueByInfo($rootScope.CLcountryList, val.value);
				console.log(appData.CLjzCounty);
			}
		});
		$scope.$watch("jzStreet", function(val) {
			if(val) {
				$scope.jzCommcu="";
				appFactory.getMedicalDictionaries(appData.CLjzCounty.key, function(dataJson) {
					$scope.CLjzStreetList = dataJson;
					appData.CLjzStreet = filterGetValueByInfo($scope.CLjzStreetList, val.value);
					appFactory.getMedicalDictionaries(appData.CLjzStreet.key, function(dataJson) {
						$scope.jzCommList = dataJson;
						$scope.jzCommList =changeListname($scope.jzCommList,"value");
						console.log($scope.jzCommList);
					})
					console.log(appData.CLjzStreet);
				})
			}
		});
		$scope.$watch("centerProvince", function(val) {
			if(val) {
				$scope.centerStreetList = filterByName($rootScope.centerList, val.key);
				$scope.centerStreetList =changeListname($scope.centerStreetList,"value");
				console.log($scope.centerStreetList);
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
		appData.hjCity = $scope.hjCity;
		appData.hjCounty = $scope.hjCounty;
		appData.hjStreet = $scope.hjStreet;
		appData.hjComm = $scope.hjComm;
		appData.jzCity = $scope.jzCity;
		appData.jzCounty = $scope.jzCounty;
		appData.jzStreet = $scope.jzStreet;
		appData.jzComm = $scope.jzComm;
		appData.centerProvince = $scope.centerProvince;
		appData.stCenter = $scope.centerStreet;
		appData.stPhoneNum = appData.stMobile;
		appData.stYbMobile = appData.stMobile;
		appData.stHjAddress = $scope.stHjAddress;
		appData.stHjPostCode = $scope.stHjPostCode;
		appData.stJzAddress = $scope.stJzAddress;
		appData.stJzPostCode = $scope.stJzPostCode;
		appData.dis_number = $scope.dis_number;
		appData.discardtype = $scope.discardtype;
		$state.go("applyInfo");
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
app.controller("applyInfo", function($scope, $state, appData, $sce, appFactory, $rootScope, $timeout) {
	$scope.funName = appData.funName;
	$scope.nextText = "提交"
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
	$scope.account_name = appData.licenseName;
	$scope.getBankDictionaries = function() {
		console.log(appData.stCenter.value);
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/CivilServiceController/getBankDictionaries.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				place: encodeURI(appData.stCenter.value)
			},
			success: function(dataJson) {
				$scope.bank_nameList = dataJson;
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	$scope.getBankDictionaries();

	$scope.prevStep = function() {
		$state.go("info");
	}
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.bank_name)) {
				$scope.isAlert = true;
				$scope.msg = "请选择发放银行！";
				return;
			}
			if(isBlank($scope.account_name)) {
				$scope.isAlert = true;
				$scope.msg = "请输入户名！";
				return;
			}
			if(isBlank($scope.account_number)) {
				$scope.isAlert = true;
				$scope.msg = "请输入账号！";
				return;
			}
		} while (condFlag);
		appData.bank_name = $scope.bank_name;
		appData.branch_name = $scope.branch_name;
		appData.account_name = $scope.account_name;
		appData.account_number = $scope.account_number;
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/CivilServiceController/checkBankCradWithPerson.do",
			data: {
				bankId: appData.account_number,
				idCard: appData.licenseNumber,
				name: $scope.account_name,
				bankKey: appData.bank_name.value,
			},
			success: function(dataJson) {
				var info = JSON.parse(dataJson);
				console.log(dataJson);
				if(info.code == "2" && info.data.match == "000") {
					$state.go("signature");
				} else {
					$scope.isAlert = true;
					$scope.msg = "请使用本人银行卡";
				}
			},
			error: function(err) {
				console.log(err);
				$scope.isAlert = true;
				$scope.msg = "请使用本人银行卡";
			}
		});
	}
});
app.controller("signature", function($scope, $http, $rootScope, appData, $state, appFactory) {
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.$watch('$viewContentLoaded', function(newValue, oldValue) {
		var name = appData.funName;
		$scope.itemName = name;
		$scope.signature = null;
		$scope.signatureFlag = false;
		$scope.SignatureBoardPlug = new SignatureBoardPlug({
			canvas: "#signature",
			clearBtn: ".clearRect",
			getSigntrue: ".saveImg",
			color: "black"
		});
	});

	$scope.isSignature = function() {
		$scope.signatureFlag = true;
	};
	$scope.notSignature = function() {
		$scope.signatureFlag = false;
	}
	//提交
	$scope.submit = function() {
		$scope.formdata = {
			"consumer_name": appData.licenseName,
			"sex": appData.stSex == "男" ? "0" : "1",
			"birth_date": appData.dtBirth,
			"mobile_phone": appData.stMobile,
			"id_number": appData.licenseNumber,
			"discardtype": appData.discardtype.id,
			"dis_number": appData.dis_number,
			"house_district": appData.CLhjCounty.key,
			"house_street": appData.CLhjStreet.key,
			"house_comm": appData.hjComm.key,
			"house_address": appData.stHjAddress,
			"house_zipcode": appData.stHjPostCode,
			"district": appData.CLjzCounty.key,
			"street": appData.CLjzStreet.key,
			"comm": appData.jzComm.key,
			"address": appData.stJzAddress,
			"zipcode": appData.stJzPostCode,
			"bank_name": appData.bank_name || "",
			"branch_name": appData.branch_name || "",
			"account_name": appData.account_name || "",
			"account_number": appData.account_number || "",
			"archivesdata": [{
				"archivescode": "YTCL00540105",
				"affirscode": "CL0054_01",
				"archivesname": "上海市残疾人交通补贴申请审批表",
				"needflag": "0",
				"imgscans": appData.imgId
			}],
			"display": {
				"申请人姓名": appData.licenseName,
				"性别": appData.stSex,
				"出生年月": appData.dtBirth,
				"联系电话": appData.stMobile,
				"身份证号": appData.licenseNumber,
				"证件类型": appData.discardtype.name,
				"证件号码": appData.dis_number,
				"户籍区": appData.CLhjCounty.value,
				"户籍街道": appData.CLhjStreet.value,
				"户籍居委": appData.hjComm.value,
				"详细户籍地址": appData.stHjAddress,
				"户籍地邮政编码": appData.stHjPostCode,
				"居住区": appData.CLjzCounty.value,
				"居住街道": appData.CLjzStreet.value,
				"居住居委": appData.jzComm.value,
				"详细居住地址": appData.stJzAddress,
				"居住地邮政编码": appData.stJzPostCode,
				"发放银行": appData.bank_name || "",
				"开户支行": appData.branch_name || "",
				"户名": appData.account_name || "",
				"账号": appData.account_number || "",
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
				"hjprovince": "310000",
				"hjcity": appData.hjCity.key,
				"hjregion": appData.hjCounty.key || "",
				"hjneighborhood": appData.hjStreet.key || "",
				"jzprovince": "310000",
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
	//上传材料
	$scope.uploadStuff = function(img) {
		let formdata = new FormData();
		formdata.append("archivescode", "YTCL00540105");
		formdata.append("affairscode", "CL0054_01");
		formdata.append("archivesname", "上海市残疾人交通补贴申请审批表");
		formdata.append("needflag", '0');
		formdata.append("img", img);
		formdata.append("attachtype", "pdf");
		formdata.append("archivessource", 0);
		$.ajax({
			url: $.getConfigMsg.preUrlSelf + '/selfapi/civilService/uploadArchiveInfo.do',
			type: "post",
			dataType: "json",
			data: formdata,
			cache: false, // 不缓存
			processData: false, // jQuery不要去处理发送的数据
			contentType: false,
			success: function(dataJsonp) {
				try {
					appData.imgId = dataJsonp.data.attachid;
				} catch(e) {}
				$scope.submit();
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	//生成pdf
	$scope.getlicensePDF = function() {
		let formdata = new FormData();
		formdata.append("area", appData.CLhjCounty.value);
		formdata.append("street", appData.CLhjStreet.value);
		formdata.append("rc", appData.hjComm.value);
		formdata.append("name", appData.licenseName);
		formdata.append("sex", appData.stSex);
		formdata.append("birth", appData.dtBirth);
		formdata.append("mobile", appData.stMobile);
		formdata.append("idCard", appData.licenseNumber);
		formdata.append("certNo", appData.dis_number);
		formdata.append("hjAddress", appData.stHjAddress);
		formdata.append("hjZipCode", appData.stHjPostCode);
		formdata.append("jzAddress", appData.stJzAddress);
		formdata.append("jzZipCode", appData.stJzPostCode);
		formdata.append("signPng", appData.picStr);
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/CivilServiceController/getlicensePDF.do",
			data: formdata,
			cache: false, // 不缓存
			processData: false, // jQuery不要去处理发送的数据
			contentType: false,
			success: function(dataJson) {
				var img = JSON.parse(dataJson);
				console.log(img.data);
				appData.archiveImg = img.data;
				$scope.uploadStuff(appData.archiveImg);
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	$scope.saveSignature = function() {
		$scope.signature = $scope.SignatureBoardPlug.Signatrue;
		if($scope.signatureFlag === false) {
			alert("请先在屏幕上签名!");
			return;
		}
		appData.picStr = $scope.signature.split(",")[1];
		$scope.getlicensePDF();
	};
	$scope.prevStep = function() {
		$state.go("/applyInfo");
	}
})
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
	recordUsingHistory('残联服务', '办理', $scope.funName, appData.licenseName, appData.licenseNumber, appData.stMobile, $scope.applyNo, JSON.stringify($scope.jsonStr));
	//行为分析(办理)
	trackEventForAffairs($scope.applyNo,$scope.funName,"上海市残疾人联合会",appData.licenseName, appData.licenseNumber,appData.stMobile);
	var date = new Date();
	var month = date.getMonth() + 1;
	$scope.date = date.getFullYear() + "年" + month + "月" + date.getDate() + "日" + date.getHours() + "时" + date.getMinutes() + "分";
	$scope.nextText = "返回首页";
	$scope.nextStep = function() {
		$.device.GoHome();
	}
});