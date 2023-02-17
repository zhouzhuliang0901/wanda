//上海银行事项列表
var shyhListJsonName = {};
//人社
//shyhListJsonName.RSList = [{
//	'name': '参加个人城镇基本养老保险缴费情况'
//}, {
//	'name': '转往外省市缴费凭证'
//}, {
//	'name': '上年度养老保险个人权益记录单'
//}, {
//	'name': '灵活就业人员电子对账单'
//}, {
//	'name': '个人享受城镇基本养老金情况'
//}, {
//	'name': '城乡居保缴费情况'
//}, {
//	'name': '享受城乡居保养老金情况'
//}, {
//	'name': '失业登记'
//}, {
//	'name': '养老金卡（折）调整'
//}, {
//	'name': '居住证积分查询'
//}, {
//	'name': '申请大龄失业就业岗位补贴'
//}];
//市残联
shyhListJsonName.CL_List = [{
	'name': '盲人有声读物阅览室（角）信息查询'
}, {
	'name': '无障碍电影放映点信息查询'
}];
//市交通委
shyhListJsonName.JTW_List = [{
	'name': '网约车平台企业信息查询'
}, {
	'name': '驾照考点查询'
}, {
	'name': '交通卡服务网点查询'
}];
//市医保局  商业保险机构网点信息查询   医保诊疗项目约定服务医院查询 长护险护理机构查询  本市开展异地就医的医院查询
shyhListJsonName.YB_List = [{
		'name': '本市开展异地就医的医院查询'
	},
	{
		'name': '服务机构查询'
	},
	{
		'name': '医保诊疗项目约定服务医院查询'
	},
	{
		'name': '零星报销进度查询'
	},
	{
		'name': '参保人员待遇查询'
	},
	{
		'name': '商业保险机构网点信息查询'
	},
	{
		'name': '药品及床位费支付标准查询'
	},
	{
		'name': '医疗费用分担模拟计算器'
	},
	{
		'name': '医疗机构查询'
	},
	{
		'name': '长护险护理机构查询'
	},
];
//市民政局
//shyhListJsonName.MZ_List = [{
//	'name': '上海市城乡居民最低生活保障家庭证明出具'
//}, {
//	'name': '上海市低收入困难家庭证明出具'
//}, {
//	'name': '上海市定期定量生活补贴人员证明出具'
//}, {
//	'name': '上海市特困人员证明出具'
//}, {
//	'name': '上海市因病支出型贫困家庭证明出具'
//}, {
//	'name': '婚姻登记预约'
//}];
// //市公安局
// shyhListJsonName.GA_List = [{
// 	'name': '属地派出所查询'
// }];

//市教委
shyhListJsonName.JW_List = [{
	'name': '民办教育机构查询'
}, {
	'name': '全市示范高中信息查询'
}, {
	'name': '中外合作办学信息查询'
}, {
	'name': '小升初报名信息查询(公办、民办)'
}, {
	'name': '幼升小报名信息查询(公办、民办)'
}, {
	'name': '3岁以下幼儿托育服务机构查询'
}, {
	'name': '民办非学历培训机构查询'
}];
//市市场监管局
shyhListJsonName.SCJG_List = [{
	'name': '餐饮脸谱查询'
}, {
	'name': '社会公用计量标准查询'
}, {
	'name': '法定计量检定机构考评员查询'
}, {
	'name': '计量标准考评员查询'
}, {
	'name': '二级注册计量师查询'
}, {
	'name': '企业名称状态查询'
}, {
	'name': '合同示范文本查询'
}, {
	'name': '广告发布登记查询'
}];
//市住建委
shyhListJsonName.ZJ_List = [{
	'name': '公积金贷款试算'
}, {
	'name': '公积金还款计算'
}, {
	'name': '公积金缴存计算'
}, {
	'name': '公积金区管理部查询'
}, {
	'name': '公积金缴存提取经办银行查询'
}, {
	'name': '纯公积金贷款受理网点'
}, {
	'name': '公积金缴存表查询'
},{
	'name': '组合贷款各受理银行查询'
}];
//规划资源局服务
shyhListJsonName.GH_List = [{
	'name': '地质资料档案查询'
}];

//市农业委员会
shyhListJsonName.NW_List = [{
	'name': '宠物诊疗机构查询'
}];

//市卫健委
shyhListJsonName.WJW_List = [{
	'name': '护士执业资格信息查询'
}, {
	'name': '医师执业资格信息查询'
}];
//市应急局
shyhListJsonName.YJGL_List = [{
	'name': '上海市禁止、限制和控制危险化学品目录（第三批）查询'
}, {
	'name': '危险化学品生产企业安全生产许可证查询'
}, {
	'name': '危险化学品经营许可证核发查询'
}, {
	'name': '危险化学品建设项目信息查询'
}, {
	'name': '安全评价机构资质许可查询'
}, {
	'name': '安全生产检测检验机构资质认可查询'
}, {
	'name': '安全生产协会会员培训机构条件核实情况查询'
}];
//市民政局
shyhListJsonName.MZ_List = [{
	'name': '社区事务受理地址查询'
}];
//市商务委
shyhListJsonName.SWW_List = [{
	'name': '预付卡余额查询'
}, {
	'name': '预付卡信息查询'
}, {
	'name': '预付卡警示名单查询'
}];
//申康医联
shyhListJsonName.SKYL_List = [{
	'name': '耗材查询'
}, {
	'name': '价格公示'
}];
var shyhMainPerjsonStr = [{
	"name": "市交通委",
	"url": "JTW_List",
}, {
	"name": "市医保局",
	"url": "YB_List",
}, {
	"name": "市残联",
	"url": "CL_List",
}, {
	"name": "市教委",
	"url": "JW_List",
}, {
	"name": "市市场监管局",
	"url": "SCJG_List",
}, {
	"name": "市住建委",
	"url": "ZJ_List",
}, {
	"name": "规划资源局服务",
	"url": "GH_List",
}, {
	"name": "市农业委员局",
	"url": "NW_List",
}, {
	"name": "市卫健委服务",
	"url": "WJW_List",
}, {
	"name": "应急局服务",
	"url": "YJGL_List",
}, {
	"name": "市民政局",
	"url": "MZ_List",
}, {
	"name": "市商务委",
	"url": "SWW_List",
},{
	"name": "申康医联",
	"url": "SKYL_List",
}, ];