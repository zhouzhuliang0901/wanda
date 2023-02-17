app.controller('loginType', function($state, $scope, appData, $location, appFactory) {
	$scope.funName = "听力、言语残疾人信息卡套餐服务申请";
	appData.funName = $scope.funName;
	appData.ywlx = "CL0033";
	$scope.operation = "请选择登录方式";
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
//	$scope.idcardLogin = function() {
//		appData.licenseNumber = "310228198808070818"; //"430426199804106174";
//		appData.licenseName = "陈雷"; //邹天奇";
//		appData.VALIDENDDAY = "2034-11-15";
//		appData.VALIDSTARTDAY = "2014-11-15";
//		$scope.nextStep();
//	}
//	$scope.idcardLogin();
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
	$scope.nationList = nations;
	$scope.nationList = changeListname($scope.nationList,"shortname")
	$scope.hjProvince = filterByInfo($rootScope.allList, "310000");
	$scope.jzProvince = filterByInfo($rootScope.allList, "310000");
	$scope.jzProvince = changeListname($scope.jzProvince,"value");
	$scope.hjCityList = filterByName($rootScope.allList, '310000');
	$scope.hjCityList = changeListname($scope.hjCityList,"value")
	$scope.jzCityList = filterByName($rootScope.allList, '310000');
	$scope.jzCityList = changeListname($scope.jzCityList,"value");
	$scope.centerProvinceList = $rootScope.ShangHaiList;
	$scope.centerProvinceList = changeListname($scope.centerProvinceList,'value');
	$scope.$watch("nation", function(val) {
		console.log(val.shortname);
	});
	//监听省市 区域变化
	$timeout(function() {
		selectBlur();
		$scope.$watch("hjCity", function(val) {
			if(val) {
				$scope.hjCountyList = $rootScope.ShangHaiList;
				$scope.hjCountyList= changeListname($scope.hjCountyList,"value");
			}
		});
		$scope.$watch("hjCounty", function(val) {
			if(val) {
				$scope.hjStreetList = filterByName($rootScope.ShangHaiStreetList, val.key);
				$scope.hjStreetList = changeListname($scope.hjStreetList,"value");
				$scope.hjStreetcu="";
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
			}
		});
		$scope.$watch("centerProvince", function(val) {
			if(val) {
				$scope.centerStreetList = filterByName($rootScope.centerList, val.key);
				$scope.centerStreetList = changeListname($scope.centerStreetList,"value");
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
	if(appData.zwdtsw_user_id) {} else {
		$scope.getUserInfoByAccessToken();
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
		//上传材料
		appFactory.upload_file(appData.encrypt_identity||appData.licenseNumber, appData.licenseName, appData.VALIDSTARTDAY, appData.VALIDENDDAY, '310105105000100', "CL00330002", "户口簿", function(dataJson) {
			if(dataJson.data == "" || dataJson.data == null || dataJson.data == undefined || dataJson.isSuccess == false) {
				layer.msg("未能从电子证照获取到户口簿证照上传");
				$timeout(function() {
					$state.go("applyInfo");
				}, 100)
			}
			$scope.imgUrl = "data:image/jpeg;base64," + dataJson.data[0].str;
		}, function(dataJson1) {
			if(dataJson1.code == "0") {
				try {
					appData.isUpload.push({
						index: 1,
						stuffName: "户口簿",
						img: $scope.imgUrl || "",
						status: 1,
						method: "高拍仪",
						imgId: dataJson1.data.attachid,
						archivescode: "CL00330002"
					});
				} catch(e) {}
			} else {
				layer.msg("未能从电子证照获取到户口簿证照上传");
			}
			$timeout(function() {
				$state.go("applyInfo");
			}, 100)
		});
	}

	$scope.prevStep = function() {
		$state.go("loginType");
	}
});
app.controller("applyInfo", function($scope, $state, appData, $timeout,$sce, appFactory) {
	$scope.funName = appData.funName;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.nextText = "提交";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.stIdCode = appData.licenseNumber;
	$scope.stPhoneNum = appData.stMobile;
	$scope.applyTypeList = applyType;
	$scope.tcTypeList = tcType;
	$scope.bordBandSpeedList = bordBandSpeed;
	$scope.baseTelecomCompanyList = baseTelecomCompany;
	$scope.disabilityTypeList = disabilityType;

	$scope.changeApplyType = function(name, index, id) {
		$scope.current = index;
		$scope.stApplyType = {
			"name": name,
			"id": id
		}
	}
	$scope.changeTcType = function(name, index, id) {
		$scope.current2 = index;
		$scope.stTcType = {
			"name": name,
			"id": id
		}
	}
	$scope.changeBordBandSpeed = function(name, index, id) {
		$scope.current3 = index;
		$scope.stBordBandSpeed = {
			"name": name,
			"id": id
		}
	}
	$scope.changeBaseTelecomCompany = function(name, index, id) {
		$scope.current4 = index;
		$scope.stBaseTelecomCompany = {
			"name": name,
			"id": id
		}
	}
	$scope.changeDisabilityType = function(name, index, id) {
		$scope.current5 = index;
		appData.stDisabilityType = {
			"name": name,
			"id": id
		}
	}
	$scope.prevStep = function() {
		$state.go("info");
	}
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.stIdCode)) {
				$scope.isAlert = true;
				$scope.msg = "请输入证件号码！";
				return;
			}
			if(isBlank($scope.disabilityId)) {
				$scope.isAlert = true;
				$scope.msg = "请输入残疾人证号！";
				return;
			}
			if(!isPhoneAvailable($scope.stPhoneNum)) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确手机号码！";
				return;
			}
			if(isBlank($scope.stApplyType)) {
				$scope.isAlert = true;
				$scope.msg = "请选择申请类型！";
				return;
			}
			if(isBlank($scope.stTcType)) {
				$scope.isAlert = true;
				$scope.msg = "请选择套餐类别！";
				return;
			}
		} while (condFlag);
		condFlag = true;
		if(isBlank($scope.stBordBandSpeed)) {
			$scope.stBordBandSpeed = {
				"id": "",
				"name": "",
			}
		}
		if(isBlank($scope.stBaseTelecomCompany)) {
			$scope.stBaseTelecomCompany = {
				"id": "",
				"name": "",
			}
		}
		//提交参数集合
		$scope.isLoading = true;
		appData.disabilityId = $scope.disabilityId;
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
				"hjregion": appData.hjCounty.key,
				"hjneighborhood": appData.hjStreet.key,
				"jzprovince": appData.jzProvince.key,
				"jzcity": appData.jzCity.key,
				"jzregion": appData.jzCounty.key,
				"jzneighborhood": appData.jzStreet.key
			},
			"ywtbAffairsapply": {
				"platform": "4",
				"affairscode": appData.ywlx,
				"affairsname": appData.funName,
				"itemcode": "312090154000",
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
					"idCode": $scope.stIdCode,
					"phoneNum": $scope.stPhoneNum,
					"applyType": $scope.stApplyType.id,
					"tcType": $scope.stTcType.id,
					"kdsbz": $scope.stKdsbz || "",
					"bordBandSpeed": $scope.stBordBandSpeed.id,
					"installAddr": $scope.stInstallAddr || "",
					"baseTelecomCompany": $scope.stBaseTelecomCompany.id,
					"archivesdata": [],
					"display": {
						"证件号码": $scope.stIdCode,
						"手机号码": $scope.stPhoneNum,
						"申请类型": $scope.stApplyType.name,
						"套餐类别": $scope.stTcType.name,
						"宽带设备号": $scope.stKdsbz || "",
						"宽带速率": $scope.stBordBandSpeed.name,
						"安装地址": $scope.stInstallAddr || "",
						"基础电信公司": $scope.stBaseTelecomCompany.name,
					}
				}
			}
		}
		console.log($scope.applicationInfo);
		if($scope.stBordBandSpeed.name == "50M") {
			appData.broadbandServiceSub = "broadbandServiceSub50";
		} else if($scope.stBordBandSpeed.name == "300M") {
			appData.broadbandServiceSub = "broadbandServiceSub300";
		}
		if($scope.stBaseTelecomCompany.name == "上海移动") {
			appData.phoneService = "Mobile";
		} else if($scope.stBaseTelecomCompany.name == "上海电信") {
			appData.phoneService = "Telecommunications";
		} else if($scope.stBaseTelecomCompany.name == "上海联通") {
			appData.phoneService = "Unicom";
		}
		appData.applicationInfo = $scope.applicationInfo;
		$scope.uploadFile1 = function() {
			//上传材料
			appFactory.upload_file(appData.encrypt_identity||appData.licenseNumber, appData.licenseName, appData.VALIDSTARTDAY, appData.VALIDENDDAY, '310105109000100', "CL00330001", "中华人民共和国居民身份证", function(dataJson) {
				if(dataJson.data == "" || dataJson.data == null || dataJson.data == undefined || dataJson.isSuccess == false) {
					layer.msg("未能从电子证照获取到身份证照上传");
					$timeout(function() {
						$state.go("materialList");
					}, 100)
				}
				$scope.imgUrl = "data:image/jpeg;base64," + dataJson.data[0].str;
			}, function(dataJson1) {
				if(dataJson1.code == "0") {
					try {
						appData.isUpload.push({
							index: 0,
							stuffName: "中华人民共和国居民身份证",
							img: $scope.imgUrl || "",
							status: 1,
							method: "高拍仪",
							imgId: dataJson1.data.attachid,
							archivescode: "CL00330001"
						});
					} catch(e) {}
				} else {
					layer.msg("未能从电子证照获取到身份证照上传");
				}
				$timeout(function() {
					$state.go("materialList");
				}, 100)
			});
		}
		$scope.uploadFile2 = function() {
			//上传材料
			appFactory.upload_file(appData.encrypt_identity||appData.licenseNumber, appData.licenseName, appData.VALIDSTARTDAY, appData.VALIDENDDAY, '310101360000100', "CL00330003", "中华人民共和国残疾人证", function(dataJson) {
				if(dataJson.data == "" || dataJson.data == null || dataJson.data == undefined || dataJson.isSuccess == false) {
					layer.msg("未能从电子证照获取到残疾人证照上传");
					$timeout(function() {
						$scope.uploadFile1();
					}, 100)
				}
				$scope.imgUrl = "data:image/jpeg;base64," + dataJson.data[0].str;
			}, function(dataJson1) {
				if(dataJson1.code == "0") {
					try {
						appData.isUpload.push({
							index: 2,
							stuffName: "中华人民共和国残疾人证",
							img: $scope.imgUrl || "",
							status: 1,
							method: "高拍仪",
							imgId: dataJson1.data.attachid,
							archivescode: "CL00330003"
						});
					} catch(e) {}
				} else {
					layer.msg("未能从电子证照获取到残疾人证照上传");
				}
				$timeout(function() {
					$scope.uploadFile1();
				}, 100)
			});
		}
		$scope.uploadFile2();
	}
});
app.controller("takePhoto", function($scope, $http, $state, $rootScope, appData, $timeout, appFactory) {
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();
	$scope.funName = appData.funName;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$.device.cmCaptureShow(680, 530, 190, 300);
		$scope.isAlert = false;
	}
	$scope.finish = [];
	$scope.prevText = "返回";
	$scope.nextText = "确认上传";
	$scope.isLoading = true;
	$.device.cmCaptureShow(680, 530, 190, 300);
	$.device.cmCaptureSelectRect(0, 0, 1920, 1920);
	$scope.isFinish = false;
	// 拍照
	var imgHTML = "";
	var imgIndex = 0;
	var scanImg1 = "";
	$scope.next = function() {
		var scanImg = $.device.cmCaptureCaptureUrl();
		scanImg1 = $.device.cmCaptureCaptureBase64();
		if(scanImg == undefined) {
			$scope.isAlert = true;
			$scope.msg = "请聚焦并对准材料后再拍照";
			$scope.alertConfirm = function() {
				$scope.isLoading = true;
				$scope.isAlert = false;
			}
		} else {
			$scope.jsonData1 = {
				archivescode: appData.archivescode,
				affairscode: appData.affairscode,
				archivesname: appData.stStuffName,
				needflag: appData.needflag,
				attachtype: ""
			};
			$scope.jsonData1 = JSON.stringify($scope.jsonData1);
			$scope.isLoading = false;
			$.device.cmCaptureHide();
			$.device.httpUpload($.getConfigMsg.preUrlSelf + '/selfapi/civilService/uploadArchiveInfo.do', "img", scanImg,
				$scope.jsonData1,
				function(result) {
					$scope.isLoading = true;
					result = JSON.parse(result);
					if(result.code == "0") {
						$scope.isAlert = true;
						$scope.msg = "上传成功";
						appData.uploadStuffId = result.data.attachid; //dataJson.appData.stuffId  ;
						if(appData.stStuffName == "1、居民身份证") {
							appData.imgId1 = result.data.attachid;
						} else {
							appData.imgId2 = result.data.attachid;
						}
						if(appData.isUpload[appData.currentIndex]) {
							appData.isUpload[appData.currentIndex] = "";
						}
						$scope.finish.push({
							index: appData.currentIndex,
							stuffName: appData.stStuffName,
							img: scanImg,
							status: 0,
							method: "高拍仪",
							imgId: result.data.attachid,
							archivescode: appData.archivescode
						});
						imgHTML += '<div class="img" id="' + appData.uploadStuffId + '"><img src="' + scanImg + '" width="150" height="200" /></div>';
						$('.imgBox').html(imgHTML);
						$scope.isFinish = true;
						$scope.alertConfirm = function() {
							$scope.finishUpload();
						}
					} else {
						$scope.isAlert = true;
						$scope.msg = "上传失败,请重试";
					}
				},
				function(webexception) {
					$scope.isLoading = true;
					$scope.isAlert = true;
					$scope.msg = "上传失败,请重试";
				});
		}
	};
	//取下标
	$scope.indexVf = function(array, str) {
		for(var i = 0; i < array.length; i++) {
			if(array[i] = str) {
				return i;
			}
		}
	}
	// 完成拍照
	$scope.finishUpload = function() {
		for(var i = 0; i < appData.isUpload.length; i++) {
			if(appData.currentIndex == appData.isUpload[i].index) {
				appData.isUpload[i] = "";
			}
		}
		for(var i = 0; i < $scope.finish.length; i++) {
			appData.isUpload.push($scope.finish[i]);
		}
		$.device.cmCaptureHide(); // 关闭高拍仪
		$state.go("materialList");
	};

	$scope.last = function() {
		$.device.cmCaptureHide(); // 关闭高拍仪
		$state.go("materialList");
	}
});
app.controller("materialList", function($scope, $state, $http, appData, $timeout, appFactory) {
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoading = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go('materialList');
	}
	$scope.nextText = "提交";
	$scope.funName = appData.funName;
	//必传材料列表
	appData.currentIndex = 0;
	$scope.mustUpload = [];
	$scope.current = 0;
	//设置上传文件 按钮变化
	$scope.btn = function() {
		appData.listImg = [{
			'index': 0,
			'stuffName': "中华人民共和国居民身份证",
			'upload': true,
			'upload2': false,
			'upload3': false,
			"affairscode": "CL0033",
			"archivescode": "CL00330001",
			"needflag": "0"
		}, {
			'index': 1,
			'stuffName': "户口簿",
			'upload': true,
			'upload2': false,
			'upload3': false,
			"affairscode": "CL0033",
			"archivescode": "CL00330002",
			"needflag": "0"
		}, {
			'index': 2,
			'stuffName': "中华人民共和国残疾人证",
			'upload': true,
			'upload2': false,
			'upload3': false,
			"affairscode": "CL0033",
			"archivescode": "CL00330003",
			"needflag": "0"
		}];
		if(appData.isUpload != "") {
			for(var i = 0; i < appData.isUpload.length; i++) {
				for(var j = 0; j < appData.listImg.length; j++) {
					console.log(appData.isUpload[i].status);
					if(appData.isUpload[i].status == 1) {
						if(appData.listImg[j].upload != false) {
							if(appData.isUpload[i].stuffName == appData.listImg[j].stuffName) {
								console.log(i);
								appData.listImg[j].upload = false;
								appData.listImg[j].upload2 = true;
							}
						}
					} else if(appData.isUpload[i].status == 0) {
						if(appData.listImg[j].upload != false) {
							if(appData.isUpload[i].stuffName == appData.listImg[j].stuffName) {
								appData.listImg[j].upload = false;
								appData.listImg[j].upload3 = true;
							}
						}
					}
				}
			}
		}
		$scope.listImg = appData.listImg;
	}
	$scope.btn();

	// 材料上传
	appData.currentIndex++;
	$scope.toUploadMaterial = function(index, name, affairscode, archivescode, needflag) {
		appData.stStuffName = name;
		appData.affairscode = affairscode;
		appData.archivescode = archivescode;
		appData.needflag = needflag;
		appData.currentIndex = index;
		appData.stuffImg = appData.listImg[appData.currentIndex];
		$state.go("takePhoto");
	}
	//查看
	$scope.view = function() {
		appData.currentIndex = 0;
		appData.view = appData.isUpload;
		$state.go("materialView");
	}
	$scope.prevStep = function() {
		$state.go("applyInfo");
	}
	//提交办件
	$scope.submit = function() {
		$scope.isLoading = true;
		$scope.archivesdata = [];
		for(let i = 0; i < appData.isUpload.length; i++) {
			if(!isBlank(appData.isUpload[i])) {
				$scope.archivesdata.push({
					"archivescode": appData.isUpload[i].archivescode,
					"affirscode": "CL0033",
					"archivesname": appData.isUpload[i].stuffName,
					"needflag": "0",
					"imgscans": appData.isUpload[i].imgId
				})
			}
		}
		appData.applicationInfo.archivesdata = $scope.archivesdata;
		$state.go("signature");
	};

});
app.controller("signature", function($scope, $http, $rootScope, appData, $state, appFactory) {
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.$watch('$viewContentLoaded', function(newValue, oldValue) {
		$scope.funName = appData.funName;
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
		console.log(appData.applicationInfo);
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/civilService/sendYwtbApplyInfo.do",
			dataType: "json",
			data: {
				jsonStr: JSON.stringify(appData.applicationInfo)
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
	$scope.uploadStuff = function(id) {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + '/selfapi/CivilServiceController/PDFDownload.do',
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data: {
				documentId: id
			},
			success: function(dataJsonp) {
				try {
					let img = dataJsonp.data;
					let formdata = new FormData();
					formdata.append("archivescode", "CL0033_DJB");
					formdata.append("affairscode", "CL0033");
					formdata.append("archivesname", "上海市听力、言语障碍者信息服务办理单");
					formdata.append("needflag", '0');
					formdata.append("img", img);
					formdata.append("attachtype", "pdf");
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
								appData.applicationInfo.archivesdata.push({
									"archivescode": "CL0033_DJB",
									"affirscode": "CL0033",
									"archivesname": "上海市听力、言语障碍者信息服务办理单",
									"needflag": "0",
									"imgscans": appData.imgId
								})
							} catch(e) {}
							$scope.submit();
						},
						error: function(err) {
							console.log(err);
						}
					});
				} catch(e) {}
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	//生成pdf
	$scope.getlicensePDF = function() {
		$scope.isLoading = true;
		let formdata = new FormData();
		formdata.append("name", encodeURI(appData.licenseName));
		formdata.append("sex", encodeURI(appData.stSex));
		formdata.append("birthdayYear", (appData.licenseNumber).substring(6, 10));
		formdata.append("birthdayMonth", (appData.licenseNumber).substring(10, 12));
		formdata.append("birthdayDay", (appData.licenseNumber).substring(12, 14));
		formdata.append("disabilityId", appData.disabilityId);
		formdata.append("speech", (appData.stDisabilityType.id == "01" ? "on" : "off"));
		formdata.append("hearing", (appData.stDisabilityType.id == "02" ? "on" : "off"));
		formdata.append("domicile", encodeURI(appData.hjCity.value + appData.hjCounty.value + appData.hjStreet.key));
		formdata.append("idCardNo", appData.licenseNumber);
		formdata.append("address", encodeURI(appData.jzCity.value + appData.jzCounty.value + appData.jzStreet.key));
		formdata.append("postcode", "200000");
		formdata.append("mobilePhoneNumber", appData.applicationInfo.ywtbAffairsapply.formdata.phoneNum);
		formdata.append("contactNumber", appData.applicationInfo.ywtbAffairsapply.formdata.phoneNum);
		formdata.append("installationAddress", appData.applicationInfo.ywtbAffairsapply.formdata.installAddr);
		formdata.append("broadbandEquipmentNumber", appData.applicationInfo.ywtbAffairsapply.formdata.kdsbz);
		formdata.append("guardianName", "");
		formdata.append("guardianTel", "");
		formdata.append("relationship", "");
		formdata.append("phoneService", appData.phoneService);
		formdata.append("broadbandServiceSub", appData.broadbandServiceSub);
		formdata.append("seal", appData.picStr);
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/CivilServiceController/creatApplyPDF.do",
			data: formdata,
			cache: false, // 不缓存
			processData: false, // jQuery不要去处理发送的数据
			contentType: false,
			success: function(dataJson) {
				let result = JSON.parse(dataJson);
				if(result.success == true) {
					appData.documentId = result.data.documentId;
					$scope.uploadStuff(appData.documentId);
				}
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
		$state.go("/materialList");
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
	trackEventForAffairs($scope.applyNo, $scope.funName, "上海市残疾人联合会", appData.licenseName, appData.licenseNumber, appData.stMobile);
	var date = new Date();
	var month = date.getMonth() + 1;
	$scope.date = date.getFullYear() + "年" + month + "月" + date.getDate() + "日" + date.getHours() + "时" + date.getMinutes() + "分";
	$scope.nextText = "返回首页";
	$scope.nextStep = function() {
		$.device.GoHome();
	}
});