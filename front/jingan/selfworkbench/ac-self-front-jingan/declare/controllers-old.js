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
app.controller("mainController", function($scope, $location, $http, data, $rootScope, $timeout, $interval, appFactory) {
	$scope.goToApp = function(address) {
		if(address.indexOf("http") != -1) {
			window.location.href = address;
		} else {
			window.location.href = address;
		}
	};
	$scope.prevStep = function() {
		$location.path('../main');
	}
	$rootScope.goHome = function() {
		try {
			window.external.URL_CLOSE();
		} catch(e) {}
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
	};
});
app.controller("startController", function($scope, $location, $http, data, $rootScope, $timeout, $interval, appFactory) {
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
	$scope.prevStep = function() {
		$location.path('/main');
	}
	$rootScope.goHome = function() {
		try {
			window.external.URL_CLOSE();
		} catch(e) {}
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
	};
});
app.controller("listController", function($scope, $route, $location, $http, $rootScope, data, appFactory) {
	$scope.current = 0;
	$scope.searchType = ["个人", "法人", "部门"];
	data.isUpload = [];
	data.listImg = [];
	data.fileName = [];
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
		removeAnimate($('#wrapper'))
		$scope.deptindex = 0;
		$scope.current = 0;
		//		urlHost1 + '/aci/declare/getItemTheme.do'
		//http://xzfwzx.jingan.gov.cn:8080/ac/aci/declare/getAllItemListForPage.do
		//		urlHost1 + '/ac-product/aci/declare/getItemTheme.do'
		$http.jsonp(JA_Extranet_urlHost + '/aci/declare/getItemTheme.do', {
			params: {
				jsonpCallback: "JSON_CALLBACK",
				areaCode: "SH00JA",
				type: '1'
			}
		}).success(function(dataJson) {
			console.log('以下是个人办事列表');
			console.log(dataJson);
			$scope.itemType = dataJson.data;
			$scope.itemType.unshift({
				itemTypeCode: "全部",
				itemTypeName: "000",
				type: "1"
			});
			$scope.isLoding = true;
			addAnimate($('#wrapper'))
		}).error(function(err) {
			console.log("个人办事列表获取失败");
		});
	}
	//法人
	$scope.corporationItem = function() {
		removeAnimate($('#wrapper'))
		$scope.deptindex = 0;
		$scope.current = 1;
		$http.jsonp(JA_Extranet_urlHost + '/aci/declare/getItemTheme.do', {
			params: {
				jsonpCallback: "JSON_CALLBACK",
				areaCode: "SH00JA",
				type: '2'
			}
		}).success(function(dataJson) {
			console.log('以下是法人办事列表');
			console.log(dataJson);
			$scope.itemType = dataJson.data;
			$scope.itemType.unshift({
				itemTypeCode: "全部",
				itemTypeName: "000",
				type: "2"
			});
			$scope.isLoding = true;
			addAnimate($('#wrapper'))
		}).error(function(err) {
			console.log("获取法人办事列表失败");
		});
	}
	//获取所有部门
	$scope.getDepartment = function() {
		removeAnimate($('#wrapper'))
		$scope.matterVal = '';
		$scope.current = 2;
		$scope.deptindex = 0;
		var organConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode: "SH00JA"
		};
		$http.jsonp(JA_Extranet_urlHost + '/aci/declare/getDeptInArea.do', {
			params: organConfig
		}).success(function(dataJson) {
			console.log('部门办事列表');
			console.log(dataJson);
			$scope.itemName = dataJson.organSetList;
			addAnimate($('#wrapper'))
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	};
	//通过主题查事项
	$scope.getItemListByType = function(code, type) {
		removeAnimate($('#wrapper'))
		$scope.deptindex = 1;
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode: "SH00JA",
			themeCode: code,
			type: type
		};
		$http.jsonp(JA_Extranet_urlHost + '/aci/declare/getItemInTheme.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.data;
			$scope.isLoding = true;
			addAnimate($('#wrapper'))
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	}

	//个人所有事项
	$scope.getPersonItemList = function() {
		removeAnimate($('#wrapper'))
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode: "SH00JA",
			themeCode: "",
			type: "1"
		};
		$http.jsonp(JA_Extranet_urlHost + '/aci/declare/getItemInTheme.do', {
			params: tConfig
		}).success(function(dataJson) {
			console.log('个人办事列表-->列表');
			console.log(dataJson);
			$scope.itemName = dataJson.data;
			addAnimate($('#wrapper'))
		}).error(function() {
			console.log('获取个人办事列表-->列表 失败');
		})
	}
	//法人所有事项
	$scope.getCorporationItemList = function() {
		removeAnimate($('#wrapper'))
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode: "SH00JA",
			themeCode: "",
			type: "2"
		};
		$http.jsonp(JA_Extranet_urlHost + '/aci/declare/getItemInTheme.do', {
			params: tConfig
		}).success(function(dataJson) {
			console.log('以下是法人办事列表-->列表');
			console.log(dataJson);
			$scope.itemName = dataJson.data;
			addAnimate($('#wrapper'))
		}).error(function() {
			console.log('获取法人办事列表-->列表 失败');
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
			$http.jsonp(JA_Extranet_urlHost + '/aci/declare/getItemListByItemNameForPage.do', {
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
		removeAnimate($('#wrapper'))
		$scope.itemName = "";
		$scope.current = 2;
		$scope.dept = true;
		$scope.deptindex = 1;
		var oConfig = {
			organCode: code,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(JA_Extranet_urlHost + '/aci/declare/getItemListByOrganCodeForPage.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.itemSetList;
			$scope.isLoding = true;
			addAnimate($('#wrapper'))
		}).error(function() {
			console.log('getItemListByOrganCodeForPage error')
		})
	};

	$scope.$on('$viewContentLoaded', function() {
		if(data.themeCode) {
			$scope.getItemListByType(data.themeCode, data.type);
			$scope.dept = true;
			if(data.type == "1") {
				$scope.current = 0;
			} else if(data.type == "2") {
				$scope.current = 1;
			}
		} else if(data.organCode) {
			$scope.getItemByOrganCode(data.organCode);
		} else {
			$scope.personalItem();
			$scope.dept = false;
		}
	});

	//	$scope.isScroll = function() {
	//
	//		new iScroll("wrapper", {
	//			vScrollbar: false,
	//			hScrollbar: false,
	//			hideScrollbar: false,
	//			bounce: true,
	//			hScroll: false,
	//			checkDOMChanges: true
	//		});
	//	};
	//	$scope.isScroll();
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
		addAnimate($('#wrapper'))
		if(itemName) {
			data.itemName = itemName;
			data.organCode = organCode;
			data.organName = organName;
			data.itemNo = itemNo;
			$location.path("/matter");
		} else if(organCode) {
			$scope.getItemByOrganCode(organCode);
			$scope.dept = true;
		}
	};
	$scope.prevStep = function() {
		$location.path("/start");
	}
});
app.controller("citylistController", function($scope, $route, $location, $http, $rootScope, data, appFactory) {
	$scope.current = 0;
	$scope.searchType = ["个人", "法人", "部门"];
	data.areaL = "SH00SH";
	data.isUpload = [];
	data.listImg = [];
	data.fileName = [];
	$scope.current = data.matindex || 0;
	$scope.itemName = [];
	$scope.currentMatters = [];
	$scope.matterVal = '';
	$scope.organCode = data.ocode || null;
	$scope.itemName = "";
	$scope.type = 1;
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
		removeAnimate($('#wrapper'))
		$scope.deptindex = 0;
		$scope.current = 0;
		$http.jsonp(urlHost1 + '/aci/declare/getItemTheme.do', {
			params: {
				jsonpCallback: "JSON_CALLBACK",
				areaCode: 'SH00SH',
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
		removeAnimate($('#wrapper'))
		$scope.deptindex = 0;
		$scope.current = 1;
		$http.jsonp(urlHost1 + '/aci/declare/getItemTheme.do', {
			params: {
				jsonpCallback: "JSON_CALLBACK",
				areaCode: 'SH00SH',
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
			addAnimate($('#wrapper'))
		}).error(function(err) {
			console.log("getItemTheme error");
		});
	}
	//个人所有事项
	$scope.getPersonItemList = function() {
		removeAnimate($('#wrapper'))
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode: "SH00SH",
			themeCode: "",
			type: "1"
		};
		$http.jsonp(urlHost1 + '/aci/declare/getItemInTheme.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.data;
			addAnimate($('#wrapper'))
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	}
	//法人所有事项
	$scope.getCorporationItemList = function() {
		removeAnimate($('#wrapper'))
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode: "SH00SH",
			themeCode: "",
			type: "2"
		};
		$http.jsonp(urlHost1 + '/aci/declare/getItemInTheme.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.data;
			addAnimate($('#wrapper'))
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	}
	//获取所有部门
	$scope.getDepartment = function() {
		removeAnimate($('#wrapper'))
		$scope.matterVal = '';
		$scope.current = 2;
		$scope.deptindex = 0;
		var organConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode: 'SH00SH'
		};
		$http.jsonp(urlHost1 + '/aci/declare/getDeptInArea.do', {
			params: organConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.organSetList;
			addAnimate($('#wrapper'))
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	};
	//通过主题查事项
	$scope.getItemListByType = function(code, type) {
		removeAnimate($('#wrapper'))
		$scope.deptindex = 1;
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode: 'SH00SH',
			themeCode: code,
			type: type
		};
		$http.jsonp(urlHost1 + '/aci/declare/getItemInTheme.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.data;
			addAnimate($('#wrapper'))
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
			$http.jsonp(urlHost1 + '/aci/declare/getItemListByItemNameForPage.do', {
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
		removeAnimate($('#wrapper'))
		$scope.itemName = "";
		$scope.current = 2;
		$scope.dept = true;
		$scope.deptindex = 1;
		var oConfig = {
			organCode: code,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost1 + '/aci/declare/getItemListByOrganCodeForPage.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.itemSetList;
			addAnimate($('#wrapper'))
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
	//	$scope.isScroll();

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
			$location.path("/matter");
		} else if(organCode) {
			$scope.getItemByOrganCode(organCode);
			$scope.dept = true;
		}
	};

	$scope.prevStep = function() {
		$location.path('/start');
	}
});
app.controller("matterController", function($scope, $route, $http, $location, data, $timeout, $rootScope, appFactory) {
	console.log(data.themeCode, data.type);
	$scope.allName = data.itemName;
	var name = data.itemName;
	$scope.itemName = name;;
	$scope.getMatterCond = function() {
		removeAnimate($('#wrapper'))
		var oConfig = {
			itemNo: data.itemNo,
			deptCode: data.organCode,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(JA_Extranet_urlHost + '/aci/declare/getWindowItemStatusList.do', {
			params: oConfig
		}).success(function(dataJson) {
			console.log(dataJson);
			$scope.itemList = dataJson.windowItemStatusList;
			addAnimate($('#wrapper'))
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
		$location.path("/guideline");
	};
	$scope.prev = function() {
		console.log(data.areaL);
		data.matindex = 1;
		if(data.areaL == "SH00SH") {
			data.ocode = data.organCode;
			data.themeCode = data.themeCode;
			data.type = data.type;
			$location.path("/citylist");
		} else if(data.areaL == "SH") {
			data.themeCode = data.themeCode;
			data.ocode = data.organCode;
			data.type = data.type;
			$location.path("/list");
		}

	}

	//	$scope.isScroll = function() {
	//
	//		new iScroll("wrapper", {
	//			vScrollbar: true,
	//			hScrollbar: false,
	//			hideScrollbar: false,
	//			bounce: true,
	//			hScroll: false,
	//			checkDOMChanges: true
	//		});
	//	};
	//	$scope.isScroll();
});
app.controller("guidelineController", function($scope, $route, $http, $location, $sce, $rootScope, data, $timeout, appFactory) {
	try {
		window.external.URL_CLOSE();
	} catch(e) {

	}
	removeAnimate($('.scrollBox2'))
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
	$scope.getGuieInfo = function() {
		var oConfig = {
			stZhallId: data.itemId,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(JA_Extranet_urlHost + '/aci/declare/getGuideInfoByZhallId.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.guideInfo = dataJson.guide;
			$scope.guideInfo.stPromiseTime = $sce.trustAsHtml("<p>法定办结时限:" + $scope.guideInfo.stPromiseTime + "</p><p>承诺办结时限:" + $scope.guideInfo.stLegalTime + "</p>");
			$scope.clRange = dataJson.guide.clRange;
			addAnimate($('.scrollBox2'))
		}).error(function() {
			console.log('getGuideInfoByZhallId error')
		});
	};
	//办事指南二维码地址
	var itemStr = data.itemNo;
	itemStr = itemStr.substring(0, itemStr.length - 1);
	var SHCODE = "SH00HK" + itemStr + 1;
	$scope.codeUrl = "http://zwdt.sh.gov.cn/govPortals/bsfw/findBsfw.do?_organName_=静安区&_organCode_=SH00JA&_organType_=other&_itemId=" + SHCODE + "&_itemType=审批&_stSubitemId=" + data.itemId;
	console.log($scope.codeUrl);
	var qrcode = new QRCode("code", {
		text: $scope.codeUrl,
		width: 200,
		height: 200,
		correctLevel: 0,
		render: "table"
	});

	$scope.print = function() {
		$scope.isAlert = true;
		$scope.msg = "正在打印...";
		let lodop = $.device.printGetLodop();
		let style = "<style>table tr>td{text-align:center}table tr>td:nth-child(1){text-align:left}" +
			"table tr>td:nth-child(1){width:920px;}table tr>td:nth-child(2){display: none;}" +
			"table tr>th:nth-child(2){display: none;}table tr>td:nth-child(3){width:200px;margin-left: 20px;}" +
			"table tr>td:nth-child(4){width:130px;margin-left: 20px;}table tr>td:nth-child(5){width:150px;margin-left: 20px;}</style>";
		let html = style + "<body>" + document.getElementById("lodop").innerHTML + "</body>";
		lodop.ADD_PRINT_TEXT(50, 0, "100%", 100, $scope.itemName + "--材料清单");
		lodop.SET_PRINT_STYLEA(0, "Alignment", 2);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		lodop.ADD_PRINT_HTM(150, 0, "100%", "100%", html);
		lodop.PRINT();
		$timeout(function() {
			$scope.isAlert = false;
		}, 3000);
	};

	//继续
	$scope.next = function() {
		// 判断事项是否通过别的渠道跳转
		var oConfig = {
			itemCode: data.itemTenNo,
			jsonpCallback: "JSON_CALLBACK",
		};
		// 此处一网通办接口暂时不能用, 先用黄浦的替代一下: 黄浦: urlHost
		$http.jsonp(JA_Extranet_urlHost + '/aci/declare/getItemAppplyInfo.do', {
			params: oConfig
		}).success(function(dataJson) {
			console.log(dataJson.data.molder);
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
	$scope.prev = function() {
		$location.path('/matter');
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

	$scope.getGuieInfo();
});
app.controller("applyController", function($scope, $route, $http, $location, data, $timeout, $rootScope, $sce, appFactory) {
	var name = data.itemName;
	$scope.itemName = name;
	$scope.is_show_prev_btn = true;
	$scope.is_show_next_btn = false;
	$scope.prev_btn_info = '上一步';
	$scope.next_btn_info = '上一步';
	// 设置跳转链接

	//	  //公钥
	//      var PUBLIC_KEY = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC68dB76JHZnops1bDHR4a2tBz+I09j2aWOSzOeBVGbJs4HI0XwPCfFVA3q2dLNpQEdDEHNwDg3M/y50uOygp1cyXG12Gy1S+QcqSl0BgGOH39aoJ5cyRkojLSKkzol670sfErX049xQvuq77wFznvHnfQzHgQkEQLqJnkYrFvr2QIDAQAB';
	//      //私钥
	//      var PRIVATE_KEY = 'MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALrx0HvokdmeimzVsMdHhra0HP4jT2PZpY5LM54FUZsmzgcjRfA8J8VUDerZ0s2lAR0MQc3AODcz/LnS47KCnVzJcbXYbLVL5BypKXQGAY4ff1qgnlzJGSiMtIqTOiXrvSx8StfTj3FC+6rvvAXOe8ed9DMeBCQRAuomeRisW+vZAgMBAAECgYB5lRGRtLU+woSmuefqA1PS+ZstkcttVjz9KV2dtTnY3Uj7jW5MCuOWy87tYdNfGaR6vuEBLrWg+XexZz3deGNcu7t0B4IRHu/54RPPXQszvoN0AcqcDaQ/sUHKE4MDwX5ij5wwA+V2TOUducBGH9+5or5N/IUzdLtKcnWlKoieMQJBANu2wk9xknXaH+dg4x1dVDzYpG1+rix8qA+dok2vF1twS7sdJwhngS7nsMSBjAIETGd2w94ZBz6VBGA9kx1C7K0CQQDZ0ZuhHPV70GhSG2ZyOI4hGI+lEAsCQe7nEKclK2U3oXZR2BNCAj+AZwO1GXqPguC+c5SBwbTTciMGysIwoNVdAkAsX7jWuqVN0APpgxPbdmHw+AAdbRxYN8TpgnipH9ejzAY/gB/F/sGEa56z0UYpkhysOLxOOtfPt+DuXwE7Q6zxAkEAzeCIsOemP7jkYXb0hdFexXlpjCJ1xVR8cnoTAdbafJJoO0N4MFPfoYW8w1epuCuEMX8dRufH+nNPGARdN4lNIQJAVtROB3lmtVYizbeMEEHFSIT3hEUQBTIcFekRgXcX4XYzv+tH35VKWjIH0IOLUvj4FOuTAuwdHs6Ux8HeHM4hHg==';
	//      //使用公钥加密
	//      var encrypt = new JSEncrypt();
	//		  //encrypt.setPrivateKey('-----BEGIN RSA PRIVATE KEY-----'+PRIVATE_KEY+'-----END RSA PRIVATE KEY-----');
	//      encrypt.setPublicKey('-----BEGIN PUBLIC KEY-----' + PUBLIC_KEY + '-----END PUBLIC KEY-----');
	//      var encrypted = encrypt.encrypt();
	//      console.log('加密后数据:%o', encrypted);
	//      //使用私钥解密
	//      var decrypt = new JSEncrypt();
	//		//decrypt.setPublicKey('-----BEGIN PUBLIC KEY-----' + PUBLIC_KEY + '-----END PUBLIC KEY-----');
	//      decrypt.setPrivateKey('-----BEGIN RSA PRIVATE KEY-----'+PRIVATE_KEY+'-----END RSA PRIVATE KEY-----');
	//      var uncrypted = decrypt.decrypt(encrypted);
	//      console.log('解密后数据:%o', uncrypted);
	//
	$scope.applyUrl = "https://zwdtuser.sh.gov.cn/uc/login/login.jsp?self=my&redirect_uri=http://ywtb.sh.gov.cn:18018" + localStorage.applyUrl;
	console.log($scope.applyUrl);
	window.external.URL_OPEN(200, 150, 1500, 750, $scope.applyUrl);

	// 设置url被angular信任 正常跳转
	$scope.prevStep = function() {
		try {
			window.external.URL_CLOSE();
		} catch(e) {}
		$location.path("/guideline");
	}
	$scope.goHome = function() {
		$.device.Camera_Hide();
		$.device.Camera_UnLink();
		$.device.cmCaptureHide();
		$.device.idCardClose();
		$.device.qrCodeClose();
		$.device.officeClose();
		try {
			window.external.URL_CLOSE();
		} catch(e) {}
		$.device.GoHome();
	};
});
app.controller("selectController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
	removeAnimate($('.linkBox1'))
	try {
		window.external.URL_CLOSE();
	} catch(e) {}
	$.device.Camera_Hide();
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	addAnimate($('.linkBox1'))
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
		$location.path("/guideline");
	}
});
app.controller("queryFaceController", function($scope, $location, data) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.statusName = data.statusName;

	$scope.getInfo = function(idCard, Name) {
		data.idCardName = idCard;
		data.idCardNum = Name;
		console.log(data.idCardName + '===' + data.idCardNum);
	}
	$scope.prev = function() {
		$location.path("/select");
	}
	$scope.next = function() {
		$location.path("/info");
	}
});
app.controller("idCardController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
	removeAnimate($('.main2'))
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$scope.isRead = true;
	addAnimate($('.main2'))
	$scope.getIdcard = function(info, images) {
		$scope.faceImage = images;
		$scope.isRead = false; //faceImg
		$scope.$apply();
		data.idCardName = info.Name;
		data.idCardNum = info.Number;
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		$location.path("/info");
	}

	//	data.idCardName = "李华熙";
	//	data.idCardNum = "520222199406140030";
	//	$location.path("/info");

	$scope.prevStep = function() {
		$location.path("/select");
	}

});
app.controller("citizenController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$scope.isLoding = true;
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.SisAlert = false;
		$location.path("/select");
	}
	$scope.ClearBr = function(key) {
		key = key.replace(/<\/?.+?>/g, "");
		key = key.replace(/[\r\n]/g, "");
		return key;
	}
	// 扫描二维码
	$.device.qrCodeOpen(function(code) {
		$scope.isLoding = false;
		var __code = $scope.ClearBr(code);
		$.ajax({
			//			url: urlHost + "/aci/window/getInfoByCodeTest.do",
			url: JA_Extranet_urlHost + "/aci/window/getQrCodeInfoByElectronicCert.do",
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data: {
				codeParam: __code,
				lzAddress: '',
				using: '',
				machineId: $.config.get('uniqueId') || "",
				itemName: '',
				itemCode: '',
				businessCode: ''
			},
			success: function(dataJsonp) {
				if(dataJsonp.result.success) {
					data.idCardName = dataJsonp.result.data.realname;
					data.idCardNum = dataJsonp.result.data.idcard;
					data.mobile = dataJsonp.result.data.mobile;
					data.idcard_valid_start_day = dataJsonp.result.data.VALIDSTARTDAY;
					data.idcard_valid_end_day = dataJsonp.result.data.VALIDENDDAY;
					$timeout(function() {
						$location.path('/info');
					}, 100);
				} else {
					$scope.isAlert = true;
					$scope.msg = dataJsonp.result.msg;
					$timeout(function() {
						$location.path('/select');
					}, 100);
				}

			},
			error: function(err) {
				$scope.isAlert = true;
				$scope.msg = "二维码已过期！";
			}
		});
	}, function(err) {
		console.log("二维码扫描出错：" + err)
	});
	$scope.prevStep = function() {
		$location.path("/select");
	}
});
app.controller("infoController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
	//	removeAnimate($('.scrollBox2'))
	$scope.concel = 'false'; // 关闭alert弹框的取消按钮
	$.device.Camera_Hide();
	$scope.fullItemName = data.itemName + "--" + data.statusName;
	var name = data.itemName;
	$scope.itemName = name;

	$scope.list = [{
			obj: '个人'
		},
		{
			obj: '法人'
		}
	];
	$scope.targetTypeName = '个人';
	$scope.targetTips = '申请人身份证号';
	$scope.targetName = '申请人姓名';
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//	addAnimate($('.scrollBox2'))
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

	// 保存数据
	$scope.flag = true;
	$scope.prevStep = function() {
		$location.path("/select");
	}
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
		// 一网通办接口用不了
		//		urlHost
		$http.jsonp(JA_Extranet_urlHost + '/aci/declare/saveApply.do?' + from, {
			params: fConfig
		}).success(function(dataJson) {
			$scope.flag = true;
			// 有的地方的applyNo可能存储与dataJson.data.applyNo
			data.applyNo = dataJson.applyNo;
			if(($('#username').val()) !== null) {
				data.username = $('#username').val();
			} else if(($('#targetName').val()) !== null) {
				data.username = $('#targetName').val();
			}

			$location.path("/materialList");
		}).error(function(e) {
			console.log(e)
		});
		$scope.flag = false;
	};
});
app.controller("materialUploadController", function($scope, $route, $http, $location, $rootScope, data, $timeout, appFactory) {
	removeAnimate($('.main2'))
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	addAnimate($('.main2'))
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
app.controller("uploadMethodController", function($scope, $route, $rootScope, $http, $location, data, $timeout, appFactory) {
	removeAnimate($('.scrollBox2'))
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();
	var name = data.itemName;
	$scope.statusName = data.statusName;
	$scope.itemName = name;
	// 显示材料名称
	$scope.stStuffName = data.stStuffName;
	$scope.stuffNameShow = data.isShowStuffName;
	addAnimate($('.scrollBox2'))
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
		$location.path("/materialList");
	}
});
app.controller("materialPicController", function($scope, $route, $http, $rootScope, $location, data, $timeout, appFactory) {
	var name = data.itemName;
	$scope.itemName = name;
	$scope.imgUrls = "";
	$scope.prevText = "返回";
	$scope.nextText = "确认上传";
	$scope.idCardNum = data.idCardNum;
	$scope.noDzzzData = false;
	$scope.profileShow = function() {
		//		$.ajax({
		////			urlHost
		//			url: JA_Extranet_urlHost + "/aci/autoterminal/dzzz/queryCertBaseData.do",
		//			type: "get",
		//			dataType: "jsonp",
		//			jsonp: "jsonpCallback",
		//			data: {
		//				jsonpCallback: "JSON_CALLBACK",
		//				certNo: $scope.idCardNum, // "340881199303145313", // 
		//				type: 0
		//			},
		//			success: function(json) {
		//				var dataJson = eval("(" + JSON.stringify(json) + ")");
		//				if(!dataJson) { // !dataJson[0].address
		//					layer.msg("没有数据，请重新选择上传方式!");
		//					$timeout(function() {
		//						$location.path('/uploadMethod');
		//					}, 1000);
		//				} else {
		//					$scope.imgUrls = dataJson;
		//					$scope.$apply();
		//					console.log($scope.imgUrls)
		//				}
		//			},
		//			error: function(err) {
		//				console.log(err)
		//			}
		//		});
		var httpConfig = {
			jsonpCallback: "JSON_CALLBACK",
			certNo: $scope.idCardNum, //"340881199303145313" || 
			type: '0', //"0" ||
			machineId: $.config.get('uniqueId') || "",
			itemName: '',
			itemCode: '',
			businessCode: '',
			name: data.idCardName,
			startDay: data.idcard_valid_start_day || '',
			endDay: data.idcard_valid_end_day || ''
		};
		$timeout(function() {
			$http.jsonp(JA_Extranet_urlHost + "/aci/autoterminal/dzzz/queryCertBaseData.do", {
					params: httpConfig
				})
				.success(function(json) {
					var dataJson = eval("(" + JSON.stringify(json) + ")");
					console.log(dataJson);
					if(dataJson.length == 0) { // !dataJson[0].address //!dataJson
						//						layer.msg("没有数据，请重新选择上传方式!");
						$scope.isAlert = true;
						$scope.noDzzzData = true;
						$scope.msg = '没有数据,请重新选择上传方式!';
						$timeout(function() {
							$location.path('/uploadMethod');
						}, 1000);
					} else {
						$scope.imgUrls = dataJson;
						//						$scope.$apply();
						console.log($scope.imgUrls)
					}
				})
				.error(function(err) {
					console.log(err);
				})
		})
	};
	$scope.profileShow();
	$scope.current = 0;
	$scope.select = function(index) {
		$scope.current = index;
	}
	//	$scope.url = urlHost;
	$scope.url = JA_Extranet_urlHost;

	$scope.goNext = function() {
		layer.msg("上传中 请稍侯");
		//		data.selectImg = urlHost + $scope.imgUrls[$scope.current].pictureUrlForBytes;
		data.selectImg = JA_Extranet_urlHost + $scope.imgUrls[$scope.current].pictureUrlForBytes;
		$scope.waitUploadImgUrl = data.selectImg;
		$scope.jsonData = {
			'applyNo': data.applyNo, //  '751122018600008'
			'stuffId': '',
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
				//				urlHost
				$.device.httpUpload(JA_Extranet_urlHost + '/aci/declare/uploadStuff.do', "file", "C:/waitUploadImg.jpg",
					$scope.jsonData,
					function(result) {
						alert('上传成功');
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
	$scope.alertConfirm = function() {
		if($scope.noDzzzData == true) {
			$location.path('/uploadMethod');
		}
	}
	$scope.alertCancel = function() {
		if($scope.noDzzzData == true) {
			$location.path('/uploadMethod');
		}
	}
	$scope.prevStep = function() {
		$location.path("/materialList");
	}

});
app.controller("takePhotoController", function($scope, $route, $http, $rootScope, $location, data, $timeout, $routeParams, appFactory) {
	var name = data.itemName;
	$scope.itemName = name;
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
				'stuffId': "",
				'stuffCode': data.stStuffCode,
				'stuffName': data.stStuffName,
				'stuffType': 0,
				'stuffStatus': 0
			};
			$scope.jsonData1 = JSON.stringify($scope.jsonData1);
			//			urlHost
			//			JA_Extranet_urlHost
			$.device.httpUpload(preUrl_JA_intranet + "/aci/declare/uploadStuff.do", "file", $scope.UData,
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
app.controller("finishController", function($scope, $route, $http, $location, $rootScope, data, $timeout, appFactory) {
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();
	$scope.nextText = "完成";
	var name = data.itemName;
	$scope.itemName = name;
	$scope.isLoading = true;
	// 调用高拍仪 参数:(width, height, x, y)
	$.device.cmCaptureShow(700, 480, 200, 375);
	// cmCaptureSelectRect用于聚焦, 参数1920是拍摄材料, 100是拍摄身份证
	$.device.cmCaptureSelectRect(0, 0, 1920, 1920);
	$scope.isFinish = false;
	$scope.prevText = "返回";
	$scope.nextText = "确认上传";
	// 拍照
	var imgHTML = "";
	var imgIndex = 0;
	$scope.next = function() {
		$scope.isLoading = false;
		var scanImg = $.device.cmCaptureCaptureUrl();
		//		$.ajax({
		//			url: urlHost+'/aci/declare/uploadStuff.do',
		//			type: "post",
		//			dataType: "json",
		//			data: {
		//				'applyNo': data.applyNo, //   '751122018600008'
		//				'stuffId': "",
		//				'stuffCode': data.stStuffCode,
		//				'stuffName': data.stStuffName,
		//				'stuffType': 0,
		//				'stuffStatus': 0,
		//				'file': scanImg
		//			},
		//			success: function(dataJson) {
		//				$scope.isLoading = true;
		//				data.uploadStuffId = data.stStuffId;
		//				if(data.listImg.length < 1) {
		//					data.currentIndex++; // 没有材料列表时   文件下标+1 
		//				}
		//				data.isUpload.push({
		//					index: data.currentIndex,
		//					stuffName: data.stStuffName,
		//					img: scanImg,
		//					method: "高拍仪"
		//				});
		//				data.fileName.push('扫描文件');
		//				imgHTML += '<div class="img" id="' + data.uploadStuffId + '"><img src="' + scanImg + '" width="150" height="200" /></div>';
		//				$('.imgBox').html(imgHTML);
		//				$scope.isFinish = true;
		//			},
		//			error: function(err) {
		//				layer.msg("上传材料失败")
		//			}
		//		});

		/**
		 * function: ajax上传文件流
		 * date: 2019.11.28
		 * author: lihuaxi
		 */
		$scope.jsonData1 = {
			'applyNo': data.applyNo, //   '751122018600008'
			'stuffId': "",
			'stuffCode': data.stStuffCode,
			'stuffName': data.stStuffName,
			'stuffType': 0,
			'stuffStatus': 0,
		};
		$scope.jsonData1 = JSON.stringify($scope.jsonData1);
		//http://218.202.254.222
		$.device.httpUpload(JA_Extranet_urlHost + '/aci/declare/uploadStuff.do', "file", scanImg, $scope.jsonData1,
			function(result) {
				$scope.isLoading = true;
				data.uploadStuffId = data.stStuffId;
				if(data.listImg.length < 1) {
					data.currentIndex++; // 没有材料列表时   文件下标+1 
				}
				data.isUpload.push({
					index: data.currentIndex,
					stuffName: data.stStuffName,
					img: scanImg,
					method: "高拍仪"
				});
				data.fileName.push('扫描文件');
				imgHTML += '<div class="img" id="' + data.uploadStuffId + '"><img src="' + scanImg + '" width="150" height="200" /></div>';
				$('.imgBox').html(imgHTML);
				$scope.isFinish = true;
			},
			function(webexception) {
				layer.msg("上传材料失败");
			});
	};
	// 完成拍照
	$scope.finishUpload = function() {
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
app.controller("materialListController", function($scope, $route, $http, $rootScope, $location, data, $timeout, appFactory) {
	removeAnimate($('#wrapper'))
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$scope.nextText = "提交办件";
	//必传材料列表
	$scope.mustUpload = [];
	$scope.current = 0;
	// 获取材料列表  	
	var fConfig = {
		itemCode: data.itemTenNo, //"0105128001", //
		jsonpCallback: "JSON_CALLBACK",
	};
	//	urlHost + '/aci/declare/getItemStuffList.do'
	$http.jsonp(JA_Extranet_urlHost + '/aci/declare/getItemStuffList.do', {
		params: fConfig
	}).success(function(dataJson) {
		console.log(dataJson);
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
						console.log(data.isUpload[i].stuffName + "=====" + data.listImg[j].stuffName);
						if(data.isUpload[i].stuffName == data.listImg[j].stuffName) {
							data.listImg[data.isUpload[i].index].upload = false;
							data.listImg[data.isUpload[i].index].upload2 = true;
						}
					}
				}
			}
		}
		$scope.listImg = data.listImg;
		addAnimate($('#wrapper'))
	}).error(function() {
		console.log('queryStuffList error')
	})
	console.log(data.isUpload);
	console.log(data.listImg);
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
		for(var i = 0; i < data.isUpload.length; i++) {
			if(index == data.isUpload[i].index) {
				data.isUpload[i] = "";
				data.listImg[index].upload = true;
				data.listImg[index].upload2 = false;
			}
		}
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
	$scope.prevStep = function() {
		$location.path("/info");
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
app.controller("materialViewController", function($scope, $http, $location, $rootScope, data, appFactory) {
	$scope.stuffList = []; //所有图片的容器
	$scope.showImgList = []; //显示图片的容器
	$scope.currentPage = 1; //当前页
	$scope.totalPages = 1; //总页数
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$scope.prevText = "返回";
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
		console.log($scope.showImgList);
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
app.controller("infoFinishController", function($scope, $route, $rootScope, $http, $location, data, $timeout, appFactory) {
	$scope.allName = data.itemName + '--' + data.statusName;
	var lodop = $.device.printGetLodop();
	var name = data.itemName;
	$scope.itemName = name;
	$scope.nextText = "返回首页";
	// 生成办件编码
	$scope.submitApply = function() {
		$scope.finishData = {
			applyNo: data.applyNo, // '751122018600008'
			subItemCodes: '', // data.itemId
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost1 + '/aci/declare/submitApply.do', {
			params: $scope.finishData
		}).success(function(dataJson) {
			console.log(dataJson);
			$scope.print();
		}).error(function(e) {
			console.log(e)
		});
	}

	$scope.submitApply();
	$scope.applyNo = data.applyNo;
	$scope.statusText = '打印凭证';
	//	var code = "http://zwdtmob.sh.gov.cn/zwdtSW/spnetQuery/bjxqxg.jsp?ST_WF_ID=" + data.applyNo + "&IdOrCode=" + encodeURI(data.username);
	var code = "http://xzfwzx.jingan.gov.cn:8080/ac/aci/ac-self-front/declare/index.html#/qrCode?applyNo=" + data.applyNo;
	var date = new Date();
	var month = date.getMonth() + 1;
	// 打印凭条
	$scope.print = function() {
		lodop.ADD_PRINT_BARCODE(28, 34, 307, 47, "128A", data.applyNo);
		lodop.ADD_PRINT_BARCODE(28, 550, 168, 146, "QRCode", code);
		lodop.ADD_PRINT_TEXT(164, 575, 200, 50, "进度查询");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(170, 260, 250, 50, "申报提交确认单");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		lodop.ADD_PRINT_TEXT(250, 28, 600, 30, "统一审批编码：" + data.applyNo);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(280, 28, 600, 30, "申请单位/申请人：" + data.username);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(310, 28, 600, 30, "申请事项：" + $scope.allName);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(370, 28, 450, 30, "请您携带办理材料至静安区行政服务中心进行办理。");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(400, 551, 168, 30, date.getFullYear() + "年" + month + "月" + date.getDate() + "日");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(480, 28, 670, 30, "亲民提示：您可凭统一审批编码至“一网通办”总门户或扫描右侧二维码查询办件进度。");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
//		lodop.PRINT_DESIGN();
//		lodop.PREVIEW();
		lodop.PRINT();
	};
	$scope.goHome = function() {
		$location.path("/start");
	}
});

app.controller("qrCodeController", function($scope, $route, $http, $location, data, $timeout) {
	$scope.applyNo = $location.search().applyNo;
	$scope.isLoding = false;
	$scope.process = function() {
		var pConfig = {
			stApplyNo: $scope.applyNo,
//			stApplyNo: '001023920000877',
			jsonpCallback: "JSON_CALLBACK"
		}
		$http.jsonp(JA_Extranet_urlHost + '/aci/autoterminal/eventquery/getApplyInfoByStApplyNo.do', {
			params: pConfig
		}).success(function(dataJson) {
			console.log(dataJson);
			$scope.applyNo = dataJson.stApplyNo;
			$scope.itemName = dataJson.stItemName;
			$scope.name = dataJson.stName || dataJson.stUnit;
			$scope.date = dataJson.stApplyStr;
			$scope.FinalState = dataJson.stFinalState;
			$scope.isLoding = true;
		}).error(function(err) {
			console.log('getApplyInfoByStApplyNo error');
		});
	}
	$scope.process();
});