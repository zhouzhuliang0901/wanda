var perjsonStr = [{
		"stuffName": "参保人员待遇查询",
		"type": "treatment",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalTreatmentDetails/index.html"
	},
	{
		"stuffName": "医保个人信息",
		"type": "info",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalInsureInfo/index.html"
	},
	{
		"stuffName": "医保综合减负试算",
		"type": "reduce",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalReduceTrial/index.html"
	}, {
		"stuffName": "办理门急诊就医记录册",
		"type": "bookMaking",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalBookMaking/index.html"
	}, {
		"stuffName": "医保就医明细查询",
		"type": "medicalDetails",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalDetailsQuery/index.html"
	}, {
		"stuffName": "帐户清算信息查询",
		"type": "accountSettlement",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalClearInfoQuery/index.html"
	}, {
		"stuffName": "服务机构查询",
		"type": "services",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_servOrgQuery/index.html"
	}, {
		"stuffName": "医疗机构查询",
		"type": "medicalInstitution",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalOrgQuery/index.html"
	}, {
		"stuffName": "办理医保网站密码变更",
		"type": "password",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_passwordUpdate/index.html"
	}, {
		"stuffName": "药品及床位费支付标准查询",
		"type": "payStand",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_payStandard/index.html"
	}, {
		"stuffName": "医疗费用分担模拟计算器",
		"type": "calculator",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalCostCalculator/index.html"
	}, {
		"stuffName": "办理结算服务项目的相关手续",
		"type": "settlementService",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_settlementService/index.html"
	}, {
		"stuffName": "办理居民医保受理登记",
		"type": "acceptRegister",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_acceptRegister/index.html"
	}, {
		"stuffName": "办理医疗卡挂失及撤销",
		"type": "medicalCard",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalCard/index.html"
	}, {
		"stuffName": "年度累计医疗费用信息查询",
		"type": "YB_accumulatedMedicalExpenses",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_accumulatedMedicalExpenses/index.html"
	}, {
		"stuffName": "异地就医一件事",
		"type": "../sqfw/index.html#/guidelineJygx",
		"ywlx": "",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../sqfw/index.html#/guidelineJygx"
	}, {
		"stuffName": "零星报销进度查询",
		"type": "../YB_sporadicReimbursementQuery/index.html",
		"ywlx": "",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_sporadicReimbursementQuery/index.html"
	}, {
		"stuffName": "帮困补助进度查询",
		"url": "../YB_helpfulQueries/index.html",
		"ywlx": "",
		"img": "../libs/common/images/newIcon/YB.png",
	}, {
		"stuffName": "帮困登记缴费进度查询",
		"url": "../YB_helpfulQueriesNext/index.html",
		"ywlx": "",
		"img": "../libs/common/images/newIcon/YB.png",
	}
	//	, {
	//		"stuffName": "零星报销进度查询",
	//		"type": "reimbursement",
	//		"ywlx":"01",
	//		"img": "../libs/common/images/newIcon/YB.png",
	//	}, {
	//		"stuffName": "居保登记缴费进度查询",
	//		"type": "residentRegistration",
	//		"ywlx":"02",
	//		"img": "../libs/common/images/newIcon/YB.png",
	//	}, {
	//		"stuffName": "帮困登记缴费进度查询",
	//		"type": "helpRegistration",
	//		"ywlx":"03",
	//		"img": "../libs/common/images/newIcon/YB.png",
	//	}, {
	//		"stuffName": "帮困补助进度查询",
	//		"type": "helpSubsidy",
	//		"ywlx":"04",
	//		"img": "../libs/common/images/newIcon/YB.png",
	//	}, {
	//		"stuffName": "长护险申请进度查询",
	//		"type": "longTermInsurance",
	//		"ywlx":"05",
	//		"img": "../libs/common/images/newIcon/YB.png",
	//	}
];
if(jQuery.getConfigMsg.isCommunityNew == "Y"  && jQuery.getConfigMsg.isCommunity !== "N") {
	perjsonStr = [{
		"stuffName": "医疗机构查询",
		"type": "medicalInstitution",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalOrgQuery/index.html"
	}, {
		"stuffName": "服务机构查询",
		"type": "services",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_servOrgQuery/index.html"
	}, {
		"stuffName": "医保就医明细查询",
		"type": "medicalDetails",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalDetailsQuery/index.html"
	}, {
		"stuffName": "帐户清算信息查询",
		"type": "accountSettlement",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalClearInfoQuery/index.html"
	}, {
		"stuffName": "药品及床位费支付标准查询",
		"type": "payStand",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_payStandard/index.html"
	}, {
		"stuffName": "医疗费用分担模拟计算器",
		"type": "calculator",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalCostCalculator/index.html"
	}, {
		"stuffName": "医保个人信息",
		"type": "info",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalInsureInfo/index.html"
	}, {
		"stuffName": "办理门急诊就医记录册",
		"type": "bookMaking",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalBookMaking/index.html"
	}, {
		"stuffName": "办理结算服务项目的相关手续",
		"type": "settlementService",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_settlementService/index.html"
	}, {
		"stuffName": "办理医保网站密码变更",
		"type": "password",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_passwordUpdate/index.html"
	}, {
		"stuffName": "办理居民医保受理登记",
		"type": "acceptRegister",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_acceptRegister/index.html"
	}, {
		"stuffName": "医保综合减负试算",
		"type": "reduce",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalReduceTrial/index.html"
	}, {
		"stuffName": "参保人员待遇查询",
		"type": "treatment",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalTreatmentDetails/index.html"
	}, {
		"stuffName": "年度累计医疗费用信息查询",
		"type": "YB_accumulatedMedicalExpenses",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_accumulatedMedicalExpenses/index.html"
	}, {
		"stuffName": "零星报销进度查询",
		"type": "../YB_sporadicReimbursementQuery/index.html",
		"ywlx": "",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_sporadicReimbursementQuery/index.html"
	}, {
		"stuffName": "办理医疗卡挂失及撤销",
		"type": "medicalCard",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalCard/index.html"
	}, {
		"stuffName": "异地就医一件事",
		"type": "../sqfw/index.html#/guidelineJygx",
		"ywlx": "",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../sqfw/index.html#/guidelineJygx"
	}, {
		"stuffName": "帮困补助进度查询",
		"url": "../YB_helpfulQueries/index.html",
		"ywlx": "",
		"img": "../libs/common/images/newIcon/YB.png",
	}, {
		"stuffName": "帮困登记缴费进度查询",
		"url": "../YB_helpfulQueriesNext/index.html",
		"ywlx": "",
		"img": "../libs/common/images/newIcon/YB.png",
	}]
}else if(jQuery.getConfigMsg.isCommunity != "N") {
	console.log(jQuery.getConfigMsg.isCommunity);
	perjsonStr = [{
		"stuffName": "医疗机构查询",
		"type": "medicalInstitution",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalOrgQuery/index.html"
	}, {
		"stuffName": "医保就医明细查询",
		"type": "medicalDetails",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalDetailsQuery/index.html"
	}, {
		"stuffName": "帐户清算信息查询",
		"type": "accountSettlement",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalClearInfoQuery/index.html"
	}, {
		"stuffName": "药品及床位费支付标准查询",
		"type": "payStand",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_payStandard/index.html"
	}, {
		"stuffName": "医疗费用分担模拟计算器",
		"type": "calculator",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalCostCalculator/index.html"
	}, {
		"stuffName": "医保个人信息",
		"type": "info",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalInsureInfo/index.html"
	}, {
		"stuffName": "办理门急诊就医记录册",
		"type": "bookMaking",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalBookMaking/index.html"
	}, {
		"stuffName": "办理结算服务项目的相关手续",
		"type": "settlementService",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_settlementService/index.html"
	}, {
		"stuffName": "办理医保网站密码变更",
		"type": "password",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_passwordUpdate/index.html"
	}, {
		"stuffName": "办理居民医保受理登记",
		"type": "acceptRegister",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_acceptRegister/index.html"
	}, {
		"stuffName": "医保综合减负试算",
		"type": "reduce",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalReduceTrial/index.html"
	}, {
		"stuffName": "参保人员待遇查询",
		"type": "treatment",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_medicalTreatmentDetails/index.html"
	}, {
		"stuffName": "年度累计医疗费用信息查询",
		"type": "YB_accumulatedMedicalExpenses",
		"ywlx": "00",
		"img": "../libs/common/images/newIcon/YB.png",
		"url": "../YB_accumulatedMedicalExpenses/index.html"
	}]
}