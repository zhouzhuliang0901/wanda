var urlHost = "https://yjssb.shhk.gov.cn/ac-product/aci/certification/";
app.controller("mainController", function($rootScope,$scope,$http,appData,$location,appConfig,$state,$rootScope) {
	appData.isStaff = false; //
	appData.loginGuard = false; //路由守卫
	$.device.qrCodeClose(); //
	$.log.debug(appConfig.sark);
	Array.prototype.indexOf = function(val) {
		for (var i = 0; i < this.length; i++) {
			if (this[i] == val) {
				return i;
			}
		}
		return -1;
	};
	Array.prototype.removeItem = function(val) {
		var index = this.indexOf(val);
		if (index > -1) {
			this.splice(index, 1);
		}
	};

	$scope.choiceMatter = function(matter) { //选择材料事项
		if (appData.isStaff === true) {
			if (matter === "license" || matter === "material") {
				appData.matterType = matter;
				$location.path("/staffOperation").search({});
				//$location.path("/staffOperationChoice").search({});
			} else {
				console.log("matters type is error!");
			}
		} else {
			if (matter === "license"){
				appData.matterType = matter;
				$location.path("/licenseTake").search({});
				//$location.path("/signature").search({});
			}else if(matter === "material") {
				appData.matterType = matter;
				$location.path("/staffOperation").search({});
				//$location.path("/scanCode").search({});
			} else {
				console.log("matters type is error!");
			}
		}
	};
	$scope.changeModel = function(model) {
		if (model === "staff") {
			appData.isStaff = true;
			$location.path("/staffOperation").search({});
			//$location.path("/staffOperationChoice").search({});
		} else if (model === "masses") {
			appData.isStaff = false;
		}
	};
});
/**
 * 办事人员取证
 */
app.controller("licenseTakeController", function($scope,$http,appConfig,appData,$timeout,$location) {
	$scope.loginType = "idCard";
	$scope.btnInfo = "市民云亮证";
	$scope.title = "自助取证";
	$scope.isReadOver = false;
	appConfig.getResideQuantity()
	if (appData.matterType === "material") {
		$scope.title = "证照放置";
	}
	$scope.loginTypeTab = function(type) {
		if (type === "idCard") {
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
	$scope.scanQrCode = function() {
		$.device.qrCodeOpen(function(code) {
			if (code == "") {
				$scope.scanQrCode();
				return;
			}
			$scope.isReadOver = true;
			var __code = code.replace(/[\r\n]/g, "");
			$.post("https://yjssb.shhk.gov.cn/ac-product/aci/window/getInfoByCodeForLogin.do", {
					codeParam:__code
				},
			   function(data){
					if(data.result.success!==true){
						alert("未获取到市民云信息");
					}	
					appData.userName = data.result.data.realname;
					appData.idCardNumber = data.result.data.idcard;
					$location.path("/signature");
			   }, "json").success(function(){
				   
			   }).error(function(){
				   alert("登录失败，请重试！");
			   });
	
			$scope.$apply();
			$.device.qrCodeClose();
		});
	};
	$scope.readIdCard = function() {
		$.device.idCardOpen(function(value) {
			var list = JSON.parse(value);
			if (list != null) {
				$.log.debug(list);
				$scope.isReadOver = true;
				appData.userName = list.Name;
				appData.idCardNumber = list.Number;
				appData.idCardImg = list.CardImagePath;
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
				if (dataJsonp.length > 0) {
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
app.controller("faceVerificationController", function($scope,$http,$location,appData,$timeout,$interval) {
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
	if ($scope.isCheckAgain == '1') { //如果是重新核验开启手动拍照自动拍照时间设为5秒
		$scope.toCount = 5;
		$timeout(function() {
			$scope.startBtn = true;
		}, 100);
	};
	$timeout(function() { //延时两秒
		$scope.countDown = $interval(function() {
			--$scope.toCount;
			if ($scope.toCount < 1) {
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
app.controller("verificationAwaitController", function($scope,$http,$location,appData,appConfig) {
	$scope.recognitionAjax = null;
	$scope.$on("$locationChangeStart", function() {
		try {
			$scope.recognitionAjax.abort();
		} catch (e) {
			console.log("不存在该请求！");
		}
	});
	$scope.recognition = function() {
		//人证数据对比
		$scope.recognitionAjax = $.ajax({
			type: "post",
			url: "http://hengshui.5uban.com/ac-product-ext/ext/aci/autoterminal/facecompare.do",
			data: {
				idCardPhoto: appData.ImageUrlStr,
				capturePhoto: appData.capturePhoto
			},
			dataType: "json",
			success: function(data) {
				$.log.debug(data);
				var verificationStatus = null; //0 失败  1 成功
				var n = data.similarity;
				if (n > 60) {
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
app.controller("verificationCompleteController", function($scope,$http,$location,appData,$timeout) {
	$scope.verificationStatus =
		$location.search().verificationStatus == "1" ? true : false; //判断人证核验成功与否
	if ($scope.verificationStatus) {
		$timeout(function() {
			$scope.Continue();
		}, 1000);
	};
	$scope.Continue = function() {
		if (appData.isStaff === true) {
			$location.path("/staffOperationChoice");
		} else {
			if (appData.matterType === "material") {
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
app.controller("licenseInputCodeController", function($scope,$http,appData,$location,appConfig) {
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
	$scope.confirmCode = function() {
		if ($scope.numCode.length < 6) {
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
				ST_APPLY_NO: $scope.qrCode.stApplyNo
			},
			success:function(res) {
				$scope.boxNum = res[0].stCertIficationNo;
				$.ajax({
					type: "post",
					data: {
						ST_CERT_IFICATION_ID:res[0].stCertIficationId,
						ST_APPLY_NO:$scope.qrCode.stApplyNo,
						ST_WORK_USER_NAME:appData.userName,
						ST_WORK_CERT_NO:appData.idCardNumber,
						ST_CERT_IFICATION_NO:res[0].stCertIficationNo,
						ST_CERT_IFICATION_TYPE:"0",
						ST_CERT_EQUIPMENT_NO:"A",
						BL_IMAGE:appData.picStr
					},
					url: urlHost+"addCertIficationInfo.do",
					dataType: "json",
					success:function(res){
						$location.path("/openBox").search({
							number: $scope.boxNum,
							tips: "取出证照"
						});
					},
					error: function(res){
						alert("未查询到证照，请联系工作人员处理");
					}
				});
			},error:function(err) {
				alert("取证码无效，请联系工作人员处理");
			}
		});
	}
});
/**
 * 签名
 */
app.controller("signatureController", function($scope,$http,appData,appConfig,$location) {
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
		if ($scope.signatureFlag === false) {
			alert("请先在屏幕上签名!");
			return;
		}
		appData.picStr = $scope.signature.split(",")[1];
		$location.path("/scanCode");
	};
});
app.controller("getLicenseConfirmController", function($scope,$http,appData,appConfig,$location) {
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
app.controller("staffOperationController", function($scope,$http,appData,$location,appConfig) {
	$scope.pageClass = "fade";
	$scope.title = "工作人员登录";
	$scope.loginType = "idCard";
	$scope.btnInfo = "市民云登录";
		$scope.scanQrCode = function() {
		$.device.qrCodeOpen(function(code) {
			if (code == "") {
				$scope.scanQrCode();
				return;
			}
			var __code = code.replace(/[\r\n]/g, "");
			$.post("https://yjssb.shhk.gov.cn/ac-product/aci/window/getInfoByCodeForLogin.do", {
					codeParam:__code
				},
				function(data){
				for (var i = 0; i < appConfig.personnel.length; i++) {
					if (appConfig.personnel[i].Code == data.result.data.idcard) {
				 		appData.loginGuard = true;
					}
				}
				if (appData.loginGuard === true) {
				 	appData.userName = data.result.data.realname;
				 	appData.idCardNumber = data.result.data.idcard;
				 	if(appData.isStaff === true){
				 		$location.path("/staffOperationChoice");
				 	}else if(appData.isStaff === false){
				 		$location.path("/scanCode");
					}
				} else {
						alert("请使用工作人员身份信息登录！");
					}
				}, "json").success(function(){
				   
				}).error(function(){
				   alert("登录失败，请重试！");
				});
			$.device.qrCodeClose();
			$scope.$apply();
		})
	};
	$scope.loginTypeTab = function(type) {
		if (type === "idCard") {
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
			if (list) {
				for (var i = 0; i < appConfig.personnel.length; i++) {
					if (appConfig.personnel[i].Code == Code) {
						appData.loginGuard = true;
					}
				}
				if (appData.loginGuard === true) {
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
				if (dataJsonp.length > 0) {
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
app.controller("licenseInfoListController", function($scope,$http,appData,$location,appConfig) {
	if (appData.loginGuard === false || appData.loginGuard === undefined) {
		alert("请先登录!");
		$location.path("/main");
		return;
	}
	$scope.hasData = false;
	$scope.isLoding = true;
	$scope.licenseList = [];
	appConfig.http(
		"login.do", {
			stIdentityNo: appData.idCardNumber
		},
		function(res) {
			$.log.debug("login :" + appData.idCardNumber);
			$scope.isLoding = false;
			if (res.overDueList.length >= 1) {
				appData.licenseList = res.overDueList;
				if (res.overDueList.length > 8) {
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
				if (appData.licenseList[0] instanceof Array) {
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
		if ($scope.currentPage < $scope.totalPages) {
			$scope.currentPage++;
			$scope.licenseList = appData.licenseList[$scope.currentPage - 1];
		}
	};
	$scope.prev = function() {
		if ($scope.currentPage > 1) {
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
				if (reqCount > 1) {
					$scope.openBox(serialNum, id);
				}
			}
		);
	};
});
/**
 * 材料列表
 */
app.controller("materialInfoListController", function($scope,$http,appData,appConfig,$location,$timeout) {
	if (appData.loginGuard === false || appData.loginGuard === undefined) {
		alert("请先登录!");
		$location.path("/main");
		return;
	}
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
				$.log.debug("login :" + appData.idCardNumber);
				$scope.isLoding = false;
				if (res.stuffList.length >= 1) {
					appData.licenseList = res.stuffList;
					if (res.stuffList.length > 8) {
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
					if (appData.licenseList[0] instanceof Array) {
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
		if ($scope.currentPage < $scope.totalPages) {
			$scope.currentPage++;
			$scope.licenseList = appData.licenseList[$scope.currentPage - 1];
		}
	};
	$scope.prev = function() {
		if ($scope.currentPage > 1) {
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
				if (reqCount > 1) {
					$scope.openBox(No, id);
				}
			}
		);
		$.log.debug($location.path());
	};
});
/**
 * 扫描二维码
 */
app.controller("scanCodeController", function($scope,$http,appData,$location,appConfig) {
	/**
	 * 扫描二维码操作  将获取的信息传入下面路由中参数  code
	 */

	$scope.isManual = false;
	$scope.isStaff = appData.isStaff;
	$scope.matterInfo = null;
	$scope.module = "办事人员操作";
	$scope.whoOperation = "证照取出";
	$scope.operation = "二维码";
	$scope.matterName = "收件凭证";
	$scope.formPrevRouter = "main";
	$scope.btnInfo ="手动输入";
	$scope.isCodeInfo = false; //用来接收扫描数据
	$scope.goPrevRoute = function() {
		$location.path("/" + $scope.formPrevRouter);
	}
	$scope.scanCode = function() {
		$.device.qrCodeOpen(function(code) {
			var _code = code.trim().replace(/\u0000/g, '');
			if(_code.substring(0,1)=="h"){
				appData.qrCodeInfo = _code;
				var index = appData.qrCodeInfo.split("?")[1];
				var obj = index.split("&")[0];
				var obj2 = index.split("&")[1];
				if(obj.split("=")[0]=="id"){
					appData.qrCodeInfo = {
						"stUserName":obj2.split("=")[1],
						"stCertNo":"",
						"stApplyNo":obj.split("=")[1],
						"stMobile":"",
						"modelType":"2",
						"modelName":"消防局"
					}
				}else if(obj.split("=")[0]=="ST_SUID"){
					appData.qrCodeInfo = {
						"stUserName":"",
						"stCertNo":"",
						"stApplyNo":obj.split("=")[1],
						"stMobile":"",
						"modelType":"1",
						"modelName":"市场监管局"
					}
				}
			}else{
			    appData.qrCodeInfo = JSON.parse(_code);
				appData.qrCodeInfo.modelType = "0";
				appData.qrCodeInfo.modelName = "新点";
			}
			if(appData.matterType=="license"){
				$location.path("/licenseInputCode");
				$scope.$apply();
				$.device.qrCodeClose();
			}else if(appData.matterType=="material"){
				$location.path("/inputPhone");
				$scope.$apply();
				$.device.qrCodeClose();
			}
		});
	};
// 	appData.qrCodeInfo = {"stUserName":"上海虹口区同心托育园","stCertNo":"1234532432432","stApplyNo":"STD201904300002","stMobile":"18692067056","modelType":"0",
// 						"modelName":"新点"};
	console.log(appData.qrCodeInfo);
	if(appData.resideQuantity < 1) {
		alert("暂无可使用柜子！");
		$location.path("/main");
		return;
	}
	if(appData.isStaff === true || appData.matterType === "material") {
		if(appData.loginGuard === false || appData.loginGuard === undefined) {
			alert("请先登录!");
			$location.path("/main");
			return;
		}
		$scope.isManual = false;
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
		if(appData.matterType=="license"){
			appData.qrCodeInfo = {
				"stUserName":"",
				"stCertNo":"",
				"stApplyNo":$scope.numCode,
				"stMobile":"",
				"modelType":"10",
				"modelName":"其他部门"
			}
			$location.path("/licenseInputCode");
		}else if(appData.matterType=="material"){
			appData.qrCodeInfo = {
				"stUserName":"",
				"stCertNo":"",
				"stApplyNo":$scope.numCode,
				"stMobile":"",
				"modelType":"10",
				"modelName":"其他部门"
			}
			$location.path("/inputPhone");
		}
	};
	$scope.scanCode();
	
	$scope.next = function(){
		if(appData.matterType=="license"){
			$location.path("/licenseInputCode");
		}else if(appData.matterType=="material"){
			appData.qrCodeInfo = appData.qrCodeInfo;
			$location.path("/inputPhone");
		}
	}
	
});
/**
 * 输入手机号
 */
app.controller("inputPhoneController", function($scope,$http,appData,$location,appConfig) {
	/**
	 * 扫描二维码操作  将获取的信息传入下面路由中参数  code
	 */
	if(appData.qrCodeInfo.stMobile!==""){
		$scope.numCode = appData.qrCodeInfo.stMobile;
	}else{
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
	if (appData.resideQuantity < 1) {
		alert("暂无可使用柜子！");
		$location.path("/main");
		return;
	}
	if (appData.isStaff === true) {
		if (appData.loginGuard === false || appData.loginGuard === undefined) {
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
		if ($scope.numCode.length < 11) {
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
app.controller("putMaterialConfirmController", function($scope,$http,appData,$location,appConfig,$timeout) {

	//证照信息确认
	$scope.matterType = "办事";
	$scope._matterInfo = "办件";
	if (appData.isStaff === true) {
		$scope._matterInfo = "证照";
		$scope.matterType = "工作";
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
		$scope.phone = appData.phoneNum;
	});
	/**
	 * 这里寻找空闲的证照柜柜子
	 * 生成一个可用柜子数组
	 * 随机选取一个空闲柜子
	 */
	$scope.Continue = function() {
		var stCabinetNo =
			appConfig.initSark[parseInt(Math.random() * appConfig.initSark.length, 10)];
		for(var i=0;i<appConfig.sark.length;i++){
			while(appConfig.sark[i]==stCabinetNo){
				stCabinetNo =
					appConfig.initSark[parseInt(Math.random() * appConfig.initSark.length, 10)];
			}
		}
		$.log.debug("stCabinetNo: " + stCabinetNo);
		stCabinetNo = $scope.yetUseCabinet || stCabinetNo; //存在原有的柜子号则打开原有的 否则打开新生成的柜子号
		
		appConfig.http(
			"addCertIficationInfo.do", {
				//办事人员放入材料
				ST_CERT_IFICATION_ID:stCabinetNo,
				ST_APPLY_NO:$scope.qrCode.stApplyNo,
				ST_USER_NAME:$scope.qrCode.stUserName,
				ST_MOBILE:$scope.qrCode.stMobile || appData.phoneNum,
				ST_VERIFICATION_CODE:"",
				ST_CERT_NO:$scope.qrCode.stCertNo,
				ST_CERT_IFICATION_NO:stCabinetNo,
				ST_CERT_CONTENT:"证照",
				ST_CERT_TYPE:"0",
				ST_CERT_IFICATION_TYPE:"1",
				ST_MODEL_TYPE:$scope.qrCode.modelType,
				ST_MODEL_NAME:$scope.qrCode.modelName,
				ST_CERT_EQUIPMENT_NO:"A",
				ST_WORK_CERT_NO:appData.userName,
				ST_WORK_USER_NAME:appData.idCardNumber
			},
			function(res) {
				$location.path("/openBox").search({
					number: stCabinetNo,
					tips: "放入证照"
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
app.controller("cabinetControllerController", function($scope,$http,appData,appConfig,$timeout,$location,$interval,$rootScope) {
	if (appData.loginGuard === false || appData.loginGuard === undefined) {
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
		if (this.indexOf(str) > -1) {
			for (var index = 0; index < this.length; index++) {
				if (this[index] === _str) {
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
		if (layer === true) {
			$http.jsonp(
				urlHost+"addCertIficationInfo.do",{
					params:{
						ST_CERT_IFICATION_ID:no,
						ST_WORK_USER_NAME:appData.userName,
						ST_WORK_CERT_NO:appData.idCardNumber,
						ST_CERT_IFICATION_NO:no,
						ST_CERT_IFICATION_TYPE:"0",
						ST_CERT_EQUIPMENT_NO:"A",
						BL_IMAGE:"",
						jsonpCallback: "JSON_CALLBACK"
					}
				}).success(function(res){
					$location.path("/openBox").search({
						number: no,
						address: $scope.currentPath
					});
				}).error(function(err){
					alert("开柜失败");
				})
		}
	};
})
/**
 * 开箱
 */
app.controller("openBoxController", function($scope,$http,appData,appConfig,$timeout,$location,$interval,$rootScope) {
	$scope.$on("$locationChangeStart", function(event, toState, toParams, fromState, fromParams) { //路由变化清除计时器
		$interval.cancel($scope.Interval);
	});
	//开柜
	$scope.Number = $location.search().number || null;
	$scope.tipsInfo = $location.search().tips || "";
	$scope.whereFrom = $location.search().address;

	$scope.isStaff = appData.isStaff;
	$scope.countDown = 60;
	$.log.debug($scope.whereFrom);
	$scope.Interval = $interval(function() {
		$scope.countDown--;
		if ($scope.countDown < 1) {
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
				ST_CERT_IFICATION_TYPE:1
			},
			success: function(res) {
				appData.resideQuantity = 30 - res.length;
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
	if (appData.resideQuantity < 1) {
		alert("柜子已满！");
	}
	/*重置柜子*/
	appConfig.sark = [].concat(appConfig.initSark);
	console.log($scope.Number);
	/**
	 * 这里执行开柜方法  $scope.Number为柜子编号
	 */
 	$.device.SarkInit();
 	$.device.SarkOpen($scope.Number);
});
