//var urlHost = ' http://172.16.6.18';
var t;
/*document.addEventListener('touchstart', tt);
document.addEventListener('click', tt);
function tt(){
	 clearInterval(t);
	 time() ;
}
function time() {
	var time = 60;
	t = setInterval(function(){
		if (time == 0) {
			clearInterval(t);
			$.device.GoHome();
		}
		$(".minute").text(time);
		time--;
	}, 1000)
}*/
app.controller("listController", function ($scope, $location, $http, $rootScope, data) {
	data.isUpload = [];
	data.listImg = [];
	data.uptype = "0";
	data.fileName = [];
	$scope.current = 0;
	$scope.itemName = [];
	$scope.itemName1 = [];
	$scope.searchType = ["事项名称"];
	$scope.currentMatters = [];
	$scope.organCode = null;
	$scope.matterVal = '';
	$scope.idRead = false;

	clearInterval(t);
	//time();

	//查询事项
	$scope.getSearchMatter = function (current) { // 查询事项
		if ($scope.matterVal) {
			$.ajax({
				type: "get",
				url: "http://172.16.0.200/main/zzsb/getApplyitem",
				async: false,
				data: {
					type: "0"
				},
				success: function (dataJson) {
					$scope.isRead = true;
					var dataObj = eval("(" + dataJson + ")");//转换成json格式
					var message = dataObj.message;
					data.backMessage = dataObj.records;
					if (message == "success") {
						$scope.itemName = [];
						for (var i = 0; i < data.backMessage.length; i++) {
							if (data.backMessage[i].WF_ITEM.indexOf($scope.matterVal) != -1) {
								if (data.backMessage[i].WF_TYPE == null) {
									$scope.itemName.push({
										"stItemName": data.backMessage[i].WF_ITEM,
										"stItemNo": data.backMessage[i].id
									})
								} else {
									$scope.itemName.push({
										"stItemName": data.backMessage[i].WF_ITEM + data.backMessage[i].WF_TYPE,
										"stItemNo": data.backMessage[i].id
									})
								}
							}

						}
					}
				},
				error: function () {
					console.log("获取信息失败！")
				}
			});
		} else {
			layer.msg('请输入事项名称');
			$scope.getMatter();
		}
	};
	$scope.getMatter = function () {
		$scope.itemName = [];
		$.ajax({
			type: "get",
			url: "http://172.16.0.200/main/zzsb/getApplyitem",
			async: false,
			data: {
				type: "0"
			},
			success: function (dataJson) {
				$scope.matterVal = '';
				$scope.isRead = true;
				var dataObj = eval("(" + dataJson + ")");//转换成json格式
				var message = dataObj.message;
				data.backMessage = dataObj.records;
				if (message == "success") {
					for (var i = 0; i < data.backMessage.length; i++) {
						if (data.backMessage[i].WF_TYPE == null) {
							$scope.itemName.push({
								"stItemName": data.backMessage[i].WF_ITEM,
								"stItemNo": data.backMessage[i].id
							})
						} else {
							$scope.itemName.push({
								"stItemName": data.backMessage[i].WF_ITEM + data.backMessage[i].WF_TYPE,
								"stItemNo": data.backMessage[i].id
							})
						}

					}
				}
			},
			error: function () {
				console.log("获取信息失败！")
			}
		});
	}
	$scope.getMatter();

	console.log($scope.itemName);
	$scope.toMaterials = function (stItemName, stItemNo) {

		data.stItemName = stItemName;
		data.stItemNo = stItemNo;
		$location.path("/select");
	};

	//	$scope.isScroll = function() {
	//
	//		new iScroll("wrapper", {
	//			vScrollbar: true,
	//			hScrollbar: false,
	//			hideScrollbar: false,
	//			bounce: true,
	//			checkDOMChanges: true
	//		});
	//	};
	//	$scope.isScroll();
	$rootScope.goHome = function () {
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		window.external.URL_CLOSE();
		$.device.GoHome();
	};
});


app.controller("selectController", function ($scope, $http, $location, data, $timeout) {
	//	try {
	//		window.external.URL_CLOSE();
	//	} catch(e) {
	//	}
	$.device.Camera_Hide();
	var name = data.stItemName;
	if (name.length > 10) {
		name = name.slice(0, 10) + '...'
	};
	$scope.stItemName = data.stItemName;
	clearInterval(t);
	//time();
	// 刷身份证获取信息
	$scope.scanIdcard = function () {
		$location.path('/idCard');
	};
	// 市民云亮证
	$scope.citizen = function () {
		$location.path('/citizen');
	};
	$scope.prev = function () {
		$location.path('/list');
	}
});


app.controller("idCardController", function ($scope, $http, $location, data, $timeout) {
	var name = data.stItemName;
	if (name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.stItemName = name;
	$scope.isRead = true;
	clearInterval(t);
	//time();
	$scope.getIdcard = function (info, images) {
		$scope.faceImage = images;
		$scope.isRead = false;//faceImg
		$scope.$apply();
		data.idCardName = info.Name;
		data.idCardNum = info.Number;
	};

	$scope.getResult = function (img) {
		$scope.cameraPos = {
			width: 640,
			height: 480,
			x: 640,
			y: 340
		}
		$scope.img = img;
		$location.path("/info");
	};

	// 测试数据
	// data.idCardName = "柏金坪";
	// data.idCardNum = "320831199503150013";
	// $location.path("/info");

	$scope.prev = function () {
		$location.path('/select');
	}

});

app.controller("citizenController", function ($scope, $http, $location, data, $timeout) {
	var name = data.stItemName;
	if (name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.stItemName = name;
	clearInterval(t);
	//time();
	$scope.ClearBr = function (key) {
		key = key.replace(/<\/?.+?>/g, "");
		key = key.replace(/[\r\n]/g, "");
		return key;
	}
	// 扫描二维码
	$.device.qrCodeOpen(function (code) {
		var __code = $scope.ClearBr(code);
		$.log.debug("市名云扫描返回结果" + __code);
		$.ajax({///aci/window/getInfoByCodeForLogin.do
			url: urlHost + "/aci/window/getQrCodeInfoByElectronicCert.do",
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data: {
				codeParam: __code
			},
			success: function (dataJsonp) {
				if (dataJsonp.result.success == "true") {
					data.idCardName = dataJsonp.result.data.realname;
					data.idCardNum = dataJsonp.result.data.idcard;
					//data.mobile = dataJsonp.result.data.mobile;
					$timeout(function () {
						$location.path('/info');
					}, 100);
				} else {
					layer.msg(dataJsonp.result.msg);
					$timeout(function () {
						$location.path('/select');
					}, 100);
				}

			},
			error: function (err) {
				$.log.debug("错误返回信息" + JSON.stringify(err));
				alert(err);
				console.log("二维码已过期！")
			}
		});
	}, function (err) {
		console.log("二维码扫描出错：" + err)
	});
	$scope.prev = function() {
		$location.path('/select');
	}
});
app.controller("infoController", function ($scope, $http, $location, data, $timeout) {
	try {
		$.device.Camera_Hide();
		$scope.fullItemName = data.stItemName;
		var name = data.stItemName;
		if (name.length > 10) {
			name = name.slice(0, 10) + '...';
		}
		$scope.itemName = name;
	} catch (e) { };
	clearInterval(t);
	//time();
	$scope.targetTypeName = '个人';
	$scope.targetTips = '申请人身份证号';
	$scope.targetName = '申请人姓名';
	$scope.concel = "false";
	$scope.alertConfirm = function () {
		$scope.isAlert = false;
	}

	// $wathc 监听 targetTypeName 
	$scope.$watch('targetTypeName', function (newValue, oldValue) {
		$scope.targetTypeName == '个人' ? $scope.targetTips = '申请人身份证号' : $scope.targetTips = '统一社会信用代码';
		$scope.targetTypeName == '个人' ? $scope.targetName = '申请人姓名' : $scope.targetName = '企业名称';
		$scope.targetTypeName == '个人' ? $('#targetName').val(data.idCardName) : $('#targetName').val('');
		$scope.targetTypeName == '个人' ? $('#targetNo').val(data.idCardNum) : $('#targetNo').val('');
	});

	$('#username').val(data.idCardName);
	$('#licenseNo').val(data.idCardNum);
	if (data.mobile) {
		$('#mobile').val(data.mobile);
	}

	// 保存数据
	$scope.flag = true;
	$scope.goNext = function () {
		/*$location.path("/materialUpload");*/
		if (!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			if ($scope.targetTypeName == '个人') {
				if ($('#targetName').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的个人姓名！";
					return;
				}
				if (!checkIdCard($('#targetNo').val())) {
					return;
				}

				if ($('#username').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的申请人姓名！";
					return;
				}
				if (!checkIdCard($('#licenseNo').val())) {
					return;
				}

				if (!isPhoneAvailable($('#mobile').val())) {
					layer.msg("请输入正确的手机号码！");
					//$scope.isAlert = true;
					//$scope.msg = "请输入正确的手机号码！";
					return;
				}


			} else if ($scope.targetTypeName == '法人') {
				if ($('#targetName').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的企业名称！";
					return;
				}
				if ($('#targetNo').val().length < 17) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的统一社会信用代码！";
					return;
				}
				if ($('#username').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的申请人姓名！";
					return;
				}
				if (!checkIdCard($('#licenseNo').val())) {

					return;
				}

				if (!isPhoneAvailable($('#mobile').val())) {
					//$scope.isAlert = true;
					//$scope.msg = "请输入正确的手机号码！";
					layer.msg("请输入正确的手机号码！");
					return;
				}

			}
		} while (condFlag);
		data.mobile = $("#mobile").val();
		data.targetName = $('#targetName').val();
		data.projname = $('#projname').val();
		data.targetName = $('#targetName').val();
		data.cotent = $('#content').val();
		var from = $('#infoForm').serialize();
		$.ajax({
			type: "post",
			async: false,
			dataType: "json",
			url: "http://172.16.0.200/main/zzsb/endshenbao",
			data: {
				"lisenseNo": data.idCardNum,
				"item": data.stItemName,
				"stritemid": data.stItemNo,
				"username": data.idCardName,
				"mobile": data.mobile,
				"bzmemo": data.cotent,
				"projname": data.projname,
				"unit": data.targetName

			},
			success: function (dataJson) {
				$scope.flag = true;
				data.caseid = dataJson.caseid;
				console.log(dataJson.caseid + "222222");
				data.uniquecode = dataJson.uniquecode;
				console.log(dataJson.uniquecode + "11111");
				$location.path('/materialList');
			},
			error: function (err) {
				console.log("保存数据失败");
			}
		});
		//	$location.path('/materialList');
		$scope.flag = false;
	};

	$scope.prev = function () {
		$location.path('/select');
	};
});
app.controller("materialListController", function ($scope, $http, $location, data, $timeout) {
	var itemid = data.stItemNo;
	var name = data.stItemName;
	if (name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$.device.fileClose();
	$scope.stItemName = name;
	clearInterval(t);
	//time();
	$scope.current = 0;
	console.log(data.isUpload);
	$scope.commit = false;
	//	 获取材料列表  	
	$.ajax({
		type: "get",
		url: "http://172.16.0.200/main/zzsb/getStuffByItemid",
		async: false,
		dataType: "json",
		data: {
			itemid: data.stItemNo
		},
		success: function (dataJson) {
			$scope.commit = true;
			console.log(dataJson.records);
			$scope.stuffList = dataJson.records;
			if (data.listImg == 0) {
				console.log($scope.stuffList);
				for (var i = 0; i < $scope.stuffList.length; i++) {
					console.log($scope.stuffList[i].WF_CLNAME);
					data.listImg[i] = {
						'activeImg': null,
						'index': i,
						'stuffName': $scope.stuffList[i].WF_CLNAME,
						'upload': true,
						'upload2': false,
					}
				}
			}
			//设置上传文件 按钮变化
			if (data.isUpload != "") {
				for (var i = 0; i < data.isUpload.length; i++) {
					for (var j = 0; j < data.listImg.length; j++) {
						if (data.listImg[j].upload != false) {
							if (data.isUpload[i].stuffName == data.listImg[j].stuffName) {
								data.listImg[data.isUpload[i].index].upload = false;
								data.listImg[data.isUpload[i].index].upload2 = true;
							}
						}
					}
				}
			}
			$scope.listImg = data.listImg;
		},
		err: function (err) {
			console.log("二维码已过期！");
		}
	});


	// 材料上传 
	data.currentIndex++;
	$scope.toUploadMaterial = function (index, WF_BXSC, WF_CLNAME) {
		data.WFBXSC = WF_BXSC;
		data.WFCLNAME = WF_CLNAME;
		data.uptype = "0";
		data.currentIndex = index;
		data.stuffImg = data.listImg[data.currentIndex];
		$location.path("/uploadMethod");
	}
	//重新上传
	$scope.toNewUploadMaterial = function (index, WF_BXSC, WF_CLNAME) {
		for (var i = 0; i < data.isUpload.length; i++) {
			if (index == data.isUpload[i].index) {
				data.isUpload[i] = "";
			}
		}
		data.uptype = "1";
		data.WFBXSC = WF_BXSC;
		data.WFCLNAME = WF_CLNAME;
		data.currentIndex = index;
		data.stuffImg = data.listImg[data.currentIndex];
		$location.path("/uploadMethod");
	}

	//查看
	$scope.view = function (index, WF_CLNAME) {
		data.currentIndex = index;
		data.view = data.isUpload;
		$location.path("/materialView");
	}

	//提交办件
	$scope.submit = function () {
		$location.path('/infoFinish');
	};

	$scope.isScroll = function () {
		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
	$scope.prev = function () {
		$location.path('/info');
	}
});

app.controller("materialUploadController", function ($scope, $http, $location, data, $timeout) {
	var name = data.itemName;
	//	if(name.length > 10) {
	//		name = name.slice(0, 10) + '...'
	//	}
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	clearInterval(t);
	time();
	//上传材料信息
	$scope.stStuffName = data.stuffImg.stStuffName;
	$scope.stuffImg = data.sample;
	$scope.test = function () {
		$('#test').viewer({
			url: 'data-original',
		});
	}
	$scope.upload = function () {
		$location.path("/uploadMethod");
	}

});
app.controller("uploadMethodController", function ($scope, $http, $location, data, $timeout) {
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();
	var name = data.stItemName;
	$.device.fileClose();
	$scope.stItemName = name;
	$scope.statusName = data.WFCLNAME;
	$scope.stItemName = name;
	// 显示材料名称
	$scope.stStuffName = data.stStuffName;
	$scope.stuffNameShow = data.isShowStuffName;
	clearInterval(t);
	//time();
	// 扫描上传
	$scope.scanPhoto = function () {
		$location.path('/finish');
	};
	// U盘上传
	$scope.takePhoto = function () {
		layer.confirm("<em style='color:black'>" + '请确认是否插入U盘！' + "</em>", {
			btn: ['已插入U盘', '未插入U盘'] //按钮
		}, function () {
			$('.layui-layer-shade').hide();
			$('.layui-layer').hide();
			$timeout(function () {
				$location.path('/takePhoto/U');
			}, 20);
		}, function () {
			$('.layui-layer-shade').hide();
			$('.layui-layer').hide();
			$timeout(function () {
				layer.msg('请选择其他上传方式！');
			}, 20);
		});
		//$location.path('/takePhoto/U');
	};
	// 档案库上传
	$scope.materialPic = function () {
		$location.path('/materialPic');
	};
	$scope.prev = function () {
		$location.path('/materialList');
	}
});
//查看上传文件
app.controller("materialViewController", function ($scope, $http, $location, data) {
	$scope.stuffList = [];
	var name = data.stItemName;
	if (name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.statusName = data.WFCLNAME;
	for (var i = 0; i < data.view.length; i++) {
		if (data.currentIndex == data.view[i].index) {
			$scope.stuffList.push(data.view[i]);
		}
	}

	if ($scope.stuffList[0].method === "高拍仪") {
		$scope.scanShow = true;
		$scope.upanShow = false;
		$scope.picShow = false;
	} else if ($scope.stuffList[0].method === "U盘上传") {
		$scope.scanShow = false;
		$scope.upanShow = true;
		$scope.picShow = false;
	} else if ($scope.stuffList[0].method === "个人档案") {
		$scope.scanShow = false;
		$scope.upanShow = false;
		$scope.picShow = true;
	}
	//放大样张
	$scope.showWord = function () {
		$(".word").viewer({
			url: "data-original",
		});
	}
	//放大已上传材料
	$scope.showScan = function () {
		$(".scan").viewer({
			url: "data-original",
		});
	}
	//打开文件
	$scope.open = function () {
		try {
			$.device.officeOpen($scope.stuffList[0].fileName);
		} catch (e) {
			layer.msg("未找到此文件");
		}
	}
	$scope.prev = function () {
		$location.path('/materialList');
	}
});

app.controller("finishController", function ($scope, $http, $location, data, $timeout) {
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();
	var name = data.stItemName;
	$scope.itemName = name;
	if (window.innerWidth > 1600) {
		$.device.cmCaptureShow(700, 480, 90, 340);
	} else {
		$.device.cmCaptureShow(700, 480, 50, 250);
	}
	$.device.cmCaptureSelectRect(0, 0, 1920, 1920);
	$scope.isFinish = '0';
	// 拍照
	var imgHTML = "";
	var imgIndex = 0;
	$scope.next = function () {
		var scanImg = $.device.cmCaptureCaptureUrl();
		//var scanImg = "123.png";
		$scope.jsonData1 = {
			'caseid': data.caseid, //   '751122018600008'
			'uptype': data.uptype,
			"FileExt": "jpg",
			'filename': data.WFCLNAME
		};
		$scope.jsonData1 = JSON.stringify($scope.jsonData1);
		console.log($scope.jsonData1);
		console.log(scanImg);
		//$.log.debug("这是为了测试上传参数:"+$scope.jsonData1);
		//$.log.debug("这是为了测试是否上传成功scanImg:"+scanImg);
		$.device.httpUpload('http://172.16.0.200/main/zzsb/uploadfile', "file", scanImg,
			$scope.jsonData1,
			function (result) {
				data.uptype = "0";
				data.uploadStuffId = data.caseid;
				if (data.listImg.length < 1) {
					data.currentIndex++; // 没有材料列表时   文件下标+1 
				}
				data.isUpload.push({
					index: data.currentIndex,
					stuffName: data.WFCLNAME,
					img: scanImg,
					method: "高拍仪"
				});
				console.log(data.isUpload);
				data.fileName.push('扫描文件');
				$.log.debug("scanImg:" + scanImg);
				imgHTML += '<div class="img" id="' + data.uploadStuffId + '"><img src="' + scanImg + '" width="150" height="200" /></div>';
				$('.imgBox').html(imgHTML);
				$scope.isFinish = '1';
				$scope.$apply();
			},
			function (webexception) {
				layer.msg("上传材料失败!")
				$timeout(function () {
					$location.path('/finish');
				}, 4000);

			});
	};
	// 完成拍照
	$scope.finishUpload = function () {
		$timeout(function () {
			$.device.cmCaptureHide(); // 关闭高拍仪
			$location.path('/materialList');
		}, 20);
	};

	$scope.last = function () {
		$.device.cmCaptureHide(); // 关闭高拍仪
		$location.path('/materialList');
	}
});
app.controller("takePhotoController", function ($scope, $http, $location, data, $timeout) {
	var name = data.stItemName;
	$scope.itemName = name;
	clearInterval(t);
	//time();
	data.lastName = "";
	try {
		$.device.fileOpen(function (value) {
			$timeout(function () {
				$scope.UData = value;//"E://123.txt";// 
			}, 100)

		});
	} catch (e) {
		$timeout(function () {
			layer.msg("请插入U盘后操作");
		}, 10000)
		$location.path("/uploadMethod");
	}
	//$scope.UData ="E://123.txt";
	// U盘上传
	$scope.highCapture = function () {
		if ($scope.UData == null) {
			layer.msg("未获取到U盘数据");
		} else {
			var a = $("#lastName").text();
			//alert("这是从页面上获取的text:"+a);
			data.lastName = a.substring(a.lastIndexOf(".") + 1);
			//alert("查看是否截取成功："+data.lastName);
			$scope.jsonData1 = {
				'caseid': data.caseid,
				'uptype': data.uptype,
				"FileExt": data.lastName,
				'filename': data.WFCLNAME
			};
			$scope.jsonData1 = JSON.stringify($scope.jsonData1);
			console.log($scope.jsonData1);
			console.log($scope.UData);
			$.device.httpUpload("http://172.16.0.200/main/zzsb/uploadfile", "file", $scope.UData,
				$scope.jsonData1,
				function (result) {
					data.uptype = "0";
					layer.msg("上传成功");
					//alert("上传成功！！！");
					data.fileName.push($scope.UData);
					data.isUpload.push({
						index: data.currentIndex,
						stuffName: data.WFCLNAME,
						fileName: $scope.UData,
						method: "U盘上传"
					});

					$timeout(function () {
						$location.path('/materialList');
					}, 5000);
				},
				function (webexception) {
					alert("上传失败！！！");
					layer.msg("上传失败");
				});
		}
	};
	$scope.prev = function () {
		$location.path('/materialList');
	}
});

app.controller("materialPicController", function ($scope, $http, $location, data, $timeout) {
	var name = data.stItemName;
	$scope.itemName = name;

	//var Btn = document.getElementById("consureOnclick");
	//Btn.style.display = "block";
	$scope.imgUrls = "";
	clearInterval(t);
	//time();
	$scope.profileShow = function () {
		$.ajax({
			url: "http://172.16.6.18:8080/aci/autoterminal/dzzz/queryCertBaseData.do",
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				jsonpCallback: "JSON_CALLBACK",
				certNo: data.idCardNum, //"340881199303145313" || 
				type: 0
			},
			success: function (json) {
				var dataJson = eval("(" + JSON.stringify(json) + ")");
				if (dataJson.length == 0) { // !dataJson[0].address
					alert(1)
					layer.msg("没有数据，请重新选择上传方式!");
					alert(2);
					$timeout(function () {
						$location.path('/uploadMethod');
					}, 1000);
				} else {
					$scope.imgUrls = dataJson;
					$scope.$apply();
					console.log($scope.imgUrls)
				}
			},
			error: function (err) {
				console.log(err)
			}
		});
	};
	$scope.profileShow();
	$scope.current = 0;
	$scope.select = function (index) {
		$scope.current = index;
	}
	$scope.isLoding = true;
	$scope.goNext = function () {
		layer.msg("上传中 请稍侯");
		data.selectImg = "http://172.16.6.18:8080" + $scope.imgUrls[$scope.current].pictureUrlForBytes;
		$scope.waitUploadImgUrl = data.selectImg;
		$scope.jsonData = {
			'caseid': data.caseid,
			'uptype': data.uptype,
			"FileExt": "jpg",
			'filename': data.WFCLNAME
		};
		$scope.jsonData = JSON.stringify($scope.jsonData);
		console.log($scope.jsonData);
		$.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		urlHost + 
			$scope.waitUploadImgUrl,
			"C:\\waitUploadImg.jpg",
			//将选中图片下载
			function (bytesCopied, totalBytes) {
				console.log(bytesCopied + "," + totalBytes);
			},
			function (result) {
				//将选中图片上传到服务器
				$.device.httpUpload('http://172.16.0.200/main/zzsb/uploadfile', "file", "C:/waitUploadImg.jpg",
					$scope.jsonData,
					function (result) {
						layer.msg("上传成功");
						data.uptype = "0";
						data.isUpload.push({
							index: data.currentIndex,
							stuffName: data.WFCLNAME,
							img: $scope.waitUploadImgUrl,
							method: "个人档案"
						});
						$timeout(function () {
							$location.path('/materialList');
						}, 1000);
					},
					function (webexception) {
						layer.msg("上传失败");
					});
			},
			function (webexception) {
				alert("下载文档失败");
			}
		);

	};

	$scope.prev = function () {
		$location.path('/materialList');
	}

});
app.controller("infoFinishController", function ($scope, $http, $location, data, $timeout) {
	var lodop = $.device.printGetLodop();
	var name = data.stItemName;
	data.applyNo = "";
	var statusName = data.WFCLNAME;
	$scope.allName = name;
	if (name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	clearInterval(t);
	//time();
	$scope.showPush = false;
	$.ajax({
		type: "post",
		url: "http://172.16.0.200/main/zzsb/submitData",
		dataType: "json",
		async: false,
		data: {
			"caseid": data.caseid
		},
		success: function (dataJson) {
			$scope.showPush = true;
			data.applyNo = dataJson.uniquecode;
			console.log("响应成功！");
		},
		err: function (err) {
			$location.path('/materialList');
			layer.msg("上传失败，请重新上传！");
		}
	});
	$scope.json = {
		"Number": data.uniquecode,
		"item": data.stItemName,
		"name": data.idCardName,
		"phone": data.mobile
	}
	$scope.applyNo = data.applyNo;
	var code = JSON.stringify($scope.json);
	var date = new Date();
	var month = date.getMonth() + 1;
	var dataTime = date.getFullYear() + "-" + month + "-" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
	$.ajax({
		url: "http://172.16.6.18:8080/aci/declare/addCertificateInfo.do",
		type: "post",
		dataType: 'jsonp',
		jsonp: "jsonpCallback",
		async: false,
		data: {
			"ST_URL": code,
			"ST_APPLY_NO": data.uniquecode,
			"ST_ITEM_NAME": data.stItemName,
			"ST_USER_NAME": data.idCardName,
			"MOBILE": data.mobile,
			"DT_DATE": dataTime
		},
		success: function () {
		},
		error: function (err) {
		}
	});
	// 打印凭条

	$scope.print = function () {
		lodop.ADD_PRINT_BARCODE(28, 34, 307, 47, "128A", data.uniquecode);
		lodop.ADD_PRINT_BARCODE(28, 550, 168, 146, "QRCode", data.uniquecode);
		lodop.ADD_PRINT_TEXT(170, 260, 250, 50, "申报提交确认单");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		lodop.ADD_PRINT_TEXT(250, 28, 600, 30, "办件编码：" + data.uniquecode);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(280, 28, 600, 30, "申请单位/申请人：" + data.idCardName);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(310, 28, 600, 30, "申请事项：" + $scope.allName);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(445, 28, 450, 30, "请您携带办理材料按如下步骤进行办理。");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(475, 27, 498, 30, "1）到“自助证照柜”，通过设备自行进行材料提交。");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(505, 27, 60, 30, "或");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(535, 25, 450, 30, "2）到“办事窗口”，通过工作人员进行材料提交。");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(565, 21, 647, 30, "（请在“自助证照柜”首页选择“材料放置”，根据界面提示信息，");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(595, 28, 450, 30, "进行自助材料提交的操作步骤。）");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(800, 551, 168, 30, date.getFullYear() + "年" + month + "月" + date.getDate() + "日");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.PRINT();

	};
	$scope.print();

});

app.controller("qrCodeController", function ($scope, $http, $location, data, $timeout) {
	$scope.applyNo = $location.search().applyNo;
	$scope.isLoding = false;
	$scope.process = function () {
		var pConfig = {
			stApplyNo: $scope.applyNo,
			jsonpCallback: "JSON_CALLBACK"
		}
		$http.jsonp(urlHost + '/aci/autoterminal/eventquery/getApplyInfoByStApplyNo.do', {
			params: pConfig
		}).success(function (dataJson) {
			$scope.itemName = dataJson.stItemName;
			$scope.name = dataJson.stName || dataJson.stUnit;
			$scope.date = dataJson.stApplyStr;
			$scope.status = dataJson.stFinalState;
			$scope.isLoding = true;
		}).error(function (err) {
			console.log('getApplyInfoByStApplyNo error');
		});
	}
	$scope.process();
});