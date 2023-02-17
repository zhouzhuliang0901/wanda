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

app.controller("list", function($scope, $state, appData) {
	$scope.operation = "请选择办事事项";
	$scope.stuffName1 = perjsonStr1;
	$scope.stuffName2 = perjsonStr2;
	$scope.stuffName3 = perjsonStr3;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.getMatterCon = function(itemName, code, type) {
		appData.itemName = itemName;
		appData.code = code;
		appData.type = type;
		if(code == "") {
			$scope.isAlert = true;
			$scope.msg = "暂未开放";
		} else if(code == "RS_ssCardInfo") {
			window.location.href = "../RS_ssCardInfo/index.html";
		} else if(code == "RS") {
			window.location.href = type;
		} else if(code == "flexibleEmployment") {
			$state.go("flexibleEmployment");
		} else {
			if(type == "jfqk" || type == "cxjbjfqk") {
				$scope.isAlert = true;
				$scope.msg = "业务调整，暂停服务";
			} else {
				$state.go("loginType");
			}
		}
	};
	//	$scope.isScroll = function() {
	//		new iScroll("wrapper", {
	//			vScrollbar: false,
	//			hScrollbar: false,
	//			hideScrollbar: true,
	//			hScroll: false,
	//			preventDefault: false,
	//			checkDOMChanges: true,
	//		});
	//	};
	//	$scope.isScroll();
});
app.controller("flexibleEmployment", function($scope, $state, appData) {
	$scope.operation = "请选择办事事项";
	$scope.stuffName = flexibleEmployment;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		$state.go("list");
	}
	$scope.getMatterCon = function(type, name) {
		trackEvent(name);
		window.location.href = type;
	};
});
app.controller('loginType', function($state, $scope, appData, $http, $location) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	if(appData.itemName == null || appData.itemName == undefined || appData.itemName == "") {
		appData.itemName = $location.search().itemName;
	}
	if(appData.code == null || appData.code == undefined || appData.code == "") {
		appData.code = $location.search().code;
	}
	if(appData.type == null || appData.type == undefined || appData.type == "") {
		appData.type = $location.search().type;
	}
	$scope.isAlert = false;
	$scope.concel = "false";
	if(appData.type == "jfqk" || appData.type == "cxjbjfqk") {
		$scope.isAlert = true;
		$scope.msg = "业务调整，暂停服务";
		$scope.alertConfirm = function(){
			window.history.go(-1)
		}
	}

	trackEvent(appData.itemName);
	$scope.operation = "请选择登录方式";
	$scope.person = appData.person;
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		appData.SwipeType = "sbCard";
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("list");
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory, $timeout) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.isLoding = true;
	appData.sign = "token";
	$scope.alertConfirm = function() {
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
		case "sscard":
			$scope.operation = "社保卡登录";
			break;
	}

	//跳转页面
	$scope.nextStep = function() {
		$scope.tokenType = "token";
		$scope.token = function() {
			if(appData.type == "residencePermit") {
				$state.go("residenceInfo");
			} else if(appData.type == "ywshbjqk") {
				$state.go("input");
			} else {
				$state.go("social");
			}
			$scope.tokenType = "";
		}
	}

	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
		} else {
			layer.msg("没有获取到")
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		appData.img = img;
		if(appData.type == "residencePermit") {
			$state.go("residenceInfo");
		} else if(appData.type == "ywshbjqk") {
			$state.go("input");
		} else {
			$state.go("social");
		}

	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}
	$scope.citizenLogin = function(info) {
		if(appData.qrCodeType == "suishenma") {
			appData.licenseName = info.zwdtsw_name;
			appData.licenseNumber = info.zwdtsw_cert_id;
			$scope.nextStep();
		} else {
			var idcardInfo = info.result.data;
			appData.licenseName = idcardInfo.realname;
			appData.licenseNumber = idcardInfo.idcard;
			$scope.nextStep();
		}
	}
	$scope.sscardLogin = function(info) {
		if(info) {
			if(info.Ssn != "" && info.Ssn != null && info.Ssn != undefined) {
				appData.licenseNumber = info.Ssn;
				appData.licenseName = info.PeopleName;
				$.device.ssCardClose();
				$scope.nextStep();
			} else {
				$scope.isAlert = true;
				$scope.msg = "未读取到您的社保卡信息,请重试";
			}

		} else {
			layer.msg("没有获取到")
		}
	}
})
app.controller("input", function($scope, $http, $state, appData, appFactory, $timeout) {
	removeAnimate($('.input'))
	addAnimate($('.input'))
	//	$scope.Type = appData.itemName == "参加个人城镇基本养老保险缴费情况" ? "jfqk" : "ydzy";
	//	appData.itemCode = appData.itemName == "参加个人城镇基本养老保险缴费情况" ? "312000105000" : "312000104000";
	$scope.isLoding = true;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.parentList = parentList;
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
		$scope.childList = $scope.search(childList, id);
	};
	$scope.change2 = function(name, index, guideline) {
		$scope.current2 = null;
		$scope.show2 = true;
		$scope.current2 = index;
	}
	$scope.nextStep = function() {
		if($(".singselect .in li").attr("value") === 0) {
			appData.reportId = $(".singselect2 .in li").attr("value");
		} else if($(".singselect .in li").attr("value") != 0) {
			appData.reportId = $(".singselect .in li").attr("value");
		}
		$state.go("social");
		console.log(appData.reportId);
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

app.controller("social", function($scope, $http, $state, appData, appFactory, $timeout) {
	$scope.operation = appData.itemName;
	$scope.isAllScreen = false;
	var date = new Date()
	var tYear = date.getFullYear();
	var tMonth = date.getMonth() + 1;
	$scope.isLoding = false;
	$scope.nextText = "打印";
	$scope.addmassage = "正在查询请稍后...";
	$scope.concel = "false";
	$scope.isShowView = false; // 是否显示预览框
	$scope.zoomCount = 1; // 图片放大计数
	$scope.rotateCount = 0; // 图片旋转计数
	$scope.isImg = false;
	if(appData.token == null || appData.token == "" || appData.token == undefined) {
		$scope.isAlert = true;
		$scope.msg = "请求异常，请稍候再试";
	}
	//	if(appData.type == "yljqk") {
	//		appData.licenseNumber = "310110194706113242";
	//	} else
	if(appData.type == "qyd" || appData.type == "cxjbqyd") {
		if(tMonth > 7) {
			appData.date = tYear - 1;
		} else {
			appData.date = tYear - 2;
		}
		//appData.licenseNumber = "370832198701134912";
	} else if(appData.type == "dzdzd") {
		appData.date = tYear;
		//appData.licenseNumber = "310101196306072018";
		//	} else if(appData.type == "cxjbjfqk") {
		//		appData.licenseNumber = "310226198109195512";
		//	} else if(appData.type == "xsyljqk") {
		//		appData.licenseNumber = "310223195110193044";
		//	} else if(appData.type == "cxjbqyd") {
		//		appData.licenseNumber = "310226196412081113";
		//	} else if(appData.type == "cxjbqyd") {
		//		appData.licenseNumber = "310226196412081113";
	} else {
		appData.date = "";
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go("list");
	}
	let i = 1;
	$scope.configUrl = $.getConfigMsg.preUrlSelf;
	$scope.humanSocietyPrint = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + '/aci/workPlatform/humanSociety/humanSocietyPrint.do',
			/*url写异域的请求地址*/
			dataType: "jsonp",
			/*加上datatype*/
			jsonp: "jsonpCallback",
			async: false,
			/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
			data: {
				businessCode: appData.tybm,
				itemCode: appData.code,
				machineId: $.config.get("uniqueId") || "",
				itemName: appData.itemName,
			},
			success: function(dataJson) {
				console.log(dataJson);
				if(dataJson.SUCCESS == "TRUE") {
					$scope.isLoding = true;
					$scope.isImg = true;
					$scope.messageAll = dataJson.MSG;
					$scope.printPicture = dataJson.PNGURL;
					$scope.previewImg = $scope.configUrl + $scope.printPicture;
					$scope.pdfPrint = $scope.configUrl + dataJson.PDFURL;
				} else if(dataJson.SUCCESS == "FALSE") {
					$timeout(function() {
						if(i == 3) {
							$scope.isLoding = true;
							$scope.isAlert = true;
							$scope.msg = "接口异常,请稍候再试！";
							$scope.alertConfirm = function() {
								$scope.isAlert = false;
								$state.go("list");
							}
						} else {
							i++;
							$scope.humanSocietyPrint();
						}
					}, 2000)
				}
			},
			error: function(err) {
				console.log("humanSocietyPrint error");
				$scope.isLoding = true;
				$scope.isAlert = true;
				$scope.msg = "请求异常，请返回重新操作！";
			}
		});
	}
	$scope.humanSocietyQuery = function() {
		$scope.isLoding = false;
		$.ajax({
			type: "get", //
			url: $.getConfigMsg.preUrl + '/aci/workPlatform/humanSociety/humanSocietyQuery.do',
			/*url写异域的请求地址*/
			dataType: "jsonp",
			/*加上datatype*/
			jsonp: "jsonpCallback",
			/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
			data: {
				type: appData.type,
				zjhm: appData.licenseNumber,
				accessToken: appData.token,
				xm: appData.licenseName,
				//				mobile: "13544444444", //$('#mobile').val(),
				itemCode: appData.code,
				reportId: appData.reportId,
				dyrq: appData.date,
				machineId: $.config.get("uniqueId") || "",
				itemName: appData.itemName,
			},
			success: function(dataJson) {
				console.log(dataJson);
				if(dataJson.SUCCESS == "TRUE") {
					$scope.isLoding = true;
					$scope.isImg = true;
					$scope.messageAll = dataJson.MSG;
					$scope.printPicture = dataJson.PNGURL;
					$scope.previewImg = $scope.configUrl + $scope.printPicture;
					$scope.pdfPrint = $scope.configUrl + dataJson.PDFURL;
				} else if(dataJson.SUCCESS == "FALSE" && dataJson.CODE == "1") {
					$scope.isLoding = true;
					$scope.isAlert = true;
					$scope.msg = "未查询到证照!";
				} else {
					$scope.isLoding = true;
					$scope.isAlert = true;
					$scope.msg = "接口异常，请返回重新操作!";
				}
			},
			error: function(err) {
				console.log("humanSocietyQuery error");
				$scope.isAlert = true;
				$scope.msg = "请求异常，请返回重新操作！";
			}

		});
	}
	$scope.humanSocietyQuery();

	$scope.allScreen = function() {
		$scope.isShowView = true;
	}
	$scope.print = function() {
		$scope.addmassage = "正在打印...";
		$scope.isShowPrint = "show";
		$scope.timestamp = Date.parse(new Date());
		$scope.path = "D:\\pdfPrint.pdf";
		$scope.filePath = "D:/pdfPrint.pdf";
		$.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		urlHost +
			$scope.pdfPrint,
			$scope.path,
			//将选中图片下载
			function(bytesCopied, totalBytes) {
				console.log(bytesCopied + "," + totalBytes);
			},
			function(result) {
				$.device.pdfPrint($scope.filePath);
				saveDeviceStatus("A4Printer", 0, "正常", 0, 0, 0, 1);
				//模块使用记录
				$scope.jsonStr = {
					SUCCESS: "true",
					data: {
						name: appData.itemName,
						Number: appData.tybm,
					}
				}
				recordUsingHistory('人社服务', '查询+打印', appData.itemName, appData.licenseName, appData.licenseNumber, '', appData.tybm, JSON.stringify($scope.jsonStr));
				//行为分析(查询)
				trackEventForQuery(appData.itemName, appData.tybm, "打印", "上海市人力资源社会保障局", appData.licenseName, appData.licenseNumber, "");
			},
			function(webexception) {
				alert("下载文档失败");
			}
		);
		$timeout(function() {
			$scope.isLoding = true;
			$scope.printok = false;
			$state.go("list");
		}, 5000);
	}
	$scope.back = function() {
		$state.go("loginType");
	};
	$scope.nextStep = function() {
		$state.go('list');
	}

	//图片预览
	$scope.isAllScreen = false;
	$scope.isPrint = false;
	$scope.view = true;
	//图片显示
	var viewer = new Viewer(document.getElementById('jq22'), {
		url: 'data-original',
		toolbar: false,
		//		button: false
	});
	$scope.show = function() {
		viewer.show();
		$scope.view = false;
	}
	$scope.hide = function() {
		viewer.hide();
		$scope.view = true;
	}
	$scope.close = function() {
		$scope.isAllScreen = false;
	};
	//	$scope.viewClose = function() {
	//		$scope.isShowView = false;
	//		$scope.zoomCount = 1;
	//		$scope.rotateCount = 0;
	//	};
	//
	//	$scope.zoomIn = function() {
	//		$scope.zoomCount += 0.5;
	//		if($scope.zoomCount > 5) {
	//			$scope.zoomCount = 5;
	//		}
	//		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	//	};
	//
	//	$scope.zoomOut = function() {
	//		$scope.zoomCount -= 0.5;
	//		if($scope.zoomCount < 0.5) {
	//			$scope.zoomCount = 0.5;
	//		}
	//		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	//	};
	//
	//	$scope.rotateLeft = function() {
	//		$scope.rotateCount -= 90;
	//		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	//	};
	//
	//	$scope.rotateRight = function() {
	//		$scope.rotateCount += 90;
	//		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	//	};
});
app.controller("iframe", function($scope, $state, appData, $sce, $timeout, $location) {
	appData.funName = "居住证积分查询";
	if(appData.licenseNumber == "" || appData.licenseNumber == undefined) {
		appData.licenseNumber = $location.search().Number;
	}
	if(appData.licenseName == "" || appData.licenseName == undefined) {
		appData.licenseName = $location.search().Name;
	}
	$timeout(function() {
		$state.go("residenceInfo");
	}, 200);
});
//居住证积分查询
app.controller("residenceInfo", function($scope, $state, appData, $sce) {
	$scope.funName = appData.itemName || appData.funName;
	$scope.isLoding = false;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.nextText = "返回首页";
	$scope.licenseNumber = appData.licenseNumber;
	$scope.licenseName = appData.licenseName;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go("list");
	}
	$scope.queryResidenceLicenseScore = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/residenceLicense/queryResidenceLicenseScore.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				idCard: appData.licenseNumber
			},
			success: function(dataJson) {
				//模块使用记录
				$scope.jsonStr = {
					SUCCESS: "true",
					data: {
						name: $scope.funName,
					}
				}
				recordUsingHistory('人社服务', '查询', $scope.funName, appData.licenseName, appData.licenseNumber, '', "", JSON.stringify($scope.jsonStr));
				//行为分析(查询)
				trackEventForQuery($scope.funName, "", "查询", "上海市人力资源社会保障局", appData.licenseName, appData.licenseNumber, "");
				$scope.isLoding = true;
				if(dataJson.msg == "ok") {
					$scope.info = dataJson;
					if(dataJson.baseScore && dataJson.baseScore == "ok") {
						$scope.baseScore = "已达标";
					} else {
						$scope.baseScore = "未达标";
					}
				} else {
					$scope.isAlert = true;
					$scope.msg = "未查询到您的居住证积分信息";
				}
			},
			error: function(err) {
				console.log("queryResidenceLicenseScore err");
			}
		});
	}
	$scope.queryResidenceLicenseScore();
	$scope.prevStep = function() {
		$state.go("list");
	}
	$scope.nextStep = function() {
		$state.go("list");
	}
});