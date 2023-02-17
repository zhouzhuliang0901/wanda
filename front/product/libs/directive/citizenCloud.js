app.directive("citizenCloud", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/citizenCloud.html",
		scope: {
			result: "&",
		},
		controller: function($scope, $location, $state, $rootScope, $interval, $timeout) {
			$scope.tipsText = "扫描随申办身份证二维码";
			$scope.cloud = true;
			$scope.load = false;
			$scope.tipsImage = "../libs/common/images/cloud.png";
			$scope.fetchError = false;
			$scope.loading = false;
			$scope.reLogin = function() {
				$state.reload();
			}
			$scope.success = function() {
				if($scope.tipsText === "登录成功") {
					$scope.result();
				} else {
					$rootScope.goAppHistoryBack()
				}

			}
			$scope.citizenCloud = function() {
				function ClearBr(key) { 
					key = key.replace(/<\/?.+?>/g,""); 
					key = key.replace(/[\r\n]/g, "");
					key = key.trim();
					return key; 
				}
				$.device.qrCodeOpen(function(code) {
					$scope.loading = true;
					var timerout = $timeout(function() {
						$scope.fetchError = true;
						$scope.tipsText = "登录超时";
						console.log("登录超时");
					}, 5000);
					var __code = ClearBr(code);
					$scope.cloud = false;
					$scope.load = true;
					$scope.tipsImage = "../libs/common/images/loadings.gif";
					$.ajax({
						url: "http://218.202.254.222/aci/window/getInfoByCodeTest.do",
						dataType: 'jsonp',
						jsonp: "jsonpCallback",
						data:{
							codeParam:__code
						},
						success: function(dataJonsp) {
							$.log.debug(code);
							//alert(JSON.stringify(dataJonsp))
							$timeout.cancel(timerout);
							if(dataJonsp.result.success) {
								$scope.loading = false;
								$scope.tipsText = "登录成功";
								$scope.result({
									info: dataJonsp
								});
							}else{
								$.log.debug(JSON.stringify(dataJonsp));
								//登录失败
								$scope.tipsText = "登录失败: " + dataJonsp.result.msg;
								$scope.fetchError = true;
								return false;
							}
						},
						error: function(err) {
							//alert(err)
							$scope.tipsText = "登录失败";
							$scope.fetchError = true;
							$timeout.cancel(timerout);
						}
					})
				});
			};
			$scope.citizenCloud();

		}
	}
});