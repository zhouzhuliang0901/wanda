//100事项搜索js
var searchListName = {};
//人社
searchListName.RSList = [{
	'name': '参加个人城镇基本养老保险缴费情况',
	"url": '../RS_endowInsurePay/index.html'
}, {
	'name': '转往外省市缴费凭证',
	'url': '../RS_zwwssjfpz/index.html'
}, {
	'name': '上年度养老保险个人权益记录单',
	'url': '../RS_lastYearEndowInsureRecord_cxjb/index.html'
}, {
	'name': '灵活就业人员电子对账单',
	'url': '../RS_nimbleEmployBill/index.html'
}, {
	'name': '个人享受城镇基本养老金情况',
	'url': '../RS_grxsczjblyjqk/index.html'
}, {
	'name': '城乡居保缴费情况',
	'url': '../RS_cxjbjfqk/index.html'
}, {
	'name': '享受城乡居保养老金情况',
	'url': '../RS_cityBaseOldPension/index.html'
}, {
	'name': '居住证积分查询',
	'url': '../RS_residenceIntegralQuery/index.html'
}, {
	'name': '业务审核办结情况',
	'url': '../RS_ywshjbqk/index.html'
}, {
	'name': '劳动者个人基本信息查询及维护',
	'url': '../RS_queryWorkerInfo/index.html'
}, {
	'name': '社保卡个人信息查询',
	'url': '../RS_ssCardInfo/index.html'
}, {
	'name': '上年度养老保险个人权益记录单（城乡居保）',
	'url': '../RS_lastYearEndowInsureRecord_cxjb/index.html'
}, {
	'name': '失业登记',
	'url': '../RS_unemploymentRegistration/index.html'
}, {
	'name': '灵活就业登记',
	'url': '../flexibleEmployment/index.html'
}, {
	'name': '申请大龄失业就业岗位补贴',
	'url': '../RS_unemploymentAllowance/index.html'
}, {
	'name': '养老金卡（折）调整',
	'url': '../RS_PensionAdjustmentCardOrPassbook/index.html'
}];
//市残联
searchListName.CL_List = [{
	'name': '盲人有声读物阅览室（角）信息查询',
	'url': '../CL_readingRoomLocations/index.html'
}, {
	'name': '无障碍电影放映点信息查询',
	'url': '../CL_movieLocationInquiry/index.html'
}, {
	'name': '残疾人交通补贴申请',
	'url': '../CL_travelAllowance/index.html'
}];
//市交通委
searchListName.JTW_List = [{
	'name': '公交卡余额查询',
	'url': '../JTW_queryBalance/index.html'
}, {
	'name': '驾照考点查询',
	'url': '../JTW_queryDrivingTestSite/index.html'
}, {
	'name': '交通卡服务网点查询',
	'url': '../JTW_travelCardNetworkQuery/index.html'
}, {
	'name': '网约车平台企业信息查询',
	'url': '../JTW_onlineCarHailingInfo/index.html'
}];
//市医保局  商业保险机构网点信息查询   医保诊疗项目约定服务医院查询 长护险护理机构查询  本市开展异地就医的医院查询
searchListName.YB_List = [{
	'name': '本市开展异地就医的医院查询',
	'url': '../YB_servOrgQuery/index.html#/bskzydjyChoose'
}, {
	'name': '办理医保网站密码变更',
	'url': '../YB_passwordUpdate/index.html#/index.html'
}, {
	'name': '服务机构查询',
	'url': '../YB_servOrgQuery/index.html#/ybxgfwChoose'
}, {
	'name': '医保诊疗项目约定服务医院查询',
	'url': '../YB_servOrgQuery/index.html#/ybzlydfwChoose'
}, {
	'name': '零星报销进度查询',
	'url': '../YB_sporadicReimbursementQuery/index.html'
}, {
	'name': '参保人员待遇查询',
	'url': '../YB_medicalTreatmentDetails/index.html'
}, {
	'name': '商业保险机构网点信息查询',
	'url': '../YB_servOrgQuery/index.html#/sybxjgwdChoose'
}, {
	'name': '药品及床位费支付标准查询',
	'url': '../YB_payStandard/index.html'
}, {
	'name': '医疗费用分担模拟计算器',
	'url': '../YB_medicalCostCalculator/index.html'
}, {
	'name': '医疗机构查询',
	'url': '../YB_medicalOrgQuery/index.html'
}, {
	'name': '长护险护理机构查询',
	'url': '../YB_servOrgQuery/index.html#/servicesList?lastMenu=true'
}, {
	'name': '医保就医明细查询',
	'url': '../YB_medicalDetailsQuery/index.html'
}, {
	'name': '帐户清算信息查询',
	'url': '../YB_medicalClearInfoQuery/index.html'
}, {
	'name': '年度累计医疗费用信息查询',
	'url': '../YB_accumulatedMedicalExpenses/index.html'
}, {
	'name': '综合减负试算',
	'url': '../YB_medicalReduceTrial/index.html'
}, {
	'name': '办理结算服务项目的相关手续',
	'url': '../YB_settlementService/index.html'
}, {
	'name': '办理居民医保受理登记',
	'url': '../YB_acceptRegister/index.html'
}, {
	'name': '办理医疗卡挂失及撤销',
	'url': '../YB_medicalCard/index.html'
}, {
	'name': '医保个人信息',
	'url': '../YB_medicalInsureInfo/index.html'
}, {
	'name': '帮困补助进度查询',
	'url': '../YB_helpfulQueries/index.html'
}, {
	'name': '帮困登记缴费进度查询',
	'url': '../YB_helpfulQueriesNext/index.html'
}];
//市民政局
searchListName.MZ_List = [{
	'name': '城乡居民最低生活保障家庭证明出具',
	'url': '../MZ_CXJMZDSHBZJTZMCJ/index.html'
}, {
	'name': '低收入困难家庭证明开具',
	'url': '../MZ_DSRKNJTZMCJ/index.html'
}, {
	'name': '定期定量生活补贴人员证明出具',
	'url': '../MZ_DQDLSHBTRYZMCJ/index.html'
}, {
	'name': '特困人员证明出具',
	'url': '../MZ_TKRYZMCJ/index.html'
}, {
	'name': '因病支出型贫困家庭证明开具',
	'url': '../MZ_YBZCXPKJTZMCJ/index.html'
}];
//市公安局
searchListName.GA_List = [{
	'name': '属地派出所查询',
	'url': '../GA_policeStationQuery/index.html'
}, {
	'name': '出入境记录查询',
	'url': '../GA_movementRecord/index.html'
}, {
	'name': '新版社保卡开通',
	'url': '../GA_ssCardOpen/index.html'
}, {
	'name': "机动车违法信息查询",
	'url': '../GA_vehIllegalInfoQuery/index.html?type=0'
}, {
	'name': "驾驶证违法信息查询",
	'url': '../GA_vehIllegalInfoQuery/index.html?type=1'
}, {
	'name': '非上海生源高校毕业生落户查询',
	'url': '../GA_fshsygxbyslh/index.html'
}, {
	'name': '开具有无违法犯罪记录证明',
	'url': '../GA_isCrimeProve/index.html'
}, {
	'name': '户籍人户分离人员居住登记注销',
	'url': '../GA_hjrhflryjzdjzx/index.html'
}, {
	'name': "户籍证明开具",
	'url': '../GA_householdRegister/index.html#/choiceMode'
}, {
	'name': "户籍人户分离人员居住登记受理",
	'url': '../GA_hjrhflryjzdjsl/index.html'
}, ];

//市教委
searchListName.JW_List = [{
	'name': '上海市计算机等级考试成绩查询',
	'url': '../JW_queryScoreOfNCRE/index.html'
}, {
	'name': '民办教育机构查询',
	'url': '../JW_privateEducational/index.html'
}, {
	'name': '全市示范高中信息查询',
	'url': '../JW_modelHighschool/index.html'
}, {
	'name': '中外合作办学信息查询',
	'url': '../JW_cooperateEducationInfo/index.html'
}, {
	'name': '小升初报名信息查询(公办、民办)',
	'url': '../JW_queryRegistrationInfo_XSC/index.html'
}, {
	'name': '幼升小报名信息查询(公办、民办)',
	'url': '../JW_queryRegistrationInfo_YSX/index.html'
}, {
	'name': '3岁以下幼儿托育服务机构查询',
	'url': '../JW_queryNurseryService/index.html'
}, {
	'name': '民办非学历培训机构查询',
	'url': '../JW_queryTrainingInstitutions/index.html'
}, {
	'name': '上海市学生事务中心存档证明',
	'url': '../JW_studentAffairsCenter/index.html'
}];
//市市场监管局
searchListName.SCJG_List = [{
	'name': '餐饮脸谱查询',
	'url': '../SCJG_cateringFacebook/index.html'
}, {
	'name': '社会公用计量标准查询',
	'url': '../SCJG_queryMeasurementstandard/index.html'
}, {
	'name': '法定计量检定机构考评员查询',
	'url': '../SCJG_queryAssessor/index.html'
}, {
	'name': '计量标准考评员查询',
	'url': '../SCJG_queryMeasurementStandardAssessor/index.html'
}, {
	'name': '二级注册计量师查询',
	'url': '../SCJG_queryMetrologist/index.html'
}, {
	'name': '企业名称状态查询',
	'url': '../SCJG_queryEnterpriseNameStatus/index.html'
}, {
	'name': '广告发布登记查询',
	'url': '../SCJG_queryAdvertising/index.html'
}];
//市住建委
searchListName.ZJ_List = [{
	'name': '公积金区管理部查询',
	'url': '../ZJ_accumulationFundQGLB/index.html'
}, {
	'name': '公积金缴存提取经办银行查询',
	'url': '../ZJ_accumulationFundBankOfJCTQ/index.html'
}, {
	'name': '纯公积金贷款受理网点',
	'url': '../ZJ_accumulationFundDKWD/index.html'
}, {
	'name': '公积金缴存表查询',
	'url': '../ZJ_accumulationFundJCB/index.html'
}, {
	'name': '组合贷款各受理银行查询',
	'url': '../ZJ_loanbanks/index.html'
}, {
	'name': '公积金贷款试算',
	'url': '../ZJ_accumulationFundDK/index.html'
}, {
	'name': '公积金还款计算',
	'url': '../ZJ_accumulationFundHK/index.html'
}, {
	'name': '公积金缴存计算',
	'url': '../ZJ_accumulationFundJC/index.html'
},];

//市发改委
searchListName.FGW_List = [{
	'name': '个人信用报告查询',
	'url': '../FGW_perCreditQuery/index.html'
}];
//规划资源局服务
searchListName.GH_List = [{
	'name': '不动产登记信息自助查询、打印',
	'url': '../GH_realEstateInformation/index.html'
}, {
	'name': '地质资料档案查询',
	'url': '../GH_searchOfGeologicalRecords/index.html'
}];
//市经信委
searchListName.JXW_List = [{
	'name': '市民网上实名认证服务',
	'url': '../JXW_citizenAuthentication/index.html'
}];

//市税务局
searchListName.SWJ_List = [{
	'name': '房产税查询',
	'url': '../SWJ_propertyTaxQuery/index.html'
}];
//市卫健委
searchListName.WJW_List = [{
	'name': '护士执业资格信息查询',
	'url': '../WJW_nurseQualificationQuery/index.html'
}, {
	'name': '医师执业资格信息查询',
	'url': '../WJW_medicalQualificationQuery/index.html'
}];
//市应急局
searchListName.YJGL_List = [{
	'name': '安全生产检测检验机构资质认可查询',
	'url': '../YJGL_queryQualificationRecognition/index.html'
}, {
	'name': '安全生产协会会员培训机构条件核实情况查询',
	'url': '../YJGL_querySafetyProductionInfo/index.html'
}, {
	'name': '上海市禁止、限制和控制危险化学品目录（第三批）查询',
	'url': '../YJGL_queryHazardousChemicals/index.html'
}, {
	'name': '危险化学品生产企业安全生产许可证查询',
	'url': '../YJGL_queryProductionLicense/index.html'
}, {
	'name': '危险化学品经营许可证核发查询',
	'url': '../YJGL_queryBusinessLicense/index.html'
}, {
	'name': '危险化学品建设项目信息查询',
	'url': '../YJGL_queryHCProjectInfo/index.html'
}, {
	'name': '安全评价机构资质许可查询',
	'url': '../YJGL_queryQualificationPermission/index.html'
}, ];
//申康医联
searchListName.SKYL_List = [{
	'name': '耗材查询',
	'url': '../SKYL_consumablesQuery/index.html'
}, {
	'name': '价格公示',
	'url': '../SKYL_pricePublicity/index.html'
}];
//粮食局
searchListName.LS_List = [{
	'name': '副食品价格补贴发放银行卡维护',
	'url': '../LS_nonStapleFood/index.html'
}];
//市司法局
searchListName.SF_List = [{
	'name': '律师专职执业变更兼职执业',
	'url': '../SF_lawyerFullTimeToPartTime/index.html'
}, {
	'name': '首次申请律师专职执业审核',
	'url': '../SF_firstApplyLawyerFullTime/index.html'
}, {
	'name': '重新申请律师专职执业',
	'url': '../SF_reapplyLawyerFullTime/index.html'
}, {
	'name': '律师兼职执业变更专职执业',
	'url': '../SF_lawyerPartTimeToFullTime/index.html'
}, {
	'name': '律师执业机构变更',
	'url': '../SF_lawyerPracticeOrganChange/index.html'
}, {
	'name': '律师执业证注销',
	'url': '../SF_lawyerPracticeCertificateCancel/index.html'
}, {
	'name': '首次申请律师兼职执业审核',
	'url': '../SF_firstApplyLawyerPartTime/index.html'
}, {
	'name': '重新申请律师兼职执业',
	'url': '../SF_reapplyLawyerPartTime/index.html'
}];
//市商务委
searchListName.SWW_List = [{
	'name': '预付卡余额查询',
	'url': '../SWW_prepaidCardBalanceQuery/index.html'
}, {
	'name': '预付卡信息查询',
	'url': '../SWW_prepaidCardInfoQuery/index.html'
}, {
	'name': '预付卡警示名单查询',
	'url': '../SWW_prepaidCardWarningQuery/index.html'
}];

function fuzzyQuery(list, keyWord) {
	var arr = [];
	for(var i = 0; i < list.length; i++) {
		if(list[i].name.split(keyWord).length > 1) {
			arr.push(list[i]);
		}
	}
	return arr;
}

function searchList(keyWord) {
	var dataList = {},
		fuzzyQueryData;
	for(let i in searchListName) {
		fuzzyQueryData = fuzzyQuery(searchListName[i], keyWord);
		if(fuzzyQueryData.length > 0) {
			dataList[i] = fuzzyQueryData;
		}
	}
	return dataList;
}