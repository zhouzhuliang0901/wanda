var perjsonStr = [{
		"stuffName": "公积金查询",
		"img": "../libs/common/images/newIcon/JG.png",
		"url": "../threeGoldQuery/index.html"
	},
	{
		"stuffName": "公积金贷款试算",
		"img": "../libs/common/images/newIcon/JG.png",
		"url": "../ZJ_accumulationFundDK/index.html"
	},
	{
		"stuffName": "公积金还款计算",
		"img": "../libs/common/images/newIcon/JG.png",
		"url": "../ZJ_accumulationFundHK/index.html"
	}, {
		"stuffName": "公积金缴存计算",
		"img": "../libs/common/images/newIcon/JG.png",
		"url": "../ZJ_accumulationFundJC/index.html"
	}, {
		"stuffName": "公积金区管理部查询",
		"img": "../libs/common/images/newIcon/JG.png",
		"url": "../ZJ_accumulationFundQGLB/index.html"
	}, {
		"stuffName": "纯公积金贷款受理网点",
		"img": "../libs/common/images/newIcon/JG.png",
		"url": "../ZJ_accumulationFundDKWD/index.html"
	}, {
		"stuffName": "公积金缴存提取经办银行查询",
		"img": "../libs/common/images/newIcon/JG.png",
		"url": "../ZJ_accumulationFundBankOfJCTQ/index.html"
	}, {
		"stuffName": "公积金缴存表查询",
		"img": "../libs/common/images/newIcon/JG.png",
		"url": "../ZJ_accumulationFundJCB/index.html"
	}, {
		"stuffName": "组合贷款各受理银行查询",
		"img": "../libs/common/images/newIcon/JG.png",
		"url": "../ZJ_loanbanks/index.html"
	},
	// {
	// 	"stuffName": "小区信息查询",
	// 	"url": "../ZJ_communityInformationSearch/index.html",
	// 	"img": "../libs/common/images/newIcon/JG.png",
	// }, {
	// 	"stuffName": "物业企业信息查询",
	// 	"url": "../ZJ_propertyInformationSearch/index.html",
	// 	"img": "../libs/common/images/newIcon/JG.png",
	// }, 
	{
		"stuffName": "个人住房公积金从外省市转移到本市",
		"url": "../ZJ_transferFundProvinceToCity/index.html",
		"img": "../libs/common/images/newIcon/JG.png",
	}, {
		"stuffName": "自愿缴存（信息变更）",
		"url": "../ZJ_voluntaryDeposit/index.html",
		"img": "../libs/common/images/newIcon/JG.png",
	}, {
		"stuffName": "企业资质类查询事项",
		"url": "../ZJ_enterpriseQualificationQuery/index.html",
		"img": "../libs/common/images/newIcon/JG.png",
	}
];
console.log(jQuery.getConfigMsg.isCommunity);
if(jQuery.getConfigMsg.isCommunityNew == "Y" && jQuery.getConfigMsg.isCommunity !== "N") {
	perjsonStr = [{
			"stuffName": "公积金查询",
			"img": "../libs/common/images/newIcon/JG.png",
			"url": "../threeGoldQuery/index.html"
		},
		{
			"stuffName": "公积金贷款试算",
			"img": "../libs/common/images/newIcon/JG.png",
			"url": "../ZJ_accumulationFundDK/index.html"
		},
		{
			"stuffName": "公积金缴存计算",
			"img": "../libs/common/images/newIcon/JG.png",
			"url": "../ZJ_accumulationFundJC/index.html"
		},
		{
			"stuffName": "公积金还款计算",
			"img": "../libs/common/images/newIcon/JG.png",
			"url": "../ZJ_accumulationFundHK/index.html"
		}, {
			"stuffName": "公积金区管理部查询",
			"img": "../libs/common/images/newIcon/JG.png",
			"url": "../ZJ_accumulationFundQGLB/index.html"
		}, {
			"stuffName": "纯公积金贷款受理网点",
			"img": "../libs/common/images/newIcon/JG.png",
			"url": "../ZJ_accumulationFundDKWD/index.html"
		}, {
			"stuffName": "公积金缴存提取经办银行查询",
			"img": "../libs/common/images/newIcon/JG.png",
			"url": "../ZJ_accumulationFundBankOfJCTQ/index.html"
		}, {
			"stuffName": "公积金缴存表查询",
			"img": "../libs/common/images/newIcon/JG.png",
			"url": "../ZJ_accumulationFundJCB/index.html"
		}, {
			"stuffName": "组合贷款各受理银行查询",
			"img": "../libs/common/images/newIcon/JG.png",
			"url": "../ZJ_loanbanks/index.html"
		}, {
			"stuffName": "小区信息查询",
			"url": "../ZJ_communityInformationSearch/index.html",
			"img": "../libs/common/images/newIcon/JG.png",
		}, {
			"stuffName": "物业企业信息查询",
			"url": "../ZJ_propertyInformationSearch/index.html",
			"img": "../libs/common/images/newIcon/JG.png",
		}, {
		"stuffName": "企业资质类查询事项",
		"url": "../ZJ_enterpriseQualificationQuery/index.html",
		"img": "../libs/common/images/newIcon/JG.png",
	}
	];
} else if(jQuery.getConfigMsg.isCommunity !== "N") {
	perjsonStr = [{
			"stuffName": "公积金查询",
			"img": "../libs/common/images/newIcon/JG.png",
			"url": "../threeGoldQuery/index.html"
		},
		{
			"stuffName": "公积金贷款试算",
			"img": "../libs/common/images/newIcon/JG.png",
			"url": "../ZJ_accumulationFundDK/index.html"
		},
		{
			"stuffName": "公积金还款计算",
			"img": "../libs/common/images/newIcon/JG.png",
			"url": "../ZJ_accumulationFundHK/index.html"
		}, {
			"stuffName": "公积金区管理部查询",
			"img": "../libs/common/images/newIcon/JG.png",
			"url": "../ZJ_accumulationFundQGLB/index.html"
		}, {
			"stuffName": "纯公积金贷款受理网点",
			"img": "../libs/common/images/newIcon/JG.png",
			"url": "../ZJ_accumulationFundDKWD/index.html"
		}, {
			"stuffName": "公积金缴存提取经办银行查询",
			"img": "../libs/common/images/newIcon/JG.png",
			"url": "../ZJ_accumulationFundBankOfJCTQ/index.html"
		}, {
			"stuffName": "公积金缴存表查询",
			"img": "../libs/common/images/newIcon/JG.png",
			"url": "../ZJ_accumulationFundJCB/index.html"
		}, {
			"stuffName": "组合贷款各受理银行查询",
			"img": "../libs/common/images/newIcon/JG.png",
			"url": "../ZJ_loanbanks/index.html"
		}, {
		"stuffName": "企业资质类查询事项",
		"url": "../ZJ_enterpriseQualificationQuery/index.html",
		"img": "../libs/common/images/newIcon/JG.png",
	}
	];
}