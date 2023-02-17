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

app.controller("searchMain", function($scope, appData, $state, $rootScope) {
	removeAnimate($('.linkBox1'))
	$scope.operation = "请选择咨询方式";
	addAnimate($('.linkBox1'))
	$scope.chooseSwipeType = function(type) {
		appData.inputType = type;
		if(type == 'func_introduct') {
			$state.go("func_introduct")
		} else if(type == 'handle_person') {
			$state.go("handle_person")
		} else if(type == 'operate_rules') {
			$state.go("operate_rules")
		} else if(type == 'handle_legal') {
			$state.go('handle_legal')
		} else {
			$state.go('main')
		}
	}
	$scope.prevStep = function() {
		$.device.idCardClose();
		$.device.qrCodeClose();
		$.device.GoHome();
	}
});
app.controller("func_introduct", function($scope, $http, $location, appData, $timeout, $rootScope, $sce, appFactory) {
	$scope.applyUrl = "https://etax.chinatax.gov.cn/bzzx.html";
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
app.controller("handle_person", function($scope, $http, $location, appData, $timeout, $rootScope, $sce, appFactory) {
	$scope.applyUrl = "https://etax.chinatax.gov.cn/";
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
app.controller("operate_rules", function($scope, $http, $location, appData, $timeout, $rootScope, $sce, appFactory) {
	$scope.applyUrl = "https://etax.shanghai.chinatax.gov.cn/yhs-web/cxzx/index.html?&id=9912#/operatingProcedures";
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
app.controller("handle_legal", function($scope, $http, $location, appData, $timeout, $rootScope, $sce, appFactory) {
	$scope.applyUrl = "https://etax.shanghai.chinatax.gov.cn/wszx-web/bszm/apps/views/beforeLogin/indexBefore/pageIndex.html#/";
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