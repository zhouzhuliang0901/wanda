app.directive("alert", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/alert.html",
		scope: {
			msg:"=",
			confirm:"&",
			cancel:"&",
			hasCancel:"@",
			confirmText:"@",
			cancelText:"@",
			concel:"=",
		},
		controller: function($scope, $location, $rootScope, $interval, $timeout) {
			$scope.messge = $scope.msg||"您已预约成功";
			console.log();
			$scope.isAlert = true;
			$scope.__confirmText = $scope.confirmText||"确定";
			$scope.__cancelText = $scope.cancelText||"取消";
			if($scope.concel=="false"){
				$scope.__concel = false;
			}else{
				$scope.__concel = true;
			}
			$scope.$on("$destroy",function(){
				$scope.msg = null;
			});
			$scope.__cancel = function(){
//				$scope.isAlert = false;
				$scope.cancel();
			};
			$scope.__confirm = function(){
			//	$scope.isAlert = false;
				$scope.confirm();
			};
//			$scope.__close = function(){
//				$scope.isAlert = false;
//			}
			$scope.maxCountDown = 60;
			$scope.timer = null;
			$scope.timeCount = function() {
				$interval.cancel($scope.timer);
				$scope.timer = $interval(function() {
					$scope.maxCountDown--;
					if($scope.maxCountDown < 1) {

						$interval.cancel($scope.timer);
						$scope.goHome();
					}
				}, 1000);
			}
			$scope.timeCount();
			$scope.resetCountDown = function() {
				$interval.cancel($scope.timer);
				$timeout(function() {
					$scope.maxCountDown = 60;
				});
				$timeout(function() {
					$scope.timeCount();
				}, 5000);
			};
			window.addEventListener("click", $scope.resetCountDown);
			window.addEventListener("touchstart", $scope.resetCountDown);
			window.addEventListener("input", $scope.resetCountDown);
		}
	}
});

app.directive("zalert", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/Zalert.html",
		scope: {
			msg:"=",
			confirm:"&",
			cancel:"&",
			hasCancel:"@",
			confirmText:"@",
			cancelText:"@",
		},
		controller: function($scope, $location, $state, $rootScope, $interval, $timeout) {
			$scope.messge = $scope.msg||"成功";
			$scope.isAlert = true;
			$scope.__confirmText = $scope.confirmText||"确定";
			$scope.__cancelText = $scope.cancelText||"取消";
			$scope.$on("$destroy",function(){
				$scope.msg = null;
			});
			$scope.__cancel = function(){
//				$scope.isAlert = false;
				$scope.cancel();
			};
			$scope.__confirm = function(){
				$scope.isAlert = false;
				$state.go("main");
				//$scope.confirm();
			};
			$scope.__close = function(){
				$scope.isAlert = false;
				$state.go("main");
			}
		}
	}
});

app.directive("salert", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/Salert.html",
		scope: {
			msg:"=",
			confirm:"&",
			cancel:"&",
			hasCancel:"@",
			confirmText:"@",
			cancelText:"@",
		},
		controller: function($scope, $location, $rootScope, $interval, $timeout) {
			$scope.messge = $scope.msg||"成功";
			$scope.isAlert = true;
			$scope.__confirmText = $scope.confirmText||"确定";
			$scope.__cancelText = $scope.cancelText||"取消";
			$scope.$on("$destroy",function(){
				$scope.msg = null;
			});
			$scope.__cancel = function(){
//				$scope.isAlert = false;
				$scope.cancel();
			};
			$scope.__confirm = function(){
				$scope.confirm();
			};
			$scope.__close = function(){
				$scope.isAlert = false;
			}
		}
	}
});