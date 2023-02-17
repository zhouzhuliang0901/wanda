app.controller("waiter", function ($scope, $state, appData,$sce) {
	window.external.URL_OPEN(60,130,1800,800, "http://newportal.lgxc.gov.cn/lgxpq/#/login");
   	$scope.address = $sce.trustAsResourceUrl("http://newportal.lgxc.gov.cn/lgxpq/#/login");
});
