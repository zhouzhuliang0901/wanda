//10.237.16.72
//218.202.254.222
var urlHost = 'http://10.237.16.72';
var t;
var machineId = $.config.get('uniqueId');

function time() {
	var time = 18000;
	t = setInterval(function() {
		if(time == 0) {
			clearInterval(t);
			$.device.GoHome();
		}
		$(".minute").text(time);
		time--;
	}, 1000)
}

function removeAnimate(ele) {
	$(ele).css({
		"transform": "translateY(0px)",
		"top": 0
	}).removeClass('transformto')
}

function addAnimate(ele) {
	$(ele).addClass('transformto').on("animationend", function() {
		$(ele).removeClass('transformto')
	})
}

app.controller("listController", function($scope, $route, $location, $http, $rootScope, data) {
	removeAnimate($('.linkbox1'))
	data.isUpload = [];
	data.listImg = [];
	data.isUploadForPush = [];
	data.fileName = [];
	data.resetData = "";
	$scope.itemName = [];
	$scope.currentMatters = [];
	$scope.searchType = ["高频事项", "窗口事项", "智能申报"];
	$scope.matterVal = '';
	$scope.idRead = false;
	$scope.isLoading = true;
	$scope.current = 0;
	clearInterval(t);
	$scope.type = "1";
	time();
	addAnimate($('.linkbox1'))

	//阅签选择
	$scope.getMatterCon = function(index, type) {
		$scope.current = index;
		switch(type) {
			case "高频事项":
				$scope.type = "1";
				$scope.itemName = "";
				$scope.itemType = "";
				$scope.idRead = false;
				$scope.getItemOne();
				break;
			case "窗口事项":
				$scope.type = "2";
				$scope.itemName = "";
				$scope.itemType = "";
				$scope.getItemTwo();
				$scope.idRead = true;
				break;
			case "智能申报":
				$scope.type = "3";
				$scope.itemName = "";
				$scope.itemType = "";
				$scope.idRead = false;
				$scope.getItemThree();
				break;
		}
	};
	//获取全部综窗事项
	$scope.getItemTwo = function() {
		$scope.current = "1";
		var tConfig = {
			groupCode: "SS",
			jsonpCallback: "JSON_CALLBACK"
		}
		$http.jsonp(urlHost + '/aci/materialUp/zcitemList.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.notnullIteam = [];
			for(var i = 0; i < dataJson.data.length; i++) {
				if(dataJson.data[i].stItemName != null && dataJson.data[i].stItemName != undefined && dataJson.data[i].stItemName != "") {
					$scope.notnullIteam.push(dataJson.data[i]);
				}

			}
			$scope.itemName = $scope.notnullIteam;
			data.showItemName = dataJson.data;
		}).error(function(err) {
			console.log(err);
		});
	}
	//搜索查询
	$scope.getSearchMatter = function() {
		$scope.current = "1";
		$scope.showItemName = [];
		for(var i = 0; i < data.showItemName.length; i++) {
			if(data.showItemName[i].stItemName != undefined && data.showItemName[i].stItemName != null) {
				if(data.showItemName[i].stItemName.indexOf($scope.matterVal) != -1) {
					$scope.showItemName.push(data.showItemName[i]);
				}
			}
		}
		$scope.itemName = $scope.showItemName;

	};
	//获取指定部门的综窗事项
	$scope.getIteamListByOgan = function(id) {
		$scope.current = "1";
		var tConfig = {
			groupCode: "SS",
			jsonpCallback: "JSON_CALLBACK"
		}
		$http.jsonp(urlHost + '/aci/materialUp/zcitemList.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.messageData = dataJson.data;
			$scope.messageDataShow = [];
			for(var i = 0; i < $scope.messageData.length; i++) {
				if($scope.messageData[i].organNodeId == id) {
					$scope.messageDataShow.push($scope.messageData[i]);
				}
			}
			$scope.itemName = $scope.messageDataShow;
		}).error(function(err) {
			console.log(err);
		});
	}
	//获取全部综窗部门
	$scope.getItemByOrganCode = function() {
		var tConfig = {
			groupCode: "SS",
			jsonpCallback: "JSON_CALLBACK"
		}
		$http.jsonp(urlHost + '/aci/materialUp/zcOrganList.do', {
			params: tConfig
		}).success(function(dataJson) {
			data.arrData = dataJson.data;
			//json去重
			for(var i = 0; i < data.arrData.length; i++) {
				for(var j = i + 1; j < data.arrData.length;) {
					if(data.arrData[i].organName == data.arrData[j].organName) { //判断条件可以按照个人需求改
						data.arrData.splice(j, 1);
					} else j++;
				}
			}
			$scope.itemName = data.arrData;
		}).error(function(err) {
			console.log(err);
		});
	}

	$scope.getItemOne = function() {
		$scope.current = "0";
		$scope.itemName = [{
				stItemName: "特种设备安装改造维修施工告知",
				organCode: "SHSCJD",
				itemNo: "5473",
				itemTenNo: "310150473000",
				organName: "嘉定区市场监督管理局",
				stItemId: "0375ca9a-8cb7-4427-bf9b-fbd432b1c5f9",
				clRange: "本指南适用于本市特种设备安装改造维修施工告知的申请与办理。",
				clNameCode: "<p>事项名称：特种设备安装改造修理施工告知</p><p>事项编码：5473</p>",
				clDealAccording: "《特种设备安全监察条例》第十七条规定：特种设备安装、改造、维修的施工单位应当在施工前将拟进行的特种设备安装、改造、维修情况书面告知直辖市或者设区的市的特种设备安全监督管理部门，告知后即可施工。",
				clDealOrgan: "各街镇社区事务受理服务中心",
				clApprovalConds: "申请材料齐全，符合法定形式，具体见申请材料目录。",
				clApprovalCount: "无数量限制",
				clApprovalMater: "<table><tbody><tr><th>材料名称</th><th>材料类型</th><th>纸质/电子报件</th><th>材料份数</th><th>材料必要性</th></tr><tr><td>特种设备安装改造维修许可证复印件（必要）</td><td>复印件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>特种设备安装改造维修施工告知单（加盖施工单位公章）（必要）</td><td>原件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>规划许可证、施工许可证（新建房屋、改变房屋结构、外挂式观光电梯）</td><td>复印件</td><td>纸质</td><td>1</td><td>非必要</td></tr><tr><td>原检测报告（改造或维修）</td><td>原件</td><td>纸质</td><td>1</td><td>非必要</td></tr><tr><td>报废单（加盖登记机关监察专用章）（报废后新装）</td><td>原件</td><td>纸质</td><td>1</td><td>非必要</td></tr><tr><td>房产证、原有井道图纸（盖使用单位章）（不改变房屋结构）</td><td>复印件</td><td>纸质</td><td>1</td><td>非必要</td></tr></table></tbody>",
				clApprovalLimit: "",
				clApprovalCert: "",
				clChargeStd: "<p>否</p>",
				clApplyRightsDuties: "申请人依法享有知情权、陈述权、申辩权，有权依法申请行政复议或者提起行政诉讼；其合法权益因行政机关违法实施行政许可受到损害的，有权依法要求赔偿。1.应当如实向行政机关提交有关材料和反映真实情况，并对其申请材料实质内容的真实性负责。\n2.依法接受、配合监督检查的义务。",
				clApplyReceive: "",
				clConsultWay: "（021）69989023",
				clComplaintChannel: "http://www.shzj.gov.cn",
				clDealType: "1.业务描述<br>（1）办理环节<br>申请人通过网上或现场提交申请材料，对提供申请材料齐全、符合规定形式的，办理人员当场进行资料审核，并将备案结果告知办理人。办证大厅根据决定结果告知申请人予以告知备案。<br>（2）审查方式：采用现场书面确认的审查方式。<br>2.适用情形<br>适用于特种设备安装改造维修施工告知的申请办理。",
				clDecidedOpen: ""
			},
			{
				stItemName: "特种设备使用登记",
				organCode: "SHSCJD",
				itemNo: "5679",
				itemTenNo: "310150679000",
				organName: "嘉定区市场监督管理局",
				stItemId: "f646a184-01ff-48ce-abdc-f770a2e730e0",
				clRange: "本指南适用于特种设备补证的申请与办理。",
				clNameCode: "特种设备使用登记&nbsp&nbsp5679",
				clDealAccording: "《中华人民共和国特种设备安全法》第三十三条：“特种设备使用单位应当在特种设备投入使用前或者投入使用后三十日内，向负责特种设备安全监督管理的部门办理使用登记，取得使用登记证书。登记标志应当置于该特种设备的显著位置。",
				clDealOrgan: "各街镇社区事务受理服务中心",
				clApprovalConds: "申请材料齐全，符合法定形式，具体见申请材料目录。",
				clApprovalCount: "无数量限制，符合条件即予审批。",
				clApprovalMater: "<table><tbody><tr><th>材料名称</th><th>材料类型</th><th>纸质/电子报件</th><th>材料份数</th><th>材料必要性</th></tr><tr><td>特种设备变更申请表（加盖公章）</td><td>复印件</td><td>纸质</td><td>3</td><td>必要</td></tr><tr><td>营业执照或机构身份证明复印件（公章）</td><td>复印件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>气瓶基本信息汇总表</td><td>原件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>监督检验报告（加盖使用单位公章）</td><td>复印件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>产品质量合格证明（公章）</td><td>复印件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>锅炉能效证明文件</td><td>复印件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>特种设备使用登记表（加盖公章）</td><td>复印件</td><td>纸质</td><td>2</td><td>必要</td></tr><tr><td>压力管道基本信息汇总表（含电子版）</td><td>原件</td><td>纸质</td><td>2</td><td>必要</td></tr><tr><td>移动式压力容器车辆走行部分行驶证</td><td>复印件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>产品质量合格证明+制造监检证书+监督检验报告或者开工回执（加盖使用单位公章）</td><td>复印件</td><td>纸质</td><td>1</td><td>必要</td></tr></table></tbody>",
				clApprovalLimit: "",
				clApprovalCert: "",
				clChargeStd: "<p>否</p>",
				clApplyRightsDuties: "1.申请人依法享有知情权、陈述权、申辩权；<br/>2.有权依法申请行政复议或者提起行政诉讼；<br/>3.其合法权益因行政机关违法实施行政审批受到损害的，有权依法要求赔偿。1.应当如实向行政机关提交有关材料和反映真实情况，并对其申请材料实质内容的真实性负责。<br/>2.依法接受、配合监督检查的义务。",
				clApplyReceive: "",
				clConsultWay: "（021）59999078",
				clComplaintChannel: "www.shzj.gov.cn",
				clDealType: "1.业务描述<br/>（1）办理环节（2）审查方式：采用现场书面确认的审查方式。<br/>2.适用情形<br/>适用于特种设备新办、依申请变更（停用、启用、移装、过户、报废、注销）、补证的申请与办理。",
				clDecidedOpen: ""
			},
			{
				stItemName: "网络预约出租汽车驾驶员人员背景审查",
				organCode: "SHSCJD",
				itemNo: "9126",
				itemTenNo: "310100891004",
				organName: "嘉定区市场监督管理局",
				stItemId: "c8b8e1f5-55bc-415a-b0e9-b39f27f473a3",
				clRange: "本指南适用于本市特种设备安装改造维修施工告知的申请与办理。",
				clNameCode: "<p>事项名称：网络预约出租汽车驾驶员人员背景审查</p><p>事项编码：9126</p>",
				clDealAccording: "1.《国务院对确需保留的行政审批项目设定行政许可的决定》<br>2.《出租汽车驾驶员从业资格管理规定》第十条、第十一条、第十四条、第十六条、第二十条、第二十二条<br>第十条申请参加出租汽车驾驶员从业资格考试的，应当符合下列条件：<br>（一）取得相应准驾车型机动车驾驶证并具有3年以上驾驶经历；<br>（二）无交通肇事犯罪、危险驾驶犯罪记录，无吸毒记录，无饮酒后驾驶记录，最近连续3个记分周期内没有记满12分记录；<br>（三）无暴力犯罪记录；<br>（四）城市人民政府规定的其他条件。<br>第十一条申请参加出租汽车驾驶员从业资格考试的，应当提供符合第十条规定的证明或者承诺材料：<br>（一）机动车驾驶证及复印件；<br>（二）无交通肇事犯罪、危险驾驶犯罪记录，无吸毒记录，无饮酒后驾驶记录，最近连续3个记分周期内没有记满12分记录的材料；<br>（三）无暴力犯罪记录的材料；<br>（四）身份证明及复印件；<br>（五）城市人民政府规定的其他材料。<br>第十四条出租汽车驾驶员从业资格考试全国公共科目和区域科目考试均合格的，设区的市级出租汽车行政主管部门应当自公布考试成绩之日起10日内向巡游出租汽车驾驶员核发《巡游出租汽车驾驶员证》、向网络预约出租汽车驾驶员核发《网络预约出租汽车驾驶员证》（《巡游出租汽车驾驶员证》和《网络预约出租汽车驾驶员证》以下统称从业资格证）。<br>从业资格证式样参照《中华人民共和国道路运输从业人员从业资格证》式样。<br>鼓励推广使用从业资格电子证件。采用电子证件的，应当包含证件式样所确定的相关信息。<br>第十六条取得从业资格证的出租汽车驾驶员，应当经出租汽车行政主管部门从业资格注册后，方可从事出租汽车客运服务。<br>出租汽车驾驶员从业资格注册有效期为3年。<br>第二十条 巡游出租汽车驾驶员注册有效期届满需继续从事出租汽车客运服务的，应当在有效期届满30日前，向所在地出租汽车行政主管部门申请延续注册。<br>第二十二条巡游出租汽车驾驶员在从业资格注册有效期内，与出租汽车经营者解除劳动合同或者经营合同的，应当在20日内向原注册机构报告，并申请注销注册。<br>巡游出租汽车驾驶员变更服务单位的，应当重新申请注册。<br>3.《网络预约出租汽车经营服务管理暂行办法》第十四条、第十五条<br>第十四条 从事网约车服务的驾驶员，应当符合以下条件：<br>(一)取得相应准驾车型机动车驾驶证并具有3年以上驾驶经历；<br>(二)无交通肇事犯罪、危险驾驶犯罪记录，无吸毒记录，无饮酒后驾驶记录，最近连续3个记分周期内没有记满12分记录；<br>(三)无暴力犯罪记录；<br>(四)城市人民政府规定的其他条件。<br>第十五条 服务所在地设区的市级出租汽车行政主管部门依驾驶员或者网约车平台公司申请，按第十四条规定的条件核查并按规定考核后，为符合条件且考核合格的驾驶员，发放《网络预约出租汽车驾驶员证》。<br>4.《上海市出租汽车管理条例》第十一条、第十四条<br>第十一条 出租汽车驾驶员必须具备下列条件：<br>（一）有本市常住户籍；<br>（二）有初中以上文化程度；<br>（三）有本市公安部门核发的机动车驾驶证；<br>（四）经出租汽车职业培训合格；<br>（五）遵守法律、法规。<br>被取消客运服务资格的驾驶员，自取消资格之日起五年内不得担任出租汽车驾驶员。<br>第十四条 经核准从事客运服务或者车辆租赁服务的企业和个人，应当持市交通行政管理部门或者区、县交通行政管理部门核发的许可凭证，分别向有关部门办理专用车辆牌照和保险等手续。<br>对按照前款规定办妥手续的，由市交通行政管理部门或者区、县交通行政管理部门发给经营资质证书，由市运输管理处或者区、县运输管理机构发给车辆营运资格证件，由市运输管理处发给驾驶员营运资格证件。<br>5.《上海市网络预约出租汽车经营服务管理若干规定》第九条、第十条<br>第九条（网约车驾驶员条件） 在本市从事网约车经营服务的驾驶员，除符合《办法》规定的条件外，还应当符合下列条件：<br>（一）本市户籍；<br>（二）自申请之日前1年内，无驾驶机动车发生5次以上道路交通安全违法行为；<br>（三）自申请之日前5年内，无被吊销出租汽车从业资格证的记录；<br>（四）截至申请之日，无5起以上道路交通违法行为逾期尚未接受处理的情形。<br>第十条（许可有效期）市交通行政管理部门作出行政许可决定的，向网约车平台公司发放有效期为3年的《网络预约出租汽车经营许可证》；向车辆发放有效期为3年的《网络预约出租汽车运输证》；向驾驶员发放注册有效期为3年的《网络预约出租汽车驾驶员证》。<br>",
				clDealOrgan: "各街镇社区事务受理服务中心",
				clApprovalConds: "申请材料齐全，符合法定形式，具体见申请材料目录。",
				clApprovalCount: "无数量限制",
				clApprovalMater: "<table><tbody><tr><th>材料名称</th><th>材料类型</th><th>纸质/电子报件</th><th>材料份数</th><th>材料必要性</th></tr><tr><td>上海市网络预约出租汽车驾驶员人员背景审查申请表</td><td>复印件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>申请人身份证</td><td>原件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>申请人驾驶证</td><td>原件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>申请人户口簿（本地）</td><td>原件</td><td>纸质</td><td>1</td><td>必要</td></tr></table></tbody>",
				clApprovalLimit: "",
				clApprovalCert: "",
				clChargeStd: "<p>否</p>",
				clApplyRightsDuties: "申请人依法享有知情权、陈述权、申辩权，有权依法申请行政复议或者提起行政诉讼；其合法权益因行政机关违法实施行政许可受到损害的，有权依法要求赔偿。1.应当如实向行政机关提交有关材料和反映真实情况，并对其申请材料实质内容的真实性负责。\n2.依法接受、配合监督检查的义务。",
				clApplyReceive: "",
				clConsultWay: "（021）12319",
				clComplaintChannel: "http://www.shygc.net",
				clDealType: "1.审查环节：申请人向市交通委交通考试中心提交规定材料后，市交通委交通考试中心负责受理，对提供材料齐全、符合规定形式的，交由市运输管理处审批；市运输管理处经书面审查后作出审批决定，送市交通委交通考试中心；由市交通委交通考试中心向申请人送达决定书或者审批证件。<br>2.审查方式：书面审查。",
				clDecidedOpen: ""
			},
			{
				stItemName: "提前服务（文广局）",
				organCode: "SHSCJD",
				itemNo: "1224",
				itemTenNo: "",
				organName: "嘉定区市场监督管理局",
				stItemId: "08a7133b-45e0-496f-9208-79c392b29f64",
				clRange: "本指南适用于本市提前服务（文广局）办理。",
				clNameCode: "<p>事项名称：提前服务（文广局）</p><p>事项编码：1224</p>",
				clDealAccording: "",
				clDealOrgan: "各街镇社区事务受理服务中心",
				clApprovalConds: "申请材料齐全，符合法定形式，具体见申请材料目录。",
				clApprovalCount: "无数量限制",
				//clApprovalMater:"<table><tbody><tr><th>材料名称</th><th>材料类型</th><th>纸质/电子报件</th><th>材料份数</th><th>材料必要性</th></tr><tr><td>上海市网络预约出租汽车驾驶员人员背景审查申请表</td><td>复印件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>申请人身份证</td><td>原件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>申请人驾驶证</td><td>原件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>申请人户口簿（本地）</td><td>原件</td><td>纸质</td><td>1</td><td>必要</td></tr></table></tbody>",
				clApprovalMater: "",
				clApprovalLimit: "",
				clApprovalCert: "",
				clChargeStd: "<p>否</p>",
				clApplyRightsDuties: "申请人依法享有知情权、陈述权、申辩权，有权依法申请行政复议或者提起行政诉讼；其合法权益因行政机关违法实施行政许可受到损害的，有权依法要求赔偿。1.应当如实向行政机关提交有关材料和反映真实情况，并对其申请材料实质内容的真实性负责。\n2.依法接受、配合监督检查的义务。",
				clApplyReceive: "",
				clConsultWay: "（021）12319",
				clComplaintChannel: "http://www.shygc.net",
				//clDealType:"1.审查环节：申请人向市交通委交通考试中心提交规定材料后，市交通委交通考试中心负责受理，对提供材料齐全、符合规定形式的，交由市运输管理处审批；市运输管理处经书面审查后作出审批决定，送市交通委交通考试中心；由市交通委交通考试中心向申请人送达决定书或者审批证件。<br>2.审查方式：书面审查。",
				clDealType: "",
				clDecidedOpen: ""
			},
			{
				stItemName: "第一类医疗器械备案",
				organCode: "SHSCJD",
				itemNo: "5713",
				itemTenNo: "",
				organName: "嘉定区市场监督管理局",
				stItemId: "c024f7f0-27b0-429d-afe9-0766c890db74",
				clRange: "本指南适用于本市第一类医疗器械备案办理。",
				clNameCode: "<p>事项名称：第一类医疗器械备案</p><p>事项编码：5713</p>",
				clDealAccording: "",
				clDealOrgan: "各街镇社区事务受理服务中心",
				clApprovalConds: "申请材料齐全，符合法定形式，具体见申请材料目录。",
				clApprovalCount: "无数量限制",
				//clApprovalMater:"<table><tbody><tr><th>材料名称</th><th>材料类型</th><th>纸质/电子报件</th><th>材料份数</th><th>材料必要性</th></tr><tr><td>上海市网络预约出租汽车驾驶员人员背景审查申请表</td><td>复印件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>申请人身份证</td><td>原件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>申请人驾驶证</td><td>原件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>申请人户口簿（本地）</td><td>原件</td><td>纸质</td><td>1</td><td>必要</td></tr></table></tbody>",
				clApprovalMater: "",
				clApprovalLimit: "",
				clApprovalCert: "",
				clChargeStd: "<p>否</p>",
				clApplyRightsDuties: "申请人依法享有知情权、陈述权、申辩权，有权依法申请行政复议或者提起行政诉讼；其合法权益因行政机关违法实施行政许可受到损害的，有权依法要求赔偿。1.应当如实向行政机关提交有关材料和反映真实情况，并对其申请材料实质内容的真实性负责。\n2.依法接受、配合监督检查的义务。",
				clApplyReceive: "",
				clConsultWay: "（021）12319",
				clComplaintChannel: "http://www.shygc.net",
				//clDealType:"1.审查环节：申请人向市交通委交通考试中心提交规定材料后，市交通委交通考试中心负责受理，对提供材料齐全、符合规定形式的，交由市运输管理处审批；市运输管理处经书面审查后作出审批决定，送市交通委交通考试中心；由市交通委交通考试中心向申请人送达决定书或者审批证件。<br>2.审查方式：书面审查。",
				clDealType: "",
				clDecidedOpen: ""
			},
			{
				stItemName: "河道规划蓝线的审核",
				organCode: "SHSCJD",
				itemNo: "9098",
				itemTenNo: "",
				organName: "嘉定区市场监督管理局",
				stItemId: "2513170e-ea42-496a-b84e-595640eeb071",
				clRange: "本指南适用于本市河道规划蓝线的审核办理。",
				clNameCode: "<p>事项名称：河道规划蓝线的审核</p><p>事项编码：9098</p>",
				clDealAccording: "",
				clDealOrgan: "各街镇社区事务受理服务中心",
				clApprovalConds: "申请材料齐全，符合法定形式，具体见申请材料目录。",
				clApprovalCount: "无数量限制",
				//clApprovalMater:"<table><tbody><tr><th>材料名称</th><th>材料类型</th><th>纸质/电子报件</th><th>材料份数</th><th>材料必要性</th></tr><tr><td>上海市网络预约出租汽车驾驶员人员背景审查申请表</td><td>复印件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>申请人身份证</td><td>原件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>申请人驾驶证</td><td>原件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>申请人户口簿（本地）</td><td>原件</td><td>纸质</td><td>1</td><td>必要</td></tr></table></tbody>",
				clApprovalMater: "",
				clApprovalLimit: "",
				clApprovalCert: "",
				clChargeStd: "<p>否</p>",
				clApplyRightsDuties: "申请人依法享有知情权、陈述权、申辩权，有权依法申请行政复议或者提起行政诉讼；其合法权益因行政机关违法实施行政许可受到损害的，有权依法要求赔偿。1.应当如实向行政机关提交有关材料和反映真实情况，并对其申请材料实质内容的真实性负责。\n2.依法接受、配合监督检查的义务。",
				clApplyReceive: "",
				clConsultWay: "（021）12319",
				clComplaintChannel: "http://www.shygc.net",
				//clDealType:"1.审查环节：申请人向市交通委交通考试中心提交规定材料后，市交通委交通考试中心负责受理，对提供材料齐全、符合规定形式的，交由市运输管理处审批；市运输管理处经书面审查后作出审批决定，送市交通委交通考试中心；由市交通委交通考试中心向申请人送达决定书或者审批证件。<br>2.审查方式：书面审查。",
				clDealType: "",
				clDecidedOpen: ""
			},
			{
				stItemName: "技术合同认定登记",
				organCode: "SHSCJD",
				itemNo: "5054",
				itemTenNo: "",
				organName: "嘉定区市场监督管理局",
				stItemId: "01ae684c-3d8d-41fc-9e4c-062ab367710c",
				clRange: "本指南适用于本市技术合同认定登记的审核办理。",
				clNameCode: "<p>事项名称：技术合同认定登记</p><p>事项编码：5054</p>",
				clDealAccording: "",
				clDealOrgan: "各街镇社区事务受理服务中心",
				clApprovalConds: "申请材料齐全，符合法定形式，具体见申请材料目录。",
				clApprovalCount: "无数量限制",
				//clApprovalMater:"<table><tbody><tr><th>材料名称</th><th>材料类型</th><th>纸质/电子报件</th><th>材料份数</th><th>材料必要性</th></tr><tr><td>上海市网络预约出租汽车驾驶员人员背景审查申请表</td><td>复印件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>申请人身份证</td><td>原件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>申请人驾驶证</td><td>原件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>申请人户口簿（本地）</td><td>原件</td><td>纸质</td><td>1</td><td>必要</td></tr></table></tbody>",
				clApprovalMater: "",
				clApprovalLimit: "",
				clApprovalCert: "",
				clChargeStd: "<p>否</p>",
				clApplyRightsDuties: "申请人依法享有知情权、陈述权、申辩权，有权依法申请行政复议或者提起行政诉讼；其合法权益因行政机关违法实施行政许可受到损害的，有权依法要求赔偿。1.应当如实向行政机关提交有关材料和反映真实情况，并对其申请材料实质内容的真实性负责。\n2.依法接受、配合监督检查的义务。",
				clApplyReceive: "",
				clConsultWay: "（021）12319",
				clComplaintChannel: "http://www.shygc.net",
				//clDealType:"1.审查环节：申请人向市交通委交通考试中心提交规定材料后，市交通委交通考试中心负责受理，对提供材料齐全、符合规定形式的，交由市运输管理处审批；市运输管理处经书面审查后作出审批决定，送市交通委交通考试中心；由市交通委交通考试中心向申请人送达决定书或者审批证件。<br>2.审查方式：书面审查。",
				clDealType: "",
				clDecidedOpen: ""
			},
			{
				stItemName: "对植物调运检疫的许可",
				organCode: "SHSCJD",
				itemNo: "5054",
				itemTenNo: "",
				organName: "嘉定区市场监督管理局",
				stItemId: "2ae11d62-e48a-4d55-a4fe-cb684d7618e8",
				clRange: "本指南适用于本市技术合同认定登记的审核办理。",
				clNameCode: "<p>事项名称：技术合同认定登记</p><p>事项编码：5054</p>",
				clDealAccording: "",
				clDealOrgan: "各街镇社区事务受理服务中心",
				clApprovalConds: "申请材料齐全，符合法定形式，具体见申请材料目录。",
				clApprovalCount: "无数量限制",
				//clApprovalMater:"<table><tbody><tr><th>材料名称</th><th>材料类型</th><th>纸质/电子报件</th><th>材料份数</th><th>材料必要性</th></tr><tr><td>上海市网络预约出租汽车驾驶员人员背景审查申请表</td><td>复印件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>申请人身份证</td><td>原件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>申请人驾驶证</td><td>原件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>申请人户口簿（本地）</td><td>原件</td><td>纸质</td><td>1</td><td>必要</td></tr></table></tbody>",
				clApprovalMater: "",
				clApprovalLimit: "",
				clApprovalCert: "",
				clChargeStd: "<p>否</p>",
				clApplyRightsDuties: "申请人依法享有知情权、陈述权、申辩权，有权依法申请行政复议或者提起行政诉讼；其合法权益因行政机关违法实施行政许可受到损害的，有权依法要求赔偿。1.应当如实向行政机关提交有关材料和反映真实情况，并对其申请材料实质内容的真实性负责。\n2.依法接受、配合监督检查的义务。",
				clApplyReceive: "",
				clConsultWay: "（021）12319",
				clComplaintChannel: "http://www.shygc.net",
				//clDealType:"1.审查环节：申请人向市交通委交通考试中心提交规定材料后，市交通委交通考试中心负责受理，对提供材料齐全、符合规定形式的，交由市运输管理处审批；市运输管理处经书面审查后作出审批决定，送市交通委交通考试中心；由市交通委交通考试中心向申请人送达决定书或者审批证件。<br>2.审查方式：书面审查。",
				clDealType: "",
				clDecidedOpen: ""
			},
		]
	}
	$scope.getItemOne();

	$scope.getItemThree = function() {
		$scope.current = "2";
		$scope.itemName = [{
			stItemName: "网络预约出租汽车驾驶员人员背景审查",
			organCode: "SHJTJD",
			itemNo: "3120W0358000",
			itemTenNo: "3120W0358000",
			organName: "嘉定区交通委员会",
			stItemId: "c8b8e1f5-55bc-415a-b0e9-b39f27f473a3",
			clRange: "拥有嘉定区户籍的个人申请并携带本人身份证、驾驶证、户口簿原件。",
			clNameCode: "<p>事项名称：网络预约出租汽车驾驶员人员背景审查</p><p>事项编码：3120W0358000</p>",
			clDealAccording: "1.《国务院对确需保留的行政审批项目设定行政许可的决定》<br>2.《出租汽车驾驶员从业资格管理规定》第十条、第十一条、第十四条、第十六条、第二十条、第二十二条<br>第十条申请参加出租汽车驾驶员从业资格考试的，应当符合下列条件：<br>（一）取得相应准驾车型机动车驾驶证并具有3年以上驾驶经历；<br>（二）无交通肇事犯罪、危险驾驶犯罪记录，无吸毒记录，无饮酒后驾驶记录，最近连续3个记分周期内没有记满12分记录；<br>（三）无暴力犯罪记录；<br>（四）城市人民政府规定的其他条件。<br>第十一条申请参加出租汽车驾驶员从业资格考试的，应当提供符合第十条规定的证明或者承诺材料：<br>（一）机动车驾驶证及复印件；<br>（二）无交通肇事犯罪、危险驾驶犯罪记录，无吸毒记录，无饮酒后驾驶记录，最近连续3个记分周期内没有记满12分记录的材料；<br>（三）无暴力犯罪记录的材料；<br>（四）身份证明及复印件；<br>（五）城市人民政府规定的其他材料。<br>第十四条出租汽车驾驶员从业资格考试全国公共科目和区域科目考试均合格的，设区的市级出租汽车行政主管部门应当自公布考试成绩之日起10日内向巡游出租汽车驾驶员核发《巡游出租汽车驾驶员证》、向网络预约出租汽车驾驶员核发《网络预约出租汽车驾驶员证》（《巡游出租汽车驾驶员证》和《网络预约出租汽车驾驶员证》以下统称从业资格证）。<br>从业资格证式样参照《中华人民共和国道路运输从业人员从业资格证》式样。<br>鼓励推广使用从业资格电子证件。采用电子证件的，应当包含证件式样所确定的相关信息。<br>第十六条取得从业资格证的出租汽车驾驶员，应当经出租汽车行政主管部门从业资格注册后，方可从事出租汽车客运服务。<br>出租汽车驾驶员从业资格注册有效期为3年。<br>第二十条 巡游出租汽车驾驶员注册有效期届满需继续从事出租汽车客运服务的，应当在有效期届满30日前，向所在地出租汽车行政主管部门申请延续注册。<br>第二十二条巡游出租汽车驾驶员在从业资格注册有效期内，与出租汽车经营者解除劳动合同或者经营合同的，应当在20日内向原注册机构报告，并申请注销注册。<br>巡游出租汽车驾驶员变更服务单位的，应当重新申请注册。<br>3.《网络预约出租汽车经营服务管理暂行办法》第十四条、第十五条<br>第十四条 从事网约车服务的驾驶员，应当符合以下条件：<br>(一)取得相应准驾车型机动车驾驶证并具有3年以上驾驶经历；<br>(二)无交通肇事犯罪、危险驾驶犯罪记录，无吸毒记录，无饮酒后驾驶记录，最近连续3个记分周期内没有记满12分记录；<br>(三)无暴力犯罪记录；<br>(四)城市人民政府规定的其他条件。<br>第十五条 服务所在地设区的市级出租汽车行政主管部门依驾驶员或者网约车平台公司申请，按第十四条规定的条件核查并按规定考核后，为符合条件且考核合格的驾驶员，发放《网络预约出租汽车驾驶员证》。<br>4.《上海市出租汽车管理条例》第十一条、第十四条<br>第十一条 出租汽车驾驶员必须具备下列条件：<br>（一）有本市常住户籍；<br>（二）有初中以上文化程度；<br>（三）有本市公安部门核发的机动车驾驶证；<br>（四）经出租汽车职业培训合格；<br>（五）遵守法律、法规。<br>被取消客运服务资格的驾驶员，自取消资格之日起五年内不得担任出租汽车驾驶员。<br>第十四条 经核准从事客运服务或者车辆租赁服务的企业和个人，应当持市交通行政管理部门或者区、县交通行政管理部门核发的许可凭证，分别向有关部门办理专用车辆牌照和保险等手续。<br>对按照前款规定办妥手续的，由市交通行政管理部门或者区、县交通行政管理部门发给经营资质证书，由市运输管理处或者区、县运输管理机构发给车辆营运资格证件，由市运输管理处发给驾驶员营运资格证件。<br>5.《上海市网络预约出租汽车经营服务管理若干规定》第九条、第十条<br>第九条（网约车驾驶员条件） 在本市从事网约车经营服务的驾驶员，除符合《办法》规定的条件外，还应当符合下列条件：<br>（一）本市户籍；<br>（二）自申请之日前1年内，无驾驶机动车发生5次以上道路交通安全违法行为；<br>（三）自申请之日前5年内，无被吊销出租汽车从业资格证的记录；<br>（四）截至申请之日，无5起以上道路交通违法行为逾期尚未接受处理的情形。<br>第十条（许可有效期）市交通行政管理部门作出行政许可决定的，向网约车平台公司发放有效期为3年的《网络预约出租汽车经营许可证》；向车辆发放有效期为3年的《网络预约出租汽车运输证》；向驾驶员发放注册有效期为3年的《网络预约出租汽车驾驶员证》。<br>",
			clDealOrgan: "各街镇社区事务受理服务中心",
			clApprovalConds: "申请材料齐全，符合法定形式，具体见申请材料目录。",
			clApprovalCount: "无数量限制",
			clApprovalMater: "<table><tbody><tr><th>材料名称</th><th>材料类型</th><th>纸质/电子报件</th><th>材料份数</th><th>材料必要性</th></tr><tr><td>申请人驾驶证</td><td>复印件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>申请人身份证</td><td>原件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>申请人户口簿（本地）</td><td>原件</td><td>纸质</td><td>1</td><td>必要</td></tr><tr><td>上海市网络预约出租汽车驾驶员人员背景审查申请表</td><td>原件</td><td>纸质</td><td>1</td><td>必要</td></tr></table></tbody>",
			clApprovalLimit: "",
			clApprovalCert: "",
			MaterialList: ['驾驶证原件', '身份证原件', '户口簿原件', '上海市网络预约出租汽车驾驶员人员背景审查申请表'],
			clChargeStd: "<p>否</p>",
			clApplyRightsDuties: "申请人依法享有知情权、陈述权、申辩权，有权依法申请行政复议或者提起行政诉讼；其合法权益因行政机关违法实施行政许可受到损害的，有权依法要求赔偿。1.应当如实向行政机关提交有关材料和反映真实情况，并对其申请材料实质内容的真实性负责。\n2.依法接受、配合监督检查的义务。",
			clApplyReceive: "",
			clConsultWay: "（021）69989138、（021）69033720、（021）12345",
			clComplaintChannel: "http://www.shygc.net",
			clDealType: "申请人自助设备提交材料，工作人员审核材料符合要求后出具《上海市网络预约出租汽车驾驶人员背景审查申请表》和《上海市网络预约出租汽车驾驶人员背景审查申请回执》。",
			clDecidedOpen: ""
		}, ]
	}

	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();

	$scope.toMaterials = function(itemName, organCode, itemNo, clNameCode, clDealAccording, clDealOrgan, clApprovalConds,
		clApprovalCount, clApprovalMater, clApprovalLimit, clApprovalCert, clChargeStd, clApplyRightsDuties,
		clApplyReceive, clConsultWay, MaterialList, clComplaintChannel, clDealType, clDecidedOpen, clRange, itemId, organName, itemTenNo) {
		$scope.isLoading = false;
		data.itemTenNo = itemTenNo;
		data.itemId = itemId;
		data.organName = organName;
		data.clRange = clRange;
		data.itemName = itemName;
		data.organCode = organCode;
		data.itemNo = itemNo;
		data.clNameCode = clNameCode;
		data.clDealAccording = clDealAccording;
		data.clDealOrgan = clDealOrgan;
		data.clApprovalConds = clApprovalConds;
		data.clApprovalCount = clApprovalCount;
		data.clApprovalMater = clApprovalMater;
		data.clApprovalLimit = clApprovalLimit;
		data.clApprovalCert = clApprovalCert;
		data.clChargeStd = clChargeStd;
		data.clApplyRightsDuties = clApplyRightsDuties;
		data.clApplyReceive = clApplyReceive;
		data.clConsultWay = clConsultWay;
		data.clComplaintChannel = clComplaintChannel;
		data.clDealType = clDealType;
		data.clDecidedOpen = clDecidedOpen;
		data.MaterialList = MaterialList || [];
		$location.path("/guideline");
	};
	$rootScope.goHome = function() {
		$scope.isLoading = false;
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
	};
});

app.controller("guidelineController", function($scope, $route, $http, $location, $sce, data, $timeout) {
	removeAnimate($('.main2'))
	var name = data.itemName;
	$scope.itemName = name;
	$scope.description = data.description;
	$scope.statusName = data.statusName;
	$scope.ItemStuffList = "";
	$scope.isShowZNSB = true;
	clearInterval(t);
	time();
	addAnimate($('.main2'))
	$scope.isLoading = true;
	//判断是否是：智能申报-网约车
	if(data.itemNo == '3120W0358000') {
		$scope.isShowZNSB = false;
	}
	if(data.itemTenNo == "" || data.itemTenNo == undefined) {
		$location.path("/matter");
	} else {
		$scope.guideInfo = {
			itemName: data.itemName,
			itemNo: data.itemNo,
			MaterialList: data.MaterialList,
			organName: data.organName,
			clRange: data.clRange,
			clNameCode: data.clNameCode,
			clDealAccording: data.clDealAccording,
			clDealOrgan: data.clDealOrgan,
			clApprovalConds: data.clApprovalConds,
			clApprovalCount: data.clApprovalCount,
			clApprovalMater: data.clApprovalMater,
			clApprovalLimit: data.clApprovalLimit,
			clApprovalCert: data.clApprovalCert,
			clChargeStd: data.clChargeStd,
			clApplyRightsDuties: data.clApplyRightsDuties,
			clApplyReceive: data.clApplyReceive,
			clConsultWay: data.clConsultWay,
			clComplaintChannel: data.clComplaintChannel,
			clDealType: data.clDealType,
			clDecidedOpen: data.clDecidedOpen
		}
		console.log($scope.guideInfo)
		//		var lodop = $.device.printGetLodop();
		//办事指南二维码地址
		if(name == "特种设备安装改造维修施工告知") {
			var itemUrl = "http://zwdt.sh.gov.cn/govPortals/bsfw/findBsfw.do?_itemId=SH00JD310150473001&_itemType=%E5%AE%A1%E6%89%B9&_stSubitemId=504e035e-a1f3-46ce-b6d5-71143f8bf0d7";
		} else if(name == "特种设备使用登记") {
			var itemUrl = "http://zwdt.sh.gov.cn/govPortals/bsfw/findBsfw.do?_itemId=SH00JD310150679001&_itemType=%E5%AE%A1%E6%89%B9&_stSubitemId=589c323d-8ea2-45a7-a423-3349ca3cf7c5";
		} else if(name == "网络预约出租汽车驾驶员人员背景审查") {
			var itemUrl = "http://zwdt.sh.gov.cn/govPortals/bsfw/findBsfw.do?_itemId=SH00SH310100891002&_itemType=%E5%AE%A1%E6%89%B9&_stSubitemId=4df7e909-5471-49cf-adf6-3cfc7f4604bd#work-apply-data";
		}
		$scope.codeUrl = itemUrl;
		console.log($scope.codeUrl);
		var qrcode = new QRCode("code", {
			text: $scope.codeUrl,
			width: 200,
			height: 200,
			correctLevel: 0,
			render: "table"
		});

		$scope.print = function() {
			/*var style = "<style>table tr>td{text-align:center}table tr>td:nth-child(1){text-align:left}"
			 			+"table tr>td:nth-child(1){width:920px;}table tr>td:nth-child(2){display: none;}"
			 			+"table tr>th:nth-child(2){display: none;}table tr>td:nth-child(3){width:200px;margin-left: 20px;}"
			 			+"table tr>td:nth-child(4){width:130px;margin-left: 20px;}table tr>td:nth-child(5){width:150px;margin-left: 20px;}</style>";
			 		var html = style+"<body>"+document.getElementById("lodop").innerHTML+"</body>";
			 		lodop.ADD_PRINT_TEXT(50,0,"100%",100,$scope.itemName+"--材料清单");
			 		lodop.SET_PRINT_STYLEA(0,"Alignment",2);
			 		lodop.SET_PRINT_STYLEA(0,"FontSize",20);
				  	lodop.ADD_PRINT_HTM(150,0,"100%","100%",html);scrollBox2
			*/
			lodop.ADD_PRINT_HTM(0, 0, 1500, "100%", document.getElementById("scrollBox2").innerHTML);
			lodop.PRINT();
		};

		$scope.isScroll = function() {

			new iScroll("wrapper", {
				vScrollbar: false,
				hScrollbar: false,
				hideScrollbar: false,
				bounce: true,
				checkDOMChanges: true
			});
		};
		$scope.isScroll();
		$scope.prevStep = function() {
			$scope.isLoading = false;
			$location.path("/list");
		}
		$scope.nextStep = function() {
			$scope.isLoading = false;
			$location.path("/matter");
		}
	};
});

app.controller("matterController", function($scope, $route, $http, $location, data, $timeout) {
	removeAnimate($('.scrollBox1'))
	$.device.Camera_Hide();
	var name = data.itemName;
	var itemId = data.itemId;
	$scope.itemName = name;
	$scope.statusName = [];
	clearInterval(t);
	time();
	addAnimate($('.scrollBox1'))
	$scope.isLoading = true;
	//根据事项id获取情形
	$scope.getMatterCond = function() {
		var oConfig = {
			itemId: itemId,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/materialUp/queryStatusList.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.itemList = dataJson.data;
		}).error(function() {
			console.log('queryStatusList error')
		})
	};
	$scope.getMatterCond();
	$scope.getSubItem = function(statusName, stStatusId) {
		$scope.isLoading = false;
		data.stStatusId = stStatusId;
		data.statusName = statusName;
		$location.path("/select");
	}
	$scope.prevStep = function() {
		$scope.isLoading = false;
		console.log(data.itemTenNo)
		if(data.itemTenNo == "") {
			$location.path("/list");
		} else {
			$location.path("/guideline");
		}
	}
});
app.controller("selectController", function($scope, $route, $http, $location, data, $timeout) {
	removeAnimate($('.scrollBox2'))
	try {
		window.external.URL_CLOSE();
	} catch(e) {}
	$.device.Camera_Hide();
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	clearInterval(t);
	time();
	addAnimate($('.scrollBox2'))
	$scope.isLoading = true;
	// 刷身份证获取信息
	$scope.scanIdcard = function() {
		$scope.isLoading = false;
		$location.path('/idCard');
	}
	// 市民云亮证
	$scope.citizen = function() {
		$scope.isLoading = false;
		$location.path('/citizen');
	}
	$scope.prevStep = function() {
		$scope.isLoading = false;
		$location.path('/matter');
	}
});

app.controller("idCardController", function($scope, $route, $http, $location, data, $timeout) {
	removeAnimate($('.main2'))
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$scope.isRead = true;
	$scope.isLoading = true;
	clearInterval(t);
	time();
	addAnimate($('.main2'))
	$scope.getIdcard = function(info, images) {
		$scope.faceImage = images;
		$scope.isRead = false; //faceImg
		$scope.$apply();
		data.idCardName = info.Name;
		data.idCardNum = info.Number;
		data.idCardSex = info.Sex;
		data.address = info.Address;
		$.log.debug(info)
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		$location.path("/info");
	}

	//data.idCardName = "lihuaxi";
	//data.idCardNum = "310114198901060020";
	//data.idCardName = "cuijinbang";
	//data.idCardNum = "430426199804106174";
	//data.idCardName = "liujun";
	//data.idCardNum = "31011419830906089X";
	//data.idCardSex = "男";
	//data.address = "上海市闵行区"
	//$location.path("/info");
	//certNo, //91310114MA1GUDX96C

	$scope.prevStep = function() {
		$scope.isLoading = false;
		$location.path("/select");
	}

});

app.controller("citizenController", function($scope, $route, $http, $location, data, $timeout) {
	removeAnimate($('.main2'))
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	clearInterval(t);
	time();
	addAnimate($('.main2'))
	$scope.prevStep = function() {
		$location.path("/select");
	}
	$scope.ClearBr = function(key) {
		key = key.replace(/<\/?.+?>/g, "");
		key = key.replace(/[\r\n]/g, "");
		return key;
	}
	// 扫描二维码
	$.device.qrCodeOpen(function(code) {
		var __code = $scope.ClearBr(code);
		$.ajax({
			url: urlHost + "/aci/window/getQrCodeInfoByElectronicCert.do",
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data: {
				machineId:machineId || '',
				itemName:data.itemName || '',
				itemCode:data.itemId || '',
				businessCode:data.applyId || '',
				using:'',
				codeParam: __code
			},
			success: function(dataJsonp) {
				if(dataJsonp.result.success === false) {
					layer.msg(dataJsonp.result.msg);
					$timeout(function() {
						$location.path('/select');
					}, 100);
				}
				data.idCardName = dataJsonp.result.data.realname;
				data.idCardNum = dataJsonp.result.data.idcard;
				//data.mobile = dataJsonp.result.data.mobile;
				$timeout(function() {
					$location.path('/info');
				}, 100);
			},
			error: function(err) {
				console.log("二维码已过期！")
			}
		});
	}, function(err) {
		console.log("二维码扫描出错：" + err)
	});
});
app.controller("infoController", function($scope, $route, $http, $location, data, $timeout,appFactory) {
	data.isUpload = [];
	removeAnimate($('.scrollBox2'))
	console.log(data)
	if(data.itemId == "c8b8e1f5-55bc-415a-b0e9-b39f27f473a3" && data.itemTenNo == "3120W0358000") {
		//智能办件

		$scope.isShowZNSB = true;
		$http.jsonp(urlHost + '/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do', {
			params: {
				machineId:machineId || '',
				itemName:data.itemName || '',
				itemCode:data.itemId || '',
				businessCode:data.applyId || '',
				identNo: data.idCardNum,
				catMainCode: '310100208000100', //证照目录编码的前半段
				jsonpCallback: "JSON_CALLBACK",
			}
		}).success(function(dataJson) {
			console.log('办件人信息:', dataJson)
			var SADDR = dataJson.sAddr || dataJson.SADDR
			var SCLASS = dataJson.sClass || dataJson.SCLASS
			var SARCHIVESNO = dataJson.sArchivesNo || dataJson.SARCHIVESNO
			var SFIRSTDATE = dataJson.sFirstDate || dataJson.SFIRSTDATE

			if(SADDR.slice(0, 3) != "上海市") {
				layer.msg('该事项仅支持本市户籍人员办理！');
				$timeout(function() {
					$location.path('/list');
				}, 2000);
			}
			$('#address').val(SADDR);
			$('#liveAddress').val(SADDR);
			$('#carType').val(SCLASS);
			$('#driveNo').val(SARCHIVESNO);
			var sFirstDate = SFIRSTDATE.split(' ')[0]
			$('#driveDate').val(sFirstDate);
			$timeout(function(){
				$('#sex').val(data.idCardSex);
			},100)
		})

		$.device.Camera_Hide();
		$scope.fullItemName = data.itemName + "--" + data.statusName;
		var name = data.itemName;
		if(name.length > 10) {
			name = name.slice(0, 10) + '...'
		}
		$scope.itemName = name;
		clearInterval(t);
		time();
		addAnimate($('.scrollBox2'))
		$scope.targetTypeName = '个人';
		$scope.targetTips = '申请人身份证号';
		$scope.targetName = '申请人姓名';
		$scope.SisAlert = false;
		$scope.concel = "false";
		$scope.isLoading = true;
		$scope.alertConfirm = function() {
			$scope.isAlert = false;
		}

		// $wathc 监听 targetTypeName 
		$scope.$watch('targetTypeName', function(newValue, oldValue) {
			$scope.targetTypeName == '个人' ? $scope.targetTips = '申请人身份证号' : $scope.targetTips = '统一社会信用代码';
			$scope.targetTypeName == '个人' ? $scope.targetName = '申请人姓名' : $scope.targetName = '企业名称';
			$scope.targetTypeName == '个人' ? $('#targetName').val(data.idCardName) : $('#targetName').val('');
			$scope.targetTypeName == '个人' ? $('#targetNo').val(data.idCardNum) : $('#targetNo').val('');
		});
		$('#targetName').val(data.idCardName);
		$('#username').val(data.idCardName);
		$('#licenseNo').val("");
		$timeout(function(){
			$('#sex').val(data.idCardSex);
		},100)
		if(data.mobile) {
			$('#mobile').val(data.mobile);
		}
		$scope.prevStep = function() {
			$scope.isLoading = false;
			$location.path("/select");
		}

		// 保存数据
		$scope.flag = true;
		$scope.goNext = function() {
			/*$location.path("/materialUpload");*/
			if(!$scope.flag) {
				return ''
			}
			var condFlag = false;
			do {

				if($scope.targetTypeName == '个人') {
					if($('#sex').val() != "男") {
						if($('#sex').val() != "女") {
							$scope.isAlert = true;
							$scope.msg = "请输入正确的申请人性别！(“男”或“女”)";
							return;
						}
					}
					//			if(!checkIdCard($('#targetNo').val())){
					//				$scope.isAlert = true;
					//				$scope.msg = "请输入正确的身份证信息！";
					//				return;
					//			}
					if($('#username').val() < 1) {
						$scope.isAlert = true;
						$scope.msg = "请输入正确的申请人姓名！";
						return;
					}
					//			if(!checkIdCard($('#licenseNo').val())){
					//				$scope.isAlert = true;
					//				$scope.msg = "请输入正确的身份证信息！";
					//				return;
					//			}

					if(!isPhoneAvailable($('#mobile').val())) {
						$scope.isAlert = true;
						$scope.msg = "请输入正确的手机号！";
						return;
					}

					if(!checkIdCard($('#targetNo').val())) {
						$scope.isAlert = true;
						$scope.msg = "请输入正确的身份证号！";
						return;
					}

					if($('#address').val() < 1) {
						$scope.isAlert = true;
						$scope.msg = "请输入正确的户籍地址！";
						return;
					}

					if($('#liveAddress').val() < 1) {
						$scope.isAlert = true;
						$scope.msg = "请输入正确的常住地址！";
						return;
					}

					if($('#carType').val() < 1) {
						$scope.isAlert = true;
						$scope.msg = "请输入正确的准驾车型！";
						return;
					}

					if($('#driveNo').val() < 1) {
						$scope.isAlert = true;
						$scope.msg = "请输入正确的驾驶证档案编号！";
						return;
					}

					if(!checkDate($('#driveDate').val())) {
						$scope.isAlert = true;
						$scope.msg = "请输入正确的驾驶证初领日期！格式为：YYYY-MM-DD   例：2010-01-02";
						return;
					}

				} else if($scope.targetTypeName == '法人') {
					if($('#targetName').val() < 1) {
						$scope.isAlert = true;
						$scope.msg = "请输入正确的企业名称！";
						return;
					}
					//			if($('#targetNo').val().length < 17) {
					//				$scope.isAlert = true;
					//				$scope.msg = "请输入正确的统一社会信用代码！";
					//				return;
					//			}
					if($('#username').val() < 1) {
						$scope.isAlert = true;
						$scope.msg = "请输入正确的经办人姓名！";
						return;
					}
					//			if(!checkIdCard($('#licenseNo').val())){
					//				$scope.isAlert = true;
					//				$scope.msg = "请输入正确的身份证信息！";
					//				return;
					//			}
					//				
					if(!isPhoneAvailable($('#mobile').val())) {
						$scope.isAlert = true;
						$scope.msg = "请输入正确的手机号！";
						return;
					}

				}
			} while (condFlag);
			//			获取编号计数的ajax
			$.ajax({
				url: urlHost + '/aci/autoterminal/countWorkApply.do?',
				dataType: 'jsonp',
				jsonp: "jsonpCallback",
				success: function(dataJson) {
					data.No = dataJson.spplyNo
				},
				error: function(err) {
					console.log('查询编号计数失败')
				}
			})
			$scope.isLoading = false;
			data.phone = document.getElementById("mobile").value;
			data.idCardNum = document.getElementById("targetNo").value;
			data.idCardName = document.getElementById("username").value;
			data.sex = document.getElementById("sex").value;
			data.address = document.getElementById("address").value;
			data.liveAddress = document.getElementById("liveAddress").value;
			data.carType = document.getElementById("carType").value;
			data.driveNo = document.getElementById("driveNo").value;
			data.driveDate = document.getElementById("driveDate").value;
			var from = $('#infoForm').serialize();
			var fConfig = {
				loginName: 'zzgzt',
				stWindowNo: encodeURI('工作台'),
				stMobile: data.phone,
				stIdentityNo: data.idCardNum,
				stUserName: data.idCardName,
				stStatusName: data.statusName,

				stStatusId: data.stStatusId,
				stItemId: data.itemId,
				jsonpCallback: "JSON_CALLBACK",
			};
			$http.jsonp(urlHost + '/aci/materialUp/saveNew.do?' + from, {
				params: fConfig
			}).success(function(dataJson) {
				console.log(dataJson)
				$scope.flag = true;
				data.applyId = dataJson.applyId;
				console.log("在ajax中测试appyId传入数据为：" + data.applyId);
				if(($('#username').val()) !== null) {
					data.username = $('#username').val();
				} else if(($('#targetName').val()) !== null) {
					data.username = $('#targetName').val();
				}

				data.autoUpload = [{
						'name': '上海市网络预约出租汽车驾驶员人员背景审查申请表',
						'stuffId': 'jd02822',
						'code': null,
						'imgUrl': ''
					},
					{
						'name': '申请人身份证',
						'stuffId': 'jd02823',
						'code': 310105109000100,
						'imgUrl': ''
					},
					{
						'name': '申请人户口簿',
						'stuffId': 'jd02824',
						'code': 310105105000100,
						'imgUrl': ''
					},
					{
						'name': '申请人驾驶证',
						'stuffId': 'jd02825',
						'code': 310100208000100,
						'imgUrl': ''
					}
				];

				data.formName = "网约车驾驶员人员背景核查申请书空表";
				//打印word
				$scope.printWord = function() {
					//alert('printWord开始')
					//alert(appData.idcardObj.frontUrl+""+appData.idcardObj.backUrl);
					//			var index2 = layer.load(0, {
					//				shade: [0.5, "white"] //0.7透明度的黑色背景
					//			});

					//word表格类型
					$scope.name = data.formName + ".doc";
					$scope.nameCopy = "copy.doc";
					$scope.path = $.device.currentPath() + "\\temp\\file\\" + $scope.name
					$scope.pathCopy = $.device.currentPath() + "\\temp\\file\\copy.doc"
					//alert('path= '+$scope.path)
					//alert('pathCopy= '+$scope.pathCopy)

					$.device.fileCopy($scope.path, $scope.pathCopy)
					//alert('1复制完成！')

					$.device.officeOpenRelative($scope.nameCopy);
					//alert('2打开完成')
					$timeout(function() {
						layer.close(index2);
						$.device.officeShow(640, 720, 220, 160);
					}, 2000);
					$timeout(function() {
						if(data.companyName && data.companyNo) {
							$.device.officeSetStringValue("companyName", data.companyName);
							$.device.officeSetStringValue("companyNo", data.companyNo);
						} else {
							$.device.officeSetStringValue("idCardName", data.idCardName);
							$.device.officeSetStringValue("sex", data.sex);
							$.device.officeSetStringValue("idCardNum", data.idCardNum);
							$.device.officeSetStringValue("address", data.address);
							$.device.officeSetStringValue("liveAddress", data.liveAddress);
							$.device.officeSetStringValue("carType", data.carType);
							$.device.officeSetStringValue("driveNo", data.driveNo);
							$.device.officeSetStringValue("phone", data.phone);
							$.device.officeSetStringValue("driveDate", data.driveDate);
							$.device.officeSetStringValue("No", data.No);
							//					$.device.officeSetJpgValue("frontImg", data.idcardObj.frontUrl);
							//					$.device.officeSetJpgValue("reverseImg", data.idcardObj.backUrl);
						}
						//				$.device.officeReadOnly(true);
						var path = $scope.pathCopy
						//alert(path)
						//$.device.officeSaveAs($scope.pathCopy);
						$.device.officeSave();
						//alert('3保存完成')
					}, 1000);

					//alert('4写入完成')

					//d打印语句
					$timeout(function() {
						$scope.isPrint = "show";
						$.device.officePrint();
						$.device.officeClose();
						$scope.isPrint = "hidden";
					}, 2000);
					//alert('5打印完成')
				}
//				$scope.printWord()
				
				//上传word
				$scope.uploadOffice = function() {
					$scope.jsonData = {
						applyId: data.applyId,
						stuffId: data.autoUpload[i].stuffId,
						reset: data.resetData,
						fileName: data.autoUpload[i].name + '.doc',
						type: "2",
						itemId: data.itemId,
						stuffName: data.autoUpload[i].name,
					};
					$scope.jsonData = JSON.stringify($scope.jsonData);

					$.device.httpUpload(urlHost + '/aci/materialUp/uploadFile.do', "fileInput", $scope.pathCopy,
						$scope.jsonData,
						function(result) {
							layer.msg("word上传成功");
							data.isUpload.push({
								index: 0,
								stuffName: '上海市网络预约出租汽车驾驶员人员背景审查申请表',
								img: $scope.pathCopy,
								method: "个人档案"
							});
							data.isUploadForPush.push({
								index: 0,
								stuffName: '上海市网络预约出租汽车驾驶员人员背景审查申请表',
								img: $scope.pathCopy,
								method: "个人档案"
							});
							data.listImg[0] = {
								'activeImg': null,
								'index': 0,
								'stuffName': '上海市网络预约出租汽车驾驶员人员背景审查申请表',
								'stuffId': 'jd02822',
								'upload': true,
								'upload2': false,
							}
							alert('word上传成功')
						},
						function(webexception) {
							$scope.isLoading = true;
							alert("上传失败");
							layer.msg("上传失败");
						});
				}

				$scope.loadAjax4 = function() {
					$scope.isLoading = false;
					$scope.uploadOffice()
				}
				$scope.loadAjax3 = function() {
					$scope.isLoading = false;
					//data, code,applyId,stuffId,itemId,stuffName ,callback, callback1
					appFactory.pro_fetch(data.idCardNum,'310100208000100',data.applyId,'jd02825',data.itemId,'申请人驾驶证',function(dataJson){
						
						try{
							if(!dataJson) {
								alert("没有身份证证照!")
							}
							data.autoUpload[3].imgUrl = 'http://180.169.7.194:8080/ac-product' + json[0].pictureUrlForBytes
							//----给listImg赋值
							data.listImg[i] = {
								'activeImg': null,
								'index': 3,
								'stuffName': '申请人驾驶证',
								'stuffId': 'jd02825',
								'upload': true,
								'upload2': false,
							}
						}catch(e){}
						
					},function(){
						data.isUpload.push({
							index: 3,
							stuffName: '申请人驾驶证',
							img: data.autoUpload[3].imgUrl,
							method: "个人档案"
						});
						data.isUploadForPush.push({
							index: 3,
							stuffName: '申请人驾驶证',
							img: data.autoUpload[3].imgUrl,
							method: "个人档案"
						});
						$scope.loadAjax4()
					})
				}
				$scope.loadAjax2 = function() {
					$scope.isLoading = false;
					//data, code,applyId,stuffId,itemId,stuffName ,callback, callback1
					appFactory.pro_fetch(data.idCardNum,'310105105000100',data.applyId,'jd02824',data.itemId,'申请人户口簿',function(dataJson){
						
						try{
							if(!dataJson) {
								alert("没有身份证证照!")
							}
							data.autoUpload[2].imgUrl = 'http://180.169.7.194:8080/ac-product' + json[0].pictureUrlForBytes
							//----给listImg赋值
							data.listImg[i] = {
								'activeImg': null,
								'index': 2,
								'stuffName': '申请人户口簿',
								'stuffId': 'jd02824',
								'upload': true,
								'upload2': false,
							}
						}catch(e){}
						
					},function(){
						data.isUpload.push({
							index: 2,
							stuffName: '申请人户口簿',
							img: data.autoUpload[2].imgUrl,
							method: "个人档案"
						});
						data.isUploadForPush.push({
							index: 2,
							stuffName: '申请人户口簿',
							img: data.autoUpload[2].imgUrl,
							method: "个人档案"
						});
						$scope.loadAjax3()
					})
				}
				$scope.loadAjax1 = function() {
					//alert('loadAjax1')
					$scope.isLoading = false;
					//data, code,applyId,stuffId,itemId,stuffName ,callback, callback1
					appFactory.pro_fetch(data.idCardNum,'310105109000100',data.applyId,'jd02823',data.itemId,'申请人身份证',function(dataJson){
						
						try{
							//alert('进入callback')
							if(!dataJson) {
								alert("没有身份证证照!")
							}
							data.autoUpload[1].imgUrl = 'http://180.169.7.194:8080/ac-product' + json[0].pictureUrlForBytes
							//----给listImg赋值
							data.listImg[1] = {
								'activeImg': null,
								'index': 1,
								'stuffName': '申请人身份证',
								'stuffId': 'jd02823',
								'upload': true,
								'upload2': false,
							}
						}catch(e){}
						
					},function(){
						//alert('进入callback1')
						data.isUpload.push({
							index: 1,
							stuffName: '申请人身份证',
							img: data.autoUpload[1].imgUrl,
							method: "个人档案"
						});
						data.isUploadForPush.push({
							index: 1,
							stuffName: '申请人身份证',
							img: data.autoUpload[1].imgUrl,
							method: "个人档案"
						});
						//alert('loadAjax1完成')
						$scope.loadAjax2()
					})
				}
				/*try{
					$scope.loadAjax1()
				}catch(err){
					$.log.debug('app报错'+err)
				}*/
				$location.path("/materialList");

			}).error(function(e) {
				console.log(e)
			});
			$scope.flag = false;
		};
	} else {
		//不是只能办件
		$scope.isShowZNSB = false;
		console.log($scope.isShowZNSB)
		$.device.Camera_Hide();
		$scope.fullItemName = data.itemName + "--" + data.statusName;
		var name = data.itemName;
		if(name.length > 10) {
			name = name.slice(0, 10) + '...'
		}
		$scope.itemName = name;
		clearInterval(t);
		time();
		addAnimate($('.scrollBox2'))
		$scope.targetTypeName = '个人';
		$scope.targetTips = '申请人身份证号';
		$scope.targetName = '申请人姓名';
		$scope.SisAlert = false;
		$scope.concel = "false";
		$scope.isLoading = true;
		$scope.alertConfirm = function() {
			$scope.isAlert = false;
		}

		// $wathc 监听 targetTypeName 
		$scope.$watch('targetTypeName', function(newValue, oldValue) {
			$scope.targetTypeName == '个人' ? $scope.targetTips = '申请人身份证号' : $scope.targetTips = '统一社会信用代码';
			$scope.targetTypeName == '个人' ? $scope.targetName = '申请人姓名' : $scope.targetName = '企业名称';
			$scope.targetTypeName == '个人' ? $('#targetName').val(data.idCardName) : $('#targetName').val('');
			$scope.targetTypeName == '个人' ? $('#targetNo').val(data.idCardNum) : $('#targetNo').val('');
		});
		$scope.idCardName = data.idCardName;
		$('#licenseNo').val("");
		if(data.mobile) {
			$('#mobile').val(data.mobile);
		}
		$scope.prevStep = function() {
			$scope.isLoading = false;
			$location.path("/select");
		}
		// 保存数据
		$scope.flag = true;
		$scope.goNext = function() {
			/*$location.path("/materialUpload");*/
			if(!$scope.flag) {
				return ''
			}
			var condFlag = false;
			do {
				if($scope.targetTypeName == '个人') {
					if($('#targetName').val() < 1) {
						$scope.isAlert = true;
						$scope.msg = "请输入正确的申请人姓名！";
						return;
					}
					//			if(!checkIdCard($('#targetNo').val())){
					//				$scope.isAlert = true;
					//				$scope.msg = "请输入正确的身份证信息！";
					//				return;
					//			}
					if($('#username').val() < 1) {
						$scope.isAlert = true;
						$scope.msg = "请输入正确的经办人姓名！";
						return;
					}
					//			if(!checkIdCard($('#licenseNo').val())){
					//				$scope.isAlert = true;
					//				$scope.msg = "请输入正确的身份证信息！";
					//				return;
					//			}

					if(!isPhoneAvailable($('#mobile').val())) {
						$scope.isAlert = true;
						$scope.msg = "请输入正确的手机号！";
						return;
					}
				} else if($scope.targetTypeName == '法人') {
					if($('#targetName').val() < 1) {
						$scope.isAlert = true;
						$scope.msg = "请输入正确的企业名称！";
						return;
					}
					//			if($('#targetNo').val().length < 17) {
					//				$scope.isAlert = true;
					//				$scope.msg = "请输入正确的统一社会信用代码！";
					//				return;
					//			}
					if($('#username').val() < 1) {
						$scope.isAlert = true;
						$scope.msg = "请输入正确的经办人姓名！";
						return;
					}
					//			if(!checkIdCard($('#licenseNo').val())){
					//				$scope.isAlert = true;
					//				$scope.msg = "请输入正确的身份证信息！";
					//				return;
					//			}
					//				
					if(!isPhoneAvailable($('#mobile').val())) {
						$scope.isAlert = true;
						$scope.msg = "请输入正确的手机号！";
						return;
					}

				}
			} while (condFlag);
			$scope.isLoading = false;

			data.mobile = document.getElementById("mobile").value;
			data.targetName = document.getElementById("targetName").value;
			data.targetNo = document.getElementById("targetNo").value;
			data.licenseNo = document.getElementById("licenseNo").value;
			var from = $('#infoForm').serialize();
			console.log('非智能办件form', from)
			var fConfig = {
				stStatusId: data.stStatusId,
				itemId: data.itemId,
				jsonpCallback: "JSON_CALLBACK",
			};
			$http.jsonp(urlHost + '/aci/materialUp/save.do?' + from, {
				params: fConfig
			}).success(function(dataJson) {
				$scope.flag = true;
				data.applyId = dataJson.applyId;
				console.log("在ajax中测试appyId传入数据为：" + data.applyId);
				if(($('#username').val()) !== null) {
					data.username = $('#username').val();
				} else if(($('#targetName').val()) !== null) {
					data.username = $('#targetName').val();
				}
				$location.path("/materialList");
			}).error(function(e) {
				console.log(e)
			});
			$scope.flag = false;
		};
	}

	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			checkDOMChanges: true,
			hScroll: false
		});
	};
	$scope.isScroll();

});
app.controller("materialListController", function($scope, $sce, $route, $http, $location, data, $timeout) {
	removeAnimate($('.main2'))
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$.device.fileClose();
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	clearInterval(t);
	time();
	addAnimate($('.main2'))
	$scope.mustUpload = [];
	$scope.isfinishUp = [];
	$scope.current = 0;
	$scope.isAlert = false;
	$scope.commit = false;

	console.log(data)
	console.log(data.isUpload);
	// 获取材料列表  	
	var oConfig = {
		statusId: data.stStatusId,
		//		statusId:'24f2c6a6-ccec-42a3-bdda-d62135dbf331',
		jsonpCallback: "JSON_CALLBACK",
	};
	//	$http.jsonp('http://10.2.100.117:8080/ac-product/aci/materialUp/queryStuffList.do', {
	$http.jsonp(urlHost + '/aci/materialUp/queryStuffList.do', {
		params: oConfig
	}).success(function(dataJson) {
		$scope.commit = true;
		$scope.stuffList = dataJson.data;
		for(var i = 0; i < $scope.stuffList.length; i++) {
			if($scope.stuffList[i].stStuffName.length >= 27) {
				$scope.stuffList[i].stStuffName = $scope.stuffList[i].stStuffName.slice(0, 27) + '...'
			}
		}
		for(var i = 0; i < $scope.stuffList.length; i++) {
			if($scope.stuffList[i].nmMust == '1') {
				$scope.mustUpload.push({
					index: i,
					stuffName: $scope.stuffList[i].stStuffName
				});
			}
		}
		if(data.listImg == 0) {
			for(var i = 0; i < $scope.stuffList.length; i++) {
				data.listImg[i] = {
					'activeImg': null,
					'index': i,
					'stuffName': $scope.stuffList[i].stStuffName,
					'stuffId': $scope.stuffList[i].stStuffId,
					'upload': true,
					'upload2': false,
				}
			}
		}
		$scope.listImg = data.listImg;
		$scope.isLoading = true;
	}).error(function() {
		console.log('queryStuffList error')
	})

	//设置上传文件 按钮变化
	if(data.isUpload != "") {
		for(var m = 0; m < data.isUpload.length; m++) {
			for(var n = 0; n < data.listImg.length; n++) {
				if(data.isUpload[m].stuffName == data.listImg[n].stuffName) {
					data.listImg[data.isUpload[m].index].upload = false;
					data.listImg[data.isUpload[m].index].upload2 = true;
				}
			}
		}
	}

	// 材料上传 
	data.currentIndex++;
	$scope.toUploadMaterial = function(index, id, name) {
		$scope.isLoading = false;
		data.stStuffId = id;
		data.stStuffName = name;
		data.currentIndex = index;
		data.stuffImg = data.listImg[data.currentIndex];
		$location.path("/uploadMethod");
	}
	//点击要点
	$scope.clickStExamineBasis = function() {
		console.log(this.stuff.stExamineBasis)
		$scope.msg = $sce.trustAsHtml(this.stuff.stExamineBasis)
		$scope.isAlert = true;
	}
	$scope.stExamineBasisHandle = function() {
		$scope.isAlert = false;
	}
	//重新上传
	$scope.toNewUploadMaterial = function(index, id, name) {
		$scope.isLoading = false;
		for(var i = 0; i < data.isUpload.length; i++) {
			if(index == data.isUpload[i].index) {
				//	data.isUpload[i] = "";
			}
		}
		for(var i = 0; i < data.isUploadForPush.length; i++) {
			if(index == data.isUploadForPush[i].index) {
				data.isUploadForPush[i] = "";
			}
		}
		data.resetData = "reset";
		data.stStuffId = id;
		data.stStuffName = name;
		data.currentIndex = index;
		data.stuffImg = data.listImg[data.currentIndex];
		$location.path("/uploadMethod");
	}

	//查看
	$scope.view = function(index, name) {
		$scope.isLoading = false;
		data.currentIndex = index;
		///alert(index)
		data.view = data.isUpload;
		$location.path("/materialView");
	}

	//提交办件
	$scope.submit = function() {
		console.log($scope.mustUpload)
		console.log('data', data)
		console.log('data.isUploadForPush', data.isUploadForPush)
		var a = 0;
		if($scope.mustUpload.length == 0) { //需上传材料列表为空
			$scope.isLoading = false;
			$location.path('/infoFinish');
		} else {
			if(data.isUploadForPush && $scope.mustUpload.length <= data.isUploadForPush.length) {
				for(var i = 0; i < data.isUploadForPush.length; i++) {
					for(var j = 0; j < $scope.mustUpload.length; j++) {
						if(data.isUploadForPush[i].stuffName == $scope.mustUpload[j].stuffName) {
							a++;
						}
					}
				}
				if(a >= $scope.mustUpload.length) {
					$location.path('/infoFinish');
				} else {
					layer.msg("请提交必上传材料");
				}
			} else {
				layer.msg("请提交必上传材料");
			}
		}
	};
	$scope.prevStep = function() {
		$scope.isLoading = false;
		$location.path('/info');
	}

	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});

app.controller("materialUploadController", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	clearInterval(t);
	time();
	//上传材料信息
	$scope.stStuffName = data.stuffImg.stStuffName;
	$scope.stuffImg = data.sample;
	$scope.test = function() {
		$('#test').viewer({
			url: 'data-original',
		});
	}
	$scope.upload = function() {
		$location.path("/uploadMethod");
	}
	$scope.prevStep = function() {
		$location.path('/materialList');
	};
	$scope.nextStep = function() {
		$location.path('/uploadMethod');
	}
});
app.controller("uploadMethodController", function($scope, $route, $http, $location, data, $timeout) {
	removeAnimate($('.linkBox1'))
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();
	var name = data.itemName;
	$.device.fileClose();
	$scope.statusName = data.statusName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	// 显示材料名称
	$scope.stStuffName = data.stStuffName;
	$scope.stuffNameShow = data.isShowStuffName;
	clearInterval(t);
	time();
	addAnimate($('.linkBox1'))
	// 扫描上传
	$scope.scanPhoto = function() {
		$location.path('/finish');
	};
	// U盘上传
	$scope.isAlert = false;
	$scope.msg = '请确认是否插入U盘';
	$scope.confirmText = '是';
	$scope.cancelText = '否';
	$scope.takePhoto = function() {
		$scope.isAlert = true;
		//		layer.confirm("<em style='color:black'>" + '请确认是否插入U盘！' + "</em>", {
		//			btn: ['已插入U盘', '未插入U盘'] //按钮
		//		}, function() {
		//			$('.layui-layer-shade').hide();
		//			$('.layui-layer').hide();
		//			$timeout(function() {
		//				$location.path('/takePhoto/U');
		//			}, 20);
		//		}, function() {
		//			$('.layui-layer-shade').hide();
		//			$('.layui-layer').hide();
		//			$timeout(function() {
		//				layer.msg('请选择其他上传方式！');
		//			}, 20);
		//		});
	};
	$scope.alertConfirm = function() {
		$('.layui-layer-shade').hide();
		$('.layui-layer').hide();
		$timeout(function() {
			$location.path('/takePhoto/U');
		}, 20);
	}
	$scope.alertCancel = function() {
		$scope.isAlert = false;
		$('.layui-layer-shade').hide();
		$('.layui-layer').hide();
		$timeout(function() {
			layer.msg('请选择其他上传方式！');
		}, 20);
	}
	// 档案库上传
	$scope.materialPic = function() {
		$location.path('/history');
	};
	$scope.prevStep = function() {
		$location.path('/materialList');
	};

});
app.controller("historyController", function($scope, $route, $http, $location, data, $timeout) {
	removeAnimate($('.linkBox1'))
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();
	var name = data.itemName;
	$.device.fileClose();
	$scope.statusName = data.statusName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	// 显示材料名称
	$scope.stStuffName = data.stStuffName;
	$scope.stuffNameShow = data.isShowStuffName;
	clearInterval(t);
	time();
	addAnimate($('.linkBox1'))
	// 个人电子证照上传
	$scope.scanPhoto = function() {
		$location.path('/materialPic');
	};
	// 法人电子证照上传
	$scope.businessPhoto = function() {
		$location.path('/materialPic2');
	};
	// 档案库上传
	$scope.materialPic = function() {
		$location.path('/materialPic1');
	};
	$scope.prevStep = function() {
		$location.path('/uploadMethod');
	}
});

app.controller("finishController", function($scope, $route, $http, $location, data, $timeout, $compile) {
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();

	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.isfinishUp = [];
	$scope.itemName = name;
	clearInterval(t);
	time();
	$.device.cmCaptureShow(680, 530, 210, 340);
	$.device.cmCaptureSelectRect(0, 0, 1920, 1920);
	// 
	$scope.isFinish = false;
	$scope.isLoading = true;
	// 拍照
	var imgHTML = "";
	var imgIndex = 0;
	var isShow = true;
	$scope.next = function() {
		if(isShow == false) {
			$.device.cmCaptureShow(680, 530, 210, 340); // 开启高拍仪
			isShow = true;
			return;
		}
		$.device.cmCaptureHide(); // 关闭高拍仪
		$scope.isLoading = false;
		var scanImg = $.device.cmCaptureCaptureUrl();
		$scope.jsonData1 = {
			applyId: data.applyId,
			/*"205fae3a-beee-492e-826e-7dd86513b2a8",*/
			stuffId: data.stStuffId,
			reset: data.resetData,
			type: "2",
			itemId: data.itemId,
			stuffName: data.stStuffName,
			fileName: "photo1.png"
		};
		$scope.jsonData1 = JSON.stringify($scope.jsonData1);
		$.device.httpUpload(urlHost + "/aci/materialUp/uploadFile.do", "fileInput", scanImg,
			$scope.jsonData1,
			function(result) {
				data.resetData = "";
				data.uploadStuffId = data.stStuffId;
				if(data.listImg.length < 1) {
					data.currentIndex++; // 没有材料列表时   文件下标+1 
				}
				data.isUploadForPush.push({
					index: data.currentIndex,
					stuffName: data.stStuffName,
					img: scanImg,
					method: "高拍仪"
				});
				$scope.isfinishUp.push({
					index: data.currentIndex,
					stuffName: data.stStuffName,
					img: scanImg,
					method: "高拍仪"
				});
				console.log(data.isUpload);
				data.fileName.push('扫描文件');
				$.log.debug("scanImg:" + scanImg);
				imgHTML += '<div ng-click="show()" class="img" id="' + data.uploadStuffId + '"><img src="' + scanImg + '" width="150" height="200" /></div>';

				$('.imgBox').html($compile(imgHTML)($scope));
				$scope.isFinish = true;
				$scope.isLoading = true;
				$.device.cmCaptureShow(680, 530, 210, 340); // 开启高拍仪
				$scope.$apply();
			},
			function(webexception) {
				$scope.isLoading = true;
				layer.msg("上传材料失败")
				$timeout(function() {
					$location.path('/finish');
				}, 10000);

			});
		$scope.show = function() {
			$.device.cmCaptureHide();
			$(".img").viewer({
				url: "src",
			});
			isShow = false;
		};

	};
	// 完成拍照
	$scope.finishUpload = function() {
		for(var i = 0; i < data.isUpload.length; i++) {
			if(data.currentIndex == data.isUpload[i].index) {
				data.isUpload[i] = "";
			}
		}
		for(var i = 0; i < $scope.isfinishUp.length; i++) {
			data.isUpload.push($scope.isfinishUp[i]);
		}
		$timeout(function() {
			$.device.cmCaptureHide(); // 关闭高拍仪
			$location.path('/materialList');
		}, 20);
	};

	$scope.prevStep = function() {
		$.device.cmCaptureHide(); // 关闭高拍仪
		$location.path('/materialList');
	}
});

app.controller("takePhotoController", function($scope, $route, $http, $location, data, $timeout, $routeParams) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.isfinishUp = [];
	$scope.itemName = name;
	clearInterval(t);
	time();
	try {
		$.device.fileOpen(function(value) {
			$timeout(function() {
				$scope.UData = value; //"E://123.txt";// 
			}, 100)

		});
	} catch(e) {
		$timeout(function() {
			layer.msg("请插入U盘后操作");
		}, 10000)
		$location.path("/uploadMethod");
	}

	// 继续U盘上传
	$scope.takePhoto = function() {
		if($scope.UData == null) {
			layer.msg("未获取到U盘数据");
		} else {
			$scope.jsonData1 = {
				applyId: data.applyId,
				/*"205fae3a-beee-492e-826e-7dd86513b2a8",*/
				stuffId: data.stStuffId,
				fileName: $scope.UData,
				reset: data.resetData,
				type: "2",
				itemId: data.itemId,
				stuffName: data.stStuffName,
			};
			$scope.jsonData1 = JSON.stringify($scope.jsonData1);
			$.device.httpUpload(urlHost + "/aci/materialUp/uploadFile.do", "fileInput", $scope.UData,
				$scope.jsonData1,
				function(result) {
					data.resetData = "";
					//layer.msg("上传成功");
					data.fileName.push($scope.UData);
					data.isUploadForPush.push({
						index: data.currentIndex,
						stuffName: data.stStuffName,
						fileName: $scope.UData,
						method: "U盘上传"
					});
					$scope.isfinishUp.push({
						index: data.currentIndex,
						stuffName: data.stStuffName,
						fileName: $scope.UData,
						method: "U盘上传"
					});
					//					$timeout(function() {
					//						$location.path('/materialList');
					//					}, 10000);
				},
				function(webexception) {
					layer.msg("上传失败");
				});
		}
		try {
			$.device.fileOpen(function(value) {
				$timeout(function() {
					$scope.UData = value; //"E://123.txt";// 
				}, 100)
			});
		} catch(e) {
			$timeout(function() {
				layer.msg("请插入U盘后操作");
			}, 100)
			$location.path("/uploadMethod");
		}
	};
	// 上传
	$scope.highCapture = function() {
		if($scope.UData == null) {
			layer.msg("未获取到U盘数据");
		} else {
			$scope.jsonData1 = {
				applyId: data.applyId,
				/*"205fae3a-beee-492e-826e-7dd86513b2a8",*/
				stuffId: data.stStuffId,
				fileName: $scope.UData,
				reset: data.resetData,
				type: "2",
				itemId: data.itemId,
				stuffName: data.stStuffName,
			};
			$scope.jsonData1 = JSON.stringify($scope.jsonData1);
			$.device.httpUpload(urlHost + "/aci/materialUp/uploadFile.do", "fileInput", $scope.UData,
				$scope.jsonData1,
				function(result) {
					layer.msg("上传成功");
					data.resetData = "";
					data.fileName.push($scope.UData);
					data.isUploadForPush.push({
						index: data.currentIndex,
						stuffName: data.stStuffName,
						fileName: $scope.UData,
						method: "U盘上传"
					});
					$scope.isfinishUp.push({
						index: data.currentIndex,
						stuffName: data.stStuffName,
						fileName: $scope.UData,
						method: "U盘上传"
					});
					for(var i = 0; i < data.isUpload.length; i++) {
						if(data.currentIndex == data.isUpload[i].index) {
							data.isUpload[i] = "";
						}
					}
					for(var i = 0; i < $scope.isfinishUp.length; i++) {
						data.isUpload.push($scope.isfinishUp[i]);
					}
					$timeout(function() {
						$location.path('/materialList');
					}, 100);
				},
				function(webexception) {
					layer.msg("上传失败");
				});
		}
	};
	$scope.prevStep = function() {
		$location.path('/materialList');
	}
});

app.controller("materialViewController", function($scope, $http, $location, data) {
	$scope.stuffList = [];
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	//	if(data.itemId == "c8b8e1f5-55bc-415a-b0e9-b39f27f473a3" && data.itemTenNo == "3120W0358000")
	for(var i = 0; i < data.view.length; i++) {
		if(data.currentIndex == data.view[i].index) {
			$scope.stuffList.push(data.view[i]);
		}
	}
	//alert($scope.stuffList.length)
	if($scope.stuffList[0].method === "高拍仪") {
		$scope.scanShow = true;
		$scope.upanShow = false;
		$scope.picShow = false;
	} else if($scope.stuffList[0].method === "U盘上传") {
		$scope.scanShow = false;
		$scope.upanShow = true;
		$scope.picShow = false;
	} else if($scope.stuffList[0].method === "个人档案") {
		$scope.scanShow = false;
		$scope.upanShow = false;
		$scope.picShow = true;
	}
	//放大样张
	$scope.showWord = function() {
		$(".word").viewer({
			url: "data-original",
		});
	}
	//放大已上传材料
	$scope.showScan = function() {
		$(".scan").viewer({
			url: "data-original",
		});
	}
	//打开文件
	$scope.open = function() {
		try {
			$.device.officeOpen($scope.stuffList[0].fileName);
		} catch(e) {
			layer.msg("未找到此文件");
		}
	}
	$scope.prevStep = function() {
		$location.path('/materialList');
	};
});

app.controller("materialPic1Controller", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.isfinishUp = [];
	var Btn = document.getElementById("consureOnclick");
	Btn.style.display = "block";
	$scope.itemName = name;
	$scope.imgUrls = "";
	clearInterval(t);
	time();
	$scope.profileShow = function() {
		$.ajax({
			//			url: "http://10.237.16.72/aci/autoterminal/dzzz/queryCertBaseData.do",
			url: urlHost + "/aci/autoterminal/forward.do",
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				//				jsonpCallback: "JSON_CALLBACK",
				//				certNo: data.idCardNum, //"340881199303145313" || idCardNum  idCardName
				//				type: 0
				fmd: "aci-archives",
				fdo: "getLicenseStuffList",
				jsonpCallback: "JSON_CALLBACK",
				stName: data.idCardName, // "夏雷" ||"340881199303145313" || 
				stIdNo: data.idCardNum,
				type: 0
			},
			success: function(json) {
				var dataJson = eval("(" + JSON.stringify(json) + ")");
				if(!dataJson || dataJson == '') { // !dataJson[0].address
					layer.msg("没有数据，请重新选择上传方式!");
					$timeout(function() {
						$location.path('/uploadMethod');
					}, 1000);
				} else {
					$scope.imgUrls = dataJson;
					$scope.$apply();
					console.log($scope.imgUrls)
				}
				$scope.isLoading = true;
			},
			error: function(err) {
				console.log(err)
				layer.msg("请求失败!");
			}
		});
	};

	$scope.profileShow();
	$scope.current = 0;
	$scope.select = function(index) {
		$scope.current = index;

	}

	$scope.goNext = function() {
		//剔除最后一张电子照片----避免闪退
		//if($scope.current == $scope.imgUrls.length-1){
		//	alert('电子照片无法上传，请重新选择！')
		//	return;
		//}

		//		Btn.style.display = "none";//-----------------------
		data.selectImg = urlHost + $scope.imgUrls[$scope.current].imageUrl;
		$scope.waitUploadImgUrl = data.selectImg;
		$scope.isLoading = false;
		$scope.jsonData = {
			applyId: data.applyId,
			/*"205fae3a-beee-492e-826e-7dd86513b2a8",*/
			stuffId: data.stStuffId,
			reset: data.resetData,
			fileName: "waitUploadImg.jpg",
			type: "2",
			itemId: data.itemId,
			stuffName: data.stStuffName,
		};
		$scope.jsonData = JSON.stringify($scope.jsonData);
		console.log($scope.jsonData);
		$.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		urlHost + 
			$scope.waitUploadImgUrl,
			"C:\\waitUploadImg.jpg",
			//将选中图片下载
			function(bytesCopied, totalBytes) {
				console.log(bytesCopied + "," + totalBytes);
			},
			function(result) {
				//将选中图片上传到服务器
				$.device.httpUpload(urlHost + '/aci/materialUp/uploadFile.do', "fileInput", "C:/waitUploadImg.jpg",
					//				$.device.httpUpload('http://10.2.100.128:8080/ac-product/aci/materialUp/uploadFile.do', "fileInput", "C:/waitUploadImg.jpg",
					$scope.jsonData,
					function(result) {
						data.resetData = "";
						angular.element(document.querySelector('.imgStyle')).addClass('uploaded')
						//						layer.msg("上传成功，选中并点击确认继续上传，上传完成请点击“返回列表”");
						layer.alert('上传成功！点击"确认"继续上传,上传完成请点击"返回列表"!', {
							skin: 'layui-layer-lan',
							closeBtn: 1,
							anim: 5 //动画类型
						});
						$scope.isLoading = true;
						data.isUploadForPush.push({
							index: data.currentIndex,
							stuffName: data.stStuffName,
							img: $scope.waitUploadImgUrl,
							method: "个人档案"
						});
						$scope.isfinishUp.push({
							index: data.currentIndex,
							stuffName: data.stStuffName,
							img: $scope.waitUploadImgUrl,
							method: "个人档案"
						});
						for(var i = 0; i < data.isUpload.length; i++) {
							if(data.currentIndex == data.isUpload[i].index) {
								data.isUpload[i] = "";
							}
						}
						for(var i = 0; i < $scope.isfinishUp.length; i++) {
							data.isUpload.push($scope.isfinishUp[i]);
						}
						//						$timeout(function() {
						//							$location.path('/materialList');
						//						}, 1000);
					},
					function(webexception) {
						$scope.isLoading = true;
						layer.msg("上传失败");
					});
			},
			function(webexception) {
				alert("下载文档失败");
			}
		);

	};

});

app.controller("materialPic2Controller", function($scope, $route, $http, $location, data, $timeout) {
	$scope.isShowView = false; // 是否显示预览框
	$scope.isLoading = false;
	//检测到没有同意识别码
	var certNo = data.licenseNo;
	if(certNo == '' || !certNo) {
		layer.msg('请手动输入统一识别码！');
		$timeout(function() {
			$location.path('/info')
		}, 2000);
	}
	console.log(certNo)
	$scope.zoomCount = 1;
	$scope.rotateCount = 0;
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.isfinishUp = [];
	var Btn = document.getElementById("consureOnclick");
	Btn.style.display = "block";
	$scope.itemName = name;
	$scope.imgUrls = "";
	clearInterval(t);
	time();
	$scope.profileShow = function() {
		$.ajax({
			url: urlHost + "/aci/autoterminal/dzzz/queryCertBaseData.do",
			//url: "http://10.237.16.72/aci/autoterminal/forward.do",
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				machineId:machineId || '',
				itemName:data.itemName || '',
				itemCode:data.itemId || '',
				businessCode:data.applyId || '',
				jsonpCallback: "JSON_CALLBACK",
				certNo: certNo, //91310114MA1GUDX96C//"340881199303145313" || idCardNum  idCardName
				type: 1
				/*fmd: "aci-archives",
				fdo: "getLicenseStuffList",
				jsonpCallback: "JSON_CALLBACK",
				stName: data.idCardName, // "夏雷" ||"340881199303145313" || 
				stIdNo: data.idCardNum,
				type: 0*/
			},
			success: function(json) {
				console.log(json)
				var dataJson = eval("(" + JSON.stringify(json) + ")");
				if(!dataJson || dataJson == '') { // !dataJson[0].address
					console.log('没有数据,请核验统一识别码！')
					$timeout(function() {
						$location.path('/info');
					}, 2000);
				} else {
					$scope.imgUrls = dataJson;
					$scope.$apply();
					console.log($scope.imgUrls)
				}
				$scope.isLoading = true;
			},
			error: function(err) {
				console.log(err)
				layer.msg("请求失败!");
			}
		});
	};

	$scope.profileShow();
	$scope.current = 0;
	$scope.select = function(index) {
		if($scope.current == index) {
			$scope.isShowView = true;
		}
		$scope.current = index;
		$scope.electImg = " http://180.169.7.194:8080/ac-product" + $scope.imgUrls[$scope.current].pictureUrlForBytes;

	}

	$scope.goNext = function() {
		//剔除最后一张电子照片----避免闪退
		//if($scope.current == $scope.imgUrls.length-1){
		//	alert('电子照片无法上传，请重新选择！')
		//	return;
		//}

		//		Btn.style.display = "none";//-----------------------
		data.selectImg = urlHost + $scope.imgUrls[$scope.current].pictureUrlForBytes;
		$scope.waitUploadImgUrl = data.selectImg;
		$scope.isLoading = false;
		$scope.jsonData = {
			applyId: data.applyId,
			/*"205fae3a-beee-492e-826e-7dd86513b2a8",*/
			stuffId: data.stStuffId,
			reset: data.resetData,
			fileName: "waitUploadImg.jpg",
			type: "2",
			itemId: data.itemId,
			stuffName: data.stStuffName,
		};
		$scope.jsonData = JSON.stringify($scope.jsonData);
		console.log($scope.jsonData);
		$.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		urlHost + 
			$scope.waitUploadImgUrl,
			"C:\\waitUploadImg.jpg",
			//将选中图片下载
			function(bytesCopied, totalBytes) {
				console.log(bytesCopied + "," + totalBytes);
			},
			function(result) {
				//将选中图片上传到服务器
				$.device.httpUpload(urlHost + '/aci/materialUp/uploadFile.do', "fileInput", "C:/waitUploadImg.jpg",
					//				$.device.httpUpload('http://10.2.100.128:8080/ac-product/aci/materialUp/uploadFile.do', "fileInput", "C:/waitUploadImg.jpg",
					$scope.jsonData,
					function(result) {
						data.resetData = "";
						angular.element(document.querySelector('.imgStyle')).addClass('uploaded')
						layer.msg("上传成功!”");
						$scope.isLoading = true;
						data.isUploadForPush.push({
							index: data.currentIndex,
							stuffName: data.stStuffName,
							img: $scope.waitUploadImgUrl,
							method: "个人档案"
						});
						$scope.isfinishUp.push({
							index: data.currentIndex,
							stuffName: data.stStuffName,
							img: $scope.waitUploadImgUrl,
							method: "个人档案"
						});
						for(var i = 0; i < data.isUpload.length; i++) {
							if(data.currentIndex == data.isUpload[i].index) {
								data.isUpload[i] = "";
							}
						}
						for(var i = 0; i < $scope.isfinishUp.length; i++) {
							data.isUpload.push($scope.isfinishUp[i]);
						}
						$timeout(function() {
							$location.path('/materialList');
						}, 1000);
					},
					function(webexception) {
						$scope.isLoading = true;
						layer.msg("上传失败");
					});
			},
			function(webexception) {
				alert("下载文档失败");
			}
		);

	};
	$scope.viewClose = function() {
		$scope.isShowView = false;
		$scope.zoomCount = 1;
		$scope.rotateCount = 0;
	};

	$scope.zoomIn = function() {
		$scope.zoomCount += 0.5;
		if($scope.zoomCount > 5) {
			$scope.zoomCount = 5;
		}
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

	$scope.zoomOut = function() {
		$scope.zoomCount -= 0.5;
		if($scope.zoomCount < 0.5) {
			$scope.zoomCount = 0.5;
		}
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

	$scope.rotateLeft = function() {
		$scope.rotateCount -= 90;
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

	$scope.rotateRight = function() {
		$scope.rotateCount += 90;
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

});
app.controller("materialPicController", function($scope, $route, $http, $location, data, $timeout) {
	$scope.isShowView = false; // 是否显示预览框
	$scope.zoomCount = 1;
	$scope.rotateCount = 0;
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.isfinishUp = [];
	var Btn = document.getElementById("consureOnclick");
	Btn.style.display = "block";
	$scope.itemName = name;
	$scope.imgUrls = "";
	clearInterval(t);
	time();
	$scope.profileShow = function() {
		$.ajax({
			url: "http://180.169.7.194:8080/ac-product/aci/autoterminal/dzzz/queryCertBaseData.do",
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				machineId:machineId || '',
				itemName:data.itemName || '',
				itemCode:data.itemId || '',
				businessCode:data.applyId || '',
				certNo: data.idCardNum, //"340881199303145313" || 
				type: 0,
				jsonpCallback: "JSON_CALLBACK"
			},
			success: function(json) {
				console.log('电子证照数据', json)
				var dataJson = eval("(" + JSON.stringify(json) + ")");
				console.log('dataJson', dataJson)
				if(dataJson.length == 0 || !dataJson) { // !dataJson[0].address
					layer.msg("没有数据，请重新选择上传方式!");
					$timeout(function() {
						$location.path('/uploadMethod');
					}, 2000);
				} else {
					$scope.imgUrls = dataJson;
					$scope.$apply();
					console.log($scope.imgUrls)
				}
				$scope.isLoading = true;
			},
			error: function(err) {
				console.log(err)
			}
		});
	};
	$scope.profileShow();
	$scope.current = 0;
	$scope.select = function(index) {
		if($scope.current == index) {
			$scope.isShowView = true;
		}
		$scope.current = index;
		$scope.electImg = " http://180.169.7.194:8080/ac-product" + $scope.imgUrls[$scope.current].pictureUrlForBytes;
	}

	$scope.goNext = function() {
		//if($scope.current == $scope.imgUrls.length-1){
		//	alert('电子照片无法上传，请重新选择!')
		//	return;
		//}
		//Btn.style.display = "none";

		data.selectImg = " http://180.169.7.194:8080/ac-product" + $scope.imgUrls[$scope.current].pictureUrlForBytes;
		$scope.waitUploadImgUrl = data.selectImg;
		$scope.isLoading = false;
		$scope.jsonData = {
			applyId: data.applyId,
			/*"205fae3a-beee-492e-826e-7dd86513b2a8",*/
			stuffId: data.stStuffId,
			reset: data.resetData,
			fileName: "waitUploadImg.jpg",
			type: "2",
			itemId: data.itemId,
			stuffName: data.stStuffName,
		};
		$scope.jsonData = JSON.stringify($scope.jsonData);
		console.log($scope.jsonData);
		$.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		urlHost + 
			$scope.waitUploadImgUrl,
			"C:\\waitUploadImg.jpg",
			//将选中图片下载
			function(bytesCopied, totalBytes) {
				console.log(bytesCopied + "," + totalBytes);
			},
			function(result) {
				//将选中图片上传到服务器
				$.device.httpUpload(urlHost + '/aci/materialUp/uploadFile.do', "fileInput", "C:/waitUploadImg.jpg",
					$scope.jsonData,
					function(result) {
						data.resetData = "";
						layer.msg("上传成功");

						$scope.isLoading = true;
						data.isUploadForPush.push({
							index: data.currentIndex,
							stuffName: data.stStuffName,
							img: $scope.waitUploadImgUrl,
							method: "个人档案"
						});
						$scope.isfinishUp.push({
							index: data.currentIndex,
							stuffName: data.stStuffName,
							img: $scope.waitUploadImgUrl,
							method: "个人档案"
						});
						for(var i = 0; i < data.isUpload.length; i++) {
							if(data.currentIndex == data.isUpload[i].index) {
								data.isUpload[i] = "";
							}
						}
						for(var i = 0; i < $scope.isfinishUp.length; i++) {
							data.isUpload.push($scope.isfinishUp[i]);
						}
						$timeout(function() {
							$location.path('/materialList');
						}, 1000);
					},
					function(webexception) {
						$scope.isLoading = true;
						layer.msg("上传失败");
					});
			},
			function(webexception) {
				alert("下载文档失败");
			}
		);

	};
	$scope.viewClose = function() {
		$scope.isShowView = false;
		$scope.zoomCount = 1;
		$scope.rotateCount = 0;
	};

	$scope.zoomIn = function() {
		$scope.zoomCount += 0.5;
		if($scope.zoomCount > 5) {
			$scope.zoomCount = 5;
		}
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

	$scope.zoomOut = function() {
		$scope.zoomCount -= 0.5;
		if($scope.zoomCount < 0.5) {
			$scope.zoomCount = 0.5;
		}
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

	$scope.rotateLeft = function() {
		$scope.rotateCount -= 90;
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

	$scope.rotateRight = function() {
		$scope.rotateCount += 90;
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

});
app.controller("infoFinishController", function($scope, $route, $http, $location, data, $timeout) {
	var lodop = $.device.printGetLodop();
	var name = data.itemName;
	data.applyNo = "";
	console.log(name + "提交办事名字测试");
	var statusName = data.statusName;
	console.log(statusName + "提交办事情形测试");
	$scope.allName = name + '--' + statusName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	clearInterval(t);
	time();
	// 生成办件编码
	$scope.submitApply = function() {
		$scope.finishData = {
			//		applyNo: data.applyNo, // '751122018600008'
			//		subItemCodes: '', // data.itemId
			applyId: data.applyId,
			userName: data.idCardName,
			subitemNos: "",
			itemNo: data.itemNo,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/materialUp/toSubmit.do', {
			params: $scope.finishData
		}).success(function(dataJson) {
			console.log(dataJson);
			data.applyNo = dataJson.applyNo;
			console.log(data.applyNo);
			$scope.applyNo = data.applyNo;
			$scope.statusText = '打印凭证';
			var code = urlHost + "/aci/workapply/getNewQueueInfo.do?t=b&bid=" + data.applyNo;
			var date = new Date();
			var month = date.getMonth() + 1;
			// 打印凭条
			var f = 0;
			if(data.itemId == "c8b8e1f5-55bc-415a-b0e9-b39f27f473a3" && data.itemTenNo == "3120W0358000") {
				$scope.print = function() {
					f++;
					$scope.statusText = '返回首页';
					if($scope.statusText == '返回首页' && f == 2) {
						$.device.GoHome();
					} else {
						$scope.name = data.formName + ".doc";
						$scope.nameCopy = "copy.doc";
						$scope.path = $.device.currentPath() + "\\temp\\file\\" + $scope.name
						$scope.pathCopy = $.device.currentPath() + "\\temp\\file\\copy.doc"
						$.device.fileCopy($scope.path, $scope.pathCopy)
						$.device.officeOpenRelative($scope.nameCopy);
						$timeout(function() {
							layer.close(index2);
							$.device.officeShow(640, 720, 220, 160);
						}, 2000);
						$timeout(function() {
							if(data.companyName && data.companyNo) {
								$.device.officeSetStringValue("companyName", data.companyName);
								$.device.officeSetStringValue("companyNo", data.companyNo);
							} else {
								$.device.officeSetStringValue("idCardName", data.idCardName);
								$.device.officeSetStringValue("sex", data.sex);
								$.device.officeSetStringValue("idCardNum", data.idCardNum);
								$.device.officeSetStringValue("address", data.address);
								$.device.officeSetStringValue("liveAddress", data.liveAddress);
								$.device.officeSetStringValue("carType", data.carType);
								$.device.officeSetStringValue("driveNo", data.driveNo);
								$.device.officeSetStringValue("phone", data.phone);
								$.device.officeSetStringValue("driveDate", data.driveDate);
								$.device.officeSetStringValue("No", data.No);
							}
							var path = $scope.pathCopy
							$.device.officeSave();
						}, 1000);
						//d打印语句
						$timeout(function() {
							$scope.isPrint = "show";
							$.device.officePrint();
							$.device.officePrint();
							$.device.officeClose();
							$scope.isPrint = "hidden";
						}, 2000);
					}
				}
			}else{
				$scope.print = function() {
					f++;
					$scope.statusText = '返回首页';
					if($scope.statusText == '返回首页' && f == 2) {
						$.device.GoHome();
					} else {
						lodop.ADD_PRINT_BARCODE(28, 34, 307, 47, "128A", data.applyNo);
						lodop.ADD_PRINT_BARCODE(28, 550, 168, 146, "QRCode", code);
						lodop.ADD_PRINT_TEXT(170, 260, 250, 50, "申报提交确认单");
						lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
						lodop.ADD_PRINT_TEXT(250, 28, 600, 30, "办件编码：" + data.applyNo);
						lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
						lodop.ADD_PRINT_TEXT(280, 28, 600, 30, "申请单位/申请人：" + data.username);
						lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
						lodop.ADD_PRINT_TEXT(310, 28, 600, 30, "申请事项：" + $scope.allName);
						lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
						lodop.ADD_PRINT_TEXT(370, 28, 450, 30, "请您携带办理材料至嘉定区行政服务中心进行办理。");
						lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
						lodop.ADD_PRINT_TEXT(400, 551, 168, 30, date.getFullYear() + "年" + month + "月" + date.getDate() + "日");
						lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
						//					lodop.ADD_PRINT_TEXT(480,28,670,30,"亲民提示：您可凭统一审批编码至“一网通办”总门户或扫描右侧二维码查询办件进度。");
						//					lodop.SET_PRINT_STYLEA(0,"FontSize",14);
						lodop.PRINT();
						lodop = $.device.printGetLodop();
						lodop.ADD_PRINT_BARCODE(28, 34, 307, 47, "128A", data.applyNo);
						lodop.ADD_PRINT_BARCODE(28, 550, 168, 146, "QRCode", code);
						lodop.ADD_PRINT_TEXT(170, 260, 250, 50, "申报提交确认单");
						lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
						lodop.ADD_PRINT_TEXT(250, 28, 600, 30, "办件编码：" + data.applyNo);
						lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
						lodop.ADD_PRINT_TEXT(280, 28, 600, 30, "申请单位/申请人：" + data.username);
						lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
						lodop.ADD_PRINT_TEXT(310, 28, 600, 30, "申请事项：" + $scope.allName);
						lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
						lodop.ADD_PRINT_TEXT(370, 28, 450, 30, "请您携带办理材料至嘉定区行政服务中心进行办理。");
						lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
						lodop.ADD_PRINT_TEXT(400, 551, 168, 30, date.getFullYear() + "年" + month + "月" + date.getDate() + "日");
						lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
						//					lodop.ADD_PRINT_TEXT(480,28,670,30,"亲民提示：您可凭统一审批编码至“一网通办”总门户或扫描右侧二维码查询办件进度。");
						//					lodop.SET_PRINT_STYLEA(0,"FontSize",14);
						lodop.PRINT();
					}
				};
			}
			
			

		}).error(function(e) {
			console.log(e)
		});
	}

	$scope.submitApply();

});

app.controller("qrCodeController", function($scope, $route, $http, $location, data, $timeout) {
	$scope.applyNo = $location.search().applyNo;
	$scope.isLoading = false;
	$scope.process = function() {
		var pConfig = {
			stApplyNo: $scope.applyNo,
			jsonpCallback: "JSON_CALLBACK"
		}
		$http.jsonp(urlHost + '/aci/autoterminal/eventquery/getApplyInfoByStApplyNo.do', {
			params: pConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.stItemName;
			$scope.name = dataJson.stName || dataJson.stUnit;
			$scope.date = dataJson.stApplyStr;
			$scope.status = dataJson.stFinalState;
			$scope.isLoading = true;
		}).error(function(err) {
			console.log('getApplyInfoByStApplyNo error');
		});
	}
	$scope.process();
});