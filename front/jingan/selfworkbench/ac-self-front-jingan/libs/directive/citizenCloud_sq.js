app.directive("citizenCloudSq", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/citizenCloud.html",
		scope: {
			result: "&",
		},
		controller: function($scope, $location, $rootScope, appData, $interval, $timeout) {
			$.device.idCardClose();
			$.device.ssCardClose();
			$.getConfigMsg.preUrl = "http://xzfwzx.jingan.gov.cn:8080/ac-self-sq"
			try {
				window.external.Hd_Audio_Stop();

			} catch(e) {}
			$scope.cloud = true;
			$scope.load = false;
			$scope.fetchError = false;
			$scope.loading = false;
			$scope.infoCard = "";
			$scope.tipsText = "扫描随申码或身份证亮证二维码";
			$scope.tipsImage = "../libs/common/images/wonders/qrCode-self.gif";
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
			$scope.getUserInfoByAccessToken = function() {
				$.ajax({
					type: "get",
					url: $.getConfigMsg.preUrl + "/selfapi/workPlatform/getUserInfoByAccessToken.do",
					dataType: "json",
					data: {
						accessToken: appData.token
					},
					success: function(dataJson) {
						console.log(dataJson);
						
						if(dataJson != undefined && dataJson != null && dataJson != "") {
							$scope.tipsText = "正在加载数据，请稍候...";
							var dataJsonInfo = {
								"zwdtsw_name": dataJson.zwdtsw_name,
								"zwdtsw_cert_id": dataJson.zwdtsw_cert_id,
								"encrypt_identity":dataJson.encrypt_identity,
								"zwdtsw_link_phone":dataJson.zwdtsw_link_phone,
								"zwdtsw_user_id":dataJson.zwdtsw_user_id,
							};
							$.log.debug(dataJsonInfo);
							$scope.result({
								info: dataJsonInfo
							});
							$scope.$apply();
						} else {
							$scope.fetchError = true;
							$scope.tipsText = "登录失败！";
							return false;
						}
					},
					error: function(err) {
						$scope.fetchError = true;
						$scope.tipsText = "登录失败！";
					}
				});
			}
			//获取token ------2、比对成功后，根据tokenSNO获取access_token
			$scope.getAccessToken = function(tokenSNO) {
				var rec = $.ajax({
					url: $.getConfigMsg.preUrl + "/selfapi/workPlatform/getAccessToken.do",
					type: "post",
					dataType: "json",
//					jsonp: "jsonpCallback",
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
							$scope.fetchError = true;
							$scope.tipsText = "登录失败！";
							return false;
						}
					},
					error: function(err) {
						$scope.fetchError = true;
						$scope.tipsText = "登录失败！";
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
					// $.log.debug(code);
					$scope.loading = true;
					$timeout(function() {
						// if(code.indexOf("http") != -1 && appData.licenseType != "corporate") {
							code = code.replace(/[\r\n]/g, "");
							appData.qrCodeType = "suishenma";
							$scope.cloud = false;
							$scope.load = true;
							$scope.tipsImage = "../libs/common/images/loadings.gif";
							$.ajax({
								type:"post",
								url: $.getConfigMsg.preUrl + "/selfapi/loginService/getTokenSNOByQrCode.do",
								dataType: 'json',
//								jsonp: "jsonpCallback",
								data: {
									certQrCode: code,
									machineId: $.config.get('uniqueId')
								},
								success: function(dataJonsp) {
									$scope.loading = false;
									$.log.debug(code);
									if(dataJonsp != null && dataJonsp != undefined && dataJonsp.encrypted == true) {
										$scope.tipsText = "正在加载数据，请稍候...";
										appData.tokenSNO = dataJonsp.biz_response.tokenSNO;
										$rootScope.tokenSNO = appData.tokenSNO;
										$scope.getAccessToken(dataJonsp.biz_response.tokenSNO);
									} else {
										$scope.fetchError = true;
										$.log.debug(JSON.stringify(dataJonsp));
										//登录失败
										try{
											$scope.tipsText = "登录失败: " + dataJonsp.result.msg||"请联系工作人员";
										}catch(e){
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
// 						} else {
// 							appData.qrCodeType = "shiminyun";
// 							var __code = ClearBr(code);
// 							$scope.cloud = false;
// 							$scope.load = true;
// 							$scope.tipsImage = "../libs/common/images/loadings.gif";
// 							$.ajax({
// 								url: $.getConfigMsg.preUrlSelf + "/selfapi/getQrCodeInfoByElectronicCert.do",
// 								dataType: 'json',
// //								jsonp: "jsonpCallback",
// 								data: {
// 									codeParam: __code,
// 									machineId: jQuery.getConfigMsg.uniqueId||"",
// 									lzAddress: encodeURI("一网通办智能终端")
// 								},
// 								success: function(dataJonsp) {
// 									$scope.loading = false;
// 									$.log.debug(code);
// 									//alert(JSON.stringify(dataJonsp))
// 									if(dataJonsp != null && dataJonsp != undefined && dataJonsp.data.result.success == "true") {
// 										$scope.tipsText = "正在加载数据，请稍候...";
// 										$scope.result({
// 											info: dataJonsp.data
// 										});
// 									} else {
// 										$scope.fetchError = true;
// 										$.log.debug(JSON.stringify(dataJonsp));
// 										//登录失败
// 										$scope.tipsText = "登录失败: " + dataJonsp.result.msg;
// 										return false;
// 									}
// 								},
// 								error: function(err) {
// 									$scope.fetchError = true;
// 									//alert(err)
// 									$scope.tipsText = "登录失败";
// 								}
// 							})
// 						}
					}, 1000);
				});
			};
			$scope.citizenCloud();

		}
	}
});