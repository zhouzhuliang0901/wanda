app.controller("main", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.stuffName = perjsonStr;
	$scope.oneItem = oneItem;
	appData.ywlx = "";
	//	$scope.isAlert = true;
	//	$scope.concel = "false";
	//	$scope.msg = "暂停服务";
	//	$scope.alertConfirm = function(){
	//		window.history.go(-1)
	//	}
	$scope.choiceType = function(type, name, ywlx, url) {
		appData.funName = name;
		appData.type = type;
		if(type == "exitAndEntry" || type == "householdRegister" || type == "isCrime") {
			$state.go("guideline");
		}
	}
	$scope.toPrint = function(type, name, ywlx) {
		appData.type = type;
		appData.funName = name;
		appData.ywlx = ywlx;
		$state.go("loginType");
	}
});
//办理须知
app.controller('guideline', function($state, $scope, appData, $state, $location) {
	$scope.funName = appData.funName;
	if(appData.type == null || appData.type == undefined || appData.type == "") {
		appData.type = $location.search().type;
		$scope.funName = appData.funName = "出境入境记录";
	}
	$scope.guideline = "<p>一、申请查询的违法犯罪记录范围为本市公安机关制作或者获取的违法犯罪记录。对已办理市居住证或领取居住登记凭证的境内来沪人员，查询其在本市的违法犯罪记录。线上申请开具有无违法犯罪记录证明，本市户籍人员请选择向户籍所在地派出所申请开具，境内来沪人员请选择向居住地派出所申请开具。</p>" +
		"<p>二、申请查询的违法犯罪记录可在以下四类中选择一至四种：</p>" +
		"<p>1.曾被判处管制、拘役、有期徒刑、无期徒刑、死刑、罚金、剥夺政治权利、没收财产以及已构成犯罪，被人民法院判处免予刑事处罚的犯罪记录，包括缓刑、假释、暂予监外执行等执行方式 </p>" +
		"<p>2.曾被处以收容教育、劳动教养、强制隔离戒毒（强制戒毒）、责令社区戒毒（限期戒毒）的违法信息，包括不执行、暂缓执行、所外执行等执行方式；</p>" +
		"<p>3.曾被处以行政拘留、暂扣或吊销许可证的违法信息；</p>" +
		"<p>4.曾被处以警告、罚款、没收的违法信息，不包括交通类违法信息。</p>" +
		"<p>三、申请人应当填写申请查询违法犯罪记录的起止时间。</p>" +
		"<p>四、根据有关规定，违法时不满18周岁，以及犯罪时不满18周岁、被判处5年有期徒刑以下刑罚人员的相关违法犯罪记录，依法予以封存。</p>" +
		"<p>五、申请开具有无违法犯罪记录证明，应当提交以下材料：</p>" +
		"<p>1.本市户籍人员申请的，应当提交本人有效身份证件；</p>" +
		"<p>2.境内来沪人员申请的，应当提交本人有效居住证或居住登记凭证；</p>" +
		"<p>六、在线申请开具有无违法犯罪记录证明的，公安派出所一般在收到申请之日起1个工作日内开具证明；如经公安派出所初步查询，申请人的违法犯罪记录登记不清、表述不全，需要进行调查核实的，公安派出所一般在收到申请之日起2个工作日内开具证明；</p>";
	//如申请人认为开具的证明中相关违法犯罪记录有错误的，可通过本平台提出异议申请，公安派出所一般在收到异议申请之日起2个工作日内开展调查核实工作，并反馈核查结果，记录确有错误的，重新开具有无违法犯罪记录证明。
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
app.controller('loginType', function($state, $scope, appData, $state) {
	$scope.funName = appData.funName;
	$scope.operation = "请选择登录方式";
	if(appData.type == "" || appData.type == undefined) {
		appData.type = $state.search().type;
	}
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		appData.SwipeType = "sbCard";
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory) {
	$scope.funName = appData.funName;
	$scope.isAlert = false;
	$scope.msg = "";
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
			if(appData.type == "isCrime") {
				if(appData.ywlx == "print") {
					$state.go('isCrimeDetail');
				} else {
					$state.go("isCrimeChoose");
				}
			}
		}
	}

	$scope.idcardLogin = function(info, images) {
		if(info) {
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
		if(appData.type == "isCrime") {
			if(appData.ywlx == "print") {
				$state.go('isCrimeDetail');
			} else {
				$state.go("isCrimeChoose");
			}
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
});
//是否违法犯罪证明
app.controller('isCrimeChoose', function($state, $scope, appData, $http, $interval, $timeout) {
	$scope.funName = appData.funName;
	appData.isUpload = []; //已上传材料
	appData.listImg = []; //需上传材料
	appData.fileName = [];
	appData.itemCode = "312000218000";
	$scope.areaList = areaList;
	$timeout(function() {
		$scope.code = $scope.area.areaCode;
		$scope.getItemApplyPlace("");
	}, 100);
	$scope.getItemApplyPlace = function(code) {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/getItemApplyPlace.do",
			dataType: "jsonp",
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
		$state.go("isCrimeInfo");
	}

	$scope.prevStep = function() {
		$state.go("loginType");
	}
})
//是否违法犯罪证明信息填写
app.controller('isCrimeInfo', function($state, $scope, appData, $http, $interval, $timeout, appFactory) {
	$scope.funName = appData.funName;
	$scope.stName = appData.licenseName;
	$scope.stIdCard = appData.licenseNumber;
	$scope.licenseNumber = appData.encrypt_identity || appData.licenseNumber;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoading = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	console.log(appData.bldCode);
	PublicChoiceById2("license_type");
	$(".form_datetime").datetimepicker({
		format: "yyyy-mm-dd", //显示日期格式
		autoclose: true,
		todayBtn: true,
		minView: "month", //只选择到天自动关闭
		language: 'zh-CN',
		pickerPosition: 'bottom-left',
		endDate: new Date()
	});
	//提交
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(!isPhoneAvailable($('#stMobile').val())) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的手机号！";
				return;
			}
			if($('#dtStartDate').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请填写证明开始时间！";
				return;
			}
			if($('#dtEndDate').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请填写证明结束时间！";
				return;
			}
			if($('#stHjAddress').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请填写户籍地址！";
				return;
			}
			if($('#stLiveAddress').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请填写居住地址！";
				return;
			}
			if($('#stLiveAddress').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请填写居住地址！";
				return;
			}
			if($("#license_type .in").text() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择证明类型！";
				return;
			}
		} while (condFlag);
		//取证明类型编号
		var list = document.getElementsByClassName('in');
		var zmlx = "";
		for(var i = 0; i < list.length; i++) {
			if(list[i].innerHTML != undefined) {
				var id = getZmlx(list[i].innerHTML);
				if(zmlx == "") {
					zmlx = id;
				} else {
					zmlx = zmlx + "," + id
				}
			}
		}
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf+"/aci/criminalRecord/queryCriminalRecord.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				startTime: $('#dtStartDate').val(),
				endTime: $('#dtEndDate').val(),
				accessToken: appData.token,
				idCard: appData.licenseNumber,
				mobile: $scope.stMobile,
				name: appData.licenseName,
				departCode: appData.bldCode,
				nowAddress: encodeURI($scope.stLiveAddress),
				hjAddress: encodeURI($scope.stHjAddress),
				zmlx: zmlx
			},
			success: function(dataJson) {
				if(dataJson.isSuccess == true) {
					appData.applyNo = dataJson.applyNo;
					//上传材料
					appFactory.upload_file($scope.licenseNumber, appData.licenseName, appData.VALIDENDDAY, appData.VALIDSTARTDAY, '310105109000100', appData.applyNo, function(dataJson) {
						if(dataJson.data == "" || dataJson.data == null || dataJson.data == undefined) {
							layer.msg("未能从电子证照获取到身份证照上传");
							$timeout(function() {
								$state.go("materialList");
							}, 100)
						}
						appData.imgStr = $scope.imgUrl = "data:image/jpeg;base64," + dataJson[0].str;
					}, function(dataJson1) {
						if(dataJson1.isSuccess == true) {
							try {
								if(appData.isUpload.length <= 0) {
									appData.isUpload.push({
										index: 0,
										stuffName: "居民身份证",
										img: $scope.imgUrl,
										status: 1,
										method: "高拍仪"
									});
								}
							} catch(e) {}
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
					recordUsingHistory('公安服务', '办理', appData.funName, appData.licenseName, appData.licenseNumber, $scope.stMobile, appData.applyNo, JSON.stringify($scope.jsonStr));
					//行为分析(办理)
					trackEventForAffairs(appData.applyNo, appData.funName, "上海市公安局", appData.licenseName, appData.licenseNumber, $scope.stMobile);
				} else {
					$scope.isAlert = true;
					$scope.msg = dataJson.msg;
					return;
				}
			},
			error: function(err) {
				console.log(err);
			}
		});
	}

	$scope.prevStep = function() {
		$state.go("isCrimeChoose");
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
				machineId: jQuery.getConfigMsg.uniqueId || "",
				itemName: encodeURI("有无违法犯罪记录证明开具"),
				itemCode: "312050035000",
				businessCode: "",
				startDay: appData.VALIDSTARTDAY,
				endDay: appData.VALIDENDDAY,
			},
			success: function(json) {
				$scope.isLoading = true;
				var dataJson = eval("(" + JSON.stringify(json) + ")");
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
			stuffCode: "stuff011",
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
				$.device.httpUpload($.getConfigMsg.preUrlSelf+ '/selfapi/uploadItemStuffs.do', "FileData", "C:/waitUploadImg.jpg",
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
				stuffCode: "stuff011",
				stuffId: "",
			};
			$scope.jsonData1 = JSON.stringify($scope.jsonData1);
			$.device.httpUpload($.getConfigMsg.preUrlSelf+ "/selfapi/uploadItemStuffs.do", "FileData", $scope.UData,
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
	if(window.innerWidth <= 1600) {
		$.device.cmCaptureShow(430, 420, 380, 150);
	} else {
		$.device.cmCaptureShow(680, 530, 190, 300);
	}
	$.device.cmCaptureSelectRect(0, 0, 1920, 1920);
	$scope.isFinish = false;
	// 拍照
	var imgHTML = "";
	var imgIndex = 0;
	var scanImg1 = "";
	$scope.next = function() {
		$scope.isLoading = false;
		var scanImg = "";
		$.device.cmCaptureCaptureUrl(function(path) {
			scanImg = path;
		});
		$.device.cmCaptureCaptureBase64(function(val) {
			scanImg1 = val;
		});
		if(scanImg == undefined) {
			$scope.isAlert = true;
			$scope.msg = "请聚焦并对准材料后再拍照";
			$scope.alertConfirm = function() {
				$scope.isAlert = false;
			}
		} else {
			$scope.jsonData1 = {
				applyNo: appData.applyNo,
				stuffCode: "stuff011",
				stuffId: "",
			};
			$scope.jsonData1 = JSON.stringify($scope.jsonData1);
			$.device.httpUpload($.getConfigMsg.preUrlSelf+ "/selfapi/uploadItemStuffs.do", "FileData", scanImg,
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
		$scope.last(); // 关闭高拍仪
	};

	$scope.last = function() {
		$.device.cmCaptureHide(); // 关闭高拍仪
		$state.go("materialList");
	}
});
app.controller("materialList", function($scope, $state, $http, appData, $timeout, appFactory) {
	if(appData.isUpload.length > 0) {
		for(var i = 0; i < appData.isUpload.length; i++) {
			if(appData.isUpload[i].status == 1) {
				$scope.show = true;
				$scope.show1 = false;
			} else if(appData.isUpload[i].status == 0) {
				$scope.show1 = true;
				$scope.show = false;
			}
		}
	} else {
		$scope.show = false;
		$scope.show1 = false;
	}
	$scope.nextText = "提交";
	$scope.funName = appData.funName;
	//必传材料列表
	appData.currentIndex = 0;
	$scope.mustUpload = [];
	$scope.current = 0;
	$scope.mustUpload.push({
		'index': 0,
		'stuffName': "居民身份证"
	});
	appData.listImg = {
		'index': 0,
		'stuffName': "居民身份证"
	};

	console.log(appData.isUpload);

	// 材料上传
	appData.currentIndex++;
	$scope.toUploadMaterial = function() {
		appData.stStuffName = "居民身份证";
		appData.currentIndex = 0;
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
app.controller('infoFinish', function($state, $scope, appData, $http, $interval, $timeout) {
	$scope.funName = appData.funName;
	$scope.itemName = appData.funName;
	$scope.applyNo = appData.applyNo;
	$scope.nextText = "返回首页";
	$scope.submit = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf+ "/selfapi/submitItem.do",
			dataType: "jsonp",
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
//违法犯罪记录查询方式
//app.controller("isCrimeSelectMethod", function($scope, appData, $state, $rootScope) {
//	$scope.funName = appData.funName;
//	removeAnimate($('.linkBox1'))
//	$scope.operation = "请选择查询方式";
//	addAnimate($('.linkBox1'))
//	$scope.choiceType = function(type) {
//		appData.inputType = type;
//		$state.go("isCrimeInput");
//	}
//
//	$scope.prevStep = function() {
//		$.device.idCardClose();
//		$.device.qrCodeClose();
//		$.device.GoHome();
//	}
//});
//app.controller("isCrimeInput", function($scope, appData, $state, appFactory, $http, $rootScope) {
//	$scope.funName = appData.funName;
//	$scope.operation = "请刷身份证";
//	$scope.inputType = appData.inputType;
//	$scope.applySerial = "";
//	$scope.concel = "false";
//	$scope.alertConfirm = function() {
//		$scope.isAlert = false;
//	}
//	$scope.serialSearch = function() {
//		if($scope.applySerial == '') {
//			$scope.isAlert = true;
//			$scope.msg = "办件编码不能为空!";
//		} else {
//			appData.applyNo = $scope.applySerial;
//			$state.go('isCrimeDetail');
//		}
//	};
//	$scope.codeSearch = function() {
//		$.device.qrCodeOpen(function(code) {
//			appData.applyNo = code.replace(/\r\n/g, "");
//			$state.go('isCrimeDetail');
//		});
//	};
//	switch($scope.inputType) {
//		case "serial":
//			$scope.operation = "请输入办件编码";
//			break;
//		case "code":
//			$scope.operation = "请扫描二维码";
//			$scope.codeSearch();
//			break;
//	}
//
//	$scope.prevStep = function() {
//		$state.go("main");
//	}
//});
//违法犯罪记录查看
app.controller('isCrimeDetail', function($state, $scope, appData, $http, $interval, $timeout) {
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
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/humanSociety/humanSocietyPrint.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				machineId: jQuery.getConfigMsg.uniqueId || "",
				itemName: "有无违法犯罪记录证明开具",
				itemCode: "312050035000",
				businessCode: applyNo
			},
			success: function(dataJson) {
				console.log(dataJson);
				$scope.isLoading = false;
				if(dataJson.SUCCESS == "TRUE") {
					$scope.previewImg = $scope.configUrl + dataJson.PNGURL;
					appData.pdfFile = $scope.configUrl + dataJson.PDFURL;
				} else if(dataJson.SUCCESS == "FALSE") {
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
				$scope.msg = "未查询到您的犯罪证明记录，请重试";
				$scope.alertConfirm = function() {
					$state.go("loginType");
				}
			}
		});
	}
	//查询犯罪记录证明办件的记录
	$scope.queryApplyInfoOfCriminalRecord = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf+ "/aci/criminalRecord/queryApplyInfoOfCriminalRecord.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				idCard: appData.licenseNumber
			},
			success: function(dataJson) {
				console.log(dataJson);
				$scope.isLoading = false;
				if(dataJson.SUCCESS == "true") {
					appData.applyNo = dataJson.applyNo;
					$scope.showStuffPicForBytes(dataJson.applyNo);
				} else if(dataJson.SUCCESS == "false") {
					$scope.isAlert = true;
					$scope.msg = dataJson.MSG;
					$scope.alertConfirm = function() {
						$state.go("main");
					}
				}
			},
			error: function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "未查询到您的犯罪证明记录，请重试";
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
		$.device.urlPdfPrint(appData.pdfFile, $scope.path, function() {});
		$timeout(function() {
			$state.go("main");
		}, 3000)
	}
})
