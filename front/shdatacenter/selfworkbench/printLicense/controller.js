app.controller("licenseMain", function($scope, $state, appData) {
	$scope.funName = '证明开具';
	$scope.operation = "请选择一种证件";
	$scope.current = 0;
	$scope.stuffName = perjsonStr;
	$scope.searchType = ["个人", "法人"];
	appData.licenseType = "person";
	appData.url = "";
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
		data: encodeURIComponent(JSON.stringify($scope.info))
	}
	$scope.encryptDataByRSA = function() {
		$http.jsonp('http://hengshui.5uban.com/xhac/aci/workPlatform/util/encryptDataByRSA.do', {
			params: httpConfig
		}).success(function(dataJson) {
			console.log(dataJson.result);
			$scope.url = "https://zwdtuser.sh.gov.cn/uc/naturalUser/jumpUcRedirect.do?app_id=535984a5&data=" + encodeURIComponent(dataJson.result) + "&redirect_uri=";
			console.log($scope.url + encodeURIComponent(appData.url));
			window.external.URL_OPEN(50, 180, 1800, 760, $scope.url + encodeURIComponent(appData.url));
		}).error(function(err) {
			console.log('encryptDataByRSA err');
		});

	}
	$scope.encryptDataByRSA();
});
app.controller('licenseLoginType', function($state, $scope, appData, $http) {
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
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.isLoding = true;
	$scope.alertConfirm = function() {

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
		$scope.isLoding = false;
		$scope.loginType = "";
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/autoterminal/dzzz/queryCertBaseData.do",
			/*url写异域的请求地址*/
			dataType: "jsonp",
			/*加上datatype*/
			jsonp: "jsonpCallback",
			/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
			data: {
				certNo: idCard, //"340881199303145313" || 
				type: licenseType, //"0" ||
				catMainCode: appData.code //"310196646654500"//
			},
			success: function(data) {
				$scope.isLoding = true;
				if(data.length > 0) {
					$state.go("license");
					$scope.$apply();
				} else {
					$scope.isAlert = true;
					$scope.msg = "您暂无该证明，可在列表点击立即办理申请";
				}
			},
			error: function(err) {
				$scope.isAlert = true;
				$scope.msg = "获取电子证明失败,请重试";
			}
		});
	}

	$scope.caLoginStatus = "";
	$scope.caLogin = function() { //登录
		$scope.caLoginStatus = 'login';
	}
	$scope.caInfo = function(companyName, companyNo) {
		if(companyName && companyNo) {
			appData.licenseNumber = companyNo;
			appData.licenseName = companyName;
			$scope.isNotStuff(appData.licenseNumber, 1);
		}
	}
	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
		} else {
			layer.msg("没有获取到")
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		if(appData.type == 'banli') {
			$state.go("apply");
		} else {
			$scope.isNotStuff(appData.licenseNumber, 0);
		}
	}

	$scope.prevStep = function() {
		$state.go("loginType");
	}
	$scope.citizenLogin = function(info) {
		var idcardInfo = info.result.data;
		appData.licenseName = idcardInfo.realname;
		if(appData.licenseType == 'person') {
			if(info.url == "") {
				layer.msg("未识别到证照信息！", {
					time: 5000
				});
				$state.reload();
			}
			if(info.code !== idcardInfo.idcard) {
				layer.msg("二维码类型错误，请扫描市民亮证个人二维码", {
					time: 2000
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
	$scope.operation = appData.stuffName;
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

	$scope.currentList = function(current) {
		if(current === undefined) {
			current = $scope.currentPage;
		}
		$scope.currentImgIndex = 0;
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
		$scope.electImg = ($scope.totalLicense[0].pictureUrlForBytes ? $scope.totalLicense[0].pictureUrlForBytes : $scope.totalLicense[0].imageUrl);
	}
	$scope.pitchOnImg = function(i, item) {
		if(item !== undefined && item.length > 1) {
			console.log("这是添加图片操作");
			$rootScope.router = 'history';
			$state.go("upload");
		}
		if(item) {
			$scope.currentImgIndex = i;
			$scope.electImg = (item.pictureUrlForBytes ? item.pictureUrlForBytes : item.imageUrl);
		}
	}

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
			jsonpCallback: "JSON_CALLBACK",
			certNo: $scope.licenseNumber, //"340881199303145313" || 
			type: $scope.licenseType,
			catMainCode: appData.code //"310196646654500"//"0" ||
		};
		$timeout(function() {
			$http.jsonp($.getConfigMsg.preUrl + "/aci/autoterminal/dzzz/queryCertBaseData.do", {
					params: httpConfig
				})
				.success(function(data) {

					$scope.elicenseData = data;
					$scope.totalLicense = $scope.elicenseData.slice(0, $scope.elicenseData.length);
					$scope.currentList()
				})
				.error(function(err) {
					if($scope.reLoadCount > 1) {
						$scope.getLicenseList();
					}
					$scope.reLoadCount--;
				})
		})

	};
	$scope.preview = function() {
		appData.previewImg = $scope.electImg;
		$state.go("preview");
	};

	$scope.getLicenseList();
	$scope.prevStep = function() {
		$state.go("login");
	}
});

app.controller("elicensePreview", function($scope, $state, appData, $timeout) {
	$scope.operation = "电子证照预览";
	$scope.configUrl = $.getConfigMsg.preUrl;
	$scope.previewImg = appData.previewImg;
	$scope.isAllScreen = false;
	$scope.isPrint = false;

	$scope.close = function() {
		$scope.isAllScreen = false;
	};
	$scope.prev = function() {
		$state.go("license");
	};
	$scope.allScreen = function(str) {
		if(str === 'con' && $scope.isAllScreen === false) {
			return;
		}
		$scope.isAllScreen = !$scope.isAllScreen;
		return;
	};
	$scope.print = function() {
		var lodop = $.device.printGetLodop();
		$scope.isPrint = 'show';

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
});