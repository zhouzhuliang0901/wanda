app.controller('copyType', function($state, $scope, appData,$http) {
	$scope.goToApp = function(address) {
		
		if(address.indexOf("http") != -1) {
			window.location.href = address;
		} else {
			window.location.href = address;
		}
	};
});
