//各接口的API_ID参数	A：申请登记受理查询    B：审核结果查看     C：浏览全文接口      D：打印出证接口  
var perjsonStr = [{
		"id": "0030",
		"stuffName": "婚姻登记档案查询",
		"code": "312090156000",
		"img": "../libs/common/images/icon-link6.png",
		"guideline": "申请人应为在本市民政部门办理婚姻登记（结婚、离婚）手续的婚姻当事人本人。受婚姻当事人委托代为办理的，或其他相关利用人，应按有关规定前往保管该档案的区档案馆办理。",
		"API_ID_A": "5ea95ff1-484d-4c39-844f-7f2d316140ac",
		"API_ID_B": "ef06b6f2-897b-48af-b99e-b978ea7e2044",
		"API_ID_C": "faf9ef6b-9701-47cc-8d22-fb29d943f74c",
		"API_ID_D": "ef67e70e-0217-4d5a-87c1-e1dd7d5e406b"
	},
	{
		"id": "0040",
		"stuffName": "独生子女档案查询",
		"code": "312090157000",
		"img": "../libs/common/images/icon-link6.png",
		"guideline": "申请人应为本市人口和计划生育行政管理部门发放的独生子女证所载当事人本人（独生子女及其父母本人）。委托他人代为办理的，代理人应按有关规定前往保管该档案的区档案馆办理。",
		"API_ID_A": "d408aead-e460-4ae9-ba0b-7285b828ad04",
		"API_ID_B": "0d42446b-5946-445b-87da-a389d4c0e2e2",
		"API_ID_C": "5f4ea6b8-1f80-42dd-b6e1-3b11d9dede6d",
		"API_ID_D": "9ec1245f-ceae-4aa6-8827-690915d975dd"
	},
	{
		"id": "0050",
		"stuffName": "知青子女入户档案查询",
		"code": "312090160000",
		"img": "../libs/common/images/icon-link6.png",
		"guideline": "申请人应为在本市内办理知青子女入户手续的知青或子女本人。委托他人代为办理的，代理人应按有关规定前往保管该档案的区档案馆办理。",
		"API_ID_A": "5dc55252-e4f6-400b-bfb1-59cf352e2547",
		"API_ID_B": "f4ff0c63-2eab-4abb-a58d-d909ca9e7ba5",
		"API_ID_C": "d760f848-219f-4880-afe1-33217a265495",
		"API_ID_D": "c569e3ec-fce2-4d4b-8afe-09d01d63aa7f"
	},
	{
		"id": "0060",
		"stuffName": "知青上山下乡档案查询",
		"code": "312090158000",
		"img": "../libs/common/images/icon-link6.png",
		"guideline": "申请人应为在本市办理知青上山下乡手续的知青本人。受知青本人委托代为办理的，或其他相关利用人，应按有关规定前往保管该档案的区档案馆办理。",
		"API_ID_A": "ada7c363-af79-4e62-9e6d-c08b474f1ac7",
		"API_ID_B": "a304a704-0849-4405-ba81-50b350244dfc",
		"API_ID_C": "871043aa-7622-49ec-9906-e32f0c1297fa",
		"API_ID_D": "0dbba556-0a7c-462b-90cd-44c5d9d239f1"
	},
	{
		"id": "0070",
		"stuffName": "知青返城档案查询",
		"code": "312090159000",
		"img": "../libs/common/images/icon-link6.png",
		"guideline": "申请人应为在本市办理知青返城手续的知青本人。受知青委托代为办理的，或其他相关利用人，应按有关规定前往保管该档案的区档案馆办理",
		"API_ID_A": "96fdd81d-6d09-4524-b22a-8bf7b6919b27",
		"API_ID_B": "84c44a75-6d59-4d11-890a-54a2b62a7e01",
		"API_ID_C": "01a74930-bbaf-46d5-aa6b-88ab49f59c18",
		"API_ID_D": "7fde320e-ad85-4a89-bf46-ba912cf68e68"
	},
	{
		"id": "0010",
		"stuffName": "再生育子女审批档案查询",
		"code": "312090161000",
		"img": "../libs/common/images/icon-link6.png",
		"guideline": "申请人应为在本市人口和计划生育行政管理部门办理再生育子女审批手续的夫妻本人。委托他人代为办理的，代理人应按有关规定前往保管该档案的区档案馆办理。",
		"API_ID_A": "d19b1be9-8709-4a16-b8ef-16c4d9d74bbe",
		"API_ID_B": "ad229b61-a913-41dc-9c9d-10ccc65f7c3c",
		"API_ID_C": "4f19c414-600a-436c-ba25-74c47d3f70fc",
		"API_ID_D": "737234dd-b290-4b57-ac37-b1e643b0efa7"
	}, {
		"id": "0020",
		"stuffName": "工伤认定档案查询",
		"code": "312090162000",
		"img": "../libs/common/images/icon-link6.png",
		"guideline": "申请人应为在本市劳动和社会保障部门办理工伤认定的工伤人员本人。委托他人代为办理的，代理人应按有关规定前往保管该档案的区档案馆办理。",
		"API_ID_A": "31d5e3e7-0b6c-400a-9d9b-73df8722e1fe",
		"API_ID_B": "1d7b2866-3ea8-4e13-a828-125c40381b9c",
		"API_ID_C": "a3e7e596-2bdc-4549-aee3-85b09e60e4ab",
		"API_ID_D": "70813553-082a-4853-8722-dfe32a2cbad3"
	}, {
		"id": "0080",
		"stuffName": "学籍档案查询",
		"code": "312090163000",
		"img": "../libs/common/images/icon-link6.png",
		"guideline": "申请人应为曾在本市部分已撤销中小学就读的学生本人或其监护人。委托其他人代为办理的，代理人应按有关规定前往保管该档案的区档案馆办理。",
		"API_ID_A": "62732037-bd63-41c8-99a2-4df94e540eb4",
		"API_ID_B": "471a4bd0-e221-44e7-ae2d-ec47ea0dbaa9",
		"API_ID_C": "d00eee46-c53b-49fb-81d6-4d54d4ccdbdd",
		"API_ID_D": "e8d1a115-c6a6-4f78-8519-ac7e0408ea34"
	}, {
		"id": "0090",
		"stuffName": "复员退伍军人档案查询",
		"code": "312090165000",
		"img": "../libs/common/images/icon-link6.png",
		"guideline": "申请人应为复员退伍军人档案记载的军人本人。委托其他人代为办理的，代理人应按有关规定前往保管该档案的区档案馆办理。",
		"API_ID_A": "00d86212-d962-47a5-b0ae-20852b8240c9",
		"API_ID_B": "3952da34-b645-460f-b6a9-47cdb0ea4e18",
		"API_ID_C": "e4e43ccc-540b-4bff-80ad-afc5a6f82196",
		"API_ID_D": "cf0e618a-d06c-415f-a027-649780e7be18"
	}, {
		"id": "0100",
		"stuffName": "兵役档案查询",
		"code": "312090164000",
		"img": "../libs/common/images/icon-link6.png",
		"guideline": "申请人应为兵役档案记载的军人本人。委托其他人代为办理的，代理人应按有关规定前往保管该档案的区档案馆办理。",
		"API_ID_A": "55f74b88-3834-47cc-8cab-3f4834b1879e",
		"API_ID_B": "73615e45-43ba-4789-9439-16d70b42af01",
		"API_ID_C": "149d3cdb-080c-4b4d-8195-ce8b6174a673",
		"API_ID_D": "08d0ed5b-14b8-42f1-9725-0c7ea4c9c2b4"
	}, {
		"id": "0110",
		"stuffName": "人才引进审批档案查询",
		"code": "312090167000",
		"img": "../libs/common/images/icon-link6.png",
		"guideline": "申请人应为人才引进审批档案所记载的引进人才本人。 委托其他人代为办理的，代理人应按有关规定前往保管该档案的区档案馆办理。",
		"API_ID_A": "73d3804b-7faf-40f9-b655-7ee481d782f6",
		"API_ID_B": "4174fc54-dc40-4a95-8be6-38905bf874aa",
		"API_ID_C": "dfd9abc9-d632-4e83-ab7e-11bc32be7657",
		"API_ID_D": "6dfc2393-c0c6-40f1-9266-169dbdab5128"
	}, {
		"id": "0120",
		"stuffName": "三峡移民档案查询",
		"code": "312090166000",
		"img": "../libs/common/images/icon-link6.png",
		"guideline": "申请人应为本市已移交进馆的部分区人民政府移民办办理三峡移民安置工作中直接形成的三峡移民档案所记载的当事人本人。 委托其他人代为办理的，代理人应按有关规定前往保管该档案的区档案馆办理。",
		"API_ID_A": "f93f7544-3cb3-47ca-9b7b-dbd6772726fc",
		"API_ID_B": "413455ab-9403-4b79-87e0-b50cdd82cffa",
		"API_ID_C": "f8aee5d6-a116-4c56-8d03-769a20903216",
		"API_ID_D": "238f0de4-b45e-407b-8436-cd11cdfe07dc"
	}, {
		"id": "0130",
		"stuffName": "涉外婚姻登记档案查询",
		"code": "312090187000",
		"img": "../libs/common/images/icon-link6.png",
		"guideline": "申请人应为在本市民政部门办理涉外婚姻登记（结婚、离婚）手续的婚姻当事人本人。受婚姻当事人委托代为办理的，或其他相关利用人，应按有关规定前往保管该档案的区档案馆办理。",
		"API_ID_A": "704ec630-fa58-4fe2-8b1f-46351619d3c3",
		"API_ID_B": "2a0ac58a-3ec8-4740-8352-3a1b2dbbcaeb",
		"API_ID_C": "79ad5af1-9072-4d88-af2b-e4c7eec17006",
		"API_ID_D": "730b5b86-f121-4ef9-89b9-59a8519ecf06"
	}, {
		"id": "0140",
		"stuffName": "市级人才引进审批档案查询",
		"code": "312090186000",
		"img": "../libs/common/images/icon-link6.png",
		"guideline": "申请人应为办理市级人才引进审批档案的本人。委托他人代为办理的，代理人应按有关规定前往保管该档案的区档案馆办理。",
		"API_ID_A": "b9e62dc8-da70-467a-8c97-6afe33307c7c",
		"API_ID_B": "cb263153-bf07-4f2f-acd1-5c113b7c3f2b",
		"API_ID_C": "9f62bcf2-2fd1-4ef8-bd74-a68e31f422d2",
		"API_ID_D": "5d622889-6ab1-4708-96e6-bb1d89ae6f8a"
	}, {
		"id": "0150",
		"stuffName": "专业技术干部的农村家属迁往城镇审批档案",
		"code": "312090186000",
		"img": "../libs/common/images/icon-link6.png",
		"guideline": "申请人应为在本市办理专业技术干部的农村家属迁往城镇审批档案的本人。受知青委托代为办理的，或其他相关利用人，应按有关规定前往保管该档案的区档案馆办理。",
		"API_ID_A": "cbbaeaa2-a1ff-4d6e-a338-42481e1edac7",
		"API_ID_B": "39ad1076-af8f-428b-b060-85a37628c660",
		"API_ID_C": "b02d51fc-632b-4a36-ba2c-0c2e448cbaa5",
		"API_ID_D": "3116bcff-7027-433e-8d36-c484d10ee19d"
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