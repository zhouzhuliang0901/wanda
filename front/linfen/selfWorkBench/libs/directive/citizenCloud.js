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
			$scope.isAlert = false;
			$scope.concel = "false";
			$scope.alertConfirm = function() {
				$scope.isAlert = false;
				window.history.go(-1);
			}
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
			$scope.getUserInfoByAccessToken = function() {
				$.ajax({
					type: "get",
					url: $.getConfigMsg.preUrl + "/aci/workPlatform/getUserInfoByAccessToken.do",
					dataType: "jsonp",
					jsonp: "jsonpCallback",
					data: {
						accessToken: appData.token
					},
					success: function(dataJson) {
						console.log(dataJson);
						if(dataJson != undefined && dataJson != null && dataJson != "") {
							$scope.tipsText = "正在加载数据，请稍候...";
							$scope.result({
								info: dataJson
							});
							$scope.$apply();
						} else {
							$scope.isAlert = true;
							$scope.msg = "扫码失败,请重试";
						}
					},
					error: function(err) {
						$scope.isAlert = true;
						$scope.msg = "扫码失败,请重试";
					}
				});
			}
			//获取token ------2、比对成功后，根据tokenSNO获取access_token
			$scope.getAccessToken = function(tokenSNO) {
				var rec = $.ajax({
					url: $.getConfigMsg.preUrl + "/aci/workPlatform/getAccessToken.do",
					type: "post",
					dataType: "jsonp",
					jsonp: "jsonpCallback",
					timeout: 5000,
					data: {
						tokenSNO: tokenSNO,
					},
					success: function(res) {
						console.log(res);
						if(res.SUCCESS === true) {
							appData.token = res.accessToken;
							$scope.getUserInfoByAccessToken();
						} else {
							$scope.isAlert = true;
							$scope.msg = "扫码失败,请重试";
						}
					},
					error: function(err) {
						$scope.isAlert = true;
						$scope.msg = "扫码失败,请重试";
					},
				})
			}

			$scope.citizenCloud = function() {
				function ClearBr(key) {
					key = key.replace(/<\/?.+?>/g, "");
					key = key.replace(/[\r\n]/g, "");
					key = key.trim();
					return key;
				}
				$.device.qrCodeOpen(function(code) {
					$.log.debug(code);
					$scope.tipsImage = "../libs/common/images/loadings.gif";
					$scope.loading = true;
					$timeout(function() {
						if(code.indexOf("http") != -1) {
							code = code.replace(/[\r\n]/g, "");
							appData.qrCodeType = "suishenma";
							$scope.cloud = false;
							$scope.load = true;
							$scope.tipsImage = "../libs/common/images/loadings.gif";
							$.ajax({
								url: $.getConfigMsg.preUrlSelf + "/selfapi/loginService/getTokenSNOByQrCode.do",
								dataType: 'jsonp',
								jsonp: "jsonpCallback",
								data: {
									certQrCode: encodeURIComponent(code),
									machineId: $.config.get('uniqueId'),
								},
								success: function(dataJonsp) {
									let data = dataJonsp.data;
									$.log.debug(code);
									if(data != null && data != undefined && data.encrypted == true) {
										$scope.tipsText = "正在加载数据，请稍候...";
										appData.tokenSNO = data.biz_response.tokenSNO;
										$rootScope.tokenSNO = appData.tokenSNO;
										$scope.getAccessToken(data.biz_response.tokenSNO);
									} else {
										$scope.isAlert = true;
										$scope.msg = "扫码失败,请重试";
									}
								},
								error: function(err) {
									$scope.isAlert = true;
									$scope.msg = "扫码失败,请重试";
								}
							})
						} else {
							appData.qrCodeType = "shiminyun";
							var __code = ClearBr(code);
							$scope.cloud = false;
							$scope.load = true;
							$scope.tipsImage = "../libs/common/images/loadings.gif";
							$scope.loading = true;
							$.ajax({
								type: "get",
								url: $.getConfigMsg.preUrlSelf + "/selfapi/getQrCodeInfoByElectronicCert.do",
								dataType: 'jsonp',
								jsonp: "jsonpCallback",
								data: {
									codeParam: __code,
									machineId: $.config.get('uniqueId'),
									lzAddress: encodeURI("一网通办智能终端")
								},
								success: function(dataJonsp) {
									$.log.debug(code);
									//alert(JSON.stringify(dataJonsp))
									if(dataJonsp != null && dataJonsp != undefined && dataJonsp.data.result.success == "true") {
										$scope.tipsText = "正在加载数据，请稍候...";
										$scope.result({
											info: dataJonsp.data
										});
									} else {
										$scope.isAlert = true;
										$scope.msg = "扫码失败,请重试";
									}
								},
								error: function(err) {
									$scope.isAlert = true;
									$scope.msg = "扫码失败,请重试";
								}
							})
						}
					}, 1000);
				});
			};
			$scope.citizenCloud();

		}
	}
});