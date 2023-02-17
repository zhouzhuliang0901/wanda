app.controller("listController", function($scope, $route, $location, $http, data, $timeout) {
	window.location.href = "../declare/index.html";
});
app.controller("guidelineController", function($scope, $route, $http, $location, data, $timeout) {
	data.isUpload = [];
	data.listImg = [];
	data.fileName = [];
	data.itemName = "临时用海项目备案";
	$scope.guideTitle = data.itemName;
	data.itemId = "dcf3a354-ca6c-4ac5-8851-92ba5d996caf";// 获取相对应的办事指南
	var name = data.itemName;
	$scope.pageSize = 8;
	$scope.currentPage = 1;
	$scope.totalPages = null;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	
	$scope.getGuieInfo = function() {
		var oConfig = {
			stZhallId: data.itemId,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp($.getConfigMsg.declareUrl + '/aci/declare/getCNGuideInfoByZhallId.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.guideTitle = dataJson.itemName ;
			$scope.guideInfo = dataJson.guide;
			$scope.clDealAccording = dataJson.guide.clDealAccording.replace(/\n/g, "<br>");
			$scope.clDealType = dataJson.guide.clDealType.replace(/\n/g, "<br>");
			$scope.clRange = dataJson.guide.clRange;
			data.nmBelong = dataJson.guide.nmBelong;
		}).error(function() {
			console.log('getGuideInfoByZhallId error')
		});
	};
	$scope.getGuieInfo();
	
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});
app.controller("preController", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.nmBelong = data.nmBelong;
	
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
	// 继续 
	$scope.next = function() {
		$location.path("/select");
	}
});
app.controller("selectController", function($scope, $route, $http, $location, data, $timeout) {
	$scope.identitySelect = true;
	$scope.modeSelect = false;
	$scope.legalPersonSelect = false;
	
	$scope.Individual = function(){
		$scope.identitySelect = false;
		$scope.modeSelect = true;
		$scope.legalPersonSelect = false;
	}
	
	$scope.legalPerson = function(){
		$scope.identitySelect = false;
		$scope.modeSelect = false;
		$scope.legalPersonSelect = true;
	}
	
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...';
	}
	$scope.itemName = name;
	// 刷身份证获取信息
	$scope.scanIdcard = function() {
		$location.path('/main')
	}
	// 随申办亮证
	$scope.citizen = function() {
		$location.path('/citizen')
	}
	// Ukey
	$scope.key = function(){
		$location.path('/key')
	}
	// 亮证
	$scope.goCitizen = function(){
		$location.path("/idcard").search({
            from: "legalPerson"
        })
	}
});
app.controller("citizenController", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.getResult = function() {
		$location.path(getRoute(data.statusIndex));
	}
});
app.controller("mainController", function($scope, $route, $location, $http, data, $timeout) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	// 读取身份证信息
	$scope.readIdCard = function(idCardInfo) {
		data.idCardInfo = idCardInfo;
		data.idCardName = idCardInfo.idCardName;
		data.idCardNum = idCardInfo.idCardNum;
		data.startDate = idCardInfo.startDate;
		data.endDate = idCardInfo.endDate;
		data.HeadImg = idCardInfo.HeadImg;
		$timeout(function() {
			$location.path("/capture");
		}, 100);
	}
//	$scope.readIdcard();
//	data.idCardName = "测试";
//	data.idCardNum = "430426199804106174";
//	$location.path('/info');
	
});
app.controller("captureController", function($scope, $route, $http, $location, data, $timeout) {
	$scope.idCardInfo = data.idCardInfo;
	$scope.getResult = function() {
		$location.path('/info');
	}
});
app.controller("infoController", function($scope, $route, $http, $location, data, $timeout) {
	$scope.fullItemName = data.itemName;
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	data.itemCode = "310101293000-01";
	if(data.itemCode) {
		$.ajax({
				type: "get",
				url: $.getConfigMsg.declareUrl + '/aci/getItemApplyPlace.do?',
				dataType: "jsonp",
				jsonp: "jsonpCallback",
				data: {
					itemCodes: data.itemCode,
					regionCode:"SH00SH",
				},
				success: function(dataJson) {
					if(dataJson) {
						data.departCode = dataJson[0].bldCode; 
					} else {
						layer.msg("查询办理点信息失败，请稍后重试");
						$location.path("/matter");
					}
				},
				error:function(err){
					
				}
			});
	};
	// 单位类型
	$scope.aST_ORG_TYPE = aST_ORG_TYPE;
	$scope.mST_ORG_TYPE = $scope.aST_ORG_TYPE[0];
	// 证件类型
	$scope.aZZJGLX = aZZJGLX;
	$scope.mZZJGLX = $scope.aZZJGLX[0];
	// 申请类型
	$scope.asqtype = asqtype;
	$scope.msqtype = $scope.asqtype[0];
	// 行政区
	$scope.aDISTRICTS = aDISTRICTS;
	$scope.mDISTRICTS = $scope.aDISTRICTS[0];

	$scope.goNext = function() {
		// 判空操作，正式环境开放
		var blackArr = $(".true").siblings("input");
		if(!isInputBlack(blackArr)){
			layer.msg('请输入必要的信息');
			return;
		}
		var info = {
					"APPLIER": $scope.APPLIER,
					"stuff19n00245": [],
					"expId": "",
					"SLH": $scope.SLH,
					"DISTRICTS": {
						"name": $scope.mDISTRICTS.name,
						"value": $scope.mDISTRICTS.value
					},
					"ZZJGLX": {
						"name": $scope.mZZJGLX.name,
						"value": $scope.mZZJGLX.value
					},
					"targetName": $scope.targetName,
					"mdata": "0QOEpDXGO4lM+Ft3RWAUJPKVzAHMnMKj17GdHPGr7IGWRIYmq+m7hyrBP1gULRTPq8+luF5Y9Xoh/NTrO1F2HBm8YeEWEgUZDVZe4HIvFnFKnwGslTC1ct1yjwuAfw4BGLFnau9crNXhd1t2t05ZPT/1v/iV6M26gxGLrr4+9NS9EMK85j4dkmvozuAIIsi5t70BVqsWbyFdgiTguJb5A1mTPFPn2xQEGhUv0VtftnWZtCHK11T0qgzOYM3CYvVClmwShFGfZpvg9d3AQCJUNPNwre5Qer3BaudhVB5w28rS6C0bZ/QU8QFYCWwGObv9",
					"sqtype": {
						"name": $scope.sqtype,
						"value": ""
					},
					"userType": "法人",
					"ST_ORG_TYPE": {
						"name": $scope.mST_ORG_TYPE.name,
						"value": $scope.mST_ORG_TYPE.value
					},
					"username": data.idCardName,
					"licenseNo": data.idCardNum,
					"eformCode": data.itemCode,
					"COMPANYCODE": $scope.COMPANYCODE,
					"stuff19n10798": [],
					"ARTMPHONE": $scope.ARTMPHONE,
					"userId": "",
					"LINKFAX": $scope.LINKFAX,
					"LINKPHONE": $scope.LINKPHONE,
					"stuff19n10679": [],
					"licenseType": {
						"name": $scope.licenseType,
						"value": ""
					},
					"APPADDRESS": $scope.APPADDRESS,
					"ST_LINK_NAME": $scope.ST_LINK_NAME,
					"LINKEMAIL": $scope.LINKEMAIL,
					"ARTIFICIALPERSON": $scope.ARTIFICIALPERSON,
					"PROJECTNAME": $scope.PROJECTNAME,
					"DWTEL": $scope.DWTEL,
					"LINKMPHONE": $scope.LINKMPHONE,
					"PROJECTADDRESS": $scope.PROJECTADDRESS,
					"XMSJXZBA": $scope.XMSJXZBA,
					"ARTFAX": $scope.ARTFAX,
					"LINKMAN": $scope.LINKMAN,
					"ARTPHONE": $scope.ARTPHONE,
					"ST_LINK_ADDRESS": $scope.ST_LINK_ADDRESS,
					"stuffPreFill": {
						"cert": {}
					},
					"APPPOST": $scope.APPPOST,
					"mobile": $scope.mobile
		}

		
		var fConfig =	
			{
				departCode: data.departCode,
				accessToken: "",
				info: info,
				itemCode: data.itemCode,
			};
		$http.get($.getConfigMsg.declareUrl + '/aci/saveInfo.do?', {
			params: {
				info:JSON.stringify(fConfig),
			}
		}).success(function(dataJson) {
			if(dataJson) {
				data.stApplyNo = dataJson.applyNo; // stApplyNo下边需要用到，统一社会审批编码
				$location.path("/materialUpload");
			} else {
				layer.msg("信息提交失败，请稍后重试");
			}
		}).error(function(e) {
			console.log(e)
		});
	}
	
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});
app.controller("materialUploadController", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	// 是否有材料列表
	$scope.haveList = false;
	$scope.noList = false;
	data.stuffListLength = 0;
	// 获取材料列表  		
	var fConfig = {
		itemCode: data.itemCode, // 事项编码
		jsonpCallback: "JSON_CALLBACK",
	};
	$http.jsonp($.getConfigMsg.declareUrl + '/aci/getItemStuffs.do', {
			params: fConfig
		})
		.success(function(dataJson) {
			if(dataJson.isSuccess || dataJson.data) {
				if(dataJson.data.stuffs.length > 0) {
					$scope.haveList = true;
//					data.stuffListLength = dataJson.data.stuffs.length;
					$scope.stuffList = dataJson.data.stuffs;
					if(data.listImg == 0) {
						for(var s = 0; s < $scope.stuffList.length; s++) {
							data.listImg[s] = {
								'activeImg': null,
								'stuffName': $scope.stuffList[s].stuffName,
								'index': s
							}
							if($scope.stuffList[s].isMust == 1){
								data.stuffListLength++;
							}
						}
					}
					// 设置已上传文件图标的变化
					if(data.isUpload.length > 0) {
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
					$scope.haveList = false;
					$scope.stuffList = [];
					data.isShowStuffName = false;					
				}
			} else {
				alert(dataJson.msg);
				$location.path("/matter");
			}
		}).error(function(e) {
			console.log(e)
			alert("数据查询失败，请重试");
		});
	// 判断材料是否上传 
	data.currentIndex++;
	// 查询电子证照库
    $scope.getCertsDataBase64 = function (certName,certNo) {
    	var base64 = '';
        $http.jsonp($.getConfigMsg.declareUrl + "/aci/autoterminal/dzzz/queryCertBaseDatas.do", {
            params: {
			        jsonpCallback: "JSON_CALLBACK",
			        certNo: data.idCardNum,
				    name: encodeURI(data.idCardName),
				    type: "0",
				    catMainCode: certNo,
				    machineId: $.config.get("uniqueId"),
				    itemName: encodeURI(certName),
				    itemCode: data.itemCode,
				    businessCode: "",
				    startDay: data.startDate || '',
				    endDay: data.endDate || '',
			    }
            })
            .success(function (result) {
            	$scope.isLoading = false;
                if (result.length == 0) { 
	                return false;
	            } else {
	            	base64 = result[0].str;
	            }
            })
            .error(function (err) {
                console.log(err);
                $scope.isLoading = false;
            })
 		return base64;
    };
	
	// 点击材料列表上传  code是材料编号
	$scope.uploadMaterial = function(index, name, code, certs) {
		data.currentIndex = index;
		data.stuffName = name;
		data.stuffCode = code;
		if(certs.length > 0) {
			// 自动上传材料
			$scope.isLoading = true;
			var stuffId = '';
			for(var i = 0; i < certs.length; i++) {
				var base64 = $scope.getCertsDataBase64(certs[i].certName,certs[i].certCode);
				if(base64){
					$.ajax({
						url: $.getConfigMsg.declareUrl + '/aci/uploadItemStuffs.do',
						type: "post",
						dataType: "json",
						data: {
							FileData: base64,
							applyNo: data.stApplyNo,
							stuffId: stuffId,
							stuffCode: data.stuffCode,
						},
						success: function(res) {
							if(res.isSuccess) {
								stuffId = res.stuffId;
								data.isUpload.push({
									index: data.currentIndex,
									stuffName: data.stuffName
								});
								data.listImg[data.currentIndex].activeImg = 'images/state_1.png';
							}
						},
						error: function(err) {
							console.log(err)
						}
					});
				} else {
					alert("未查询到"+certs[i].certName+"证照，请手动上传");
					$location.path("/uploadMethod");
				}
			}
			$scope.isLoading = false;
//			data.listImg[data.currentIndex].activeImg = 'images/state_1.png';
		} else {
			$location.path("/uploadMethod");
		}
	};
	
	// 上传完文件 提交办件
	$scope.submit = function() {
//		$location.path('/infoFinish');
		if(data.isUpload.length >= data.stuffListLength) {
			$location.path('/infoFinish');
		} else {
			layer.msg('请提交必上传的材料!');
		}
	};
	$scope.returnList = function() {
		$scope.isLoading = false;
	}
	
	// 滚动条
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();

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
	//   OcxControl.scanClose();
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
			data: {
				type: 0,
				username: "yun",
				password: "04b34557c2110962",
				stName: data.idCardName, // data.idCardName
				stIdNo: data.idCardNum // data.idCardNum	张：362330199307205799	于：412702199406084145
			},
			url: "http://31.0.1.212:8080/ac/aci/app/getLicenseStuffList.do",
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
	//$scope.back = function() {
	//history.back();
	//}
	// 拍照
	$scope.highCapture = function() { // 高拍仪拍照
		if($routeParams.flag == 'U') { // U盘上传
			$scope.jsonData1 = {
				'applyNo': data.stApplyNo, //   '751122018600008'
				'projectid2': "",
				'fileName': ""
			};
			$scope.jsonData1 = JSON.stringify($scope.jsonData1);
			$.device.httpUpload($.getConfigMsg.declareUrl + "/aci/declare/uploadCNStuff.do", "file", $scope.UData,
				$scope.jsonData1,
				function(result) {
					layer.msg("上传成功");
					data.isUpload.push({
						index: data.currentIndex,
						stuffName: data.stuffName
					});
					$timeout(function() {
						$location.path('/materialUpload');
					}, 100);
				},
				function(webexception) {
					layer.msg("上传失败");
				});
			data.fileName.push($scope.UData);
		} else if($routeParams.flag == 'pic') { // 个人档案上传
			var loading = layer.load(1, {
				shade: [0.4, '#000'] //0.1透明度的白色背景
			});
			$scope.jsonData = {
				'applyNo': data.stApplyNo, //   '751122018600008'
				'projectid2': data.projectId2,
				'fileName': ""
			};
			$scope.jsonData = JSON.stringify($scope.jsonData);
			$.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		$.getConfigMsg.declareUrl + 
				$scope.waitUploadImgUrl,
				"C:\\waitUploadImg.jpg",
				//将选中图片下载
				function(bytesCopied, totalBytes) {
					console.log(bytesCopied + "," + totalBytes);
				},
				function(result) {
					//将选中图片上传到服务器
					$.device.httpUpload($.getConfigMsg.declareUrl + '/aci/declare/uploadCNStuff.do', "file", "C:/waitUploadImg.jpg",
						$scope.jsonData,
						function(result) {
							layer.close(loading);
							layer.msg("上传成功");
							data.isUpload.push({
								index: data.currentIndex,
								stuffName: data.stuffName
							});
							$timeout(function() {
								$location.path('/materialUpload');
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
			data.fileName.push($scope.imgName);
		}
		$scope.photoFlag = false;
		$scope.nextFlag = true;
//		data.isUpload.push({
//			index: data.currentIndex,
//			stuffName: data.stuffName
//		});
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
	$scope.capture = true; // 拍照按钮显示   重拍、上传按钮不显示
	data.uploadStuffId = "";// 上传多个材料时所需的id

	OcxControl.scanOpen({ // 打开高拍仪
		height: "530",
		width: "745",
		left: "865",
		top: "300"
	}, function(data) {
		$('#showImg').attr('src', '');
	});

	// 重拍
	$scope.refresh = function() {
		OcxControl.scanClose(); // 关闭高拍仪
		OcxControl.scanOpen({ // 打开高拍仪
			height: "530",
			width: "745",
			left: "865",
			top: "300"
		}, function(data) {
			$scope.capture = true;
			$('#showImg').attr('src', '');
		});
	};

	// 拍照
	$scope.takePhoto = function() {
		var imgHTML = '';
		$scope.capture = false;
		OcxControl.scanSave2(function(scanImg) { // 高拍仪拍照	
			data.scanImg = scanImg;
			var params = {
				FileData: scanImg,
				applyNo: data.stApplyNo,
				stuffId: data.uploadStuffId,
				stuffCode: data.stuffCode,
			}
			// 上传附件
			$.ajax({
				url: $.getConfigMsg.declareUrl + '/aci/uploadItemStuffs.do',
				type: "post",
				dataType: "json",
				data: params,
				success: function(res) {
					if(res.isSuccess){
						data.uploadStuffId = res.stuffId;
					}else{
						data.uploadStuffId = 0;
					}
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
					if(res.isSuccess){
						data.uploadStuffId = res.stuffId;
					}else{
						data.uploadStuffId = 0;
					}
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

	// 上传
	$scope.upload = function() {
		OcxControl.scanClose(); // 关闭高拍仪
		$timeout(function() {
			layer.confirm('上传成功！', {
				btn: ['继续上传', '完成上传'] //按钮
			}, function() {
				$('.layui-layer-shade').hide();
				$('.layui-layer').hide();
				$scope.capture = true;
				$timeout(function() {
					OcxControl.scanOpen({ // 打开高拍仪
						height: "530",
						width: "745",
						left: "865",
						top: "300"
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
		}, 100);
	}

	// 完成上传，返回材料清单
	$scope.finishUpload = function() {
		$timeout(function() {
			OcxControl.scanClose(); // 关闭高拍仪
			$location.path('/materialUpload');
		}, 20);
	};
});
app.controller("infoFinishController", function($scope, $route, $http, $location, data, $timeout, $sce) {
	$scope.allName = data.itemName ;
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	// 生成办件编码
	$scope.finishData = {
		applyNo: data.stApplyNo,
		jsonpCallback: "JSON_CALLBACK",
	};
	$http.jsonp($.getConfigMsg.declareUrl + '/aci/submitItem.do', {
		params: $scope.finishData
	}).success(function(dataJson) {
		console.log(dataJson);
	}).error(function(e) {
		console.log(e)
	});
	
	$scope.qrcode = new QRCode("code", {
		text: 'http://zwdt.sh.gov.cn/zwdtSW/spnetQuery/bjxqxg2.jsp?ST_WF_ID=' + $scope.applyNo + '&IdOrCode=' + data.idCardName,
		width: 200,
		height: 200,
		correctLevel: 0,
		render: "table"
	});

	$scope.applyNo = data.stApplyNo;
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
				'\n您可至 “一网通办” 总门户 “用户中心” 查询办理进度，或通过扫描上方二维码查询\n请您携带办理材料至 上海市长宁区水务局 进行办理\n',
				function(success) {
					OcxControl.receiptPrintClose();
				},
				function(error) {
					layer.msg('打印失败');
				});
		}
	};
});
