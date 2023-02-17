app.directive("citizenCloud", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/citizenCloud.html",
		scope: {
			result: "&",
		},
		controller: function($scope, $location, $state, $rootScope, appData, $interval, $timeout) {
			$.device.idCardClose();
			$.device.ssCardClose();
			try {
				window.external.Hd_Audio_Stop();

			} catch(e) {}
			$scope.tipsText = "扫描随申办身份证二维码";
			$scope.cloud = true;
			$scope.load = false;
			$scope.fetchError = false;
			$scope.loading = false;
			$scope.infoCard = "";
			$scope.tipsImage = "../libs/common/images/newTips/qrCode-self.png";
			if(appData.source == "idcardOrCitizen" && appData.handle == 'self') {
				$scope.tipsImage = "../libs/common/images/newTips/qrCode-self.png";
			} else if(appData.source == "idcardOrCitizen" && appData.handle == 'agent') {
				$scope.tipsImage = "../libs/common/images/newTips/qrCode-angent.png";
			} else if(appData.source == "bookMakingChoose" && appData.handle == 'agent') {
				$scope.tipsImage = "../libs/common/images/newTips/qrCode-angent.png";
			}
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
								trackEvent("电子证照调用");
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