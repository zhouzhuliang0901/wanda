let perjsonStr = []
if($.getConfigMsg.isCommunity == 'Y' || $.getConfigMsg.isCommunity == null || $.getConfigMsg.isCommunity ==  undefined) {
	perjsonStr = [{
		"stuffName": "宠物诊疗机构查询",
		"type": "NW_petHospital",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	}];
} else {
	perjsonStr = [{
		"stuffName": "宠物诊疗机构查询",
		"type": "NW_petHospital",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	}];
}