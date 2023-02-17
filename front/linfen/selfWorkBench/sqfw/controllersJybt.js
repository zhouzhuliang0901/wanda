app.controller("guidelineJybtController", function($scope, $route, $http, $location, $rootScope, $sce, data, $timeout, appFactory) {
	data.itemName = "灵活就业两项补贴";
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
		clName: "<p>灵活就业两项补贴 </p >", //事项名称
		clDept: "市人社局", //主管部门
		clApplyConds: "<p>申请“&nbsp;&nbsp;就业困难人员 ”灵活就业社会保险费补贴的人员应同时具备下列条件：</p ><p>&nbsp;&nbsp;1、已经认定为“&nbsp;&nbsp;就业困难人员 ”；</p ><p>&nbsp;&nbsp;2、已经办妥灵活就业备案登记手续；</p ><p>&nbsp;&nbsp;3、已经办理灵活就业人员缴纳社会保险费手续。</p ><p>申请大龄失业就业岗位补贴的人员应同时具备下列条件：</p ><p>&nbsp;&nbsp;1、按规定缴纳失业保险费满一年或1998年10月前单位劳动经历满1年；</p ><p>&nbsp;&nbsp;2、距法定退休年龄三年或不足三年（符合特殊工种提前退休规定的，必须已在本市社会保险经办机构办理有关特殊工种工作年限的确认手续），符合领取失业保险金的条件（含延长领取失业保险金）；</p ><p>&nbsp;&nbsp;3、已经办理灵活就业（自谋职业或自主创业）就业登记备案手续。",
		clApprovalMater: "<p>申请对象需提供以下材料的原件：</p ><p>&nbsp;&nbsp;1、本人有效居民身份证原件；</p ><p>&nbsp;&nbsp;2、实名制银行结算户借记卡或存折原件；</p ><p>&nbsp;&nbsp;3、本人《就业创业证》（或《就业失业登记证》、或《劳动手册》）原件。</p >",
		clApprovalProcess: "<p>【本事项可“&nbsp;&nbsp;全市通办 ”】符合申请条件的人员，可通过自助服务设备等渠道提交申请，街道、镇社区事务受理服务中心会对申请进行处理。</p ><p>如“&nbsp;&nbsp;就业困难人员 ”灵活就业社会保险费补贴申请通过，补贴（2019年补贴金额为875元）在社会保险经办机构收到“&nbsp;&nbsp;就业困难人员 ”社会保险缴费的次月，发放到申请人员提供的银行账户；</p ><p>如大龄失业就业岗位补贴申请通过，补贴（2019年补贴金额为1240元）将于次月起每月15日后发放到申请人员提供的银行账户；</p ><p>申请未通过的，工作人员告知不能申请的原因。</p >",
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
		//$location.path("/materialJybtList");
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
app.controller("infoJybtController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
	$.device.Camera_Hide();
	$.device.idCardClose();
	var name = data.itemName;
	$scope.itemName = name;
	//	$(".form_datetime").datetimepicker({
	//		format: "yyyy-mm-dd", //显示日期格式
	//		autoclose: true,
	//		todayBtn: true,
	//		minView: "month", //只选择到天自动关闭
	//		language: 'zh-CN',
	//	});
	City();
	shouliCenter();
	PublicChoiceForJob('stType');
	PublicChoiceForJob('bank');
	$scope.SisAlert = false;
	$scope.concel = "false";
	$scope.nextText = "提交";
	$scope.SalertConfirm = function() {
		$scope.SisAlert = false;
	}
	$scope.info = function() {
		$scope.isLoading = false;
		$scope.stName = data.idCardName;
		$scope.stIdCard = data.idCardNum;
		$scope.stSex = ((parseInt(data.idCardNum.substring(16, 17)) % 2) == 0) ? "女" : "男";
		$scope.dtBirth = (data.idCardNum).substring(6, 10) + "-" + (data.idCardNum).substring(10, 12) + "-" + (data.idCardNum).substring(12, 14);
		$scope.stMomile = data.mobile || "";
		$scope.next1 = function() {
			//男方户口本
			var nConfig = {
				identNo: data.encrypt_identity || data.idCardNum,
				catMainCode: "310105105000100",
				machineId: $.config.get('uniqueId'),
				itemName: "就业补贴",
				itemCode: "",
				businessCode: "",
				name: encodeURI(data.idCardName),
				startDay: data.VALIDSTARTDAY,
				endDay: data.VALIDENDDAY,
			}
			//男方户口本信息
			var manLicense = $.ajax({
				type: "get",
				url: $.getConfigMsg.preUrlSelf + "/selfapi/DZCert/getCertOriginalData.do",
				/*url写异域的请求地址*/
				dataType: "jsonp",
				timeout: 5000,
				/*加上datatype*/
				jsonp: "jsonpCallback",
				/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
				data: nConfig,
				success: function(dataJsonp) {
					console.info(dataJsonp);
					$scope.isLoading = true;
					setCenter();
					$scope.hjc = (dataJsonp.SADDR.split('市')[1]).split('区')[0];
					$scope.hjs = (dataJsonp.SADDR.split('市')[1]).split('区')[1];
					setHjAdressText('province', 'city', 'county', 'street', '', "上海市", "上海市", $scope.hjc + '区', $scope.hjs.split('镇')[0] + '镇' || "", '');
					setHjAdressText('liveProvince', 'liveCity', 'liveCounty', 'liveStreet', "上海市", '', "上海市", $scope.hjc + '区', $scope.hjs.split('镇')[0] + '镇' || "", '');
					$scope.ads = $scope.hjs.split('镇')[1];
					$scope.stAddress = $scope.ads;
					$scope.liveStAddress = $scope.ads;
				},
				error: function(err) {
					$scope.isLoading = true;
					setCenter();

					console.info("queryMarriageCertificateInfo error");
				},
				complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
					　　　　
					if(status == 'timeout') { //超时,status还有success,error等值的情况
						　　　　　
						manLicense.abort();　　　　　
						$scope.next1();　　　　
					}　　
				}
			});
		}
		$scope.next2 = function() {
			//身份证信息
			var manIdcard = $.ajax({
				type: "get",
				url: $.getConfigMsg.preUrlSelf + "/selfapi/DZCert/getCertOriginalData.do",
				/*url写异域的请求地址*/
				dataType: "jsonp",
				timeout: 5000,
				/*加上datatype*/
				jsonp: "jsonpCallback",
				/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
				data: {
					identNo: data.encrypt_identity || data.idCardNum,
					catMainCode: "310105109000100",
					machineId: $.config.get('uniqueId'),
					itemName: "就业补贴",
					itemCode: "",
					businessCode: "",
					name: encodeURI(data.idCardName),
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
						$scope.next2();　　　　
					}　　
				}
			})
		}
		$scope.next2();
	}
	$scope.info();
	$scope.isLoading = true;
	$scope.synchronize = function() {
		if($('#liveProvince').attr('vName') == "上海市") {
			$('#stRecipientPostCode').val("200000");
		}
		$scope.lStreet = ($('#liveStreet').attr('vName') == "选择街/镇") ? "" : $('#liveStreet').attr('vName');
		$scope.address = $('#liveProvince').attr('vName') + $('#liveCity').attr('vName') + $('#liveCounty').attr('vName') +
			$scope.lStreet + (($scope.liveStAddress == undefined) ? "" : $scope.liveStAddress);
		$('#stRecipientPostAddress').val($scope.address);
	}

	$scope.prevStep = function() {
		$location.path("/select");
	}
	// 保存数据
	$scope.flag = true;
	$scope.next = function() {
		data.infoJybt = {
			stName: $scope.stName,
			stSex: $scope.stSex,
			stIdCard: $scope.stIdCard,
			stMobile: $scope.stMomile,
			birth: $scope.dtBirth,
			nation: $('#nations').attr('vName'),
			nid: $('#nations').attr('vId'),
			province: $('#province').attr('vName'),
			pid: $('#province').attr('vId'),
			city: $('#city').attr('vName'),
			cid: $('#city').attr('vId'),
			street: $('#street').attr('vName'),
			sid: $('#street').attr('vId'),
			county: $('#county').attr('vName'),
			coid: $('#county').attr('vId'),
			liveProvince: $('#liveProvince').attr('vName'),
			lpid: $('#liveProvince').attr('vId'),
			liveCity: $('#liveCity').attr('vName'),
			lcid: $('#liveCity').attr('vId'),
			liveCounty: $('#liveCounty').attr('vName'),
			lcoid: $('#liveCounty').attr('vId'),
			liveStreet: $('#liveStreet').attr('vName'),
			lsid: $('#liveStreet').attr('vId'),
			address: $('#stRecipientPostAddress').val(),
			stRecipient: $scope.stRecipient,
			stRecipientMomile: $scope.stRecipientMomile,
			stRecipientPostCode: $('#stRecipientPostCode').val(),
		}
		//提交所需字段
		data.nations = $('#nations').attr('vId');
		data.centerCode = $('#stCenter').attr('vId');
		data.centerAddress = $('#stCenter').attr('vName');
		$scope.lStreet = ($('#liveStreet').attr('vName') == "选择街/镇") ? "" : $('#liveStreet').attr('vName');
		$scope.address = $('#liveProvince').attr('vName') + $('#liveCity').attr('vName') + $('#liveCounty').attr('vName') +
			$scope.lStreet + (($scope.stLxAddress == undefined) ? "" : $scope.stLxAddress);
		data.address = $scope.address;
		data.mobile = $('#stMomile').val();
		data.sex = $('#stSex').val();
		data.date = $scope.date;
		data.jzAddress = {
			liveProvince: $('#liveStreet').attr('vId'),
			liveCity: $('#liveCity').attr('vId'),
			liveCounty: $('#liveCounty').attr('vId'),
			liveStreet: $('#liveStreet').attr('vId')
		}
		data.hjAddress = {
			province: $('#province').attr('vId'),
			city: $('#city').attr('vId'),
			county: $('#county').attr('vId'),
			street: $('#street').attr('vId')
		}
		data.jzAddressName = {
			liveProvince: $('#liveProvince').attr('vName'),
			liveCity: $('#liveCity').attr('vName'),
			liveCounty: $('#liveCounty').attr('vName'),
			liveStreet: $('#liveStreet').attr('vName')
		}
		data.hjAddressName = {
			province: $('#province').attr('vName'),
			city: $('#city').attr('vName'),
			county: $('#county').attr('vName'),
			street: $('#street').attr('vName')
		}
		data.bankType = $scope.bankType === "硬卡（磁卡）" ? "2" : "1";
		data.bankNum = $scope.bankNum;
		data.bankId = $(".in li").attr('vId');
		data.bankName = $(".in li").text();
		data.bankTypeName = $scope.bankType === "硬卡（磁卡）" ? "硬卡" : "软卡";
		console.log(data.infoJybt);
		if(!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			if($('#nations').attr('vName') < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请选择民族！";
				return;
			}
			if($('#province').attr('vName') < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请选择户籍省份！";
				return;
			}
			if($('#city').attr('vName') < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请选择户籍城市！";
				return;
			}
			if($('#county').attr('vName') < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请选择户籍区/县！";
				return;
			}
			if($('#liveProvince').attr('vName') < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请选择居住省份！";
				return;
			}
			if($('#liveCity').attr('vName') < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请选择居住城市！";
				return;
			}
			if($('#liveCounty').attr('vName') < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请选择居住区/县！";
				return;
			}
			if($('#stRecipient').val() < 1 && $('#stRecipient').prop('disabled') == false) {
				$scope.SisAlert = true;
				$scope.Smsg = "请输入收件人姓名！";
				return;
			}
			if($('#stRecipientMomile').val() < 1 && $('#stRecipientMomile').prop('disabled') == false) {
				$scope.SisAlert = true;
				$scope.Smsg = "请输入收件人电话！";
				return;
			}
			if($('#stRecipientPostCode').val() < 1 && $('#stRecipientPostCode').prop('disabled') == false) {
				$scope.SisAlert = true;
				$scope.Smsg = "请输入收件地址邮编！";
				return;
			}
			if($('#stRecipientPostAddress').val() < 1 && $('#stRecipientPostAddress').prop('disabled') == false) {
				$scope.SisAlert = true;
				$scope.Smsg = "请输入收件地址！";
				return;
			}
			if($('#stCenterCounty').val() < 1 && $('#stCenterCounty').prop('disabled') == false) {
				$scope.SisAlert = true;
				$scope.Smsg = "请选择受理中心地点！";
				return;
			}
			if($('#stCenter').val() < 1 && $('#stCenter').prop('disabled') == false) {
				$scope.SisAlert = true;
				$scope.Smsg = "请选择受理中心地点！";
				return;
			}
		} while (condFlag);
		$scope.flag = false;
		data.mobile = $('#stMomile').val();
		data.centerCode = $('#stCenter').attr('vId');
		data.centerAddress = $('#stCenter').attr('vName');
		$scope.idCardNum = data.encrypt_identity || data.idCardNum;
		//判断是否有身份证照
		$scope.nextload1 = function() {
			$scope.isLoading = false;
			appFactory.pro_fetch($scope.idCardNum, data.idCardName,'就业补贴',data.VALIDSTARTDAYTRAT,data.VALIDENDDAY,'310105109000100', 'YJS00010003', 'YJS0001', '居民身份证', '1', function(dataJson) {
				if(!dataJson) {
					$scope.SisAlert = true;
					$scope.Smsg = "没有该证照!";
				}
				try {
					data.imgStr = $.getConfigMsg.preUrl + dataJson[0].pictureUrl;
					$scope.imgUrl = $.getConfigMsg.preUrl + dataJson[0].pictureUrlForBytes;
					if(data.isUpload.length <= 0) {
						data.isUpload.push({
							index: 0,
							stuffName: "1、居民身份证",
							img: $scope.imgUrl,
							status: 1,
							method: "高拍仪"
						});
					}
				} catch(e) {}
				$location.path("/materialJybtList");
				$scope.$apply();
			}, function(status) {
				if(status == 'timeout') {　　　　　
					$scope.nextload1();　　　　
				}
			}, function(dataJson) {
				data.imgId = dataJson.rtnData.imgid;
			})
		}
		$scope.nextload1();
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
app.controller("materialJybtListController", function($scope, $route, $http, $rootScope, $location, data, $timeout, appFactory) {
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
		'stuffName': "1、居民身份证"
	});
	data.listImg = {
		'index': 0,
		'stuffName': "1、居民身份证"
	};
	console.log(data.isUpload);

	//材料上传
	data.currentIndex++;
	$scope.toUploadMaterial = function(index, id, code, name) {
		data.stStuffName = "1、居民身份证";
		data.archivescode = 'RS1099_010001';
		data.affairscode = 'RS1099';
		data.needflag = '0';
		data.currentIndex = 0;
		data.stuffImg = data.listImg[data.currentIndex];
		$location.path("/uploadMethod");
	}

	//查看
	$scope.view = function() {
		data.currentIndex = 0;
		data.view = data.isUpload;
		$location.path("/materialView");
	}
	//上一步
	$scope.prevStep = function() {
		$location.path("/infoJybt");
	}
	//提交办件
	$scope.submit = function() {
		$location.path('/signatureJybt');
	};
});
app.controller("signatureJybtController", function($scope, $http, $location, $rootScope, data, appFactory) {
	$rootScope.isAlert = false;
	$scope.$watch('$viewContentLoaded', function(newValue, oldValue) {
		var name = data.itemName;
		$scope.itemName = name;
		$scope.signature = null;
		$scope.signatureFlag = false;
		$scope.SignatureBoardPlug = new SignatureBoardPlug({
			canvas: "#signature",
			clearBtn: ".clearRect",
			getSigntrue: ".saveImg",
			color: "black"
		});
	});

	$scope.isSignature = function() {
		$scope.signatureFlag = true;
	};
	$scope.notSignature = function() {
		$scope.signatureFlag = false;
	}
	$scope.saveSignature = function() {
		$scope.signature = $scope.SignatureBoardPlug.Signatrue;
		console.log($scope.SignatureBoardPlug);
		if($scope.signatureFlag === false) {
			alert("请先在屏幕上签名!");
			return;
		}
		data.picStr = $scope.signature.split(",")[1];
		$location.path("/infoFinish");
	};
	$scope.prev = function() {
		$location.path("/materialJybtList");
	}
});