function formJzdj(appData){
	$.device.officeSetStringValue('stName', appData.stName);
	$.device.officeSetStringValue('stIdCard', appData.stIdCard);
	$.device.officeSetStringValue('mobile', appData.mobile);
	$.device.officeSetStringValue('stSex', appData.stSex);
	$.device.officeSetStringValue('nations', appData.nations);
	$.device.officeSetStringValue('address', appData.address);
	$.device.officeSetStringValue('shCounty', appData.shCounty);
	$.device.officeSetStringValue('shStreet', appData.shStreet);
	$.device.officeSetStringValue('stShRoad', appData.stShRoad);
	$.device.officeSetStringValue('stShLane', appData.stShLane);
	$.device.officeSetStringValue('stShNo', appData.stShNo);
	$.device.officeSetStringValue('stShRoom', appData.stShRoom);
	choiceJzdj(appData.liveType,appData.liveReason,appData.education);
	$.device.officeSetStringValue('other', appData.other);
	$.device.officeReadOnly(true);
}

function formJzsq(appData){
	$.device.officeSetStringValue('stName', appData.stName);
	$.device.officeSetStringValue('stIdCard', appData.stIdCard);
	$.device.officeSetStringValue('stPhone', appData.stPhone);
	$.device.officeSetStringValue('stTel', appData.stTel);
	$.device.officeSetStringValue('stSex', appData.stSex);
	$.device.officeSetStringValue('stMail', appData.stMail);
	$.device.officeSetStringValue('nations', appData.nations);
	$.device.officeSetStringValue('address', appData.address);
	$.device.officeSetStringValue('shCounty', appData.shCounty);
	$.device.officeSetStringValue('shStreet', appData.shStreet);
	$.device.officeSetStringValue('stShRoad', appData.stShRoad);
	$.device.officeSetStringValue('stShLane', appData.stShLane);
	$.device.officeSetStringValue('stShNo', appData.stShNo);
	$.device.officeSetStringValue('stShRoom', appData.stShRoom);
	choiceJzsq(appData.liveType,appData.liveReason,appData.education,appData.takePhoto,appData.ifStudy,appData.ifWorking,appData.industyType,appData.unoccupied,appData.liveSource);
	$.device.officeSetStringValue('other', appData.other);
	$.device.officeSetStringValue('stRelationship1', appData.stRelationship1);
	$.device.officeSetStringValue('stName1', appData.stName1);
	$.device.officeSetStringValue('stIdCard1', appData.stIdCard1);
	$.device.officeSetStringValue('stRelationship2', appData.stRelationship2);
	$.device.officeSetStringValue('stName2', appData.stName2);
	$.device.officeSetStringValue('stIdCard2', appData.stIdCard2);
	$.device.officeSetStringValue('stRelationship3', appData.stRelationship3);
	$.device.officeSetStringValue('stName3', appData.stName3);
	$.device.officeSetStringValue('stIdCard3', appData.stIdCard3);
	$.device.officeSetStringValue('stRelationship4', appData.stRelationship4);
	$.device.officeSetStringValue('stName4', appData.stName4);
	$.device.officeSetStringValue('stIdCard4', appData.stIdCard4);
	$.device.officeSetStringValue('stSchool', appData.stSchool);
	$.device.officeSetStringValue('stSchoolAddr', appData.stSchoolAddr);
	$.device.officeSetStringValue('dtStart', appData.dtStart);
	$.device.officeSetStringValue('dtEnd', appData.dtEnd);
	$.device.officeSetStringValue('stJob', appData.stJob);
	$.device.officeSetStringValue('stCompany', appData.stCompany);
	$.device.officeSetStringValue('stCompanyAddr', appData.stCompanyAddr);
	$.device.officeReadOnly(true);
}
//\u2611 \u2600
function formJzqzsq(appData){
	$.device.officeSetStringValue('stName', appData.stName);
	$.device.officeSetStringValue('stIdCard', appData.stIdCard);
	$.device.officeSetStringValue('stPhone', appData.stPhone);
	$.device.officeSetStringValue('stTel', appData.stTel);
	$.device.officeSetStringValue('stSex', appData.stSex);
	$.device.officeSetStringValue('nations', appData.nations);
	$.device.officeSetStringValue('shCounty', appData.shCounty);
	$.device.officeSetStringValue('shStreet', appData.shStreet);
	$.device.officeSetStringValue('stShRoad', appData.stShRoad);
	$.device.officeSetStringValue('stShLane', appData.stShLane);
	$.device.officeSetStringValue('stShNo', appData.stShNo);
	$.device.officeSetStringValue('stShRoom', appData.stShRoom);
	choiceJzqzsq(appData.liveType);
	$.device.officeReadOnly(true);
}
//居住证签注申请表的选择条件
function choiceJzqzsq(str){
	if(str =="本人房地产权证明"){
		$.device.officeSetStringValue('a', "\u2611");
	}else{
		$.device.officeSetStringValue('a', "\u2610");
	}
	if(str =="租赁合同登记备案"){
		$.device.officeSetStringValue('b', "\u2611");
	}else{
		$.device.officeSetStringValue('b', "\u2610");
	}
	if(str =="近亲属的相关居住证明"){
		$.device.officeSetStringValue('c', "\u2611");
	}else{
		$.device.officeSetStringValue('c', "\u2610");
	}
	if(str =="集体宿舍证明"){
		$.device.officeSetStringValue('d', "\u2611");
	}else{
		$.device.officeSetStringValue('d', "\u2610");
	}
}

//居住登记信息表选择条件
function choiceJzdj(str,str2,str3){
	if(str =="本人房地产权证明"){
		$.device.officeSetStringValue('Ta', "\u2611");
	}else{
		$.device.officeSetStringValue('Ta', "\u2610");
	}
	if(str =="租赁合同登记备案"){
		$.device.officeSetStringValue('Tb', "\u2611");
	}else{
		$.device.officeSetStringValue('Tb', "\u2610");
	}
	if(str =="近亲属的相关居住证明"){
		$.device.officeSetStringValue('Tc', "\u2611");
	}else{
		$.device.officeSetStringValue('Tc', "\u2610");
	}
	if(str =="集体宿舍证明"){
		$.device.officeSetStringValue('Td', "\u2611");
	}else{
		$.device.officeSetStringValue('Td', "\u2610");
	}
	if(str2 =="务工"){
		$.device.officeSetStringValue('R1', "\u2611");
	}else{
		$.device.officeSetStringValue('R1', "\u2610");
	}
	if(str2 =="经商"){
		$.device.officeSetStringValue('R2', "\u2611");
	}else{
		$.device.officeSetStringValue('R2', "\u2610");
	}
	if(str2 =="务农"){
		$.device.officeSetStringValue('R3', "\u2611");
	}else{
		$.device.officeSetStringValue('R3', "\u2610");
	}
	if(str2 =="服务"){
		$.device.officeSetStringValue('R4', "\u2611");
	}else{
		$.device.officeSetStringValue('R4', "\u2610");
	}
	if(str2 =="因公出差"){
		$.device.officeSetStringValue('R5', "\u2611");
	}else{
		$.device.officeSetStringValue('R5', "\u2610");
	}
	if(str2 =="借读培训"){
		$.device.officeSetStringValue('R6', "\u2611");
	}else{
		$.device.officeSetStringValue('R6', "\u2610");
	}
	if(str2 =="治病疗养"){
		$.device.officeSetStringValue('R7', "\u2611");
	}else{
		$.device.officeSetStringValue('R7', "\u2610");
	}
	if(str2 =="随迁亲属"){
		$.device.officeSetStringValue('R8', "\u2611");
	}else{
		$.device.officeSetStringValue('R8', "\u2610");
	}
	if(str2 =="拆迁搬家"){
		$.device.officeSetStringValue('R9', "\u2611");
	}else{
		$.device.officeSetStringValue('R9', "\u2610");
	}
	if(str2 =="寄挂户口"){
		$.device.officeSetStringValue('R10', "\u2611");
	}else{
		$.device.officeSetStringValue('R10', "\u2610");
	}
	if(str2 =="婚姻嫁娶"){
		$.device.officeSetStringValue('R11', "\u2611");
	}else{
		$.device.officeSetStringValue('R11', "\u2610");
	}
	if(str2 =="投靠亲友"){
		$.device.officeSetStringValue('R12', "\u2611");
	}else{
		$.device.officeSetStringValue('R12', "\u2610");
	}
	if(str2 =="保姆"){
		$.device.officeSetStringValue('R13', "\u2611");
	}else{
		$.device.officeSetStringValue('R13', "\u2610");
	}
	if(str2 =="探亲访友"){
		$.device.officeSetStringValue('R14', "\u2611");
	}else{
		$.device.officeSetStringValue('R14', "\u2610");
	}
	if(str2 =="旅游观光"){
		$.device.officeSetStringValue('R15', "\u2611");
	}else{
		$.device.officeSetStringValue('R15', "\u2610");
	}
	if(str2 =="人才引进"){
		$.device.officeSetStringValue('R16', "\u2611");
	}else{
		$.device.officeSetStringValue('R16', "\u2610");
	}
	if(str2 =="其他"){
		$.device.officeSetStringValue('R17', "\u2611");
	}else{
		$.device.officeSetStringValue('R17', "\u2610");
	}
	if(str3 =="未上过学"){
		$.device.officeSetStringValue('E1', "\u2611");
	}else{
		$.device.officeSetStringValue('E1', "\u2610");
	}
	if(str3 =="小学"){
		$.device.officeSetStringValue('E2', "\u2611");
	}else{
		$.device.officeSetStringValue('E2', "\u2610");
	}
	if(str3 =="初中"){
		$.device.officeSetStringValue('E3', "\u2611");
	}else{
		$.device.officeSetStringValue('E3', "\u2610");
	}
	if(str3 =="高中"){
		$.device.officeSetStringValue('E4', "\u2611");
	}else{
		$.device.officeSetStringValue('E4', "\u2610");
	}
	if(str3 =="大学专科"){
		$.device.officeSetStringValue('E5', "\u2611");
	}else{
		$.device.officeSetStringValue('E5', "\u2610");
	}
	if(str3 =="大学本科"){
		$.device.officeSetStringValue('E6', "\u2611");
	}else{
		$.device.officeSetStringValue('E6', "\u2610");
	}
	if(str3 =="研究生"){
		$.device.officeSetStringValue('E7', "\u2611");
	}else{
		$.device.officeSetStringValue('E7', "\u2610");
	}
}
//上海市居住证申请表的选择条件
function choiceJzsq(str,str2,str3,str4,str5,str6,str7,str8,str9){
	choiceJzdj(str,str2,str3);
	if(str4 =="已采集"){
		$.device.officeSetStringValue('P1', "\u2611");
	}else{
		$.device.officeSetStringValue('P1', "\u2610");
	}
	if(str4 =="未采集"){
		$.device.officeSetStringValue('P2', "\u2611");
	}else{
		$.device.officeSetStringValue('P2', "\u2610");
	}
	if(str5 =="是"){
		$.device.officeSetStringValue('S1', "\u2611");
	}else{
		$.device.officeSetStringValue('S1', "\u2610");
	}
	if(str5 =="否"){
		$.device.officeSetStringValue('S2', "\u2611");
	}else{
		$.device.officeSetStringValue('S2', "\u2610");
	}
	if(str6 =="就业"){
		$.device.officeSetStringValue('W1', "\u2611");
	}else{
		$.device.officeSetStringValue('W1', "\u2610");
	}
	if(str6 =="未就业"){
		$.device.officeSetStringValue('W2', "\u2611");
	}else{
		$.device.officeSetStringValue('W2', "\u2610");
	}
	if(str7 =="党政机关"){
		$.device.officeSetStringValue('J1', "\u2611");
	}else{
		$.device.officeSetStringValue('J1', "\u2610");
	}
	if(str7 =="公用事业"){
		$.device.officeSetStringValue('J2', "\u2611");
	}else{
		$.device.officeSetStringValue('J2', "\u2610");
	}
	if(str7 =="国有企业"){
		$.device.officeSetStringValue('J3', "\u2611");
	}else{
		$.device.officeSetStringValue('J3', "\u2610");
	}
	if(str7 =="外资企业"){
		$.device.officeSetStringValue('J4', "\u2611");
	}else{
		$.device.officeSetStringValue('J4', "\u2610");
	}
	if(str7 =="私营企业"){
		$.device.officeSetStringValue('J5', "\u2611");
	}else{
		$.device.officeSetStringValue('J5', "\u2610");
	}
	if(str7 =="个体工商户"){
		$.device.officeSetStringValue('J6', "\u2611");
	}else{
		$.device.officeSetStringValue('J6', "\u2610");
	}
	if(str7 =="其他"){
		$.device.officeSetStringValue('J7', "\u2611");
	}else{
		$.device.officeSetStringValue('J7', "\u2610");
	}
	if(str8 =="在校学生"){
		$.device.officeSetStringValue('U1', "\u2611");
	}else{
		$.device.officeSetStringValue('U1', "\u2610");
	}
	if(str8 =="丧失工作能力"){
		$.device.officeSetStringValue('U2', "\u2611");
	}else{
		$.device.officeSetStringValue('U2', "\u2610");
	}
	if(str8 =="毕业后未找到工作"){
		$.device.officeSetStringValue('U3', "\u2611");
	}else{
		$.device.officeSetStringValue('U3', "\u2610");
	}
	if(str8 =="因单位原因失去工作"){
		$.device.officeSetStringValue('U4', "\u2611");
	}else{
		$.device.officeSetStringValue('U4', "\u2610");
	}
	if(str8 =="因个人原因失去工作"){
		$.device.officeSetStringValue('U5', "\u2611");
	}else{
		$.device.officeSetStringValue('U5', "\u2610");
	}
	if(str8 =="承包土地被征用"){
		$.device.officeSetStringValue('U6', "\u2611");
	}else{
		$.device.officeSetStringValue('U6', "\u2610");
	}
	if(str8 =="离退休"){
		$.device.officeSetStringValue('U7', "\u2611");
	}else{
		$.device.officeSetStringValue('U7', "\u2610");
	}
	if(str8 =="料理家务"){
		$.device.officeSetStringValue('U8', "\u2611");
	}else{
		$.device.officeSetStringValue('U8', "\u2610");
	}
	if(str8 =="其他"){
		$.device.officeSetStringValue('U9', "\u2611");
	}else{
		$.device.officeSetStringValue('U9', "\u2610");
	}
	if(str9 =="劳动收入"){
		$.device.officeSetStringValue('LS1', "\u2611");
	}else{
		$.device.officeSetStringValue('LS1', "\u2610");
	}
	if(str9 =="离退休金养老金"){
		$.device.officeSetStringValue('LS2', "\u2611");
	}else{
		$.device.officeSetStringValue('LS2', "\u2610");
	}
	if(str9 =="失业保险金"){
		$.device.officeSetStringValue('LS3', "\u2611");
	}else{
		$.device.officeSetStringValue('LS3', "\u2610");
	}
	if(str9 =="最低生活保障金"){
		$.device.officeSetStringValue('LS4', "\u2611");
	}else{
		$.device.officeSetStringValue('LS4', "\u2610");
	}
	if(str9 =="财产性收入"){
		$.device.officeSetStringValue('LS5', "\u2611");
	}else{
		$.device.officeSetStringValue('LS5', "\u2610");
	}
	if(str9 =="家庭其他成员供养"){
		$.device.officeSetStringValue('LS6', "\u2611");
	}else{
		$.device.officeSetStringValue('LS6', "\u2610");
	}
	if(str9 =="其他"){
		$.device.officeSetStringValue('LS7', "\u2611");
	}else{
		$.device.officeSetStringValue('LS7', "\u2610");
	}
}
