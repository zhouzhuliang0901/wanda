app.controller("waiter", function ($scope, $state, appData,$sce) {
	$scope.funName = "微信";
	$scope.prevStep = function(){
		$state.go("main");
	}
   	window.external.URL_OPEN(50,160,1800,800, "https://wx.qq.com");
 	$scope.address = $sce.trustAsResourceUrl("https://wx.qq.com");
});
