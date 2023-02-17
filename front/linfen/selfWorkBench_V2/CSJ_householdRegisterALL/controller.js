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
app.controller('choiceMode', function($state, $scope, appData, $timeout, appFactory, $http) {
	$scope.funName = appData.funName = "户籍证明开具";
	appData.itemCode = $scope.itemCode = "0105105000-07-04";
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.choiceType = function(type) {
		if(type == "print") {
			appData.modeType = "print";
		}
		$state.go("choiceProvince");
	}
})
app.controller('choiceProvince', function($state, $scope, appData, $timeout, appFactory, $http) {
	$scope.funName = appData.funName = "长三角户籍证明";
	if(acBridgeMac.vendor() == 'jhdevice'){
		$scope.isJhdevice = true;
	}else{
		$scope.isJhdevice = false;
	}
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.choiceType = function(type) {
		appData.type = type;
//		if(type == "zj" || type == "ah") {
//			$scope.isAlert = true;
//			$scope.msg = "该省市暂未开通户籍证明，请敬请期待！";
//			$scope.alertConfirm = function() {
//				$scope.isAlert = false;
//			}
//		} else {
			$state.go("guideline");
//		}
	}
	$scope.prevStep = function() {
		$state.go("choiceMode");
	}
})
app.controller("guideline", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.funName = appData.funName;
	$scope.prevStep = function() {
		$state.go("choiceProvince");
	}
	//sign = 1 代表从长三角进入
	$scope.nextStep = function() {
		if(appData.type == "sh") {
			if(appData.modeType == "print"){
				window.location.href = '../GA_householdRegister/index.html#/loginType?sign=1&type=print';
			}else{
				window.location.href = '../GA_householdRegister/index.html#/loginType?sign=1';
			}
		}else{
			if(appData.modeType == "print"){
				window.location.href = '../CSJ_householdRegister/index.html#/loginType?type='+appData.type+'&sign=1&modeType=print';
			}else{
				window.location.href = '../CSJ_householdRegister/index.html#/loginType?type='+appData.type+'&sign=1';
			}
		}
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: true,
			bounce: true,
			click: true,
			taps: true,
			preventDefault: false,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
	addAnimate($('.main2'))
});