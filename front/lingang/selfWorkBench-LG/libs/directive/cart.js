app.directive("cart", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/token.html",
		scope: {
			result: "&",
		},
		controller: function($scope, $location, $state, $rootScope, appData, $interval, $timeout) {
			$scope.isAlert = false;
			$scope.concel = "false";
			//获取证书
			$scope.getCertToken = function() {
				console.log("获取证书");
				$.ajax({
					type: "get",
					url: "http://127.0.0.1:18495/cert/token",
					data: {},
					dataType: "json",
					success: function(result) {
						console.log(result);
						if(result.code== 0) {
							$scope.GetDataMac(result.data.tokenBase64);
						} else {
							$scope.MyAlert("获取证书失败");
						}
					},
					error: function(err) {
						$scope.MyAlert("请检查是否打开协卡助手");
					},
				});
			};
			//获取MAC
			$scope.GetDataMac = function(token) {
				$.ajax({
					type: "post",
					url: "http://127.0.0.1:18495/cert/getMac",
					data: {},
					dataType: "json",
					success: function(result) {
						console.log(result);
						if(result.data) {
							$scope.cartTokenMAC(token, result.data);
						} else {
							$scope.MyAlert("获取MAC失败");
						}
					},
					error: function(err) {
						console.log(err);
					},
				});

			};
			//认证
			$scope.cartTokenMAC = function(value, mac) {
				var token = {
					'token': value,
					'machineId': mac
				};
				$.ajax({
					type: "post",
					url: "http://180.169.7.194:8081/ac-self/selfapi/authentication.do",
					dataType: "json",
					data: token,
					success: function(result) {
						console.log(result);
						if(result.success == true) {
							console.log(result.data);
						} else {
							$scope.MyAlert("认证失败");
						}
					},
					error: function(err) {
						$scope.MyAlert("认证接口调用失败");
					},
				});
			};
			$timeout(function() {
				$scope.getCertToken();
			});
			$scope.MyAlert = function(txt) {
				$scope.isAlert = true;
				$scope.msg = txt;
				$scope.$apply();
				$scope.alertConfirm = function() {
					console.log("返回首页");
					//$.device.GoHome();
				}
			};
		}
	}
});