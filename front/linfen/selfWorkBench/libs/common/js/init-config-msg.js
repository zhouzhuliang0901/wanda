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
	piwikurl = "183.194.250.112";
} else if(curWwwPath == "10.81.16.56") {
	//政务网地址
	preUrl = "http://10.81.16.56:8080/ac-product";
	preUrlSelf = "http://10.81.16.56:8090/ac-self";
	preUrlSelfApi = preUrlSelf;
	piwikurl = "10.81.16.56:8080";
} else {
	//互联网地址								
	preUrl = "http://183.194.250.112/ac-product";
	preUrlSelf = "http://183.194.250.112/ac-self";
	piwikurl = "183.194.250.112";
	//测试地址
	//	preUrl ="http://180.169.7.194:8080/ac-product";
	//	preUrlSelf = "http://180.169.7.194:8081/ac-self";
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
	piwikurl: piwikurl,
	//是否是扩展的项目
	isextproduct: true,
	//扩展项目目录
	extproductpath: 'ext/pudong/',
	//办事档案是否需要企业查询
	iskeyshow: false,
	isCommunity: $.config.get("isCommunity")||'N'
};