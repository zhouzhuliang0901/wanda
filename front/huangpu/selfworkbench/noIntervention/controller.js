app.controller("synthesizeIframe", function($scope, $state,$timeout, appData,$sce) {
	$scope.funName = "无人干预";
	appData.address = "http://180.169.19.159:8080/zwdt/hpaizwdt/wrspindex/nobody";
	console.log($scope.funName);
	$scope.address = $sce.trustAsResourceUrl(appData.address);	 
	window.external.URL_OPEN(200,180,1500,700,appData.address);
});
