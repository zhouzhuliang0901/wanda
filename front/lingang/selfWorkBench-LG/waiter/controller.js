app.controller("waiter", function ($scope, $state, appData,$sce) {
 	window.external.URL_OPEN(50,160,1800,800, "http://zwdtlg.sh.gov.cn");
 	$scope.address = $sce.trustAsResourceUrl("http://zwdtlg.sh.gov.cn");
});
