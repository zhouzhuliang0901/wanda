app.controller("waiter", function ($scope, $state, appData,$sce) {
	$scope.funName = "上海虹口";
 	window.external.URL_OPEN(200,180,1500,700, "http://www.shhk.gov.cn/shhk/");
 	$scope.address = $sce.trustAsResourceUrl("http://www.shhk.gov.cn/shhk/");
});
