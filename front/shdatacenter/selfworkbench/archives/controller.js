app.controller("archivesMain", function($scope, $state, appData) {
	$scope.funName = '档案查询';
	$scope.stuffName = perjsonStr;
	$scope.item = "请选择档案";
	$scope.toPrint = function(id, code, stuffName, guideline, a, b, c, d) {
		appData.id = id;
		appData.code = code;
		appData.stuffName = stuffName;
		appData.guideline = guideline;
		appData.API_ID = {
			"A": a,
			"B": b,
			"C": c,
			"D": d,
		};
		appData.type = "print";
		$state.go("loginType");
	}
	$scope.Handle = function(id, code, stuffName, guideline, a, b, c, d) {
		appData.id = id;
		appData.type = "handle";
		appData.code = code;
		appData.stuffName = stuffName;
		appData.guideline = guideline;
		appData.API_ID = {
			"A": a,
			"B": b,
			"C": c,
			"D": d,
		};
		$state.go("guideline");
	}
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
app.controller("guideline", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.code = appData.code;
	$scope.stuffName = appData.stuffName;
	$scope.guideline = appData.guideline;
	if($scope.stuffName == "涉外婚姻登记档案查询") {
		$scope.info = "<p>（1）主管部门：市档案局</p><p>（2）申报条件：" + $scope.guideline + "</p><p>（3）申请材料：申请对象需提供以下材料的原件：1、有效身份证明原件（居民身份证、护照）；</p>" +
			"<p>（4）办理流程：【本事项可“全市通办”】符合申请条件的人员，可通过自助服务设备等渠道提交申请。1.提出申请：申请人填写申请表，提交申请材料。2.审核出证：相关档案馆审核，对符合利用条件的制作档案复制件。3.自助打印：约30分钟后，申请人可在自助终端上打印带有档案馆电子印章的档案复制件。</p>" +
			"<p>（5）查档须知：1.该服务仅限本人申请查询；2.各档案馆在线审核时间为周一至周六8:30-16:30，周日及国定节假日8:30～11:30；3.目前仅提供由市、区综合档案馆接收和保存的相关民生档案；4.申请人获取的档案复制件及其相关档案信息，未经保管档案的档案馆同意，不得擅自向社会公布，或用以从事法律法规禁止的其他行为。</p>";
	} else {
		$scope.info = "<p>（1）主管部门：市档案局</p><p>（2）申报条件：" + $scope.guideline + "</p><p>（3）申请材料：申请对象需提供以下材料的原件：1、本人有效居民身份证原件；</p>" +
			"<p>（4）办理流程：【本事项可“全市通办”】符合申请条件的人员，可通过自助服务设备等渠道提交申请。1.提出申请：申请人填写申请表，提交申请材料。2.审核出证：相关档案馆审核，对符合利用条件的制作档案复制件。3.自助打印：约30分钟后，申请人可在自助终端上打印带有档案馆电子印章的档案复制件。</p>" +
			"<p>（5）查档须知：1.该服务仅限本人申请查询；2.各档案馆在线审核时间为周一至周六8:30-16:30，周日及国定节假日8:30～11:30；3.目前仅提供由市、区综合档案馆接收和保存的相关民生档案；4.申请人获取的档案复制件及其相关档案信息，未经保管档案的档案馆同意，不得擅自向社会公布，或用以从事法律法规禁止的其他行为。</p>";
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
	$scope.nextStep = function() {
		$state.go('loginType');
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: true,
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
app.controller('archivesLoginType', function($state, $scope, appData, $http) {
	$scope.operation = "请选择登录方式";
	$scope.stuffName = appData.stuffName;
	$scope.person = appData.person;
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.choice = function() {
		if($scope.stuffName == "婚姻登记档案查询") {
			$state.go("infoMarry");
		} else if($scope.stuffName == "独生子女档案查询") {
			$state.go("IdCardType");
		} else if($scope.stuffName == "知青子女入户档案查询") {
			$state.go("infoChildrenOfEducatedYouthToHousehold");
		} else if($scope.stuffName == "知青上山下乡档案查询") {
			$state.go("infoEducatedYouthToMountainAndCountryside");
		} else if($scope.stuffName == "知青返程档案查询") {
			$state.go("infoEducatedYouthToCity");
		} else if($scope.stuffName == "再生育子女审批档案查询") {
			$state.go("infoReproductionOfChildren");
		} else if($scope.stuffName == "工伤认定档案查询") {
			$state.go("infoWorkRelatedInjury");
		} else if($scope.stuffName == "学籍档案查询") {
			$state.go("infoStudentStatus");
		} else if($scope.stuffName == "复员退伍伍军人档案查询") {
			$state.go("infoVeteran");
		} else if($scope.stuffName == "兵役档案查询") {
			$state.go("infoMilitaryService");
		} else if($scope.stuffName == "人才引进审批档案查询") {
			$state.go("infoTalentIntroduction");
		} else if($scope.stuffName == "三峡移民档案查询") {
			$state.go("infoThreeGorgesMigrants");
		} else if($scope.stuffName == "涉外婚姻登记档案查询") {
			$state.go("infoForeignMarriage");
		} else if($scope.stuffName == "市级人才引进审批档案查询") {
			$state.go("infoTalentIntroductionToCity");
		} else if($scope.stuffName == "专业技术干部的农村家属迁往城镇审批档案") {
			$state.go("infoCountrysideMoveToCity");
		}
	}
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		//appData.archivesNumber = "430426199804106174"; //"210283199210200521";//"310228198808070818";//
		//appData.archivesName = "邹天奇"; //"测试-万达";//"陈雷";//
		//审核结果查看
		$scope.approvalResultsQuery = function(idCard, id) {
			$scope.isLoding = false;
			$.ajax({
				type: "get",
				url: "http://hengshui.5uban.com/xhac/aci/workPlatform/archives/approvalResultsQuery.do",
				/*url写异域的请求地址*/
				dataType: "jsonp",
				/*加上datatype*/
				jsonp: "jsonpCallback",
				/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
				data: {
					appName: appData.API_ID.B,
					identNo: "430426199804106174", //idCard, //"340881199303145313" || 
					code: id //"0" ||
				},
				success: function(data) {
					$scope.isLoding = true;
					if(data != undefined && data != "" && data != null) {
						if(data.SUCCESS == "true") {
							if(appData.type == "handle") {
								$scope.choice();
								$scope.$apply();
							} else if(appData.type == "print") {
								$scope.isAlert = true;
								$scope.msg = "未查询到您的申请记录，是否前去申请";
								$scope.alertConfirm = function() {
									$scope.choice();
									$scope.$apply();
								}
								$scope.alertCancel = function() {
									$state.go("main");
								}
							}
						} else if(data.SUCCESS == "false") {
							$scope.isAlert = true;
							$scope.concel = "false";
							if(data.DATA.spzt != undefined) {
								if(data.DATA.spzt == "4") {
									$state.go("preview");
									appData.slbh = data.DATA.slbh;
									appData.slid = data.DATA.slid;
									$scope.$apply();
								} else if(data.DATA.spzt == "5") {
									$state.go("preview");
									appData.slbh = data.DATA.slbh;
									appData.slid = data.DATA.slid;
									$scope.$apply();
								} else if(data.DATA.spzt == "3") {
									$scope.msg = "已接受过申请，请等待审批"
								} else {
									if(appData.type == "handle") {
										$scope.choice();
										$scope.$apply();
									} else if(appData.type == "print") {
										$scope.isAlert = true;
										$scope.msg = "未查询到您的申请记录，是否前去申请";
										$scope.alertConfirm = function() {
											$scope.choice();
											$scope.$apply();
										}
										$scope.alertCancel = function() {
											$state.go("main");
										}
									}
								}
							} else {
								if(appData.type == "handle") {
									$scope.choice();
									$scope.$apply();
								} else if(appData.type == "print") {
									$scope.isAlert = true;
									$scope.msg = "未查询到您的申请记录，是否前去申请";
									$scope.alertConfirm = function() {
										$scope.choice();
										$scope.$apply();
									}
									$scope.alertCancel = function() {
										$state.go("main");
									}
								}
							}
						}
					} else {
						$scope.isAlert = true;
						$scope.msg = "未查询到您的信息，请重试";
					}
				},
				error: function(err) {
					$scope.isAlert = true;
					$scope.msg = "审核结果查看失败,请重试";
				}
			});
		}
		//$scope.approvalResultsQuery(appData.archivesNumber, appData.id);
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller('archivesLogin', function($scope, $http, $state, appData, appFactory, $timeout) {
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
			$scope.operation = "随申办";
			break;
	}
	$scope.choice = function() {
		if($scope.stuffName == "婚姻登记档案查询") {
			$state.go("infoMarry");
		} else if($scope.stuffName == "独生子女档案查询") {
			$state.go("IdCardType");
		} else if($scope.stuffName == "知青子女入户档案查询") {
			$state.go("infoChildrenOfEducatedYouthToHousehold");
		} else if($scope.stuffName == "知青上山下乡档案查询") {
			$state.go("infoEducatedYouthToMountainAndCountryside");
		} else if($scope.stuffName == "知青返程档案查询") {
			$state.go("infoEducatedYouthToCity");
		} else if($scope.stuffName == "再生育子女审批档案查询") {
			$state.go("infoReproductionOfChildren");
		} else if($scope.stuffName == "工伤认定档案查询") {
			$state.go("infoWorkRelatedInjury");
		} else if($scope.stuffName == "学籍档案查询") {
			$state.go("infoStudentStatus");
		} else if($scope.stuffName == "复员退伍伍军人档案查询") {
			$state.go("infoVeteran");
		} else if($scope.stuffName == "兵役档案查询") {
			$state.go("infoMilitaryService");
		} else if($scope.stuffName == "人才引进审批档案查询") {
			$state.go("infoTalentIntroduction");
		} else if($scope.stuffName == "三峡移民档案查询") {
			$state.go("infoThreeGorgesMigrants");
		} else if($scope.stuffName == "涉外婚姻登记档案查询") {
			$state.go("infoForeignMarriage");
		} else if($scope.stuffName == "市级人才引进审批档案查询") {
			$state.go("infoTalentIntroductionToCity");
		} else if($scope.stuffName == "专业技术干部的农村家属迁往城镇审批档案") {
			$state.go("infoCountrysideMoveToCity");
		}
	}
	//审核结果查看
	$scope.approvalResultsQuery = function(idCard, id) {
		$scope.isLoding = false;
		$.ajax({
			type: "get",
			url: "http://hengshui.5uban.com/xhac/aci/workPlatform/archives/approvalResultsQuery.do",
			/*url写异域的请求地址*/
			dataType: "jsonp",
			/*加上datatype*/
			jsonp: "jsonpCallback",
			/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
			data: {
				appName: appData.API_ID.B,
				identNo: "210283199210200521", //idCard, //"340881199303145313" || 
				code: id //"0" ||
			},
			success: function(data) {
				$scope.isLoding = true;
				if(data != undefined && data != "" && data != null) {
					if(data.SUCCESS == "true") {
						if(appData.type == "handle") {
							$scope.choice();
							$scope.$apply();
						} else if(appData.type == "print") {
							$scope.isAlert = true;
							$scope.msg = "未查询到您的申请记录，是否前去申请";
							$scope.alertConfirm = function() {
								$scope.choice();
								$scope.$apply();
							}
							$scope.alertCancel = function() {
								$state.go("main");
							}
						}
					} else if(data.SUCCESS == "false") {
						$scope.isAlert = true;
						$scope.concel = "false";
						if(data.DATA.spzt != undefined) {
							if(data.DATA.spzt == "4") {
								$state.go("preview");
								appData.slbh = data.DATA.slbh;
								appData.slid = data.DATA.slid;
								$scope.$apply();
							} else if(data.DATA.spzt == "5") {
								$state.go("preview");
								appData.slbh = data.DATA.slbh;
								appData.slid = data.DATA.slid;
								$scope.$apply();
							} else if(data.DATA.spzt == "3") {
								$scope.msg = "已接受过申请，请等待审批"
							} else {
								if(appData.type == "handle") {
									$scope.choice();
									$scope.$apply();
								} else if(appData.type == "print") {
									$scope.isAlert = true;
									$scope.msg = "未查询到您的申请记录，是否前去申请";
									$scope.alertConfirm = function() {
										$scope.choice();
										$scope.$apply();
									}
									$scope.alertCancel = function() {
										$state.go("main");
									}
								}
							}
						} else {
							if(appData.type == "handle") {
								$scope.choice();
								$scope.$apply();
							} else if(appData.type == "print") {
								$scope.isAlert = true;
								$scope.msg = "未查询到您的申请记录，是否前去申请";
								$scope.alertConfirm = function() {
									$scope.choice();
									$scope.$apply();
								}
								$scope.alertCancel = function() {
									$state.go("main");
								}
							}
						}
					}
				} else {
					$scope.isAlert = true;
					$scope.msg = "未查询到您的信息，请重试";
				}
			},
			error: function(err) {
				$scope.isAlert = true;
				$scope.msg = "审核结果查看失败,请重试";
			}
		});
	}

	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.archivesNumber = info.Number;
			appData.archivesName = info.Name;
		} else {
			layer.msg("没有获取到")
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		appData.img = img;
		$scope.approvalResultsQuery(appData.archivesNumber, appData.id);
	}

	$scope.prevStep = function() {
		$state.go("loginType");
	}
	$scope.citizenLogin = function(info) {
		var idcardInfo = info.result.data;
		appData.archivesName = idcardInfo.realname;
		appData.archivesNumber = idcardInfo.idcard;
		$scope.approvalResultsQuery(appData.archivesNumber, appData.id);
	}
})
app.controller("IdCardType", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.operation = "请选择身份";
	$scope.prevStep = function() {
		$state.go("guideline");
	}
	$scope.choiceType = function(type) {
		if(type == "parent") {
			$state.go("infoOnlyChild");
		} else if(type == "child") {
			$state.go("infoOnlyChildForSon");
		}
	}
});
app.controller("info", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.stuffName = appData.stuffName;
	$scope.stName = appData.archivesName;
	$scope.stIdCard = appData.archivesNumber;
	$scope.stSex = ((parseInt(appData.archivesNumber.substring(16, 17)) % 2) == 0) ? "女" : "男";
	$scope.stBirth = (appData.archivesNumber).substring(6, 10) + "-" + (appData.archivesNumber).substring(10, 12) + "-" + (appData.archivesNumber).substring(12, 14);
	PublicchoiceById("useType");
	PublicchoiceById("archivesType");

	// 保存数据
	$scope.flag = true;
	$scope.prevStep = function() {
		$state.go("loginType");
	}
	$scope.nextStep = function() {
		if(!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			if($('#stAddress').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的联系地址！";
				return;
			}

			if($('#stMobile').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的手机号！";
				return;
			}

			if($('#stBirth').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的出生日期！";
				return;
			}
			if($("#archivesType .in").text() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择查档用途！";
				return;
			}
			if($("#useType .in").text() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择利用方式！";
				return;
			}
		} while (condFlag);
		appData.submitInfo = {
			stName: $scope.stName,
			stSex: $scope.stSex,
			stIdCard: $scope.stIdCard,
			stMobile: $scope.stMobile,
			stBirth: $scope.stBirth,
			stAddress: $scope.stAddress,
			stArchives: $("#archivesType .in").text(),
			stUses: $("#useType .in").text()
		}
		console.info(appData.submitInfo);
		$state.go("submit");
		$scope.flag = false;
	};
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: true,
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
app.controller("infoMarry", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoding = true;
	$scope.nextText = "提交";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.stuffName = appData.stuffName;
	$scope.stName = appData.archivesName;
	$scope.stIdCard = appData.archivesNumber;
	$scope.stSex = ((parseInt(appData.archivesNumber.substring(16, 17)) % 2) == 0) ? "女" : "男";
	$scope.stBirth = (appData.archivesNumber).substring(6, 10) + "-" + (appData.archivesNumber).substring(10, 12) + "-" + (appData.archivesNumber).substring(12, 14);
	$scope.archivesAddressList = archivesAddressList;
	PublicchoiceById("useType");
	PublicchoiceById("archivesType");
	$scope.idCardInfo = function() {
		//身份证信息
		var manIdcard = $.ajax({
			type: "get",
			url: "http://hengshui.5uban.com/xhac/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do",
			/*url写异域的请求地址*/
			dataType: "jsonp",
			timeout: 5000,
			/*加上datatype*/
			jsonp: "jsonpCallback",
			/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
			data: {
				identNo: appData.archivesNumber,
				catMainCode: "310105109000100"
			},
			success: function(dataJsonp) {
				$scope.stAddress = dataJsonp.SADDR;
			},
			error: function(err) {
				console.info("queryMarriageCertificateInfo error");
			},
			complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
				if(status == 'timeout') { //超时,status还有success,error等值的情况
					manIdcard.abort();　　　　　
					$scope.idCardInfo();　　　　
				}　　
			}
		});
	}
	$scope.idCardInfo();
	// 保存数据
	$scope.flag = true;
	$scope.prevStep = function() {
		$state.go("loginType");
	}
	$scope.nextStep = function() {
		if(!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {

			if($('#stAddress').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的联系地址！";
				return;
			}
			if($('#stWomen').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入配偶姓名！";
				return;
			}
			if($('#stMobile').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的手机号！";
				return;
			}

			if($('#stBirth').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的出生日期！";
				return;
			}
			if($("#archivesType .in").text() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择查档用途！";
				return;
			}
			if($("#useType .in").text() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择利用方式！";
				return;
			}
		} while (condFlag);
		appData.submitInfo = {
			stName: $scope.stName,
			stSex: $scope.stSex,
			stIdCard: $scope.stIdCard,
			stMobile: $scope.stMobile,
			stBirth: $scope.stBirth,
			stAddress: $scope.stAddress,
			stWomen: $scope.stWomen,
			stArchives: $("#archivesType .in").text(),
			stUses: $("#useType .in").text()
		}
		console.info(appData.submitInfo);
		//		$scope.param = {
		//			"guest": {
		//				"xm": appData.submitInfo.stName, //姓名 
		//				"zjlx": "身份证", //证件类型 
		//				"zjhm": appData.submitInfo.stIdCard, //证件号码 
		//				"xb": appData.submitInfo.stSex, // 性别 
		//				"csrq": appData.submitInfo.stBirth, //出生日期 
		//				"lxdh": appData.submitInfo.stMobile, //联系电话 
		//				"lxdz": appData.submitInfo.stAddress, //联系地址 
		//				"czr": "100002", //接待人ID 
		//				"czrxm": "社区开发", //接待人姓名 
		//				"zjnr": appData.img, //照片 
		//			},
		//			"query": {
		//				"pagenum": "", //分页参数 
		//				"cdyt": appData.submitInfo.stArchives, //查档用途（汉字） 
		//				"lyfx": appData.submitInfo.stUses, //利用方式 
		//				"bccdxs": appData.submitInfo.stOther|"", //补充查档线索 
		//				"slr": "100002", //受理人 
		//				"slrxm": "社区开发", //受理人姓名 
		//				"org_name": "自助终端",//受理单位名称 
		//				"org_code": "20190926002", //受理单位code  
		//				"code": appData.id, //档案类型 
		//				"ssdagmc": "", //区县档案馆名称 
		//				"condition_detail": { //查询条件
		//					"Q_LIKE_MAN": appData.submitInfo.stName,
		//					"Q_LIKE_WOMAN": appData.submitInfo.stWomen,
		//					"Q_EQ_DISTRICT": ""
		//				}
		//			}
		//		};
		$scope.param = {
			"guest": {
				"xm": "测试-万达",
				"zjlx": "身份证",
				"zjhm": "210283199210200521",
				"xb": "男",
				"csrq": "2015-04-21",
				"lxdh": "13512120546",
				"lxdz": "上海市闵行区",
				"czr": "100002",
				"czrxm": "社区开发",
				"zjnr": "base64Code",
			},
			"query": {
				"pagenum": "",
				"cdyt": "办理退休手续",
				"lyfx": "利用证明",
				"bccdxs": "结婚",
				"slr": "100002",
				"slrxm": "社区开发",
				"org_name": "自助终端", //受理单位名称 
				"org_code": "20190926002", //受理单位code  
				"code": "0030",
				"ssdagmc": "",
				"condition_detail": {
					"Q_LIKE_MAN": "蔡晔",
					"Q_LIKE_WOMAN": "梁艳",
					"Q_EQ_DISTRICT": ""
				}
			}
		}
		$scope.submit = function() {
			$scope.isLoding = false;
			$.ajax({
				type: "post",
				url: "http://hengshui.5uban.com/xhac/aci/workPlatform/archives/archivesQueryApply.do",
				dataType: "jsonp",
				jsonp: "jsonpCallback",
				data: {
					appName: appData.API_ID.A,
					param: JSON.stringify($scope.param)

				},
				success: function(dataJson) {
					$scope.isLoding = true;
					if(dataJson[0].hasOwnProperty("datas")) {
						$scope.isAlert = true;
						$scope.msg = "提交成功";
						$scope.alertConfirm = function() {
							$state.go("submit");
						}
					} else {
						$scope.isAlert = true;
						$scope.msg = "申请信息有误，请重试";
					}
				},
				error: function(err) {
					$scope.isLoding = true;
					$scope.isAlert = true;
					$scope.msg = "提交接口失败，请重试";
				}
			});
		}
		$scope.submit();
		$scope.flag = false;
	};
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: true,
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
app.controller("submit", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.stuffName = appData.stuffName;
	$scope.nextText = "返回首页";
	$scope.nextStep = function() {
		$.device.GoHome();
	}
})
app.controller("preview", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.stuffName = appData.stuffName;
	$scope.nextText = "打印";
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.previewImg = "";
	$scope.isLoding = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//浏览全文接口
	$scope.browseFullText = function() {
		function ClearBr(key) {
			key = key.replace(/[\r\n]/g, "");
			return key;
		}
		$.ajax({
			type: "get",
			url: "http://hengshui.5uban.com/xhac/aci/workPlatform/archives/browseFullText.do",
			/*url写异域的请求地址*/
			dataType: "jsonp",
			/*加上datatype*/
			jsonp: "jsonpCallback",
			/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
			data: {
				appName: appData.API_ID.C,
				slbh: appData.slbh,
				slid: appData.slid
			},
			success: function(data) {
				console.log(data);
				$scope.isLoding = true;
				try {
					$scope.previewImg = "data:image/png;base64," + ClearBr(data.linkaddress);
				} catch(e) {
					console.log(2);
					$scope.isAlert = true;
					$scope.msg = "未获取到档案图片信息，请重试";
					$scope.alertConfirm = function() {
						$state.go("main");
					}
				}
			},
			error: function(err) {
				$scope.isAlert = true;
				$scope.msg = "获取电子证明失败,请重试";
				$scope.alertConfirm = function() {
					$state.go("main");
				}
			}
		});
	}
	//打印出证接口
	$scope.printResult = function() {
		var dysj = new Date();
		console.log(dysj);
		$.ajax({
			type: "get",
			url: "http://hengshui.5uban.com/xhac/aci/workPlatform/archives/printResult.do",
			/*url写异域的请求地址*/
			dataType: "jsonp",
			/*加上datatype*/
			jsonp: "jsonpCallback",
			/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
			data: {
				appName: appData.API_ID.D,
				slbh: appData.slbh,
				slid: appData.slid,
				dysj: dysj,
				dysl: "1"
			},
			success: function(data) {
				console.log(data);
			},
			error: function(err) {
				console.log('printResult err');
			}
		});
	}
	$scope.browseFullText();
	$scope.prevStep = function() {
		$state.go("main");
	}
	$scope.print = function() {
		var lodop = $.device.printGetLodop();
		lodop.ADD_PRINT_IMAGE(0, 0, 800, 1300, "<img border='0' src='" + $scope.previewImg + "'>");
		lodop.SET_PRINT_STYLEA(0, "Stretch", 2); //按原图比例(不变形)缩放模式
		//lodop.ADD_PRINT_TEXT(0, 0, 400, 500, "此证照由市电子证照库提供");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		lodop.SET_PRINT_STYLEA(0, "FontColor", "#ccc");
		lodop.SET_PRINT_STYLEA(0, "ItemType", 1);
		lodop.SET_PRINT_STYLEA(0, "Angle", 50);
		lodop.SET_PRINT_STYLEA(0, "Repeat", true);
		lodop.PRINT();
		$scope.printResult();
		$timeout(function() {
			$scope.isAlert = true;
			$scope.msg = "正在打印中。。。";
			$scope.alertConfirm = function() {
				$scope.isAlert = false;
				$state.go("main");
			}
		}, 3000);
	}
});