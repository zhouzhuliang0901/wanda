let perjsonStr = [];
if($.getConfigMsg.isCommunity == 'Y' || $.getConfigMsg.isCommunity == null || $.getConfigMsg.isCommunity ==  undefined) {
	// 展示社区事项
	perjsonStr = [{
			"stuffName": "公积金查询",
			"type": "../threeGoldQuery/index.html#/infoLoginType?type=gjj",
			"ywlx": "00",
			"img": "../libs/common/images/newIcon/JG.png",
		},
		{
			"stuffName": "公积金贷款试算",
			"type": "../ZJ_accumulationFundDK/index.html",
			"ywlx": "00",
			"img": "../libs/common/images/newIcon/JG.png",
		},
		{
			"stuffName": "公积金还款计算",
			"type": "../ZJ_accumulationFundHK/index.html",
			"ywlx": "00",
			"img": "../libs/common/images/newIcon/JG.png",
		},
		{
			"stuffName": "公积金区管理部查询",
			"type": "../ZJ_accumulationFundQGLB/index.html",
			"ywlx": "00",
			"img": "../libs/common/images/newIcon/JG.png",
		},
		{
			"stuffName": "纯公积金贷款受理网点",
			"type": "../ZJ_accumulationFundDKWD/index.html",
			"ywlx": "00",
			"img": "../libs/common/images/newIcon/JG.png",
		},
		{
			"stuffName": "公积金缴存提取经办银行查询",
			"type": "../ZJ_accumulationFundBankOfJCTQ/index.html",
			"ywlx": "00",
			"img": "../libs/common/images/newIcon/JG.png",
		},
		{
			"stuffName": "公积金缴存表查询",
			"type": "../ZJ_accumulationFundJCB/index.html",
			"ywlx": "00",
			"img": "../libs/common/images/newIcon/JG.png",
		},
		{
			"stuffName": "组合贷款各受理银行查询",
			"type": "../ZJ_loanbanks/index.html",
			"ywlx": "00",
			"img": "../libs/common/images/newIcon/JG.png",
		}
	];
} else {
	perjsonStr = [
		{
			"stuffName": "公积金查询",
			"type": "../threeGoldQuery/index.html#/infoLoginType?type=gjj",
			"ywlx": "00",
			"img": "../libs/common/images/newIcon/JG.png",
		},
		{
			"stuffName": "公积金贷款试算",
			"type": "../ZJ_accumulationFundDK/index.html",
			"ywlx": "00",
			"img": "../libs/common/images/newIcon/JG.png",
		},
		{
			"stuffName": "公积金还款计算",
			"type": "../ZJ_accumulationFundHK/index.html",
			"ywlx": "00",
			"img": "../libs/common/images/newIcon/JG.png",
		},
		{
			"stuffName": "公积金缴存计算",
			"type": "../ZJ_accumulationFundJC/index.html",
			"ywlx": "00",
			"img": "../libs/common/images/newIcon/JG.png",
		},
		{
			"stuffName": "公积金区管理部查询",
			"type": "../ZJ_accumulationFundQGLB/index.html",
			"ywlx": "00",
			"img": "../libs/common/images/newIcon/JG.png",
		},
		{
			"stuffName": "纯公积金贷款受理网点",
			"type": "../ZJ_accumulationFundDKWD/index.html",
			"ywlx": "00",
			"img": "../libs/common/images/newIcon/JG.png",
		},
		{
			"stuffName": "公积金缴存提取经办银行查询",
			"type": "../ZJ_accumulationFundBankOfJCTQ/index.html",
			"ywlx": "00",
			"img": "../libs/common/images/newIcon/JG.png",
		},
		{
			"stuffName": "公积金缴存表查询",
			"type": "../ZJ_accumulationFundJCB/index.html",
			"ywlx": "00",
			"img": "../libs/common/images/newIcon/JG.png",
		},
		{
			"stuffName": "组合贷款各受理银行查询",
			"type": "../ZJ_loanbanks/index.html",
			"ywlx": "00",
			"img": "../libs/common/images/newIcon/JG.png",
		},
		{
			"stuffName": "小区信息查询",
			"type": "../ZJ_communityInformationSearch/index.html",
			"ywlx": "00",
			"img": "../libs/common/images/newIcon/JG.png",
		},
		{
			"stuffName": "物业企业信息查询",
			"type": "../ZJ_propertyInformationSearch/index.html",
			"ywlx": "00",
			"img": "../libs/common/images/newIcon/JG.png",
		}
	];
}