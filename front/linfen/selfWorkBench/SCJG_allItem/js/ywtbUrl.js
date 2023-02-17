let perjsonStr = []
if($.getConfigMsg.isCommunity == 'Y' || $.getConfigMsg.isCommunity == null || $.getConfigMsg.isCommunity ==  undefined) {
	perjsonStr = [{
		"stuffName": "餐饮脸谱查询",
		"url": "../SCJG_cateringFacebook/index.html",
		"img": "../libs/common/images/newIcon/GA.png",
	}];
} else {
	perjsonStr = [{
		"stuffName": "餐饮脸谱查询",
		"url": "../SCJG_cateringFacebook/index.html",
		"img": "../libs/common/images/newIcon/GA.png",
	}, {
		"stuffName": "社会公用计量标准查询",
		"url": "../SCJG_queryMeasurementstandard/index.html",
		"img": "../libs/common/images/newIcon/GA.png",
	}, {
		"stuffName": "法定计量检定机构考评员查询",
		"url": "../SCJG_queryAssessor/index.html",
		"img": "../libs/common/images/newIcon/GA.png",
	}, {
		"stuffName": "广告发布登记查询",
		"url": "../SCJG_queryAdvertising/index.html",
		"img": "../libs/common/images/newIcon/GA.png",
	}, {
		"stuffName": "合同示范文本查询",
		"url": "../SCJG_queryContractTextOfDemonstration/index.html",
		"img": "../libs/common/images/newIcon/GA.png",
	}, {
		"stuffName": "企业名称状态查询",
		"url": "../SCJG_queryEnterpriseNameStatus/index.html",
		"img": "../libs/common/images/newIcon/GA.png",
	}, {
		"stuffName": "计量标准考评员查询",
		"url": "../SCJG_queryMeasurementStandardAssessor/index.html",
		"img": "../libs/common/images/newIcon/GA.png",
	}, {
		"stuffName": "二级注册计量师查询",
		"url": "../SCJG_queryMetrologist/index.html",
		"img": "../libs/common/images/newIcon/GA.png",
	}];
}