app.controller("listController", function($scope, $route, $location, $http, $rootScope, data, appFactory) {
	$scope.itemName = [];
	$scope.currentMatters = [];
	$scope.matterVal = '';
	$scope.idRead = false;
	//街道下主题
	$scope.getThemeInStreet = function() {
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK"
		}
//		urlHost1
		$http.jsonp(JA_Extranet_urlHost + '/aci/declare/getThemeInStreet.do', {
			params: tConfig
		}).success(function(dataJson) {
			console.log('街道主题办事列表');
			console.log(dataJson);
			$scope.itemName = dataJson.data;
			$scope.itemName.unshift({
				itemTypeName: "000",
				itemTypeCode: "全部"
			});
		}).error(function(err) {
			console.log(err);
		});
	}
	$scope.getThemeInStreet();

	$scope.toMaterials = function(itemTypeCode, itemTypeName, index) {
		data.itemTypeCode = itemTypeName;
		data.itemTypeName = itemTypeCode;
		$location.path("/itemlist");
	};
	$rootScope.goHome = function() {
		window.external.URL_CLOSE();
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
	};
	$scope.prevStep = function () {
		window.location.href = '../declare/index.html#/start';
	}
});
app.controller("itemlistController", function($scope, $route, $location, $http, $rootScope, data, appFactory) {
	//通过主题获取事项
	$scope.getItemOfThemeInStreet = function(code) {
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			themeCode: code
		}
//		urlHost1
		$http.jsonp(JA_Extranet_urlHost + '/aci/declare/getItemOfThemeInStreet.do', {
			params: tConfig
		}).success(function(dataJson) {
			console.log('街道主题办事列表-->列表');
			console.log(dataJson);
			$scope.itemName = dataJson.data;
		}).error(function(err) {
			console.log(err);
		});
	}
	if(data.itemTypeName == "全部") {
		$scope.getItemOfThemeInStreet("");
	} else {
		$scope.getItemOfThemeInStreet(data.itemTypeCode);
	}

	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
//	$scope.isScroll();
	$scope.setItemCode = function(code) {
		data.stItemNo = code;
		data.itemName = "街道";
		$location.path("/select");
	}
	$scope.prev = function() {
		$location.path("/list");
	}
});
app.controller("selectController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
	data.isUpload = [];
	data.listImg = [];
	data.fileName = [];
	data.imgId = "";
	$.device.Camera_Hide();
	$.device.qrCodeClose();
	var name = data.itemName || $location.search().itemName;
	data.itemName = name;
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	// 刷身份证获取信息
	$scope.scanIdcard = function() {
		$location.path('/idCard');
	}
	// 市民云亮证
	$scope.citizen = function() {
		$location.path('/citizen');
	}
	//刷脸认证
	$scope.queryFace = function() {
		$location.path('/queryFace');
	}
	$scope.prev = function() {
		$location.path("/itemlist");
	}
	$rootScope.goHome = function() {
		window.external.URL_CLOSE();
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
	};
});
app.controller("idCardController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	data.idCardNum = "";
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$scope.isRead = true;
	$scope.isLoding = true;
	$rootScope.isAlert = false;
	$scope.SisAlert = false;
	$scope.SalertConfirm = function() {
		$scope.SisAlert = false;
		$location.path("/select");
	}


	$scope.prevStep = function() {
		$location.path("/select");
	}
	$scope.getIdcard = function(info, images) {
		$scope.faceImage = images;
		data.idCardName = info.Name;
		data.idCardNum = info.Number;
		$scope.isRead = false; //faceImg
//		$scope.$apply();
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		$location.path("/iframe");
	}

//	data.idCardName = "李华熙"; //"邹天奇"; //"王梅华"; //
//	data.idCardNum = "520222199406140030"; //"430426199804106174"; //"310109194911262065"; //"310104197308010412"; //""; //"";//"320831199503150013";//"310105197805313613"; //
//	data.mobile = "18692067056";
//	data.nation = "汉族";
//	$location.path("/iframe");

});
app.controller("citizenController", function($scope, $route, $http, $location, $rootScope, data, $timeout, appFactory) {
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$rootScope.isAlert = false;
	$scope.SisAlert = false;
	$scope.isLoding = true;
	$scope.SalertConfirm = function() {
		$scope.SisAlert = false;
		$timeout(function() {
			$location.path('/select');
		}, 100);
	}
	$scope.ClearBr = function(key) {
		key = key.replace(/<\/?.+?>/g, "");
		key = key.replace(/[\r\n]/g, "");
		return key;
	}
	// 扫描二维码
	$.device.qrCodeOpen(function(code) {
		var __code = $scope.ClearBr(code);
		$.ajax({
//			url: urlHost + "/aci/window/getInfoByCodeForLogin.do",
			url: JA_Extranet_urlHost + "/aci/window/getQrCodeInfoByElectronicCert.do",
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data: {
				codeParam: __code,
				lzAddress: '',
				using: '',
				machineId: $.config.get('uniqueId') || "",
				itemName: '',
				itemCode: '',
				businessCode: ''
			},
			success: function(dataJsonp) {
				if(dataJsonp.result.success === false) {
					$scope.SisAlert = true;
					$scope.Smsg = dataJsonp.result.msg;
					$timeout(function() {
						$location.path('/select');
					}, 100);
				}
				data.idCardName = dataJsonp.result.data.realname;
				data.idCardNum = dataJsonp.result.data.idcard;
				data.mobile = dataJsonp.result.data.mobile;
				data.idcard_valid_start_day = dataJsonp.result.data.VALIDSTARTDAY;
				data.idcard_valid_end_day = dataJsonp.result.data.VALIDENDDAY;
				$timeout(function() {
					$location.path("/iframe");
				}, 100);
			},
			error: function(err) {
				$scope.SisAlert = true;
				$scope.Smsg = "二维码已过期！";
			}
		});
	}, function(err) {
		console.log("二维码扫描出错：" + err)
	});
	$scope.prevStep = function() {
		$location.path("/select");
	}
});
app.controller("iframeController", function($scope, $route, $http, $location, data, $timeout, $rootScope, $sce, appFactory) {
	$rootScope.isAlert = false;
	$scope.info = {
		type: "1",
		idCard: data.idCardNum,
		credit_code: "",
		ca_code: "",
		name: data.idCardName
	}
	console.log($scope.info);
	var httpConfig = {
		jsonpCallback: "JSON_CALLBACK",
		data: encodeURIComponent(JSON.stringify($scope.info))
	}
	$scope.encryptDataByRSA = function() {
		$http.jsonp('http://hengshui.5uban.com/xhac/aci/workPlatform/util/encryptDataByRSA.do', {
			params: httpConfig
		}).success(function(dataJson) {
			console.log(dataJson.result);
			$scope.url = "https://zwdtuser.sh.gov.cn/uc/naturalUser/jumpUcRedirect.do?app_id=535984a5&data=" + encodeURIComponent(dataJson.result) + "&redirect_uri=";
			$scope.url1 = "http://ywtb.sh.gov.cn:18018/ac-product-net/netapply/apply.do?itemCode=" + data.stItemNo;
			console.log($scope.url + $scope.url1);
			window.external.URL_OPEN(50, 95, 1800, 850, $scope.url + encodeURIComponent($scope.url1));
		}).error(function(err) {
			console.log('encryptDataByRSA err');
		});

	}
	$scope.encryptDataByRSA();

	// 设置url被angular信任 正常跳转
	$scope.prev = function() {
		try {
			window.external.URL_CLOSE();
		} catch(e) {}
		$location.path("/itemlist");
	}
});
