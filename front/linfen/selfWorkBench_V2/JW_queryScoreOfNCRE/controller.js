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
app.controller('loginType', function($state, $scope, appData) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	appData.funName = "上海市计算机等级考试成绩查询";
	$scope.operation = "请选择登录方式";
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		window.location.href = "../JW_allItem/index.html";
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory, $timeout) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.isLoding = true;
	$scope.alertConfirm = function() {
		$state.go("loginType");
	}
	switch($scope.loginType) {
		case "idcard":
			$scope.operation = "身份证登录";
			break;
		case "cloud":
			$scope.operation = "随申办登录";
			break;
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
		appData.img = img;
		$state.go("choose");

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
			$state.go("choose");
		} else {
			var idcardInfo = info.result.data;
			appData.licenseName = idcardInfo.realname;
			appData.licenseNumber = idcardInfo.idcard;
			appData.VALIDENDDAY = idcardInfo.VALIDENDDAY;
			appData.VALIDSTARTDAY = idcardInfo.VALIDSTARTDAY;
			$state.go("choose");
		}
	}
})
app.controller('choose', function($scope, $state, appData, $sce, $http) {
	$scope.operation = "请选择考试年份";
	$scope.change = function(index, year) {
		$scope.current = index;
		appData.year = year;
	}
	//2、查询考试年月
	$http.get($.getConfigMsg.preUrlSelf + "/selfapi/examinationResult/queryExaminationYear.do", {
		params: {
			code: "03"
		}
	}).success(function(dataJson) {
		$scope.yearList = dataJson.data;
		console.log(dataJson);
	}).error(function(err) {
		console.log(err);
	});
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		$state.go('loginType');
	}
	$scope.nextStep = function() {
		$state.go('preview');
	}
});
app.controller("preview", function($scope, $http, $state, appData, appFactory, $timeout) {
	$scope.operation = appData.funName;
	$scope.isAllScreen = false;
	let date = new Date()
	let tYear = date.getFullYear();
	let tMonth = date.getMonth();
	let i = 1; //请求计数;
	$scope.isLoading = true;
	$scope.nextText = "打印";
	$scope.addmassage = "正在查询请稍后...";
	$scope.concel = "false";
	$scope.isShowView = false; // 是否显示预览框
	$scope.zoomCount = 1; // 图片放大计数
	$scope.rotateCount = 0; // 图片旋转计数
	$scope.isImg = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go("loginType");
	}
	console.log(appData.licenseName + "--" + appData.year);
	$scope.pinyinName = (pinyin.getFullChars(appData.licenseName)).toLowerCase();

	//5. 查询证照
	$scope.queryLicense = function() {
		$http.get($.getConfigMsg.preUrlSelf + "/selfapi/dzzz/queryCertBaseData.do", {
			params: {
				certNo: appData.encrypt_identity || appData.licenseNumber,
				type: "0",
				catMainCode: "310198756809500",
				machineId: $.config.get('uniqueId') || "",
				itemName: encodeURI("上海市计算机等级考试成绩查询"),
				itemCode: "",
				businessCode: "",
				name: encodeURI(appData.licenseName),
				startDay: appData.VALIDSTARTDAY,
				endDay: appData.VALIDENDDAY,
			}
		}).success(function(dataJson) {
			console.log(dataJson);
			if(dataJson.length > 0) {
				$scope.isLoading = false;
				$scope.elicenseData = dataJson[0];
				$scope.previewImg = $.getConfigMsg.preUrlSelf + $scope.elicenseData.pictureUrlForBytes;
				$scope.pdfPrint = $.getConfigMsg.preUrlSelf + $scope.elicenseData.derivePictureUrlForBytes;
			} else {
				$timeout(function() {
					if(i == 3) {
						$scope.isLoading = false;
						$scope.isAlert = true;
						$scope.msg = "接口异常,请稍候再试！";
					} else {
						i++;
						$scope.queryLicense();
					}
				}, 2000)
			}
		}).error(function(err) {
			$scope.isLoading = false;
			$scope.isAlert = true;
			$scope.msg = "接口异常,请稍候再试！";
		});
	}
	//4.生成证明
	$scope.creatLicense = function() {
		$http.get($.getConfigMsg.preUrlSelf + "/selfapi/examinationResult/queryAndCreatCertificate.do", {
			params: {
				year: appData.year,
				idCard: appData.licenseNumber,
				name: encodeURI(appData.licenseName),
				pinyin: $scope.pinyinName,
				type: "create",
				code: "03"
			}
		}).success(function(dataJson) {
			if(dataJson.errorcode == "0") {
				if(dataJson.data.state == "1") {
					$scope.queryLicense();
				} else {
					$scope.isAlert = true;
					$scope.msg = "没有查到您的证明数据";
				}
			} else {
				$scope.isAlert = true;
				$scope.msg = "生成证明异常,请稍后再试";
			}
		}).error(function(err) {
			$scope.isAlert = true;
			$scope.msg = "生成证明异常,请稍后再试";
		});
	}
	//3、查询成绩
	$http.get($.getConfigMsg.preUrlSelf + "/selfapi/examinationResult/queryAndCreatCertificate.do", {
		params: {
			year: appData.year,
			idCard: appData.licenseNumber,
			name: encodeURI(appData.licenseName),
			pinyin: $scope.pinyinName,
			type: "query",
			code: "03"
		}
	}).success(function(dataJson) {
		if(dataJson.errorcode == "0") {
			if(dataJson.data.state == "1") {
				$scope.creatLicense();
			} else {
				$scope.isAlert = true;
				$scope.msg = "没有查到您的成绩数据";
			}
		} else {
			$scope.isAlert = true;
			$scope.msg = "查询异常,请稍后再试";
		}
	}).error(function(err) {
		$scope.isAlert = true;
		$scope.msg = "查询异常,请稍后再试";
	});

	$scope.allScreen = function() {
		$scope.isShowView = true;
	}
	$scope.print = function() {
		$scope.addmassage = "正在打印...";
		$scope.isShowPrint = "show";
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
						name: appData.funName,
						Number: "",
					}
				}
				recordUsingHistory('教委服务', '查询+打印', appData.funName, appData.licenseName, appData.licenseNumber, '', "", JSON.stringify($scope.jsonStr));
				//行为分析(查询)
				trackEventForQuery(appData.funName, "", "打印", "上海市教育委员会", appData.licenseName, appData.licenseNumber, "");
			},
			function(webexception) {
				alert("下载文档失败");
			}
		);
		$timeout(function() {
			$scope.isLoding = true;
			$scope.printok = false;
			$state.go("loginType");
		}, 5000);
	}
	$scope.back = function() {
		$state.go("loginType");
	};

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