var country = ["中国", "美国", "韩国"];
// 单位类型
var ST_ORG_TYPE = ["企业","事业","社会团体","其他组织"];

// 单位类型
var aST_ORG_TYPE = [
	{name: '企业',value: 'a'},
	{name: '事业',value: 'b'},
	{name: '社会团体',value: 'c'},
	{name: '其他组织',value: 'd'}
];
// 证件类型
var aZZJGLX = [
	{name: '统一社会信用代码',value: 'a'},
	{name: '注册号/事业单位证书号',value: 'b'},
	{name: '组织机构代码',value: 'c'},
	{name: '税务登记号',value: 'd'}
];
// 申请类型
var asqtype = [
	{name: '新申请',value: 'a'},
	{name: '延续',value: 'b'},
	{name: '变更',value: 'c'},
	{name: '补证',value: 'd'}
];
// 行政区
var aDISTRICTS = [
	{name: '黄浦区', value: 'a'},
	{name: '徐汇区', value: 'b'},
	{name: '长宁区', value: 'c'},
	{name: '静安区', value: 'd'},
	{name: '普陀区', value: 'e'},
	{name: '崇明区', value: 'f'},
	{name: '虹口区', value: 'g'},
	{name: '杨浦区', value: 'h'},
	{name: '闵行区', value: 'i'},
	{name: '宝山区', value: 'j'},
	{name: '嘉定区', value: 'k'},
	{name: '浦东新区', value: 'l'},
	{name: '金山区', value: 'm'},
	{name: '松江区', value: 'n'},
	{name: '青浦区', value: 'o'},
	{name: '奉贤区', value: 'p'},
];



// 自主申报相关数据
var listJson = [
	{"itemName":"",
	 "itemUrl":""},
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