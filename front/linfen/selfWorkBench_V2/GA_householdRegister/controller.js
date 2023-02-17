app.controller('choiceMode', function($state, $scope, appData, $timeout, appFactory, $http) {
	$scope.funName = appData.funName = "户籍证明开具";
	appData.itemCode = $scope.itemCode = "0105105000-07-04";
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.choiceType = function(type) {
		if (type == "print") {
			appData.type = type;
		}
		$state.go("guideline");
	}
	$scope.prevStep = function() {
		window.location.href = "../GA_allItem/index.html";
	}
})

//办理须知
app.controller('guideline', function($state, $scope, appData, $state, $location) {
	appData.funName = $scope.stuffName = "户籍证明开具";
	appData.itemCode = $scope.itemCode = "0105105000-07-04";
	$scope.nextStep = function() {
		$state.go("loginType");
	}
	$scope.prevStep = function() {
		window.location.href = "../GA_allItem/index.html"
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			bounce: true,
			click: true,
			taps: true,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true,
		});
	};
	$scope.isScroll();
});
app.controller('loginType', function($state, $scope, appData, $state, $location) {
	appData.xsbb = false;
	appData.funName = $scope.stuffName = "户籍证明开具";
	appData.itemCode = $scope.itemCode = "0105105000-07-04";
	if (appData.type == null || appData.type == undefined || appData.type == "") {
		appData.type = $location.search().type;
	}
	if (appData.sign == null || appData.sign == undefined || appData.sign == "") {
		appData.sign = $location.search().sign;
	}
	console.log(appData.type + "------" + appData.sign);
	$scope.operation = "请选择登录方式";
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		window.location.href = "../CSJ_householdRegisterALL/index.html#/";
		//		if((appData.type == "print") && (appData.sign == 1)) {
		//			window.location.href = "../CSJ_householdRegister/index.html#/choiceProvince"
		//		} else if(appData.type == "print") {
		//			window.location.href = "../GA_allItem/index.html"
		//		} else if(appData.sign == 1) {
		//			window.location.href = "../CSJ_householdRegister/index.html#/choiceProvince"
		//		} else {
		//			$state.go("guideline");
		//		}
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory) {
	$scope.funName = appData.funName;
	$scope.isAlert = false;
	$scope.msg = "";
	$scope.loginType = appData.loginType;
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
	//跳转页面
	$scope.nextStep = function() {
		$scope.tokenType = "token";
		$scope.token = function() {
			if (appData.type == "print") {
				$state.go("preview");
			} else {
				$state.go("choose");
			}
		}
	}

	$scope.idcardLogin = function(info, images) {
		if (info) {
			$scope.faceImage = images;
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
			appData.VALIDENDDAY = info.ValidtermOfEnd;
			appData.VALIDSTARTDAY = info.ValidtermOfStart;
			$scope.loginType = "recognition";
		} else {
			layer.msg("很抱歉，未获取到相关信息，请重试。");
		}
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.alertCancel = function() {
		$scope.isAlert = false;
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		if (appData.type == "print") {
			$state.go("preview");
		} else {
			$state.go("choose");
		}
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
		if (appData.qrCodeType == "suishenma") {
			appData.licenseName = info.zwdtsw_name;
			appData.licenseNumber = info.zwdtsw_cert_id;
			appData.zwdtsw_user_id = info.zwdtsw_user_id;
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
});
//选择区和办理点
app.controller('choose', function($state, $scope, appData, $http, $interval, $timeout) {
	$scope.funName = appData.funName;
	appData.xsbb = true;
	appData.isUpload = []; //已上传材料
	appData.listImg = []; //需上传材料
	appData.fileName = [];
	appData.currentIndex = 0;
	$scope.areaList = areaList;
	$timeout(function() {
		$scope.code = $scope.area.areaCode;
		$scope.getItemApplyPlace("");
	}, 100);
	$scope.getItemApplyPlace = function(code) {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/getItemApplyPlace.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				itemCodes: appData.itemCode,
				regionCode: code || $scope.code
			},
			success: function(dataJson) {
				$timeout(function() {
					$scope.handleList = dataJson;
				}, 100);
				console.log(dataJson);
			},
			error: function(err) {
				console.log("getItemApplyPlace err");
			}
		});
	}
	$scope.getAddress = function(address, bldName, bldCode) {
		console.log(bldName + address);
		$scope.address = bldName + address;
		$scope.bldCode = bldCode;
	}
	$scope.nextStep = function() {
		appData.bldCode = $scope.bldCode;
		$state.go("info");
	}

	$scope.prevStep = function() {
		$state.go("loginType");
	}
})
//信息填写
app.controller('info', function($state, $scope, $rootScope, appData, $http, $interval, $timeout, appFactory) {
	$scope.funName = appData.funName;
	$scope.stName = appData.licenseName;
	$scope.stIdCard = appData.licenseNumber;
	$scope.licenseNumber = appData.encrypt_identity || appData.licenseNumber;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoading = false;
	$scope.nextText = "提交";
	appFactory.pro_fetch($rootScope.tokenSNO, function(res) {
		$scope.token = res.accessToken;
	})
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$scope.isLoading = false;
	}
	$scope.kjfwTypeList = kjfwType

	$scope.change = function(index, item) {
		$scope.current = index;
		$scope.kjfw = item;
	}

	//提交
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if (!isPhoneAvailable($('#stMobile').val())) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的手机号！";
				return;
			}
			if (isBlank($scope.kjfw.id)) {
				$scope.isAlert = true;
				$scope.msg = "请选择开具范围！";
				return;
			}
			if (isBlank($('#stReason').val())) {
				$scope.isAlert = true;
				$scope.msg = "请填写申报事由！";
				return;
			}
		} while (condFlag);
		$scope.isLoading = true;
		let paramStr = {
			data: {
				accessToken: $scope.token,
				departCode: appData.bldCode,
				source: "",
				itemCode: appData.itemCode,
				info: {
					licenseNo: appData.licenseNumber,
					username: appData.licenseName,
					mobile: $scope.stMobile,
					kjfw: {
						value: $scope.kjfw.id,
						name: $scope.kjfw.name
					},
					content: $('#stReason').val()
				}
			}
		}
		console.log(paramStr);
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/selfDeclare/saveApply.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				appName: "9b7d183d-10d7-4cf1-a50a-add588a6d18f",
				idCard: appData.licenseNumber,
				name: encodeURI(appData.licenseName),
				itemName: encodeURI(appData.funName),
				paramStr: encodeURI(JSON.stringify(paramStr))
			},
			success: function(dataJson) {
				if (dataJson.success == true && dataJson.data.isSuccess == true) {
					appData.applyNo = dataJson.data.applyNo;
					//上传材料
					appFactory.upload_file($scope.licenseNumber, appData.licenseName, appData
						.VALIDENDDAY, appData.VALIDSTARTDAY, '310105109000100', appData
						.applyNo,
						function(dataJson) {
							if (dataJson.data == "" || dataJson.data == null || dataJson
								.data == undefined || dataJson.data.length == 0) {
								layer.msg("未能从电子证照获取到身份证照上传");
								$timeout(function() {
									$state.go("materialList");
								}, 100)
							}
							try {
								appData.imgStr = $scope.imgUrl = "data:image/jpeg;base64," +
									dataJson.data[0].str;
							} catch (e) {}
						},
						function(dataJson1) {
							if (dataJson1) {
								if (dataJson1.isSuccess == true) {
									try {
										if (appData.isUpload.length < 1) {
											appData.isUpload.push({
												index: 4,
												stuffName: "《居民身份证》",
												img: $scope.imgUrl,
												status: 1,
												method: "高拍仪"
											});
										}
									} catch (e) {}
								}
							} else {
								layer.msg("未能从电子证照获取到身份证照上传");
							}
							$timeout(function() {
								$state.go("materialList");
							}, 100)
						});
					//模块使用记录
					$scope.jsonStr = {
						SUCCESS: "true",
						data: {
							name: appData.funName,
							applyNo: appData.applyNo,
							mobile: $scope.stMobile,
						}
					}
					recordUsingHistory('公安服务', '办理', appData.funName, appData.licenseName,
						appData.licenseNumber, $scope.stMobile, appData.applyNo, JSON
						.stringify($scope.jsonStr));
					//行为分析(办理)
					trackEventForAffairs(appData.applyNo, appData.funName, "上海市公安局", appData
						.licenseName, appData.licenseNumber, $scope.stMobile);
				} else {
					$scope.isAlert = true;
					$scope.msg = dataJson.msg || dataJson.data.msg || dataJson.data.message;
					return;
				}
			},
			error: function(err) {
				console.log(err);
			}
		});
	}

	$scope.prevStep = function() {
		$state.go("choose");
	}
})
app.controller("uploadMethod", function($scope, $http, $state, $rootScope, appData, $timeout, appFactory) {
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();
	$scope.funName = appData.funName;
	// 扫描上传
	$scope.scanPhoto = function() {
		$state.go('takePhoto');
	};
	// U盘上传
	$scope.takePhoto = function() {
		layer.confirm("<em style='color:black'>" + '请确认是否插入U盘！' + "</em>", {
			btn: ['已插入U盘', '未插入U盘'] //按钮
		}, function() {
			$('.layui-layer-shade').hide();
			$('.layui-layer').hide();
			$timeout(function() {
				$state.go('uFileUpload/U');
			}, 20);
		}, function() {
			$('.layui-layer-shade').hide();
			$('.layui-layer').hide();
			$timeout(function() {
				layer.msg('请选择其他上传方式！');
			}, 20);
		});
	};
	// 档案库上传
	$scope.materialPic = function() {
		$state.go('materialPic');
	};
	//返回
	$scope.prve = function() {
		$state.go('materialList');
	}
});
app.controller("materialPic", function($scope, $http, $state, appData, $rootScope, $timeout, appFactory) {
	$scope.funName = appData.funName;
	$scope.imgUrls = "";
	$scope.prevText = "返回";
	$scope.nextText = "确认上传";
	$scope.url = $.getConfigMsg.preUrlSelf;
	$scope.prevStep = function() {
		$state.go('materialList');
	}
	$scope.profileShow = function() {
		$scope.isLoading = false;
		$.ajax({
			url: $.getConfigMsg.preUrlSelf + "/selfapi/dzzz/queryCertBaseData.do",
			type: "get",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				//jsonpCallback: "JSON_CALLBACK",
				certNo: appData.encrypt_identity || appData.licenseNumber,
				name: encodeURI(appData.licenseName),
				type: 0,
				machineId: $.config.get("uniqueId") || "",
				itemName: encodeURI(appData.funName),
				itemCode: "0105105000-07-04",
				businessCode: "",
				startDay: appData.VALIDSTARTDAY,
				endDay: appData.VALIDENDDAY,
				licenseType: "cert"
			},
			success: function(json) {
				$scope.isLoading = true;
				var dataJson = json;
				if (!dataJson) { // !dataJson[0].address
					layer.msg("没有数据，请重新选择上传方式!");
					$timeout(function() {
						$state.go('uploadMethod');
					}, 1000);
				} else {
					$scope.imgUrls = dataJson.data;
					$scope.$apply();
					console.log($scope.imgUrls)
				}
			},
			error: function(err) {
				console.log(err)
			}
		});
	};
	$scope.profileShow();
	$scope.current = 0;
	$scope.select = function(index) {
		$scope.current = index;
	}
	$scope.convertImgToBase64 = function(url, outputFormat, callback) {
		var canvas = document.createElement('CANVAS'),
			ctx = canvas.getContext('2d'),
			img = new Image();
		img.crossOrigin = 'Anonymous';
		img.onload = function() {
			canvas.height = img.height;
			canvas.width = img.width;
			ctx.drawImage(img, 0, 0);
			var dataURL = canvas.toDataURL(outputFormat || 'image/png');
			callback.call(this, dataURL);
			canvas = null;
		};
		img.src = url;
	}

	$scope.goNext = function() {
		$scope.isLoading = false;
		appData.selectImg = $.getConfigMsg.preUrlSelf + $scope.imgUrls[$scope.current].pictureUrlForBytes;
		$scope.waitUploadImgUrl = appData.selectImg;
		$scope.jsonData = {
			applyNo: appData.applyNo,
			stuffCode: appData.stuffCode,
			stuffId: "",
		};
		$scope.jsonData = JSON.stringify($scope.jsonData);
		console.log($scope.jsonData);
		try {
			//上传base64
			$scope.convertImgToBase64(appData.selectImg, 'image/jpg', function(a) {
				var dataImg = a;
				dataImg = dataImg.replace(/^data:image\/\w+;base64,/, "");
				try {
					$.ajax({
						url: $.getConfigMsg.preUrlSelf +
							'/selfapi/uploadItemStuffs.do',
						type: "post",
						dataType: "json",
						data: {
							applyNo: appData.applyNo,
							stuffCode: appData.stuffCode,
							stuffId: "",
							"FileData": dataImg,
						},
						success: function(result) {
							layer.msg("上传成功");
							appData.isUpload.push({
								index: appData.currentIndex,
								stuffName: appData
									.stStuffName,
								img: appData.selectImg,
								status: 0,
								method: "个人档案"
							});
							//alert(appData.isUpload);
							$timeout(function() {
								$state.go('materialList');
							}, 100);
						},
						error: function(err) {
							layer.msg("上传失败");
							console.log(err);
						}
					});
				} catch (e) {
					layer.msg($.getConfigMsg.preUrlSelf +
						'/selfapi/uploadItemStuffs.do' + "接口异常");
				}
			})
		} catch (e) {
			layer.msg(appData.selectImgBase64URLEnd + "返回base64接口异常");
		}
		// $.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		$.getConfigMsg.preUrl +
		// 	$scope.waitUploadImgUrl,
		// 	"C:\\waitUploadImg.jpg",
		// 	//将选中图片下载
		// 	function(bytesCopied, totalBytes) {
		// 		console.log(bytesCopied + "," + totalBytes);
		// 	},
		// 	function(result) {
		// 		//将选中图片上传到服务器
		// 		$.device.httpUpload($.getConfigMsg.preUrlSelf + '/selfapi/uploadItemStuffs.do', "FileData", "C:/waitUploadImg.jpg",
		// 			$scope.jsonData,
		// 			function(result) {
		// 				layer.msg("上传成功");
		// 				//						if(appData.isUpload[appData.currentIndex].length > 0) {
		// 				//							appData.isUpload[appData.currentIndex] = "";
		// 				//						}
		// 				appData.isUpload.push({
		// 					index: appData.currentIndex,
		// 					stuffName: appData.stStuffName,
		// 					img: appData.selectImg,
		// 					status: 0,
		// 					method: "个人档案"
		// 				});
		// 				//alert(appData.isUpload);
		// 				$timeout(function() {
		// 					$state.go('materialList');
		// 				}, 100);
		// 			},
		// 			function(webexception) {
		// 				layer.msg("上传失败");
		// 			});
		// 	},
		// 	function(webexception) {
		// 		alert("下载文档失败");
		// 	}
		// );
	};

});
app.controller("uFileUpload", function($scope, $http, $state, $rootScope, appData, $timeout, $routeParams, appFactory) {
	var name = appData.itemName;
	$scope.itemName = name;
	$scope.prevText = "返回";
	$scope.nextText = "确认上传";
	try {
		$.device.fileOpen(function(value) {
			$timeout(function() {
				$scope.UData = value; //"E://123.txt";//
			}, 100)
		});
	} catch (e) {
		$timeout(function() {
			layer.msg("请插入U盘后操作");
		}, 100)
		$state.go("uploadMethod");
	}
	// 上传
	$scope.highCapture = function() {
		if ($scope.UData == null) {
			layer.msg("未获取到U盘数据");
		} else {
			$scope.jsonData1 = {
				applyNo: appData.applyNo,
				stuffCode: appData.stuffCode,
				stuffId: "",
			};
			$scope.jsonData1 = JSON.stringify($scope.jsonData1);
			$.device.httpUpload($.getConfigMsg.preUrlSelf + "/selfapi/uploadItemStuffs.do", "FileData",
				$scope.UData,
				$scope.jsonData1,
				function(result) {
					layer.msg("上传成功");
					appData.fileName.push($scope.UData);
					if (appData.isUpload[appData.currentIndex].length > 0) {
						appData.isUpload[appData.currentIndex] = "";
					}
					appData.isUpload[appData.currentIndex] = {
						index: appData.currentIndex,
						stuffName: appData.stStuffName,
						img: scanImg,
						status: 0,
						method: "U盘上传"
					};
					$timeout(function() {
						$state.go('materialList');
					}, 100);
				},
				function(webexception) {
					layer.msg("上传失败");
				});
		}
	};
	$scope.prevStep = function() {
		$state.go('materialList');
	}
});
app.controller("takePhoto", function($scope, $http, $state, $rootScope, appData, $timeout, appFactory) {
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();
	$scope.funName = appData.funName;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$.device.cmCaptureHide(); // 关闭高拍仪
		$state.go('materialList');
	}
	$scope.finish = [];
	$scope.prevText = "返回";
	$scope.nextText = "确认上传";
	$scope.isLoading = true;
	$.device.cmCaptureShow(680, 530, 190, 300);
	$.device.cmCaptureSelectRect(0, 0, 1920, 1920);
	$scope.isFinish = false;
	// 拍照
	var imgHTML = "";
	var imgIndex = 0;
	var scanImg1 = "";
	var scanImg = '';
	$scope.next = function() {
		$scope.isLoading = false;
		if (jQuery.getConfigMsg.ishttpUploadYH) {
			$.device.cmCaptureCaptureBase64(function(Base64Value) {
				scanImg1 = Base64Value;
				if (jQuery.getConfigMsg.ishttpUploadYH && scanImg1 != '') {
					$.ajax({
						url: $.getConfigMsg.preUrlSelf + '/selfapi/uploadItemStuffs.do',
						type: "post",
						dataType: "json",
						data: {
							applyNo: appData.applyNo,
							stuffCode: appData.stuffCode,
							stuffId: "",
							"FileData": scanImg1
						},
						success: function(result) {
							$scope.isLoading = true;
							appData.uploadStuffId = result
								.stuffId; //dataJson.appData.stuffId  ;
							appData.imgStr = 'data:image/png;base64,' + scanImg1;
							//		appData.imgId = appData.imgId + "," + dataJson.rtnData.imgid;
							if (appData.isUpload[appData.currentIndex]) {
								appData.isUpload[appData.currentIndex] = "";
							}
							$scope.finish.push({
								index: appData.currentIndex,
								stuffName: appData.stStuffName,
								img: appData.imgStr,
								status: 0,
								method: "高拍仪"
							});
							imgHTML += '<div class="img" id="' + appData.uploadStuffId +
								'"><img src="' + appData.imgStr +
								'"/></div>';
							$('.imgBox').html(imgHTML);
							$scope.isFinish = true;
						},
						error: function(err) {
							$scope.isLoading = true;
							layer.msg("上传材料失败");
							$.device.cmCaptureHide(); // 关闭高拍仪
							$state.go("materialList");
						}
					});
				}
			});
			$.device.cmCaptureCaptureUrl(function(UrlValue) {
				scanImg = UrlValue;
			});
		} else {
			$.device.cmCaptureCaptureBase64(function(Base64Value) {
				scanImg1 = Base64Value;
			});
			$.device.cmCaptureCaptureUrl(function(UrlValue) {
				scanImg = UrlValue;
				if (scanImg == '') {
					$scope.isAlert = true;
					$scope.msg = "请聚焦并对准材料后再拍照";
					$scope.alertConfirm = function() {
						$scope.isAlert = false;
					}
				} else {
					$scope.jsonData1 = {
						applyNo: appData.applyNo,
						stuffCode: appData.stuffCode,
						stuffId: "",
					};
					$scope.jsonData1 = JSON.stringify($scope.jsonData1);
					$.device.httpUpload($.getConfigMsg.preUrlSelf + "/selfapi/uploadItemStuffs.do",
						"FileData", scanImg,
						$scope.jsonData1,
						function(result) {
							$scope.isLoading = true;
							appData.uploadStuffId = result
								.stuffId; //dataJson.appData.stuffId  ;
							appData.imgStr = scanImg1;
							//		appData.imgId = appData.imgId + "," + dataJson.rtnData.imgid;
							if (appData.isUpload[appData.currentIndex]) {
								appData.isUpload[appData.currentIndex] = "";
							}
							$scope.finish.push({
								index: appData.currentIndex,
								stuffName: appData.stStuffName,
								img: scanImg,
								status: 0,
								method: "高拍仪"
							});
							imgHTML += '<div class="img" id="' + appData.uploadStuffId +
								'"><img src="' + scanImg +
								'"/></div>';
							$('.imgBox').html(imgHTML);
							$scope.isFinish = true;
						},
						function(webexception) {
							$scope.isLoading = true;
							layer.msg("上传材料失败");
							$state.go("materialList");
						});
				}
			});
		}
		//		var scanImg = $.device.cmCaptureCaptureUrl();
		//		scanImg1 = $.device.cmCaptureCaptureBase64();
	};
	//取下标
	$scope.indexVf = function(array, str) {
		for (var i = 0; i < array.length; i++) {
			if (array[i] = str) {
				return i;
			}
		}
	}
	// 完成拍照
	$scope.finishUpload = function() {
		for (var i = 0; i < appData.isUpload.length; i++) {
			if (appData.currentIndex == appData.isUpload[i].index) {
				appData.isUpload[i] = "";
			}
		}
		for (var i = 0; i < $scope.finish.length; i++) {
			appData.isUpload.push($scope.finish[i]);
		}
		$(".next").attr("disabled", true);
		$.device.cmCaptureHide(); // 关闭高拍仪
		$state.go("materialList");
	};

	$scope.last = function() {
		$.device.cmCaptureHide(); // 关闭高拍仪
		$state.go("materialList");
	}
});
app.controller("materialList", function($scope, $state, $http, appData, $timeout, appFactory) {
	$scope.nextText = "提交";
	$scope.funName = appData.funName;
	//必传材料列表
	$scope.mustUpload = [];
	$scope.current = 0;

	//设置上传文件 按钮变化
	$scope.btn = function() {
		// 获取材料列表
		let fConfig = {
			itemCode: appData.itemCode, //"0105128001", //
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp($.getConfigMsg.preUrlSelf + '/selfapi/getItemStuffs.do', {
			params: fConfig
		}).success(function(dataJson) {
			$scope.stuffList = dataJson.data.stuffs;
			for (var s = 0; i < $scope.stuffList.length; i++) {
				if ($scope.stuffList[i].isMust == 1) {
					$scope.mustUpload.push({
						index: i,
						stuffName: $scope.stuffList[i].stuffName
					});
				}
			}

			if (appData.listImg == 0) {
				for (var i = 0; i < $scope.stuffList.length; i++) {
					appData.listImg[i] = {
						'isMust': ($scope.stuffList[i].isMust == "1") ? true : false,
						'index': i,
						'stuffName': $scope.stuffList[i].stuffName,
						'stuffCode': $scope.stuffList[i].stuffCode,
						'upload': true,
						'upload2': false,
						'upload3': false,
					}
				}
			}
			console.log(appData.isUpload);
			console.log(appData.listImg);
			if (appData.isUpload != "") {
				for (var i = 0; i < appData.isUpload.length; i++) {
					if (appData.isUpload[i] != "") {
						for (var j = 0; j < appData.listImg.length; j++) {
							if (appData.isUpload[i].status == 1) {
								if (appData.listImg[j].upload != false) {
									if (appData.isUpload[i].stuffName == appData.listImg[j]
										.stuffName) {
										appData.listImg[j].upload = false;
										appData.listImg[j].upload2 = true;
									}
								}
							} else if (appData.isUpload[i].status == 0) {
								console.log(appData.isUpload[i]);
								console.log(appData.listImg[j]);
								if (appData.isUpload[i].stuffName == appData.listImg[j].stuffName) {
									console.log(appData.listImg[j]);
									appData.listImg[j].upload = false;
									appData.listImg[j].upload2 = false;
									appData.listImg[j].upload3 = true;
								}
							}
						}
					}
				}
			}
			console.info(appData.listImg);
			$scope.listImg = appData.listImg;
		}).error(function(err) {
			console.log(err);
		})
	}
	$scope.btn();
	console.log(appData.isUpload);

	// 材料上传
	$scope.toUploadMaterial = function(code, name, index) {
		appData.stuffCode = code;
		appData.stStuffName = name;
		appData.currentIndex = index;
		console.log(appData.currentIndex);
		appData.stuffImg = appData.listImg[appData.currentIndex];
		$state.go("uploadMethod");
	}
	//查看
	$scope.view = function(index) {
		//		appData.currentIndex = 0;
		appData.currentIndex = index;
		appData.view = appData.isUpload;
		$state.go("materialView");
	}

	$scope.prevStep = function() {
		$state.go("info");
	}
	//提交办件
	$scope.submit = function() {
		$state.go("infoFinish");
	};

});
//材料显示
app.controller("materialView", function($scope, $state, $http, appData, appFactory) {
	$scope.stuffList = []; //所有图片的容器
	$scope.showImgList = []; //显示图片的容器
	$scope.currentPage = 1; //当前页
	$scope.totalPages = 1; //总页数
	$scope.funName = appData.funName;
	//当页显示图片
	$scope.currentList = function(current) {
		if (current === undefined) {
			current = $scope.currentPage;
		}
		$("#jq22 img").remove();
		$scope.currentImgIndex = null;
		$scope.startPos = (current - 1) * 3; //0 -3  3 - 6
		$scope.endPos = $scope.startPos + 3;
		console.log($scope.stuffList);
		$scope.showImgList = $scope.stuffList.slice($scope.startPos, $scope.endPos);
		console.log($scope.showImgList);
		$scope.totalPages = Math.ceil($scope.stuffList.length / 3);
		for (var i in $scope.showImgList) {
			$("#jq22").append('<img data-original="' + $scope.showImgList[i].img + '" src="' + $scope
				.showImgList[i].img + '" alt="">');
		}
		//图片显示
		var viewer = new Viewer(document.getElementById('jq22'), {
			url: 'data-original',
			//						toolbar:false,
			//						button:false
		});
	}

	$scope.$watch('$viewContentLoaded', function(newValue, oldValue) {
		for (var i = 0; i < appData.view.length; i++) {
			if (appData.currentIndex == appData.view[i].index) {
				$scope.stuffList.push(appData.view[i]);
				$scope.currentList();
			}
		}
		console.log($scope.stuffList);
		if ($scope.stuffList[0].method === "高拍仪") {
			$scope.scanShow = true;
			$scope.upanShow = false;
		} else if ($scope.stuffList[0].method === "U盘上传") {
			$scope.scanShow = false;
			$scope.upanShow = true;
		} else if ($scope.stuffList[0].method === "个人档案") {
			$scope.scanShow = true;
			$scope.upanShow = false;
		}
	});
	//下一页
	$scope.nextPage = function() {
		if ($scope.currentPage < $scope.totalPages) {
			++$scope.currentPage;
			$scope.currentList();
		}
	};
	//上一页
	$scope.prevPage = function() {
		if ($scope.currentPage > 1) {
			--$scope.currentPage;
			$scope.currentList();
			console.log($scope.showImgList);
		}
	};

	$scope.prev = function() {
		$state.go("materialList");
	}

	//打开文件
	$scope.open = function() {
		try {
			$.device.officeOpen($scope.stuffList[0].fileName);
		} catch (e) {
			layer.msg("未找到此文件");
		}
	}
});
app.controller('infoFinish', function($state, $scope, appData, $http, $interval, $timeout) {
	$scope.funName = appData.funName;
	$scope.itemName = appData.funName;
	$scope.applyNo = appData.applyNo;
	$scope.nextText = "返回首页";
	$scope.submit = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/submitItem.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				applyNo: appData.applyNo
			},
			success: function(dataJson) {
				layer.msg("提交成功");
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	$scope.submit();
	$scope.goHome = function() {
		$state.go("main");
	}
});
//查看
app.controller('preview', function($state, $scope, appData, $http, $interval, $timeout) {
	$scope.funName = appData.funName;
	$scope.configUrl = $.getConfigMsg.preUrlSelf;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.nextText = "打印";
	$scope.isLoading = true;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//查询证照
	$scope.showStuffPicForBytes = function(applyNo) {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/DZCert/getCertByApplyNo.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				machineId: $.config.get("uniqueId") || "",
				itemName: encodeURI(appData.funName),
				itemCode: appData.itemCode,
				businessCode: applyNo
			},
			success: function(dataJson) {
				console.log(dataJson);
				$scope.isLoading = false;
				if (dataJson.SUCCESS == "TRUE") {
					$scope.previewImg = $scope.configUrl + dataJson.PNGURL;
					appData.pdfFile = $scope.configUrl + dataJson.PDFURL;
				} else if (dataJson.SUCCESS == "FALSE") {
					$scope.isAlert = true;
					$scope.msg = "<p>暂未查到您的证明。</p><p>请确认是否已申请,申请成功后一般在收到申请之日起2个工作日内开具证明</p>";
					$scope.alertConfirm = function() {
						$state.go("main");
					}
				}
			},
			error: function(err) {
				console.log("showStuffPicForBytes err");
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "未查询到您办理的户籍证明，请重试";
				$scope.alertConfirm = function() {
					$state.go("loginType");
				}
			}
		});
	}
	//查询户籍证明办件的记录
	$scope.queryApplyInfoOfCriminalRecord = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/selfDeclare/queryApplyInfo.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				idCard: appData.licenseNumber,
				item: encodeURI(appData.funName)
			},
			success: function(dataJson) {
				console.log(dataJson);
				$scope.isLoading = false;
				if (dataJson.SUCCESS == "true") {
					appData.applyNo = dataJson.applyNo;
					$scope.showStuffPicForBytes(dataJson.applyNo);
				} else if (dataJson.SUCCESS == "false") {
					$scope.isAlert = true;
					$scope.msg = dataJson.MSG;
					$scope.alertConfirm = function() {
						$state.go("loginType");
					}
				}
			},
			error: function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "未查询到您办理的户籍证明记录，请重试";
				$scope.alertConfirm = function() {
					$state.go("loginType");
				}
			}
		});
	}
	$scope.queryApplyInfoOfCriminalRecord();
	//图片预览
	$scope.isAllScreen = false;
	$scope.isPrint = false;
	$scope.view = true;
	//图片显示
	var viewer = new Viewer(document.getElementById('jq22'), {
		url: 'data-original',
		toolbar: false,
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
	$scope.prevStep = function() {
		$state.go("loginType");
	}
	//打印
	$scope.nextStep = function() {
		console.log(appData.pdfFile);
		$scope.isPrint = "show";
		$scope.path = "D:\\pdfPrint.pdf";
		$scope.filePath = "D:/pdfPrint.pdf";
		$.device.urlPdfPrint($scope.pdfPrint, $scope.path, function() {
			saveDeviceStatus("A4Printer", 0, "正常", 0, 0, 0, 1);
			//模块使用记录
			$scope.jsonStr = {
				SUCCESS: "true",
				data: {
					name: appData.funName,
					applyNo: appData.applyNo,
				}
			}
			recordUsingHistory('公安服务', '查询+打印', appData.funName, appData.licenseName, appData
				.licenseNumber, "", appData.applyNo, JSON.stringify($scope.jsonStr));
			//行为分析(查询)
			trackEventForQuery(appData.funName, appData.applyNo, "打印", "上海市公安局", appData
				.licenseName, appData.licenseNumber, "");
		});
		//		$.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		$.getConfigMsg.preUrl +
		//			appData.pdfFile,
		//			$scope.path,
		//			//将选中图片下载
		//			function(bytesCopied, totalBytes) {
		//				console.log(bytesCopied + "," + totalBytes);
		//			},
		//			function(result) {
		//				$.device.pdfPrint($scope.filePath);
		//				saveDeviceStatus("A4Printer", 0, "正常", 0, 0, 0, 1);
		//				//模块使用记录
		//				$scope.jsonStr = {
		//					SUCCESS: "true",
		//					data: {
		//						name: appData.funName,
		//						applyNo: appData.applyNo,
		//					}
		//				}
		//				recordUsingHistory('公安服务', '查询+打印', appData.funName, appData.licenseName, appData.licenseNumber, "", appData.applyNo, JSON.stringify($scope.jsonStr));
		//				//行为分析(查询)
		//				trackEventForQuery(appData.funName, appData.applyNo, "打印", "上海市公安局", appData.licenseName, appData.licenseNumber, "");
		//			},
		//			function(webexception) {
		//				alert("下载文档失败");
		//			}
		//		);
		$timeout(function() {
			window.location.href = "../publicSecurity/index.html"
		}, 3000)
	}
})
