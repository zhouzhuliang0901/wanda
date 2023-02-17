function removeAnimate(ele) {
	//	$(ele).css({
	//		"transform": "translateY(0px)",
	//		"top": 0
	//	}).removeClass('transformto')
}

function addAnimate(ele) {
	$(ele).css({
		'margin-top': '300px',
		'opacity': '0'
	});
	$(ele).animate({
		marginTop: '0',
		opacity: '1'
	}, 1000);
}
app.controller("listController", function($scope, $route, $location, $http, $rootScope, data) {
	removeAnimate($('#wrapper'));
	data.areaCode = "SH00LG";
	$scope.current = 0;
	$scope.searchType = ["个人", "法人", "部门"];
	$scope.current = data.matindex || 0;
	$scope.itemName = [];
	$scope.currentMatters = [];
	$scope.matterVal = '';
	$scope.organCode = data.ocode || null;
	$scope.itemName = "";
	$scope.type = "1";
	$scope.isLoding = false;
	addAnimate($('#wrapper'))
	//
	//阅签选择
	$scope.getMatterCon = function(index, type) {
		$scope.current = index;
		switch(type) {
			case "部门":
				$scope.itemName = "";
				$scope.itemType = "";
				$scope.getDepartment();
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
		$http.jsonp(urlHost + '/selfapi/declare/getItemThemeBySq.do', {
			params: {
				jsonpCallback: "JSON_CALLBACK",
				areaCode: data.areaCode,
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
			addAnimate($('#wrapper'))
		}).error(function(err) {
			console.log("getItemTheme error");
		});
	}
	//法人
	$scope.corporationItem = function() {
		removeAnimate($('#wrapper'))
		$scope.deptindex = 0;
		$scope.current = 1;
		$http.jsonp(urlHost + '/selfapi/declare/getItemThemeBySq.do', {
			params: {
				jsonpCallback: "JSON_CALLBACK",
				areaCode: data.areaCode,
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
	//获取所有部门
	$scope.getDepartment = function() {
		removeAnimate($('#wrapper'))
		$scope.itemName = '';
		$scope.current = 2;
		$scope.deptindex = 1;
		$scope.dept = true;
		var organConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode: data.areaCode
		};
		$http.jsonp(urlHost + '/selfapi/declare/getDeptInAreaBySq.do', {
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
			areaCode: data.areaCode,
			themeCode: code,
			type: type
		};
		$http.jsonp(urlHost + '/selfapi/declare/getItemInThemeBySq.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.data;
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
			areaCode: data.areaCode,
			themeCode: "",
			type: "1"
		};
		$http.jsonp(urlHost + '/selfapi/declare/getItemInThemeBySq.do', {
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
			areaCode: data.areaCode,
			themeCode: "",
			type: "2"
		};
		$http.jsonp(urlHost + '/selfapi/declare/getItemInThemeBySq.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.data;
			addAnimate($('#wrapper'))
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	}
	//通过部门id获取部门事项
	$scope.getItemByOrganCode = function(code) {
		removeAnimate($('#wrapper'))
		$scope.current = 2;
		$scope.dept = true;
		$scope.deptindex = 1;
		var oConfig = {
			organCode: code,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/selfapi/declare/getItemListByOrganCodeForPageBySq.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.itemSetList;
			addAnimate($('#wrapper'))
		}).error(function() {
			console.log('getItemListByOrganCodeForPage error')
		})
	};

	$scope.$on('$viewContentLoaded', function() {
		$scope.personalItem();
		$scope.dept = false;
	});

	$scope.isScroll = function() {

		//		new iScroll("wrapper", {
		//			vScrollbar: true,
		//			hScrollbar: false,
		//			hideScrollbar: false,
		//			bounce: true,
		//			hScroll: false,
		//			checkDOMChanges: true
		//		});
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
			$scope.getItemListByType(name, $scope.type);
			$scope.dept = true;
		}
	}
	$scope.toMaterials = function(itemName, organCode, organName, itemNo) {
		removeAnimate($('#wrapper'))
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
		addAnimate($('#wrapper'))
	};
	$scope.prevStep = function() {
		$location.path('/start');
	}
});
app.controller("matterController", function($scope, $route, $http, $location, data, $timeout, $rootScope) {
	removeAnimate($('#wrapper'))
	$scope.allName = data.itemName;
	var name = data.itemName;
	$scope.itemName = name;;
	$scope.getMatterCond = function() {
		var oConfig = {
			itemNo: data.itemNo,
			deptCode: data.organCode,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/selfapi/declare/getWindowItemStatusListBySq.do', {
			params: oConfig
		}).success(function(dataJson) {
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
		data.matindex = 1;
		$location.path("/list");

	}

	$scope.isScroll = function() {

	};
	$scope.isScroll();
});
app.controller("guidelineController", function($scope, $route, $http, $location, $sce, $rootScope, data, $timeout) {
	try {
		window.external.URL_CLOSE();
	} catch(e) {

	}
	removeAnimate($('.main2'))
	var name = data.itemName;
	$scope.itemName = name;
	$scope.description = data.description;
	$scope.statusName = data.statusName;
	$scope.ItemStuffList = "";
	$scope.isAlert = false;
	$scope.isLoading = true;
	$scope.concel = "false";

	$scope.charge_flagList = charge_flag;
	$scope.materialnecessityList = materialnecessity;
	$scope.materialformatList = materialformat;
	$scope.submittypeList = submittype;
	$scope.result_typeList = result_type;
	$scope.guideInfo = "";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.getGuieInfo = function() {
		var oConfig = {
			stId: data.itemId,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/selfapi/selfDeclare/getItemDetailByIDForSQ.do', {
			params: oConfig
		}).success(function(dataJson) {
			console.log(dataJson);
			$scope.isLoading = false;
			$scope.guideInfo = dataJson.custom;
			//办事指南二维码地址
			$scope.codeUrl = "http://zwdt.sh.gov.cn/govPortals/bsfw/item/" + $scope.guideInfo.auditfwzn.item_id;
			console.log($scope.codeUrl);
			var qrcode = new QRCode("code", {
				text: $scope.codeUrl,
				width: 200,
				height: 200,
				correctLevel: 0,
				render: "table"
			});
		}).error(function() {
			console.log('getGuideInfoByZhallId error')
		});
	};
	$scope.getGuieInfo();
	$scope.print = function() {
		$scope.isAlert = true;
		$scope.msg = "正在打印...";
		var lodop = $.device.printGetLodop('');
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
		$timeout(function() {
			$scope.isAlert = false;
		}, 3000);
	};

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
		$location.path('/matter');
	}
});
