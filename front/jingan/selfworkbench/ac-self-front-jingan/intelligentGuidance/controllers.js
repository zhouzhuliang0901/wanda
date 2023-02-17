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
app.controller("mainController", function($scope, $location, $http, data, $rootScope, $timeout, $interval, appFactory) {
	$scope.choiceType = function (choiceType) {
		if(choiceType == 'companyStart') {
			console.log(111)
			$location.path('/companyStart');
		} else if(choiceType == 'projectConstruction') {
			$location.path('/projectConstruction');
		}
	}
	$scope.prevStep = function () {
		$location.path('../main');
	}
	$rootScope.goHome = function() {
		try{
			window.external.URL_CLOSE();
		}catch(e){}
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
	};
});
app.controller("companyStartController", function($scope, $route, $http, $location, data, $timeout, $rootScope, $sce, appFactory) {
	$scope.applyUrl = "http://zwdtja.sh.gov.cn/zwdtSW/smart/zwdtSW/workGuide/typeGuide.jsp";
	window.external.URL_OPEN(200, 150, 1500, 750, $scope.applyUrl);
	
	// 设置url被angular信任 正常跳转
	$scope.prevStep = function() {
		try {
			window.external.URL_CLOSE();
		} catch(e) {

		}
		$location.path("/main");
	}
	$scope.goHome = function() {
		$.device.Camera_Hide();
		$.device.Camera_UnLink();
		$.device.cmCaptureHide();
		$.device.idCardClose();
		$.device.qrCodeClose();
		$.device.officeClose();
		try {
			window.external.URL_CLOSE();
		} catch(e) {
			//TODO handle the exception
		}
		$.device.GoHome();
	};
});
app.controller("projectConstructionController", function($scope, $route, $http, $location, data, $timeout, $rootScope, $sce, appFactory) {
	$scope.applyUrl = "http://zwdtja.sh.gov.cn/zwdtSW/smart/construction/zwdtSW/workGuide/typeGuide.jsp";
	window.external.URL_OPEN(200, 150, 1500, 750, $scope.applyUrl);

	// 设置url被angular信任 正常跳转
	$scope.prevStep = function() {
		try {
			window.external.URL_CLOSE();
		} catch(e) {

		}
		$location.path("/main");
	}
	$scope.goHome = function() {
		$.device.Camera_Hide();
		$.device.Camera_UnLink();
		$.device.cmCaptureHide();
		$.device.idCardClose();
		$.device.qrCodeClose();
		$.device.officeClose();
		try {
			window.external.URL_CLOSE();
		} catch(e) {
			//TODO handle the exception
		}
		$.device.GoHome();
	};
});
