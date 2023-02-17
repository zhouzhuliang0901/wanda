function removeAnimate(ele) {
	$(ele).css({
		"transform": "translateY(0px)",
		"top": 0
	}).removeClass('transformto')
}

function addAnimate(ele) {
	$(ele).addClass('transformto').on("animationend", function() {
		$(ele).removeClass('transformto')
	})
}
app.controller("choose", function($scope, $state, appData, $location, $timeout) {
	$timeout(function() {
		removeAnimate($('.scrollBox2'))
	}, 1000);
	$timeout(function() {
		addAnimate($('.scrollBox2'))
	}, 50);
	appData.stuffName = $location.search().itemName;
	var result = perjsonStr.filter(function(p) {
		return p.stuffName == appData.stuffName;
	});

	$scope.toPrint = function() {
		appData.id = result[0].id;
		appData.code = result[0].code;
		appData.guideline = result[0].guideline;
		appData.API_ID = {
			"A": result[0].API_ID_A,
			"B": result[0].API_ID_B,
			"C": result[0].API_ID_C,
			"D": result[0].API_ID_D,
		};
		appData.type = "print";
		$state.go("loginType");
	}
	$scope.Handle = function() {
		appData.type = "handle";
		appData.id = result[0].id;
		appData.code = result[0].code;
		appData.guideline = result[0].guideline;
		appData.API_ID = {
			"A": result[0].API_ID_A,
			"B": result[0].API_ID_B,
			"C": result[0].API_ID_C,
			"D": result[0].API_ID_D,
		};
		$state.go("guideline");
	}
	$scope.prevStep = function() {
		window.location.href = "../declare/index.html#main";
	}
})
app.controller("archivesMain", function($scope, $state, appData) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
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
	addAnimate($('.scrollBox2'))
});
app.controller("guideline", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	removeAnimate($('.main2'))
	$scope.code = perjsonStr[0].code;
	$scope.stuffName = perjsonStr[0].stuffName;
	$scope.guideline = perjsonStr[0].guideline;
	appData.API_ID = {
		"A": perjsonStr[0].API_ID_A,
		"B": perjsonStr[0].API_ID_B,
		"C": perjsonStr[0].API_ID_C,
		"D": perjsonStr[0].API_ID_D,
	};
	$scope.info = "<p>1、主管部门：市档案局</p>" +
		"<p>2、申报条件：" + $scope.guideline + "</p>" +
		"<p>3、申请材料：申请对象需提供以下材料的原件：1、本人有效居民身份证原件；</p>" +
		"<p>4、办理流程：【本事项可“全市通办”】符合申请条件的人员，可通过自助服务设备等渠道提交申请。</p>" +
		"<p>（1）提出申请：申请人填写申请表，提交申请材料。</p>" +
		"<p>（2）审核出证：相关档案馆审核，对符合利用条件的制作档案复制件。</p>" +
		"<p>（3）自助打印：约<span style='color:#e1ba1c;font-weight:bold;'>30分钟后</span>，申请人可在自助终端上打印带有档案馆电子印章的档案复制件。</p>" +
		"<p>5、查档须知：</p>" +
		"<p>（1）该服务仅限本人申请查询，不得查询其他公民的档案；</p>" +
		"<p>（2）该服务只提供由市、区综合档案馆接收和保存的档案。对于档案馆尚未接收进馆的档案，则暂不能提供查询服务；</p>" +
		"<p>（3）民生档案查询“一网通办”服务时间为<span style='color:#e1ba1c;font-weight:bold;'>周一至周五8:30-16:30，周六、周日及国定节假日8:30～11:30</span>。非工作时间提交的查档申请将顺延办理；</p>" +
		"<p>（4）查询人获取的档案复制件及相关档案信息，未经保管档案的档案馆同意，不得擅自向社会公布，或用以从事法律法规禁止的行为。</p>" +
		"<p>（5）目前，查档申请均由各档案馆人工审核。若等候时间过长，请致电相关档案馆（联系方式如下）：</p>" +
		"<table cellspacing='0' style='border: #477cbe 1px solid;'><tr><th>序号</th><th>档案馆</th><th>咨询电话</th><th>地址</th></tr><tr><td>1</td><td>上海市档案馆</td><td>63336633-1515</td><td>中山东二路9号（200002）</td></tr>" +
		"<tr><td rowspan='3'>2</td><td rowspan='3'>浦东新区档案馆</td><td>28949804</td><td>浦东新区迎春路520号（200135）</td></tr>" +
		"<tr><td>58022248</td><td>惠南镇县东街15号（201300）</td></tr>" +
		"<tr><td>58906859</td><td>新川路540号（201299）</td></tr>" +
		"<tr><td rowspan='2'>3</td><td rowspan='2'>徐汇区档案馆</td><td>34768956</td><td rowspan='2'>徐汇区浦北路268号东门（200235）</td></tr>" +
		"<tr><td>34768955</td></tr>" +
		"<tr><td>4</td><td>长宁区档案馆</td><td>22050072</td><td>长宁区长宁路599号16号楼（200050）</td></tr>" +
		"<tr><td rowspan='2'>5</td><td rowspan='2'>普陀区档案馆</td><td>52500010-6001</td><td rowspan='2'>普陀区同普路602号1号楼（200062）</td></tr>" +
		"<tr><td>52500010-6072</td></tr>" +
		"<tr><td>6</td><td>虹口区档案馆</td><td>25657253</td><td>虹口区三河路358号（200086）</td></tr>" +
		"<tr><td>7</td><td>杨浦区档案馆</td><td>55217610</td><td>杨浦区靖宇东路269号（200093）</td></tr>" +
		"<tr><td>8</td><td>黄浦区档案馆</td><td>63504445</td><td>黄浦区广西北路158号18楼（200001）</td></tr>" +
		"<tr><td>9</td><td>静安区档案馆</td><td>33094324</td><td>静安区秣陵路46号政务大楼711室（200070）</td></tr>" +
		"<tr><td>10</td><td>闵行区档案馆</td><td>64138937</td><td>闵行区名都路85号（201199）</td></tr>" +
		"<tr><td>11</td><td>宝山区档案馆</td><td>56567419</td><td>宝山区淞宝路104号（200940）</td></tr>" +
		"<tr><td>12</td><td>嘉定区档案馆</td><td>69989056</td><td>嘉定区墅沟路2003号（201822）</td></tr>" +
		"<tr><td rowspan='2'>13</td><td rowspan='2'>金山区档案馆</td><td>33694596</td><td rowspan='2'>金山区浩源路299号（200540）</td></tr>" +
		"<tr><td>33694595</td></tr>" +
		"<tr><td rowspan='2'>14</td><td rowspan='2'>松江区档案馆</td><td>37736578</td><td>松江区中山中路38号4号楼（201600）</td></tr>" +
		"<tr><td>57821000-8101</td><td>周六：松江区乐都西路867-871号行政服务中心2号楼2楼综合窗口</td></tr>" +
		"<tr><td rowspan='2'>15</td><td rowspan='2'>青浦区档案馆</td><td>69717987</td><td rowspan='2'>青浦区秀泉路450号（201799）</td></tr>" +
		"<tr><td>69717734</td></tr>" +
		"<tr><td>16</td><td>奉贤区档案馆</td><td>57420639</td><td>奉贤区南桥镇远东路2670号（201499）</td></tr>" +
		"<tr><td rowspan='2'>17</td><td rowspan='2'>崇明区档案馆</td><td>59610179</td><td>崇明区城桥镇新城定澜路1588号（202155）</td></tr>" +
		"<tr><td>69696988-8242</td><td>周六日：翠竹路1501号行政服务中心4号窗</td></tr>" +
		"</table>";

	$scope.prevStep = function() {
		$state.go('main');
	}
	$scope.nextStep = function() {
		$state.go('loginType');
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
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
	addAnimate($('.main2'))
});
app.controller('archivesLoginType', function($state, $scope, appData, $http) {
	console.log(appData.API_ID.B);
	removeAnimate($('.scrollBox2'))
	$scope.operation = "请选择登录方式";
	$scope.stuffName = appData.stuffName;
	$scope.person = appData.person;
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	addAnimate($('.scrollBox2'))
	$scope.choice = function() {
		$state.go("info");
	}
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		appData.archivesNumber = "310105197402192853"; //"310115198606194014"////"430426199804106174"; //"210283199210200521"; //
		appData.archivesName = "夏广平"; //"邹天奇"; //"测试-万达"; //
		//审核结果查看
		$scope.approvalResultsQuery = function(idCard, id) {
			$scope.isLoding = false;
			$.ajax({
				type: "get",
				url: $.getConfigMsg.preUrl + "/aci/workPlatform/archives/approvalResultsQuery.do",
				/*url写异域的请求地址*/
				dataType: "jsonp",
				/*加上datatype*/
				jsonp: "jsonpCallback",
				/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
				data: {
					appName: appData.API_ID.B,
					identNo: idCard, //"340881199303145313" || 
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
							if(data.DATA.spzt != undefined) {
								if(data.DATA.spzt == "4" || data.DATA.spzt == "5") {
									$state.go("preview");
									appData.slbh = data.DATA.slbh;
									appData.slid = data.DATA.slid;
									$scope.$apply();
								} else if(data.DATA.spzt == "3") {
									$scope.isAlert = true;
									$scope.concel = "false";
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
								$scope.isAlert = true;
								$scope.concel = "false";
								$scope.msg = "您的查询暂未审批，请稍候再试";
								$scope.alertCancel = function() {
									$state.go("main");
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
		//		appData.archivesNumber = "210283199210200521"; //"310228198808070818";//"430426199804106174"; //
		//		appData.archivesName = "测试-万达"; //"陈雷";//"邹天奇"; //
		//		$scope.approvalResultsQuery(appData.archivesNumber, appData.id);
		//		$scope.choice();
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller('archivesLogin', function($scope, $http, $state, appData, appFactory, $timeout) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.stuffName = appData.stuffName;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.isLoding = true;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go("main");
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
		$.device.audioPPlay("Realtek High Definition Audio", $.device.currentPath() + "\\resources\\audio\\inputInfo.wav");
		$state.go("info");
	}
	//审核结果查看
	$scope.approvalResultsQuery = function(idCard, id) {
		$scope.isLoding = false;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/archives/approvalResultsQuery.do",
			/*url写异域的请求地址*/
			dataType: "jsonp",
			/*加上datatype*/
			jsonp: "jsonpCallback",
			/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
			data: {
				appName: appData.API_ID.B,
				identNo: idCard, //"340881199303145313" || 
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
						if(data.DATA.spzt != undefined) {
							if(data.DATA.spzt == "4" || data.DATA.spzt == "5") {
								$state.go("preview");
								appData.slbh = data.DATA.slbh;
								appData.slid = data.DATA.slid;
								$scope.$apply();
							} else if(data.DATA.spzt == "3") {
								$scope.isAlert = true;
								$scope.concel = "false";
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
							$scope.isAlert = true;
							$scope.concel = "false";
							$scope.msg = "您的查询暂未审批，请稍候再试";
							$scope.alertCancel = function() {
								$state.go("main");
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
		$scope.loginType = '';
		$scope.approvalResultsQuery(appData.archivesNumber, appData.id);
	}

	$scope.prevStep = function() {
		$state.go("loginType");
	}
	$scope.citizenLogin = function(info) {
		if(appData.qrCodeType == "suishenma") {
			appData.archivesName = info.zwdtsw_name;
			appData.archivesNumber = info.zwdtsw_cert_id;
			$scope.approvalResultsQuery(appData.archivesNumber, appData.id);
		} else {
			var idcardInfo = info.result.data;
			appData.archivesName = idcardInfo.realname;
			appData.archivesNumber = idcardInfo.idcard;
			$scope.approvalResultsQuery(appData.archivesNumber, appData.id);
		}
	}
})
app.controller("info", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoding = true;
	$scope.nextText = "提交";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$(".form_datetime").datetimepicker({
		format: "yyyy", //显示日期格式
		autoclose: true,
		startView: 4,
		minView: 4,
		language: 'zh-CN',
		pickerPosition: 'bottom-left',
	});
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
			url: $.getConfigMsg.preUrlSelf + "/selfapi/DZCert/getCertOriginalData.do",
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
		})
	}
	//	$scope.idCardInfo();
	// 保存数据
	$scope.flag = true;
	$scope.prevStep = function() {
		$state.go("loginType");
	}
	$scope.nextStep = function() {
		if(!$scope.flag) {
			//			return ''
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
		$scope.stAge = judgeOld($scope.stIdCard);
		appData.submitInfo = {
			stName: $scope.stName,
			stSex: $scope.stSex,
			stIdCard: $scope.stIdCard,
			stMobile: $scope.stMobile,
			stBirth: $scope.stBirth,
			stAddress: $scope.stAddress,
			stAge: $scope.stAge,
			stYear: $('#stYear').val(),
			stWork: $scope.stWork,
			stArchivesAddress: $scope.archivesAddress.address,
			stArchivesAddressCode: $scope.archivesAddress.code || "",
			stOther: $scope.stOther,
			stArchives: $("#archivesType .in").text(),
			stUses: $("#useType .in").text()
		}
		console.info(appData.submitInfo);
		$scope.param = {
			"guest": {
				"xm": appData.submitInfo.stName, //姓名 
				"zjlx": "身份证", //证件类型 
				"zjhm": appData.submitInfo.stIdCard, //证件号码 
				"xb": appData.submitInfo.stSex, // 性别 
				"csrq": appData.submitInfo.stBirth, //出生日期 
				"lxdh": appData.submitInfo.stMobile, //联系电话 
				"czr": appData.submitInfo.stIdCard, //接待人ID 
				"czrxm": appData.submitInfo.stName, //接待人姓名 
				"zjnr": "", //照片 
			},
			"query": {
				"pagenum": "", //分页参数 
				"cdyt": appData.submitInfo.stArchives, //查档用途（汉字） 
				"lyfx": appData.submitInfo.stUses, //利用方式 
				"org_name": "自助终端", //受理单位名称 
				"org_code": "20190926002", //受理单位code  
				"code": appData.id, //档案类型 
				"ssdagmc": appData.submitInfo.stArchivesAddress, //区县档案馆名称 
				"condition_detail": { //查询条件
					"Q_LIKE_NAME": appData.submitInfo.stName,
					"Q_LIKE_DXND": appData.submitInfo.stYear,
					"Q_LIKE_RWQGZDW": appData.submitInfo.stWork,
					"Q_LIKE_SFZH": appData.submitInfo.stAge,
					"Q_EQ_DISTRICT": appData.submitInfo.stArchivesAddressCode
				}
			}
		};
		//		$scope.param = {
		//			"guest": {
		//				"xm": "测试-万达",
		//				"zjlx": "身份证",
		//				"zjhm": "210283199210200521",
		//				"xb": "男",
		//				"csrq": "2015-04-21",
		//				"lxdh": "13512120546",
		//				"lxdz": "上海市闵行区",
		//				"czr": "100002",
		//				"czrxm": "社区开发",
		//				"zjnr": "base64Code",
		//			},
		//			"query": {
		//				"pagenum": "",
		//				"cdyt": "办理退休手续",
		//				"lyfx": "利用全文",
		//				"bccdxs": "结婚",
		//				"slr": "100002",
		//				"slrxm": "社区开发",
		//				"org_name": "自助终端", //受理单位名称 
		//				"org_code": "20190926002", //受理单位code  
		//				"code": appData.id,
		//				"ssdagmc": "上海市",
		//				"condition_detail": {
		//					"Q_LIKE_NAME": "沈文燕",
		//					"Q_LIKE_DXND": "1979",
		//					"Q_LIKE_RWQGZDW": "上海市眼病皮肤病防治所",
		//					"Q_LIKE_SFZH": "58",
		//					"Q_EQ_DISTRICT": "shdaj"
		//				}
		//			}
		//		}
		$scope.submit = function() {
			$scope.isLoding = false;
			$.ajax({
				type: "post",
				url: $.getConfigMsg.preUrl + "/aci/workPlatform/archives/archivesQueryApply.do",
				dataType: "jsonp",
				jsonp: "jsonpCallback",
				data: {
					appName: appData.API_ID.A,
					param: JSON.stringify($scope.param)

				},
				success: function(dataJson) {
					$scope.isLoding = true;
					if(dataJson != null && dataJson != undefined && dataJson != "") {
						if(dataJson[0].total != "" && dataJson[0].total != "0" && dataJson[0].total != null) {
							//模块使用记录
							$scope.jsonStr = {
								SUCCESS: "true",
								data: {
									name: appData.stuffName,
								}
							}
							recordUsingHistory('档案服务', '办理', appData.stuffName, appData.archivesName, appData.archivesNumber, appData.submitInfo.stMobile, '', JSON.stringify($scope.jsonStr));
							//行为分析(办理)
							trackEventForAffairs("", appData.stuffName, "上海市档案局", appData.archivesName, appData.archivesNumber, appData.submitInfo.stMobile);
							$scope.isAlert = true;
							$scope.msg = "提交成功";
							$scope.alertConfirm = function() {
								$state.go("submit");
							}
						} else {
							$scope.isAlert = true;
							$scope.msg = "未查询到您的档案,请确认所填信息是否有误";
							return;
						}
					} else {
						$scope.isAlert = true;
						$scope.msg = "未查询到您的档案,请确认所填信息是否有误";
						return;
					}
					//					if(dataJson[0].hasOwnProperty("datas")) {
					//						$scope.isAlert = true;
					//						$scope.msg = "提交成功";
					//						$scope.alertConfirm = function() {
					//							$state.go("submit");
					//						}
					//					} else {
					//						$scope.isAlert = true;
					//						$scope.msg = "未查询到您的档案,请确认所填信息是否有误";
					//					}
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
	$scope.currentImgIndex = null;
	$scope.previewImg = "";
	$scope.isLoding = false;
	$scope.printPdf = "";
	$scope.currentPage = 1; //当前页
	$scope.totalPages = 1; //总页数
	$scope.previewImgList = []; //预览图片
	$scope.emptyPreviewImgList = []; //存在空值的数组
	$scope.totalList = [];
	$scope.List = [];
	$scope.isShowView = false; // 是否显示预览框
	$scope.zoomCount = 1; // 图片放大计数
	$scope.rotateCount = 0; // 图片旋转计数
	$scope.base = "data:image/png;base64,"
	$scope.isAllScreen = false;
	$scope.isPrint = false;
	$scope.view = true;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.nextPage = function() {
		if($scope.currentPage < $scope.totalPages) {
			++$scope.currentPage;
			$scope.currentList();
		}
	};
	$scope.prevPage = function() {
		if($scope.currentPage > 1) {
			--$scope.currentPage;
			$scope.currentList();
		}
	};
	$scope.currentList = function(current) {
		if(current === undefined) {
			current = $scope.currentPage;
		}
		$("#jq22 img").remove();
		$scope.currentImgIndex = null;
		$scope.startPos = (current - 1) * 2; //0 -3  3 - 6 
		$scope.endPos = $scope.startPos + 2;
		$scope.emptyPreviewImgList = $scope.totalList.slice($scope.startPos, $scope.endPos);
		$scope.totalPages = Math.ceil($scope.totalList.length / 2);
		$scope.emptyPreviewImgList.length = 2;
		console.log($scope.emptyPreviewImgList[1]);
		for(var i in $scope.emptyPreviewImgList) {
			if($scope.emptyPreviewImgList[i] != undefined) {
				$scope.previewImgList.push($scope.emptyPreviewImgList[i]);
			}
			$("#jq22").append('<img data-original="' + $scope.base + $scope.emptyPreviewImgList[i].png + '" src="' + $scope.base + $scope.emptyPreviewImgList[i].png + '" alt="">');
		}
		//图片显示
		var viewer = new Viewer(document.getElementById('jq22'), {
			url: 'data-original',
			//						toolbar:false,
			//						button:false
		});
	}
	//浏览全文接口
	$scope.browseFullText = function() {
		function ClearBr(key) {
			key = key.replace(/[\r\n]/g, "");
			return key;
		}
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/archives/browseFullText.do",
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
					$scope.List = data.pngData;
					$scope.totalList = $scope.List.slice(0, $scope.List.length);
					$scope.currentList();
					console.log(data.pngData.length);
					if(data.pngData.length == 0) {
						$scope.isAlert = true;
						$scope.msg = "未获取到档案图片信息，请重试";
						$scope.alertConfirm = function() {
							$state.go("main");
						}
					} else {
						$scope.printPdf = data.str;
					}
				} catch(e) {
					console.log(e);
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
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/archives/printResult.do",
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
	$scope.allScreen = function() {
		$scope.isShowView = true;
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
	$scope.print = function() {
		$.device.urlPdfPrint($scope.printPdf, "C:\\pdfLicense.pdf", function() {
			saveDeviceStatus("A4Printer", 0, "正常", 0, 0, 0, $scope.totalList.length);
			//模块使用记录
			$scope.jsonStr = {
				SUCCESS: "true",
				data: {
					name: appData.stuffName,
				}
			}
			recordUsingHistory('档案服务', '查询+打印', appData.stuffName, appData.archivesName, appData.archivesNumber, '', '', JSON.stringify($scope.jsonStr));
			//行为分析(查询)
			trackEventForQuery(appData.stuffName, "", "打印", "上海市档案局", appData.archivesName, appData.archivesNumber, "");
			$scope.printResult();
		});
//		$.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		urlHost + 
//			$scope.printPdf,
//			"C:\\pdfLicense.pdf",
//			//将选中图片下载
//			function(bytesCopied, totalBytes) {
//				console.log(bytesCopied + "," + totalBytes);
//			},
//			function(result) {
//				$.device.pdfPrint("C:/pdfLicense.pdf");
//				saveDeviceStatus("A4Printer", 0, "正常", 0, 0, 0, $scope.totalList.length);
//				//模块使用记录
//				$scope.jsonStr = {
//					SUCCESS: "true",
//					data: {
//						name: appData.stuffName,
//					}
//				}
//				recordUsingHistory('档案服务', '查询+打印', appData.stuffName, appData.archivesName, appData.archivesNumber, '', '', JSON.stringify($scope.jsonStr));
//				//行为分析(查询)
//				trackEventForQuery(appData.stuffName, "", "打印", "上海市档案局", appData.archivesName, appData.archivesNumber, "");
//				$scope.printResult();
//			},
//			function(webexception) {
//				alert("下载文档失败");
//			}
//		);
		$timeout(function() {
			$scope.isAlert = true;
			$scope.msg = "正在打印中。。。";
			$scope.alertConfirm = function() {
				$scope.isAlert = false;
				$state.go("main");
			}
		}, 3000);

	}

	//图片预览
	$scope.viewClose = function() {
		$scope.isShowView = false;
		$scope.zoomCount = 1;
		$scope.rotateCount = 0;
	};

	$scope.zoomIn = function() {
		$scope.zoomCount += 0.5;
		if($scope.zoomCount > 5) {
			$scope.zoomCount = 5;
		}
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

	$scope.zoomOut = function() {
		$scope.zoomCount -= 0.5;
		if($scope.zoomCount < 0.5) {
			$scope.zoomCount = 0.5;
		}
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

	$scope.rotateLeft = function() {
		$scope.rotateCount -= 90;
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

	$scope.rotateRight = function() {
		$scope.rotateCount += 90;
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};
});