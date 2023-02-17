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
	$scope.areaCode = $location.search().areaCode;
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
	$scope.lastStep = function(){
		$scope.isLoding = false;
		$scope.personalItem();
		$scope.dept = false;
		$scope.prev = false;
	}
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
		$http.jsonp(urlHost + '/aci/declare/getThemeInStreet.do', {
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
		$http.jsonp(urlHost + '/aci/declare/getItemOfThemeInStreet.do', {
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
			areaCode:data.areaCode
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
	if(data.areaCode == "SH00SH"){
		$scope.codeUrl = "http://zwdt.sh.gov.cn/govPortals/bsfw/findBsfw.do?_organName_=&_organCode_=&_organType_=&_itemId=" + SHCODE + "&_itemType=审批&_stSubitemId=" + data.itemId;
	}else{
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