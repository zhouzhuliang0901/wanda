var country = ["中国", "美国", "韩国"];
// 单位类型
var ST_ORG_TYPE = ["企业", "事业", "社会团体", "其他组织"];
// 行政区
var DISTRICTS = [{
		"name": "黄浦区",
		"value": "黄浦区"
	},
	{
		"name": "徐汇区",
		"value": "徐汇区"
	},
	{
		"name": "长宁区",
		"value": "长宁区"
	},
	{
		"name": "静安区",
		"value": "静安区"
	},
	{
		"name": "普陀区",
		"value": "普陀区"
	},
	{
		"name": "虹口区",
		"value": "虹口区"
	},
	{
		"name": "杨浦区",
		"value": "杨浦区"
	},
	{
		"name": "闵行区",
		"value": "闵行区"
	},
	{
		"name": "宝山区",
		"value": "宝山区"
	},
	{
		"name": "嘉定区",
		"value": "嘉定区"
	},
	{
		"name": "浦东新区",
		"value": "浦东新区"
	},
	{
		"name": "金山区",
		"value": "金山区"
	},
	{
		"name": "松江区",
		"value": "松江区"
	},
	{
		"name": "青浦区",
		"value": "青浦区"
	},
	{
		"name": "奉贤区",
		"value": "奉贤区"
	},
	{
		"name": "崇明区",
		"value": "崇明区"
	},
]
// 证件类型
var LICENSE_TYPE = [{
		"name": "统一社会信用代码",
		"value": "统一社会信用代码"
	},
	{
		"name": "注册号/事业单位证书号",
		"value": "注册号/事业单位证书号"
	},
	{
		"name": "组织机构代码",
		"value": "组织机构代码"
	},
	{
		"name": "税务登记号",
		"value": "税务登记号"
	},
]
// 类型
var TYPE = [{
		"name": "市管河道及中心城区内河道",
		"value": "市管河道及中心城区内河道"
	},
	{
		"name": "其他河道",
		"value": "其他河道"
	}
]
// 项目性质 
var PROJECTTYPE = [{
		"name": "新建",
		"value": "新建"
	},
	{
		"name": "改建",
		"value": "改建"
	},
	{
		"name": "扩建",
		"value": "扩建"
	},
]
// 建设性质
var JSTYPE = [{
		"name": "新建",
		"value": "0"
	},
	{
		"name": "迁建",
		"value": "1"
	},
	{
		"name": "扩建",
		"value": "2"
	},
	{
		"name": "改建",
		"value": "3"
	}
]
// 立项或批准级别
var LXPZLEVEL = [{
		"name": "市级",
		"value": "0"
	},
	{
		"name": "区级",
		"value": "1"
	}
]
// 立项获取批准方式
var LXPZWAY = [{
		"name": "审批",
		"value": "A00001"
	},
	{
		"name": "核准",
		"value": "A00002"
	},
	{
		"name": "备案",
		"value": "A00003"
	}
]
// 所在区
var LOCATION = [{
		"name": "跨省市",
		"value": "000000"
	},
	{
		"name": "跨区",
		"value": "310001"
	},
	{
		"name": "上海市",
		"value": "310000"
	},
	{
		"name": "徐汇区",
		"value": "310104"
	},
	{
		"name": "长宁区",
		"value": "310105"
	},
	{
		"name": "静安区",
		"value": "310106"
	},
	{
		"name": "普陀区",
		"value": "310107"
	},
	{
		"name": "虹口区",
		"value": "310109"
	},
	{
		"name": "杨浦区",
		"value": "310110"
	},
	{
		"name": "闵行区",
		"value": "310112"
	},
	{
		"name": "宝山区",
		"value": "310113"
	},
	{
		"name": "浦东新区",
		"value": "310115"
	},
	{
		"name": "嘉定区",
		"value": "310114"
	},
	{
		"name": "奉贤区",
		"value": "310120"
	},
	{
		"name": "松江区",
		"value": "310117"
	},
	{
		"name": "金山区",
		"value": "310116"
	},
	{
		"name": "青浦区",
		"value": "310230"
	},
	{
		"name": "崇明区",
		"value": "310230"
	},
	{
		"name": "黄浦区",
		"value": "310101"
	},
]
// 自主申报相关数据
var listJson = [{
		"itemName": "",
		"itemUrl": ""
	},
	{}
]

// 填堵河道的审批材料列表
var riverStuffList = [
	"河道管理范围内建设项目申请表",
	"申请人法定身份证明材料",
	"建设项目批准文件（如立项、规划选址意见书、扩初设计批复等）",
	"建设项目涉及河道部分的初步方案（包括平面布置图、结构图、河道蓝线等）",
	"建设项目对河势稳定、堤防和护岸等水工程安全、河道行洪排涝、排水和水质的影响以及拟采取的补救措施"
];

var infoData = [{
		"itemId": "0001",
		"itemContent": [{
				"labelName": "申请人姓名",
				"elementType": "input",
				"required": "required",
				"id": "name"
			},
			{
				"labelName": "申请人国籍",
				"elementType": "select",
				"selectItem": country,
				"id": "country"
			},
		]
	},
	{
		"itemId": "0002",
		"itemContent": [{
				"labelName": "申请人姓名",
				"elementType": "input",
				"id": "name"
			},
			{
				"labelName": "身份证号码",
				"elementType": "input",
				"id": "idCard"
			},
		]
	},

]