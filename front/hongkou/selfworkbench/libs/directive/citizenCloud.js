app.directive("citizenCloud", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/citizenCloud.html",
		scope: {
			result: "&",
		},
		controller: function($scope, $location, $state,appData, $rootScope, $interval, $timeout) {
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
			$scope.getUserInfoByAccessToken = function() {
				$.ajax({
					type: "get",
					url: "http://183.194.250.112/ac-product/aci/workPlatform/getUserInfoByAccessToken.do",
					dataType: "jsonp",
					jsonp: "jsonpCallback",
					data: {
						accessToken: appData.token
					},
					success: function(dataJson) {
						console.log(dataJson);
						if(dataJson != undefined && dataJson != null && dataJson != "") {
							$scope.tipsText = "正在加载数据，请稍候...";
							var dataJsonInfo = {
								"result": {
									"data": {
										"realname": dataJson.zwdtsw_name,
										"idcard": dataJson.zwdtsw_cert_id
									}
								},
								"zwdtsw_name": dataJson.zwdtsw_name,
								"zwdtsw_cert_id": dataJson.zwdtsw_cert_id,
								"encrypt_identity":dataJson.encrypt_identity,
								"zwdtsw_link_phone":dataJson.zwdtsw_link_phone,
								"zwdtsw_user_id":dataJson.zwdtsw_user_id,
							};
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
					url:"http://183.194.250.112/ac-product/aci/workPlatform/getAccessToken.do",
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
					$.log.debug(code);
					$scope.loading = true;
					$timeout(function() {
						if(code.indexOf("http") != -1 && appData.licenseType != "corporate") {
							code = code.replace(/[\r\n]/g, "");
							appData.qrCodeType = "suishenma";
							$scope.cloud = false;
							$scope.load = true;
							$scope.tipsImage = "../libs/common/images/loadings.gif";
							$.ajax({
								url: "http://183.194.250.112/ac-self/selfapi/loginService/getTokenSNOByQrCode.do",
								dataType: 'jsonp',
								jsonp: "jsonpCallback",
								data: {
									certQrCode: code,
									machineId: $.config.get('uniqueId')||""
								},
								success: function(dataJonsp) {
									$scope.loading = false;
									$.log.debug(code);
									dataJonsp=dataJonsp.data;
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
						} else {
							appData.qrCodeType = "shiminyun";
							var __code = ClearBr(code);
							$scope.cloud = false;
							$scope.load = true;
							$scope.tipsImage = "../libs/common/images/loadings.gif";
							$.ajax({
								url: "http://183.194.250.112/ac-self/selfapi/getQrCodeInfoByElectronicCert.do",
								dataType: 'jsonp',
								jsonp: "jsonpCallback",
								data: {
									codeParam: __code,
									machineId: $.config.get('uniqueId')||"",
									lzAddress: encodeURI("一网通办智能终端")
								},
								success: function(dataJonsp) {
									$scope.loading = false;
									$.log.debug(code);
									//alert(JSON.stringify(dataJonsp))
									if(dataJonsp != null && dataJonsp != undefined && dataJonsp.data.result.success == "true") {
										$scope.tipsText = "正在加载数据，请稍候...";
										$scope.result({
											info: dataJonsp.data
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
						}
					}, 1000);
				});
			};
			$scope.citizenCloud();

		}
	}
});