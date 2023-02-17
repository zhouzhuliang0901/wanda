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
	$scope.codeImg = "";
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller('login', function($scope, $http, $state, appData, $timeout) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	appData.funName = $(".headName").text();
	appData.SwipeType = 'sbCard';
	$scope.operation = "请使用'个人所得税'APP扫码授权";
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$.device.GoHome();
	}

	//2.	获取二维码授权状态
	$scope.getCodeStatus = function() {
		var num = 0;
		var t;

		function queryFrQrCode() {
			if (num < 10) {
				console.log("输出");
				t = setTimeout(function() {
					queryFrQrCode()
				}, 3000);
				num++;
			}
			$.customAjax.get($.getConfigMsg.preUrlSelf + '/selfapi/taxBureau/getAuthStatus.do', {
					wybs: $scope.wybs || '',
					timeout: new Date()
				},
				function(res) {
					console.log(res);
					if (res.code == '0' && res.data.status == '03') {
						appData.zrrdah = res.data.zrrdah;
						appData.xm = res.data.xm;
						appData.accessToken = res.data.accessToken;
						clearTimeout(t);
						$state.go("chooseDate");
					} else if (res.code == '0' && res.data.status == '02') {
						$scope.isLoading = true;
					}
				},
				function(err) {});
		}
		queryFrQrCode();
	}

	//1.	获取用户授权二维码
	$scope.createQrCode = function() {
		$.customAjax.get($.getConfigMsg.preUrlSelf + '/selfapi/taxBureau/createQrCode.do', {}, function(
			res) {
			console.log(res);
			$scope.codeImg = 'data:image/png;base64,' + res.data.ewm;
			$scope.wybs = res.data.wybs;
			$scope.getCodeStatus();
		}, function(err) {})
	}
	$scope.createQrCode();


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
		appData.skssqq = $('#dtStartDate').val();
		appData.skssqz = $('#dtEndDate').val();
		do {
			if (appData.skssqq == '' || appData.skssqq == undefined || appData.skssqq == null) {
				$scope.isAlert = true;
				$scope.msg = "请选择税款所属期起！";
				return;
			}
			if (appData.skssqz == '' || appData.skssqz == undefined || appData.skssqz == null) {
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
		//3.	获取自然人业务数据
		$.customAjax.get($.getConfigMsg.preUrlSelf + '/selfapi/taxBureau/applyForProof.do', {
			zrrdah: appData.zrrdah,
			skssqq: appData.skssqq,
			skssqz: appData.skssqz,
			slswjgDm: '',
			accessToken: appData.accessToken
		}, function(res) {
			console.log(res);
			if (res.code == '0') {
				appData.sqxh = res.data.sqxh;
				$state.go('preview');
			} else {
				$scope.isAlert = true;
				$scope.msg = "获取自然人业务数据失败！";
			}
		}, function(err) {
			$scope.isAlert = true;
			$scope.msg = "获取自然人业务数据接口异常！";
		})
	}
});
app.controller('preview', function($state, $scope, appData, $rootScope) {
	removeAnimate($('.scrollBox2'));
	addAnimate($('.scrollBox2'));
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
		$.customAjax.get($.getConfigMsg.preUrlSelf + '/selfapi/taxBureau/priview.do', {
			sqxh: appData.sqxh,
			accessToken: appData.accessToken,
			skssqq: appData.fkssqq,
			skssqz: appData.fkssqz,
		}, function(res) {
			if (res.code == '0') {
				$scope.isLoding = true;
				$scope.List = res.data.pngList;
				$scope.fileName = res.data.fileName;
				$scope.totalList = $scope.List.slice(0, $scope.List.length);
				$scope.currentList();
				$scope.proofPrintBase();
				console.log(res);
			} else {
				$scope.isAlert = true;
				$scope.msg = "获取预览图失败，请重试";
			}
		}, function(err) {
			$scope.isAlert = true;
			$scope.msg = "获取预览图接口异常，请重试";
		});
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
		recordUsingHistory('税务服务', '查询', appData.funName, '', '', '', '', JSON.stringify($scope.jsonStr));
		$timeout(function() {
			$state.go("loginType");
		}, 3000);
	}
});
