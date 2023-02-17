var archives,handles,other,perjsonStr;
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
	},{
		"itemName": "再生子女档案查询",
		"url": "../CSJ_regenerateChildrenArchives/index.html",
		"type": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	},{
		"itemName": "退伍军人档案查询",
		"url": "../CSJ_veteranArchives/index.html",
		"type": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	}];
	handles = [{
		"itemName": "户籍事项证明",
		"url": "../CSJ_householdRegisterALL/index.html",
		"type": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	},{
		"itemName": "基本医疗保险关系转移接续",
		"url": "../CSJ_basicMedicalInsurance/index.html",
		"type": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	},{
		"itemName": "有无违法犯罪记录证明开具",
		"url": "../CSJ_isCrimeProveALL/index.html",
		"type": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	},{
		"itemName": "个人申请出具异地贷款缴存使用证明",
		"url": "../CSJ_grsqcjyddkjcsyzm/index.html",
		"type": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	},{
		"itemName": "长三角购房提取住房公积金",
		"url": "../CSJ_providentFundWithdrawals/index.html",
		"type": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	}];

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
