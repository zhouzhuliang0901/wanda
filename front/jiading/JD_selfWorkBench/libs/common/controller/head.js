//头部控制器
app.controller("headController", function($scope, $rootScope, $route, $location, data) {
	var strPath = window.document.location.pathname;
	var path = strPath.substring(strPath.indexOf("/",2),strPath.length);
	var basePath = "../";
	if(path.indexOf("ext") > 0){
		basePath = "../../../";
	}
	$scope.returnHome = function() {
		try {
			window.external.ReturnToHome();
		} catch(e) {
			if($.getConfigMsg.isextproduct){
				if(path.indexOf("ext") > 0){
					window.location.href = "../index.html";
				}else{
					window.location.href = "../"+$.getConfigMsg.extproductpath+"index.html";
				}
	    	}else{
	    		window.location.href = "../index.html";
	    	}
		}
	};
	$rootScope.returnUpImg = "btn_back";
	$rootScope.orderSubmitImg = "btn_yuyue";
	$rootScope.logoImg = basePath + $.getConfigMsg.logoIcon;
	$rootScope.homeImg = basePath + "libs/common/images/home.png";
	$rootScope.timingParam = 70;
	console.log($rootScope.logoImg,$rootScope.homeImg);
});