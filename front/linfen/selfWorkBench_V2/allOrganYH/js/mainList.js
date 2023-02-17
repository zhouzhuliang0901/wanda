var mainPerjsonStr = [];
var zhMainPerjsonStr = [{
	"name": "市人社局",
	"url": "RS_List",
}, {
	"name": "市交通委",
	"url": "JTW_List",
}, {
	"name": "市医保局",
	"url": "YB_List",
}, {
	"name": "市民政局",
	"url": "MZ_List",
}, {
	"name": "市公安局",
	"url": "GA_List",
}, {
	"name": "市残联",
	"url": "CL_List",
}, {
	"name": "市教委",
	"url": "JW_List",
}, {
	"name": "市市场监管局",
	"url": "SCJG_List",
}, {
	"name": "市住建委",
	"url": "ZJ_List",
}, {
	"name": "市发改委",
	"url": "FGW_List",
}, {
	"name": "规划资源局服务",
	"url": "GH_List",
}, {
	"name": "市农业委员局",
	"url": "NW_List",
}, {
	"name": "市卫健委服务",
	"url": "WJW_List",
}, {
	"name": "应急局服务",
	"url": "YJGL_List",
}, {
	"name": "市税务局",
	"url": "SWJ_List",
}, {
	"name": "市司法",
	"url": "SF_List",
}, {
	"name": "申康医联",
	"url": "SKYL_List",
}, {
	"name": "粮食局",
	"url": "LS_List",
}, {
	"name": "市商务委",
	"url": "SWW_List",
}, {
	"name": "市档案局",
	"url": "DA_List",
}, {
	"name": "市经信委",
	"url": "JXW_List",
}];
var nhMainPerjsonStr = [{
	"name": "市人社局",
	"url": "RS_List",
}, {
	"name": "市交通委",
	"url": "JTW_List",
}, {
	"name": "市医保局",
	"url": "YB_List",
}, {
	"name": "市民政局",
	"url": "MZ_List",
}, {
	"name": "市公安局",
	"url": "GA_List",
}, {
	"name": "市残联",
	"url": "CL_List",
}, {
	"name": "市教委",
	"url": "JW_List",
}, {
	"name": "市市场监管局",
	"url": "SCJG_List",
}, {
	"name": "市住建委",
	"url": "ZJ_List",
}, {
	"name": "市发改委",
	"url": "FGW_List",
}, {
	"name": "规划资源局服务",
	"url": "GH_List",
}, {
	"name": "市农业委员局",
	"url": "NW_List",
}, {
	"name": "市卫健委服务",
	"url": "WJW_List",
}, {
	"name": "应急局服务",
	"url": "YJGL_List",
}, {
	"name": "市税务局",
	"url": "SWJ_List",
}, {
	"name": "区绿容",
	"url": "QLR_List",
}];
console.log(acBridgeMac.vendor());
var yhPublicItemdeviceName = '';
try {
	if(localStorage.getItem("yhPublicItemdevice") != null) {
		yhPublicItemdeviceName = localStorage.getItem("yhPublicItemdevice");
	}
} catch(e) {}
if(yhPublicItemdeviceName == 'index-yh-yb') {
	mainPerjsonStr = yhPublicMainPerjsonStr;
} else if(yhPublicItemdeviceName == 'index-shsdsx') {
	mainPerjsonStr = yhPublicItemMainPerjsonStr;
} else if(jQuery.getConfigMsg.iszhdevice) {
	if(getUrlParms("zhid") == 'zhdeviceAH') {
		mainPerjsonStr = zhMainPerjsonStr;
	} else {
		mainPerjsonStr = zhMainPerjsonStr;
	}
} else if(acBridgeMac.vendor() == 'nhdevice') {
	mainPerjsonStr = nhMainPerjsonStr;
} else if(acBridgeMac.vendor() == 'jtdevice') {
	if(window.isBocomDev == "trans") {
		mainPerjsonStr = jtyhNoPrintMainPerjsonStr;
	} else if(window.isBocomDev == "print") {
		mainPerjsonStr = jtyhMainPerjsonStr;
	}
} else if(acBridgeMac.vendor() == 'pfdevice') {
	mainPerjsonStr = pfMainPerjsonStr;
} else if(acBridgeMac.vendor() == 'ghdevice') {
	var ghdeviceUrlSuffixNameList = 'index-gh';
	try {
		if(localStorage.getItem("ghdeviceUrlSuffixName") != null) {
			ghdeviceUrlSuffixNameList = localStorage.getItem("ghdeviceUrlSuffixName");
		}
	} catch(e) {}
	if(ghdeviceUrlSuffixNameList == 'index-gh-print') {
		mainPerjsonStr = ghPrintMainPerjsonStr;
	} else {
		mainPerjsonStr = ghMainPerjsonStr;
	}

} else if(acBridgeMac.vendor() == 'nsdevice') {
	var nsdeviceUrlSuffixNameList = 'index-ns';
	try {
		if(localStorage.getItem("nsdeviceUrlSuffixName") != null) {
			nsdeviceUrlSuffixNameList = localStorage.getItem("nsdeviceUrlSuffixName");
		}
	} catch(e) {}
	if(nsdeviceUrlSuffixNameList == 'index-ns-print') {
		mainPerjsonStr = nsMainPerjsonStr;
	} else {
		mainPerjsonStr = FirstOrderItemMainPerjsonStr;
	}
} else if($.device.vendor() == 'shyhdevice') {
	mainPerjsonStr = shyhMainPerjsonStr;
} else {
	mainPerjsonStr = FirstOrderItemMainPerjsonStr;
}