let perjsonStr = []
if($.getConfigMsg.isCommunity == 'Y' || $.getConfigMsg.isCommunity == null || $.getConfigMsg.isCommunity ==  undefined) {
	perjsonStr = [{
		"itemName": "社会保险个人权益记录单查询",
		"url": "http://csj.sh.gov.cn/govService/bszn/grqy.html",
		"type": "iframe",
		"img": "../libs/common/images/newIcon/GA.png",
	},{
		"itemName": "社会保障卡应用状态查询",
		"url": "http://csj.sh.gov.cn/govService/bszn/shbzk.html",
		"type": "iframe",
		"img": "../libs/common/images/newIcon/GA.png",
	}];
} else {
	perjsonStr = [{
		"itemName": "社会保险个人权益记录单查询",
		"url": "http://csj.sh.gov.cn/govService/bszn/grqy.html",
		"type": "iframe",
		"img": "../libs/common/images/newIcon/GA.png",
	},{
		"itemName": "社会保障卡应用状态查询",
		"url": "http://csj.sh.gov.cn/govService/bszn/shbzk.html",
		"type": "iframe",
		"img": "../libs/common/images/newIcon/GA.png",
	},{
		"itemName": "社保证明开具",
		"url": "sbzmkj",
		"type": "choice",
		"img": "../libs/common/images/newIcon/GA.png",
	}];
	archives = [{
		"itemName": "知青档案查询",
		"url": "../CSJ_educatedYouthArchives/index.html",
		"type": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	},{
		"itemName": "婚姻登记档案查询",
		"url": "../CSJ_queryMarriageRegistration/index.html",
		"type": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	},{
		"itemName": "独生子女档案查询",
		"url": "../CSJ_queryOnlyChildFile/index.html",
		"type": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	}];
	handles = [{
		"itemName": "户籍事项证明",
		"url": "../CSJ_householdRegister/index.html",
		"type": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	},{
		"itemName": "基本医疗保险关系转移接续",
		"url": "../CSJ_basicMedicalInsurance/index.html",
		"type": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	}];
	other = [{
		"itemName": "个人申请出具异地贷款缴存使用证明",
		"url": "https://zwdtuser.sh.gov.cn/uc/naturalUser/jumpCountry.do?ly=csj&redirect_uri=http://zwdt.sh.gov.cn/zwdtSW/csj/showHousing.do",
		"type": "iframe",
		"img": "../libs/common/images/newIcon/GA.png",
	},{
		"itemName": "定量包装商品生产企业计量保证能力自我声明一体化办理及管理",
		"url": "http://ywtb.sh.gov.cn:18018/ac-product-net/cbiaozhi/packaging/index.html",
		"type": "iframe",
		"img": "../libs/common/images/newIcon/GA.png",
	},{
		"itemName": "人防行业从业企业信息查询",
		"url": "http://117.184.226.110:7070/ac-product-net-csj/csjRfCompanySearch/index.jsp",
		"type": "iframe",
		"img": "../libs/common/images/newIcon/GA.png",
	},{
		"itemName": "人防设计甲级资质企业备案",
		"url": "https://zwdtuser.sh.gov.cn/uc/naturalUser/jumpCountry.do?ly=csj&redirect_uri=http://39.98.153.80/garfsp/ywtb/page/rfzzba_sj.html",
		"type": "iframe",
		"img": "../libs/common/images/newIcon/GA.png",
	},{
		"itemName": "人防监理甲级资质企业备案",
		"url": "https://zwdtuser.sh.gov.cn/uc/naturalUser/jumpCountry.do?ly=csj&redirect_uri=http://39.98.153.80/garfsp/ywtb/page/rfzzba_jl.html",
		"type": "iframe",
		"img": "../libs/common/images/newIcon/GA.png",
	},{
		"itemName": "出入境记录查询",
		"url": "crjjl",
		"type": "choice",
		"img": "../libs/common/images/newIcon/GA.png",
	}
	,{
		"itemName": "婚姻登记预约服务",
		"url": "marriageRegistration",
		"type": "choice-iframe",
		"img": "../libs/common/images/newIcon/GA.png",
	},{
		"itemName": "办理跨省异地就医登记备案相关手续",
		"url": "medicalInDifferentPlaces",
		"type": "choice-iframe",
		"img": "../libs/common/images/newIcon/GA.png",
	},{
		"itemName": "内资有限公司设立",
		"url": "domesticCompanies",
		"type": "choice-iframe",
		"img": "../libs/common/images/newIcon/GA.png",
	}
	];
}

let choiceList = [{
	"itemName":'社保证明开具',
	"type":"sbzmkj",
	"url": "https://etax.shanghai.chinatax.gov.cn/wszx-web/bszm/apps/views/beforeLogin/indexBefore/pageIndex.html#/",
	'provice':'sh'
},{
	"itemName":'社保证明开具',
	"type":"sbzmkj",
	"url": "http://zjcbzmdy.yyhj.zjzwfw.gov.cn/preauth.html",
	'provice':'zj'
},{
	"itemName":'社保证明开具',
	"type":"sbzmkj",
	"url": "http://www.jszwfw.gov.cn/col/col140096/index.html",
	'provice':'js'
},{
	"itemName":'社保证明开具',
	"type":"sbzmkj",
	"url": "http://hf.ahzwfw.gov.cn/bog-bsdt/static/workProcess/components/applicationMaterial.html?ssqdCode=e05746f7d964447ba226aa5c65c8f5d4",
	'provice':'ah'
},{
	"itemName":'出入境记录查询',
	"type":"crjjl",
	"url": "https://zwdtuser.sh.gov.cn/uc/naturalUser/jump.do?redirect_uri=http://ywtb.sh.gov.cn:18018/ac-product-net/smyExitEntryQuery/index.do",
	'provice':'sh'
},{
	"itemName":'出入境记录查询',
	"type":"crjjl",
	"url": "https://s.nia.gov.cn/mps/views/query/query-history.html",
	'provice':'zj'
},{
	"itemName":'出入境记录查询',
	"type":"crjjl",
	"url": "https://s.nia.gov.cn/mps/views/query/query-history.html",
	'provice':'js'
},{
	"itemName":'出入境记录查询',
	"type":"crjjl",
	"url": "https://s.nia.gov.cn/mps/views/query/query-history.html",
	'provice':'ah'
}]
