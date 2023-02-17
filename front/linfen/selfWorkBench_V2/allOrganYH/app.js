var app = angular.module("terminalApp", ["ngAnimate", "ui.router"]);
app.value("appData", {});
app.run(function($rootScope, $log, $location, $state) {
	$rootScope.$on("$viewContentLoaded", function(event, toState) {
		$rootScope.goAppHistoryBack = function() {
			$.device.Camera_Hide();
			$.device.idCardClose();
			$.device.qrCodeClose();
			try {
				window.external.URL_CLOSE();
			} catch(e) {
				//TODO handle the exception
			}
			if($state.$current.name == "main") {
				$.device.GoHome();
			} else {
				$location.path("/main");
			}
		};
	});
});
//自定义指令repeatFinish --------- 防止滑动时触发点击事件
app.directive('repeatFinish', function() {
	return {
		link: function(scope, element, attr) {
			if(scope.$last == true) {
				var scroll = new BScroll('.wrapper', {
					scrollX: false,
					scrollY: true,
					scrollbar: true,
					preventDefault: false,
					checkDOMChanges: true,
					click: true,
					tap: true
				})
				$('.wrapper').find("a").on('tap', function() {});
			}
		}
	}
});
app.factory("appFactory", function($http, $rootScope) {})
///
function kReturnYHList(id) {
	//中行
	var JosnListName;
	var yhPublicItemdeviceName = '';
	try {
		if(localStorage.getItem("yhPublicItemdevice") != null) {
			yhPublicItemdeviceName = localStorage.getItem("yhPublicItemdevice");
		}
	} catch(e) {}
	if(yhPublicItemdeviceName == 'index-yh-yb') {
		JosnListName = yhPublicListJsonName;
	} else if(yhPublicItemdeviceName == 'index-shsdsx') {
		JosnListName = yhPublicItemData;
	} else if(jQuery.getConfigMsg.iszhdevice) {
		//中行
		if(getUrlParms("zhid") == 'zhdeviceAH') {
			JosnListName = zhListJsonName;
		} else {
			JosnListName = zhListJsonName;
		}
	} else if(acBridgeMac.vendor() == 'jtdevice') {
		if(window.isBocomDev == "trans") {
			JosnListName = jtyhNoPrintListJsonName;
		} else if(window.isBocomDev == "print") {
			JosnListName = jtyhListJsonName;
		}
	} else if(acBridgeMac.vendor() == 'nhdevice') {
		JosnListName = nhListJsonName;
	} else if(acBridgeMac.vendor() == 'pfdevice') {
		JosnListName = pfListJsonName;
	} else if(acBridgeMac.vendor() == 'ghdevice') {
		var ghdeviceUrlSuffixNameList = 'index-gh';
		try {
			if(localStorage.getItem("ghdeviceUrlSuffixName") != null) {
				ghdeviceUrlSuffixNameList = localStorage.getItem("ghdeviceUrlSuffixName");
			}
		} catch(e) {}
		if(ghdeviceUrlSuffixNameList == 'index-gh-print') {
			JosnListName = ghPrintListJsonName;
		} else {
			JosnListName = ghListJsonName;
		}
	} else if(acBridgeMac.vendor() == 'nsdevice') {
		var nsdeviceUrlSuffixNameList = 'index-ns';
		try {
			if(localStorage.getItem("nsdeviceUrlSuffixName") != null) {
				nsdeviceUrlSuffixNameList = localStorage.getItem("nsdeviceUrlSuffixName");
			}
		} catch(e) {}
		if(nsdeviceUrlSuffixNameList == 'index-ns-print') {
			JosnListName = nsListJsonName;
		} else {
			JosnListName = FirstOrderItemData;
		}
	} else if ($.device.vendor() == 'shyhdevice') {
		JosnListName = shyhListJsonName;
	} else {
		JosnListName = FirstOrderItemData;
	}
	var ZJOSNLISTNAME;
	if(id == "rs") {
		ZJOSNLISTNAME = JosnListName.RSList;
	} else if(id == "jtw") {
		ZJOSNLISTNAME = JosnListName.JTW_List;
	} else if(id == "yb") {
		ZJOSNLISTNAME = JosnListName.YB_List;
	} else if(id == "mz") {
		ZJOSNLISTNAME = JosnListName.MZ_List;
	} else if(id == "ga") {
		ZJOSNLISTNAME = JosnListName.GA_List;
	} else if(id == "cl") {
		ZJOSNLISTNAME = JosnListName.CL_List;
	} else if(id == "jw") {
		ZJOSNLISTNAME = JosnListName.JW_List;
	} else if(id == "scjg") {
		ZJOSNLISTNAME = JosnListName.SCJG_List;
	} else if(id == "zj") {
		ZJOSNLISTNAME = JosnListName.ZJ_List;
	} else if(id == "da") {
		ZJOSNLISTNAME = JosnListName.DA_List;
	} else if(id == "fgw") {
		ZJOSNLISTNAME = JosnListName.FGW_List;
	} else if(id == "gh") {
		ZJOSNLISTNAME = JosnListName.GH_List;
	} else if(id == "nw") {
		ZJOSNLISTNAME = JosnListName.NW_List;
	} else if(id == "swj") {
		ZJOSNLISTNAME = JosnListName.SWJ_List;
	} else if(id == "wjw") {
		ZJOSNLISTNAME = JosnListName.WJW_List;
	} else if(id == "yjgl") {
		ZJOSNLISTNAME = JosnListName.YJGL_List;
	} else if(id == "fgj") {
		ZJOSNLISTNAME = JosnListName.FGJ_List;
	} else if(id == "qlr") {
		ZJOSNLISTNAME = JosnListName.QLR_List;
	} else if(id == "skyl") {
		ZJOSNLISTNAME = JosnListName.SKYL_List;
	} else if(id == "sww") {
		ZJOSNLISTNAME = JosnListName.SWW_List;
	} else if(id == "sf") {
		ZJOSNLISTNAME = JosnListName.SF_List;
	} else if(id == "ls") {
		ZJOSNLISTNAME = JosnListName.LS_List;
	} else if(id == "jxw") {
		ZJOSNLISTNAME = JosnListName.JXW_List;
	} else {
		ZJOSNLISTNAME = JosnListName;
	}
	return ZJOSNLISTNAME;
}

function kReturnList(data, id) {
	if(!data) return;
	var JosnListStuffName = data;
	var JosnName = kReturnYHList(id);
	//console.log(JosnName);
	if(JosnName == undefined) return;
	var reData = [];
	//console.log(JosnListStuffName);
	for(var i = 0; i < JosnName.length; i++) {
		//console.log(JosnName[i]);
		for(var j = 0; j < JosnListStuffName.length; j++) {
			if(JosnListStuffName[j]['stuffName'] == JosnName[i]['name']) {
				//console.log(JosnListStuffName[j]);
				reData.push(JosnListStuffName[j]);
			}
		}
	}
	console.log(reData);
	return reData;
}