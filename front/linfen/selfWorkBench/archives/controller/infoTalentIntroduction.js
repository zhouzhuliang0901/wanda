//人才引进审批档案查询
app.controller("infoTalentIntroduction", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
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
		//		todayBtn: true,
		//		minView: "month", //只选择到天自动关闭
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
			stMateName: $scope.stMateName,
			stReceivingUnit: $scope.stReceivingUnit,
			stIntroductionDate: $scope.stIntroductionDate,
			stGraduateSchool: $scope.stGraduateSchool,
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
					"Q_LIKE_NAME": (appData.submitInfo.stSex == "男") ? appData.submitInfo.stName : appData.submitInfo.stMateName,
					"Q_LIKE_POXM": (appData.submitInfo.stSex == "女") ? appData.submitInfo.stName : appData.submitInfo.stMateName,
					"Q_LIKE_JSDW": appData.submitInfo.stReceivingUnit,
					"Q_LIKE_YJSJ": appData.submitInfo.stIntroductionDate,
					"Q_LIKE_SFZH": appData.submitInfo.stIdCard,
					"Q_LIKE_CSNY": appData.submitInfo.stBirth,
					"Q_LIKE_BYXX": appData.submitInfo.stGraduateSchool,
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
		//				"ssdagmc": "崇明区档案馆",
		//				"condition_detail": {
		//					"Q_LIKE_NAME": "王学明",
		//					//					"Q_LIKE_POXM": "XXXXX",
		//					"Q_LIKE_JSDW": "185",
		//					//					"Q_LIKE_YJSJ": "XXXXX",
		//					//					"Q_LIKE_SFZH": "XXXXX",
		//					//					"Q_LIKE_CSNY": "xxxx",
		//					//					"Q_LIKE_BYXX": "xxxx",
		//					"Q_EQ_DISTRICT": "putuo"
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
							recordUsingHistory('档案查询', '办理', appData.stuffName, appData.archivesName, appData.archivesNumber, appData.submitInfo.stMobile, '', JSON.stringify($scope.jsonStr));
							//行为分析(办理)
							trackEventForAffairs("",appData.stuffName,"上海市档案局",appData.archivesName, appData.archivesNumber,appData.submitInfo.stMobile);
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