app.controller("synthesizeMain", function($scope, $state, appData) {
	$scope.choice = function(address) {
		appData.address = address;
		$state.go("iframe");
	};
	$scope.goToApp = function(address) {

		if(address.indexOf("http") != -1) {
			window.location.href = address;
		} else {
			window.location.href = address;
		}
	};
});
app.controller("synthesizeIframe", function($scope, $state,$timeout, appData,$sce) {
	$scope.address = $sce.trustAsResourceUrl(appData.address);	 
	window.external.URL_OPEN(50,160,1800,800,appData.address);
});