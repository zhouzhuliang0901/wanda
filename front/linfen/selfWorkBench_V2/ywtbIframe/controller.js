var rootApplyNo = "";
var curWwwPath = window.document.location.hostname;

function zlsaveback(applyNo) {
	console.log('ssss' + applyNo);
	rootApplyNo = applyNo;
	if(curWwwPath == "10.81.16.56") {
		//政务网地址
		window.location.href = 'http://10.81.16.56:8080/ac-product/selfWorkBench_V2/ywtbIframe/index.html#/materialList';
	} else if(curWwwPath == '183.194.250.112') {
		window.location.href = 'http://183.194.250.112/ac-product/selfWorkBench_V2/ywtbIframe/index.html#/materialList';
	}
}
//$.getConfigMsg.preUrl = "http://10.2.14.127:8080/ac-self";
app.controller('main', function($state, $scope, appData, $location) {
	$scope.operation = "请选择部门";
	$scope.deptName = departmentList;
	$scope.getItem = function(item) {
		$scope.dept = false;
		$scope.prevShow = '1';
		$scope.operation = "请选择事项";
		$scope.itemName = getItemByID(item.id, itemList);
		appData.itemName = $scope.itemName;
		$state.go('item');
	}
	$scope.prevStep = function() {
		$state.go('matter');
	}
});
app.controller('item', function($state, $scope, appData, $location) {
	$scope.operation = "请选择事项";
	$scope.itemName = appData.itemName;
	$scope.getMatter = function(item) {
		$scope.itemStatus = getItemByID(item.id, itemStatusList);
		appData.itemStatus = $scope.itemStatus;
		$state.go('matter');
	}
	$scope.prevStep = function() {
		$state.go('main');
	}
});
app.controller('matter', function($state, $scope, appData, $location) {
	$scope.operation = "请选择情形";
	$scope.itemStatus = appData.itemStatus;
	$scope.nextStep = function(item){
		appData.itemInfo = item;
		$state.go('loginType');
	}
	$scope.prevStep = function() {
		$state.go('item');
	}
});
app.controller('loginType', function($state, $scope, appData, $location) {
	console.log(appData.itemInfo.type)
	$scope.operation = "请选择登录方式";
	appData.listImg = [];
	appData.isUpload = [];
	appData.token = "";
	rootApplyNo = "";
	appData.applyNo = "";
	$scope.type = appData.itemInfo.type;
	appData.funName = $scope.funName = appData.itemName.name;
	appData.itemCode = appData.itemInfo.code;
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go('main');
	}
});
app.controller('login', function($scope, $http, $state, appData) {
	// $.getConfigMsg.preUrlSelf = $.getConfigMsg.preUrl = "http://180.169.7.194:8081/ac-self"
	$scope.funName = appData.funName;
	$scope.isAlert = false;
	$scope.msg = "";
	$scope.nextLink = ""; // 下一步标识符
	$scope.loginType = appData.loginType;
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
			$state.go("iframe");
		}
	}

	//获取token ------2、比对成功后，根据tokenSNO获取access_token
			$scope.getAccessToken = function(tokenSNO) {
				var rec = $.ajax({
					url: $.getConfigMsg.preUrl + "/aci/workPlatform/getAccessToken.do",
					type: "post",
					dataType: "json",
					//					jsonp: "jsonpCallback",
					timeout: 5000,
					data: {
						tokenSNO: tokenSNO,
					},
					success: function(res) {
						console.log(res);
						if(res.SUCCESS === true) {
							appData.token = res.accessToken;
							$state.go("iframe");
						} else {
							rec.abort();
						}
					},
					error: function(err) {
						console.log(err);
					}
				})
			}

	//ca获取token
	$scope.getTokenSNOForCorporation = function(){
		$.ajax({
			type:"get",
			url:"http://10.2.14.127:8080/ac-self/aci/workPlatform/getTokenSNOForCorporation.do",
			dataType: "json",
			data: {
				creditCode: appData.licenseNumber,
				companyName: encodeURI(appData.licenseName),
				use_type:'1',
				caCode:appData.commoncode
			},success:function(res){
				$scope.getAccessToken(res.tokenSNO);
			},error:function(err){
				console.log(res);
			}
		});
	}

	$scope.caLoginStatus = "";
	$scope.caLogin = function() { //登录
		$scope.caLoginStatus = 'login';
	}
	$scope.caInfo = function(companyName, companyNo,commoncode) {
		if(companyName && companyNo) {
			appData.licenseNumber = companyNo;
			appData.licenseName = companyName;
			appData.commoncode = commoncode;
			console.log('commoncode'+commoncode)
			$scope.getTokenSNOForCorporation();
		}
	}


	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
			appData.VALIDENDDAY = info.ValidtermOfEnd;
			appData.VALIDSTARTDAY = info.ValidtermOfStart;
			$scope.loginType = 'recognition';
		} else {
			layer.msg("很抱歉，未获取到相关信息，请重试。");
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		$state.go("iframe");
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.alertCancel = function() {
		$scope.isAlert = false;
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
			appData.licenseNumber = idcardInfo.idcard;
			appData.VALIDENDDAY = idcardInfo.VALIDENDDAY;
			appData.VALIDSTARTDAY = idcardInfo.VALIDSTARTDAY;
			$scope.nextStep();
		}
	}
});
app.controller('iframe', function($state, $scope, appData, $location, $sce) {
	if(curWwwPath == "10.81.16.56") {
		//政务网地址
		$scope.address = $sce.trustAsResourceUrl("http://10.81.16.56:8080/ac-product-net/netapply/apply.do?itemCode="+appData.itemCode+"&access_token="+appData.token+"&type=terminal&applyNo=" + appData.applyNo);
	} else if(curWwwPath == '183.194.250.112') {
		$scope.address = $sce.trustAsResourceUrl("http://183.194.250.112/ac-product-net/netapply/apply.do?itemCode="+appData.itemCode+"&access_token="+appData.token+"&type=terminal&applyNo=" + appData.applyNo);
	}
	console.log($scope.address);
	//	$scope.address = $sce.trustAsResourceUrl("http://117.184.226.70:8022/ac-product-net/netapply/apply.do?itemCode=310100839001-1&access_token="+appData.token+"&type=terminal&applyNo="+appData.applyNo);
	// console.log("http://180.169.7.194:8099/ac-product-net/netapply/apply.do?itemCode="+appData.itemCode+"&access_token="+appData.token+"&type=terminal&applyNo=" + appData.applyNo);
	$scope.next = function() {
		if(typeof $('#container-iframe')[0].contentWindow.sb == 'function') {
			$('#container-iframe')[0].contentWindow.sb();
		}
		if(typeof $('#container-iframe')[0].contentWindow.submitsave == 'function') {
			$('#container-iframe')[0].contentWindow.submitsave();
		}
	}

	$scope.prevStep = function(){
		$state.go('main');
	}

});
app.controller("uploadMethod", function($scope, $http, $state, $rootScope, appData, $timeout) {
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
app.controller("uFileUpload", function($scope, $http, $state, $rootScope, appData, $timeout) {
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
app.controller("takePhoto", function($scope, $http, $state, $rootScope, appData, $timeout) {
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
app.controller("materialList", function($scope, $state, $http, appData, $timeout) {
	appData.applyNo = rootApplyNo;
	$scope.nextText = "提交";
	$scope.listImg = appData.listImg;
	$scope.funName = appData.funName;
	$scope.concel = "false";
	$scope.isAlert = false;
	//必传材料列表
	$scope.mustUpload = [];
	$scope.current = 0;
	// 获取材料列表
	var fConfig = {
		itemCode: appData.itemCode, //"0101361000",//
		jsonpCallback: "JSON_CALLBACK",
	};
	//设置上传文件 按钮变化
	$scope.btn = function() {
		$http.jsonp($.getConfigMsg.preUrlSelf + '/selfapi/getItemStuffs.do', {
			params: fConfig
		}).success(function(dataJson) {
			//材料列表
			if(dataJson.data.flag == false) {
				if(dataJson.data.subItems[0].itemCode.indexOf('-1') != -1) {
					$scope.stuffList = dataJson.data.subItems[0].stuffs;
				} else {
					$scope.stuffList = dataJson.data.subItems[1].stuffs;
				}
			} else {
				$scope.stuffList = dataJson.data.stuffs;
			}
			if($scope.stuffList.length<=0){
				$scope.isAlert = true;
				$scope.msg = "此事项无需上传材料"
				$scope.alertConfirm = function(){
					$state.go('submit')
				}
			}
			if(appData.listImg == 0) {
				for(var i = 0; i < $scope.stuffList.length; i++) {
					appData.listImg[i] = {
						'activeImg': null,
						'index': i,
						'stuffName': $scope.stuffList[i].stuffName,
						"stuffCode": $scope.stuffList[i].stuffCode,
						'upload': true,
						'upload2': false,
						'upload3': false,
						"isMust": "0"
					}
				}
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
		}).error(function() {
			console.log('queryStuffList error')
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
	$scope.view = function() {
		//		appData.currentIndex = 0;
		appData.view = appData.isUpload;
		$state.go("materialView");
	}
	$scope.prevStep = function() {
		$state.go("iframe");
	}
	//提交办件
	$scope.submit = function() {
		$state.go("submit");
	};

});
//材料显示
app.controller("materialView", function($scope, $state, $http, appData) {
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
	//	$.ajax({
	//		type: "post",
	//		url: $.getConfigMsg.preUrlSelf + "/selfapi/submitItem.do",
	//		dataType: 'json',
	//		data: {
	//			applyNo: appData.applyNo
	//		},
	//		success: function(res) {
	//			console.log(res);
	//		},
	//		error: function(err) {}
	//	});
	$scope.goHome = function() {
		$.device.GoHome();
	}
});