var country = ["中国", "美国", "韩国"];
// 单位类型
var ST_ORG_TYPE = [
	{name: '企业',value: 'a'},
	{name: '事业',value: 'b'},
	{name: '社会团体',value: 'c'},
	{name: '其他组织',value: 'd'}
];
// 投资项目类型
var TZXMLX =[
	{name: '社会投资项目',value:'a'}
];
// 排污口事项类型
var PWKSXLX = [
	{name: '排污口事项',value: '1'},
	{name: '非排污口事项',value: '2'}
];
// 涉及类型
var SJLX = [
	{name: '不涉及排水口',value: '1'},
	{name: '涉及排水口',value: '2'},
	{name: '设置或扩大排污口',value: '3'}
];
// 行政区
var DISTRICTS = [
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
	{name: '奉贤区', value: 'p'}
];
// 证件类型
var ZZJGLX = [
	{name: '统一社会信用代码', value: 'A1'},
	{name: '注册号/事业单位证书号', value: 'A2'},
	{name: '组织机构代码', value: 'A3'},
	{name: '税务登记号', value: 'A4'}
];
// 代码类型
var CODETYPE = [
	{name: '上海市发改委项目代码', value: '1'},
	{name: '国家发改委项目代码', value: '2'},
	{name: '区发改委项目代码', value: '3'}
];
// 建设性质
var JSTYPE = [
	{name: '新建', value: '0'},
	{name: '迁建', value: '1'},
	{name: '扩建', value: '2'},
	{name: '改建', value: '3'}
];
// 立项或批准级别
var LXPZLEVEL = [
	{name: '市级', value: '0'},
	{name: '区级', value: '1'}
];
// 立项或批准方式
var LXPZWAY = [
	{name: '审批', value: 'A00001'},
	{name: '核准', value: 'A00002'},
	{name: '备案', value: 'A00003'}
];
// 所在区
var LOCATION = [
	{name: '跨省市', value: '000000'},
	{name: '跨区', value: '310001'},
	{name: '上海市', value: '310000'},
	{name: '卢湾区', value: '310103'},
	{name: '徐汇区', value: '310104'},
	{name: '长宁区', value: '310105'},
	{name: '静安区', value: '310106'},
	{name: '普陀区', value: '310107'},
	{name: '虹口区', value: '310109'},
	{name: '杨浦区', value: '310110'},
	{name: '闵行区', value: '310112'},
	{name: '宝山区', value: '310113'},
	{name: '浦东新区', value: '310115'},
	{name: '嘉定区', value: '310114'},
	{name: '南汇区', value: '310119'},
	{name: '奉贤区', value: '310120'},
	{name: '松江区', value: '310117'},
	{name: '金山区', value: '310116'},
	{name: '青浦区', value: '310118'},
	{name: '崇明区', value: '310230'},
	{name: '黄浦区', value: '310101'}
];
// 排水（污）口设置类型
var PSKSZLX = [
	{name: '新建', value: 'PSKSZLXXJ'},
	{name: '改建', value: 'PSKSZLXGJ'},
	{name: '扩建', value: 'PSKSZLXKD'}
];
// 排水（污）口性质
var PSKXZ = [
	{name: '企业', value: 'PSKXZQY'},
	{name: '市政', value: 'PSKXZSZ'},
	{name: '其它', value: 'PSKXZQT'}
];
// 排放方式
var PFFS = [
	{name: '连续', value: 'PFFSLX'},
	{name: '间歇', value: 'PFFSJX'}
];
// 入河方式
var RHFS = [
	{name: '明渠', value: 'RHFSMQ'},
	{name: '暗管', value: 'RHFSAG'},
	{name: '泵站', value: 'RHFSBZ'},
	{name: '涵闸', value: 'RHFSHZ'},
	{name: '潜没', value: 'RHFSQM'},
	{name: '其他', value: 'RHFSQT'}
];

// 填堵河道的审批材料列表
var riverStuffList = [
		"河道管理范围内建设项目申请表",
		"申请人法定身份证明材料",
		"建设项目批准文件（如立项、规划选址意见书、扩初设计批复等）",
		"建设项目涉及河道部分的初步方案（包括平面布置图、结构图、河道蓝线等）",
		"建设项目对河势稳定、堤防和护岸等水工程安全、河道行洪排涝、排水和水质的影响以及拟采取的补救措施"
	];
var riverStuffList1 = [{
			"certs": [],
			"stDesc": "",
			"stuffName": "建设项目对河势稳定、堤防和护岸等水工程安全、河道行洪排涝、排水和水质的影响以及拟采取的补救措施",
			"isMust": "1",
			"stuffCode": "stuff20n00108"
		}, {
			"certs": [{
				"certName": "关于XX项目环境影响报告书（表）的审批意见",
				"certCode": "312003001000500"
			}, {
				"certName": "建设项目选址意见书（建设项目规划土地意见书）",
				"certCode": "310191338689300"
			}, {
				"certName": "建设项目设计批复文件（建设工程设计方案批复）",
				"certCode": "310100482000300"
			}, {
				"certName": "外商投资项目核准的批复",
				"certCode": "310196129229100"
			}, {
				"certName": "对使用政府性资金投资建设的固定资产投资项目初步设计及概算的审批（含初审）的批复",
				"certCode": "310100008007300"
			}, {
				"certName": "境外投资项目备案通知书",
				"certCode": "310103035000100"
			}, {
				"certName": "对使用政府性资金投资建设的固定资产投资项目可行性研究报告的审批的（含初审）批复",
				"certCode": "310100006000300"
			}, {
				"certName": "企业投资项目核准的批复",
				"certCode": "310100423000400"
			}, {
				"certName": "建设工程规划许可证",
				"certCode": "310100503000202"
			}, {
				"certName": "建设用地规划许可证",
				"certCode": "310100501000200"
			}, {
				"certName": "对使用政府性资金投资建设的固定资产投资项目项目建议书的审批（含初审）的批复",
				"certCode": "310100007000300"
			}],
			"stDesc": "",
			"stuffName": "建设项目批准文件（如立项、规划选址意见书、扩初设计批复等）",
			"isMust": "1",
			"stuffCode": "stuff20n00107"
		}, {
			"certs": [],
			"stDesc": "",
			"stuffName": "河道管理范围内建设项目申请表",
			"isMust": "1",
			"stuffCode": "stuff20n00106"
		}, {
			"certs": [],
			"stDesc": "",
			"stuffName": "申请人法定身份证明材料",
			"isMust": "1",
			"stuffCode": "stuff2036"
		}, {
			"certs": [],
			"stDesc": "",
			"stuffName": "建设项目涉及河道部分的初步方案（包括平面布置图、结构图、河道蓝线等）",
			"isMust": "1",
			"stuffCode": "stuff19n10701"
		}]




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