app.controller("startController", function($state, $scope, $location, $http, data, $rootScope, $timeout, $interval, appFactory) {
	data.ocode = "";
	let perjsonStr = [{
		"stuffName": "社会救助项目证明",
		"type": "/main",
	}, {
		"stuffName": "领取年老一次性计划生育奖励费申请",
		"type": "/list",
	}, {
		"stuffName": "开具存档证明",
		"type": "/confirm",
	}];
	$scope.operation = "请选择办理事项";
	$scope.stuffName = perjsonStr;
	$scope.choiceType = function(type, name, ywlx) {
		data.funName = name;
		data.type = type;
		$location.path(type);
	}
});
app.controller("mainController", function($state, $scope, $location, $http, data, $rootScope, $timeout, $interval, appFactory) {
	data.itemName = "社会救助项目证明";
	$scope.largeImg = "images/2.png";
	$scope.tishiName = "仅查询本街道享受社会救助人员。";
	$scope.p1Name = "进行人证核验";
	$scope.p2Name = "判断是否为本街道受理人员";
	$scope.p3Name = "等待工作人员确认后取表";
	$scope.text = " 2.仅出具享受社会救助证明，如要出具不享受证明，请取号至窗口办理。";
	$scope.next = function() {
		$location.path("/select");
	}
	$scope.prevStep = function() {
		$location.path("/start");
	}
	$rootScope.goHome = function() {
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
	};
});
app.controller("listController", function($state, $scope, $location, $http, data, $rootScope, $timeout, $interval, appFactory) {
	$scope.largeImg = "images/1.png";
	$scope.tishiName = "若为代办人，请前往窗口办理";
	$scope.p1Name = "智能引导";
	$scope.p2Name = "身份证获取/信息填写";
	$scope.p3Name = "材料打印并提交窗口";
	$scope.text = "2.最早办理日期不得早于退休前一个月。";
	$scope.next = function() {
		$location.path("/guide");
	}
	$scope.prevStep = function() {
		$location.path("/start");
	}
});
app.controller("confirmController", function($state, $scope, $location, $http, data, $rootScope, $timeout, $interval, appFactory) {
	data.itemName = "开具存档证明";
	$scope.largeImg = "images/流动人员人事档案存放证明.png";
	$scope.tishiName = "出具本辖区证明，外辖区居民查询请取号至窗口。";
	$scope.p1Name = "进行人证核验";
	$scope.p2Name = "等待工作人员确认";
	$scope.p3Name = "申请表与签收表（屏幕签名）";
	$scope.p4Name = "开具存档证明";
	$scope.text = "";
	$scope.next = function() {
		$location.path("/select");
	}
	$scope.prevStep = function() {
		$location.path("/start");
	}
});
app.controller("guideController", function($state, $scope, $location, $http, data, $rootScope, $timeout, $interval, appFactory) {
	data.itemName = "领取年老一次性计划生育奖励费申请";
	$scope.guideName = "是否为本市企业退休";
	var number = 0;
	$scope.guideNameS = true;
	$scope.chooseT = true;
	$scope.nexta = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		if(number == 4) {
			$location.path("/select");
		} else {
			$location.path("/start");
		}
	}
	$scope.yesA = function() {
		if($scope.guideName == "是否为本市企业退休") {
			number++;
			$scope.guideName = "户籍地址是否属于宜川路街道";
		} else if($scope.guideName == "户籍地址是否属于宜川路街道") {
			number++;
			$scope.guideName = "是否有过一次以上婚史";
		} else if($scope.guideName == "是否有过一次以上婚史") {
			number--;
			$scope.guideName = "结婚日期是否在2004年4月15日之前";
		} else if($scope.guideName == "结婚日期是否在2004年4月15日之前") {
			number++;
			console.log(number);
			if(number == 4) {
				$scope.guideNameS = false;
				$scope.isAlert = true;
				$scope.msg = "请点击确定继续操作";
				$scope.chooseT = false;
				$scope.yynext = false;
				$scope.nexta = true;
			} else {
				$scope.guideNameS = false;
				$scope.chooseT = false;
				$scope.yynext = false;
				$scope.nexta = false;
				$scope.isAlert = true;
				$scope.msg = "请您取号到窗口办理";
				$timeout(function() {
					$location.path('/start');
				}, 10000);
			}
		}
	}
	$scope.no = function() {
		if($scope.guideName == "是否为本市企业退休") {
			$scope.guideName = "户籍地址是否属于宜川路街道";
		} else if($scope.guideName == "户籍地址是否属于宜川路街道") {
			$scope.guideName = "是否有过一次以上婚史";
		} else if($scope.guideName == "是否有过一次以上婚史") {
			number++;
			$scope.guideName = "结婚日期是否在2004年4月15日之前";
		} else if($scope.guideName == "结婚日期是否在2004年4月15日之前") {
			$scope.guideNameS = false;
			$scope.chooseT = false;
			$scope.yynext = false;
			$scope.nexta = false;
			$scope.isAlert = true;
			$scope.msg = "请您取号到窗口办理";
			$timeout(function() {
				$location.path('/start');
			}, 10000);
		}
	}
	$scope.next = function() {
		$location.path("/select");
	}
	$scope.prevStep = function() {
		$location.path("/list");
	}
	$rootScope.goHome = function() {
		$.device.idCardClose();
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
	};
});
app.controller("selectController", function($state, $scope, $http, $location, data, $rootScope, $timeout, appFactory) {
	$.device.Camera_Hide();
	$scope.itemName = data.itemName;
	// 刷身份证获取信息
	$scope.scanIdcard = function() {
		$location.path('/idCard');
	}
	// 市民云亮证
	$scope.citizen = function() {
		$location.path('/citizen');
	}
	$scope.prev = function() {
		if(data.itemName == "社会救助项目证明") {
			$location.path("/main");
		} else if(data.itemName == "领取年老一次性计划生育奖励费申请") {
			$location.path('/guide');
		} else if(data.itemName == "开具存档证明") {
			$location.path('/confirm');
		}
	}
});
app.controller("idCardController", function($state, $scope, $http, $location, data, $rootScope, $timeout, appFactory) {
	$scope.itemName = data.itemName;
	$scope.isRead = true;
	if(data.itemName == "社会救助项目证明") {
		$scope.isRead = true;
		$scope.getIdcard = function(info, images) {
			$scope.faceImage = images;
			$scope.isRead = false; //faceImg
			$scope.$apply();
			data.idCardName = info.Name;
			data.idCardNum = info.Number;
			data.VALIDENDDAY = info.ValidtermOfEnd;
			data.VALIDSTARTDAY = info.ValidtermOfStart;
		}
		$scope.getResult = function(img) {
			$scope.img = img;
			$location.path("/info");
		}
	} else if(data.itemName == "领取年老一次性计划生育奖励费申请") {
		$scope.isRead = true;
		$scope.getIdcard = function(info, images) {
			$scope.faceImage = images;
			$scope.isRead = false; //faceImg
			$scope.$apply();
			data.idCardName = info.Name;
			data.idCardNum = info.Number;
			data.VALIDENDDAY = info.ValidtermOfEnd;
			data.VALIDSTARTDAY = info.ValidtermOfStart;
			$location.path("/information");
		}
	} else if(data.itemName == "开具存档证明") {
		$scope.isRead = true;
		$scope.getIdcard = function(info, images) {
			$scope.faceImage = images;
			$scope.isRead = false; //faceImg
			$scope.$apply();
			data.idCardName = info.Name;
			data.idCardNum = info.Number;
			data.VALIDENDDAY = info.ValidtermOfEnd;
			data.VALIDSTARTDAY = info.ValidtermOfStart;
		}
		$scope.getResult = function(img) {
			$scope.img = img;
			$location.path("/confirmapply");
		}
	}

	$scope.prevStep = function() {
		$location.path("/select");
	}

	//	data.idCardName = "吴家忠";
	//	data.idCardNum = "31010719590318461X";

	//	data.idCardName = "陈擂";
	//	data.idCardNum = "310115198606194014";
	//	data.idCardName = "陈云翔";
	//	data.idCardNum = "310105197805313613";
	//	if(data.itemName == "社会救助项目证明") {
	//		$location.path("/info");
	//	} else if(data.itemName == "领取年老一次性计划生育奖励费申请") {
	//		$location.path("/information");
	//	} else if(data.itemName == "开具存档证明") {
	////		$location.path("/confirmapply");
	//			$location.path('/verify');
	//	}

});
app.controller("citizenController", function($state, $scope, $http, $location, data, $rootScope, $timeout, appFactory) {
	$scope.itemName = data.itemName;
	$scope.tipsText = "扫描市民云身份证二维码";
	$scope.tipsImage = "../libs/common/images/dicon/" + acBridgeMac.imagePath() + "/qrCode-self.gif";
	$scope.ClearBr = function(key) {
		key = key.replace(/<\/?.+?>/g, "");
		key = key.replace(/[\r\n]/g, "");
		return key;
	}
	$scope.prevStep = function() {
		$location.path("/select");
	}
	// 扫描二维码
	$.device.qrCodeOpen(function(code) {
		var __code = $scope.ClearBr(code);
		$.ajax({ //http://31.1.137.251:8080/ac-product/aci/window/getInfoByCodeTest.do
			url: "http://31.1.137.251:8080/ac-product/aci/window/getQrCodeInfoByElectronicCert.do", //getInfoByCodeTest  getInfoByCodeForLogin
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data: {
				codeParam: __code,
				machineId: $.config.get("uniqueId") || "",
				itemName: "",
				itemCode: "",
				businessCode: "",
				lzAddress: "一网通办智能终端"
			},
			success: function(dataJsonp) {
				if(dataJsonp.result.success) {
					data.idCardName = dataJsonp.result.data.realname;
					data.idCardNum = dataJsonp.result.data.idcard;
					data.VALIDENDDAY = dataJsonp.result.data.VALIDENDDAY;
					data.VALIDSTARTDAY = dataJsonp.result.data.VALIDSTARTDAY;
					data.mobile = dataJsonp.result.data.mobile;
					$timeout(function() {
						if(data.itemName == "社会救助项目证明") {
							$location.path('/info');
						} else if(data.itemName == "领取年老一次性计划生育奖励费申请") {
							$location.path('/information');
						} else if(data.itemName == "开具存档证明") {
							$location.path('/confirmapply');
						}
					}, 100);
				} else {
					layer.msg(dataJsonp.result.msg);
					$timeout(function() {
						$location.path('/select');
					}, 100);
				}

			},
			error: function(err) {
				console.log("二维码已过期！")
			}
		});
	}, function(err) {
		console.log("二维码扫描出错：" + err)
	});
});

app.controller("confirmapplyController", function($state, $scope, $http, $location, data, $rootScope, $timeout, appFactory) {
	$.device.idCardClose();
	$.device.Camera_Hide();
	data.idcardAddress = "";
	data.message = [];
	$scope.type2 = "";
	$scope.yynext = false;
	$scope.nexta = false;
	$scope.isLoding = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.profileShow = function() {
		$scope.isLoding = false;
		$.ajax({
			url: "http://31.1.137.251:8080/ac-product/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do",
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				identNo: data.idCardNum,
				catMainCode: "310105105000100", //
				machineId: $.config.get('uniqueId'),
				itemName: "",
				itemCode: "",
				businessCode: "",
				name: data.idCardName,
				startDay: data.VALIDSTARTDAY,
				endDay: data.VALIDENDDAY,
			},
			success: function(dataJson) {
				console.log(dataJson);
				data.idcardAddress = dataJson.SADDR;
				data.street = dataJson.MAKEDEPART;
				console.log(data.street);
				if(data.idcardAddress.indexOf("上海") != -1) {
					$scope.postOne();
				} else {
					$scope.isLoding = true;
					$scope.isAlert = true;
					$scope.msg = "档案不在我街道保存，进一步咨询请前往服务台取号！";
					$timeout(function() {
						$location.path('/start');
					}, 4000);
				}

			},
			error: function(err) {
				console.log(err)
				$scope.isLoding = true;
				$scope.isAlert = true;
				$scope.msg = "请求出错，请找工作人员联系！";
				$timeout(function() {
					$location.path('/start');
				}, 2000);
			}
		});
	};
	$scope.profileShow();

	$scope.postOne = function() {
		$.ajax({
			type: "post",
			url: "http://31.1.137.251:8088/cim_pt/workBench.submitInfoCheck.do",
			dataType: "json",
			data: {
				"userName": data.idCardName,
				"identNo": data.idCardNum,
				"address": data.idcardAddress,
				"type": "2"
			},
			success: function(dataJson) {
				var type1 = dataJson.type;
				data.postId = dataJson.data.id;
				if(data.postId != "" && type1 == "1") {
					//$scope.time();
					time();
				}
			},
			error: function(err) {
				console.log(err)
				$scope.isLoding = true;
				$scope.isAlert = true;
				$scope.msg = "出现错误 请联系工作人员！";
				$timeout(function() {
					$location.path('/start');
				}, 2000);
			}
		});
	};

	function time() {
		var time = 60;
		t = setInterval(function() {
			if(time == 0) {
				$scope.isLoding = true;
				$scope.isAlert = true;
				$scope.msg = "请求超时 ，请联系工作人员！";
				clearInterval(t);
				$.device.GoHome();
			}
			$scope.getOne();
			time--;
		}, 5000)
	}

	$scope.getOne = function() {
		$.ajax({
			type: "get",
			url: "http://31.1.137.251:8088/cim_pt/workBench.getInfoResultById.do",
			dataType: "json",
			data: {
				"id": data.postId,
				"t_": new Date().getTime()
			},
			success: function(dataJson) {
				console.log(dataJson);
				console.log(dataJson.msg);
				$scope.type2 = dataJson.type;
				$scope.getName = dataJson.data.name;
				$scope.indetNo = dataJson.data.indetNo;
				$scope.item = dataJson.data.item;
				$scope.assistance = dataJson.data.archivesNo;
				$scope.status = dataJson.data.status;

				if(dataJson.msg == "审核不通过") {
					clearInterval(t);
					$scope.isLoding = true;
					$scope.isAlert = true;
					$scope.msg = "档案不在我街道保存，进一步咨询请前往服务台取号！";
					clearInterval(t);
					$timeout(function() {
						$location.path('/start');
					}, 10000);
				}
				if(dataJson.type == "1" && dataJson.data.status == "y") {
					$scope.isLoding = true;
					data.message = {
						"name": $scope.getName,
						"identNo": $scope.indetNo,
						"item": $scope.item,
						"archivesNo": $scope.assistance, //编号
						"status": $scope.status //是否通过  y：通过； n：不通过
					}
					console.log(data.message);
					if(data.message.status == "y") {
						$scope.isLoding = true;
						$scope.yynext = true;
						$scope.nexta = true;
						$scope.isAlert = true;
						$scope.msg = "档案在我辖区，请点击继续填写相关申请信息！";
						clearInterval(t);
					}
					clearInterval(t);
				}
			},
			error: function(err) {
				console.log(err)
				$scope.isLoding = true;
				$scope.isAlert = true;
				$scope.msg = "出现错误 请联系工作人员！";
				$timeout(function() {
					$location.path('/start');
				}, 2000);
			}
		});
	}
	$scope.next = function() {
		$location.path('/verify');
	}
	$scope.prevStep = function() {
		$location.path('/select');
	}
});

app.controller("verifyController", function($state, $scope, $location, $http, data, $rootScope, $timeout, $interval, appFactory) {
	$scope.itemName = data.idCardName;
	$scope.nextText = "确认并继续";
	$scope.itemNumber = data.idCardNum;
	$scope.archivesNo = data.message.archivesNo;
	console.log($scope.archivesNo);
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	var date = new Date();
	var month = date.getMonth() + 1;
	$scope.dataTime = date.getFullYear() + "年" + month + "月" + date.getDate() + "日";
	$scope.$watch('$viewContentLoaded', function(newValue, oldValue) {
		PublicchoiceById('liveType');
		$('#liveType a').eq(2).addClass('in');
	});
	$scope.prevStep = function() {
		$location.path("/confirmapply");
	}
	$scope.flag = true;
	$scope.next = function() {
		if(!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			if($("#liveType .in").text() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择办理原因！";
				return;
			}
		} while (condFlag);
		data.itemReson = $("#liveType .in").text();
		console.log(data.itemReson);
		$location.path("/sign");
	}
});
app.controller("signController", function($state, $scope, $location, $http, data, $rootScope, $timeout, $interval, appFactory) {
	$scope.selectReson = "其他";
	$scope.nextText = "确认并继续";
	$scope.itemName = data.idCardName;
	$scope.item = data.itemReson;
	$scope.itemNumber = data.idCardNum;
	var date = new Date();
	var month = date.getMonth() + 1;
	$scope.dataTime = date.getFullYear() + "年" + month + "月" + date.getDate() + "日";
	$scope.next = function() {
		$location.path("/signatureJybt");
	}
	$scope.prevStep = function() {
		$location.path("/verify");
	}
});

app.controller("signatureJybtController", function($scope, $http, $location, $rootScope, data, appFactory) {
	$rootScope.isAlert = false;
	$scope.$watch('$viewContentLoaded', function(newValue, oldValue) {
		var name = data.itemName;
		$scope.itemName = name;
		$scope.signature = null;
		$scope.signatureFlag = false;
		$scope.SignatureBoardPlug = new SignatureBoardPlug({
			canvas: "#signature",
			clearBtn: ".clearRect",
			getSigntrue: ".saveImg",
			color: "black"
		});
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
		data.picStr = $scope.signature.split(",")[1];
		console.log(data.picStr);
		$location.path("/infoFinish");
	};
});

app.controller("infoFinishController", function($scope, $http, $location, $rootScope, data, appFactory) {
	$scope.applyNumber = data.message.archivesNo; //编号
	$scope.itemName = data.itemName; //存档证明
	$scope.stName = data.idCardName; //name
	$scope.stIdCard = data.idCardNum; //number
	$scope.item = data.itemReson; //其他、~、~
	$scope.img = data.picStr; //base64 图片
	$scope.isLoding = false;
	var lodop = $.device.printGetLodop();
	var date = new Date();
	var month = date.getMonth() + 1;
	var times = 1;
	$scope.prevStep = function() {
		$location.path("/signatureJybt");
	}
	if(data.idCardName && times == "1") {
		lodop.ADD_PRINT_TEXT(120, 239, 359, 47, "流动人员人事档案存放证明");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.SET_PRINT_STYLEA(0, "Bold", 1);
		lodop.SET_PRINT_STYLEA(0, "Horient", 2);
		lodop.ADD_PRINT_TEXT(74, 31, 600, 30, "证明申请编号：" + $scope.applyNumber);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.ADD_PRINT_TEXT(280, 110, 60, 30, "姓名:");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.ADD_PRINT_TEXT(280, 170, 85, 30, data.idCardName);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.ADD_PRINT_TEXT(280, 255, 130, 30, ",身份证号码:");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.ADD_PRINT_TEXT(280, 385, 193, 30, data.idCardNum);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.ADD_PRINT_TEXT(280, 585, 178, 30, "，该同志人事");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.ADD_PRINT_TEXT(330, 70, 180, 40, "档案现在我处存放。");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.ADD_PRINT_TEXT(432, 110, 178, 40, "特此证明。");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.SET_PRINT_STYLEA(0, "Stretch", 1); //按原图比例(不变形)缩放模式
		lodop.ADD_PRINT_TEXT(650, 288, 409, 30, "上海市普陀区宜川路街道社区事务受理服务中心");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.ADD_PRINT_TEXT(700, 420, 240, 30, date.getFullYear() + " 年 " + month + " 月 " + date.getDate() + " 日 ");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.PRINT();

		var lodop = $.device.printGetLodop();
		lodop.ADD_PRINT_TEXT(120, 219, 447, 47, "   出具流动人员人事档案证明申请表");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.SET_PRINT_STYLEA(0, "Bold", 1);
		lodop.SET_PRINT_STYLEA(0, "Horient", 2);
		lodop.ADD_PRINT_TEXT(180, 550, 200, 30, "编号：" + $scope.applyNumber);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.ADD_PRINT_TEXT(230, 57, 411, 30, "上海市普陀区宜川路街道社区事务受理服务中心");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.ADD_PRINT_TEXT(270, 103, 586, 30, "因办理:" + $scope.item + "事宜，特需要您单位开具档案存放证明，");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.ADD_PRINT_TEXT(310, 50, 150, 30, "开具对象姓名：" + data.idCardName);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.ADD_PRINT_TEXT(310, 200, 310, 30, "身份证号:" + data.idCardNum);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.ADD_PRINT_TEXT(550, 350, 400, 30, "申请人/单位经办人（签字）：");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.ADD_PRINT_IMAGE(530, 600, 100, 100, "<img border='0' src='data:image/png;base64," + $scope.img + "'>");
		lodop.SET_PRINT_STYLEA(0, "Stretch", 2);
		lodop.ADD_PRINT_TEXT(600, 350, 300, 30, "申请日期：" + date.getFullYear() + " 年 " + month + " 月 " + date.getDate() + " 日 ");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.PRINT();

		var aaa = "<style type='text/css'>.table{text-align: center;font-size: 10px;width: 100%;table-layout: fixed;word-break: break-all;height: 200px;}table, th, td{border: 1px solid black;text-align: center;overflow: hidden;}table{border-collapse:collapse;}tr{border:1px solid white;}</style>";
		var bbb = "<table><tr><th style='width:15%'>姓名</th><th style='width:25%'>身份证号</th><th style='width:20%'>事由</th><th style='width:40%'>提供内容</th></tr><tr><td>" + $scope.stName + "</td><td >" +
			data.idCardNum + "</td><td>" +
			$scope.item + "</td><td>流动人员人事档案存放证明</td></tr></table>";
		var lodop = $.device.printGetLodop();
		lodop.ADD_PRINT_TEXT(120, 270, 350, 50, "       档案证明签收单");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		lodop.SET_PRINT_STYLEA(0, "Bold", 1);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.SET_PRINT_STYLEA(0, "Horient", 2);
		lodop.ADD_PRINT_TABLE(180, 75, 780, 400, aaa + bbb);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.ADD_PRINT_TEXT(300, 72, 87, 30, "签收人：");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.ADD_PRINT_IMAGE(290, 140, 80, 75, "<img border='0' src='data:image/png;base64," + $scope.img + "'>");
		lodop.SET_PRINT_STYLEA(0, "Stretch", 2);
		lodop.ADD_PRINT_TEXT(300, 220, 290, 30, "身份证号：" + data.idCardNum);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.ADD_PRINT_TEXT(300, 498, 208, 30, "日期：" + date.getFullYear() + "年" + month + "月" + date.getDate() + "日");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.PRINT();
		$timeout(function() {
			$scope.isLoding = true;
		}, 10000);
	}
});

app.controller("informationController", function($state, $scope, $location, $http, data, $rootScope, $timeout, $interval, appFactory) {
	$.device.idCardClose();
	$scope.itemName = data.itemName;
	$scope.SisAlert = false;
	$scope.concel = "false";
	$scope.childStatus = false;
	//进入页面默认这些选项值为空  等待后续接口获取
	$scope.honorConfirm = ""; //《光荣证》编号
	$scope.honorFrom = ""; //发证机关
	$scope.marryNumber = ""; //婚姻证明编号
	$scope.birthNumber = ""; //收养文书编号
	$scope.childFamilyAddress = ""; //子女父（母）户籍地址
	$scope.childFamilyBirthday = ""; //子女父（母）出生日期
	$scope.childFamilyName = ""; //子女父（母）姓名
	$scope.childFamilyNumber = ""; //子女父（母）身份证号码
	$scope.childFamilySex = ""; //子女父（母）性别

	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		$location.path("/select");
	}
	$scope.$watch('$viewContentLoaded', function(newValue, oldValue) {
		$scope.stName = data.idCardName;
		$scope.stIdCard = data.idCardNum;

		//310115198606194014
		//生日
		$scope.birthday = data.idCardNum.substring(6, 10) + "-" + data.idCardNum.substring(10, 12) + "-" + data.idCardNum.substring(12, 14);
		$scope.stSex = ((parseInt(data.idCardNum.substring(16, 17)) % 2) == 0) ? "女" : "男";
		City();
		PublicchoiceById('liveType');
		PublicchoiceById('liveReason');
		PublicchoiceById('education');
		if(data.address) {
			$scope.stAddress = data.address;
		} else {
			//身份证信息
			appFactory.idCardInfo(data.idCardNum, data.idCardName, data.VALIDSTARTDAY, data.VALIDENDDAY, function(dataJsonp) {
				$scope.stAddress = dataJsonp.SADDR; //户籍地址
				$scope.stAddress1 = dataJsonp.SADDR;
				$scope.stAddress2 = dataJsonp.SADDR;
				$scope.stAddress3 = dataJsonp.SADDR;
			});
		}
	});

	if(data.idCardNum) {
		//户口本信息
		appFactory.addressInfo(data.idCardNum, data.idCardName, data.VALIDSTARTDAY, data.VALIDENDDAY, function(dataJsonp) {
			data.address = dataJsonp.SADDR;
			//$scope.itentify = dataJsonp.STYPE; //户口性质
			if(dataJsonp.STYPE.substring(0, 1) == "非") {
				$scope.itentify = "非农业户口";
			} else {
				$scope.itentify = "农业户口";
			}

			$scope.numberFaimly = dataJsonp.EXTENDDATA.CZRK;
			for(var i = 0; i < $scope.numberFaimly.length; i++) {
				if($scope.numberFaimly[i].XM == data.idCardName) {
					$scope.workAddress = $scope.numberFaimly[i].FWCS; //工作单位
					//婚姻状况
					if($scope.numberFaimly[i].HYZK == "丧偶") {
						$scope.marryStatus = "丧偶";
					} else if($scope.numberFaimly[i].HYZK == "离异") {
						$scope.marryStatus = "离异";
					} else {
						$scope.marryStatus = "已婚";
					}
				}
				if($scope.numberFaimly[i].HZHYHZGX == "女" || $scope.numberFaimly[i].HZHYHZGX == "子") {
					//子女姓名
					$scope.childName = $scope.numberFaimly[i].XM;
					//子女身份证号码
					$scope.childNumber = $scope.numberFaimly[i].SFZJBH;
					//性别
					$scope.childSex = ((parseInt($scope.numberFaimly[i].SFZJBH.substring(16, 17)) % 2) == 0) ? "女" : "男";
					//出生日期
					$scope.childBirthday = $scope.numberFaimly[i].SFZJBH.substring(6, 10) +
						"-" + $scope.numberFaimly[i].SFZJBH.substring(10, 12) + "-" +
						$scope.numberFaimly[i].SFZJBH.substring(12, 14);
					appFactory.birthInfo($scope.childNumber, $scope.childName, "", "", function(dataJsonp) {

					});
				}
			}
		});
	}
	if(data.idCardNum) {
		appFactory.marryInfo(data.idCardNum, data.idCardName, data.VALIDSTARTDAY, data.VALIDENDDAY, function(dataJsonp) {
			if(dataJsonp) {
				$scope.marryNumber = dataJsonp.SCHARACTER; //婚姻证明编号

			} else {
				$scope.marryNumber = "";
			}
		});
	}

	if(data.idCardNum) {
		appFactory.birthInfo(data.idCardNum, data.idCardName, data.VALIDSTARTDAY, data.VALIDENDDAY, function(dataJsonp) {
			if(dataJsonp) {
				$scope.birthConfirm = dataJsonp.CERTCODE; //出生证证号
			} else {
				$scope.birthConfirm = "";
			}
		});
	}
	if(data.idCardNum) {
		appFactory.selfInfo(data.idCardNum, data.idCardName, data.VALIDSTARTDAY, data.VALIDENDDAY, function(dataJsonp) {
			if(dataJsonp) {
				$scope.honorConfirm = dataJsonp.CERTCODE; //光荣证编号
				$scope.honorFrom = dataJsonp.MAKEDEPART; //发证机关
				//若户口本无子女，则查询光荣证明中的子女姓名、性别、出生日期
				if($scope.childName == null) {
					console.log("户口本中无子女");
					$scope.childName = dataJsonp.SCHILDNAME;
					$scope.childNumber = dataJsonp.SCHILDIDCARDNO;
					$scope.childSex = ((parseInt(dataJsonp.SCHILDIDCARDNO.substring(16, 17)) % 2) == 0) ? "女" : "男";
					$scope.childBirthday = dataJsonp.SCHILDIDCARDNO.substring(6, 10) + "-" + dataJsonp.SCHILDIDCARDNO.substring(10, 12) + "-" + dataJsonp.SCHILDIDCARDNO.substring(12, 14);
				}
			} else {
				$scope.honorConfirm = "";
				$scope.honorFrom = "";
			}
		});
	}
	$scope.choose = function() {
		if($("#liveType .in").text() == "生育（包括收养）并存活子女") {
			$scope.childStatus = true;
		}
	}
	$scope.flag = true;
	$scope.nextStep = function() {
		if(!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			if(!isPhoneAvailable($scope.mobile)) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的手机号！";
				return;
			}
			if($('#marryNumber').val() < 1) {
				workAddress
				$scope.isAlert = true;
				$scope.msg = "请填写婚姻编号！";
				return;
			}
			if($('#stAddress').val() < 1) {
				workAddress
				$scope.isAlert = true;
				$scope.msg = "请填写户籍地址！";
				return;
			}
			if($('#stAddress1').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请填写居住地址！";
				return;
			}
			if($('#workAddress').val() < 1) {
				honorConfirm
				$scope.isAlert = true;
				$scope.msg = "请填写工作单位！";
				return;
			}
			if($('#honorConfirm').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请填写光荣证编号！";
				return;
			}
			if($('#marryStatus').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择婚姻状况！";
				return;
			}
			if($('#itentify').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请填写户口性质";
				return;
			}

			if($("#liveType .in").text() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择子女情况！";
				return;
			}
			if($("#liveType .in").text() == "生育（包括收养）并存活子女") {
				if($('#childName').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请填写子女姓名！";
					return;
				}
				if($('#childSex').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请选择子女性别！";
					return;
				}
				if($('#childNumber').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请选择子女身份证号！";
					return;
				}

				if($('#stAddress2').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请填写子女居住地址！";
					return;
				}
				if($('#stAddress3').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请填写子女户籍地址！";
					return;
				}
			} else if($("#liveType .in").text() === "无子女") {
				$scope.childName = "";
				$scope.childSex = "";
				$scope.childNumber = "";
				$scope.childBirthday = "";
				$scope.birthConfirm = "";
				$scope.birthNumber = "";
				$scope.childFamilyName = "";
				$scope.childFamilySex = "";
				$scope.childFamilyBirthday = "";
				$scope.childFamilyNumber = "";
				$scope.childFamilyAddress = "";
				$scope.stAddress2 = "";
				$scope.stAddress3 = "";
			}

		} while (condFlag);
		//领取年老一次性计划生育奖励费申请审核表字段
		data.oldEncourage = {
			stName: data.idCardName,
			stSex: $scope.stSex,
			stIdCard: data.idCardNum,
			birthday: $scope.birthday,
			marryStatus: $scope.marryStatus,
			marryNumber: $scope.marryNumber,
			itentify: $scope.itentify,
			stAddress: $scope.stAddress,
			stAddress1: $scope.stAddress1,
			stAddress2: $scope.stAddress2,
			stAddress3: $scope.stAddress3,
			mobile: $scope.mobile,
			workAddress: $scope.workAddress,
			honorConfirm: $scope.honorConfirm,
			honorFrom: $scope.honorFrom,
			liveType: $("#liveType .in").text(), //子女情况
			childName: $scope.childName,
			childSex: $scope.childSex,
			childNumber: $scope.childNumber,
			childBirthday: $scope.childBirthday,
			birthConfirm: $scope.birthConfirm,
			birthNumber: $scope.birthNumber,
			childFamilyName: $scope.childFamilyName,
			childFamilySex: $scope.childFamilySex,
			childFamilyBirthday: $scope.childFamilyBirthday,
			childFamilyNumber: $scope.childFamilyNumber,
			childFamilyAddress: $scope.childFamilyAddress
		}
		console.info(data.oldEncourage);
		if(data.oldEncourage) {
			var child = "";
			if(data.oldEncourage.liveType == "无子女") {
				child = "符合婚后无子女人员年老一次性奖励条件";
			} else if(data.oldEncourage.liveType == "生育（包括收养）并存活子女") {
				child = "符合独生子女父母年老一次性奖励条件";
			}
			console.log(child);
			var date = new Date();
			var month = date.getMonth() + 1;
			var aaa = "<style>table{ font-size:13px;border-collapse:collapse;height: auto;max-width: 80%;width: 80%;table-layout: fixed;word-break: break-all;}table, th, td{border: 1px solid black;text-align: center; overflow: hidden;}td{ overflow: hidden;white-space: nowrap;text-overflow: ellipsis;}tr{height:0px;}td{line-height:20px;}</style>";
			var bbb = "<table class='table'><tr><td style='width:21%'>申请人姓名</td><td style='width:16%' id='name'>" +
				data.oldEncourage.stName + "</td><td style='width:9%'>性别</td><td style='width:12%' id='sex'>" +
				data.oldEncourage.stSex + "</td><td style='width:19%'>出生日期</td><td style='width:23%'>" +
				data.oldEncourage.birthday + "</td></tr><tr><td>婚姻状况</td><td colspan='3'>" +
				data.oldEncourage.marryStatus + "</td><td colspan='1'>婚姻证明编号</td><td colspan='1' id='confirm'>" +
				data.oldEncourage.marryNumber + "</td></tr><tr><td>身份证号码</td><td colspan='3'>" +
				data.oldEncourage.stIdCard + "</td><td colspan='1'>户口性质</td><td colspan='1'>" +
				data.oldEncourage.itentify + "</td></tr><tr><td>户籍地址</td><td colspan='5'>" +
				data.oldEncourage.stAddress + "</td></tr><tr><td>居住地址</td><td colspan='5'>" +
				data.oldEncourage.stAddress1 + "</td></tr><tr><td>联系电话</td><td colspan='3' >" +
				data.oldEncourage.mobile + "</td><td colspan='1'>工作单位</td><td colspan='1' >" +
				data.oldEncourage.workAddress + "</td></tr><tr><td>《光荣证》编号</td><td colspan='3'>" +
				data.oldEncourage.honorConfirm + "</td><td colspan='1'>发证机关</td><td colspan='1'>" +
				data.oldEncourage.honorFrom + "</td></tr><tr><td>子女情况</td><td colspan='5' id='ensureChilddren'>" +
				data.oldEncourage.liveType + "</td></tr><tr><td>子女姓名</td><td colspan='3' id='childName'>" +
				data.oldEncourage.childName + "</td><td colspan='1'>性别</td><td colspan='1' id='childSex '>" +
				data.oldEncourage.childSex + "</td> </tr> <tr><td>子女身份证号码</td><td colspan='3' id='childIdentify'>" +
				data.oldEncourage.childNumber + "</td><td colspan='1'>出生日期</td><td colspan='1' id='childBirthday '>" +
				data.oldEncourage.childBirthday + "</td></tr><tr><td>出生证证号</td><td colspan='3'>" +
				data.oldEncourage.birthConfirm + "</td><td colspan='1'>收养文书编号</td><td colspan='1' >" +
				data.oldEncourage.birthNumber + "</td></tr><tr><td>子女户籍地址</td><td colspan='5'>" +
				data.oldEncourage.stAddress2 + "</td></tr><tr><td>子女居住地址</td><td colspan='5'>" +
				data.oldEncourage.stAddress3 + "</td> </tr>  <tr><td>子女父(母)姓名</td><td >" +
				data.oldEncourage.childFamilyName + "</td><td colspan='1'>性别</td><td colspan='1'>" +
				data.oldEncourage.childFamilySex + "</td><td colspan='1'>出生日期</td><td colspan='1'>" +
				data.oldEncourage.childFamilyBirthday + "</td></tr><tr><td>户籍地址</td><td colspan='3'>" +
				data.oldEncourage.childFamilyAddress + "</td><td colspan='1'>身份证号码</td>	<td colspan='1' >" +
				data.oldEncourage.childFamilyNumber + "</td></tr><tr><td>居住地址</td><td colspan='5' >" +
				data.oldEncourage.childFamilyAddress + "</td></tr> <tr><td colspan='6'><p style='width:10px;font-weight:bold;float: left;margin-left: 20px;'>本人承诺以上所述情况属实,如有不实,愿承担由此引起的相应法律后果。</p><br/> <p style='font-weight:bold;float: left;margin-left: 30px;'>申请人签名:</p><p style='font-weight:bold;float: right;margin-right: 100px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</p></td> </tr><tr><td colspan='6'> <p style='float: left;margin-left: 20px;width:10px;'>本人户籍地镇(乡)、街道办事处审核意见:</p><br /> <p style='float: left;margin-left: 30px;margin-top: 20px;width:0px;'>经审核,该对象年老退休时</p><br><p style='float: left;margin-left: 0px;margin-top: 23px;width:0px;'>" +
				child + "</p><br /><p style='float: left;margin-left: 200px;margin-top: 25px;width:0px;'>经办人签名:</p><br /> <p style='float: left;margin-left: 0px;margin-top: 30px;width:1px;'>负责人签名:</p><br /><p style='float:right;margin-right: 20px;margin-top: 50px;'>" +
				"日期:" + date.getFullYear() + " 年 " + month + " 月 " + date.getDate() + " 日" + "</p></td></tr><tr><td colspan='6'> <p style='float: left;margin-left: 30px;margin-top: 10px;width:10px;'>社会保险经办意见:</p><br /> <p style='float: left;margin-left: 100px;width:10px;margin-top: 50px;'>经办人签名:</p> <p style='float: right;margin-right: 20px;margin-top: 100px;'>" +
				"日期:" + date.getFullYear() + " 年 " + month + " 月 " + date.getDate() + " 日" + "</p></td></tr></table>";
			var lodop = $.device.printGetLodop();
			lodop.ADD_PRINT_TEXT(90, 51, 615, 30, "     领取年老一次性计划生育奖励费申请");
			lodop.SET_PRINT_STYLEA(0, "FontSize", "20");
			lodop.SET_PRINT_STYLEA(3, "FontName", "小三");
			lodop.SET_PRINT_STYLEA(0, "BOLD", 1);
			lodop.SET_PRINT_STYLEA(0, "Horient", 2);
			lodop.ADD_PRINT_TEXT(130, 388, 300, 40, "第                               号");
			lodop.SET_PRINT_STYLEA(0, "FontName", "小五");
			lodop.SET_PRINT_STYLEA(0, "FontSize", 11);
			lodop.ADD_PRINT_TABLE(150, 70, 780, 1080, aaa + bbb);
			lodop.ADD_PRINT_TEXT(700, 575, 200, 40, " 专用章");
			lodop.SET_PRINT_STYLEA(0, "FontName", "小五");
			lodop.SET_PRINT_STYLEA(0, "FontSize", 11);
			lodop.ADD_PRINT_TEXT(942, 0, 700, 40, "说明： 1、本表一式2份，一份交发放机构，作为申领年老一次性计划生育奖励费的一项凭证，一份由镇");
			lodop.SET_PRINT_STYLEA(0, "FontName", "小五");
			lodop.SET_PRINT_STYLEA(0, "FontSize", 11);
			lodop.SET_PRINT_STYLEA(0, "Horient", 2);
			lodop.ADD_PRINT_TEXT(970, 0, 700, 40, "（乡），街道留存。2、必须用钢笔填写、涂改无效。3、本表经《人口世界》网上打印，作用等同正表。");
			lodop.SET_PRINT_STYLEA(0, "FontName", "小五");
			lodop.SET_PRINT_STYLEA(0, "FontSize", 11);
			lodop.SET_PRINT_STYLEA(0, "Horient", 2);
			lodop.PRINT();
			$scope.flag = false;
			$location.path("/table");
		}
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: true,
			bounce: true,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});
app.controller("tableController", function($state, $scope, $location, $http, data, $rootScope, $timeout, $interval, appFactory) {
	$scope.operation = "个人电子证照";
	$scope.nextText = "确认打印";
	//	data.idCardName = "陈云翔";
	//	data.idCardNum = "310105197805313613";
	$scope.licenseNumber = data.idCardNum; // 号码
	$scope.licenseType = "0";
	$scope.currentPage = 1; //当前页
	$scope.totalPages = 1; //总页数
	$scope.showLicenseList = []; //展示的图片容器
	$scope.totalLicense = []; //所有证照容器
	$scope.historyData = []; //历史上传
	$scope.elicenseData = []; //电子证照库电子证照
	$scope.elicenseData1 = [];
	$scope.currentLicense = 'license'; //现在证照类型
	$scope.currentImgIndex = null; //现在选择图片下标
	$scope.electImg = ''; //当前选中图片地址
	$scope.isAlert = false;
	$scope.concel = "false";
	data.type = "license";
	$scope.isLoding = false;
	$scope.addmassage = "正在加载数据，请稍后...";
	$scope.alertConfirm = function() {

	}
	$scope.alertCancel = function() {

	}
	$scope.currentList = function(current) {
		if(current === undefined) {
			current = $scope.currentPage;
		}
		$scope.currentImgIndex = null;
		$scope.startPos = (current - 1) * 3; //0 -3  3 - 6 
		$scope.endPos = $scope.startPos + 3;
		if($scope.currentLicense === 'history' &&
			$scope.totalLicense.indexOf("../libs/common/images/addImg.png") === -1
		) {
			$scope.totalLicense.unshift("../libs/common/images/addImg.png"); //当为历史上传材料时添加上传按钮
		}
		$scope.showLicenseList = $scope.totalLicense.slice($scope.startPos, $scope.endPos);
		$scope.totalPages = Math.ceil($scope.totalLicense.length / 3);
		$scope.showLicenseList.length = 3;
	}
	$scope.pitchOnImg = function(i, item) {
		if(item !== undefined && item.length > 1) {
			console.log("这是添加图片操作");
			$rootScope.router = 'history';
			$state.go("upload");
		}
		if(item) {
			$scope.currentImgIndex = i;
			$scope.stuffId = (item.stPersonalDocument ? item.stPersonalDocument : "");
			$scope.electImg = (item.pictureUrlForBytes ? item.pictureUrlForBytes : item.imageUrl);
			$scope.certName = item.certName;
			$scope.pdfLicense = item.derivePictureUrlForBytes;
		}
	}
	$scope.choiceLicenseType = function(type) {
		$scope.currentImgIndex = null;
		$scope.currentLicense = type;
		$scope.currentPage = 1;
		$scope.currentList();
		if(type == 'license') {
			data.type = "license";
			if($scope.elicenseData.length > 0) {
				$scope.totalLicense = $scope.elicenseData1.slice(0, $scope.elicenseData1.length);
				$scope.currentList(1);
			} else {
				$scope.getLicenseList();
			}
		} else if(type == 'history') {
			data.type = "history";
			if($scope.historyData.length > 0) {
				$scope.totalLicense = $scope.historyData.slice(0, $scope.historyData.length);
				$scope.currentList(1);
			} else {
				$scope.getHistoryData();
			}

		}
	}
	$scope.configUrl = "http://31.1.137.251:8080/ac-product"; //$.getConfigMsg.preUrl
	$scope.nextPage = function() {
		if($scope.currentPage < $scope.totalPages) {
			++$scope.currentPage;
			$scope.currentList();
		}
	};
	$scope.prevPage = function() {
		if($scope.currentPage > 1) {
			--$scope.currentPage;
			$scope.currentList();
		}
	};
	$scope.reLoadCount = 5;
	$scope.getLicenseList = function() { //电子证照库数据
		$rootScope.router = undefined;

		var httpConfig = {
			jsonpCallback: "JSON_CALLBACK",
			certNo: $scope.licenseNumber, //"340881199303145313" || 
			type: $scope.licenseType, //"0" ||
			machineId: $.config.get("uniqueId") || "",
			itemName: data.itemName,
			itemCode: "",
			businessCode: "",
		};
		$timeout(function() {
			$http.jsonp("http://31.1.137.251:8080/ac-product/aci/autoterminal/dzzz/queryCertBaseData.do", {
					params: httpConfig
				})
				.success(function(data) {
					$scope.isLoding = true;
					if(data) {
						$scope.elicenseData = data;
						for(var i = 0; i < $scope.elicenseData.length; i++) {
							if($scope.elicenseData[i].certName == "中华人民共和国居民身份证") {
								$scope.elicenseData1.push($scope.elicenseData[i]);
							}
							if($scope.elicenseData[i].certName == "中华人民共和国结婚证") {
								$scope.elicenseData1.push($scope.elicenseData[i]);
							}
							if($scope.elicenseData[i].certName == "居民户口簿") {
								$scope.elicenseData1.push($scope.elicenseData[i]);
							}
							if($scope.elicenseData[i].certName == "独生子女父母光荣证") {
								$scope.elicenseData1.push($scope.elicenseData[i]);
							}
						}
						console.log($scope.elicenseData1);
						$scope.totalLicense = $scope.elicenseData1.slice(0, $scope.elicenseData1.length);
						$scope.currentList()
					}
				})
				.error(function(err) {
					$scope.isLoding = true;
					if($scope.reLoadCount > 1) {
						$scope.getLicenseList();
					}
					$scope.reLoadCount--;
				})
		})

	};

	$scope.getHistoryData = function() { //历史上传数据
		$timeout(function() {
			appFactory.pro_fetch("forward.do", {
				fmd: "aci-archives",
				fdo: "getLicenseStuffList",
				stName: data.idCardName, // "夏雷" ||"340881199303145313" || 
				stIdNo: $scope.licenseNumber,
				type: "0"
			}, function(data) {
				if(!data) {
					$scope.totalLicense = [];
				} else {
					$scope.historyData = data;
					$scope.totalLicense = $scope.historyData.slice(0, $scope.historyData.length);
					console.log($scope.totalLicense);
				}
				$scope.currentList();
			}, function(err) {
				if($scope.reLoadCount > 1) {
					$scope.getHistoryData();
				}
				$scope.reLoadCount--;
			})
		})
	};

	if($rootScope.router) {
		$scope.choiceLicenseType('history');
	} else {
		$scope.getLicenseList();
	}

	$scope.preview = function() {
		for(var i = 0; i < $scope.elicenseData1.length; i++) {
			if($scope.elicenseData1[i].certName == "中华人民共和国居民身份证") {
				$scope.isLoding = false;
				$scope.addmassage = "正在打印，请稍等...";
				LODOP_PRINT.personLicense($scope.configUrl + $scope.elicenseData1[i].pictureUrlForBytes);
			}
			if($scope.elicenseData1[i].certName == "独生子女父母光荣证") {
				$scope.isLoding = false;
				$scope.addmassage = "正在打印，请稍等...";
				$.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		urlHost + 
					$scope.configUrl + $scope.elicenseData1[i].derivePictureUrlForBytes,
					"C:\\pdfLicense1.pdf",
					//将选中图片下载
					function(bytesCopied, totalBytes) {
						console.log(bytesCopied + "," + totalBytes);
					},
					function(result) {
						$.device.pdfPrint("C:/pdfLicense1.pdf");
					},
					function(webexception) {
						alert("下载文档失败");
					}
				);
			}
			if($scope.elicenseData1[i].certName == "居民户口簿") {
				$scope.isLoding = false;
				$scope.addmassage = "正在打印，请稍等...";
				$.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		urlHost + 
					$scope.configUrl + $scope.elicenseData1[i].derivePictureUrlForBytes,
					"C:\\pdfLicense2.pdf",
					//将选中图片下载
					function(bytesCopied, totalBytes) {
						console.log(bytesCopied + "," + totalBytes);
					},
					function(result) {
						$.device.pdfPrint("C:/pdfLicense2.pdf");
					},
					function(webexception) {
						alert("下载文档失败");
					}
				);
			}
			if($scope.elicenseData1[i].certName == "中华人民共和国结婚证") {
				$scope.isLoding = false;
				$scope.addmassage = "正在打印，请稍等...";
				$.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		urlHost + 
					$scope.configUrl + $scope.elicenseData1[i].derivePictureUrlForBytes,
					"C:\\pdfLicense3.pdf",
					//将选中图片下载
					function(bytesCopied, totalBytes) {
						console.log(bytesCopied + "," + totalBytes);
					},
					function(result) {
						$.device.pdfPrint("C:/pdfLicense3.pdf");
					},
					function(webexception) {
						alert("下载文档失败");
					}
				);
			}
		}
		$timeout(function() {
			$scope.isLoding = true;
			$location.path('/start');
		}, 60000);

	};
	$scope.prevStep = function() {
		$location.path('/information');
	}
});

app.controller("infoController", function($state, $scope, $http, $location, data, $rootScope, $timeout, appFactory) {
	$.device.idCardClose();
	$.device.Camera_Hide();
	data.idcardAddress = "";
	data.message = [];
	data.type2 = "";
	$scope.yynext = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.profileShow = function() {
		$scope.isLoding = false;
		$.ajax({
			url: "http://31.1.137.251:8080/ac-product/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do",
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				identNo: data.idCardNum,
				catMainCode: "310105105000100",
				machineId: $.config.get('uniqueId'),
				itemName: "",
				itemCode: "",
				businessCode: "",
				name: data.idCardName,
				startDay: data.VALIDSTARTDAY,
				endDay: data.VALIDENDDAY,
			},
			success: function(dataJson) {
				console.log(dataJson);
				data.idcardAddress = dataJson.SADDR;
				data.street = dataJson.MAKEDEPART;
				console.log(data.idcardAddress); //上海市闵行区龙茗路1458弄55号1602室  && data.idcardAddress.indexOf("宜川") != -1
				if(data.idcardAddress.indexOf("普陀区") != -1) {
					$scope.postOne();
				} else {
					$scope.isLoding = true;
					$scope.isAlert = true;
					$scope.msg = "仅查询本街道享受社会救助人员！";
					$timeout(function() {
						$location.path('/start');
					}, 10000);
				}
			},
			error: function(err) {
				$scope.isLoding = true;
				console.log(err)
				$scope.isAlert = true;
				$scope.msg = "请求出错，请联系工作人员！";
				$timeout(function() {
					$location.path('/start');
				}, 10000);
			}
		});
	};
	$scope.profileShow();

	$scope.postOne = function() {
		$.ajax({
			type: "post",
			url: "http://31.1.137.251:8088/cim_pt/workBench.submitInfoCheck.do",
			dataType: "json",
			data: {
				"userName": data.idCardName,
				"identNo": data.idCardNum,
				"address": data.idcardAddress,
				"type": "1"
			},
			success: function(dataJson) {
				var type1 = dataJson.type;
				data.postId = dataJson.data.id;
				if(data.postId != "" && type1 == "1") {
					//$scope.time();
					time();
				}
			},
			error: function(err) {
				console.log(err)
				$scope.isLoding = true;
				$scope.isAlert = true;
				$scope.msg = "出现错误 请联系工作人员！";
				$timeout(function() {
					$location.path('/start');
				}, 10000);
			}
		});
	};
	//var t;
	//var time = 60;
	function time() {
		var time = 60;
		t = setInterval(function() {
			if(time == 0) {
				$scope.isLoding = true;
				$scope.isAlert = true;
				$scope.msg = "请求超时 ，请联系工作人员！";
				clearInterval(t);
				$.device.GoHome();
			}
			$scope.getOne();
			time--;
		}, 5000)
	}

	$scope.getOne = function() {
		$.ajax({
			type: "get",
			url: "http://31.1.137.251:8088/cim_pt/workBench.getInfoResultById.do",
			dataType: "json",
			data: {
				"id": data.postId,
				"t_": new Date().getTime()
			},
			success: function(dataJson) {
				console.log(dataJson);
				//alert(dataJson.msg);
				console.log(dataJson.msg);
				data.type2 = dataJson.type;
				$scope.getName = dataJson.data.name;
				$scope.indetNo = dataJson.data.indetNo;
				$scope.item = dataJson.data.item;
				$scope.address = dataJson.data.address;
				$scope.assistance = dataJson.data.assistance;
				$scope.status = dataJson.data.status;
				if(dataJson.msg == "审核不通过") {
					clearInterval(t);
					$scope.isLoding = true;
					$scope.isAlert = true;
					$scope.msg = "审核不通过，请区号到窗口办理！";
					clearInterval(t);
					$timeout(function() {
						$location.path('/start');
					}, 10000);
				}
				if(dataJson.type == "1" && dataJson.data.status == "y") {
					$scope.isLoding = true;
					data.message = {
						"name": dataJson.data.name,
						"identNo": dataJson.data.indetNo,
						"item": dataJson.data.item,
						"address": dataJson.data.address,
						"assistance": dataJson.data.assistance, //救助编号
						"status": dataJson.data.status //是否通过  y：通过； n：不通过
					}
					console.log(data.message);
					if(data.message.status == "y") {
						$scope.isLoding = true;
						$scope.yynext = true;
						$scope.isAlert = true;
						$scope.msg = "正在打印社会救助证明,请勿离开！";
						clearInterval(t);
						$scope.print();
						$timeout(function() {
							$location.path('/start');
						}, 13000);
					}
					clearInterval(t);
				}
			},
			error: function(err) {
				console.log(err)
				$scope.isLoding = true;
				$scope.isAlert = true;
				$scope.msg = "出现错误 请联系工作人员！";
				$timeout(function() {
					$location.path('/start');
				}, 10000);
			}
		});

	}

	$scope.print = function() {
		var date = new Date();
		var month = date.getMonth() + 1;
		var lodop = $.device.printGetLodop();
		lodop.ADD_PRINT_TEXT(120, 270, 350, 50, "    享受社会救助证明");
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		lodop.SET_PRINT_STYLEA(0, "Bold", 1);
		lodop.SET_PRINT_STYLEA(0, "Horient", 2);
		lodop.ADD_PRINT_TEXT(187, 110, 648, 30, "  兹有我地区:" + $scope.address + "," + $scope.getName + "家庭");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.SET_PRINT_STYLEA(0, "Horient", 2);
		lodop.ADD_PRINT_TEXT(228, 50, 691, 30, "（人员）,享受本市城镇最低生活保障。");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.ADD_PRINT_TEXT(267, 77, 476, 30, "救助编号为：" + data.message.assistance);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.ADD_PRINT_TEXT(306, 77, 473, 30, "本证有效期为30天。");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.ADD_PRINT_TEXT(346, 77, 473, 30, "特此证明！");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.ADD_PRINT_IMAGE(450, 480, 175, 180, "<img border='0' src='data:image/png;base64,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'>");
		lodop.SET_PRINT_STYLEA(0, "Stretch", 1); //按原图比例(不变形)缩放模式
		lodop.ADD_PRINT_TEXT(510, 405, 277, 30, "宜川路街道社会救助事务管理所");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.ADD_PRINT_TEXT(550, 520, 277, 30, date.getFullYear() + "年" + month + "月" + date.getDate() + "日");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "宋体");
		lodop.PRINT();
	};

	$scope.prevStep = function() {
		$location.path('/select');
	}
});