function removeAnimate(ele) {
	//	$(ele).css({
	//		"transform": "translateY(0px)",
	//		"top": 0
	//	}).removeClass('transformto')
}

function addAnimate(ele) {
	$(ele).css({
		'margin-top': '300px',
		'opacity': '0'
	});
	$(ele).animate({
		marginTop: '0',
		opacity: '1'
	}, 1000);
}
app.controller('loginType', function($state, $scope, appData) {
	//显示社保卡登录选项
	$scope.ShowSscard = jQuery.getConfigMsg.isShowSscard;
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	appData.funName = $(".headName").text();
	$scope.operation = "请选择登录方式";
	appData.SwipeType = 'sbCard';
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller('login', function($scope, $http, $state, appData, $timeout) {
	$scope.loginType = appData.loginType;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$.device.GoHome();
	}
	switch ($scope.loginType) {
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
		case "sscard":
			$scope.operation = "社保卡登录";
			break;
	}

	$scope.nextStep = function() {
		$scope.tokenType = "token";
		$scope.token = function() {
			$state.go("chooseDate");
		}
	};

	// $scope.idcardLogin = function(info, images) {
	// 	appData.licenseNumber = '310109197603180424';
	// 	appData.licenseName = '孙燕雯';
	// 	$state.go("chooseDate");
	// }
	// $scope.idcardLogin();

	$scope.idcardLogin = function(info, images) {
		if (info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
			appData.VALIDENDDAY = info.ValidtermOfEnd;
			appData.VALIDSTARTDAY = info.ValidtermOfStart;
			$scope.nextStep();
		} else {
			layer.msg("没有获取到")
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		$state.go("chooseDate");
	}
	$scope.citizenLogin = function(info) {
		function ClearBr(key) {
			key = key.replace(/\+/g, "-");
			key = key.replace(/\#/g, ",");
			return key;
		}
		if (appData.qrCodeType == "suishenma") {
			appData.licenseName = info.zwdtsw_name;
			appData.licenseNumber = info.zwdtsw_cert_id;
			appData.zwdtsw_user_id = info.zwdtsw_user_id;
			appData.zwdtsw_link_phone = info.zwdtsw_link_phone;
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

	$scope.prevStep = function() {
		$state.go("loginType");
	}
});
app.controller('chooseDate', function($state, $scope, appData, $rootScope) {
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$(".form_datetime").datetimepicker({
		format: "yyyy-mm-dd", //显示日期格式
		autoclose: true,
		todayBtn: true,
		minView: "month", //只选择到天自动关闭
		language: 'zh-CN',
		endDate: new Date()
	});

	$scope.nextStep = function() {
		var condFlag = false;
		appData.fkssqq = $('#dtStartDate').val();
		appData.fkssqz = $('#dtEndDate').val();
		do {
			if (appData.fkssqq == '' || appData.fkssqq == undefined || appData.fkssqq == null) {
				$scope.isAlert = true;
				$scope.msg = "请选择税款所属期起！";
				return;
			}
			if (appData.fkssqz == '' || appData.fkssqz == undefined || appData.fkssqz == null) {
				$scope.isAlert = true;
				$scope.msg = "请选择税款所属期止！";
				return;
			}
			if ($("#dtEndDate").val() < $("#dtStartDate").val()) {
				$scope.isAlert = true;
				$scope.msg = "税款所属期起不能早于税款所属期止！";
				return;
			}
		} while (condFlag);
		$state.go('preview');
	}
});
app.controller('preview', function($state, $scope, appData, $rootScope) {
	removeAnimate($('.scrollBox2'));
	addAnimate($('.scrollBox2'));
	// $.getConfigMsg.preUrlSelf = 'http://10.2.14.143:8080/ac-self'
	$scope.nextText = "打印";
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.previewImg = "";
	$scope.printImg = "";
	$scope.printImgBase64 = "";
	$scope.isLoding = false;
	$scope.isShowView = false; // 是否显示预览框
	$scope.zoomCount = 1; // 图片放大计数
	$scope.rotateCount = 0; // 图片旋转计数
	$scope.currentPage = 1; //当前页
	$scope.totalPages = 1; //总页数
	$scope.previewImgList = []; //预览图片
	$scope.emptyPreviewImgList = []; //存在空值的数组
	$scope.totalList = [];
	$scope.List = [];
	$scope.base = "data:image/png;base64,"
	$scope.isAllScreen = false;
	$scope.isPrint = false;
	$scope.view = true;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.nextPage = function() {
		if ($scope.currentPage < $scope.totalPages) {
			++$scope.currentPage;
			$scope.currentList();
		}
	};
	$scope.prevPage = function() {
		if ($scope.currentPage > 1) {
			--$scope.currentPage;
			$scope.currentList();
		}
	};
	$scope.currentList = function(current) {
		if (current === undefined) {
			current = $scope.currentPage;
		}
		$scope.currentImgIndex = null;
		$scope.startPos = (current - 1) * 4; //0 -3  3 - 6
		$scope.endPos = $scope.startPos + 4;
		$scope.emptyPreviewImgList = $scope.totalList.slice($scope.startPos, $scope.endPos);
		$scope.totalPages = Math.ceil($scope.totalList.length / 4);
		$scope.emptyPreviewImgList.length = 4;
		console.log($scope.emptyPreviewImgList);
		for (var i in $scope.emptyPreviewImgList) {
			if ($scope.emptyPreviewImgList[i] != undefined) {
				$scope.previewImgList.push($scope.emptyPreviewImgList[i]);
			}
			$("#jq22").append('<img data-original="' + $scope.base + $scope.emptyPreviewImgList[i].png +
				'" src="' + $scope.base + $scope.emptyPreviewImgList[i].png + '" alt="">');
		}
		//图片显示
		var viewer = new Viewer(document.getElementById('jq22'), {
			url: 'data-original',
			//						toolbar:false,
			//						button:false
		});
	}
	//获取证明pdfbase64
	$scope.proofPrintBase = function() {
		if (jQuery.getConfigMsg.isPrintBase64 == 'N') {
			//万达文件流
			$scope.printImg = $.getConfigMsg.preUrlSelf + "/selfapi/taxBureau/proofPrint.do" +
				"?fileName=" + $scope.fileName + "&type=byte"
			console.log($scope.printImg);
		} else {
			$.customAjax.get($.getConfigMsg.preUrlSelf + '/selfapi/taxBureau/proofPrint.do', {
				fileName: $scope.fileName,
				type: 'base64'
			}, function(res) {
				$scope.isLoding = true;
				console.log(res);
				appData.printpdfBase64 = res.data.jfzm;
			}, function(err) {});
		}
	}

	//获取证明图片
	$scope.proofPrint = function() {
		$.customAjax.get($.getConfigMsg.preUrlSelf + '/selfapi/taxBureau/proofPriview.do', {
			name: encodeURI(appData.licenseName),
			idCard: appData.licenseNumber,
			xz: '01',
			fkssqq: appData.fkssqq,
			fkssqz: appData.fkssqz,
			// type:'byte'
		}, function(res) {
			$scope.isLoding = true;
			$scope.List = res.data.pngList;
			$scope.fileName = res.data.fileName;
			$scope.totalList = $scope.List.slice(0, $scope.List.length);
			$scope.currentList();
			$scope.proofPrintBase();
			console.log($scope.fileName);
		}, function(err) {});
	}
	$scope.proofPrint();
	$scope.allScreen = function() {
		$scope.isShowView = true;
	}
	$scope.print = function() {
		$scope.isAlert = true;
		$scope.msg = "正在打印中...";
		$scope.alertConfirm = function() {
			$scope.isAlert = false;
			$state.go("loginType");
		}
		$scope.timestamp = Date.parse(new Date());
		$scope.path = "D:\\pdfPrint.pdf";
		$.device.urlPdfPrint($scope.printImg, $scope.path, function() {}, appData.printpdfBase64);
		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: $scope.funName,
				Number: "",
			}
		}
		recordUsingHistory('税务服务', '查询', appData.funName, appData.licenseName, appData.licenseNumber, '', '', JSON.stringify($scope.jsonStr));
		$timeout(function() {
				$state.go("loginType");
		}, 3000);
	}
});
