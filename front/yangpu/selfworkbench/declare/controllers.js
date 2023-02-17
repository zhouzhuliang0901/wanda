app.controller("startController", function($scope, $location, $http, data, $rootScope, $timeout, $interval) {
	data.ocode = "";
	data.themeCode = "";
	data.organCode = "";
	$scope.goToApp = function(address) {

		if(address.indexOf("http") != -1) {
			window.location.href = address;
		} else {
			window.location.href = address;
		}
	};
	$rootScope.goHome = function() {
		window.external.URL_CLOSE();
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
	};
});
app.controller("listController", function($scope, $route, $location, $http, $rootScope, data) {
	data.isUpload = [];
	data.listImg = [];
	data.fileName = [];
	$scope.areaCode = $location.search().areaCode || 'SH00HP';
	$scope.current = 0;
	$scope.searchType = ["个人", "法人", "部门"];
	$scope.current = data.matindex || 0;
	$scope.itemName = [];
	$scope.currentMatters = [];
	$scope.matterVal = '';
	$scope.organCode = data.ocode || null;
	$scope.itemName = "";
	data.areaL = "SH";
	$scope.type = "1";
	$scope.isLoding = false;
	//
	//阅签选择
	$scope.getMatterCon = function(index, type) {
		$scope.current = index;
		switch(type) {
			case "部门":
				$scope.itemName = "";
				$scope.itemType = "";
				$scope.getDepartment();
				$scope.dept = true;
				break;
			case "法人":
				$scope.isLoding = false;
				$scope.type = "2";
				$scope.itemName = "";
				$scope.itemType = "";
				$scope.corporationItem();
				$scope.dept = false;
				break;
			case "个人":
				$scope.isLoding = false;
				$scope.type = "1";
				$scope.itemName = "";
				$scope.itemType = "";
				$scope.personalItem();
				$scope.dept = false;
		}
	};
	//个人
	$scope.personalItem = function() {
		$scope.deptindex = 0;
		$scope.current = 0;
		$http.jsonp(urlHost + '/aci/declare/getItemTheme.do', {
			params: {
				jsonpCallback: "JSON_CALLBACK",
				areaCode: $scope.areaCode,
				type: '1'
			}
		}).success(function(dataJson) {
			console.log(dataJson);
			$scope.itemType = dataJson.data;
			$scope.itemType.unshift({
				itemTypeCode: "全部",
				itemTypeName: "000",
				type: "1"
			});
			$scope.isLoding = true;
		}).error(function(err) {
			console.log("getItemTheme error");
		});
	}
	//法人
	$scope.corporationItem = function() {
		$scope.deptindex = 0;
		$scope.current = 1;
		$http.jsonp(urlHost + '/aci/declare/getItemTheme.do', {
			params: {
				jsonpCallback: "JSON_CALLBACK",
				areaCode: $scope.areaCode,
				type: '2'
			}
		}).success(function(dataJson) {
			console.log(dataJson);
			$scope.itemType = dataJson.data;
			$scope.itemType.unshift({
				itemTypeCode: "全部",
				itemTypeName: "000",
				type: "2"
			});
			$scope.isLoding = true;
		}).error(function(err) {
			console.log("getItemTheme error");
		});
	}
	//获取所有部门
	$scope.getDepartment = function() {
		$scope.matterVal = '';
		$scope.current = 2;
		$scope.deptindex = 0;
		var organConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode: $scope.areaCode
		};
		$http.jsonp(urlHost + '/aci/declare/getDeptInArea.do', {
			params: organConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.organSetList;
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	};
	//通过主题查事项
	$scope.getItemListByType = function(code, type) {
		$scope.deptindex = 1;
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode: $scope.areaCode,
			themeCode: code,
			type: type
		};
		$http.jsonp(urlHost + '/aci/declare/getItemInTheme.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.data;
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	}

	//个人所有事项
	$scope.getPersonItemList = function() {
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode: $scope.areaCode,
			themeCode: "",
			type: "1"
		};
		$http.jsonp(urlHost + '/aci/declare/getItemInTheme.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.data;
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	}
	//法人所有事项
	$scope.getCorporationItemList = function() {
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode: $scope.areaCode,
			themeCode: "",
			type: "2"
		};
		$http.jsonp(urlHost + '/aci/declare/getItemInTheme.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.data;
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	}
	//查询事项
	$scope.getSearchMatter = function(current) { // 查询事项
		$scope.current = 1;
		$scope.isDept = false;
		$scope.organCode = 'search';
		var vConfig = {
			itemName: $scope.matterVal,
			jsonpCallback: "JSON_CALLBACK",
		};
		if($scope.matterVal) {
			$http.jsonp(urlHost + '/aci/declare/getItemListByItemNameForPage.do', {
				params: vConfig
			}).success(function(dataJson) {
				$('.tabBotbox1inner').find('a:first').addClass('active').siblings().removeClass('active');
				$scope.itemName = dataJson.itemSetList;
			}).error(function() {
				console.log('getOrganListForDeclarePage error')
			})
		} else {
			layer.msg('请输入事项名称');
		}
	};
	//通过部门id获取部门事项
	$scope.getItemByOrganCode = function(code) {
		$scope.current = 2;
		$scope.dept = true;
		$scope.deptindex = 1;
		var oConfig = {
			organCode: code,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/declare/getItemListByOrganCodeForPage.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.itemSetList;
		}).error(function() {
			console.log('getItemListByOrganCodeForPage error')
		})
	};

	$scope.$on('$viewContentLoaded', function() {
		$scope.prev = false;
		if(data.themeCode) {
			$scope.prev = true;
			$scope.getItemListByType(data.themeCode, data.type);
			$scope.isLoding = true;
			$scope.dept = true;
			if(data.type == "1") {
				$scope.current = 0;
			} else if(data.type == "2") {
				$scope.current = 1;
			}
		} else if(data.organCode) {
			$scope.prev = true;
			$scope.getItemByOrganCode(data.organCode);
			$scope.isLoding = true;
		} else {
			$scope.personalItem();
			$scope.dept = false;
		}
	});

	$scope.toItemTypeMaterials = function(name, code, type) {
		if(type) {
			if(code == "全部" && type == "1") {
				console.log(1);
				$scope.getPersonItemList();
				$scope.dept = true;
				$scope.deptindex = 1;
			} else if(code == "全部" && type == "2") {
				$scope.getCorporationItemList();
				$scope.dept = true;
				$scope.deptindex = 1;
			}
		} else {
			data.themeCode = name;
			data.type = $scope.type;
			$scope.getItemListByType(name, $scope.type);
			$scope.dept = true;
		}
	}
	$scope.toMaterials = function(itemName, organCode, organName, itemNo) {
		if(itemName) {
			data.itemName = itemName;
			data.organCode = organCode;
			data.organName = organName;
			data.itemNo = itemNo;
			data.areaCode = $scope.areaCode;
			$location.path("/matter");
		} else if(organCode) {
			$scope.getItemByOrganCode(organCode);
			$scope.dept = true;
		}
	};
	$scope.lastStep = function() {
		$scope.isLoding = false;
		$scope.personalItem();
		$scope.dept = false;
		$scope.prev = false;
	}
	$scope.prevStep = function() {
		$location.path("/start");
	}
	$scope.isScroll = function() {
		myScroll = new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true,
			touchMoveStopPropagation: false
		});
	};
	$scope.isScroll();

});
app.controller("streetController", function($scope, $route, $location, $http, $rootScope, data) {
	$scope.itemName = [];
	$scope.currentMatters = [];
	$scope.matterVal = '';
	$scope.idRead = false;
	//街道下主题
	$scope.getThemeInStreet = function() {
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK"
		}
		$http.jsonp(urlHost+'/aci/declare/getThemeInStreet.do', {
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
	//通过主题获取事项
	$scope.getItemOfThemeInStreet = function(code) {
		$scope.current = 1;
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			themeCode: code
		}
		$http.jsonp(urlHost+'/aci/declare/getItemOfThemeInStreet.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.data;
		}).error(function(err) {
			console.log(err);
		});
	}

	$scope.toMaterials = function(itemTypeCode, itemTypeName, stItemName, stItemNo, organCode) {
		if(itemTypeCode == "全部") {
			$scope.getItemOfThemeInStreet("");
		} else {
			$scope.getItemOfThemeInStreet(itemTypeName);
		}
		if(stItemName) {
			data.itemNo = stItemNo;
			data.organCode = organCode;
			data.itemName = "街道";
			$location.path("/select");
		}
	};
	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
	$scope.prevStep = function() {
		$location.path("/start");
	}
});
app.controller("matterController", function($scope, $route, $http, $location, data, $timeout, $rootScope) {
	$scope.allName = data.itemName;
	var name = data.itemName;
	$scope.itemName = name;

	$scope.getMatterCond = function() {
		var oConfig = {
			itemNo: data.itemNo,
			deptCode: data.organCode,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/declare/getWindowItemStatusList.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.itemList = dataJson.windowItemStatusList;
		}).error(function() {
			console.log('getWindowItemStatusList error')
		})
	};
	$scope.getMatterCond();
	$scope.getSubItem = function(id, name, description, tenNo, itemNo) {
		data.itemId = id;
		data.statusName = name;
		data.description = description;
		data.itemTenNo = tenNo;
		data.itemNo = itemNo;
		//		 		data.applyNo = "751565219600005";
		//		 		$location.path("/materialList");
		$location.path("/guideline");
	};
	$scope.prev = function() {
		data.matindex = 1;
		data.ocode = data.organCode;
		$location.path("/list").search({
			areaCode: data.areaCode
		});
	}

	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});
app.controller("guidelineController", function($scope, $route, $http, $location, $sce, data, $timeout) {
	try {
		window.external.URL_CLOSE();
	} catch(e) {

	}
	var name = data.itemName;
	$scope.itemName = name;
	$scope.description = data.description;
	$scope.statusName = data.statusName;
	$scope.ItemStuffList = "";
	$scope.getGuieInfo = function() {
		var oConfig = {
			stZhallId: data.itemId,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/declare/getGuideInfoByZhallId.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.guideInfo = dataJson.guide;
			$scope.guideInfo.stPromiseTime = $sce.trustAsHtml("<p>法定办结时限:" + $scope.guideInfo.stPromiseTime + "</p><p>承诺办结时限:" + $scope.guideInfo.stLegalTime + "</p>");
			$scope.clRange = dataJson.guide.clRange;
		}).error(function() {
			console.log('getGuideInfoByZhallId error')
		});
	};
	//办事指南二维码地址
	var itemStr = data.itemNo;
	itemStr = itemStr.substring(0, itemStr.length - 1);
	var SHCODE = data.areaCode + itemStr + 1;
	if(data.areaCode == "SH00SH") {
		$scope.codeUrl = "http://zwdt.sh.gov.cn/govPortals/bsfw/findBsfw.do?_organName_=&_organCode_=&_organType_=&_itemId=" + SHCODE + "&_itemType=审批&_stSubitemId=" + data.itemId;
	} else {
		$scope.codeUrl = "http://zwdt.sh.gov.cn/govPortals/bsfw/findBsfw.do?_organName_=黄浦区&_organCode_=SH00HP&_organType_=other&_itemId=" + SHCODE + "&_itemType=审批&_stSubitemId=" + data.itemId;
	}
	console.log($scope.codeUrl);
	var qrcode = new QRCode("code", {
		text: $scope.codeUrl,
		width: 200,
		height: 200,
		correctLevel: 0,
		render: "table"
	});

	$scope.print = function() {
		var lodop = $.device.printGetLodop();
		var style = "<style>table tr>td{text-align:center}table tr>td:nth-child(1){text-align:left}" +
			"table tr>td:nth-child(1){width:920px;}table tr>td:nth-child(2){display: none;}" +
			"table tr>th:nth-child(2){display: none;}table tr>td:nth-child(3){width:200px;margin-left: 20px;}" +
			"table tr>td:nth-child(4){width:130px;margin-left: 20px;}table tr>td:nth-child(5){width:150px;margin-left: 20px;}</style>";
		var html = style + "<body>" + document.getElementById("lodop").innerHTML + "</body>";
		lodop.ADD_PRINT_TEXT(50, 0, "100%", 100, $scope.itemName + "--材料清单");
		lodop.SET_PRINT_STYLEA(0, "Alignment", 2);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		lodop.ADD_PRINT_HTM(150, 0, "100%", "100%", html);
		lodop.PRINT();
	};
	$scope.prev = function() {
		$location.path('/matter');
	}
	//继续
	$scope.next = function() {
		// 判断事项是否通过别的渠道跳转
		var oConfig = {
			itemCode: data.itemTenNo,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/declare/getItemAppplyInfo.do', {
			params: oConfig
		}).success(function(dataJson) {
			if(dataJson) {
				if(dataJson.data.molder == 1) { // 为1 时，走官网一网通办的流程
					localStorage.applyUrl = dataJson.data.itemApplyUrl;
					$location.path("/apply");
				} else { // 为0时，走正常流程
					$location.path("/select");
				}
			}
		}).error(function() {
			console.log('getItemAppplyInfo error')
		});
	}
	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();

	$scope.getGuieInfo();
});
app.controller("applyController", function($scope, $route, $http, $location, data, $timeout, $sce) {
	var name = data.itemName;
	$scope.itemName = name;
	// 设置跳转链接
	if(data.itemName == "街道") {
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
			$http.jsonp(urlHost+'/aci/workPlatform/util/encryptDataByRSA.do', {
				params: httpConfig
			}).success(function(dataJson) {
				console.log(dataJson.result);
				$scope.url = "https://zwdtuser.sh.gov.cn/uc/naturalUser/jumpUcRedirect.do?app_id=535984a5&data=" + encodeURIComponent(dataJson.result) + "&redirect_uri=";
				$scope.url1 = "http://ywtb.sh.gov.cn:18018/ac-product-net/netapply/apply.do?itemCode=" + data.itemNo;
				window.external.URL_OPEN(200, 180, 1500, 700, $scope.url + encodeURIComponent($scope.url1));
			}).error(function(err) {
				console.log('encryptDataByRSA err');
			});

		}
		$scope.encryptDataByRSA();
	} else {
		$scope.applyUrl = "https://zwdtuser.sh.gov.cn/uc/login/login.jsp?self=my&redirect_uri=http://ywtb.sh.gov.cn:18018" + localStorage.applyUrl;
		console.log($scope.applyUrl);
		window.external.URL_OPEN(200, 180, 1500, 700, $scope.applyUrl);
	}

	// 设置url被angular信任 正常跳转
	$scope.prev = function() {
		try {
			window.external.URL_CLOSE();
		} catch(e) {}
		if(data.itemName == "街道") {
			$location.path("street");
		}
		$location.path("/guideline");
	}
});
app.controller("selectController", function($scope, $route, $http, $location, data, $timeout) {
	try {
		window.external.URL_CLOSE();
	} catch(e) {}
	$.device.Camera_Hide();
	var name = data.itemName;
	var statusName = (data.statusName == undefined) ? "" : ("---" + data.statusName);
	console.log(name + "---" + statusName + "---" + data.statusName);
	$scope.itemName = name + statusName;
	//$scope.statusName = data.statusName;

	// 刷身份证获取信息
	$scope.scanIdcard = function() {
		$location.path('/idCard');
	}
	// 市民云亮证
	$scope.citizen = function() {
		$location.path('/citizen');
	}
	$scope.prev = function() {
		if(data.itemName == "街道") {
			$location.path("/street");
		} else {
			$location.path("/guideline");
		}
	}
});
app.controller("idCardController", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$scope.isRead = true;

	$scope.getIdcard = function(info, images) {

		$scope.faceImage = images;
		$scope.isRead = false; //faceImg
		$scope.$apply();
		data.idCardName = info.Name;
		data.idCardNum = info.Number;
		data.VALIDENDDAY = info.ValidtermOfEnd;
		data.VALIDSTARTDAY = info.ValidtermOfStart;
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		if(data.itemName == "街道") {
			$location.path("/apply");
		} else {
			$location.path("/info");
		}
	}
	//	data.idCardName = "zoutianqi";
	//	data.idCardNum = "430426199804106174";
	//	$location.path("/info");

	$scope.prevStep = function() {
		$location.path("/select");
	}
});
app.controller("citizenController", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$scope.isLoding = true;
	$scope.ClearBr = function(key) {
		key = key.replace(/<\/?.+?>/g, "");
		key = key.replace(/[\r\n]/g, "");
		return key;
	}
	// 扫描二维码
	$.device.qrCodeOpen(function(code) {
		var __code = $scope.ClearBr(code);
		$.ajax({
			url: urlHost + "/aci/window/getQrCodeInfoByElectronicCert.do",
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data: {
				codeParam: __code,
				machineId: $.config.get("uniqueId") || "",
				itemName: data.itemName,
				itemCode: "",
				businessCode: "",
				using: "",
				lzAddress: "一网通办智能终端"
			},
			success: function(dataJsonp) {
				if(dataJsonp.result.success) {
					data.idCardName = dataJsonp.result.data.realname;
					data.idCardNum = dataJsonp.result.data.idcard;
					data.VALIDSTARTDAY = dataJsonp.result.data.VALIDSTARTDAY;
					data.VALIDENDDAY = dataJsonp.result.data.VALIDENDDAY;
					data.mobile = dataJsonp.result.data.mobile;
					$timeout(function() {
						if(data.itemName == "街道") {
							$location.path("/apply");
						} else {
							$location.path("/info");
						}
					}, 100);
				} else {
					layer.msg(dataJsonp.result.msg);
					$timeout(function() {
						$location.path('/select');
					}, 100);
				}

			},
			error: function(err) {
				console.log("二维码已过期！")
			}
		});
	}, function(err) {
		console.log("二维码扫描出错：" + err)
	});
	$scope.prevStep = function() {
		$location.path("/select");
	}
});
app.controller("infoController", function($scope, $route, $http, $location, data, $timeout) {
	$.device.Camera_Hide();
	$scope.fullItemName = data.itemName + "--" + data.statusName;
	var name = data.itemName;
	$scope.itemName = name;
	$scope.isLoding = true;
	$scope.list = [{
		obj: '个人'
	}, {
		obj: '法人'
	}];
	$scope.targetTypeName = '个人';
	$scope.targetTips = '申请人身份证号';
	$scope.targetName = '申请人姓名';
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}

	// $wathc 监听 targetTypeName
	$scope.$watch('targetTypeName', function(newValue, oldValue) {
		$scope.targetTypeName == '个人' ? $scope.targetTips = '申请人身份证号' : $scope.targetTips = '统一社会信用代码';
		$scope.targetTypeName == '个人' ? $scope.targetName = '申请人姓名' : $scope.targetName = '企业名称';
		$scope.targetTypeName == '个人' ? $('#targetName').val(data.idCardName) : $('#targetName').val('');
		$scope.targetTypeName == '个人' ? $('#targetNo').val(data.idCardNum) : $('#targetNo').val('');
	});

	$('#username').val(data.idCardName);
	$('#licenseNo').val(data.idCardNum);
	if(data.mobile) {
		$('#mobile').val(data.mobile);
	}
	$scope.prevStep = function() {
		$location.path("/select");
	}
	// 保存数据
	$scope.flag = true;
	$scope.goNext = function() {
		/*$location.path("/materialUpload");*/
		if(!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			if($scope.targetTypeName == '个人') {
				if($('#targetName').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的申请人姓名！";
					return;
				}
				if(!checkIdCard($('#targetNo').val())) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的身份证信息！";
					return;
				}
				if($('#username').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的经办人姓名！";
					return;
				}
				if(!checkIdCard($('#licenseNo').val())) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的身份证信息！";
					return;
				}

				if(!isPhoneAvailable($('#mobile').val())) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的手机号！";
					return;
				}
			} else if($scope.targetTypeName == '法人') {
				if($('#targetName').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的企业名称！";
					return;
				}
				if($('#targetNo').val().length < 17) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的统一社会信用代码！";
					return;
				}
				if($('#username').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的经办人姓名！";
					return;
				}
				if(!checkIdCard($('#licenseNo').val())) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的身份证信息！";
					return;
				}

				if(!isPhoneAvailable($('#mobile').val())) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的手机号！";
					return;
				}

			}
		} while (condFlag);
		$scope.isLoding = false;
		var from = $('#infoForm').serialize();
		var fConfig = {
			'applyNo': '',
			'itemCode': data.itemTenNo, // data.itemTenNo	'0101220000-00-00-2'
			'itemName': data.itemName,
			'userId': '',
			'source': '网上申请',
			'departCode': data.organCode,
			'departName': data.organName,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/declare/saveApply.do?' + from, {
			params: fConfig
		}).success(function(dataJson) {
			$scope.flag = true;
			data.applyNo = dataJson.applyNo;
			if(($('#username').val()) !== null) {
				data.username = $('#username').val();
			} else if(($('#targetName').val()) !== null) {
				data.username = $('#targetName').val();
			}
			$scope.isLoding = true;
			data.mobile = $('#mobile').val();
			$location.path("/materialList");
		}).error(function(e) {
			console.log(e)
		});
		$scope.flag = false;
	};
});
app.controller("materialUploadController", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;

	//上传材料信息
	$scope.stStuffName = data.stuffImg.stuffName;
	$scope.stuffImg = data.sample;
	$scope.test = function() {
		$('#test').viewer({
			url: 'data-original',
		});
	}
	$scope.upload = function() {
		$location.path("/uploadMethod");
	}

});
app.controller("uploadMethodController", function($scope, $route, $http, $location, data, $timeout) {
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
	$scope.prevStep = function() {
		$location.path('/materialList');
	}
});
app.controller("materialPicController", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	$scope.itemName = name;
	$scope.imgUrls = "";
	$scope.prevText = "返回";
	$scope.nextText = "确认上传";
	$scope.profileShow = function() {
		$.ajax({
			url: urlHost + "/aci/autoterminal/dzzz/queryCertBaseData.do",
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				jsonpCallback: "JSON_CALLBACK",
				certNo: data.idCardNum, // "340881199303145313", //
				type: 0,
				machineId: $.config.get("uniqueId") || "",
				itemName: data.itemName,
				itemCode: data.itemTenNo,
				businessCode: data.applyNo,
				name: data.idCardName,
				startDay: data.VALIDSTARTDAY,
				endDay: data.VALIDENDDAY,
			},
			success: function(json) {
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
	$scope.url = urlHost;

	$scope.goNext = function() {
		data.selectImg = urlHost + $scope.imgUrls[$scope.current].pictureUrlForBytes;
		$scope.waitUploadImgUrl = data.selectImg;
		$scope.jsonData = {
			'applyNo': data.applyNo, //  '751122018600008'
			'stuffId': data.stStuffId,
			'stuffCode': data.stuffCode,
			'stuffName': data.stuffName,
			'stuffType': 0,
			'stuffStatus': 0,
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
				$.device.httpUpload(urlHost + '/aci/declare/uploadStuff.do', "file", "C:/waitUploadImg.jpg",
					$scope.jsonData,
					function(result) {
						layer.msg("上传成功");
						data.isUpload.push({
							index: data.currentIndex,
							stuffName: data.stStuffName,
							img: $scope.waitUploadImgUrl,
							method: "个人档案"
						});
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
	$scope.prevStep = function() {
		$location.path('/materialList');
	}
});
app.controller("takePhotoController", function($scope, $route, $http, $location, data, $timeout, $routeParams) {
	var name = data.itemName;
	$scope.itemName = name;
	$scope.isLoading = true;
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
				'applyNo': data.applyNo, //   '751122018600008'
				'stuffId': data.stStuffId,
				'stuffCode': data.stStuffCode,
				'stuffName': data.stStuffName,
				'stuffType': 0,
				'stuffStatus': 0
			};
			$scope.jsonData1 = JSON.stringify($scope.jsonData1);
			$.device.httpUpload(urlHost + "/aci/declare/uploadStuff.do", "file", $scope.UData,
				$scope.jsonData1,
				function(result) {
					layer.msg("上传成功");
					data.fileName.push($scope.UData);
					data.isUpload.push({
						index: data.currentIndex,
						stuffName: data.stStuffName,
						fileName: $scope.UData,
						method: "U盘上传"
					});
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
		$location.path('/materialList');
	}
});
app.controller("finishController", function($scope, $route, $http, $location, data, $timeout) {
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();

	var name = data.itemName;
	$scope.itemName = name;
	$scope.finishUp = [];
	$scope.isLoading = true;
	$scope.prevText = "返回";
	$scope.nextText = "确认上传";
	$.device.cmCaptureShow(680, 530, 210, 340);
	$.device.cmCaptureSelectRect(0, 0, 1920, 1920);
	$scope.isFinish = false;
	// 拍照
	var imgHTML = "";
	var imgIndex = 0;
	$scope.next = function() {
		layer.msg("正在上传中，请稍候");
		$scope.isLoading = false;
		var scanImg = $.device.cmCaptureCaptureUrl();
		$scope.jsonData1 = {
			'applyNo': data.applyNo,
			'stuffId': data.stStuffId,
			'stuffCode': data.stStuffCode,
			'stuffName': data.stStuffName,
			'stuffType': 0,
			'stuffStatus': 0,

		};
		$scope.jsonData1 = JSON.stringify($scope.jsonData1);
		$.device.httpUpload(urlHost + "/aci/declare/uploadStuff.do", "file", scanImg,
			$scope.jsonData1,
			function(result) {
				data.uploadStuffId = data.stStuffId;
				if(data.listImg.length < 1) {
					data.currentIndex++; // 没有材料列表时   文件下标+1
				}
				$scope.finishUp.push({
					index: data.currentIndex,
					stuffName: data.stStuffName,
					img: scanImg,
					method: "高拍仪"
				});
				data.fileName.push('扫描文件');
				imgHTML += '<div class="img" id="' + data.uploadStuffId + '"><img src="' + scanImg + '" width="150" height="200" /></div>';
				$('.imgBox').html(imgHTML);
				$scope.isLoading = true;
				$scope.isFinish = true;
				$scope.$apply();

			},
			function(webexception) {
				layer.msg("上传材料失败")
			});
	};
	// 完成拍照
	$scope.finishUpload = function() {
		for(var i = 0; i < data.isUpload.length; i++) {
			if(data.currentIndex == data.isUpload[i].index) {
				data.isUpload[i] = "";
			}
		}
		for(var i = 0; i < $scope.finishUp.length; i++) {
			data.isUpload.push($scope.finishUp[i]);
		}
		$timeout(function() {
			$.device.cmCaptureHide(); // 关闭高拍仪
			$location.path('/materialList');
		}, 20);
	};

	$scope.last = function() {
		$.device.cmCaptureHide(); // 关闭高拍仪
		$location.path('/materialList');
	}
});
app.controller("materialListController", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$scope.nextText = "提交办件";
	//必传材料列表
	$scope.mustUpload = [];
	$scope.current = 0;
	// 获取材料列表
	var fConfig = {
		itemCode: data.itemTenNo, //"0101361000",//
		jsonpCallback: "JSON_CALLBACK",
	};
	$http.jsonp(urlHost + '/aci/declare/getItemStuffList.do', {
		params: fConfig
	}).success(function(dataJson) {
		$scope.stuffList = dataJson.data;
		for(var s = 0; i < $scope.stuffList.length; i++) {
			if($scope.stuffList[i].isMust == 1) {
				$scope.mustUpload.push({
					index: i,
					stuffName: $scope.stuffList[i].stuffName
				});
			}
		}

		if(data.listImg == 0) {
			for(var i = 0; i < $scope.stuffList.length; i++) {
				data.listImg[i] = {
					'activeImg': null,
					'index': i,
					'stuffName': $scope.stuffList[i].stuffName,
					'upload': true,
					'upload2': false,
				}
			}
		}
		//设置上传文件 按钮变化
		if(data.isUpload != "") {
			for(var i = 0; i < data.isUpload.length; i++) {
				for(var j = 0; j < data.listImg.length; j++) {
					if(data.listImg[j].upload != false) {
						if(data.isUpload[i].stuffName == data.listImg[j].stuffName) {
							data.listImg[data.isUpload[i].index].upload = false;
							data.listImg[data.isUpload[i].index].upload2 = true;
						}
					}
				}
			}
		}
		$scope.listImg = data.listImg;
	}).error(function() {
		console.log('queryStuffList error')
	})

	// 材料上传
	data.currentIndex++;
	$scope.toUploadMaterial = function(index, id, code, name) {
		data.stStuffId = id;
		data.stStuffCode = code;
		data.stStuffName = name;
		data.currentIndex = index;
		data.stuffImg = data.listImg[data.currentIndex];
		$location.path("/uploadMethod");
	}
	//重新上传
	$scope.toNewUploadMaterial = function(index, id, code, name) {
		data.stStuffId = id;
		data.stStuffCode = code;
		data.stStuffName = name;
		data.currentIndex = index;
		data.stuffImg = data.listImg[data.currentIndex];
		$location.path("/uploadMethod");
	}

	//查看
	$scope.view = function(index, code, name) {
		data.currentIndex = index;
		data.view = data.isUpload;
		$location.path("/materialView");
	}

	//提交办件
	$scope.submit = function() {
		var a = 0;
		if($scope.mustUpload.length == 0) {
			$location.path('/infoFinish');
		} else {
			if($scope.mustUpload.length <= data.isUpload.length) {
				for(var i = 0; i < data.isUpload.length; i++) {
					for(var j = 0; j < $scope.mustUpload.length; j++) {
						if(data.isUpload[i].stuffName == $scope.mustUpload.stuffName) {
							a++;
						}
					}
				}
				if(a >= $scope.mustUpload.length) {
					$location.path('/infoFinish');
				} else {
					layer.msg("请提交必上传材料");
				}
			} else {
				layer.msg("请提交必上传材料");
			}
		}
	};

	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
	$scope.prevStep = function() {
		$location.path("/info");
	}
});
app.controller("materialViewController", function($scope, $http, $location, data) {
	console.log(data.isUpload);
	$scope.stuffList = []; //所有图片的容器
	$scope.showImgList = []; //显示图片的容器
	$scope.currentPage = 1; //当前页
	$scope.totalPages = 1; //总页数
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	//当页显示图片
	$scope.currentList = function(current) {
		if(current === undefined) {
			current = $scope.currentPage;
		}
		$scope.currentImgIndex = null;
		$scope.startPos = (current - 1) * 3; //0 -3  3 - 6
		$scope.endPos = $scope.startPos + 3;
		console.log($scope.stuffList);
		$scope.showImgList = $scope.stuffList.slice($scope.startPos, $scope.endPos);
		$scope.totalPages = Math.ceil($scope.stuffList.length / 3);
	}
	for(var i = 0; i < data.view.length; i++) {
		if(data.currentIndex == data.view[i].index) {
			$scope.stuffList.push(data.view[i]);
			$scope.currentList();
		}
	}

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
	//图片显示
	$scope.closeFlag = true;
	$scope.imgShow = function(imgUrl) {
		$scope.largeImg = imgUrl;
		$scope.closeFlag = !$scope.closeFlag;
	}
	//打开文件
	$scope.open = function() {
		try {
			$.device.officeOpen($scope.stuffList[0].fileName);
		} catch(e) {
			layer.msg("未找到此文件");
		}
	}
	$scope.prevStep = function() {
		$location.path("/materialList");
	}
});
app.controller("infoFinishController", function($scope, $route, $http, $location, data, $timeout) {
	$scope.allName = data.itemName + '--' + data.statusName;
	var name = data.itemName;
	$scope.itemName = name;
	var htmlBody = "";
	$scope.nextText = "返回首页";
	// 生成办件编码
	$scope.submitApply = function() {
		$scope.finishData = {
			applyNo: data.applyNo, // '751122018600008'
			subItemCodes: '', // data.itemId
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/declare/submitApply.do', {
			params: $scope.finishData
		}).success(function(dataJson) {
			console.log(dataJson);
			$scope.print();
		}).error(function(e) {
			console.log(e)
		});
	}
	$scope.stuffList = function() {
		console.log(JSON.stringify(data.isUpload));
		var stuffList = [];
		for(let i in data.isUpload) {
			console.log(stuffList.indexOf(data.isUpload[i].stuffName));
			if(stuffList.indexOf(data.isUpload[i].stuffName) == -1 && data.isUpload[i].stuffName != undefined && data.isUpload[i].stuffName != null) {
				stuffList.push(data.isUpload[i].stuffName);
			}
		}
		var str = "<style>table{font-family:'FangSong'}table td{border:1px solid} tr td:nth-child(1){text-align:center}tr td:nth-child(2){padding-left:20px;}tr td:nth-child(3){padding-left:20px;} table th{border:1px solid} table{border-collapse: collapse}</style><table><tr><th style='width:60px'>序号</th><th style='width:300px'>申请材料</th><th style='width:250px'>材料来源</th></tr>";
		htmlBody = str;
		var str2 = "</table>";
		for(let i in stuffList) {
			var str1 = "<tr><td>" + (parseInt(i) + 1) + "</td><td>" + stuffList[i] + "</td><td></td></tr>";
			htmlBody = htmlBody + str1;
		}
		htmlBody = htmlBody + str2;
	}
	$scope.stuffList();
	$scope.submitApply();
	$scope.applyNo = data.applyNo;
	$scope.statusText = '打印凭证';
	var code = "http://zwdt.huangpuqu.sh.cn:8080/ac/aci/HP/declare/index.html#/qrCode?applyNo=" + data.applyNo;
	var date = new Date();
	var month = date.getMonth() + 1;
	// 打印凭条
	$scope.print = function() {
		var lodop = $.device.printGetLodop();
		lodop.ADD_PRINT_BARCODE(38, 34, 307, 47, "128A", $scope.applyNo);
		lodop.ADD_PRINT_TEXT(170, 574, 200, 30, "扫一扫,查进度");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 10);
		lodop.ADD_PRINT_BARCODE(38, 550, 168, 146, "QRCode", $scope.applyNo); // code
		lodop.ADD_PRINT_TEXT(190, 165, 600, 50, "上海市政务服务申请材料 收件凭证");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		lodop.SET_PRINT_STYLEA(0, "BOLD", 1);
		lodop.ADD_PRINT_TEXT(250, 28, 600, 30, "申请事项：");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "BOLD", 1);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(250, 125, 600, 30, data.itemName);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(280, 28, 600, 30, "申请人：");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "BOLD", 1);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(280, 125, 600, 30, data.username);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(280, 480, 600, 30, "联系电话：");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "BOLD", 1);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(280, 580, 600, 30, data.mobile);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(330, 65, 700, 30, "经核查，您（单位）提交的申请材料齐全，符合法定形式，现予收件。");
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(370, 25, 700, 120, "    根据规定，承办部门将在出具本凭证之日起5个工作日内，作出受理或不予受理的决定，申请材料不齐全或者不符合法定形式的，将在5个工作日内一次告知需要补正的材料。如在5个工作日内未被告知需要补正材料的，则视为正式受理，本凭证即为受理凭证。");
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(500, 65, 670, 150, "收件材料清单附后");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "BOLD", 1);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(560, 480, 300, 30, "收件日期：" + date.getFullYear() + "年" + month + "月" + date.getDate() + "日");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TABLE(700, 50, 700, 1000, htmlBody);
		lodop.PRINT();
	};
});
app.controller("qrCodeController", function($scope, $route, $http, $location, data, $timeout) {
	$scope.applyNo = $location.search().applyNo;
	$scope.isLoding = false;
	$scope.process = function() {
		var pConfig = {
			stApplyNo: $scope.applyNo,
			jsonpCallback: "JSON_CALLBACK"
		}
		$http.jsonp(urlHost + '/aci/autoterminal/eventquery/getApplyInfoByStApplyNo.do', {
			params: pConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.stItemName;
			$scope.name = dataJson.stName || dataJson.stUnit;
			$scope.date = dataJson.stApplyStr;
			$scope.isLoding = true;
		}).error(function(err) {
			console.log('getApplyInfoByStApplyNo error');
		});
	}
	$scope.process();
});