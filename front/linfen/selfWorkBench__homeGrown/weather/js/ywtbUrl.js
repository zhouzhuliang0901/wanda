var perjsonStr = [{
	"stuffName": "听力、言语残疾人信息卡套餐服务申请",
	"type": "CL_informationCard",
	"ywlx": "00",
	"img": "../libs/common/images/newIcon/GA.png",
}, {
	"stuffName": "残疾人交通补贴申请",
	"type": "CL_travelAllowance",
	"ywlx": "00",
	"img": "../libs/common/images/newIcon/GA.png",
}, {
	"stuffName": "无障碍电影放映点信息查询",
	"type": "CL_movieLocationInquiry",
	"ywlx": "00",
	"img": "../libs/common/images/newIcon/GA.png",
}, {
	"stuffName": "盲人有声读物阅览室（角）信息查询",
	"type": "CL_readingRoomLocations",
	"ywlx": "00",
	"img": "../libs/common/images/newIcon/GA.png",
}];
if($.getConfigMsg.isCommunity != "N") {
	perjsonStr = [{
		"stuffName": "残疾人交通补贴申请",
		"type": "CL_travelAllowance",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/GA.png",
	}]
}