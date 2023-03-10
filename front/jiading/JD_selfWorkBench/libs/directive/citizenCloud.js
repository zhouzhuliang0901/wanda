app.directive("citizenCloud", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/citizenCloud.html",
		scope: {
			result: "&",
		},
		controller: function($scope, $location, $state, $rootScope, $interval, $timeout, appData) {
			$scope.tipsText = "扫描随申办身份证二维码";
			var machineId = $.config.get('uniqueId');
			$scope.cloud = true;
			$scope.load = false;
			$scope.tipsImage = "../libs/common/images/cloud.png";
			$scope.fetchError = false; // 控制重新登录和完成按钮的显示与隐藏
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

			// 记录随申码扫描
			function SsmUsedCounter(qrCode) {
				$.ajax({
					url: $.getConfigMsg.preUrl + "/aci/autoterminal/getUserInfoBySsmCode.do",
		//				url: "http://10.2.104.111:8080/ac-product/aci/autoterminal/getUserInfoBySsmCode.do",
		//				url: "http://10.2.104.36:8080/ac-product/aci/autoterminal/getUserInfoBySsmCode.do",
					type: "post",
					dataType: "jsonp",
					jsonp: "jsonpCallback",
					data: {
						"qrCode": encodeURIComponent(qrCode),
						"account": "zzgzt",
						"from": "shjdqj9485728102dada8a"
					},
					success: function(res) {
						//alert("成功")
						$.log.debug('随申码记录成功')
					},
					error: function(err) {
						//alert("失败")
						$.log.debug('随申码记录失败')
					},
				})
			}

			// 2-------------通过token获取用户信息
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
//						alert('扫描随申码返回值: ' + JSON.stringify(dataJson));
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
								"encrypt_identity": dataJson.encrypt_identity
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
			// 1------------获取token 、比对成功后，根据tokenSNO获取access_token
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
//						alert('access_token: '+JSON.stringify(res));
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
					code = code.replace(/[\r\n]/g, "");
					$.log.debug('qrCodeOpen执行，二维码结果：' + code)
					$scope.loading = true;
					// 登录超时倒计时
					//					var timerout = $timeout(function() {
					//						$scope.fetchError = true;
					//						$scope.tipsText = "登录超时";
					//						alert("登陆超时")
					//					}, 10000);

					if(code.indexOf("http") != -1) {
						// 随申码扫描记录
						SsmUsedCounter(code)
						appData.qrCodeType = "suishenma";
						$scope.cloud = false;
						$scope.load = true;
						$scope.tipsImage = "../libs/common/images/loadings.gif";
						$.ajax({
							url: $.getConfigMsg.preUrl + "/selfapi/loginService/getTokenSNOByQrCode.do",
//							url: $.getConfigMsg.preUrlSelf + "/selfapi/loginService/getTokenSNOByQrCode.do",
							dataType: 'jsonp',
							jsonp: "jsonpCallback",
							data: {
								certQrCode: encodeURIComponent(code),
							},
							success: function(dataJonsp) {
								$scope.loading = false;
								$.log.debug("随申码：" + code);
								if(dataJonsp != null && dataJonsp != undefined && dataJonsp.encrypted == true) {
									$scope.tipsText = "正在加载数据，请稍候...";
									appData.tokenSNO = dataJonsp.biz_response.tokenSNO;
									$scope.getAccessToken(dataJonsp.biz_response.tokenSNO);
								} else {
									$scope.fetchError = true;
									$.log.debug("随申码错误信息1： " + JSON.stringify(dataJonsp));
									$scope.tipsText = "登录失败: " + dataJonsp.result.msg;
									return false;
								}
							},
							error: function(err) {
								$.log.debug("随申码错误信息2： " + JSON.stringify(err));
								$scope.fetchError = true;
								$scope.tipsText = "登录失败";
							}
						})
					} else {
						appData.qrCodeType = "shiminyun";
						var __code = ClearBr(code);
						$.log.debug('处理后的二维码结果：' + __code)
						$scope.cloud = false;
						$scope.load = true;
						$scope.tipsImage = "../libs/common/images/loadings.gif";
						$.log.debug('开始发送请求')
						$.ajax({
//							url: "http://10.237.16.72/aci/window/getQrCodeInfoByElectronicCert.do",
							url:  $.getConfigMsg.preUrl + "/aci/window/getQrCodeInfoByElectronicCert.do",
							dataType: 'jsonp',
							jsonp: "jsonpCallback",
							data: {
								codeParam: __code,
								machineId: machineId || '',
								itemName: appData.itemName || '',
								itemCode: appData.itemId || '',
								businessCode: appData.applyId || '',
								using: '',
								lzAddress: "一网通办智能终端"
							},
							success: function(dataJonsp) {
//								alert('扫描市民云返回结果: '+JSON.stringify(dataJonsp));
								$.log.debug('登录成功！');
								$scope.loading = false;
								//$timeout.cancel(timerout);
								if(dataJonsp != null && dataJonsp != undefined && dataJonsp.result.success) {
									$scope.tipsText = "登录成功";
									$scope.result({
										info: dataJonsp
									});
								} else {
									$.log.debug('登录失败！');
									$scope.tipsText = "登录失败: " + dataJonsp.result.msg;
									$scope.fetchError = true;
									return false;
								}
							},
							error: function(err) {
								$scope.tipsText = "登录失败";
								$scope.fetchError = true;
								//$timeout.cancel(timerout);
							}
						})
					}
				});
			};
			$scope.citizenCloud();
		}
	}
});