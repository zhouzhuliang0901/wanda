app.controller("list", function($scope, $state, appData) {
	$scope.operation = "请选择办事事项";
	$scope.stuffName = perjsonStr;
	$scope.getMatterCon = function(itemName,code,type) {
		appData.itemName = itemName;
		appData.code = code;
		appData.type = type;
		$state.go("loginType");
	};
		$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			bounce: true,
			click: true,
			taps: true,
			preventDefault: false,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});
app.controller('loginType', function($state, $scope, appData, $http) {
	$scope.operation = "请选择登录方式";
	$scope.person = appData.person;
	$scope.choiceLogin = function(type) {
		//appData.archivesNumber = "430426199804106174";
		//appData.archivesName = "邹天奇";
		//$state.go("input");
			appData.loginType = type;
				$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("list");
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory, $timeout) {
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
		case "sscard":
			$scope.operation = "社保卡";
			break;
	}

	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.archivesNumber = info.Number;
			appData.archivesName = info.Name;
		} else {
			layer.msg("没有获取到")
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		appData.img = img;
		$state.go("input");
		//$scope.queryArchivesInfoByIdentNo(appData.archivesNumber, 1);
	}

	$scope.prevStep = function() {
		$state.go("loginType");
	}
	$scope.citizenLogin = function(info) {
		var idcardInfo = info.result.data;
		appData.archivesName = idcardInfo.realname;
		appData.archivesNumber = idcardInfo.idcard;
		$state.go("input");
	}
	//
	$scope.sscardLogin = function(info) {
		if(info) {
			appData.archivesNumber = info.Ssn;
			appData.archivesName = info.PeopleName;
			$state.go("input");
		} else {
			layer.msg("没有获取到")
		}
	}
})
app.controller("input", function($scope, $http, $state, appData, appFactory, $timeout) {
	$scope.operation = "请输入正确的手机号码";
	$scope.isLoding = true;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}

	$scope.serialSearch = function() {
		if(!isPhoneAvailable($('#mobile').val())) {
			$scope.isAlert = true;
			$scope.msg = "请输入正确的手机号！";
			return;
		} else {
			$scope.isLoding = false;
			$scope.humanSocietyQuery();
		}
	}
	$scope.humanSocietyQuery = function() {
		$http.jsonp('http://hengshui.5uban.com/xhac/aci/workPlatform/humanSociety/humanSocietyQuery.do', {
			params: {
				jsonpCallback: "JSON_CALLBACK",
				type: appData.type,
				zjhm: appData.archivesNumber,
				xm: appData.archivesName,
				mobile: $('#mobile').val(),
				itemCode:appData.code
			}
		}).success(function(dataJson) {
			console.log(dataJson);
			if(dataJson.tybm != null && dataJson.tybm != '') {
				$scope.isLoding = true;
				appData.tybm = dataJson.tybm; //统一审批编号
				$state.go("social");
			}
		}).error(function(err) {
			$scope.isAlert = true;
			$scope.msg = "请求异常，请返回重新操作！";
			$scope.isLoding = true;
			console.log("humanSocietyQuery error");
		});
	}

});

app.controller("social", function($scope, $http, $state, appData, appFactory, $timeout) {
	$scope.operation = appData.itemName;
	$scope.backok = false;
	$scope.lookok = false;
	$scope.printok = false;
	$scope.isAllScreen = false;
	$scope.isLoding = false;
	$scope.addmassage = "正在查询请稍后...";
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.configUrl = "http://hengshui.5uban.com/xhac";
	$scope.humanSocietyPrint = function() {
		$http.jsonp($scope.configUrl + '/aci/workPlatform/humanSociety/humanSocietyPrint.do', {
			params: {
				jsonpCallback: "JSON_CALLBACK",
				businessCode: appData.tybm,
				itemCode: appData.code
			}
		}).success(function(dataJson) {
			console.log(dataJson);
			if(dataJson.SUCCESS == "TRUE") {
				$scope.messageAll = dataJson.MSG;
				$scope.printPicture = dataJson.URL;
				$scope.previewImg = $scope.configUrl + $scope.printPicture;
				$scope.isLoding = true;
				$scope.backok = true;
				$scope.lookok = true;
				$scope.printok = true;
			} else if(dataJson.SUCCESS == "FALSE"){
				$scope.isAlert = true;
				$scope.msg = "请求失败，请联系工作人员！";
				$scope.isLoding = true;
			}

		}).error(function(err) {
			$scope.isAlert = true;
			$scope.msg = "请求异常，请返回重新操作！";
			$scope.isLoding = true;
			console.log("humanSocietyPrint error");
		});
	}
	$scope.humanSocietyPrint();
	
	$scope.look = function() {
		$scope.backok = false;
		$scope.lookok = false;
		$scope.printok = false;
		$scope.isAllScreen = true;
	};
	$scope.allScreen = function(str) {
		if(str === 'con' && $scope.isAllScreen === false) {
			return;
		}
		$scope.backok = true;
		$scope.lookok = true;
		$scope.printok = true;
		$scope.isAllScreen = !$scope.isAllScreen;
		return;
	};

		$scope.print = function() {
		$scope.isLoding = false;
		$scope.addmassage = "正在打印...";
		var lodop = $.device.printGetLodop();
		lodop.ADD_PRINT_IMAGE(0, 0, 800, 1300, "<img border='0' src='" + $scope.previewImg + "'>");
		lodop.SET_PRINT_STYLEA(0, "Stretch", 2); //按原图比例(不变形)缩放模式
		//lodop.ADD_PRINT_TEXT(0, 0, 400, 500, "此证照由市电子证照库提供");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		lodop.SET_PRINT_STYLEA(0, "FontColor", "#ccc");
		lodop.SET_PRINT_STYLEA(0, "ItemType", 1);
		lodop.SET_PRINT_STYLEA(0, "Angle", 50);
		lodop.SET_PRINT_STYLEA(0, "Repeat", true);
		lodop.PRINT();
		$timeout(function() {
			$scope.isLoding = true;
			$scope.printok = false;
			$state.go("list");
		}, 5000);
	}
	$scope.back = function() {
		$state.go("loginType");
	};
	

});