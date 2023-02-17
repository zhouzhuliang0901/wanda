let perjsonStr = []
if($.getConfigMsg.isCommunity == 'Y' || $.getConfigMsg.isCommunity == null || $.getConfigMsg.isCommunity ==  undefined) {
	// 显示社区
	perjsonStr = [{
		"name": "市人社局",
		"url": "../aSocial/index.html",
	}, {
		"name": "市医保局",
		"url": "../medical/index.html",
	}, {
		"name": "市档案局",
		"url": "../archives/index.html",
	}, {
		"name": "市公安局",
		"url": "../publicSecurity/index.html",
	}, {
		"name": "市住建委",
		"url": "../housingConstruction/index.html",
	}, {
		"name": "市经信委",
		"url": "../authentication/index.html",
	}, {
		"name": "市发改委",
		"url": "../search/index.html",
	}, {
		"name": "市民政局",
		"url": "../civilAffairs/index.html",
	}, {
		"name": "市残联",
		"url": "../CL_allItem/index.html",
	}, {
		"name": "市交通委",
		"url": "../JTW_allItem/index.html",
	}, {
		"name": "市市场监管局",
		"url": "../SCJG_allItem/index.html",
	}];
} else {
		// 显示全部
	perjsonStr = [{
		"name": "市人社局",
		"url": "../aSocial/index.html",
	}, {
		"name": "市医保局",
		"url": "../medical/index.html",
	}, {
		"name": "市档案局",
		"url": "../archives/index.html",
	}, {
		"name": "市公安局",
		"url": "../publicSecurity/index.html",
	}, {
		"name": "市住建委",
		"url": "../housingConstruction/index.html",
	}, {
		"name": "市经信委",
		"url": "../authentication/index.html",
	}, {
		"name": "市发改委",
		"url": "../search/index.html",
	}, {
		"name": "市民政局",
		"url": "../civilAffairs/index.html",
	}, {
		"name": "市残联",
		"url": "../CL_allItem/index.html",
	}, {
		"name": "市教委",
		"url": "../JW_allItem/index.html",
	}, {
		"name": "市交通委",
		"url": "../JTW_allItem/index.html",
	}, {
		"name": "市司法局",
		"url": "../SF_allItem/index.html",
	}, {
		"name": "市市场监管局",
		"url": "../SCJG_allItem/index.html",
	}, {
		"name": "市应急局",
		"url": "../YJGL_allItem/index.html",
	}, {
		"name": "申康医联",
		"url": "../SKYL_allItem/index.html",
	}, {
		"name": "市商务委",
		"url": "../SWW_allItem/index.html",
	}, {
		"name": "市卫健委",
		"url": "../WJW_allItem/index.html",
	}
//	, {
//		"name": "市税务局",
//		"url": "../SWJ_allItem/index.html",
//	}
, {
		"name": "市规划资源局",
		"url": "../GH_allItem/index.html",
	}, {
		"name": "市农业委员局",
		"url": "../NW_allItem/index.html",
	}];
}
