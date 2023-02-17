//通过配置文件读取  是否有互联网环境
var preUrl;
var preUrlSelf;
var preUrlSelfApi;
var curWwwPath = window.document.location.hostname;
var protocol = window.location.protocol;
if (curWwwPath == "10.81.16.56") {
	//政务网地址
	//preUrl = "http://10.81.16.56:8080/ac-product";
	preUrl = "http://10.81.16.56:8090/ac-self";
	preUrlSelf = "http://10.81.16.56:8090/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = "10.81.16.56:8080";
} else if (curWwwPath == 'gst.sh.cegn.cn') {
	//建行内网地址
	//preUrl = "http://gst.sh.cegn.cn:8080/ac-product";
	preUrl = "http://gst.sh.cegn.cn:8090/ac-self";
	preUrlSelf = "http://gst.sh.cegn.cn:8090/ac-self";
	preUrlSelfApi = "http://gst.sh.cegn.cn:8088/ac-self-api";
	piwikurl = "http://gst.sh.cegn.cn:8080";
} else if (curWwwPath == '10.5.20.217') {
	//农行内网地址
	//preUrl = "http://10.5.20.217:8050/ac-product";
	preUrl = "http://10.5.20.217:8060/ac-self";
	preUrlSelf = "http://10.5.20.217:8060/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = "10.5.20.217:8050";
} else if (curWwwPath == 'nisp.tpaas.abc') {
	//农行其他省份1内网地址
	//preUrl = "http://10.5.20.217:8050/ac-product";
	preUrl = "http://nisp.tpaas.abc/ac-self";
	preUrlSelf = "http://nisp.tpaas.abc/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = "nisp.tpaas.abc";
} else if (curWwwPath == '21.196.248.127') {
	//中行内网地址
	//preUrl = "http://21.196.248.127:8080/ac-product";
	preUrl = "http://21.196.248.127:8090/ac-self";
	preUrlSelf = "http://21.196.248.127:8090/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = "http://21.196.248.127:8080";
} else if (curWwwPath == '21.196.68.14') {
	//中行内网地址
	preUrl = "http://21.196.68.14:8090/ac-self";
	preUrlSelf = "http://21.196.68.14:8090/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = "http://21.196.68.14:8080";
} else if (curWwwPath == '21.200.133.186') {
	//中行其他省份内网地址1
	preUrl = "http://21.200.133.186:8889/ac-self";
	preUrlSelf = "http://21.200.133.186:8889/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = "http://21.200.133.186:8889";
} else if (curWwwPath == '21.208.7.100') {
	//中行其他省份内网地址2
	preUrl = "http://21.208.7.100:8889/ac-self";
	preUrlSelf = "http://21.208.7.100:8889/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = "http://21.208.7.100:8889";
} else if (curWwwPath == '21.164.4.132') {
	//中行其他省份内网地址3
	preUrl = "http://21.164.4.132:8889/ac-self";
	preUrlSelf = "http://21.164.4.132:8889/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = "http://21.164.4.132:8889";
} else if (curWwwPath == '21.210.1.108') {
	//中行其他省份内网地址4
	preUrl = "http://21.210.1.108:8889/ac-self";
	preUrlSelf = "http://21.210.1.108:8889/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = "http://21.210.1.108:8889";
} else if (curWwwPath == '21.216.14.23') {
	//中行安徽内网地址
	preUrl = "http://21.216.14.23:8089/ac-self";
	preUrlSelf = "http://21.216.14.23:8089/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = "http://21.216.14.23:8089";
} else if (curWwwPath == '10.100.17.68') {
	//浦发内网地址
	preUrl = "http://10.100.17.68:8090/ac-self";
	preUrlSelf = "http://10.100.17.68:8090/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = "http://10.100.17.68:8080";
} else if (curWwwPath == '10.202.210.109') {
	//浦发内网地址
	preUrl = "http://10.202.210.109:8090/ac-self";
	preUrlSelf = "http://10.202.210.109:8090/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = "http://10.202.210.109:8080";
} else if (curWwwPath == '10.20.4.181') {
	//农商内网测试地址
	preUrl = "http://10.20.4.181:8090/ac-self";
	preUrlSelf = "http://10.20.4.181:8090/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = "http://10.20.4.181:8080";
} else if (curWwwPath == '10.94.2.81') {
	//农商内网地址
	preUrl = "http://10.94.2.81:8090/ac-self";
	preUrlSelf = "http://10.94.2.81:8090/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = "http://10.94.2.81:8080";
} else if (curWwwPath == '182.2.200.114') {
	//交通银行内网地址
	preUrl = "http://182.2.200.114:8090/ac-self";
	preUrlSelf = "http://182.2.200.114:8090/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = "http://182.2.200.114:8080";
} else if (curWwwPath == '107.2.18.34') {
	//工行银行内网地址
	preUrl = "http://107.2.18.34:8090/ac-self";
	preUrlSelf = "http://107.2.18.34:8090/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = "http://107.2.18.34:8080";
} else if (curWwwPath == 'ywtb.sh.icbc') {
	//工行外省内网地址1
	preUrl = "http://ywtb.sh.icbc:8090/ac-self";
	preUrlSelf = "http://ywtb.sh.icbc:8090/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = "http://ywtb.sh.icbc";
} else if (curWwwPath == '107.2.50.136') {
	//工行外省内网地址2
	preUrl = "http://107.2.50.136:8090/ac-self";
	preUrlSelf = "http://107.2.50.136:8090/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = "http://107.2.50.136:8080";
} else if (curWwwPath == '10.10.18.49') {
	//烟草局网点
	preUrl = "http://10.10.18.49:9089";
	preUrlSelf = "http://10.10.18.49:9089";
	preUrlSelfApi = preUrlSelf;
	piwikurl = "http://10.10.18.49:9089";
} else if (curWwwPath == "183.194.250.112" || curWwwPath == "zzzd.sh.gov.cn") {
	//互联网地址
	/*
	 * ip :http://183.194.250.112/
	 * 域名：http://zzzd.sh.gov.cn/
	 */
	preUrl = protocol + "//" + curWwwPath + "/ac-self";
	preUrlSelf = protocol + "//" + curWwwPath + "/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = curWwwPath;
} else if (curWwwPath == '10.231.85.40') {
	//上海银行内网地址1
	preUrl = "http://10.231.85.40:8081/ac-self";
	preUrlSelf = "http://10.231.85.40:8081/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = "10.231.85.40:8081";
} else if (curWwwPath == '10.201.85.12') {
	//上海银行内网地址2
	preUrl = "http://10.201.85.12:8081/ac-self";
	preUrlSelf = "http://10.201.85.12:8081/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = "10.201.85.12:8081";
} else {
	//互联网or测试
	preUrl = "http://183.194.250.112/ac-self";
	preUrlSelf = "http://183.194.250.112/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = "183.194.250.112";
}
jQuery.getConfigMsg = {
	preUrlSelf: preUrlSelf, //"http://180.169.7.194:8081/ac-self",
	preUrl: preUrl,
	preUrlSelfApi: preUrlSelfApi,
	//文山办事指南的url地址
	preUrlWS: "http://10.1.8.145:8080/ac-product-ext/ext/wenshan",
	//人证比对url地址
	preUrlFR: "http://192.168.1.123:8080/ac-product-ext/ext",
	logoIcon: "libs/common/images/logo.png",
	deviceId: "1",
	//是否开启市民云登录系统
	openCitizenCloudLogin: true,
	//支付参数
	isIdCopyPay: "0",
	idCopyPayMoney: 0.01,
	idCopyPayDesc: "政务自助服务",
	isMaterialCopyPay: "0",
	materialCopyPayMoney: 0.01,
	materialCopyPayDesc: "政务自助服务",
	//是否记录日志
	isPiwikLog: true,
	piwikvalue: '2',
	piwikurl: piwikurl,
	//是否是扩展的项目
	isextproduct: true,
	//扩展项目目录
	extproductpath: 'ext/pudong/',
	//办事档案是否需要企业查询
	iskeyshow: false,
	//无人审批接口,徐汇
	//NoApprovalUrl:"http://31.0.161.7:7700/ac-product-ext"
	NoApprovalUrl: "https://zwdtja.sh.gov.cn:8089/ac-product-ext",
	isCommunity: $.config.get("isCommunity"),
	isCommunityNew: "Y",
	isPrintBase64: "N",
	isShowSscard: true,
	ishttpUploadYH: false,
	isYHShowHtmlPrintButton: true,
};

function panduanIsPrintBase64() {
	try {
		if (JSON.parse(window.AppHost.getManage().getAppData('machineInfo')).MachineId) {
			//是否打印base64位字符串;
			jQuery.getConfigMsg.isPrintBase64 = 'Y';
			//是显示中行;
			jQuery.getConfigMsg.iszhdevice = true;
			//是否显示社保卡登录;
			jQuery.getConfigMsg.isShowSscard = false;
			//高拍仪银行上传
			jQuery.getConfigMsg.ishttpUploadYH = true;
			//是否显示打印html按钮;
			jQuery.getConfigMsg.isYHShowHtmlPrintButton = false;
		}
	} catch (e) {}
	try {
		if (window.external.AppKamfu == "kamfu") { //金赋
			jQuery.getConfigMsg.isYHShowHtmlPrintButton = false;
			jQuery.getConfigMsg.isShowSscard = false;
		}
	} catch (e) {}
	if (acBridgeMac.vendor() == 'jhdevice') {
		jQuery.getConfigMsg.isPrintBase64 = 'Y';
		jQuery.getConfigMsg.isShowSscard = false;
		jQuery.getConfigMsg.isYHShowHtmlPrintButton = false;
	} else if (acBridgeMac.vendor() == 'nhdevice') {
		jQuery.getConfigMsg.isPrintBase64 = 'Y';
		jQuery.getConfigMsg.isShowSscard = false;
		jQuery.getConfigMsg.ishttpUploadYH = true;
		jQuery.getConfigMsg.isYHShowHtmlPrintButton = false;
	} else if (acBridgeMac.vendor() == 'pfdevice') {
		jQuery.getConfigMsg.isPrintBase64 = 'Y';
		jQuery.getConfigMsg.isShowSscard = false;
		jQuery.getConfigMsg.isYHShowHtmlPrintButton = false;
	} else if (acBridgeMac.vendor() == 'jtdevice') {
		jQuery.getConfigMsg.isShowSscard = false;
		jQuery.getConfigMsg.isYHShowHtmlPrintButton = false;
	} else if (acBridgeMac.vendor() == 'ghdevice') {
		jQuery.getConfigMsg.isPrintBase64 = 'Y';
		jQuery.getConfigMsg.isShowSscard = false;
		jQuery.getConfigMsg.ishttpUploadYH = true;
		jQuery.getConfigMsg.isYHShowHtmlPrintButton = false;
	} else if (acBridgeMac.vendor() == 'nsdevice') {
		jQuery.getConfigMsg.isPrintBase64 = 'Y';
		jQuery.getConfigMsg.isShowSscard = false;
		jQuery.getConfigMsg.isYHShowHtmlPrintButton = false;
	}
	console.log("isPrintBase64:" + jQuery.getConfigMsg.isPrintBase64);
	console.log("isShowSscard:" + jQuery.getConfigMsg.isShowSscard);
}
panduanIsPrintBase64();
(function() {
	//获取打印地点配置信息
	$.config.load('Outlets', function(d) {
		jQuery.getConfigMsg.qutletsVal = decodeURI(d);
		console.log(jQuery.getConfigMsg.qutletsVal);
	});
	//获取区县
	$.config.load('districtAndCounty', function(e) {
		jQuery.getConfigMsg.districtAndCountyVal = decodeURI(e);
		console.log(jQuery.getConfigMsg.districtAndCountyVal);
	});
	//获取设备uniqueId
	$.config.load('uniqueId', function(f) {
		jQuery.getConfigMsg.uniqueId = f;
		console.log(jQuery.getConfigMsg.uniqueId);
	});
	//获取是否有离线码机器
	$.config.load('isLxmPrintMachine', function(g) {
		jQuery.getConfigMsg.isLxmPrintMachine = g;
		console.log(jQuery.getConfigMsg.isLxmPrintMachine);
	});
})();

function getMacInfo() {
	$.ajax({
		type: "get",
		url: $.getConfigMsg.preUrlSelfApi + "/infopub/deviceinfo/listMac.do", //http://10.81.16.56:8088
		dataType: "json",
		async: false,
		data: {
			stDeviceMac: jQuery.getConfigMsg.uniqueId || ""
		},
		success: function(dataJson) {
			console.log(dataJson)
			var address = "";
			try {
				address = dataJson.data[0].stDeviceAddress
			} catch (e) {}
			console.log(address.indexOf("长宁区") != -1)
			if (address.indexOf("长宁区") != -1) {
				jQuery.getConfigMsg.isCommunityNew = 'Y'
			} else {
				jQuery.getConfigMsg.isCommunityNew = 'N'

			}
		},
		error: function(err) {
			console.log(err);
		}
	});
}
//getMacInfo();
