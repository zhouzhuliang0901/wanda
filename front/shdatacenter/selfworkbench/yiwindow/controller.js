app.controller("waiter", function ($scope, $state, appData,$sce) {
 	window.external.URL_OPEN(50,160,1800,800, "http://yct.sh.gov.cn/portal_yct/");
 	$scope.address = $sce.trustAsResourceUrl("http://yct.sh.gov.cn/portal_yct/");
});
