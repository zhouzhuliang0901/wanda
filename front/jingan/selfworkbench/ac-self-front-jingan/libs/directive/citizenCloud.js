app.directive("citizenCloud", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/citizenCloud.html",
		scope: {
			result: "&",
		},
		controller: function($scope, $location, $state, $rootScope, appData, $interval, $timeout) {
			$scope.tipsText = "扫描随申办身份证二维码";
			$scope.cloud = true;
			$scope.load = false;
			$scope.fetchError = false;
			$scope.loading = false;
			$scope.tipsImage = "../libs/common/images/tips/cloud.png";
			if(appData.source == "idcardOrCitizen") {
				$scope.tipsImage = "../libs/common/images/newTips/qrCode-angent.png";
			} else {
//				$scope.tipsImage = "../libs/common/images/newTips/qrCode-self.png";
				$scope.tipsImage = "../libs/common/images/tips/cloud.png";
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
					key = key.replace(/<\/?.+?>/g,""); 
					key = key.replace(/[\r\n]/g, "");
//					key = key.trim();
					return key; 
				}
				$.device.qrCodeOpen(function(code) {
					$scope.loading = true;
					var timerout = $timeout(function() {
						$scope.fetchError = true;
						$scope.tipsText = "登录超时";
						console.log("登录超时");
					}, 10000);
					var __code = ClearBr(code);
					$scope.cloud = false;
					$scope.load = true;
					$scope.tipsImage = "../libs/common/images/loadings.gif";
					$.ajax({
						// /aci/window/getQrCodeInfoByElectronicCert.do
//						url: "http://10.81.16.56:8080/ac-product/aci/window/getInfoByCodeTest.do",
						url: "http://xzfwzx.jingan.gov.cn:8080/ac/aci/window/getQrCodeInfoByElectronicCert.do",
						dataType: 'jsonp',
						jsonp: "jsonpCallback",
						data:{
							codeParam:__code,
							machineId: $.config.get('uniqueId') || "",
							itemName: "",
							itemCode: "",
							businessCode: ""
						},
						success: function(dataJsonp) {
							$.log.debug('61行');
							$.log.debug(JSON.stringify(dataJsonp));
							$timeout.cancel(timerout);
							if(dataJsonp != null && dataJsonp != undefined && dataJsonp.result.success) {
								$scope.loading = false;
								$scope.tipsText = "登录成功";
								$scope.result({
									info: dataJsonp
								});
							}else{
								$.log.debug(JSON.stringify(dataJsonp));
								//登录失败
								$scope.tipsText = "登录失败: " + dataJsonp.result.msg;
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