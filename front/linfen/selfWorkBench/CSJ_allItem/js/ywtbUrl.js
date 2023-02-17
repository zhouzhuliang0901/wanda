let perjsonStr = []
if($.getConfigMsg.isCommunity == 'Y' || $.getConfigMsg.isCommunity == null || $.getConfigMsg.isCommunity ==  undefined) {
	perjsonStr = [{
		"stuffName": "社会保险个人权益记录单查询",
		"url": "CL_travelAllowance",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	}];
} else {
	perjsonStr = [{
		"stuffName": "社会保险个人权益记录单查询",
		"url": "CL_informationCard",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	}];
}