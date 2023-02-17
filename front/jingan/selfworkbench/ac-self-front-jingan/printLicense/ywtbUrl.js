//1、一照一码户清税证明：680bb408-2311-4dd3-af07-d780609c839e
var one = "http://zwdt.sh.gov.cn/govPortals/ftl/prove/transition.do?st_id=680bb408-2311-4dd3-af07-d780609c839e";

//2、上海市学生事务中心存档证明：3d4fce65-607c-46e6-a81d-ff417d876be2
var two = "http://183.194.250.112/ac-product/cmpt/selfWorkBench_V2/JW_studentAffairsCenter/index.html#/guideline";

//3、丢失增值税专用发票已报税证明单：45993258-8a9c-4bb0-8193-44b02821f412
var three = "http://zwdt.sh.gov.cn/govPortals/ftl/prove/transition.do?st_id=45993258-8a9c-4bb0-8193-44b02821f412";

//4、出境入境记录：5d58e207-ddca-40a6-baa7-4a2ebb3885fc
var four = "http://183.194.250.112/ac-product/cmpt/selfWorkBench_V2/GA_movementRecord/index.html#/main";

//5、城乡居民社保缴费证明打印：56cb44aa-0032-4e75-a562-d5a6c4b0e453
//var fiveOne = "http://183.194.250.112/ac-product/aci/selfWorkBench/aSocial/index.html#/loginType?itemName=参加个人城镇基本养老保险缴费情况&code=310195323433&type=jfqk";
//var fiveTwo = "http://183.194.250.112/ac-product/aci/selfWorkBench/aSocial/index.html#/loginType?itemName=城乡居保缴费情况&code=310199154354&type=cxjbjfqk";
var fiveOne = "http://183.194.250.112/ac-product/cmpt/selfWorkBench_V2/RS_endowInsurePay/index.html#/main";
var fiveTwo = "http://183.194.250.112/ac-product/cmpt/selfWorkBench_V2/RS_cxjbjfqk/index.html#/main";

//6、基本养老保险转往外省市缴费凭证：039d618f-ab73-4b36-abdd-e502e3b89840
var six = "http://183.194.250.112/ac-product/cmpt/selfWorkBench_V2/RS_zwwssjfpz/index.html#/main";

//7、增值税一般纳税人登记（登记通知）：2540af97-e017-4e69-a154-f25e4ea25d06
var seven = "http://zwdt.sh.gov.cn/govPortals/ftl/prove/transition.do?st_id=2540af97-e017-4e69-a154-f25e4ea25d06";

//8、婚姻登记档案查询：c555ed7a-b142-4de6-88b7-8636fc242454
var eight = "http://zwdt.sh.gov.cn/govPortals/ftl/prove/transition.do?st_id=c555ed7a-b142-4de6-88b7-8636fc242454";

//9、户籍证明：adc5588d-d159-4a9c-abc8-5a4d81bada2b
var nine = "http://zwdt.sh.gov.cn/govPortals/ftl/prove/transition/adc5588d-d159-4a9c-abc8-5a4d81bada2b";

//10、来沪人员生育情况证明：bd28736b-ce5d-46d4-9ba4-68d5ca3b00c5
//var ten = "http://zwdt.sh.gov.cn/govPortals/ftl/prove/transition/bd28736b-ce5d-46d4-9ba4-68d5ca3b00c5";

//11、税收完税证明（表格式）：c1d9b0b3-0a17-4cc5-ac80-4791c3f11161
var eleven = "http://zwdt.sh.gov.cn/govPortals/ftl/prove/transition.do?st_id=c1d9b0b3-0a17-4cc5-ac80-4791c3f11161";
var twelve = "http://183.194.250.112/ac-product/cmpt/selfWorkBench_V2/DC-offlineCode/index.html";
var perjsonStr = [
	{
		"stuffName": "随申码离线服务",
		"code": "1111111111",
		"img": "../libs/common/images/newIcon/JY.png",
		"url": twelve,
		"isSelf":"1"
	},{
		"stuffName": "上海市学生事务中心存档证明",
		"code": "312003001000500",
		"img": "../libs/common/images/newIcon/JY.png",
		"url": two,
		"isSelf":"1"
	},
	{
		"stuffName": "户籍证明",
		"code": "310195631043500",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": nine,
		"isSelf":"0"
	},
	{
		"stuffName": "出境入境记录",
		"code": "310196646654500",
		"img": "../libs/common/images/newIcon/GA.png",
		"url": four,
		"isSelf":"1"
	},
//	{
//		"stuffName": "来沪人员生育情况证明",
//		"code": "312090119000500",
//		"img": "../libs/common/images/newIcon/YB.png",
//		"url": ten,
//		"isSelf":"0"
//	},
	{
		"stuffName": "转往外省市缴费凭证",
		"code": "1111111111",
		"img": "../libs/common/images/newIcon/JG.png",
		"url": six,
	}, {
		"stuffName": "参加个人城镇基本养老保险缴费情况",
		"code": "1111111111",
		"img": "../libs/common/images/newIcon/RS.png",
		"url": fiveOne,
	},
	{
		"stuffName": "城乡居保缴费情况",
		"code": "1111111111",
		"img": "../libs/common/images/newIcon/RS.png",
		"url": fiveTwo,
	}
];
var legaljsonStr = [{
		"stuffName": "一照一码户清税证明",
		"code": "310196102986500",
		"img": "../libs/common/images/newIcon/RS.png",
		"url": one,
	},
	{
		"stuffName": "丢失增值税专用发票已报税证明单",
		"code": "311050593000500",
		"img": "../libs/common/images/newIcon/RS.png",
		"url": three
	},
	{
		"stuffName": "增值税一般纳税人登记（登记通知）",
		"code": "310750113000500",
		"img": "../libs/common/images/newIcon/RS.png",
		"url": seven
	},
	{
		"stuffName": "税收完税证明（表格式）",
		"code": "310193490271500",
		"img": "../libs/common/images/newIcon/RS.png",
		"url": eleven
	}
];