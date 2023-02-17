app.controller("main", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.funName = "事项列表";
	$scope.stuffName = mainPerjsonStr;
	$scope.choiceType = function(url, name) {
		$state.go(url);
	}
});
app.controller("RS_List", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择办事事项";
	$scope.stuffName1 = kReturnList(perjsonStr1, 'rs');
	$scope.stuffName2 = kReturnList(perjsonStr2, 'rs');
	$scope.stuffName3 = kReturnList(perjsonStr3, 'rs');
	$scope.funName = "人社服务";
	$scope.getMatterCon = function(itemName, code, type, url) {
		if(url == "flexibleEmployment") {
			$state.go("RSListFlexibleEmployment");
		} else {
			window.location.href = url;
		}
		appData.itemName = itemName;
		appData.code = code;
		appData.type = type;
	};
});
app.controller("RSListFlexibleEmployment", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择办事事项";
	$scope.funName = "灵活就业登记";
	$scope.stuffName = flexibleEmployment;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		$.device.GoHome();
	}
	$scope.getMatterCon = function(type) {
		window.location.href = type;
	};
});
app.controller("JTW_List", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.funName = "交通委服务";
	$scope.stuffName = kReturnList(JWTperjsonStr, 'jtw');
	$scope.choiceType = function(type, name, url) {
		appData.funName = name;
		appData.type = type;
		window.location.href = url
	}
});
app.controller("YB_List", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.funName = "医保服务";
	$scope.stuffName = kReturnList(YBperjsonStr, 'yb');
	$scope.choiceType = function(type, name, url) {
		appData.funName = name;
		appData.type = type;
		window.location.href = url
	}
});
app.controller("MZ_List", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.funName = "市民政局";
	$scope.stuffName = kReturnList(MZperjsonStr, 'mz');
	$scope.choiceType = function(name, url) {
		appData.funName = name;
		window.location.href = url
	}
});
app.controller("GA_List", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	//$scope.funName="市民政局";
	$scope.stuffName = kReturnList(GAperjsonStr, 'ga');
	$scope.choiceType = function(name, url) {
		appData.funName = name;
		window.location.href = url
	}
});
app.controller("CL_List", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	//$scope.funName="市民政局";
	$scope.stuffName = kReturnList(CLperjsonStr, 'cl');
	$scope.choiceType = function(type, name, ywlx) {
		trackEvent('住建委', name);
		appData.funName = name;
		appData.type = type;
		appData.ywlx = ywlx;
		if(type == "CL_informationCard") {
			window.location.href = "../CL_informationCard/index.html"
		} else {
			window.location.href = "../" + type + "/index.html"
		}
	}
});
app.controller("JW_List", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	//$scope.funName="市民政局";
	$scope.stuffName = kReturnList(JWperjsonStr, 'jw');
	$scope.choiceType = function(url, name, type) {
		appData.funName = name;
		appData.type = type;
		window.location.href = url
	}
});
app.controller("SCJG_List", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	//$scope.funName="市民政局";
	$scope.stuffName = kReturnList(SCJGperjsonStr, 'scjg');
	$scope.choiceType = function(type, name, url) {
		appData.funName = name;
		appData.type = type;
		window.location.href = url
	}
});
app.controller("ZJ_List", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	//$scope.funName="市民政局";
	$scope.stuffName = kReturnList(ZJperjsonStr, 'zj');
	$scope.choiceType = function(name, url) {
		appData.funName = name;
		//appData.type = type;
		window.location.href = url
	}
});
app.controller("DA_List", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择办事事项";
	$scope.stuffName1 = kReturnList(DA_allItem, 'da');
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.getMatterCon = function(itemName, url) {
		window.location.href = url;
		appData.itemName = itemName;
		appData.code = code;
		appData.type = type;
		//		if(code == "") {
		//			$scope.isAlert = true;
		//			$scope.msg = "暂未开放";
		//		} else if(code == "RS_ssCardInfo") {
		//			window.location.href = "../RS_ssCardInfo/index.html"
		//		} else {
		//			$state.go("loginType");
		//		}
	};
});
app.controller("FGW_List", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择服务内容";
	$scope.stuffName = kReturnList(fgwPerjsonStr, 'fgw');
	$scope.choiceType = function(name, url) {
		appData.funName = name;
		window.location.href = url
	}
});
app.controller("GH_List", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.stuffName = kReturnList(ghPerjsonStr, 'gh');
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.choiceType = function(type, name, ywlx) {
		trackEvent(name);
		appData.funName = name;
		appData.type = type;
		appData.ywlx = ywlx;
		if(type == "CL_informationCard") {
//			window.location.href = "../CL_informationCard/index.html"
			$scope.isAlert = true;
			$scope.msg = "暂停服务";
			$scope.alertConfirm = function(){
				$scope.isAlert = false;
			}
		} else {
			window.location.href = "../"+type+"/index.html"
		}
	}
});
app.controller("NW_List", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.stuffName = kReturnList(nwPerjsonStr, 'nw');
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.choiceType = function(type, name, ywlx) {
		trackEvent(name);
		appData.funName = name;
		appData.type = type;
		appData.ywlx = ywlx;
		if(type == "CL_informationCard") {
//			window.location.href = "../CL_informationCard/index.html"
			$scope.isAlert = true;
			$scope.msg = "暂停服务";
			$scope.alertConfirm = function(){
				$scope.isAlert = false;
			}
		} else {
			window.location.href = "../"+type+"/index.html"
		}
	}
});
app.controller("SWJ_List", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.stuffName = kReturnList(swjPerjsonStr, 'swj');
	$scope.choiceType = function(type, name, url) {
		trackEvent(name);
		appData.funName = name;
		appData.type = type;
		window.location.href = url
	}
});
app.controller("WJW_List", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.stuffName = kReturnList(wjwPerjsonStr, 'wjw');
	$scope.choiceType = function(type, name, url) {
		appData.funName = name;
		appData.type = type;
		window.location.href = url
	}
});
app.controller("YJGL_List", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.stuffName = kReturnList(yjglPerjsonStr, 'yjgl');
	$scope.choiceType = function(type, name, url) {
		appData.funName = name;
		appData.type = type;
		window.location.href = type
	}
});
app.controller("FGJ_List", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.stuffName = kReturnList(FGJPerjsonStr, 'fgj');
	$scope.choiceType = function(type, name, url) {
		trackEvent(name);
		appData.funName = name;
		appData.type = type;
		window.location.href = url
	}
});
app.controller("QLR_List", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	//$scope.funName="市民政局";
	$scope.stuffName = kReturnList(QLRperjsonStr, 'qlr');
	$scope.choiceType = function(url, name, type) {
		appData.funName = name;
		appData.type = type;
		window.location.href = url
	}
});

app.controller("SKYL_List", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.stuffName = kReturnList(SKYLperjsonStr, 'skyl');
	$scope.choiceType = function(type, name, url) {
		trackEvent(name);
		appData.funName = name;
		appData.type = type;
		window.location.href = url
	}
});
app.controller("SWW_List", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.stuffName = kReturnList(SWWperjsonStr, 'sww');
	$scope.choiceType = function(type, name, url) {
		trackEvent(name);
		appData.funName = name;
		appData.type = type;
		window.location.href = url
	}
});
app.controller("SF_List", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.stuffName = kReturnList(SFperjsonStr, 'sf');
	$scope.choiceType = function(type, name, url) {
		trackEvent(name);
		appData.funName = name;
		appData.type = type;
		window.location.href = url
	}
});
app.controller("LS_List", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.stuffName = kReturnList(LSperjsonStr, 'ls');
	$scope.choiceType = function(type, name, url) {
		trackEvent(name);
		appData.funName = name;
		appData.type = type;
		window.location.href = url
	}
});
app.controller("JXW_List", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.stuffName = kReturnList(JXWperjsonStr, 'jxw');
	$scope.choiceType = function(type, name, url) {
		trackEvent(name);
		appData.funName = name;
		appData.type = type;
		window.location.href = url
	}
});
app.controller("test_List", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	var ZJperjsonStr = [{
		"stuffName": "test13",
		"img": "../libs/common/images/newIcon/JG.png",
		"url": "../../selfWorkBench_V2test13/index.html#/index-gh-print"
	}]
	$scope.stuffName = ZJperjsonStr;
	$scope.choiceType = function(name, url) {
		console.log(url);
		trackEvent(name);
		appData.funName = name;
		window.location.href = url
	}
});