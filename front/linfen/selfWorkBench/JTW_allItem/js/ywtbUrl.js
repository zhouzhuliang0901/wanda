let perjsonStr = []
if($.getConfigMsg.isCommunity == 'Y' || $.getConfigMsg.isCommunity == null || $.getConfigMsg.isCommunity ==  undefined) {
	perjsonStr = [{
		"stuffName": "交通卡余额查询",
		"url": "../JTW_queryBalance/index.html",
		"img": "../libs/common/images/newIcon/GA.png",
	}, {
		"stuffName": "驾照考点查询",
		"url": "../JTW_queryDrivingTestSite/index.html",
		"img": "../libs/common/images/newIcon/GA.png",
	}];
} else {
	perjsonStr = [{
		"stuffName": "交通卡余额查询",
		"url": "../JTW_queryBalance/index.html",
		"img": "../libs/common/images/newIcon/GA.png",
	}, {
		"stuffName": "驾照考点查询",
		"url": "../JTW_queryDrivingTestSite/index.html",
		"img": "../libs/common/images/newIcon/GA.png",
	}, {
		"stuffName": "交通卡服务网点查询",
		"url": "../JTW_travelCardNetworkQuery/index.html",
		"img": "../libs/common/images/newIcon/GA.png",
	}];
}