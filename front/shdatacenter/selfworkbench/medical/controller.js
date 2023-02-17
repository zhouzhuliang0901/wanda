app.controller("main", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.choiceType = function(type) {
		appData.type = type;
		if(appData.type == "info") {
			appData.funName = "医保个人信息";
			$state.go("loginType");
		} else if(appData.type == "bookMaking") {
			appData.funName = "医保制册";
			$state.go("bookMaking");
		} else if(appData.type == "reduce") {
			appData.funName = "综合减负";
			$state.go("loginType1");
		}
	}
});
app.controller('loginType', function($state, $scope, appData, $location) {
	$scope.funName = appData.funName;
	$scope.operation = "请选择登录方式";
	if(appData.type == "" || appData.type == undefined) {
		appData.type = $location.search().type;
	}
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		appData.SwipeType = "sbCard";
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("main");
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
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});

app.controller('login', function($scope, $http, $state, appData, appFactory) {
	$scope.funName = appData.funName;
	$scope.isAlert = false;
	$scope.msg = "";
	$scope.nextLink = ""; // 下一步标识符
	$scope.searchResult = "";// 查询结果标识符 "0"或"1"
	if(appData.source == "idcardOrCitizen" && appData.loginType == "idcard") {
		$scope.operation = "请刷办理人身份证";
	} else if(appData.source == "idcardOrCitizen" && appData.loginType == "cloud") {
		$scope.operation = "请刷办理人市民云";
	} else if(appData.loginType == 'idcard') {
		$scope.operation = "身份证登录";
	} else if(appData.loginType == 'cloud') {
		$scope.operation = "市民云登录";
	} else if(appData.loginType == 'medical') {
		$scope.operation = "医保卡登录";
	}
	$scope.loginType = appData.loginType;
	console.log($scope.loginType);
	// 参保人信息查询函数
	$scope.searchInsuredInfo = function() {
		$http.jsonp("http://hengshui.5uban.com/xhac/aci/workPlatform/medicalInsurance/queryAccountInfo.do", {
			params: {
				jsonpCallback: "JSON_CALLBACK",
				identNo: appData.licenseNumber // "310104194906012828"
			}
		}).success(function(returnData) {
			if(returnData[0].cbrxxs) {
				appData.zhh = returnData[0].cbrxxs[0].cbrxx[0].zhh;// 账户号
				console.log(appData.zhh);
				$state.go("bookMakingReason");
			} else {
				$scope.isAlert = true; // 是否显示打印提示框
				$scope.msg = "未找到该参保人";
				$scope.searchResult = "0";
			}
			console.log(returnData);
		}).error(function(err) {
			$scope.isAlert = true; // 是否显示打印提示框
			$scope.msg = "查询失败";
			$scope.searchResult = "0";
		});
	}
	$scope.idcardLogin = function(info, images) {
		if(info) {
			// 存储办理人信息
			$scope.faceImage = images;
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
			if(appData.source == "idcardOrCitizen" && appData.handle == "self") {
				$scope.searchInsuredInfo();
			} else if(appData.source == "idcardOrCitizen" && appData.handle == "agent") {
				$state.go("swipeAgentIdcard");
			} else {
				$scope.loginType = 'recognition';
			}

		} else {
			layer.msg("很抱歉，未获取到相关信息，请重试。");
		}
	}
	$scope.sscardLogin = function(info) {
		if(info) {
			// 存储社保卡信息
			alert(info)
			$.log.Debug(info)
			//			$scope.sscardInfo = info;
			//			appData.sscardInfo = $scope.sscardInfo;
			appData.licenseNumber = info.Ssn;
			if(appData.handle == "self") {
				$scope.searchInsuredInfo();
			} else if(appData.handle == "agent") {
				$scope.isShowSscard = false;
				$scope.isShowIdcard = true;
			}
		} else {
			layer.msg("很抱歉，未获取到相关信息，请重试。")
		}
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		if($scope.searchResult == "0") {
			$state.go("bookMaking");
		}
	}
	$scope.alertCancel = function() {
		$scope.isAlert = false;
		if($scope.searchResult == "0") {
			$state.go("bookMaking");
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		if(appData.type == "info") {
			$state.go("infoChoose");
		} else if(appData.type == "reduce") {
			$state.go("reduceChoose");
		}
	}
	$scope.source = appData.source;
	$scope.prevStep = function() {
		if($scope.source == "idcardOrCitizen") {
			$state.go("idcardOrCitizen");
			return;
		}
		$state.go("loginType");
	}

	$scope.citizenLogin = function(info) {
		var idcardInfo = info.result.data;
		appData.licenseName = idcardInfo.realname;
		appData.licenseNumber = idcardInfo.idcard;
		if(appData.type == "info") {
			$state.go("infoChoose");
		} else if(appData.type == "reduce") {
			$state.go("reduceChoose");
		} else if(appData.source = "idcardOrCitizen" && appData.handle == "self") {
			$scope.searchInsuredInfo();
		} else if(appData.source = "idcardOrCitizen" && appData.handle == "agent") {
			$state.go("swipeAgentIdcard");
		}
		try {
			$scope.$apply();
		} catch(e) {}
	}
});

app.controller('swipeAgentIdcard', function($scope, $http, $state, $window, appData, appFactory) {
	$scope.isShowChoiceLoginType = true;
	$scope.isAlert = false;
	$scope.msg = "";
	$scope.operation = "请选择刷代理人身份证方式";
	$scope.loginType = appData.loginType;
	$scope.searchResult = "";// 查询结果标识符 "0"或"1"
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
		$http.jsonp("http://hengshui.5uban.com/xhac/aci/workPlatform/medicalInsurance/queryAccountInfo.do", {
			params: {
				jsonpCallback: "JSON_CALLBACK",
				identNo: appData.licenseNumber // "310104194906012828"
			}
		}).success(function(returnData) {
			if(returnData[0].cbrxxs) {
				appData.zhh = returnData[0].cbrxxs[0].cbrxx[0].zhh;
				console.log(appData.zhh);
				$state.go("bookMakingReason");
			} else {
				$scope.isAlert = true;
				$scope.msg = "未找到该参保人";
				$scope.searchResult = "0";// 查询结果标识符 "0"或"1"
			}
			console.log(returnData);
		}).error(function(err) {
			$scope.isAlert = true;
			$scope.msg = "查询失败";
			$scope.searchResult = "0";// 查询结果标识符 "0"或"1"
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
		if($scope.searchResult == "0") {
			$state.go("bookMaking");
		}
	}
	$scope.alertCancel = function() {
		$scope.isAlert = false;
		if($scope.searchResult == "0") {
			$state.go("bookMaking");
		}
	}
	$scope.prevStep = function() {
		//		if(appData.source == "idcardOrCitizen") {
		//			$state.go("idcardOrCitizen");
		//		}
		if($scope.isShowChoiceLoginType == true) {
			$state.go("idcardOrCitizen");
		} else if($scope.isShowChoiceLoginType == false) {
			$scope.isShowChoiceLoginType = true;
			$scope.operation = "请选择刷代理人身份证方式";
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
	$scope.idcardMedicalPng = idcardMedicalPng;
	$scope.ybcardPng = ybcardMedicalPng;
	$scope.sbcardPng = sbcardMedicalPng;

	$scope.chooseSwipeType = function(SwipeType, handle) {
		appData.SwipeType = SwipeType;
		appData.handle = handle;
		if(appData.SwipeType == "idCard") {
			$state.go("idcardOrCitizen");
		} else if(appData.SwipeType == "ybCard") {
			//			$scope.isAlert = true;
			//			$scope.msg = "功能暂未开放";
			$state.go("bookMakingChoose");

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
	$scope.prevStep = function(){
		$state.go("main");
	}
});

app.controller('idcardOrCitizen', function($scope, $state, appData, $sce) {
	$scope.operation = "请选择登录方式";
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

app.controller('bookMakingChoose', function($scope, $http, $state, appData, $sce, $timeout) {
	$scope.isAlert = false;
	$scope.msg = "";
	$scope.isShowSscard = true;
	$scope.isShowIdcard = false;
	$scope.operation = "请刷社保卡";
	$scope.sscardInfo = "";
	$scope.handle = appData.handle; // "本人"或者"代办"
	appData.agentNumber = ""; // 代理人身份证号
	appData.agentName = ""; // 代理人姓名
	$scope.searchResult = "";// 查询结果标识符 "0"或"1"
	if(appData.SwipeType == 'sbCard') {
		$scope.operation = "请刷社保卡";
	} else if(appData.SwipeType == 'ybCard') {
		$scope.operation = "请刷医保卡";
	}
	// 收集医保卡数据
	$scope.$on("$viewContentLoaded", function(event, toState, toParams, fromState, fromParams) {
		document.getElementById('ybCardNumber').focus();
     	document.getElementById('ybCardNumber').select();
	});
	// 参保人信息查询函数
	$scope.searchInsuredInfo = function() {
		$http.jsonp("http://hengshui.5uban.com/xhac/aci/workPlatform/medicalInsurance/queryAccountInfo.do", {
			params: {
				jsonpCallback: "JSON_CALLBACK",
				identNo: appData.licenseNumber // "310104194906012828"
			}
		}).success(function(returnData) {
			if(returnData[0].cbrxxs) {
				appData.zhh = returnData[0].cbrxxs[0].cbrxx[0].zhh;
				console.log(appData.zhh);
				$state.go("bookMakingReason");
			} else {
				$scope.isAlert = true; // 是否显示打印提示框
				$scope.msg = "未找到该参保人";
				$scope.searchResult = "0";
			}
			console.log(returnData);
		}).error(function(err) {
			$scope.isAlert = true; // 是否显示打印提示框
			$scope.msg = "查询失败";
			$scope.searchResult = "0";
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
			$.device.ssCardClose();
			// 存储社保卡信息
//			alert(info.Ssn)
//			$.log.Debug(info)
//			$scope.sscardInfo = info;
//			appData.sscardInfo = $scope.sscardInfo;
			appData.licenseNumber = info.Ssn;
			if($scope.handle == "self") {
				$scope.searchInsuredInfo();
			} else if($scope.handle == "agent") {
				$scope.isShowSscard = false;
				$scope.isShowIdcard = true;
			}
		} else {
			layer.msg("很抱歉，未获取到相关信息，请重试。")
		}
	}

	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		if($scope.searchResult == "0") {
			$state.go("bookMaking");
		}
	}

	$scope.alertCancel = function() {
		$scope.isAlert = false;
		if($scope.searchResult == "0") {
			$state.go("bookMaking");
		}
	}

	$scope.prevStep = function() {
		$state.go('bookMaking');
	}
});

app.controller('bookMakingReason', function($scope, $http, $state, appData, $sce) {
	$scope.operation = '请选择制册原因';
	$scope.updateRecordBookReason = ""; // 补册原因
	$scope.isAlert = false; // 是否显示打印提示框
	$scope.msg = "是否打印医保就医记录册?";
	$scope.isShowPrint = false;
	$scope.searchResult = "";// 查询结果标识符
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
		$http.jsonp("http://hengshui.5uban.com/xhac/aci/workPlatform/medicalInsurance/updateRecordBook.do", {
			params: {
				jsonpCallback: "JSON_CALLBACK",
				zhh: appData.zhh,
				bcyy: $scope.updateRecordBookReason,
				wtrxm: appData.agentName, // "李华熙", 
				wtrsfzh: appData.agentNumber // "520222199406140030"
			}
		}).success(function(returnData) {
			console.log(returnData);
			console.log("账户号：" + appData.zhh);
			if(!returnData[0].syscode) {
				appData.newBookBh = returnData[0].jlch; // 新就医记录册编号
				appData.newBookKh = returnData[0].kh; // 新就医记录册卡号
				appData.userXb = returnData[0].xb == "1" ? "男" : "女";
				appData.userName = returnData[0].xm;
				appData.userAge = returnData[0].sfzh.substring(6, 10) + "年" + returnData[0].sfzh.substring(10, 12) + "月";
				$state.go("bookMakingTakePhoto");
			} else if(returnData[0].syscode) {
				if(returnData[0].errmsg == "[E001]参保人没有记录册，只允许新增制册") {
					$scope.isAlert = true;
					$scope.msg = "参保人没有记录册，只允许新增制册";
					$scope.searchResult = "E001";
				} else if(returnData[0].errmsg == "参保人本年度制册数超过医保审核标准，请至市医保中心审核后制册") {
					$scope.isAlert = true;
					$scope.msg = "参保人本年度制册数超过医保审核标准，请至市医保中心审核后制册";
					$scope.searchResult = "E002";
				} else if(returnData[0].errmsg == "[E001]该参保人账户撤销已清算，不能制册。") {
					$scope.isAlert = true;
					$scope.msg = "该参保人账户撤销已清算，不能制册";
					$scope.searchResult = "E003";
				}
			}
		}).error(function(err) {
			$scope.isAlert = true; // 是否显示打印提示框
			$scope.msg = "未找到该参保人";
		});
	}

	$scope.alertConfirm = function() {
		if($scope.searchResult == "E001") {
			$scope.isAlert = false;
		} else if($scope.searchResult == "E002" || $scope.searchResult == "E003") {
			console.log($scope.searchResult)
			$scope.isAlert = false;
			$state.go("bookMaking");
		}
	}

	$scope.alertCancel = function() {
		if($scope.searchResult == "E001") {
			$scope.isAlert = false;
		} else if($scope.searchResult == "E002" || $scope.searchResult == "E003") {
			$scope.isAlert = false;
			$state.go("bookMaking");
		}
	}
});

app.controller('bookMakingTakePhoto', function($scope, $state, appData, $sce) {
	$scope.newBookBh = appData.newBookBh;
	$scope.newBookKh = appData.newBookKh;
	$scope.userXb = appData.userXb;
	$scope.userName = appData.userName;
	$scope.userAge = appData.userAge;
	$scope.isShowPrint = false;
	$scope.operation = "请看摄像头";
	$scope.camera = true;
	$scope.imageData = null;
	$scope.__cameraPos = $scope.cameraPos || {
		width: 640,
		height: 480,
		x: 560,
		y: 240
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
	$.device.serialPortOpen("COM5", 9600, 8, function() {}) //开启串口
	$scope.confirm = function() {
		var lodop = $.device.printGetLodop()
		lodop.SET_PRINTER_INDEX('DASCOM DS-7860');
		lodop.ADD_PRINT_TEXT(230, 150, 670, 50, $scope.newBookBh);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
		lodop.ADD_PRINT_TEXT(270, 150, 670, 30, $scope.userName);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
		lodop.ADD_PRINT_TEXT(305, 150, 670, 30, $scope.userXb);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
		lodop.ADD_PRINT_TEXT(340, 150, 670, 30, $scope.newBookKh);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
		lodop.ADD_PRINT_TEXT(375, 150, 670, 30, $scope.userAge);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
		lodop.PRINT(); //打印
		setTimeout(function() {
			$.device.serialPortWriteString("S0001#") //发送指令
		}, 0)
		//  alert('打印完毕，关闭串口')
		//  $.device.serialPortClose();  

		$scope.isShowPrint = "show";
		setTimeout(function() {
			$scope.isShowPrint = false;
			$state.go("bookMaking");
		}, 5000);
	};
});

app.controller('infoChoose', function($scope, $state, appData, $sce) {
	var date = new Date();
	$scope.year = date.getFullYear();
	$scope.show = false;
	PublicchoiceById('ybbf');
	PublicchoiceById('year');
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
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
	$scope.prevStep = function() {
		$state.go('loginType');
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
app.controller("info", function($scope, $state, appData, $sce) {
	$scope.nextText = "打印";
	$scope.isLoding = false;
	var date = new Date();
	$scope.year = date.getFullYear();
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go("infoChoose");
	}
	$scope.peopleInfo = function() {
		$.ajax({
			type: "get",
			url: "http://hengshui.5uban.com/xhac/aci/workPlatform/medicalInsurance/personalInfoQuery.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				indentNo: appData.licenseNumber,
				userName: appData.licenseName,
				mobile: appData.mobile,
				type: 0
			},
			success: function(dataJson) {
				console.log(dataJson);
				console.log(dataJson[0].cbrxxs[0].cbrxx[0]);
				if(dataJson) {
					if(dataJson.code == 409) {
						$scope.isAlert = true;
						$scope.msg = "未查询到信息 ，请重试"
					} else {
						appData.zhh = dataJson[0].cbrxxs[0].cbrxx[0].zhh;
						$scope.medicalInfo(appData.zhh);
					}
				} else {
					$scope.isAlert = true;
					$scope.msg = "未查询到信息 ，请重试"
				}
			},
			error: function(err) {
				console.log(err);
			}
		});
	}

	$scope.medicalInfo = function(zhh) {
		$.ajax({
			type: "get",
			url: "http://hengshui.5uban.com/xhac/aci/workPlatform/medicalInsurance/urbanInsurance.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				zhh: zhh,
				ndbz: Math.abs(appData.infoyear - $scope.year),
				ybbf: appData.ybbf,
				mkbz: 1
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

	$scope.peopleInfo();

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
		$state.go("infoChoose");
	}
	$scope.print = function() {
		var LODOP = $.device.printGetLodop();
		var date = new Date();
		var month = date.getMonth() + 1;
		LODOP.ADD_PRINT_TEXT(32, 250, 430, 50, "个人账户信息查询表(2019)");
		LODOP.SET_PRINT_STYLE("BOLD", 1);
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 20);
		LODOP.ADD_PRINT_TEXT(83, 43, 154, 20, "卡号：" + $scope.zhh);
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
		LODOP.ADD_PRINT_TEXT(83, 207, 142, 20, "姓名：" + $scope.xm);
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
		LODOP.ADD_PRINT_TEXT(83, 371, 226, 20, "身份证号：" + appData.licenseNumber);
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
		LODOP.ADD_PRINT_TEXT(83, 598, 111, 20, "人员类别：" + $scope.rylb);
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
		LODOP.ADD_PRINT_TEXT(105, 43, 154, 20, "卡状态：" + $scope.zhzt);
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
		LODOP.ADD_PRINT_TEXT(105, 207, 142, 20, "账户状态：" + $scope.fcqk);
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
		LODOP.ADD_PRINT_TEXT(105, 371, 221, 20, "保健对象：" + $scope.bjdx);
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
		LODOP.ADD_PRINT_TEXT(105, 597, 154, 20, "特殊人员：" + $scope.tsry);
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
		LODOP.ADD_PRINT_TEXT(125, 43, 154, 20, "封存情况：" + $scope.fcqk);
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
		LODOP.ADD_PRINT_TEXT(125, 207, 162, 20, "公务员情况：" + $scope.gwyqk);
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
		LODOP.ADD_PRINT_TEXT(125, 371, 221, 20, "职退情况：" + $scope.ztqk);
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
		LODOP.ADD_PRINT_TEXT(125, 597, 115, 20, "医保办法：" + $scope.ybbf);
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
		LODOP.ADD_PRINT_TEXT(145, 42, 154, 20, "单位码：");
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
		LODOP.ADD_PRINT_TEXT(145, 207, 427, 20, "单位名称：");
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
		LODOP.ADD_PRINT_TEXT(905, 43, 200, 22, "区县：静安区医保中心");
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 12);
		LODOP.ADD_PRINT_TEXT(905, 300, 200, 22, "经办人：" + appData.licenseName);
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 12);
		LODOP.ADD_PRINT_TEXT(905, 530, 309, 22, "打印时间：" + date.getFullYear() + "年" + month + "月" + date.getDate() + "日");
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 12);
		LODOP.ADD_PRINT_TABLE(170, 43, 1300, 1400, appData.tableInfo);
		LODOP.PRINT();
	}
});
app.controller('reduceChoose', function($scope, $state, appData, $sce) {
	var date = new Date();
	$scope.year = date.getFullYear();
	PublicchoiceById('ztqk');
	PublicchoiceById('year');
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.show = true;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	document.getElementById("input1").onfocus = function() {
		console.log(1);
		$scope.show = true;
	};
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
		if($scope.count == undefined || $scope.count == "") {
			$scope.isAlert = true;
			$scope.msg = "请输入试算金额";
			return;
		}
		if($scope.phone == undefined || $scope.phone == "") {
			$scope.isAlert = true;
			$scope.msg = "请输入手机号";
			return;
		}
		appData.year = $('#year a.in li').text().split('年')[0];
		appData.ztqk = $('#ztqk a.in li').text();
		appData.count = $scope.count;
		appData.phone = $scope.phone;
		$state.go('reduce');
	}
});
app.controller('reduce', function($scope, $state, appData, $sce) {
	var date = new Date();
	$scope.year = date.getFullYear();
	$scope.isLoding = false;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go('reduceChoose');
	}
	$scope.prevStep = function() {
		$state.go("reduceChoose");
	}
	$scope.reduce = function() {
		$.ajax({
			type: "get",
			url: "http://hengshui.5uban.com/xhac/aci/workPlatform/medicalInsurance/comprehensiveLoadReduction.do",
			dataType: "jsonp",
			data: {
				userName: appData.licenseName,
				mobile: appData.phone,
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
					console.log(dataJson);
					if(dataJson[0].errmsg) {
						$scope.msg = dataJson[0].errmsg;
					} else if(dataJson[0].jfje) {
						$scope.msg = "您的减负金额为" + dataJson[0].jeje + "元";
					}

				}
				console.log(dataJson);
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