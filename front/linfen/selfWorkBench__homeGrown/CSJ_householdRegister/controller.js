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
app.controller('choiceMode', function($state, $scope, appData, $timeout, appFactory, $http) {
	$scope.funName = appData.funName = "户籍证明开具";
	appData.itemCode = $scope.itemCode = "0105105000-07-04";
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.choiceType = function(type) {
		if(type == "print") {
			appData.modeType = "print";
		}
		$state.go("choiceProvince");
	}
	$scope.prevStep = function() {
		window.location.href = "../CSJ_allItem/index.html";
	}
})
app.controller('choiceProvince', function($state, $scope, appData, $timeout, appFactory, $http) {
	$scope.funName = appData.funName = "长三角户籍证明";
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.choiceType = function(type) {
		appData.type = type;
		if(type == "zj" || type == "ah") {
			$scope.isAlert = true;
			$scope.msg = "该省市暂未开通户籍证明，请敬请期待！";
			$scope.alertConfirm = function() {
				$scope.isAlert = false;
			}
		} else {
			$state.go("guideline");
		}
	}
	$scope.prevStep = function() {
		$state.go("choiceMode");
	}
})
app.controller("guideline", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.funName = appData.funName;
	$scope.prevStep = function() {
		$state.go("choiceProvince");
	}
	//sign = 1 代表从长三角进入
	$scope.nextStep = function() {
		if(appData.type == "sh") {
			if(appData.modeType == "print"){
				window.location.href = '../GA_householdRegister/index.html#/loginType?sign=1&type=print';
			}else{
				window.location.href = '../GA_householdRegister/index.html#/loginType?sign=1';
			}
		}
		$state.go('loginType');
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: true,
			bounce: true,
			click: true,
			taps: true,
			preventDefault: false,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
	addAnimate($('.main2'))
});
app.controller('loginType', function($state, $scope, appData) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.operation = "请选择登录方式";
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("guideline");
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
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
	}

	//跳转页面
	$scope.nextStep = function() {
		$scope.tokenType = "token";
		$scope.token = function() {
			$state.go('infoJS');
		}
	}

//	//test 跳过核验
//	$scope.idcardLogin = function() {
//		//		$state.go('infoJS');
//		$scope.nextStep();
//	}
//	$scope.idcardLogin();

	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
			appData.VALIDENDDAY = info.ValidtermOfEnd;
			appData.VALIDSTARTDAY = info.ValidtermOfStart;
			appData.Address = info.Address;
			appData.nation = info.People;
			if(appData.nation.lastIndexOf('族') < 0) {
				appData.nation = appData.nation + '族';
			}
		} else {
			layer.msg("没有获取到")
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		$state.go('infoJS');
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
			$scope.nextStep();
		} else {
			var idcardInfo = info.result.data;
			appData.licenseName = idcardInfo.realname;
			appData.VALIDENDDAY = idcardInfo.VALIDENDDAY;
			appData.VALIDSTARTDAY = idcardInfo.VALIDSTARTDAY;
			$scope.nextStep();
		}
	}
})
app.controller('infoJS', function($state, $scope, $rootScope, appData, $timeout, appFactory, $http) {
	$scope.operation = "请选择查询条件";
	appData.isUpload = []; //已上传材料
	appData.listImg = []; //需上传材料
	appData.fileName = [];
	appData.uploadStuffId = []; //材料id
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
	$scope.kjfwTypeList = kjfwType;
	$scope.expressModeList = expressMode;
	$scope.change1 = function(index, item) {
		$scope.current1 = index;
		$scope.expressMode = item;
	}
	$scope.change = function(index, item) {
		$scope.current = index;
		$scope.kjfw = item;
	}

	//获取用户id
	$scope.getUserInfoByAccessToken = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getUserInfoByAccessToken.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				accessToken: appData.token1
			},
			success: function(dataJson) {
				console.log(dataJson);
				if(dataJson.zwdtsw_user_id != undefined && dataJson.zwdtsw_user_id != null && dataJson.zwdtsw_user_id != "") {
					appData.zwdtsw_user_id = dataJson.zwdtsw_user_id;
				} else {
					$scope.isAlert = true;
					$scope.msg = dataJson.ERROR || '未获得对应的用户标识！';
					$scope.alertConfirm = function() {
						$state.go("loginType");
					}
				}
			},
			error: function(err) {
				console.log("getUserInfoByAccessToken err");
				$scope.isAlert = true;
				$scope.msg = '未获得对应的用户标识！';
				$scope.alertConfirm = function() {
					$state.go("loginType");
				}
			}
		});
	}
	//获取token ------2、比对成功后，根据tokenSNO获取access_token
	$scope.getAccessToken = function(tokenSNO) {
		var rec = $.ajax({
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getAccessToken.do",
			type: "post",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			timeout: 5000,
			data: {
				tokenSNO: tokenSNO,
			},
			success: function(res) {
				console.log(res);
				if(res.SUCCESS === true) {
					appData.token1 = res.accessToken;
					$scope.getUserInfoByAccessToken();
				} else {
					$scope.isAlert = true;
					$scope.msg = '未获得对应的用户标识！';
					$scope.alertConfirm = function() {
						$state.go("loginType");
					}
				}
			},
			error: function(err) {
				console.log(err);
			},
		})
	}
	if(appData.zwdtsw_user_id) {} else {
		$scope.getAccessToken($rootScope.tokenSNO);
	}

	//监听日期控件 变化
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(!isPhoneAvailable($scope.stMobile)) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的手机号";
				return
			}
			if(($scope.current1 == 1) && isBlank($scope.stReceiptName)) {
				$scope.isAlert = true;
				$scope.msg = "请输入收件人姓名";
				return
			}
			if(($scope.current1 == 1) && isBlank($scope.stReceiptAddress)) {
				$scope.isAlert = true;
				$scope.msg = "请输入收件人联系地址";
				return
			}
			if(($scope.current1 == 1) && isBlank($scope.stReceiptMobile)) {
				$scope.isAlert = true;
				$scope.msg = "请输入收件人联系电话";
				return
			}
			if(isBlank($scope.expressMode.name)) {
				$scope.isAlert = true;
				$scope.msg = "请选择领取方式";
				return
			}
			if(isBlank($scope.kjfw.name)) {
				$scope.isAlert = true;
				$scope.msg = "请选择开具范围";
				return
			}
			if(isBlank($('#stReason').val())) {
				$scope.isAlert = true;
				$scope.msg = "请填写申请事由";
				return
			}
		} while (condFlag);
		//提交参数集合
		condFlag = true;
		appData.mobile = $scope.stMobile;
		appData.paramStr = {
			data: {
				accessToken: $scope.token,
				departCode: appData.bldCode,
				source: "",
				itemCode: appData.itemCode,
				info: {
					licenseType: {
						"name": "身份证",
						"value": "a"
					},
					licenseNo: appData.licenseNumber,
					username: appData.licenseName,
					mobile: $scope.stMobile,
					ga1260371622: appData.uploadStuffId,
					userType: "个人",
					kjfw: {
						value: $scope.kjfw.id,
						name: $scope.kjfw.name
					},
					lqfs: {
						value: $scope.expressMode.id,
						name: $scope.expressMode.name
					},
					blss: {
						"name": "江苏省",
						"value": "3"
					},
					sjrUsername: $scope.stReceiptName,
					sjrAddress: $scope.stReceiptAddress,
					sjrMobile: $scope.stReceiptMobile,
					content: $('#stReason').val()
				}
			}
		}
		//上传材料
		//		appFactory.upload_file($scope.licenseNumber, appData.licenseName, appData.VALIDENDDAY, appData.VALIDSTARTDAY, '310105109000100', appData.applyNo, function(dataJson) {
		//			if(dataJson == "" || dataJson == null || dataJson == undefined) {
		//				layer.msg("未能从电子证照获取到身份证照上传");
		//				$timeout(function() {
		//					$state.go("materialList");
		//				}, 100)
		//			}
		//			appData.imgStr = $scope.imgUrl = "data:image/jpeg;base64," + dataJson.data[0].str;
		//		}, function(dataJson1) {
		//			if(dataJson1.isSuccess == true) {
		//				try {
		//					if(appData.isUpload.length < 1) {
		//						appData.isUpload.push({
		//							index: 0,
		//							stuffName: "《居民身份证》",
		//							img: $scope.imgUrl,
		//							status: 1,
		//							method: "高拍仪"
		//						});
		//					}
		//				} catch(e) {}
		//			} else {
		//				layer.msg("未能从电子证照获取到身份证照上传");
		//			}
		//			$timeout(function() {
		//				$state.go("materialList");
		//			}, 100)
		//		});
		//办件信息同步接口
		$state.go('materialList');
	}
});
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
	$scope.profileShow = function() {
		$scope.isLoading = false;
		$.ajax({
			url: $.getConfigMsg.preUrlSelf + "/selfapi/dzzz/queryCertBaseData.do",
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				jsonpCallback: "JSON_CALLBACK",
				certNo: appData.encrypt_identity || appData.licenseNumber,
				name: encodeURI(appData.licenseName),
				type: 0,
				machineId: $.config.get("uniqueId") || "",
				itemName: encodeURI(appData.funName),
				itemCode: "0105105000-07-04",
				businessCode: "",
				startDay: appData.VALIDSTARTDAY,
				endDay: appData.VALIDENDDAY,
			},
			success: function(json) {
				$scope.isLoading = true;
				var dataJson = eval("(" + JSON.stringify(json.data) + ")");
				if(!dataJson) { // !dataJson[0].address
					layer.msg("没有数据，请重新选择上传方式!");
					$timeout(function() {
						$state.go('uploadMethod');
					}, 1000);
				} else {
					$scope.imgUrls = dataJson;
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
		$.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		$.getConfigMsg.preUrl +
			$scope.waitUploadImgUrl,
			"C:\\waitUploadImg.jpg",
			//将选中图片下载
			function(bytesCopied, totalBytes) {
				console.log(bytesCopied + "," + totalBytes);
			},
			function(result) {
				//将选中图片上传到服务器
				$.device.httpUpload($.getConfigMsg.preUrlSelf + '/selfapi/selfDeclareForCSJ/uploadStuffs.do', "FileData", "C:/waitUploadImg.jpg",
					$scope.jsonData,
					function(result) {
						layer.msg("上传成功");
						if(appData.isUpload[appData.currentIndex].length > 0) {
							appData.isUpload[appData.currentIndex] = "";
						}
						appData.isUpload[appData.currentIndex] = {
							index: appData.currentIndex,
							stuffName: appData.stStuffName,
							img: scanImg,
							status: 0,
							method: "个人档案"
						};
						$timeout(function() {
							$state.go('materialList');
						}, 1000);
					},
					function(webexception) {
						layer.msg("上传失败");
					});
			},
			function(webexception) {
				alert("下载文档失败");
			}
		);
	};

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
	$scope.next = function() {
		$scope.isLoading = false;
		var scanImg = $.device.cmCaptureCaptureUrl();
		scanImg1 = $.device.cmCaptureCaptureBase64();
		if(scanImg == undefined) {
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
			$.device.httpUpload($.getConfigMsg.preUrlSelf + "/selfapi/selfDeclareForCSJ/uploadStuffs.do", "FileData", scanImg,
				$scope.jsonData1,
				function(result) {
					$scope.isLoading = true;
					appData.uploadStuffId.push(result.stuffId); //dataJson.appData.stuffId  ;
					appData.imgStr = scanImg1;
					//		appData.imgId = appData.imgId + "," + dataJson.rtnData.imgid;
					if(appData.isUpload[appData.currentIndex]) {
						appData.isUpload[appData.currentIndex] = "";
					}
					$scope.finish.push({
						index: appData.currentIndex,
						stuffName: appData.stStuffName,
						img: scanImg,
						status: 0,
						method: "高拍仪"
					});
					imgHTML += '<div class="img" id="' + result.stuffId + '"><img src="' + scanImg + '" width="150" height="200" /></div>';
					$('.imgBox').html(imgHTML);
					$scope.isFinish = true;
				},
				function(webexception) {
					$scope.isLoading = true;
					layer.msg("上传材料失败");
					$state.go("materialList");
				});
		}
	};
	//取下标
	$scope.indexVf = function(array, str) {
		for(var i = 0; i < array.length; i++) {
			if(array[i] = str) {
				return i;
			}
		}
	}
	// 完成拍照
	$scope.finishUpload = function() {
		for(var i = 0; i < appData.isUpload.length; i++) {
			if(appData.currentIndex == appData.isUpload[i].index) {
				appData.isUpload[i] = "";
			}
		}
		for(var i = 0; i < $scope.finish.length; i++) {
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
	$scope.isAlert = false;
	$scope.concel = "false";
	//必传材料列表
	appData.currentIndex = 0;
	$scope.mustUpload = [];
	$scope.current = 0;

	//设置上传文件 按钮变化
	$scope.btn = function() {
		// 获取材料列表
		$scope.stuffList = [{
			"certs": [{
				"certName": "居民身份证",
				"certCode": "310105109000100"
			}],
			"stDesc": "",
			"stuffName": "《居民身份证》",
			"isMust": "1",
			"stuffCode": "ga1260371622"
		}];
		for(var s = 0; i < $scope.stuffList.length; i++) {
			if($scope.stuffList[i].isMust == 1) {
				$scope.mustUpload.push({
					index: i,
					stuffName: $scope.stuffList[i].stuffName
				});
			}
		}

		if(appData.listImg == 0) {
			for(var i = 0; i < $scope.stuffList.length; i++) {
				appData.listImg[i] = {
					'isMust': ($scope.stuffList[i].isMust == "1") ? true : false,
					'index': i,
					'stuffName': $scope.stuffList[i].stuffName,
					'upload': true,
					'upload2': false,
					'upload3': false,
				}
			}
		}
		console.log(appData.isUpload);
		console.log(appData.listImg);
		if(appData.isUpload != "") {
			for(var i = 0; i < appData.isUpload.length; i++) {
				if(appData.isUpload[i] != "") {
					for(var j = 0; j < appData.listImg.length; j++) {
						if(appData.isUpload[i].status == 1) {
							if(appData.listImg[j].upload != false) {
								if(appData.isUpload[i].stuffName == appData.listImg[j].stuffName) {
									appData.listImg[j].upload = false;
									appData.listImg[j].upload2 = true;
								}
							}
						} else if(appData.isUpload[i].status == 0) {
							console.log(appData.isUpload[i]);
							console.log(appData.listImg[j]);
							if(appData.isUpload[i].stuffName == appData.listImg[j].stuffName) {
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
	}
	$scope.btn();
	console.log(appData.isUpload);

	// 材料上传
	appData.currentIndex++;
	$scope.toUploadMaterial = function(code, name, index) {
		appData.stuffCode = code;
		appData.stStuffName = name;
		appData.currentIndex = index;
		appData.stuffImg = appData.listImg[appData.currentIndex];
		$state.go("uploadMethod");
	}
	//查看
	$scope.view = function() {
		appData.currentIndex = 0;
		appData.view = appData.isUpload;
		$state.go("materialView");
	}
	$scope.prevStep = function() {
		$state.go("infoJS");
	}
	//提交办件
	$scope.submit = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/selfDeclare/saveApply.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				appName: "8b3b9e01-cfe8-49f0-a459-a55b1b53d75f",
				idCard: appData.licenseNumber,
				name: encodeURI(appData.licenseName),
				itemName: encodeURI(appData.funName),
				paramStr: encodeURI(JSON.stringify(appData.paramStr))
			},
			success: function(dataJson) {
				if(dataJson.success == true && dataJson.data.isSuccess == true) {
					appData.applyNo = dataJson.data.applyNo;
					$state.go("submit");
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
		if(current === undefined) {
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
		for(var i in $scope.showImgList) {
			$("#jq22").append('<img data-original="' + $scope.showImgList[i].img + '" src="' + $scope.showImgList[i].img + '" alt="">');
		}
		//图片显示
		var viewer = new Viewer(document.getElementById('jq22'), {
			url: 'data-original',
			//						toolbar:false,
			//						button:false
		});
	}

	$scope.$watch('$viewContentLoaded', function(newValue, oldValue) {
		console.log(appData.view);
		for(var i = 0; i < appData.view.length; i++) {
			if(appData.currentIndex == appData.view[i].index) {
				$scope.stuffList.push(appData.view[i]);
				$scope.currentList();
			}
		}
		console.log($scope.stuffList);
		if($scope.stuffList[0].method === "高拍仪") {
			$scope.scanShow = true;
			$scope.upanShow = false;
		} else if($scope.stuffList[0].method === "U盘上传") {
			$scope.scanShow = false;
			$scope.upanShow = true;
		} else if($scope.stuffList[0].method === "个人档案") {
			$scope.scanShow = true;
			$scope.upanShow = false;
		}
	});
	//下一页
	$scope.nextPage = function() {
		if($scope.currentPage < $scope.totalPages) {
			++$scope.currentPage;
			$scope.currentList();
		}
	};
	//上一页
	$scope.prevPage = function() {
		if($scope.currentPage > 1) {
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
		} catch(e) {
			layer.msg("未找到此文件");
		}
	}
});
app.controller('submit', function($state, $scope, appData) {
	$scope.funName = appData.funName;
	$scope.applyNo = appData.applyNo;
	$scope.licenseName = appData.licenseName || "邹天奇";
	$scope.date = getCurrentDate(2);
	$scope.nextText = "返回首页";
	$scope.submit = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/selfDeclareForCSJ/submitApply.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				applyNo: appData.applyNo
			},
			success: function(dataJson) {
				layer.msg("提交成功");
				//模块使用记录
				$scope.jsonStr = {
					SUCCESS: "true",
					data: {
						name: appData.funName,
						applyNo: appData.applyNo,
						mobile: appData.mobile,
					}
				}
				recordUsingHistory('长三角服务', '办理', appData.funName, appData.licenseName, appData.licenseNumber, appData.mobile, appData.applyNo, JSON.stringify($scope.jsonStr));
				//行为分析(办理)
				trackEventForAffairs(appData.applyNo, appData.funName, "长三角服务", appData.licenseName, appData.licenseNumber, appData.mobile);
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	$scope.submit();
	$scope.goHome = function() {
		$.device.GoHome();
	}
});