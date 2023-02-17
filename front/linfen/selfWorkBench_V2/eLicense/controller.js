function removeAnimate(ele) {
	//	$(ele).css({
	//		"transform": "translateY(0px)",
	//		"top": 0
	//	}).removeClass('transformto')
}

function addAnimate(ele) {
	$(ele).css({
		'margin-top': '300px',
		'opacity': '0'
	});
	$(ele).animate({
		marginTop: '0',
		opacity: '1'
	}, 1000);
}
app.controller("licenseMain", function($scope, $state, appData) {
	// $.getConfigMsg.preUrl = "http://180.169.7.194:8081/ac-self"
	// $.getConfigMsg.preUrlSelf = "http://180.169.7.194:8081/ac-self"
	$scope.isCommunity = $.getConfigMsg.isCommunity == "N" ? true : false;
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.operation = "请选择证照类型";
	$scope.choiceType = function(license) {
		console.log(license)
		appData.licenseType = license;
		$state.go("loginType");
	}
});
app.controller('licenseLoginType', function($state, $scope, appData) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.operation = "请选择登录方式";
	appData.encrypt_identity = "";
	$scope.licenseType = appData.licenseType;
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller('licenseLogin', function($scope, $http, $state, appData, appFactory) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	//重置数据
	appData.elicenseData = []; //电子证照库电子证照
	appData.authorizedLicenseList = []; //被授权证照容器
	appData.csjLicenseList = []; //长三角证照容器
	$scope.loginBtn = false;
	switch($scope.loginType) {
		case "idcard":
			$scope.operation = "身份证登录";
			break;
		case "ukey":
			$scope.loginBtn = true;
			$scope.operation = "ukey登录";
			break;
		case "cloud":
			$scope.operation = "随申办";
			break;
	}

	//获取查询人手机号
	$scope.getUserInfoByAccessToken = function(token) {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getUserInfoByAccessToken.do",
			dataType: "json",
			//			jsonp: "jsonpCallback",
			data: {
				accessToken: token
			},
			success: function(dataJson) {
				console.log(dataJson);
				if(dataJson != undefined && dataJson != null && dataJson != "") {
					$scope.tipsText = "正在加载数据，请稍候...";
					appData.zwdtsw_link_phone = dataJson.zwdtsw_link_phone;
					$state.go("license");
				}
			},
			error: function(err) {}
		});
	}

	//获取token ------2、比对成功后，根据tokenSNO获取access_token
	$scope.getAccessToken = function(tokenSNO) {
		var rec = $.ajax({
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getAccessToken.do",
			type: "post",
			dataType: "json",
			//					jsonp: "jsonpCallback",
			timeout: 5000,
			data: {
				tokenSNO: tokenSNO,
			},
			success: function(res) {
				console.log(res);
				if(res.SUCCESS === true) {
					$scope.getUserInfoByAccessToken(res.accessToken);
				} else {
					rec.abort();
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

	$scope.caLoginStatus = "";
	$scope.caLogin = function() { //登录
		$scope.caLoginStatus = 'login';
	}
	$scope.caInfo = function(companyName, companyNo) {
		if(companyName && companyNo) {
			appData.licenseNumber = companyNo;
			appData.licenseName = companyName;
			$state.go("license");
			$scope.$apply();
		}
	}

	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
			appData.VALIDENDDAY = info.ValidtermOfEnd;
			appData.VALIDSTARTDAY = info.ValidtermOfStart;
		} else {
			layer.msg("没有获取到")
		}
	}
		
	// $scope.idcardLogin = function() {
	// 	appData.licenseNumber = '429004199312101138';
	// 	appData.licenseName = '肖邦';
	// 	appData.VALIDENDDAY = '2029-02-03';
	// 	appData.VALIDSTARTDAY = '2019-02-03';
	// 	// appData.licenseNumber = '430426199804106174';
	// 	// appData.licenseName = '邹天奇';
	// 	// appData.VALIDENDDAY = '2029-05-13';
	// 	// appData.VALIDSTARTDAY = '2019-05-13';
	// 	$state.go("license");
	// }
	// $scope.idcardLogin();
	$scope.getResult = function(img) {
		$scope.img = img;
		$scope.getAccessToken(appData.tokenSNO)

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
			appData.zwdtsw_link_phone = info.zwdtsw_link_phone;
			appData.encrypt_identity = ClearBr(info.encrypt_identity);
			$state.go("license");
		} else {
			var idcardInfo = info.result.data;
			appData.licenseName = idcardInfo.realname;
			appData.VALIDENDDAY = idcardInfo.VALIDENDDAY;
			appData.VALIDSTARTDAY = idcardInfo.VALIDSTARTDAY;
			if(appData.licenseType == 'person') {
				if(info.url == "") {
					layer.msg("未识别到证照信息！", {
						time: 5000
					});
					$state.reload();
				}
				if(info.code == idcardInfo.idcard) {
					layer.msg("二维码类型错误，请扫描市民亮证个人二维码", {
						time: 2000
					});
					$state.reload();
				} else {
					appData.licenseNumber = idcardInfo.idcard;
					$state.go("license");
					$scope.$apply();
				}
			} else if(appData.licenseType == 'corporate') {
				if(info.code !== idcardInfo.idcard) {
					layer.msg("未识别到您的企业信息，请确认二维码正确后重新扫描！", {
						time: 5000
					});
					$state.reload();
				} else {
					appData.licenseNumber = info.code;
					$state.go("license");
					$scope.$apply();
				}
			}
		}
	}
});
// 处理数据 csj证照
function csjLicenseListreturnData(data) {
	var gifFile, pdfbase64File, pdfFile, dataFileSun;
	dataFileSun = data;

	function pingDataReturn(dataPing) {
		try {
			var machineIdS = "&machineId=" + jQuery.getConfigMsg.uniqueId || '';
			var fileFormatS = "&fileFormat=" + dataPing.fileFormat;
			var urlS = "url=" + encodeURIComponent(encodeURIComponent(dataPing.url));
			var typeS = '&type=byte';
			// gif
			var certificateTypeS = "certificateType=";
			var certificateNumberS = "&certificateNumber=" + dataPing.certificateNumber;
			var urlSgif = "&url=" + encodeURIComponent(encodeURIComponent(dataPing.url));
			var watermarkS = "&watermark=" + encodeURI(encodeURI('一网通办自助终端'));
		} catch (e) {}
		try {
			var returnData = {};
			returnData.pdfFile = "/selfapi/GBElecCert/getCertFile.do?" + urlS + fileFormatS + machineIdS + typeS;
			returnData.pdfbase64File = "/selfapi/GBElecCert/getCertFile.do?" + urlS + fileFormatS + machineIdS;
			returnData.gifFile = "/selfapi/GBElecCert/getThumbnail.do?" + certificateTypeS + certificateNumberS +
				urlSgif + fileFormatS + watermarkS + machineIdS + typeS;
		} catch (e) {}
		return returnData;
	}
	if (data.length > 0) {
		for (var m = 0; m < data.length; m++) {
			dataFileSun[m].derivePictureUrlForBytes = pingDataReturn(data[m]).pdfFile;
			dataFileSun[m].derivePictureUrl = pingDataReturn(data[m]).pdfbase64File;
			dataFileSun[m].pictureUrlForBytes = pingDataReturn(data[m]).gifFile;
		}
	}
	console.log(dataFileSun);
	return dataFileSun;
}
app.controller("licenseLicense", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.operation = (appData.licenseType == 'person') ? "我的证照" : "法人电子证照";
	$scope.licenseNumber = appData.licenseNumber; // 号码
	$scope.licenseType = (appData.licenseType == 'person') ? '0' : '1'; //法人 1 个人0
	$scope.currentPage = 1; //当前页
	$scope.totalPages = 1; //总页数
	$scope.showLicenseList = []; //展示的图片容器
	$scope.elicenseData = []; //电子证照库电子证照
	$scope.authorizedLicenseList = []; //被授权证照容器
	$scope.csjLicenseList = []; //长三角证照容器
	$scope.currentLicense = appData.type || 'license'; //现在证照类型
	$scope.currentImgIndex = null; //现在选择图片下标
	$scope.electImg = ''; //当前选中图片地址
	$scope.isShowPrint = false; //是否显示打印弹框
	$scope.isShowView = false; // 是否显示预览框
	$scope.zoomCount = 1;
	$scope.rotateCount = 0;
	$scope.reLoadCount = 5;
	$scope.isAlert = false;
	$scope.concel = "false";
	appData.type = "license";
	$scope.isLoding = false;
	$scope.alertConfirm = function() {

	}
	$scope.alertCancel = function() {

	}
	$scope.pitchOnImg = function(i, item) {
		if(item) {
			$scope.currentImgIndex = i;
			appData.currentLicense = $scope.currentLicense
			$scope.isShowView = true;
			$scope.certName = item.certName;
			appData.certName = $scope.certName;
			if($scope.currentLicense == "license" || $scope.currentLicense == "queryeles") {
				$scope.electImg = item.imgBase64;
				$scope.pdfBase64Url = '/selfapi/electronicCertificate/showStuffPicForBase64.do?certUuid=' + item.certUuid + '&machineId=' + $.config.get('uniqueId');
				$scope.pdfLicense = '/selfapi/electronicCertificate/showStuffPicForBytes.do?certUuid=' + item.certUuid + '&machineId=' + $.config.get('uniqueId');
			} else {
				$scope.electImg = (item.pictureUrlForBytes ? item.pictureUrlForBytes : item.imageUrl);
				//base64
				$scope.pdfBase64Url = item.derivePictureUrl;
				$scope.pdfLicense = item.derivePictureUrlForBytes;
			}
			appData.pdfBase64Url = $scope.pdfBase64Url;
			appData.pdfLicense = $scope.pdfLicense;
			appData.previewImg = $scope.electImg;
			$state.go("preview");
		}
	}
	//选择证照
	$scope.choiceLicenseType = function(type) {
		$scope.currentImgIndex = null;
		$scope.currentLicense = type;
		$scope.currentPage = 1;
		$scope.showLicenseList = [];
		if(type == 'license') {
			appData.type = "license";
			if($scope.elicenseData.length > 0) {
				$scope.showLicenseList = $scope.elicenseData.slice(0, 3);
			} else {
				$scope.getLicenseList();
			}
		} else if(type == 'queryeles') {
			appData.type = "queryeles";
			if($scope.authorizedLicenseList.length > 0) {
				$scope.showLicenseList = $scope.authorizedLicenseList.slice(0, 3);
			} else {
				$scope.getAuthorizedLicenseList();
			}
		} else if(type == 'csjLicense') {
			appData.type = "csjLicense";
			if($scope.csjLicenseList.length > 0) {
				$scope.showLicenseList = $scope.csjLicenseList.slice(0, 3);
			} else {
				$scope.getCsjLicenseList();
			}
		}
	}
	//图片地址
	$scope.configUrl = $.getConfigMsg.preUrlSelf;
	//查询您的长三角证照
	$scope.getCsjLicenseList = function() {
		$scope.isLoding = false;
		try {
			$.ajax({
				type: "get",
				url: $.getConfigMsg.preUrlSelf + "/selfapi/GBElecCert/queryCertBaseDataForGb.do",
				dataType: 'json',
//				jsonp: "jsonpCallback",
				data: {
					machineId: $.config.get('uniqueId'),
					CertificateHolderCode: appData.licenseNumber,
					name: encodeURI(appData.licenseName),
					startDay: appData.VALIDSTARTDAY,
					endDay: appData.VALIDENDDAY,
					businessCode: '', //"0" ||
					CertificateType: '',
					itemName: '',
					itemCode: '',
					watermark: encodeURI('一网通办自助终端')
				},
				success: function(dataJsonp) {
					$scope.isLoding = true;
					if(dataJsonp.success == true) {
						$scope.csjLicenseList = csjLicenseListreturnData(dataJsonp.data);
						$scope.showLicenseList = $scope.csjLicenseList.slice(0, 3);
						appData.csjLicenseList = $scope.csjLicenseList;
					}
				},
				error: function(err) {
					console.log(err);
				}
			});
		} catch(e) {}
	}

	//查询被是否有被授权证照
	$scope.getAuthorizedLicenseList = function() {
		$scope.isLoding = false;
		try {
			$.ajax({
				type: "get",
				url: $.getConfigMsg.preUrlSelf + "/selfapi/electronicCertificate/queryeles.do",
				dataType: 'json',
				//				jsonp: "jsonpCallback",
				data: {
					certNo: appData.encrypt_identity || appData.licenseNumber, //"340881199303145313" ||
					phone: appData.zwdtsw_link_phone || "", //"0" ||
					name: encodeURI(appData.licenseName),
					startDay: appData.VALIDSTARTDAY,
					endDay: appData.VALIDENDDAY,
					use: encodeURI('一网通办自助终端'),
					licenseType: "cert"
				},
				success: function(dataJsonp) {
					$scope.isLoding = true;
					if(dataJsonp.success == true && dataJsonp.data.CODE == 0) {
						$scope.authorizedLicenseList = dataJsonp.data.certArrs;
						appData.authorizedLicenseList = $scope.authorizedLicenseList;
						$scope.showLicenseList = $scope.authorizedLicenseList;
						for (var i = 0; i < $scope.showLicenseList.length; i++) {
							$scope.previewImgThree(i, $scope.showLicenseList[i].certUuid);
						}
					}
				},
				error: function(err) {
					console.log(err);
				}
			});
		} catch(e) {}
	}

	//预览图三张的base64
	$scope.previewImgThree = function(i, certUuid) {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/electronicCertificate/showElectronicCertificate.do",
			data: {
				fileFormat: 'String',
				machineId: $.config.get('uniqueId'),
				itemName: "",
				itemCode: "",
				businessCode: "",
				certUuid: certUuid,
			},
			dataType: 'json',
//			jsonp: "jsonpCallback",
			success: function(res) {
				try {
					$scope.showLicenseList[i].imgBase64 = 'data:image/jpeg;base64,' + res.data.str;
				} catch(e) {
					console.log(e);
				}
			},
			error: function(err) {
				console.log(err);
			}
		});
	}

	$scope.reLoadCount = 5;
	//上海市证照
	$scope.getLicenseList = function() { //电子证照库数据
		$rootScope.router = undefined;
		var httpConfig = {
			jsonpCallback: "JSON_CALLBACK",
			certNo: appData.encrypt_identity || $scope.licenseNumber, //"340881199303145313" ||
			type: $scope.licenseType, //"0" ||
			machineId: $.config.get('uniqueId'),
			itemName: "",
			itemCode: "",
			businessCode: "",
			name: encodeURI(appData.licenseName),
			startDay: appData.VALIDSTARTDAY,
			endDay: appData.VALIDENDDAY,
			licenseType: "cert"
		};
		$timeout(function() {
			$http.jsonp($.getConfigMsg.preUrlSelf + "/selfapi/electronicCertificate/queryCertBaseData.do", {
					params: httpConfig
				})
				.success(function(data) {
					if(data.data) {
						$scope.isLoding = true;
						$scope.elicenseData = data.data;
						appData.elicenseData = $scope.elicenseData;
						$scope.showLicenseList = $scope.elicenseData.slice(0, 3);
						for(var i = 0; i < $scope.showLicenseList.length; i++) {
							$scope.previewImgThree(i, $scope.showLicenseList[i].certUuid);
						}
					}
					$scope.isLoding = true;
				})
				.error(function(err) {
					if($scope.reLoadCount > 1) {
						$scope.getLicenseList();
					}
					$scope.reLoadCount--;
				})
		})
	};
	$scope.queryAll = function() {
		appData.elicenseData = $scope.elicenseData;
		appData.currentLicense = $scope.currentLicense;
		$state.go("licenseList");
	};

	//返回当前页
	if(appData.type == 'license') {
		if(appData.elicenseData.length > 0) {
			$scope.isLoding = true;
			$scope.elicenseData = appData.elicenseData;
			$scope.showLicenseList = $scope.elicenseData.slice(0, 3);
		} else {
			$scope.getLicenseList();
		}
	} else if(appData.type == 'queryeles') {
		if(appData.authorizedLicenseList.length > 0) {
			$scope.isLoding = true;
			$scope.authorizedLicenseList = appData.authorizedLicenseList;
			$scope.showLicenseList = $scope.authorizedLicenseList.slice(0, 3);
		} else {
			$scope.getAuthorizedLicenseList();
		}
	} else if(appData.type == 'csjLicense') {
		if(appData.authorizedLicenseList.length > 0) {
			$scope.csjLicenseList = appData.csjLicenseList;
			$scope.showLicenseList = $scope.csjLicenseList.slice(0, 3);
		} else {
			$scope.getCsjLicenseList();
		}
	} else {
		$scope.getLicenseList();
	}
	addAnimate($('.licenseContainer'))
	$scope.prevStep = function() {
		$state.go("loginType");
	}
});
app.controller("licenseList", function($scope, $state, appData, $timeout) {
	$scope.operation = "我的证照";
	$scope.licenseList = []; //展示的证照列表
	$scope.currentImgIndex = null;
	$scope.currentLicense = appData.type || "license";
	appData.sign = "list"; // 标志   :查看过列表
	$scope.isLoding = true;
	//查询您的长三角证照
	$scope.getCsjLicenseList = function() {
		$scope.isLoding = false;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/GBElecCert/queryCertBaseDataForGb.do",
			dataType: 'json',
//			jsonp: "jsonpCallback",
			data: {
				machineId: $.config.get('uniqueId'),
				CertificateHolderCode: appData.licenseNumber,
				name: encodeURI(appData.licenseName),
				startDay: appData.VALIDSTARTDAY,
				endDay: appData.VALIDENDDAY,
				businessCode: '', //"0" ||
				CertificateType: '',
				itemName: '',
				itemCode: '',
				watermark: encodeURI('一网通办自助终端')
			},
			success: function(dataJsonp) {
				$scope.isLoding = true;
				if(dataJsonp.success == true) {
					$scope.csjLicenseList = csjLicenseListreturnData(dataJsonp.data);
					$scope.licenseList = $scope.csjLicenseList;
					appData.csjLicenseList = $scope.csjLicenseList;
				}
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	//查询被是否有被授权证照
	$scope.getAuthorizedLicenseList = function() {
		$scope.isLoding = false;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/DZCert/queryeles.do",
			dataType: 'json',
			//			jsonp: "jsonpCallback",
			data: {
				certNo: appData.encrypt_identity || appData.licenseNumber, //"340881199303145313" ||
				phone: appData.zwdtsw_link_phone || "", //"0" ||
				name: encodeURI(appData.licenseName),
				startDay: appData.VALIDSTARTDAY,
				endDay: appData.VALIDENDDAY,
				use: encodeURI('一网通办自助终端')
			},
			success: function(dataJsonp) {
				$scope.isLoding = true;
				if(dataJsonp.success == true && dataJsonp.data.CODE == 0) {
					$scope.authorizedLicenseList = dataJsonp.data.certArrs;
					$scope.licenseList = $scope.authorizedLicenseList;
					appData.authorizedLicenseList = $scope.authorizedLicenseList;
				}
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	$scope.reLoadCount = 5;
	$scope.getLicenseList = function() { //电子证照库数据
		$rootScope.router = undefined;
		var httpConfig = {
			jsonpCallback: "JSON_CALLBACK",
			certNo: appData.encrypt_identity || $scope.licenseNumber, //"340881199303145313" ||
			type: $scope.licenseType, //"0" ||
			machineId: $.config.get('uniqueId'),
			itemName: "",
			itemCode: "",
			businessCode: "",
			name: encodeURI(appData.licenseName),
			startDay: appData.VALIDSTARTDAY,
			endDay: appData.VALIDENDDAY,
		};
		$timeout(function() {
			$http.jsonp($.getConfigMsg.preUrlSelf + "/selfapi/dzzz/queryCertBaseData.do", {
					params: httpConfig
				})
				.success(function(data) {
					console.log(data.data);
					if(data.data) {
						$scope.isLoding = true;
						$scope.elicenseData = data.data;
						$scope.licenseList = $scope.elicenseData;
						appData.elicenseData = $scope.elicenseData;
					}
					$scope.isLoding = true;
				})
				.error(function(err) {
					if($scope.reLoadCount > 1) {
						$scope.getLicenseList();
					}
					$scope.reLoadCount--;
				})
		})
	};

	if($scope.currentLicense == 'license') {
		if(appData.elicenseData) {
			$scope.licenseList = appData.elicenseData;
		} else {
			$scope.getLicenseList();
		}
	} else if($scope.currentLicense == 'queryeles') {
		if(appData.authorizedLicenseList) {
			$scope.licenseList = appData.authorizedLicenseList;
		} else {
			$scope.getAuthorizedLicenseList();
		}
	} else if($scope.currentLicense == 'csjLicense') {
		if(appData.csjLicenseList) {
			$scope.licenseList = appData.csjLicenseList;
		} else {
			$scope.getCsjLicenseList();
		}
	}
	//选择证照
	$scope.choiceLicenseType = function(type) {
		$scope.currentImgIndex = null;
		$scope.currentLicense = type;
		$scope.currentPage = 1;
		$scope.licenseList = [];
		if(type == 'license') {
			appData.type = "license";
			if(appData.elicenseData) {
				$scope.licenseList = appData.elicenseData;
			} else {
				$scope.getLicenseList();
			}
		} else if(type == 'queryeles') {
			appData.type = "queryeles";
			if(appData.authorizedLicenseList) {
				$scope.licenseList = appData.authorizedLicenseList;
			} else {
				$scope.getAuthorizedLicenseList();
			}
		} else if(type == 'csjLicense') {
			appData.type = "csjLicense";
			if(appData.csjLicenseList) {
				$scope.licenseList = appData.csjLicenseList;
			} else {
				$scope.getCsjLicenseList();
			}
		}
	}
	$scope.choice = function(certName, electImg, pdfLicense, derivePictureUrl, certUuid) {
		appData.certName = certName;
		console.log(appData.currentLicense);
		if(appData.currentLicense == "license" || appData.currentLicense == "queryeles") {
			appData.pdfBase64Url = '/selfapi/electronicCertificate/showStuffPicForBase64.do?certUuid=' + certUuid + '&machineId=' + $.config.get('uniqueId');
			appData.pdfLicense = '/selfapi/electronicCertificate/showStuffPicForBytes.do?certUuid=' + certUuid + '&machineId=' + $.config.get('uniqueId');
			appData.certUuid = certUuid;
		} else {
			appData.previewImg = electImg;
			appData.pdfLicense = pdfLicense;
			appData.pdfBase64Url = derivePictureUrl;
		}
		$state.go("preview");
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}
});
app.controller("elicensePreview", function($scope, $state, appData, $timeout, $http) {
	$scope.operation = "电子证照预览";
	$scope.configUrl = $.getConfigMsg.preUrlSelf;
	$scope.certName = appData.certName;
	$scope.pdfLicense = appData.pdfLicense;
	if(appData.certUuid) {
		$scope.isLoding = false;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/electronicCertificate/showElectronicCertificate.do",
			data: {
				fileFormat: 'String',
				machineId: $.config.get('uniqueId'),
				itemName: "",
				itemCode: "",
				businessCode: "",
				certUuid: appData.certUuid,
			},
			dataType: 'json',
//			jsonp: "jsonpCallback",
			success: function(res) {
				$scope.isLoding = true;
				try {
					$scope.previewImg = 'data:image/jpeg;base64,' + res.data.str;
				} catch(e) {
					console.log(e);
				}
			},
			error: function(err) {
				console.log(err);
			}
		});
	}else if(appData.currentLicense == 'license' || appData.currentLicense == 'queryeles') {
		$scope.previewImg = appData.previewImg;
	} else {
		$scope.isLoding = true;
		$scope.previewImg = $scope.configUrl + appData.previewImg;
	}
	//base64
	$scope.InPdfBase64Url = $scope.configUrl + appData.pdfBase64Url;
	$scope.getPdfBase64 = function() {
		$scope.isLoding = false;
		if(appData.pdfBase64Url) {
			$http({
				url: $scope.InPdfBase64Url,
				method: 'GET'
			}).success(function(data) {
				if(data.success == true) {
					$scope.isLoding = true;
					appData.printpdfBase64 = data.data.str;
				} else {
					layer.msg("返回base64有误");
				}
			}).error(function(data) {
				layer.msg("返回base64接口异常");
			});
		}
	}
	if(appData.currentLicense == 'csjLicense') {
		appData.printpdfBase64 = appData.pdfBase64Url;
	} else {
		$scope.getPdfBase64();
	}

	$scope.isAllScreen = false;
	$scope.isPrint = false;
	//图片显示
	var viewer = new Viewer(document.getElementById('jq22'), {
		url: 'data-original',
	});
	$scope.prevStep = function() {
		$state.go("licenseList");
	};
	$scope.banTrigger=true;
	$scope.print = function() {
		console.log($scope.configUrl + $scope.pdfLicense);
		console.log(appData.printpdfBase64);
		$scope.isPrint = 'show';
		$scope.timestamp = Date.parse(new Date());
		$scope.path = "D:\\pdfPrint.pdf";
		if($scope.banTrigger){
			$scope.banTrigger = false;
		}else{
			return
		}
		$scope.filePath = "C:/" + $scope.timestamp + ".pdf";
		if(jQuery.getConfigMsg.isPrintBase64 == 'Y' || acBridgeMac.vendor() == 'jtdevice') {
			if(appData.printpdfBase64 || acBridgeMac.vendor() == 'jtdevice') {
				//jtdevice 打印url
				$.device.urlPdfPrint($scope.configUrl + $scope.pdfLicense, $scope.path, function() {
					saveDeviceStatus("A4Printer", 0, "正常", 0, 0, 0, 3);
				}, appData.printpdfBase64);
			} else {
				layer.msg("返回base64有误,请重试！！！");
			}
		}
		if($scope.certName === "中华人民共和国居民身份证") {
			LODOP_PRINT.personLicense($scope.previewImg);
			saveDeviceStatus("A4Printer", 0, "正常", 0, 0, 0, 1);
		} else {
			$.device.urlPdfPrint($scope.configUrl + $scope.pdfLicense, $scope.path, function() {
				saveDeviceStatus("A4Printer", 0, "正常", 0, 0, 0, 3);
			});
		}
		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: $scope.certName,
			}
		}
		recordUsingHistory('我的证照', '查询+打印', $scope.certName, appData.licenseName, appData.licenseNumber, '', '', JSON.stringify($scope.jsonStr));
		$timeout(function() {
			$state.go("licenseList");
		}, 5000);
	};
});