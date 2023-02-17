app.controller("main", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.stuffName = perjsonStr;
	$scope.choiceType = function(type, name, ywlx) {
		appData.funName = name;
		appData.type = type;
		appData.ywlx = ywlx;
		if(type == "exitAndEntry" || type == "householdRegister") {
			$state.go("guideline");
		} else if(type == "residencePermit") {
			$state.go("loginType");
			$state.go("residenceInfo");
		} else if(type == "HongKongAndMacao" || type == "TaiwanPass") {
			//			$state.go("loginType");
			appData.licenseNumber = "430426199804106174";
			appData.licenseName = "邹天奇";
			$state.go("choose");
		} else if(type == "residenceVisa") {
			$state.go("loginResidence");
		} else if(type == "lllegalInfo") {
			window.location.href = "../infoSearch/index.html#infoLoginType?type=jjxx";
		}
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			bounce: true,
			click: true,
			taps: true,
			hScroll: false,
		});
	};
	$scope.isScroll();
});
//办理须知
app.controller('guideline', function($state, $scope, appData, $location) {
	$scope.funName = appData.funName;
	if(appData.type == "exitAndEntry") {
		$scope.guideline = "<p>1、 您的查询结果需要大约两小时更新，请耐心等待;</p>" +
			"<p>2、 若您持有我驻外使领馆签发的出入境证件、因公普通护照，  或者您是港澳台居民，暂无法通过网上查询，请您至.上海市公安局出入境管理局、自贸区分局出入境管理支队和各分局出入境管理办公窗口查询;</p>" +
			"<p>3、 您的出入境记录查询结果展示后，请仔细核对信息，  存疑的可至出入境管理部门查询窗口询问;</p>" +
			"<p>4、目前出入境记录查询结果可直接打印，打印后的查询结果附带公章，与出入境管理部门出具的出入境记录具有同等效力。</p>";
	} else if(appData.type == "householdRegister") {
		$scope.guideline = "<p>1、该服务须由本人申请，相关法律责任由本人承担。</p>" +
			"<p>2、能够用居民户口簿、个人户口卡、居民身份证或者护照等法定身份证件证明的事项，</p>" +
			"<p>公安派出所不再开具户籍证明。但有下列情形之一的，公安派出所可以根据申请开具户籍证明：</p>" +
			"<p>（一）居民户口簿、个人户口卡遗失尚未补办的;</p>" +
			"<p>（二）因家庭矛盾等原因无法获得居民户口簿，经民警调查确需开具的;</p>" +
			"<p>（三）学生集体户口、博士后工作站集体户口以及中央各部委、各省市驻沪办事处工作集体户口人员需要证明户籍信息的;</p>" +
			"<p>（四）需要查阅历史户籍档案，证明曾经同户人员间亲属关系的。</p>" +
			"<p>3、婚姻状况、文化程度、宗教信仰、兵役状况、职业、服务处所、血型等非公安机关主管事项，公安派出所不予开具相关户籍证明。</p>" +
			"<p>4、网上《户籍证明》结果展示后，请仔细核对信息，如您认为登记的信息错误，可以携带相关证明材料，向户口所在地派出所申请更正，最终以派出所反馈的信息为准。</p>" +
			"<p>5、《户籍证明》仅限于国内使用（港、澳、台地区除外）。</p>" +
			"<p>6、《户籍证明》只证明户籍登记情况。</p>" +
			"<p>7、如您无法通过网上申请出具《户籍证明》的，可以携带相关证明材料，前往户口所在地派出所申请开具。</p>" +
			"<p>8、网上申请的《户籍证明》与派出所出具的《户籍证明》具有同等效力。</p>";
	}
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
app.controller('loginType', function($state, $scope, appData, $location) {
	$scope.funName = appData.funName;
	$scope.operation = "请选择登录方式";
	if(appData.type == "" || appData.type == undefined) {
		appData.type = $location.search().type;
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
	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
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
		if(type == "exitAndEntry") {
			$state.go("exitAndEntryInfo");
		} else if(type == "householdRegister") {
			$state.go("householdRegisterInfo");
		} else if(type == "residencePermit") {
			$state.go("residenceInfo");
		} else if(type == "HongKongAndMacao" || type == "TaiwanPass") {
			$state.go("choose");
		}
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}

	$scope.citizenLogin = function(info) {
		var idcardInfo = info.result.data;
		appData.licenseName = idcardInfo.realname;
		appData.licenseNumber = idcardInfo.idcard;
		if(type == "exitAndEntry") {
			$state.go("exitAndEntryInfo");
		} else if(type == "householdRegister") {
			$state.go("householdRegisterInfo");
		} else if(type == "residencePermit") {
			$state.go("residenceInfo");
		} else if(type == "HongKongAndMacao" || type == "TaiwanPass") {
			$state.go("choose");
		}
	}
});
//居住证签注 信息
app.controller('loginResidence', function($state, $scope, appData, $http, $interval, $timeout) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.operation = "请选择登录方式";
	//	$scope.choice = function() {
	//		$state.go("infoResidence");
	//	}
	$scope.readResidence = function() {
		//签注机吞卡
		window.external.DataCard_Open('XPS Card Printer');
		$timeout(function() {
			$interval.cancel($scope.timer);
			//签注机读卡
			//			appData.cardInfo = window.external.DataCard_Read();
			//			$.log.debug(appData.cardInfo);
			$state.go("infoResidence");
		}, 2000);
	}
	$scope.maxCountDown = 3;
	$scope.timer = null;
	$scope.timeCount = function() {
		$interval.cancel($scope.timer);
		$scope.timer = $interval(function() {
			$scope.maxCountDown--;
			if($scope.maxCountDown < 1) {
				$scope.readResidence();
				$interval.cancel($scope.timer);
				$scope.resetCountDown();
			}
		}, 1000);
	}
	$scope.timeCount();
	$scope.resetCountDown = function() {
		$interval.cancel($scope.timer);
		$timeout(function() {
			$scope.maxCountDown = 3;
		});
		$timeout(function() {
			$scope.timeCount();
		}, 0);
	};
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller("infoResidence", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.nextText = "继续";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.stName = appData.Name;
	$scope.stIdCard = appData.Number;

	// 保存数据
	$scope.prevStep = function() {
		$state.go("loginResidence");
	}
	$scope.nextStep = function() {
		$state.go("updateResidence");
	};
});
app.controller("updateResidence", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.confirmshow = "false";
	$scope.nextText = "更新卡片";
	$scope.updateResidenceLicenseInfo = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/residenceLicense/updateResidenceLicenseInfo.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				cardId: "310000D1560000057851378200DB2120",
				regcode: "310000000298704470",
				deviceId: "xxxxxx",
				userId: "3500339101"
			},
			success: function(dataJson) {
				console.log(dataJson);
				if(dataJson.SUCCESS == true) {
					$scope.liveAddr = dataJson.respData.liveAddr;
					$scope.validdate = dataJson.respData.validdate;
					//					//签注机退卡
					//					window.external.DataCard_Close();
				} else {
					$scope.isAlert = true;
					$scope.msg = dataJson.resultMessage;
					$scope.alertConfirm = function() {
						$scope.isAlert = false;
						$state.go("login");
					}
				}
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	$scope.updateResidenceLicenseInfo();
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		$state.go("infoResidence");
	}
	$scope.nextStep = function() {
		$scope.isAlert = true;
		$scope.msg = "<p>正在更新卡面，请等待…</p><p>（大约需要20秒）</p>";
//		$scope.alertConfirm = function() {
//			$scope.isAlert = false;
//			$.device.GoHome();
//		}
		$timeout(function(){
			window.external.DataCard_Print(135, 85, 300, 400, "<style>td{padding:0px;text-align: justify; -ms-text-justify: inter-ideograph; -ms-text-align-last: justify;}</style><table cellspacing='0' style='font-family:黑体;font-size: 10px;font-weight: bold;'><tr><td>居住地</td><td>:</td><td rowspan='2'>松江区沪松公路20弄1号102室<td></tr><tr><td>地址</td><td>:</td></tr><tr><td>有效期限</td><td>:</td><td>2015年06月09日--2020年09月16日<td></tr></table>");
			$.device.GoHome();
		},100);
	}
});
//港澳台通行证-选择项
app.controller('choose', function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.show = false;
	$scope.nextText = "返回首页";
	$scope.peopel = peopel;
	$scope.search = function(list, id) {
		var result = list.filter(function(p) {
			return p.pid == id;
		});
		return result;
	}
	$scope.change = function(name, index, id) {
		$scope.current = null;
		$scope.current2 = null;
		$scope.show = true;
		$scope.show2 = false;
		$scope.current = index;
		$scope.itemType = $scope.search(itemType, id);
	};
	$scope.change2 = function(name, index, guideline) {
		$scope.current2 = null;
		$scope.show2 = true;
		$scope.current2 = index;
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go('main');
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
	$scope.nextStep = function() {
		appData.people = $('.singselect .in li').text();
		appData.itemType = $('.singselect2 .in li').text()
		console.log($('.singselect .in li').text());
		console.log($('.singselect2 .in li').text());
		if(appData.type == "HongKongAndMacao") {
			$state.go("applyInfoHKM");
		} else if(appData.type == "TaiwanPass") {
			$state.go("applyInfoTaiWan");
		}
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
});
//台湾通行证申请信息
app.controller("applyInfoTaiWan", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName;
	$scope.nextText = "提交";
	$scope.isLoding = false;
	$scope.concel = "false";
	$scope.isAlert = false;
	PublicChoiceById("self");
	$scope.endorsement = endorsement;
	$scope.shanghai = endorsement;
	$scope.differentPlaces = differentPlaces;
	$scope.stPassAddress = stPassAddress;
	$scope.itemType = appData.itemType;
	$scope.people = appData.people;
	PublicChoiceById("stPassAddress");
	$scope.sh_index = null;
	$scope.changeStChSurname = function() {
		console.log(1);
		$scope.stPySurname = pinyin.getFullChars($scope.stChSurname);
	}
	$scope.changeStChName = function() {
		console.log(2);
		$scope.stPyName = pinyin.getFullChars($scope.stChName);
	}
	switch($scope.itemType) {
		case "首次申请":
			$scope.ST_APPLY_TYPE = {
				"name": "首次申请",
				"value": "11"
			}
			break;
		case "换发申请":
			$scope.ST_APPLY_TYPE = {
				"name": "换发",
				"value": "31"
			}
			break;
		case "过期申请":
			$scope.ST_APPLY_TYPE = {
				"name": "失效重新申请",
				"value": "13"
			}
			break;
	}
	$scope.stIdCard = appData.licenseNumber;
	$scope.stName = appData.licenseName;
	$scope.search = function(list, id) {
		var result = list.filter(function(p) {
			return p.pid == id;
		});
		return result;
	}
	$scope.changeQZ = function(name, index, id) {
		$scope.qz_index = index;
		$scope.TaiWan = $scope.search(TaiWan, id);
	}
	$scope.changeSH = function(name, index, id) {
		$scope.sh_index = index;
	}
	$scope.changeDP = function(name, index, id) {
		$scope.dp_index = index;
	}
	$scope.changePA = function(name, index, id) {
		$scope.pa_index = index;
	}
	$scope.changeTW = function(name, index, id) {
		$scope.tw_index = index;
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
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
	$scope.prevStep = function() {
		$state.go("choose");
	}
	$scope.flag = true;
	$scope.nextStep = function() {
		if(!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			//			if($('#stMobile').val() < 1) {
			//				$scope.isAlert = true;
			//				$scope.msg = "请输入正确的手机号！";
			//				return;
			//			}
			//			if($('#stChSurname').val() < 1) {
			//				$scope.isAlert = true;
			//				$scope.msg = "请输入中文姓！";
			//				return;
			//			}
			//			if($('#stChName').val() < 1) {
			//				$scope.isAlert = true;
			//				$scope.msg = "请输入中文名！";
			//				return;
			//			}
			//
			//			if($('#stUrgent').val() < 1) {
			//				$scope.isAlert = true;
			//				$scope.msg = "请输入紧急联系人！";
			//				return;
			//			}
			//			if($('#stUrgentMobile').val() < 1) {
			//				$scope.isAlert = true;
			//				$scope.msg = "请输入紧急联系人手机号！";
			//				return;
			//			}
			//			if($("#endorsement .in").text() < 1) {
			//				$scope.isAlert = true;
			//				$scope.msg = "请选择是否同时办理签注！";
			//				return;
			//			}
			//			if($("#endorsement .in").text() == "是" && $("#destination .in").text() < 1) {
			//				$scope.isAlert = true;
			//				$scope.msg = "请选择前往地！";
			//				return;
			//			}
			//			console.log($("#destination .in").text());
			//			if($("#destination a").eq(0).attr('class') == 'ng-binding ng-scope in' && $("#destination a").eq(0).text() == "香港" && $("#Hongkong .in").text() < 1) {
			//				$scope.isAlert = true;
			//				$scope.msg = "请选择往来香港事由！";
			//				return;
			//			}
		} while (condFlag);
		if($("#destination a").eq(0).attr('class') == 'ng-binding ng-scope in') {
			$scope.HK = $("#destination a").eq(0).text() || "";
			$scope.HKvlaue = $("#destination a").eq(0).attr('value') || "";
		}
		if($("#destination a").eq(1).attr('class') == 'ng-binding ng-scope in') {
			$scope.MC = $("#destination a").eq(1).text() || "";
			$scope.MCvlaue = $("#destination a").eq(1).attr('value') || "";
		}
		console.log($("#endorsement .in").text());
		console.log($("#endorsement .in").attr('value'));

		switch($scope.people) {
			case "我是本市居民":
				$scope.appName = "d23278bf-28a5-4562-a367-6a954c1cfa67";
				//本省市
				$scope.sumbitInfo = {
					"data": {
						"accessToken": "", //appData.token,
						"departCode": "SHGASH-CRJ001",
						"itemCode": "0100193000-01-00",
						"info": {
							"ST_APPLY_TYPE": {
								"name": $scope.ST_APPLY_TYPE.name || "",
								"value": $scope.ST_APPLY_TYPE.value || ""
							},
							"ST_Certificates_shanghai": {
								"name": "否",
								"value": "b"
							},
							"ST_JINJILIANXIREN_NAME": "阿萨德大所大所",
							"ST_JINJILIANXIREN_PHONE": "13265455542",
							"ST_OLD_PASS_ADDRESS": {
								"items": ["LBYA"],
								"names": ["驻利比亚使馆"],
								"value": "LBYA"
							},
							"ST_OLD_PASS_DATE": {
								"unix": 1564329600000,
								"value": "2019-07-29"
							},
							"ST_OLD_PASS_NO": "234234234324",
							"ST_PINYING_MING": {
								"name": "ESADE",
								"value": "B"
							},
							"ST_PINYING_XING": {
								"name": "SADAISHENGDI",
								"value": "H"
							},
							"ST_QIANZHU_MEANTIME": {
								"name": $("#endorsement .in").text(),
								"value": $("#endorsement .in").attr('value')
							},
							"ST_QIANZHU_YOUXIAOQI_AOMEN": {
								"name": $("#MCTour .in").text(),
								"value": $("#MCTour .in").attr('value')
							},
							"ST_QIANZHU_YOUXIAOQI_XIANGGANG": {
								"name": $("#MCTour .in").text(),
								"value": $("#MCTour .in").attr('value')
							},
							"ST_RESON_AOMEN": {
								"name": $("#Macao .in").text(),
								"value": $("#Macao .in").attr('value')
							},
							"ST_RESON_XIANGGANG": {
								"name": $("#Hongkong .in").text(),
								"value": $("#Hongkong .in").attr('value')
							},
							"ST_SIMPLE_MING": "阿萨德",
							"ST_SIMPLE_XING": "撒大声地",
							"ST_SMARTGATE": {
								"name": $("#Hongkong .in").text(),
								"value": $("#Hongkong .in").attr('value')
							},
							"ST_mudidi": {
								"items": [$scope.HKvlaue || "", $scope.MCvlaue || ""],
								"names": [$scope.HK || "", $scope.MC || ""],
								"value": $scope.HKvlaue || "" + "," + $scope.MCvlaue || ""
							},
							"licenseNo": "220182199411114874",
							"mobile": "15601898415",
							"username": "周航11"
						}
					}
				}
				break;
			case "我是外省市居民":
				$scope.appName = "8b74e784-6129-4cad-81b5-47cc9ef29df3";
				//外省市
				$scope.sumbitInfo = {
					"data": {
						"accessToken": "", //appData.token,
						"departCode": "SHGASH-CRJ001",
						"itemCode": "0100193000-02-00",
						"info": {
							"applyNo": "001019319003905",
							"data": {
								"ST_APPLY_TYPE": {
									"name": $scope.ST_APPLY_TYPE.name || "",
									"value": $scope.ST_APPLY_TYPE.value || ""
								},
								"ST_Certificates_shanghai": {
									"name": $("#shanghai .in").text(),
									"value": $("#shanghai .in").attr('value')
								},
								"ST_JINJILIANXIREN_NAME": "水电费发的",
								"ST_JINJILIANXIREN_PHONE": "13576565554",
								"ST_OLD_PASS_ADDRESS": {
									"items": [$("#stPassAddress .in").attr('value') || ""],
									"names": [$("#stPassAddress .in").text() || ""],
									"value": $("#stPassAddress .in").attr('value') || ""
								},
								"ST_OLD_PASS_DATE": {
									"unix": 1564329600000,
									"value": "2019-07-29"
								},
								"ST_OLD_PASS_NO": "23443324324324",
								"ST_PINYING_MING": {
									"name": "XUFANFAGUI",
									"value": "C"
								},
								"ST_PINYING_XING": {
									"name": "SHUIDIANFEIFANDESA",
									"value": "A"
								},
								"ST_QIANZHU_MEANTIME": {
									"name": $("#endorsement .in").text(),
									"value": $("#endorsement .in").attr('value')
								},
								"ST_QIANZHU_YOUXIAOQI_AOMEN": {
									"name": $("#MCTour .in").text(),
									"value": $("#MCTour .in").attr('value') || ""
								},
								"ST_QIANZHU_YOUXIAOQI_XIANGGANG": {
									"name": $("#MCTour .in").text(),
									"value": $("#MCTour .in").attr('value') || ""
								},
								"ST_RESON_AOMEN": {
									"name": $("#Macao .in").text(),
									"value": $("#Macao .in").attr('value') || ""
								},
								"ST_RESON_XIANGGANG": {
									"name": $("#Hongkong .in").text(),
									"value": $("#Hongkong .in").attr('value') || ""
								},
								"ST_SIMPLE_MING": "规范法规",
								"ST_SIMPLE_XING": "水电费范德萨",
								"ST_SMARTGATE": {
									"name": $("#Hongkong .in").text(),
									"value": $("#Hongkong .in").attr('value') || ""
								},
								"ST_different_places": {
									"name": $("#differentPlaces .in").text(),
									"value": $("#differentPlaces .in").attr('value')
								},
								"ST_mudidi": {
									"items": [$scope.HKvlaue || "", $scope.MCvlaue || ""],
									"names": [$scope.HK || "", $scope.MC || ""],
									"value": $scope.HKvlaue || "" + "," + $scope.MCvlaue || ""
								},
								"licenseNo": "220182199411114874",
								"mobile": "15601898415",
								"username": "周11"
							}
						}
					}
				}
				break;
		}
		console.log($scope.sumbitInfo);
		$scope.submit = function() {
			$.ajax({
				type: "get",
				url: $.getConfigMsg.preUrl + "/aci/passPermit/applyPassPermit.do",
				async: true,
				dataType: "jsonp",
				jsonp: "jsonpCallback",
				data: {
					appName: $scope.appName,
					param: JSON.stringify($scope.sumbitInfo)
				},
				success: function(dataJson) {
					console.log(dataJson);
				},
				error: function(err) {
					console.log("applyPassPermit err");
				}
			});
		}
		$scope.submit();
		$scope.flag = false;
	}
});
//港澳通行证申请信息
app.controller("applyInfoHKM", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName;
	$scope.nextText = "提交";
	$scope.isLoding = false;
	$scope.concel = "false";
	$scope.isAlert = false;
	PublicChoiceById("self");
	$scope.endorsement = endorsement;
	$scope.shanghai = endorsement;
	$scope.differentPlaces = differentPlaces;
	$scope.stPassAddress = stPassAddress;
	$scope.HKTour = HKTour;
	$scope.MCTour = MCTour;
	$scope.itemType = appData.itemType;
	$scope.people = appData.people;
	PublicChoiceById("stPassAddress");
	$scope.sh_index = null;
	$scope.changeStChSurname = function() {
		console.log(1);
		$scope.stPySurname = pinyin.getFullChars($scope.stChSurname);
	}
	$scope.changeStChName = function() {
		console.log(2);
		$scope.stPyName = pinyin.getFullChars($scope.stChName);
	}
	switch($scope.itemType) {
		case "首次申请":
			$scope.ST_APPLY_TYPE = {
				"name": "首次申请",
				"value": "11"
			}
			break;
		case "换发申请":
			$scope.ST_APPLY_TYPE = {
				"name": "换发",
				"value": "31"
			}
			break;
		case "过期申请":
			$scope.ST_APPLY_TYPE = {
				"name": "失效重新申请",
				"value": "13"
			}
			break;
	}
	$scope.stIdCard = appData.licenseNumber;
	$scope.stName = appData.licenseName;
	$scope.search = function(list, id) {
		var result = list.filter(function(p) {
			return p.pid == id;
		});
		return result;
	}
	$scope.changeQZ = function(name, index, id) {
		$scope.qz_index = index;
		$scope.destination = $scope.search(destination, id);
		if($scope.qz_index != 0) {
			$scope.isHongkong = false;
			$scope.isMacao = false;
		}
	}
	$scope.changeSH = function(name, index, id) {
		$scope.sh_index = index;
	}
	$scope.changeDP = function(name, index, id) {
		$scope.dp_index = index;
	}
	$scope.changePA = function(name, index, id) {
		$scope.pa_index = index;
	}
	$scope.changeDes = function(name, index, id) {
		$scope.hk_index = null;
		$scope.mc_index = null;
		if($("#destination a").eq(index).attr('class') == 'ng-binding ng-scope in') {
			$("#destination a").eq(index).removeClass("in");
		} else {
			$("#destination a").eq(index).addClass("in");
		}
		if($("#destination a").eq(0).text() == "香港" && $("#destination a").eq(0).attr('class') == 'ng-binding ng-scope in') {
			$scope.isHongkong = true;
			$scope.Hongkong = $scope.search(Hongkong, 1);
		} else {
			$scope.isHongkong = false;
		}
		if($("#destination a").eq(1).text() == "澳门" && $("#destination a").eq(1).attr('class') == 'ng-binding ng-scope in') {
			$scope.isMacao = true;
			$scope.Macao = $scope.search(Macao, 2);
		} else {
			$scope.isMacao = false;
		}
	}
	$scope.changeHK = function(name, index, id) {
		$scope.hkt_index = null;
		$scope.hk_index = index;
		if(($scope.hk_index == "1" && $("#Hongkong a").eq(index).text() == "团队旅游") || ($scope.hk_index == "2" && $("#Hongkong a").eq(index).text() == "个人旅游")) {
			$scope.isHKTour = true;
		} else {
			$scope.isHKTour = false;
		}
		$scope.HKTour = $scope.search(HKTour, id);
	}
	$scope.changeMC = function(name, index, id) {
		$scope.mct_index = null;
		$scope.mc_index = index;
		if(($scope.mc_index == "1" && $("#Macao a").eq(index).text() == "团队旅游") || ($scope.mc_index == "2" && $("#Macao a").eq(index).text() == "个人旅游")) {
			$scope.isMCTour = true;
		} else {
			$scope.isMCTour = false;
		}
		$scope.MCTour = $scope.search(MCTour, id);
	}
	$scope.changeHKT = function(name, index, id) {
		$scope.hkt_index = index;
	}
	$scope.changeMCT = function(name, index, id) {
		$scope.mct_index = index;
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
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
	$scope.prevStep = function() {
		$state.go("choose");
	}
	$scope.flag = true;
	$scope.nextStep = function() {
		if(!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			//			if($('#stMobile').val() < 1) {
			//				$scope.isAlert = true;
			//				$scope.msg = "请输入正确的手机号！";
			//				return;
			//			}
			//			if($('#stChSurname').val() < 1) {
			//				$scope.isAlert = true;
			//				$scope.msg = "请输入中文姓！";
			//				return;
			//			}
			//			if($('#stChName').val() < 1) {
			//				$scope.isAlert = true;
			//				$scope.msg = "请输入中文名！";
			//				return;
			//			}
			//
			//			if($('#stUrgent').val() < 1) {
			//				$scope.isAlert = true;
			//				$scope.msg = "请输入紧急联系人！";
			//				return;
			//			}
			//			if($('#stUrgentMobile').val() < 1) {
			//				$scope.isAlert = true;
			//				$scope.msg = "请输入紧急联系人手机号！";
			//				return;
			//			}
			//			if($("#endorsement .in").text() < 1) {
			//				$scope.isAlert = true;
			//				$scope.msg = "请选择是否同时办理签注！";
			//				return;
			//			}
			//			if($("#endorsement .in").text() == "是" && $("#destination .in").text() < 1) {
			//				$scope.isAlert = true;
			//				$scope.msg = "请选择前往地！";
			//				return;
			//			}
			//			console.log($("#destination .in").text());
			//			if($("#destination a").eq(0).attr('class') == 'ng-binding ng-scope in' && $("#destination a").eq(0).text() == "香港" && $("#Hongkong .in").text() < 1) {
			//				$scope.isAlert = true;
			//				$scope.msg = "请选择往来香港事由！";
			//				return;
			//			}
			//			if($("#destination a").eq(1).attr('class') == 'ng-binding ng-scope in' && $("#destination a").eq(1).text() == "澳门" && $("#Macao .in").text() < 1) {
			//				$scope.isAlert = true;
			//				$scope.msg = "请选择往来澳门事由！";
			//				return;
			//			}
			//			if(($("#Hongkong .in").text() == "团队旅游" || $("#Hongkong .in").text() == "个人旅游") && $("#HKTour .in").text() < 1) {
			//				$scope.isAlert = true;
			//				$scope.msg = "请选择签注有效期（香港）！";
			//				return;
			//			}
			//			if(($("#Macao .in").text() == "团队旅游" || $("#Macao .in").text() == "个人旅游") && $("#MCTour .in").text() < 1) {
			//				$scope.isAlert = true;
			//				$scope.msg = "请选择签注有效期（澳门）！";
			//				return;
			//			}
		} while (condFlag);
		if($("#destination a").eq(0).attr('class') == 'ng-binding ng-scope in') {
			$scope.HK = $("#destination a").eq(0).text() || "";
			$scope.HKvlaue = $("#destination a").eq(0).attr('value') || "";
		}
		if($("#destination a").eq(1).attr('class') == 'ng-binding ng-scope in') {
			$scope.MC = $("#destination a").eq(1).text() || "";
			$scope.MCvlaue = $("#destination a").eq(1).attr('value') || "";
		}
		console.log($("#endorsement .in").text());
		console.log($("#endorsement .in").attr('value'));

		switch($scope.people) {
			case "我是本市居民":
				$scope.appName = "d23278bf-28a5-4562-a367-6a954c1cfa67";
				//本省市
				$scope.sumbitInfo = {
					"data": {
						"accessToken": "",
						"departCode": "SHGASH-CRJ001",
						"itemCode": "0100193000-01-00",
						"info": {
							"ST_APPLY_TYPE": {
								"name": $scope.ST_APPLY_TYPE.name || "",
								"value": $scope.ST_APPLY_TYPE.value || ""
							},
							"ST_Certificates_shanghai": {
								"name": "否",
								"value": "b"
							},
							"ST_JINJILIANXIREN_NAME": "阿萨德大所大所",
							"ST_JINJILIANXIREN_PHONE": "13265455542",
							"ST_OLD_PASS_ADDRESS": {
								"items": ["LBYA"],
								"names": ["驻利比亚使馆"],
								"value": "LBYA"
							},
							"ST_OLD_PASS_DATE": {
								"unix": 1564329600000,
								"value": "2019-07-29"
							},
							"ST_OLD_PASS_NO": "234234234324",
							"ST_PINYING_MING": {
								"name": "ESADE",
								"value": "B"
							},
							"ST_PINYING_XING": {
								"name": "SADAISHENGDI",
								"value": "H"
							},
							"ST_QIANZHU_MEANTIME": {
								"name": $("#endorsement .in").text(),
								"value": $("#endorsement .in").attr('value')
							},
							"ST_QIANZHU_YOUXIAOQI_AOMEN": {
								"name": $("#MCTour .in").text(),
								"value": $("#MCTour .in").attr('value')
							},
							"ST_QIANZHU_YOUXIAOQI_XIANGGANG": {
								"name": $("#MCTour .in").text(),
								"value": $("#MCTour .in").attr('value')
							},
							"ST_RESON_AOMEN": {
								"name": $("#Macao .in").text(),
								"value": $("#Macao .in").attr('value')
							},
							"ST_RESON_XIANGGANG": {
								"name": $("#Hongkong .in").text(),
								"value": $("#Hongkong .in").attr('value')
							},
							"ST_SIMPLE_MING": "阿萨德",
							"ST_SIMPLE_XING": "撒大声地",
							"ST_SMARTGATE": {
								"name": $("#Hongkong .in").text(),
								"value": $("#Hongkong .in").attr('value')
							},
							"ST_mudidi": {
								"items": [$scope.HKvlaue || "", $scope.MCvlaue || ""],
								"names": [$scope.HK || "", $scope.MC || ""],
								"value": $scope.HKvlaue || "" + "," + $scope.MCvlaue || ""
							},
							"licenseNo": "220182199411114874",
							"mobile": "15601898415",
							"username": "周航11"
						}
					}
				}
				break;
			case "我是外省市居民":
				$scope.appName = "8b74e784-6129-4cad-81b5-47cc9ef29df3";
				//外省市
				$scope.sumbitInfo = {
					"data": {
						"accessToken": "",
						"departCode": "SHGASH-CRJ001",
						"itemCode": "0100193000-02-00",
						"info": {
							"applyNo": "001019319003905",
							"data": {
								"ST_APPLY_TYPE": {
									"name": $scope.ST_APPLY_TYPE.name || "",
									"value": $scope.ST_APPLY_TYPE.value || ""
								},
								"ST_Certificates_shanghai": {
									"name": $("#shanghai .in").text(),
									"value": $("#shanghai .in").attr('value')
								},
								"ST_JINJILIANXIREN_NAME": "水电费发的",
								"ST_JINJILIANXIREN_PHONE": "13576565554",
								"ST_OLD_PASS_ADDRESS": {
									"items": [$("#stPassAddress .in").attr('value') || ""],
									"names": [$("#stPassAddress .in").text() || ""],
									"value": $("#stPassAddress .in").attr('value') || ""
								},
								"ST_OLD_PASS_DATE": {
									"unix": 1564329600000,
									"value": "2019-07-29"
								},
								"ST_OLD_PASS_NO": "23443324324324",
								"ST_PINYING_MING": {
									"name": "XUFANFAGUI",
									"value": "C"
								},
								"ST_PINYING_XING": {
									"name": "SHUIDIANFEIFANDESA",
									"value": "A"
								},
								"ST_QIANZHU_MEANTIME": {
									"name": $("#endorsement .in").text(),
									"value": $("#endorsement .in").attr('value')
								},
								"ST_QIANZHU_YOUXIAOQI_AOMEN": {
									"name": $("#MCTour .in").text(),
									"value": $("#MCTour .in").attr('value') || ""
								},
								"ST_QIANZHU_YOUXIAOQI_XIANGGANG": {
									"name": $("#MCTour .in").text(),
									"value": $("#MCTour .in").attr('value') || ""
								},
								"ST_RESON_AOMEN": {
									"name": $("#Macao .in").text(),
									"value": $("#Macao .in").attr('value') || ""
								},
								"ST_RESON_XIANGGANG": {
									"name": $("#Hongkong .in").text(),
									"value": $("#Hongkong .in").attr('value') || ""
								},
								"ST_SIMPLE_MING": "规范法规",
								"ST_SIMPLE_XING": "水电费范德萨",
								"ST_SMARTGATE": {
									"name": $("#Hongkong .in").text(),
									"value": $("#Hongkong .in").attr('value') || ""
								},
								"ST_different_places": {
									"name": $("#differentPlaces .in").text(),
									"value": $("#differentPlaces .in").attr('value')
								},
								"ST_mudidi": {
									"items": [$scope.HKvlaue || "", $scope.MCvlaue || ""],
									"names": [$scope.HK || "", $scope.MC || ""],
									"value": $scope.HKvlaue || "" + "," + $scope.MCvlaue || ""
								},
								"licenseNo": "220182199411114874",
								"mobile": "15601898415",
								"username": "周11"
							}
						}
					}
				}
				break;
		}
		console.log($scope.sumbitInfo);
		$scope.submit = function() {
			$.ajax({
				type: "get",
				url: $.getConfigMsg.preUrl + "/aci/passPermit/applyPassPermit.do",
				async: true,
				dataType: "jsonp",
				jsonp: "jsonpCallback",
				data: {
					appName: $scope.appName,
					param: JSON.stringify($scope.sumbitInfo)
				},
				success: function(dataJson) {
					console.log(dataJson);
				},
				error: function(err) {
					console.log("applyPassPermit err");
				}
			});
		}
		$scope.submit();
		$scope.flag = false;
	}
});