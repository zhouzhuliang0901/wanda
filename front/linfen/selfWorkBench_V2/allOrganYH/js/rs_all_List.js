//各接口的API_ID参数	A：申请登记受理查询    B：审核结果查看     C：浏览全文接口      D：打印出证接口
var perjsonStr1 = [{
		"stuffName": "参加个人城镇基本养老保险缴费情况",
		"code": "310195323433",
		"type": "jfqk",
		"img": "social.png",
		"url": '../RS_endowInsurePay/index.html'
	}, {
		"stuffName": "个人享受城镇基本养老金情况",
		"code": "310195593409",
		"type": "yljqk",
		"img": "social.png",
		"url": '../RS_grxsczjblyjqk/index.html'
	}, {
		"stuffName": "上年度养老保险个人权益记录单",
		"type": "qyd",
		"code": "310192365426",
		"img": "social.png",
		"url": '../RS_lastYearEndowInsureRecord/index.html'
	},
	{
		"stuffName": "灵活就业人员电子对账单",
		"code": "310193163220",
		"type": "dzdzd",
		"img": "social.png",
		"url": '../RS_nimbleEmployBill/index.html'
	},
	{
		"stuffName": "转往外省市缴费凭证",
		"code": "312014501000",
		"type": "ydzy",
		"img": "social.png",
		"url": '../RS_zwwssjfpz/index.html'
	},
];
var perjsonStr2 = [{
		"stuffName": "城乡居保缴费情况",
		"code": "310199154354",
		"type": "cxjbjfqk",
		"img": "social.png",
		"url": '../RS_cxjbjfqk/index.html'
	},
	{
		"stuffName": "享受城乡居保养老金情况",
		"code": "310196434305",
		"type": "xsyljqk",
		"img": "social.png",
		"url": '../RS_cityBaseOldPension/index.html'
	},
	{
		"stuffName": "上年度养老保险个人权益记录单（城乡居保）",
		"code": "310191773194",
		"type": "cxjbqyd",
		"img": "social.png",
		"url": '../RS_lastYearEndowInsureRecord_cxjb/index.html'
	},
	{
		"stuffName": "业务审核办结情况",
		"code": "310199034560", //"",
		"type": "ywshbjqk",
		"img": "social.png",
		"url": '../RS_ywshjbqk/index.html'
	}
]
var perjsonStr3 = [{
	"stuffName": "居住证积分查询",
	"code": "xxxx",
	"type": "residencePermit",
	"img": "social.png",
	"url": '../RS_residenceIntegralQuery/index.html'
}, {
	"stuffName": "社保卡个人信息查询",
	"code": "RS_ssCardInfo",
	"type": "residencePermit",
	"img": "social.png",
	"url": '../RS_ssCardInfo/index.html'
}, {
	"stuffName": "养老金卡（折）调整",
	"code": "RS",
	"type": "residencePermit",
	"img": "social.png",
	"url": '../RS_PensionAdjustmentCardOrPassbook/index.html'
}, {
	"stuffName": "劳动者个人基本信息查询及维护",
	"code": "RS",
	"type": "residencePermit",
	"img": "social.png",
	"url": '../RS_queryWorkerInfo/index.html'
}, {
	"stuffName": "失业保险金申领",
	"code": "RS",
	"type": "residencePermit",
	"img": "social.png",
	"url": '../RS_unemploymentInsurance/index.html'
}, {
	"stuffName": "失业登记",
	"code": "RS",
	"type": "residencePermit",
	"img": "social.png",
	"url": '../RS_unemploymentRegistration/index.html'
}, {
	"stuffName": "灵活就业登记",
	"code": "flexibleEmployment",
	"type": "flexibleEmployment",
	"img": "social.png",
	"url": 'flexibleEmployment'
}, {
	"stuffName": "申请大龄失业就业岗位补贴",
	"code": "RS",
	"type": "residencePermit",
	"img": "social.png",
	"url": '../RS_unemploymentAllowance/index.html'
}, {
	"stuffName": "申请“就业困难人员”灵活就业社会保险费补贴",
	"code": "RS",
	"type": "residencePermit",
	"img": "social.png",
	"url": '../RS_unemploymentSubsidy/index.html'
}];
var flexibleEmployment = [{
	"stuffName": "本市户籍人员灵活就业登记",
	"code": "RS",
	"type": "../RS_flexibleEmploymentRegister_thisCity/index.html",
	"img": "social.png",
}, {
	"stuffName": "本市户籍人员退出灵活就业登记",
	"code": "RS",
	"type": "../RS_flexibleEmploymentExit_thisCity/index.html",
	"img": "social.png",
}, {
	"stuffName": "来沪人员灵活就业登记录入",
	"code": "RS",
	"type": "../RS_flexibleEmploymentRegister_otherCity/index.html",
	"img": "social.png",
}, {
	"stuffName": "来沪人员灵活就业登记中止",
	"code": "RS",
	"type": "../RS_flexibleEmploymentStop_otherCity/index.html",
	"img": "social.png",
}]