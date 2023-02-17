app.controller("mainController", function($scope, $route, $location, $http, data, $timeout) {
	$scope.currentPage = 1;
	$scope.formList = [{
			imgUrl: "../libs/common/images/yangbiao/liaisonMan.png",
			formName: "联络员信息",
			paste: true
		},
		{
			imgUrl: "../libs/common/images/yangbiao/finance.png",
			formName: "财务负责人信息",
			paste: true
		},
		{
			imgUrl: "../libs/common/images/yangbiao/company.png",
			formName: "公司登记（备案、注销）申请书",
			paste: true
		},
		{
			imgUrl: "../libs/common/images/yangbiao/非公司企业法人登记（备案）申请书.jpg",
			formName: "非公司企业法人登记（备案）申请书",
			paste: true
		},
		{
			imgUrl: "../libs/common/images/yangbiao/非公司企业法人改制登记（备案）申请书（含材料目录）.jpg",
			formName: "非公司企业法人改制登记（备案）申请书（含材料目录）",
			paste: true
		},
		{
			imgUrl: "../libs/common/images/yangbiao/个人独资企业登记（备案）申请书（含材料目录）.jpg",
			formName: "个人独资企业登记（备案）申请书（含材料目录）",
			paste: true
		},
		{
			imgUrl: "../libs/common/images/yangbiao/合伙企业登记（备案）申请书.jpg",
			formName: "合伙企业登记（备案）申请书",
			paste: true
		},
		{
			imgUrl: "../libs/common/images/yangbiao/股权出质登记申请书（含材料目录）.jpg",
			formName: "股权出质登记申请书（含材料目录）",
			paste: true
		},
		{
			imgUrl: "../libs/common/images/yangbiao/增、减、补、换发证照申请书（含材料目录）.jpg",
			formName: "增、减、补、换发证照申请书（含材料目录）",
			paste: true
		},
		{
			imgUrl: "../libs/common/images/yangbiao/换照申领表（启用新办执照使用）.jpg",
			formName: "换照申领表（启用新办执照使用）",
			paste: true
		}
	];
	$scope.PagingList = EXTPaging({ //进行分页
		data: $scope.formList,
		quantity: 6
	});
	$scope.listData = $scope.PagingList.data[0];
	$scope.nextPage = function() {
		if($scope.currentPage < $scope.PagingList.pages) {
			$scope.currentPage++;
		}
	};
	$scope.previousPage = function() {
		if($scope.currentPage > 1) {
			$scope.currentPage--;
		}
	};
	$scope.$watch("currentPage", function() {
		$scope.listData = $scope.PagingList.data[$scope.currentPage - 1];
	});
	$scope.takeTab = function(index) {
		data.formName = $scope.listData[index].formName;
		data.isPaste = $scope.listData[index].paste;
		data.formImg = $scope.listData[index].imgUrl;
		$location.path("/note");
	};
});
app.controller("noteController", function($scope, $route, $location, $http, $sce, data, $routeParams) {
	$scope.showImg = data.formImg;
	$scope.formName = data.formName;

	$scope.takeTable = function() {
		if(data.isPaste === true) {
			$location.path("/idCard");
		} else if(data.isPaste === false) {
			$location.path("/fillform");
		}
	};
});
app.controller("idCardController", function($scope, $route, $location, data, $timeout, $rootScope) {
	$scope.readIdCard = function() {
		PROMISE_METHOD.getIdCardInfo()
			.then(function(dataObj) {
				var list = JSON.parse(dataObj.identityInfo);
				data.idCardNumber = list.Code;
				data.userName = list.Name;
				$location.path("/fillform");
				$scope.$apply();
			})
			.catch(function(err) {
				console.log(err);
			})
	}
	$scope.readIdCard();
});
app.controller("fillformController", function($scope, $route, $location, $http, $sce, data, $timeout) {
	$scope.printNums = 1;
	$scope.showImg = data.formImg; //显示的表图片
	$scope.formName = data.formName;
	data.printNums = $scope.printNums;
	$scope.getForm = function() {
		var index2 = layer.load(0, {
			shade: [0.5, 'white'] //0.7透明度的黑色背景
		});
		//word表格类型
		$.device.officeOpenRelative(data.formName + ".doc");
		$timeout(function() {
			layer.close(index2);
			$.device.officeShow(700, 760, 730, 240);
		}, 4000);
		$timeout(function() {
			$.device.officeSetStringValue('idCardNo', data.idCardNumber);
			$.device.officeSetStringValue('idCardName', data.userName);
			$.device.officeSetJpgValue('frontImg', "d:/Front.bmp");
			$.device.officeSetJpgValue('reverseImg', "d:/Back.bmp");
			$.device.officeReadOnly(true);
		}, 2000);

	};
	$scope.getForm();
	$scope.printWord = function() {
		$.device.officePrint();
		$location.path("/pwait");
	};
	$scope.home = function() {
		$location.path("/main");
	};
});