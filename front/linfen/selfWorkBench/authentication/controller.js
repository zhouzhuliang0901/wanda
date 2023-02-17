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
app.controller("main", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.stuffName = perjsonStr;
	$scope.choiceType = function(type, name, ywlx) {
		trackEvent(name);
		appData.funName = name;
		appData.type = type;
		appData.ywlx = ywlx;
		$state.go("loginType");
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
app.controller('loginType', function($state, $scope, appData, $http) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.operation = "请选择登录方式";
	$scope.person = appData.person;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory, $timeout) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isLoding = true;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	switch($scope.loginType) {
		case "idcard":
			$scope.operation = "身份证登录";
			break;
		case "ukey":
			$scope.loginBtn = true;
			$scope.operation = "ukey登录";
			break;
		case "cloud":
			$scope.operation = "随申办";
			break;
	}

	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			appData.Number = info.Number;
			appData.Name = info.Name;
			appData.idCardImg = $scope.faceImage;
			$state.go('recognition');
		} else {
			layer.msg("没有获取到")
		}
	}

	$scope.prevStep = function() {
		$state.go("loginType");
	}
	$scope.citizenLogin = function(info) {
		if(appData.qrCodeType == "suishenma") {
			appData.Name = info.zwdtsw_name;
			appData.Number = info.zwdtsw_cert_id;
			$state.go('recognition');
			$scope.$apply();
		} else {
			var idcardInfo = info.result.data;
			appData.Name = idcardInfo.realname;
			appData.Number = idcardInfo.idcard;
			$state.go('recognition');
			$scope.$apply();
		}
	}

})
app.controller("recognition", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoding = true;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$scope.recognitionOver = false;
	}
	//	$.device.Camera_Init(640, 480, 640, 240);
	//	var camera = window.external.GetConfig('camera');
	//	var index = window.external.GetConfig('resolution') || 1;
	//	$.device.Camera_Link(camera, index); //初始化摄像头
	//	$.device.Camera_Show();
	$scope.capture = function() { //拍照
		$.device.Face_Show(640, 480, 640, 240, function(info) {
			if(window.external.GetConfig('liveDetection') == 'N') {
				if(info) {
					$scope.showImage = "../libs/common/images/recognition.png";
					$scope.tipsText = "正在核验...";
					$scope.istakePhoto = false;
					$scope.capturePhoto = info;
					$scope.getTokenSNO($scope.faceImage, $scope.capturePhoto);
					$.device.Face_Close();
				} else {
					$scope.tipsText = "活体检测失败";
					$.device.Face_Close();
					$scope.capture();
				}
			} else {
				$timeout(function() {
					info = JSON.parse(info);
					if(info.Success === true) {
						$scope.showImage = "../libs/common/images/recognition.png";
						$scope.tipsText = "正在核验...";
						$scope.istakePhoto = false;
						$scope.capturePhoto = $.device.fileBase64(info.Data.ImageUrl);
						$scope.getTokenSNO($scope.faceImage, $scope.capturePhoto);
						$.device.Face_Close();
					} else if(info.Success === false) {
						$scope.tipsText = "活体检测失败";
						$.device.Face_Close();
						$scope.capture();
					}
				}, 1000)
			}
		})
	};
	$scope.capture();
	$scope.recognitionOver = false;
	//获取token ------1、两照对比获取tokenSNO
	$scope.getTokenSNO = function(faceImg,photo) {
		var rec = $.ajax({
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getTokenSNO.do",
			type: "post",
			dataType: "json",
			data: {
				name: appData.Name,
				idCard: appData.Number,
				facePhoto: photo,
				copyIDPhoto: faceImg || photo
			},
			success: function(res) {
				if(res.SUCCESS === true && res.verify === 1) {
					$scope.isAlert = true;
					$scope.msg = "比对成功";
					appData.tokenSNO = res.tokenSNO;
					$scope.alertConfirm = function() {
						$state.go("inputPhone");
					}
				} else if(res.SUCCESS === false && res.verify === 1) {
					appData.codeSNO = res.codeSNO;
					if(res.phone != undefined && res.phone != "" && res.phone != null) {
						appData.phone = res.phone;
						$scope.isAlert = true;
						$scope.msg = "您为一网通办初级用户，请前往升级";
					} else {
						$scope.isAlert = true;
						$scope.msg = "您暂无一网通办账号，请前往注册";
					}
					$scope.alertConfirm = function() {
						$state.go('info');
					}
				} else {
					$scope.isAlert = true;
					$scope.msg = "比对失败，请重试";
					$scope.alertConfirm = function() {
						$scope.isAlert = false;
						$scope.reRecognition();
					}
				}
			},
			error: function(err) {
				$scope.isAlert = true;
				$scope.msg = "比对失败，请重试";
				$scope.alertConfirm = function() {
					$scope.isAlert = false;
					$scope.reRecognition();
				}
			},
			complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
				if(status === 'timeout' || status === 'error') { //超时,status还有success,error等值的情况
					rec.abort();　　　　
				}　　
			}
		})
	}
	//	$scope.capture = function() {
	//		$scope.recognitionOver = true;
	//		$scope.isLoding = false;
	//		$scope.showImage = "../libs/common/images/recognition.png";
	//		appData.faceImg = $.device.Camera_Base64();
	//		$.device.audioStop();
	//		$.device.Camera_Hide();
	//		$timeout(function() {
	//			$scope.getTokenSNO();
	//		}, 100);
	//	}
	$scope.reRecognition = function() {
		$.device.Camera_Show();
		$scope.capture();
		$scope.recognitionOver = false;
		$scope.isLoding = true;
	}
	$scope.prevStep = function() {
		$.device.Camera_Hide();
		$state.go("loginType");
	}
})
app.controller("inputPhone", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	removeAnimate($('.stable'))
	addAnimate($('.stable'))
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.nextText = "提交";
	$scope.isLoding = true;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.nation = ["汉族", "蒙古族", "回族", "藏族", "维吾尔族", "苗族", "彝族", "壮族", "布依族", "朝鲜族", "满族", "侗族", "瑶族", "白族", "土家族", "哈尼族", "哈萨克族", "傣族", "黎族", "傈僳族", "佤族", "畲族", "高山族", "拉祜族", "水族", "东乡族", "纳西族", "景颇族", "柯尔克孜族", "土族", "达斡尔族", "仫佬族", "羌族", "布朗族", "撒拉族", "毛难族", "仡佬族", "锡伯族", "阿昌族", "普米族", "塔吉克族", "怒族", "乌孜别克族", "俄罗斯族", "鄂温克族", "崩龙族", "保安族", "裕固族", "京族", "塔塔尔族", "独龙族", "鄂伦春族", "赫哲族", "门巴族", "珞巴族", "基诺族"];
	$scope.Name = appData.Name;
	$scope.idCard = appData.Number;
	$scope.count = (appData.sumbitInfo == undefined) ? "请输入" : appData.sumbitInfo.stMobile;
	$scope.keybordArr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 'X', 0, '删除'];
	$scope.sex = ((parseInt(appData.Number.substring(16, 17)) % 2) == 0) ? "女" : "男";
	$scope.keyboardInput = function(e) { //键盘输入
		var keycode = window.event ? e.keyCode : e.which;
		if(keycode >= 48 && keycode <= 57) {} else if(keycode == 8) {} else {
			console.log(e);
			$scope.count = $scope.count.substring(0, $scope.count.length - 1);
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
	$scope.prevStep = function() {
		$state.go('loginType');
	}
	$scope.nextStep = function() {
		console.log($scope.selectedName);
		$scope.isLoding = false;
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/CA/dataSynchronizationToCivilAdministration.do",
			dataType: "json",
			data: {
				userName: appData.Name,
				sex: ($scope.sex == "男") ? "1" : "2",
				identNo: appData.Number,
				nation: $scope.selectedName,
				birthday: (appData.Number).substring(6, 10) + "-" + (appData.Number).substring(10, 12) + "-" + (appData.Number).substring(12, 14),
				mobile: $scope.count,
				cardCreateDate: "",
				cardValidity: "",
				cardCompany: "",
				residenceAddress: $scope.stAddress,
				photo: appData.faceImg
			},
			success: function(data) {
				$scope.isLoding = true;
				if(data.SUCCESS == "true") {
					$scope.isAlert = true;
					$scope.msg = "线下认证完成";
					$scope.alertConfirm = function() {
						$.device.GoHome();
					}
					//模块使用记录
					$scope.jsonStr = {
						SUCCESS: "true",
						data: {
							name: '市民网上实名认证',
						}
					}
					recordUsingHistory('经信委服务', '实名认证', '市民网上实名认证', appData.Name, appData.Number, $scope.count, '', JSON.stringify($scope.jsonStr));
					//行为分析(办理)
					trackEventForAffairs("", "市民网上实名认证", "上海市经济和信息化委员会", appData.Name, appData.Number, "");
				} else {
					if(data.result == "-1200") {
						$scope.isAlert = true;
						$scope.msg = "您已是高级实名用户,无需再次认证";
						$scope.alertConfirm = function() {
							$.device.GoHome();
						}
					} else {
						$scope.isAlert = true;
						$scope.msg = $scope.MSG;
						$scope.alertConfirm = function() {
							$.device.GoHome();
						}
					}
				}
			},
			error: function(err) {
				$scope.isLoding = true;
				$scope.isAlert = true;
				$scope.msg = "信息同步接口有误，请重试！";
			}
		});
	}
})
app.controller("info", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope, $interval) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.operation = "填写个人信息--注册";
	$scope.show = false;
	$scope.show1 = false;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.nextText = "提交";
	$scope.code = true;
	$scope.isLoding = true;
	$scope.isEqualPhone = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.stName = appData.Name;
	$scope.stIdCard = appData.Number;
	$scope.stMobile = appData.phone || "";
	$scope.view = function() {
		$scope.show = true;
		document.getElementById('stPassword').type = "text";
		setCaretPosition('stPassword');
	}
	$scope.hidden = function() {
		$scope.show = false;
		document.getElementById('stPassword').type = "password";
		setCaretPosition('stPassword');
	}
	$scope.view1 = function() {
		$scope.show1 = true;
		document.getElementById('stPassword1').type = "text";
		setCaretPosition('stPassword1');
	}
	$scope.hidden1 = function() {
		$scope.show1 = false;
		document.getElementById('stPassword1').type = "password";
		setCaretPosition('stPassword1');
	}
	//获取短信验证码
	$scope.getMesageCode = function() {
		if(appData.phone === $scope.stMobile) {
			$scope.isEqualPhone = true;
		} else {
			$scope.isEqualPhone = false;
		}
		$scope.code = false;
		$scope.time = 60;
		$scope.timer = null;
		$scope.timeCount = function() {
			$interval.cancel($scope.timer);
			$scope.timer = $interval(function() {
				$scope.time--;
				if($scope.time < 1) {
					$interval.cancel($scope.timer);
					$scope.code = true;
				}
			}, 1000);
			$.ajax({
				type: "get",
				url: $.getConfigMsg.preUrl + "/aci/workPlatform/sendMessage.do",
				dataType: "jsonp",
				jsonp: "jsonpCallback",
				data: {
					codeSNO: appData.codeSNO,
					mobile: $scope.stMobile
				},
				success: function(dataJson) {
					console.log(dataJson);
				},
				error: function(err) {
					console.log();
				}
			});
		}
		$scope.timeCount();
	}
	// 保存数据
	$scope.flag = true;
	$scope.prevStep = function() {
		$state.go("loginType");
	}
	$scope.nextStep = function() {
		if(!$scope.flag) {
			return ''
		}
		//		if($('#stMobile').val()!=appData.phone){
		//			$scope.isAlert = true;
		//			$scope.concel = "true";
		//			$scope.msg = "确认修改已绑定手机号?";
		//			$scope.alertConfirm = function(){}
		//		}
		var condFlag = false;
		do {
			if($('#stName').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入姓名！";
				return;
			}
			if(!checkIdCard($('#stIdCard').val())) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的身份证号码！";
				return;
			}
			if(!isPhoneAvailable($('#stMobile').val())) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的手机号！";
				return;
			}
			if(!passwordAvailable($('#stPassword').val())) {
				$scope.isAlert = true;
				$scope.msg = "不能单独数字、字母、特殊字符的密码，且长度不能小于8不能大于18！";
				return;
			}
			if(!passwordAvailable($('#stPassword1').val())) {
				$scope.isAlert = true;
				$scope.msg = "不能单独数字、字母、特殊字符的密码，且长度不能小于8不能大于18！";
				return;
			}
			if($('#stPassword1').val() != $('#stPassword').val()) {
				$scope.isAlert = true;
				$scope.msg = "两次密码输入不一致！";
				return;
			}
			if($('#stCode').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入验证码！";
				return;
			}
		} while (condFlag);
		appData.submitInfo = {
			stName: $scope.stName,
			stIdCard: $scope.stIdCard,
			stMobile: $scope.stMobile,
			stBirth: $scope.stBirth,
			stPassword: $scope.stPassword,
			stCode: $scope.stCode
		}
		$scope.sumbit = function() {
			$scope.isLoding = false;
			$.ajax({
				type: "get",
				url: $.getConfigMsg.preUrl + "/aci/workPlatform/registerUser.do",
				dataType: "jsonp",
				jsonp: "jsonpCallback",
				data: {
					codeSNO: appData.codeSNO,
					name: appData.Name,
					idCard: appData.Number,
					mobile: appData.submitInfo.stMobile,
					password: appData.submitInfo.stPassword || $('#stPassword').val(),
					authCode: appData.submitInfo.stCode,
					type: ""
				},
				success: function(data) {
					console.log(data);
					if(data.SUCCESS === true && data.tokenSNO != "") {
						$scope.isAlert = true;
						$scope.msg = "注册成功,请前往实名认证";
						$scope.alertConfirm = function() {
							$state.go("inputPhone");
						}
					} else {
						$scope.isAlert = true;
						$scope.msg = "注册失败,请重试";
						$scope.alertConfirm = function() {
							$state.go("info");
							return;
						}
					}
				},
				error: function(err) {
					$scope.isAlert = true;
					$scope.msg = "注册接口有误，请重试！";
				}
			});
		}
		$scope.sumbit();
		console.info(appData.submitInfo);
	};
});