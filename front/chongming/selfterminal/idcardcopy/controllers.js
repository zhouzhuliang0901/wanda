app.controller("mainController", function ($scope, $route, $location, data, $timeout, $rootScope) {
	$scope.readIdCard = function () {
		PROMISE_METHOD.getIdCardInfo()
			.then(function (res) {
				$location.path("/pwait").search({
					imageType: "idcard",
					payParams: "1"
				});
				$scope.$apply();
			})
			.catch(function (err) {
				console.log(err);
			})
	}

	$scope.readIdCard();
});