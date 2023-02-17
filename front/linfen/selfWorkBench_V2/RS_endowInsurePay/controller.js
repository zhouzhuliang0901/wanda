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
app.controller('main', function($state, $scope, appData, $http, $location) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))

	// 该模块为"参加个人城镇基本养老保险缴费情况"
	appData.type = 'jfqk';
	appData.code = '310195323433';
	appData.itemName = '参加个人城镇基本养老保险缴费情况';
    //显示社保卡登录选项
    $scope.ShowSscard=jQuery.getConfigMsg.isShowSscard;
	if(appData.itemName == null || appData.itemName == undefined || appData.itemName == "") {
		appData.itemName = $location.search().itemName;
	}
	if(appData.code == null || appData.code == undefined || appData.code == "") {
		appData.code = $location.search().code;
	}
	if(appData.type == null || appData.type == undefined || appData.type == "") {
		appData.type = $location.search().type;
	}
	$scope.operation = "请选择登录方式";
	$scope.person = appData.person;
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		appData.SwipeType = "sbCard";
		$state.go("login");
	}
	$scope.prevStep = function() {
		//$state.go("list");
		$.device.GoHome();
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
		$state.go("main");
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
			appData.licenseType = "rs";

			//			appData.licenseNumber = '310228198808070818';
			//			appData.licenseName = '陈雷';
			//			$scope.getTokenSNO(recognition_base64_photo, recognition_base64_photo);

		} else {
			layer.msg("没有获取到")
		}
	}

	//	$scope.idcardLogin();

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
		$state.go("main");
	}
	$scope.citizenLogin = function(info) {
		var idcardInfo = info.result.data;
		appData.licenseName = idcardInfo.realname;
		appData.licenseNumber = idcardInfo.idcard;
		$scope.nextStep();
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
app.controller("social", function($scope, $http, $state, appData, appFactory, $timeout) {
	$scope.operation = appData.itemName;
	$scope.isAllScreen = false;
	var date = new Date()
	var tYear = date.getFullYear();
	var tMonth = date.getMonth();
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
	if(appData.type == "qyd") {
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
		$state.go("main");
	}
	$scope.index = 3;
	$scope.falg = false;
	var i = 0;
	$scope.configUrl = $.getConfigMsg.preUrlSelf;
	$scope.humanSocietyPrint = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + '/selfapi/DZCert/getCertByApplyNo.do',
			/*url写异域的请求地址*/
			dataType: "json",
			/*加上datatype*/
			jsonp: "jsonpCallback",
			async: false,
			/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
			data: {
				businessCode: appData.tybm,
				itemCode: appData.code,
				machineId: jQuery.getConfigMsg.uniqueId || "",
				itemName: appData.itemName,
			},
			success: function(dataJson) {
				console.log(dataJson);
				dataJson = dataJson.data;
				if(dataJson.SUCCESS == "TRUE") {
					$scope.isLoding = true;
					$scope.isImg = true;
					$scope.messageAll = dataJson.MSG;
					$scope.printPicture = dataJson.PNGURL;
					$scope.previewImg = $scope.configUrl + $scope.printPicture;
					$scope.pdfPrint = $scope.configUrl + dataJson.PDFURL;
					//appData.printpdfBase64Url = dataJson.base64Url;
					if(dataJson.base64Url) {
						$http({
							url: $scope.configUrl + dataJson.base64Url,
							method: 'GET'
						}).success(function(data) {
							if(data.success == true) {
								appData.printpdfBase64 = data.data.str;
							} else {
								layer.msg("返回base64有误");
							}
						}).error(function(data) {
							layer.msg("返回base64接口异常");
						});
					}
				} else if(dataJson.SUCCESS == "FALSE") {
					$timeout(function() {
						if(i == 3) {
							$scope.isLoding = true;
							$scope.isAlert = true;
							$scope.msg = "接口异常,请稍候再试！";
							$scope.alertConfirm = function() {
								$scope.isAlert = false;
								$state.go("main");
							}
						} else {
							i++;
							$scope.humanSocietyPrint();
						}
					}, 2000)
				}
			},
			error: function(err) {
				$scope.isLoding = true;
				$scope.isAlert = true;
				$scope.msg = "请求异常，请返回重新操作！";
				console.log("humanSocietyPrint error");
			}
		});
		//		$http.jsonp($scope.configUrl + '/aci/workPlatform/humanSociety/humanSocietyPrint.do', {
		//			params: {
		//				jsonpCallback: "JSON_CALLBACK",
		//				businessCode: appData.tybm,
		//				itemCode: appData.code
		//			}
		//		}).success(function(dataJson) {
		//			$scope.isLoding = true;
		//			console.log(dataJson);
		//			if(dataJson.SUCCESS == "TRUE") {
		//				$scope.isImg = true;
		//				$scope.messageAll = dataJson.MSG;
		//				$scope.printPicture = dataJson.PNGURL;
		//				$scope.previewImg = $scope.configUrl + $scope.printPicture;
		//				$scope.pdfPrint = $scope.configUrl + dataJson.PDFURL;
		//			} else if(dataJson.SUCCESS == "FALSE") {
		//				$scope.isAlert = true;
		//				$scope.msg = "未获取到您的信息！";
		//			}
		//
		//		}).error(function(err) {
		//			$scope.isLoding = true;
		//			$scope.isAlert = true;
		//			$scope.msg = "请求异常，请返回重新操作！";
		//			console.log("humanSocietyPrint error");
		//		});
	}
	$scope.humanSocietyQuery = function() {
		$scope.isLoding = false;
		$.ajax({
			type: "get", //
			url: $.getConfigMsg.preUrlSelf + '/aci/workPlatform/humanSociety/humanSocietyQuery.do',
			/*url写异域的请求地址*/
			dataType: "json",
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
				machineId: jQuery.getConfigMsg.uniqueId || "",
				itemName: appData.itemName,
			},
			success: function(dataJson) {
				console.log(dataJson);
				if(dataJson.success == "1") {
					$scope.isLoding = true;
					$scope.isImg = true;
					//$scope.messageAll = dataJson.MSG;
					$scope.datass = {
						'certUuid': JSON.parse(dataJson.data).certUuid,
						'sessionId': '',
						'machineId': jQuery.getConfigMsg.uniqueId || "",
						'itemName': appData.itemName,
						'itemCode': appData.code,
						'businessCode':'',
					};
					//$scope.printPicture = dataJson.PNGURL;
					$scope.previewImg = $scope.configUrl + returnPDFAndPNGdataS("png", $scope.datass);
					$scope.pdfPrint = $scope.configUrl + returnPDFAndPNGdataS("pdfurl", $scope.datass);
					$scope.base64Urlname=returnPDFAndPNGdataS("base64url", $scope.datass);
					console.log($scope.pdfPrint);
					if(jQuery.getConfigMsg.isPrintBase64 == 'Y'&&$scope.base64Urlname) {
						$scope.isLoding = false;
						$http({
							url: $scope.configUrl+$scope.base64Urlname,
							method: 'GET'
						}).success(function(data) {
							$scope.isLoding = true;
							if(data.success == true) {
								appData.printpdfBase64 = data.data;
							} else {
								layer.msg("返回base64有误");
							}
						}).error(function(data) {
							$scope.isLoding = true;
							layer.msg("返回base64接口异常");
						});
					}
				} else if(dataJson.SUCCESS == "FALSE" && dataJson.CODE == "1") {
					$scope.isLoding = true;
					$scope.isAlert = true;
					$scope.msg = "未查询到证照!";
				} else if(dataJson.success == "0") {
					$scope.isLoding = true;
					$scope.isAlert = true;
					if(dataJson.errmsg.indexOf('[error]') != -1){
						$scope.msg = "接口异常，请返回重新操作!";
					}else{
						$scope.msg = dataJson.errmsg;
					}
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
	$scope.astrict = 0;
	$scope.print = function() {
		$scope.isLoding = false;
		$scope.addmassage = "正在打印...";
		//				var lodop = $.device.printGetLodop();
		//				lodop.ADD_PRINT_IMAGE(0, 0, 800, 1300, "<img border='0' src='" + $scope.previewImg + "'>");
		//				lodop.SET_PRINT_STYLEA(0, "Stretch", 2); //按原图比例(不变形)缩放模式
		//				//lodop.ADD_PRINT_TEXT(0, 0, 400, 500, "此证照由市电子证照库提供");
		//				lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		//				lodop.SET_PRINT_STYLEA(0, "FontColor", "#ccc");
		//				lodop.SET_PRINT_STYLEA(0, "ItemType", 1);
		//				lodop.SET_PRINT_STYLEA(0, "Angle", 50);
		//				lodop.SET_PRINT_STYLEA(0, "Repeat", true);
		//				lodop.PRINT();
		$scope.timestamp = Date.parse(new Date());
		$scope.path = "D:\\pdfPrint.pdf";
		$scope.filePath = "D:/pdfPrint.pdf";
		if($scope.astrict == 1) {
			return;
		} else {
			$scope.astrict = 1
		};
		$.device.urlPdfPrint($scope.pdfPrint, $scope.path, function() {}, appData.printpdfBase64);
		saveDeviceStatus("A4Printer", 0, "正常", 0, 0, 0, 1);
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
		//		$.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		urlHost +
		//			$scope.pdfPrint,
		//			$scope.path,
		//			//将选中图片下载
		//			function(bytesCopied, totalBytes) {
		//				console.log(bytesCopied + "," + totalBytes);
		//			},
		//			function(result) {
		//				//$.device.pdfPrint($scope.filePath);
		//				//saveDeviceStatus("A4Printer", 0, "正常", 0, 0, 0, 1);
		//				//模块使用记录
		//				$scope.jsonStr = {
		//					SUCCESS: "true",
		//					data: {
		//						name: appData.itemName,
		//						Number: appData.tybm,
		//					}
		//				}
		//				recordUsingHistory('人社服务', '查询+打印', appData.itemName, appData.licenseName, appData.licenseNumber, '', appData.tybm, JSON.stringify($scope.jsonStr));
		//			},
		//			function(webexception) {
		//				alert("下载文档失败");
		//			}
		//		);
		$timeout(function() {
			$scope.isLoding = true;
			$scope.printok = false;
			$state.go("main");
		}, 6000);
	}
	$scope.back = function() {
		$state.go("main");
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
});