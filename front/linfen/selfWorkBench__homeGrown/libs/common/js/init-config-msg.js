//通过配置文件读取  是否有互联网环境
var preUrl;
var preUrlSelf;
var preUrlSelfApi;
var curWwwPath = window.document.location.hostname;
var protocol = window.location.protocol;
if(curWwwPath == "10.81.16.56") {
	//政务网地址
	preUrl = "http://10.81.16.56:8080/ac-product";
	preUrlSelf = "http://10.81.16.56:8090/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = "10.81.16.56:8080";
} else if(curWwwPath == 'gst.sh.cegn.cn') {
	//建行内网地址
	preUrl = "http://gst.sh.cegn.cn:8080/ac-product";
	preUrlSelf = "http://gst.sh.cegn.cn:8090/ac-self";
	preUrlSelfApi = "http://gst.sh.cegn.cn:8088/ac-self-api";
	piwikurl = "http://gst.sh.cegn.cn:8080";
}else if(curWwwPath == "183.194.250.112" || curWwwPath == "zzzd.sh.gov.cn") {
	//互联网地址
	/*
	 * ip :http://183.194.250.112/
	 * 域名：http://zzzd.sh.gov.cn/
	 */
	preUrl = protocol+"//"+curWwwPath+"/ac-product";
	preUrlSelf = protocol+"//"+curWwwPath+"/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = curWwwPath;
}else{
	//互联网or测试
	preUrl = "http://183.194.250.112/ac-product";
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
	NoApprovalUrl: "http://192.168.1.153:7700/ac-product-ext",
	isCommunity: $.config.get("isCommunity"),
	isCommunityNew : "Y"
};

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
})();
function getMacInfo() {
	$.ajax({
		type: "get",
		url: $. getConfigMsg.preUrlSelfApi+"/infopub/deviceinfo/listMac.do", //http://10.81.16.56:8088
		dataType: "json",
		async:false,
		data: {
			stDeviceMac: jQuery.getConfigMsg.uniqueId||""
		},
		success: function(dataJson) {
			console.log(dataJson)
			var address = "";
			try{
				address = dataJson.data[0].stDeviceAddress
			}catch(e){}
			console.log(address.indexOf("长宁区") != -1)
			if(address.indexOf("长宁区") != -1){
				jQuery.getConfigMsg.isCommunityNew = 'Y'
			}else{
				jQuery.getConfigMsg.isCommunityNew = 'N'

			}
		},
		error: function(err) {
			console.log(err);
		}
	});
}
//getMacInfo();
