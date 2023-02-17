var preIP_JA_extranet = "http://101.230.224.65:8080/ac"; // 静安外网IP地址

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
app.controller("licenseMain", function($scope, $state, appData) {
//	removeAnimate($('.scrollBox2'))
//	addAnimate($('.scrollBox2'))
	$scope.operation = "请选择证照类型";
	$scope.choiceType = function(license) {
		console.log(license);
		appData.licenseType = license;
		$state.go("loginType");
	}
});
app.controller('licenseLoginType', function($state, $scope, appData) {
//	removeAnimate($('.scrollBox2'))
	//	addAnimate($('.scrollBox2'))
	$scope.operation = "请选择登录方式";
	$scope.licenseType = appData.licenseType;
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");

//		测试数据
//		appData.licenseName = '岳敏';
//		appData.licenseNumber = "31010919810727205X"; //"310105198006031219";
		
//		appData.licenseName = '陈雷';
//		appData.licenseNumber ="310228198808070818";
//		appData.idcard_valid_start_day = '2011-06-03';
//		appData.idcard_valid_end_day = '2021-06-03';
		
//		$state.go("license");
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller('licenseLogin', function($scope, $http, $state, appData, appFactory) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
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
	$scope.caLoginStatus = "";
	$scope.caLogin = function() { //登录
		$scope.caLoginStatus = 'login';
	}
	$scope.caInfo = function(companyName, companyNo) {
		if(companyName && companyNo) {
			appData.licenseNumber = companyNo;
			appData.licenseName = companyName;
			$state.go("license");
			$scope.$apply();
		}
	}
	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
			appData.idcard_valid_start_day = info.ValidtermOfStart;
			appData.idcard_valid_end_day = info.ValidtermOfEnd;
		} else {
			layer.msg("没有获取到")
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		$state.go("license");
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}

	$scope.citizenLogin = function(info) {
		var idcardInfo = info.result.data;
		appData.licenseName = idcardInfo.realname;
		appData.idcard_valid_start_day = idcardInfo.VALIDSTARTDAY;
		appData.idcard_valid_end_day = idcardInfo.VALIDENDDAY;
		if(appData.licenseType == 'person') {
			if(info.url == "") {
				layer.msg("未识别到证照信息！", {
					time: 5000
				});
				$state.reload();
			}
			if(info.code != idcardInfo.idcard) {
				layer.msg("二维码类型错误，请扫描市民亮证个人二维码", {
					time: 2000
				});
				$state.reload();
			} else {
				appData.licenseNumber = info.code;
				$state.go("license");
				$scope.$apply();
			}
		} else if(appData.licenseType == 'corporate') {
			if(info.code == idcardInfo.idcard) {
				layer.msg("未识别到您的企业信息，请确认二维码正确后重新扫描！", {
					time: 5000
				});
				$state.reload();
			} else {
				appData.licenseNumber = info.code;
				$state.go("license");
				$scope.$apply();
			}
		}
	}
})
app.controller("licenseLicense", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.operation = (appData.licenseType == 'person') ? "个人电子证照" : "法人电子证照";
	$scope.licenseNumber = appData.licenseNumber; // 号码
	$scope.licenseType = (appData.licenseType == 'person') ? '0' : '1'; //法人 1 个人0
	$scope.currentPage = 1; //当前页
	$scope.totalPages = 1; //总页数
	$scope.showLicenseList = []; //展示的图片容器
	$scope.totalLicense = []; //所有证照容器
	$scope.historyData = []; //历史上传
	$scope.elicenseData = []; //电子证照库电子证照
	$scope.currentLicense = 'license'; //现在证照类型
	$scope.currentImgIndex = null; //现在选择图片下标
	$scope.electImg = ''; //当前选中图片地址
	$scope.isShowPrint = false; //是否显示打印弹框
	$scope.isShowView = false; // 是否显示预览框
	$scope.zoomCount = 1;
	$scope.rotateCount = 0;
	$scope.reLoadCount = 5;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoding = false;
	$scope.certName=""; // 存储被点击的证照名称
	$scope.pdfLicense = ""; // 存储被点击的pdf文件
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
		if($scope.currentLicense === 'history' && $scope.totalLicense.indexOf("../libs/common/images/addImg.png") === -1) {
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
		if(item != "../libs/common/images/addImg.png") {
			console.log(item.certName);
			$scope.certName = item.certName;
			$scope.currentImgIndex = i;
			$scope.stuffId = (item.stPersonalDocument ? item.stPersonalDocument : "");
			$scope.electImg = (item.pictureUrlForBytes ? item.pictureUrlForBytes : item.imageUrl);
			$scope.pdfLicense = item.derivePictureUrlForBytes;
			$scope.isShowView = true;
		}
	}
	$scope.choiceLicenseType = function(type) {
		$scope.currentImgIndex = null;
		$scope.currentLicense = type;
		$scope.currentPage = 1;
		$scope.currentList();
		if(type == 'license') {
			if($scope.elicenseData.length > 0) {
				$scope.totalLicense = $scope.elicenseData.slice(0, $scope.elicenseData.length);
				$scope.currentList(1);
			} else {
				$scope.getLicenseList();
			}
		} else if(type == 'history') {
			if($scope.historyData.length > 0) {
				$scope.totalLicense = $scope.historyData.slice(0, $scope.historyData.length);
				$scope.currentList(1);
			} else {
				$scope.getHistoryData();
			}

		}
	}
	$scope.configUrl = $.getConfigMsg.preUrl;
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
			machineId: $.config.get('uniqueId') || "",
			itemName: "",
			itemCode: "",
			businessCode: "",
			name: appData.licenseName || "",
			startDay: appData.idcard_valid_start_day || "",
			endDay: appData.idcard_valid_end_day || ""
		};
		$timeout(function() {
			$http.jsonp($.getConfigMsg.preUrl + "/aci/autoterminal/dzzz/queryCertBaseData.do", {
					params: httpConfig
				})
				.success(function(data) {
					if(data) {
						console.log(data);
						$scope.elicenseData = data;
						$scope.totalLicense = $scope.elicenseData.slice(0, $scope.elicenseData.length);
						$scope.currentList()
					}
					// 静安自助用证记录保存
					$scope.saveECertRecords()
					$scope.isLoding = true;
				})
				.error(function(err) {
					if($scope.reLoadCount > 1) {
						$scope.getLicenseList();
					}
					$scope.reLoadCount--;
				})
		})
	};

	// 静安自助用证记录保存
	$scope.saveECertRecords = function() {
		var httpConfig = {
			machineId: $.config.get('uniqueId') || "",
			itemName: "电子证照",
			itemCode: "",
			certNo: $scope.licenseNumber, //"340881199303145313" || 
			name: appData.licenseName || "",
			catMainCode: "",
			jsonpCallback: "JSON_CALLBACK"
		};
		$http.jsonp($.getConfigMsg.preUrl + "/ext/ja/saveECertRecords.do", {
			params: httpConfig
		})
		.success(function(data) {
			if(data.success) {
				console.log('用证记录保存成功');
				$.log.debug("用证记录保存成功")
			} else {
				console.log('用证记录保存失败');
				$.log.debug("用证记录保存失败")
			}
		})
		.error(function(err) {
			console.log("用证记录保存失败: ", err)
			$.log.debug("用证记录保存失败: ", err)
		})
	}

	$scope.preview = function() {
		if($scope.currentImgIndex == null) {
			$scope.isAlert = true;
			$scope.msg = "请选择图片后再预览";
			$scope.alertConfirm = function() {
				$scope.isAlert = false;
				$state.go("license");
			}
		} else {
			appData.previewImg = $scope.electImg;
			$state.go("preview");
		}
	};
	//删除上传材料
	$scope.delStuff = function() {
		if($scope.currentImgIndex == null) {
			$scope.isAlert = true;
			$scope.msg = "请选择图片后再删除";
			$scope.alertConfirm = function() {
				$scope.getHistoryData();
			}
		}
		$timeout(function() {
			appFactory.pro_fetch("forward.do", {
				fmd: "aci-archives",
				fdo: "delWorkLicenseStuffById",
				stPersonalDocument: $scope.stuffId
			}, function(data) {
				console.log(data);
				if(data == '1') {
					$scope.isAlert = true;
					$scope.msg = "删除成功";
					$scope.alertConfirm = function() {
						$scope.getHistoryData();
						$scope.isAlert = false;
					}
				} else {
					$scope.isAlert = true;
					$scope.msg = "删除失败";
					$scope.alertConfirm = function() {
						$scope.getHistoryData();
						$scope.isAlert = false;
					}
				}
			}, function(err) {
				console.log(err);
			})
		})
		$scope.getHistoryData();
	}

	$scope.getHistoryData = function() { //历史上传数据
//		$timeout(function() {
//			appFactory.pro_fetch("forward.do", {
//				fmd: "aci-archives",
//				fdo: "getLicenseStuffList",
//				stName: appData.licenseName, // "夏雷" ||"340881199303145313" || 
//				stIdNo: $scope.licenseNumber,
//				type: $scope.licenseType
//			}, function(data) {
//				if(!data) {
//					$scope.totalLicense = [];
//				} else {
//					$scope.historyData = data;
//					$scope.totalLicense = $scope.historyData.slice(0, $scope.historyData.length);
//					console.log($scope.totalLicense);
//				}
//				$scope.currentList();
//			}, function(err) {
//				if($scope.reLoadCount > 1) {
//					$scope.getHistoryData();
//				}
//				$scope.reLoadCount--;
//			})
//		})
		
		$.ajax({
			url: $.getConfigMsg.preUrl + '/aci/autoterminal/forward.do',
			type: 'get',
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data: {
				fmd: "aci-archives",
				fdo: "getLicenseStuffList",
				stName: appData.licenseName, // "夏雷" ||"340881199303145313" || 
				stIdNo: $scope.licenseNumber,
				type: $scope.licenseType
			},
			success: function(data) {
				console.log(data);
				$scope.isLoding = true;
				if(!data) {
					$scope.totalLicense = [];
				} else {
					$scope.historyData = data;
					$scope.totalLicense = $scope.historyData.slice(0, $scope.historyData.length);
					console.log($scope.totalLicense);
				}
				$scope.currentList();
			},
			error: function (err) {
				if($scope.reLoadCount > 1) {
					$scope.getHistoryData();
				}
				$scope.reLoadCount--;
			}
		})
	};

	if($rootScope.router) {
		$scope.choiceLicenseType('history');
	} else {
		$scope.getLicenseList();
	};

	$scope.print = function() {
//		$timeout(function() {
//			if(appData.licenseType == 'person') {
//				LODOP_PRINT.personLicense($scope.configUrl + $scope.electImg);
//			} else {
//				LODOP_PRINT.corporateLicense($scope.configUrl + $scope.electImg);
//			}
//		}, 200)
		$scope.isShowPrint = 'show';
		$scope.timestamp = Date.parse(new Date());
		$scope.path = "C:\\"+$scope.timestamp+".pdf";
		$scope.filePath = "C:/"+$scope.timestamp+".pdf";
		$.log.debug('pdf地址: ');
		$.log.debug("http://101.230.224.65:8080/ac" + $scope.pdfLicense);
		console.log("http://101.230.224.65:8080/ac" + $scope.pdfLicense);
		if($scope.currentLicense == 'license') {
			if($scope.certName == '中华人民共和国居民身份证') {
//				LODOP_PRINT.personLicense($scope.configUrl + $scope.electImg);
//				$timeout(function() {
					if(appData.licenseType == 'person') {
						LODOP_PRINT.personLicense($scope.configUrl + $scope.electImg);
					} else {
						LODOP_PRINT.corporateLicense($scope.configUrl + $scope.electImg);
					}
//				}, 200)
			} else {
				$.device.httpDownload(
//					$scope.configUrl + $scope.pdfLicense,
//					"http://12.113.230.10:8080/ac" + $scope.pdfLicense,
					"http://101.230.224.65:8080/ac" + $scope.pdfLicense,
//					"C:\\pdfLicense.pdf",
					$scope.path,
					// 将选中图片下载
					function(bytesCopied, totalBytes) {
						console.log(bytesCopied + "," + totalBytes);
					},
					function(result) {
						$timeout(function () {
//							$.device.pdfPrint($scope.filePath);
							$.device.pdfAdobeReaderPrint($scope.filePath);
						}, 2000);
					},
					function(webexception) {
						alert("下载文档失败");
					}
				)
			}
		} else if($scope.currentLicense == 'history') {
			LODOP_PRINT.personLicense($scope.configUrl + $scope.electImg);
		}
		$timeout(function() {
			$scope.isShowPrint = false;
		}, 3000);
	};

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

	//	addAnimate($('.licenseContainer'))
	$('.licenseContainer').fadeIn(1000);

	$scope.prevStep = function() {
		$state.go("loginType");
	}
});
app.controller("historyUpload", function($scope, $state, appData, $http, appFactory, $timeout) {
//	$scope.Name = appData.licenseName;
//	$scope.Number = appData.licenseNumber;

	$scope.type = (appData.licenseType == 'person') ? '0' : '1';
	$scope.materialData = null;
	$scope.isCapture = true;
	$scope.isAlert = false;
//		$scope.upload = function() {
//			$.ajax({
//				url: $.getConfigMsg.preUrl + "/aci/autoterminal/forward.do",
//				type: "post",
//				data: {
//					fmd: "aci-archives",
//					fdo: "saveLicenseStuff",
//					stName: appData.licenseName,
//					stIdNo: appData.licenseNumber,
//					stLicenseName: "工作台上传",
//					type: $scope.type,
//					stApplyId: "",
//					stShareCode: "",
//					stPersonalDocument: "",
//					FileData: $scope.materialData //图片数据
//				},
//				success: function(data) {
//					$scope.isAlert = true;
//					$scope.msg = "上传成功";
//					$scope.alertConfirm = function() {
//						$scope.isAlert = false;
//						$state.go("license");
//						$scope.$apply();
//					}
//	
//				},
//				error: function() {
//					$scope.isAlert = true;
//					$scope.msg = "上传失败";
//					$scope.alertConfirm = function() {
//						$scope.isAlert = false;
//						$state.go("license");
//						$scope.$apply();
//					}
//	
//				}
//			})
//	
//		}

	$scope.upload = function() {
		$.log.debug($scope.materialData);
		$scope.jsonData = {
			'stName': appData.licenseName, //  '751122018600008'
			'stIdNo': appData.licenseNumber,
			'stLicenseName': "工作台上传",
			'type': $scope.type,
			'stApplyId': "",
			'stShareCode': "",
			'stPersonalDocument': "",
		};
		//将选中图片上传到服务器
		$.device.httpUpload($.getConfigMsg.preUrl_JA_intranet + '/aci/autoterminal/archives/saveLicenseStuff.do', 'FileData', $scope.materialData,
			JSON.stringify($scope.jsonData),
			function(result) {
				layer.msg("上传成功");
				$scope.isLoading = true;
				$scope.isUploading = false;
	            $state.go("license");
			},
			function(webexception) {
				$scope.isLoading = true;
				layer.msg("上传失败");
				$scope.isUploading = false;
	            $state.go("license");
		});
	};
	$scope.getImg = function(img, url) {
//		if(img) {
//			$scope.materialData = img.replace('data:image/png;base64,', ''); //data:image/png;base64,
//			$scope.isCapture = false;
//			$scope.upload()
//		}
		if(url) {
			$scope.materialData = url
			$scope.isCapture = false;
			$scope.isUploading = true;
			$scope.upload();
		}
	}

	$scope.prevStep = function() {
		$state.go("license");
	}
	
	$scope.confirm = function () {
		$scope.isUploading = false;
	}

});
