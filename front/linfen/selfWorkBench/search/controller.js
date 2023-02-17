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
	$scope.operation = "请选择查询内容";
	$scope.stuffName = perjsonStr;
	$scope.choiceType = function(type, name, ywlx) {
		trackEvent(name);
		appData.funName = name;
		appData.type = type;
		appData.ywlx = ywlx;
		if(type == "nonStapleFood") {
			window.location.href = "../FGW_nonStapleFood/index.html";
		} else {
			$state.go("guideline");
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
app.controller('guideline', function($state, $scope, appData, $http) {
	removeAnimate($('.scrollBox2'))
	$scope.operation = "请选择登录方式";
	$scope.funName = "信用报告";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.guideline = "<p>受上海市公共信用信息服务平台委托,配合参与2015年上海市信用实事项目推出个人信用报告查询服务。为广大市民在线免费提供个人信用查询报告。市民通过身份验证即可在线免费获取包含登记类、资质类、监管类、判决类、执行类、违约类、公益类信息的信用查询报告。</p>";
	$scope.guideline1 = "<p>1、全市18岁以上常住人口（具有本市户籍或持有居住证）</p>";
	addAnimate($('.scrollBox2'))
	$scope.prevStep = function() {
		window.location.href = "../declare/index.html#main";
	}
	$scope.nextStep = function() {
		$state.go("loginType");
	}
});
app.controller('loginType', function($state, $scope, appData, $http) {
	removeAnimate($('.scrollBox2'))
	$scope.operation = "请选择登录方式";
	$scope.funName = "信用报告";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	addAnimate($('.scrollBox2'))
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go('login');
	}
	$scope.prevStep = function() {
		$state.go("main");
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

	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.archivesNumber = info.Number;
			appData.archivesName = info.Name;
		} else {
			layer.msg("没有获取到")
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		appData.img = img;
		$state.go("creditReport");
	}

	$scope.prevStep = function() {
		$state.go("loginType");
	}
	$scope.citizenLogin = function(info) {
		if(appData.qrCodeType == "suishenma") {
			appData.archivesName = info.zwdtsw_name;
			appData.archivesNumber = info.zwdtsw_cert_id;
			$state.go("creditReport");
		} else {
			var idcardInfo = info.result.data;
			appData.archivesName = idcardInfo.realname;
			appData.archivesNumber = idcardInfo.idcard;
			$state.go("creditReport");
		}

	}
})
app.controller("creditReport", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.stuffName = "个人信用报告";
	$scope.nextText = "打印";
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.previewImg = "";
	$scope.printImg = "";
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
	$scope.currentList = function(current) {
		if(current === undefined) {
			current = $scope.currentPage;
		}
		$scope.currentImgIndex = null;
		$scope.startPos = (current - 1) * 2; //0 -3  3 - 6 
		$scope.endPos = $scope.startPos + 2;
		$scope.emptyPreviewImgList = $scope.totalList.slice($scope.startPos, $scope.endPos);
		$scope.totalPages = Math.ceil($scope.totalList.length / 2);
		$scope.emptyPreviewImgList.length = 2;
		console.log($scope.emptyPreviewImgList);
		for(var i in $scope.emptyPreviewImgList) {
			if($scope.emptyPreviewImgList[i] != undefined) {
				$scope.previewImgList.push($scope.emptyPreviewImgList[i]);
			}
			$("#jq22").append('<img data-original="' + $scope.base + $scope.emptyPreviewImgList[i].png + '" src="' + $scope.base + $scope.emptyPreviewImgList[i].png + '" alt="">');
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
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl+"/aci/workPlatform/medicalInsurance/creditReport.do",
			/*url写异域的请求地址*/
			dataType: "jsonp",
			/*加上datatype*/
			jsonp: "jsonpCallback",
			/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
			data: {
				SFZH: appData.archivesNumber,
				userName: appData.archivesName,
			},
			success: function(data) {
			  	console.log(data);
				$scope.isLoding = true;
				if(data.result == "1005") {
					$scope.List = data.pngList;
					$scope.totalList = $scope.List.slice(0, $scope.List.length);
					$scope.currentList();
					//$scope.img = $scope.List[0].png;
					$scope.printImg = $.getConfigMsg.preUrl + "/aci/pdfTemp/"+data.pdf;
				} else {
					$scope.isAlert = true;
					$scope.msg = "未获取到档案图片信息，请重试";
					$scope.alertConfirm = function() {
						$state.go("loginType");
					}
				}
			},
			error: function(err) {
				$scope.isAlert = true;
				$scope.msg = "获取电子证明失败,请重试";
				$scope.alertConfirm = function() {
					$state.go("loginType");
				}
			}
		});
	}
	$scope.creditReport();
	$scope.allScreen = function() {
		$scope.isShowView = true;
	}
	$scope.prevStep = function() {
		$state.go("main");
	}

	$scope.print = function() {
		$scope.isShowPrint = "show";
		$scope.path = "D:\\pdfPrint.pdf";
		$scope.filePath = "D:/pdfPrint.pdf";
		$.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		urlHost + 
			$scope.printImg,
			$scope.path,
			//将选中图片下载
			function(bytesCopied, totalBytes) {
				console.log(bytesCopied + "," + totalBytes);
			},
			function(result) {
				$.device.pdfPrint($scope.filePath);
				saveDeviceStatus("A4Printer", 0, "正常", 0, 0, 0, $scope.totalList.length);
				//模块使用记录
				$scope.jsonStr = {
					SUCCESS: "true",
					data: {
						name: '信用报告查询',
					}
				}
				recordUsingHistory('发改委服务', '查询+打印', '信用报告查询', appData.archivesName, appData.archivesNumber, '', '', JSON.stringify($scope.jsonStr));
				trackEventForQuery('信用报告查询', '', "打印", "上海市发展和改革委员会", appData.archivesName, appData.archivesNumber, "");
			},
			function(webexception) {
				alert("下载文档失败");
			}
		);
		$timeout(function() {
			$state.go("guideline");
		}, 3000);
	}

})
app.controller("info", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.operation = "填写个人信息--注册";
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.nextText = "提交";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
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