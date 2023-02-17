let perjsonStr = []
if($.getConfigMsg.isCommunity == 'Y' || $.getConfigMsg.isCommunity == null || $.getConfigMsg.isCommunity ==  undefined) {
	// 展示社区事项
	perjsonStr = [{
		"stuffName": "市民网上实名认证",
		"type": "treatment",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	}];
} else {
	// 展示全部事项
	perjsonStr = [{
		"stuffName": "市民网上实名认证",
		"type": "treatment",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	}];
}