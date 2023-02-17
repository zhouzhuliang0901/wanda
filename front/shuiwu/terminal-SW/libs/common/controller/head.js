//头部控制器
app.controller("headController", function ($scope, $route, $location) {
	var strPath = window.document.location.pathname;
	var path = strPath.substring(strPath.indexOf("/", 2), strPath.length);
	var basePath = "../";
	if (strPath.indexOf("ext") > 0) {
		basePath = "../../../";
	}
	$scope.Home = function () {
		$.device.officeClose();
		$.device.GoHome();
	};
	$scope.back = function () {
		history.go(-1);
	}
	$scope.basePath = basePath;
	$scope.returnUpImg = "btn_back";
	$scope.orderSubmitImg = "btn_yuyue";
	$scope.homeImg = basePath + "libs/common/images/home.png";
	$scope.logoImg = basePath + "libs/common/images/logo1.png";
	$scope.timingParam = 70;
	$scope.test = "test text!";
});