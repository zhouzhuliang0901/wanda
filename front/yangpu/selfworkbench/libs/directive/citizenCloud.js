app.directive("citizenCloud", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/citizenCloud.html",
		scope: {
			result: "&",
		},
		controller: function($scope, $location, $state, $rootScope, $interval, $timeout) {
			$scope.tipsText = "扫描市民云身份证二维码";
			$scope.cloud = true;
			$scope.load = false;
			$scope.tipsImage = "../libs/common/images/tips/cloud.png";
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
					key = key.replace(/<\/?.+?>/g, "");
					key = key.replace(/[\r\n]/g, "");
					key = key.trim();
					return key;
				}
				$.device.qrCodeOpen(function(code) {
					$scope.loading = true;
					var __code = ClearBr(code);
					$scope.cloud = false;
					$scope.load = true;

					$scope.tipsImage = "../libs/common/images/loadings.gif";
					$.ajax({
						url: $.getConfigMsg.preUrl + "/aci/window/getQrCodeInfoByElectronicCert.do",
						dataType: 'jsonp',
						jsonp: "jsonpCallback",
						data: {
							codeParam: __code,
							machineId: $.config.get("uniqueId") || "",
							itemName: "",
							itemCode: "",
							businessCode: "",
							using: "",
							lzAddress: "一网通办智能终端"
						},
						success: function(dataJonsp) {
							$scope.loading = false;
							$.log.debug(code);
							//alert(JSON.stringify(dataJonsp))
							if(dataJonsp != null && dataJonsp != undefined && dataJonsp.result.success) {
								$scope.tipsText = "正在加载数据，请稍候...";
								$scope.result({
									info: dataJonsp
								});
							} else {
								$scope.fetchError = true;
								$.log.debug(JSON.stringify(dataJonsp));
								//登录失败
								$scope.tipsText = "登录失败: " + dataJonsp.result.msg;
								return false;
							}
						},
						error: function(err) {
							$scope.fetchError = true;
							//alert(err)
							$scope.tipsText = "登录失败";
						}
					})
				});
			};
			$scope.citizenCloud();

		}
	}
});