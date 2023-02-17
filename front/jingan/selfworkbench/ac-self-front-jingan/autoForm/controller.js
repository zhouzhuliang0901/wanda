app.controller("formMain", function($scope, $state, appData, appFactory) {
	$scope.operation = "自助取表";
	$scope.takeFormList = [{
			formName: "特种设备变更登记申请表",
			formImg: "../libs/common/images/autoForm/tzsb.jpg"
		},
		{
			formName: "联络员信息",
			formImg: "../libs/common/images/autoForm/lly.jpg"
		},
		{
			formName: "财务负责人信息",
			formImg: "../libs/common/images/autoForm/cwfzr.jpg"
		},
		{
			formName: "董事、监事、经理信息",
			formImg: "../libs/common/images/autoForm/dsjs.jpg"
		},
		{
			formName: "公司登记（备案、注销）申请书",
			formImg: "../libs/common/images/autoForm/gsdj.jpg"
		},
		{
			formName: "法定代表人信息",
			formImg: "../libs/common/images/autoForm/fddb.jpg"
		}
	];
//	console.log($scope.takeFormList)
//	$scope.currentPage = 1;
//	$scope.totalPages = Math.ceil($scope.takeFormList.length / 5);
//	$scope.currentList = $scope.takeFormList.slice(
//		($scope.currentPage - 1) * 5,
//		$scope.currentPage * 5
//	);
	$scope.prevPage = function() {
		$scope.takeFormList.unshift($scope.takeFormList.pop());
	};
	$scope.nextPage = function() {
		$scope.takeFormList.push($scope.takeFormList.shift());
	};
	//  $scope.prevPage = function () {
	//      if ($scope.currentPage > 1) {
	//          $scope.currentPage--;
	//          $scope.currentList = $scope.takeFormList.slice(
	//              ($scope.currentPage - 1) * 5,
	//              $scope.currentPage * 5
	//          );
	//      }
	//  };
	//  $scope.nextPage = function () {
	//      if ($scope.currentPage < $scope.totalPages) {
	//          $scope.currentPage++;
	//          $scope.currentList = $scope.takeFormList.slice(($scope.currentPage - 1) * 3, $scope.currentPage * 3);
	//      }
	//  };

	$scope.prev = function() {
		$.device.GoHome();
	}
	$scope.isPrint = "show";
	$scope.choiceForm = function(item) {
		appData.formName = item.formName;
		appData.imgUrl = item.formImg;

		if(appData.formName == "特种设备变更登记申请表") {
			$state.go("loginType");
		} else {
			$state.go("explain");
		}
	};
});

app.controller("formExplain", function($scope, $state, appData, appFactory) {
	$scope.operation = "";
	$scope.largeImg = appData.imgUrl;
	$scope.nextStep = function() {
		$state.go("paste");
//		$state.go('print');
	};
});

app.controller("formLoginType", function($state, $scope, appData) {
	$scope.operation = "请选择登录方式";
	$scope.formName = appData.formName;
	$scope.choiceLogin = function(type) {
		//		appData.idcardName = "zoutanqi";
		//		appData.idCardNum = "430426199508138892";//"310105197805313613";
		//		appData.mobile = "18692067056";
		//		appData.nation = "汉族";
		//		if($scope.formName == "居住登记信息表") {
		//			$state.go("jzdj");
		//		} else if($scope.formName == "上海市居住证申请表") {
		//			$state.go("jzsq");
		//		} else if($scope.formName == "上海市居住证签注申请表") {
		//			$state.go("jzqzsq");
		//		}
		appData.loginType = type;
		$state.go("login");
	};
});

app.controller("formLogin", function($scope, $http, $state, appData, appFactory) {
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
			$scope.operation = "随申办";
			break;
	}
	// ukey登录
	$scope.caLoginStatus = "";
	$scope.caLogin = function() {
		//登录
		$scope.caLoginStatus = "login";
	};
	$scope.caInfo = function(companyName, companyNo) {
		if(companyName && companyNo) {
			appData.companyName = companyName;
			appData.companyNo = companyNo;
			$state.go("print");
			$scope.$apply();
		}
	};
	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = "recognition";
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
		} else {
			layer.msg("没有获取到");
		}
	};
	$scope.getResult = function(img) {
		$scope.img = img;
		$state.go("license");
	};
	$scope.prevStep = function() {
		$state.go("loginType");
	};

	$scope.citizenLogin = function(info) {
		var idcardInfo = info.result.data;
		appData.licenseName = idcardInfo.realname;
		if(appData.licenseType == "person") {
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
				$state.go("license");
				$scope.$apply();
			}
		} else if(appData.licenseType == "corporate") {
			if(info.code == idcardInfo.idcard) {
				layer.msg(
					"未识别到您的企业信息，请确认二维码正确后重新扫描！", {
						time: 5000
					}
				);
				$state.reload();
			} else {
				appData.licenseNumber = info.code;
				$state.go("license");
				$scope.$apply();
			}
		}
	};
});

app.controller("formPaste", function($scope, $state, appData) {
	$scope.operation = "读取身份证信息";
	$scope.frontImg = null;
	$scope.backImg = null;
	$scope.photograph = false;
	appData.idcardObj = {};
	$scope.readIdcard = function(info) {
		appData.idcardObj.idcardName = info.Name;
		appData.idcardObj.idcardNumber = info.Number;
		$scope.operation = "拍摄身份证正面";
		$scope.photograph = true;
	};
	$scope.getImg = function(img, url) {
		if(!img) {
			return;
		}
		if($scope.frontImg === null) {
			$scope.frontImg = img;
			$scope.operation = "拍摄身份证反面";
			appData.idcardObj.frontUrl = url;
			return;
		} else {
			$scope.backImg = img;
			console.log("拍照完毕");
			console.log($scope.frontImg, $scope.backImg);
			appData.idcardObj.backUrl = url;
			$state.go("print");
			return;
		}
	};
});

app.controller("formPrint", function($scope, $state, appData, appFactory, $timeout) {
	$scope.largeImg = "../libs/common/images/autoForm/png";
	$scope.printQuantity = 1;
	$scope.isPrint = false;

	$scope.getForm = function() {
		//alert(appData.idcardObj.frontUrl+""+appData.idcardObj.backUrl);
		var index2 = layer.load(0, {
			shade: [0.5, "white"] //0.7透明度的黑色背景
		});

		//word表格类型
		$scope.name = appData.formName + ".doc";
		$.device.officeOpenRelative($scope.name);
		$timeout(function() {
			layer.close(index2);
			$.device.officeShow(640, 720, 220, 160);
		}, 4000);
		$timeout(function() {
			if(appData.companyName && appData.companyNo) {
				$.device.officeSetStringValue("companyName", appData.companyName);
				$.device.officeSetStringValue("companyNo", appData.companyNo);
			} else {
				$.device.officeSetStringValue("idCardNo", appData.idcardObj.idcardNumber);
				$.device.officeSetStringValue("idCardName", appData.idcardObj.idcardName);
				$.device.officeSetJpgValue("frontImg", appData.idcardObj.frontUrl);
				$.device.officeSetJpgValue("reverseImg", appData.idcardObj.backUrl);
				
			}
			$.device.officeReadOnly(true);
		}, 2000);
	};
	$scope.getForm();

	$scope.minus = function() {
		if($scope.printQuantity > 1) {
			--$scope.printQuantity;
			console.log($scope.printQuantity);
		} else {
			$scope.printQuantity = 1;
		}
	};
	$scope.plus = function() {
		if($scope.printQuantity < 5) {
			++$scope.printQuantity;
			console.log($scope.printQuantity);
		} else {
			$scope.printQuantity = 5;
		}
	};
	$scope.print = function() {
		for(var i = 1; i <= $scope.printQuantity; i++) {
			$.device.officePrint();
		}
		$.device.officeClose();
//		$.device.officePrint();
//		$.device.officeClose();
		$scope.isPrint = "show";
		$timeout(function() {
			$scope.isPrint = "hidden";
			$state.go("main");
		}, 5000);
	};
});