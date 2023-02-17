app.controller('loginType', function($state, $scope, appData, $location, appFactory) {
	$scope.funName = "办理结算服务项目的相关手续";
	appData.funName = $scope.funName;
	appData.itemCode = "312090035000";
	appData.ywlx = "RS2038";
	$scope.operation = "请选择登录方式";
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		window.location.href = "../medical/index.html";
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
	//test跳过身份验证
//		$scope.idcardLogin = function(info, images) {
//			if(true) {
//				$scope.faceImage = images;
//				appData.licenseNumber = "430426199804106174";
//				appData.licenseName = "邹天奇";
//				appData.VALIDENDDAY = "2029-05-13";
//				appData.VALIDSTARTDAY = "2019-05-13";
//				$scope.getTokenSNO(photo, photo);
//			} else {
//				layer.msg("很抱歉，未获取到相关信息，请重试。");
//			}
//		}
//		$scope.idcardLogin();
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
			appData.VALIDENDDAY = idcardInfo.VALIDENDDAY;
			appData.VALIDSTARTDAY = idcardInfo.VALIDSTARTDAY;
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
	appFactory.pro_fetch(appData.licenseNumber, appData.licenseName, appData.token, function(data) {
		if(!isBlank(data.body)) {
			appData.ybbf = data.body.ybbfsm;
			$scope.ybbf = appData.ybbf;
			if(appData.ybbf == "城保") {
				appData.funName = "新增结算服务项目的相关手续（职保零报）";
				appData.ywlx = "RS2038_01";
				appData.itemCode = "312090035000";
			} else if(appData.ybbf == "居保") {
				appData.funName = "新增结算服务项目的相关手续（居保零报）";
				appData.ywlx = "RS2038_05";
				appData.itemCode = "312090035000";
			}
		} else {
			$scope.isAlert = true;
			$scope.msg = "未查询到您的医保办法 ，请重试"
			$scope.alertConfirm = function() {
				$state.go("loginType");
			}
		}
	}, function(err) {
		$scope.isAlert = true;
		$scope.msg = "未查询到信息 ，请重试"
		$scope.alertConfirm = function() {
			$state.go("loginType");
		}
	});
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
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getUserInfoByAccessToken.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				accessToken: appData.token1
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
			dataType: "json",
			jsonp: "jsonpCallback",
			timeout: 5000,
			data: {
				tokenSNO: tokenSNO,
			},
			success: function(res) {
				console.log(res);
				if(res.SUCCESS === true) {
					appData.token1 = res.accessToken;
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
			url: $.getConfigMsg.preUrl + '/selfapi/workPlatform/getApplyNoByItemNo.do',
			dataType: 'json',
			//jsonp: "jsonpCallback",
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
		$state.go("applyInfo");
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}
});
app.controller("applyInfo", function($scope, $state, appData, $sce, appFactory) {
	$scope.funName = appData.funName;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.stName = appData.licenseName;
	$scope.stIdCode = appData.licenseNumber;
	$scope.stPhoneNum = appData.stMobile;
	$scope.payList = pay;
	$scope.stPay = $scope.payList[0];
	$scope.bankTypeList = bankType;
	$scope.stBankType = $scope.bankTypeList[0];
	$scope.bankCodeList = bankCode;

	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(!isPhoneAvailable($scope.stPhoneNum)) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确联系电话！";
				return;
			}
			if(isBlank($scope.stBankCode)) {
				$scope.isAlert = true;
				$scope.msg = "请选择银行信息！";
				return;
			}
			if(isBlank($scope.stBankZh)) {
				$scope.isAlert = true;
				$scope.msg = "请输入银行账号！";
				return;
			}
		} while (condFlag);
		condFlag = true;
		appData.stPhoneNum = $scope.stPhoneNum;
		if(isBlank($scope.stBankCode)) {
			appData.stBankCode = {
				"id": "",
				"name": "",
			}
		} else {
			appData.stBankCode = $scope.stBankCode
		}
		appData.stBankZh = $scope.stBankZh;
		appData.stPostCode = $scope.stPostCode;
		$state.go("billInfoMZ");
	}
});
app.controller("billInfoMZ", function($scope, $state, appData, $sce, appFactory) {
	$scope.funName = appData.funName;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.nextText = "提交";
	$scope.billInfoList = [{
		"name": "门诊发票",
	}, {
		"name": "住院发票",
	}]
	$(".form_datetime").datetimepicker({
		format: "yyyy-mm-dd", //显示日期格式
		autoclose: true,
		todayBtn: true,
		minView: "month", //只选择到天自动关闭
		language: 'zh-CN',
		pickerPosition: 'bottom-left',
	});
	$scope.item = '';
	$scope.items = appData.billInfoMZ || [];
	$scope.stBillType = "普通发票";
	//增加列表项方法
	$scope.add = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.stBillType)) {
				$scope.isAlert = true;
				$scope.msg = "请输入发票类型";
				return;
			}
			if(isBlank($scope.stBillCode)) {
				$scope.isAlert = true;
				$scope.msg = "请输入发票号码";
				return;
			}
			if(isBlank($scope.stBillSum)) {
				$scope.isAlert = true;
				$scope.msg = "请输入费用总额";
				return;
			}
			if(isBlank($('#stBillDate').val())) {
				$scope.isAlert = true;
				$scope.msg = "请选择发票日期";
				return;
			}
		} while (condFlag);
		$scope.items.push({
			stBillType: $scope.stBillType,
			stBillCode: $scope.stBillCode,
			stBillSum: convertCurrency($scope.stBillSum),
			stBillDate: $('#stBillDate').val(),
			stBillInfo: $scope.stBillInfo.name
		});
		//提交完一个清空输入框数据
		$scope.stBillCode = "";
		$scope.stBillSum = "";
		$('#stBillDate').val('');
		console.log($scope.items);
	}
	//删除列表项
	$scope.delete = function(index) {
		$scope.items.splice(index, 1);
		console.log($scope.items);
	}
	$scope.prevStep = function(){
		$state.go("applyInfo");
	}
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if($scope.items.length <= 0) {
				$scope.isAlert = true;
				$scope.msg = "请添加发票信息";
				return;
			}
		} while (condFlag);
		appData.billInfoMZ = $scope.items;
		//提交参数集合
		$scope.isLoading = true;
		$scope.fpxxmz = [];
		$scope.fpxxmzzy = [];
		$scope.displayMz = "";
		$scope.displayZy = "";
		//业务参数整理
		for(var i = 0; i < appData.billInfoMZ.length; i++) {
			if(appData.billInfoMZ[i].stBillInfo == "门诊发票") {
				$scope.fpxxmz.push({
					"fplx": "0",
					"pjhm": appData.billInfoMZ[i].stBillCode,
					"fyze": appData.billInfoMZ[i].stBillSum,
					"pzrqdate": appData.billInfoMZ[i].stBillDate,
				});
				if($scope.displayMz == "") {
					$scope.displayMz = "(发票类型:普通发票,发票代码:" + appData.billInfoMZ[i].stBillCode + ",发票金额:" + appData.billInfoMZ[i].stBillSum + ",发票日期:" + appData.billInfoMZ[i].stBillDate + ")";
				} else {
					$scope.displayMz = $scope.displayMz + ",(发票类型:普通发票,发票代码:" + appData.billInfoMZ[i].stBillCode + ",发票金额:" + appData.billInfoMZ[i].stBillSum + ",发票日期:" + appData.billInfoMZ[i].stBillDate + ")";
				}
			} else if(appData.billInfoMZ[i].stBillInfo == "住院发票") {
				$scope.fpxxmzzy.push({
					"fplx": "0",
					"pjhm": appData.billInfoMZ[i].stBillCode,
					"fyze": appData.billInfoMZ[i].stBillSum,
					"pzrqdate": appData.billInfoMZ[i].stBillDate,
				});
				if($scope.displayZy == "") {
					$scope.displayZy = "(发票类型:普通发票,发票代码:" + appData.billInfoMZ[i].stBillCode + ",发票金额:" + appData.billInfoMZ[i].stBillSum + ",发票日期:" + appData.billInfoMZ[i].stBillDate + ")";
				} else {
					$scope.displayZy = $scope.displayZy + ",(发票类型:普通发票,发票代码:" + appData.billInfoMZ[i].stBillCode + ",发票金额:" + appData.billInfoMZ[i].stBillSum + ",发票日期:" + appData.billInfoMZ[i].stBillDate + ")";
				}
			}
		}
		$scope.formdata = {
			"xm": appData.licenseName,
			"sfzh": appData.licenseNumber,
			"yb": appData.stPostCode,
			"lxdh": appData.stPhoneNum,
			"dz": "",
			"zffs": "2",
			"yhlx": "01",
			"yhdm": appData.stBankCode.id,
			"yhzh": appData.stBankZh,
			"jsxm": "1",
			"fpxxmz": $scope.fpxxmz,
			"fpxxmzzy": $scope.fpxxmzzy,
			"archivesdata": [],
			"display": {
				"姓名": appData.licenseName,
				"身份证号": appData.licenseNumber,
				"邮编": appData.stPostCode || "",
				"联系电话": appData.stPhoneNum,
				"地址": "",
				"支付方式": "银行卡",
				"银行类型": "其他银行卡",
				"银行代码": appData.stBankCode.name,
				"银行账号": appData.stBankZh,
				"结算类别": "零星报销",
				"发票信息-门诊": $scope.displayMz,
				"发票信息-住院": $scope.displayZy,
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
				jsonStr: encodeURI(JSON.stringify($scope.applicationInfo))
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
	$scope.funName = appData.funName = "办理结算服务项目的相关手续";
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