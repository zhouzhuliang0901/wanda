app.controller("synthesizeMain", function($scope, $state, appData) {
	$scope.choice = function(address) {
		appData.address = address;
		$state.go("iframe");
	};
});
app.controller("synthesizeIframe", function($scope, $state, $timeout, appData, $sce) {
	$scope.address = $sce.trustAsResourceUrl(appData.address);
	window.external.URL_OPEN(200,180,1500,700, appData.address);
	$scope.prevStep = function() {
		try {
			window.external.URL_CLOSE();
		} catch(e) {
			//TODO handle the exception
		}
		$state.go("main");
	}
});