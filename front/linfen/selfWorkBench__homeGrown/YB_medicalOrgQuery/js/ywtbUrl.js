var perjsonStr = [{
		"stuffName": "参保人员待遇查询",
		"type": "treatment",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
	},
	{
		"stuffName": "医保个人信息",
		"type": "info",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
	},
	{
		"stuffName": "医保综合减负试算",
		"type": "reduce",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
	}, 
	{
		"stuffName": "办理门急诊就医记录册",
		"type": "bookMaking",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
	}, 
	{
		"stuffName": "医保就医明细查询",
		"type": "medicalDetails",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
	}, 
	{
		"stuffName": "帐户清算信息查询",
		"type": "accountSettlement",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
	}, 
	{
		"stuffName": "服务机构查询",
		"type": "services",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
	}, 
	{
		"stuffName": "医疗机构查询",
		"type": "medicalInstitution",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
	}, 
	{
		"stuffName": "办理医保网站密码变更",
		"type": "password",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
	}, 
	{
		"stuffName": "支付标准查询",
		"type": "payStand",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
	}, 
	{
		"stuffName": "医疗费用分担模拟计算器",
		"type": "calculator",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
	}
	//	, {
	//		"stuffName": "零星报销进度查询",
	//		"type": "reimbursement",
	//		"ywlx":"01",
	//		"img": "../libs/common/images/newIcon/YB.png",
	//	}, {
	//		"stuffName": "居保登记缴费进度查询",
	//		"type": "residentRegistration",
	//		"ywlx":"02",
	//		"img": "../libs/common/images/newIcon/YB.png",
	//	}, {
	//		"stuffName": "帮困登记缴费进度查询",
	//		"type": "helpRegistration",
	//		"ywlx":"03",
	//		"img": "../libs/common/images/newIcon/YB.png",
	//	}, {
	//		"stuffName": "帮困补助进度查询",
	//		"type": "helpSubsidy",
	//		"ywlx":"04",
	//		"img": "../libs/common/images/newIcon/YB.png",
	//	}, {
	//		"stuffName": "长护险申请进度查询",
	//		"type": "longTermInsurance",
	//		"ywlx":"05",
	//		"img": "../libs/common/images/newIcon/YB.png",
	//	}
];
var servicesList = [{
		"stuffName": "医保诊疗项目约定服务医院查询",
		"type": "ybzlydfw",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
	},
	{
		"stuffName": "长期护理保险护理机构查询",
		"type": "cqhlbxhl",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
	},
	{
		"stuffName": "商业保险机构网点信息查询",
		"type": "sybxjgwd",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
	}, {
		"stuffName": "本市开展异地就医的医院查询",
		"type": "bskzydjy",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
	}, {
		"stuffName": "医保及相关服务机构查询",
		"type": "ybxgfw",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
	}
];
//上海市区代码
var areaList = [{
		"areaCode": "",
		"areaName": "所有区"
	}, {
		"areaCode": "01",
		"areaName": "黄浦区"
	}, {
		"areaCode": "04",
		"areaName": "徐汇区"
	},
	{
		"areaCode": "05",
		"areaName": "长宁区"
	},
	{
		"areaCode": "06",
		"areaName": "静安区"
	}, {
		"areaCode": "07",
		"areaName": "普陀区"
	},
	{
		"areaCode": "08",
		"areaName": "闸北区"
	},
	{
		"areaCode": "09",
		"areaName": "虹口区"
	}, {
		"areaCode": "10",
		"areaName": "杨浦区"
	},
	{
		"areaCode": "11",
		"areaName": "闵行区"
	}, {
		"areaCode": "12",
		"areaName": "宝山区"
	},
	{
		"areaCode": "13",
		"areaName": "嘉定区"
	}, {
		"areaCode": "14",
		"areaName": "浦东新区"
	}, {
		"areaCode": "16",
		"areaName": "奉贤区"
	}, {
		"areaCode": "17",
		"areaName": "松江区"
	},
	{
		"areaCode": "18",
		"areaName": "金山区"
	},
	{
		"areaCode": "19",
		"areaName": "青浦区"
	},
	{
		"areaCode": "20",
		"areaName": "崇明区"
	},
	{
		"areaCode": "22",
		"areaName": "农场局"
	}
]
var areaList2 = [{
		"areaCode": "01",
		"areaName": "黄浦区"
	}, {
		"areaCode": "04",
		"areaName": "徐汇区"
	},
	{
		"areaCode": "05",
		"areaName": "长宁区"
	},
	{
		"areaCode": "06",
		"areaName": "静安区"
	}, {
		"areaCode": "07",
		"areaName": "普陀区"
	},
	{
		"areaCode": "08",
		"areaName": "闸北区"
	},
	{
		"areaCode": "09",
		"areaName": "虹口区"
	}, {
		"areaCode": "10",
		"areaName": "杨浦区"
	},
	{
		"areaCode": "11",
		"areaName": "闵行区"
	}, {
		"areaCode": "12",
		"areaName": "宝山区"
	},
	{
		"areaCode": "13",
		"areaName": "嘉定区"
	}, {
		"areaCode": "14",
		"areaName": "浦东新区"
	}, {
		"areaCode": "16",
		"areaName": "奉贤区"
	}, {
		"areaCode": "17",
		"areaName": "松江区"
	},
	{
		"areaCode": "18",
		"areaName": "金山区"
	},
	{
		"areaCode": "19",
		"areaName": "青浦区"
	},
	{
		"areaCode": "20",
		"areaName": "崇明区"
	},
	{
		"areaCode": "22",
		"areaName": "农场局"
	}
]
//医保约定项目
var ybydxm = [{
	"name": "冠状动脉疾病介入治疗",
	"id": "01",
}, {
	"name": "肾移植治疗",
	"id": "02",
}, {
	"name": "血液透析治疗",
	"id": "03",
}, {
	"name": "医用高压氧治疗",
	"id": "04",
}, {
	"name": "恶性肿瘤放射治疗",
	"id": "05",
}, {
	"name": "造血干细胞移植治疗",
	"id": "06",
}, {
	"name": "人工心脏起搏器植入治疗",
	"id": "07",
}, {
	"name": "人工关节置换治疗",
	"id": "08",
}, {
	"name": "磁共振扫描装置",
	"id": "09",
}, {
	"name": "大型新增及血管造影数字减影装置",
	"id": "10",
}];
//医院分类
var hospitalList = [{
	"name": "综合医院",
	"id": "11",
}, {
	"name": "中医医院",
	"id": "12",
}, {
	"name": "中西医结合医院",
	"id": "13",
}, {
	"name": "专科医院",
	"id": "15",
}, {
	"name": "疗养院",
	"id": "16",
}, {
	"name": "社区卫生服务中心",
	"id": "21",
}, {
	"name": "门诊部",
	"id": "41",
}, {
	"name": "诊所",
	"id": "42",
}, {
	"name": "医务室",
	"id": "44",
}, {
	"name": "妇幼保健院（所、站）",
	"id": "70",
}, {
	"name": "妇幼保健院",
	"id": "71",
}, {
	"name": "妇幼保健站",
	"id": "73",
}, {
	"name": "专科疾病防治院（所、站）",
	"id": "80",
}, {
	"name": "专科疾病防治院",
	"id": "81",
}];

//医院结算等级
var reduceLevel = [{
	"name": "三级特等",
	"id": "01",
}, {
	"name": "三级甲等",
	"id": "02",
}, {
	"name": "三级乙等",
	"id": "03",
}, {
	"name": "三级丙等",
	"id": "04",
}, {
	"name": "二级甲等",
	"id": "05",
}, {
	"name": "二级乙等",
	"id": "06",
}, {
	"name": "二级丙等",
	"id": "07",
}, {
	"name": "一级甲等",
	"id": "08",
}, {
	"name": "一级乙等",
	"id": "09",
}, {
	"name": "一级丙等",
	"id": "10",
}];

//参保人员待遇查询
var medicalType = [{
	"name": "上海市城镇职工基本医疗保险",
	"id": "01",
}, {
	"name": "城乡居民基本医疗保险",
	"id": "02",
}, {
	"name": "城镇职工基本医疗保险综合减负",
	"id": "03",
}, {
	"name": "市民社区医疗互助帮困计划",
	"id": "04",
}];

var treatmentDetials = [{
	"name": "退休老人",
	"pid": "01",
	"guideline": "<p class='top'>【原退休“老人”】</p><p>2000年12月31日前已办理退休手续的退休人员</p><p>【个人帐户计入标准】</p><p>--退休至74岁以下的为1680元</p><p>--75岁以上的为1890元</p>" +
		"<p class='top'>【门急诊】</p><p>先由个人帐户当年计入资金支付，用完后，本人负担300元，超过本人负担部分发生的医疗费用参照如下:</p>" +
		"<table cellspacing='0'><tr><th>医院等级</th><th>个人自负</th><th>地方附加医疗保险基金支付</th></tr><tr><td>一级</td><td>10%</td><td>90%</td></tr><tr><td>二级</td><td>15%</td><td>85%</td></tr><tr><td>三级</td><td>20%</td><td>80%</td></tr></table>" +
		"<p>如个人账户有历年结余资金的，可以抵冲“自负段”和“共付段”的个人自负部分，不足部分由个人现金自负。</p>" +
		"<p class='top'>【住院、急诊观察室留院观察】</p><p>--起付标准为700元</p><p>--起付标准以上、最高支付限额以下的医疗费用由统筹基金支付92%，个人支付8%</p><p>--起付标准以下的医疗费用以及由统筹基金支付后其余部分的医疗费用，由个人帐户历年结余资金支付，不足部分由个人现金自负。</p>" +
		"<p class='top'>【门诊大病和家庭病床】</p><p>--不设起付标准</p><p>--最高支付限额以下的医疗费用:</p><p style='margin-left: 35px;'>  门诊大病由统筹基金支付92%，个人支付8%</p><p style='margin-left: 35px;'>  家庭病床由统筹基金支付80%，个人支付20%</p>" +
		"<p>--由统筹基金支付后其余的部分医疗费用，由个人帐户历年结余资金支付，不足部分由个人现金自负。</p>" +
		"<p class='top'>【最高支付限额】</p><p>--最高支付限额为53万元</p><p>--最高支付限额以上的医疗费用由地方附加医疗保险附加基金支付80%，个人自负20%。</p>"
}, {
	"name": "退休中人一档",
	"pid": "01",
	"guideline": "<p class='top'>【原退休“中一”人员】</p><p>1955年12月31日前出生、在2000年12月31日前参加工作并在2001年1月1日后办理退休手续的退休人员</p><p>【个人帐户计入标准】</p><p>--退休至74岁以下的为1680元</p><p>--75岁以上的为1890元</p>" +
		"<p class='top'>【门急诊】</p><p>先由个人帐户当年计入资金支付，用完后，本人负担700元，超过本人负担部分发生的医疗费用参照如下:</p>" +
		"<table cellspacing='0'><tr><th>医院等级</th><th>个人自负</th><th>地方附加医疗保险基金支付</th></tr><tr><td>一级</td><td>15%</td><td>85%</td></tr><tr><td>二级</td><td>20%</td><td>80%</td></tr><tr><td>三级</td><td>25%</td><td>75%</td></tr></table>" +
		"<p>如个人账户有历年结余资金的，可以抵冲“自负段”和“共付段”的个人自负部分，不足部分由个人现金自负。</p>" +
		"<p class='top'>【住院、急诊观察室留院观察】</p><p>--起付标准为1200元</p><p>--起付标准以上、最高支付限额以下的医疗费用由统筹基金支付92%，个人支付8%</p><p>--起付标准以下的医疗费用以及由统筹基金支付后其余部分的医疗费用，由个人帐户历年结余资金支付，不足部分由个人现金自负。</p>" +
		"<p class='top'>【门诊大病和家庭病床】</p><p>--不设起付标准</p><p>--最高支付限额以下的医疗费用:</p><p style='margin-left: 35px;'>  门诊大病由统筹基金支付92%，个人支付8%</p><p style='margin-left: 35px;'>  家庭病床由统筹基金支付80%，个人支付20%</p>" +
		"<p>--由统筹基金支付后其余的部分医疗费用，由个人帐户历年结余资金支付，不足部分由个人现金自负。</p>" +
		"<p class='top'>【最高支付限额】</p><p>--最高支付限额为53万元</p><p>--最高支付限额以上的医疗费用由地方附加医疗保险附加基金支付80%，个人自负20%。</p>"
}, {
	"name": "退休人员70岁以上",
	"pid": "01",
	"guideline": "<p class='top'>【70岁以上退休人员】</p><p>70岁以上退休人员（不含2000年12月31日前已办理退休手续的退休“老人”和1955年12月31日前出生、在2000年12月31日前参加工作并在2001年1月1日后办理退休手续的退休“中一”人员）</p>" +
		"<p class='top'>【个人帐户计入标准】</p><p>--退休至74岁以下的为1680元</p><p>--75岁以上的为1890元</p>" +
		"<p class='top'>【门急诊】</p><p>先由个人帐户当年计入资金支付，用完后，本人负担700元，超过本人负担部分发生的医疗费用参照如下:</p>" +
		"<table cellspacing='0'><tr><th>医院等级</th><th>个人自负</th><th>地方附加医疗保险基金支付</th></tr><tr><td>一级</td><td>15%</td><td>85%</td></tr><tr><td>二级</td><td>20%</td><td>80%</td></tr><tr><td>三级</td><td>25%</td><td>75%</td></tr></table>" +
		"<p>如个人账户有历年结余资金的，可以抵冲“自负段”和“共付段”的个人自负部分，不足部分由个人现金自负。</p>" +
		"<p class='top'>【住院、急诊观察室留院观察】</p><p>--起付标准为1200元</p><p>--起付标准以上、最高支付限额以下的医疗费用由统筹基金支付92%，个人支付8%</p><p>--起付标准以下的医疗费用以及由统筹基金支付后其余部分的医疗费用，由个人帐户历年结余资金支付，不足部分由个人现金自负。</p>" +
		"<p class='top'>【门诊大病和家庭病床】</p><p>--不设起付标准</p><p>--最高支付限额以下的医疗费用:</p><p style='margin-left: 35px;'>  门诊大病由统筹基金支付92%，个人支付8%</p><p style='margin-left: 35px;'>  家庭病床由统筹基金支付80%，个人支付20%</p>" +
		"<p>--由统筹基金支付后其余的部分医疗费用，由个人帐户历年结余资金支付，不足部分由个人现金自负。</p>" +
		"<p class='top'>【最高支付限额】</p><p>--最高支付限额为53万元</p><p>--最高支付限额以上的医疗费用由地方附加医疗保险附加基金支付80%，个人自负20%。</p>"
}, {
	"name": "退休人员69岁以下",
	"pid": "01",
	"guideline": "<p class='top'>【69岁以下退休人员】</p><p>69岁以下退休人员（不含2000年12月31日前已办理退休手续的退休“老人”和1955年12月31日前出生、在2000年12月31日前参加工作并在2001年1月1日后办理退休手续的退休“中一”人员）</p>" +
		"<p class='top'>【个人帐户计入标准】</p><p>1680元</p>" +
		"<p class='top'>【门急诊】</p><p>先由个人帐户当年计入资金支付，用完后，本人负担700元，超过本人负担部分发生的医疗费用参照如下:</p>" +
		"<table cellspacing='0'><tr><th>医院等级</th><th>个人自负</th><th>地方附加医疗保险基金支付</th></tr><tr><td>一级</td><td>20%</td><td>80%</td></tr><tr><td>二级</td><td>25%</td><td>75%</td></tr><tr><td>三级</td><td>30%</td><td>70%</td></tr></table>" +
		"<p>如个人账户有历年结余资金的，可以抵冲“自负段”和“共付段”的个人自负部分，不足部分由个人现金自负。</p>" +
		"<p class='top'>【住院、急诊观察室留院观察】</p><p>--起付标准为1200元</p><p>--起付标准以上、最高支付限额以下的医疗费用由统筹基金支付92%，个人支付8%</p><p>--起付标准以下的医疗费用以及由统筹基金支付后其余部分的医疗费用，由个人帐户历年结余资金支付，不足部分由个人现金自负。</p>" +
		"<p class='top'>【门诊大病和家庭病床】</p><p>--不设起付标准</p><p>--最高支付限额以下的医疗费用:</p><p style='margin-left: 35px;'>  门诊大病由统筹基金支付92%，个人支付8%</p><p style='margin-left: 35px;'>  家庭病床由统筹基金支付80%，个人支付20%</p>" +
		"<p>--由统筹基金支付后其余的部分医疗费用，由个人帐户历年结余资金支付，不足部分由个人现金自负。</p>" +
		"<p class='top'>【最高支付限额】</p><p>--最高支付限额为53万元</p><p>--最高支付限额以上的医疗费用由地方附加医疗保险附加基金支付80%，个人自负20%。</p>"
}, {
	"name": "在职中人一档",
	"pid": "01",
	"guideline": "<p class='top'>【原在职“中一”人员】</p><p>1955年12月31日前出生、2000年12月31日前参加工作的在职职工</p>" +
		"<p class='top'>【个人帐户计入标准】</p><p>个人账户个人缴费预计入部分按当年2月份职工本人实际缴费额为基数计算计入，加单位缴费计入部分630元。次年3月按实际缴费进行清算，多扣少补。</p>" +
		"<p class='top'>【门急诊】</p><p>	先由个人帐户当年计入资金支付，用完后，本人负担1500元，超过本人负担部分发生的医疗费用参照如下:</p>" +
		"<table cellspacing='0'><tr><th>医院等级</th><th>个人自负</th><th>地方附加医疗保险基金支付</th></tr><tr><td>一级</td><td>25%</td><td>75%</td></tr><tr><td>二级</td><td>25%</td><td>75%</td></tr><tr><td>三级</td><td>30%</td><td>70%</td></tr></table>" +
		"<p>如个人账户有历年结余资金的，可以抵冲“自负段”和“共付段”的个人自负部分，不足部分由个人现金自负。</p>" +
		"<p class='top'>【住院、急诊观察室留院观察】</p><p>--起付标准为1500元</p><p>--起付标准以上、最高支付限额以下的医疗费用由统筹基金支付85%，个人支付15%</p><p>--起付标准以下的医疗费用以及由统筹基金支付后其余部分的医疗费用，由个人帐户历年结余资金支付，不足部分由个人现金自负。</p>" +
		"<p class='top'>【门诊大病和家庭病床】</p><p>--不设起付标准</p><p>--最高支付限额以下的医疗费用:</p><p style='margin-left: 35px;'>  门诊大病由统筹基金支付85%，个人支付15%</p><p style='margin-left: 35px;'>  家庭病床由统筹基金支付80%，个人支付20%</p>" +
		"<p>--由统筹基金支付后其余的部分医疗费用，由个人帐户历年结余资金支付，不足部分由个人现金自负。</p>" +
		"<p class='top'>【最高支付限额】</p><p>--最高支付限额为53万元</p><p>--最高支付限额以上的医疗费用由地方附加医疗保险附加基金支付80%，个人自负20%。</p>"
}, {
	"name": "在职45岁以上",
	"pid": "01",
	"guideline": "<p class='top'>【45岁以上在职职工】</p><p>45岁以上在职职工（不含1955年12月31日前出生、2000年12月31日前参加工作的原在职“中一”人员）</p>" +
		"<p class='top'>【个人帐户计入标准】</p><p>个人账户个人缴费预计入部分按当年2月份职工本人实际缴费额为基数计算计入，加单位缴费计入部分630元。次年3月按实际缴费进行清算，多扣少补。</p>" +
		"<p class='top'>【门急诊】</p><p>	先由个人帐户当年计入资金支付，用完后，本人负担1500元，超过本人负担部分发生的医疗费用参照如下:</p>" +
		"<table cellspacing='0'><tr><th>医院等级</th><th>个人自负</th><th>地方附加医疗保险基金支付</th></tr><tr><td>一级</td><td>25%</td><td>75%</td></tr><tr><td>二级</td><td>30%</td><td>70%</td></tr><tr><td>三级</td><td>40%</td><td>60%</td></tr></table>" +
		"<p>如个人账户有历年结余资金的，可以抵冲“自负段”和“共付段”的个人自负部分，不足部分由个人现金自负。</p>" +
		"<p class='top'>【住院、急诊观察室留院观察】</p><p>--起付标准为1500元</p><p>--起付标准以上、最高支付限额以下的医疗费用由统筹基金支付85%，个人支付15%</p><p>--起付标准以下的医疗费用以及由统筹基金支付后其余部分的医疗费用，由个人帐户历年结余资金支付，不足部分由个人现金自负。</p>" +
		"<p class='top'>【门诊大病和家庭病床】</p><p>--不设起付标准</p><p>--最高支付限额以下的医疗费用:</p><p style='margin-left: 35px;'>  门诊大病由统筹基金支付85%，个人支付15%</p><p style='margin-left: 35px;'>  家庭病床由统筹基金支付80%，个人支付20%</p>" +
		"<p>--由统筹基金支付后其余的部分医疗费用，由个人帐户历年结余资金支付，不足部分由个人现金自负。</p>" +
		"<p class='top'>【最高支付限额】</p><p>--最高支付限额为53万元</p><p>--最高支付限额以上的医疗费用由地方附加医疗保险附加基金支付80%，个人自负20%。</p>"
}, {
	"name": "在职44岁以下",
	"pid": "01",
	"guideline": "<p class='top'>【44岁以下在职职工】</p><p>44岁以下在职职工</p>" +
		"<p class='top'>【个人帐户计入标准】</p><p>个人账户个人缴费预计入部分按当年2月份职工本人实际缴费额为基数计算计入，加单位缴费计入部分：34岁以下为210元，35岁至44岁为420元。次年3月按实际缴费进行清算，多扣少补。</p>" +
		"<p class='top'>【门急诊】</p><p>	先由个人帐户当年计入资金支付，用完后，本人负担1500元，超过本人负担部分发生的医疗费用参照如下:</p>" +
		"<table cellspacing='0'><tr><th>医院等级</th><th>个人自负</th><th>地方附加医疗保险基金支付</th></tr><tr><td>一级</td><td>35%</td><td>65%</td></tr><tr><td>二级</td><td>40%</td><td>60%</td></tr><tr><td>三级</td><td>50%</td><td>50%</td></tr></table>" +
		"<p>如个人账户有历年结余资金的，可以抵冲“自负段”和“共付段”的个人自负部分，不足部分由个人现金自负。</p>" +
		"<p class='top'>【住院、急诊观察室留院观察】</p><p>--起付标准为1500元</p><p>--起付标准以上、最高支付限额以下的医疗费用由统筹基金支付85%，个人支付15%</p><p>--起付标准以下的医疗费用以及由统筹基金支付后其余部分的医疗费用，由个人帐户历年结余资金支付，不足部分由个人现金自负。</p>" +
		"<p class='top'>【门诊大病和家庭病床】</p><p>--不设起付标准</p><p>--最高支付限额以下的医疗费用:</p><p style='margin-left: 35px;'>  门诊大病由统筹基金支付85%，个人支付15%</p><p style='margin-left: 35px;'>  家庭病床由统筹基金支付80%，个人支付20%</p>" +
		"<p>--由统筹基金支付后其余的部分医疗费用，由个人帐户历年结余资金支付，不足部分由个人现金自负。</p>" +
		"<p class='top'>【最高支付限额】</p><p>--最高支付限额为53万元</p><p>--最高支付限额以上的医疗费用由地方附加医疗保险附加基金支付80%，个人自负20%。</p>"
}, {
	"name": "70周岁以上人员",
	"pid": "02",
	"guideline": "<p class='top'>【70周岁以上】</p><p>--70周岁以上人员</p><p>--不设个人医疗账户</p><p>--年缴费390元</p>" +
		"<p class='top'>【门急诊（含家庭病床）】</p><p>--门急诊起付标准300元</p><p>--村卫生室不计起付标准</p><p>--起付标准以下的医疗费用，由个人支付</p>" +
		"<p>--超过起付标准以上的医疗费用，参照如下：</p>" +
		"<table cellspacing='0'><tr><th>医院等级</th><th>个人自负</th><th>居保基金支付</th></tr><tr><td>一级</td><td>30%</td><td>70%</td></tr><tr><td>二级</td><td>40%</td><td>60%</td></tr><tr><td>三级</td><td>50%</td><td>50%</td></tr><tr><td>村卫生室</td><td>20%</td><td>80%</td></tr></table>" +
		"<p class='top'>【住院（含急诊观察室留院观察）】</p><p>---每次住院发生的医疗费用设起付标准，具体为：一级医院50元，二级医院100元，三级医院300元</p><p>--起付标准以下的医疗费用，由个人支付</p>" +
		"<p>--超过起付标准以上的医疗费用，参照如下：</p>" +
		"<table cellspacing='0'><tr><th>医院等级</th><th>个人自负</th><th>居保基金支付</th></tr><tr><td>一级</td><td>10%</td><td>90%</td></tr><tr><td>二级</td><td>20%</td><td>80%</td></tr><tr><td>三级</td><td>30%</td><td>70%</td></tr></table>"
}, {
	"name": "60-69周岁人员",
	"pid": "02",
	"guideline": "<p class='top'>【60周岁以上、不满70周岁的人员】</p><p>--60周岁以上、不满70周岁人员</p><p>--不设个人医疗账户</p><p>--年缴费555元</p>" +
		"<p class='top'>【门急诊（含家庭病床）】</p><p>--门急诊起付标准300元</p><p>--村卫生室不计起付标准</p><p>--起付标准以下的医疗费用，由个人支付</p>" +
		"<p>--超过起付标准以上的医疗费用，参照如下：</p>" +
		"<table cellspacing='0'><tr><th>医院等级</th><th>个人自负</th><th>居保基金支付</th></tr><tr><td>一级</td><td>30%</td><td>70%</td></tr><tr><td>二级</td><td>40%</td><td>60%</td></tr><tr><td>三级</td><td>50%</td><td>50%</td></tr><tr><td>村卫生室</td><td>20%</td><td>80%</td></tr></table>" +
		"<p class='top'>【住院（含急诊观察室留院观察）】</p><p>---每次住院发生的医疗费用设起付标准，具体为：一级医院50元，二级医院100元，三级医院300元</p><p>--起付标准以下的医疗费用，由个人支付</p>" +
		"<p>--超过起付标准以上的医疗费用，参照如下：</p>" +
		"<table cellspacing='0'><tr><th>医院等级</th><th>个人自负</th><th>居保基金支付</th></tr><tr><td>一级</td><td>10%</td><td>90%</td></tr><tr><td>二级</td><td>20%</td><td>80%</td></tr><tr><td>三级</td><td>30%</td><td>70%</td></tr></table>"
}, {
	"name": "19-59周岁人员",
	"pid": "02",
	"guideline": "<p class='top'>【18周岁以上、不满60周岁的人员】</p><p>--18周岁以上、不满60周岁人员</p><p>--不设个人医疗账户</p><p>--年缴费740元</p>" +
		"<p class='top'>【门急诊（含家庭病床）】</p><p>--门急诊起付标准500元</p><p>--村卫生室不计起付标准</p><p>--起付标准以下的医疗费用，由个人支付</p>" +
		"<p>--超过起付标准以上的医疗费用，参照如下：</p>" +
		"<table cellspacing='0'><tr><th>医院等级</th><th>个人自负</th><th>居保基金支付</th></tr><tr><td>一级</td><td>30%</td><td>70%</td></tr><tr><td>二级</td><td>40%</td><td>60%</td></tr><tr><td>三级</td><td>50%</td><td>50%</td></tr><tr><td>村卫生室</td><td>20%</td><td>80%</td></tr></table>" +
		"<p class='top'>【住院（含急诊观察室留院观察）】</p><p>---每次住院发生的医疗费用设起付标准，具体为：一级医院50元，二级医院100元，三级医院300元</p><p>--起付标准以下的医疗费用，由个人支付</p>" +
		"<p>--超过起付标准以上的医疗费用，参照如下：</p>" +
		"<table cellspacing='0'><tr><th>医院等级</th><th>个人自负</th><th>居保基金支付</th></tr><tr><td>一级</td><td>20%</td><td>80%</td></tr><tr><td>二级</td><td>25%</td><td>75%</td></tr><tr><td>三级</td><td>40%</td><td>60%</td></tr></table>"
}, {
	"name": "大学生",
	"pid": "02",
	"guideline": "<p class='top'>【全日制本科学生、高职高专学生以及非在职研究生】</p><p>--大学生 </p><p>--不设个人医疗账户</p><p>--年缴费130元</p>" +
		"<p class='top' >【门急诊】</p><p>--校内门诊：校内门诊发生的医疗费用，由各院校按照不低于90%支付，其余部分由个人自负</p>" +
		"<p>--校门外门诊：按照居保中小学生门急诊待遇支付，具体为：门急诊医疗费用设置起付线300元，年累计超过起付线以上的部分，参照如下：</p>" +
		"<table cellspacing='0'><tr><th>医院等级</th><th>个人自负</th><th>居保基金支付</th></tr><tr><td>一级</td><td>30%</td><td>70%</td></tr><tr><td>二级</td><td>40%</td><td>60%</td></tr><tr><td>三级</td><td>50%</td><td>50%</td></tr><tr><td>村卫生室</td><td>20%</td><td>80%</td></tr></table>" +
		"<p class='top'>【住院（含急诊观察室留院观察）】</p><p>---每次住院发生的医疗费用设起付标准，具体为：一级医院50元，二级医院100元，三级医院300元</p><p>--起付标准以下的医疗费用，由个人支付</p>" +
		"<p>--超过起付标准以上的医疗费用，参照如下：</p>" +
		"<table cellspacing='0'><tr><th>医院等级</th><th>个人自负</th><th>居保基金支付</th></tr><tr><td>一级</td><td>20%</td><td>80%</td></tr><tr><td>二级</td><td>25%</td><td>75%</td></tr><tr><td>三级</td><td>40%</td><td>60%</td></tr></table>"
}, {
	"name": "中小学生和婴幼儿",
	"pid": "02",
	"guideline": "<p class='top'>【中小学生和婴幼儿】</p><p>--中小学生和婴幼儿</p><p>--不设个人医疗账户</p><p>--年缴费130元</p>" +
		"<p class='top'>【门急诊（含家庭病床）】</p><p>--门急诊起付标准300元</p><p>--村卫生室不计起付标准</p><p>--起付标准以下的医疗费用，由个人支付</p>" +
		"<p>--超过起付标准以上的医疗费用，参照如下：</p>" +
		"<table cellspacing='0'><tr><th>医院等级</th><th>个人自负</th><th>居保基金支付</th></tr><tr><td>一级</td><td>30%</td><td>70%</td></tr><tr><td>二级</td><td>40%</td><td>60%</td></tr><tr><td>三级</td><td>50%</td><td>50%</td></tr><tr><td>村卫生室</td><td>20%</td><td>80%</td></tr></table>" +
		"<p class='top'>【住院（含急诊观察室留院观察）】</p><p>---每次住院发生的医疗费用设起付标准，具体为：一级医院50元，二级医院100元，三级医院300元</p><p>--起付标准以下的医疗费用，由个人支付</p>" +
		"<p>--超过起付标准以上的医疗费用，参照如下：</p>" +
		"<table cellspacing='0'><tr><th>医院等级</th><th>个人自负</th><th>居保基金支付</th></tr><tr><td>一级</td><td>20%</td><td>80%</td></tr><tr><td>二级</td><td>25%</td><td>75%</td></tr><tr><td>三级</td><td>40%</td><td>60%</td></tr></table>"
}, {
	"name": "外地医保落实人员",
	"pid": "03",
	"guideline": "<p class='top'>【外地医保落实人员】</p><p>--帮困计划</p><p>--外地医保落实人员</p><p>--年缴费130元</p>" +
		"<p class='top'>【门急高额自负医疗费补助】</p><p>--门诊医疗互助帮困补贴每人每年150元</p>" +
		"<p>--门诊医疗互助帮困补贴用完后，门急诊医疗费用个人现金自负年累计标准为500元，超过部分的医疗费用参照如下：</p>" +
		"<table cellspacing='0'><tr><th>医院等级</th><th>个人自负</th><th>居保基金支付</th></tr><tr><td>一级</td><td>15%</td><td>85%</td></tr><tr><td>二级</td><td>20%</td><td>80%</td></tr><tr><td>三级</td><td>25%</td><td>75%</td></tr></table>" +
		"<p class='top'>【住院高额自负医疗费补助】</p><p>---扣除当地医保起付标准以下的医疗费用和已报销的医疗费用后，人自付部分补助60%</p><p>--医疗互助对象当年累计住院医疗费，进行住院医疗互助帮困补助后，个人实际自负住院医疗费不得低于住院医疗总费用的8%,低于8%的部分不予补助。</p>" +
		"<p class='top'>【社区居家照护和养老机构照护补助】</p><p>--享受条件、申办流程、评估认定、服务内容及待遇等,比照本市长期护理保险的相关政策执行。</p>"
}, {
	"name": "外地医保不落实人员",
	"pid": "03",
	"guideline": "<p class='top'>【外地医保不落实人员】</p><p>--年缴费130元</p>" +
		"<p class='top'>【门急高额自负医疗费补助】</p><p>--门诊医疗互助帮困补贴每人每年150元</p>" +
		"<p>--门诊医疗互助帮困补贴用完后，门急诊医疗费用个人现金自负年累计标准为500元，超过部分的医疗费用参照如下：</p>" +
		"<table cellspacing='0'><tr><th>医院等级</th><th>个人自负</th><th>居保基金支付</th></tr><tr><td>一级</td><td>15%</td><td>85%</td></tr><tr><td>二级</td><td>20%</td><td>80%</td></tr><tr><td>三级</td><td>25%</td><td>75%</td></tr></table>" +
		"<p class='top'>【住院高额自负医疗费补助】</p><p>--扣除起付标准1000元以下的医疗费用和已报销的医疗费用后，个人自付部分补助50%</p><p>--医疗互助对象当年累计住院医疗费,进行住院医疗互助帮困补助后，个人实际自负住院医疗费不得低于住院医疗总费用的8%,低于8%的部分不予补助。</p>" +
		"<p class='top'>【社区居家照护和养老机构照护补助】</p><p>--享受条件、申办流程、评估认定、服务内容及待遇等,比照本市长期护理保险的相关政策执行。</p>"
}, {
	"name": "退休人员",
	"pid": "04",
	"guideline": "<p class='top'>【退休人员】</p><p style='font-weight: bold;'>精减退职回乡老职工，其医保综合减负年收入按本市最低生活保障标准计算</p><p>年自负医疗费累计超过本市上年度职工最低生活保障标准25%以上的部分,可申请减负90%的自负医疗费。</p>" +
		"<p>------------------------------------------------------------------------------------------------------------------------------------------------------------------</p>" +
		"<p style='font-weight: bold;'>退休人员年养老金在本市上年度职工最低工资标准80%及以下的，医保综合减负年收入按最低工资标准80%计算</p><p>年自负医疗费累计超过本市.上年度职工最低生活保障标准80%以上的25%的部分，可申请减负90%的自负医疗费。</p>" +
		"<p>------------------------------------------------------------------------------------------------------------------------------------------------------------------</p>" +
		"<p style='font-weight: bold;'>退休人员年养老金在本市上年度职工最低工资标准80%至最低工资标准之间的，医保综合减负年收入按实际年养老金计算</p><p>年自负医疗费累计超过本人年养老金25%以上的部分，可申请减负90%的自负医疗费。</p>" +
		"<p>------------------------------------------------------------------------------------------------------------------------------------------------------------------</p>" +
		"<p style='font-weight: bold;'>退休人员年养老金在本市上年度职工最低工资标准以上的，医保综合减负年收入按实际年养老金计算</p><p>年自负医疗费累计超过本人年养老金30%以上的部分,可申请减负90%的自负医疗费。</p>" +
		"<p class='top'>【年自负医疗费】</p><p>是指在一个医保年度内，按照本市基本医疗保险规定现金自负的医疗费，即符合基本医疗保险诊疗项目、医疗服务设施和用药范围预计支付标准的医疗费中按规定由个人现金自负的医疗费。</p>" +
		"<p style='font-weight: bold;'>包括：</p><p>--诊疗项目分类自负的医疗费用、B等病房分类自负的床位费,以及使用本市基本医疗保险药品目录中乙类药品分类自负的药品费用</p>" +
		"<p style='font-weight: bold;'>不包括：</p><p>--按照本市医疗保险其他减负规定的医疗费;</p><p>--按照市人民政府《关于本市实施公务员医疗补助试运行意见》规定补助的医疗费</p><p>--按照市总工会医疗互助保障计划规定给付的医疗费</p>"
}, {
	"name": "在职职工",
	"pid": "04",
	"guideline": "<p class='top'>【在职职工】</p><p style='font-weight: bold;'>因患大病或大部分丧失劳动能力原因无法就业的协保人员，医院综合减负年收入按本市最低生活保障标准计算。</p><p>凡符合条件的协保人员年自负医疗费累计超过本市上年度职工最低生活保障标准25%以上的部分，可以申请减负90%的自负医疗费。</p>" +
		"<p>------------------------------------------------------------------------------------------------------------------------------------------------------------------</p>" +
		"<p style='font-weight: bold;'>在职职工年收入在本市上年度职工最低工资标准80%及以下的，医保综合减负年收入按最低工资标准80%计算。</p><p>年自负医疗费累计超过本市上年度职工最低生活保障标准80%以上的25%的部分，可申请减负90%的自负医疗费。</p>" +
		"<p>------------------------------------------------------------------------------------------------------------------------------------------------------------------</p>" +
		"<p style='font-weight: bold;'>在职职工年收入在本市上年度职工最低工资标准80%至最低工资标准之间的，医保综合减负年收入按实际年收入计算。</p><p>年自负医疗费累计超过本人年收入的25%以上的部分，可申请减负90%的自负医疗费。</p>" +
		"<p>------------------------------------------------------------------------------------------------------------------------------------------------------------------</p>" +
		"<p style='font-weight: bold;'>在职职工年收入在本市.上年度职工最低工资标准以.上的、本市上年度职工年平均工资1.5倍以下的,医保综合减负年收入按实际年收入计算。</p><p>年自负医疗费累计超过本人年收入的30%以上的部分，可申请减负90%的自负医疗费。</p>" +
		"<p>------------------------------------------------------------------------------------------------------------------------------------------------------------------</p>" +
		"<p style='font-weight: bold;'>在职职工年收入在本市.上年度职工年平均工资1.5倍以上、3倍以下的，医保综合减负年收入按实际年收入计算。</p><p>年自负医疗费累计超过本人年收入的40%以上的部分，可申请减负90%的自负医疗费。</p>" +
		"<p class='top'>【年自负医疗费】</p><p>是指在一个医保年度内，按照本市基本医疗保险规定现金自负的医疗费，即符合基本医疗保险诊疗项目、医疗服务设施和用药范围预计支付标准的医疗费中，按规定由个人现金自负的医疗费</p>" +
		"<p style='font-weight: bold;'>包括：</p><p>--诊疗项目分类自负的医疗费用、B等病房分类自负的床位费,以及使用本市基本医疗保险药品目录中乙类药品分类自负的药品费用</p>" +
		"<p style='font-weight: bold;'>不包括：</p><p>--按照本市医疗保险其他减负规定的医疗费;</p><p>--按照市人民政府《关于本市实施公务员医疗补助试运行意见》规定补助的医疗费</p><p>--按照市总工会医疗互助保障计划规定给付的医疗费</p><p>--其他不属于减负范围的费用。</p>"
}]

var photo = "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";