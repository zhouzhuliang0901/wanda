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
				$location.path("/staffOperation");
			} else {
				console.log("matters type is error!");
			}
		} else {
			if(matter === "license" || matter === "material") {
				appData.matterType = matter;
				$location.path("/licenseInputCode").search({});
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
			//using:"证照柜亮证",
			codeParam: _code
		}
		$http
			.jsonp("http://10.3.80.232:8080/ac-product/aci/window/getQrCodeInfoByElectronicCert.do", {
				params: $scope.httpConfig,
				timeout: 10000
			})
			.success(function(data) {
				if(data.result.success === false) {
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
	$scope.readIdCard = function() {
		$.device.idCardOpen(function(value) {
			var list = JSON.parse(value);
			//		let list = {
			//			Name: "张三",
			//			Number: "350722199011260084"
			//		}
			if(list != null) {
				$.log.debug(list);
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
							$location.path("/putMaterialConfirm").search({
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
	$.device.Camera_Link($.config.get("camera"), $.config.get("resolution")); //初始化摄像头
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
//		alert(appData.capturePhoto);
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
//		alert("身份证："+appData.ImageUrlStr);
//		alert("照片："+appData.capturePhoto);
		$scope.recognitionAjax = $.ajax({
			type: "post",
			url: "http://10.5.2.100:8080/ac-product-ext/ext/aci/autoterminal/facecompare.do",
			async: true,
			data: {
				idCardPhoto: appData.ImageUrlStr,
				capturePhoto: appData.capturePhoto
			},
			success: function(data) {
//				alert("人证核验完成");
				$.log.debug(data);
				var dataJson = JSON.parse(data);
//				alert(data);
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
//				alert("error");
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
//	if($scope.verificationStatus) {
//		$timeout(function() {
//			$scope.Continue();
//		}, 1000);
//	};
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
		if(appData.BBID_AC == 1) {
			//代办人员，从新赋值
			appData.idCardNumber = appData.BBidCardNumber;
		}
		if($scope.numCode.length < 6) {
			alert("请输入六位取证码!");
			return;
		}
		$.ajax({
			type: "get",
			url: appConfig.httpUrl + "getCertFlowForUser.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				stReceiveNum: $scope.numCode,
				stMachineId: appConfig.stMachineId
			},
			success: function(res) {
				console.log(res);
				$scope.callbackInfo(res);// 贵都取证回调
				if(res.success == true) {
					$location.path("/openBox").search({
						number: res.stCabinetNo,
						tips: "取出证照"
					});
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
		$scope.callbackInfo = function(info){
			// 贵都取证回调
			$.ajax({
				type: "post",
				url: appConfig.gdHttpUrl + "certBoxQueryResultBack.do",
				dataType: "jsonp",
				jsonp: "jsonpCallback",
				data: {
					teamCode: info.stApplyNo ,// 团组条形码
					type: "take",// 存取类型  存：save 取：take
					stMachineId: appConfig.stMachineId , // 设备ID
					stCabinetNo: info.stCabinetNo,// 柜子编号
					isAdmin: "0"// 是否为管理员模式操作  （1为管理员模式， 跳过校验强制操作时设为1）
				},
				success: function(dataJson) {
					console.log(dataJson.data);
					$scope.$apply();
				},
				error: function(err) {
					console.log('失败信息: ', err);
					$scope.loginErrorType = "登录失败，请重新登录";
					return
				}
			})
			
		}
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
	$state,
	$timeout
) {
	console.log(appData.isStaff);
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
			.jsonp("http://10.3.80.232:8080/ac-product/aci/window/getQrCodeInfoByElectronicCert.do", {
				params: $scope.httpConfig,
				using: "证照柜亮证",
				timeout: 10000
			})
			.success(function(data) {
				if(data.result.success === false) {
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
				}, function(err) {});
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
		appData.loginGuard = true;
		$.device.idCardOpen(function(value) {
			var list = JSON.parse(value);
//					let list = {
//						Name: "邹天奇",
//						Number: "430426199804106174",
//					}
			var Name = list.Name;
			var Code = list.Number;
			alert(Name+Code);
			let result = [];
			if(list) {
				//判断是否存在工作人员身份证
				for(var i = 0; i < appConfig.personnel.length; i++) {
					if(appConfig.personnel[i].Code == Code) {
						result = appConfig.personnel[i];
						alert("是管理员");
					}
				}
				if(result !="" && result!=null) {
					appData.loginGuard = true;
					appData.userName = list.Name;
					appData.idCardNumber = list.Number;
					appData.idCardImg = list.CardImagePath;
					alert("姓名："+appData.userName+"证件号："+appData.idCardNumber+"照片路径："+appData.idCardImg);
					$scope.getImgbaseStr(
						appData.idCardNumber,
						appData.userName,
						appData.idCardImg
					);
					$timeout(function() {
						console.log(appData.isStaff)
						if(appData.isStaff === true) {
//							$location.path("/staffOperationChoice");// 非人证核验流程
							$location.path("/faceVerification");// 人证核验流程
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
					}, 2000);
				} else {
					alert("请用工作人员信息登录！");
					$state.go("main");
				}
			}
			$scope.$apply();
		});
	};
	//获取上传的身份证base64
	$scope.getImgbaseStr = function(idCardNo, name, imgUrl) {
		appData.ImageUrlStr = $.device.fileBase64(imgUrl);
//		alert("通过路径"+imgUrl+",获取的身份证照片："+appData.ImageUrlStr);
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
	$scope.flag = true; // 继续按钮生效
	$scope.licenseList = [];
	appConfig.http(
		"getCertFlowForAdmin.do", {
			//			stIdentityNo: appData.idCardNumber
		},
		function(res) {
			$scope.isLoding = false;
			if(res.data.length >= 1) {
				appData.licenseList =res.data;
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
			$.ajax({
				type: "get",
				url: appConfig.httpUrl + "getCertFlowForUser.do",
				dataType: "jsonp",
				jsonp: "jsonpCallback",
				data: {
					stCabinetNo : serialNum,
					stMachineId: appConfig.stMachineId
				},
				success: function(res) {
					if(res.success == true) {
						$scope.callbackInfo(res);
						$location.path("/openBox").search({
							number: res.stCabinetNo,
							tips: "取出证照"
						});
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
		}
	};
	$scope.callbackInfo = function(info){
			// 贵都取证回调
			$.ajax({
				type: "post",
				url: appConfig.gdHttpUrl + "certBoxQueryResultBack.do",
				dataType: "jsonp",
				jsonp: "jsonpCallback",
				data: {
					teamCode: info.stApplyNo ,// 团组条形码
					type: "take",// 存取类型  存：save 取：take
					stMachineId: appConfig.stMachineId,// 设备ID
					stCabinetNo: info.stCabinetNo,// 柜子编号
					isAdmin: "0"// 是否为管理员模式操作  （1为管理员模式， 跳过校验强制操作时设为1）
				},
				success: function(dataJson) {
					console.log(dataJson.data);
					$scope.$apply();
				},
				error: function(err) {
					console.log('失败信息: ', err);
					$scope.loginErrorType = "登录失败，请重新登录";
					return
				}
			})
			
		}
	
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
app.controller("inputPhoneController", function($scope, $http, appData, $location, appConfig) {
	/**
	 * 扫描二维码操作  将获取的信息传入下面路由中参数  code
	 */
	$scope.numCode = "";
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
		$scope.numCode.length < 15 ? ($scope.numCode += val) : "";
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
		$scope.stPhone = appData.phoneNum
		$scope.idCardNumber = appData.idCardNumber;
	});
	/**
	 * 这里寻找空闲的证照柜柜子
	 * 生成一个可用柜子数组
	 * 随机选取一个空闲柜子
	 */
	$scope.Continue = function() {
		var stCabinetNo =
			appConfig.initSark[parseInt(Math.random() * appConfig.initSark.length, 10)];
		var arr = appConfig.sark;
		console.log("已存在的柜子号："+arr.toString());
		var index = arr.indexOf(stCabinetNo);
		while(index != -1) {// 已存件柜子号中无生成的stCabinetNo时，index等于-1
			stCabinetNo =
				appConfig.initSark[parseInt(Math.random() * appConfig.initSark.length, 10)];
			console.log("生成的柜子号："+stCabinetNo);
			index = arr.indexOf(stCabinetNo);
		}
		console.log("使用的柜子号："+stCabinetNo);
		$.log.debug("stCabinetNo: " + stCabinetNo)
		$.log.debug(JSON.stringify(appConfig.sark))
		stCabinetNo = $scope.yetUseCabinet || stCabinetNo; //存在原有的柜子号则打开原有的 否则打开新生成的柜子号
		if(appData.isStaff == true) {
			// 存证
			$.ajax({
				type: "post",
				url: appConfig.httpUrl + "putCertFlow.do",
				dataType: "jsonp",
				jsonp: "jsonpCallback",
				data: {
					stMachineId: appConfig.stMachineId,
					stCabinetNo: stCabinetNo,// 柜子号
					stName: encodeURI($scope.userName),// 放证人姓名
					idCard: $scope.idCardNumber,// 放证人身份证
					stUserName: appData.stUserName,// 取件人姓名
					stMobile: appData.phoneNum,// 取件人手机号
					stIdentityNo: "",// 取件人身份证号
					certName: encodeURI(""),// 证照名称
					ext: 1,// 取件人标识
					stApplyNo:appData.qrCodeInfo // 证件编码
				},
				success: function(res) {
					if(res.success==true){
						$scope.callbackInfo(res);
						$location.path("/openBox").search({
							
							number: stCabinetNo,
							tips: "放入材料"
						});
					}
				},
				error: function(err) {
					$.log.debug('false' + err);
					console.log(err);
				}
			});
			
		} else {
			$.ajax({
				type: "get",
				url: appConfig.httpUrl + "putCertFlow.do",
				dataType: "jsonp",
				jsonp: "jsonpCallback",
				data: {
					stCabinetNo: stCabinetNo,
					stName: encodeURI($scope.userName),
					idCard: $scope.idCardNumber,
					stUserName: encodeURI(appConfig.personnel[0].Name),
					stMobile: appConfig.personnel[0].Tel,
					stIdentityNo: appConfig.personnel[0].Code,
					certName: encodeURI(""),
					ext: 1,
				},
				success: function(res) {
					if(res.success == true) {
						$location.path("/openBox").search({
							number: stCabinetNo,
							tips: "放入材料"
						});
					} else {
						alert("没有查询到办件信息，请重新扫描或输入！")
					}
				},
				error: function(err) {
					$.log.debug('false' + err);
				}
			})
		}
		$scope.callbackInfo = function(info){
			// 贵都存证回调
			$.ajax({
				type: "post",
				url: appConfig.gdHttpUrl + "certBoxQueryResultBack.do",
				dataType: "jsonp",
				jsonp: "jsonpCallback",
				data: {
					teamCode: info.stApplyNo ,// 团组条形码
					type: "save",// 存取类型  存：save 取：take
					stMachineId: appConfig.stMachineId,// 设备ID
					stCabinetNo: stCabinetNo,// 柜子编号
					isAdmin: "1"// 是否为管理员模式操作  （1为管理员模式， 跳过校验强制操作时设为1）
				},
				success: function(dataJson) {
	//				alert(dataJson.data);
					console.log(dataJson.data);
					$scope.$apply();
				},
				error: function(err) {
					console.log('失败信息: ', err);
					$scope.loginErrorType = "登录失败，请重新登录";
					return
				}
			});
		}
	};
	
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
	$scope.getMatterInfo = function() {// http://180.169.7.194:8080/ac-product/aci/certflow/getCertFlowOnUse.do
//		appConfig.http(
//			"login.do", {
//				stIdentityNo: appData.idCardNumber
//			},
//			function(res) {
//				console.log(res)
//				if(res.msg === "success") {
//					$scope.usedCabinet = $scope.usedCabinet.concat(res.cabList);
//					$scope.matterInfo = $scope.matterInfo.concat(res.overDueList, res.stuffList);
//				} else {
//					console.log("登录失败！");
//				}
//			},
//			function(err) {
//				console.log("err:" + errr);
//			}
//		);
		$.ajax({
			type: "post",
			url: appConfig.httpUrl + "getCertFlowOnUse.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				stMachineId: appConfig.stMachineId
			},
			success: function(res) {
				$scope.usedCabinet = $scope.usedCabinet.concat(res.cabList);
				
				
			},
			error: function(err) {
				$.log.debug('false' + err);
				console.log(err);
			}
		});
	}
	$scope.confirmOpen = function() {
		$location.path("/openBox").search({
			number: $scope.currentMatter.cabinetNo,
			address: $scope.currentPath
		});
	};
	$scope.openCabinet = function(no) {// 传入柜子号
		$.ajax({
			type: "get",
			url: appConfig.httpUrl + "getCertFlowForUser.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				stCabinetNo : no,
				stMachineId: appConfig.stMachineId
			},
			success: function(res) {
				
				if(res.success == true) {
					$scope.callbackInfo(res);
					$location.path("/openBox").search({
						number: res.stCabinetNo,
						tips: "取出证照"
					});
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
		
		if($scope.currentMatter === null) {
			var layer = confirm("是否打开" + no + "号柜子?");
			if(layer === true) {
				$location.path("/openBox").search({
					number: no,
					address: $scope.currentPath
				});
			}
		}
		
		$scope.callbackInfo = function(info){
				// 贵都取证回调
				//alert("贵都回调");
				$.ajax({
					type: "post",
					url: appConfig.gdHttpUrl + "certBoxQueryResultBack.do",
					dataType: "jsonp",
					jsonp: "jsonpCallback",
					data: {
						teamCode: info.stApplyNo ,// 团组条形码
						type: "take",// 存取类型  存：save 取：take
						stMachineId: $.config.get('uniqueId') || "12-12-12-12" ,// "00-E2-69-1F-8D-3D",// 设备ID
						stCabinetNo: info.stCabinetNo,// 柜子编号
						isAdmin: "0"// 是否为管理员模式操作  （1为管理员模式， 跳过校验强制操作时设为1）
					},
					success: function(dataJson) {
						console.log(dataJson.data);
						$scope.$apply();
					},
					error: function(err) {
						console.log('失败信息: ', err);
						$scope.loginErrorType = "登录失败，请重新登录";
						return
					}
				})
			
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
	$scope.stReceiveNum = $location.search().stReceiveNum || "";
	
	$scope.isStaff = appData.isStaff;
	$scope.countDown = 60;
	console.log("柜子号："+ $scope.Number + "----信息：" + $scope.tipsInfo + "---倒计时" + $scope.countDown);
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
		$location.path("/staffOperationChoice");
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
			url: appConfig.httpUrl + "getCertFlowOnUse.do",
			async: true,
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				stMachineId: appConfig.stMachineId
			},
			success: function(res) {
				appData.resideQuantity = 60 - res.cabList.length;
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
	//appConfig.sark = [].concat(appConfig.initSark);
	/*将开箱号码添加到已开柜数组中*/
	appConfig.sark.push($scope.Number);
	/**
	 * 这里执行开柜方法  $scope.Number为柜子编号
	 */
	$.device.SarkInit();
	$.device.SarkOpen($scope.Number);
});

/**
 * 扫描二维码
 */
app.controller("qrCodeController", function(
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
	$scope.operation = "团组条形码";
	$scope.btnInfo = "手动输入";
	$scope.matterName = "收件凭证";
	$scope.formPrevRouter = "main";
	$scope.isCodeInfo = false; //用来接收扫描数据
	$scope.goPrevRoute = function() {
		$location.path("/" + $scope.formPrevRouter);
	}
	$scope.scanCode = function() {
//		alert("开始调用二维码");
		$.device.qrCodeOpen(function(code) {
			var _code = code.trim().replace(/\u0000/g, '');
//			alert("团组编码："+_code);
			appData.qrCodeInfo = _code;
			if($scope.zfbs(appData.qrCodeInfo)) {
				$location.path("/putMaterialConfirmzf").search({
					code: _code
				});
			} else {
				$.ajax({
					type: "get",
					url: appConfig.gdHttpUrl + "certBoxTeamCheck.do",
					dataType: "jsonp",
					jsonp: "jsonpCallback",
					data: {
						teamCode: appData.qrCodeInfo
					},
					success: function(dataJson) {
						if(dataJson.code=="1"){// 传入数据是否正确
							alert(dataJson.data);
						}else{
							var info = dataJson.data;
							appData.phoneNum = info.contactPhone;// 取件人手机号
							appData.stUserName = info.contactName;// 取件人姓名
							
							console.log(info);
							if(info.success=="true"){
								$location.path("/putMaterialConfirm").search({
									code: appData.qrCodeInfo
								});
								$scope.$apply();
							}else{
								alert(info.comment);
								$location.path("/staffOperationChoice");
								$scope.$apply();
							}
						}
					},
					error: function(err) {
						console.log('失败信息: ', err);
						$scope.loginErrorType = "登录失败，请重新登录";
						return
					}
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
		
		appData.qrCodeInfo = $scope.numCode;
//		appData.qrCodeInfo = "24201920029198";
//		$scope.numCode = appData.qrCodeInfo;
		if($scope.numCode.length < 14) {
			alert("请输入十四位团组条形码!");
			return;
		}
		
		// http://localhost:8080/acdeal-p/acceptance/certBoxTeamCheck.do
		$.ajax({
			type: "post",
			url: appConfig.gdHttpUrl + "certBoxTeamCheck.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				teamCode: appData.qrCodeInfo
			},
			success: function(dataJson) {
				if(dataJson.code=="1"){// 传入数据是否正确
					alert(dataJson.data);
				}else{
					var info = dataJson.data;
					appData.phoneNum = info.contactPhone;// 取件人手机号
					appData.stUserName = info.contactName;// 取件人姓名
					
					console.log(info);
					if(info.success=="true"){
						$location.path("/putMaterialConfirm").search({
							code: appData.qrCodeInfo
						});
						$scope.$apply();
					}else{
						alert(info.comment);
						$location.path("/staffOperationChoice");
						$scope.$apply();
					}
				}
			},
			error: function(err) {
				console.log('失败信息: ', err);
				$scope.loginErrorType = "登录失败，请重新登录";
				return
			}
		});
//		if($scope.zfbs(appData.qrCodeInfo)) {
//			$location.path("/putMaterialConfirmzf").search({
//				code: $scope.numCode
//			});
//		} else {
//			$location.path("/putMaterialConfirm").search({
//				code: $scope.numCode
//			});
//		}
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

