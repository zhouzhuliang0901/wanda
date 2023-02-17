function removeAnimate(ele) {
	$(ele).css({
		"transform": "translateY(0px)",
		"top": 0
	}).removeClass('transformto')
}

function addAnimate(ele) {
	$(ele).addClass('transformto');
}
app.controller('loginType', function($state, $scope, appData) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	appData.funName = "养老金卡（折）调整";
	appData.itemCode = "312090092000";
	$scope.operation = "请选择登录方式";
	//获取统一审批编码
	$scope.getApplyNoByItemNo = function(code) {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + '/aci/workPlatform/elderlyCard/getApplyNoByItemNo.do',
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data: {
				itemCode: code
			},
			success: function(dataJson) {
				if(dataJson.success === true) {
					appData.applyNo = dataJson.aplyNo;
				} else {
					appData.applyNo = "";
				}
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	$scope.getApplyNoByItemNo(appData.itemCode);
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.concel = "false";
	switch($scope.loginType) {
		case "idcard":
			$scope.operation = "身份证登录";
			break;
		case "ukey":
			$scope.loginBtn = true;
			$scope.operation = "ukey登录";
			break;
		case "cloud":
			$scope.operation = "随申办登录";
			break;
	}

	//判断是否符合办理条件
	$scope.handleCondition = function() {
		$scope.age = IdCard(appData.licenseNumber, 3);
		$scope.sex = IdCard(appData.licenseNumber, 2);
		if(($scope.sex == "男" && $scope.age >= 53) || ($scope.sex == "女" && $scope.age >= 45)) {
			$state.go("info");
		} else if($scope.sex == "男") {
			$scope.isAlert = true;
			$scope.msg = "很抱歉，您未满53周岁，不符合该事项办理条件！";
			$scope.alertConfirm = function() {
				$scope.isAlert = false;
				$state.go("loginType");
			}
		} else if($scope.sex == "女") {
			$scope.isAlert = true;
			$scope.msg = "很抱歉，您未满45周岁，不符合该事项办理条件！";
			$scope.alertConfirm = function() {
				$scope.isAlert = false;
				$state.go("loginType");
			}
		}
	}
	//test 跳过核验
	//	$scope.idcardLogin = function() {
	//		appData.licenseNumber = "310228196605311053";
	//		appData.licenseName = "胡秀强";
	//		$scope.handleCondition();
	//	}
	//	$scope.idcardLogin();

	//跳转页面
	$scope.nextStep = function() {
		$scope.tokenType = "token";
		$scope.token = function() {
			$scope.handleCondition();
		}
	}

	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
			appData.VALIDENDDAY = info.ValidtermOfEnd;
			appData.VALIDSTARTDAY = info.ValidtermOfStart;
		} else {
			layer.msg("没有获取到")
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		$scope.handleCondition();
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}

	$scope.citizenLogin = function(info) {
		function ClearBr(key) {
			key = key.replace(/\+/g, "-");
			key = key.replace(/\#/g, ",");
			return key;
		}
		if(appData.qrCodeType == "suishenma") {
			appData.licenseName = info.zwdtsw_name;
			appData.licenseNumber = info.zwdtsw_cert_id;
			appData.zwdtsw_user_id = info.zwdtsw_user_id;
			appData.encrypt_identity = ClearBr(info.encrypt_identity);
			$scope.nextStep();
		} else {
			var idcardInfo = info.result.data;
			appData.licenseName = idcardInfo.realname;
			appData.licenseNumber = idcardInfo.idcard;
			appData.VALIDENDDAY = idcardInfo.VALIDENDDAY;
			appData.VALIDSTARTDAY = idcardInfo.VALIDSTARTDAY;
			$scope.nextStep();
		}
	}
})
app.controller('info', function($state, $scope, appData, appFactory, $http) {
	$scope.operation = "请填写基本信息";
	$scope.nextText = "提交";
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.idCard = appData.licenseNumber;
	$scope.name = appData.licenseName;
	$scope.bankList = bankList;
	//银行选择
	$scope.change = function(index, item) {
		$scope.current = index;
		$scope.id = item.id;
		$scope.clid = item.clid;
		$scope.bankName = item.name;
	}
	//获取用户id
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
				if(dataJson.zwdtsw_user_id != undefined && dataJson.zwdtsw_user_id != null && dataJson.zwdtsw_user_id != "") {
					appData.zwdtsw_user_id = dataJson.zwdtsw_user_id;
				} else {
					$scope.isAlert = true;
					$scope.msg = dataJson.ERROR || '未获得对应的用户标识！';
					$scope.alertConfirm = function() {
						$state.go("loginType");
					}
				}
			},
			error: function(err) {
				console.log("getUserInfoByAccessToken err");
				$scope.isAlert = true;
				$scope.msg = '未获得对应的用户标识！';
				$scope.alertConfirm = function() {
					$state.go("loginType");
				}
			}
		});
	}
	if(appData.zwdtsw_user_id) {} else {
		$scope.getUserInfoByAccessToken();
	}

	$scope.getBankParamStr = {
		zjlx: "01",
		zjhm: appData.licenseNumber
	}
	//获取本人（原）养老金发放银行及银行卡
	$scope.getPrimaryBank = function() {
		$scope.isLoading = true;
		appFactory.pro_fetch("LD0060Q1", "sb", appData.applyNo, JSON.stringify($scope.getBankParamStr), function(dataJson) {
			console.log(dataJson);
			$scope.isLoading = false;
			if(!isBlank(dataJson)) {
				$scope.head = dataJson.data.response.msg.head;
				$scope.body = dataJson.data.response.msg.body;
				if(!isBlank($scope.head)) {
					if($scope.head.rst.buscode == "000000") {
						$scope.bankResult = $scope.body;
						$scope.ffxs = $scope.bankResult.ffxs;
						$scope.ffzh = $scope.bankResult.ffzh;
						let bank = bankCardAttribution($scope.ffzh);
						$scope.oldBankName = bank.bankName;
					} else {
						$scope.isAlert = true;
						$scope.msg = $scope.head.rst.errmsg;
					}
				} else {
					$scope.isAlert = true;
					$scope.msg = "查询接口异常,请稍后再试";
				}
			} else {
				$scope.isAlert = true;
				$scope.msg = "查询接口异常,请稍后再试";
			}
		}, function(err) {
			$scope.isLoading = false;
			$scope.isAlert = true;
			$scope.msg = "查询接口异常,请稍后再试";
		});
	}
	$scope.getPrimaryBank();

	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.newBankId)) {
				$scope.isAlert = true;
				$scope.msg = "请填写养老金卡(折)！";
				return;
			}
			if(isBlank($scope.id)) {
				$scope.isAlert = true;
				$scope.msg = "请选择养老金发放银行";
				return;
			}

		} while (condFlag);
		//提交参数集合
		condFlag = true;
		//办件信息同步接口
		$scope.saveItemInfo = function(result) {
			$http.get($.getConfigMsg.preUrlSelf + "/selfapi/pensionAdjustment/saveItemInfo.do", {
				params: {
					userId: appData.zwdtsw_user_id,
					itemCode: encodeURI(appData.itemCode),
					itemName: encodeURI(appData.funName),
					username: encodeURI(appData.licenseName),
					mobile: "",
					idCardNo: appData.licenseNumber,
					result: result
				}
			}).success(function(dataJson) {
				if(dataJson.code == "200") {
					appData.applyNo = dataJson.data.applyNo;
					if(result == "1") {
						$state.go("submit");
					} else {
						$state.go("loginType");
					}
				} else {
					$scope.isLoading = false;
					appData.applyNo = "";
					$scope.isAlert = true;
					$scope.msg = "办件信息同步失败,请稍后再试";
				}
			}).error(function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "办件信息同步异常,请稍后再试";
			});
		}
		//人社不见面统一 -- 发放形式保存接口
		$scope.saveApplyInfo = function() {
			$scope.isLoading = true;
			$scope.saveParamStr = {
				pid: $scope.bankResult.pid.toString(),
				yhdm: $scope.id,
				ffxs_in: $scope.ffxs,
				yhzh: $scope.newBankId,
				kzbz: "1",
				shzlall: "|001,发放机构变更核定表,1,1|002,《领取养老金方式确认表（个人填写）》,4,0|003,居民身份证正、反面复印件,4,0|004,本人在本市指定金融机构范围内开立的实名制结算账户卡（折）复印件,4,0|005,委托人和受托人居民身份证正、反面复印件,4,0|",
				shzlsl: "0",
				hdbsl: "1",
				tsds: "0",
				shzltotal: "5",
				hdbbh: "核10表",
				czqx: "",
			}
			console.log($scope.saveParamStr);
			appFactory.pro_fetch("LD0060S1", "sb", appData.applyNo, encodeURI(JSON.stringify($scope.saveParamStr)), function(dataJson) {
				console.log(dataJson);
				if(!isBlank(dataJson)) {
					$scope.head = dataJson.data.response.msg.head;
					$scope.body = dataJson.data.response.msg.body;
					if(!isBlank($scope.head)) {
						if($scope.head.rst.buscode == "000000") {
							$scope.saveResult = $scope.body;
							$scope.saveItemInfo("1");
						} else {
							$scope.isAlert = true;
							$scope.msg = $scope.head.rst.errmsg;
							$scope.alertConfirm = function() {
								$scope.isAlert = false;
								$scope.saveItemInfo("0");
							}
						}
					} else {
						$scope.isLoading = false;
						$scope.isAlert = true;
						$scope.msg = "保存接口异常,请稍后再试";
					}
				} else {
					$scope.isLoading = false;
					$scope.isAlert = true;
					$scope.msg = "保存接口异常,请稍后再试";
				}
			}, function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "保存接口异常,请稍后再试";
			});
		}
		//银行卡校验
		$scope.checkBankCradWithPerson = function() {
			$scope.isAlert = false;
			$scope.isLoading = true;
			$scope.alertConfirm = function() {
				$scope.isAlert = false;
				$scope.isLoading = false;
			}
			$http.get($.getConfigMsg.preUrlSelf + "/selfapi/CivilServiceController/checkBankCradWithPerson.do", {
				params: {
					bankId: $scope.newBankId,
					idCard: appData.licenseNumber,
					name: encodeURI(appData.licenseName),
					bankKey: $scope.clid,
				}
			}).success(function(dataJson) {
				console.log(dataJson);
				if(dataJson.code == "200" && dataJson.data.match == "000") {
					$scope.saveApplyInfo();
				} else if(dataJson.code == "200" && dataJson.data.match == "001") {
					$scope.isAlert = true;
					$scope.msg = "当前填写的银行卡（折）非申请人所有，请更换为您本人的银行卡（折）！";
				} else {
					$scope.isAlert = true;
					$scope.msg = "银行卡校验异常,请重试";
				}
			}).error(function(err) {
				console.log(err);
				$scope.isAlert = true;
				$scope.msg = "银行卡校验异常,请重试";
			});
		}
		$scope.isAlert = true;
		$scope.msg = "<p style='margin-top: -50px;'>养老金发放银行:" + $scope.bankName + "</p>" + "<p>发放卡折号:" + $scope.newBankId + "</p><p>请确认您调整后的养老金发放银行及发放账号！</p>";
		$scope.alertConfirm = function() {
			$scope.checkBankCradWithPerson();
		}
	}
});
app.controller('submit', function($state, $scope, appData) {
	$scope.funName = appData.funName;
	$scope.applyNo = appData.applyNo;
	$scope.nextText = "返回首页";
	//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: $scope.funName,
			Number: $scope.applyNo,
		}
	}
	recordUsingHistory('人社服务', '办理', $scope.funName, appData.licenseName, appData.licenseNumber, '', $scope.applyNo, JSON.stringify($scope.jsonStr));
	//行为分析(办理)
	trackEventForAffairs($scope.applyNo, $scope.funName, "上海市人力资源社会保障局", appData.licenseName, appData.licenseNumber, '');
	$scope.goHome = function() {
		$.device.GoHome();
	}
});