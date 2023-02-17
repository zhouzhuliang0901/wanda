var urlHost = 'http://zwdtyp.sh.gov.cn:8088/ac';
var t;
function time() {
	var time = 60;
    t = setInterval(function(){
        if (time == 0) {
            clearInterval(t);
            //$.device.GoHome();
        }
        $(".minute").text(time);
        time--;
    }, 1000)
}
app.controller("listController", function($scope, $route, $location, $http, $rootScope,data) {
	$scope.current = 0;
	$scope.searchType = ["按部门", "按事项"];
	$scope.itemName = [];
	$scope.currentMatters = [];
	$scope.organCode = null;
	$scope.matterVal = '';
	$scope.idRead = false;
	clearInterval(t);
	time();
	//查询事项
	$scope.getSearchMatter = function(current) { // 查询事项
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
	//获取所有事项
	$scope.getMatter = function() {
		$scope.matterVal = '';
		$scope.active = null;
		$scope.organCode = null;
		var config = {
			jsonpCallback: "JSON_CALLBACK",
		}
		$http.jsonp(urlHost + '/aci/declare/getAllItemListForPage.do', {
			params: config
		}).success(function(dataJson) {
			$scope.itemName = dataJson.itemSetList;
		}).error(function() {
			console.log('getAllItemListForPage error')
		})
	};

	//获取所有部门
	$scope.getDepartment = function() {
		$scope.matterVal = '';
		var organConfig = {
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/declare/getOrganListForDeclarePage.do', {
			params: organConfig
		}).success(function(dataJson) {
			for(var i = 0; i<dataJson.organSetList.length;i++){
				if(dataJson.organSetList[i].organName=="杨浦区市场监督管理局"){
					dataJson.organSetList[i].organName = "杨浦区安监局";
					dataJson.organSetList[i].description = "杨浦区安监局";
					dataJson.organSetList[i].organCode = "SHAQYP";
				}
			}
			dataJson.organSetList[0].organName = "杨浦区市场监督管理局";
			dataJson.organSetList[0].description = "杨浦区市场监督管理局";
			dataJson.organSetList[0].organCode ="SHSCYP";
			$scope.itemName = dataJson.organSetList;
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	};	
	//通过部门id获取部门事项
	$scope.getItemByOrganCode = function(code){
		$scope.current = 1;
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
		$scope.getDepartment();
	});

	$scope.getMatterCon = function(index, type) {
		$scope.current = index;
		switch(type) {
			case "按部门":
				$scope.getDepartment();
				$scope.isRead = false;
				break;
			case "按事项":
				$scope.getMatter();
				$scope.isRead = true;
				break;
			default:
				$scope.getDepartment();
				$scope.isRead = false;
		}
	};
	$scope.isScroll = function(){
		
		new iScroll("wrapper",{
			vScrollbar: true,
			hScrollbar:false,
			hideScrollbar:false,
			bounce:true,
			checkDOMChanges:true
		});
	};
	$scope.isScroll();
	
	$scope.toMaterials = function(itemName, organCode, organName, itemNo) {
		if(itemName){
			data.itemName = itemName;
			data.organCode = organCode;
			data.organName = organName;
			data.itemNo = itemNo;
			$location.path("/matter");
		}else if(organCode) {
			$scope.getItemByOrganCode(organCode);
		} 
	};
	
	$rootScope.goHome = function(){
		$.device.GoHome();
	};
	
});

app.controller("matterController", function($scope, $route, $http, $location, data, $timeout) {
	$scope.allName = data.itemName;
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	clearInterval(t);
	time();
	$scope.getMatterCond = function() {
		var oConfig = {
			itemNo: data.itemNo,
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
	$scope.getSubItem = function(id, name, description,tenNo,itemNo) {
		data.itemId = id;
		data.statusName = name;
		data.description = description;
		data.itemTenNo = tenNo;
		data.itemNo = itemNo;
		$location.path("/guideline");
	};
});

app.controller("guidelineController", function($scope, $route, $http, $location,$sce, data, $timeout) {
	clearInterval(t);
	time();
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.description = data.description;
	$scope.statusName = data.statusName;
	$scope.ItemStuffList = "";
	//var lodop = $.device.printGetLodop();
	$scope.getGuieInfo = function() {
		var oConfig = {
			stZhallId: data.itemId,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/declare/getGuideInfoByZhallId.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.guideInfo = dataJson.guide;
			$scope.clRange = dataJson.guide.clRange;
		}).error(function() {
			console.log('getGuideInfoByZhallId error')
		});
	};
	var itemStr = data.itemNo;
	itemStr = itemStr.substring(0,itemStr.length-1);
	var SHCODE = "SH00YP"+itemStr+1;
	$scope.codeUrl = "http://zwdt.sh.gov.cn/govPortals/bsfw/findBsfw.do?_organName_=杨浦区&_organCode_=SH00YP&_organType_=other&_itemId="+SHCODE+"&_itemType=审批&_stSubitemId="+data.itemId;
	console.log($scope.codeUrl);
	
	var qrcode = new QRCode("code", {
		text: $scope.codeUrl,
		width: 200,
		height: 200,
		correctLevel: 0,
		render: "table"
	});
	// 获取材料列表  		
	$scope.getItemStuffList = function(){
		var fConfig = {
			itemCode: data.itemTenNo, // 	data.itemTenNo	'0045065000'
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/declare/getItemStuffList.do', {
				params: fConfig
		}).success(function(dataJson){
			if(dataJson.data.length>0){
				for(var i = 0;i<dataJson.data.length;i++){
					$scope.ItemStuffList += "<p>"+(i+1)+"."+dataJson.data[i].stuffName+"</p>";
				}
				$scope.ItemStuff=$sce.trustAsHtml($scope.ItemStuffList);
			}
		}).error(function(e){
			console.log(e);
		});
	}; 
	
	$scope.getItemStuffList();
	
	$scope.print = function(){
	
//lodop.ADD_PRINT_HTM(0,0,"100%","100%",document.getElementById("scrollBox2").innerHTML);
	
	//	lodop.PRINT();
	};
	
	$scope.isScroll = function(){
		
		new iScroll("wrapper",{
			vScrollbar: true,
			hScrollbar:false,
			hideScrollbar:false,
			bounce:true,
			checkDOMChanges:true
		});
	};
	$scope.isScroll();

	$scope.getGuieInfo();
});

