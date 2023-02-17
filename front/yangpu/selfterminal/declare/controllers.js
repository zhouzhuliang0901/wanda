app.controller("listController", function($scope, $route, $location, $http, data, $timeout) {
	data.isUpload = [];
	data.listImg = [];
	data.fileName = [];
	data.currentIndex = 0;
	$scope.currentPage = 1; // 当前页
	$scope.totalPages = null; // 总页数
	$scope.pageSize = 8; // 每页显示数量
	$scope.organCode = null;
	$scope.matterVal = '';
	$scope.getSearchMatter = function(current) { // 查询事项
		$scope.currentPage = current || 1;
		$scope.organCode = 'search';
		var vConfig = {
			pageSize: $scope.pageSize,
			currentPage: $scope.currentPage,
			itemName: $scope.matterVal,
			jsonpCallback: "JSON_CALLBACK",
		};
		if($scope.matterVal) {
			$http.jsonp(urlHost + '/aci/declare/getItemListByItemNameForPage.do', {
				params: vConfig
			}).success(function(dataJson) {
				$('.sub-index').find('a:first').addClass('active').siblings().removeClass('active');
				$scope.totalPages = dataJson.totalPageCount;
				$scope.itemList = checkList(dataJson.itemSetList);
			}).error(function() {
				console.log('getOrganListForDeclarePage error')
			})
		} else {
			layer.msg('请输入事项名称');
		}
	};
	$scope.getOrgan = function() { // 获取部门列表
		$scope.matterVal = '';
		var organConfig = {
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/declare/getOrganListForDeclarePage.do', {
			params: organConfig
		}).success(function(dataJson) {
			$scope.organList = dataJson.organSetList;
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	};
	$scope.getAllMtter = function(current) { // 获取全部事项
		$scope.matterVal = '';
		$scope.active = null;
		$scope.organCode = null;
		$scope.currentPage = current || 1;
		var config = {
			pageSize: $scope.pageSize,
			currentPage: $scope.currentPage,
			jsonpCallback: "JSON_CALLBACK",
		}
		$http.jsonp(urlHost + '/aci/declare/getAllItemListForPage.do', {
			params: config
		}).success(function(dataJson) {
			$scope.totalPages = dataJson.totalPageCount;
			$scope.itemList = checkList(dataJson.itemSetList);
		}).error(function() {
			console.log('getAllItemListForPage error')
		})
	};
	$scope.nextPage = function() { // 下一页
		if($scope.currentPage < $scope.totalPages) {
			++$scope.currentPage;
			if($scope.organCode == 'search') {
				$scope.getSearchMatter($scope.currentPage);
			} else if($scope.organCode) {
				$scope.getMatter($scope.organCode, $scope.active, $scope.currentPage);
			} else {
				$scope.getAllMtter($scope.currentPage);
			}
		}
	};
	$scope.previousPage = function() { // 上一页
		if($scope.currentPage > 1) {
			--$scope.currentPage;
			if($scope.organCode == 'search') {
				$scope.getSearchMatter($scope.currentPage);
			} else if($scope.organCode) {
				$scope.getMatter($scope.organCode, $scope.active, $scope.currentPage);
			} else {
				$scope.getAllMtter($scope.currentPage);
			}
		}
	};
	$scope.getMatter = function(organCode, index, current) {
		$scope.matterVal = '';
		$scope.organCode = organCode;
		$scope.currentPage = current || 1;
		$scope.active = index; // 动态添加class元素
		$scope.getOrganList(organCode);
	};
	$scope.getOrganList = function(code) {
		var oConfig = {
			pageSize: $scope.pageSize,
			currentPage: $scope.currentPage,
			organCode: code,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/declare/getItemListByOrganCodeForPage.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.totalPages = dataJson.totalPageCount;
			$scope.itemList = checkList(dataJson.itemSetList);
		}).error(function() {
			console.log('getItemListByOrganCodeForPage error')
		})
	};
	$scope.getMatterDetail = function(itemName, organCode, organName, itemTenNo) {
		data.itemName = itemName;
		data.organCode = organCode;
		data.organName = organName;
		data.itemTenNo = itemTenNo;
		$location.path("/matter");
	};
	$scope.getAllMtter();
	$scope.getOrgan();
	//初始化滚动条插件
	/*angular.element(document).ready(function() {
		$(".sub-index").mCustomScrollbar({
			theme: "dark",
			scrollInertia: 400
		});
	});*/
});
app.controller("matterController", function($scope, $route, $http, $location, data, $timeout) {
	$scope.allName = data.itemName;
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.pageSize = 6;
	$scope.currentPage = 1;
	$scope.totalPages = null;
	$scope.getMatterCond = function() {
		var oConfig = {
			pageSize: $scope.pageSize,
			currentPage: $scope.currentPage,
			itemNo: data.itemTenNo,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/declare/getWindowItemStatusList.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.totalPages = dataJson.totalPageCount;
			$scope.itemList = dataJson.windowItemStatusList;
		}).error(function() {
			console.log('getWindowItemStatusList error')
		})
	};
	$scope.previousPage = function() {
		if($scope.currentPage > 1) {
			--$scope.currentPage;
			$scope.getMatterCond();
		}
	};
	$scope.nextPage = function() {
		if($scope.currentPage < $scope.totalPages) {
			++$scope.currentPage;
			$scope.getMatterCond();
		}
	};
	$scope.getMatterCond();
	$scope.getSubItem = function(id, name, tenNo) {
		data.itemId = id;
		data.statusName = name;
		data.itemTenNo = tenNo;
		$location.path("/guideline");
	};
});
app.controller("guidelineController", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	$scope.pageSize = 8;
	$scope.currentPage = 1;
	$scope.totalPages = null;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	//设置滚动条速度
	$scope.rollingSpeed = 500;
	$scope.getGuieInfo = function() {
		var oConfig = {
			stZhallId: data.itemId,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/declare/getGuideInfoByZhallId.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.guideTitle = dataJson.itemName + "--" + data.statusName;
			$scope.guideInfo = dataJson.guide;
			$scope.clRange = dataJson.guide.clRange;
		}).error(function() {
			console.log('getGuideInfoByZhallId error')
		});
	};
	$scope.getGuieInfo();
	//初始化滚动条插件
	angular.element(document).ready(function() {
		$(".inner-box").mCustomScrollbar({
			theme: "dark-thin",
			scrollInertia: 400
		});
	});
	//滚动条上移
	$scope.moveUp = function() {
		$(".inner-box").mCustomScrollbar("scrollTo", "+=" + $scope.rollingSpeed);
	};
	//滚动条下移
	$scope.moveDown = function() {
		$(".inner-box").mCustomScrollbar("scrollTo", "-=" + $scope.rollingSpeed);
	}
});
app.controller("preController", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	//设置滚动条速度
	$scope.rollingSpeed = 500;
	//初始化滚动条插件
	angular.element(document).ready(function() {
		$(".right-inner").mCustomScrollbar({
			theme: "dark-thin",
			scrollInertia: 400
		});
	});
	//滚动条上移
	$scope.moveUp = function() {
		$(".inner-box").mCustomScrollbar("scrollTo", "+=" + $scope.rollingSpeed);
	};
	//滚动条下移
	$scope.moveDown = function() {
		$(".inner-box").mCustomScrollbar("scrollTo", "-=" + $scope.rollingSpeed);
	}
	// 继续 
	$scope.next = function() {
		// 判断事项是否通过别的渠道跳转
		var oConfig = {
			itemCode: data.itemTenNo,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/declare/getItemAppplyInfo.do', {
			params: oConfig
		}).success(function(dataJson) {
			if(dataJson) {
				if(dataJson.data.molder == 1) { // 为1 时，走官网一网通办的流程
					localStorage.applyUrl = dataJson.data.itemApplyUrl;
					$location.path("/apply");
				} else {						// 为0时，走正常流程
					$location.path("/select");
				}
			}
		}).error(function() {
			console.log('getItemAppplyInfo error')
		});
	}
});
app.controller("applyController", function($scope, $route, $http, $location, data, $timeout, $sce) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	// 设置跳转链接
	$scope.applyUrl = "https://zwdtuser.sh.gov.cn/uc/login/login.jsp?self=my&redirect_uri=http://ywtb.sh.gov.cn:18018" + localStorage.applyUrl;
	// 设置url被angular信任 正常跳转
	$scope.trustSrc = function(src) {
		return $sce.trustAsResourceUrl(src);
	}
});
app.controller("selectController", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	// 刷身份证获取信息
	$scope.scanIdcard = function() {
		$location.path('/main')
	}
	// 市民云亮证
	$scope.citizen = function() {
		$location.path('/citizen')
	}
});
app.controller("citizenController", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	// 扫描二维码
	OcxControl.Barcode(function(res) {
		var httpConfig = {
			codeParam: res,
			jsonpCallback: "JSON_CALLBACK"
		};
		OcxControl.Light.qrcodeLightClose();
		OCX_Barcode.CloseConnection();		// 关闭二维码扫描
		$http.jsonp(urlHost + "/aci/window/getInfoByCodeTest.do", {
				params: httpConfig
			})
			.success(function(dataJsonp) {
				if(!dataJsonp.result.success) {
					layer.msg(dataJsonp.result.msg);
					$timeout(function() {
						$location.path('/select');
					}, 2000)
				} else {
					data.idCardName = dataJsonp.result.data.realname;
					data.idCardNum = dataJsonp.result.data.idcard;
					data.mobile = dataJsonp.result.data.mobile;
					$timeout(function() {
						$location.path('/info');
					}, 100)
				}
			})
			.error(function(err) {
				console.log("二维码已过期！")
			});
	}, function(err) {
		console.log("二维码扫描出错：" + err)
	});
});
app.controller("mainController", function($scope, $route, $location, $http, data, $timeout) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	if (data.isReRecognition === true) {
		data.isReRecognition = false;
		window.location.reload();
	}
	// 读取身份证信息
	$scope.readIdcard = function() {
		OcxControl.idCardRead(function(Obj) {
			var identityInfo = JSON.parse(Obj.identityInfo);
			data.idCardName = identityInfo.Name;
			data.idCardNum = identityInfo.Code;
			data.HeadImg = Obj.portrait;
			$timeout(function() {
				$location.path("/capture");
			}, 100);
		});
	}
	$scope.readIdcard();
});
app.controller("captureController", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.captureImage = null; //拍照照片
	// 打开前置摄像头
	OcxControl.cameraOpen({
		height: "520",
		width: "740",
		left: "865",
		top: "320"
	}, function(data) {
		console.log(data)
	}, function(err) {
		console.log(err)
	});

	$scope.status = false; //拍照OR核验
	$scope.complete = false; //核验完成
	$scope.completeTips = false; // 核验结果信息
	$scope.completeError = false; // 核验失败 重新核验按钮
	$scope.confirm = true; //确认拍照图片
	$scope.btnInfo = "拍照";
	$scope.tipsText = "对比通过"; //核验完成提示

	$scope.capture = function() {
		OcxControl.cameraPhotograph(function(res) {
			$timeout(function() {
				$scope.captureImage = res;
				$scope.recognition()
			}, 100);
		}, function(err) {})
	}
	$scope.recognition = function() { //人证数据对比
		var idCardPhoto = data.HeadImg;
		var capturePhoto = $scope.captureImage;
		$scope.status = true;
		$scope.confirm = false;

		// 人证对比接口请求
		$.ajax({
			url: "http://hengshui.5uban.com/ac-product-ext/ext/aci/autoterminal/facecompare.do",
			type: "post",
			dataType: "json",
			data: {
				idCardPhoto: idCardPhoto,
				capturePhoto: capturePhoto
			},
			success: function(res) {
				$timeout(function() {
					var n = res.similarity;
					$scope.status = false;
					if(n > 60) {
						//核验通过
						$scope.completeTips = true;
						$scope.complete = true;
					} else {
						//人证不符
						$scope.tipsText = "人证不符";
						$scope.completeTips = true;
						$scope.tipsText = "人证不符";
						$scope.complete = false;
						$scope.completeError = true;
					}
				}, 10)
			},
			error: function(err) {
				$timeout(function() {
					$scope.tipsText = "人证不符";
				}, 10)
			}
		});
	};
	$scope.finish = function() { // 核验完成
		$timeout(function() {
			$location.path('/info');
		}, 100)
	};
	$scope.confirm = function() {
		$.device.GoHome();
	}
	$scope.backTo = function() { // 核验失败
		data.isReRecognition = true;
		$location.path("/main");
	}
});
app.controller("infoController", function($scope, $route, $http, $location, data, $timeout) {
	$scope.fullItemName = data.itemName + "--" + data.statusName;
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.targetTypeName = '个人';
	$scope.targetTips = '申请人身份证号';
	$scope.targetName = '申请人姓名';

	// $wathc 监听 targetTypeName 
	$scope.$watch('targetTypeName', function(newValue, oldValue) {
		$scope.targetTypeName == '个人' ? $scope.targetTips = '申请人身份证号' : $scope.targetTips = '统一社会信用代码';
		$scope.targetTypeName == '个人' ? $scope.targetName = '申请人姓名' : $scope.targetName = '企业名称';
		$scope.targetTypeName == '个人' ? $('#targetName').val(data.idCardName) : $('#targetName').val('');
		$scope.targetTypeName == '个人' ? $('#targetNo').val(data.idCardNum) : $('#targetNo').val('');
	});

	$('#username').val(data.idCardName);
	$('#licenseNo').val(data.idCardNum);
	if(data.mobile) {
		$('#mobile').val(data.mobile);
	}

	// 保存数据
	$scope.flag = true;
	$scope.goNext = function() {
		/*$location.path("/materialUpload");*/
		if(!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			if($scope.targetTypeName == '个人') {
				if($('#targetName').val() < 1) {
					layer.msg('请输入正确的个人姓名！');
					return;
				}
				if(!checkIdCard($('#targetNo').val()))
					return;
				if($('#username').val() < 1) {
					layer.msg('请输入正确的申请人姓名！');
					return;
				}
				if(!checkIdCard($('#licenseNo').val()))
					return;
				if(!isPhoneAvailable($('#mobile').val()))
					return;
			} else if($scope.targetTypeName == '法人') {
				if($('#targetName').val() < 1) {
					layer.msg('请输入正确的企业名称！');
					return;
				}
				if($('#targetNo').val().length < 17) {
					layer.msg('请输入正确的统一社会信用代码！');
					return;
				}
				if($('#username').val() < 1) {
					layer.msg('请输入正确的申请人姓名！');
					return;
				}
				if(!checkIdCard($('#licenseNo').val()))
					return;
				if(!isPhoneAvailable($('#mobile').val()))
					return;
			}
		} while (condFlag);
		var from = $('#infoForm').serialize();
		var fConfig = {
			'applyNo': '',
			'itemCode': data.itemTenNo, // data.itemTenNo	'0101220000-00-00-2'
			'itemName': data.itemName,
			'userId': '',
			'source': '网上申请',
			'departCode': data.organCode,
			'departName': data.organName,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/declare/saveApply.do?' + from, {
			params: fConfig
		}).success(function(dataJson) {
			$scope.flag = true;
			data.applyNo = dataJson.data.applyNo;
			data.userName = $('#username').val();
			$location.path("/materialUpload");
		}).error(function(e) {
			console.log(e)
		});
		$scope.flag = false;
	};
});
app.controller("materialUploadController", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	// 必传材料的长度
	$scope.mustUpload = [];
	// 是否有材料列表
	$scope.haveList = false;
	$scope.noList = false;
	//设置滚动条速度
	$scope.rollingSpeed = 500;
	$scope.uploadShow = false;
	// 获取材料列表  		0  不必须  1  必须 
	var fConfig = {
		itemCode: data.itemTenNo, // 	data.itemTenNo	'0045065000'
		jsonpCallback: "JSON_CALLBACK",
	};
	$http.jsonp(urlHost + '/aci/declare/getItemStuffList.do', {
			params: fConfig
		})
		.success(function(dataJson) {
			if(dataJson.data.length > 0) {
				$(".nolist").hide();
				$('.submit').css('left', '412px');
				for(var i = 0; i < dataJson.data.length; i++) {
					if(dataJson.data[i].isMust == 1) {
						$scope.mustUpload.push({
							index: i,
							stuffName: dataJson.data[i].stuffName
						});
					}
				}
				$scope.stuffList = dataJson.data;
				$scope.haveList = true;
				if($scope.stuffList.length > 4) {
					$('.content-side').show();
				}
				if(data.listImg == 0) {
					for(var s = 0; s < $scope.stuffList.length; s++) {
						data.listImg[s] = {
							'activeImg': null,
							'stuffName': $scope.stuffList[s].stuffName,
							'index': s
						}
					}
				}
				// 设置已上传文件图标的变化
				if(data.isUpload) {
					for(var i = 0; i < data.isUpload.length; i++) {
						for(var j = 0; j < data.listImg.length; j++) {
							if(data.listImg[j].activeImg != 'images/state_1.png') {
								if(data.isUpload[i].stuffName == data.listImg[j].stuffName) {
									data.listImg[data.isUpload[i].index].activeImg = 'images/state_1.png';
								}
							}
						}
					}
				}
				$scope.listImg = data.listImg;
				// 设置 选择上传方式 时 材料名称标题 是否显示
				data.isShowStuffName = true;
			} else {
				$scope.uploadShow = true;
				$scope.stuffList = data.fileName;
				if(data.fileName.length > 0) {
					$scope.noList = true;
				}
				if(data.fileName.length > 4) {
					$('.content-side').show();
				}
				// 设置 选择上传方式 时 材料名称标题 是否显示
				data.isShowStuffName = false;
			}
		}).error(function(e) {
			console.log(e)
		});
	// 判断材料是否上传 
	data.currentIndex++;
	// 点击材料列表上传
	$scope.uploadMaterial = function(index, code, id, name) {
		data.stuffCode = code;
		data.stuffId = id;
		data.stuffName = name;
		data.currentIndex = index;
		$location.path("/uploadMethod");
	};
	// 上传完文件 提交办件
	$scope.submit = function() {
		if($scope.uploadShow) {
			$location.path('/infoFinish');
		} else {
			var a = 0;
			if(data.isUpload.length >= $scope.mustUpload.length) {
				for(var i = 0; i < data.isUpload.length; i++) {
					for(var j = 0; j < $scope.mustUpload.length; j++) {
						if(data.isUpload[i].stuffName == $scope.mustUpload[j].stuffName) {
							a++;
						}
					}
				}
				if(a >= $scope.mustUpload.length) {
					$location.path('/infoFinish');
				} else {
					layer.msg('请提交必上传的材料!');
				}
			} else {
				layer.msg('请提交必上传的材料!');
			}
		}
	};
	//初始化滚动条插件
	angular.element(document).ready(function() {
		$(".inner-box").mCustomScrollbar({
			theme: "dark-thin",
			scrollInertia: 400
		});
	});
	//滚动条上移
	$scope.moveUp = function() {
		$(".inner-box").mCustomScrollbar("scrollTo", "+=" + $scope.rollingSpeed);
	};
	//滚动条下移
	$scope.moveDown = function() {
		$(".inner-box").mCustomScrollbar("scrollTo", "-=" + $scope.rollingSpeed);
	}
});
app.controller("uploadMethodController", function($scope, $route, $http, $location, data, $timeout) {
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	// 显示材料名称
	$scope.stuffName = data.stuffName;
	$scope.stuffNameShow = data.isShowStuffName;
	// 关闭高拍仪
	OcxControl.scanClose();
	// 扫描上传
	$scope.scanPhoto = function() {
		$location.path('/finish');
	};
	// U盘上传
	$scope.takePhoto = function() {
		layer.confirm('请确认是否插入U盘！', {
			btn: ['已插入U盘', '未插入U盘'] //按钮
		}, function() {
			$('.layui-layer-shade').hide();
			$('.layui-layer').hide();
			$timeout(function() {
				$location.path('/takePhoto/U');
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
		$location.path('/materialPic');
	};
});
app.controller("materialPicController", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	var $temp = new ImageShow(".imageShow");
	$scope.profileShow = function() {
		$.ajax({
			type: "get",
			async: true,
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			/*url: "http://hengshui.5uban.com/ac/aci/autoterminal/forward.do",	// 产品化
			 * url: "http://31.0.1.212:8080/ac/aci/app/getLicenseStuffList.do",	// 徐汇
			data: {
				type: 0,
				username: "yun",
				password: "04b34557c2110962",
				stName: data.idCardName, // data.idCardName
				stIdNo: data.idCardNum // data.idCardNum	
			},
			//	http://hengshui.5uban.com/ac/aci/autoterminal/forward.do
			//  fmd: "aci-archives", 		fdo: "getLicenseStuffList",
			* */
			/* 黄埔地址  */
			data: {
				type: 0,
				fmd: "aci-archives", 		
				fdo: "getLicenseStuffList",
				stName: data.idCardName, 
				stIdNo: data.idCardNum 
			},
			url: urlHost+"/aci/autoterminal/forward.do",
			success: function(json) {
				var dataJson = eval("(" + JSON.stringify(json) + ")");
				if(!dataJson) { // !dataJson[0].address
					layer.alert('没有数据，请重新选择上传方式！', {
						skin: 'layui-layer-molv', //样式类名
						closeBtn: 0
					}, function() {
						$timeout(function() {
							layer.close();
							$location.path('/uploadMethod');
						}, 100);
					});
				} else {
					data.profile = dataJson;
					$temp.SetUrls(data.profile);
				}
			},
			error: function(err) {
				console.log(e)
			}
		});
	};
	$scope.goNext = function() {
		var page = $('.currentPage').html();
		page = page.split('/')[0] - 1;
		data.page = page;
		$location.path('/takePhoto/pic');
	}
	$scope.profileShow();
});
app.controller("takePhotoController", function($scope, $route, $http, $location, data, $timeout, $routeParams) {
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	if($routeParams.flag == 'U') { // U盘上传
		$.device.fileOpen(function(value) {
			$timeout(function() {
				$scope.UData = value;
				$('#showImg').hide();
				var list = '<div class="document"><div>待上传文件：</div><div>' +
					$scope.UData + '</div></div>';
				$('.camera').html(list);
			}, 100)
		});
	} else { // 个人档案上传
		$scope.waitUploadImgUrl = data.profile[data.page].address;
		$scope.imgName = data.profile[data.page].stLicenseName;
		//	'http://hengshui.5uban.com/ac' + 
		$('#showImg').attr('src', $scope.waitUploadImgUrl);
	}
	$scope.photoFlag = true;
	$scope.nextFlag = false;
	// 上一步
	$scope.back = function() {
		history.back();
	}
	// 拍照
	$scope.highCapture = function() { // 高拍仪拍照
		var loading = layer.load(1, {
			shade: [0.4, '#000'] //0.1透明度的白色背景
		});
		if($routeParams.flag == 'U') { // U盘上传
			$scope.jsonData1 = {
				'applyNo': data.applyNo, //  '751122018600008'
				'stuffId': '',
				'stuffCode': data.stuffCode,
				'stuffName': data.stuffName,
				'stuffType': 0,
				'stuffStatus': 0,
			};
			$scope.jsonData1 = JSON.stringify($scope.jsonData1);
			$.device.httpUpload(urlHost + "/aci/declare/uploadStuff.do", "file", $scope.UData,
				$scope.jsonData1,
				function(result) {
					layer.close(loading);
					layer.msg("上传成功");
					$timeout(function() {
						$location.path('/materialUpload');
					}, 100);
				},
				function(webexception) {
					layer.close(loading);
					layer.msg("上传失败");
				});
			data.fileName.push($scope.UData);
		} else if($routeParams.flag == 'pic') { // 个人档案上传
			$scope.jsonData = {
				'applyNo': data.applyNo, //  '751122018600008'
				'stuffId': '',
				'stuffCode': data.stuffCode,
				'stuffName': data.stuffName,
				'stuffType': 0,
				'stuffStatus': 0,
			};
			$scope.jsonData = JSON.stringify($scope.jsonData);
			$.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		urlHost + 
				$scope.waitUploadImgUrl,
				"C:\\waitUploadImg.jpg",
				//将选中图片下载
				function(bytesCopied, totalBytes) {
					console.log(bytesCopied + "," + totalBytes);
				},
				function(result) {
					//将选中图片上传到服务器
					$.device.httpUpload(urlHost + '/aci/declare/uploadStuff.do', "file", "C:/waitUploadImg.jpg",
						$scope.jsonData,
						function(result) {
							layer.close(loading);
							layer.msg("上传成功");
							$timeout(function() {
								$location.path('/materialUpload');
							}, 1000);
						},
						function(webexception) {
							layer.close(loading);
							layer.msg("上传失败");
						});
				},
				function(webexception) {
					layer.close(loading);
					alert("下载文档失败");
				}
			);
			data.fileName.push($scope.imgName);
		}
		$scope.photoFlag = false;
		$scope.nextFlag = true;
		data.isUpload.push({
			index: data.currentIndex,
			stuffName: data.stuffName
		});
	};
});
app.controller("finishController", function($scope, $route, $http, $location, data, $timeout) {
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();

	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;

	OcxControl.scanOpen({ // 打开高拍仪
		height: "530",
		width: "745",
		left: "865",
		top: "120"
	}, function(data) {
		//		$('#showImg').attr('src', '');
	});

	$scope.capture = true; // 拍照按钮显示   重拍、上传按钮显示

	// 重拍
	$scope.refresh = function() {
		OcxControl.scanClose(); // 关闭高拍仪
		OcxControl.scanOpen({ // 打开高拍仪
			height: "530",
			width: "745",
			left: "865",
			top: "120"
		}, function(data) {
			$scope.capture = true;
			//			$('#showImg').attr('src', '');
		});
	};

	// 拍照
	var imgHTML = '',
		imgIndex = 0;
	$scope.next = function() {
		/*OcxControl.scanSave(function(scanImg) { // 高拍仪拍照
			$scope.capture = false;
			data.scanImg = scanImg;
			$('#showImg').attr('src', "data:image/png;base64," + scanImg);
		});*/
		// 点击 拍照 生成的图片放在左侧 

		/*data.uploadStuffId = imgIndex;

		if(data.listImg.length < 1) {
			data.currentIndex++; // 没有材料列表时   文件下标+1 
		}
		data.isUpload.push({
			index: data.currentIndex,
			stuffName: data.stuffName
		});
		data.fileName.push('扫描文件' + data.currentIndex);

		imgHTML += '<div class="img" id="' + data.uploadStuffId + '"><img src="images/1.png" width="150" height="200" /><p onclick="del(\'' + data.uploadStuffId + '\')" class="del">X</p></div>';
		$('.imgBox').html(imgHTML);

		imgIndex++;*/

		OcxControl.scanSave2(function(scanImg) { // 高拍仪拍照	
			// 上传附件
			$.ajax({
				url: urlHost + '/aci/declare/uploadStuff.do',
				type: "post",
				dataType: "json",
				data: {
					'applyNo': data.applyNo, //   '751122018600008'
					'stuffId': '',
					'stuffCode': data.stuffCode,
					'stuffName': data.stuffName,
					'stuffType': 0,
					'stuffStatus': 0,
					'file': scanImg,
				},
				success: function(res) {
					if(res.data.stuffId) // 下载 删除附件 所需id
						data.uploadStuffId = res.data.stuffId;
					else
						data.uploadStuffId = 111;

					if(data.listImg.length < 1) {
						data.currentIndex++; // 没有材料列表时   文件下标+1 
					}
					data.isUpload.push({
						index: data.currentIndex,
						stuffName: data.stuffName
					});
					data.fileName.push('扫描文件');

					imgHTML += '<div class="img" id="' + data.uploadStuffId + '"><img src="data:image/png;base64,' + scanImg + '" width="150" height="200" /><p onclick="del(\'' + data.uploadStuffId + '\')" class="del">X</p></div>';
					$('.imgBox').html(imgHTML);
				},
				error: function(err) {
					data.uploadStuffId = 111;
					if(data.listImg.length < 1) {
						data.currentIndex++; // 没有材料列表时   文件下标+1 
					}
					data.isUpload.push({
						index: data.currentIndex,
						stuffName: data.stuffName
					});
					data.fileName.push('扫描文件');
					imgHTML += '<div class="img" id="' + data.uploadStuffId + '"><img src="data:image/png;base64,' + scanImg + '" width="150" height="200" /><p onclick="del(\'' + data.uploadStuffId + '\')" class="del">X</p></div>';
					$('.imgBox').html(imgHTML);
				}
			});
		});
	};
	// 完成拍照
	$scope.finishUpload = function() {
		$timeout(function() {
			OcxControl.scanClose(); // 关闭高拍仪
			$location.path('/materialUpload');
		}, 20);
	};
	// 上传
	$scope.finish = function() {
		OcxControl.scanClose(); // 关闭高拍仪
		$.ajax({
			url: urlHost + '/aci/declare/uploadStuff.do',
			type: "post",
			dataType: "json",
			data: {
				'applyNo': data.applyNo, //   '751122018600008'
				'stuffId': '',
				'stuffCode': data.stuffCode,
				'stuffName': data.stuffName,
				'stuffType': 0,
				'stuffStatus': 0,
				'file': data.scanImg,
			},
			success: function(res) {
				// res.data.stuffId		传此id 可下载 删除附件
				$timeout(function() {
					layer.confirm('上传成功！', {
						btn: ['继续上传', '完成上传'] //按钮
					}, function() {
						$('.layui-layer-shade').hide();
						$('.layui-layer').hide();
						if(data.listImg.length < 1) {
							data.currentIndex++; // 没有材料列表时   文件下标+1 
						}
						$scope.capture = true;
						$timeout(function() {
							OcxControl.scanOpen({ // 打开高拍仪
								height: "530",
								width: "745",
								left: "865",
								top: "120"
							}, function(data) {
								$('#showImg').attr('src', '');
							});
						}, 20);
					}, function() {
						$('.layui-layer-shade').hide();
						$('.layui-layer').hide();
						$scope.capture = false;
						$timeout(function() {
							$location.path('/materialUpload');
						}, 20);
					});
					data.isUpload.push({
						index: data.currentIndex,
						stuffName: data.stuffName
					});
					data.fileName.push('扫描文件' + data.currentIndex);
				}, 100);
			},
			error: function(err) {
				//	layer.msg('上传失败！')
			}
		});
	};
});
app.controller("infoFinishController", function($scope, $route, $http, $location, data, $timeout) {
	$scope.allName = data.itemName + '--' + data.statusName;
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	// 生成办件编码
	$scope.finishData = {
		applyNo: data.applyNo, 
		subItemCodes: '', // data.itemId
		jsonpCallback: "JSON_CALLBACK",
	};
	$http.jsonp(urlHost + '/aci/declare/submitApply.do', {
		params: $scope.finishData
	}).success(function(dataJson) {
		console.log(dataJson);
	}).error(function(e) {
		console.log(e)
	});
	$scope.applyNo = data.applyNo;
	$scope.statusText = '打印凭证';
	// 打印凭条
	var f = 0;
	$scope.print = function() {
		f++;
		$scope.statusText = '返回首页';
		if($scope.statusText == '返回首页' && f == 2) {
			$.device.GoHome();
		} else {
			OcxControl.receiptPrint(
				'自助申报申请回执\n' +
				'@qrcode@' + 'http://zwdt.sh.gov.cn/zwdtSW/spnetQuery/bjxqxg2.jsp?ST_WF_ID=' + $scope.applyNo + '&IdOrCode=' + data.idCardName + '@qrcode@\n' +
				'\n事项名称：' + $scope.allName +
				'\n事件编码：' + $scope.applyNo +
				'\n您可至 “一网通办” 总门户 “用户中心” 查询办理进度，或通过扫描上方二维码查询\n请您携带办理材料至 上海市黄浦区行政服务中心 进行办理\n',
				function(success) {
					OcxControl.receiptPrintClose();
				},
				function(error) {
					layer.msg('打印失败');
				});
		}
	};
});