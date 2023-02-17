var perjsonStr = [
{
		"stuffName": "开具有无违法犯罪记录证明",
		"type": "treatment",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_isCrimeProve/index.html"
	},
	{
		"stuffName": "户籍证明开具",
		"type": "bookMaking",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_householdRegister/index.html#/choiceMode"
	},
	{
		"stuffName": "出境入境记录",
		"type": "info",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_movementRecord/index.html"
	},
	{
		"stuffName": "居住证签注",
		"type": "reduce",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_residenceRegister/index.html"
	}, {
		"stuffName": "机动车违法信息查询",
		"type": "bookMaking",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_vehIllegalInfoQuery/index.html"
	}, {
		"stuffName": "敬老卡申领",
		"type": "medicalDetails",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_oldCardApply/index.html"
	}, {
		"stuffName": "户籍人户分离人员居住登记受理",
		"type": "accountSettlement",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_hjrhflryjzdjsl/index.html"
	}, {
		"stuffName": "户籍人户分离人员居住登记注销",
		"type": "services",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_hjrhflryjzdjzx/index.html"
	}, {
		"stuffName": "新版社保卡开通",
		"type": "medicalInstitution",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_ssCardOpen/index.html"
	}, {
		"stuffName": "非上海生源高校毕业生落户查询",
		"type": "password",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_fshsygxbyslh/index.html"
	}, {
		"stuffName": "新生儿重名查询",
		"type": "payStand",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_duplicateName/index.html"
	}, {
		"stuffName": "属地派出所查询",
		"type": "calculator",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_policeStationQuery/index.html"
	}, {
		"stuffName": "同日出生人数",
		"type": "settlementService",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_sameBirthday/index.html"
	},
];
if(jQuery.getConfigMsg.isCommunityNew == "Y" && jQuery.getConfigMsg.isCommunity !== "N") {
	perjsonStr = [{
		"stuffName": "机动车违法信息查询",
		"type": "bookMaking",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_vehIllegalInfoQuery/index.html?type=0"
	},{
		"stuffName": "驾驶证违法信息查询",
		"type": "bookMaking",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_vehIllegalInfoQuery/index.html?type=1"
	},{
		"stuffName": "户籍人户分离人员居住登记受理",
		"type": "accountSettlement",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_hjrhflryjzdjsl/index.html"
	}, {
		"stuffName": "户籍人户分离人员居住登记注销",
		"type": "services",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_hjrhflryjzdjzx/index.html"
	}, {
		"stuffName": "新版社保卡开通",
		"type": "medicalInstitution",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_ssCardOpen/index.html"
	}, {
		"stuffName": "新生儿重名查询",
		"type": "payStand",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_duplicateName/index.html"
	}, {
		"stuffName": "属地派出所查询",
		"type": "calculator",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_policeStationQuery/index.html"
	}, {
		"stuffName": "同日出生人数",
		"type": "settlementService",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_sameBirthday/index.html"
	}, {
		"stuffName": "非上海生源高校毕业生落户查询",
		"type": "password",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_fshsygxbyslh/index.html"
	}];
}else if(jQuery.getConfigMsg.isCommunity != "N") {
	perjsonStr = [{
		"stuffName": "户籍人户分离人员居住登记受理",
		"type": "accountSettlement",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_hjrhflryjzdjsl/index.html"
	}, {
		"stuffName": "户籍人户分离人员居住登记注销",
		"type": "services",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_hjrhflryjzdjzx/index.html"
	}, {
		"stuffName": "新版社保卡开通",
		"type": "medicalInstitution",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_ssCardOpen/index.html"
	}, {
		"stuffName": "新生儿重名查询",
		"type": "payStand",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_duplicateName/index.html"
	}, {
		"stuffName": "属地派出所查询",
		"type": "calculator",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_policeStationQuery/index.html"
	}, {
		"stuffName": "同日出生人数",
		"type": "settlementService",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": "../GA_sameBirthday/index.html"
	}];
}