function removeAnimate(ele) {
	$(ele).css({
		"transform": "translateY(0px)",
		"top": 0
	}).removeClass('transformto')
}

function addAnimate(ele) {
	$(ele).addClass('transformto');
}
app.controller('guideline', function($state, $scope, appData) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	appData.funName = $scope.funName = "对建设工程征占用林地审核";
	appData.itemCode = "310100846000";
	$scope.nextStep = function() {
		$state.go("loginType");
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
app.controller('login', function($scope, $http, $state, appData, appFactory, $timeout) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.concel = "false";

		//获取法人扫码
	$scope.createFrQrCode = function() {
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/workPlatform/createFrQrCode.do",
			dataType: 'json',
			data: {},
			success: function(res) {
				console.log(res);
				if(res.code == 0) {
					$scope.codeImg = "data:image/png;base64," + res.data.qrimage;
					$scope.qrid = res.data.qrid;
					$timeout(function() {
						$.ajax({
							type: "post",
							url: $.getConfigMsg.preUrlSelf + "/selfapi/workPlatform/queryFrQrCode.do",
							dataType: 'json',
							data: {
								qrid: $scope.qrid
							},
							success: function(res) {
								console.log(res);
								if(res.code == 0) {
									appData.deptName = res.data.entname;
									appData.targetNo = res.data.uniscid;
									appData.targetName = res.data.legalName;
									$state.go('info');
								}
							},
							error: function(err) {}
						});
					}, 10000)
				}
			},
			error: function(err) {}
		});
	}

	switch($scope.loginType) {
		case "ukey":
			$scope.loginBtn = true;
			$scope.operation = "ukey登录";
			break;
		case "cloud":
			$scope.operation = "电子营业执照";
			$scope.createFrQrCode();
			break;
	}
	$scope.caLoginStatus = "";
	$scope.caLogin = function() { //登录
		$scope.caLoginStatus = 'login';
	}
	$scope.caInfo = function(companyName, companyNo) {
		if(companyName && companyNo) {
			appData.targetNo = companyNo;
			appData.deptName = companyName;
			$state.go('info');
			$scope.$apply();
		}
	}
	//跳转页面
	$scope.nextStep = function() {
		$scope.tokenType = "token";
		$scope.token = function() {
			$state.go('info');
		}
	}

	$scope.prevStep = function() {
		$state.go("loginType");
	}

})
app.controller('info', function($state, $scope, appData, appFactory, $http) {
	$scope.operation = "请填写基本信息";
	var name = appData.itemName;
	$scope.itemName = name;
	$scope.isLoding = true;
	$scope.concel = 'false';
	$scope.areaList = areaList;
	$scope.deptTypeList = deptType;
	$scope.isCrossArea = isCrossArea;
	$scope.nextText = "提交";
	appData.isUpload = [];
	appData.listImg = [];
	$scope.deptName = appData.deptName;
	$scope.targetNo = appData.targetNo;
	$scope.targetName = appData.targetName;

	$scope.change = function(index, item, type) {
		switch(type) {
			case "1":
				$scope.current1 = index;
				$scope.deptType = item.name;
				$scope.deptTypeId = item.id;
				break;
			case "2":
				$scope.current2 = index;
				$scope.areaName = item.areaName;
				$scope.areaCode = item.areaCode;
				break;
			case "3":
				$scope.current3 = index;
				$scope.crossArea = item.name;
				$scope.crossAreaId = item.id;
				break;
		}
	}
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.itemName = appData.itemName;
	$scope.prevStep = function() {
		$state.go("loginType");
	}
	// 保存数据
	$scope.flag = true;
	$scope.nextStep = function() {
		if(!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			if($('#deptName').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入单位名称！";
				return;
			}
			if($scope.deptType == "" || $scope.deptType == null || $scope.deptType == undefined) {
				$scope.isAlert = true;
				$scope.msg = "请选择单位类型！";
				return;
			}
			if($('#targetNo').val().length < 17) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的社会统一信用代码！";
				return;
			}
			if($('#targetName').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入法定代表人姓名！";
				return;
			}
			if($('#targetPost').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入法定代表人职务！";
				return;
			}
			if($('#linkAddress').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入联系地址！";
				return;
			}
			if($('#phone').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入固定电话！";
				return;
			}
			if(!isPhoneAvailable($('#mobile').val())) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的手机号！";
				return;
			}
			if($('#eMail').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入电子邮件！";
				return;
			}
			if($('#itemName').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入事项名称！";
				return;
			}
			if($('#itemUrl').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入事项地址！";
				return;
			}
			if($('#content').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入申请理由！";
				return;
			}
			if($scope.areaName == "" || $scope.areaName == null || $scope.areaName == undefined) {
				$scope.isAlert = true;
				$scope.msg = "请选择所在区！";
				return;
			}
			if($scope.crossArea == "" || $scope.crossArea == null || $scope.crossArea == undefined) {
				$scope.isAlert = true;
				$scope.msg = "请选择是否跨区！";
				return;
			}
		} while (condFlag);
		$scope.paramStr = {
			"departCode": "",
			"accessToken": "",
			"info": {
				"CONTACT_NAME": $scope.linkName,
				"LEGAL_PERS_DUTY": $scope.targetPost,
				"expId": "",
				"targetName": $scope.deptName,
				"targetNo": $scope.targetNo,
				"mdata": "0QOEpDXGO4lM+Ft3RWAUJAZXpmXdDy8bc8Z5VDGw4Q79DrBqxpBRGGon9BGYMF3z75bKsGm4A7wHT3Tn2UiEooZmwZkM3vRiRdw5vXNBmBpedZokn9BDwP2Akfzx4WgBXdxody4EQObcNXsmkTAcOfKNK+kUR9SS4rqGdICdX7o69ISKngRQNzSMKb2FSFdjkDHYoJTVGVPZfTNAVCv3WRw4ghSMsNHoCM7vEJOUWlgU7+DeLNzQptpiHcpXfwuENpmMy7zv/OtQGbQmHaMGsTpDuYQXEtqMZ7oF6DjljfH9WS0upJs2S4+Q988fDjCoIlxU39TcFVHoO35mcKwzgRw0T8gW8CmKoB4GAGYveRc8Ga41XQWvm/240oOw/Ct+",
				"userType": "法人",
				"ST_ORG_TYPE": {
					"name": $scope.deptType,
					"value": $scope.deptTypeId
				},
				"stuff19n00604": [],
				"eformCode": "310100846000-0",
				"ST_DOMICILE_QUXIAN": {
					"name": $scope.areaName,
					"value": $scope.areaCode
				},
				"userId": "",
				"LEGAL_PERS_NAME": $scope.targetName,
				"ST_APPLY_REASON": $scope.content,
				"stuff19n00602": [],
				"stuff19n00603": [],
				"licenseType": {
					"name": "营业执照",
					"value": "d"
				},
				"itemCode": "310100846000-0",
				"ST_APPLY_ADDRESS": $scope.linkAddress,
				"ST_IS_KUAQU": {
					"name": $scope.crossArea,
					"value": $scope.crossAreaId
				},
				"DWTEL": $scope.phone,
				"ST_APPLY_NAME": $scope.itemName,
				"stuff19n00616": [],
				"ST_LINK_ADDERS": $scope.linkAddress,
				"DWEMAIL": $scope.eMail,
				"stuffPreFill": {
					"cert": {},
					"img": {}
				},
				"mobile": $scope.mobile
			},
			"itemCode": "310100846000-0"
		}

		console.log($scope.paramStr);
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/selfDeclare/saveInfo.do",
			dataType: 'json',
			data: {
				info: JSON.stringify($scope.paramStr)
			},
			success: function(res) {
				appData.applyNo = res.applyNo;
				$scope.isLoding = false;
				$state.go("materialList");
			},
			error: function(err) {}
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
			'stuffName': "占用林地（迁移林木、采伐林木、变更补建时间）审核审批申请",
			'upload': true,
			'upload2': false,
			'upload3': false,
			"stuffCode": "stuff19n00104",
			"isMust": "0"
		}, {
			'index': 1,
			'stuffName': "建设项目批件",
			'upload': true,
			'upload2': false,
			'upload3': false,
			"stuffCode": "stuff20n08933",
			"isMust": "0"
		}, {
			'index': 2,
			'stuffName': "区主管部门意见征询单、调查登记表，林木、林地权属人意见",
			'upload': true,
			'upload2': false,
			'upload3': false,
			"stuffCode": "stuff20n08931",
			"isMust": "0"
		}, {
			'index': 3,
			'stuffName': "补偿协议与补建林地规资部门意见",
			'upload': true,
			'upload2': false,
			'upload3': false,
			"stuffCode": "stuff20n08932",
			"isMust": "0"
		}, {
			'index': 4,
			'stuffName': "占用林地补建林地承诺书",
			'upload': true,
			'upload2': false,
			'upload3': false,
			"stuffCode": "stuff20n08935",
			"isMust": "0"
		}, {
			'index': 5,
			'stuffName': "建设项目使用林地可行性报告",
			'upload': true,
			'upload2': false,
			'upload3': false,
			"stuffCode": "stuff19n00105",
			"isMust": "0"
		}, {
			'index': 6,
			'stuffName': "营业执照或事业单位法人证书或社会团体法人登记证书（可免交）",
			'upload': true,
			'upload2': false,
			'upload3': false,
			"stuffCode": "stuff20n08934",
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
		url: $.getConfigMsg.preUrlSelf + "/selfapi/submitItem.do",
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