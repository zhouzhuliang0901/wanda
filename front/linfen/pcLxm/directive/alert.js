app.directive("alert", function() {
	return {
		restrict: "E",
		templateUrl: "directive/alert.html",
		scope: {
			msg: "=",
			confirm: "&",
			cancel: "&",
			hasCancel: "@",
			confirmText: "=",
			cancelText: "=",
			concel: "=",
			confirmshow: "="
		},
		controller: function($scope, $location, $rootScope, $interval, $timeout, $sce) {
			$scope.messge = $sce.trustAsHtml($scope.msg) || "";
			$scope.isAlert = true;
			$scope.__confirmText = $scope.confirmText || "确定";
			$scope.__cancelText = $scope.cancelText || "取消";
			console.log($scope.concel);
			if($scope.concel == "false") {
				$scope.__concel = false;
			} else {
				$scope.__concel = true;
			}
			if($scope.confirmshow == "false") {
				$scope.__confirmshow = false;
			} else {
				$scope.__confirmshow = true;
			}
			try {
//				$scope.$on("$destroy", function() {
//					$scope.msg = null;
//				});
			} catch(e) {}

			$scope.__cancel = function() {
				$scope.cancel();
			};
			$scope.__confirm = function() {
				$scope.confirm();
			};
		}
	}
});