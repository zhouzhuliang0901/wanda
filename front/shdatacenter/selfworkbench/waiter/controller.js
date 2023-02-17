app.controller("waiter", function ($scope, $state, appData,$sce) {
 	window.external.URL_OPEN(50,160,1800,800, "http://zwdthk.sh.gov.cn:8080/hkzwdt/jszwdt/pages/clubservice/index.html");
 	$scope.address = $sce.trustAsResourceUrl("http://zwdthk.sh.gov.cn:8080/hkzwdt/jszwdt/pages/clubservice/index.html");
});
