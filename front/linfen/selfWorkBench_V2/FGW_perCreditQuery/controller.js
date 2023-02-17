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
	$scope.operation = "个人信用报告查询";
	$scope.stuffName = perjsonStr;
	$scope.choiceType = function(type, name, ywlx) {
		trackEvent('发改委', name);
		appData.funName = name;
		appData.type = type;
		appData.ywlx = ywlx;
		$state.go("guideline");
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
	//	$scope.isScroll();
});
app.controller('guideline', function($state, $scope, appData, $http) {
	removeAnimate($('.scrollBox2'))
	$scope.operation = "请选择登录方式";
	$scope.funName = "信用报告";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.guideline =
		"<p>受上海市公共信用信息服务平台委托,配合参与2015年上海市信用实事项目推出个人信用报告查询服务。为广大市民在线免费提供个人信用查询报告。市民通过身份验证即可在线免费获取包含登记类、资质类、监管类、判决类、执行类、违约类、公益类信息的信用查询报告。</p>";
	$scope.guideline1 = "<p>1、全市18岁以上常住人口（具有本市户籍或持有居住证）</p>";
	addAnimate($('.scrollBox2'))
	$scope.prevStep = function() {
		//		window.location.href = "../declare/index.html#main";
		$state.go('main');
	}
	$scope.nextStep = function() {
		$state.go("loginType");
	}
});
app.controller('loginType', function($state, $scope, appData, $http) {
	//removeAnimate($('.scrollBox2'))
	$scope.operation = "请选择登录方式";
	$scope.funName = "信用报告";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//addAnimate($('.scrollBox2'))
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go('login');
	}
	$scope.prevStep = function() {
		//		$state.go('main');
		$state.go("guideline");
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory, $timeout) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.isLoding = true;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$.state.go("main");
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
			$scope.operation = "随申办登录";
			break;
	}

	// $scope.idcardLogin = function() {
	// 	appData.archivesName = "邹天奇";
	// 	appData.archivesNumber = "430426199804106174";
	// 	$state.go("input");
	// }
	// $scope.idcardLogin();

	$scope.idcardLogin = function(info, images) {
		if (info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.archivesNumber = info.Number;
			appData.archivesName = info.Name;
			//			appData.archivesNumber = '310228198808070818';
			//			appData.archivesName = '陈雷';
			//			$state.go("creditReport");
		} else {
			layer.msg("没有获取到")
		}
	}
	//$scope.idcardLogin();
	$scope.getResult = function(img) {
		$scope.img = img;
		appData.img = img;
		$state.go("input");
	}

	$scope.prevStep = function() {
		$state.go("loginType");
	}
	$scope.citizenLogin = function(info) {
		var idcardInfo = info.result.data;
		appData.archivesName = idcardInfo.realname;
		appData.archivesNumber = idcardInfo.idcard;
		$state.go("input");
	}
})
app.controller('input', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.funName = appData.funName;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.query = function(licenseCode) {
		let condFlag = false;
		do {
			if (licenseCode == "" || licenseCode == null || licenseCode == undefined) {
				$scope.isAlert = true;
				$scope.msg = "请输入查询用途！";
				return;
			}
		} while (condFlag);
		$scope.isLoading = true;
		$.customAjax.post($.getConfigMsg.preUrlSelf +
			"/selfapi/creditReport/getQueyZrrrCxbhThirdParty.do", {
				name: encodeURI(appData.archivesName),
				idCard: appData.archivesNumber,
				cxyt: encodeURI(licenseCode)
			},
			function(res) {
				$scope.isLoading = false;
				if (res.code == 200) {
					res = JSON.parse(res.data);
					if (res.result == "200") {
						appData.cxbh = res.cxbh;
						$state.go('creditReport');
					} else {
						$scope.isAlert = true;
						$scope.msg = res.resultStr;
					}
				} else {
					$scope.isAlert = true;
					$scope.msg = '接口异常，请重试';
				}
			},
			function(err) {
				$scope.isAlert = true;
				$scope.msg = '接口异常，请重试';
			})
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}
});
app.controller("creditReport", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.stuffName = "个人信用报告";
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
	//浏览全文接口
	$scope.creditReport = function() {
		function ClearBr(key) {
			key = key.replace(/[\r\n]/g, "");
			return key;
		}
		$.customAjax.post($.getConfigMsg.preUrlSelf + "/selfapi/creditReport/getQueryXydaCxbhPdf.do", {
			cxbh: appData.cxbh,
			fileType: jQuery.getConfigMsg.isPrintBase64 == 'Y' ? 'String' : 'byteArray'
		}, function(res) {
			console.log(res);
			$scope.isLoding = true;
			try {
				if (res.code == 200) {
					res = res.data;
					$scope.List = res.pngList;
					$scope.totalList = $scope.List.slice(0, $scope.List.length);
					$scope.currentList();
					//$scope.img = $scope.List[0].png;
					try {
						$scope.printImg = $.getConfigMsg.preUrl + "/pdfTemp/" + res.pdf;
						appData.printpdfBase64 = res.pdfBase64;
					} catch (e) {}

				} else {
					$scope.isAlert = true;
					$scope.msg = "未获取到档案图片信息，请重试";
					$scope.alertConfirm = function() {
						$state.go("loginType");
					}
				}
			} catch (e) {
				alert('接口返回信息异常' + JSON.stringify(res))
			}
		}, function(err) {
			$scope.isAlert = true;
			$scope.msg = "获取电子证明失败,请重试";
			$scope.alertConfirm = function() {
				$state.go("loginType");
			}
		})
	}
	$scope.creditReport();
	$scope.allScreen = function() {
		$scope.isShowView = true;
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
	//	$scope.show = function(){
	//		viewer.show();
	//		$scope.view = false;
	//	}
	//	$scope.hide = function(){
	//		viewer.hide();
	//		$scope.view = true;
	//	}
	//	$scope.close = function() {
	//		$scope.isAllScreen = false;
	//	};
	$scope.print = function() {
		$scope.isAlert = true;
		$scope.msg = "正在打印中。。。";
		$scope.timestamp = Date.parse(new Date());
		//		$scope.path = "C:\\" + $scope.timestamp + ".pdf";
		//		$scope.filePath = "C:/" + $scope.timestamp + ".pdf";
		$scope.path = "D:\\pdfPrint.pdf";
		$.device.urlPdfPrint($scope.printImg, $scope.path, function() {}, appData.printpdfBase64);
		saveDeviceStatus("A4打印机", 0, "正常", 0, 0, 0, $scope.totalList.length);
		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: '信用报告查询',
			}
		}
		recordUsingHistory('发改委服务', '查询+打印', '信用报告查询', appData.archivesName, appData.archivesNumber, '', '',
			JSON.stringify($scope.jsonStr));
		trackEventForQuery('信用报告查询', '', "打印", "上海市发展和改革委员会", appData.archivesName, appData.archivesNumber,
			"");
		//		$.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		urlHost +
		//			$scope.printImg,
		//			$scope.path,
		//			//将选中图片下载
		//			function(bytesCopied, totalBytes) {
		//				console.log(bytesCopied + "," + totalBytes);
		//			},
		//			function(result) {
		//				$.device.pdfPrint($scope.filePath);
		//				saveDeviceStatus("A4打印机", 0, "正常", 0, 0, 0, $scope.totalList.length);
		//				//模块使用记录
		//				$scope.jsonStr = {
		//					SUCCESS: "true",
		//					data: {
		//						name: '个人信用报告',
		//					}
		//				}
		//				recordUsingHistory('发改委服务', '查询+打印', '个人信用报告', appData.archivesName, appData.archivesNumber, '', '', JSON.stringify($scope.jsonStr));
		//			},
		//			function(webexception) {
		//				alert("下载文档失败");
		//			}
		//		);
		$timeout(function() {
			$scope.alertConfirm = function() {
				$scope.isAlert = false;
				$state.go("loginType");
			}
		}, 3000);
	}
})
