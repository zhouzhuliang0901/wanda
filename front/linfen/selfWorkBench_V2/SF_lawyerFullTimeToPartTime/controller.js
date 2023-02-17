app.controller('personChoice', function($scope, $state, appData, $sce, appFactory) {
	$scope.isAlert = false;
	appData.funName = "律师专职执业变更兼职执业";
	appData.itemCode = "310100284000-05";
	$scope.operation = "请选择办理方式";
	appData.SwipeType = "idCard"; // 刷身份证
	appData.handle = ""; // 存储办理人    "本人"或"代理人"
	appData.isSon = false; // 是否是被监护人
	$scope.chooseSwipeType = function(handle) {
		appData.handle = handle;
		// 本人和代办都走刷脸的流程,界面提示信息不同
		if(appData.handle == "agent") {
			appData.operation = "监护人登录方式";
		} else {
			appData.operation = "本人登录方式";
		}
		$state.go('loginType');
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.alertCancel = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		$.device.GoHome();
	}
});
app.controller('loginType', function($state, $scope, appData, $http) {
	$scope.operation = appData.operation;
	appData.a = 1;
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	addAnimate($('.scrollBox2'))
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go('login');
	}
	$scope.prevStep = function() {
		$state.go("personChoice");
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory, $timeout) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.isLoding = true;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$.state.go("main");
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
			$scope.operation = "随申办登录";
			break;
	}
	//获取token ------2、比对成功后，根据tokenSNO获取access_token
	$scope.getAccessToken = function(tokenSNO) {
		var rec = $.ajax({
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getAccessToken.do",
			type: "post",
			dataType: "json",
			jsonp: "jsonpCallback",
			timeout: 5000,
			data: {
				tokenSNO: tokenSNO,
			},
			success: function(res) {
				console.log(res);
				if(res.SUCCESS === true) {
					appData.token = res.accessToken;
					if(appData.handle == "agent") {
						appData.operation = "被监护人登录方式"
						appData.source = "idcardOrCitizen";
						appData.isSon = true;
						$state.go("loginType");
					} else {
						$state.go("info");
					}
				} else {
					$scope.isAlert = true;
					$scope.msg = "数据加载异常,请重试";
					$scope.alertConfirm = function() {
						$state.go("loginType");
					}
				}
			},
			error: function(err) {
				console.log(err);
			},
			complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
				if(status === 'timeout' || status === 'error') { //超时,status还有success,error等值的情况
					rec.abort();
				}
			}
		})
	}
	//获取token ------1、两照对比获取tokenSNO
	$scope.getTokenSNO = function(face, photograph) {
		var idCardPhoto = face;
		var capturePhoto = photograph;
		var rec = $.ajax({
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getTokenSNO.do",
			type: "post",
			dataType: "json",
			//			jsonp: "jsonpCallback",
			data: {
				name: encodeURI(appData.licenseName),
				idCard: appData.licenseNumber,
				facePhoto: capturePhoto,
				copyIDPhoto: idCardPhoto
			},
			success: function(res) {
				console.log(res);
				if(res.SUCCESS === true && res.verify === 1) {
					appData.tokenSNO = res.tokenSNO;
					$scope.getAccessToken(res.tokenSNO);
				} else {
					$scope.isAlert = true;
					$scope.msg = "数据加载异常,请重试";
					$scope.alertConfirm = function() {
						$state.go("loginType");
					}
				}
			},
			error: function(err) {
				console.log(err);
			},
			complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
				if(status === 'timeout' || status === 'error') { //超时,status还有success,error等值的情况
					rec.abort();
				}
			}
		})
	}
	$scope.idcardLogin = function(info, images) {
		if(info) {
			if(appData.isSon) {
				// 被监护人刷身份证
				$scope.faceImage = images;
				appData.licenseName = info.Name;
				appData.licenseNumber = info.Number;
				appData.sonInfo = {
					name: appData.licenseName,
					idCard: appData.licenseNumber
				};
				if(appData.sonInfo.idCard == appData.fatherInfo.idCard) {
					layer.msg("被监护人的身份证不能与监护人的身份证一致");
					return;
				} else {
					$state.go("info");
				}
			} else {
				// 本人或者监护人刷身份证
				$scope.faceImage = images;
				$scope.loginType = 'recognition';
				appData.licenseName = info.Name;
				appData.licenseNumber = info.Number;
				appData.fatherInfo = {
					name: appData.licenseName,
					idCard: appData.licenseNumber
				};
			}
		} else {
			layer.msg("很抱歉,没有获取到您的信息,请重试")
		}
	}

	$scope.getResult = function(img) {
		$scope.img = img;
		appData.img = img;
		// 如果是本人就去信息展示界面，若果是代办就去验证被监护人信息
		if(appData.handle == "agent") {
			appData.operation = "被监护人登录方式"
			appData.source = "idcardOrCitizen";
			appData.isSon = true;
			$state.go("loginType");
		} else {
			$state.go("info");
		}
	}

	$scope.prevStep = function() {
		$state.go("loginType");
		$.device.Face_Close();
	}
	$scope.citizenLogin = function(info) {
		if(info) {
			if(appData.qrCodeType == "shiminyun") {
				var idcardInfo = info.result.data;
				if(appData.isSon) {
					appData.licenseNumber = idcardInfo.idcard;
					appData.licenseName = idcardInfo.realname;
					appData.sonInfo = {
						name: appData.licenseName,
						idCard: appData.licenseNumber
					};
					if(appData.sonInfo.idCard == appData.fatherInfo.idCard) {
						layer.msg("被监护人的身份证不能与监护人的身份证一致");
						return;
					} else {
						$scope.getTokenSNO(photo, photo);
					}
				} else {
					appData.licenseNumber = idcardInfo.idcard;
					appData.licenseName = idcardInfo.realname;
					appData.fatherInfo = {
						name: appData.licenseName,
						idCard: appData.licenseNumber
					};
					$scope.getTokenSNO(photo, photo);
				}
			} else {
				$scope.suishenMaLogin(info);
			}
		} else {
			layer.msg("很抱歉,没有获取到您的信息,请重试")
		}
	}
	$scope.suishenMaLogin = function(info) {
		var idcardInfo = info;
		if(info) {
			if(appData.isSon) {
				appData.licenseName = idcardInfo.zwdtsw_name;
				appData.licenseNumber = idcardInfo.zwdtsw_cert_id;
				appData.sonInfo = {
					name: appData.licenseName,
					idCard: appData.licenseNumber
				};
				if(appData.sonInfo.idCard == appData.fatherInfo.idCard) {
					layer.msg("被监护人的身份证不能与监护人的身份证一致");
					return;
				} else {
					$scope.getAccessToken(appData.tokenSNO);
				}
			} else {
				appData.licenseNumber = idcardInfo.zwdtsw_cert_id;
				appData.licenseName = idcardInfo.zwdtsw_name;
				appData.fatherInfo = {
					name: appData.licenseName,
					idCard: appData.licenseNumber
				};
				$scope.getAccessToken(appData.tokenSNO);
			}
		} else {
			layer.msg("很抱歉,没有获取到您的信息,请重试")
		}
	}
})
app.controller('info', function($state, $scope, appData, $location, appFactory, $timeout, $rootScope) {
	$scope.funName = appData.funName;
	appData.isUpload = [];
	appData.listImg = [];
	$scope.concel = "false";
	$scope.isAlert = false;
	if(appData.handle == "agent") {
		appData.licenseName = appData.sonInfo.name;
		appData.licenseNumber = appData.sonInfo.idCard;
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$(".form_datetime").datetimepicker({
		format: "yyyy-mm-dd", //显示日期格式
		autoclose: true,
		todayBtn: true,
		minView: "month", //只选择到天自动关闭
		language: 'zh-CN',
		pickerPosition: 'bottom-left',
	});
	$scope.certificatesTypeList = certificatesType;
	//个人信息
	$scope.stName = appData.licenseName;
	$scope.stIdCard = appData.licenseNumber;
	$scope.stSex = ((parseInt(appData.licenseNumber.substring(16, 17)) % 2) == 0) ? "女" : "男";
	$scope.dtBirth = (appData.licenseNumber).substring(6, 10) + "-" + (appData.licenseNumber).substring(10, 12) + "-" + (appData.licenseNumber).substring(12, 14);
	//若上一步则数据缓存
	$scope.stMobile = appData.stMobile || "";
	$scope.certificatesType = appData.certificatesType;
	$scope.certificatesCode = appData.certificatesCode;
	$scope.address = appData.address;
	$scope.postCode = appData.postCode;
	$scope.bldCode = appData.bldCode;
	$scope.nationList = nations;
	//办理点
	$scope.bldList = bld;
	//监听省市 区域变化
	$timeout(function() {
		selectBlur();
		$scope.$watch("nation", function(val) {
			console.log(val.shortname);
		});
		$scope.$watch("certificatesType", function(val) {
			if(val) {
				if(val.id == "a") {
					$scope.certificatesCode = $scope.stIdCard;
				}
			}
		});
	}, 100);
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(!isPhoneAvailable($scope.stMobile)) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确联系电话！";
				return;
			}
			if(isBlank($scope.certificatesType)) {
				$scope.isAlert = true;
				$scope.msg = "请选择证件类型！";
				return;
			}
			if(isBlank($scope.certificatesCode)) {
				$scope.isAlert = true;
				$scope.msg = "请输入证件号码！";
				return;
			}
			if(isBlank($scope.address)) {
				$scope.isAlert = true;
				$scope.msg = "请输入住址！";
				return;
			}
			if(isBlank($scope.postCode)) {
				$scope.isAlert = true;
				$scope.msg = "请输入邮编！";
				return;
			}
			if(isBlank($scope.bldCode)) {
				$scope.isAlert = true;
				$scope.msg = "请选择办理点！";
				return;
			}
			if(isBlank($scope.contactAddress)) {
				$scope.isAlert = true;
				$scope.msg = "请输入联系地址！";
				return;
			}
		} while (condFlag);
		//提交参数集合
		condFlag = true;
		$scope.isLoading = true;
		$scope.licenseNumber = appData.encrypt_identity || appData.licenseNumber;
		appData.stSex = $scope.stSex;
		appData.dtBirth = $scope.dtBirth;
		appData.nation = $scope.nation;
		appData.stMobile = $scope.stMobile;
		appData.certificatesType = $scope.certificatesType;
		appData.certificatesCode = $scope.certificatesCode;
		appData.address = $scope.address;
		appData.contactAddress = $scope.contactAddress;
		appData.postCode = $scope.postCode;
		appData.bldCode = $scope.bldCode;
		if(appData.handle == "agent") {
			$state.go("agentInfo");
		} else {
			$state.go("pickUpMethod");
		}
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}
});
app.controller('agentInfo', function($state, $scope, appData, $location, appFactory, $timeout, $rootScope) {
	$scope.funName = appData.funName;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.agentCertificatesTypeList = agentCertificatesType;
	$scope.agentWorkList = mechanism;
	//个人信息
	$scope.stAgentName = appData.fatherInfo.name;
	$scope.stAgentIdCard = appData.fatherInfo.idCard;
	$timeout(function() {
		$scope.$watch("agentWork", function(val) {
			if(val) {
				console.log($scope.agentWork);
				$scope.agentWorkId = getKeyByName(mechanism, val);
				console.log($scope.agentWorkId);
			}
		});
	}, 100)
	$scope.nextStep = function() {
		//提交参数集合
		appData.stAgentName = $scope.stAgentName;
		appData.stAgentIdCard = $scope.stAgentIdCard;
		appData.agentCertificatesCode = $scope.agentCertificatesCode;
		appData.agentMobile = $scope.agentMobile;
		appData.agentAddress = $scope.agentAddress;
		appData.agentEmail = $scope.agentEmail;
		appData.agentPostCode = $scope.agentPostCode;
		appData.agentWork = $scope.agentWork;
		appData.agentWorkId = $scope.agentWorkId;
		$state.go("pickUpMethod");
	}
	$scope.prevStep = function() {
		$state.go("info");
	}
});
app.controller('pickUpMethod', function($state, $scope, appData, $location, appFactory, $timeout, $rootScope) {
	$scope.funName = appData.funName;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.sendWayList = sendWay;
	$scope.changeSendWay = function(id,index,name){
		$scope.currentSendWay = index;
		$scope.sendWay = {
			"id":id,
			"name":name,
		}
		console.log($scope.sendWay);
	}
	//个人信息
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.sendWay)) {
				$scope.isAlert = true;
				$scope.msg = "请选择送达方式！";
				return;
			}
		} while (condFlag);
		//提交参数集合
		condFlag = true;
		$scope.isLoading = true;
		appData.sendWay = $scope.sendWay;
		appData.sendAddress = $scope.sendAddress;
		appData.sendPostCode = $scope.sendPostCode;
		appData.sendOther = $scope.sendOther;
		if(isBlank($scope.agentCertificatesType)) {
			appData.agentCertificatesType = {
				"id": "",
				"name": "",
			}
		} else {
			appData.agentCertificatesType = $scope.agentCertificatesType;
		}
		//提交字段
		$scope.params = {
			"data": {
				"accessToken": appData.tokenSNO,
				"departCode": "",
				"itemCode": appData.itemCode,
				"info": {
					"ST_DOMICILE_QUXIAN": {
						"name": "",
						"value": ""
					},
					"ST_IS_KUAQU": {
						"name": "",
						"value": ""
					},
					"ST_LINK_ADDRESS": appData.contactAddress,
					"agentAddress": appData.agentAddress,
					"agentEmail": appData.agentEmail,
					"agentIdNumber": appData.stAgentIdCard,
					"agentIdType": {
						"name": appData.agentCertificatesType.name,
						"value": appData.agentCertificatesType.id
					},
					"agentMobile": appData.agentMobile,
					"agentName": appData.stAgentName,
					"agentPracticeOrg": {
						"items": [appData.agentWorkId || ""],
						"names": [appData.agentWork || ""],
						"value": appData.agentWorkId || ""
					},
					"agentZipcode": appData.agentPostCode,
					"applicant_address": appData.address,
					"applicant_gender": {
						"name": appData.stSex,
						"value": appData.stSex == "男" ? "a" : "b"
					},
					"applicant_idNumber": appData.certificatesCode,
					"applicant_idType": {
						"name": appData.certificatesType.name,
						"value": appData.certificatesType.id
					},
					"applicant_mobile": appData.stMobile,
					"applicant_name": appData.licenseName,
					"applicant_nation": {
						"name": appData.nation.shortname,
						"value": appData.nation.id
					},
					"applicant_zipcode": appData.postCode,
					"base_hasCertificatePhoto": {
						"name": "是",
						"value": "a"
					},
					"base_organization": {
						"name": appData.bldCode.name,
						"value": appData.bldCode.code
					},
					"licenseNo": appData.licenseNumber,
					"licenseType": {
						"name": "身份证",
						"value": "a"
					},
					"mobile": appData.stMobile,
					"sendAddress": appData.sendAddress,
					"sendDescription": appData.sendOther,
					"sendType": {
						"name": appData.sendWay.name,
						"value": appData.sendWay.id
					},
					"sendZipcode": appData.sendPostCode,
					"userId": "",
					"username": appData.licenseName
				}
			}
		}
		console.log($scope.params);
		//保存办件信息得到办件编码
		$scope.saveApplyInfo = function() {
			$.ajax({
				type: "get",
				url: $.getConfigMsg.preUrlSelf + "/selfapi/lawyerPractice/saveLawyerInfo.do",
				dataType: "json",
				jsonp: "jsonpCallback",
				data: {
					params: encodeURI(JSON.stringify($scope.params))
				},
				success: function(dataJson) {
					console.log(dataJson);
					if(dataJson.isSuccess == true) {
						appData.applyNo = dataJson.applyNo;
						$scope.isAlert = true;
						$scope.msg = "保存办件信息成功";
						//上传材料
						appFactory.pro_fetch(appData.licenseNumber, appData.licenseName, appData.funName, appData.VALIDSTARTDAY, appData.VALIDENDDAY, appData.applyNo, function(dataJson, dataJson1) {
							if(dataJson1.isSuccess == true) {
								try {
									$scope.imgUrl = "data:image/jpeg;base64," + dataJson[0].str;
									if(appData.isUpload.length <= 0) {
										appData.isUpload.push({
											index: 4,
											stuffName: "身份证明",
											img: $scope.imgUrl,
											status: 1,
											method: "高拍仪"
										});
									}
								} catch(e) {}
							}
						});
						$scope.alertConfirm = function() {
							$timeout(function() {
								$state.go("materialList");
							}, 100);
						}
					} else {
						$scope.isAlert = true;
						$scope.msg = dataJson.msg;
						$scope.alertConfirm = function() {
							$state.go("info");
						}
					}
				},
				error: function(err) {
					$scope.isAlert = true;
					$scope.msg = "保存办件信息失败,请重试";
					$scope.alertConfirm = function() {
						$state.go("info");
					}
				}
			});
		}
		$scope.saveApplyInfo();
	}
	$scope.prevStep = function() {
		if(appData.handle == "agent") {
			$state.go("agentInfo");
		} else {
			$state.go("info");
		}
	}
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
	//返回
	$scope.prve = function() {
		$state.go('materialList');
	}
});
app.controller("uFileUpload", function($scope, $http, $state, $rootScope, appData, $timeout, $routeParams, appFactory) {
	var name = appData.funName;
	$scope.itemName = name;
	$scope.prevText = "返回";
	$scope.nextText = "确认上传";
	appData.uploadStuffId = "";
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
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
				stuffId: appData.uploadStuffId || "",
			};
			$scope.jsonData1 = JSON.stringify($scope.jsonData1);
			$.device.httpUpload($.getConfigMsg.preUrl + "/aci/uploadItemStuffs.do", "FileData", $scope.UData,
				$scope.jsonData1,
				function(result) {
					result = JSON.parse(result);
					if(result.isSuccess == true) {
						$scope.isAlert = true;
						$scope.msg = "上传成功";
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
						$scope.alertConfirm = function() {
							$timeout(function() {
								$state.go('materialList');
							}, 100);
						}
					} else {
						$scope.isAlert = true;
						$scope.msg = "上传失败,请重试";
					}

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
	appData.uploadStuffId = "";
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$.device.cmCaptureShow(680, 530, 190, 300);
		$scope.isAlert = false;
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
		var scanImg = $.device.cmCaptureCaptureUrl();
		scanImg1 = $.device.cmCaptureCaptureBase64();
		if(scanImg == undefined) {
			$scope.isAlert = true;
			$scope.msg = "请聚焦并对准材料后再拍照";
			$scope.alertConfirm = function() {
				$scope.isLoading = true;
				$scope.isAlert = false;
			}
		} else {
			$scope.jsonData1 = {
				applyNo: appData.applyNo,
				stuffCode: appData.stuffCode,
				stuffId: appData.uploadStuffId || "",
			};
			$scope.jsonData1 = JSON.stringify($scope.jsonData1);
			$scope.isLoading = false;
			$.device.cmCaptureHide();
			$.device.httpUpload($.getConfigMsg.preUrl + '/aci/uploadItemStuffs.do', "FileData", scanImg,
				$scope.jsonData1,
				function(result) {
					$scope.isLoading = true;
					result = JSON.parse(result);
					if(result.isSuccess == true) {
						$scope.isAlert = true;
						$scope.msg = "上传成功";
						appData.uploadStuffId = result.stuffId;
						if(appData.isUpload[appData.currentIndex]) {
							appData.isUpload[appData.currentIndex] = "";
						}
						$scope.finish.push({
							index: appData.currentIndex,
							stuffName: appData.stStuffName,
							img: scanImg,
							status: 0,
							method: "高拍仪",
							attachId: result.stuffId
						});
						imgHTML += '<div class="img" id="' + appData.uploadStuffId + '"><img src="' + scanImg + '" width="150" height="200" /></div>';
						$('.imgBox').html(imgHTML);
						$scope.isFinish = true;
						$scope.alertConfirm = function() {
							$scope.finishUpload();
						}
					} else {
						$scope.isAlert = true;
						$scope.msg = "上传失败,请重试";
					}
				},
				function(webexception) {
					$scope.isLoading = true;
					$scope.isAlert = true;
					$scope.msg = "上传失败,请重试";
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
		$.device.cmCaptureHide(); // 关闭高拍仪
		$state.go("materialList");
	};

	$scope.last = function() {
		$.device.cmCaptureHide(); // 关闭高拍仪
		$state.go("materialList");
	}
});
app.controller("materialList", function($scope, $state, $http, appData, $timeout, appFactory, $rootScope) {
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.nextText = "提交";
	$scope.isLoading = true;
	$scope.funName = appData.funName;
	//必传材料列表
	appData.currentIndex = 0;
	$scope.mustUpload = [];
	$scope.current = 0;
	//设置上传文件 按钮变化
	$scope.btn = function() {
		//获取材料列表
		$scope.getItemStuffs = function() {
			$.ajax({
				type: "get",
				url: $.getConfigMsg.preUrl + "/selfapi/getItemStuffs.do",
				dataType: "json",
				jsonp: "jsonpCallback",
				data: {
					itemCode: appData.itemCode
				},
				success: function(dataJson) {
					$scope.isLoading = false;
					if(dataJson) {
						$scope.stuffList = dataJson.data.stuffs;
						for(var i = 0; i < $scope.stuffList.length; i++) {
							appData.listImg.push({
								'index': i,
								'stuffName': $scope.stuffList[i].stuffName,
								'upload': true,
								'upload2': false,
								'upload3': false,
								"stuffCode": $scope.stuffList[i].stuffCode,
								"isMust": $scope.stuffList[i].isMust
							});
						}
					} else {
						$scope.isAlert = true;
						$scope.msg = "未获取到此事项材料";
						$scope.alertConfirm = function() {
							$state.go("pickUpMethod");
						}
					}
				},
				error: function(err) {
					$scope.isAlert = true;
					$scope.msg = "未获取到此事项材料";
					$scope.alertConfirm = function() {
						$state.go("pickUpMethod");
					}
				}
			});
		}
		$scope.getItemStuffs();
		console.log(appData.isUpload);
		console.log(appData.listImg);
		if(appData.isUpload != "") {
			for(var i = 0; i < appData.isUpload.length; i++) {
				for(var j = 0; j < appData.listImg.length; j++) {
					console.log(appData.isUpload[i].status);
					if(appData.isUpload[i].status == 1) {
						if(appData.listImg[j].upload != false) {
							if(appData.isUpload[i].stuffName == appData.listImg[j].stuffName) {
								console.log(i);
								appData.listImg[j].upload = false;
								appData.listImg[j].upload2 = true;
							}
						}
					} else if(appData.isUpload[i].status == 0) {
						if(appData.listImg[j].upload != false) {
							if(appData.isUpload[i].stuffName == appData.listImg[j].stuffName) {
								appData.listImg[j].upload = false;
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
	appData.currentIndex++;
	$scope.toUploadMaterial = function(index, name, stuffCode, isMust) {
		appData.stStuffName = name;
		appData.affairscode = stuffCode;
		appData.needflag = isMust;
		appData.currentIndex = index;
		appData.stuffImg = appData.listImg[appData.currentIndex];
		$state.go("takePhoto");
	}
	//查看
	$scope.view = function() {
		appData.currentIndex = 0;
		appData.view = appData.isUpload;
		$state.go("materialView");
	}
	$scope.prevStep = function() {
		$state.go("pickUpMethod");
	}
	//提交办件
	$scope.submit = function() {
		//		var flag = false;
		//		do {
		//			if(appData.listImg[0].upload == true) {
		//				$scope.isAlert = true;
		//				$scope.msg = "请上传居民身份证";
		//				return;
		//			}
		//		} while (flag)
		$state.go("submit");
	};
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
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
});
app.controller("submit", function($scope, $state, appData, $sce, appFactory) {
	$scope.funName = appData.funName;
	$scope.applyNo = appData.applyNo;
	$scope.itemName = appData.funName;
	$scope.sumbitItem = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/submitItem.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				applyNo: appData.applyNo
			},
			success: function(dataJson) {
				console.log(dataJson);
				if(dataJson.isSuccess == true) {
					layer.msg("提交成功");
				}
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	$scope.sumbitItem();
	//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: $scope.funName,
			Number: $scope.applyNo,
		}
	}
	recordUsingHistory('司法服务', '办理', $scope.funName, appData.licenseName, appData.licenseNumber, appData.stMobile, $scope.applyNo, JSON.stringify($scope.jsonStr));
	trackEventForAffairs($scope.applyNo,'办理','上海市司法局',appData.licenseName,appData.licenseNumber,appData.stMobile)
	var date = new Date();
	var month = date.getMonth() + 1;
	$scope.date = date.getFullYear() + "年" + month + "月" + date.getDate() + "日" + date.getHours() + "时" + date.getMinutes() + "分";
	$scope.nextText = "返回首页";
	$scope.nextStep = function() {
		$.device.GoHome();
	}
});