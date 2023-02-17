var perjsonStr = [{
	"stuffName": "医师执业资格信息查询",
	"url": "../WJW_medicalQualificationQuery/index.html",
	"img": "../libs/common/images/newIcon/YB.png",
}, {
	"stuffName": "护士执业资格信息查询",
	"url": "../WJW_nurseQualificationQuery/index.html",
	"img": "../libs/common/images/newIcon/YB.png",
}, {
	"stuffName": "核酸检测采样机构查询",
	"url": "../WJW_nucleicAcidDetectionQuery/index.html",
	"img": "../libs/common/images/newIcon/YB.png",
},{
	"stuffName": "疫苗接种点查询",
	"url": getUrlForZhuofanItem('疫苗接种点查询'),
	"img": "../libs/common/images/newIcon/YB.png",
}];
if(jQuery.getConfigMsg.isCommunity != "N") {
	perjsonStr = [{
	"stuffName": "医师执业资格信息查询",
	"url": "../WJW_medicalQualificationQuery/index.html",
	"img": "../libs/common/images/newIcon/YB.png",
}, {
	"stuffName": "护士执业资格信息查询",
	"url": "../WJW_nurseQualificationQuery/index.html",
	"img": "../libs/common/images/newIcon/YB.png",
}];
}