//通过配置文件读取  是否有互联网环境
let preUrl;
let preUrlSelf;
let preUrlSelfApi;
var curWwwPath = window.document.location.hostname;
if(curWwwPath == "183.194.250.112") {
	//互联网地址
	preUrl = "http://183.194.250.112/ac-product";
	preUrlSelf = "http://183.194.250.112/ac-self";
	preUrlSelfApi = preUrlSelf;
} else if(curWwwPath == "10.81.16.56") {
	//政务网地址
	preUrl = "http://10.81.16.56:8080/ac-product";
	preUrlSelf = "http://10.81.16.56:8090/ac-self";
	preUrlSelfApi = "http://10.81.16.56:8088/ac-self-api";
} else if(curWwwPath == 'gst.sh.cegn.cn') {
	//建行内网地址
	preUrl = "http://gst.sh.cegn.cn:8080/ac-product";
	preUrlSelf = "http://gst.sh.cegn.cn:8090/ac-self";
	preUrlSelfApi = "http://gst.sh.cegn.cn:8088/ac-self-api";
} else {
	//互联网地址or测试地址
	preUrl = "http://183.194.250.112/ac-product"; // "http://180.169.7.194:8081/ac-product";//
	preUrlSelf = "http://183.194.250.112/ac-self"; //"http://180.169.7.194:8081/ac-self";//
	preUrlSelfApi = preUrlSelf;
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
	piwikurl: "http://180.169.148.92:8088/dcollect/dcollect/",
	//是否是扩展的项目
	isextproduct: true,
	//扩展项目目录
	extproductpath: 'ext/pudong/',
	//办事档案是否需要企业查询
	iskeyshow: false,
	//无人审批接口,徐汇
	//NoApprovalUrl:"http://31.0.161.7:7700/ac-product-ext"
	NoApprovalUrl: "http://192.168.1.153:7700/ac-product-ext",
	isCommunity: $.config.get("isCommunity")
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