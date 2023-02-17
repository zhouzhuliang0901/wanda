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
var machineId = $.config.get('uniqueId');
app.controller("licenseMain", function($scope, $state, appData) {
	addAnimate($('.scrollBox2'))
	$scope.funName = '证明开具';
	$scope.operation = "请选择一种证件";
	$scope.current = 0;
	$scope.stuffName = perjsonStr;
	$scope.searchType = ["个人", "法人"];
	appData.licenseType = "person";
	$scope.person = true;
	$scope.getMatterCon = function(index, type) {
		$scope.current = index;
		switch(type) {
			case "个人":
				$scope.stuffName = perjsonStr;
				appData.licenseType = "person";
				$scope.person = true;
				break;
			case "法人":
				$scope.stuffName = legaljsonStr;
				appData.licenseType = "corporate";
				$scope.person = false;
				break;
			default:
				$scope.stuffName = perjsonStr;
		}
	};
	$scope.toStuffName = function(code, stuffName) {
		appData.code = code;
		appData.stuffName = stuffName;
		appData.person = $scope.person;
		$state.go("loginType");
	}

	$scope.Handle = function(url, banli) {
		appData.url = url;
		appData.type = banli;
		appData.person = $scope.person;
		$state.go("loginType");
	}

	$scope.prevStep = function() {
		$state.go("../index.html")
	}
});

app.controller('apply', function($state, $scope, appData, $http) {
	$scope.info = {
		type: "1",
		idCard: appData.licenseNumber,
		credit_code: "",
		ca_code: "",
		name: appData.licenseName
	}
	console.log($scope.info);
	var httpConfig = {
		jsonpCallback: "JSON_CALLBACK",
		data: encodeURIComponent(JSON.stringify($scope.info)),
		dataTime: Math.random()
	}
	$scope.encryptDataByRSA = function() {

		//		$.ajax({
		//			url:'http://hengshui.5uban.com/xhac/aci/workPlatform/util/encryptDataByRSA.do',
		//			type:'GET',
		//			jsonp: "jsonpCallback",
		//			dataType:'jsonp',
		//			data:{params:httpConfig},
		//			success:function(res){
		//				console.log(res)
		//			},
		//			error:function(err){
		//				console.log(err)
		//			}
		//		})

		$http.jsonp($.getConfigMsg.preUrl + '/aci/workPlatform/util/encryptDataByRSA.do', {
			params: httpConfig
		}).success(function(dataJson) {
			console.log(dataJson)
			$.log.debug(dataJson);
			$scope.url = "https://zwdtuser.sh.gov.cn/uc/naturalUser/jumpredirect.do?app_id=535984a5&data=" + encodeURIComponent(dataJson.result) + "&redirect_uri=";
			//$scope.url = "https://zwdtuser.sh.gov.cn/uc/naturalUser/jumpUcRedirect.do?app_id=535984a5&data=" + encodeURIComponent(dataJson.result) + "&redirect_uri=";
			$scope.url1 = appData.url;
			console.log($scope.url + encodeURIComponent($scope.url1));
			window.external.URL_OPEN(190, 170, 1540, 710, $scope.url + encodeURIComponent($scope.url1));
		}).error(function(err) {
			$.log.debug(err);
			console.log(err)
		});

	}
	$scope.encryptDataByRSA();

	$scope.prevStep = function() {
		$state.go("main")
		setTimeout(function() {
			location.reload()
		}, 0)
	}

	//	$scope.encryption = function(idCard) {
	//		//公钥
	//		var PUBLIC_KEY = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCDOvG8syfBm/UYl7CazBHWkbluHZC7cA7XMHPkQundV9YueyaHpKJO+plset/foZzvYwlJw6bTTevrKsfY2XTUrYMq6Rw6qKpQ7+QI77B3lMKijTTtVDDymGU+Gy7qIcFA7Tlyj7OW74oXiPvBVj9dEqojZGVadIInoU3JmRIQ9QIDAQAB';
	//		//私钥
	//		var PRIVATE_KEY = 'MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALrx0HvokdmeimzVsMdHhra0HP4jT2PZpY5LM54FUZsmzgcjRfA8J8VUDerZ0s2lAR0MQc3AODcz/LnS47KCnVzJcbXYbLVL5BypKXQGAY4ff1qgnlzJGSiMtIqTOiXrvSx8StfTj3FC+6rvvAXOe8ed9DMeBCQRAuomeRisW+vZAgMBAAECgYB5lRGRtLU+woSmuefqA1PS+ZstkcttVjz9KV2dtTnY3Uj7jW5MCuOWy87tYdNfGaR6vuEBLrWg+XexZz3deGNcu7t0B4IRHu/54RPPXQszvoN0AcqcDaQ/sUHKE4MDwX5ij5wwA+V2TOUducBGH9+5or5N/IUzdLtKcnWlKoieMQJBANu2wk9xknXaH+dg4x1dVDzYpG1+rix8qA+dok2vF1twS7sdJwhngS7nsMSBjAIETGd2w94ZBz6VBGA9kx1C7K0CQQDZ0ZuhHPV70GhSG2ZyOI4hGI+lEAsCQe7nEKclK2U3oXZR2BNCAj+AZwO1GXqPguC+c5SBwbTTciMGysIwoNVdAkAsX7jWuqVN0APpgxPbdmHw+AAdbRxYN8TpgnipH9ejzAY/gB/F/sGEa56z0UYpkhysOLxOOtfPt+DuXwE7Q6zxAkEAzeCIsOemP7jkYXb0hdFexXlpjCJ1xVR8cnoTAdbafJJoO0N4MFPfoYW8w1epuCuEMX8dRufH+nNPGARdN4lNIQJAVtROB3lmtVYizbeMEEHFSIT3hEUQBTIcFekRgXcX4XYzv+tH35VKWjIH0IOLUvj4FOuTAuwdHs6Ux8HeHM4hHg==';
	//		//使用公钥加密
	//		var encrypt = new JSEncrypt();
	//		//encrypt.setPrivateKey('-----BEGIN RSA PRIVATE KEY-----'+PRIVATE_KEY+'-----END RSA PRIVATE KEY-----');
	//		encrypt.setPublicKey('-----BEGIN PUBLIC KEY-----' + PUBLIC_KEY + '-----END PUBLIC KEY-----');
	//		var encrypted = encrypt.encrypt(idCard);
	//		console.log('加密后数据:%o', encrypted);
	//		console.log('加密后数据URI:%o', encodeURIComponent(encrypted));
	//		return encodeURIComponent(encrypted);
	//	}
	//	$scope.data = {
	//		idCard: appData.licenseNumber //"310115198606194014" //
	//	}
	//	$scope.url = "https://zwdtuser.sh.gov.cn/uc/naturalUser/jumpredirect.do?data=" + $scope.encryption(JSON.stringify($scope.data)) + "&redirect_uri=";
	//	console.log($scope.url + appData.url);
	//	//50, 180, 1800, 760, 
	//	window.external.URL_OPEN(190, 170, 1540, 710, $scope.url + appData.url);
});

app.controller('licenseLoginType', function($state, $scope, appData) {
	$scope.operation = "请选择登录方式";
	$scope.person = appData.person;
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller('licenseLogin', function($scope, $http, $state, appData, appFactory, $timeout) {

	var timerout = $timeout(function() {
		layer.msg('登录超时，请重新登录！')
		$timeout(function() {
			$state.go("loginType");
		}, 1000)
	}, 8000)

	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.isLoding = true;
	$scope.alertConfirm = function() {
		$scope.isLoding = true;
		$scope.isAlert = false;
		$state.go('loginType');
	}
	$scope.alertCancel = function() {
		$scope.isAlert = false;
		$scope.isLoding = true;
		$state.go('loginType');
	}
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

	$scope.isNotStuff = function(idCard, licenseType) {
		//alert('isNoStuff');
		$scope.isLoding = false;
		$scope.loginType = "";
		var dataMassage = {
			machineId: $.config.get('uniqueId') || 'test',
			itemName: '',
			itemCode: '',
			businessCode: '',
			certNo: appData.encrypt_identity || idCard, //"340881199303145313" || 
			type: licenseType, //"0" ||
			catMainCode: appData.code, //"310196646654500"//
			jsonpCallback: "JSON_CALLBACK",
			//新增参数
			name: encodeURI(appData.licenseName) || '',
			startDay: appData.startDay || '',
			endDay: appData.endDay || ''
		}
		//alert(JSON.stringify(dataMassage));
	
		$http.jsonp($.getConfigMsg.preUrl + '/aci/autoterminal/dzzz/queryCertBaseData.do', {
			params: dataMassage
		}).success(function(dataJson) {
			//alert(JSON.stringify(dataJson));
			//alert('请求成功');
			$scope.isLoding = true;
			if(dataJson.length > 0) {
				$state.go("license");
				$scope.$apply();
			} else {
				$scope.isAlert = true;
				$scope.msg = "您暂无该证明，可在列表点击立即办理申请";
			}
		}).error(function() {
			$scope.isLoding = true;
			$scope.isAlert = true;
			$scope.msg = "获取电子证明失败,请重试";
		})
	}

	$scope.caLoginStatus = "";
	$scope.caLogin = function() { //登录
		/*关闭定时器*/
		if(timerout) {
			$timeout.cancel(timerout);
		}
		$scope.caLoginStatus = 'login';
	}
	$scope.caInfo = function(companyName, companyNo) {
		/*关闭定时器*/
		if(timerout) {
			$timeout.cancel(timerout);
		}
		if(companyName && companyNo) {
			appData.licenseNumber = companyNo;
			appData.licenseName = companyName;
			$scope.isNotStuff(appData.licenseNumber, 1);
		}
	}
	$scope.idcardLogin = function(info, images) {
		/*关闭定时器*/
		if(timerout) {
			$timeout.cancel(timerout);
		}
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;

			if(!info.ValidtermOfStart || !info.ValidtermOfEnd) {
				alert('没有获取到开始-结束时间')
			}
			$.log.debug(info)
			appData.startDay = info.ValidtermOfStart;
			appData.endDay = info.ValidtermOfEnd;
			$.log.debug(appData.startDay + '--------------------' + appData.endDay)

			$scope.$apply();
		} else {
			layer.msg("没有获取到")
		}
	}
	$scope.getResult = function(img) {
		/*关闭定时器*/
		if(timerout) {
			$timeout.cancel(timerout);
		}
		$scope.img = img;
		if(appData.type == 'banli') {
			$state.go("apply");
		} else {
			$scope.isNotStuff(appData.licenseNumber, 0);
		}
	}

	//	------------------------------------------------------------------------------------------------------
	/*appData.licenseNumber = 370285199611114728;
	appData.licenseName = '赵振芳';
	$state.go("apply");*/

	$scope.prevStep = function() {
		/*关闭定时器*/
		if(timerout) {
			$timeout.cancel(timerout);
		}
		$state.go("loginType");
	}
	$scope.citizenLogin = function(info) {
		function ClearBr(key) {
			key = key.replace(/\+/g, "-");
			key = key.replace(/\#/g, ",");
			return key;
		}
		/*关闭定时器*/
		if(timerout) {
			$timeout.cancel(timerout);
		}
		//alert(JSON.stringify(info));
		let idcardInfo = info.result.data;
		appData.licenseName = idcardInfo.realname;

		if(appData.qrCodeType == "suishenma") {
			//alert('随申码没有开始-结束时间, 设为空');
			appData.startDay = '';
			appData.endDay = '';
			appData.encrypt_identity = ClearBr(info.encrypt_identity);
		} else if(appData.qrCodeType == "shiminyun") {
			appData.startDay = idcardInfo.VALIDSTARTDAY;
			appData.endDay = idcardInfo.VALIDENDDAY;
		}

		//		if(info.encrypt_identity) {
		//			appData.encrypt_identity = ClearBr(info.encrypt_identity);
		//		}
		//		if(!idcardInfo.VALIDSTARTDAY || !idcardInfo.VALIDENDDAY) {
		//			alert('没有获取到开始-结束时间')
		//		}

		$.log.debug(JSON.stringify(idcardInfo))
		$.log.debug(appData.startDay + ' 开始--------结束 ' + appData.endDay)

		if(appData.licenseType == 'person') {
			if(info.url == "") {
				layer.msg("未识别到证照信息！", {
					time: 5000
				});
				$state.reload();
			}
			if(info.code != idcardInfo.idcard) {
				layer.msg("二维码类型错误，请扫描市民亮证个人二维码", {
					time: 2000
				});
				$state.reload();
			} else {
				appData.licenseNumber = idcardInfo.idcard;
				if(appData.type == 'banli') {
					$state.go("apply");
				} else {
					$scope.isNotStuff(appData.licenseNumber, 0);
				}
			}
		} else if(appData.licenseType == 'corporate') {
			if(info.code == idcardInfo.idcard) {
				layer.msg("未识别到您的企业信息，请确认二维码正确后重新扫描！", {
					time: 5000
				});
				$state.reload();
			} else {
				appData.licenseNumber = info.code;
				if(appData.type == 'banli') {
					$state.go("apply");
				} else {
					$scope.isNotStuff(appData.licenseNumber, 0);
				}
			}
		}
	}
})

app.controller("licenseLicense", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	//alert('进来了');
	$scope.operation = appData.stuffName;
	$scope.encrypt_identity = appData.encrypt_identity
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
	$scope.isShowView = false; // 是否显示预览框
	$scope.zoomCount = 1; // 图片放大计数
	$scope.rotateCount = 0; // 图片旋转计数
	$scope.configUrl = $.getConfigMsg.preUrl;
	$scope.isShowPrint = false;

	$scope.currentList = function(current) {
		if(current === undefined) {
			current = $scope.currentPage;
		}
		$scope.currentImgIndex = 0;
		$scope.startPos = (current - 1) * 3; //0 -3  3 - 6 
		$scope.endPos = $scope.startPos + 3;
		if($scope.currentLicense === 'history' &&
			$scope.totalLicense.indexOf("../libs/common/images/addImg.png") == -1
		) {
			$scope.totalLicense.unshift("../libs/common/images/addImg.png"); //当为历史上传材料时添加上传按钮
		}
		$scope.showLicenseList = $scope.totalLicense.slice($scope.startPos, $scope.endPos);
		$scope.totalPages = Math.ceil($scope.totalLicense.length / 3);
		$scope.showLicenseList.length = 3;
		$scope.electImg = ($scope.totalLicense[0].pictureUrlForBytes ? $scope.totalLicense[0].pictureUrlForBytes : $scope.totalLicense[0].imageUrl);
	}
	$scope.pitchOnImg = function(i, item) {
		if(item !== undefined && item.length > 1) {
			console.log("这是添加图片操作");
			$rootScope.router = 'history';
			//			$state.go("upload");
		}
		if(item) {
			$scope.currentImgIndex = i;
			$scope.electImg = (item.pictureUrlForBytes ? item.pictureUrlForBytes : item.imageUrl);
			$scope.isShowView = true;
		}
	};

	$scope.configUrl = $.getConfigMsg.preUrl;
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
	$scope.getLicenseList = function() { //电子证照库数据
		$rootScope.router = undefined;

		var httpConfig = {
			machineId: machineId || '',
			itemName:'',
			itemCode: '',
			businessCode: '',
			jsonpCallback: "JSON_CALLBACK",
			certNo: $scope.encrypt_identity || $scope.licenseNumber, //"340881199303145313" || 
			type: $scope.licenseType,
			catMainCode: appData.code, //"310196646654500"//"0" ||
			//新增参数
			name: appData.licenseName || '',
			startDay: appData.startDay || '',
			endDay: appData.endDay || ''
		};
		$timeout(function() {
			$http.jsonp($.getConfigMsg.preUrl + "/aci/autoterminal/dzzz/queryCertBaseData.do", {
					params: httpConfig
				})
				.success(function(data) {

					$scope.elicenseData = data;
					$scope.totalLicense = $scope.elicenseData.slice(0, $scope.elicenseData.length);
					$scope.currentList();
				})
				.error(function(err) {
					if($scope.reLoadCount > 1) {
						$scope.getLicenseList();
					}
					$scope.reLoadCount--;
				})
		})

	};

	$scope.getLicenseList();
	//	$scope.preview = function() {
	//		appData.previewImg = $scope.electImg;
	//		$state.go("preview");
	//	};

	$scope.viewClose = function() {
		$scope.isShowView = false;
		$scope.zoomCount = 1;
		$scope.rotateCount = 0;
	};

	$scope.zoomIn = function() {
		$scope.zoomCount += 0.5;
		if($scope.zoomCount > 5) {
			$scope.zoomCount = 5;
		}
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

	$scope.zoomOut = function() {
		$scope.zoomCount -= 0.5;
		if($scope.zoomCount < 0.5) {
			$scope.zoomCount = 0.5;
		}
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

	$scope.rotateLeft = function() {
		$scope.rotateCount -= 90;
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

	$scope.rotateRight = function() {
		$scope.rotateCount += 90;
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

	$scope.print = function() {
		var lodop = $.device.printGetLodop();
		$scope.isShowPrint = 'show';

		$timeout(function() {
			var img = $scope.configUrl + $scope.previewImg;
			lodop.ADD_PRINT_IMAGE(0, 0, 800, 1300, "<img border='0' src='" + img + "'>");
			lodop.SET_PRINT_STYLEA(0, "Stretch", 2); //按原图比例(不变形)缩放模式
			lodop.ADD_PRINT_TEXT(0, 0, 400, 500, "此证照由市电子证照库提供");
			lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
			lodop.SET_PRINT_STYLEA(0, "FontColor", "#ccc");
			lodop.SET_PRINT_STYLEA(0, "ItemType", 1);
			lodop.SET_PRINT_STYLEA(0, "Angle", 50);
			lodop.SET_PRINT_STYLEA(0, "Repeat", true);
			// lodop.PREVIEW();
			lodop.PRINT();
			//LODOP_PRINT.corporateLicense($scope.configUrl + $scope.previewImg);
		}, 200)
		$timeout(function() {
			$state.go("main");
		}, 300);
	};

	$scope.prevStep = function() {
		$state.go("loginType");
	};
});

//app.controller("elicensePreview", function($scope, $state, appData, $timeout) {
//	$scope.operation = "电子证照预览";
//	$scope.configUrl = $.getConfigMsg.preUrl;
//	$scope.previewImg = appData.previewImg;
//	$scope.isAllScreen = false;
//	$scope.isPrint = false;
//
//	$scope.close = function() {
//		$scope.isAllScreen = false;
//	};
//	$scope.prev = function() {
//		$state.go("license");
//	};
//	$scope.allScreen = function(str) {
//		$event.cancelBubble = true;
//		if(str === 'con' && $scope.isAllScreen === false) {
//			return;
//		}
//		$scope.isAllScreen = !$scope.isAllScreen;
//		return;
//	};
//	$scope.print = function() {
//		var lodop = $.device.printGetLodop();
//		$scope.isPrint = 'show';
//
//		$timeout(function() {
//			var img = $scope.configUrl + $scope.previewImg;
//			lodop.ADD_PRINT_IMAGE(0, 0, 800, 1300, "<img border='0' src='" + img + "'>");
//			lodop.SET_PRINT_STYLEA(0, "Stretch", 2); //按原图比例(不变形)缩放模式
//			lodop.ADD_PRINT_TEXT(0, 0, 400, 500, "此证照由市电子证照库提供");
//			lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
//			lodop.SET_PRINT_STYLEA(0, "FontColor", "#ccc");
//			lodop.SET_PRINT_STYLEA(0, "ItemType", 1);
//			lodop.SET_PRINT_STYLEA(0, "Angle", 50);
//			lodop.SET_PRINT_STYLEA(0, "Repeat", true);
//			// lodop.PREVIEW();
//			lodop.PRINT();
//			//LODOP_PRINT.corporateLicense($scope.configUrl + $scope.previewImg);
//		}, 200)
//		$timeout(function() {
//			$state.go("main");
//		}, 300);
//	};
//});