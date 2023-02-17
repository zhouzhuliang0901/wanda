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
	$scope.isShowCancel = true;
	$scope.choiceType = function(type) {
		appData.inputType = type;
		if(type == 'intelligent_consult') {
//			$scope.isAlert = true;
//			$scope.isShowCancel = false;
//			$scope.msg = '敬请期待';
						$state.go("intelligentConsult")
		} else if(type == 'teacher_mao') {
			$scope.isShowCancel = true;
			// 打开视频咨询工具
			let date = new Date();
			let week = date.getDay();
			let hour = date.getHours();
			let videoWeek = $.config.get('videoWeek').split(',');
			let thisDay = false; // 判断今天是否开启毛老师工作室
			for (i = 0; i < videoWeek.length; i++) {
				
				if(Number(videoWeek[i]) == week) {
					thisDay = true;
				}
			
			}
			if($.config.get('videoEndTime') > 24) {
				$scope.isAlert = true;
				$scope.msg = '视频咨询开放时间为每周二上午9:00-11:00';
			} else if(thisDay && $.config.get('videoStartTime') <= hour && $.config.get('videoEndTime') >= hour) {
				$.log.debug(window.external.GetCurrentPath() + "\\resources\\wandazhineng\\msconf.exe");
				$.device.Camera_UnLink();
				$.device.cmCaptureClose();
				$.device.exeOpenAbsolute("智能工作台视频咨询", window.external.GetCurrentPath() + "\\resources\\wandazhineng\\msconf.exe");
			} else {
				$scope.isAlert = true;
				$scope.msg = '视频咨询开放时间为每周二上午9:00-11:00';
			}
		}
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.alertCancel = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		$.device.idCardClose();
		$.device.qrCodeClose();
		$.device.GoHome();
	}
});
app.controller("intelligentConsult", function($scope, $http, $location, appData, $timeout, $rootScope, $sce, appFactory) {
	$scope.applyUrl = "https://shanghai.chinatax.gov.cn/znzx/znzxnsr/main";
	if(window.innerWidth == 1920) {
		window.external.URL_OPEN(200, 150, 1500, 750, $scope.applyUrl);
	} else if(window.innerWidth == 1366) {
		window.external.URL_OPEN(0, 70, 1366, 600, $scope.applyUrl);
	} else if(window.innerwidth == 1280) {
		window.external.URL_OPEN(0, 120, 1280, 750, $scope.applyUrl);
	}
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