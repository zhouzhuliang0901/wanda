function removeAnimate(ele) {
	$(ele).css({
		"transform": "translateY(0px)",
		"top": 0
	}).removeClass('transformto')
}

function addAnimate(ele) {
	$(ele).addClass('transformto').on("animationend", function() {
		$(ele).removeClass('transformto')
	})
}
app.controller('personChoice', function($scope, $state, appData, $sce) {
	$scope.isAlert = false;
	$scope.operation = "请选择办理方式";
	appData.SwipeType = "idCard"; // 刷身份证
	appData.handle = ""; // 存储办理人    "本人"或"代理人"
	appData.isSon = false; // 是否是被监护人
	$scope.chooseSwipeType = function(handle) {
		appData.handle = handle;
		// 本人和代办都走刷脸的流程,界面提示信息不同
		if(appData.handle == "agent") {
			appData.operation = "监护人登录方式";
		} else {
			appData.operation = "本人登录方式";
		}
		$state.go('loginType');
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.alertCancel = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		window.location.href = "../publicSecurity/index.html";
	}
});
app.controller('loginType', function($state, $scope, appData, $http) {
	//	removeAnimate($('.scrollBox2'));
	appData.operation = "请选择登录方式";
	$scope.operation = appData.operation;
	appData.handle = 'self';
	appData.xsbb = false;
	appData.a = 1;
	appData.isSon = false;
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//	addAnimate($('.scrollBox2'))
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		appData.SwipeType = "sbCard";
		$state.go('login');
	}
	$scope.prevStep = function() {
		$state.go("personChoice")
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory, $timeout) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.isLoding = true;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go("login");
	}
	switch($scope.loginType) {
		case "idcard":
			$scope.operation = "身份证登录";
			break;
		case "sscard":
			$scope.operation = "社保卡登录";
			break;
		case "cloud":
			$scope.operation = "随申办登录";
			break;
	}

	$scope.nextStep = function() {
		// 如果是本人就去信息展示界面，若果是代办就去验证被监护人信息
		if(appData.handle == "agent") {
			appData.operation = "被监护人登录方式"
			appData.source = "idcardOrCitizen";
			appData.isSon = true;
			$state.go("loginType");
		} else {
			$state.go("info");
			//$scope.$apply();
		}
	}
//	$scope.idcardLogin = function() {
//		let info = {
//			Name:"赵璧",
//			Number:"430181199611042694"
//		}
//		appData.licenseName = info.Name;
//		appData.licenseNumber = info.Number;
//		appData.fatherInfo = info;
//		appData.fatherNumber = info.Number; // 存储监护人的身份证号
//		$state.go("info");
//	}
//	$scope.idcardLogin()
	$scope.idcardLogin = function(info, images) {
		if(info) {
			if(appData.isSon) {
				// 被监护人刷身份证
				$scope.faceImage = images;
				appData.sonInfo = info;
				appData.licenseName = info.Name;
				appData.licenseNumber = info.Number;
				if(judgeOld(info.Number)) {
					$scope.isAlert = true;
					$scope.msg = "监护人代办条件为子女年龄不超过16周岁";
					return;
				}
				if(info.Number == appData.fatherNumber) {
					$scope.isAlert = true;
					$scope.msg = "被监护人的身份证不能与监护人的身份证一致";
					return;
				} else {
					$state.go("info");
				}
			} else {
				$scope.faceImage = images;
				$scope.loginType = 'recognition';
				appData.licenseName = info.Name;
				appData.licenseNumber = info.Number;
				appData.fatherInfo = info;
				appData.fatherNumber = info.Number; // 存储监护人的身份证号
			}
		} else {
			$scope.isAlert = true;
			$scope.msg = "很抱歉,没有获取到您的信息,请重试";
		}
	}
	$scope.sscardLogin = function(info) {
		if(info) {
			if(info.Ssn != "" && info.Ssn != null && info.Ssn != undefined) {
				if(appData.isSon) {
					appData.sonInfo = {
						Name: info.PeopleName,
						Number: info.Ssn
					};
					appData.licenseName = info.PeopleName;
					appData.licenseNumber = info.Ssn;
					if(judgeOld(info.Ssn)) {
						$scope.isAlert = true;
						$scope.msg = "监护人代办条件为子女年龄不超过16周岁";
						return;
					}
					if(info.Ssn == appData.fatherNumber) {
						$scope.isAlert = true;
						$scope.msg = "被监护人的身份证不能与监护人的身份证一致";
						return;
					} else {
						$state.go("info");
					}
				} else {
					appData.fatherInfo = {
						Name: info.PeopleName,
						Number: info.Ssn
					};
					appData.licenseName = info.PeopleName;
					appData.licenseNumber = info.Ssn;
					appData.fatherNumber = info.Ssn; // 存储监护人的身份证号
					$.device.ssCardClose();
					$scope.nextStep();
				}
			} else {
				$scope.isAlert = true;
				$scope.msg = "未读取到您的社保卡信息,请重试";
			}

		} else {
			layer.msg("没有获取到")
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		appData.img = img;
		$scope.nextStep();
	}

	$scope.prevStep = function() {
		$.device.Face_Close();
		if(appData.isSon) {
			$state.go("login");
		} else {
			$state.go("loginType");
		}
	}
	$scope.citizenLogin = function(info) {
		var idcardInfo = [];
		if(info) {
			if(appData.qrCodeType != "suishenma") {
				idcardInfo = info.result.data;
				if(appData.isSon) {
					appData.sonInfo = idcardInfo;
					appData.licenseNumber = idcardInfo.idcard;
					appData.licenseName = idcardInfo.realname;
					if(judgeOld(idcardInfo.idcard)) {
						$scope.isAlert = true;
						$scope.msg = "监护人代办条件为子女年龄不超过16周岁";
						return;
					}
					if(idcardInfo.idcard == appData.fatherNumber) {
						$scope.isAlert = true;
						$scope.msg = "被监护人的身份证不能与监护人的身份证一致";
					} else {
						$state.go("info");
					}
				} else {
					appData.fatherNumber = idcardInfo.idcard; // 记录监护人的身份证号
					appData.fatherInfo = idcardInfo;
					appData.licenseNumber = idcardInfo.idcard;
					appData.licenseName = idcardInfo.realname;
					$scope.nextStep();
				}
			} else {
				idcardInfo = info;
				$scope.suishenBanLogin(idcardInfo);
			}
		} else {
			$scope.isAlert = true;
			$scope.msg = "很抱歉,没有获取到您的信息,请重试";
		}
	}
	$scope.suishenBanLogin = function(info) {
		var idcardInfo = info;
		if(info) {
			if(appData.isSon) {
				appData.sonInfo = idcardInfo;
				appData.licenseName = idcardInfo.zwdtsw_name;
				appData.licenseNumber = idcardInfo.zwdtsw_cert_id;
				if(judgeOld(idcardInfo.zwdtsw_cert_id)) {
					$scope.isAlert = true;
					$scope.msg = "监护人代办条件为子女年龄不超过16周岁";
					return;
				}
				if(idcardInfo.zwdtsw_cert_id == appData.fatherNumber) {
					$scope.isAlert = true;
					$scope.msg = "被监护人的身份证不能与监护人的身份证一致";
				} else {
					$state.go("info");
				}
			} else {
				appData.fatherNumber = idcardInfo.zwdtsw_cert_id; // 记录监护人的身份证号
				appData.fatherInfo = idcardInfo;
				appData.licenseNumber = idcardInfo.zwdtsw_cert_id;
				appData.licenseName = idcardInfo.zwdtsw_name;
				$scope.nextStep();
			}
		} else {
			$scope.isAlert = true;
			$scope.msg = "很抱歉,没有获取到您的信息,请重试";
		}
	}
})
app.controller("info", function($scope, $state, appData, $sce, appFactory) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.funName = "查看个人信息";
	appData.xsbb = true;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isSon = appData.isSon;
	var fatherInfo = appData.fatherInfo; //监护人信息、本人操作时信息
	var sonInfo = appData.sonInfo; // 被监护人信息

	// 展示个人或者监护人信息
	$scope.licenseName = fatherInfo.Name || fatherInfo.realname || fatherInfo.zwdtsw_name;
	$scope.licenseNumber = fatherInfo.Number || fatherInfo.idcard || fatherInfo.zwdtsw_cert_id;
	$scope.Sex = ((parseInt($scope.licenseNumber.substring(16, 17)) % 2) == 0) ? "女" : "男";
	$scope.People = fatherInfo.People || "未查询到";
	$scope.Birthday = fatherInfo.Birthday || ($scope.licenseNumber).substring(6, 10) + "-" + ($scope.licenseNumber).substring(10, 12) + "-" + ($scope.licenseNumber).substring(12, 14);
	$scope.ValidtermOfStart = fatherInfo.ValidtermOfStart || fatherInfo.VALIDSTARTDAY || "未查询到";
	$scope.ValidtermOfEnd = fatherInfo.ValidtermOfEnd || fatherInfo.VALIDENDDAY || "未查询到";
	// 展示被监护人信息
	if(appData.isSon) {
		// 展示被监护人信息
		$scope.sonlicenseName = sonInfo.Name || sonInfo.realname || sonInfo.zwdtsw_name;
		$scope.sonlicenseNumber = sonInfo.Number || sonInfo.idcard || sonInfo.zwdtsw_cert_id;
		$scope.sonSex = ((parseInt($scope.sonlicenseNumber.substring(16, 17)) % 2) == 0) ? "女" : "男";
		$scope.sonPeople = sonInfo.People || "未查询到";
		$scope.sonBirthday = sonInfo.Birthday || ($scope.licenseNumber).substring(6, 10) + "-" + ($scope.licenseNumber).substring(10, 12) + "-" + ($scope.licenseNumber).substring(12, 14);;
		$scope.sonValidtermOfStart = sonInfo.ValidtermOfStart || sonInfo.VALIDSTARTDAY || "未查询到";
		$scope.sonValidtermOfEnd = sonInfo.ValidtermOfEnd || sonInfo.VALIDENDDAY || "未查询到";
		// 记录以下信息，方便后边查询社保卡信息
		appData.sonlicenseName = $scope.sonlicenseName;
		appData.sonlicenseNumber = $scope.sonlicenseNumber;
		appData.fatherName = $scope.licenseName;
		appData.fatherNumber = $scope.licenseNumber;
	}

	// 下一步填写社保卡后四位
	$scope.nextStep = function() {
		$state.go("submit");
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false, // 垂直的
			hScrollbar: false, // 水平的
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});

app.controller("submit", function($scope, $state, appData, $sce, appFactory) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.funName = "填写社保卡信息";
	$scope.nextText = "提交";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.isContinue = true; // 判断代办人的社保卡信息是否拿到
	$scope.isOpen = false; // 判断拿到的社保卡状态是否可以开通
	$scope.concel = "false";
	$scope.count = "";
	$scope.ztList = [35, 41, 45, 70]; // 可以办理激活的社保卡状态码zt
	$scope.keybordArr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 'X', 0, '删除'];
	$scope.keyboardInput = function(e) { //不允许用键盘输入
		var keycode = window.event ? e.keyCode : e.which;
		console.log(keycode);
		if(keycode >= 48 && keycode <= 57 || keycode == 88) {
			if($scope.count.length >= 5) {
				$scope.count = $scope.count.substring(0, $scope.count.length - 1);
				return;
			} else {}
		} else if(keycode == 8) {} else {
			$scope.count = $scope.count.substring(0, $scope.count.length - 1);
			return;
		}
	}
	$scope.inputNumber = function(item) { //软键盘输入
		console.log(item);
		if(item === '删除') {
			$scope.count = $scope.count.substring(0, $scope.count.length - 1);
		} else {
			if($scope.count.length >= 4) {
				return;
			} else {
				$scope.count += item;
			}
		}
	}

	// 查询监护人社保卡信息
	$scope.getfatherCardInfo = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/socialSecurityCard/getSocialSecurityCardInfo.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				zjhm: appData.fatherNumber,
				zjlx: "01", // 01:居民身份证
				sbkh: "",
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				var info = dataJson.body;
				if(!info) {
					layer.msg("未查询到代办人信息");
					$scope.isContinue = false;
					return;
				}
				appData.dbr_xm = info.xm;
				appData.dbr_lxdh = info.sjhm;
				appData.dbr_zjlx = info.zjlx;
			},
			error: function(err) {
				$.log.debug("err:" + JSON.stringify(err))
			}
		});
	}
	if(appData.isSon) {
		$scope.getfatherCardInfo();
	}

	// 查询被监护人社保卡信息
	$scope.getssCardInfo = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/socialSecurityCard/getSocialSecurityCardInfo.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				zjhm: appData.sonlicenseNumber || appData.licenseNumber,
				zjlx: "01", // 01:居民身份证
				sbkh: "",
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				var info = dataJson.body;
				if(!info) {
					$scope.isAlert = true;
					$scope.msg = dataJson.head.return_msg;
					$scope.isContinue = false;
					return;
				} else {
					appData.sbkh = info.sbkh;
					appData.shbzhm = info.shbzhm;
					appData.zt = info.zt;
					// 根据zt判断此人能否继续激活社保卡
					if(isOpen(appData.zt, $scope.ztList)) {
						$scope.isOpen = true;
					} else {
						$scope.isOpen = false;
						$scope.isAlert = true;
						$scope.msg = "您的社保卡状态目前暂不支持激活";
						$scope.alertConfirm = function(){
							$state.go('loginType');
						}
					}
				}
			},
			error: function(err) {
				$scope.isAlert = true;
				$scope.msg = "访问失败";
				$.log.debug("err:" + JSON.stringify(err))
			}
		});
	}
	$scope.getssCardInfo();

	//节流阀
	let flag = true;
	// 判断是否开通成功
	$scope.nextStep = function() {
		if(flag) {
			if(!$scope.count || $scope.count.length != 4) {
				$scope.isAlert = true;
				$scope.msg = "请填写银行卡号后4位";
				return;
			}
			if(!$scope.isContinue) {
				$scope.isAlert = true;
				$scope.msg = "对不起，您无法办理社保卡开通服务";
				return;
			}
			if(!$scope.isOpen) {
				$scope.isAlert = true;
				$scope.msg = "对不起，您无法激活社保卡";
				return;
			}
			flag = false;
			$scope.isLoading = true;
			$.ajax({
				type: "get",
				url: $.getConfigMsg.preUrlSelf + "/selfapi/socialSecurityCard/changeCardActivated.do",
				dataType: "json",
				jsonp: "jsonpCallback",
				data: {
					shbzhm: appData.shbzhm,
					sbkh: appData.sbkh,
					yhkh: $scope.count,
					bgyxq: "", //变更有效期
					zxyy: "", // 注销原因
					kplzzt: appData.zt,
					dbr_xm: (appData.isSon == true)?appData.fatherName:"",
					dbr_lxdh: (appData.isSon == true)?appData.dbr_lxdh:"",
					dbr_zjlx: "",
					dbr_zjhm: (appData.isSon == true)?appData.fatherNumber:"",
					bz: "",
				},
				success: function(dataJson) {
					flag = true;
					$scope.isLoading = false;
					var info = dataJson.body;
					if(dataJson) {
						$scope.isAlert = true;
						$scope.msg = dataJson.head.return_msg;
					}
					//模块使用记录
					$scope.jsonStr = {
						SUCCESS: "true",
						data: {
							name: "新版社保卡开通服务",
							Number: "",
							managerID:appData.managerID
						}
					}
					recordUsingHistory('公安服务', '办理', "新版社保卡开通", appData.sonlicenseName || appData.licenseName, appData.sonlicenseNumber || appData.licenseNumber, '', "", JSON.stringify($scope.jsonStr));
					//行为分析(办理)
					trackEventForAffairs("", "新版社保卡开通", "上海市公安局", appData.sonlicenseName || appData.licenseName, appData.sonlicenseNumber || appData.licenseNumber, "");
				},
				error: function(err) {
					flag = true;
					$scope.isAlert = true;
					$scope.msg = "访问失败，请重新登录";
					$.log.debug("err:" + JSON.stringify(err))
				}
			});
		} else {
			return;
		}
	}

	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
});