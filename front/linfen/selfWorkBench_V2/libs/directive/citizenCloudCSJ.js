app.directive("citizenCloud", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/citizenCloud.html",
		scope: {
			result: "&",
		},
		controller: function($scope, $location, $rootScope, appData, $interval, $timeout) {
			$.device.idCardClose();
			$.device.ssCardClose();
			try {
				window.external.Hd_Audio_Stop();

			} catch(e) {}
			$scope.cloud = true;
			$scope.load = false;
			$scope.fetchError = false;
			$scope.loading = false;
			$scope.infoCard = "";
			console.info(acBridgeMac.vendor());
			if(appData.licenseType == "corporate") {
				$scope.tipsText = "扫描法人证照二维码";
				$scope.tipsImage = "../libs/common/images/newTips/qrCode-self.png";
			} else {
				$scope.tipsText = '目前支持上海“随申码”、江苏“苏服码”、浙江“浙江证照码”、安徽“安康码”亮码登录';
				$scope.tipsImage = "../libs/common/images/dicon/" + acBridgeMac.imagePath() + "/qrCode-self.gif";
			}
			if(appData.source == "idcardOrCitizen" && appData.handle == 'self') {
				$scope.tipsImage = "../libs/common/images/dicon/" + acBridgeMac.imagePath() + "/qrCode-self.gif";
			} else if(appData.source == "idcardOrCitizen" && appData.handle == 'agent') {
				$scope.tipsImage = "../libs/common/images/dicon/" + acBridgeMac.imagePath() + "/qrCode-angent.gif";
			} else if(appData.source == "bookMakingChoose" && appData.handle == 'agent') {
				$scope.tipsImage = "../libs/common/images/dicon/" + acBridgeMac.imagePath() + "/qrCode-angent.gif";
			}
			$scope.reLogin = function() {
				window.history.back();
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
					// $.log.debug(code);
					$scope.loading = true;
					$timeout(function() {
						code = code.replace(/[\r\n]/g, "");
						appData.qrCodeType = "suishenma";
						$scope.cloud = false;
						$scope.load = true;
						$scope.tipsImage = "../libs/common/images/loadings.gif";
						$.ajax({
							type: "post",
							url: $.getConfigMsg.preUrlSelf + "/selfapi/changjiangDelta/loginByQrCodeForCSJ.do",
							dataType: 'json',
							data: {
								qrCode: encodeURI(code),
								machineId: $.config.get('uniqueId')
							},
							success: function(dataJonsp) {
								$scope.loading = false;
								$.log.debug(code);
								console.log(dataJonsp)
								if(dataJonsp.head.status == '0') {
									$scope.tipsText = "正在加载数据，请稍候...";
									var dataJsonInfo = {
										"result": {
											"data": {
												"realname": dataJonsp.data.userinfo.userName,
												"idcard": dataJonsp.data.userinfo.idCard
											}
										},
										"zwdtsw_name": dataJonsp.data.userinfo.userName,
										"zwdtsw_cert_id": dataJonsp.data.userinfo.idCard,
										"encrypt_identity":'',
									};
									$scope.result({
										info: dataJsonInfo
									});
								}else if(dataJonsp.head.status == '1'){
									$scope.fetchError = true;
									if(dataJonsp.head.message.indexOf("二维码已失效") != -1){
										$scope.tipsText = "登录失败: 二维码已失效";
									}else if(dataJonsp.head.message.indexOf("二维码已过期") != -1){
										$scope.tipsText = "登录失败: 二维码已过期";
									}else{
										$scope.tipsText = "登录失败: 获取失败，请重试";
									}
								} else {
									$scope.fetchError = true;
									//登录失败
									try {
										$scope.tipsText = "登录失败: 获取失败，请重试";
									} catch(e) {
										//TODO handle the exception
									}
									return false;
								}
							},
							error: function(err) {
								$scope.fetchError = true;
								//alert(err)
								$scope.tipsText = "登录失败";
							}
						})
					}, 1000);
				});
			};
			$scope.citizenCloud();

		}
	}
});