app.controller("mainController", function(
	$rootScope,
	$scope,
	$http,
	appData,
	$location,
	appConfig,
	$state,
	$rootScope
) {
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
				//$location.path("/staffOperation").search({});
				$location.path("/staffOperationChoice");
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
			//$location.path("/staffOperationChoice");
		} else if(model === "masses") {
			appData.isStaff = false;
		}
	};
});
/**
 * 办事人员取证
 */
app.controller("licenseTakeController", function(
	$scope,
	$rootScope,
	$http,
	appConfig,
	appData,
	$timeout,
	$location,
	$q,
	$state,
	$interval
) {
	$scope.loginType = "idCard";
	$scope.btnInfo = "市民云亮证";
	$scope.title = "自助取证";
	$scope.isReadOver = false;
	$scope.logging = true;
	$scope.loginErrorType = "正在登陆...";
	$scope.codeString = null;
	/*test*/
	//	$scope.isReadOver = true;
	//	$scope.loginType = "citizenCloud";

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
	$scope.loginRequest = function(code, isRe) {
		var _code = code.trim().replace(/\u0000/g, '');

		if(isRe != undefined) {
			$scope.logging = true;
		}
		timeLog.timeStart();
		$scope.httpConfig = {
			jsonpCallback: "JSON_CALLBACK",
			codeParam: _code
		}
		$http
			.jsonp("http://101.230.224.65:8080/ac/aci/window/getQrCodeInfoByElectronicCert.do", {
				params: $scope.httpConfig,
				timeout: 10000
			})
			.success(function(data) {
				if(data.result.success == 'false') {
					$scope.loginErrorType = data.result.msg;
					$scope.logging = false;
					return false;
				}
				loginLog.setLoginRecord({
					t: timeLog.timeEnd(),
					name: "办事人员"
				})
				$.log.debug(loginLog.getLoginRecord());
				appData.userName = data.result.data.realname;
				appData.idCardNumber = data.result.data.idcard;
				if(appData.matterType === "material") {
					$location.path("/scanCode");
				} else {
					$location.path("/licenseInputCode");
				}
			})
			.error(function(err, state) {
				$scope.logging = false;
				if(state == -1) {
					$scope.loginErrorType = "登录超时，请重新登录";
					return;
				}
				$scope.loginErrorType = "登录失败，请重新登录";
			});
	}
	$scope.scanQrCode = function() {
		$.device.qrCodeOpen(function(code) {
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
				//alert("1111111");
				if(dataJsonp.length > 0) {
					$scope.ImageStr = dataJsonp[0].imageStr;
					appData.ImageUrlStr = $scope.ImageStr;
					//alert($scope.ImageStr);
					$.log.debug("$scope.ImageStr");
					return;
				} else {
					$scope.uploadIdCardImg(idCardNo, name, imgUrl);
				}
			},
			error: function() {}
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
				//alert(appData.userName+appData.idCardNumber+appData.idCardImg);
				$scope.getImgbaseStr(
					appData.idCardNumber,
					appData.userName,
					appData.idCardImg
				);
				$timeout(function() {
					$location.path("/faceVerification");
					$scope.$apply();
				}, 2000);
			}
			$.device.idCardClose();
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

	//每3秒调用一次检索人脸接口
	$scope.queryFace = function() {
		$.device.Camera_Init(650, 480, 0, 0); //初始化摄像头
		//$.device.Camera_Link("RGB Camera", 9); //初始化摄像头
		$.device.Camera_Link("LHT-820CH", 9);
		//$.device.Camera_Link("EasyCamera", 1); //初始化摄像头
		//$.device.Camera_Show();
		$scope.maxCountDown = 3;
		$rootScope.timer = null;
		$scope.timeCount = function() {
			$interval.cancel($rootScope.timer);
			$rootScope.timer = $interval(function() {
				console.log($scope.maxCountDown);
				$scope.maxCountDown--;
				if($scope.maxCountDown < 1) {
					$interval.cancel($rootScope.timer);
					$scope.faceImg = $.device.Camera_Base64();
					$.log.debug('图片' + $scope.faceImg);
					$.ajax({
						type: "post",
						url: "http://31.0.178.73/ucenter/yitu/queryFace.do",
						async: true,
						data: {
							image: $scope.faceImg,
							width: "650"
						},
						success: function(data) {
							$.log.debug('返回结果' + data);
							var data = JSON.parse(data);
							console.log(data.isSuccess);
							if(data.isSuccess === true) {
								appData.userName = data.data.name;
								appData.idCardNumber = data.data.person_id;
								appData.mobile = data.data.phone;
								$.device.Camera_Hide();
								$location.path("/personInfo");
							} else {
								$scope.resetCountDown();
							}
						},
						error: function(err) {
							$scope.resetCountDown();
							$.log.debug('queryFace' + JSON.stringify(err));
						}
					});
				}
			}, 1000)
		};
		$scope.timeCount();
		$scope.resetCountDown = function() {
			$interval.cancel($rootScope.timer);
			$timeout(function() {
				$scope.maxCountDown = 3;
			});
			$timeout(function() {
				$scope.timeCount();
			}, 1000);
		};
	}
	//$scope.queryFace();
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
	//215 715
	$scope.isCheckAgain = $location.search().reVerfication;
	$.device.Camera_Init(650, 480, 615, 385); //初始化摄像头
	//$.device.Camera_Link("RGB Camera", 6); //初始化摄像头
	$.device.Camera_Link("Eyecool ECF271 Col", 6);
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
	$scope.recognition = function() { //人证数据对比
		$.ajax({
			url: "http://hengshui.5uban.com/ac-product-ext/ext/aci/autoterminal/facecompare.do",
			type: "post",
			dataType: "json",
			data: {
				idCardPhoto: appData.ImageUrlStr,
				capturePhoto: appData.capturePhoto
			},
			success: function(res) {
				var n = res.similarity;
				$scope.recognitionOver = true;
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
				$.log.debug("err:" + JSON.stringify(err));
			}
		})
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
	$.device.Camera_UnLink();
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
app.controller("licenseInputCodeController", function(
	$scope,
	$http,
	appData,
	$location,
	appConfig
) {
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
		if($scope.numCode.length < 6) {
			alert("请输入六位取证码!");
			return;
		}
		appConfig.http(
			"getCabinetNo.do", {
				stReceiveNum: $scope.numCode,
				stIdentityNo: appData.idCardNumber
			},
			function(res) {
				//$.log.debug(JSON.stringify(res));
				if(!res.stCertFlowId) {
					alert("该取证码没有可取证照!");
				} else {
					if(res.nmIsAgentUser == 'true') {
						appData.massesGetLicense = {
							stCabinetNo: res.stCabinetNo,
							stCertFlowId: res.stCertFlowId,
							stReceiverPhone: res.stReceiverPhone,
							stCertName: res.stCertName,
							stName: res.stName,
							stCertFlowId: res.stCertFlowId,
							stReceiverName: res.stReceiverName,
							imageStr: res.imageStr,
							cabList: res.cabList
						};
						$location.path("/getLicenseConfirm");
					} else {
						alert("对不起，该证照只能本人持身份证前来取证");
					}
				}

			},
			function(err) {
				console.log(err);
				if($scope.getNumber > 1) {
					$scope.getNumber--;
					$scope.confirmCode();
				}
			}
		);
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
					tips: "取出证照"
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
app.controller("staffOperationController", function(
	$scope,
	$http,
	appData,
	$location,
	appConfig,
	$state
) {
	$scope.pageClass = "fade";
	$scope.title = "工作人员登录";
	$scope.loginType = "idCard";
	$scope.btnInfo = "市民云登录";

	$scope.isReadOver = false; //读取个人数据
	$scope.logging = true; //登录中
	$scope.loginErrorType = "正在登陆..."; //错误提示
	$scope.codeString = null; //暂存扫描出来的二维码

	$scope.loginRequest = function(code, isRe) {
		var _code = code.trim().replace(/\u0000/g, '');
		if(isRe != undefined) {
			$scope.logging = true;
		}
		timeLog.timeStart();

		$scope.httpConfig = {
			codeParam: _code,
			jsonpCallback: "JSON_CALLBACK"
		};
		$http
			.jsonp("http://101.230.224.65:8080/ac/aci/window/getQrCodeInfoByElectronicCert.do", {
				params: $scope.httpConfig,
				timeout: 10000
			})
			.success(function(data) {
				if(data.result.success == 'false') {
					$scope.loginErrorType = data.result.msg;
					$scope.logging = false;
					return false;
				}
				var idcardNumber = data.result.data.idcard;
				loginLog.setLoginRecord({
					t: timeLog.timeEnd(),
					name: "工作人员登录"
				})
				$.log.debug(loginLog.getLoginRecord());

				appConfig.http("isStaff.do", {
					"stIdentityNo": idcardNumber
				}, function(res) {

					if(res.result == "true") {
						appData.loginGuard = true;
						appData.userName = data.result.data.realname;
						appData.idCardNumber = idcardNumber;
						$location.path("/staffOperationChoice");
					} else {
						alert("请用工作人员信息登录！");
						$state.reload();
					}
				}, function(err) {

				})

			})
			.error(function(err, status) {
				$scope.logging = false;
				if(status == -1) {
					$scope.loginErrorType = "登录超时,请重新登录";
					return;
				}
				$scope.loginErrorType = "登录失败,请重新登录";

			});
	}
	$scope.scanQrCode = function() {
		$.device.qrCodeOpen(function(code) {
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
				appConfig.http("isStaff.do", {
					"stIdentityNo": Code
				}, function(res) {
					if(res.result == "true") {
						appData.loginGuard = true;
						appData.userName = list.Name;
						appData.idCardNumber = list.Number;
						appData.idCardImg = list.CardImagePath;
						$scope.getImgbaseStr(
							appData.idCardNumber,
							appData.userName,
							appData.idCardImg
						);
						$location.path("/faceVerification");
					} else {
						alert("请用工作人员信息登录！");
						$state.reload();
					}
				})
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
/*gongzuorenyuancaozuo */
app.controller("staffOperationChoiceController", function() {

	$.device.qrCodeClose();
});
/**
 * 证照列表
 */
app.controller("licenseInfoListController", function(
	$scope,
	$http,
	appData,
	$location,
	appConfig
) {
	//	if(appData.loginGuard === false || appData.loginGuard === undefined) {
	//		alert("请先登录!");
	//		$location.path("/main");
	//		return;
	//	}
	$scope.hasData = false;
	$scope.isLoding = true;
	$scope.licenseList = [];
	appConfig.http(
		"login.do", {
			stIdentityNo: appData.idCardNumber
		},
		function(res) {
			$scope.isLoding = false;
			if(res.overDueList.length >= 1) {
				appData.licenseList = res.overDueList;
				if(res.overDueList.length > 8) {
					//超过八条数据进行分页
					appData.licenseList = EXTPaging({
						data: res.overDueList,
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
	$scope.openBox = function(serialNum, id) {
		appConfig.http(
			"getCert.do", {
				isOpen: "true",
				stCertFlowId: id,
				isStuff: "1",
				stuffName: encodeURI(appData.userName)
			},
			function(res) {
				$.log.debug("license: " + res);
				var routerName = $location.path();
				$location.path("/openBox").search({
					address: routerName,
					number: serialNum,
					tips: "取出证照"
				});
			},
			function(err) {
				reqCount--;
				if(reqCount > 1) {
					$scope.openBox(serialNum, id);
				}
			}
		);
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
app.controller("materialInfoListController", function(
	$scope,
	$http,
	appData,
	appConfig,
	$location,
	$timeout
) {
	//	if(appData.loginGuard === false || appData.loginGuard === undefined) {
	//		alert("请先登录!");
	//		$location.path("/main");
	//		return;
	//	}
	$scope.cabinetNo = null;
	$scope.hasData = false;
	$scope.isLoding = true;
	$scope.licenseList = [];
	$scope.staffLogin = function() {
		appConfig.http(
			"login.do", {
				stIdentityNo: appData.idCardNumber
			},
			function(res) {
				$.log.debug("login :" + JSON.stringify(res));

				$scope.isLoding = false;
				if(res.stuffList.length >= 1) {
					appData.licenseList = res.stuffList;
					if(res.stuffList.length > 8) {
						//超过八条数据进行分页
						appData.licenseList = EXTPaging({
							data: res.stuffList,
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
		appConfig.http(
			"getStuff.do", {
				isOpen: "true",
				stSelfApplyId: id
			},
			function() {
				var routerName = $location.path();
				$location.path("/openBox").search({
					number: No,
					address: routerName,
					tips: "取出材料"
				});
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
	$scope.operation = "二维码";
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
		if($scope.numCode.length < 15) {
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
 * 信息确认
 */
app.controller("putMaterialConfirmController", function(
	$scope,
	$http,
	appData,
	$location,
	appConfig,
	$timeout
) {

	//证照信息确认
	$timeout(function() {
		$scope.isShow = undefined;
		$scope.isLoding = false;
	}, 0)

	$scope.qrCode = appData.qrCodeInfo; //获取二维码扫描编码
	$scope.matterType = "办事";
	$scope._matterInfo = "材料";
	if(appData.isStaff === true) {
		$scope._matterInfo = "证照";
		$scope.matterType = "工作";
	}
	$scope.stCertFlowId = null;
	$scope.cabList = [];
	$scope.getMethod =
		appData.isStaff === true ? "getCertInfo.do" : "getMatterInfo.do";
	$scope.goHome = function() {
		$location.path("/main");
	};
	var reqCount = 10;
	$scope.getStInfo = function() {
		//$scope.isLoding = false;
		//$scope.isShow = true;
		appConfig.http(
			$scope.getMethod, {
				stApplyNo: $scope.qrCode
			},
			function(res) {
				$scope.isLoding = false;
				//alert("判断是否走响应成功");
				console.log(JSON.stringify(res));
				$.log.debug(JSON.stringify(res));
				var resultOne = res.result;
				var resultTwo = res.err;
				if(resultOne == "false") {
					//alert("没有该办件编码信息！111111");
					$location.path("/scanCode");
				} else if(resultTwo == "false") {
					//alert("没有该办件编码信息！");
					$location.path("/scanCode");
				}
				$scope.isShow = true;
				$scope.matterInfo = res;
				$scope.stCertFlowId = res.stSelfApplyId;
				$scope.yetUseCabinet = res.cabinetNo;
				if(appData.isStaff === true) {
					$scope.stCertFlowId = res.stCertFlowId;
				}
				if($scope.matterInfo.stReceiverPhone == '') {
					alert("该证照无手机号!");
				}
				if($scope.matterInfo.stReceiverIdcard == '') {
					alert("该证照无身份证号!");
				}
				$scope.cabList = res.cabList;
				appConfig.sark.removeArr($scope.cabList);

			},
			function(err) {
				alert("没有该办件编码信息！走arr");
				$location.path("/scanCode");
				$scope.isShow = false;
				$scope.isLoding = false;
				if(reqCount > 1) {
					$scope.getStInfo();
					reqCount--;
				}

				$.log.debug("$scope.matterInfo.stCertName");
			}
		);
	};
	$scope.$on("$viewContentLoaded", function(event, toState, toParams, fromState, fromParams) {
		$scope.isShow = undefined;
		$scope.isLoding = true;
		$scope.getStInfo();
	});
	/**
	 * 这里寻找空闲的证照柜柜子
	 * 生成一个可用柜子数组
	 * 随机选取一个空闲柜子
	 */
	$scope.Continue = function() {
		var index = layer.load(1, {
			shade: [0.5, '#fff'] //0.1透明度的白色背景
		});
		var stCabinetNo =
			appConfig.sark[parseInt(Math.random() * appConfig.sark.length, 10)];
		$.log.debug("stCabinetNo: " + stCabinetNo)
		$.log.debug(JSON.stringify(appConfig.sark))
		if(appData.isStaff === true) {
			//放入材料接口
			appConfig.http(
				"putCert.do", {
					//工作人员放证照
					stCabinetNo: stCabinetNo,
					stCertFlowId: $scope.stCertFlowId
				},
				function(res) {
					layer.close(index);
					$.log.debug("工作人员放证照：" + JSON.stringify(res));
					if(res.result === "ok") {
						$.log.debug(JSON.stringify(appConfig.sark))

						$location.path("/openBox").search({
							number: stCabinetNo,
							address: "/scanCode",
							tips: "放入证照"
						});
					} else {
						alert("证照放入错误,请确认证照状态!")
					}
				}
			);
			return;
		}
		stCabinetNo = $scope.yetUseCabinet || stCabinetNo; //存在原有的柜子号则打开原有的 否则打开新生成的柜子号
		appConfig.http(
			"putMatter.do", {
				//办事人员放入材料
				stCabinetNo: stCabinetNo,
				stSelfApplyId: $scope.stCertFlowId,
				stApplyNo: $scope.matterInfo.stApplyNo,
				stSenderName: encodeURI($scope.matterInfo.stSenderName),
				stSensderIdcard: $scope.matterInfo.stSensderIdcard,
				stSenderPhone: $scope.matterInfo.stSenderPhone,
				itemName: encodeURI($scope.matterInfo.itemName),
				stName: encodeURI($scope.matterInfo.stName),
			},
			function(res) {
				layer.close(index);
				if(res.result === "ok") {
					$location.path("/openBox").search({
						number: stCabinetNo,
						tips: "放入材料"
					});
				} else {
					alert("没有查询到办件信息，请重新扫描或输入！")
				}
			},
			function(err) {
				$.log.debug(err);
			}
		);
	};
	$scope.reScan = function() {
		$location.path("/scanCode");
	};
	$scope.back = function() {
		if(appData.isStaff === true) {
			$location.path("/scanCode");
		} else {
			$location.path("/scanCode");
		}
	}
});
app.controller("putMaterialConfirmzfController", function(
	$scope,
	$http,
	appData,
	$location,
	appConfig,
	$timeout
) {

	//证照信息确认
	$timeout(function() {
		$scope.isShow = undefined;
		$scope.isLoding = true;
	}, 0)

	$scope.qrCode = appData.qrCodeInfo; //获取二维码扫描编码
	$scope.matterType = "办事";
	$scope._matterInfo = "材料";
	if(appData.isStaff === true) {
		$scope._matterInfo = "证照";
		$scope.matterType = "工作";
	}
	$scope.stCertFlowId = null;
	$scope.cabList = [];
	$scope.getMethod =
		appData.isStaff === true ? "getCertInfo.do" : "getMatterInfo.do";
	$scope.goHome = function() {
		$location.path("/main");
	};
	var reqCount = 10;
	$scope.getStInfo = function() {
		appConfig.http(
			$scope.getMethod, {
				stApplyNo: $scope.qrCode
			},
			function(res) {
				$.log.debug(JSON.stringify(res));
				var resultOne = res.result;
				var resultTwo = res.err;
				if(resultOne == "false") {
					alert("没有该办件编码信息！");
					$scope.isShow = false;
					//$location.path("/main");
				} else if(resultTwo == "false") {
					alert("没有该办件编码信息！");
					$scope.isShow = false;
					//$location.path("/main");
				} else {
					$scope.isShow = true;
					$scope.matterInfo = res;
					$scope.stCertFlowId = res.stSelfApplyId;
					$scope.yetUseCabinet = res.cabinetNo;
					if(appData.isStaff === true) {
						$scope.stCertFlowId = res.stCertFlowId;
					}

					$scope.cabList = res.cabList;
					$scope.isLoding = false;
					appConfig.sark.removeArr($scope.cabList);
				}

			},
			function(err) {
				$scope.isShow = false;
				$scope.isLoding = false;

				$.log.debug("$scope.matterInfo.stCertName");
			}
		);
	};
	$scope.$on("$viewContentLoaded", function(event, toState, toParams, fromState, fromParams) {
		$scope.isShow = undefined;
		$scope.isLoding = true;
		$scope.getStInfo();
	});
	/**
	 * 这里寻找空闲的证照柜柜子
	 * 生成一个可用柜子数组
	 * 随机选取一个空闲柜子
	 */
	$scope.Continue = function() {
		var stCabinetNo =
			appConfig.sark[parseInt(Math.random() * appConfig.sark.length, 10)];
		$.log.debug("stCabinetNo: " + stCabinetNo)
		$.log.debug(JSON.stringify(appConfig.sark))
		if(appData.isStaff === true) {
			//放入材料接口
			appConfig.http(
				"putCert.do", {
					//工作人员放证照
					stCabinetNo: stCabinetNo,
					stCertFlowId: $scope.stCertFlowId
				},
				function(res) {
					if(res.result === "ok") {
						$.log.debug(JSON.stringify(appConfig.sark))

						$location.path("/openBox").search({
							number: stCabinetNo,
							address: "/scanCode",
							tips: "放入证照"
						});
					} else {
						alert("证照放入错误,请确认证照状态!")
					}
				}
			);
			return;
		}
		stCabinetNo = $scope.yetUseCabinet || stCabinetNo; //存在原有的柜子号则打开原有的 否则打开新生成的柜子号
		appConfig.http(
			"putMatter.do", {
				//办事人员放入材料
				stCabinetNo: stCabinetNo,
				stSelfApplyId: "",
				stApplyNo: $scope.qrCode,
				stSenderName: "",
				stSensderIdcard: "",
				stSenderPhone: "",
				itemName: "",
				stName: "",
			},
			function(res) {
				if(res.result === "ok") {
					$location.path("/openBox").search({
						number: stCabinetNo,
						tips: "放入材料"
					});
				} else {
					alert("没有查询到办件信息，请重新扫描或输入！")
				}
			},
			function(err) {
				$.log.debug(err);
			}
		);
	};
	$scope.reScan = function() {
		$location.path("/scanCode");
	};
	$scope.back = function() {
		if(appData.isStaff === true) {
			$location.path("/scanCode");
		} else {
			$location.path("/scanCode");
		}
	}
});
/**
 * 控制中心
 * */
app.controller("cabinetControllerController", function($scope,
	$http,
	appData,
	appConfig,
	$timeout,
	$location,
	$interval,
	$rootScope) {
	if(appData.loginGuard === false || appData.loginGuard === undefined) {
		alert("请先登录!");
		$location.path("/main");
		return;
	}
	$scope.totalCabinet = appConfig.initSark;
	$scope.usedCabinet = [];
	$scope.matterInfo = [];
	$scope.currentMatter = null;
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
	$scope.getMatterInfo = function() {
		appConfig.http(
			"login.do", {
				stIdentityNo: appData.idCardNumber
			},
			function(res) {
				console.log(res)
				if(res.msg === "success") {
					$scope.usedCabinet = $scope.usedCabinet.concat(res.cabList);
					$scope.matterInfo = $scope.matterInfo.concat(res.overDueList, res.stuffList);
				} else {
					console.log("登录失败！");
				}
			},
			function(err) {
				console.log("err:" + errr);
			}
		);
	}
	$scope.confirmOpen = function() {
		$location.path("/openBox").search({
			number: $scope.currentMatter.cabinetNo,
			address: $scope.currentPath
		});
	};
	$scope.openCabinet = function(no) {
		$scope.currentMatter = null;
		for(var index = 0; index < $scope.matterInfo.length; index++) {
			if(no === $scope.matterInfo[index].ST_CABINET_NO) {
				$scope.currentMatter = true;
				$scope.stApplyNo = $scope.matterInfo[index].ST_APPLY_NO;
				$scope.getMethod =
					$scope.matterInfo[index].ST_SENDER_IDCARD === undefined ? "getCertInfo.do" : "getMatterInfo.do";

				appConfig.http($scope.getMethod, {
					stApplyNo: $scope.stApplyNo,
				}, function(res) {
					$scope.currentMatter = res;
					$scope.currentMatter.stApplyNo = $scope.stApplyNo;
					$scope.currentMatter.cabinetNo = no;
					$scope.mask = true;
				})

			}
		}
		if($scope.currentMatter === null) {
			var layer = confirm("是否打开" + no + "号柜子?");
			if(layer === true) {
				$location.path("/openBox").search({
					number: no,
					address: $scope.currentPath
				});
			}
		}
	};
	$scope.closeMask = function() {
		$scope.mask = false;
	};
	$scope.getMatterInfo();
})
/**
 * 开箱
 */
app.controller("openBoxController", function(
	$scope,
	$http,
	appData,
	appConfig,
	$timeout,
	$location,
	$interval,
	$rootScope
) {
	$scope.$on("$locationChangeStart", function(event, toState, toParams, fromState, fromParams) { //路由变化清除计时器
		$interval.cancel($scope.Interval);
	});
	//开柜
	$scope.Number = $location.search().number || null;
	$scope.tipsInfo = $location.search().tips || "";
	$scope.whereFrom = $location.search().address;

	$scope.isStaff = appData.isStaff;
	$scope.countDown = 3;
	$.log.debug($scope.whereFrom);
	$scope.Interval = $interval(function() {
		$scope.countDown--;
		if($scope.countDown < 1) {
			$interval.cancel($scope.Interval);
			$.device.GoHome();
			//$location.path("/main").search({});
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
	var Count = 20;
	$scope.getResideQuantity = function() {
		$.ajax({
			type: "get",
			url: appConfig.httpUrl + "getOnUse.do",
			async: true,
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				stMachineId: appConfig.stMachineId
			},
			success: function(res) {
				appData.resideQuantity = 5 - res.cabList.length;
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
		appConfig.http("sendMsg.do", {}, function(res) {
			if(res.result === "ok") {
				alert("柜子已满！");
			} else {

			}
		});
	}
	/*重置柜子*/
	appConfig.sark = [].concat(appConfig.initSark);
	/**
	 * 这里执行开柜方法  $scope.Number为柜子编号
	 */
	$.device.serialPortOpen("COM4", 115200, 8, function() {})
	$.log.debug("$scope.Number柜子编号为---：" + $scope.Number);
	//$.device.SarkInit();
	$.device.SarkOpen($scope.Number);
});