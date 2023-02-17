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
app.controller("synthesizeMain", function($scope, $state, appData) {
	removeAnimate($('.container'))
	addAnimate($('.container'))
	$scope.choice = function(type) {
		appData.type = type;
		$state.go("infoLoginType");
	};
	$scope.goToApp = function(address) {

		if(address.indexOf("http") != -1) {
			window.location.href = address;
		} else {
			window.location.href = address;
		}
	};
});
app.controller('infoLoginType', function($state, $scope, appData, $http, $location) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	if(appData.type == "" || appData.type == undefined) {
		appData.type = $location.search().type;
	}
	$scope.operation = "请选择登录方式";
	$scope.person = true;
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("infoLogin");
	}
	$scope.prevStep = function() {
		if(appData.type == "jjxx" || appData.type == "gjj") {
			$.device.GoHome();
		} else {
			$state.go("main");
		}
	}
});
app.controller('infoLogin', function($scope, $http, $state, appData, appFactory, $timeout, $rootScope) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go('main');
	}
	switch($scope.loginType) {
		case "idcard":
			$scope.operation = "身份证登录";
			break;
		case "ukey":
			$scope.loginBtn = true;
			$scope.operation = "ukey登录";
			break;
		case "cloud":
			$scope.operation = "随申办登录";
			break;
	}
	//跳转页面
	$scope.nextStep = function() {
		$scope.tokenType = "token";
		$scope.token = function() {
			$state.go("info");
		}
	}
	$scope.caLoginStatus = "";
	$scope.prevStep = function() {
		$state.go("infoLoginType");
	}
	$scope.caLogin = function() { //登录
		$scope.caLoginStatus = 'login';
	}
	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
		} else {
			layer.msg("没有获取到")
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		$state.go("info");
		$scope.$apply();
	}
	$scope.citizenLogin = function(info) {
		if(appData.qrCodeType == "suishenma") {
			appData.licenseName = info.zwdtsw_name;
			appData.licenseNumber = info.zwdtsw_cert_id;
			$scope.nextStep();
		} else {
			var idcardInfo = info.result.data;
			appData.licenseName = idcardInfo.realname;
			appData.licenseNumber = idcardInfo.idcard;
			$scope.nextStep();
		}
	}
});
app.controller("info", function($scope, $state, appData, $sce, appFactory, $timeout) {
	$scope.caption = "查询结果"
	$scope.funName = appData.funName;
	$scope.isLoading = true;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.nextText = "打印";
	var info = []; // 记录医保金数据
	var oldInfo = []; // 记录养老金数据
	$scope.searchType = ["养老金", "医保金", "公积金"];
	$scope.houseType = ['住房公积金信息', '补充住房公积金信息'];
	$scope.licenseName = appData.licenseName;
	$scope.licenseNumber = appData.licenseNumber;
	$timeout(function() {
		$scope.isLoading = false;
		switch(appData.type) {
			case "ylj":
				$scope.getMatterCon(0, "养老金");
				break;
			case "ybj":
				$scope.getMatterCon(1, "医保金");
				break;
			case "gjj":
				$scope.getMatterCon(2, "公积金");
				break;
			default:
				$scope.getMatterCon(0, "养老金");
				break;
		}
	}, 2000);
	// 养老金查询
	$scope.getPensionInfo = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/SJInfoQuery/queryOldAgePension.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				accessToekn: appData.token,
			},
			success: function(dataJson) {
				if(dataJson.success) {
					$scope.jobYear = dataJson.data.sum1.workingYears;
					oldInfo = dataJson.data;
				} else {
					//					$scope.isAlert = true;
					//					$scope.msg = dataJson.msg;
					$scope.isYlj = false;
				}
			},
			error: function(err) {
				$scope.isYlj = false;
			}
		});
		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: '个人养老金信息查询',
			}
		}
		recordUsingHistory('人社服务', '查询', '个人养老金信息查询', $scope.licenseName, $scope.licenseNumber, '', '', JSON.stringify($scope.jsonStr));
		//行为分析(查询)
		trackEventForQuery("个人养老金信息查询", "", "查询", "上海市人力资源社会保障局", appData.licenseName, appData.licenseNumber, "");
	}
	$scope.getPensionInfo();
	// 医保金查询
	$scope.queryMedicalDetails = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/SJInfoQuery/queryMedicalInsuranceFund.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				idCard: $scope.licenseNumber,
				userName: encodeURI($scope.licenseName)
			},
			success: function(dataJson) {
				info = dataJson;
			},
			error: function(err) {
				$scope.isYbj = false;
			}
		});
		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: '个人医保金信息查询',
			}
		}
		recordUsingHistory('医保服务', '查询', '个人医保金信息查询', appData.licenseName, appData.licenseNumber, '', '', JSON.stringify($scope.jsonStr));
		trackEventForQuery('个人医保金信息查询', '', '查询', '上海市医疗保障局', appData.licenseName, appData.licenseNumber, '');
	}
	$scope.queryMedicalDetails();
	// 公积金查询
	// 个人住房公积金帐户基本信息查询接口
	$scope.accumulationFundInfo = function() {
		$scope.isShowPrint = "show";
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/accumulationFund/accumulationFundInfo.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				name: encodeURI($scope.licenseName),
				identNo: $scope.licenseNumber
			},
			success: function(dataJsonp) {
				if(dataJsonp.head.rst.buscode == "000000") {
					$scope.basicInfo = dataJsonp.body;
				} else {
					$scope.msg = "未查询到信息";
				}
				$scope.basicAccumulationFundDetailInfo();
			},
			error: function(err) {

			}
		});
	}

	// 个人住房公积金补充帐户明细查询接口
	$scope.supplementAccumulationFundDetailInfo = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/accumulationFund/supplementAccumulationFundDetailInfo.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				name: encodeURI($scope.licenseName),
				identNo: $scope.licenseNumber,
				type: $scope.type || ""
			},
			success: function(dataJsonp) {
				if(dataJsonp.body.datas.length <= 1) {
					$scope.supplementDetailInfo = "";
				} else {
					$scope.supplementDetailInfo = dataJsonp.body.datas;
				}
			},
			error: function(err) {

			}
		});
	}

	// 个人住房公积金基本帐户明细查询接口
	$scope.basicAccumulationFundDetailInfo = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/accumulationFund/basicAccumulationFundDetailInfo.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				name: encodeURI($scope.licenseName),
				identNo: $scope.licenseNumber,
				type: 'year'
			},
			success: function(dataJsonp) {
				if(dataJsonp.head.rst.buscode == "000000") {
					$scope.basicDetailInfo = dataJsonp.body.datas;
				} else {
					$scope.msg = "未查询到信息";
				}
				$scope.supplementAccumulationFundDetailInfo();
			},
			error: function(err) {

			}
		});
	}
	$scope.accumulationFundInfo();

	//判断是否为空 为空用"-"展示
	function changeTrim(str) {
		str = str.trim();
		if(str == undefined || str == "") {
			return '—';
		} else {
			return str;
		}
	}
	// 选择三金展示项
	$scope.getMatterCon = function(index, type) {
		$scope.current = index;
		switch(type) {
			case "养老金":
				$scope.ylj = true;
				$scope.ybj = false;
				$scope.gjj = false;
				if($scope.isYlj == false) {
					$scope.isAlert = true;
					$scope.msg = "未查询到您的信息";
					$scope.alertConfirm = function() {
						$scope.isAlert = false;
					}
				}
				$scope.RecentItem = oldInfo.sum2.reverse();
				$scope.RealItem = oldInfo.sum3.reverse();
				$scope.TotalItem = oldInfo.sum4;
				break;
			case "医保金":
				$scope.ybj = true;
				$scope.ylj = false;
				$scope.gjj = false;
				if($scope.isYbj == false) {
					$scope.isAlert = true;
					$scope.msg = "未查询到您的信息";
					$scope.alertConfirm = function() {
						$scope.isAlert = false;
					}
				}
				$scope.Item = [{
						"firstName": "姓名",
						"firstValue": $scope.licenseName,
						"secondName": "就医记录册号",
						"secondValue": info.number
					},
					{
						"firstName": "职退状态",
						"firstValue": info.situation,
						"secondName": "帐号状态",
						"secondValue": info.mstate
					},
					{
						"firstName": "参保方法",
						"firstValue": info.method,
						"secondName": "就医关系",
						"secondValue": info.relation
					},
					{
						"firstName": "急诊当年状态",
						"firstValue": info.oestate,
						"secondName": "住院当年状态",
						"secondValue": info.hstate
					},
					{
						"firstName": "医疗保险卡状态",
						"firstValue": info.astate,
						"secondName": "社会保险卡状态",
						"secondValue": info.sstate
					},
					{
						"firstName": "当年账户余额",
						"firstValue": info.caccount,
						"secondName": "历年账户余额",
						"secondValue": info.taccount
					},
				]
				break;
			case "公积金":
				$scope.gjj = true;
				$scope.ybj = false;
				$scope.ylj = false;
				$scope.getHouse(0, '住房公积金信息'); // 默认选中住房公积金
				break;
		}
	};

	// 选择住房展示项
	$scope.getHouse = function(index, type) {
		$scope.showLine = true;
		$scope.currentC = index;
		switch(type) {
			case "住房公积金信息":
				$scope.baseInfo = true;
				$scope.addInfo = false;
				$scope.gjjItem = [{
						"firstName": "姓名",
						"firstValue": $scope.basicInfo.name,
						"secondName": "证件号码",
						"secondValue": $scope.basicInfo.id_card_num
					},
					{
						"firstName": "开户日期",
						"firstValue": $scope.basicInfo.jopen_date,
						"secondName": "销户日期",
						"secondValue": changeTrim($scope.basicInfo.jclose_date)
					},
					{
						"firstName": "缴存网点",
						"firstValue": $scope.basicInfo.brunch_name,
						"secondName": "住房公积金账号",
						"secondValue": $scope.basicInfo.pri_account
					},
					{
						"firstName": "住房公积金账户状态",
						"firstValue": $scope.basicInfo.jstate,
						"secondName": "住房公积金余额",
						"secondValue": $scope.basicInfo.jsurplus
					},
					{
						"firstName": "公积金累计缴存",
						"firstValue": $scope.basicInfo.jtotal_pay,
						"secondName": "累计支取",
						"secondValue": $scope.basicInfo.jtotal_draw
					},
					{
						"firstName": "末次汇缴月份",
						"firstValue": $scope.basicInfo.jlast_pay_month,
						"secondName": "当前月缴额",
						"secondValue": $scope.basicInfo.jmonth_pay
					},
					{
						"firstName": "当前单位账号",
						"firstValue": $scope.basicInfo.unit_code,
						"secondName": "当前所在单位名称",
						"secondValue": $scope.basicInfo.unit_name
					},
				]
				$scope.gjjDetailItem = $scope.basicDetailInfo;
				break;
			case '补充住房公积金信息':
				$scope.baseInfo = false;
				$scope.addInfo = true;
				$scope.gjjAddItem = [{
						"firstName": "姓名",
						"firstValue": $scope.basicInfo.name,
						"secondName": "证件号码",
						"secondValue": $scope.basicInfo.id_card_num
					},
					{
						"firstName": "开户日期",
						"firstValue": $scope.basicInfo.bopen_date,
						"secondName": "销户日期",
						"secondValue": changeTrim($scope.basicInfo.bclose_date)
					},
					{
						"firstName": "缴存网点",
						"firstValue": $scope.basicInfo.brunch_name,
						"secondName": "补充住房公积金账号",
						"secondValue": $scope.basicInfo.bpri_account
					},
					{
						"firstName": "补充住房公积金账户状态",
						"firstValue": $scope.basicInfo.bstate,
						"secondName": "补充住房公积金余额",
						"secondValue": $scope.basicInfo.bsurplus
					},
					{
						"firstName": "补充公积金累计缴存",
						"firstValue": $scope.basicInfo.btotal_pay,
						"secondName": "累计支取",
						"secondValue": $scope.basicInfo.btotal_draw
					},
					{
						"firstName": "末次汇缴月份",
						"firstValue": $scope.basicInfo.blast_pay_month,
						"secondName": "当前月缴额",
						"secondValue": $scope.basicInfo.bmonth_pay
					},
					{
						"firstName": "当前单位账号",
						"firstValue": $scope.basicInfo.bunit_code,
						"secondName": "当前所在单位名称",
						"secondValue": $scope.basicInfo.bunit_name
					},
				]
				$scope.gjjAddDetailItem = $scope.supplementDetailInfo;
				break;
		}
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go('infoLoginType');
	}
	$scope.prevStep = function() {
		$state.go("infoLoginType");
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
	$scope.print = function() {
		$state.go("print");
	}
});
app.controller("print", function($scope, $state, $timeout, appData, $sce, $location) {
	//获取网点信息
	console.log(jQuery.getConfigMsg.qutletsVal);
	$scope.licenseName = appData.licenseName;
	$scope.licenseNumber = appData.licenseNumber;
	$scope.isAlert = true;
	$scope.msg = "请选择打印时间段：";
	$scope.cancelText = "近一年";
	$scope.confirmText = "近半年";
	$scope.page = 1; // 页数
	$scope.supplementDetailInfo = "";
	$scope.basicInfo = "";
	$scope.basicDetailInfo = "";
	//3、个人住房公积金补充帐户明细查询接口
	$scope.supplementAccumulationFundDetailInfo = function() {
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrl + "/aci/accumulationFund/supplementAccumulationFundDetailInfo.do",
			dataType: "json",
			//			jsonp: "jsonpCallback",
			data: {
				name: encodeURI($scope.licenseName),
				identNo: $scope.licenseNumber,
				type: $scope.type || ""
			},
			success: function(dataJsonp) {
				if(dataJsonp.body.datas.length <= 1) {
					$scope.supplementDetailInfo = "";
					$scope.print();
				} else {
					$scope.supplementDetailInfo = dataJsonp.body.datas;
					$scope.page = $scope.page + 1;
					if($scope.supplementDetailInfo.length > 17) {
						$scope.supplementPage = Math.ceil(($scope.supplementDetailInfo.length - 17) / 30)
						$scope.page = $scope.page + $scope.supplementPage;
					}
					$scope.print();
				}
			},
			error: function(err) {

			}
		});
	}

	//2、个人住房公积金基本帐户明细查询接口
	$scope.basicAccumulationFundDetailInfo = function() {
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrl + "/aci/accumulationFund/basicAccumulationFundDetailInfo.do",
			dataType: "json",
			//			jsonp: "jsonpCallback",
			data: {
				name: encodeURI($scope.licenseName),
				identNo: $scope.licenseNumber,
				type: $scope.type || ""
			},
			success: function(dataJsonp) {
				if(dataJsonp.head.rst.buscode == "000000") {
					$scope.basicDetailInfo = dataJsonp.body.datas;
					$scope.startDate = dataJsonp.start_date;
					$scope.endDate = dataJsonp.end_date;
					$scope.startDate = $scope.startDate.replace(/-/g, '');
					$scope.endDate = $scope.endDate.replace(/-/g, '');
					if($scope.basicDetailInfo.length > 17) {
						$scope.basicPage = Math.ceil(($scope.basicDetailInfo.length - 17) / 30)
						$scope.page = $scope.page + $scope.basicPage;
					}
				} else {
					$scope.msg = "未查询到信息";
				}
				$scope.supplementAccumulationFundDetailInfo();
			},
			error: function(err) {

			}
		});
	}
	//1、个人住房公积金帐户基本信息查询接口
	$scope.accumulationFundInfo = function() {
		$scope.isShowPrint = "show";
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrl + "/aci/accumulationFund/accumulationFundInfo.do",
			dataType: "json",
			//			jsonp: "jsonpCallback",
			data: {
				name: encodeURI($scope.licenseName),
				identNo: $scope.licenseNumber
			},
			success: function(dataJsonp) {
				if(dataJsonp.head.rst.buscode == "000000") {
					$scope.basicInfo = dataJsonp.body;
				} else {
					$scope.msg = "未查询到信息";
				}
				$scope.basicAccumulationFundDetailInfo();
			},
			error: function(err) {

			}
		});
	}
	//打印
	$scope.print = function() {
		$scope.basicPageSum = 0;
		$scope.imgBase64 = $.getConfigMsg.preUrl + "/aci/selfWorkBench/infoSearch/image/logo.png";

		function changeTrim(str) {
			str = str.trim();
			if(str == undefined || str == "") {
				return '—';
			} else {
				return str;
			}
		}
		var LODOP = $.device.printGetLodop('');
		var date = new Date();
		var month = date.getMonth() + 1;

		function print2() {
			var table1 = "<style>table {font-size: 13px;border-collapse: collapse;border-bottom: 1px solid;border-right: 1px solid;text-align: center;width: 700px;}table td {height:30px;border-left: 1px solid;border-top: 1px solid;}</style>" +
				"<table><tr><td>个人账号</td><td>姓名</td><td>身份证号</td><td>开户日期</td><td>销户日期</td><td>账户状态</td></tr>" +
				"<tr><td>" + $scope.basicInfo.bpri_account + "</td><td>" + $scope.basicInfo.name + "</td><td>" + $scope.basicInfo.id_card_num + "</td><td>" + $scope.basicInfo.bopen_date + "</td><td>" + changeTrim($scope.basicInfo.bclose_date) + "</td><td>" + $scope.basicInfo.bstate + "</td></tr>" +
				"<tr><td>当前余额</td><td>末次汇缴月份</td><td>缴存网点</td><td>累计缴存</td><td>累计支取</td><td>当前月缴额</td></tr>" +
				"<tr><td>" + $scope.basicInfo.bsurplus + "</td><td>" + $scope.basicInfo.blast_pay_month + "</td><td>" + $scope.basicInfo.bcbrunch_name + "</td><td>" + $scope.basicInfo.btotal_pay + "</td><td>" + $scope.basicInfo.btotal_draw + "</td><td>" + $scope.basicInfo.bmonth_pay + "</td</tr><tr><td colspan='2'>当前单位账号</td><td colspan='4'>当前所在单位名称</td></tr><tr><td colspan='2'>" + $scope.basicInfo.bunit_code + "</td><td colspan='4'>" + $scope.basicInfo.bunit_name + "</td></tr></table>";
			var table2 = "<style>table {font-size: 13px;border-collapse: collapse;border-bottom: 1px solid;border-right: 1px solid;text-align: center;width: 700px;}table td {height:30px;border-left: 1px solid;border-top: 1px solid;}tr td:nth-child(1),tr td:nth-child(4){width:72px;}" +
				"tr td:nth-child(1){width:10%}tr td:nth-child(2){width:9%}tr td:nth-child(3){width:13%}tr td:nth-child(4){width:9%}tr td:nth-child(5){width:10%}tr td:nth-child(6){width:11%}tr td:nth-child(7){width:9%}tr td:nth-child(8){width:8%}tr td:nth-child(9){}</style>" +
				"<table><tr><td>发生日期</td><td>业务类型</td><td>业务发生原因</td><td>汇缴月份</td><td>发生金额</td><td>当前余额</td><td>银行利息</td><td>单位账号</td><td>单位名称</td></tr>";
			var str = "";
			var length = $scope.supplementDetailInfo.length;
			console.log(length);
			if($scope.supplementDetailInfo.length < 17) {
				for(var i = 0; i < $scope.supplementDetailInfo.length; i++) {
					str = "<tr><td>" + $scope.supplementDetailInfo[i].btime +
						"</td><td>" + $scope.supplementDetailInfo[i].bsummary + "</td><td>" + ($scope.supplementDetailInfo[i].breason_code || '—') +
						"</td><td>" + ($scope.supplementDetailInfo[i].pay_month || '—') + "</td><td>" + $scope.supplementDetailInfo[i].bsurplus_hp +
						"</td><td>" + $scope.supplementDetailInfo[i].bcur_surplus + "</td><td>" + $scope.supplementDetailInfo[i].byhlx +
						"</td><td>" + $scope.supplementDetailInfo[i].bunit_code + "</td><td>" + $scope.supplementDetailInfo[i].bunit_name + "</td></tr>";
					table2 = table2 + str;
				}
				table2 = table2 + "</table>";
			} else {
				var tableList = [];
				for(var i = 0; i < 17; i++) {
					str = "<tr><td>" + $scope.supplementDetailInfo[i].btime +
						"</td><td>" + $scope.supplementDetailInfo[i].bsummary + "</td><td>" + ($scope.supplementDetailInfo[i].breason_code || '—') +
						"</td><td>" + ($scope.supplementDetailInfo[i].pay_month || '—') + "</td><td>" + $scope.supplementDetailInfo[i].bsurplus_hp +
						"</td><td>" + $scope.supplementDetailInfo[i].bcur_surplus + "</td><td>" + $scope.supplementDetailInfo[i].byhlx +
						"</td><td>" + $scope.supplementDetailInfo[i].bunit_code + "</td><td>" + $scope.supplementDetailInfo[i].bunit_name + "</td></tr>";
					table2 = table2 + str;
				}
				table2 = table2 + "</table>";
				for(var j = 1; j <= $scope.supplementPage; j++) {
					tableList[j - 1] = "<style>tr td:nth-child(9){width:21%}table {font-size: 13px;border-collapse: collapse;border-bottom: 1px solid;border-right: 1px solid;text-align: center;width: 700px;}table td {height:30px;border-left: 1px solid;border-top: 1px solid;}tr td:nth-child(1),tr td:nth-child(4){width:72px;}" +
						"tr td:nth-child(1){width:10%}tr td:nth-child(2){width:9%}tr td:nth-child(3){width:13%}tr td:nth-child(4){width:9%}tr td:nth-child(5){width:10%}tr td:nth-child(6){width:11%}tr td:nth-child(7){width:9%}tr td:nth-child(8){width:8%}tr td:nth-child(9){}</style>" +
						"<table><tr><td>发生日期</td><td>业务类型</td><td>业务发生原因</td><td>汇缴月份</td><td>发生金额</td><td>当前余额</td><td>银行利息</td><td>单位账号</td><td>单位名称</td></tr>";
					for(var i = (17 + ($scope.supplementPage - 1) * 30); i < (17 + $scope.supplementPage * 30); i++) {
						if(i < $scope.supplementDetailInfo.length) {
							str = "<tr><td>" + $scope.supplementDetailInfo[i].btime +
								"</td><td>" + $scope.supplementDetailInfo[i].bsummary + "</td><td>" + ($scope.supplementDetailInfo[i].breason_code || '—') +
								"</td><td>" + ($scope.supplementDetailInfo[i].pay_month || '—') + "</td><td>" + $scope.supplementDetailInfo[i].bsurplus_hp +
								"</td><td>" + $scope.supplementDetailInfo[i].bcur_surplus + "</td><td>" + $scope.supplementDetailInfo[i].byhlx +
								"</td><td>" + $scope.supplementDetailInfo[i].bunit_code + "</td><td>" + $scope.supplementDetailInfo[i].bunit_name + "</td></tr>";
							tableList[j - 1] = tableList[j - 1] + str;
						}
					}
					tableList[j - 1] = tableList[j - 1] + "</table>";
				}
			}
			//LODOP.NewPage();
			$scope.imgBase64 = $.getConfigMsg.preUrl + "/aci/selfWorkBench/infoSearch/image/logo.png";
			var div = '<div style="width: 700px;justify-content: center;display: flex;">' +
				'<img style="width:30px;height:30px;" src="' + $scope.imgBase64 + '"/>' +
				'<h1 style="font-size: 12px;">个人住房公积金查询单</h1></div>';
			var addxinxi = '<p style="width: 680px;font-size: 13px;"><span style="float: left;">渠道：“一网通办”自助终端</span>' +
				'<span style="margin-left: 40px;">网点:' + ($scope.qutletsMsg || "临汾路街道社区事务受理服务中心") + '</span>' +
				'<span style="float: right;">打印日期:' + date.getFullYear() + month + date.getDate() + '</span></p>';
			var addxinxi2 = '<p style="width: 680px;font-size: 13px;"><span style="float: left;">公积金类型：补充住房公积金</span>' +
				'<span style="margin-left:40px;">查询时间段：' + $scope.startDate + "至" + $scope.endDate + '</span>';
			var addxinxi3 = '<span style="float: right;">第1页共:' + $scope.page + "页" + '</span></p>';
			var zijindogntai = '<p style="width: 700px;text-align: center;">资金动态明细</p>';
			var xxx = '<p style="font-size: 13px;position: absolute;bottom: 20px;left: 30px;">*本查询仅做信息查询使用</p>';
			var htmls = '<html><head><meta charset="UTF-8"></head><body style="margin: 10px 0px 0px 45px;">' + div + addxinxi + addxinxi2 + addxinxi3 + table1 + zijindogntai + table2 + xxx + '</body></html>';

			//LODOP.ADD_PRINT_IMAGE(22, 295, 35, 35, "<img style='width:30px;height:30px;' border='0' src='" + $scope.imgBase64 + "' />");
			//LODOP.ADD_PRINT_TEXT(32, 335, 184, 29, "个人住房公积金查询单");
			//LODOP.SET_PRINT_STYLEA(0, "FontSize", 12);
			//LODOP.SET_PRINT_STYLEA(0, "Bold", 1);
			//LODOP.ADD_PRINT_TEXT(100, 55, 188, 20, "渠道：“一网通办”自助终端");
			//LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			//LODOP.ADD_PRINT_TEXT(100, 312, 262, 20, "网点：" + (jQuery.getConfigMsg.qutletsVal || "临汾路街道社区事务受理服务中心"));
			//LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			//LODOP.ADD_PRINT_TEXT(121, 55, 188, 20, "公积金类型：补充住房公积金");
			//LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			//LODOP.ADD_PRINT_TEXT(100, 576, 141, 20, "打印日期：" + date.getFullYear() + month + date.getDate());
			//LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			//LODOP.ADD_PRINT_TEXT(120, 312, 231, 20, "查询时间段：" + $scope.startDate + "至" + $scope.endDate);
			//LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			//LODOP.ADD_PRINT_TEXT(120, 576, 100, 20, "第" + ($scope.basicPageSum + 2) + "页共" + $scope.page + "页");
			//LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			//LODOP.ADD_PRINT_TABLE(140, 30, 1300, 200, table1);
			//LODOP.ADD_PRINT_TEXT(336, 352, 200, 20, "资金动态明细");
			//LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			//LODOP.ADD_PRINT_TABLE(362, 30, 1300, 1400, table2);
			//LODOP.ADD_PRINT_TEXT(1000, 55, 200, 20, "*本查询仅做信息查询使用");
			//LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			console.log(tableList != undefined);
			if(tableList != undefined) {
				if(tableList.length > 0) {
					for(var i = 0; i < tableList.length; i++) {
						addxinxi3 = '<span style="float: right;">' + "第" + (i + 2) + "页共" + $scope.page + "页" + '</span></p>';
						var htmls2 = '<html><head><meta charset="UTF-8"></head><body style="margin: 10px 0px 0px 45px;">' + div + addxinxi + addxinxi2 + addxinxi3 + table1 + zijindogntai + tableList[i] + xxx + '</body></html>';
						setTimeout(function() {
							$.device.printerHtml(htmls2);
						}, 500);
						//LODOP.NewPage();
						//LODOP.ADD_PRINT_TEXT(80, 576, 100, 20, "第" + ($scope.basicPageSum + i + 2) + "页共" + $scope.page + "页");
						//LODOP.ADD_PRINT_TABLE(100, 30, 1300, 1400, tableList[i]);
						//LODOP.ADD_PRINT_TEXT(1000, 55, 200, 20, "*本查询仅做信息查询使用");
						//LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
					}
				}
			}
			//LODOP.PRINT();
			//console.log(htmls);
			setTimeout(function() {
				$.device.printerHtml(htmls);
			}, 100);
		}

		function print1() {
			var table1 = "<style>table {font-size: 13px;border-collapse: collapse;border-bottom: 1px solid;border-right: 1px solid;text-align: center;width: 700px;}table td {height:30px;border-left: 1px solid;border-top: 1px solid;}</style>" +
				"<table><tr><td>个人账号</td><td>姓名</td><td>身份证号</td><td>开户日期</td><td>销户日期</td><td>账户状态</td></tr>" +
				"<tr><td>" + $scope.basicInfo.pri_account + "</td><td>" + $scope.basicInfo.name + "</td><td>" + $scope.basicInfo.id_card_num + "</td><td>" + $scope.basicInfo.jopen_date + "</td><td>" + changeTrim($scope.basicInfo.jclose_date) + "</td><td>" + $scope.basicInfo.jstate + "</td></tr>" +
				"<tr><td>当前余额</td><td>末次汇缴月份</td><td>缴存网点</td><td>累计缴存</td><td>累计支取</td><td>当前月缴额</td></tr>" +
				"<tr><td>" + $scope.basicInfo.jsurplus + "</td><td>" + $scope.basicInfo.jlast_pay_month + "</td><td>" + $scope.basicInfo.brunch_name + "</td><td>" + $scope.basicInfo.jtotal_pay + "</td><td>" + $scope.basicInfo.jtotal_draw + "</td><td>" + $scope.basicInfo.jmonth_pay + "</td</tr><tr><td colspan='2'>当前单位账号</td><td colspan='4'>当前所在单位名称</td></tr><tr><td colspan='2'>" + $scope.basicInfo.unit_code + "</td><td colspan='4'>" + $scope.basicInfo.unit_name + "</td></tr></table>";
			var table2 = "<style>tr td:nth-child(9){width:21%}table {font-size: 13px;border-collapse: collapse;border-bottom: 1px solid;border-right: 1px solid;text-align: center;width: 700px;}table td {height:30px;border-left: 1px solid;border-top: 1px solid;}tr td:nth-child(1),tr td:nth-child(4){width:72px;}" +
				"tr td:nth-child(1){width:10%}tr td:nth-child(2){width:9%}tr td:nth-child(3){width:13%}tr td:nth-child(4){width:9%}tr td:nth-child(5){width:10%}tr td:nth-child(6){width:11%}tr td:nth-child(7){width:9%}tr td:nth-child(8){width:8%}tr td:nth-child(9){}</style>" +
				"<table><tr><td>发生日期</td><td>业务类型</td><td>业务发生原因</td><td>汇缴月份</td><td>发生金额</td><td>当前余额</td><td>银行利息</td><td>单位账号</td><td>单位名称</td></tr>";
			var str = "";
			var table3 = "";
			var length = $scope.basicDetailInfo.length;
			console.log(length);
			if($scope.basicDetailInfo.length < 17) {
				for(var i = 0; i < $scope.basicDetailInfo.length; i++) {
					str = "<tr><td>" + $scope.basicDetailInfo[i].jtime +
						"</td><td>" + $scope.basicDetailInfo[i].jsummary + "</td><td>" + changeTrim($scope.basicDetailInfo[i].jreason_code) +
						"</td><td>" + changeTrim($scope.basicDetailInfo[i].pay_month) + "</td><td>" + $scope.basicDetailInfo[i].jsurplus_hp +
						"</td><td>" + $scope.basicDetailInfo[i].jcur_surplus + "</td><td>" + $scope.basicDetailInfo[i].jyhlx +
						"</td><td>" + $scope.basicDetailInfo[i].junit_code + "</td><td>" + $scope.basicDetailInfo[i].junit_name + "</td></tr>";
					table2 = table2 + str;
				}
				table2 = table2 + "</table>";
			} else {
				var tableList = [];
				for(var i = 0; i < 17; i++) {
					str = "<tr><td>" + $scope.basicDetailInfo[i].jtime +
						"</td><td>" + $scope.basicDetailInfo[i].jsummary + "</td><td>" + changeTrim($scope.basicDetailInfo[i].jreason_code) +
						"</td><td>" + changeTrim($scope.basicDetailInfo[i].pay_month) + "</td><td>" + $scope.basicDetailInfo[i].jsurplus_hp +
						"</td><td>" + $scope.basicDetailInfo[i].jcur_surplus + "</td><td>" + $scope.basicDetailInfo[i].jyhlx +
						"</td><td>" + $scope.basicDetailInfo[i].junit_code + "</td><td>" + $scope.basicDetailInfo[i].junit_name + "</td></tr>";
					table2 = table2 + str;
				}
				table2 = table2 + "</table>";
				for(var j = 1; j <= $scope.basicPage; j++) {
					tableList[j - 1] = "<style>tr td:nth-child(9){width:21%}table {font-size: 13px;border-collapse: collapse;border-bottom: 1px solid;border-right: 1px solid;text-align: center;width: 700px;}table td {height:30px;border-left: 1px solid;border-top: 1px solid;}tr td:nth-child(1),tr td:nth-child(4){width:72px;}" +
						"tr td:nth-child(1){width:10%}tr td:nth-child(2){width:9%}tr td:nth-child(3){width:13%}tr td:nth-child(4){width:9%}tr td:nth-child(5){width:10%}tr td:nth-child(6){width:11%}tr td:nth-child(7){width:9%}tr td:nth-child(8){width:8%}tr td:nth-child(9){}</style>" +
						"<table><tr><td>发生日期</td><td>业务类型</td><td>业务发生原因</td><td>汇缴月份</td><td>发生金额</td><td>当前余额</td><td>银行利息</td><td>单位账号</td><td>单位名称</td></tr>";
					for(var i = (17 + ($scope.basicPage - 1) * 30); i < (17 + $scope.basicPage * 30); i++) {
						if(i < $scope.basicDetailInfo.length) {
							str = "<tr><td>" + $scope.basicDetailInfo[i].jtime +
								"</td><td>" + $scope.basicDetailInfo[i].jsummary + "</td><td>" + changeTrim($scope.basicDetailInfo[i].jreason_code) +
								"</td><td>" + changeTrim($scope.basicDetailInfo[i].pay_month) + "</td><td>" + $scope.basicDetailInfo[i].jsurplus_hp +
								"</td><td>" + $scope.basicDetailInfo[i].jcur_surplus + "</td><td>" + $scope.basicDetailInfo[i].jyhlx +
								"</td><td>" + $scope.basicDetailInfo[i].junit_code + "</td><td>" + $scope.basicDetailInfo[i].junit_name + "</td></tr>";
							tableList[j - 1] = tableList[j - 1] + str;
						}
					}
					tableList[j - 1] = tableList[j - 1] + "</table>";
				}
			}

			$scope.imgBase64 = $.getConfigMsg.preUrl + "/aci/selfWorkBench/infoSearch/image/logo.png";
			//console.log($scope.imgBase64)
			var div = '<div style="width: 700px;justify-content: center;display: flex;">' +
				'<img style="width:30px;height:30px;" src="' + $scope.imgBase64 + '"/>' +
				'<h1 style="font-size: 12px;">个人住房公积金查询单</h1></div>';
			var addxinxi = '<p style="width: 680px;font-size: 13px;"><span style="float: left;">渠道：“一网通办”自助终端</span>' +
				'<span style="margin-left: 40px;">网点:' + ($scope.qutletsMsg || "临汾路街道社区事务受理服务中心") + '</span>' +
				'<span style="float: right;">打印日期:' + date.getFullYear() + month + date.getDate() + '</span></p>';
			var addxinxi2 = '<p style="width: 680px;font-size: 13px;"><span style="float: left;">公积金类型：住房公积金</span>' +
				'<span style="margin-left: 66px;">查询时间段：' + $scope.startDate + "至" + $scope.endDate + '</span>';
			var addxinxi3 = '<span style="float: right;">第1页共:' + $scope.page + "页" + '</span></p>';
			var zijindogntai = '<p style="width: 700px;text-align: center;">资金动态明细</p>';
			var xxx = '<p style="font-size: 13px;position: absolute;bottom: 20px;left: 30px;">*本查询仅做信息查询使用</p>';
			var htmls = '<html><head><meta charset="UTF-8"></head><body style="margin: 10px 0px 0px 45px;">' + div + addxinxi + addxinxi2 + addxinxi3 + table1 + zijindogntai + table2 + xxx + '</body></html>';
			//LODOP.ADD_PRINT_IMAGE(22, 295, 35, 35, "<img style='width:30px;height:30px;' border='0' src='" + $scope.imgBase64 + "' />");
			//LODOP.ADD_PRINT_TEXT(32, 335, 184, 29, "个人住房公积金查询单");
			//LODOP.SET_PRINT_STYLEA(0, "FontSize", 12);
			//LODOP.SET_PRINT_STYLEA(0, "Bold", 1);
			//LODOP.ADD_PRINT_TEXT(100, 55, 188, 20, "渠道：“一网通办”自助终端");
			//LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			//LODOP.ADD_PRINT_TEXT(100, 312, 262, 20, "网点：" + (jQuery.getConfigMsg.qutletsVal || "临汾路街道社区事务受理服务中心"));
			//LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			//LODOP.ADD_PRINT_TEXT(121, 55, 188, 20, "公积金类型：住房公积金");
			//LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			//LODOP.ADD_PRINT_TEXT(100, 576, 141, 20, "打印日期：" + date.getFullYear() + month + date.getDate());
			//LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			//LODOP.ADD_PRINT_TEXT(120, 312, 231, 20, "查询时间段：" + $scope.startDate + "至" + $scope.endDate);
			//LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			//LODOP.ADD_PRINT_TEXT(120, 576, 100, 20, "第1页共" + $scope.page + "页");
			//LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			//LODOP.ADD_PRINT_TABLE(140, 30, 1300, 200, table1);
			//LODOP.ADD_PRINT_TEXT(336, 352, 200, 20, "资金动态明细");
			//LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			//LODOP.ADD_PRINT_TABLE(362, 30, 1300, 1400, table2);
			//LODOP.ADD_PRINT_TEXT(1000, 55, 200, 20, "*本查询仅做信息查询使用");
			//LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			if(tableList != undefined) {
				if(tableList.length > 0) {
					$scope.basicPageSum = tableList.length;
					for(var i = 0; i < tableList.length; i++) {
						addxinxi3 = '<span style="float: right;">' + "第" + (i + 2) + "页共" + $scope.page + "页" + '</span></p>';
						var htmls2 = '<html><head><meta charset="UTF-8"></head><body style="margin: 10px 0px 0px 45px;">' + div + addxinxi + addxinxi2 + addxinxi3 + table1 + zijindogntai + tableList[i] + xxx + '</body></html>';
						setTimeout(function() {
							$.device.printerHtml(htmls2);
						}, 1100);
						//LODOP.NewPage();
						//LODOP.ADD_PRINT_TEXT(80, 576, 100, 20, "第" + (i + 2) + "页共" + $scope.page + "页");
						//LODOP.ADD_PRINT_TABLE(100, 30, 1300, 1400, tableList[i]);
						//LODOP.ADD_PRINT_TEXT(1000, 55, 200, 20, "*本查询仅做信息查询使用");
						//LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
					}
				}
			}
			console.log($scope.supplementDetailInfo.length);
			setTimeout(function() {
				$.device.printerHtml(htmls);
			}, 1000);
			if($scope.supplementDetailInfo.length <= 1) {
				//LODOP.PRINT();
			} else {
				print2();
			}
		}
		print1();
		$timeout(function() {
			saveDeviceStatus("A4Printer", 0, "正常", 0, 0, 0, $scope.page);
			//模块使用记录
			$scope.jsonStr = {
				SUCCESS: "true",
				data: {
					name: '个人住房公积金信息查询',
				}
			}
			recordUsingHistory('住建委服务', '打印', '个人住房公积金信息查询', $scope.licenseName, $scope.licenseNumber, '', '', JSON.stringify($scope.jsonStr));
			//行为分析(查询)
			trackEventForQuery("个人住房公积金信息查询", "", "打印", "上海市住房城乡建设管理委", appData.licenseName, appData.licenseNumber, "");
			$state.go("main");
		}, 8000);
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$scope.type = "halfYear";
		$scope.accumulationFundInfo();
	}
	$scope.alertCancel = function() {
		$scope.isAlert = false;
		$scope.type = "year";
		$scope.accumulationFundInfo();
	}
});