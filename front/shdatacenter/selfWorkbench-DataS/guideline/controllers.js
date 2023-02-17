function removeAnimate(ele){
	$(ele).css({"transform":"translateY(0px)","top":0}).removeClass('transformto')
}
function addAnimate(ele){
	$(ele).addClass('transformto').on("animationend",function(){
		$(ele).removeClass('transformto')
	})
}
app.controller("startController", function($scope, $location, $http, data, $rootScope, $timeout, $interval) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	data.ocode = "";
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
app.controller("mainController", function($scope, $location, $http, data, $rootScope, $timeout, $interval) {
	$scope.areaList = [];
	$scope.getAreaList = function() {
		var aConfig = {
			jsonpCallback: "JSON_CALLBACK"
		}
		$http.jsonp(urlHost+"/aci/declare/getAllAreaInShanghai.do", {
			params: aConfig
		}).success(function(data) {
			for(var i = 1; i < data.organSetList.length; i++) {
				$scope.areaList.push(data.organSetList[i]);
			}
		}).error(function() {
			console.log('getAreaList error');
		});
	}
	$scope.getAreaList();
	$scope.choice = function(areaName, areaCode) {
		data.areaName = areaName;
		data.areaCode = areaCode;
		$location.path("/list");
	}
	$rootScope.goHome = function() {
		window.external.URL_CLOSE();
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
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
app.controller("listController", function($scope, $route, $location, $http, $rootScope, data) {
	removeAnimate($('#wrapper'))
	$scope.current = 0;
	$scope.searchType = ["个人","法人","部门"];
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
	$scope.personalItem = function(){
		removeAnimate($('#wrapper'))
		$scope.deptindex = 0;
		$scope.current = 0;
		$http.jsonp(urlHost+'/aci/declare/getItemTheme.do',{
			params:{
				jsonpCallback: "JSON_CALLBACK",
				areaCode : "SH00JA",
				type:'1'
			}
		}).success(function(dataJson){
			console.log(dataJson);
			$scope.itemType = dataJson.data;
			$scope.itemType.unshift({
				itemTypeCode:"全部",
				itemTypeName:"000",
				type:"1"
			});
			$scope.isLoding = true;
			addAnimate($('#wrapper'))
		}).error(function(err){
			console.log("getItemTheme error");
		});
	}
	//法人
	$scope.corporationItem = function(){
		removeAnimate($('#wrapper'))
		$scope.deptindex = 0;
		$scope.current = 1;
		$http.jsonp(urlHost+'/aci/declare/getItemTheme.do',{
			params:{
				jsonpCallback: "JSON_CALLBACK",
				areaCode : "SH00JA",
				type:'2'
			}
		}).success(function(dataJson){
			console.log(dataJson);
			$scope.itemType = dataJson.data;
			$scope.itemType.unshift({
				itemTypeCode:"全部",
				itemTypeName:"000",
				type:"2"
			});
			$scope.isLoding = true;
			addAnimate($('#wrapper'))
		}).error(function(err){
			console.log("getItemTheme error");
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
			areaCode:"SH00JA"
		};
		$http.jsonp(urlHost+'/aci/declare/getDeptInArea.do', {
			params: organConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.organSetList;
			addAnimate($('#wrapper'))
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	};
	//通过主题查事项
	$scope.getItemListByType = function(code,type){
		removeAnimate($('#wrapper'))
		$scope.deptindex = 1;
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode:"SH00JA",
			themeCode:code,
			type:type
		};
		$http.jsonp(urlHost+'/aci/declare/getItemInTheme.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.data;
			addAnimate($('#wrapper'))
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	}

	//个人所有事项
	$scope.getPersonItemList = function(){
		removeAnimate($('#wrapper'))
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode:"SH00JA",
			themeCode:"",
			type:"1"
		};
		$http.jsonp(urlHost+'/aci/declare/getItemInTheme.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.data;
			addAnimate($('#wrapper'))
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	}
	//法人所有事项
	$scope.getCorporationItemList = function(){
		removeAnimate($('#wrapper'))
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode:"SH00JA",
			themeCode:"",
			type:"2"
		};
		$http.jsonp(urlHost+'/aci/declare/getItemInTheme.do', {
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
			$http.jsonp(urlHost+'/aci/declare/getItemListByItemNameForPage.do', {
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
	$scope.getItemByOrganCode = function(code){
		removeAnimate($('#wrapper'))
		$scope.current = 2;
		$scope.dept = true;
		$scope.deptindex = 1;
		var oConfig = {
			organCode: code,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost+'/aci/declare/getItemListByOrganCodeForPage.do', {
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
	
	$scope.isScroll = function(){
		
		new iScroll("wrapper",{
			vScrollbar: true,
			hScrollbar:false,
			hideScrollbar:false,
			bounce:true,
			hScroll: false,
			checkDOMChanges:true
		});
	};
	$scope.isScroll();	
	$scope.toItemTypeMaterials = function(name,code,type){
		if(type){
			if(code=="全部" && type=="1"){
				console.log(1);
				$scope.getPersonItemList();
				$scope.dept = true;
				$scope.deptindex = 1;
			}else if(code=="全部" && type=="2"){
				$scope.getCorporationItemList();
				$scope.dept = true;
				$scope.deptindex = 1;
			}
		}else{
			$scope.getItemListByType(name,$scope.type);
			$scope.dept = true;
		}
	}
	$scope.toMaterials = function(itemName, organCode, organName, itemNo) {
		removeAnimate($('#wrapper'))
		if(itemName){
			data.itemName = itemName;
			data.organCode = organCode;
			data.organName = organName;
			data.itemNo = itemNo;
			$location.path("/matter");
		}else if(organCode) {
			$scope.getItemByOrganCode(organCode);
			$scope.dept=true;
		}
		addAnimate($('#wrapper'))
	};
	$scope.prevStep = function(){
		$location.path('/start');
	}
});
app.controller("citylistController", function($scope, $route, $location, $http, $rootScope, data) {
	removeAnimate($('#wrapper'))
	$scope.current = 0;
	$scope.searchType = ["个人","法人","部门"];
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
	$scope.type=1;
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
	$scope.personalItem = function(){
		removeAnimate($('#wrapper'))
		$scope.deptindex = 0;
		$scope.current = 0;
		$http.jsonp(urlHost+'/aci/declare/getItemTheme.do',{
			params:{
				jsonpCallback: "JSON_CALLBACK",
				areaCode : 'SH00SH',
				type:'1'
			}
		}).success(function(dataJson){
			console.log(dataJson);
			$scope.itemType = dataJson.data;
			$scope.itemType.unshift({
				itemTypeCode:"全部",
				itemTypeName:"000",
				type:"1"
			});
			$scope.isLoding = true;
			addAnimate($('#wrapper'))
		}).error(function(err){
			console.log("getItemTheme error");
		});
	}
	//法人
	$scope.corporationItem = function(){
		removeAnimate($('#wrapper'))
		$scope.deptindex = 0;
		$scope.current = 1;
		$http.jsonp(urlHost+'/aci/declare/getItemTheme.do',{
			params:{
				jsonpCallback: "JSON_CALLBACK",
				areaCode : 'SH00SH',
				type:'2'
			}
		}).success(function(dataJson){
			console.log(dataJson);
			$scope.itemType = dataJson.data;
			$scope.itemType.unshift({
				itemTypeCode:"全部",
				itemTypeName:"000",
				type:"2"
			});
			$scope.isLoding = true;
			addAnimate($('#wrapper'))
		}).error(function(err){
			console.log("getItemTheme error");
		});
	}
	//个人所有事项
	$scope.getPersonItemList = function(){
		removeAnimate($('#wrapper'))
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode:"SH00SH",
			themeCode:"",
			type:"1"
		};
		$http.jsonp(urlHost+'/aci/declare/getItemInTheme.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.data;
			addAnimate($('#wrapper'))
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	}
	//法人所有事项
	$scope.getCorporationItemList = function(){
		removeAnimate($('#wrapper'))
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode:"SH00SH",
			themeCode:"",
			type:"2"
		};
		$http.jsonp(urlHost+'/aci/declare/getItemInTheme.do', {
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
			areaCode:'SH00SH'
		};
		$http.jsonp(urlHost+'/aci/declare/getDeptInArea.do', {
			params: organConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.organSetList;
			addAnimate($('#wrapper'))
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	};
	//通过主题查事项
	$scope.getItemListByType = function(code,type){
		removeAnimate($('#wrapper'))
		$scope.deptindex = 1;
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode:'SH00SH',
			themeCode:code,
			type:type
		};
		$http.jsonp(urlHost+'/aci/declare/getItemInTheme.do', {
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
			$http.jsonp(urlHost+'/aci/declare/getItemListByItemNameForPage.do', {
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
	$scope.getItemByOrganCode = function(code){
		removeAnimate($('#wrapper'))
		$scope.current = 2;
		$scope.dept = true;
		$scope.deptindex = 1;
		var oConfig = {
			organCode: code,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost+'/aci/declare/getItemListByOrganCodeForPage.do', {
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
	
	$scope.isScroll = function(){
		
		new iScroll("wrapper",{
			vScrollbar: true,
			hScrollbar:false,
			hideScrollbar:false,
			bounce:true,
			hScroll: false,
			checkDOMChanges:true
		});
	};
	$scope.isScroll();
	
	$scope.toItemTypeMaterials = function(name,code,type){
		if(type){
			if(code=="全部" && type=="1"){
				console.log(1);
				$scope.getPersonItemList();
				$scope.dept = true;
				$scope.deptindex = 1;
			}else if(code=="全部" && type=="2"){
				$scope.getCorporationItemList();
				$scope.dept = true;
				$scope.deptindex = 1;
			}
		}else{
			$scope.getItemListByType(name,$scope.type);
			$scope.dept = true;
		}
	}
	$scope.toMaterials = function(itemName, organCode, organName, itemNo) {
		if(itemName){
			data.itemName = itemName;
			data.organCode = organCode;
			data.organName = organName;
			data.itemNo = itemNo;
			$location.path("/matter");
		}else if(organCode) {
			$scope.getItemByOrganCode(organCode);
			$scope.dept=true;
		}
	};
	$scope.prevStep = function(){
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
		$http.jsonp(urlHost + '/aci/declare/getWindowItemStatusList.do', {
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
		console.log(data.areaL);
		data.matindex = 1;
		if(data.areaL == "SH00SH") {
			data.ocode = data.organCode;
			$location.path("/citylist");
		} else if(data.areaL == "SH") {
			data.ocode = data.organCode;
			$location.path("/list");
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
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
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
			addAnimate($('.main2'))
		}).error(function() {
			console.log('getGuideInfoByZhallId error')
		});
	};
	//办事指南二维码地址
	var itemStr = data.itemNo;
	itemStr = itemStr.substring(0, itemStr.length - 1);
	var SHCODE = "SH00HK" + itemStr + 1;
	$scope.codeUrl = "http://zwdt.sh.gov.cn/govPortals/bsfw/findBsfw.do?_organName_=虹口区&_organCode_=SH00HK&_organType_=other&_itemId=" + SHCODE + "&_itemType=审批&_stSubitemId=" + data.itemId;
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

	$scope.getGuieInfo();
	$scope.prevStep = function(){
		$location.path('/matter');
	}
});