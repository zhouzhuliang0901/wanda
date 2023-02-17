let perjsonStr = []
if($.getConfigMsg.isCommunity == 'Y' || $.getConfigMsg.isCommunity == null || $.getConfigMsg.isCommunity ==  undefined) {
	perjsonStr = [{
		"stuffName": "个人信用报告查询",
		"type": "treatment",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	}];
} else {
	perjsonStr = [{
		"stuffName": "个人信用报告查询",
		"type": "treatment",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	}, {
		"stuffName": "副食品价格补贴发放银行卡维护",
		"type": "nonStapleFood",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	}];
}