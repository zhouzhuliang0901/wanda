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
	$scope.guideline1 = "<p>1、市民云实名用户</p><p>2、全市18岁以上常住人口（具有本市户籍或持有居住证）</p>";
	addAnimate($('.scrollBox2'))
	$scope.prevStep = function() {
		window.location.href = "../declare/index.html#main";
	}
	$scope.nextStep = function(){
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
//		appData.archivesNumber = "429004199312101138"; //"430426199804106174";
//		appData.archivesName = "肖邦"; //"邹天奇";
//		$state.go("creditReport");
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
			$scope.operation = "随申办";
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
		var idcardInfo = info.result.data;
		appData.archivesName = idcardInfo.realname;
		appData.archivesNumber = idcardInfo.idcard;
		$state.go("creditReport");
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
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//浏览全文接口
	$scope.creditReport = function() {
		function ClearBr(key) {
			key = key.replace(/[\r\n]/g, "");
			return key;
		}
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/medicalInsurance/creditReport.do",
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
					$scope.previewImg = "data:image/png;base64," + ClearBr(data.png);
					$scope.printImg = $.getConfigMsg.preUrl + "/aci/creditPDF.pdf";
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
		$.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		urlHost + 
			$scope.printImg,
			"C:\\pdfLicense.pdf",
			//将选中图片下载
			function(bytesCopied, totalBytes) {
				console.log(bytesCopied + "," + totalBytes);
			},
			function(result) {
				$.device.pdfPrint("C:/pdfLicense.pdf");
			},
			function(webexception) {
				alert("下载文档失败");
			}
		);
		$timeout(function() {
			$scope.isAlert = true;
			$scope.msg = "正在打印中。。。";
			$scope.alertConfirm = function() {
				$scope.isAlert = false;
				$state.go("loginType");
			}
		}, 3000);
	}
	//图片预览
	$scope.viewClose = function() {
		$scope.isShowView = false;
		$scope.zoomCount = 1;
		$scope.rotateCount = 0;
	};

	$scope.zoomIn = function() {
		$scope.zoomCount += 0.5;
		if($scope.zoomCount > 5) {
			$scope.zoomCount = 5;
		}
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

	$scope.zoomOut = function() {
		$scope.zoomCount -= 0.5;
		if($scope.zoomCount < 0.5) {
			$scope.zoomCount = 0.5;
		}
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

	$scope.rotateLeft = function() {
		$scope.rotateCount -= 90;
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

	$scope.rotateRight = function() {
		$scope.rotateCount += 90;
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};
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