var perjsonStr = [{
		"stuffName": "居住证签注",
		"type": "residenceVisa",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
	},{
		"stuffName": "机动车违法信息查询",
		"type": "lllegalInfo",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
	}
//	,{
//		"stuffName": "出境入籍记录查询",
//		"type": "exitAndEntry",
//		"ywlx": "00",
//		"img": "../libs/common/images/newIcon/YB.png",
//	},
//	{
//		"stuffName": "户籍证明",
//		"type": "householdRegister",
//		"ywlx": "00",
//		"img": "../libs/common/images/newIcon/YB.png",
//	},
//	{
//		"stuffName": "居住证积分查询",
//		"type": "residencePermit",
//		"ywlx": "00",
//		"img": "../libs/common/images/newIcon/YB.png",
//	}, {
//		"stuffName": "往来港澳通行证和签注签发",
//		"type": "HongKongAndMacao",
//		"ywlx": "00",
//		"img": "../libs/common/images/newIcon/YB.png",
//	}
//,{
//		"stuffName": "大陆居民往来台湾通行证和签注签发",
//		"type": "TaiwanPass",
//		"ywlx": "00",
//		"img": "../libs/common/images/newIcon/YB.png",
//	}
];
//通行证事项类型选择
var peopel = [{
	"name": "我是本市居民",
	"id": "01",
}, {
	"name": "我是外省市居民",
	"id": "02",
}];

var itemType = [{
	"name": "首次申请",
	"pid": "01",
}, {
	"name": "换发申请",
	"pid": "01",
}, {
	"name": "过期申请",
	"pid": "01",
}, {
	"name": "首次申请",
	"pid": "02",
}, {
	"name": "换发申请",
	"pid": "02",
}, {
	"name": "过期申请",
	"pid": "02",
}]
//申请信息
var endorsement = [{
	"value": "a",
	"name": "是",
	"id": "1",
}, {
	"value": "b",
	"name": "否",
	"id": "2",
}]

var destination = [{
	"value": "HKG",
	"pid": "1",
	"name": "香港",
	"id": "1",
}, {
	"value": "MAC",
	"pid": "1",
	"name": "澳门",
	"id": "2",
}]

var Hongkong = [{
	"value": "11",
	"pid": "1",
	"name": "探亲",
	"id": "1",
}, {
	"value": "12",
	"pid": "1",
	"name": "团队旅游",
	"id": "2",
}, {
	"value": "1B",
	"pid": "1",
	"name": "个人旅游",
	"id": "3",
}, {
	"value": "1G",
	"pid": "1",
	"name": "逗留",
	"id": "4",
}, {
	"value": "19",
	"pid": "1",
	"name": "其他",
	"id": "5",
}]

var Macao = [{
	"value": "11",
	"pid": "2",
	"name": "探亲",
	"id": "1",
}, {
	"value": "12",
	"pid": "2",
	"name": "团队旅游",
	"id": "2",
}, {
	"value": "1B",
	"pid": "2",
	"name": "个人旅游",
	"id": "3",
}, {
	"value": "1G",
	"pid": "2",
	"name": "逗留",
	"id": "4",
}, {
	"value": "19",
	"pid": "2",
	"name": "其他",
	"id": "5",
}]

var HKTour = [{
	"value": "a",
	"pid": "2",
	"name": "3个月1次",
	"id": "1",
}, {
	"value": "b",
	"pid": "2",
	"name": "3个月2次",
	"id": "2",
}, {
	"value": "c",
	"pid": "2",
	"name": "1年1次",
	"id": "3",
}, {
	"value": "d",
	"pid": "2",
	"name": "1年2次",
	"id": "4",
}, {
	"value": "a",
	"pid": "3",
	"name": "3个月1次",
	"id": "1",
}, {
	"value": "b",
	"pid": "3",
	"name": "3个月2次",
	"id": "2",
}, {
	"value": "c",
	"pid": "3",
	"name": "1年1次",
	"id": "3",
}, {
	"value": "d",
	"pid": "3",
	"name": "1年2次",
	"id": "4",
}]

var MCTour = [{
	"value": "a",
	"pid": "2",
	"name": "3个月1次",
	"id": "1",
}, {
	"value": "b",
	"pid": "2",
	"name": "3个月2次",
	"id": "2",
}, {
	"value": "c",
	"pid": "2",
	"name": "1年1次",
	"id": "3",
}, {
	"value": "d",
	"pid": "2",
	"name": "1年2次",
	"id": "4",
}, {
	"value": "a",
	"pid": "3",
	"name": "3个月1次",
	"id": "1",
}, {
	"value": "b",
	"pid": "3",
	"name": "3个月2次",
	"id": "2",
}, {
	"value": "c",
	"pid": "3",
	"name": "1年1次",
	"id": "3",
}, {
	"value": "d",
	"pid": "3",
	"name": "1年2次",
	"id": "4",
}]

//异地办证身份类别
var differentPlaces = [{
		"value": "001",
		"name": "人才引进类"
	},
	{
		"value": "002",
		"name": "人才引进配偶类"
	},
	{
		"value": "003",
		"name": "人才引进子女类"
	},
	{
		"value": "004",
		"name": "一般就业类"
	},
	{
		"value": "005",
		"name": "高校就读类"
	},
	{
		"value": "010",
		"name": "本市人员配偶类"
	},
	{
		"value": "011",
		"name": "本市人员子女类"
	},
	{
		"value": "012",
		"name": "一般就业人员配偶类"
	},
	{
		"value": "013",
		"name": "一般就业人员子女类"
	},
	{
		"value": "014",
		"name": "高校就读人员配偶类"
	},
	{
		"value": "015",
		"name": "高校就读人员子女类"
	},
	{
		"value": "016",
		"name": "60周岁以上暂住居民"
	}, {
		"value": "017",
		"name": "其他社会暂住居民"
	}, {
		"value": "018",
		"name": "持上海市居住证类"
	}, {
		"value": "019",
		"name": "本市人员父母类"
	}
];

//原通行证签发地
var stPassAddress = [{
		"value": "LCAA",
		"name": "驻圣卢西亚使馆"
	}, {
		"value": "LBYA",
		"name": "驻利比亚使馆"
	}]
