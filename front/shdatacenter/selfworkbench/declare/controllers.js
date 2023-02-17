app.controller("startController", function($scope, $location, $http, data, $rootScope, $timeout, $interval, appFactory) {
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
app.controller("mainController", function($scope, $location, $http, data, $rootScope, $timeout, $interval, appFactory) {
	$scope.areaList = [];
	$scope.getAreaList = function() {
		var aConfig = {
			jsonpCallback: "JSON_CALLBACK"
		}
		$http.jsonp(urlHost1+"/aci/declare/getAllAreaInShanghai.do", {
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
app.controller("listController", function($scope, $route, $location, $http, $rootScope, data, appFactory) {
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
		$scope.deptindex = 0;
		$scope.current = 0;
		$http.jsonp(urlHost1+'/aci/declare/getItemTheme.do',{
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
		}).error(function(err){
			console.log("getItemTheme error");
		});
	}
	//法人
	$scope.corporationItem = function(){
		$scope.deptindex = 0;
		$scope.current = 1;
		$http.jsonp(urlHost1+'/aci/declare/getItemTheme.do',{
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
		}).error(function(err){
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
			areaCode:"SH00JA"
		};
		$http.jsonp(urlHost1+'/aci/declare/getDeptInArea.do', {
			params: organConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.organSetList;
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	};
	//通过主题查事项
	$scope.getItemListByType = function(code,type){
		$scope.deptindex = 1;
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode:"SH00JA",
			themeCode:code,
			type:type
		};
		$http.jsonp(urlHost1+'/aci/declare/getItemInTheme.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.data;
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	}

	//个人所有事项
	$scope.getPersonItemList = function(){
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode:"SH00JA",
			themeCode:"",
			type:"1"
		};
		$http.jsonp(urlHost1+'/aci/declare/getItemInTheme.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.data;
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	}
	//法人所有事项
	$scope.getCorporationItemList = function(){
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode:"SH00JA",
			themeCode:"",
			type:"2"
		};
		$http.jsonp(urlHost1+'/aci/declare/getItemInTheme.do', {
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
			$http.jsonp(urlHost1+'/aci/declare/getItemListByItemNameForPage.do', {
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
		$scope.itemName = "";
		$scope.current = 2;
		$scope.dept = true;
		$scope.deptindex = 1;
		var oConfig = {
			organCode: code,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost1+'/aci/declare/getItemListByOrganCodeForPage.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.itemSetList;
		}).error(function() {
			console.log('getItemListByOrganCodeForPage error')
		})
	};
	
	$scope.$on('$viewContentLoaded', function() {
		if(data.themeCode){
			$scope.getItemListByType(data.themeCode,data.type);
			$scope.isLoding = true;
			$scope.dept = true;
			if(data.type=="1"){
				$scope.current = 0;
			}else if(data.type=="2"){
				$scope.current = 1;
			}
		}else if(data.organCode){
			$scope.getItemByOrganCode(data.organCode);
			$scope.isLoding = true;
		}else{
			$scope.personalItem();
			$scope.dept = false;
		}
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
			data.themeCode = name;
			data.type = $scope.type;
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
});
app.controller("citylistController", function($scope, $route, $location, $http, $rootScope, data, appFactory) {
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
		$scope.deptindex = 0;
		$scope.current = 0;
		$http.jsonp(urlHost1+'/aci/declare/getItemTheme.do',{
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
		}).error(function(err){
			console.log("getItemTheme error");
		});
	}
	//法人
	$scope.corporationItem = function(){
		$scope.deptindex = 0;
		$scope.current = 1;
		$http.jsonp(urlHost1+'/aci/declare/getItemTheme.do',{
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
		}).error(function(err){
			console.log("getItemTheme error");
		});
	}
	//个人所有事项
	$scope.getPersonItemList = function(){
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode:"SH00SH",
			themeCode:"",
			type:"1"
		};
		$http.jsonp(urlHost1+'/aci/declare/getItemInTheme.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.data;
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	}
	//法人所有事项
	$scope.getCorporationItemList = function(){
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode:"SH00SH",
			themeCode:"",
			type:"2"
		};
		$http.jsonp(urlHost1+'/aci/declare/getItemInTheme.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.data;
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	}
	//获取所有部门
	$scope.getDepartment = function() {
		$scope.matterVal = '';
		$scope.current = 2;
		$scope.deptindex = 0;
		var organConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode:'SH00SH'
		};
		$http.jsonp(urlHost1+'/aci/declare/getDeptInArea.do', {
			params: organConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.organSetList;
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	};
	//通过主题查事项
	$scope.getItemListByType = function(code,type){
		$scope.deptindex = 1;
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode:'SH00SH',
			themeCode:code,
			type:type
		};
		$http.jsonp(urlHost1+'/aci/declare/getItemInTheme.do', {
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
			$http.jsonp(urlHost1+'/aci/declare/getItemListByItemNameForPage.do', {
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
		$scope.itemName = "";
		$scope.current = 2;
		$scope.dept = true;
		$scope.deptindex = 1;
		var oConfig = {
			organCode: code,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost1+'/aci/declare/getItemListByOrganCodeForPage.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.itemSetList;
		}).error(function() {
			console.log('getItemListByOrganCodeForPage error')
		})
	};
	
	$scope.$on('$viewContentLoaded', function() {
		if(data.themeCode){
			$scope.getItemListByType(data.themeCode,data.type);
			$scope.isLoding = true;
			$scope.dept = true;
			if(data.type=="1"){
				$scope.current = 0;
			}else if(data.type=="2"){
				$scope.current = 1;
			}
		}else if(data.organCode){
			$scope.getItemByOrganCode(data.organCode);
			$scope.isLoding = true;
		}else{
			$scope.personalItem();
			$scope.dept = false;
		}
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
			data.themeCode = name;
			data.type = $scope.type;
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
});
app.controller("matterController", function($scope, $route, $http, $location, data, $timeout, $rootScope, appFactory) {
	console.log(data.themeCode,data.type);
	$scope.allName = data.itemName;
	var name = data.itemName;
	$scope.itemName = name;;
	$scope.getMatterCond = function() {
		var oConfig = {
			itemNo: data.itemNo,
			deptCode: data.organCode,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost1 + '/aci/declare/getWindowItemStatusList.do', {
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
app.controller("guidelineController", function($scope, $route, $http, $location, $sce, $rootScope, data, $timeout, appFactory) {
	try {
		window.external.URL_CLOSE();
	} catch(e) {

	}
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
		$http.jsonp(urlHost1 + '/aci/declare/getGuideInfoByZhallId.do', {
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
	window.external.URL_OPEN(50, 180, 1800, 760, $scope.applyUrl);

	// 设置url被angular信任 正常跳转
	$scope.prev = function() {
		try {
			window.external.URL_CLOSE();
		} catch(e) {

		}
		$location.path("/guideline");
	}
});
app.controller("selectController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
	try {
		window.external.URL_CLOSE();
	} catch(e) {}
	$.device.Camera_Hide();
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;

	// 刷身份证获取信息
	$scope.scanIdcard = function() {
		$location.path('/idCard');
	}
	// 市民云亮证
	$scope.citizen = function() {
		$location.path('/citizen');
	}
	//刷脸认证
	$scope.queryFace = function(){
		$location.path('/queryFace');
	}
	$scope.prev = function() {
		$location.path("/guideline");
	}
});
app.controller("queryFaceController",function($scope,$location,data){
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.statusName = data.statusName;

	$scope.getInfo = function(idCard,Name){
		data.idCardName = idCard;
	 	data.idCardNum = Name;
	 	console.log(data.idCardName+'==='+data.idCardNum);
	}
	$scope.prev = function(){
		$location.path("/select");
	}
	$scope.next = function(){
		$location.path("/info");
	}
});
app.controller("idCardController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
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
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		$location.path("/info");
	}
//	 	data.idCardName = "zoutianqi";
//	 	data.idCardNum = "430426199804106174";
//	 	$location.path("/info");

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
			url: urlHost+"/aci/window/getQrCodeInfoByElectronicCert.do",
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data: {
				codeParam: __code,
				lzAddress:"静安区行政服务中心"
			},
			success: function(dataJsonp) {
				if(dataJsonp.result.success) {
					data.idCardName = dataJsonp.result.data.realname;
					data.idCardNum = dataJsonp.result.data.idcard;
					//data.mobile = dataJsonp.result.data.mobile;
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
});
app.controller("infoController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
	$.device.Camera_Hide();
	$scope.fullItemName = data.itemName + "--" + data.statusName;
	var name = data.itemName;
	$scope.itemName = name;

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
			data.applyNo = dataJson.data.applyNo;
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
app.controller("uploadMethodController", function($scope, $route, $rootScope, $http, $location, data, $timeout, appFactory) {
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
});
app.controller("materialPicController", function($scope, $route, $http, $rootScope, $location, data, $timeout, appFactory) {
	var name = data.itemName;
	$scope.itemName = name;
	$scope.imgUrls = "";

	$scope.profileShow = function() {
		$.ajax({
			url: urlHost + "/aci/autoterminal/dzzz/queryCertBaseData.do",
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				jsonpCallback: "JSON_CALLBACK",
				certNo: data.idCardNum, // "340881199303145313", // 
				type: 0
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
		layer.msg("上传中 请稍侯");
		data.selectImg = urlHost + $scope.imgUrls[$scope.current].pictureUrlForBytes;
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
				$.device.httpUpload(urlHost+'/aci/declare/uploadStuff.do', "file", "C:/waitUploadImg.jpg",
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

});
app.controller("takePhotoController", function($scope, $route, $http, $rootScope, $location, data, $timeout, $routeParams, appFactory) {
	var name = data.itemName;
	$scope.itemName = name;

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
			$.device.httpUpload(urlHost+"/aci/declare/uploadStuff.do", "file", $scope.UData,
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
});
app.controller("finishController", function($scope, $route, $http, $location, $rootScope, data, $timeout, appFactory) {
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();

	var name = data.itemName;
	$scope.itemName = name;

//	$.device.cmCaptureShow(700, 480, 90, 375);
//	$.device.cmCaptureSelectRect(0, 0, 1920, 1920);
	$scope.isFinish = false;
	// 拍照
	var imgHTML = "";
	var imgIndex = 0;
	$scope.next = function() {
		layer.msg("正在上传中，请稍候");
//		var scanImg = $.device.cmCaptureCaptureUrl();
		var scanImg = "data:image/png;base64,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";
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
//			},
//			error: function(err) {
//				layer.msg("上传材料失败")
//			}
//		});
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
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;

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
						console.log(data.isUpload[i].stuffName+"====="+data.listImg[j].stuffName);
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
});
app.controller("infoFinishController", function($scope, $route, $rootScope, $http, $location, data, $timeout, appFactory) {
	$scope.allName = data.itemName + '--' + data.statusName;
	var lodop = $.device.printGetLodop();
	var name = data.itemName;
	$scope.itemName = name;

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

	$scope.submitApply();
	$scope.applyNo = data.applyNo;
	$scope.statusText = '打印凭证';
	var code = "http://zwdtmob.sh.gov.cn/zwdtSW/spnetQuery/bjxqxg.jsp?ST_WF_ID=" + data.applyNo + "&IdOrCode=" + encodeURI(data.username);
	var date = new Date();
	var month = date.getMonth() + 1;
	// 打印凭条
	$scope.print = function() {
		lodop.ADD_PRINT_BARCODE(28, 34, 307, 47, "128A", data.applyNo);
		lodop.ADD_PRINT_BARCODE(28, 550, 168, 146, "QRCode", code);
		lodop.ADD_PRINT_TEXT(170, 260, 250, 50, "申报提交确认单");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		lodop.ADD_PRINT_TEXT(250, 28, 600, 30, "统一审批编码：" + data.applyNo);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(280, 28, 600, 30, "申请单位/申请人：" + data.username);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(310, 28, 600, 30, "申请事项：" + $scope.allName);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(370, 28, 450, 30, "请您携带办理材料至虹口区行政服务中心进行办理。");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(400, 551, 168, 30, date.getFullYear() + "年" + month + "月" + date.getDate() + "日");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(480, 28, 670, 30, "亲民提示：您可凭统一审批编码至“一网通办”总门户或扫描右侧二维码查询办件进度。");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.PRINT();
	};
});