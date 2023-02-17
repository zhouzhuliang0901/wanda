function removeAnimate(ele) {
	$(ele).css({
		"transform": "translateY(0px)",
		"top": 0
	}).removeClass('transformto')
}

function addAnimate(ele) {
	$(ele).addClass('transformto').on("animationend", function() {
		$(ele).removeClass('transformto')
	})
}
app.controller("licenseMain", function($scope, $state, appData) {
	$scope.isCommunity = true;
	if($.getConfigMsg.isCommunity == 'Y' || $.getConfigMsg.isCommunity == null || $.getConfigMsg.isCommunity == undefined) {
		$scope.isCommunity = false;
	} else {
		$scope.isCommunity = true;
	}
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
			$scope.operation = "随申办登录";
			break;
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
	$scope.getResult = function(img) {
		$scope.img = img;
		$state.go("license");
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
			appData.licenseNumber = idcardInfo.idcard;
			$state.go("license");
			$scope.$apply();
		}
	}
})
app.controller("licenseLicense", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.operation = (appData.licenseType == 'person') ? "个人电子证照" : "法人电子证照";
	$scope.licenseNumber = appData.licenseNumber; // 号码
	$scope.licenseType = (appData.licenseType == 'person') ? '0' : '1'; //法人 1 个人0
	$scope.currentPage = 1; //当前页
	$scope.totalPages = 1; //总页数
	$scope.showLicenseList = []; //展示的图片容器
	$scope.totalLicense = []; //所有证照容器
	$scope.historyData = []; //历史上传
	$scope.elicenseData = []; //电子证照库电子证照
	$scope.currentLicense = 'license'; //现在证照类型
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
	$scope.currentList = function(current) {
		if(current === undefined) {
			current = $scope.currentPage;
		}
		$scope.currentImgIndex = null;
		$scope.startPos = (current - 1) * 3; //0 -3  3 - 6
		$scope.endPos = $scope.startPos + 3;
		if($scope.currentLicense === 'history' &&
			$scope.totalLicense.indexOf("../libs/common/images/addImg.png") === -1
		) {
			$scope.totalLicense.unshift("../libs/common/images/addImg.png"); //当为历史上传材料时添加上传按钮
		}
		$scope.showLicenseList = $scope.totalLicense.slice($scope.startPos, $scope.endPos);
		$scope.totalPages = Math.ceil($scope.totalLicense.length / 3);
		$scope.showLicenseList.length = 3;
	}
	$scope.pitchOnImg = function(i, item) {
		if(item !== undefined && item.length > 1) {
			console.log("这是添加图片操作");
			$rootScope.router = 'history';
			$state.go("upload");
		}
		if(item) {
			$scope.currentImgIndex = i;
			$scope.stuffId = (item.stPersonalDocument ? item.stPersonalDocument : "");
			$scope.electImg = (item.pictureUrlForBytes ? item.pictureUrlForBytes : item.imageUrl);
			$scope.isShowView = true;
			$scope.certName = item.certName;
			$scope.pdfLicense = item.derivePictureUrlForBytes;
			appData.elicenseData = $scope.elicenseData;
			appData.previewImg = $scope.electImg;
			appData.certName = $scope.certName;
			appData.pdfLicense = $scope.pdfLicense;
			$state.go("preview");
		}
	}
	$scope.choiceLicenseType = function(type) {
		$scope.currentImgIndex = null;
		$scope.currentLicense = type;
		$scope.currentPage = 1;
		$scope.currentList();
		if(type == 'license') {
			appData.type = "license";
			if($scope.elicenseData.length > 0) {
				$scope.totalLicense = $scope.elicenseData.slice(0, $scope.elicenseData.length);
				$scope.currentList(1);
			} else {
				$scope.getLicenseList();
			}
		} else if(type == 'history') {
			appData.type = "history";
			if($scope.historyData.length > 0) {
				$scope.totalLicense = $scope.historyData.slice(0, $scope.historyData.length);
				$scope.currentList(1);
			} else {
				$scope.getHistoryData();
			}

		}
	}
	$scope.configUrl = $.getConfigMsg.preUrlSelf;
	$scope.nextPage = function() {
		if($scope.currentPage < $scope.totalPages) {
			++$scope.currentPage;
			$scope.currentList();
		}
	};
	$scope.prevPage = function() {
		if($scope.currentPage > 1) {
			--$scope.currentPage;
			$scope.currentList();
		}
	};
	$scope.reLoadCount = 5;
	//查询被是否有被授权证照
	$scope.getAuthorizedLicenseList = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/DZCert/queryeles.do",
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data: {
				certNo: appData.encrypt_identity || appData.licenseNumber, //"340881199303145313" ||
				phone: appData.zwdtsw_link_phone || "", //"0" ||
				name: encodeURI(appData.licenseName),
				startDay: appData.VALIDSTARTDAY,
				endDay: appData.VALIDENDDAY,
				use: encodeURI('一网通办自助终端')
			},
			success: function(dataJsonp) {
				console.log(dataJsonp);
				if(dataJsonp.success == true && dataJsonp.data.CODE == 0) {
					$scope.authorizedLicenseList = dataJsonp.data.certArrs
				}
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	$scope.getAuthorizedLicenseList();
	$scope.getLicenseList = function() { //电子证照库数据
		$rootScope.router = undefined;
		var httpConfig = {
			jsonpCallback: "JSON_CALLBACK",
			certNo: appData.encrypt_identity || appData.licenseNumber, //"340881199303145313" ||
			type: $scope.licenseType, //"0" ||
			machineId: $.config.get('uniqueId') || "test",
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
					if(data.success == true) {
						$scope.elicenseData = data.data;
						$scope.elicenseData.push.apply($scope.elicenseData, $scope.authorizedLicenseList);
						console.log($scope.elicenseData);
						$scope.totalLicense = $scope.elicenseData.slice(0, 3);
						console.log($scope.totalLicense);
						$scope.currentList()
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
		$state.go("licenseList");
	};

	if($rootScope.router) {
		$scope.choiceLicenseType('history');
	} else {
		console.log(appData.elicenseData);
		if(appData.elicenseData) {
			$scope.isLoding = true;
			$scope.elicenseData = appData.elicenseData;
			$scope.totalLicense = $scope.elicenseData.slice(0, 3);
			$scope.currentList()
		} else {
			$scope.getLicenseList();
		}
	};

	addAnimate($('.licenseContainer'))

	$scope.prevStep = function() {
		$state.go("loginType");
	}
});
app.controller("licenseList", function($scope, $state, appData, $timeout) {
	$scope.operation = "我的证照";
	$scope.licenseList = appData.elicenseData;
	console.log($scope.licenseList);
	$scope.choice = function(certName, electImg, pdfLicense) {
		appData.previewImg = electImg;
		appData.certName = certName;
		appData.pdfLicense = pdfLicense;
		$state.go("preview");
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}
})
app.controller("elicensePreview", function($scope, $state, appData, $timeout) {
	$scope.operation = "电子证照预览";
	$scope.configUrl = $.getConfigMsg.preUrlSelf;
	$scope.certName = appData.certName;
	$scope.pdfLicense = appData.pdfLicense;
	$scope.previewImg = $scope.configUrl + appData.previewImg;
	$scope.isAllScreen = false;
	$scope.isPrint = false;
	//图片显示
	var viewer = new Viewer(document.getElementById('jq22'), {
		url: 'data-original',
	});
	$scope.prevStep = function() {
		$state.go("licenseList");
	};
	$scope.print = function() {
		$scope.isPrint = 'show';
		$scope.path = "D:\\pdfPrint.pdf";
		$scope.filePath = "D:/pdfPrint.pdf";
		if(appData.type == "license") {
			if($scope.certName === "中华人民共和国居民身份证") {
				LODOP_PRINT.personLicense($scope.previewImg);
				saveDeviceStatus("A4Printer", 0, "正常", 0, 0, 0, 1);
			} else {
				$.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		urlHost +
					$scope.configUrl + $scope.pdfLicense,
					$scope.path,
					//将选中图片下载
					function(bytesCopied, totalBytes) {
						console.log(bytesCopied + "," + totalBytes);
					},
					function(result) {
						$.device.pdfPrint($scope.filePath);
						saveDeviceStatus("A4Printer", 0, "正常", 0, 0, 0, 3);
					},
					function(webexception) {
						alert("下载文档失败");
					}
				);
			}
		} else if(appData.type == "history") {
			LODOP_PRINT.corporateLicense($scope.previewImg);
			saveDeviceStatus("A4Printer", 0, "正常", 0, 0, 0, 1);
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