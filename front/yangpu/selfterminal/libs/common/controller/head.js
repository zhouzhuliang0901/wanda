//头部控制器
app.controller("headController", function ($scope, $rootScope, $route, $location) {
	var strPath = window.document.location.pathname;
	var path = strPath.substring(strPath.indexOf("/", 2), strPath.length);
	var basePath = "../";
	if (strPath.indexOf("ext") > 0) {
		basePath = "../../../";
	}
	$scope.Home = function () {
		$.device.officeClose();
		$.device.GoHome();
		// window.location.href = "http://192.168.1.112:8020/my-git/productService/ext/huangpu/index.html?__hbt=1541641030865"
	};
	$scope.returnUpImg = "btn_back";
	$scope.orderSubmitImg = "btn_yuyue";
	$scope.homeImg = basePath + "libs/common/images/home.png";
	$scope.logoImg = basePath + "libs/common/images/logo.png";
	$scope.timingParam = 70;
	$scope.test = "test text!";
});