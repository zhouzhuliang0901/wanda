let perjsonStr = []
if(jQuery.getConfigMsg.isCommunityNew == "Y" && jQuery.getConfigMsg.isCommunity !== "N") {
	perjsonStr = [{
		"stuffName": "不动产登记信息自助查询、打印",
		"type": "GH_realEstateInformation",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	}];
} else {
	perjsonStr = [{
		"stuffName": "地质档案查询",
		"type": "GH_searchOfGeologicalRecords",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	}, {
		"stuffName": "不动产登记信息自助查询、打印",
		"type": "GH_realEstateInformation",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	}];
}