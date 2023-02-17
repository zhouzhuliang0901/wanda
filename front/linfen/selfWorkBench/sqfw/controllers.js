app.controller("listController", function($scope, $route, $location, $http, $rootScope, data, appFactory) {
	$scope.itemName = [];
	$scope.currentMatters = [];
	$scope.matterVal = '';
	$scope.idRead = false;
	//街道下主题
	$scope.getThemeInStreet = function() {
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK"
		}
		$http.jsonp(urlHost1 + '/aci/declare/getThemeInStreet.do', {
			params: tConfig
		}).success(function(dataJson) {
			console.log(dataJson);
			$scope.itemName = dataJson.data;
			$scope.itemName.unshift({
				itemTypeName: "000",
				itemTypeCode: "全部"
			});
		}).error(function(err) {
			console.log(err);
		});
	}
	$scope.getThemeInStreet();

	$scope.toMaterials = function(itemTypeCode, itemTypeName, index) {
		data.itemTypeCode = itemTypeName;
		data.itemTypeName = itemTypeCode;
		$location.path("/itemlist");
	};
	$rootScope.goHome = function() {
		window.external.URL_CLOSE();
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
	};
});
app.controller("itemlistController", function($scope, $route, $location, $http, $rootScope, data, appFactory) {
	//通过主题获取事项
	$scope.getItemOfThemeInStreet = function(code) {
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			themeCode: code
		}
		$http.jsonp(urlHost1 + '/aci/declare/getItemOfThemeInStreet.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.data;
		}).error(function(err) {
			console.log(err);
		});
	}
	if(data.itemTypeName == "全部") {
		$scope.getItemOfThemeInStreet("");
	} else {
		$scope.getItemOfThemeInStreet(data.itemTypeCode);
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
	$scope.setItemCode = function(code) {
		data.stItemNo = code;
		data.itemName = "街道";
		$location.path("/select");
	}
	$scope.prev = function() {
		$location.path("/list");
	}
});
app.controller("guidelineController", function($scope, $route, $http, $location, $sce, data, $timeout, $rootScope, appFactory) {
	data.itemName = "敬老卡申领";
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
		clName: "<p>敬老卡申领 </p >", //事项名称
		clDept: "市公安局", //主管部门
		clApplyConds: "年满65周岁的本市户籍市民", //申报条件
		clApprovalMater: "<p>申请对象需带齐以下材料：</p ><p>&nbsp;&nbsp;1、有效身份证原件</p ><p>",
		clApprovalProcess: "<p>1、该项业务全市通办，申请对象可在满65周岁生日前一个月带齐所需材料至就近的街（镇）社区事务受理服务中心办理</p ><p>2、材料齐全，无特殊情况当场受理</p ><p>3、转市审批30个工作日后，本人凭有效身份证原件领卡，若委托他人领卡的，还需提供被委托人有效身份证原件及申请对象签名或盖章的书面委托书</p ><p>4、目前有2家银行（中国工商银行、上海银行）代为发放综合津贴，居民可在受理时自行选择</p ><p>5、市民政热线：962200  卡面信息有误请拨打市社会保障卡服务中心热线：962222</p ><p>&nbsp;&nbsp;津贴标准：</p ><p>&nbsp;&nbsp;65-69 周岁每月75元      70-79周岁每月150元     80-89周岁每月180元</p ><p>&nbsp;&nbsp;90-99周岁每月350元      100周岁以上每月600元</p >",
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
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
	$scope.next = function() {
		$location.path("/select");
		//$location.path("/materialList");
		data.clDept = $scope.guideInfo.clDept;
	}
	$scope.prev = function() {
		window.location.href = "../declare/index.html";
	}
	$rootScope.goHome = function() {
		window.external.URL_CLOSE();
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
	};

});
app.controller("selectController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
	data.isUpload = [];
	data.listImg = [];
	data.fileName = [];
	data.imgId = "";
	$.device.Camera_Hide();
	$.device.qrCodeClose();
	var name = data.itemName || $location.search().itemName;
	data.itemName = name;
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	// 刷身份证获取信息
	$scope.scanIdcard = function() {
		$location.path('/idCard');
	}
	// 市民云亮证
	$scope.citizen = function() {
		$location.path('/citizen');
	}
	//刷脸认证
	$scope.queryFace = function() {
		$location.path('/queryFace');
	}
	$scope.prev = function() {
		if(data.itemName == "灵活就业两项补贴") {
			$location.path("/guidelineJybt");
		} else if(data.itemName == "敬老卡申领") {
			$location.path("/guideline");
		} else if(data.itemName == "生育保险") {
			$location.path("/guidelineSybx");
		} else if(data.itemName == "跨省异地就医登记备案") {
			$location.path("/guidelineJygx");
		} else if(data.itemName == "街道") {
			$location.path("/itemlist");
		}
	}
	$rootScope.goHome = function() {
		window.external.URL_CLOSE();
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
	};
});
app.controller("idCardController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	data.idCardNum = "";
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$scope.isRead = true;
	$scope.isLoding = true;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$location.path("/select");
	}

	$scope.choice = function() {
		$scope.isLoding = false;
		if(data.itemName == "灵活就业两项补贴") {
			$location.path("/infoJybt");
		} else if(data.itemName == "敬老卡申领") {
			//			$http.jsonp(urlHost+"/aci/workPlatform/DZCert/checkElderlyCard.do", {
			//				params: {
			//					jsonpCallback: "JSON_CALLBACK",
			//					identNo: data.idCardNum
			//				}
			//			}).success(function(dataJson) {
			//				if(dataJson.type == "false") {
			//					$scope.isAlert = true;
			//					$scope.msg = "<p>&nbsp</p><p>" + dataJson.age + "</p><p>" + dataJson.nation + "</p><p>" + dataJson.havingElderlyCard + "</p>";
			////					$scope.$on('changeModel',function(){
			////				           $scope.isAlert = false;
			////				    })
			//				} else {
			$location.path("/info");
			//				}
			//			}).error(function(err) {
			//				layer.msg("未获取到敬老卡信息，请重试！");
			//				$location.path("/select");
			//				console.log(err);
			//			});
		} else if(data.itemName == "生育保险") {
			$scope.sex = ((parseInt(data.idCardNum.substring(16, 17)) % 2) == 0) ? "女" : "男";
			//			if($scope.sex == "男") {
			//				$scope.isAlert = true;
			//				$scope.msg = "抱歉，该业务仅限生育女性申请。"
			//			} else {
			//				$http.jsonp(urlHost + "/aci/workPlatform/DZCert/checkBirthBenefits.do", {
			//					params: {
			//						jsonpCallback: "JSON_CALLBACK",
			//						identNo: data.idCardNum
			//					}
			//				}).success(function(dataJson) {
			//					if(dataJson.type == "false") {
			//						$scope.isAlert = true;
			//						$scope.msg = "<style> p{font-size:40px;}</style><p>" + dataJson.birthDate + "</p><p>" + dataJson.firstMarriage + "</p><p>" + dataJson.marriageCity + "</p><p>" + dataJson.birthCity + "</p><p>" + dataJson.birthBenefits + "</p><p>" + dataJson.InsuredStateThisMonth + "</p>";
			//					} else {
			$location.path("/infoSybx");
			//					}
			//				}).error(function(err) {
			//					layer.msg("未获取到生育信息，请重试！");
			//					$location.path("/select");
			//					console.log(err);
			//				});
			//			}
		} else if(data.itemName == "跨省异地就医登记备案") {
			$.ajax({
				type: "get",
				url: urlHost + "/aci/workPlatform/ybj/chooseApplyType.do",
				/*url写异域的请求地址*/
				dataType: "jsonp",
				async: false,
				/*加上datatype*/
				jsonp: "jsonpCallback",
				/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
				data: {
					identNo: data.idCardNum,
					type: "0"
				},
				success: function(dataJson) {
					$scope.isLoding = true;
					if(dataJson.type == "false") {
						$scope.isAlert = true;
						$scope.msg = "<p style='padding-top:30px;'>" + dataJson.msg + "</p>";
						$timeout(function() {
							$.device.Camera_Close();
						}, 300);
					} else {
						data.jygxsm = dataJson.jygxsm;
						data.ybbfsm = dataJson.ybbfsm;
						data.jxgx = dataJson.jygx;
						data.ybbf = dataJson.ybbf;
						data.zhh = dataJson.zhh;
						$location.path("/infoJygx");
					}
				},
				error: function(err) {
					$scope.isLoding = true;
					$scope.isAlert = true;
					$scope.msg = "未获取到就医信息，请重试！";
					$timeout(function() {
						$.device.Camera_Close();
					}, 300);
				}
			});
		} else if(data.itemName == "街道") {
			$location.path("/iframe");
		}
	}

	$scope.prevStep = function() {
		$location.path("/select");
	}
	$scope.getIdcard = function(info, images) {
		$scope.faceImage = images;
		$scope.isRead = false; //faceImg
		$scope.$apply();
		data.idCardName = info.Name;
		data.idCardNum = info.Number;
		data.address = info.Address;
		data.VALIDENDDAY = info.ValidtermOfEnd;
		data.VALIDSTARTDAY = info.ValidtermOfStart;
		data.nation = info.People;
		if(data.nation.lastIndexOf('族') < 0) {
			data.nation = data.nation + '族';
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		$.device.Camera_UnLink();
		$timeout(function() {
			$scope.choice();
		}, 300);
	}
//		 data.idCardName = "陈雷"; //
//		 data.idCardNum = "310228198808070818"; //"330727198502063214";//"310228198808070818";
//		 data.VALIDENDDAY = "2029-05-13";
//		 data.VALIDSTARTDAY = "2019-05-13";
//		 data.mobile = "18692067056";
//		 data.address = "浙江省金华市磐安县";
//		 data.nation = "汉族";
//		 $location.path("/infoFinish");

});
app.controller("citizenController", function($scope, $route, $http, $location, $rootScope, data, $timeout, appFactory) {
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$scope.SisAlert = false;
	$scope.concel = "false";
	$scope.isLoding = true;
	$scope.SalertConfirm = function() {
		$scope.SisAlert = false;
		$timeout(function() {
			$location.path('/select');
		}, 100);
	}
	$scope.ClearBr = function(key) {
		key = key.replace(/<\/?.+?>/g, "");
		key = key.replace(/[\r\n]/g, "");
		return key;
	}
	$scope.choice = function() {
		$scope.isLoding = false;
		if(data.itemName == "灵活就业两项补贴") {
			$location.path("/infoJybt");
		} else if(data.itemName == "敬老卡申领") {
			//			$http.jsonp("http://10.1.93.168:8080/ac/aci/workPlatform/DZCert/checkElderlyCard.do", {
			//				params: {
			//					jsonpCallback: "JSON_CALLBACK",
			//					identNo: data.idCardNum
			//				}
			//			}).success(function(dataJson) {
			//				if(dataJson.type == "false") {
			//					$scope.SisAlert = true;
			//					$scope.Smsg = "<p>&nbsp</p><p>" + dataJson.age + "</p><p>" + dataJson.nation + "</p><p>" + dataJson.havingElderlyCard + "</p>";
			//				} else {
			$location.path("/info");
			//				}
			//			}).error(function(err) {
			//				layer.msg("未获取到敬老卡信息，请重试！");
			//				$location.path("/select");
			//				console.log(err);
			//			});
		} else if(data.itemName == "生育保险") {
			$scope.sex = ((parseInt(data.idCardNum.substring(16, 17)) % 2) == 0) ? "女" : "男";
			if($scope.sex == "男") {
				$scope.SisAlert = true;
				$scope.Smsg = "抱歉，该业务仅限生育女性申请。"
			} else {
				$http.jsonp(urlHost + "/aci/workPlatform/DZCert/checkBirthBenefits.do", {
					params: {
						jsonpCallback: "JSON_CALLBACK",
						identNo: data.idCardNum,
					}
				}).success(function(dataJson) {
					if(dataJson.type == "false") {
						$scope.SisAlert = true;
						$scope.Smsg = "<style> p{font-size:40px;}</style><p>" + dataJson.birthDate + "</p><p>" + dataJson.firstMarriage + "</p><p>" + dataJson.marriageCity + "</p><p>" + dataJson.birthCity + "</p><p>" + dataJson.birthBenefits + "</p><p>" + dataJson.InsuredStateThisMonth + "</p>";
					} else {
						$location.path("/infoSybx");
					}
				}).error(function(err) {
					layer.msg("未获取到生育信息，请重试！");
					$location.path("/select");
					console.log(err);
				});
			}
		} else if(data.itemName == "跨省异地就医登记备案") {
			$http.jsonp(urlHost + "/aci/workPlatform/ybj/chooseApplyType.do", {
				params: {
					jsonpCallback: "JSON_CALLBACK",
					identNo: data.idCardNum,
					type: "1"
				}
			}).success(function(dataJson) {
				if(dataJson.type == "false") {
					$scope.SisAlert = true;
					$scope.Smsg = "<p style='padding-top:30px;'>" + dataJson.msg + "</p>";
				} else {
					data.jygxsm = dataJson.jygxsm;
					data.ybbfsm = dataJson.ybbfsm;
					data.jxgx = dataJson.jygx;
					data.ybbf = dataJson.ybbf;
					data.zhh = dataJson.zhh;
					$location.path("/infoJygx");
				}
			}).error(function(err) {
				$scope.SisAlert = true;
				$scope.Smsg = "未获取到就医信息，请重试！";
				console.log(err);
			});
		} else if(data.itemName == "街道") {
			$location.path("/iframe");
		}
	}

	$scope.getUserInfoByAccessToken = function() {
		function ClearBr2(key) {
			key = key.replace(/\+/g, "-");
			key = key.replace(/\#/g, ",");
			return key;
		}
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getUserInfoByAccessToken.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				accessToken: data.token
			},
			success: function(dataJson) {
				console.log(dataJson);
				if(dataJson != undefined && dataJson != null && dataJson != "") {
					data.idCardName = dataJson.zwdtsw_name;
					data.idCardNum = dataJson.zwdtsw_cert_id;
					data.encrypt_identity = ClearBr2(dataJson.encrypt_identity);
					$timeout(function() {
						$scope.choice();
					}, 100);
				} else {
					$scope.SisAlert = true;
					$scope.Smsg = "扫码失败请重试！";
				}
			},
			error: function(err) {}
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
					data.token = res.accessToken;
					$scope.getUserInfoByAccessToken();
				} else {
					$scope.SisAlert = true;
					$scope.Smsg = "扫码失败请重试！";
				}
			},
			error: function(err) {},
		})
	}

	// 扫描二维码
	$.device.qrCodeOpen(function(code) {
		if(code.indexOf("http") != -1) {
			code = code.replace(/[\r\n]/g, "");
			$scope.cloud = false;
			$scope.load = true;
			$scope.tipsImage = "../libs/common/images/loadings.gif";
			$.ajax({
				url: $.getConfigMsg.preUrlSelf + "/selfapi/loginService/getTokenSNOByQrCode.do",
				dataType: 'jsonp',
				jsonp: "jsonpCallback",
				data: {
					certQrCode: encodeURIComponent(code),
				},
				success: function(dataJonsp) {
					$scope.loading = false;
					$.log.debug(code);
					if(dataJonsp != null && dataJonsp != undefined && dataJonsp.encrypted == true) {
						$scope.tipsText = "正在加载数据，请稍候...";
						$scope.getAccessToken(dataJonsp.biz_response.tokenSNO);
					} else {
						$scope.SisAlert = true;
						$scope.Smsg = "扫码失败请重试"
						$timeout(function() {
							$location.path('/select');
						}, 100);
					}
				},
				error: function(err) {
					$scope.SisAlert = true;
					$scope.Smsg = "扫码失败请重试！";
				}
			})
		} else {
			var __code = $scope.ClearBr(code);
			$.ajax({
				url: $.getConfigMsg.preUrlSelf + "/selfapi/getQrCodeInfoByElectronicCert.do",
				dataType: 'jsonp',
				jsonp: "jsonpCallback",
				data: {
					codeParam: __code,
					machineId: $.config.get("uniqueId") || "",
					itemName: "",
					itemCode: "",
					businessCode: "",
					lzAddress: encodeURI("一网通办智能终端")
				},
				success: function(dataJsonp) {
					if(dataJsonp.data.result.success === false) {
						$scope.SisAlert = true;
						$scope.Smsg = dataJsonp.result.msg;
						$timeout(function() {
							$location.path('/select');
						}, 100);
					}
					data.idCardName = dataJsonp.data.result.data.realname;
					data.idCardNum = dataJsonp.data.result.data.idcard;
					data.VALIDENDDAY = dataJsonp.data.result.data.VALIDENDDAY;
					data.VALIDSTARTDAY = dataJsonp.data.result.data.VALIDSTARTDAY;
					$timeout(function() {
						$scope.choice();
					}, 100);
				},
				error: function(err) {
					$scope.SisAlert = true;
					$scope.Smsg = "二维码已过期！";
				}
			});
		}
	}, function(err) {
		console.log("二维码扫描出错：" + err)
	});
	$scope.prevStep = function() {
		$location.path("/select");
	}
});
app.controller("iframeController", function($scope, $route, $http, $location, data, $timeout, $rootScope, $sce, appFactory) {
	$rootScope.isAlert = false;
	$scope.info = {
		type: "1",
		idCard: data.idCardNum,
		credit_code: "",
		ca_code: "",
		name: data.idCardName
	}
	console.log($scope.info);
	var httpConfig = {
		jsonpCallback: "JSON_CALLBACK",
		data: encodeURIComponent(JSON.stringify($scope.info))
	}
	$scope.encryptDataByRSA = function() {
		$http.jsonp('http://hengshui.5uban.com/xhac/aci/workPlatform/util/encryptDataByRSA.do', {
			params: httpConfig
		}).success(function(dataJson) {
			console.log(dataJson.result);
			$scope.url = "https://zwdtuser.sh.gov.cn/uc/naturalUser/jumpUcRedirect.do?app_id=535984a5&data=" + encodeURIComponent(dataJson.result) + "&redirect_uri=";
			$scope.url1 = "http://ywtb.sh.gov.cn:18018/ac-product-net/netapply/apply.do?itemCode=" + data.stItemNo;
			console.log($scope.url + $scope.url1);
			window.external.URL_OPEN(50, 180, 1800, 760, $scope.url + encodeURIComponent($scope.url1));
		}).error(function(err) {
			console.log('encryptDataByRSA err');
		});

	}
	$scope.encryptDataByRSA();

	// 设置url被angular信任 正常跳转
	$scope.prev = function() {
		try {
			window.external.URL_CLOSE();
		} catch(e) {}
		$location.path("/itemlist");
	}
});
app.controller("infoController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
	$.device.Camera_Hide();
	$.device.idCardClose();
	var name = data.itemName;
	$scope.itemName = name;
	$scope.isLoading = false;
	//	$(".form_datetime").datetimepicker({
	//		format: "yyyy-mm-dd", //显示日期格式
	//		autoclose: true,
	//		todayBtn: true,
	//		minView: "month", //只选择到天自动关闭
	//		language: 'zh-CN',
	//	});
	City();
	shouliCenter();
	$scope.SisAlert = false;
	$scope.concel = "false";
	$scope.nextText = "提交";
	$scope.SalertConfirm = function() {
		$scope.SisAlert = false;
	}

	$scope.info = function() {
		$scope.name = data.idCardName;
		$scope.idCard = data.idCardNum;
		$scope.mobile = data.mobile || "";
		$scope.shebaoCard = data.idCardNum;
		$scope.sex = ((parseInt(data.idCardNum.substring(16, 17)) % 2) == 0) ? "女" : "男";
		$scope.date = (data.idCardNum).substring(6, 10) + "-" + (data.idCardNum).substring(10, 12) + "-" + (data.idCardNum).substring(12, 14);
		$timeout(function() {
			setCenter();
		}, 300);
		$scope.next2 = function() {
			try {
				setHjAdressText('liveProvince', 'liveCity', 'liveCounty', 'liveStreet', '', "上海市", "上海市", $scope.hjc + '区', $scope.hjs.split('镇')[0] + "镇", '');
			} catch(e) {}
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
					itemName: "敬老卡申领",
					itemCode: "",
					businessCode: "",
					name: encodeURI(data.idCardName),
					startDay: data.VALIDSTARTDAY,
					endDay: data.VALIDENDDAY,
				},
				success: function(dataJsonp) {
					$scope.isLoading = true;
					try {
						setSelectCheckedByText("nations", dataJsonp.SETHNIC);
					} catch(e) {}
				},
				error: function(err) {
					$scope.isLoading = true;
					console.info("queryMarriageCertificateInfo error");
				},
				complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
					　　　　
					if(status == 'timeout') { //超时,status还有success,error等值的情况
						　　　　　
						manIdcard.abort();　　　　　
						$scope.next2();　　　　
					}　　
				}
			})
		}

		//		var config = {
		//			jsonpCallback: "JSON_CALLBACK",
		//			idno: data.idCardNum
		//		}
		//		$http.jsonp(urlHost + "/aci/workPlatform/elderlyCard/getApplicantInfo.do", {
		//			params: config
		//		}).success(function(dataJsonp) {
		//			setCenter();
		//			if(dataJsonp.rtnData) {
		//				data.rtnData = dataJsonp.rtnData;
		//				setHjAdress('province', 'city', 'county', 'street', '', dataJsonp.rtnData.hjprovince, dataJsonp.rtnData.hjcity, dataJsonp.rtnData.hjregion, dataJsonp.rtnData.hjneighborhood, '');
		//				setHjAdress('liveProvince', 'liveCity', 'liveCounty', 'liveStreet', '', dataJsonp.rtnData.jzprovince || "310000", dataJsonp.rtnData.jzcity || "", dataJsonp.rtnData.jzregion || "", dataJsonp.rtnData.jzneighborhood || "", '');
		//			} else {
		$scope.next1 = function() {
			var nConfig = {
				identNo: data.encrypt_identity || data.idCardNum,
				catMainCode: "310105105000100",
				machineId: $.config.get('uniqueId'),
				itemName: "敬老卡申领",
				itemCode: "",
				businessCode: "",
				name: encodeURI(data.idCardName),
				startDay: data.VALIDSTARTDAY,
				endDay: data.VALIDENDDAY,
			}
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
					try {
						$scope.hjc = (dataJsonp.SADDR.split('市')[1]).split('区')[0];
						$scope.hjs = (dataJsonp.SADDR.split('市')[1]).split('区')[1];
						setHjAdressText('province', 'city', 'county', 'street', '', "上海市", "上海市", $scope.hjc + '区', $scope.hjs.split('镇')[0] + "镇", '');
					} catch(e) {}
					$scope.next2();
				},
				error: function(err) {
					console.info("queryMarriageCertificateInfo error");
					$scope.next2();
				},
				complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
					if(status == 'timeout') { //超时,status还有success,error等值的情况
						manLicense.abort();　　　　　
						$scope.next1();　　　　
					}　　
				}
			})
		}
		$scope.next1();
		//			}
		//		}).error(function(err) {
		//			setCenter();
		//			console.log("getApplicantInfo error");
		//		})
	}
	$scope.info();
	$scope.synchronize = function() {
		$scope.lStreet = ($('#liveStreet').attr('vName') == "选择街/镇") ? "" : $('#liveStreet').attr('vName');
		$scope.address = $('#liveProvince').attr('vName') + $('#liveCity').attr('vName') + $('#liveCounty').attr('vName') +
			$scope.lStreet + (($scope.stLxAddress == undefined) ? "" : $scope.stLxAddress);
		$('#stLxAddress').val($scope.address);
	}
	//判断是哪个银行
	$scope.bank = function(str) {
		$scope.src = "";
		switch(str) {
			case "工商银行":
				$scope.src = "1";
				$scope.srcName = "中国工商银行";
				break;
			case "上海银行":
				$scope.src = "4";
				$scope.srcName = "上海银行";
				break;
		}
	}

	$scope.prevStep = function() {
		$location.path("/select");
	}

	// 保存数据
	$scope.flag = true;
	$scope.next = function() {
		$scope.bank($(".in li").text());
		console.log($scope.src);
		if(!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			if($('#name').val() < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请输入正确的姓名！";
				return;
			}
			if(!checkIdCard($('#idCard').val())) {
				$scope.SisAlert = true;
				$scope.Smsg = "请输入正确的身份证信息！";
				return;
			}
			if($('#sex').val() < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请输入正确的性别！";
				return;
			}
			if($('#shebaoCard').val() < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请输入正确的社保卡号！";
				return;
			}
			//			if(!isPhoneAvailable($('#mobile').val())) {
			//				$scope.SisAlert = true;
			//				$scope.Smsg = "请输入正确的手机号！";
			//				return;
			//			}
			if($('#address').val() < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请输入正确的地址！";
				return;
			}
			if($scope.src == "" || $scope.src == undefined) {
				$scope.SisAlert = true;
				$scope.Smsg = "请选择银行！";
				return;
			}
		} while (condFlag);
		$scope.flag = false;
		//提交所需字段
		data.centerCode = $('#stCenter').attr('vId');
		data.centerAddress = $('#stCenter').attr('vName');
		$scope.lStreet = ($('#liveStreet').attr('vName') == "选择街/镇") ? "" : $('#liveStreet').attr('vName');
		$scope.address = $('#liveProvince').attr('vName') + $('#liveCity').attr('vName') + $('#liveCounty').attr('vName') +
			$scope.lStreet + (($scope.stLxAddress == undefined) ? "" : $scope.stLxAddress);
		data.address = $scope.address;
		data.mobile = $('#mobile').val();
		data.bankid = $scope.src;
		data.sex = $('#sex').val();
		data.date = $scope.date;
		data.bank = $scope.srcName;
		data.jzAddress = {
			liveProvince: $('#liveProvince').attr('vId'),
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
		//判断是否有身份证照
		$scope.idCardNum = data.encrypt_identity || data.idCardNum;
		$scope.nextload1 = function() {
			$scope.isLoading = false;
			appFactory.pro_fetch($scope.idCardNum, data.idCardName, '敬老卡申领', data.VALIDSTARTDAY, data.VALIDENDDAY, '310105109000100', 'GA2011_010001', 'GA2011_01', '居民身份证', '0', function(dataJson) {
				if(dataJson.length <= 0) {
					$scope.SisAlert = true;
					$scope.Smsg = "没有该证照!";
					$scope.$apply();
				}
				try {
					$scope.imgUrl = $.getConfigMsg.preUrlSelf + dataJson[0].pictureUrlForBytes;
					data.imgStr = $.getConfigMsg.preUrlSelf + dataJson[0].pictureUrl;
					if(data.isUpload.length <= 0) {
						data.isUpload.push({
							index: 0,
							stuffName: "居民身份证",
							img: $scope.imgUrl,
							status: 1,
							method: "高拍仪"
						});
					}
				} catch(e) {}
				$location.path("/materialList");
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
	//公用单选
	Publicchoice2('.singleselect2');
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
app.controller("uploadMethodController", function($scope, $route, $http, $location, $rootScope, data, $timeout, appFactory) {
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();
	var name = data.itemName;
	$scope.statusName = data.statusName;
	$scope.itemName = name;
	// 显示材料名称
	$scope.stStuffName = data.stStuffName;
	$scope.stuffNameShow = data.isShowStuffName;
	// 扫描上传
	$scope.scanPhoto = function() {
		$location.path('/finish');
	};
	// U盘上传
	$scope.takePhoto = function() {
		layer.confirm("<em style='color:black'>" + '请确认是否插入U盘！' + "</em>", {
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
	//返回
	$scope.prve = function() {
		if(data.itemName == "灵活就业两项补贴") {
			$location.path("/materialJybtList");
		} else if(data.itemName == "敬老卡申领") {
			$location.path("/materialList");
		} else if(data.itemName == "生育保险") {
			$location.path("/materialSybxList");
		} else if(data.itemName == "跨省异地就医登记备案") {
			$location.path("/materialJygxList");
		}
	}
});
app.controller("materialPicController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
	var name = data.itemName;
	$scope.itemName = name;
	$scope.imgUrls = "";
	$rootScope.isAlert = false;
	$scope.prevText = "返回";
	$scope.nextText = "确认上传";
	$scope.url = urlHost;
	$scope.profileShow = function() {
		$scope.isLoding = false;
		$.ajax({
			url: $.getConfigMsg.preUrlSelf + "/selfapi/dzzz/queryCertBaseData.do",
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				jsonpCallback: "JSON_CALLBACK",
				certNo: data.encrypt_identity || data.idCardNum, // "340881199303145313", //
				type: 0,
				machineId: $.config.get("uniqueId") || "",
				itemName: "",
				itemCode: "",
				businessCode: "",
				name: data.idCardName,
				startDay: data.VALIDSTARTDAY,
				endDay: data.VALIDENDDAY,
			},
			success: function(json) {
				$scope.isLoding = true;
				var dataJson = eval("(" + JSON.stringify(json) + ")");
				if(!dataJson) { // !dataJson[0].address
					layer.msg("没有数据，请重新选择上传方式!");
					$timeout(function() {
						$location.path('/uploadMethod');
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
		$scope.isLoding = false;
		data.selectImg = urlHost + $scope.imgUrls[$scope.current].pictureUrlForBytes;
		$scope.waitUploadImgUrl = data.selectImg;
		$scope.jsonData = {
			archivescode: data.archivescode,
			affairscode: data.affairscode,
			archivesname: data.stStuffName,
			needflag: data.needflag
		};
		$scope.jsonData = JSON.stringify($scope.jsonData);
		console.log($scope.jsonData);
		$.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		urlHost + 
			$scope.waitUploadImgUrl,
			"C:\\waitUploadImg.jpg",
			//将选中图片下载
			function(bytesCopied, totalBytes) {
				console.log(bytesCopied + "," + totalBytes);
			},
			function(result) {
				//将选中图片上传到服务器
				$.device.httpUpload(urlHost + '/aci/workPlatform/elderlyCard/uploadArchiveInfo.do', "img", "C:/waitUploadImg.jpg",
					$scope.jsonData,
					function(result) {
						layer.msg("上传成功");
						if(data.isUpload[data.currentIndex].length > 0) {
							data.isUpload[data.currentIndex] = "";
						}
						data.isUpload[data.currentIndex] = {
							index: data.currentIndex,
							stuffName: data.stStuffName,
							img: scanImg,
							status: 0,
							method: "个人档案"
						};
						$timeout(function() {
							$location.path('/materialList');
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
app.controller("takePhotoController", function($scope, $route, $http, $location, $rootScope, data, $timeout, $routeParams, appFactory) {
	var name = data.itemName;
	$scope.itemName = name;
	$rootScope.isAlert = false;
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
		$location.path("/uploadMethod");
	}
	// 上传
	$scope.highCapture = function() {
		if($scope.UData == null) {
			layer.msg("未获取到U盘数据");
		} else {
			$scope.jsonData1 = {
				archivescode: data.archivescode,
				affairscode: data.affairscode,
				archivesname: data.stStuffName,
				needflag: data.needflag
			};
			$scope.jsonData1 = JSON.stringify($scope.jsonData1);
			$.device.httpUpload(urlHost + "/aci/workPlatform/elderlyCard/uploadArchiveInfo.do", "img", $scope.UData,
				$scope.jsonData1,
				function(result) {
					layer.msg("上传成功");
					data.fileName.push($scope.UData);
					if(data.isUpload[data.currentIndex].length > 0) {
						data.isUpload[data.currentIndex] = "";
					}
					data.isUpload[data.currentIndex] = {
						index: data.currentIndex,
						stuffName: data.stStuffName,
						img: scanImg,
						status: 0,
						method: "U盘上传"
					};
					$timeout(function() {
						$location.path('/materialList');
					}, 100);
				},
				function(webexception) {
					layer.msg("上传失败");
				});
		}
	};
	$scope.prevStep = function() {
		if(data.itemName == "灵活就业两项补贴") {
			$location.path("/materialJybtList");
		} else if(data.itemName == "敬老卡申领") {
			$location.path("/materialList");
		} else if(data.itemName == "生育保险") {
			$location.path("/materialSybxList");
		} else if(data.itemName == "跨省异地就医登记备案") {
			$location.path("/materialJygxList");
		}
	}
});
app.controller("finishController", function($scope, $route, $http, $location, $rootScope, data, $timeout, appFactory) {
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();
	$scope.SisAlert = false;
	$scope.SalertConfirm = function() {
		$.device.cmCaptureHide(); // 关闭高拍仪
		$location.path('/materialSybxList');
	}
	$scope.finish = [];
	$scope.prevText = "返回";
	$scope.nextText = "确认上传";
	$scope.isLoading = true;
	var name = data.itemName;
	$scope.itemName = name;
	$.device.cmCaptureShow(680, 530, 210, 340);
	$.device.cmCaptureSelectRect(0, 0, 1920, 1920);
	$scope.isFinish = false;
	$scope.ff = function() {
		if(data.itemName == "灵活就业两项补贴") {
			$location.path("/materialJybtList");
		} else if(data.itemName == "敬老卡申领") {
			$location.path("/materialList");
		} else if(data.itemName == "生育保险") {
			$location.path("/materialSybxList");
		} else if(data.itemName == "跨省异地就医登记备案") {
			$location.path("/materialJygxList");
		}
	}
	// 拍照
	var imgHTML = "";
	var imgIndex = 0;
	var scanImg1 = "";
	$scope.next = function() {
		$scope.isLoading = false;
		var scanImg = $.device.cmCaptureCaptureUrl();
		scanImg1 = $.device.cmCaptureCaptureBase64();
		if(scanImg == undefined) {
			$scope.SisAlert = true;
			$scope.Smsg = "请聚焦并对准材料后再拍照";
			$scope.SalertConfirm = function() {
				$scope.SisAlert = false;
			}
		} else {
			$scope.jsonData1 = {
				'applyNo': data.applyNo, //   '751122018600008'
				'stuffId': '',
				'stuffCode': data.stStuffCode,
				'stuffName': data.stStuffName,
				'stuffType': 0,
				'stuffStatus': 0
			};
			$scope.jsonData1 = JSON.stringify($scope.jsonData1);
			$.device.httpUpload(urlHost + "/aci/declare/uploadStuff.do", "file", scanImg,
				$scope.jsonData1,
				function(result) {
					$scope.isLoading = true;
					data.uploadStuffId = data.archivescode; //dataJson.data.stuffId  ;
					data.imgStr = scanImg1;
					//		data.imgId = data.imgId + "," + dataJson.rtnData.imgid;
					if(data.isUpload[data.currentIndex]) {
						data.isUpload[data.currentIndex] = "";
					}
					$scope.finish.push({
						index: data.currentIndex,
						stuffName: data.stStuffName,
						img: scanImg,
						status: 0,
						method: "高拍仪"
					});
					imgHTML += '<div class="img" id="' + data.uploadStuffId + '"><img src="' + scanImg + '" width="150" height="200" /></div>';
					$('.imgBox').html(imgHTML);
					$scope.isFinish = true;
				},
				function(webexception) {
					$scope.isLoading = true;
					layer.msg("上传材料失败");
					$scope.ff();
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
		for(var i = 0; i < data.isUpload.length; i++) {
			if(data.currentIndex == data.isUpload[i].index) {
				data.isUpload[i] = "";
			}
		}
		for(var i = 0; i < $scope.finish.length; i++) {
			data.isUpload.push($scope.finish[i]);
		}
		$(".next").attr("disabled", true);
		$.device.cmCaptureHide(); // 关闭高拍仪
		if(data.currentIndex == 4) {
			var formData = new FormData();
			formData.append('imgFile', scanImg1);
			$.ajax({
				url: urlHost + '/aci/workPlatform/util/readImgToText.do',
				type: 'POST',
				cache: false,
				data: formData,
				processData: false,
				contentType: false
			}).done(function(res) {
				try {
					res = JSON.parse(res);
				} catch(e) {}
				if(res.bank_card_number != undefined && res.bank_card_number != "") {
					$scope.SisAlert = true;
					$scope.Smsg = "该银行卡为申请人（" + data.idCardName + "）所有，银行卡号为" + res.bank_card_number;
					$scope.$apply();
				} else {
					layer.msg("银行卡号为空");
					$location.path('/materialSybxList');
				}
			}).fail(function(res) {
				layer.msg("未识别出银行卡号");
				$location.path('/materialSybxList');
			});
		} else {
			$timeout(function() {
				$.device.cmCaptureHide(); // 关闭高拍仪
				$scope.ff();
			}, 20);
		}

	};

	$scope.last = function() {
		$.device.cmCaptureHide(); // 关闭高拍仪
		$scope.ff();
	}
});
app.controller("materialListController", function($scope, $route, $http, $location, $rootScope, data, $timeout, appFactory) {
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

	// 材料上传 
	data.currentIndex++;
	$scope.toUploadMaterial = function() {
		data.stStuffName = "居民身份证";
		data.archivescode = 'GA2011_010001';
		data.affairscode = 'GA2011_01';
		data.needflag = '0';
		data.currentIndex = 0;
		data.stuffImg = data.listImg[data.currentIndex];
		$location.path("/uploadMethod");
	}
	//重新上传

	//查看
	$scope.view = function() {
		data.currentIndex = 0;
		data.view = data.isUpload;
		$location.path("/materialView");
	}
	$scope.prevStep = function() {
		$location.path("/info");
	}
	//提交办件
	$scope.submit = function() {
		$location.path('/signature');
	};

	//自助填表
	$scope.autoForm = function() {
		$location.path("/autoForm");
	}

});
app.controller("autoFormController", function($scope, $http, $location, $rootScope, data, appFactory) {
	$scope.name = data.idCardName;
	$scope.xing = pinyin.getFullChars((data.idCardName).substring(0, 1));
	$scope.ming = pinyin.getFullChars((data.idCardName).substring(1, data.idCardName.length));
	$scope.idCard = data.idCardNum;
	$scope.mobile = data.mobile;
	$scope.date = data.idCardNum.substring(6, 10) + "-" + data.idCardNum.substring(10, 12) + "-" + data.idCardNum.substring(12, 14);
	$scope.newAddress = data.address ? data.address : "";
	$scope.pNewAddress = pinyin.getFullChars($scope.newAddress);
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
	$scope.SisAlert = false;
	$scope.SalertConfirm = function() {
		$scope.SisAlert = false;
	}

	$scope.prevStep = function() {
		$location.path("/materialList");
	}
	$scope.flag = true;
	$scope.next = function() {
		if(!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			if($('#name').val() < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请输入正确的姓名！";
				return;
			}
			if($('#date').val() < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请输入正确的日期！";
				return;
			}
			if($('#juming').val() < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请选择一个税收模式！";
				return;
			}
			if($('#xing').val() < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请输入正确的姓！";
				return;
			}
			if($('ming').val() < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请输入正确的名！";
				return;
			}
			if(!checkIdCard($('#idCard').val())) {
				$scope.SisAlert = true;
				$scope.Smsg = "请输入正确的身份证信息！";
				return;
			}
		} while (condFlag);
		$scope.flag = false;
		$location.path("/materialList");
	}
})
app.controller("soundController", function($scope, $http, $location, $rootScope, data, appFactory) {
	var name = data.itemName;
	$scope.itemName = name;
	$scope.next = function() {
		$location.path("/signatureSybx");
	}
})
app.controller("signatureController", function($scope, $http, $location, $rootScope, data, appFactory) {
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
		if($scope.signatureFlag === false) {
			alert("请先在屏幕上签名!");
			return;
		}
		data.picStr = $scope.signature.split(",")[1];
		$location.path("/infoFinish");
	};
	$scope.prev = function() {
		$location.path("/materialList");
	}
})
app.controller("materialViewController", function($scope, $http, $location, $rootScope, data, appFactory) {
	$scope.stuffList = []; //所有图片的容器
	$scope.showImgList = []; //显示图片的容器
	$scope.currentPage = 1; //当前页
	$scope.totalPages = 1; //总页数
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$rootScope.isAlert = false;
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
		console.log(data.view);
		for(var i = 0; i < data.view.length; i++) {
			if(data.currentIndex == data.view[i].index) {
				$scope.stuffList.push(data.view[i]);
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
		if(data.itemName == "灵活就业两项补贴") {
			$location.path("/materialJybtList");
		} else if(data.itemName == "敬老卡申领") {
			$location.path("/materialList");
		} else if(data.itemName == "生育保险") {
			$location.path("/materialSybxList");
		} else if(data.itemName == "跨省异地就医登记备案") {
			$location.path("/materialJygxList");
		}
	}

	//	//图片显示
	//	$scope.closeFlag = true;
	//	$scope.imgShow = function(imgUrl) {
	//		$scope.largeImg = imgUrl;
	//		$scope.closeFlag = !$scope.closeFlag;
	//	}
	//打开文件
	$scope.open = function() {
		try {
			$.device.officeOpen($scope.stuffList[0].fileName);
		} catch(e) {
			layer.msg("未找到此文件");
		}
	}
});
app.controller("infoFinishController", function($scope, $route, $http, $location, $rootScope, data, $timeout, appFactory) {
	$scope.urladdress = "";
	$scope.nextText = "返回首页";
	//	$scope.$watch("viewContentLoaded", function(event, toState, toParams, fromState, fromParams) {
	//		//$scope.isLoding = true;
	//		$scope.loding();
	//	});
	//	$scope.loding = function() {
	$scope.getApplyNoByItemNo = function(code) {
		$.ajax({
			type: "get",
			url: urlHost + '/aci/workPlatform/elderlyCard/getApplyNoByItemNo.do',
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data: {
				itemCode: code
			},
			success: function(dataJson) {
				if(dataJson.success === true) {
					data.applyNo = dataJson.aplyNo;
				} else {
					data.applyNo = "";
				}
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	if(data.itemName == "灵活就业两项补贴") {
		$scope.show = false;
		$scope.stuffName = '请凭办理凭条将"劳动手册"到自助取证柜进行材料投递';
		$('.successBox1').css("margin-left", "410px");
		$('.successBox1 p').css("margin-left", "10px");
		$scope.getApplyNoByItemNo('YJS0001');
		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: '就业补贴一件事',
				Number: data.applyNo,
			}
		}
		recordUsingHistory('高效办成一件事', '办理', '就业补贴一件事', data.idCardName, data.idCardNum, data.mobile, data.applyNo, JSON.stringify($scope.jsonStr));
		trackEventForAffairs(data.applyNo, '办理', '上海市人力资源与社会保障局', data.idCardName, data.idCardNum, data.mobile)
	} else if(data.itemName == "敬老卡申领") {
		$scope.show = true;
		$scope.getApplyNoByItemNo('GA2011_01');
		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: '敬老卡申领',
				Number: data.applyNo,
			}
		}
		recordUsingHistory('公安服务', '办理', '敬老卡申领', data.idCardName, data.idCardNum, data.mobile, data.applyNo, JSON.stringify($scope.jsonStr));
		trackEventForAffairs(data.applyNo, '办理', '上海市公安局', data.idCardName, data.idCardNum, data.mobile)
	} else if(data.itemName == "生育保险") {
		$scope.stuffName = '请凭办理凭条将"生育医学证明"到自助取证柜进行材料投递';
		$('.successBox1').css("margin-left", "410px");
		$('.successBox1 p').css("margin-left", "10px");
		$scope.getApplyNoByItemNo('YJS0002');
		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: '生育保险一件事',
				Number: data.applyNo,
			}
		}
		recordUsingHistory('高效办成一件事', '办理', '生育保险一件事', data.idCardName, data.idCardNum, data.mobile, data.applyNo, JSON.stringify($scope.jsonStr));
		trackEventForAffairs(data.applyNo, '办理', '上海市卫生健康委员会', data.idCardName, data.idCardNum, data.mobile)
	} else if(data.itemName == "跨省异地就医登记备案") {
		$scope.show = false;
		$('.successBox1').css("margin-left", "410px");
		$('.successBox1 p').css("margin-left", "10px");
		$scope.applyNo = "751021900100201";
		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: '异地就医一件事',
				Number: data.applyNo,
			}
		}
		recordUsingHistory('高效办成一件事', '办理', '异地就医一件事', data.idCardName, data.idCardNum, data.mobile, $scope.applyNo, JSON.stringify($scope.jsonStr));
		trackEventForAffairs($scope.applyNo, '办理', '上海市医疗保障局', data.idCardName, data.idCardNum, data.mobile)
	}
	$scope.urladdress = urlHost + "/aci/selfWorkBench/sqfw/index.html#/qrCode?applyNo=" + data.applyNo + "&itemName=" + data.itemName + "&name=" + data.idCardName;
	//	}
	var name = data.itemName;
	$scope.itemName = name;
	if(data.itemName == "跨省异地就医登记备案") {

	} else {
		data.rtnData = {
			idno: data.idCardNum,
			fullname: data.idCardName,
			sex: (data.sex == '男') ? '1' : '2',
			birthday: data.date,
			nationality: data.nations || "",
			cellno: data.mobile,
			hjprovince: data.hjAddress.province || "",
			hjcity: data.hjAddress.city || "",
			hjregion: data.hjAddress.county || "",
			hjneighborhood: data.hjAddress.street || "",
			jzprovince: data.jzAddress.liveProvince || "",
			jzcity: data.jzAddress.liveCity || "",
			jzregion: data.jzAddress.liveCounty || "",
			jzneighborhood: data.jzAddress.liveStreet || ""
		}
	}
	$scope.application = function() {
		$.ajax({
			url: urlHost + '/aci/workPlatform/elderlyCard/getDictionaryCodeByName.do',
			type: 'get',
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data: {
				dictionaryDept: 'GA',
				dictionaryNames: encodeURI(data.hjAddressName.province + ',' + data.hjAddressName.city + "," + data.hjAddressName.county + ',' + data.hjAddressName.street + ',' +
					data.jzAddressName.liveProvince + ',' + data.jzAddressName.liveCity + ',' + data.jzAddressName.liveCounty + ',' + data.jzAddressName.liveStreet)
			},
			success: function(dataJson) {
				try {
					data.rtnData.hjprovince = dataJson[0].code;
					data.rtnData.hjcity = dataJson[1].code;
					data.rtnData.hjregion = dataJson[2].code;
					data.rtnData.hjneighborhood = dataJson[3].code || "";
					data.rtnData.jzprovince = dataJson[4].code;
					data.rtnData.jzcity = dataJson[5].code;
					data.rtnData.jzregion = dataJson[6].code;
					data.rtnData.jzneighborhood = dataJson[7].code || "";
				} catch(e) {}
			},
			error: function() {
				console.log("getDictionaryCodeByName err");
			}
		})
	}
	$scope.submit = function() {
		console.log($scope.finishData);
		$.ajax({
			url: urlHost + '/aci/workPlatform/elderlyCard/sendYwtbApplyInfo.do',
			type: "post",
			dataType: "json",
			data: {
				jsonStr: JSON.stringify($scope.finishData)
			},
			success: function(dataJson) {
				console.log(dataJson);
				// $scope.print();
			},
			error: function(e) {
				console.log(e);
			},
		});
	}
	var date = new Date();
	console.log(date);
	// 生成办件编码
	$scope.submitApply = function() {
		$scope.finishData = "";
		if(data.itemName == "敬老卡申领") {
			$scope.application();
			//敬老卡
			$scope.finishData = {
				applicant: data.rtnData,
				ywtbAffairsapply: {
					platform: '4',
					affairscode: "GA2011_01",
					affairsname: "敬老卡申领",
					Itemcode: "",
					subtime: date,
					suid: $scope.applyNo,
					suborgancode: data.centerCode,
					suborganname: data.centerAddress,
					formdata: [{
						name: data.rtnData.fullname,
						pid: data.rtnData.idno,
						sex: data.rtnData.sex,
						enrolid: data.rtnData.idno || "",
						birthday: data.rtnData.birthday,
						telephone: data.mobile,
						commaddress: data.address,
						bankid: data.bankid,
						dbphone: "",
						archivesdata: [{
							archivescode: " GA2011_010001 ",
							affirscode: " GA2011_01 ",
							archivesname: "居民身份证",
							needflag: "0",
							imgscans: data.imgId
						}],
						display: {
							"姓名": data.rtnData.fullname,
							"身份证": data.rtnData.idno,
							"性别": (data.rtnData.sex == '1') ? '男' : '女',
							"出生年月": data.rtnData.birthday,
							"联系电话": data.mobile,
							"联系地址": data.address,
							"发放银行": data.bank,
							"联系电话": data.mobile,
						}
					}]
				},
				jsonpCallback: "JSON_CALLBACK",
			};
			$scope.submit();
		} else if(data.itemName == "灵活就业两项补贴") {
			$scope.application();
			//灵活就业两项补贴
			$scope.finishData = {
				applicant: data.rtnData,
				ywtbAffairsapply: {
					platform: '4',
					affairscode: "YJS0001",
					affairsname: "灵活就业两项补贴",
					Itemcode: "",
					subtime: date,
					suid: $scope.applyNo,
					suborgancode: data.centerCode,
					suborganname: data.centerAddress,
					formdata: [{
						person_name: data.rtnData.fullname,
						cetf_id: data.rtnData.idno,
						sign_png: data.picStr,
						khyh: data.bankId,
						khlx: data.bankType,
						yhzhd: data.bankNum,
						yhzhqr: data.bankNum,
						archivesdata: [{
							archivescode: "YJS00010003",
							affirscode: "YJS0001",
							archivesname: "居民身份证",
							needflag: "1",
							imgscans: data.imgId
						}],
						display: {
							姓名: data.rtnData.fullname,
							身份证: data.rtnData.idno,
							开户银行: data.bankName,
							卡号类型: data.bankTypeName,
							银行账号: data.bankNum,
							银行账号确认: data.bankNum
						}
					}]

				}
			}
			$scope.submit();
		} else if(data.itemName == "生育保险") {
			$scope.application();
			$.ajax({
				url: urlHost + '/aci/workPlatform/elderlyCard/getDictionaryCodeByName.do',
				type: 'get',
				dataType: 'jsonp',
				jsonp: "jsonpCallback",
				data: {
					dictionaryDept: 'WJ',
					dictionaryNames: encodeURI(data.hjAddressName.province + ',' + data.hjAddressName.county + ',' + data.hjAddressName.street + ',' +
						data.jzAddressName.liveProvince + ',' + data.jzAddressName.liveCounty + ',' + data.jzAddressName.liveStreet + ',' +
						data.mhjAddressName.province + ',' + data.mhjAddressName.county + ',' + data.mhjAddressName.street + ',' +
						data.mjzAddressName.liveProvince + ',' + data.mjzAddressName.liveCounty + ',' + data.mjzAddressName.liveStreet)
				},
				success: function(dataJson) {
					console.log(dataJson);
					console.log(dataJson.length);
					data.rtnData.hjprovince = dataJson[0].code;
					data.rtnData.hjregion = dataJson[1].code;
					data.rtnData.hjneighborhood = dataJson[2].code;
					data.rtnData.jzprovince = dataJson[3].code;
					data.rtnData.jzregion = dataJson[4].code;
					data.rtnData.jzneighborhood = dataJson[5].code;
					data.mhjAddress.province = dataJson[6].code;
					data.mhjAddress.county = dataJson[7].code;
					data.mhjAddress.street = dataJson[8].code;
					data.mjzAddress.liveProvince = dataJson[9].code;
					data.mjzAddress.liveCounty = dataJson[10].code;
					data.mjzAddress.liveStreets = dataJson[11].code;
					$scope.finishData = {
						applicant: data.rtnData,
						ywtbAffairsapply: {
							platform: '4',
							affairscode: "YJS0002",
							affairsname: "生育保险",
							Itemcode: "",
							subtime: date,
							suid: $scope.applyNo,
							suborgancode: data.centerCode,
							suborganname: data.centerAddress,
							formdata: [{
								"name": data.rtnData.fullname,
								"sex": data.rtnData.sex,
								"idcard": data.rtnData.idno,
								"nationc": data.nations,
								"isdszv": data.isdszv,
								"phone": data.mobile,
								"hjprovince": data.rtnData.hjprovince,
								"hjcity": data.rtnData.hjregion,
								"regAddrCodeA": data.rtnData.hjneighborhood,
								"regAddrCode": data.hjAddress.neighborhood,
								"regfulladdr": data.address,
								"jzprovince": data.rtnData.jzprovince,
								"jzcity": data.rtnData.jzregion,
								"resaddrcodeA": data.rtnData.jzneighborhood,
								"resaddrcode": data.jzAddress.liveNeighborhood,
								"resfulladdr": data.liveAddress,
								"boyn": data.boyn,
								"girln": data.girln,
								"spousename": data.maleName,
								"spousesex": "2",
								"spouseidcard": data.maleIdCard,
								"spousebirth": (data.maleIdCard).substring(6, 10) + "-" + (data.maleIdCard).substring(10, 12) + "-" + (data.maleIdCard).substring(12, 14),
								"spouseisdszv": data.mIsdszv,
								"spousephone": data.maleMobile,
								"pohjprovince": data.mhjAddress.province,
								"pohjcity": data.mhjAddress.county,
								"spouseregaddrcodeA": data.mhjAddress.street,
								"spouseregaddrcode": data.mhjAddress.neighborhood,
								"spouseregfulladdr": data.maddress,
								"pojzprovince": data.mjzAddress.liveProvince,
								"pojzcity": data.mjzAddress.liveCounty,
								"spouseresaddrcodeA": data.mjzAddress.liveStreet,
								"spouseresaddrcode": data.mjzAddress.liveNeighborhood,
								"spouseresfulladdr": data.mliveAddress,
								"spouseqsboy": "0",
								"spouseqsgirl": "0",
								"applytype": "0",
								"qsboy": "0",
								"qsgirl": "0",
								"isbl": "0",
								"zcc": "6",
								"iswy": data.iswy,
								"sqrznxx": data.childsList,
								"sign_png": data.picStr,
								"app_fname": data.rtnData.fullname,
								"app_fidcard": data.rtnData.idno,
								"app_hjaddress": data.address,
								"app_telphone": data.mobile,
								"app_jzaddress": data.liveAddress,
								"app_postcode": "200000",
								"app_mname": data.maleName,
								"app_midcard": data.maleIdCard,
								"app_sydate": data.app_sydate,
								"app_ifmulti": (data.app_ifmulti == "是") ? "1" : "0",
								"app_boys": data.app_boys || "",
								"app_girls": data.app_girls || "",
								"app_borg": data.app_borgid,
								"app_type": (data.app_type == "顺产") ? "1" : "2",
								"app_abortion": data.app_abortion || "",
								"app_sign": data.picStr,
								"app_applydate": data.app_applydate,
								"birth": data.date, //新增
								"nativepropc": data.nativepropc,
								"culturec": data.culturec,
								"marristatusc": data.marristatuscId,
								"spousenation": data.maleNations,
								"spousemarristatusc": data.spousemarristatuscId,
								"spousecompany": data.spousecompany,
								"spouseculturec": data.spouseculturec,
								"marritestifysn": data.marritestifysn,
								"marriregdate": data.marriregdate,
								"archivesdata": [{
										"archivescode": "YJS00020001",
										"affirscode": "YJS0002",
										"archivesname": "女方居民身份证",
										"needflag": "1",
										"imgscans": data.imgId1
									},
									{
										"archivescode": "YJS00020003",
										"affirscode": "YJS0002",
										"archivesname": "男方居民身份证",
										"needflag": "1",
										"imgscans": data.imgId2
									},
									{
										"archivescode": "YJS00020002",
										"affirscode": "YJS0002",
										"archivesname": "女方户籍证明",
										"needflag": "1",
										"imgscans": data.imgId3
									},
									{
										"archivescode": "YJS00020004",
										"affirscode": "YJS0002",
										"archivesname": "男方户籍证明",
										"needflag": "1",
										"imgscans": data.imgId4
									}
								],
								"display": {
									"姓名": data.rtnData.fullname,
									"性别": (data.rtnData.sex == '1') ? '男' : '女',
									"身份证号": data.rtnData.idno,
									"民族": data.nationName,
									"是否独生子女": (data.isdszv == "1") ? "是" : "否",
									"联系电话": data.mobile,
									"户籍省市": data.hjAddressName.province,
									"户籍区": data.hjAddressName.county,
									"户籍街道": data.hjAddressName.street,
									"户籍居委": data.hjAddressName.neighborhood,
									"户籍详细地址": data.address,
									"居住省市": data.jzAddressName.liveProvince,
									"居住区": data.jzAddressName.liveCounty,
									"居住街道": data.jzAddressName.liveStreet,
									"居住居委": data.jzAddressName.liveNeighborhood,
									"居住详细地址": data.liveAddress,
									"本次生育男孩数": data.boyn,
									"本次生育女孩数": data.girln,
									"配偶姓名": data.maleName,
									"配偶性别": "男",
									"配偶身份证号": data.maleIdCard,
									"配偶出生日期": (data.maleIdCard).substring(6, 10) + "-" + (data.maleIdCard).substring(10, 12) + "-" + (data.maleIdCard).substring(12, 14),
									"配偶是否独生子女": (data.mIsdszv == "1") ? "是" : "否",
									"配偶联系电话": data.maleMobile,
									"配偶户籍省市": data.mhjAddressName.province,
									"配偶户籍区": data.mhjAddressName.county,
									"配偶户籍街道": data.mhjAddressName.street,
									"配偶户籍居委": data.mhjAddressName.neighborhood,
									"配偶户籍详细地址": data.maddress,
									"配偶居住省市": data.mjzAddressName.liveProvince,
									"配偶居住区": data.mjzAddressName.liveCounty,
									"配偶居住街道": data.mjzAddressName.liveStreet,
									"配偶居住居委": data.mjzAddressName.liveNeighborhood,
									"配偶居住详细地址": data.mliveAddress,
									"配偶生育（流产）前男孩数": "0",
									"配偶生育（流产）前女孩数": "0",
									"业务办理类型": "生育",
									"生育（流产）前男孩数": "0",
									"生育（流产）前女孩数": "0",
									"是否委托办理": "否",
									"生育行为": "符合政策生育第一胎",
									"是否晚育": (data.iswy == "1") ? "是" : "否",
									//"子女信息": data.childsInfo,
									"子女信息": "(姓名:子女1,身份证号:310xxxxxxxxxxxxxxxxx,与子女关系:亲生,子女出生日期：1998-08-08,与父亲关系:亲生,与母亲关系:亲生,父是否婚前生育:否,母是否婚前生育:否,是否本次生育:是)",
									"申请人出生日期": "1975-08-09",
									"申请人户口性质": "非农村户口",
									"申请人文化程度": "大学本科教育",
									"申请人婚姻状况": "已婚",
									"配偶民族": "汉族",
									"配偶婚姻状况": " 已婚",
									"配偶工作单位": "XXXXX",
									"配偶文化程度": "大学本科教育",
									"婚姻登记号": "1231231231231",
									"婚姻变动日期": data.marriregdate
								}
							}]
						}
					}
					console.log(JSON.stringify($scope.finishData));
					$scope.submit();

				},
				error: function(err) {
					console.log(err);
				}
			});
		}
	}

	$scope.prev = function() {
		if(data.itemName == "灵活就业两项补贴") {
			$location.path("/materialJybtList");
		} else if(data.itemName == "敬老卡申领") {
			$location.path("/materialList");
		} else if(data.itemName == "生育保险") {
			$location.path("/materialSybxList");
		} else if(data.itemName == "跨省异地就医登记备案") {
			$location.path("/materialJygxList");
		}
	}
	$scope.submitApply();
	var date = new Date();
	var month = date.getMonth() + 1;
	// 打印凭条
	$scope.print = function() {
		var lodop = $.device.printGetLodop('');
		if(data.itemName == "灵活就业两项补贴") {
			lodop.ADD_PRINT_BARCODE(38, 34, 307, 47, "128A", "751021900100501");
		} else if(data.itemName == "敬老卡申领") {
			lodop.ADD_PRINT_BARCODE(38, 34, 307, 47, "128A", "751021900200401");
		} else if(data.itemName == "生育保险") {
			lodop.ADD_PRINT_BARCODE(38, 34, 307, 47, "128A", "751021900300301");
		} else if(data.itemName == "跨省异地就医登记备案") {
			//lodop.ADD_PRINT_BARCODE(38, 34, 307, 47, "128A", "751021900400201");
		}
		if(data.itemName == "跨省异地就医登记备案") {
			lodop.ADD_PRINT_TEXT(140,223,402,50,"上海市政务服务告知单");
			lodop.SET_PRINT_STYLEA(0,"FontSize",20);
			lodop.SET_PRINT_STYLEA(0,"Bold",1);
		} else {
			lodop.ADD_PRINT_TEXT(170, 574, 200, 30, "扫一扫,查进度");
			lodop.SET_PRINT_STYLEA(0, "FontSize", 10);
			lodop.ADD_PRINT_BARCODE(38, 550, 168, 146, "QRCode", $scope.urladdress);
			lodop.ADD_PRINT_TEXT(190, 165, 600, 50, "上海市政务服务申请材料 收件凭证");
			lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
			lodop.SET_PRINT_STYLEA(0, "BOLD", 1);
		}
		lodop.ADD_PRINT_TEXT(250,40,600,30,"申请事项：");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "BOLD", 1);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(250,140,600,30, "异地就医一件事");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(280,40,600,30,"申请人：");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "BOLD", 1);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(280,141,600,30, data.idCardName);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(280,445,600,30, "联系电话：");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "BOLD", 1);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(280,561,600,30, data.mobile);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		if(data.itemName == "跨省异地就医登记备案") {
			lodop.ADD_PRINT_TEXT(330,39,665,65,"    您已成功提交就医关系转移、跨省异地就医登记备案2项事项相关申请手续");
			lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
			lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		} else if(data.itemName == "生育保险") {
			lodop.ADD_PRINT_TEXT(330, 65, 700, 30, "经核查，您（单位）提交的申请材料齐全，符合法定形式，现予收件。");
			lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
			lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
			lodop.ADD_PRINT_TEXT(370, 25, 700, 120, "    根据规定，承办部门将在出具本凭证之日起5个工作日内，作出受理或不予受理的决定，申请材料不齐全或者不符合法定形式的，将在5个工作日内一次告知需要补正的材料。如在5个工作日内未被告知需要补正材料的，则视为正式受理，本凭证即为受理凭证。申请享受生育保险待遇计划生育情况审核通过后，生育保险金将在20日内发放到您提交的银行卡内。");
			lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
			lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		} else {
			lodop.ADD_PRINT_TEXT(330, 65, 700, 30, "经核查，您（单位）提交的申请材料齐全，符合法定形式，现予收件。");
			lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
			lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
			lodop.ADD_PRINT_TEXT(370, 25, 700, 120, "    根据规定，承办部门将在出具本凭证之日起5个工作日内，作出受理或不予受理的决定，申请材料不齐全或者不符合法定形式的，将在5个工作日内一次告知需要补正的材料。如在5个工作日内未被告知需要补正材料的，则视为正式受理，本凭证即为受理凭证。");
			lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
			lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		}
		lodop.ADD_PRINT_TEXT(600,65,670,150, "收件材料清单");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "BOLD", 1);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(400,450,300,30, "收件日期：" + date.getFullYear() + "年" + month + "月" + date.getDate() + "日");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		if(data.itemName == "灵活就业两项补贴") {
			lodop.ADD_PRINT_TABLE(700, 50, 700, 1000, "<style>table{font-family:'FangSong'}table td{border:1px solid} tr td:nth-child(1){text-align:center}tr td:nth-child(2){padding-left:20px;}tr td:nth-child(3){padding-left:20px;} table th{border:1px solid} table{border-collapse: collapse}</style><table><tr><th style='width:60px'>序号</th><th style='width:300px'>申请材料</th><th style='width:250px'>材料来源</th></tr><tr><td>1</td><td>居民身份证</td><td>电子文件/纸质</td></tr><tr><td>2</td><td>劳动手册</td><td>原件(待放入自助取证柜)</td></tr></table>");
		} else if(data.itemName == "敬老卡申领") {
			lodop.ADD_PRINT_TABLE(700, 50, 700, 1000, "<style>table{font-family:'FangSong'}table td{border:1px solid} tr td:nth-child(1){text-align:center}tr td:nth-child(2){padding-left:20px;}tr td:nth-child(3){padding-left:20px;} table th{border:1px solid} table{border-collapse: collapse}</style><table><tr><th style='width:60px'>序号</th><th style='width:300px'>申请材料</th><th style='width:250px'>材料来源</th></tr><tr><td>1</td><td>居民身份证</td><td>电子文件/纸质</td></tr><tr><td>2</td><td>个税声明</td><td>原件(待放入自助取证柜)</td></tr></table>");
		} else if(data.itemName == "生育保险") {
			lodop.ADD_PRINT_TABLE(700, 50, 700, 1000, "<style>table{font-family:'FangSong'}table td{border:1px solid} tr td:nth-child(1){text-align:center}tr td:nth-child(2){padding-left:20px;}tr td:nth-child(3){padding-left:20px;} table th{border:1px solid} table{border-collapse: collapse}</style><table><tr><th style='width:60px'>序号</th><th style='width:300px'>申请材料</th><th style='width:250px'>材料来源</th></tr><tr><td>1</td><td>女方居民身份证</td><td>电子文件/纸质</td></tr><tr><td>2</td><td>男方居民身份证</td><td>电子文件</td></tr><tr><td>3</td><td>女方户籍证明</td><td>电子文件</td></tr><tr><td>4</td><td>男方户籍证明</td><td>电子文件</td></tr><tr><td>5</td><td>生育医学证明</td><td>原件</td></tr><tr><td>6</td><td>银行卡</td><td>电子文件</td></tr><tr><td>7</td><td>男方结婚证明</td><td>电子文件/纸质</td></tr><tr><td>8</td><td>女方结婚证明</td><td>电子文件/纸质</td></tr></table>");
		} else if(data.itemName == "跨省异地就医登记备案") {
			lodop.ADD_PRINT_TABLE(650, 50, 700, 1000, "<style>table{font-family:'FangSong'}table td{border:1px solid} tr td:nth-child(1){text-align:center}tr td:nth-child(2){padding-left:20px;}tr td:nth-child(3){padding-left:20px;} table th{border:1px solid} table{border-collapse: collapse}</style><table><tr><th style='width:60px'>序号</th><th style='width:300px'>申请材料</th><th style='width:250px'>材料来源</th></tr><tr><td>1</td><td>居民身份证</td><td>电子文件/纸质</td></tr></table>");
		}
		lodop.PRINT();
	};
	$scope.print();
});
app.controller("qrCodeController", function($scope, $route, $http, $rootScope, $location, data, $timeout, appFactory) {
	var date = new Date();

	function formatDate(date) {
		var date = new Date(date);
		var YY = date.getFullYear() + '-';
		var MM = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
		var DD = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate());
		var hh = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
		var mm = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
		var ss = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds());
		return YY + MM + DD + " " + hh + mm + ss;
	}
	$scope.applyNo = $location.search().applyNo;
	$scope.itemName = $location.search().itemName;
	$scope.name = $location.search().name;
	$scope.date = formatDate(date);
});