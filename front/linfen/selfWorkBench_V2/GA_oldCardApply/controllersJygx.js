app.controller("guidelineJygxController", function($scope, $route, $http, $location, $rootScope, $sce, data, $timeout, appFactory) {
	data.itemName = "跨省异地就医登记备案";
	var name = data.itemName;
	$scope.itemName = name;
	$scope.description = data.description;
	$scope.statusName = data.statusName;
	$scope.ItemStuffList = "";
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.guideInfo = {
		clName: "<p>跨省异地就医登记备案 </p >", //事项名称
		clDept: "市医保局", //主管部门
		clApplyConds: "需要办理就医关系转移至外省市的本市职保退休人员、居保人员", //申报条件
		clApprovalMater: "<p>申请对象需带齐以下材料原件：</p ><p>&nbsp;&nbsp;1、	有效身份证；</p >",
		clApprovalProcess: "<p>1、该项业务全市通办，申请对象携带相关材料至就近的街(镇)社区事务受理服务中心办理；</p ><p>2、材料齐全，无特殊情况当场办结；</p ><p>3、上海市医疗保险咨询热线：962218。", // 办理流程</p >, // 办理流程
		clChargeStd: "<p>免费</p >"
	};
	$scope.print = function() {
		$scope.isAlert = true;
		$scope.msg = "正在打印...";
		var lodop = $.device.printGetLodop('');
		lodop.ADD_PRINT_TEXT(40, 354, 250, 50, "办事指南");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		lodop.SET_PRINT_STYLEA(0, "BOLD", 1);
		lodop.ADD_PRINT_HTM(70, 45, "100%", "100%", "<style> dd{width:620px;} dt{font-weight:bold}</style><body>" + document.getElementById("scrollBox2").innerHTML + "</body>");
		lodop.PRINT();
		$timeout(function() {
			$scope.isAlert = false;
		}, 3000);
	};

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
	$scope.next = function() {
		data.clDept = $scope.guideInfo.clDept;
		$location.path("/select");
		//$location.path("/materialJygxList");
	}
	$scope.prev = function() {
		window.location.href = "../declare/index.html";
	}
	$rootScope.goHome = function() {
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
	};
});
app.controller("infoJygxController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
	$.device.Camera_Hide();
	$.device.idCardClose();
	var name = data.itemName;
	$scope.itemName = name;
	$scope.nextText = "提交";
	$scope.isAlert = false;
	$timeout(function() {
		$(".form_datetime").datetimepicker({
			format: "yyyy-mm-dd", //显示日期格式
			autoclose: true,
			todayBtn: true,
			minView: "month", //只选择到天自动关闭
			language: 'zh-CN',
			pickerPosition: 'bottom-left',
		});
	}, 300)
	City1();
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//其中医保办法为“职保退休”的人员，若为“本市转外地”状态，系统先弹框提示
	if(data.ybbf == "00" && data.jygx == "0") {
		$scope.isAlert = true;
		$scope.msg = "职保退休人员因涉及账户资金处理，只可在一网通办上办理就医关系转出，如需办理转入请至区医保中心";
	}
	$scope.isLoading = true;
	$scope.info = function() {
		$scope.stRelationship = data.jygxsm;
		if($scope.stRelationship == "本市") {
			$scope.stTransferType = "本市转外地";
			$scope.stTransferTypeId = "2";
		} else if($scope.stRelationship == "外地") {
			$scope.stTransferType = "外地转本市";
			$scope.stTransferTypeId = "1";
		}
		$scope.stMeasure = data.ybbfsm;
		$scope.stName = data.idCardName;
		$scope.stIdCard = data.idCardNum;
		$scope.stSex = ((parseInt(data.idCardNum.substring(16, 17)) % 2) == 0) ? "女" : "男";
		$scope.stBirth = (data.idCardNum).substring(6, 10) + "-" + (data.idCardNum).substring(10, 12) + "-" + (data.idCardNum).substring(12, 14);
		$scope.stMobile = data.mobile || "";
		$scope.next1 = function() {
			var nConfig = {
				jsonpCallback: "JSON_CALLBACK",
				identNo: data.idCardNum,
				catMainCode: "310105105000100",
								machineId: $.config.get('uniqueId'),
				itemName: "就医关系",
				itemCode: "",
				businessCode: "",
				name: data.idCardName,
				startDay: data.VALIDSTARTDAY,
				endDay: data.VALIDENDDAY,
			}
			//女方户口本信息
			var womenLicense = $.ajax({
				type: "get",
				url: urlHost + "/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do",
				/*url写异域的请求地址*/
				dataType: "json",
				/*加上datatype*/
				timeout: 5000,
				jsonp: "jsonpCallback",
				/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
				data: nConfig,
				success: function(dataJsonp) {
					$scope.isLoading = true;
					$scope.stLiveAddress = dataJsonp.SADDR;
					$scope.stLivePostCode = "200000";
				},
				error: function(err) {
					$scope.isLoading = true;
					console.info("queryMarriageCertificateInfo error");
				},
				complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
					　　　　
					if(status == 'timeout') { //超时,status还有success,error等值的情况
						　　　　　
						womenLicense.abort();　　　　　
						$scope.next1();　　　　
					}　　
				}
			});
		}
		//身份证信息
		$scope.next = function() {
			var manIdcard = $.ajax({
				type: "get",
				url: urlHost + "/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do",
				/*url写异域的请求地址*/
				dataType: "json",
				timeout: 5000,
				/*加上datatype*/
				jsonp: "jsonpCallback",
				/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
				data: {
					identNo: data.idCardNum,
					catMainCode: "310105109000100",
									machineId: $.config.get('uniqueId'),
				itemName: "就医关系",
				itemCode: "",
				businessCode: "",
				name: data.idCardName,
				startDay: data.VALIDSTARTDAY,
				endDay: data.VALIDENDDAY,
				},
				success: function(dataJsonp) {
					console.info(dataJsonp);
					if(dataJsonp.SETHNIC.lastIndexOf('族') < 0) {
						dataJsonp.SETHNIC = dataJsonp.SETHNIC + '族';
					}
					setSelectCheckedByText("nations", dataJsonp.SETHNIC);
					$timeout(function() {
						$scope.next1();
					}, 300);
				},
				error: function(err) {
					console.info("queryMarriageCertificateInfo error");
					$timeout(function() {
						$scope.next1();
					}, 300);
				},
				complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
					　　　　
					if(status == 'timeout') { //超时,status还有success,error等值的情况
						　　　　　
						manIdcard.abort();　　　　　
						$scope.next();　　　　
					}　　
				}
			})
		}
		//$scope.next();
	}
	$scope.info();
	PublicchoiceById('stEducation');
	$scope.isp = function() {
		if($('#stEducation .in').text() == "异地安置退休人员") {
			$scope.jsyy = 1;
		} else if($('#stEducation .in').text() == "异地长期居住人员") {
			$scope.jsyy = 2;
		}
	}
	//提交
	$scope.ifAlready = function() {
		$scope.isp();
		var zConfig = {
			jsonpCallback: "JSON_CALLBACK",
			json: {
				zhh: data.zhh,
				xm: $scope.stName,
				sfzh: $scope.stIdCard,
				ydss: $('#province').attr('vName'),
				ydsz: $('#city').attr('vName'),
				ydxq: $('#county').attr('vName'),
				yddz: $scope.stAddress,
				ydyb: $scope.stPostCode,
				jsyy: $scope.jsyy,
				jzdz: $scope.stLiveAddress,
				jzyb: $scope.stLivePostCode,
				lxdh: $scope.stMobile,
				mobile: $scope.stMobile,
				wtrxm: "",
				wtrsfzh: "",
				djlb: "00",
				mzid: $('#nations').attr('vName'),
				ydssdm: $('#province').attr('vId'),
				ydszdm: $('#city').attr('vId'),
				qsrq: "",
				zzrq: "",
				jygx: data.jxgx
			}
		}
		console.log(zConfig);
		$http.jsonp(urlHost + "/aci/workPlatform/ybj/applySubmit.do", {
			params: zConfig
		}).success(function(dataJson) {
			console.log(dataJson);
		}).error(function(err) {
			console.log(err);
		});
	}
	$scope.prevStep = function() {
		$location.path("/select");
	}
	// 保存数据
	$scope.flag = true;
	$scope.next = function() {
		if(!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			if($('#nations').attr('vName') < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择民族！";
				return;
			}
			if($('#province').attr('vName') < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择异地结算目的地省份！";
				return;
			}
			if($('#city').attr('vName') < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择异地结算目的地城市！";
				return;
			}
			if($('#county').attr('vName') < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择异地结算目的地省份区/县！";
				return;
			}
			if($('#stRecipient').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入收件人姓名！";
				return;
			}
			if($('#stRecipientMomile').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入收件人电话！";
				return;
			}
			if($('#stRecipientPostCode').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入收件地址邮编！";
				return;
			}
			if($('#stRecipientPostAddress').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入收件地址！";
				return;
			}
		} while (condFlag);
		$scope.flag = false;
		data.mobile = $('#stMobile').val();
		$scope.ifAlready();
		//判断是否有身份证照
		$scope.nextload = function() {
			$scope.isLoading = false;
			appFactory.pro_fetch(data.idCardNum,data.idCardName,'就医关系',data.VALIDSTARTDAYTRAT,data.VALIDENDDAY, '310105109000100', '', '', '', '1', function(dataJson) {
				try {
					if(!dataJson) {
						$scope.isAlert = true;
						$scope.msg = "没有该证照!";
					}
					data.imgStr2 = $.getConfigMsg.preUrl + dataJson[0].pictureUrl;
					$scope.imgUrl = $.getConfigMsg.preUrl + dataJson[0].pictureUrlForBytes;
					if(data.isUpload.length <= 1) {
						data.isUpload.push({
							index: 0,
							stuffName: "居民身份证",
							img: $scope.imgUrl,
							status: 1,
							method: "高拍仪"
						});
					}
				} catch(e) {}
				$location.path("/materialJygxList");
				$scope.$apply();
			}, function(status) {
				if(status == 'timeout') {　　　　　
					$scope.nextload();　　　　
				}
			}, function(dataJson) {
				data.imgId = dataJson.rtnData.imgid;
			})
		}
		//$scope.nextload();
	};
	//滚动条
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
});
app.controller("materialJygxListController", function($scope, $route, $http, $location, $rootScope, data, $timeout, appFactory) {
	if(data.isUpload.length > 0) {
		for(var i = 0; i < data.isUpload.length; i++) {
			if(data.isUpload[i].status == 1) {
				$scope.show = true;
				$scope.show1 = false;
			} else if(data.isUpload[i].status == 0) {
				$scope.show1 = true;
				$scope.show = false;
			}
		}
	} else {
		$scope.show = false;
		$scope.show1 = false;
	}
	$scope.nextText = "提交";
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$rootScope.isAlert = false;
	//必传材料列表
	data.currentIndex = 0;
	$scope.mustUpload = [];
	$scope.current = 0;
	$scope.mustUpload.push({
		'index': 0,
		'stuffName': "居民身份证"
	});
	data.listImg = {
		'index': 0,
		'stuffName': "居民身份证"
	};
	console.log(data.isUpload);

	//材料上传
	data.currentIndex++;
	$scope.toUploadMaterial = function() {
		data.stStuffName = "居民身份证";
		data.currentIndex = 0;
		data.stuffImg = data.listImg[data.currentIndex];
		$location.path("/uploadMethod");
	}
	$scope.prevStep = function() {
		$location.path("/infoJygx");
	}
	//查看
	$scope.view = function() {
		data.currentIndex = 0;
		data.view = data.isUpload;
		$location.path("/materialView");
	}

	//提交办件
	$scope.submit = function() {
		$location.path('/infoFinish');
	};
});