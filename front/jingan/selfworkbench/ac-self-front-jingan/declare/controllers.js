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
	$scope.choiceType = function(type) {
		$location.path(type)
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
app.controller("onlineHandleController", function($scope, $location, $http, data, $rootScope, $timeout, $interval, appFactory) {
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
	$scope.choiceType = function(type) {
		$location.path(type)
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
app.controller("oneThingOneTimeController", function($scope, $http, $location, $timeout, $rootScope, $sce, appFactory) {
	$scope.applyUrl = "http://zwdt.sh.gov.cn/govPortals/column/ot/onething.html";
	if(window.innerWidth == 1920) {
		//window.external.URL_OPEN(200, 150, 1500, 750, $scope.applyUrl);
		window.external.URL_EDGE_OPEN(200, 150, 1500, 750, $scope.applyUrl)
	} else if(window.innerWidth == 1366) {
		window.external.URL_OPEN(0, 70, 1366, 600, $scope.applyUrl);
	} else if(window.innerwidth == 1280) {
		window.external.URL_OPEN(0, 120, 1280, 750, $scope.applyUrl);
	}
	// ??????url???angular?????? ????????????
	$scope.prevStep = function() {
		try {
			window.external.URL_CLOSE();
			window.external.URL_EDGE_CLOSE();
		} catch(e) {

		}
		$location.path("/main");
	}
	$scope.goHome = function() {
		try {
			window.external.URL_CLOSE();
			window.external.URL_EDGE_CLOSE();
		} catch(e) {
			//TODO handle the exception
		}
		$.device.Camera_Hide();
		$.device.Camera_UnLink();
		$.device.cmCaptureHide();
		$.device.idCardClose();
		//$.device.qrCodeClose();
		$.device.officeClose();

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
	$scope.searchType = ["??????", "??????", "??????"];
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
	//????????????
	$scope.getMatterCon = function(index, type) {
		$scope.current = index;
		switch(type) {
			case "??????":
				$scope.itemName = "";
				$scope.itemType = "";
				$scope.getDepartment();
				$scope.dept = true;
				break;
			case "??????":
				$scope.isLoding = false;
				$scope.type = "2";
				$scope.itemName = "";
				$scope.itemType = "";
				$scope.corporationItem();
				$scope.dept = false;
				break;
			case "??????":
				$scope.isLoding = false;
				$scope.type = "1";
				$scope.itemName = "";
				$scope.itemType = "";
				$scope.personalItem();
				$scope.dept = false;
		}
	};
	//??????
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
			console.log('???????????????????????????');
			console.log(dataJson);
			$scope.itemType = dataJson.data;
			$scope.itemType.unshift({
				itemTypeCode: "??????",
				itemTypeName: "000",
				type: "1"
			});
			$scope.isLoding = true;
			addAnimate($('#wrapper'))
		}).error(function(err) {
			console.log("??????????????????????????????");
		});
	}
	//??????
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
			console.log('???????????????????????????');
			console.log(dataJson);
			$scope.itemType = dataJson.data;
			$scope.itemType.unshift({
				itemTypeCode: "??????",
				itemTypeName: "000",
				type: "2"
			});
			$scope.isLoding = true;
			addAnimate($('#wrapper'))
		}).error(function(err) {
			console.log("??????????????????????????????");
		});
	}
	//??????????????????
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
			console.log('??????????????????');
			console.log(dataJson);
			$scope.itemName = dataJson.organSetList;
			addAnimate($('#wrapper'))
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	};
	//?????????????????????
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

	//??????????????????
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
			console.log('??????????????????-->??????');
			console.log(dataJson);
			$scope.itemName = dataJson.data;
			addAnimate($('#wrapper'))
		}).error(function() {
			console.log('????????????????????????-->?????? ??????');
		})
	}
	//??????????????????
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
			console.log('???????????????????????????-->??????');
			console.log(dataJson);
			$scope.itemName = dataJson.data;
			addAnimate($('#wrapper'))
		}).error(function() {
			console.log('????????????????????????-->?????? ??????');
		})
	}
	//????????????
	$scope.getSearchMatter = function(current) { // ????????????
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
			layer.msg('?????????????????????');
		}
	};
	//????????????id??????????????????
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
			if(code == "??????" && type == "1") {
				console.log(1);
				$scope.getPersonItemList();
				$scope.dept = true;
				$scope.deptindex = 1;
			} else if(code == "??????" && type == "2") {
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
		$location.path("/main");
	}
});
app.controller("citylistController", function($scope, $route, $location, $http, $rootScope, data, appFactory) {
	$scope.current = 0;
	$scope.searchType = ["??????", "??????", "??????"];
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
	//????????????
	$scope.getMatterCon = function(index, type) {
		$scope.current = index;
		switch(type) {
			case "??????":
				$scope.itemName = "";
				$scope.itemType = "";
				$scope.getDepartment();
				$scope.dept = true;
				break;
			case "??????":
				$scope.isLoding = false;
				$scope.type = "2";
				$scope.itemName = "";
				$scope.itemType = "";
				$scope.corporationItem();
				$scope.dept = false;
				break;
			case "??????":
				$scope.isLoding = false;
				$scope.type = "1";
				$scope.itemName = "";
				$scope.itemType = "";
				$scope.personalItem();
				$scope.dept = false;
		}
	};
	//??????
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
				itemTypeCode: "??????",
				itemTypeName: "000",
				type: "1"
			});
			$scope.isLoding = true;
		}).error(function(err) {
			console.log("getItemTheme error");
		});
	}
	//??????
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
				itemTypeCode: "??????",
				itemTypeName: "000",
				type: "2"
			});
			$scope.isLoding = true;
			addAnimate($('#wrapper'))
		}).error(function(err) {
			console.log("getItemTheme error");
		});
	}
	//??????????????????
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
	//??????????????????
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
	//??????????????????
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
	//?????????????????????
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

	//????????????
	$scope.getSearchMatter = function(current) { // ????????????
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
			layer.msg('?????????????????????');
		}
	};
	//????????????id??????????????????
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
			if(code == "??????" && type == "1") {
				console.log(1);
				$scope.getPersonItemList();
				$scope.dept = true;
				$scope.deptindex = 1;
			} else if(code == "??????" && type == "2") {
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
			$scope.guideInfo.stPromiseTime = $sce.trustAsHtml("<p>??????????????????:" + $scope.guideInfo.stPromiseTime + "</p><p>??????????????????:" + $scope.guideInfo.stLegalTime + "</p>");
			$scope.clRange = dataJson.guide.clRange;
			addAnimate($('.scrollBox2'))
		}).error(function() {
			console.log('getGuideInfoByZhallId error')
		});
	};
	//???????????????????????????
	var itemStr = data.itemNo;
	itemStr = itemStr.substring(0, itemStr.length - 1);
	var SHCODE = "SH00HK" + itemStr + 1;
	$scope.codeUrl = "http://zwdt.sh.gov.cn/govPortals/bsfw/findBsfw.do?_organName_=?????????&_organCode_=SH00JA&_organType_=other&_itemId=" + SHCODE + "&_itemType=??????&_stSubitemId=" + data.itemId;
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
		$scope.msg = "????????????...";
		let lodop = $.device.printGetLodop();
		let style = "<style>table tr>td{text-align:center}table tr>td:nth-child(1){text-align:left}" +
			"table tr>td:nth-child(1){width:920px;}table tr>td:nth-child(2){display: none;}" +
			"table tr>th:nth-child(2){display: none;}table tr>td:nth-child(3){width:200px;margin-left: 20px;}" +
			"table tr>td:nth-child(4){width:130px;margin-left: 20px;}table tr>td:nth-child(5){width:150px;margin-left: 20px;}</style>";
		let html = style + "<body>" + document.getElementById("lodop").innerHTML + "</body>";
		lodop.ADD_PRINT_TEXT(50, 0, "100%", 100, $scope.itemName + "--????????????");
		lodop.SET_PRINT_STYLEA(0, "Alignment", 2);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		lodop.ADD_PRINT_HTM(150, 0, "100%", "100%", html);
		lodop.PRINT();
		$timeout(function() {
			$scope.isAlert = false;
		}, 3000);
	};

	//??????
	$scope.next = function() {
		// ??????????????????????????????????????????
		var oConfig = {
			itemCode: data.itemTenNo,
			jsonpCallback: "JSON_CALLBACK",
		};
		// ???????????????????????????????????????, ???????????????????????????: ??????: urlHost
		$http.jsonp(JA_Extranet_urlHost + '/aci/declare/getItemAppplyInfo.do', {
			params: oConfig
		}).success(function(dataJson) {
			console.log(dataJson.data.molder);
			if(dataJson) {
				if(dataJson.data.molder == 1) { // ???1 ????????????????????????????????????
					localStorage.applyUrl = dataJson.data.itemApplyUrl;
					$location.path("/apply");
				} else { // ???0?????????????????????
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
	$scope.prev_btn_info = '?????????';
	$scope.next_btn_info = '?????????';
	// ??????????????????

	//	  //??????
	//      var PUBLIC_KEY = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC68dB76JHZnops1bDHR4a2tBz+I09j2aWOSzOeBVGbJs4HI0XwPCfFVA3q2dLNpQEdDEHNwDg3M/y50uOygp1cyXG12Gy1S+QcqSl0BgGOH39aoJ5cyRkojLSKkzol670sfErX049xQvuq77wFznvHnfQzHgQkEQLqJnkYrFvr2QIDAQAB';
	//      //??????
	//      var PRIVATE_KEY = 'MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALrx0HvokdmeimzVsMdHhra0HP4jT2PZpY5LM54FUZsmzgcjRfA8J8VUDerZ0s2lAR0MQc3AODcz/LnS47KCnVzJcbXYbLVL5BypKXQGAY4ff1qgnlzJGSiMtIqTOiXrvSx8StfTj3FC+6rvvAXOe8ed9DMeBCQRAuomeRisW+vZAgMBAAECgYB5lRGRtLU+woSmuefqA1PS+ZstkcttVjz9KV2dtTnY3Uj7jW5MCuOWy87tYdNfGaR6vuEBLrWg+XexZz3deGNcu7t0B4IRHu/54RPPXQszvoN0AcqcDaQ/sUHKE4MDwX5ij5wwA+V2TOUducBGH9+5or5N/IUzdLtKcnWlKoieMQJBANu2wk9xknXaH+dg4x1dVDzYpG1+rix8qA+dok2vF1twS7sdJwhngS7nsMSBjAIETGd2w94ZBz6VBGA9kx1C7K0CQQDZ0ZuhHPV70GhSG2ZyOI4hGI+lEAsCQe7nEKclK2U3oXZR2BNCAj+AZwO1GXqPguC+c5SBwbTTciMGysIwoNVdAkAsX7jWuqVN0APpgxPbdmHw+AAdbRxYN8TpgnipH9ejzAY/gB/F/sGEa56z0UYpkhysOLxOOtfPt+DuXwE7Q6zxAkEAzeCIsOemP7jkYXb0hdFexXlpjCJ1xVR8cnoTAdbafJJoO0N4MFPfoYW8w1epuCuEMX8dRufH+nNPGARdN4lNIQJAVtROB3lmtVYizbeMEEHFSIT3hEUQBTIcFekRgXcX4XYzv+tH35VKWjIH0IOLUvj4FOuTAuwdHs6Ux8HeHM4hHg==';
	//      //??????????????????
	//      var encrypt = new JSEncrypt();
	//		  //encrypt.setPrivateKey('-----BEGIN RSA PRIVATE KEY-----'+PRIVATE_KEY+'-----END RSA PRIVATE KEY-----');
	//      encrypt.setPublicKey('-----BEGIN PUBLIC KEY-----' + PUBLIC_KEY + '-----END PUBLIC KEY-----');
	//      var encrypted = encrypt.encrypt();
	//      console.log('???????????????:%o', encrypted);
	//      //??????????????????
	//      var decrypt = new JSEncrypt();
	//		//decrypt.setPublicKey('-----BEGIN PUBLIC KEY-----' + PUBLIC_KEY + '-----END PUBLIC KEY-----');
	//      decrypt.setPrivateKey('-----BEGIN RSA PRIVATE KEY-----'+PRIVATE_KEY+'-----END RSA PRIVATE KEY-----');
	//      var uncrypted = decrypt.decrypt(encrypted);
	//      console.log('???????????????:%o', uncrypted);
	//
	$scope.applyUrl = "https://zwdtuser.sh.gov.cn/uc/login/login.jsp?self=my&redirect_uri=http://ywtb.sh.gov.cn:18018" + localStorage.applyUrl;
	console.log($scope.applyUrl);
	window.external.URL_OPEN(200, 150, 1500, 750, $scope.applyUrl);

	// ??????url???angular?????? ????????????
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
	// ????????????????????????
	$scope.scanIdcard = function() {
		$location.path('/idCard');

//		????????????
//		data.idCardName = "??????";
//		data.idCardNum = "31010919810727205X";
//		$location.path("/info");
	}
	// ???????????????
	$scope.citizen = function() {
		$location.path('/citizen');
	}
	//????????????
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

	//	data.idCardName = "?????????";
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
	// ???????????????
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
				$scope.msg = "?????????????????????";
			}
		});
	}, function(err) {
		console.log("????????????????????????" + err)
	});
	$scope.prevStep = function() {
		$location.path("/select");
	}
});
app.controller("infoController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
	//	removeAnimate($('.scrollBox2'))
	$scope.concel = 'false'; // ??????alert?????????????????????
	$.device.Camera_Hide();
	$scope.fullItemName = data.itemName + "--" + data.statusName;
	var name = data.itemName;
	$scope.itemName = name;

	$scope.list = [{
			obj: '??????'
		},
		{
			obj: '??????'
		}
	];
	$scope.targetTypeName = '??????';
	$scope.targetTips = '?????????????????????';
	$scope.targetName = '???????????????';
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//	addAnimate($('.scrollBox2'))
	// $wathc ?????? targetTypeName
	$scope.$watch('targetTypeName', function(newValue, oldValue) {
		$scope.targetTypeName == '??????' ? $scope.targetTips = '?????????????????????' : $scope.targetTips = '????????????????????????';
		$scope.targetTypeName == '??????' ? $scope.targetName = '???????????????' : $scope.targetName = '????????????';
		$scope.targetTypeName == '??????' ? $('#targetName').val(data.idCardName) : $('#targetName').val('');
		$scope.targetTypeName == '??????' ? $('#targetNo').val(data.idCardNum) : $('#targetNo').val('');
	});

	$('#username').val(data.idCardName);
	$('#licenseNo').val(data.idCardNum);
	if(data.mobile) {
		$('#mobile').val(data.mobile);
	}

	// ????????????
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
			if($scope.targetTypeName == '??????') {
				if($('#targetName').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "????????????????????????????????????";
					return;
				}
				if(!checkIdCard($('#targetNo').val())) {
					$scope.isAlert = true;
					$scope.msg = "????????????????????????????????????";
					return;
				}
				if($('#username').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "????????????????????????????????????";
					return;
				}
				if(!checkIdCard($('#licenseNo').val())) {
					$scope.isAlert = true;
					$scope.msg = "????????????????????????????????????";
					return;
				}

				if(!isPhoneAvailable($('#mobile').val())) {
					$scope.isAlert = true;
					$scope.msg = "??????????????????????????????";
					return;
				}
			} else if($scope.targetTypeName == '??????') {
				if($('#targetName').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "?????????????????????????????????";
					return;
				}
				if($('#targetNo').val().length < 17) {
					$scope.isAlert = true;
					$scope.msg = "?????????????????????????????????????????????";
					return;
				}
				if($('#username').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "????????????????????????????????????";
					return;
				}
				if(!checkIdCard($('#licenseNo').val())) {
					$scope.isAlert = true;
					$scope.msg = "????????????????????????????????????";
					return;
				}
				if(!isPhoneAvailable($('#mobile').val())) {
					$scope.isAlert = true;
					$scope.msg = "??????????????????????????????";
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
			'source': '????????????',
			'departCode': data.organCode,
			'departName': data.organName,
			jsonpCallback: "JSON_CALLBACK",
		};
		// ???????????????????????????
		//		urlHost
		$http.jsonp(JA_Extranet_urlHost + '/aci/declare/saveApply.do?' + from, {
			params: fConfig
		}).success(function(dataJson) {
			$scope.flag = true;
			// ???????????????applyNo???????????????dataJson.data.applyNo
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
	//??????????????????
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
	// ??????????????????
	$scope.stStuffName = data.stStuffName;
	$scope.stuffNameShow = data.isShowStuffName;
	addAnimate($('.scrollBox2'))
	// ????????????
	$scope.scanPhoto = function() {
		$location.path('/finish');
	};
	// U?????????
	$scope.takePhoto = function() {
		layer.confirm("<em style='color:black'>" + '?????????????????????U??????' + "</em>", {
			btn: ['?????????U???', '?????????U???'] //??????
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
				layer.msg('??????????????????????????????');
			}, 20);
		});
	};
	// ???????????????
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
	$scope.prevText = "??????";
	$scope.nextText = "????????????";
	$scope.idCardNum = data.idCardNum;
	$scope.noDzzzData = false;

		$scope.currentPage = 1;
	$scope.totalPages = 1; //?????????
	$scope.previewImgList = []; //????????????
	$scope.emptyPreviewImgList = []; //?????????????????????
	$scope.totalList = [];
	$scope.url = JA_Extranet_urlHost;
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
		$scope.startPos = (current - 1) * 3; //0 -3  3 - 6
		$scope.endPos = $scope.startPos + 3;
		console.log($scope.totalList);
		$scope.emptyPreviewImgList = $scope.totalList.slice($scope.startPos, $scope.endPos);
		$scope.totalPages = Math.ceil($scope.totalList.length / 3);
		$scope.emptyPreviewImgList.length = 3;
		for(var i in $scope.emptyPreviewImgList) {
			if($scope.emptyPreviewImgList[i] != undefined) {
				$scope.previewImgList.push($scope.emptyPreviewImgList[i]);
			}
			$("#jq22").append('<img data-original="' + $scope.url + $scope.emptyPreviewImgList[i].pictureUrlForBytes + '" src="' + $scope.url + $scope.emptyPreviewImgList[i].pictureUrlForBytes + '" alt="">');
			$("#jq22").on('click', 'img' ,function(){
				$(this).addClass("imgStyle").siblings().removeClass("imgStyle");
				data.selectImg = $(this).attr('src');
			})
		}
		//????????????
		var viewer = new Viewer(document.getElementById('jq22'), {
			url: 'data-original',
		});
	}


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
		//					layer.msg("??????????????????????????????????????????!");
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
						//						layer.msg("??????????????????????????????????????????!");
						$scope.isAlert = true;
						$scope.noDzzzData = true;
						$scope.msg = '????????????,???????????????????????????!';
						$timeout(function() {
							$location.path('/uploadMethod');
						}, 1000);
					} else {
						$scope.imgUrls = dataJson;
						$scope.totalList = $scope.imgUrls.slice(0, $scope.imgUrls.length);
						$scope.currentList();
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


	$scope.goNext = function() {
		layer.msg("????????? ?????????");
		//		data.selectImg = urlHost + $scope.imgUrls[$scope.current].pictureUrlForBytes;
		//data.selectImg = JA_Extranet_urlHost + $scope.imgUrls[$scope.current].pictureUrlForBytes;
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
		$.device.httpDownload( // ???????????????????????????????????????????????????????????????????????????		urlHost +
			$scope.waitUploadImgUrl,
			"C:\\waitUploadImg.jpg",
			//?????????????????????
			function(bytesCopied, totalBytes) {
				console.log(bytesCopied + "," + totalBytes);
			},
			function(result) {
				//?????????????????????????????????
				//				urlHost
				$.device.httpUpload(JA_Extranet_urlHost + '/aci/declare/uploadStuff.do', "file", "C:/waitUploadImg.jpg",
					$scope.jsonData,
					function(result) {
						alert('????????????');
						layer.msg("????????????");
						data.isUpload.push({
							index: data.currentIndex,
							stuffName: data.stStuffName,
							img: $scope.waitUploadImgUrl,
							method: "????????????"
						});
						$timeout(function() {
							$location.path('/materialList');
						}, 1000);
					},
					function(webexception) {
						layer.msg("????????????");
					});
			},
			function(webexception) {
				alert("??????????????????");
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
	$scope.prevText = "??????";
	$scope.nextText = "????????????";
	try {
		$.device.fileOpen(function(value) {
			$timeout(function() {
				$scope.UData = value; //"E://123.txt";//
			}, 100)
		});
	} catch(e) {
		$timeout(function() {
			layer.msg("?????????U????????????");
		}, 100)
		$location.path("/uploadMethod");
	}
	// ??????
	$scope.highCapture = function() {
		if($scope.UData == null) {
			layer.msg("????????????U?????????");
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
					layer.msg("????????????");
					data.fileName.push($scope.UData);
					data.isUpload.push({
						index: data.currentIndex,
						stuffName: data.stStuffName,
						fileName: $scope.UData,
						method: "U?????????"
					});
					$timeout(function() {
						$location.path('/materialList');
					}, 100);
				},
				function(webexception) {
					layer.msg("????????????");
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
	$scope.nextText = "??????";
	var name = data.itemName;
	$scope.itemName = name;
	$scope.isLoading = true;
	// ??????????????? ??????:(width, height, x, y)
	$.device.cmCaptureShow(700, 480, 200, 375);
	// cmCaptureSelectRect????????????, ??????1920???????????????, 100??????????????????
	$.device.cmCaptureSelectRect(0, 0, 1920, 1920);
	$scope.isFinish = false;
	$scope.prevText = "??????";
	$scope.nextText = "????????????";
	// ??????
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
		//					data.currentIndex++; // ?????????????????????   ????????????+1
		//				}
		//				data.isUpload.push({
		//					index: data.currentIndex,
		//					stuffName: data.stStuffName,
		//					img: scanImg,
		//					method: "?????????"
		//				});
		//				data.fileName.push('????????????');
		//				imgHTML += '<div class="img" id="' + data.uploadStuffId + '"><img src="' + scanImg + '" width="150" height="200" /></div>';
		//				$('.imgBox').html(imgHTML);
		//				$scope.isFinish = true;
		//			},
		//			error: function(err) {
		//				layer.msg("??????????????????")
		//			}
		//		});

		/**
		 * function: ajax???????????????
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
					data.currentIndex++; // ?????????????????????   ????????????+1
				}
				data.isUpload.push({
					index: data.currentIndex,
					stuffName: data.stStuffName,
					img: scanImg,
					method: "?????????"
				});
				data.fileName.push('????????????');
				imgHTML += '<div class="img" id="' + data.uploadStuffId + '"><img src="' + scanImg + '" width="150" height="200" /></div>';
				$('.imgBox').html(imgHTML);
				$scope.isFinish = true;
			},
			function(webexception) {
				layer.msg("??????????????????");
			});
	};
	// ????????????
	$scope.finishUpload = function() {
		$timeout(function() {
			$.device.cmCaptureHide(); // ???????????????
			$location.path('/materialList');
		}, 20);
	};

	$scope.last = function() {
		$.device.cmCaptureHide(); // ???????????????
		$location.path('/materialList');
	}
});
app.controller("materialListController", function($scope, $route, $http, $rootScope, $location, data, $timeout, appFactory) {
	removeAnimate($('#wrapper'))
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$scope.nextText = "????????????";
	//??????????????????
	$scope.mustUpload = [];
	$scope.current = 0;
	// ??????????????????
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
		//?????????????????? ????????????
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
	// ????????????
	data.currentIndex++;
	$scope.toUploadMaterial = function(index, id, code, name) {
		data.stStuffId = id;
		data.stStuffCode = code;
		data.stStuffName = name;
		data.currentIndex = index;
		data.stuffImg = data.listImg[data.currentIndex];
		$location.path("/uploadMethod");
	}
	//????????????
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

	//??????
	$scope.view = function(index, code, name) {
		data.currentIndex = index;
		data.view = data.isUpload;
		$location.path("/materialView");
	}

	//????????????
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
					layer.msg("????????????????????????");
				}
			} else {
				layer.msg("????????????????????????");
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
	$scope.stuffList = []; //?????????????????????
	$scope.showImgList = []; //?????????????????????
	$scope.currentPage = 1; //?????????
	$scope.totalPages = 1; //?????????
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$scope.prevText = "??????";
	//??????????????????
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

	//?????????
	$scope.nextPage = function() {
		if($scope.currentPage < $scope.totalPages) {
			++$scope.currentPage;
			$scope.currentList();
		}
	};
	//?????????
	$scope.prevPage = function() {
		if($scope.currentPage > 1) {
			--$scope.currentPage;
			$scope.currentList();
			console.log($scope.showImgList);
		}
	};
	if($scope.stuffList[0].method === "?????????") {
		$scope.scanShow = true;
		$scope.upanShow = false;
	} else if($scope.stuffList[0].method === "U?????????") {
		$scope.scanShow = false;
		$scope.upanShow = true;
	} else if($scope.stuffList[0].method === "????????????") {
		$scope.scanShow = true;
		$scope.upanShow = false;
	}
	//????????????
	$scope.closeFlag = true;
	$scope.imgShow = function(imgUrl) {
		$scope.largeImg = imgUrl;
		$scope.closeFlag = !$scope.closeFlag;
	}
	//????????????
	$scope.open = function() {
		try {
			$.device.officeOpen($scope.stuffList[0].fileName);
		} catch(e) {
			layer.msg("??????????????????");
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
	$scope.nextText = "????????????";
	// ??????????????????
	$scope.submitApply = function() {
		$scope.finishData = {
			applyNo: data.applyNo, // '751122018600008'
			subItemCodes: '', // data.itemId
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost1 + '/aci/declare/submitApply.do', {
			params: $scope.finishData
		}).success(function(dataJson) {
			$scope.print();
		}).error(function(e) {
			console.log(e)
		});
	}

	$scope.submitApply();

	$scope.statusText = '????????????';
	//	var code = "http://zwdtmob.sh.gov.cn/zwdtSW/spnetQuery/bjxqxg.jsp?ST_WF_ID=" + data.applyNo + "&IdOrCode=" + encodeURI(data.username);
	var code = "http://xzfwzx.jingan.gov.cn:8080/ac/aci/ac-self-front/declare/index.html#/qrCode?applyNo=" + data.applyNo;
	var date = new Date();
	var month = date.getMonth() + 1;

	$scope.applyNo = data.applyNo;
	$scope.codeUrl = encodeURIComponent(encodeURIComponent(code));
	$scope.applyName = data.idCardName;

	/*
	 * ??????: ????????????lodop????????????, ?????????????????????, ?????????????????????
	 * ??????: 2020.09.28
	 **/
//	$scope.print = function() {
//		lodop.ADD_PRINT_BARCODE(28, 34, 307, 47, "128A", data.applyNo);
//		lodop.ADD_PRINT_BARCODE(28, 550, 168, 146, "QRCode", code);
//		lodop.ADD_PRINT_TEXT(164, 575, 200, 50, "????????????");
//		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
//		lodop.ADD_PRINT_TEXT(170, 260, 250, 50, "?????????????????????");
//		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
//		lodop.ADD_PRINT_TEXT(250, 28, 600, 30, "?????????????????????" + data.applyNo);
//		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
//		lodop.ADD_PRINT_TEXT(280, 28, 600, 30, "????????????/????????????" + data.username);
//		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
//		lodop.ADD_PRINT_TEXT(310, 28, 600, 30, "???????????????" + $scope.allName);
//		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
//		lodop.ADD_PRINT_TEXT(370, 28, 450, 30, "?????????????????????????????????????????????????????????????????????");
//		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
//		lodop.ADD_PRINT_TEXT(400, 551, 168, 30, date.getFullYear() + "???" + month + "???" + date.getDate() + "???");
//		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
//		lodop.ADD_PRINT_TEXT(480, 28, 670, 30, "?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????");
//		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
////		lodop.PRINT_DESIGN();
////		lodop.PREVIEW();
//		lodop.PRINT();
//	};


	/*
	 * ??????: ???????????????????????????
	 * ??????: 2020.09.28
	 * */
	$scope.print = function() {
		$scope.path = "D:\\pdfPrint.pdf";
		$scope.filePath = "D:/pdfPrint.pdf";
		$scope.pdfPrintUrl = "http://12.113.230.10:8080/ac/ext/ja/onLine/onLineCredential.do?stApplyNo=" + $scope.applyNo + "&itemName=" + $scope.itemName + "&applyName=" + $scope.applyName + "&url=" + $scope.codeUrl;
		$.device.httpDownload( // ???????????????????????????????????????????????????????????????????????????		urlHost +
			$scope.pdfPrintUrl,
			$scope.path,
			//?????????????????????
			function(bytesCopied, totalBytes) {
				console.log(bytesCopied + "," + totalBytes);
			},
			function(result) {
				$scope.isAlert = true;
				$scope.msg = '????????????, ?????????';
				$.device.pdfAdobeReaderPrint($scope.filePath);
			},
			function(webexception) {
				alert("??????????????????");
			}
		);
	};

	$scope.alertConfirm = function() {
		$scope.msg = '';
		$scope.isAlert = false;
	}

	$scope.goHome = function() {
		$.device.GoHome();
	}
});

app.controller("qrCodeController", function($scope, $route, $http, $location, data, $timeout) {
	$scope.applyNo = $location.search().applyNo;
	$scope.isLoading = false;
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
			$scope.isLoading = true;
		}).error(function(err) {
			console.log('getApplyInfoByStApplyNo error');
		});
	}
	$scope.process();
});