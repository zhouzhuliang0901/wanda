app.controller("main", function($scope, $state, appData, $sce) {
	$scope.choice = function(address) {
		appData.address = address;
		$state.go("iframe");
	};
	$scope.goToDetails = function() {
		$state.go("details");
	}
});
app.controller("details", function($scope, $state, $timeout, appData, $sce) {
	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
	$scope.choice = function(address) {
		appData.address = address;
		$state.go("iframe");
	};
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller("iframe", function($scope, $state, $timeout, appData, $sce) {
	$scope.address = $sce.trustAsResourceUrl(appData.address);
	window.external.URL_OPEN(200,180,1500,700, appData.address);
	$scope.prevStep = function() {
		$state.go("main");
	}
});