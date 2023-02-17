function removeAnimate(ele) {
	$(ele).css({
		"transform": "translateY(0px)",
		"top": 0
	}).removeClass('transformto')
}

function addAnimate(ele) {
	$(ele).addClass('transformto');
}

app.controller('main', function($state, $scope, appData) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	appData.funName = $scope.funName = "烟草专卖零售许可证";
	appData.itemCode = "0105632000";
	$scope.operation = "请选择登录方式";
	appData.xsbb = false;
	$scope.choiceType = function(type) {
		appData.itemType = type;
		appData.funName = '烟草专卖零售许可证' + (type == 'TY' ? '停业' : '恢复营业');
		appData.itemSign = (type == 'TY' ? '04' : '05')
		$state.go("guideline");
	}
});

app.controller('guideline', function($state, $scope, appData) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	console.log(appData.funName)
	$scope.nextStep = function() {
		$state.go("loginType");
	}
	$scope.prevStep = function() {
		$state.go("main");
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
});
app.controller('loginType', function($state, $scope, appData) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.operation = "请选择登录方式";
	appData.xsbb = false;
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

	//test 跳过核验
//		appData.licenseNumber = "341181198209046229";
//		appData.licenseName = "厉俊毅";
//		$state.go('info');

	//跳转页面
	$scope.nextStep = function() {
		$scope.tokenType = "token";
		$scope.token = function() {
			$state.go('info');
		}
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
		$scope.nextStep();
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
})
app.controller('info', function($state, $scope, appData, appFactory, $http) {
	$scope.isLoding = true;
	$scope.concel = 'false';
	$scope.nextText = "提交";
	$scope.itemType = appData.itemType;
	appData.isUpload = [];
	appData.listImg = [];
	$scope.funName = appData.funName;
	$(".form_datetime").datetimepicker({
		format: "yyyy-mm-dd", //显示日期格式
		autoclose: true,
		todayBtn: true,
		minView: "month", //只选择到天自动关闭
		language: 'zh-CN',
		pickerPosition: 'bottom-left',
	});
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}

	//烟草专卖零售许可证基本信息查询
	$scope.getBookingLicenceinfo = function() {
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/tobacco/getBookingLicenceinfo.do",
			dataType: 'json',
			data: {
				idCard: appData.licenseNumber,
				applytype: appData.itemSign
			},
			success: function(res) {
				console.log(res);
				if(res != undefined && res != null && res != "") {
					if(res.resultstr == 1 && res.resultlist.length > 0) {
						$scope.resultList = res.resultlist;
					} else {
						$scope.isAlert = true;
						$scope.msg = "未查询您的烟草零售许可证，暂不可办理"
						$scope.alertConfirm = function() {
							$state.go('main')
						}
					}
				} else {
					$scope.isAlert = true;
					$scope.msg = "未查询您的烟草零售许可证，暂不可办理"
					$scope.alertConfirm = function() {
						$state.go('main')
					}
				}
				$scope.isLoading = false;
			},
			error: function(err) {
				$scope.isLoading = false;
			}
		});
	}

	$scope.$on("$viewContentLoaded", function(event, toState, toParams, fromState, fromParams) {
		$scope.isLoading = true;
		$scope.getBookingLicenceinfo();
	});

	$scope.$watch('permitKey', function(val) {
		if(val) {
			$scope.baseInfo = val;
			$scope.REGIELICENCENO = $scope.baseInfo.REGIELICENCENO;
			$scope.permitValidity = $scope.baseInfo.REGIEUSEFULDATE;
			$scope.licenseNo = $scope.baseInfo.SOCIAL_CREDIT_CODE;
			$scope.licenseValidity = $scope.baseInfo.CORPREGTIME;
			$scope.corporateName = $scope.baseInfo.CUSTOMERNAME;
			$scope.corporateAddress = $scope.baseInfo.CUSTOMERPLACE;
			$scope.legalName = $scope.baseInfo.PIC_NAME;
			$scope.idType = $scope.baseInfo.PIC_CARDCODE_NAME;
			$scope.idCard = $scope.baseInfo.PIC_CARDNUM;
			$scope.linkName = $scope.baseInfo.CONTACTER;
			$scope.linkPhone = $scope.baseInfo.CONTACTER_PHONE;
			$scope.startDate = $scope.baseInfo.CLOSEDATE_FROM;
			$scope.endDate = $scope.baseInfo.CLOSEDATE_END;
			if(appData.itemType == "TY") {
				$scope.taskHandleItem = filterByInfo(areaList, $scope.baseInfo.DEPT_NAME).itemCodeTY
			} else if(appData.itemType == "HFYY") {
				$scope.taskHandleItem = filterByInfo(areaList, $scope.baseInfo.DEPT_NAME).itemCodeHFYY
			}
			console.log($scope.taskHandleItem);
		}
	})

	// 保存数据
	$scope.flag = true;
	$scope.nextStep = function() {
		if(!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			if(isBlank($scope.permitKey)) {
				$scope.isAlert = true;
				$scope.msg = "请选择许可证号！";
				return;
			}
			if(isBlank($scope.licenseValidity)) {
				$scope.isAlert = true;
				$scope.msg = "请输入营业执照有效期！";
				return;
			}
			if(isBlank($scope.linkName)) {
				$scope.isAlert = true;
				$scope.msg = "请输入业务联系人！";
				return;
			}
			if(isBlank($scope.linkPhone)) {
				$scope.isAlert = true;
				$scope.msg = "请输入联系电话！";
				return;
			}
			if($scope.itemType == 'HFYY') {
				if(isBlank($('#applyData').val())) {
					$scope.isAlert = true;
					$scope.msg = "请选择申请恢复营业日期！";
					return;
				}
			} else if($scope.itemType == 'TY') {
				if(isBlank($('#startDate').val())) {
					$scope.isAlert = true;
					$scope.msg = "请选择原申请停业期限起始日！";
					return;
				}
				if(isBlank($('#endDate').val())) {
					$scope.isAlert = true;
					$scope.msg = "请选择原申请停业期限截止日！";
					return;
				}
			}
		} while (condFlag);
		$scope.infoParams = {
			regieusefuldate: $scope.permitValidity,
			social_credit_code: $scope.licenseNo,
			corpregtime: $scope.licenseValidity,
			customername: $scope.corporateName,
			customerplace: $scope.corporateAddress,
			pic_name: $scope.legalName,
			pic_cardcode_code: $scope.baseInfo.PIC_CARDCODE_CODE,
			pic_cardnum: $scope.idCard,
			contacter: $scope.linkName,
			contacter_phone: $scope.linkPhone,
			closedate_from: $('#startDate').val() || $scope.startDate || "",
			closedate_end: $('#endDate').val() || $scope.endDate || "",
			li13_applydate: $('#applyData').val() || "",
			regielicenceno: $scope.REGIELICENCENO,
			pdchinasoftxzsp: '6',
			applytype: appData.itemSign,
		};
		console.log($scope.infoParams);
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/deal/work/saveApplyInfo.do",
			dataType: 'json',
			data: {
				"itemCode": appData.itemCode,
				"taskHandleItem": $scope.taskHandleItem,
				"itemName": appData.funName,
				"targetType": "企业",
				"targetName": $scope.corporateName,
				"targetNo": $scope.licenseNo,
				"userId": "",
				"username": $scope.legalName,
				"licenseType": "身份证",
				"licenseNo": $scope.idCard,
				"mobile": $scope.linkPhone,
				"departCode": "",
				"departName": "",
				"source": "网上申请",
				"content": "",
				"opTime": "",
				"districtCode": "",
				"info": JSON.stringify($scope.infoParams)
			},
			success: function(res) {
				console.log(res);
				if(res.code == '200' && res.isSuccess == true) {
					appData.applyNo = res.data.applyNo;
					appData.taskHandleItem = $scope.taskHandleItem;
					$state.go("materialList");
				}
			},
			error: function(err) {
				console.log(err);
			}

		});
		$scope.flag = false;
	};
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
				$state.go('uFileUpload');
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
app.controller("uFileUpload", function($scope, $http, $state, $rootScope, appData, $timeout, appFactory) {
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
	} catch(e) {
		$timeout(function() {
			layer.msg("请插入U盘后操作");
		}, 100)
		$state.go("uploadMethod");
	}
	// 上传
	$scope.highCapture = function() {
		if($scope.UData == null) {
			layer.msg("未获取到U盘数据");
		} else {
			$scope.jsonData1 = {
				applyNo: appData.applyNo,
				stuffCode: appData.stuffCode,
				stuffId: "",
			};
			$scope.jsonData1 = JSON.stringify($scope.jsonData1);
			$.device.httpUpload($.getConfigMsg.preUrlSelf + "/selfapi/uploadItemStuffs.do", "FileData", $scope.UData,
				$scope.jsonData1,
				function(result) {
					layer.msg("上传成功");
					appData.fileName.push($scope.UData);
					if(appData.isUpload[appData.currentIndex].length > 0) {
						appData.isUpload[appData.currentIndex] = "";
					}
					appData.isUpload[appData.currentIndex] = {
						index: appData.currentIndex,
						stuffName: appData.stStuffName,
						img: $scope.UData,
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
	$scope.next = function() {
		var scanImg = "";
		$.device.cmCaptureCaptureUrl(function(info) {
			scanImg = info;
		});
		scanImg1 = $.device.cmCaptureCaptureBase64(function(info) {
			scanImg1 = info;
		});
		if(scanImg == undefined) {
			$scope.isAlert = true;
			$scope.msg = "请聚焦并对准材料后再拍照";
			$scope.alertConfirm = function() {
				$scope.isAlert = false;
			}
		} else {
			$scope.isLoading = false;
			$scope.jsonData1 = {
				applyNo: appData.applyNo,
				stuffCode: appData.stuffCode,
				stuffId: "",
			};
			$scope.jsonData1 = JSON.stringify($scope.jsonData1);
			$.device.httpUpload($.getConfigMsg.preUrlSelf + "/selfapi/uploadItemStuffs.do", "FileData", scanImg,
				$scope.jsonData1,
				function(result) {
					$scope.isLoading = true;
					appData.uploadStuffId = result.stuffId; //dataJson.appData.stuffId  ;
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
					imgHTML += '<div class="img" id="' + appData.uploadStuffId + '"><img src="' + scanImg + '" width="150" height="200" /></div>';
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
	//必传材料列表
	$scope.mustUpload = [];
	$scope.current = 0;

	//设置上传文件 按钮变化
	$scope.btn = function() {
		// 获取材料列表
		appData.listImg = [{
			'index': 0,
			'stuffName': "身份证",
			'upload': true,
			'upload2': false,
			'upload3': false,
			"stuffCode": "MATERIAL21D073446",
			"isMust": "0"
		}];
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
	$scope.toUploadMaterial = function(code, name, index) {
		appData.stuffCode = code;
		appData.stStuffName = name;
		appData.currentIndex = index;
		console.log(appData.currentIndex);
		appData.stuffImg = appData.listImg[appData.currentIndex];
		$state.go("uploadMethod");
	}
	//查看
	$scope.view = function() {
		//		appData.currentIndex = 0;
		appData.view = appData.isUpload;
		$state.go("materialView");
	}
	$scope.prevStep = function() {
		$state.go("info");
	}
	//提交办件
	$scope.submit = function() {
		$state.go("submit");
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
		});
	}

	$scope.$watch('$viewContentLoaded', function(newValue, oldValue) {
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
	$scope.nextText = "返回首页";
	$.ajax({
		type: "post",
		url: $.getConfigMsg.preUrlSelf + "/selfapi/deal/work/submitApplyInfo.do",
		dataType: 'json',
		data: {
			applyNo: appData.applyNo
		},
		success: function(res) {
			console.log(res);
		},
		error: function(err) {}
	});
	//	//模块使用记录
	//	$scope.jsonStr = {
	//		SUCCESS: "true",
	//		data: {
	//			name: $scope.funName,
	//			Number: $scope.applyNo,
	//		}
	//	}
	//	recordUsingHistory('人社服务', '办理', $scope.funName, appData.licenseName, appData.licenseNumber, '', $scope.applyNo, JSON.stringify($scope.jsonStr));
	//	//行为分析(办理)
	//	trackEventForAffairs($scope.applyNo, $scope.funName, "上海市人力资源社会保障局", appData.licenseName, appData.licenseNumber, '');
	$scope.goHome = function() {
		$.device.GoHome();
	}
});