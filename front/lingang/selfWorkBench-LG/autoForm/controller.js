app.controller("formMain", function($scope, $state, appData, appFactory) {
	$scope.operation = "自助填表";
	$scope.takeFormList = [{
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
	$scope.currentPage = 1;
	$scope.totalPages = Math.ceil($scope.takeFormList.length / 3);
	$scope.currentList = $scope.takeFormList.slice(($scope.currentPage - 1) * 3, $scope.currentPage * 3);
	//	$scope.prev = function() {
	//		if($scope.currentPage>1){
	//			$scope.currentPage--;
	//			$scope.currentList = $scope.takeFormList.slice(($scope.currentPage - 1) * 3, $scope.currentPage * 3);
	//
	//		}
	//	}
	//	$scope.next = function() {
	//		if($scope.currentPage<$scope.totalPages){
	//			$scope.currentPage++;
	//			$scope.currentList = $scope.takeFormList.slice(($scope.currentPage - 1) * 3, $scope.currentPage * 3);
	//		}
	//	}
	$scope.prevPage = function() {
		$scope.takeFormList.unshift($scope.takeFormList.pop());
	};
	$scope.nextPage = function() {
		$scope.takeFormList.push($scope.takeFormList.shift());
	};
	$scope.isPrint = "show";
	$scope.choiceForm = function(item) {
		appData.formName = item.formName;
		appData.imgUrl = item.formImg;
		$state.go("explain");
	};
});
app.controller("formExplain", function($scope, $state, appData, appFactory) {
	$scope.operation = "";
	$scope.largeImg = appData.imgUrl;
	$scope.nextStep = function() {
		$state.go("paste");
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
		};
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
			$.device.idCardClose();
			$.device.cmCaptureClose();
			$state.go("print");
			return;
		}
	};
	$scope.prevStep = function() {
		$state.go("explain");
	}
});
app.controller("formPrint", function($scope, $state, appData, appFactory, $timeout) {
	$scope.largeImg = "../libs/common/images/autoForm/png";
	$scope.printQuantity = 1;
	$scope.isPrint = false;

	$scope.getForm = function() {
		var index2 = layer.load(0, {
			shade: [0.5, 'white'] //0.7透明度的黑色背景
		});
		//word表格类型
		$scope.name = appData.formName + ".doc";
		$.device.officeOpenRelative($scope.name);
		$timeout(function() {
			layer.close(index2);
			$.device.officeShow(600, 700, 260, 170);
		}, 4000);
		$timeout(function() {
			$.device.officeSetStringValue('idCardNo', appData.idcardObj.idcardNumber);
			$.device.officeSetStringValue('idCardName', appData.idcardObj.idcardName);
			$.device.officeSetJpgValue('frontImg', appData.idcardObj.frontUrl);
			$.device.officeSetJpgValue('reverseImg', appData.idcardObj.backUrl);
			$.device.officeReadOnly(true);
		}, 2000);
	};
	$scope.getForm();

	$scope.minus = function() {
		if($scope.printQuantity > 1) {
			--$scope.printQuantity;
		}
	}
	$scope.plus = function() {
		if($scope.printQuantity < 5) {
			++$scope.printQuantity;
		}
	}
	$scope.print = function() {
		$.device.officePrint();
		$.device.officeClose();
		$scope.isPrint = "show";
		$timeout(function() {
			$scope.isPrint = "hidden";
			$state.go("main");
		}, 3000);
	}
});