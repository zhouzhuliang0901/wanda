app.directive("recognition", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/recognition.html",
		scope: {
			result: "&",
			faceImage: "@",
			cameraPos: "="
		},
		controller: function($scope, $location, $state, $rootScope, $interval, $timeout, appData) {
			$scope.isAlert = false;
			$scope.concel = "false";
			$scope.__cameraPos = $scope.cameraPos || {
				width: 640,
				height: 480,
				x: 640,
				y: 240
			}

			//判断是否适老事项
			$scope.getCheckItemForElderly = function(UUserCard, itemName) {
				let itemList = [
					'新版社保卡开通',
					'办理就医记录册的申领、更换、补发',
					'养老金卡（折）调整',
					'听力、言语残疾人信息卡套餐服务申请',
				]
				//获取年龄
				var myDate = new Date();
				var month = myDate.getMonth() + 1;
				var day = myDate.getDate();
				var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1;
				if(UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) {
					age++;
				}
				//
				if(age >= 60 && jQuery.getConfigMsg.isCommunity !== "N" && itemList.indexOf(itemName) !== -1) {
					return true;
				} else {
					return false;
				}

			}

			// 检测屏幕宽高, 调整摄像头宽高和坐标
			if(window.innerWidth <= 1600) {
				$scope.__cameraPos.height = 450;
				$scope.__cameraPos.x = 350;
			}
			if(window.innerWidth == 1366 && acBridgeMac.vendor() == 'wonders') {
				$scope.__cameraPos.y = 180;
			}
			$scope.oldType = function() {
				$scope.operation = "身份证登录";
				$scope.isSuishenma = false;
				$scope.resultOld = "";
				$scope.showImageOld = '../libs/common/images/failed-old.png'
				$scope.leftText = "重新核验";
				$scope.rightText = "人工核验";
				$scope.tipsOne = "人证不符！";
				$scope.tipsTwo = "请联系工作人员协助处理";
			}
			$scope.capture = function() { //拍照
				//测试正常流程
				//				$scope.recognitionOver = true;
				//				$scope.showImage = "../libs/common/images/failed.png";
				//				$scope.tipsText = "人证不符";
				//测试适老化流程
				//								$scope.oldLoginType = true;
				//								$scope.oldType();

				$.device.Face_Show($scope.__cameraPos.width, $scope.__cameraPos.height, $scope.__cameraPos.x, $scope.__cameraPos.y, function(info) {
					if(info) {
						$scope.showImage = "../libs/common/images/recognition.png";
						$scope.tipsText = "正在核验...";
						$scope.istakePhoto = false;
						$scope.capturePhoto = info;
						$.device.Face_Close();
						$scope.getTokenSNO($scope.faceImage, $scope.capturePhoto);
					} else if(info.Success === false) {
						$scope.tipsText = "活体检测失败";
						$.device.Face_Close();
						$scope.capture();
					}
				})
			};
			$scope.capture();
			$scope.tipsText = "请看摄像头";
			$scope.recognitionOver = false;
			$scope.istakePhoto = true;

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
							appData.zwdtsw_user_id = dataJson.zwdtsw_cert_id;
							$scope.adminId = dataJson.zwdtsw_cert_id;
							$scope.checkItemForElderly();
						}
					},
					error: function(err) {
						$scope.oldFailed()
					}
				});
			}

			//获取token ------2、比对成功后，根据tokenSNO获取access_token
			$scope.getAccessToken = function(tokenSNO) {
				var rec = $.ajax({
					url: $.getConfigMsg.preUrl + "/aci/workPlatform/getAccessToken.do",
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
						} else {
							rec.abort();
						}
					},
					error: function(err) {
						console.log(err);
					},
					complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
						if(status === 'timeout' || status === 'error') { //超时,status还有success,error等值的情况
							rec.abort();
						}
					}
				})
			}
			//获取token ------1、两照对比获取tokenSNO
			$scope.getTokenSNO = function(face, photograph) {
				var idCardPhoto = face;
				if(appData.licenseType == "rs") {
					var capturePhoto = photograph;
				} else {
					var capturePhoto = photograph;
				}
				$scope.status = true;
				$scope.confirm = false;
				$scope.showImage = "../libs/common/images/recognition.png";
				$.ajax({
					url: $.getConfigMsg.preUrlSelf + "/selfapi/changjiangDelta/loginByIdCardForCSJ.do",
					type: "post",
					dataType: "json",
					data: {
						otherImg: capturePhoto,
						personImg: idCardPhoto,
					},
					success: function(res) {
						$.device.Face_Close();
						console.log(res);
						$scope.recognitionOver = true;
						try {
							if(res.result.authResult === true) {
								//核验通过
								$scope.showImage = "../libs/common/images/success.png";
								$scope.tipsText = "核验通过";
							} else if(res.result.authResult === false) {
								$scope.isAlert = true;
								$scope.msg = res.result.msg;
								$scope.alertConfirm = function() {
									$.device.GoHome();
								}
							} else {
								//人证不符
								$scope.showImage = "../libs/common/images/failed.png";
								$scope.tipsText = "人证不符";
							}
						} catch(e) {
							//人证不符
							$scope.showImage = "../libs/common/images/failed.png";
							$scope.tipsText = "人证不符";
						}

					},
					error: function(err) {
						$scope.showImage = "../libs/common/images/failed.png";
						$scope.tipsText = "核验错误";
						console.log(err);
					}
				})
			}
			$scope.reRecognition = function() {
				$.device.Face_Close();
				$scope.tipsText = "请看摄像头";
				$scope.showImage = "../libs/common/images/read1.png";
				$scope.oldLoginType = false;
				$scope.recognitionOver = false;
				$scope.istakePhoto = true;
				$scope.capture();
			};
			$scope.success = function() {
				$.device.Camera_Hide();
				if($scope.tipsText === "核验通过") {
					$scope.result({
						img: $scope.capturePhoto
					});
				} else {
					$rootScope.goAppHistoryBack()
				}

			}
			try{
				if(acBridgeMac.vendor() != 'ghdevice'){
					$scope.$on("$destroy", function() {
				       $.device.Face_Close();
				       $.device.Camera_Hide();
				       console.log("已离开当前页面");
			        });
				}
			}catch(e){}

			//适老化事项流程

			//工作人员扫随申码
			$scope.citizenCloud = function() {
				function ClearBr(key) {
					key = key.replace(/<\/?.+?>/g, "");
					key = key.replace(/[\r\n]/g, "");
					key = key.trim();
					return key;
				}
				$.device.qrCodeOpen(function(code) {
					$scope.isShowLoadingAlert = true;
					$timeout(function() {
						if(code.indexOf("http") != -1) {
							code = code.replace(/[\r\n]/g, "");
							$.ajax({
								url: $.getConfigMsg.preUrlSelf + "/selfapi/loginService/getTokenSNOByQrCode.do",
								dataType: 'jsonp',
								jsonp: "jsonpCallback",
								data: {
									certQrCode: encodeURIComponent(code),
									machineId: $.config.get('uniqueId')
								},
								success: function(dataJonsp) {
									$scope.loading = false;
									$.log.debug(code);
									dataJonsp = dataJonsp.data;
									if(dataJonsp != null && dataJonsp != undefined && dataJonsp.encrypted == true) {
										$scope.tipsText = "正在加载数据，请稍候...";
										$scope.getAccessToken(dataJonsp.biz_response.tokenSNO);
										$timeout(function() {
											$scope.getUserInfoByAccessToken();
										}, 2000);
									} else {
										$scope.loginFailedOld()
									}
								},
								error: function(err) {
									$scope.loginFailedOld()
								}
							})
						} else {
							appData.qrCodeType = "shiminyun";
							var __code = ClearBr(code);
							$.ajax({
								url: $.getConfigMsg.preUrlSelf + "/selfapi/getQrCodeInfoByElectronicCert.do",
								dataType: 'jsonp',
								jsonp: "jsonpCallback",
								data: {
									codeParam: __code,
									machineId: jQuery.getConfigMsg.uniqueId || "",
									lzAddress: encodeURI("一网通办智能终端")
								},
								success: function(dataJonsp) {
									if(dataJonsp != null && dataJonsp != undefined && dataJonsp.data.result.success == "true") {
										$scope.adminId = dataJonsp.data.result.data.idcard;
										$scope.checkItemForElderly()
									} else {
										$scope.loginFailedOld()
									}
								},
								error: function(err) {
									$scope.loginFailedOld()
								}
							})
						}
					}, 1000);
				});
			};

			//验证是否有工作人员权限
			$scope.checkItemForElderly = function() {
				$.ajax({
					url: $.getConfigMsg.preUrlSelf + "/infopub/infopubManager/checkItemForElderly.do",
					type: "post",
					dataType: "json",
					data: {
						managerIdCard: $scope.adminId,
						machineMac: jQuery.getConfigMsg.uniqueId || "",
						itemName: encodeURI($(".headName").text() || ""),
					},
					success: function(res) {
						if(res.success == true) {
							appData.managerID = res.managerID;
							$scope.oldSuccess()
						} else {
							$scope.oldFailed()
						}
					},
					error: function(err) {
						$scope.oldFailed()
					}
				})
			}
			//人工核验
			$scope.manualVerification = function() {
				$scope.isSuishenma = true;
				$scope.operation = "工作人员随申办登录";
				$scope.tipsTextOld = "扫描随申办身份证二维码或随申码";
				$scope.tipsImageOld = "../libs/common/images/dicon/" + acBridgeMac.imagePath() + "/qrCode-self.gif";
				$scope.citizenCloud();
			}

			//成功
			$scope.oldSuccess = function() {
				$scope.isShowLoadingAlert = false;
				$scope.operation = "人工核验";
				$scope.isSuishenma = false;
				$scope.resultOld = "success";
				$scope.showImageOld = '../libs/common/images/success-old.png'
				$scope.leftText = "通过";
				$scope.rightText = "取消";
				$scope.tipsOne = "登录成功！";
				$scope.tipsTwo = "请确认核验结果";
				$scope.pass = function() {
					$scope.result({
						img: ''
					});
				}
				$scope.cancel = function() {
					console.log(2);
					$state.go('loginType')
				}
			}

			//失败无权限
			$scope.oldFailed = function() {
				$scope.isShowLoadingAlert = false;
				$scope.operation = "人工核验";
				$scope.isSuishenma = false;
				$scope.resultOld = "failed";
				$scope.showImageOld = '../libs/common/images/failed-old.png'
				$scope.leftText = "重新登录";
				$scope.tipsOne = "";
				$scope.tipsTwo = "您无操作权限，请联系指定工作人员协助办理";
			}

			//登录失败
			$scope.loginFailedOld = function() {
				$scope.isShowLoadingAlert = false;
				$scope.operation = "人工核验";
				$scope.isSuishenma = false;
				$scope.resultOld = "failed";
				$scope.showImageOld = '../libs/common/images/failed-old.png'
				$scope.leftText = "重新登录";
				$scope.tipsOne = "登录失败！";
				$scope.tipsTwo = "请稍候再试";
			}
			$scope.prev = function() {
				$scope.oldType();
			}
		}
	}
});