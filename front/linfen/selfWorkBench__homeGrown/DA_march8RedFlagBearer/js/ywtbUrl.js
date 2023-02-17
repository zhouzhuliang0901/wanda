//各接口的API_ID参数	A：申请登记受理查询    B：审核结果查看     C：浏览全文接口      D：打印出证接口
var perjsonStr = [{
		"id": "0180",
		"stuffName": "市三八红旗手登记档案查询",
		"code": "312090156000",
		"img": "../libs/common/images/icon-link6.png",
		"guideline": "申请人应为上海市三八红旗手档案记载的本人。委托其他人代为办理的，代理人应按有关规定前往保管该档案的区档案馆办理。",
		"API_ID_A": "69bd1c1f-ca3e-41ca-84ba-737807c29b05",
		"API_ID_B": "2ee61b00-5cca-4112-ab81-957e152cd726",
		"API_ID_C": "3e482df9-0500-4099-8aac-58edf83625d2",
		"API_ID_D": "20783d91-1941-4ad6-bc97-8d01b4d41bbb"
	}
];
//id: '01-00',
//		address: '上海市',
//		code: 'shdaj'
var archivesAddressList = [{
		id: '',
		address: '不详',
		code: ''
	}, {
		id: '59371498',
		address: '普陀区',
		code: 'putuo',
	},
	{
		id: '2448503',
		address: '杨浦区',
		code: 'yangpu',
	},
	{
		id: '662434502',
		address: '虹口区',
		code: 'hongkou',
	}, {
		id: '425088781',
		address: '闵行区',
		code: 'minhang',
	}, {
		id: '560115594',
		address: '松江区',
		code: 'songjiang'
	}, {
		id: '425064392',
		address: '青浦区',
		code: 'qingpu'
	}, {
		id: '425165003',
		address: '浦东新区',
		code: 'pudong'
	}, {
		id: '425032104',
		address: '徐汇区',
		code: 'xuhui'
	}, {
		id: '778096533',
		address: '奉贤区',
		code: 'fengxian'
	}, {
		id: 'E78836598',
		address: '金山区',
		code: 'jinshan'
	}, {
		id: '666071462',
		address: '黄浦区',
		code: 'huangpu'
	}, {
		id: '55425942X',
		address: '长宁区',
		code: 'changning'
	}, {
		id: '798938806',
		address: '静安区',
		code: 'jingan'
	}, {
		id: '662428452',
		address: '宝山区',
		code: 'baoshan'
	}, {
		id: '2454508',
		address: '嘉定区',
		code: 'jiading'
	}, {
		id: 'E78838809',
		address: '崇明区',
		code: 'chongming'
	}
]