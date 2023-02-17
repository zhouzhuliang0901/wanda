var urlHost = "http://172.16.6.18:8080/aci/certification/";
//var urlHost = "http://192.168.1.142:8080/ac-product/aci/certification/"
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
				$location.path("/staffOperation").search({});
				//$location.path("/staffOperationChoice").search({});
			} else {
				console.log("matters type is error!");
			}
		} else {
			if(matter === "license" || matter === "material") {
				appData.matterType = matter;
				$location.path("/licenseTake").search({});
				//$location.path("/scanCode").search({});
			} else {
				console.log("matters type is error!");
			}
		}
	};
	$scope.changeModel = function(model) {
		if(model === "staff") {
			appData.isStaff = true;
			$location.path("/staffOperation").search({});
			//           $location.path("/staffComfirm").search({});
		} else if(model === "masses") {
			appData.isStaff = false;
		}
	};
});
/**
 * 办事人员取证
 */
app.controller("licenseTakeController", function($scope, $http, appConfig, appData, $timeout, $location) {
	$scope.loginType = "idCard";
	$scope.btnInfo = "市民云亮证";
	$scope.title = "自助取证";
	$scope.isReadOver = false;
	appConfig.getResideQuantity()
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
	//	$scope.scanQrCode = function() {
	//		appData.userName = "赵振芳";
	//		appData.idCardNumber = "370285199611114728";
	//		$location.path("/scanCode");
	//	}

	var url = "http://183.194.250.112/ac-self";
	$scope.getUserInfoByAccessToken = function() {
		$.ajax({
			type: "get",
			url: url + "/aci/workPlatform/getUserInfoByAccessToken.do",
			dataType: "json",
			data: {
				accessToken: appData.token
			},
			success: function(dataJson) {
				console.log(dataJson);
				if(dataJson != undefined && dataJson != null && dataJson != "") {
					appData.userName = dataJson.zwdtsw_name;
					appData.idCardNumber = dataJson.zwdtsw_cert_id;
					$location.path("/scanCode");
				} else {
					alert("未获取到市民信息");
				}
			},
			error: function(err) {
				alert("身份信息获取失败！");
			}
		});
	}
	//获取token ------2、比对成功后，根据tokenSNO获取access_token
	$scope.getAccessToken = function(tokenSNO) {
		var rec = $.ajax({
			url: url + "/aci/workPlatform/getAccessToken.do",
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
					alert('token获取失败');
				}
			},
			error: function(err) {
				alert('token获取失败');
			},
		})
	}

	$scope.scanQrCode = function() {
		$.device.qrCodeOpen(function(code) {
			if(code == "") {
				$scope.scanQrCode();
				return;
			}
			$scope.isReadOver = true;
			var __code = code.replace(/[\r\n]/g, "");
			$.post(url + "/selfapi/loginService/getTokenSNOByQrCode.do", {
					certQrCode: __code,
					machineId: $.config.get('uniqueId')
				},
				function(data) {
					console.log(data);
					var dataJonsp = data.data;
					if(dataJonsp != null && dataJonsp != undefined && dataJonsp.encrypted == true) {
						$scope.getAccessToken(dataJonsp.biz_response.tokenSNO);
					} else {
						alert('tokenSNO获取失败');
					}
				}, "json").success(function() {

			}).error(function() {
				alert("登录失败，请重试！");
			});
			$scope.$apply();
			$.device.qrCodeClose();
		});
	};
	$scope.readIdCard = function() {
		$.device.idCardOpen(function(value) {
			var list = JSON.parse(value);
			if(list != null) {
				$.log.debug(list);
				$scope.isReadOver = true;
				appData.userName = list.Name;
				appData.idCardNumber = list.Number;
				appData.idCardImg = list.CardImagePath;
				/*$scope.getImgbaseStr(
				    appData.idCardNumber,
				    appData.userName,
				    appData.idCardImg
				);*/
				$timeout(function() {
					//                  $location.path("/faceVerification"); 
					if(appData.isStaff === true) {
						$location.path("/staffOperationChoice");
					} else {
						//                      if(appData.matterType === "material") {
						//放入材料staffOperation
						$location.path("/scanCode").search({
							address: $location.path()
						});
						//                      } else {
						//取出证照
						//                          $location.path("/scanCode");
						//                      }
					}
					$scope.$apply();
				}, 2000);
			}
			$.device.idCardClose();
		});
	};
	//获取上传的身份证base64
	$scope.getImgbaseStr = function(idCardNo, name, imgUrl) {
		$.ajax({
			type: "post",
			url: appConfig.preUrl + "/aci/autoterminal/archives/getLicenseStuffList.do",
			data: {
				stIdNo: idCardNo,
				type: 0,
				baseType: "WITNESS_CONTRAST"
			},
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			success: function(dataJsonp) {
				if(dataJsonp.length > 0) {
					$scope.ImageStr = dataJsonp[0].imageStr;
					appData.ImageUrlStr = $scope.ImageStr;
					$.log.debug("$scope.ImageStr");
					return;
				} else {
					$scope.uploadIdCardImg(idCardNo, name, imgUrl);
				}
			},
			error: function() {}
		});
	};

	//上传身份证
	$scope.uploadIdCardImg = function(idCardNo, name, imgUrl) {
		var jsonData = {
			type: "0",
			stShareCode: "WITNESS_CONTRAST",
			stName: name,
			stIdNo: idCardNo
		};
		$.device.httpUpload(
			appConfig.preUrl + "/aci/autoterminal/archives/saveLicenseStuff.do",
			"FileData",
			imgUrl,
			JSON.stringify(jsonData),
			function(result) {
				$scope.getImgbaseStr(idCardNo, name, imgUrl);
			},
			function(webexception) {
				layer.msg("网络异常");
			}
		);
	};
	$scope.readIdCard();
});
/**
 * 人证核验
 */
app.controller("faceVerificationController", function($scope, $http, $location, appData, $timeout, $interval) {
	$scope.$on("$locationChangeStart", function() {
		$interval.cancel($scope.countDown);
	});
	$scope.isCheckAgain = $location.search().reVerfication;
	$.device.Camera_Init(650, 480, 215, 715); //初始化摄像头
	$.device.Camera_Link("RGB Camera", 5); //初始化摄像头
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
app.controller("verificationAwaitController", function($scope, $http, $location, appData, appConfig) {
	$scope.recognitionAjax = null;
	$scope.$on("$locationChangeStart", function() {
		try {
			$scope.recognitionAjax.abort();
		} catch(e) {
			console.log("不存在该请求！");
		}
	});
	$scope.recognition = function() {
		//人证数据对比
		$scope.recognitionAjax = $.ajax({
			type: "post",
			url: "http://172.16.6.18:8088/ext/ext/aci/autoterminal/facecomparebd.do",
			data: {
				idCardPhoto: appData.ImageUrlStr,
				capturePhoto: appData.capturePhoto
			},
			dataType: "json",
			success: function(data) {
				$.log.debug(data);
				var verificationStatus = null; //0 失败  1 成功
				var n = JSON.parse(data).similarity;
				if(n > 60) {
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
				$.log.debug("err:" + data);
			}
		});
	};
	$scope.recognition();
});
app.controller("verificationCompleteController", function($scope, $http, $location, appData, $timeout) {
	$scope.verificationStatus =
		$location.search().verificationStatus == "1" ? true : false; //判断人证核验成功与否
	if($scope.verificationStatus) {
		$timeout(function() {
			$scope.Continue();
		}, 1000);
	};
	$scope.tcontinue = false;
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
				$location.path("/signature");
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
	console.log(appData.picStr);
	$scope.numCode = "";
	$scope.getNumber = 10;
	$scope.qrCode = appData.qrCodeInfo;
	$scope.boxNum = "";
	$scope.inputCode = function(val) {
		$scope.numCode.length < 6 ? ($scope.numCode += val) : "";
	};
	$scope.deleteCode = function() {
		$scope.numCode = $scope.numCode.slice(0, $scope.numCode.length - 1);
	};
	//	$scope.confirmCode = function() {
	//		$location.path("/openBox").search({
	//			number: "26",
	//			tips: "取出证照",
	//			userFlag: true
	//		});
	//	}
	$scope.confirmCode = function() {
		if($scope.numCode.length < 6) {
			alert("请输入六位取证码!");
			return;
		}
		//验证码和办件编码
		$.ajax({
			type: "get",
			url: appConfig.httpUrl + "queryCertIficationInfo.do",
			async: true,
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				ST_VERIFICATION_CODE: $scope.numCode,
				ST_APPLY_NO: $scope.qrCode.stApplyNo,
				ST_CERT_TYPE: 0
			},
			success: function(res) {
				if(res.length <= 0) {
					alert("未查询到证照，请联系工作人员处理");
				}
				$scope.boxNum = res[0].stCertIficationNo;
				// 加一个判断 0已取证   1已放证  stCertIficationType
				if(res[0].stCertIficationType == '0') {
					alert("未查询到证照，请联系工作人员处理");
				} else {
					$.ajax({
						type: "post",
						data: {
							ST_CERT_IFICATION_ID: res[0].stCertIficationId,
							ST_APPLY_NO: $scope.qrCode.stApplyNo,
							ST_WORK_USER_NAME: appData.userName,
							ST_WORK_CERT_NO: appData.idCardNumber,
							ST_CERT_IFICATION_NO: res[0].stCertIficationNo,
							ST_CERT_IFICATION_TYPE: "0",
							ST_CERT_EQUIPMENT_NO: "A",
							BL_IMAGE: '' //appData.picStr
						},
						url: urlHost + "addCertIficationInfo.do",
						dataType: "json",
						success: function(res) {
							$location.path("/openBox").search({
								number: $scope.boxNum,
								caseId: $scope.qrCode.stApplyNo,
								receiveCode: res.stVerificationCode || "",
								tips: "取出证照",
								userFlag: true
							});
							$scope.$apply();
						},
						error: function(res) {
							alert("未查询到证照，请联系工作人员处理");
						}
					});
				}

			},
			error: function(err) {
				alert("取证码无效，请联系工作人员处理");
			}
		});
	}
});
/**
 * 签名
 */
app.controller("signatureController", function($scope, $http, appData, appConfig, $location) {
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
		appData.picStr = $scope.signature.split(",")[1];
		$location.path("/scanCode");
	};
});
app.controller("getLicenseConfirmController", function($scope, $http, appData, appConfig, $location) {
	//确认证照信息
	$scope.certInfo = appData.massesGetLicense;
	$scope.Continue = function() {
		$.log.debug(JSON.stringify($scope.certInfo));
	};
});
/**
 * 工作人员登录操作
 */
app.controller("staffOperationController", function($scope, $http, appData, $location, appConfig) {
	$scope.pageClass = "fade";
	$scope.title = "工作人员登录";
	$scope.loginType = "idCard";
	$scope.btnInfo = "市民云登录";
	var url = "http://183.194.250.112/ac-self";
	$scope.getUserInfoByAccessToken = function() {
		$.ajax({
			type: "get",
			url: url + "/aci/workPlatform/getUserInfoByAccessToken.do",
			dataType: "json",
			data: {
				accessToken: appData.token
			},
			success: function(dataJson) {
				console.log(dataJson);
				if(dataJson != undefined && dataJson != null && dataJson != "") {
					for(var i = 0; i < appConfig.personnel.length; i++) {
						if(appConfig.personnel[i].Code == dataJson.zwdtsw_cert_id) {
							appData.loginGuard = true;
						}
					}
					if(appData.loginGuard === true) {
						appData.userName = dataJson.zwdtsw_name;
						appData.idCardNumber = dataJson.zwdtsw_cert_id;
						if(appData.isStaff === true) {
							$location.path("/staffOperationChoice");
						} else if(appData.isStaff === false) {
							$location.path("/scanCode");
						}
					} else {
						alert("请使用工作人员身份信息登录！");
					}
				} else {}
			},
			error: function(err) {
				alert("身份信息获取失败！");
			}
		});
	}
	//获取token ------2、比对成功后，根据tokenSNO获取access_token
	$scope.getAccessToken = function(tokenSNO) {
		var rec = $.ajax({
			url: url + "/aci/workPlatform/getAccessToken.do",
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
					alert('token获取失败');
				}
			},
			error: function(err) {
				alert('token获取失败');
			},
		})
	}

	$scope.scanQrCode = function() {
		$.device.qrCodeOpen(function(code) {
			if(code == "") {
				$scope.scanQrCode();
				return;
			}
			var __code = code.replace(/[\r\n]/g, "");
			$.post(url + "/selfapi/loginService/getTokenSNOByQrCode.do", {
					certQrCode: __code,
					machineId: $.config.get('uniqueId')
				},
				function(data) {
					console.log(data);
					var dataJonsp = data.data;
					if(dataJonsp != null && dataJonsp != undefined && dataJonsp.encrypted == true) {
						$scope.getAccessToken(dataJonsp.biz_response.tokenSNO);
					} else {
						alert('tokenSNO获取失败');
					}
				}, "json").success(function() {

			}).error(function() {
				alert("登录失败，请重试！");
			});
			$.device.qrCodeClose();
			$scope.$apply();
		})
	};
	$scope.loginTypeTab = function(type) {
		if(type === "idCard") {
			$scope.btnInfo = "身份证登录";
			$scope.loginType = "citizenCloud";
			$scope.scanQrCode();
			$.device.idCardClose();
			return;
		}
		$scope.btnInfo = "市民云登录";
		$scope.loginType = "idCard";
		$scope.idCardOpen();
		$.device.qrCodeClose()
	};
	/**
	 * 身份证读取 存储身份证编号
	 *
	 * */
	$scope.goHome = function() {
		$.device.Camera_Hide();
		$location.path("/main");
	};
	$scope.idCardOpen = function() {
		$.device.idCardOpen(function(value) {
			var list = JSON.parse(value);
			var Name = list.Name;
			var Code = list.Number;
			if(list) {
				for(var i = 0; i < appConfig.personnel.length; i++) {
					if(appConfig.personnel[i].Code == Code) {
						appData.loginGuard = true;
					}
				}
				if(appData.loginGuard === true) {
					appData.userName = list.Name;
					appData.idCardNumber = list.Number;
					appData.idCardImg = list.CardImagePath;
					$scope.getImgbaseStr(
						appData.idCardNumber,
						appData.userName,
						appData.idCardImg
					);
					$location.path("/staffOperationChoice");
				} else {
					alert("请使用工作人员身份信息登录！");
				}
			}
			$scope.$apply();

		});

	};
	//获取上传的身份证base64
	$scope.getImgbaseStr = function(idCardNo, name, imgUrl) {
		$.ajax({
			type: "post",
			url: appConfig.preUrl + "/aci/autoterminal/archives/getLicenseStuffList.do",
			data: {
				stIdNo: idCardNo,
				type: 0,
				baseType: "WITNESS_CONTRAST"
			},
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			success: function(dataJsonp) {
				if(dataJsonp.length > 0) {
					$scope.ImageStr = dataJsonp[0].imageStr;
					appData.ImageUrlStr = $scope.ImageStr;
					$.log.debug("$scope.ImageStr");
					return;
				} else {
					$scope.uploadIdCardImg(idCardNo, name, imgUrl);
				}
			},
			error: function() {}
		});
	};

	//上传身份证
	$scope.uploadIdCardImg = function(idCardNo, name, imgUrl) {
		var jsonData = {
			type: "0",
			stShareCode: "WITNESS_CONTRAST",
			stName: name,
			stIdNo: idCardNo
		};
		$.device.httpUpload(
			appConfig.preUrl + "/aci/autoterminal/archives/saveLicenseStuff.do",
			"FileData",
			imgUrl,
			JSON.stringify(jsonData),
			function(result) {
				$scope.getImgbaseStr(idCardNo, name, imgUrl);
			},
			function(webexception) {
				layer.msg("网络异常");
			}
		);
	};
	$scope.idCardOpen();
});
/*gongzuorenyuancaozuo*/
app.controller("staffOperationChoiceController", function() {
	$.device.qrCodeClose();
});
/**
 * 证照列表
 */
app.controller("licenseInfoListController", function($scope, $http, appData, $location, appConfig) {
	if(appData.loginGuard === false || appData.loginGuard === undefined) {
		alert("请先登录!");
		$location.path("/main");
		return;
	}
	$scope.hasData = false;
	$scope.isLoding = true;
	$scope.licenseList = [];
	var sConfig = {
		ST_CERT_TYPE: 0,
		ST_CERT_IFICATION_TYPE: 1,
		jsonpCallback: "JSON_CALLBACK"
	}
	$http.jsonp(urlHost + 'queryCertIficationInfo.do', {
		params: sConfig
	}).success(function(res) {
		console.log(res);
		$.log.debug("login :" + JSON.stringify(res));
		$.log.debug("login :" + appData.idCardNumber);
		$scope.isLoding = false;
		if(res.length >= 1) {
			appData.licenseList = res;
			if(res.length > 8) {
				//超过八条数据进行分页
				appData.licenseList = EXTPaging({
					data: res,
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
			$.log.debug("login:" + JSON.stringify($scope.licenseList));
		} else {
			console.log(res);
			$scope.hasData = true;

			console.log("登录失败！");
		}
	}).error(function(err) {
		$scope.hasData = true;
		$scope.isLoding = false;
		$.log.debug("login error:" + err);
	});
	//$scope.bsnumber = appData.qrCodeInfo.stApplyNo;
	$scope.next = function() {
		if($scope.currentPage < $scope.totalPages) {
			$scope.currentPage++;
			$scope.licenseList = appData.licenseList[$scope.currentPage - 1];
		}
	};
	$scope.back = function() {
		$location.path("/staffOperationChoice");
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
	$scope.openBox = function(no, num, applyNo) {
		$http.jsonp(
			urlHost + "addCertIficationInfo.do", {
				params: {
					ST_CERT_IFICATION_ID: no,
					ST_WORK_USER_NAME: appData.userName,
					ST_WORK_CERT_NO: appData.idCardNumber,
					ST_CERT_IFICATION_NO: num,
					ST_CERT_IFICATION_TYPE: "0",
					ST_CERT_EQUIPMENT_NO: "A",
					BL_IMAGE: "",
					jsonpCallback: "JSON_CALLBACK"
				}
			}).success(function(res) {
			var routerName = $location.path();
			$location.path("/openBox").search({
				address: routerName,
				number: num,
				caseId: applyNo,
				receiveCode: res.stVerificationCode || "",
				tips: "取出证照",
				clearInterval: true
			});
		}).error(function(err) {
			reqCount--;
			if(reqCount > 1) {
				$scope.openBox(no, num, applyNo);
			}
		});
		$.log.debug($location.path());
	};
});
/**
 * 材料列表
 */
app.controller("materialInfoListController", function($scope, $http, appData, appConfig, $location, $timeout) {
	if(appData.loginGuard === false || appData.loginGuard === undefined) {
		alert("请先登录!");
		$location.path("/main");
		return;
	}
	$scope.cabinetNo = null;
	$scope.hasData = false;
	$scope.isLoding = true;
	$scope.licenseList = [];
	$scope.staffLogin = function() {
		var sConfig = {
			ST_CERT_TYPE: 1,
			ST_CERT_IFICATION_TYPE: 1,
			jsonpCallback: "JSON_CALLBACK"
		}
		$http.jsonp(urlHost + 'queryCertIficationInfo.do', {
			params: sConfig
		}).success(function(res) {
			console.log(res);
			$.log.debug("login :" + JSON.stringify(res));
			$.log.debug("login :" + appData.idCardNumber);
			$scope.isLoding = false;
			if(res.length >= 1) {
				appData.licenseList = res;
				if(res.length > 8) {
					//超过八条数据进行分页
					appData.licenseList = EXTPaging({
						data: res,
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
		}).error(function(err) {
			$scope.hasData = true;
			$scope.isLoding = false;
			$.log.debug("login error:" + err);
		});
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
	// fairy更新num参数，待测
	$scope.openBox = function(id, num) {
		$http.jsonp(
			urlHost + "addCertIficationInfo.do", {
				params: {
					ST_CERT_IFICATION_ID: id,
					ST_WORK_USER_NAME: appData.userName,
					ST_WORK_CERT_NO: appData.idCardNumber,
					ST_CERT_IFICATION_NO: num,
					ST_CERT_IFICATION_TYPE: "0",
					ST_CERT_EQUIPMENT_NO: "A",
					BL_IMAGE: "",
					jsonpCallback: "JSON_CALLBACK"
				}
			}).success(function(res) {
			var routerName = $location.path();
			$location.path("/openBox").search({
				number: num,
				caseId: "",
				receiveCode: res.stVerificationCode || "",
				address: routerName,
				tips: "",
				clearInterval: true
			});
		}).error(function(err) {
			reqCount--;
			if(reqCount > 1) {
				$scope.openBox(id, num);
			}
		});
		$.log.debug($location.path());
	};
});
/**
 * 扫描二维码
 */
app.controller("scanCodeController", function($scope, $http, appData, $location, appConfig, $interval) {
	/**
	 * 扫描二维码操作  将获取的信息传入下面路由中参数  code
	 */
	$scope.$on("$locationChangeStart", function(event, toState, toParams, fromState, fromParams) { //路由变化清除计时器
		$interval.cancel($scope.Interval);
	});
	$scope.countDown = 60;
	$.log.debug($scope.whereFrom);
	// stuff路径操作取证时清除倒计时
	if($scope.clearInterval) {
		$interval.cancel($scope.Interval);
	} else {
		$scope.Interval = $interval(function() {
			$scope.countDown--;
			if($scope.countDown < 1) {
				$interval.cancel($scope.Interval);
				$location.path("/main").search({});
			}
		}, 1000);
	}
	//	$scope.scanCode = function() {
	//		setTimeout(function(){
	//			$location.path("/licenseInputCode");
	//		},2000)
	//	}
	//$scope.isManual = true;
	$scope.isManual = false;
	$scope.stuff = false;
	$scope.reScan = false;
	$scope.isStaff = appData.isStaff;
	$scope.matterInfo = null;
	$scope.operation = "扫描条形码";
	$scope.btnInfo = "扫二维码";
	$scope.matterName = "收件凭证";
	$scope.formPrevRouter = "main";
	$scope.isCodeInfo = false; //用来接收扫描数据

	if(!appData.isStaff) {
		$scope.module = "办事人员操作";
		if(appData.matterType == "license") {
			$scope.whoOperation = "自助取证";
		} else {
			$scope.whoOperation = "材料放置";
		}
	}

	$scope.goPrevRoute = function() {
		$location.path("/" + $scope.formPrevRouter);
	}
	$scope.returnHome = function() {
		$location.path("/main");
	}
	$scope.scanCode = function() {
		$.device.qrCodeOpen(function(code) {
			var _code = code.trim().replace(/\u0000/g, '');
			console.log(_code.length);
			if(_code.length != 15 && _code.length != 11 && _code.length != 13) {
				alert("请用正确二维码扫描!");
				return;
			}
			appData.qrCodeInfo = {
				"stUserName": "",
				"stCertNo": "",
				"stApplyNo": _code,
				"stMobile": "",
				"modelType": "10",
				"modelName": "其他部门"
			}
			if(appData.isStaff === true) {
				// 工作人员放证
				if(_code.length == 15) {
					$location.path("/putMaterialConfirm");
				} else {
					$location.path("/staffComfirm").search({
						numCode: _code || "",
					});
				}
				$scope.$apply();
				$.device.qrCodeClose();
			} else if(appData.matterType == "license") {
				$location.path("/licenseInputCode");
				$scope.$apply();
				$.device.qrCodeClose();
			} else {
				$location.path("/putMaterialConfirm");
				$scope.$apply();
				$.device.qrCodeClose();
			}
		});
	};
	console.log(appData.qrCodeInfo);
	if(appData.resideQuantity < 1) {
		alert("暂无可使用柜子！");
		$location.path("/main");
		return;
	}
	if(appData.isStaff === true) {
		if(appData.loginGuard === false || appData.loginGuard === undefined) {
			alert("请先登录!");
			$location.path("/main");
			return;
		}

		$scope.isManual = false;
		$scope.stuff = true;
		$scope.btnInfo = "手动输入";
		$scope.operation = "扫描条形码";
		$scope.module = "工作人员操作";
		$scope.whoOperation = "自助放证";
		$scope.matterName = "收件凭证";
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
		$scope.operation = "输入编码";
		if($scope.isManual === false) {
			$.device.qrCodeClose();
			$scope.reScan = false;
		} else if($scope.isManual === true) {
			$scope.operation = "扫描条形码";
			$scope.reScan = true;
			$scope.scanCode();
		}
		$scope.isManual = !$scope.isManual;
	};
	$scope.reScan = function() {
		$scope.scanCode();
	};
	$scope.confirmCode = function() {
		if($scope.numCode.length != 11 && $scope.numCode.length != 15 && $scope.numCode.length != 13) {
			alert("办事编码有误!");
			return;
		}
		appData.qrCodeInfo = {
			"stUserName": "",
			"stCertNo": "",
			"stApplyNo": $scope.numCode,
			"stMobile": "",
			"modelType": "10",
			"modelName": "其他部门"
		}
		if(appData.isStaff === true) {
			// 工作人员放证
			if($scope.numCode.length == 15) {
				$location.path("/putMaterialConfirm");
			} else {
				$location.path("/staffComfirm").search({
					numCode: $scope.numCode || "",
				});
			}
		} else if(appData.matterType == "license") {
			$location.path("/licenseInputCode");
		} else {
			$location.path("/putMaterialConfirm");
		}
	};
	if(appData.isStaff === true) {
		$scope.scanCode();
	}
	if($scope.stuff === false) {
		$scope.scanCode();
	}
});
app.controller("staffComfirmController", function($scope, $http, appData, appConfig, $location, $timeout) {
	$scope.isLoding = false;
	$scope.show = false;
	$scope.goNext = false; // 打开查询还是手动输入电话号码的下一步
	$scope.numCode = ''; // 存储手机号
	$.device.qrCodeClose();
	appData.backFor = "1";
	$scope.organ = "输入手机号码";
	$scope.idCard = appData.idCardNumber;
	// 接受上个页面传过来的凭证编码,下边getCaseInfo用
	$scope.itemCode = $location.search().numCode;
	// 查询办事人相关信息
	$scope.getCaseInfo = function() {
		$scope.isLoding = true;
		$.ajax({
			type: "get",
			url: "http://10.89.5.226:8080/api/cabinet/genCaseInfo",
			async: true,
			dataType: "json",
			data: {
				caseId: $scope.itemCode,
			},
			success: function(dataJson) {
				$scope.isLoding = false;
				$scope.show = true;
				if(dataJson.success) {
					$scope.numCode = dataJson.obj.RELATED_MOBILE;
					if($scope.numCode.length == 11) {
						$scope.goNext = true;
						appData.relatedName = dataJson.obj.RELATED_PERSONNAME;
					} else {
						alert("手机号格式不正确，请手动输入")
					}
					$scope.$apply();
				} else {
					alert("获取手机号失败,请手动输入")
				}
			},
			error: function(err) {
				alert($scope.itemCode + "办件未查询到,请手动输入手机号")
				$scope.isLoding = false;
				$scope.show = true;
				$.log.debug("err number" + err);
			}
		});
	}
	if($scope.itemCode.length == 13) {
		$scope.getCaseInfo();
	} else {
		$scope.show = true;
	}
	$scope.inputCode = function(val) {
		$scope.numCode.length < 12 ? ($scope.numCode += val) : "";
	};
	$scope.deleteCode = function() {
		$scope.numCode = $scope.numCode.slice(0, $scope.numCode.length - 1);
	};
	$scope.back = function() {
		appData.backFor = "";
		$location.path("/staffOperationChoice");
	};
	var i = 0;
	$scope.goBack = function() {
		i = 0;
		$scope.numCode = "";
		document.getElementById("mobile").placeholder = "请输入手机号"
		$location.path("/staffComfirm");
	}
	$scope.serialSearch = function() {
		if(!$scope.goNext) {
			$scope.mobile = $('#mobile').val();
			i = i + 1;
			var condFlag = false;
			do {
				if(!isPhoneAvailable($('#mobile').val())) {
					i = i - 1;
					alert("请输入正确的手机号");
					return;
				}
			} while (condFlag);
			if(i == 1) {
				$scope.mobileOne = $('#mobile').val();
				console.log(i);
				$scope.numCode = "";
				document.getElementById("mobile").placeholder = "请再输入一遍!"
			} else if(i == 2) {
				$scope.mobileTwo = $('#mobile').val();
				if($scope.mobileTwo == $scope.mobileOne) {
					appData.mobileOne = $scope.mobileOne;
					$location.path("/putMaterialConfirm");
				} else {
					i = i - 1;
					alert("两次输入不一致，第一次为：" + $scope.mobileOne);
					$location.path("/staffComfirm");
				}
			}
		} else {
			appData.mobileOne = $scope.numCode;
			$location.path("/putMaterialConfirm");
		}
	}
});

/**
 * 输入手机号
 */
app.controller("inputPhoneController", function($scope, $http, appData, $location, appConfig) {
	/**
	 * 扫描二维码操作  将获取的信息传入下面路由中参数  code
	 */
	if(appData.qrCodeInfo.stMobile !== "") {
		$scope.numCode = appData.qrCodeInfo.stMobile;
	} else {
		$scope.numCode = "";
	}
	$scope.isManual = false;
	$scope.isStaff = appData.isStaff;
	$scope.matterInfo = null;
	$scope.module = "工作人员操作";
	$scope.whoOperation = "证照放入";
	$scope.operation = "手机号";
	$scope.formPrevRouter = "main";
	$scope.isCodeInfo = false; //用来接收扫描数据
	$scope.goPrevRoute = function() {
		$location.path("/" + $scope.formPrevRouter);
	}
	if(appData.resideQuantity < 1) {
		alert("暂无可使用柜子！");
		$location.path("/main");
		return;
	}
	if(appData.isStaff === true) {
		if(appData.loginGuard === false || appData.loginGuard === undefined) {
			alert("请先登录!");
			$location.path("/main");
			return;
		}
		$scope.isManual = false;
		$scope.module = "工作人员操作";
		$scope.whoOperation = "自助放证";
		$scope.matterName = "送达回证";
		$scope.formPrevRouter = "staffOperationChoice";
	}
	$scope.inputCode = function(val) {
		$scope.numCode.length < 9 ? ($scope.numCode += val) : "";
	};
	$scope.deleteCode = function() {
		$scope.numCode = $scope.numCode.slice(0, $scope.numCode.length - 1);
	};

	$scope.confirmCode = function() {
		if($scope.numCode.length < 11) {
			alert("请输入正确手机号!");
			return;
		}
		appData.phoneNum = $scope.numCode;
		$location.path("/putMaterialConfirm");
	};
});
/**
 * 信息确认
 */
app.controller("putMaterialConfirmController", function($scope, $http, appData, $location, appConfig, $timeout) {
	//证照信息确认
	$scope.matterType = "办事";
	$scope._matterInfo = "办件";
	if(appData.isStaff === true) {
		$scope._matterInfo = "证照";
		$scope.matterType = "工作";
		$scope.goBack = "staffOperationChoice";
	} else {
		$scope.goBack = "main";
	}
	$scope.stCertFlowId = null;
	$scope.cabList = [];
	$scope.goHome = function() {
		$location.path("/main");
	};
	var reqCount = 10;

	$scope.$on("$viewContentLoaded", function(event, toState, toParams, fromState, fromParams) {
		$scope.isShow = true;
		$scope.isLoding = false;
		$scope.qrCode = appData.qrCodeInfo;
		$scope.phone = appData.mobileOne;
		$scope.relatedName = appData.relatedName || '';
	});
	/**
	 * 这里寻找空闲的证照柜柜子
	 * 生成一个可用柜子数组
	 * 随机选取一个空闲柜子
	 */
	$scope.Continue = function() {
		$scope.isLoding = true;
		$scope.isShow = false;
		var stCabinetNo =
			appConfig.initSark[parseInt(Math.random() * appConfig.initSark.length, 10)];

		var arr = appConfig.sark;
		var index = arr.indexOf(stCabinetNo);
		while(index != -1) {
			stCabinetNo =
				appConfig.initSark[parseInt(Math.random() * appConfig.initSark.length, 10)];
			index = arr.indexOf(stCabinetNo);
		}

		//alert("已使用的柜子"+appConfig.sark);
		//for(var i=0;i<appConfig.sark.length;i++){
		//alert(appConfig.sark[i]+"====>随机到的柜号"+stCabinetNo);
		//while(appConfig.sark[i]==stCabinetNo){
		//stCabinetNo =
		//appConfig.initSark[parseInt(Math.random() * appConfig.initSark.length, 10)];
		//}
		//}
		$.log.debug("stCabinetNo: " + stCabinetNo);
		stCabinetNo = $scope.yetUseCabinet || stCabinetNo; //存在原有的柜子号则打开原有的 否则打开新生成的柜子号
		appConfig.http(
			"addCertIficationInfo.do", {
				//办事人员放入证照
				//ST_MOBILE:appData.mobileOne,
				//ST_CERT_IFICATION_ID:stCabinetNo,
				ST_APPLY_NO: $scope.qrCode.stApplyNo,
				ST_USER_NAME: $scope.qrCode.stUserName,
				ST_MOBILE: $scope.qrCode.stMobile || appData.mobileOne || "",
				ST_VERIFICATION_CODE: "",
				ST_CERT_NO: $scope.qrCode.stCertNo,
				ST_CERT_IFICATION_NO: stCabinetNo,
				ST_CERT_CONTENT: (appData.isStaff == true) ? "证照" : "材料",
				ST_CERT_TYPE: (appData.isStaff == true) ? "0" : "1",
				ST_CERT_IFICATION_TYPE: "1",
				ST_MODEL_TYPE: $scope.qrCode.modelType,
				ST_MODEL_NAME: $scope.qrCode.modelName,
				ST_CERT_EQUIPMENT_NO: "A",
				ST_WORK_CERT_NO: appData.idCardNumber,
				ST_WORK_USER_NAME: appData.userName
			},
			function(res) {
				$scope.isLoding = false;
				$location.path("/openBox").search({
					number: stCabinetNo,
					caseId: $scope.qrCode.stApplyNo,
					receiveCode: res.stVerificationCode || "",
					tips: "放入证照",
					clearInterval: true
				});
			},
			function(err) {
				$.log.debug(err);
			}
		);
	};
	$scope.reScan = function() {
		$location.path("/scanCode");
	};
});

/**
 * 控制中心
 * */
app.controller("cabinetControllerController", function($scope, $http, appData, appConfig, $timeout, $location, $interval, $rootScope) {
	if(appData.loginGuard === false || appData.loginGuard === undefined) {
		alert("请先登录!");
		$location.path("/main");
		return;
	}
	$scope.totalCabinet = appConfig.initSark;
	$scope.usedCabinet = [];
	$scope.mask = false;
	$scope.currentPath = $location.path();
	Array.prototype.hasCongruentStr = function(str) {
		var _str = str;
		if(this.indexOf(str) > -1) {
			for(var index = 0; index < this.length; index++) {
				if(this[index] === _str) {
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	};
	$scope.openCabinet = function(no) {
		var layer = confirm("是否打开" + no + "号柜子?");
		if(layer === true) {
			$http.jsonp(
				urlHost + "addCertIficationInfo.do", {
					params: {
						ST_CERT_IFICATION_ID: no,
						ST_WORK_USER_NAME: appData.userName,
						ST_WORK_CERT_NO: appData.idCardNumber,
						ST_CERT_IFICATION_NO: no,
						ST_CERT_IFICATION_TYPE: "0",
						ST_CERT_EQUIPMENT_NO: "A",
						BL_IMAGE: "",
						jsonpCallback: "JSON_CALLBACK"
					}
				}).success(function(res) {
				$location.path("/openBox").search({
					number: no,
					caseId: "",
					receiveCode: "",
					address: $scope.currentPath,
					clearInterval: true
				});
			}).error(function(err) {
				alert("开柜失败");
			})
		}
	};
})
/**
 * 开箱
 */
app.controller("openBoxController", function($scope, $http, appData, appConfig, $timeout, $location, $interval, $rootScope) {
	$scope.$on("$locationChangeStart", function(event, toState, toParams, fromState, fromParams) { //路由变化清除计时器
		$interval.cancel($scope.Interval);
	});
	//开柜号
	$scope.Number = $location.search().number || null;
	// 界面展示  取件还是放件的区分
	$scope.tipsInfo = $location.search().tips || "";
	// 针对客户取证之后的提示信息
	$scope.isUserTake = $location.search().userFlag || false;
	// 针对界面60秒展示与否
	$scope.clearInterval = $location.search().clearInterval || false;
	$scope.whereFrom = $location.search().address;
	// 办件凭证编码
	$scope.caseId = $location.search().caseId;
	// 办件的取件码
	$scope.receiveCode = $location.search().receiveCode;
	appData.backFor1 = "1";
	$scope.receiveStatus = 0; // 为后边的第三方接口准备参数
	$scope.isStaff = appData.isStaff;
	// 主界面小房子的点击回到相应的主界面
	if(appData.isStaff) {
		$interval.cancel($scope.Interval);
		$scope.goBack = "staffOperationChoice";
	} else {
		$interval.cancel($scope.Interval);
		$scope.goBack = "main";
	}
	$scope.countDown = 60;
	$.log.debug($scope.whereFrom);
	// stuff路径操作取证时清除倒计时
	if($scope.clearInterval) {
		$interval.cancel($scope.Interval);
	} else {
		$scope.Interval = $interval(function() {
			$scope.countDown--;
			if($scope.countDown < 1) {
				$interval.cancel($scope.Interval);
				$location.path("/main").search({});
			}
		}, 1000);
	}

	// 调用放证接口
	$scope.putInfo = function() {
		$.ajax({
			type: "get",
			url: "http://10.89.5.226:8080/api/cabinet/syncCabinet",
			async: true,
			dataType: "json",
			data: {
				caseId: $scope.caseId,
				receiveAddr: "上海临港新片区管理委员会",
				receiveCode: $scope.receiveCode,
				boxNo: $scope.Number,
			},
			success: function(res) {
				if(res.success) {
					$.log.debug("信息同步成功");
				} else {
					$.log.debug("信息同步失败");
				}

			},
			error: function(err) {
				$.log.debug("err number" + err);
			}
		});
	};
	// 调用取件接口
	$scope.takeInfo = function(status) {
		$.ajax({
			type: "get",
			url: "http://10.89.5.226:8080/api/cabinet/syncReceiveStatus",
			async: true,
			dataType: "json",
			data: {
				caseId: $scope.caseId,
				receiveStatus: status, // 1：办事人员 2：工作人员
			},
			success: function(res) {
				if(res.success) {
					$.log.debug("信息同步成功");
				} else {
					$.log.debug("信息同步失败");
				}
			},
			error: function(err) {
				$.log.debug("err number" + err);
			}
		});
	}

	// 判断是取件还是放件调用不同的接口
	if($scope.tipsInfo == "放入证照") {
		$scope.putInfo();
	} else if($scope.tipsInfo == "取出证照") {
		if(appData.isStaff) {
			$scope.receiveStatus = 2;
		} else {
			$scope.receiveStatus = 1;
		}
		$scope.takeInfo($scope.receiveStatus);
	}

	$scope.Continue = function() {
		$interval.cancel($scope.Interval);
		//$location.path("/scanCode");
		if(appData.backFor1 == "1") {
			$location.path("/scanCode");
		}
		$location.path($scope.whereFrom);
	};
	$scope.goHome = function() {
		$interval.cancel($scope.Interval);
		$location.path("/main").search({});
	};
	$scope.otherHandle = function() {
		appData.backFor1 = "";
		$interval.cancel($scope.Interval);
		$location.path("/staffOperationChoice");
	};
	var Count = 20;
	$scope.getResideQuantity = function() {
		$.ajax({
			type: "get",
			url: appConfig.httpUrl + "queryCertIficationInfo.do",
			async: true,
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				ST_CERT_EQUIPMENT_NO: "A",
				ST_CERT_IFICATION_TYPE: 1
			},
			success: function(res) {
				appData.resideQuantity = 60 - res.length;
				console.log(appData.resideQuantity)
				$.log.debug("number" + appData.resideQuantity);
				$.log.debug(JSON.stringify(res));
			},
			error: function(err) {
				$.log.debug("err number" + err);
			}
		});
	};
	$scope.getResideQuantity();
	if(appData.resideQuantity < 1) {
		alert("柜子已满！");
	}
	/*重置柜子*/
	//appConfig.sark = [].concat(appConfig.initSark);
	appConfig.sark.push($scope.Number);
	console.log($scope.Number);
	/**
	 * 这里执行开柜方法  $scope.Number为柜子编号
	 */
	$.device.SarkInit();
	$.device.SarkOpen($scope.Number);
});