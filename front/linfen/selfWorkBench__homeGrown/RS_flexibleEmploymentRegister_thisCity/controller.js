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
app.controller("guideline", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	appData.funName = "本市户籍人员灵活就业登记";
	appData.itemNo = $scope.itemNo = "312090045000";
	$scope.stuffName = appData.funName;
	$scope.info = "<p>本事项办理需符合以下条件：</p>" +
		"<p>1、	本人办理</p>" +
		"<p>2、	本市户籍人员</p>" +
		"<p>3、	年龄范围为16周岁以上，男性未满60周岁、女性未满55周岁</p>" +
		"<p>4、	持有《就业创业证》（或《就业失业登记证》、《劳动手册》）</p>" +
		"<p>5、	自助终端仅能办理起始日期在当月的灵活就业登记</p>";
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
	$scope.prevStep = function() {
		window.location.href="../aSocial/index.html#/flexibleEmployment";
	}
	$scope.nextStep = function() {
		$state.go('loginType');
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: true,
			bounce: true,
			click: true,
			taps: true,
			preventDefault: false,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
	addAnimate($('.main2'))
});
app.controller('loginType', function($state, $scope, appData) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.operation = "请选择登录方式";
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
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go("loginType");
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
			$scope.operation = "随申办登录";
			break;
	}

	//判断是否符合办理条件
	$scope.handleCondition = function() {
		$scope.paramStr = {
			person_name: encodeURI(appData.licenseName),
			cert_id: appData.licenseNumber
		}
		appFactory.pro_fetch("LD0015Q1", "ld", appData.applyNo, JSON.stringify($scope.paramStr), function(dataJson) {
			console.log(dataJson);
			$scope.isLoading = false;
			if(!isBlank(dataJson)) {
				$scope.head = dataJson.data.msg.head;
				$scope.body = dataJson.data.msg.body;
				if(!isBlank($scope.head)) {
					if($scope.head.rst.buscode == "000000" && !isBlank($scope.body.pid)) {
						appData.hylbResult = $scope.body;
						$state.go("info");
					} else {
						$scope.isAlert = true;
						$scope.msg = "您无法自助办理该项业务，请前往窗口咨询办理！";
					}
				} else {
					$scope.isAlert = true;
					$scope.msg = "您无法自助办理该项业务，请前往窗口咨询办理！";
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

	//test 跳过核验
//	$scope.idcardLogin = function() {
//		appData.licenseNumber = "310111196303070036";
//		appData.licenseName = "吴兴宝";
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
			appData.Address = info.Address;
			appData.nation = info.People;
			if(appData.nation.lastIndexOf('族') < 0) {
				appData.nation = appData.nation + '族';
			}
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
			appData.encrypt_identity = ClearBr(info.encrypt_identity);
			$scope.nextStep();
		} else {
			var idcardInfo = info.result.data;
			appData.licenseName = idcardInfo.realname;
			appData.VALIDENDDAY = idcardInfo.VALIDENDDAY;
			appData.VALIDSTARTDAY = idcardInfo.VALIDSTARTDAY;
			$scope.nextStep();
		}
	}
})
app.controller('info', function($state, $scope, appData, $timeout, appFactory, $http) {
	$scope.operation = "请填写基本信息";
	$scope.nextText = "提交";
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoading = false;
	$scope.jyqsrq = "";
	let jyqsrq;
	//获取当前月份开始 结束
	let date = new Date();
	let year = date.getFullYear();
	let month = date.getMonth() + 1;
	let firstDay = new Date(year, month - 1, 1); //这个月的第一天
	let currentMonth = firstDay.getMonth(); //取得月份数
	let lastDay = new Date(firstDay.getFullYear(), currentMonth + 1, 0); //是0而不是-1
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//初始化日期控件
	$(".form_datetime").datetimepicker({
		format: "yyyy-mm-dd", //显示日期格式
		autoclose: true,
		todayBtn: true,
		minView: "month", //只选择到天自动关闭
		language: 'zh-CN',
		pickerPosition: 'bottom-left',
		startDate: firstDay,
		endDate: lastDay
	});
	$scope.lhjyTypeList = lhjyType;
	$scope.checkList = check;

	$scope.change = function(type, index) {
		if(type == "ztsbjbz") {
			$scope.ztsbjbz = index;
		} else if(type == "ztycsbjbz") {
			$scope.ztycsbjbz = index;
		} else if(type == "btnltsbz1") {
			$scope.btnltsbz1 = index;
		} else if(type == "btnltsbz2") {
			$scope.btnltsbz2 = index;
		} else if(type == "sydjtsbz1") {
			$scope.sydjtsbz1 = index;
		} else if(type == "sydjtsbz2") {
			$scope.sydjtsbz2 = index;
		}
	}
	console.log(appData.hylbResult);
	//接口获取信息预填
	$scope.stName = appData.licenseName;
	$scope.stIdCard = appData.licenseNumber;
	//监听日期控件 变化
	$timeout(function() {
		$scope.dateControl = function() {
			$scope.isLoading = true;
			$scope.paramStr = {
				person_name: encodeURI(appData.licenseName),
				cetf_id: appData.licenseNumber,
				pid: appData.hylbResult.pid.toString(),
				zygz: "",
				jyqsrq: jyqsrq,
				czygh: "",
				bz: "",
				lhjylx: $scope.lhjyType.id,
				czqx_id: appData.hylbResult.czqx_id.toString(),
				czjd_id: appData.hylbResult.czjd_id.toString(),
				czjw_id: appData.hylbResult.czjw_id.toString(),
				czdz: encodeURI(appData.hylbResult.czdz),
				czlxdh: appData.hylbResult.czlxdh.toString(),
			}
			appFactory.pro_fetch("LD0015J1", "ld", appData.applyNo, JSON.stringify($scope.paramStr), function(dataJson) {
				console.log(dataJson);
				$scope.isLoading = false;
				if(!isBlank(dataJson)) {
					$scope.head = dataJson.data.msg.head;
					$scope.body = dataJson.data.msg.body;
					if(!isBlank($scope.head)) {
						if($scope.head.rst.buscode == "000000") {
							$scope.checkResult = $scope.body;
							$scope.ztsbjbz = $scope.checkResult.ztsbjbz; //暂停失保金，是否继续
							$scope.ztycsbjbz = $scope.checkResult.ztycsbjbz; //暂停延长失保金，是否继续
							$scope.btnltsbz1 = $scope.checkResult.btnltsbz1; //特殊工种协保就业补贴年龄段未到，是否继续
							$scope.btnltsbz2 = $scope.checkResult.btnltsbz2; //大龄协保就业补贴年龄段未到，是否继续
							$scope.sydjtsbz1 = $scope.checkResult.sydjtsbz1; //请先操作失业登记，才能符合大龄失业就业补贴条件，是否继续
							$scope.sydjtsbz2 = $scope.checkResult.sydjtsbz2; //请先操作失业登记，才能符合大龄失业社保补贴条件，是否继续
						} else {
							$scope.isAlert = true;
							$scope.msg = $scope.head.rst.errmsg;
							$scope.alertConfirm = function() {
								$scope.isAlert = false;
								$state.go("main");
							}
						}
					} else {
						$scope.isAlert = true;
						$scope.msg = "您暂无校验数据！";
						$scope.alertConfirm = function() {
							$scope.isAlert = false;
							$state.go("main");
						}
					}
				} else {
					$scope.isAlert = true;
					$scope.msg = "校验接口异常,请稍后再试";
					$scope.alertConfirm = function() {
						$scope.isAlert = false;
						$state.go("main");
					}
				}
			}, function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "校验接口异常,请稍后再试";
				$scope.alertConfirm = function() {
					$scope.isAlert = false;
					$state.go("main");
				}
			});
		}
		$('.form_datetime')
			.datetimepicker()
			.on('changeDate', function(ev) {
				if(ev) {
					jyqsrq = $scope.jyqsrq = formatDateCustom(ev.date.valueOf());
					jyqsrq = (jyqsrq.replace(/-/g, ""));
					let condFlag = false;
					do {
						if(isBlank($scope.lhjyType)) {
							$scope.isAlert = true;
							$scope.msg = "请选择就业类型";
							return;
						}
					} while (condFlag);
					$scope.dateControl();
				}
			});

		$scope.$watch('lhjyType', function(val) {
			if(val) {
				console.log(val);
				console.log($scope.jyqsrq);
				if($scope.jyqsrq) {
					$scope.dateControl();
				}
			}
		})
	});

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

	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.lhjyType)) {
				$scope.isAlert = true;
				$scope.msg = "请选择就业类型";
				return;
			}
			if(isBlank($scope.jyqsrq)){
				$scope.isAlert = true;
				$scope.msg = "请选择就业起始日期";
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
						$state.go("guideline");
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
				person_name: encodeURI(appData.licenseName),
				cetf_id: appData.licenseNumber,
				pid: appData.hylbResult.pid.toString(),
				zygz: "",
				jyqsrq: jyqsrq,
				czygh: "",
				bz: "",
				lhjylx: $scope.lhjyType.id,
				czqx_id: appData.hylbResult.czqx_id.toString(),
				czjd_id: appData.hylbResult.czjd_id.toString(),
				czjw_id: appData.hylbResult.czjw_id.toString(),
				czdz: encodeURI(appData.hylbResult.czdz),
				czlxdh: appData.hylbResult.czlxdh.toString(),
				ztsbjbz: $scope.ztsbjbz,
				ztycsbjbz: $scope.ztycsbjbz,
				btnltsbz1: $scope.btnltsbz1,
				btnltsbz2: $scope.btnltsbz2,
				sydjtsbz1: $scope.sydjtsbz1,
				sydjtsbz2: $scope.sydjtsbz2,
			}
			console.log($scope.saveParamStr);
			appFactory.pro_fetch("LD0015S1", "ld", appData.applyNo, JSON.stringify($scope.saveParamStr), function(dataJson) {
				console.log(dataJson);
				if(!isBlank(dataJson)) {
					$scope.head = dataJson.data.msg.head;
					$scope.body = dataJson.data.msg.body;
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
		$scope.saveApplyInfo();
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