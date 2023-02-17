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
app.controller('loginType', function($state, $scope, appData, $location) {
	$scope.operation = "请选择登录方式";
	$scope.funName = appData.funName;
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
	$scope.operation = "请选择登录方式";
	$scope.funName = appData.funName;
	$scope.funName = newPerjsonStr.medical_compre_reduce_trial.stuffName;
	appData.type = newPerjsonStr.medical_compre_reduce_trial.type;
	appData.ywlx = newPerjsonStr.medical_compre_reduce_trial.ywlx;

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
	$scope.operation = "请选择登录方式";

	$scope.funName = newPerjsonStr.medical_compre_reduce_trial.stuffName;
	appData.type = newPerjsonStr.medical_compre_reduce_trial.type;
	appData.ywlx = newPerjsonStr.medical_compre_reduce_trial.ywlx;

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

app.controller('login', function($scope, $http, $state, appData, appFactory) {
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
			dataType: "json",
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
				name: encodeURI(appData.licenseName),
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
				$state.go("bookMakingTakePhoto");
			} else {
				//				appData.zhh = "";
				//				$state.go("bookMakingTakePhoto");
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

			//			appData.licenseNumber = '310228198808070818';
			//			appData.licenseName = '陈雷';

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
				//$state.go('idcardOrCitizen');
				$state.go('main');
			} else if(appData.source == 'bookMakingChoose') {
				//$state.go('bookMakingChoose');
				$state.go('main');
			} else {
				$state.go('main');
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
			appData.agentName = idcardInfo.realname;
			appData.agentNumber = idcardInfo.idcard;
			$scope.searchInsuredInfo();
		} else {
			layer.msg("很抱歉，未获取到相关信息，请重试。");
		}
	}
});
// 原来的bookMaking控制器
app.controller('main', function($scope, $state, appData, $sce) {
	$scope.isAlert = false;
	$scope.operation = "请选择刷卡方式";
	appData.xsbb = false;
	appData.SwipeType = ""; // 存储刷卡方式
	appData.handle = ""; // 存储办理人    "本人"或"代理人"

	$scope.funName = newPerjsonStr.handle_emy_medical_record.stuffName;
	appData.type = newPerjsonStr.handle_emy_medical_record.type;
	appData.ywlx = newPerjsonStr.handle_emy_medical_record.ywlx;

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
		$state.go('main');
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
				//$scope.$apply();
				$scope.isShowSscard = false;
				$scope.searchInsuredInfo();
			} else if(appData.handle == "agent") {
				$.device.ssCardClose();
				try {
					$scope.$apply();
				} catch(e) {}
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
		$state.go('main');
	}

	$scope.alertCancel = function() {
		$scope.isAlert = false;
		$state.go('main');
	}

	$scope.prevStep = function() {
		$state.go('main');
	}
});

app.controller('bookMakingReason', function($scope, $http, $state, appData, $sce, appFactory) {
	$scope.operation = '请选择制册原因';
	appData.xsbb = true;
	$scope.updateRecordBookReason = ""; // 补册原因
	$scope.isAlert = false; // 是否显示打印提示框
	$scope.isShowPrint = false;
	$scope.concel = "false";
	$scope.isLoading = false;
	$scope.alertConfirm = function() {
		$state.go("main");
	}

	//记录册补册校验
	$scope.updateRecordBookCheck = function(){
		$scope.isLoading = true;
		$.ajax({
			type:"get",
			url:$.getConfigMsg.preUrl + "/aci/workPlatform/medicalInsurance/updateRecordBookCheck.do",
			dataType:'json',
			data:{
				knsj:appData.licenseNumber,
			},success:function(res){
				$scope.isLoading = false;
				if(res.ybkh=="" && res.sbkh == ""){
					$scope.concel = true;
					$scope.isAlert = true;
					$scope.msg = "您的卡号为空，暂不能办理，请联系工作人员";
					$scope.alertCancel = function() {
						$scope.isAlert = false;
						$state.go('main');
					}
				}
			},error:function(err){
				$scope.isLoading = false;
				$scope.concel = true;
				$scope.isAlert = true;
				$scope.msg = "校验接口异常，请重试";
				$scope.alertCancel = function() {
					$scope.isAlert = false;
					$state.go('main');
				}
				console.log(err);
			}
		});
	}
	$scope.updateRecordBookCheck();
	$scope.cont = 0;
	$scope.choiceReason = function(reason) {
		if($scope.cont == 1) {
			return
		}else{
			$scope.cont = 1;
			if(reason == "Renewed") {
				// 存储制册原因为"用完换新"
				$scope.updateRecordBookReason = "07";
				$scope.getTodayInfoByIdCard();
			} else if(reason == "newMore") {
				// 存储制册原因为"新增制册"
				$scope.updateRecordBookReason = "04";
				$scope.getTodayInfoByIdCard();
			} else if(reason == "Reissue") {
				// 存储制册原因为"遗失补办"
				$scope.updateRecordBookReason = "01";
				$scope.getTodayInfoByIdCard();
			}
		};
	}

	// 记录册更换
	$scope.updateRecordBook = function() {
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/medicalInsurance/updateRecordBook.do",
			dataType: "json",
			data: {
				zhh: appData.zhh,
				bcyy: $scope.updateRecordBookReason,
				wtrxm: encodeURI(appData.agentName || ''),
				wtrsfzh: appData.agentNumber || '',
				machineMac: jQuery.getConfigMsg.uniqueId || ""
			},
			success: function(returnData) {
				console.log(returnData);
				console.log("账户号：" + appData.zhh);
				if(returnData == undefined || returnData == "" || returnData == null) {
					$scope.sfjxzcbz = 1;
					$scope.isAlert = true;
					$scope.msg = "接口异常，请稍候再试";
				} else if(!returnData[0].syscode) {
					$scope.newBookBh = returnData[0].jlch; // 新就医记录册编号
					$scope.newBookKh = returnData[0].kh; // 新就医记录册卡号
					$scope.userXb = returnData[0].xb == "1" ? "男" : "女";
					$scope.userName = returnData[0].xm;
					$scope.userAge = returnData[0].sfzh.substring(6, 10) + "年" + returnData[0].sfzh.substring(10, 12) + "月";
					//模块使用记录
					$scope.jsonStr = {
						SUCCESS: "true",
						data: {
							name: '办理就医记录册的申领、更换、补发',
							newBookBh: $scope.newBookBh,
							userName: $scope.userName,
							userXb: $scope.userXb,
							newBookKh: $scope.newBookKh,
							userAge: returnData[0].sfzh,
							managerID:appData.managerID
						}
					}
					$scope.name = appData.agentName || appData.licenseName;
					$scope.Number = appData.agentNumber || appData.licenseNumber;
					recordUsingHistory('医保服务', '办理', '办理就医记录册的申领、更换、补发', $scope.name, $scope.Number, '', appData.imageData, JSON.stringify($scope.jsonStr));
					//行为分析(查询)
					trackEventForQuery("办理就医记录册的申领、更换、补发", "", "查询", "上海市医疗保障局", $scope.name, $scope.Number, "");

					$scope.confirm = function() {
						$scope.isAlert = true;
						$scope.msg = "<p>正在制册中，请等待…</p><p>友情提示：请从自助终端右侧自助领取塑料套</p>";
						$.device.medicalPrint($scope.newBookBh, $scope.newBookKh, $scope.userXb, $scope.userName, $scope.userAge);
						console.log("制册人基本信息--" + $scope.newBookBh + "---" + $scope.newBookKh + "---" + $scope.userXb + $scope.userName + $scope.userAge);
						$.log.debug("制册人基本信息--" + $scope.newBookBh + $scope.newBookKh + $scope.userXb + $scope.userName + $scope.userAge);
						setTimeout(function() {
							$state.go("main");
						}, 9000);
					};
					$scope.confirm();
					saveDeviceStatus("MedicalInsuranc", 0, "正常", 0, 0, 0, 1);
				} else if(returnData[0].syscode) {
					$scope.sfjxzcbz = "1";
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
					} else if(returnData[0].errmsg == "[E001]未找到该参保人") {
						$scope.isAlert = true;
						$scope.msg = "未找到该参保人";
					}
				}
			},
			error: function(err) {
				$scope.isAlert = true; // 是否显示打印提示框
				$scope.msg = "未找到该参保人";
			}
		});
	}
	// 记录册今日打印次数
	$scope.getTodayInfoByIdCard = function() {
		$http.jsonp($.getConfigMsg.preUrl + "/aci/workPlatform/medicalInsurance/getTodayInfoByIdCard.do", {
			params: {
				jsonpCallback: "JSON_CALLBACK",
				identNo: appData.licenseNumber,
				range: "day" // day：当日；week：当周；month：当月
			}
		}).success(function(returnData) {
			if(returnData > 0) {
				$scope.concel = true;
				$scope.isAlert = true;
				$scope.msg = "<p>今日您已办理过<b style='font-weight:bold'> " + returnData + "次 </b>医保记录册</p><p>请确认是否继续办理</p>";
				$scope.alertCancel = function() {
					$scope.isAlert = false;
				}
				$scope.alertConfirm = function() {
					$scope.isAlert = false;
					$scope.updateRecordBook();
				}
			} else {
				$scope.updateRecordBook();
			}
		}).error(function(err) {
			$scope.isAlert = false; // 是否显示打印提示框
			$scope.updateRecordBook();
		});
	}
	$scope.alertConfirm = function() {
		if($scope.sfjxzcbz == "1") {
			$scope.isAlert = false;
			$state.go("main");
		} else {
			$scope.isAlert = false;
		}
	}

	$scope.alertCancel = function() {
		if($scope.sfjxzcbz == "1") {
			$scope.isAlert = false;
			$state.go("main");
		} else {
			$scope.isAlert = false;
		}
	}

	$scope.prevStep = function() {
		$state.go('main');
	}
});

app.controller('bookMakingTakePhoto', function($scope, $state, appData, $sce) {
	$scope.isShowPrint = false;
	$scope.operation = "请看摄像头";
	$scope.camera = true;
	$scope.imageData = null;
	$scope.isWonders = false;
	$scope.__cameraPos = $scope.cameraPos || {
		width: 640,
		height: 480,
		x: 600,
		y: 310
	};
	if(window.innerWidth <= 1600) {
		$scope.__cameraPos.x = 300;
		$scope.__cameraPos.y = 310;
	}
	//	if(acBridgeMac.imagePath() == "zhuofansoft_gzt"||acBridgeMac.imagePath() == "zhuofan_superMachine") {
	//		$scope.__cameraPos.x = 310;
	//		$scope.__cameraPos.y = 600;
	//	}
	$scope.$on("$destroy", function() {
		$.device.Camera_Hide();
	});
	if(acBridgeMac.vendor() == "wonders") {
		$scope.isWonders = true;
		//初始化摄像头
		$.device.Camera_Init($scope.__cameraPos.width, $scope.__cameraPos.height, $scope.__cameraPos.x, $scope.__cameraPos.y); //初始化摄像头
		var camera = window.external.GetConfig('camera');
		var index = window.external.GetConfig('resolution') || 1;
		$.device.Camera_Link(camera, index); //初始化摄像头
		$.device.Camera_Show();
		$scope.capture = function() {
			$.device.Camera_Base64(function(data) {
				$scope.capturePhoto = data;
				appData.imageData = $scope.capturePhoto;
				$scope.imageData = "data:image/png;base64," + $scope.capturePhoto;
				$.device.Camera_Hide();
				$scope.camera = false;
			});
		};
	} else {
		$scope.capture = function() { //拍照
			$.device.Face_Show($scope.__cameraPos.width, $scope.__cameraPos.height, $scope.__cameraPos.x, $scope.__cameraPos.y, function(info) {
				$scope.capturePhoto = info;
				appData.imageData = $scope.capturePhoto;
				$scope.imageData = "data:image/png;base64," + $scope.capturePhoto;
				$.device.Face_Close();
				$scope.camera = false;
			});
		}
		$scope.capture();
	};
	$scope.reCapture = function() {
		$scope.capture();
		$scope.camera = true;
	};
	$scope.confirm = function() {
		$state.go("bookMakingReason");
	};
	$scope.prevStep = function() {
		$state.go('main');
	}
});