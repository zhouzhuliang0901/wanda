app.controller("mainController", function($rootScope, $scope, $http, appData, $location, appConfig, $state, $rootScope) {
	appData.isStaff = false; //
	appData.loginGuard = false; //路由守卫
	$.device.qrCodeClose(); //
	$.log.debug(appConfig.sark);
	Array.prototype.indexOf = function(val) {
		for(var i = 0; i < this.length; i++) {
			if(this[i] == val) {
				return i;
			}
		}
		return -1;
	};
	Array.prototype.removeItem = function(val) {
		var index = this.indexOf(val);
		if(index > -1) {
			this.splice(index, 1);
		}
	};

	$scope.choiceMatter = function(matter) { //选择材料事项
		if(appData.isStaff === true) {
			if(matter === "license" || matter === "material") {
				appData.matterType = matter;
				$location.path("/staffOperationChoice");
			} else {
				console.log("matters type is error!");
			}
		} else {
			if(matter === "license" || matter === "material") {
				appData.matterType = matter;
				$location.path("/licenseTake").search({});
			} else {
				console.log("matters type is error!");
			}
		}
	};
	$scope.changeModel = function(model) {
		if(model === "staff") {
			appData.isStaff = true;
			$location.path("/staffOperation").search({});
		} else if(model === "masses") {
			appData.isStaff = false;
		}
	};
});
/**
 * 办事人员取证
 */
app.controller("licenseTakeController", function($scope, $rootScope, $http, appConfig, appData, $timeout, $location, $q, $state, $interval) {
	$scope.loginType = "idCard";
	$scope.btnInfo = "市民云亮证";
	$scope.title = "自助取证";
	$scope.isReadOver = false;
	$scope.logging = true;
	$scope.loginErrorType = "正在登陆...";
	$scope.codeString = null;

	//	appConfig.getResideQuantity()

	if(appData.matterType === "material") {
		$scope.title = "材料放置";
	}

	$scope.loginTypeTab = function(type) {
		if(type === "idCard") {
			$scope.btnInfo = "身份证登录";
			$scope.loginType = "citizenCloud";
			$scope.scanQrCode();
			$.device.idCardClose();
			return;
		}
		$scope.readIdCard();
		$.device.qrCodeClose();
		$scope.btnInfo = "市民云亮证";
		$scope.loginType = "idCard";
	};

	// 1 获取token 、比对成功后，根据tokenSNO获取access_token
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
				console.log('access-token: ', res)
				if(res.SUCCESS === true) {
					appData.token = res.accessToken;
					$scope.getUserInfoByAccessToken();
				} else {
					$scope.loginErrorType = "登录失败，请重新登录";
					return false;
				}
			},
			error: function(err) {
				$scope.loginErrorType = "登录失败，请重新登录";
				return false;
			},
		})
	}

	// 2 通过token获取用户信息
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
				console.log('通过token获取的用户信息: ', dataJson)
				if(dataJson != undefined && dataJson != null && dataJson != "") {
					appData.userName = dataJson.zwdtsw_name;
					appData.idCardNumber = dataJson.zwdtsw_cert_id;
					if(appData.isStaff === true) {
						$location.path("/staffOperationChoice");
					} else {
						if(appData.matterType === "material") {
							//放入材料staffOperation
							$location.path("/inputPhone").search({
								address: $location.path()
							});
						} else {
							//取出证照
							$location.path("/licenseInputCode");
						}
					}
					$scope.$apply();
				} else {
					$scope.loginErrorType = "登录失败，请重新登录";
					return
				}
			},
			error: function(err) {
				$scope.loginErrorType = "登录失败，请重新登录";
				return
			}
		});
	}

	$scope.loginRequest = function(code, isRe) {
		var _code = code.trim().replace(/\u0000/g, '');
		if(code.indexOf("http") != -1) {
			// 扫描的是随申码
			$.ajax({
				type:'post',
				url: $.getConfigMsg.preUrl + "selfapi/loginService/getTokenSNOByQrCode.do",
				dataType: 'json',
				//jsonp: "jsonpCallback",
				data: {
					certQrCode: code,
				},
				success: function(dataJson) {
					console.log('通过二维码获取的tokenSNO: ', dataJson)
					if(dataJson.data != null && dataJson.data != undefined && dataJson.data.encrypted == true) {
						appData.tokenSNO = dataJson.data.biz_response.tokenSNO;
						$scope.getAccessToken(dataJson.data.biz_response.tokenSNO);
					} else {
						$scope.loginErrorType = "登录失败，请重新登录";
						return
					}
				},
				error: function(err) {
					$scope.loginErrorType = "登录失败，请重新登录";
					return
				}
			})
		} else {
			// 扫描的是身份证亮证
			$scope.httpConfig = {
				jsonpCallback: "JSON_CALLBACK",
				//using:"证照柜亮证",
				codeParam: _code,
				machineId: $.config.get("uniqueId") || "00-E2-69-1F-8D-3D"
			}
			$http.jsonp($.getConfigMsg.preUrl + "selfapi/getQrCodeInfoByElectronicCert.do", {
				params: $scope.httpConfig,
				timeout: 10000
			}).success(function(data) {
				console.log('身份证亮证返回值: ', data)
				$.log.debug(JSON.stringify(data));
				if(data.data.result.success === false) {
					$scope.loginErrorType = data.result.msg;
					$scope.logging = false;
					return false;
				}

				//				 在localstorage存储记录(没什么用)
				//				loginLog.setLoginRecord({
				//					t: timeLog.timeEnd(),
				//					name: "办事人员"
				//				})

				$.log.debug(loginLog.getLoginRecord());
				appData.userName = data.data.result.data.realname;
				appData.idCardNumber = data.data.result.data.idcard;
				appData.VALIDENDDAY = data.data.result.data.VALIDENDDAY
				appData.VALIDSTARTDAY = data.data.result.data.VALIDSTARTDAY

				//			if(appData.matterType === "material") {
				//				$location.path("/scanCode");
				//			} else {
				//				$location.path("/licenseInputCode");
				//			}
				// 徐汇的需要扫描条形码, 其他地方的不需要, 所以不用跳转scanCode
				if(appData.isStaff === true) {
					$location.path("/staffOperationChoice");
				} else {
					if(appData.matterType === "material") {
						//放入材料staffOperation
						$location.path("/inputPhone").search({
							address: $location.path()
						});
					} else {
						//取出证照
						$location.path("/licenseInputCode");
					}
				}
				$scope.$apply();
			}).error(function(err, state) {
				$scope.logging = false;
				if(state == -1) {
					$scope.loginErrorType = "登录超时，请重新登录";
					return;
				}
				$scope.loginErrorType = "登录失败，请重新登录";
			});
		}
	}
	$scope.scanQrCode = function() {
		$.device.qrCodeOpen(function(code) {
			code = code.replace(/[\r\n]/g, "");
			if(code == "" || code === null) {
				$scope.scanQrCode();
				return;
			}
			$scope.codeString = code;
			$scope.isReadOver = true;
			$scope.loginRequest(code);
			$.device.qrCodeClose();
			$scope.$apply();
		});
	};
	$scope.readIdCard = function() {
		$.device.idCardOpen(function(value) {
			let list = JSON.parse(value);

			//					let list = {
			//						Name: "李华熙",
			//						Number: "520222199406140030"
			//					}
			if(list != null) {
				console.log(list)
				$scope.isReadOver = true;
				appData.userName = list.Name;
				appData.idCardNumber = list.Number;
				//				appData.idCardImg = list.CardImagePath;
				//				$scope.getImgbaseStr(
				//					appData.idCardNumber,
				//					appData.userName,
				//					appData.idCardImg
				//				);
				$timeout(function() {
					if(appData.isStaff === true) {
						$location.path("/staffOperationChoice");
					} else {
						if(appData.matterType === "material") {
							//放入材料staffOperation
							$location.path("/inputPhone").search({
								address: $location.path()
							});
						} else {
							//取出证照
							$location.path("/licenseInputCode");
						}
					}
				}, 2000);
			}

			$.device.idCardClose();
		});
	};
	//	//获取上传的身份证base64
	//	$scope.getImgbaseStr = function(idCardNo, name, imgUrl) {
	//		appData.ImageUrlStr = $.device.fileBase64(imgUrl);
	//	};

	$scope.readIdCard();

	/*
	 * 测试扫码
	 * 传入二维码解析出来的地址
	 * */
	//		$scope.loginRequest('https://s.sh.gov.cn/67b671cb4731a3ac24127b99b08a061611650106017')

});
/**
 * 个人信息
 */
app.controller("personInfoController", function(
	$scope,
	$http,
	$location,
	appData,
	$timeout,
	$interval
) {
	$.device.Camera_Hide();
	$scope.$on("$viewContentLoaded", function(event, toState, toParams, fromState, fromParams) {
		$scope.isShow = undefined;
		$scope.isLoding = true;
		$scope.name1 = appData.userName;
		$scope.name = '*' + $scope.name1.substring(1, $scope.name1.length);
		$scope.idcard = appData.idCardNumber;
		$scope.mobile = appData.mobile;
	});
	$scope.Continue = function() {
		appData.userName = $scope.name1;
		appData.idCardNumber = $scope.idcard;
		if(appData.matterType === "material") {
			$location.path("/scanCode");
		} else {
			$location.path("/licenseInputCode");
		}
	}
	$scope.reScan = function() {
		$location.path("/licenseTake");
	};
})
/**
 * 人证核验
 */
app.controller("faceVerificationController", function(
	$scope,
	$http,
	$location,
	appData,
	$timeout,
	$interval
) {
	$scope.$on("$locationChangeStart", function() {
		$interval.cancel($scope.countDown);
	});
	$scope.isCheckAgain = $location.search().reVerfication;
	$.device.Camera_Init(650, 480, 215, 715); //初始化摄像头
	$.device.Camera_Link("LHT-820VM31B", 6); //初始化摄像头
	$.device.Camera_Show();
	$scope.startBtn = false;
	$scope.toCount = 3;
	$scope.countDown = undefined;
	if($scope.isCheckAgain == '1') { //如果是重新核验开启手动拍照自动拍照时间设为5秒
		$scope.toCount = 5;
		$timeout(function() {
			$scope.startBtn = true;
		}, 100);
	};
	$timeout(function() { //延时两秒
		$scope.countDown = $interval(function() {
			--$scope.toCount;
			if($scope.toCount < 1) {
				$interval.cancel($scope.countDown);
				$scope.capture();
			}
		}, 1200);
	}, 2000);

	$scope.capture = function() { //拍照
		$scope.capturePhoto = $.device.Camera_Base64();
		appData.capturePhoto = $scope.capturePhoto;
		$.device.Camera_Hide();
		$location.path("/verificationAwait").search({});
	};
});
app.controller("verificationAwaitController", function(
	$scope,
	$http,
	$location,
	appData,
	appConfig
) {
	$scope.recognitionAjax = null;
	$scope.$on("$locationChangeStart", function() {
		try {
			$scope.recognitionAjax.abort();
		} catch(e) {
			console.log("不存在该请求！");
		}
	});
	$scope.recognition = function() {
		//$.log.info(appData.ImageUrlStr);
		//$.log.info("----------------");
		//$.log.info(appData.capturePhoto);
		//人证数据对比
		$scope.recognitionAjax = $.ajax({
			type: "post",
			url: "http://180.169.7.194:8080/ac-product-ext/ext/aci/autoterminal/facecompare.do",
			async: true,
			data: {
				idCardPhoto: appData.ImageUrlStr,
				capturePhoto: appData.capturePhoto
			},
			success: function(data) {
				$.log.debug(data);
				var dataJson = JSON.parse(data);
				var verificationStatus = null; //0 失败  1 成功
				//if(dataJson.pair_verify_similarity > 60) 
				if(dataJson.similarity > 60) {
					//核验通过
					verificationStatus = "1";
				} else {
					//人证不符
					verificationStatus = "0";
				}

				$location.path("/verificationComplete").search({
					//将结果传给verificationComplete
					verificationStatus: verificationStatus
				});

				$scope.$apply();
			},
			error: function(err) {
				$.log.debug("err:" + JSON.stringify(err));
			}
		});
	};
	$scope.recognition();
});
app.controller("verificationCompleteController", function(
	$scope,
	$http,
	$location,
	appData,
	$timeout
) {
	$scope.verificationStatus =
		$location.search().verificationStatus == "1" ? true : false; //判断人证核验成功与否
	if($scope.verificationStatus) {
		$timeout(function() {
			$scope.Continue();
		}, 1000);
	};
	$scope.Continue = function() {
		if(appData.isStaff === true) {
			$location.path("/staffOperationChoice");
		} else {
			if(appData.matterType === "material") {
				//放入材料staffOperation
				$location.path("/scanCode").search({
					address: $location.path()
				});
			} else {
				//取出证照
				$location.path("/licenseInputCode");
			}
		}
	};
	$scope.reVerfication = function() {
		$location.path("/faceVerification").search({
			reVerfication: "1"
		});
	};
	$scope.Home = function() {
		$location.path("/main");
	};
});
/**
 * 输入取证码
 */
app.controller("licenseInputCodeController", function($scope, $http, appData, $location, appConfig) {
	$scope.numCode = "";
	$scope.getNumber = 10;
	$scope.inputCode = function(val) {
		$scope.numCode.length < 6 ? ($scope.numCode += val) : "";
		console.log($scope.numCode.length);
	};
	$scope.deleteCode = function() {
		$scope.numCode = $scope.numCode.slice(0, $scope.numCode.length - 1);
	};

	$scope.confirmCode = function() {
		if(appData.BBID_AC == 1) {
			//代办人员，从新赋值
			appData.idCardNumber = appData.BBidCardNumber;
		}
		if($scope.numCode.length < 6) {
			alert("请输入六位取证码!");
			return;
		}
		$.ajax({
			type: "POST",
			url: appConfig.httpUrl + "take.do",
			//			dataType: "jsonp",
			//			jsonp: "jsonpCallback",
			data: {
				stMachineId: $.config.get('uniqueId') || "00-E2-69-1F-8D-3D",
				nmCertType: '0',
				stReceiveNum: $scope.numCode,
				stCabinetNo: ''
			},
			success: function(res) {
				if(JSON.parse(res).success == true) {
					$location.path("/openBox").search({
						number: JSON.parse(res).stCabinetNo,
						tips: "取出证照",
						address: $location.path()
					});
					$scope.$apply()
				} else {
					alert("对不起，该取证码没有可取证照");
				}
			},
			error: function(err) {
				console.log(err);
				if($scope.getNumber > 1) {
					$scope.getNumber--;
					$scope.confirmCode();
				}
			}
		})
	};
});
/**
 * 签名
 */
app.controller("signatureController", function(
	$scope,
	$http,
	appData,
	appConfig,
	$location
) {
	$scope.signature = null;
	$scope.signatureFlag = false;
	$scope.SignatureBoardPlug = new SignatureBoardPlug({
		canvas: "#signature",
		clearBtn: ".clearRect",
		getSigntrue: ".saveImg",
		color: "black"
	});
	$scope.isSignature = function() {
		$scope.signatureFlag = true;
	};
	$scope.notSignature = function() {
		$scope.signatureFlag = false;
	}
	$scope.saveSignature = function() {
		$scope.signature = $scope.SignatureBoardPlug.Signatrue;
		if($scope.signatureFlag === false) {
			alert("请先在屏幕上签名!");
			return;
		}
		$.ajax({
			type: "post",
			data: {
				stMachineId: appConfig.stMachineId,
				stCertFlowId: appData.massesGetLicense.stCertFlowId,
				picStr: $scope.signature
			},
			url: appConfig.httpUrl + "addSignPic.do",
			dataType: "json",
			success: function(dataJsonp) {
				console.log(dataJsonp);
				appConfig.http("getCert.do", {
					isOpen: "true",
					stCertFlowId: appData.massesGetLicense.stCertFlowId,
					isStuff: "0",
					stuffName: encodeURI(appData.userName)
				});
				var routerName = $location.path();
				$location.path("/openBox").search({
					number: appData.massesGetLicense.stCabinetNo,
					tips: "取出证照",
					address: $location.path()
				});
			},
			error: function(msg) {
				alert("签名上传失败！");
				console.log("error" + msg);
			}
		});
	};
});
app.controller("getLicenseConfirmController", function(
	$scope,
	$http,
	appData,
	appConfig,
	$location
) {
	//确认证照信息
	$scope.certInfo = appData.massesGetLicense;
	$scope.Continue = function() {
		$.log.debug(JSON.stringify($scope.certInfo));
		$location.path("/signature");
	};
});
/**
 * 工作人员登录操作
 */
app.controller("staffOperationController", function($scope, $http, appData, $location, appConfig, $state, $timeout) {
	console.log('是否为工作人员: ', appData.isStaff);
	$scope.pageClass = "fade";
	$scope.title = "工作人员登录";
	$scope.loginType = "idCard";
	$scope.btnInfo = "市民云登录";
	$scope.authentication = '请刷身份证'

	$scope.isReadOver = false; //读取个人数据
	$scope.logging = true; //登录中
	$scope.loginErrorType = "正在登陆..."; //错误提示
	$scope.codeString = null; //暂存扫描出来的二维码

	// 1 获取token 、比对成功后，根据tokenSNO获取access_token
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
				console.log('access-token: ', res)
				if(res.SUCCESS === true) {
					appData.token = res.accessToken;
					$scope.getUserInfoByAccessToken();
				} else {
					$scope.loginErrorType = "登录失败，请重新登录";
					return false;
				}
			},
			error: function(err) {
				$scope.loginErrorType = "登录失败，请重新登录";
				return false;
			},
		})
	}

	// 2 通过token获取用户信息
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
				console.log('通过token获取的用户信息: ', dataJson)
				if(dataJson != undefined && dataJson != null && dataJson != "") {
					appData.userName = dataJson.zwdtsw_name;
					appData.idCardNumber = dataJson.zwdtsw_cert_id;

					let Code = appData.idCardNumber;
					let result = {};
					//判断是否存在该工作人员
					for(var i = 0; i < appConfig.personnel.length; i++) {
						if(appConfig.personnel[i].Code == Code) {
							result = appConfig.personnel[i];
						}
					}
					if(result.Code) {
						console.log('员工信息: ', result)
						appData.loginGuard = true;
						if(appData.isStaff === true) {
							$location.path("/staffOperationChoice");
						} else {
							if(appData.matterType === "material") {
								//放入材料staffOperation
								$location.path("/putMaterialConfirm").search({
									address: $location.path()
								});
							} else {
								//取出证照
								$location.path("/licenseInputCode");
							}
						}
					} else {
						alert("请用工作人员信息登录！");
						$state.go("main");
					}

					$scope.$apply();
				} else {
					$scope.loginErrorType = "登录失败，请重新登录";
					return
				}
			},
			error: function(err) {
				$scope.loginErrorType = "登录失败，请重新登录";
				return
			}
		});
	}

	$scope.loginRequest = function(code, isRe) {
		var _code = code.trim().replace(/\u0000/g, '');
		if(code.indexOf("http") != -1) {
			// 扫描的是随申码
			$.ajax({
				type:'post',
				url: $.getConfigMsg.preUrl + "selfapi/loginService/getTokenSNOByQrCode.do",
				dataType: 'json',
				//jsonp: "jsonpCallback",
				data: {
					certQrCode: code,
				},
				success: function(dataJson) {
					console.log('通过二维码获取的tokenSNO: ', dataJson)
					if(dataJson.data != null && dataJson.data != undefined && dataJson.data.encrypted == true) {
						appData.tokenSNO = dataJson.data.biz_response.tokenSNO;
						$scope.getAccessToken(dataJson.data.biz_response.tokenSNO);
					} else {
						$scope.loginErrorType = "登录失败，请重新登录";
						return
					}
				},
				error: function(err) {
					$scope.loginErrorType = "登录失败，请重新登录";
					return
				}
			})
		} else {
			// 扫描的是身份证亮证
			$scope.httpConfig = {
				jsonpCallback: "JSON_CALLBACK",
				//using:"证照柜亮证",
				codeParam: _code,
				machineId: $.config.get("uniqueId") || "00-E2-69-1F-8D-3D"
			}
			$http.jsonp($.getConfigMsg.preUrl + "selfapi/getQrCodeInfoByElectronicCert.do", {
				params: $scope.httpConfig,
				//				using: "证照柜亮证",
				timeout: 10000
			}).success(function(data) {
				console.log('身份证亮证返回值: ', data)
				$.log.debug('身份证亮证返回值: ');
				$.log.debug(JSON.stringify(data));
				if(data.data.result.success === false) {
					$scope.loginErrorType = data.result.msg;
					$scope.logging = false;
					return false;
				}
				appData.userName = data.data.result.data.realname;
				appData.idCardNumber = data.data.result.data.idcard;
				appData.VALIDENDDAY = data.data.result.data.VALIDENDDAY
				appData.VALIDSTARTDAY = data.data.result.data.VALIDSTARTDAY

				//判断是否存在该工作人员
				let Code = appData.idCardNumber
				let result = {};
				for(var i = 0; i < appConfig.personnel.length; i++) {
					if(appConfig.personnel[i].Code == Code) {
						result = appConfig.personnel[i];
					}
				}
				if(result.Code) {
					if(appData.isStaff === true) {
						$location.path("/staffOperationChoice");
					} else {
						if(appData.matterType === "material") {
							//放入材料staffOperation
							$location.path("/putMaterialConfirm").search({
								address: $location.path()
							});
						} else {
							//取出证照
							$location.path("/licenseInputCode");
						}
					}
				}
			}).error(function(err, status) {
				$scope.logging = false;
				if(status == -1) {
					$scope.loginErrorType = "登录超时,请重新登录";
					return;
				}
				$scope.loginErrorType = "登录失败,请重新登录";

			});
		}
	}

	$scope.scanQrCode = function() {
		$.device.qrCodeOpen(function(code) {
			code = code.replace(/[\r\n]/g, "");
			if(code == "") {
				$scope.scanQrCode();
				return;
			}
			$scope.isReadOver = true;
			$scope.codeString = code;
			$scope.loginRequest(code);
			$.device.qrCodeClose();
			$scope.$apply();
		});
	};

	$scope.loginTypeTab = function(type) {
		if(type === "idCard") {
			$scope.authentication = '请扫描随申码'
			$scope.btnInfo = "身份证登录";
			$scope.loginType = "citizenCloud";
			$scope.scanQrCode();
			$.device.idCardClose();
			return;
		}
		$scope.authentication = '请刷身份证'
		$scope.btnInfo = "市民云登录";
		$scope.loginType = "idCard";
		$scope.idCardOpen();
		$.device.qrCodeClose()
	};
	/**
	 * 身份证读取 存储身份证编号
	 * */
	$scope.goHome = function() {
		$.device.Camera_Hide();
		$location.path("/main");
	};
	$scope.idCardOpen = function() {
		appData.loginGuard = true;
		$.device.idCardOpen(function(value) {
			var list = JSON.parse(value);

			//			测试数据
			//					let list = {
			//						Name: "邹天奇",
			//						Number: "430426199804106174",
			//					}

			let Name = list.Name;
			let Code = list.Number;
			let result = {};
			if(list) {
				//判断是否存在该工作人员
				for(var i = 0; i < appConfig.personnel.length; i++) {
					if(appConfig.personnel[i].Code == Code) {
						result = appConfig.personnel[i];
					}
				}
				if(result.Code) {
					appData.loginGuard = true;
					appData.userName = list.Name;
					appData.idCardNumber = list.Number;
					appData.idCardImg = list.CardImagePath;

					// 获取身份证base64
					//					$scope.getImgbaseStr(
					//						appData.idCardNumber,
					//						appData.userName,
					//						appData.idCardImg
					//					);

					if(appData.isStaff === true) {
						$location.path("/staffOperationChoice");
					} else {
						if(appData.matterType === "material") {
							//放入材料staffOperation
							$location.path("/putMaterialConfirm").search({
								address: $location.path()
							});
						} else {
							//取出证照
							$location.path("/licenseInputCode");
						}
					}

				} else {
					alert("请用工作人员信息登录！");
					$state.go("main");
				}
			}

			$scope.$apply();
		});

	};
	//获取上传的身份证base64
	//	$scope.getImgbaseStr = function(idCardNo, name, imgUrl) {
	//		appData.ImageUrlStr = $.device.fileBase64(imgUrl);
	//	};

	// 进入该页面是默认刷身份证
	$scope.idCardOpen();

	//测试扫码
	//	$scope.loginRequest('https://s.sh.gov.cn/023f87e241728bb9c00c86582e60061611651497812');

});
/*
 * 工作人员操作 
 */
app.controller("staffOperationChoiceController", function($scope, $http, appData, $location, appConfig) {
	$.device.qrCodeClose();
	$scope.handleChoice = function(type) {
		if(type == 'staff-putin-license') {
			$location.path('/inputPhone')
		} else {
			$location.path('/materialInfoList')
		}
	}
	//	if(appData.loginGuard === false || appData.loginGuard === undefined) {
	//		alert("请先登录!");
	//		$location.path("/staffOperation");
	//		return;
	//	}
});
/**
 * 证照列表
 */
app.controller("licenseInfoListController", function($scope, $http, appData, $location, appConfig) {
	if(appData.loginGuard === false || appData.loginGuard === undefined) {
		alert("请先登录!");
		$location.path("/staffOperation");
		return;
	}
	$scope.hasData = false;
	$scope.isLoding = true;
	$scope.flag = true; // 继续按钮生效
	$scope.licenseList = [];
	appConfig.http(
		"getCertFlowForAdmin.do", {
			//			stIdentityNo: appData.idCardNumber
		},
		function(res) {
			$scope.isLoding = false;
			if(res.data.length >= 1) {
				appData.licenseList = res.data;
				if(res.data.length > 8) {
					//超过八条数据进行分页
					appData.licenseList = EXTPaging({
						data: res.data,
						quantity: 8
					}).data;
				}
				//取出证照
				$scope.licenseList = appData.licenseList;
				$scope.isPaging = false;
				$scope.totalPages = 1;
				$scope.currentPage = 1;
				if(appData.licenseList[0] instanceof Array) {
					$scope.totalPages = appData.licenseList.length;
					$scope.licenseList = appData.licenseList[0];
					$scope.isPaging = true;
				}
				console.log($scope.licenseList);
			} else {
				$scope.hasData = true;
				console.log(res);
			}
		},
		function(err) {
			$scope.isLoding = false;
			$scope.hasData = true;
			$.log.debug("login`" + err);
		}
	);

	$scope.next = function() {
		if($scope.currentPage < $scope.totalPages) {
			$scope.currentPage++;
			$scope.licenseList = appData.licenseList[$scope.currentPage - 1];
		}
	};
	$scope.prev = function() {
		if($scope.currentPage > 1) {
			$scope.currentPage--;
			$scope.licenseList = appData.licenseList[$scope.currentPage - 1];
		}
	};
	$scope.$watch("licenseList", function(n) {
		n.sort(function(a, b) {
			return Date.parse(a.DT_STORE) - Date.parse(b.DT_STORE)
		});
		$scope.licenseList = n;
	});
	var reqCount = 10;
	$scope.openBox = function(serialNum) {
		if($scope.flag) {
			$scope.flag = false;
			appConfig.http(
				"getCertFlowForUser.do", {
					stCabinetNo: serialNum,
				},
				function(res) {
					$.log.debug("license: " + res);
					$location.path("/openBox").search({
						number: serialNum,
						tips: "取出证照",
						address: $location.path()
					});
				},
				function(err) {
					reqCount--;
					if(reqCount > 1) {
						$scope.openBox(serialNum, id);
					}
				}
			);
		}
	};
	$scope.back = function() {
		if(appData.isStaff === true) {
			$location.path("/staffOperationChoice");
		} else {
			$location.path("/main");
		}
	}
});
/**
 * 材料列表
 */
app.controller("materialInfoListController", function($scope, $http, appData, appConfig, $location, $timeout) {
	if(appData.loginGuard === false || appData.loginGuard === undefined) {
		alert("请先登录!");
		$location.path("/staffOperation");
		return;
	}
	$scope.cabinetNo = null;
	$scope.hasData = false;
	$scope.isLoding = true;
	$scope.licenseList = [];
	$scope.staffLogin = function() {
		appConfig.http("takeStuffs.do", {
				stMachineId: $.config.get('uniqueId') || "00-E2-69-1F-8D-3D"
			},
			function(res) {
				$.log.debug("login :" + JSON.stringify(res));
				console.log('材料列表: ', res)
				$scope.isLoding = false;
				if(res.data.length >= 1) {
					appData.licenseList = res.data;
					if(res.data.length > 8) {
						//超过八条数据进行分页
						appData.licenseList = EXTPaging({
							data: res.data,
							quantity: 8
						}).data;
					}

					//取出材料
					$scope.licenseList = appData.licenseList;
					$scope.isPaging = false;
					$scope.totalPages = 1;
					$scope.currentPage = 1;
					if(appData.licenseList[0] instanceof Array) {
						$scope.totalPages = appData.licenseList.length;
						$scope.licenseList = appData.licenseList[0];
						$scope.isPaging = true;
					}
					$.log.debug("login:" + JSON.stringify($scope.licenseList));
				} else {
					console.log(res);
					$scope.hasData = true;
					console.log("登录失败！");
				}
			},
			function(err) {
				$scope.hasData = true;
				$scope.isLoding = false;
				$.log.debug("login error:" + err);
			}
		);
	};
	$timeout(function() {
		$scope.staffLogin();
	}, 300);
	$scope.$watch("licenseList", function(n) {
		n.sort(function(a, b) {
			return Date.parse(a.DT_STORE) - Date.parse(b.DT_STORE)
		});
		$scope.licenseList = n;
	});
	$scope.next = function() {
		if($scope.currentPage < $scope.totalPages) {
			$scope.currentPage++;
			$scope.licenseList = appData.licenseList[$scope.currentPage - 1];
		}
	};
	$scope.prev = function() {
		if($scope.currentPage > 1) {
			$scope.currentPage--;
			$scope.licenseList = appData.licenseList[$scope.currentPage - 1];
		}
	};
	//取出材料
	//一个小bug
	var reqCount = 10;
	$scope.openBox = function(No, id) {
		appConfig.http("take.do", {
				//				isOpen: "true",
				//				stSelfApplyId: id
				stMachineId: $.config.get('uniqueId') || "00-E2-69-1F-8D-3D",
				nmCertType: '1',
				stReceiveNum: '',
				stCabinetNo: No
			},
			function(res) {
				console.log(res)
				if(res.success == true) {
					var routerName = $location.path();
					$location.path("/openBox").search({
						number: No,
						address: routerName,
						tips: "取出材料"
					});
				} else {
					reqCount--;
					if(reqCount > 1) {
						$scope.openBox(No, id);
					}
				}
			},
			function(err) {
				reqCount--;
				if(reqCount > 1) {
					$scope.openBox(No, id);
				}
			}
		);
		$.log.debug($location.path());
	};
	$scope.back = function() {
		if(appData.isStaff === true) {
			$location.path("/staffOperationChoice");
		} else {
			$location.path("/main");
		}
	}
});
/**
 * 扫描二维码
 */
app.controller("scanCodeController", function(
	$scope,
	$http,
	appData,
	$location,
	appConfig
) {
	/**
	 * 扫描二维码操作  将获取的信息传入下面路由中参数  code
	 */

	$scope.isManual = false;
	$scope.isStaff = appData.isStaff;
	$scope.matterInfo = null;
	$scope.module = "办事人员操作";
	$scope.whoOperation = "材料放置";
	$scope.operation = "条形码";
	$scope.btnInfo = "手动输入";
	$scope.matterName = "收件凭证";
	$scope.formPrevRouter = "main";
	$scope.isCodeInfo = false; //用来接收扫描数据
	$scope.goPrevRoute = function() {
		$location.path("/" + $scope.formPrevRouter);
	}
	$scope.scanCode = function() {
		$.device.qrCodeOpen(function(code) {
			var _code = code.trim().replace(/\u0000/g, '');
			appData.qrCodeInfo = _code;
			if($scope.zfbs(appData.qrCodeInfo)) {
				$location.path("/putMaterialConfirmzf").search({
					code: _code
				});
			} else {
				$location.path("/putMaterialConfirm").search({
					code: _code
				});
			}
			$scope.$apply();
			$.device.qrCodeClose();
		});
	};
	$scope.zfbs = function(str) {
		var t = parseInt(str.substring(0, 4));
		var c = t.toString(2);
		var a = [];
		for(var i = 0; i < c.length; i++) {
			if(c[i] == "0") {
				a[i] = "1";
			} else {
				a[i] = "0";
			}
		}
		var z = a.toString().replace(/,/g, "");
		var x = parseInt(z, 2);
		console.log(str.substr(-3, 3));
		if(x == str.substr(-3, 3)) {
			return true;
		} else {
			return false;
		}
	}
	if(appData.resideQuantity < 1) {
		alert("暂无可使用柜子！");
		$location.path("/main");
		return;
	}
	if(appData.isStaff === true) {
		//		if(appData.loginGuard === false || appData.loginGuard === undefined) {
		//			alert("请先登录!");
		//			$location.path("/main");
		//			return;
		//		}
		$scope.isManual = false;
		$scope.module = "工作人员操作";
		$scope.whoOperation = "自助放证";
		$scope.matterName = "送达回证";
		$scope.formPrevRouter = "staffOperationChoice";
	}
	$scope.numCode = "";
	$scope.inputCode = function(val) {
		$scope.numCode.length < 15 ? ($scope.numCode += val) : "";
	};
	$scope.deleteCode = function() {
		$scope.numCode = $scope.numCode.slice(0, $scope.numCode.length - 1);
	};
	$scope.changeInputModel = function() {
		/**
		 * 开关扫描仪
		 */
		$scope.btnInfo = $scope.btnInfo === "手动输入" ? "扫二维码" : "手动输入";
		if($scope.isManual === false) {
			$.device.qrCodeClose();
		} else if($scope.isManual === true) {
			$scope.scanCode();
		}
		$scope.isManual = !$scope.isManual;
	};
	$scope.confirmCode = function() {
		if($scope.numCode.length < 5) {
			alert("请输入十五位办证编码!");
			return;
		}
		appData.qrCodeInfo = $scope.numCode;
		if($scope.zfbs(appData.qrCodeInfo)) {
			$location.path("/putMaterialConfirmzf").search({
				code: $scope.numCode
			});
		} else {
			$location.path("/putMaterialConfirm").search({
				code: $scope.numCode
			});
		}
	};
	$scope.scanCode();
	$scope.back = function() {
		if(appData.isStaff === true) {
			$location.path("/staffOperationChoice");
		} else {
			$location.path("/main");
		}
	}
});
/**
 * 输入手机号
 */
app.controller("inputPhoneController", function($scope, $http, appData, $rootScope, $location, appConfig) {
	/**
	 * 扫描二维码操作  将获取的信息传入下面路由中参数  code
	 */
	$scope.numCode = "";
	$scope.isManual = false;
	$scope.isStaff = appData.isStaff;
	$scope.matterInfo = null;
	$scope.module = "办理用户操作";
	$scope.whoOperation = "材料放入";
	$scope.operation = "手机号";
	$scope.formPrevRouter = "main";
	$scope.isCodeInfo = false; //用来接收扫描数据
	$scope.goPrevRoute = function() {
		$location.path("/" + $scope.formPrevRouter);
	}
	//查询空柜子数量
	if($rootScope.resideQuantity < 1) {
		alert("暂无可使用柜子！");
		$location.path("/main");
		return;
	}
	console.log('是否为工作人员: ', appData.isStaff)
	if(appData.isStaff === true) {
		if(appData.loginGuard === false || appData.loginGuard === undefined) {
			alert("请先登录!");
			// 此处跳转到工作人员登录界面
			$location.path("/staffOperation");
			return;
		}
		$scope.isManual = false;
		$scope.module = "工作人员操作";
		$scope.whoOperation = "自助放证";
		$scope.matterName = "送达回证";
		$scope.formPrevRouter = "staffOperationChoice";
	}
	$scope.inputCode = function(val) {
		$scope.numCode.length < 15 ? ($scope.numCode += val) : "";
	};
	$scope.deleteCode = function() {
		$scope.numCode = $scope.numCode.slice(0, $scope.numCode.length - 1);
	};

	$scope.confirmCode = function() {
		if($scope.numCode.length < 11) {
			return alert("请输入正确手机号!");
		}
		appData.phoneNum = $scope.numCode;
		$location.path("/putMaterialConfirm");
	};
	$scope.back = function() {
		if(appData.isStaff === true) {
			$location.path("/staffOperationChoice");
		} else {
			$location.path("/main");
		}
	}
});
/**
 * 信息确认
 */
app.controller("putMaterialConfirmController", function($scope, $http, appData, $location, appConfig, $timeout) {
	//证照信息确认
	$scope.qrCode = appData.qrCodeInfo; //获取二维码扫描编码
	$scope.matterType = "办事";
	$scope._matterInfo = "材料";
	if(appData.isStaff === true) {
		$scope._matterInfo = "证照";
		$scope.matterType = "工作";
	}
	$scope.stCertFlowId = null;
	$scope.cabList = [];
	$scope.getMethod = "putCertFlow.do";
	$scope.goHome = function() {
		$location.path("/main");
	};
	var reqCount = 10;
	$scope.$on("$viewContentLoaded", function(event, toState, toParams, fromState, fromParams) {
		$scope.isShow = true;
		$scope.isLoding = false;
		$scope.isStaff = appData.isStaff;
		$scope.userName = appData.userName;
		$scope.stPhone = appData.phoneNum;
		$scope.idCardNumber = appData.idCardNumber;
	});
	/**
	 * 这里寻找空闲的证照柜柜子
	 * 随机选取一个空闲柜子
	 */
	$scope.Continue = function() {
		//		// 生成一个大于零,小于柜子数量的随机整数
		//		var stCabinetNo = appConfig.initSark[parseInt(Math.random() * appConfig.initSark.length, 10)];
		//		// 获取已使用掉的柜子数组
		//		var arr = appConfig.sark;
		//		var index = arr.indexOf(stCabinetNo);
		//		while(index != -1) {
		//			stCabinetNo = appConfig.initSark[parseInt(Math.random() * appConfig.initSark.length, 10)];
		//			index = arr.indexOf(stCabinetNo);
		//		}
		//		$.log.debug("stCabinetNo: " + stCabinetNo)
		//		$.log.debug(JSON.stringify(appConfig.sark))
		appConfig.getEmptyCabinet(function(res) {
			let stCabinetNo = 0
			let stCabinetNos = res.data
			let index = Math.floor(Math.random() * res.data.length + 1)
			stCabinetNo = stCabinetNos[index]
			stCabinetNo = $scope.yetUseCabinet || stCabinetNo; //存在原有的柜子号则打开原有的 否则打开新生成的柜子号
			console.log('柜号: ', stCabinetNo)
			$scope.takeAndStore(stCabinetNo)
		})

		//			appConfig.http(
		//				"putCertFlow.do", {
		//					//办事人员放入材料
		//					stCabinetNo: stCabinetNo,
		//					stName: $scope.userName,
		//					idCard: $scope.idCardNumber,
		//					stUserName: appConfig.personnel[0].Name,
		//					stMobile: appConfig.personnel[0].Tel,
		//					stIdentityNo: appConfig.personnel[0].Code,
		//					certName: "",
		//					ext: 1,
		//				},
		//				function(res) {
		//					alert('success'+res);
		//					if(res.success == true) {
		//						$location.path("/openBox").search({
		//							number: stCabinetNo,
		//							tips: "放入材料"
		//						});
		//					} else {
		//						alert("没有查询到办件信息，请重新扫描或输入！")
		//					}
		//				},
		//				function(err) {
		//					alert('false'+err);
		//					$.log.debug('false'+err);
		//				}
		//			);
	};
	/**
	 * 1. 工作人员存入证照
	 * 2. 办事人员放入材料
	 */
	$scope.takeAndStore = function(stCabinetNo) {
		if(appData.isStaff == true) {
			appConfig.http("store.do", {
					//工作人员放入证照
					stMachineId: $.config.get('uniqueId') || "00-E2-69-1F-8D-3D",
					stCabinetNo: stCabinetNo,
					nmCertType: '0',
					stReceiverName: $.config.get('stReceiverName') || '',
					stReceiverIdcard: $.config.get('stReceiverIdcard') || '',
					stReceiverPhone: $scope.stPhone || '',
					stSenderName: encodeURIComponent(appConfig.personnel[0].Name),
					stSenderId: appConfig.personnel[0].Code,
					stSenderPhone: appConfig.personnel[0].Tel,
					stCertEquipmentNo: $.config.get('stCertEquipmentNo') || 'A'
				},
				function(res) {
					console.log(res)
					if(res.success == true) {
						$location.path("/openBox").search({
							number: stCabinetNo,
							stReceiveNum: res.stReceiveNum,
							tips: "放入材料",
							address: 'staffOperationChoice'
						});
					} else {
						alert("没有查询到办件信息，请重新扫描或输入！")
					}
				},
				function(err) {
					$.log.debug(err);
				}
			);
		} else {
			$.ajax({
				type: "POST",
				url: appConfig.httpUrl + "store.do",
				//				dataType: "jsonp",
				//				jsonp: "jsonpCallback",
				data: {
					stMachineId: $.config.get('uniqueId') || "00-E2-69-1F-8D-3D",
					stCabinetNo: stCabinetNo,
					nmCertType: '1',
					stReceiverName: appConfig.personnel[0].Name || '',
					stReceiverIdcard: appConfig.personnel[0].Code || '',
					stReceiverPhone: appConfig.personnel[0].Tel || '',
					stSenderName: encodeURIComponent($scope.userName),
					stSenderId: $scope.idCardNumber,
					stSenderPhone: $scope.stPhone,
					stCertEquipmentNo: $.config.get('stCertEquipmentNo') || 'A'
				},
				success: function(res) {
					if(JSON.parse(res).success == true) {
						$location.path("/openBox").search({
							number: stCabinetNo,
							tips: "放入材料",
							address: $location.path()
						});
						$scope.$apply()
					} else {
						alert("没有查询到办件信息，请重新扫描或输入！")
					}
				},
				error: function(err) {
					$.log.debug('false' + err);
				}
			})
		}
	}
	$scope.reScan = function() {
		if(appData.isStaff === true) {
			$location.path("/staffOperation");
		} else {
			$location.path("/licenseTake");
		}
	};
	$scope.back = function() {
		if(appData.isStaff === true) {
			$location.path("/staffOperation");
		} else {
			$location.path("/licenseTake");
		}
	}
});
/**
 * 控制中心
 * */
app.controller("cabinetControllerController", function($scope, $http, appData, appConfig, $timeout, $location, $interval, $rootScope) {
	//	if(appData.loginGuard === false || appData.loginGuard === undefined) {
	//		alert("请先登录!");
	//		$location.path("/main");
	//		return;
	//	}
	$scope.totalCabinet = appConfig.initSark;
	$scope.matterInfo = [];
	$scope.currentMatter = null;
	$scope.mask = false;
	$scope.currentPath = $location.path();

	$scope.compare = function(property) {
		return function(a, b) {
			var value1 = Number(a[property]);
			var value2 = Number(b[property]);
			return value1 - value2;
		}
	}

	//通过mac地址获取该设备的所有柜子信息
	$scope.getAllCabinetInfo = function() {
		appConfig.http("allCabinetInfo.do", {
				stMachineId: $.config.get('uniqueId') || "00-E2-69-1F-8D-3D"
			},
			function(res) {
				if(res.success) {
					$scope.totalCabinet = res.data.sort($scope.compare('stCabinetNo'))
					//					$scope.totalCabinet.forEach((item, index) => {
					//						if(item.nmCertType == 0) {
					//							item.className = 'hasLicense'
					//						} else if(item.nmCertType == 1) {
					//							item.className = 'hasMaterial'
					//						} else {
					//							item.className = 'emptyCabinet'
					//						}
					//					})
					for(let i = 0; i < $scope.totalCabinet.length; i++) {
						if($scope.totalCabinet[i].nmCertType == 0) {
							$scope.totalCabinet[i].className = 'hasLicense'
						} else if($scope.totalCabinet[i].nmCertType == 1) {
							$scope.totalCabinet[i].className = 'hasMaterial'
						} else {
							$scope.totalCabinet[i].className = 'emptyCabinet'
						}
					}
					console.log('所有柜子信息: ', $scope.totalCabinet)
				}
			},
			function(err) {
				alert('发生错误,请重试!')
			}
		);
	}
	//通过mac地址获取该设备的所有柜子信息
	$scope.getAllCabinetInfo()

	$scope.confirmOpen = function() {
		$location.path("/openBox").search({
			number: $scope.currentMatter.cabinetNo,
			address: $scope.currentPath
		});
	};

	//打开柜子
	$scope.openCabinet = function(item) {
		$scope.currentMatter = null;
		if($scope.currentMatter === null) {
			var layer = confirm("是否打开" + item.stCabinetNo + "号柜子?");
			if(layer === true) {
				appConfig.http("take.do", {
						stMachineId: $.config.get('uniqueId') || "00-E2-69-1F-8D-3D",
						nmCertType: item.nmCertType,
						stReceiveNum: '',
						stCabinetNo: item.stCabinetNo
					},
					function(res) {
						if(res.success == true) {
							$location.path("/openBox").search({
								number: item.stCabinetNo,
								address: $scope.currentPath,
								tips: '操作完成'
							});
						} else {
							alert('发生错误,请重试!')
						}
					},
					function(err) {
						alert('发生错误,请重试!')
					}
				);
			}
		}
	};
	$scope.closeMask = function() {
		$scope.mask = false;
	};
})
/**
 * 开箱
 */
app.controller("openBoxController", function($scope, $http, appData, appConfig, $timeout, $location, $interval, $rootScope) {
	$scope.$on("$locationChangeStart", function(event, toState, toParams, fromState, fromParams) { //路由变化清除计时器
		$interval.cancel($scope.Interval);
	});
	//开柜
	$scope.Number = $location.search().number || null;
	$scope.tipsInfo = $location.search().tips || "";
	$scope.whereFrom = $location.search().address;
	$scope.stReceiveNum = $location.search().stReceiveNum || "";

	$scope.isStaff = appData.isStaff;
	$scope.countDown = 600;
	$.log.debug($scope.whereFrom);
	$scope.Interval = $interval(function() {
		$scope.countDown--;
		if($scope.countDown < 1) {
			$interval.cancel($scope.Interval);
			$location.path("/main").search({});
		}
	}, 1000);
	$scope.Continue = function() {
		$interval.cancel($scope.Interval);
		$location.path($scope.whereFrom);
	};
	$scope.goHome = function() {
		$interval.cancel($scope.Interval);
		$location.path("/main").search({});
	};
	$scope.otherHandle = function() {
		$interval.cancel($scope.Interval);
		$location.path("/staffOperationChoice");
	};

	/**
	 * 这里执行开柜方法  $scope.Number为柜子编号
	 * 先打开端口,才能打开柜子
	 * SarkInit: 打开端口
	 * SarkOpen: 打开柜子
	 */
	appConfig.getEmptyCabinet(function(res) {
		$rootScope.resideQuantity = res.data.length
	})
	$.device.SarkInit();
	$.device.SarkOpen($scope.Number);
});