app.controller('loginType', function($state, $scope, appData, $location, appFactory) {
	$scope.funName = "户籍人户分离人员居住登记注销";
	appData.funName = $scope.funName;
	appData.ywlx = "GA0031";
	$scope.operation = "请选择登录方式";
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		window.location.href = "../publicSecurity/index.html";
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory) {
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
	$scope.nationList=changeListname($scope.nationList,'shortname')
	$scope.hjProvince = filterByInfo($rootScope.allList, "310000");
	$scope.jzProvince = filterByInfo($rootScope.allList, "310000");
	$scope.hjCityList = filterByName($rootScope.allList, '310000');
	$scope.hjCityList=changeListname($scope.hjCityList,'value')
	$scope.jzCityList = filterByName($rootScope.allList, '310000');
	$scope.$watch("nation", function(val) {
		console.log(val.shortname);
	});
	$scope.logOutReasonList = logOutReason;
	$scope.change = function(name, index, id) {
		$scope.current = index;
		$scope.stLogOutReason = name;
		appData.stLogOutReasonName = name;
		appData.stLogOutReasonId = id;
	}
	//监听省市 区域变化
	$timeout(function() {
		selectBlur();
		$scope.$watch("hjCity", function(val) {
			if(val) {
				$scope.hjCountyList = $rootScope.ShangHaiList;
				$scope.hjCountyList=changeListname($scope.hjCountyList,'value')
			}
		});
		$scope.$watch("hjCounty", function(val) {
			if(val) {
				$scope.hjsss='';
				$scope.hjStreetList = filterByName($rootScope.ShangHaiStreetList, val.key);
				$scope.hjStreetList=changeListname($scope.hjStreetList,"value")
			}
		});
		$scope.$watch("jzCity", function(val) {
			if(val) {
				$scope.jzCountyList = $rootScope.ShangHaiList;
				$scope.jzCountyList=changeListname($scope.jzCountyList,"value")
			}
		});
		$scope.$watch("jzCounty", function(val) {
			if(val) {
				$scope.jzsss='';
				$scope.jzStreetList = filterByName($rootScope.ShangHaiStreetList, val.key);
				$scope.jzStreetList=changeListname($scope.jzStreetList,"value");
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
			if(isBlank($scope.stLogOutReason)) {
				$scope.isAlert = true;
				$scope.msg = "请输入注销原因！";
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
		} while (condFlag);
		//提交参数集合
		condFlag = true;
		$scope.isLoading = true;
		$scope.licenseNumber = appData.encrypt_identity || appData.licenseNumber;
		appData.stSex = $scope.stSex;
		appData.dtBirth = $scope.dtBirth;
		appData.stMobile = $scope.stMobile;
		appData.hjProvince = $scope.hjProvince;
		appData.hjCity = $scope.hjCity;
		appData.hjCounty = $scope.hjCounty;
		appData.hjStreet = $scope.hjStreet;
		appData.jzProvince = $scope.jzProvince;
		appData.jzCity = $scope.jzCity;
		appData.jzCounty = $scope.jzCounty;
		appData.jzStreet = $scope.jzStreet;
		appFactory.pro_fetch($scope.licenseNumber, appData.licenseName, appData.funName, appData.VALIDSTARTDAY, appData.VALIDENDDAY, '310105109000100', 'YTGA003101', 'GA0031', '有效身份证明', '1', function(dataJson) {
			if(dataJson == "" || dataJson == null || dataJson == undefined) {
				layer.msg("未能从电子证照获取到身份证照上传");
				$timeout(function() {
					$state.go("materialList");
				}, 100)
			}
			try{
				appData.imgStr = $scope.imgUrl = "data:image/jpeg;base64," + dataJson[0].str;
			}catch(e){
				//TODO handle the exception
			}
		}, function(dataJson1) {
			if(dataJson1.code == "0") {
				try {
					if(appData.isUpload.length <= 0) {
						appData.isUpload.push({
							index: 0,
							stuffName: "1、居民身份证",
							img: $scope.imgUrl,
							status: 1,
							method: "高拍仪"
						});
					}
				} catch(e) {}
				appData.imgId1 = dataJson1.data.attachid;
			}
			$timeout(function() {
				$state.go("materialList");
			}, 1000);
		});
	}
	$scope.prevStep = function() {
		$state.go("loginType");
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
	if(window.innerWidth <= 1600){
		$.device.cmCaptureShow(430, 420, 150, 360);
	}else{
		$.device.cmCaptureShow(680, 530, 190, 300);
	}
	$.device.cmCaptureSelectRect(0, 0, 1920, 1920);
	$scope.isFinish = false;
	// 拍照
	var imgHTML = "";
	var imgIndex = 0;
	var scanImg1 = "";
	$scope.next = function() {
		var scanImg="";
		$.device.cmCaptureCaptureUrl(function(path){
			scanImg = path;
		});
		$.device.cmCaptureCaptureBase64(function(val){
			scanImg1 = "data:image/png;base64,"+val;
		});
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
				attachtype: "",
				archivessource:0
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
							img: scanImg1,
							status: 0,
							method: "高拍仪",
							attachId: result.data.attachid
						});
						imgHTML += '<div class="img" id="' + appData.uploadStuffId + '"><img src="' + scanImg1 + '" width="150" height="200" /></div>';
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
app.controller("materialList", function($scope, $state, $http, appData, $timeout, appFactory, $rootScope) {
	$scope.isAlert = false;
	$scope.concel = "false";
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
			'stuffName': "1、居民身份证",
			'upload': true,
			'upload2': false,
			'upload3': false,
			"affairscode": "GA0031",
			"archivescode": "YTGA003101",
			"needflag": "1"
		}];
		console.log(appData.isUpload);
		console.log(appData.listImg);
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
		console.info(appData.listImg);
		$scope.listImg = appData.listImg;
	}
	$scope.btn();

	console.log(appData.isUpload);

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
		$state.go("info");
	}
	//提交办件
	$scope.submit = function() {
		var flag = false;
		do {
			if(appData.listImg[0].upload == true) {
				$scope.isAlert = true;
				$scope.msg = "请上传居民身份证";
				return;
			}
		} while (flag)

		//获取受理中心
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
		$scope.applicationInfo = {
			"applicant": {
				"idtype": "1",
				"idno": appData.licenseNumber,
				"fullname": appData.licenseName,
				"sex": appData.stSex == "男" ? "1" : "2",
				"birthday": appData.dtBirth,
				"nationality": appData.nationality || "01",
				"cellno": appData.stMobile,
				"hjprovince": appData.hjProvince.key || "310000",
				"hjcity": appData.hjCity.key,
				"hjregion": appData.hjCounty.key,
				"hjneighborhood": appData.hjStreet.key,
				"jzprovince": appData.jzProvince.key || "310000",
				"jzcity": appData.jzCity.key,
				"jzregion": appData.jzCounty.key,
				"jzneighborhood": appData.jzStreet.key
			},
			"ywtbAffairsapply": {
				"platform": "4",
				"affairscode": appData.ywlx,
				"affairsname": appData.funName,
				"itemcode": "312000086000",
				"suid": appData.applyNo,
				"subtime": new Date(),
				"suborgancode": appData.stCenterKey,
				"suborganname": appData.stCenter,
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
				"formdata": {
					"ZXYYDM": appData.stLogOutReasonId,
					"archivesdata": [{
						"archivescode": "YTGA003101",
						"affirscode": "GA0031",
						"archivesname": "身份证",
						"needflag": "1",
						"imgscans": appData.imgId1

					}],
					"display": {
						"注销原因": appData.stLogOutReasonName
					}
				}
			}
		}
		console.log($scope.applicationInfo);
		appData.applicationInfo = $scope.applicationInfo;
		$.log.debug($scope.applicationInfo);
		$scope.isLoading = true;
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/civilService/sendYwtbApplyInfo.do",
			dataType: "json",
			data: {
				jsonStr: JSON.stringify(appData.applicationInfo)
			},
			success: function(dataJson) {
				$scope.isLoading = false;
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
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "提交失败,请重试";
				console.log("sendYwtbApplyInfo err");
			}
		});
	};

});
//材料显示
app.controller("materialView", function($scope, $state, $http, appData, appFactory) {
	$scope.stuffList = []; //所有图片的容器
	$scope.showImgList = []; //显示图片的容器
	$scope.currentPage = 1; //当前页
	$scope.totalPages = 1; //总页数
	$scope.funName = appData.funName;
	//当页显示图片
	$scope.currentList = function(current) {
		if(current === undefined) {
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
		for(var i in $scope.showImgList) {
			$("#jq22").append('<img data-original="' + $scope.showImgList[i].img + '" src="' + $scope.showImgList[i].img + '" alt="">');
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
		for(var i = 0; i < appData.view.length; i++) {
			if(appData.currentIndex == appData.view[i].index) {
				$scope.stuffList.push(appData.view[i]);
				$scope.currentList();
			}
		}
		console.log($scope.stuffList);
		if($scope.stuffList[0].method === "高拍仪") {
			$scope.scanShow = true;
			$scope.upanShow = false;
		} else if($scope.stuffList[0].method === "U盘上传") {
			$scope.scanShow = false;
			$scope.upanShow = true;
		} else if($scope.stuffList[0].method === "个人档案") {
			$scope.scanShow = true;
			$scope.upanShow = false;
		}
	});
	//下一页
	$scope.nextPage = function() {
		if($scope.currentPage < $scope.totalPages) {
			++$scope.currentPage;
			$scope.currentList();
		}
	};
	//上一页
	$scope.prevPage = function() {
		if($scope.currentPage > 1) {
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
		} catch(e) {
			layer.msg("未找到此文件");
		}
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
	recordUsingHistory('公安服务', '办理', $scope.funName, appData.licenseName, appData.licenseNumber, appData.stMobile, $scope.applyNo, JSON.stringify($scope.jsonStr));
	//行为分析(办理)
	trackEventForAffairs($scope.applyNo, $scope.funName, "上海市公安局", appData.licenseName, appData.licenseNumber, appData.stMobile);
	var date = new Date();
	var month = date.getMonth() + 1;
	$scope.date = date.getFullYear() + "年" + month + "月" + date.getDate() + "日" + date.getHours() + "时" + date.getMinutes() + "分";
	$scope.nextText = "返回首页";
	$scope.nextStep = function() {
		$.device.GoHome();
	}
});