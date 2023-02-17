app.controller("main_old", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.stuffName = perjsonStr;
	$scope.choiceType = function(type, name, ywlx) {
		appData.funName = name;
		appData.type = type;
		appData.ywlx = ywlx;
		if(appData.type == "bookMaking") {
			$state.go("bookMaking");
		} else if(appData.type == "reduce") {
			$state.go("loginType1");
		} else if(appData.type == "reimbursement" || appData.type == "residentRegistration" || appData.type == "helpRegistration" || appData.type == "helpSubsidy" || appData.type == "longTermInsurance") {
			//			appData.idCardNum = "310103193807283226";
			//			appData.applyNo = "0107CH101201808060001";
			//			$state.go("handleProgressQuery");
			$state.go("handleLoginType");
		} else if(appData.type == "treatment") {
			$state.go("treatmentDetails");
		} else {
			$state.go("loginType");
		}
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			bounce: true,
			click: true,
			taps: true,
			hScroll: false,
		});
	};
	$scope.isScroll();
});
app.controller('main', function($state, $scope, appData, $location) {
	$scope.operation = "请选择登录方式";

	$scope.funName = newPerjsonStr.medical_insure_info.stuffName;
	appData.type = newPerjsonStr.medical_insure_info.type;
	appData.ywlx = newPerjsonStr.medical_insure_info.ywlx;

	if(appData.type == "" || appData.type == undefined) {
		appData.type = $location.search().type;
	}
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		appData.SwipeType = "sbCard";
		$state.go("login");
	}
});
app.controller('loginTypeBookMaking', function($state, $scope, appData, $location) {
	$scope.funName = appData.funName;
	$scope.operation = "请选择登录方式";
	if(appData.type == "" || appData.type == undefined) {
		appData.type = $location.search().type;
	}
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller('loginType1', function($state, $scope, appData, $location) {
	$scope.funName = appData.funName;
	$scope.operation = "请选择登录方式";
	if(appData.type == "" || appData.type == undefined) {
		appData.type = $location.search().type;
	}
	console.log(appData.type);
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		appData.SwipeType = "sbCard";
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller('handleLoginType', function($state, $scope, appData, $location) {
	if(appData.type == "" || appData.type == undefined) {
		appData.type = $location.search().type;
	}
	$scope.funName = appData.funName;
	$scope.operation = "请刷身份证";
	//刷身份证
	$scope.readIdCard = function() {
		$.device.idCardOpen(function(list) {
			var data = JSON.parse(list);
			$scope.idcardInfo = data;
			appData.idCardNum = $scope.idcardInfo.Number;
		})
	};
	$scope.prevStep = function() {
		$state.go("main");
	}
	$scope.readIdCard();
	$scope.changeModel = function() {
		$state.go("handleLoginTypeManual");
	}
});
app.controller('handleLoginTypeManual', function($state, $scope, appData, $location) {
	if(appData.type == "" || appData.type == undefined) {
		appData.type = $location.search().type;
	}
	if(appData.idCardNum == "" || appData.idCardNum == undefined) {
		$scope.phone = "请输入";
	} else {
		$scope.phone = appData.idCardNum;
	}
	$scope.show = true;
	$scope.funName = appData.funName;
	document.getElementById("input1").onfocus = function() {
		console.log(1);
		$scope.show = true;
	};
	document.getElementById("input").onfocus = function() {
		console.log(2);
		$scope.show = false;
	};

	$scope.count = "请输入";
	$scope.keybordArr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 'X', 0, '删除'];
	$scope.keyboardInput = function(e) { //键盘输入
		var keycode = window.event ? e.keyCode : e.which;
		if(keycode >= 48 && keycode <= 57) {} else if(keycode == 8) {} else if(keycode >= 65 && keycode <= 90) {} else {
			console.log(e);
			$scope.count = $scope.count.substring(0, $scope.count.length - 1);
		}
	}
	$scope.phoneInput = function(e) { //键盘输入
		var keycode = window.event ? e.keyCode : e.which;
		if(keycode >= 48 && keycode <= 57) {} else if(keycode == 8) {} else {
			console.log(e);
			$scope.phone = $scope.phone.substring(0, $scope.phone.length - 1);
		}
	}
	$scope.inputNumber = function(item) { //软键盘输入
		if($scope.count == "请输入") {
			$scope.count = "";
		} else if(item === '删除') {
			$scope.count = $scope.count.substring(0, $scope.count.length - 1);
		} else {
			$scope.count += item;
			console.log($scope.count);
		}
	}
	$scope.inputPhone = function(item) { //软键盘输入
		if($scope.phone == "请输入") {
			$scope.phone = "";
		} else if(item === '删除') {
			$scope.phone = $scope.phone.substring(0, $scope.phone.length - 1);
		} else {
			$scope.phone += item;
			console.log($scope.phone);
		}
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
	$scope.nextStep = function() {
		appData.idCardNum = $scope.phone;
		appData.applyNo = $scope.count;
		$state.go("handleProgressQuery");
	}
	$scope.changeModel = function() {
		$state.go("handleLoginType");
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory) {
	$scope.funName = newPerjsonStr.medical_insure_info.stuffName;
	$scope.isAlert = false;
	$scope.msg = "";
	$scope.nextLink = ""; // 下一步标识符
	appData.sign = "token";
	if(appData.source == "idcardOrCitizen" && appData.loginType == "idcard") {
		$scope.funName = appData.funName;
		$scope.operation = "请刷办理人身份证";
	} else if(appData.source == "idcardOrCitizen" && appData.loginType == "cloud") {
		$scope.funName = appData.funName;
		$scope.operation = "请刷办理人随申办";
	} else if(appData.loginType == 'idcard') {
		$scope.operation = "身份证登录";
	} else if(appData.loginType == 'cloud') {
		$scope.operation = "随申办登录";
	} else if(appData.loginType == 'medical') {
		$scope.operation = "社保卡登录";
	}
	$scope.loginType = appData.loginType;
	//获取token ------2、比对成功后，根据tokenSNO获取access_token
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
				console.log(res);
				if(res.SUCCESS === true) {
					appData.token = res.accessToken;
					if(appData.type == "info") {
						$state.go("infoChoose");
					} else if(appData.type == "reduce") {
						$state.go("reduceChoose");
					} else if(appData.type == "medicalDetails") {
						$state.go("medicalDetails");
					} else if(appData.type == "accountSettlement") {
						$state.go("accountSettlement");
					} else if(appData.source = "idcardOrCitizen" && appData.handle == "self") {
						$scope.searchInsuredInfo();
					} else if(appData.source = "idcardOrCitizen" && appData.handle == "agent") {
						$state.go("swipeAgentIdcard");
					}
				} else {
					$scope.isAlert = true;
					$scope.msg = "数据加载异常,请重试";
					$scope.alertConfirm = function() {
						$state.go("loginType");
					}
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
		var capturePhoto = photograph;
		var rec = $.ajax({
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getTokenSNO.do",
			type: "post",
			dataType: "json",
			//					jsonp: "jsonpCallback",
			data: {
				name: appData.licenseName,
				idCard: appData.licenseNumber,
				facePhoto: capturePhoto,
				copyIDPhoto: idCardPhoto
			},
			success: function(res) {
				console.log(res);
				if(res.SUCCESS === true && res.verify === 1) {
					$scope.getAccessToken(res.tokenSNO);
				} else {
					$scope.isAlert = true;
					$scope.msg = "数据加载异常,请重试";
					$scope.alertConfirm = function() {
						$state.go("loginType");
					}
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
	// 参保人信息查询函数
	$scope.searchInsuredInfo = function() {
		$http.jsonp($.getConfigMsg.preUrl + "/aci/workPlatform/medicalInsurance/queryAccountInfo.do", {
			params: {
				jsonpCallback: "JSON_CALLBACK",
				identNo: appData.licenseNumber // "310104194906012828"
			}
		}).success(function(returnData) {
			if(returnData[0].cbrxxs) {
				appData.zhh = returnData[0].cbrxxs[0].cbrxx[0].zhh;
				console.log(returnData);
				$state.go("bookMakingTakePhoto");
			} else {
				$scope.isAlert = true; // 是否显示打印提示框
				$scope.msg = returnData[0].errmsg;
			}
			console.log(returnData);
		}).error(function(err) {
			$scope.isAlert = true; // 是否显示打印提示框
			$scope.msg = "查询失败";
		});
	}
	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
			if(appData.source == "idcardOrCitizen" && appData.handle == "self") {
				$scope.searchInsuredInfo();
			} else if(appData.source == "idcardOrCitizen" && appData.handle == "agent") {
				$state.go("swipeAgentIdcard");
			} else {
				$scope.loginType = 'recognition';
				//				appData.licenseNumber = '310228198808070818';
				//				appData.licenseName = '陈雷';
				//				$scope.getTokenSNO(photo, photo);
			}
		} else {
			layer.msg("很抱歉，未获取到相关信息，请重试。");
		}
	}
	//	$scope.idcardLogin();
	$scope.sscardLogin = function(info) {
		if(info) {
			// 存储社保卡信息
			appData.licenseNumber = info.Ssn;
			appData.licenseName = info.PeopleName;
			$.device.ssCardClose();
			$scope.getTokenSNO(photo, photo);
		} else {
			layer.msg("很抱歉，未获取到相关信息，请重试。")
		}
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.alertCancel = function() {
		$scope.isAlert = false;
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		if(appData.type == "info") {
			$state.go("infoChoose");
		} else if(appData.type == "reduce") {
			$state.go("reduceChoose");
		} else if(appData.type == "medicalDetails") {
			$state.go("medicalDetails");
		} else if(appData.type == "accountSettlement") {
			$state.go("accountSettlement");
		}
	}
	$scope.source = appData.source;
	$scope.prevStep = function() {
		if($scope.source == "idcardOrCitizen") {
			$state.go("idcardOrCitizen");
			return;
		}
		$state.go("main");
	}

	$scope.citizenLogin = function(info) {
		var idcardInfo = info.result.data;
		appData.licenseName = idcardInfo.realname;
		appData.licenseNumber = idcardInfo.idcard;
		$scope.getTokenSNO(photo, photo);
		try {
			$scope.$apply();
		} catch(e) {}
	}
});

app.controller('swipeAgentIdcard', function($scope, $http, $state, appData, appFactory) {
	$scope.isShowChoiceLoginType = true;
	$scope.isAlert = false;
	$scope.msg = "";
	$scope.operation = "请选择代办人登录方式";
	$scope.loginType = appData.loginType;
	$scope.choiceLoginType = function(loginType) {
		if(loginType == "idcard") {
			$scope.operation = "请刷代理人身份证";
			$scope.loginType = "idcard";
			$scope.isShowChoiceLoginType = false;
		} else if(loginType == "cloud") {
			$scope.operation = "请按照导图扫描二维码";
			$scope.loginType = "cloud";
			$scope.isShowChoiceLoginType = false;
		}
	}
	// 参保人信息查询函数
	$scope.searchInsuredInfo = function() {
		$http.jsonp($.getConfigMsg.preUrl + "/aci/workPlatform/medicalInsurance/queryAccountInfo.do", {
			params: {
				jsonpCallback: "JSON_CALLBACK",
				identNo: appData.licenseNumber // "310104194906012828"
			}
		}).success(function(returnData) {
			if(returnData[0].cbrxxs) {
				appData.zhh = returnData[0].cbrxxs[0].cbrxx[0].zhh;
				console.log(appData.zhh);
				$state.go("bookMakingTakePhoto");
			} else {
				$scope.isAlert = true;
				$scope.msg = returnData[0].errmsg;
			}
			console.log(returnData);
		}).error(function(err) {
			$scope.isAlert = true;
			$scope.msg = "查询失败";
		});
	}
	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.agentImage = images;
			appData.agentNumber = info.Number;
			appData.agentName = info.Name;
			$scope.searchInsuredInfo();
		} else {
			layer.msg("很抱歉，未获取到相关信息，请重试。");
		}
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.alertCancel = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		if($scope.isShowChoiceLoginType == true) {
			if(appData.source == 'idcardOrCitizen') {
				$state.go('idcardOrCitizen');
			} else if(appData.source == 'bookMakingChoose') {
				$state.go('bookMakingChoose');
			} else {
				$state.go('bookMaking')
			}
		} else if($scope.isShowChoiceLoginType == false) {
			$scope.isShowChoiceLoginType = true;
			$scope.operation = "请选择刷代理人身份证方式";
			return;
		}
	}

	$scope.citizenLogin = function(info) {
		if(info) {
			var idcardInfo = info.result.data;
			appData.agentNumber = idcardInfo.realname;
			appData.agentName = idcardInfo.idcard;
			$scope.searchInsuredInfo();
		} else {
			layer.msg("很抱歉，未获取到相关信息，请重试。");
		}
	}
});

app.controller('bookMaking', function($scope, $state, appData, $sce) {
	$scope.isAlert = false;
	$scope.operation = "请选择刷卡方式";
	appData.SwipeType = ""; // 存储刷卡方式
	appData.handle = ""; // 存储办理人    "本人"或"代理人"
	$scope.chooseSwipeType = function(SwipeType, handle) {
		appData.SwipeType = SwipeType;
		appData.handle = handle;
		if(appData.SwipeType == "idCard") {
			$state.go("idcardOrCitizen");
		} else if(appData.SwipeType == "ybCard") {
			$scope.isAlert = true;
			$scope.msg = "功能暂未开放";
		} else if(appData.SwipeType == "sbCard") {
			$state.go("bookMakingChoose");
		}
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.alertCancel = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		$state.go('main');
	}
});

app.controller('idcardOrCitizen', function($scope, $state, appData, $sce) {
	$scope.operation = "请选择办理人登录方式";
	$scope.choiceLoginType = function(loginType) {
		appData.source = "idcardOrCitizen";
		if(loginType == "idcard") {
			appData.loginType = "idcard";
			$state.go("login");
		} else if(loginType == "citizen") {
			appData.loginType = "cloud";
			$state.go("login");
		}
	}
	$scope.prevStep = function() {
		$state.go('bookMaking');
	}
});

app.controller('bookMakingChoose', function($scope, $http, $state, appData, $sce) {
	$scope.isAlert = false;
	$scope.msg = "";
	$scope.isShowSscard = true;
	$scope.isShowIdcard = false;
	$scope.operation = "请刷社保卡";
	$scope.sscardInfo = "";
	$scope.handle = appData.handle; // "本人"或者"代办"
	appData.agentNumber = ""; // 代理人身份证号
	appData.agentName = ""; // 代理人姓名
	if(appData.SwipeType == 'sbCard') {
		$scope.operation = "请插入办理人社保卡";
	} else if(appData.SwipeType == 'ybCard') {
		$scope.operation = "请插入办理人医保卡";
	}
	// 参保人信息查询函数
	$scope.searchInsuredInfo = function() {
		$http.jsonp($.getConfigMsg.preUrl + "/aci/workPlatform/medicalInsurance/queryAccountInfo.do", {
			params: {
				jsonpCallback: "JSON_CALLBACK",
				identNo: appData.licenseNumber // "310104194906012828"
			}
		}).success(function(returnData) {
			if(returnData[0].cbrxxs) {
				appData.zhh = returnData[0].cbrxxs[0].cbrxx[0].zhh;
				console.log(appData.zhh);
				$state.go("bookMakingTakePhoto");
			} else {
				$scope.isAlert = true; // 是否显示打印提示框
				$scope.msg = returnData[0].errmsg;
			}
			console.log(returnData);
		}).error(function(err) {
			$scope.isAlert = true; // 是否显示打印提示框
			$scope.msg = "查询失败";
		});
	}

	$scope.idcardLogin = function(info, images) {
		if(info) {
			appData.agentNumber = info.Number;
			appData.agentName = info.Name;
			$scope.searchInsuredInfo();
		} else {
			layer.msg("很抱歉，未获取到相关信息，请重试。");
		}
	}

	$scope.sscardLogin = function(info) {
		if(info) {
			// 存储社保卡信息
			$scope.sscardInfo = info;
			appData.licenseNumber = info.Ssn;
			appData.licenseName = info.PeopleName;
			appData.sscardInfo = $scope.sscardInfo;
			if(appData.handle == "self") {
				$.device.ssCardClose();
				$scope.$apply();
				$scope.isShowSscard = false;
				$scope.searchInsuredInfo();
			} else if(appData.handle == "agent") {
				//				$scope.isShowSscard = false;
				//				$scope.isShowIdcard = true;
				$.device.ssCardClose();
				$scope.$apply();
				$scope.isShowSscard = false;
				appData.source = "bookMakingChoose"; // 用于在citizenCloud.js中做判断
				$state.go("swipeAgentIdcard");
			}
		} else {
			layer.msg("很抱歉，未获取到相关信息，请重试。")
		}
	}

	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go('bookMaking');
	}

	$scope.alertCancel = function() {
		$scope.isAlert = false;
		$state.go('bookMaking');
	}

	$scope.prevStep = function() {
		$state.go('bookMaking');
	}
});

app.controller('bookMakingReason', function($scope, $http, $state, appData, $sce, appFactory) {
	$scope.operation = '请选择制册原因';
	$scope.updateRecordBookReason = ""; // 补册原因
	$scope.isAlert = false; // 是否显示打印提示框
	$scope.isShowPrint = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$state.go("main");
	}
	$scope.choiceReason = function(reason) {
		if(reason == "Renewed") {
			// 存储制册原因为"用完换新"
			$scope.updateRecordBookReason = "07";
			$scope.updateRecordBook();
		} else if(reason == "newMore") {
			// 存储制册原因为"新增制册"
			$scope.updateRecordBookReason = "04";
			$scope.updateRecordBook();
		} else if(reason == "Reissue") {
			// 存储制册原因为"遗失补办"
			$scope.updateRecordBookReason = "01";
			$scope.updateRecordBook();
		}
	}
	// 记录册补册校验
	//	$scope.updateRecordBookCheck = function () {
	//		$http.jsonp("http://192.168.1.142:8080/ac-product/aci/workPlatform/medicalInsurance/updateRecordBookCheck.do", {
	//			params: {
	//				jsonpCallback: "JSON_CALLBACK",
	//				knsj: appData.sscardInfo // appData.licenseNumber
	//			}
	//		}).success(function(returnData) {
	//			if(returnData.wxzcbz == "1") {
	//				$scope.isAlert = true;
	//				$scope.msg = "该参保人无需制册！";
	//			} else if(returnData.sfjxzcbz == "1") {
	//				$scope.sfjxzcbz = returnData.sfjxzcbz;
	//				$scope.isAlert = true; // 是否显示打印提示框
	//				$scope.msg = "外来人员本年度可暂不用册，是否继续制作?";
	//			}
	//		}).error(function(err) {
	//			$scope.isAlert = true; // 是否显示打印提示框
	//			$scope.msg = "未找到该参保人";
	//		});
	//	}

	// 记录册更换
	$scope.updateRecordBook = function() {
		$http.jsonp($.getConfigMsg.preUrl + "/aci/workPlatform/medicalInsurance/updateRecordBook.do", {
			params: {
				jsonpCallback: "JSON_CALLBACK",
				zhh: appData.zhh,
				bcyy: $scope.updateRecordBookReason,
				wtrxm: appData.agentName || '', // "李华熙",
				wtrsfzh: appData.agentNumber || '' // "520222199406140030"
			}
		}).success(function(returnData) {
			console.log(returnData);
			console.log("账户号：" + appData.zhh);
			if(!returnData[0].syscode) {
				$scope.newBookBh = returnData[0].jlch; // 新就医记录册编号
				$scope.newBookKh = returnData[0].kh; // 新就医记录册卡号
				$scope.userXb = returnData[0].xb == "1" ? "男" : "女";
				$scope.userName = returnData[0].xm;
				$scope.userAge = returnData[0].sfzh.substring(6, 10) + "年" + returnData[0].sfzh.substring(10, 12) + "月";
				var bookMedicalPort = "COM" + (window.external.GetConfig('bookMedicalPort') || "4");
				$.device.serialPortOpen(bookMedicalPort, 9600, 8, function() {}) //开启串口
				$scope.confirm = function() {
					$scope.isAlert = true;
					$scope.msg = "<p>正在制册中，请等待…</p><p>友情提示：请从自助终端右侧自助领取塑料套</p>";
					var lodop = $.device.printGetLodop('medical')
					lodop.SET_PRINTER_INDEX('DASCOM DS-7860');
					lodop.ADD_PRINT_TEXT(230, 140, 670, 50, $scope.newBookBh);
					lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
					lodop.ADD_PRINT_TEXT(270, 140, 670, 30, $scope.userName);
					lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
					lodop.ADD_PRINT_TEXT(305, 140, 670, 30, $scope.userXb);
					lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
					lodop.ADD_PRINT_TEXT(340, 140, 670, 30, $scope.newBookKh);
					lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
					lodop.ADD_PRINT_TEXT(375, 140, 670, 30, $scope.userAge);
					lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
					lodop.PRINT(); //打印
					saveDeviceStatus("MedicalInsuranc", 0, "正常", 0, 0, 0, 1);
					//模块使用记录
					$scope.jsonStr = {
						SUCCESS: "true",
						data: {
							name: '办理门急诊就医记录册',
							newBookBh: $scope.newBookBh,
							userName: $scope.userName,
							userXb: $scope.userXb,
							newBookKh: $scope.newBookKh,
							userAge: returnData[0].sfzh
						}
					}
					$scope.name = appData.agentName || appData.licenseName;
					$scope.Number = appData.agentNumber || appData.licenseNumber;
					recordUsingHistory('医保服务', '打印', '办理门急诊就医记录册', $scope.name, $scope.Number, '', '', JSON.stringify($scope.jsonStr));
					setTimeout(function() {
						$.device.serialPortWriteString("S0001#") //发送指令
					}, 0)
					//  alert('打印完毕，关闭串口')
					//  $.device.serialPortClose();
					setTimeout(function() {
						$state.go("main");
					}, 5000);
				};
				$scope.confirm();
			} else if(returnData[0].syscode) {
				if(returnData[0].errmsg == "[E001]参保人没有记录册，只允许新增制册") {
					$scope.isAlert = true;
					$scope.msg = "参保人没有记录册，只允许新增制册";
				} else if(returnData[0].errmsg == "参保人本年度制册数超过医保审核标准，请至市医保中心审核后制册") {
					$scope.isAlert = true;
					$scope.msg = "参保人本年度制册数超过医保审核标准，请至市医保中心审核后制册";
				} else if(returnData[0].errmsg == "[E001]该参保人账户撤销已清算，不能制册。") {
					$scope.isAlert = true;
					$scope.msg = "该参保人账户撤销已清算，不能制册";
				} else if(returnData[0].errmsg == "[E001]参保人已有记录册，不允许新增制册") {
					$scope.isAlert = true;
					$scope.msg = "参保人已有记录册，不允许新增制册";
				}
			}
		}).error(function(err) {
			$scope.isAlert = true; // 是否显示打印提示框
			$scope.msg = "未找到该参保人";
		});
	}

	$scope.alertConfirm = function() {
		if($scope.sfjxzcbz == "1") {
			$scope.isAlert = false;
		} else {
			$scope.isAlert = false;
		}
	}

	$scope.alertCancel = function() {
		if($scope.sfjxzcbz == "1") {
			$scope.isAlert = false;
			$state.go("bookMaking");
		} else {
			$scope.isAlert = false;
		}
	}

	$scope.prevStep = function() {
		$state.go('bookMaking');
		$scope.$apply();
	}
});

app.controller('bookMakingTakePhoto', function($scope, $state, appData, $sce) {
	$scope.isShowPrint = false;
	$scope.operation = "请看摄像头";
	$scope.camera = true;
	$scope.imageData = null;
	$scope.__cameraPos = $scope.cameraPos || {
		width: 640,
		height: 480,
		x: 600,
		y: 310
	};
	//初始化摄像头
	$.device.Camera_Init($scope.__cameraPos.width, $scope.__cameraPos.height, $scope.__cameraPos.x, $scope.__cameraPos.y); //初始化摄像头
	var camera = window.external.GetConfig('camera');
	var index = window.external.GetConfig('resolution') || 1;
	$.device.Camera_Link(camera, index); //初始化摄像头
	$.device.Camera_Show();
	$scope.$on("$destroy", function() {
		$.device.Camera_Hide();
	});
	$scope.capture = function() { //拍照
		$scope.capturePhoto = $.device.Camera_Base64();
		$scope.imageData = "data:image/png;base64," + $scope.capturePhoto;
		$.device.Camera_Hide();
		$scope.camera = false;
	};
	$scope.reCapture = function() {
		$.device.Camera_Show();
		$scope.camera = true;
	};
	$scope.confirm = function() {
		$state.go("bookMakingReason");
	};
});
//给外部接入
app.controller("iframe", function($scope, $state, appData, $sce, $timeout, $location) {
	if(appData.type == "" || appData.type == undefined) {
		appData.type = $location.search().type;
		if(appData.type == "info") {
			appData.funName = "医保个人信息";
		} else if(appData.type == "medicalDetails") {
			appData.funName = "医保就医明细查询";
		}
	}
	if(appData.licenseNumber == "" || appData.licenseNumber == undefined) {
		appData.licenseNumber = $location.search().Number;
	}
	if(appData.licenseName == "" || appData.licenseName == undefined) {
		appData.licenseName = $location.search().Name;
	}

	//获取token ------2、比对成功后，根据tokenSNO获取access_token
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
				console.log(res);
				if(res.SUCCESS === true) {
					appData.token = res.accessToken;
					if(appData.type == "info") {
						$state.go("infoChoose");
					} else if(appData.type == "medicalDetails") {
						$state.go("medicalDetails");
					}
				} else {
					$scope.isAlert = true;
					$scope.msg = "数据加载异常,请重试";
					$scope.alertConfirm = function() {
						$scope.isAlert = false;
					}
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
		var capturePhoto = photograph;
		var rec = $.ajax({
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getTokenSNO.do",
			type: "post",
			dataType: "json",
			data: {
				name: appData.licenseName,
				idCard: appData.licenseNumber,
				facePhoto: capturePhoto,
				copyIDPhoto: idCardPhoto
			},
			success: function(res) {
				console.log(res);
				if(res.SUCCESS === true && res.verify === 1) {
					$scope.getAccessToken(res.tokenSNO);
				} else {
					$scope.isAlert = true;
					$scope.msg = "数据加载异常,请重试";
					$scope.alertConfirm = function() {
						$scope.isAlert = false;
					}
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
	$scope.getTokenSNO(photo, photo);
});
//个人信息查询 ---选择项
app.controller('infoChoose', function($scope, $state, appData, $sce) {
	var date = new Date();
	var tMonth = date.getMonth();
	var tYear = date.getFullYear();
	if(tMonth > 4) {
		$scope.year = tYear;
	} else {
		$scope.year = tYear - 1;
	}
	$scope.show = false;
	PublicchoiceById('ybbf');
	PublicchoiceById('year');
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.count = "请输入";
	$('#ybbf a').eq(0).addClass('in');
	$('#year a').eq(0).addClass('in');
	$scope.keybordArr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 'X', 0, '删除'];
	$scope.keyboardInput = function(e) { //键盘输入
		var keycode = window.event ? e.keyCode : e.which;
		if(keycode >= 48 && keycode <= 57) {} else if(keycode == 8) {} else {
			console.log(e);
			$scope.count = $scope.count.substring(0, $scope.count.length - 1);
		}
	}
	$scope.phoneInput = function(e) { //键盘输入
		var keycode = window.event ? e.keyCode : e.which;
		if(keycode >= 48 && keycode <= 57) {} else if(keycode == 8) {} else {
			console.log(e);
			$scope.phone = $scope.phone.substring(0, $scope.phone.length - 1);
		}
	}
	$scope.inputNumber = function(item) { //软键盘输入
		if($scope.count == "请输入") {
			$scope.count = "";
		} else if(item === '删除') {
			$scope.count = $scope.count.substring(0, $scope.count.length - 1);
		} else {
			$scope.count += item;
			console.log($scope.count);
		}
	}
	$scope.inputPhone = function(item) { //软键盘输入
		if($scope.phone == "请输入") {
			$scope.phone = "";
		} else if(item === '删除') {
			$scope.phone = $scope.phone.substring(0, $scope.phone.length - 1);
		} else {
			$scope.phone += item;
			console.log($scope.phone);
		}
	}
	$scope.prevStep = function() {
		$state.go('main');
	}
	$scope.nextStep = function() {
		if($('#ybbf a.in li').text() == undefined || $('#ybbf a.in li').text() == "") {
			$scope.isAlert = true;
			$scope.msg = "请选择医保办法";
			return;
		}
		if($('#year a.in li').text() == undefined || $('#year a.in li').text() == "") {
			$scope.isAlert = true;
			$scope.msg = "请选择年份";
			return;
		}
		if($scope.count == undefined || $scope.count == "") {
			$scope.isAlert = true;
			$scope.msg = "请输入手机号";
			return;
		}
		appData.infoyear = $('#year a.in li').text().split('年')[0];
		appData.ybbf = ($('#ybbf a.in li').text() == "职工保险") ? "0" : "1";
		appData.mobile = $scope.count;
		$state.go('info');
	}
});

//医保个人信息
app.controller("info", function($scope, $state, appData, $sce, appFactory) {
	//获取网点信息
	$scope.qutletsMsg = '';
	console.log(jQuery.getConfigMsg.districtAndCountyVal);
	$scope.GetConfigLoads = function() {
		$.config.load('Outlets', function(datas) {
			$scope.qutletsMsg = decodeURI(datas);
			console.log($scope.qutletsMsg);
		});
	}
	$scope.GetConfigLoads();
	$scope.nextText = "打印";
	$scope.isLoding = false;
	var date = new Date();
	var tMonth = date.getMonth();
	var tYear = date.getFullYear();
	if(tMonth > 4) {
		$scope.year = tYear;
	} else {
		$scope.year = tYear - 1;
	}
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go("infoChoose");
	}
	appFactory.pro_fetch(appData.licenseNumber, appData.licenseName, appData.token, function(data) {
		appData.zhh = data;
		appFactory.getOrganByAccount(appData.zhh, function(dataJson) {
			appData.dwbm = dataJson[0].dwbm;
			appData.dwmc = dataJson[0].dwmc;
			$scope.dwbm = appData.dwbm;
			$scope.dwmc = appData.dwmc;
		});
		$scope.medicalInfo();
	}, function(err) {
		$scope.isAlert = true;
		$scope.msg = "未查询到信息 ，请重试"
	});

	$scope.medicalInfo = function(zhh) {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/medicalInsurance/urbanInsurance.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				zhh: appData.zhh,
				ndbz: Math.abs(appData.infoyear - $scope.year),
				ybbf: appData.ybbf,
				mkbz: 3
			},
			success: function(dataJson) {
				console.log(dataJson[0]);
				var info = dataJson[0];
				$scope.isLoding = true;
				if(dataJson.length > 0) {
					$scope.xm = info.xm;
					$scope.xb = ((parseInt(appData.licenseNumber.substring(16, 17)) % 2) == 0) ? "女" : "男";
					$scope.zhh = info.zhh;
					$scope.sfzh = info.sfzh;
					$scope.zhzt = info.zhzt;
					$scope.bjdx = info.zhbzsm.substring(8, 13);
					$scope.fcqk = info.zhbzsm.substring(27, 29);
					$scope.rylb = info.rylbsm;
					$scope.tsry = info.zhbzsm.substring(59, 64);
					$scope.gwyqk = info.zhbzsm.substring(13, 17);
					$scope.ztqk = info.zhbzsm.substring(0, 2);
					$scope.ybbf = info.zhbzsm.substring(70, 72);
					$scope.zrzhzjze = toDecimal2(info.zrzhzjze);
					$scope.dnzhye = toDecimal2(info.dnzhye);
					$scope.zhlx = toDecimal2(info.zhlx);
					$scope.lnzhye = toDecimal2(info.lnzhye);
					$scope.dnzh_lj = toDecimal2(info.dnzh_lj);
					$scope.lnzh_lj = toDecimal2(info.lnzh_lj);
					$scope.xj_lj = toDecimal2(info.xj_lj);
					$scope.fj_lj = toDecimal2(info.fj_lj);
					$scope.tc_lj = toDecimal2(info.tc_lj);
					$scope.qfxs_fdxx_fy_lj = toDecimal2(info.qfxs_fdxx_fy_lj);
					appData.tableInfo = "<style>table{font-size:14px;border-collapse: collapse; width:680px} table td{border:1px solid} table th{text-align:right;border:1px solid}</style><table>" +
						"<tr><th>注入资金总额：</th><td>" + toDecimal2(info.zrzhzjze) + "</td><th>账户利息：</th><td>" + toDecimal2(info.zhlx) + "</td></tr>" +
						"<tr><th>当年账户余额：</th><td>" + toDecimal2(info.dnzhye) + "</td><th>历年账户余额：</th><td>" + toDecimal2(info.lnzhye) + "</td></tr>" +
						"<tr><th>当年账户累计：</th><td>" + toDecimal2(info.dnzh_lj) + "</td><th>历年账户累计：</th><td>" + toDecimal2(info.lnzh_lj) + "</td></tr>" +
						"<tr><th>现金支付累计：</th><td>" + toDecimal2(info.xj_lj) + "</td><th>统筹支付累计：</th><td>" + toDecimal2(info.tc_lj) + "</td></tr>" +
						"<tr><th>地方附加支付累计：</th><td>" + toDecimal2(info.fj_lj) + "</td><th>起付线上封顶线下医疗费用累计：</th><td>" + toDecimal2(info.qfxs_fdxx_fy_lj) + "</td></tr>" +
						"<tr><th>门急诊当年账户支付累计：</th><td>" + toDecimal2(info.mjz_dnzh_lj) + "</td><th>商业保险历年账户支付金额：</th><td>" + toDecimal2(info.sybx_qnzh_zf) + "</td></tr>" +
						"<tr><th>门急诊及药店购药自负段累计：</th><td>" + toDecimal2(info.mjz_zfdxj_lj) + "</td><th>门急诊历年账户支付累计：</th><td>" + toDecimal2(info.mjz_lnzh_lj) + "</td></tr>" +
						"<tr><th>门急诊自负段历年账户支付累计：</th><td>" + toDecimal2(info.mjz_zfdlnzh_lj) + "</td><th>门急诊分类自负段累计：</th><td>" + toDecimal2(info.mjz_flzf_lj) + "</td></tr>" +
						"<tr><th>门急诊附加段现金累计：</th><td>" + toDecimal2(info.mjz_fjdxj_lj) + "</td><th>门急诊附加段历年账户支付累计：</th><td>" + toDecimal2(info.mjz_fjdlnzh_lj) + "</td></tr>" +
						"<tr><th>门急诊统筹支付累计：</th><td>" + toDecimal2(info.mjz_tc_lj) + "</td><th>门急诊附加支付累计：</th><td>" + toDecimal2(info.mjz_fj_lj) + "</td></tr>" +
						"<tr><th>门诊大病统筹段历年账户支付累计：</th><td>" + toDecimal2(info.db_tcdlnzh_lj) + "</td><th>门诊大病统筹段现金支付累计：</th><td>" + toDecimal2(info.db_tcdxj_lj) + "</td></tr>" +
						"<tr><th>门诊大病统筹支付累计：</th><td>" + toDecimal2(info.db_tc_lj) + "</td><th>门诊大病附加段历年账户支付累计：</th><td>" + toDecimal2(info.db_fjdlnzh_lj) + "</td></tr>" +
						"<tr><th>门诊大病附加段现金支付累计：</th><td>" + toDecimal2(info.db_fjdxj_lj) + "</td><th>门诊大病附加支付累计：</th><td>" + toDecimal2(info.db_fj_lj) + "</td></tr>" +
						"<tr><th>门诊大病分类自负累计：</th><td>" + toDecimal2(info.db_flzf_lj) + "</td><th>住院天数：</th><td>" + toDecimal2(info.zyts) + "</td></tr>" +
						"<tr><th>住院起付段历年账户支付累计：</th><td>" + toDecimal2(info.zy_qfdlnzh_lj) + "</td><th>家床起付段历年账户支付累计：</th><td>" + toDecimal2(info.jc_qfdlnzh_lj) + "</td></tr>" +
						"<tr><th>住院起付段现金支付累计：</th><td>" + toDecimal2(info.zy_qfdxj_lj) + "</td><th>家床起付段现金支付累计：</th><td>" + toDecimal2(info.jc_qfdxj_lj) + "</td></tr>" +
						"<tr><th>住院统筹段历年账户支付累计：</th><td>" + toDecimal2(info.zy_tcdlnzh_lj) + "</td><th>家床统筹段历年账户支付累计：</th><td>" + toDecimal2(info.jc_tcdlnzh_lj) + "</td></tr>" +
						"<tr><th>住院统筹段现金支付累计：</th><td>" + toDecimal2(info.zy_tcdxj_lj) + "</td><th>家床统筹段现金支付累计：</th><td>" + toDecimal2(info.jc_tcdxj_lj) + "</td></tr>" +
						"<tr><th>住院统筹支付累计：</th><td>" + toDecimal2(info.zy_tc_lj) + "</td><th>家床统筹支付累计：</th><td>" + toDecimal2(info.jc_tc_lj) + "</td></tr>" +
						"<tr><th>住院附加段历年账户支付累计：</th><td>" + toDecimal2(info.zy_fjdlnzh_lj) + "</td><th>家床附加段历年账户支付累计：</th><td>" + toDecimal2(info.jc_fjdlnzh_lj) + "</td></tr>" +
						"<tr><th>住院附加段现金支付累计：</th><td>" + toDecimal2(info.zy_fjdxj_lj) + "</td><th>家床附加段现金支付累计：</th><td>" + toDecimal2(info.jc_fjdxj_lj) + "</td></tr>" +
						"<tr><th>住院附加支付累计：</th><td>" + toDecimal2(info.zy_fj_lj) + "</td><th>家床附加支付累计：</th><td>" + toDecimal2(info.jc_fj_lj) + "</td></tr>" +
						"<tr><th>住院分类自负累计：</th><td>" + toDecimal2(info.zy_flzf_lj) + "</td><th>家床分类自负累计：</th><td>" + toDecimal2(info.jc_flzf_lj) + "</td></tr>" +
						"<tr><th>急观起付段历年账户支付累计：</th><td>" + toDecimal2(info.jg_qfdlnzh_lj) + "</td><th>药店购药当年账户支付累计：</th><td>" + toDecimal2(info.yd_dnzh_lj) + "</td></tr>" +
						"<tr><th>急观起付段现金支付累计：</th><td>" + toDecimal2(info.jg_qfdxj_lj) + "</td><th>药店购药历年账户支付累计：</th><td>" + toDecimal2(info.yd_lnzh_lj) + "</td></tr>" +
						"<tr><th>急观统筹段历年账户支付累计：</th><td>" + toDecimal2(info.jg_tcdlnzh_lj) + "</td><th>药店购药自负段现金支付累计：</th><td>" + toDecimal2(info.gy_zfdxj_lj) + "</td></tr>" +
						"<tr><th>急观统筹段现金支付累计：</th><td>" + toDecimal2(info.jg_tcdxj_lj) + "</td><th>药店购药分类自负累计：</th><td>" + toDecimal2(info.gy_flzf_lj) + "</td></tr>" +
						"<tr><th>急观统筹支付累计：</th><td>" + toDecimal2(info.jg_tc_lj) + "</td><th>高价药门急诊当年账户支付累计：</th><td>" + toDecimal2(info.gjy_mjz_dnzh_lj) + "</td></tr>" +
						"<tr><th>急观附加段历年账户支付累计：</th><td>" + toDecimal2(info.jg_fjdlnzh_lj) + "</td><th>高价药门急诊历年支付累计：</th><td>" + toDecimal2(info.gjy_mjz_lnzh_lj) + "</td></tr>" +
						"<tr><th>急观附加段现金支付累计：</th><td>" + toDecimal2(info.jg_fjdxj_lj) + "</td><th>高价药门急诊自负段历年账户支付累计：</th><td>" + toDecimal2(info.gjy_mjz_zfdlnzh_lj) + "</td></tr>" +
						"<tr><th>急观附加支付累计：</th><td>" + toDecimal2(info.jg_fj_lj) + "</td><th>高价药门急诊自负段现金支付累计：</th><td>" + toDecimal2(info.gjy_mjz_zfdxj_lj) + "</td></tr>" +
						"<tr><th>急观分类自负累计：</th><td>" + toDecimal2(info.jg_flzf_lj) + "</td><th>高价药门急诊附加段历年账户支付累计：</th><td>" + toDecimal2(info.gjy_mjz_fjdlnzh_lj) + "</td></tr>" +
						"<tr><th>高价药住院起付段历年账户支付累计：</th><td>" + toDecimal2(info.gjy_zy_qfdlnzh_lj) + "</td><th>高价药门急诊附加段现金支付累计：</th><td>" + toDecimal2(info.gjy_mjz_fjdxj_lj) + "</td></tr>" +
						"<tr><th>高价药住院起付段现金支付累计：</th><td>" + toDecimal2(info.gjy_zy_qfdxj_lj) + "</td><th>高价药门急诊附加累计：</th><td>" + toDecimal2(info.gjy_mjz_fj_lj) + "</td></tr>" +
						"<tr><th>高价药住院统筹段历年账户支付累计：</th><td>" + toDecimal2(info.gjy_zy_tcdlnzh_lj) + "</td><th>高价药门急诊统筹段现金支付累计：</th><td>" + toDecimal2(info.gjy_mjz_tcdxj_lj) + "</td></tr>" +
						"<tr><th>高价药住院统筹段现金支付累计：</th><td>" + toDecimal2(info.gjy_zy_tcdxj_lj) + "</td><th>高价药门急诊统筹累计：</th><td>" + toDecimal2(info.gjy_mjz_tc_lj) + "</td></tr>" +
						"<tr><th>高价药住院统筹累计：</th><td>" + toDecimal2(info.gjy_zy_tc_lj) + "</td><th>高价药住院附加段现金支付累计：</th><td>" + toDecimal2(info.gjy_zy_fjdxj_lj) + "</td></tr>" +
						"<tr><th>高价药住院附加段历年账户支付累计：</th><td>" + toDecimal2(info.gjy_zy_fjdlnzh_lj) + "</td><th>高价药住院附加累计：</th><td>" + toDecimal2(info.gjy_zy_fj_lj) + "</td></tr></table>";
				}
			},
			error: function(err) {
				$scope.isLoding = true;
				console.log(err);
			}
		});
	}

	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
	$scope.prevStep = function() {
		$state.go("main");
	}
	$scope.print = function() {
		$scope.isShowPrint = "show";
		setTimeout(function() {
			var LODOP = $.device.printGetLodop('');
			var date = new Date();
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			var styletablet='<style>body{margin: 0px 0px 0px 55px;}#toptable th {border:none;text-align:left;}</style>'
			var tabletopH1='<h1 style="width: 680px;text-align: center;margin: 10px 0px 10px 0px;font-size: 20px;">个人账户信息查询表(' + appData.infoyear + ')</h1>';
            var tabletop='<table frame=void id="toptable">'+
            '<tr><th>医保办法：'+$scope.ybbf+'</th><th>姓名：'+$scope.xm+'</th>'+
            '<th>身份证号：'+appData.licenseNumber+'</th><th>人员类别：'+$scope.rylb+'</th></tr>'+
            '<tr><th>卡状态：'+$scope.zhzt+'</th><th>账户状态：'+$scope.fcqk+'</th>'+
            '<th>保健状态：'+$scope.bjdx+'</th><th>特殊人员：'+$scope.tsry+'</th></th></tr>'+
            '<th>封存情况：'+$scope.fcqk+'</th><th></th><th>职退情况：'+$scope.ztqk+'</th><th>单位码：'+appData.dwbm+'</th></tr>'+
            '<tr><th colspan="3">单位名称：'+appData.dwmc+'</th></tr></table>';
            var tablebottom='<table frame=void id="toptable">'+
            '<tr><th>区县：'+jQuery.getConfigMsg.districtAndCountyVal+'</th><th>渠道：“一网通办”自助终端</th><th>打印时间：'+date.getFullYear() + "年" + month + "月" + date.getDate() + "日"+'</th></tr>'+
            '<tr><th>网点：'+$scope.qutletsMsg+'</th></tr></table>'+
            '<p style="margin:20px 0px 0px 20px;font-size: 14px;">*本查询仅做信息查询使用</p>';
            var Html=styletablet+tabletopH1+tabletop+appData.tableInfo+tablebottom;
            var htmls = '<html><head><meta charset="UTF-8"></head><body>' + Html+ '</body></html>';
            console.log(htmls);
            $.device.printerHtml(htmls);
//			LODOP.ADD_PRINT_TEXT(32, 250, 430, 50, "个人账户信息查询表(" + appData.infoyear + ")");
//			LODOP.SET_PRINT_STYLE("BOLD", 1);
//			LODOP.SET_PRINT_STYLEA(0, "FontSize", 20);
//			//		LODOP.ADD_PRINT_TEXT(83, 43, 154, 20, "卡号：" + $scope.zhh);
//			//		LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
//			LODOP.ADD_PRINT_TEXT(83, 43, 115, 20, "医保办法：" + $scope.ybbf);
//			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
//			LODOP.ADD_PRINT_TEXT(83, 207, 142, 20, "姓名：" + $scope.xm);
//			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
//			LODOP.ADD_PRINT_TEXT(83, 371, 226, 20, "身份证号：" + appData.licenseNumber);
//			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
//			LODOP.ADD_PRINT_TEXT(83, 598, 111, 20, "人员类别：" + $scope.rylb);
//			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
//			LODOP.ADD_PRINT_TEXT(105, 43, 154, 20, "卡状态：" + $scope.zhzt);
//			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
//			LODOP.ADD_PRINT_TEXT(105, 207, 142, 20, "账户状态：" + $scope.fcqk);
//			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
//			LODOP.ADD_PRINT_TEXT(105, 371, 221, 20, "保健对象：" + $scope.bjdx);
//			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
//			LODOP.ADD_PRINT_TEXT(105, 597, 154, 20, "特殊人员：" + $scope.tsry);
//			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
//			LODOP.ADD_PRINT_TEXT(125, 43, 154, 20, "封存情况：" + $scope.fcqk);
//			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
////			LODOP.ADD_PRINT_TEXT(125, 207, 162, 20, "公务员情况：" + $scope.gwyqk);
////			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
//			LODOP.ADD_PRINT_TEXT(125, 371, 221, 20, "职退情况：" + $scope.ztqk);
//			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
//			LODOP.ADD_PRINT_TEXT(125, 597, 154, 20, "单位码："+ appData.dwbm);
//			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
//			LODOP.ADD_PRINT_TEXT(145, 42, 427, 20, "单位名称："+ appData.dwmc);
//			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
//			LODOP.ADD_PRINT_TEXT(905, 43, 200, 22, "区县：" + jQuery.getConfigMsg.districtAndCountyVal);
//			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
//			LODOP.ADD_PRINT_TEXT(905, 260, 270, 22, "渠道：“一网通办”智能终端");
//			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
//			LODOP.ADD_PRINT_TEXT(905, 530, 309, 22, "打印时间：" + date.getFullYear() + "年" + month + "月" + date.getDate() + "日");
//			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
//			LODOP.ADD_PRINT_TEXT(930, 43, 427, 20, "网点:" + $scope.qutletsMsg);
//			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
//			LODOP.ADD_PRINT_TEXT(1000, 55, 200, 20, "*本查询仅做信息查询使用");
//			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
//			LODOP.ADD_PRINT_TABLE(170, 43, 1300, 1400, appData.tableInfo);
//			LODOP.PRINT();
		}, 10);
		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: '医保个人信息查询',
			}
		}
		recordUsingHistory('医保服务', '查询+打印', '医保个人信息查询', appData.licenseName, appData.licenseNumber, '', '', JSON.stringify($scope.jsonStr));
		trackEventForQuery("医保个人信息查询", "", "打印", "上海市医疗保障局", appData.licenseName, appData.licenseNumber, "");
		setTimeout(function() {
			$state.go("main");
		}, 5000);
	}
});
//综合减负试算---选择项
app.controller('reduceChoose', function($scope, $state, appData, $sce) {
	var date = new Date();
	var tMonth = date.getMonth();
	var tYear = date.getFullYear();
	if(tMonth > 4) {
		$scope.year = tYear;
	} else {
		$scope.year = tYear - 1;
	}
	PublicchoiceById('ztqk');
	PublicchoiceById('year');
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.show = true;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	document.getElementById("input").onfocus = function() {
		console.log(2);
		$scope.show = false;
	};
	$scope.phone = "请输入";
	$scope.count = "请输入";
	$scope.keybordArr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 'X', 0, '删除'];
	$scope.keyboardInput = function(e) { //键盘输入
		var keycode = window.event ? e.keyCode : e.which;
		if(keycode >= 48 && keycode <= 57) {} else if(keycode == 8) {} else {
			console.log(e);
			$scope.count = $scope.count.substring(0, $scope.count.length - 1);
		}
	}
	$scope.phoneInput = function(e) { //键盘输入
		var keycode = window.event ? e.keyCode : e.which;
		if(keycode >= 48 && keycode <= 57) {} else if(keycode == 8) {} else {
			console.log(e);
			$scope.phone = $scope.phone.substring(0, $scope.phone.length - 1);
		}
	}
	$scope.inputNumber = function(item) { //软键盘输入
		if($scope.count == "请输入") {
			$scope.count = "";
		} else if(item === '删除') {
			$scope.count = $scope.count.substring(0, $scope.count.length - 1);
		} else {
			$scope.count += item;
			console.log($scope.count);
		}
	}
	$scope.inputPhone = function(item) { //软键盘输入
		if($scope.phone == "请输入") {
			$scope.phone = "";
		} else if(item === '删除') {
			$scope.phone = $scope.phone.substring(0, $scope.phone.length - 1);
		} else {
			$scope.phone += item;
			console.log($scope.phone);
		}
	}
	$scope.change = function() {
		if($('#ztqk a.in li').text() == "退休") {
			$scope.isZtqk = false;
			$scope.count = "";
		} else if($('#ztqk a.in li').text() == "在职") {
			$scope.isZtqk = true;
		}
	}
	$scope.prevStep = function() {
		$state.go('loginType');
	}
	$scope.nextStep = function() {
		if($('#ztqk a.in li').text() == undefined || $('#ztqk a.in li').text() == "") {
			$scope.isAlert = true;
			$scope.msg = "请选择职退情况";
			return;
		}
		if($('#year a.in li').text() == undefined || $('#year a.in li').text() == "") {
			$scope.isAlert = true;
			$scope.msg = "请选择年份";
			return;
		}
		if(($scope.count == undefined || $scope.count == "") && $scope.isZtqk == true) {
			$scope.isAlert = true;
			$scope.msg = "请输入上年份收入";
			return;
		}
		//		if($scope.phone == undefined || $scope.phone == "") {
		//			$scope.isAlert = true;
		//			$scope.msg = "请输入手机号";
		//			return;
		//		}
		appData.year = $('#year a.in li').text().split('年')[0];
		appData.ztqk = $('#ztqk a.in li').text();
		appData.count = $scope.count;
		appData.phone = $scope.phone;
		$state.go('reduce');
	}
});
//综合减负试算
app.controller('reduce', function($scope, $state, appData, $sce) {
	var date = new Date();
	var tMonth = date.getMonth();
	var tYear = date.getFullYear();
	if(tMonth > 4) {
		$scope.year = tYear;
	} else {
		$scope.year = tYear - 1;
	}
	$scope.isLoding = true;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.nextText = "返回首页";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go('main');
	}
	$scope.prevStep = function() {
		$state.go("reduceChoose");
	}
	$scope.reduce = function() {
		$scope.isLoding = false;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/medicalInsurance/comprehensiveLoadReduction.do",
			dataType: "jsonp",
			data: {
				access_token: appData.token,
				SFZH: appData.licenseNumber,
				ZTQK: (appData.ztqk == "在职") ? "1" : "2",
				NDBZ: Math.abs(appData.year - $scope.year) + 1,
				ZGGZ: appData.count
			},
			jsonp: "jsonpCallback",
			success: function(dataJson) {
				$scope.isLoding = true;
				if(dataJson) {
					$scope.isAlert = true;
					if(dataJson[0].errmsg) {
						$scope.msg = "现在结算没有金额可减负";
					} else if(dataJson[0].jfje) {
						$scope.msg = "您的减负金额为" + dataJson[0].jeje + "元";
					} else if(dataJson.ERROR) {
						$scope.msg = "现在结算没有金额可减负";
					}
				}
				//模块使用记录
				$scope.jsonStr = {
					SUCCESS: "true",
					data: {
						name: '综合减负试算',
					}
				}
				recordUsingHistory('医保服务', '查询', '综合减负试算', appData.licenseName, appData.licenseNumber, '', '', JSON.stringify($scope.jsonStr));

			},
			error: function(err) {
				$scope.isLoding = true;
				$scope.isAlert = true;
				$scope.msg = "未计算出减负金额";
				console.log(err);
			}
		});
	}
	$scope.reduce();
});
//医保就医明细查询
app.controller('medicalDetails', function($scope, $state, appData, $sce, appFactory) {
	$scope.searchType = ["门急诊", "内设门急诊", "住院", "急观", "大病", "家床", "购药", "账户购买保险"];
	$scope.funName = appData.funName;
	var date = new Date();
	$scope.year = date.getFullYear();
	$scope.isLoding = true;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.nextText = "返回首页";
	$scope.show = true;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go('loginType');
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}
	$scope.getMatterCon = function(index, type) {
		$scope.current = index;
		switch(type) {
			case "门急诊":
				$scope.queryMedicalDetails("01");
				break;
			case "内设门急诊":
				$scope.queryMedicalDetails("02");
				break;
			case "住院":
				$scope.queryMedicalDetails("03");
				break;
			case "急观":
				$scope.queryMedicalDetails("04");
				break;
			case "大病":
				$scope.queryMedicalDetails("05");
				break;
			case "家床":
				$scope.queryMedicalDetails("06");
				break;
			case "购药":
				$scope.queryMedicalDetails("07");
				break;
			case "账户购买保险":
				$scope.queryMedicalDetails("08");
				break;
			default:
		}
	};
	appFactory.pro_fetch(appData.licenseNumber, appData.licenseName, appData.token, function(data) {
		appData.zhh = data;
		$scope.queryMedicalDetails("01");
	}, function(err) {
		$scope.isAlert = true;
		$scope.msg = "未查询到信息 ，请重试"
	});
	$scope.queryMedicalDetails = function(type) {
		$scope.isLoding = false;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/medicalInsurance/queryMedicalDetails.do",
			dataType: "jsonp",
			data: {
				ZHH: appData.zhh,
				JYLX: type,
				YYMC: ""
			},
			jsonp: "jsonpCallback",
			success: function(dataJson) {
				$scope.isLoding = true;
				console.log(dataJson);
				try {
					$scope.show = true;
					$scope.medicalDetail = dataJson[0].jymxfys[0].jymxfy;
				} catch(e) {
					$scope.medicalDetail = "";
					$scope.show = false;
				}
			},
			error: function(err) {
				$scope.isLoding = true;
				$scope.isAlert = true;
				$scope.msg = "未查询到您的信息";
				console.log(err);
			}
		});
	}
	//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: '医保就医明细',
		}
	}
	recordUsingHistory('医保服务', '查询', '医保就医明细', appData.licenseName, appData.licenseNumber, '', '', JSON.stringify($scope.jsonStr));

	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});
//账户清算信息查询
app.controller('accountSettlement', function($scope, $state, appData, $sce, appFactory, $timeout) {
	//获取网点信息
	console.log(jQuery.getConfigMsg.qutletsVal);
	$scope.funName = appData.funName;
	var date = new Date();
	$scope.year = date.getFullYear();
	$scope.months = date.getMonth() + 1;
	if($scope.months < 4) {
		$scope.year = $scope.year - 2;
	} else {
		$scope.year = $scope.year - 1;
	}
	$scope.isLoding = false;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.nextText = "打印";
	$scope.name = appData.licenseName;
	$scope.isShowPrint = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go('main');
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
	appFactory.pro_fetch(appData.licenseNumber, appData.licenseName, appData.token, function(data) {
		appData.zhh = data;
		$scope.clearAccount();
	}, function(err) {
		$scope.isAlert = true;
		$scope.msg = "未查询到信息 ，请重试"
	});
	$scope.clearAccount = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/medicalInsurance/clearAccount.do",
			dataType: "jsonp",
			data: {
				ZHH: appData.zhh //"32390850" //
			},
			jsonp: "jsonpCallback",
			success: function(dataJson) {
				$scope.isLoding = true;
				if(dataJson.buscode == "FFF200") {
					$scope.isAlert = true;
					$scope.msg = "未找到该参保人";
				} else {
					try {
						$scope.info = dataJson[0];
						console.log($scope.info);
						$scope.zhh = appData.zhh;
					} catch(e) {
						$scope.isAlert = true;
						$scope.msg = "未查询到您的账户清算信息";
					}
				}

			},
			error: function(err) {
				$scope.isLoding = true;
				$scope.isAlert = true;
				$scope.msg = "未查询到您的账户清算信息";
				console.log(err);
			}
		});
	}
	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
	$scope.print = function() {
		$scope.isShowPrint = "show";
		var time = getNowFormatDate();
		var LODOP = $.device.printGetLodop('');
		var Html = "<style>table {font-size: 12px;border-collapse: collapse;border-bottom: 1px solid;border-right: 1px solid;text-align: center;width: 700px;}" +
			"table td {height: 21px;border-left: 1px solid;border-top: 1px solid;}.month{width:10%} div{text-align:center;height: 45px;margin-top: 22px;font-size: 18px;font-weight: bold;}" +
			"img{position: absolute;width: 60px;margin-left: 60px;margin-top: 10px;}b{font-weight: normal;font-size:14px;} </style><table>" +
			"<tr><td colspan='14' style='text-align:left'><img src='" + $.getConfigMsg.preUrl + "/aci/selfWorkBench/medical/js/medical.jpg'><div>医疗保险个人帐户资金查询（城保）<br/><b>（" + $scope.year + "医保年度）</b></div></td></tr>" +
			"<tr><td colspan='2'>姓名</td><td colspan='4'>" + $scope.name + "</td><td colspan='2'>身份证号码</td><td colspan='6'>" + appData.licenseNumber + "</td></tr>" +
			"<tr><td colspan='2'>社保卡卡号</td><td colspan='4'></td><td colspan='2'>医保卡卡号</td><td colspan='6'></td></tr>" +
			"<tr><td colspan='2'>本年度年初预注额③</td><td colspan='12'>" + toDecimal2($scope.info.cur_ncyze) + "</td></tr>" +
			"<tr><td rowspan='2' colspan='2'>本年度末帐户余额④</td><td rowspan='2' colspan='2'>" + toDecimal2($scope.info.bndmzhye) + "</td><td colspan='10'>其中</td></tr>" +
			"<tr><td colspan='3'>当前帐户余额</td><td colspan='2'>" + toDecimal2($scope.info.dnzhye) + "</td><td colspan='3'>历年帐户余额</td><td colspan='2'>" + toDecimal2($scope.info.lnzhye) + "</td></tr>" +
			"<tr><td rowspan='10' style='width:2%'>医保缴费信息</td><td rowspan='4' style='width:15%'>每月缴费额</td><td colspan='2'>4月</td><td colspan='2'>5月</td><td colspan='2'>6月</td><td colspan='2'>7月</td><td colspan='2'>8月</td><td colspan='2'>9月</td></tr>" +
			"<tr><td>" + toDecimal2($scope.info.zcjf01) + "</td><td>☆</td><td>" + toDecimal2($scope.info.zcjf02) + "</td><td>☆</td><td>" + toDecimal2($scope.info.zcjf03) + "</td><td>☆</td><td>" + toDecimal2($scope.info.zcjf04) + "</td><td>☆</td><td>" + toDecimal2($scope.info.zcjf05) + "</td><td>☆</td><td>" + toDecimal2($scope.info.zcjf06) + "</td><td>☆</td></tr>" +
			"<tr><td class='month' colspan='2'>10月</td><td class='month' colspan='2'>11月</td><td class='month' colspan='2'>12月</td><td class='month' colspan='2'>01月</td><td class='month' colspan='2'>02月</td><td class='month' colspan='2'>03月</td></tr>" +
			"<tr><td>" + toDecimal2($scope.info.zcjf07) + "</td><td>☆</td><td>" + toDecimal2($scope.info.zcjf08) + "</td><td>☆</td><td>" + toDecimal2($scope.info.zcjf09) + "</td><td>☆</td><td>" + toDecimal2($scope.info.zcjf10) + "</td><td>☆</td><td>" + toDecimal2($scope.info.zcjf11) + "</td><td>☆</td><td>" + toDecimal2($scope.info.zcjf12) + "</td><td>☆</td></tr>" +
			"<tr><td>补缴金额</td><td colspan='4'>" + toDecimal2($scope.info.bjhj) + "</td><td colspan='4'>补缴月数</td><td colspan='4'>" + $scope.info.bjys.slice(6) + "</td></tr>" +
			"<tr><td>预缴金额</td><td colspan='4'>" + toDecimal2($scope.info.yjhj) + "</td><td colspan='4'>预缴月数</td><td colspan='4'>" + $scope.info.yjys.slice(6) + "</td></tr>" +
			"<tr><td>调整金额</td><td colspan='4'>" + toDecimal2($scope.info.tzhj) + "</td><td colspan='4'>调整月数</td><td colspan='4'>" + $scope.info.tzys.slice(6) + "</td></tr>" +
			"<tr><td>退帐金额</td><td colspan='4'>" + toDecimal2($scope.info.tkhj) + "</td><td colspan='4'>退帐月数</td><td colspan='4'>" + $scope.info.tkys.slice(6) + "</td></tr>" +
			"<tr><td>非常规调整金额</td><td colspan='4'>" + toDecimal2($scope.info.sumfcgtzje) + "</td><td colspan='4'>非常规调整月数</td><td colspan='4'>" + $scope.info.sumfcgtzys.slice(6) + "</td></tr>" +
			"<tr><td>缴费合计①</td><td colspan='12'>" + toDecimal2($scope.info.jfhj) + "</td></tr>" +
			"<tr><td rowspan='6'>医保清算信息</td><td rowspan='2'>单位缴费计入个人账户金额②</td><td rowspan='2' colspan='4'>" + toDecimal2($scope.info.sphbje) + "</td><td colspan='8'>其中</td></tr>" +
			"<tr><td colspan='2'>在职</td><td colspan='2'>" + toDecimal2($scope.info.zzsphbje) + "</td><td colspan='2'>退休</td><td colspan='2'>" + toDecimal2($scope.info.txsphbje) + "</td></tr>" +
			"<tr><td>上年度扣减额⑤</td><td colspan='10' style='border-right: 0px;'>" + toDecimal2($scope.info.pre_kje) + "</td><td colspan='2' style='border-left: 0px;'>【详细信息】</td></tr>" +
			"<tr><td>本年度医保调整额⑥</td><td colspan='10' style='border-right: 0px;' >" + toDecimal2($scope.info.bndybtze) + "</td><td colspan='2' style='border-left: 0px;'>【详细信息】</td></tr>" +
			"<tr><td>其他保险转入额⑦</td><td colspan='10' style='border-right: 0px;'>" + toDecimal2($scope.info.qtbxzre) + "</td><td colspan='2' style='border-left: 0px;'>【详细信息】</td></tr>" +
			"<tr><td>本年度清算额</td><td colspan='12'>" + toDecimal2($scope.info.bndmzhye) + "</td></tr>" +
			"<tr><td colspan='2'>本年度帐户资金利息</td><td colspan='5'>" + toDecimal2($scope.info.cur_zhlx) + "</td><td colspan='2'>利率</td><td colspan='5'>" + toDecimal2($scope.info.lilv) + "%</td></tr>" +
			"<tr><td colspan='2'>本年度帐户结转额<br/>【历年帐户】（含息）</td><td colspan='12'>" + toDecimal2($scope.info.cur_jze) + "</td></tr>" +
			"<tr><td colspan='2'>下年度预注月记帐金额⑧</td><td colspan='12'>" + toDecimal2($scope.info.xndyzyjzje) + "</td></tr>" +
			"<tr><td colspan='2'>下年度帐户预注额<br/>【当年帐户】</td><td colspan='12'>" + toDecimal2($scope.info.next_yze) + "</td></tr>" +
			"<tr>" +
			"<td colspan='14' style='text-align:left;padding-left:12px;'>" +
			"清算说明<br>" +
			"个人帐户单位缴费部分计入标准：" +
			"<table style='width:660px;'>" +
			"	<tr><td>职退情况</td><td>年龄分段</td><td>计入标准⑨</td></tr>" +
			"	<tr><td rowspan='3'>在职</td><td>34岁以下</td><td>210</td></tr>" +
			"	<tr><td>35-44岁</td><td>420</td></tr>" +
			"	<tr><td>45岁-退休</td><td>630</td></tr>" +
			"	<tr><td rowspan='2'>退休</td><td>74岁以下</td><td>1680</td></tr>" +
			"	<tr><td>75岁以上</td><td>1890</td></tr>" +
			"</table>" +
			"<br/>计算公式：<br/>" +
			"历年账户=(①+②-③+④-⑤+⑥+⑦)×(1+利率) <br/>" +
			"当年账户=预注月记账额⑧×12+个人账户单位缴费部分计入标准⑨+[本年度清算额(如本年度清算额为负数)]<br/>" +
			"标志：<br/> " +
			"☆城保在职&nbsp;&nbsp;&nbsp;&nbsp;★个保在职&nbsp;&nbsp;&nbsp;&nbsp;◆镇保在职&nbsp;&nbsp;&nbsp;&nbsp;▲灵活就业人员&nbsp;&nbsp;&nbsp;&nbsp;△协保差额缴费	&nbsp;&nbsp;&nbsp;&nbsp;◎退休&nbsp;&nbsp;&nbsp;&nbsp;" +
			"■终止&nbsp;&nbsp;&nbsp;&nbsp;◇郊区从业&nbsp;&nbsp;&nbsp;&nbsp;□原镇保征地<br/>○原城保征地&nbsp;&nbsp;&nbsp;&nbsp;●城保征地" +
			"	</td>	" +
			"</tr>" +
			"</table>";
		console.log(Html);
		LODOP.ADD_PRINT_TABLE(45, 30, 1300, 1400, Html);
		LODOP.ADD_PRINT_TEXT(940, 35, 188, 20, "渠道：“一网通办”自助终端");
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
		LODOP.ADD_PRINT_TEXT(940, 230, 262, 20, "网点：" + jQuery.getConfigMsg.qutletsVal);
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
		LODOP.ADD_PRINT_TEXT(940, 500, 320, 20, "查询时间：" + time);
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
		LODOP.ADD_PRINT_TEXT(1000, 55, 200, 20, "*本查询仅做信息查询使用");
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
		LODOP.PRINT();
		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: '账户清算信息',
			}
		}
		recordUsingHistory('医保服务', '查询+打印', '账户清算信息', appData.licenseName, appData.licenseNumber, '', '', JSON.stringify($scope.jsonStr));

		$timeout(function() {
			$state.go("main");
		}, 3000);
	}
});
//参保人员待遇查询
app.controller('treatmentDetails', function($scope, $state, appData, $sce, appFactory) {
	//	$scope.funName = appData.funName;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.show = false;
	$scope.nextText = "返回首页";
	$scope.medicalType = medicalType;
	$scope.search = function(list, id) {
		var result = list.filter(function(p) {
			return p.pid == id;
		});
		return result;
	}
	$scope.change = function(name, index, id) {
		$scope.current = null;
		$scope.current2 = null;
		$scope.show = true;
		$scope.show2 = false;
		$scope.current = index;
		$scope.treatmentDetials = $scope.search(treatmentDetials, id);
	};
	$scope.change2 = function(name, index, guideline) {
		$scope.current2 = null;
		$scope.show2 = true;
		$scope.current2 = index;
		$scope.guideline = $sce.trustAsHtml(guideline);
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go('reduceChoose');
	}
	//模块使用记录
	recordUsingHistory('医保服务', '查询', '参保人员待遇', '', '', '', '', '');

	$scope.prevStep = function() {
		$state.go("reduceChoose");
	}
	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});
//医报业务办理进度查询
app.controller('handleProgressQuery', function($scope, $state, appData, $sce, appFactory) {
	$scope.funName = appData.funName;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.show = false;
	$scope.nextText = "返回首页";
	$scope.queryReimbursementProgress = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/medicalInsurance/queryReimbursementProgress.do",
			dataType: "jsonp",
			data: {
				"SFZH": appData.idCardNum,
				"SQH": appData.applyNo,
				"YWLX": appData.ywlx,
				"userName": "查询者",
				"mobile": "13433333333"
			},
			jsonp: "jsonpCallback",
			success: function(dataJson) {
				$scope.isLoding = true;
				console.log(dataJson);
				//				try {
				//					$scope.info = dataJson[0];
				//				} catch(e) {
				//					$scope.isAlert = true;
				//					$scope.msg = "未查询到您的账户清算信息";
				//				}
			},
			error: function(err) {
				$scope.isLoding = true;
				$scope.isAlert = true;
				$scope.msg = "未查询到您的账户清算信息";
				console.log(err);
			}
		});
	}
	$scope.queryReimbursementProgress();
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {}
	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});