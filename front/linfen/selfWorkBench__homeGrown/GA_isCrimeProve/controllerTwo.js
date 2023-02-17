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