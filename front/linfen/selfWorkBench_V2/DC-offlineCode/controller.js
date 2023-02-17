function removeAnimate(ele) {
	//	$(ele).css({
	//		"transform": "translateY(0px)",
	//		"top": 0
	//	}).removeClass('transformto')
}

function addAnimate(ele) {
	$(ele).css({
		'margin-top': '300px',
		'opacity': '0'
	});
	$(ele).animate({
		marginTop: '0',
		opacity: '1'
	}, 1000);
}
//白名单
var nameList = [
	'320721199408210016',
	'320723199611295230',
	'310112199802105619',
	'342626199712134910',
	'420821199410233013',
	'370124198803060020',
	'34252919890426601X',
	'362402199812300527',
	'310226199402150019',
	'320681199610262013',
	'430426199804106174',
	'14060219961016051X',
	'429004199312101138',
]
app.controller('loginType', function($state, $scope, appData) {
	//显示社保卡登录选项
	$scope.ShowSscard = jQuery.getConfigMsg.isShowSscard;
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.operation = "请选择登录方式";
	appData.SwipeType = 'sbCard';
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		if(type == "qinshu") {
			$state.go('qinshuCode');
		} else {
			$state.go("login");
		}
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller('qinshuCode', function($state, $scope, appData) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$state.go('loginType');
	}
	$scope.imgPath = "../libs/common/images/dicon/" + acBridgeMac.imagePath() + "/qrCode-self.gif";
	$scope.citizenCloud = function() {
		//		var code = "https://s.sh.gov.cn/ecefb115419e9b2747b746a6683f081653216279605";
		$.device.qrCodeOpen(function(code) {
			$scope.isLoading = true;
			code = code.replace(/[\r\n]/g, "")
			$.ajax({
				type: "post",
				url: $.getConfigMsg.preUrlSelf + "/selfapi/workPlatform/checkRelativesCode.do",
				dataType: 'json',
				data: {
					qrCode: code,
				},
				success: function(dataJonsp) {
					$scope.isLoading = false;
					if(dataJonsp.success == true) {
						for(var i = 0; i < dataJonsp.data.length; i++) {
							if(dataJonsp.data[i].isRelation == '1') {
								appData.licenseNumber = dataJonsp.data[i].zjhm;
								appData.licenseName = dataJonsp.data[i].xm;
								$state.go("print");
							}
						}
					} else if(dataJonsp.success == false) {
						$scope.isAlert = false;
						if(dataJonsp.data == "") {
							$scope.msg = dataJonsp.msg;
						} else {
							try {
								$scope.msg = dataJonsp.data.message;
							} catch(e) {
								$scope.msg = "接口异常，请重试";
							}
						}
					} else {
						$scope.isAlert = false;
						if(dataJonsp.data == "") {
							$scope.msg = dataJonsp.msg;
						} else {
							try {
								$scope.msg = dataJonsp.data.message;
							} catch(e) {
								$scope.msg = "接口异常，请重试";
							}
						}

					}
				},
				error: function(err) {
					$scope.isLoading = false;
					$scope.isAlert = false;
					$scope.msg = '接口异常，请重试';
				}
			})
		})
	}
	$scope.citizenCloud();
});
app.controller('login', function($scope, $http, $state, appData, appFactory, $timeout) {
	$scope.loginType = appData.loginType;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$.device.GoHome();
	}
	switch($scope.loginType) {
		case "idcard":
			$scope.operation = "身份证登录";
			break;
		case "ukey":
			$scope.loginBtn = true;
			$scope.operation = "ukey登录";
			break;
		case "cloud":
			$scope.operation = "随申办";
			break;
		case "sscard":
			$scope.operation = "社保卡登录";
			break;
	}

	function getAge(UUserCard) {
		var myDate = new Date();
		var month = myDate.getMonth() + 1;
		var day = myDate.getDate();
		var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1;
		if(UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) {
			age++;
		}
		return age;
	}
	$scope.nextStep = function() {
		if(!checkIdCard(appData.licenseNumber)) {
			$scope.isAlert = true;
			$scope.msg = "身份证格式不正确";
			$scope.alertConfirm = function() {
				$scope.isAlert = false;
				$scope.GoHome();
			}
		} else if(appData.licenseName == "" || appData.licenseName == null || appData.licenseName == undefined) {
			$scope.isAlert = true;
			$scope.msg = "未读取到身份证姓名";
			$scope.alertConfirm = function() {
				$scope.isAlert = false;
				$scope.GoHome();
			}
		} else {
			$state.go("print");
		}
	};

		// $scope.idcardLogin = function(info, images) {
		// 	appData.licenseNumber = '430426199804106174';
		// 	appData.licenseName = '邹天奇';
		// 	$scope.nextStep();
		// }
		// $scope.idcardLogin();

	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
			appData.VALIDENDDAY = info.ValidtermOfEnd;
			appData.VALIDSTARTDAY = info.ValidtermOfStart;
			$scope.nextStep();
		} else {
			layer.msg("没有获取到")
		}
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

	$scope.sscardLogin = function(info) {
		if(info) {
			if(info.Ssn != "" && info.Ssn != null && info.Ssn != undefined) {
				appData.licenseNumber = info.Ssn;
				appData.licenseName = info.PeopleName;
				$.device.ssCardClose();
				$scope.nextStep();
			} else {
				$scope.isAlert = true;
				$scope.msg = "未读取到您的社保卡信息,请重试";
			}

		} else {
			layer.msg("没有获取到")
		}
	}

	$scope.prevStep = function() {
		$state.go("loginType");
	}
});
app.controller("print", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoading = true;
	$scope.userName = appData.licenseName;
	$scope.imgSrc = 'images/lxm2.png';
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}

	$scope.choice = function(index, item) {
		$scope.current = index;
		if(item == "申领") {
			$scope.creatOfflineCode('1');
		} else if(item == "打印") {
			$scope.priviewOfflineCode('1')
		} else if(item == "挂失") {
			$scope.isAlert = true;
			$scope.msg = "<p>确认挂失？</p><p>挂失后已申领的“离线随申码”将失效</p>"
			$scope.alertConfirm = function() {
				$scope.isAlert = false;
				$scope.reportlossOfflineCode();
			}
		} else if(item == "补领") {
			$scope.item = "补领";
			$scope.isAlert = true;
			$scope.msg = "<p>确认补领？ </p><p>补领将自动挂失已申领的“离线随申码”</p><p>补领的“离线随申码”有效期重新计算</p>"
			$scope.alertConfirm = function() {
				$scope.isAlert = false;
				$scope.creatOfflineCode('2')
			}

		}
	}

	//离线码挂失接口
	$scope.reportlossOfflineCode = function() {
		$scope.isLoading = true;
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/offlineCode/reportlossOfflineCode.do",
			dataType: 'json',
			data: {
				name: encodeURI(appData.licenseName),
				idCard: appData.licenseNumber
			},
			success: function(res) {
				console.log(res);
				console.log(1);
				if(res.code == "0") {
					if($scope.item == '补领') {
						$scope.creatOfflineCode('2');
					} else {
						$scope.isLoading = false;
						$scope.isAlert = true;
						$scope.msg = res.message;
						$scope.alertConfirm = function() {
							$scope.isAlert = false;
							$state.go('loginType');
						}
					}
					//模块使用记录
					$scope.jsonStr = {
						SUCCESS: "true",
						data: {
							name: '随申码离线服务',
							Number: '',
						}
					}
					recordUsingHistory('随申码离线服务', '挂失', '随申码离线服务', appData.licenseName, appData.licenseNumber, '', '', JSON.stringify($scope.jsonStr));
				} else {
					$scope.isLoading = false;
					$scope.isAlert = true;
					$scope.msg = res.message;
					$scope.alertConfirm = function() {
						$scope.isAlert = false;
					}
				}
			},
			error: function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "挂失失败，请联系工作人员";
				$scope.alertConfirm = function() {
					$scope.isAlert = false;
				}
				console.log(err);
			}
		});
	}

	//离线码预览接口
	$scope.priviewOfflineCode = function(sign) {
		var fileType = "0";
		if(sign == "0") {
			fileType = "0";
		} else {
			try {
				//获取是否有离线码机器
				$.config.load('isLxmPrintMachine', function(g) {
					jQuery.getConfigMsg.isLxmPrintMachine = g;
				});
				if(jQuery.getConfigMsg.isLxmPrintMachine == 'Y') {
					fileType = '0';
				} else {
					fileType = '1';
				}
			} catch(e) {}
		}
		$scope.isLoading = true;
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/offlineCode/priviewOfflineCode.do",
			dataType: 'json',
			data: {
				name: encodeURI(appData.licenseName),
				idCard: appData.licenseNumber,
				fileType: fileType
			},
			success: function(res) {
				console.log(res);
				if(res.code == "0") {
					if(sign == "1") {
						$scope.print(fileType, res.fileName, res.data, res.data);
					} else {
						var img = "data:image/png;base64," + res.data;
						$scope.img = img;
					}
					$scope.isLoading = false;
					//模块使用记录
					$scope.jsonStr = {
						SUCCESS: "true",
						data: {
							name: '随申码离线服务',
							Number: '',
						}
					}
					recordUsingHistory('随申码离线服务', '申领', '随申码离线服务', appData.licenseName, appData.licenseNumber, '', '', JSON.stringify($scope.jsonStr));
				} else {
					$scope.isLoading = false;
					$scope.isAlert = true;
					$scope.msg = res.message;
					$scope.alertConfirm = function() {
						$scope.isAlert = false;
						$.device.GoHome();
					}
				}
			},
			error: function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "接口异常，请联系工作人员";
				$scope.alertConfirm = function() {
					$scope.isAlert = false;
					$.device.GoHome();
				}
				console.log(err);
			}
		});
	}

	//打印方式
	$scope.print = function(fileType, fileName, imgBase64, pdfbase64) {
		$scope.isAlert = true;
		$scope.msg = "<p>正在打印离线随申码，请等待…</p><p>（大约需要20秒）</p>";
		if(jQuery.getConfigMsg.isPrintBase64 == "Y") {
			try {
				if(acBridgeMac.vendor() == "zhdevice") {
					try{
						window.AppHost.getLogHelper().log("万达isLxmPrintMachine"+jQuery.getConfigMsg.isLxmPrintMachine);
						zhcmCapture.zh_getStatusSyncFunc(function(value){
							window.AppHost.getLogHelper().log("万达zh_getStatusSyncFunc"+value);
							if(value=='true'){
								zhcmCapture.zhOffice_Base_PrintCard(imgBase64)
							}else{
								zhcmCapture.zh_LXM_Print(pdfbase64)
							}
						})
					}catch(e){
						zhcmCapture.zh_LXM_Print(pdfbase64)
					}
					// $.post('http://localhost:10210/inteGration/hardwareControl/api/printNew', {
					// 	tranType: 1,
					// 	fileExtent: 'pdf',
					// 	prnFlag: 0,
					// 	fileContent: pdfbase64
					// }).done(function(info) {}).fail(function(err) {
					// 	alert(JSON.stringify(err))
					// });
				} else {
					$.device.urlPdfPrint("", "", function() {}, pdfbase64);
				}
			} catch(e) {}
		} else {
			if(fileType == "1") {
				$scope.path = "D:\\pdfPrint.pdf";
				$scope.pdfPrint = $.getConfigMsg.preUrlSelf + "/selfapi/offlineCode/getOfflineCodePDF.do?fileName=" + fileName
				$.device.urlPdfPrint($scope.pdfPrint, $scope.path, function() {}, '');
			} else if(fileType == "0") {
				var img = "data:image/png;base64," + imgBase64;
				$scope.printImg = img;
				try {
					if(window.external.AppKamfu == "kamfu") { //金赋
						//alert($scope.printImg)
						window.external.Office_Base_PrintCard($scope.printImg);
						return;
					}
				} catch(e) {}
				if(acBridgeMac.vendor() == 'wonders') { //万达
					let lodop = $.device.printGetLodop('');
					lodop.SET_PRINTER_INDEX('DASCOM DC-2300');
					lodop.SET_PRINT_PAGESIZE(1, "240mm", "330mm", "");
					lodop.ADD_PRINT_IMAGE(0, 0, 210, 350, "<img src='" + $scope.printImg + "'>");
					lodop.SET_PRINT_STYLEA(0, "Stretch", 1); //按原图比例(不变形)缩放模式
					lodop.PRINT();
				} else if(acBridgeMac.vendor() == 'zhuofansoft') {
					try { //卓繁
						DSDirect300Kp.AKeyToPrint(imgBase64);
					} catch(e) {}
				} else { //万达
					let lodop = $.device.printGetLodop('');
					lodop.SET_PRINTER_INDEX('DASCOM DC-2300');
					lodop.SET_PRINT_PAGESIZE(1, "240mm", "330mm", "");
					lodop.ADD_PRINT_IMAGE(0, 0, 210, 350, "<img src='" + $scope.printImg + "'>");
					lodop.SET_PRINT_STYLEA(0, "Stretch", 1); //按原图比例(不变形)缩放模式
					lodop.PRINT();
				}
			} else {
				$scope.path = "D:\\pdfPrint.pdf";
				$scope.pdfPrint = $.getConfigMsg.preUrlSelf + "/selfapi/offlineCode/getOfflineCodePDF.do?fileName=" + fileName
				$.device.urlPdfPrint($scope.pdfPrint, $scope.path, function() {}, '');
			}
		}
	}

	//离线码生成接口
	$scope.creatOfflineCode = function(type) {
		var fileType = "1";
		try {
			//获取是否有离线码机器
			$.config.load('isLxmPrintMachine', function(g) {
				jQuery.getConfigMsg.isLxmPrintMachine = g;
			});
			if(jQuery.getConfigMsg.isLxmPrintMachine == 'Y') {
				fileType = '0';
			} else {
				fileType = '1';
			}
		} catch(e) {}
		$scope.isLoading = true;
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/offlineCode/creatOfflineCode.do",
			dataType: 'json',
			data: {
				name: encodeURI(appData.licenseName),
				idCard: appData.licenseNumber,
				type: type,
				fileType: fileType, //1:pdf   0:png
				machineId: jQuery.getConfigMsg.uniqueId || ""
			},
			success: function(res) {
				console.log(res);
				if(res.code == "0") {
					$scope.print(fileType, res.fileName, res.data, res.data);
					$scope.alertConfirm = function() {
						$scope.isAlert = false;
						$.device.GoHome();
					}
					$timeout(function() {
						$.device.GoHome();
					}, 15000);
					$scope.isLoading = false;
					//模块使用记录
					$scope.jsonStr = {
						SUCCESS: "true",
						data: {
							name: '随申码离线服务',
							Number: '',
						}
					}
					$scope.modelName = (type == "1") ? "申领" : '补领';
					recordUsingHistory('随申码离线服务', $scope.modelName, '随申码离线服务', appData.licenseName, appData.licenseNumber, '', '', JSON.stringify($scope.jsonStr));
				} else if(res.code == "7") {
					$scope.reportlossOfflineCode();
				} else {
					$scope.isLoading = false;
					$scope.isAlert = true;
					$scope.msg = res.message;
					$scope.alertConfirm = function() {
						$scope.isAlert = false;
					}
				}
			},
			error: function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "生成失败，请联系工作人员";
				$scope.alertConfirm = function() {
					$scope.isAlert = false;
				}
				console.log(err);
			}
		});
	}

	//离线码状态查询
	$scope.queryOfflineCodeStatus = function() {
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/offlineCode/queryStatus.do",
			dataType: 'json',
			data: {
				name: encodeURI(appData.licenseName),
				idCard: appData.licenseNumber,
			},
			success: function(res) {
				$scope.isLoading = false;
				if(res != "" && res != undefined && res != null) {
					if(res.code == "0" && res.code != "" && res.code != undefined && res.code != null) {
						$scope.info = res.data;
						$scope.btnList = ['打印', '挂失', '补领'];
					} else if((res.code == "8" || res.code == "9") && res.code != "" && res.code != undefined && res.code != null) {
						$scope.isLoading = false;
						$scope.isAlert = true;
						$scope.msg = "用户身份认证失败";
						$scope.alertConfirm = function() {
							$state.go('loginType');
						}
					} else if(res.code == "10" && res.code != "" && res.code != undefined && res.code != null) {
						$scope.isLoading = false;
						$scope.isAlert = true;
						$scope.msg = "用户身份认证超时";
						$scope.alertConfirm = function() {
							$state.go('loginType');
						}
					} else if(res.code == "11" && res.code != "" && res.code != undefined && res.code != null) {
						$scope.isLoading = false;
						$scope.isAlert = true;
						$scope.msg = "您不符合申领条件";
						$scope.alertConfirm = function() {
							$state.go('loginType');
						}
					} else if(res.code == "6" && res.code != "" && res.code != undefined && res.code != null) {
						$scope.isLoading = false;
						$scope.isAlert = true;
						$scope.msg = "当前离线码已失效";
						$scope.alertConfirm = function() {
							$state.go('loginType');
						}
					} else {
						$scope.isLoading = false;
						$scope.isAlert = true;
						//						$scope.msg = "暂无有效离线码，请联系工作人员";
						$scope.msg = "无效离线码，请联系工作人员";
						$scope.alertConfirm = function() {
							$state.go('loginType');
						}
					}
				} else {
					$scope.isLoading = false;
					$scope.isAlert = true;
					$scope.msg = "接口异常，请联系工作人员";
					$scope.alertConfirm = function() {
						$state.go('loginType');
					}
				}
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	$scope.queryOfflineCodeStatus();
});