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
//				$state.go("print");
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
	console.log(appData.type);
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
app.controller('infoLogin', function($scope, $http, $state, appData, appFactory, $timeout) {
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
			$scope.operation = "随申办";
			break;
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
		$state.go("apply");
		$scope.$apply();
	}
	$scope.citizenLogin = function(info) {
		var idcardInfo = info.result.data;
		appData.licenseName = idcardInfo.realname;
		appData.licenseNumber = idcardInfo.idcard;
		$state.go("apply");
	}
});
app.controller("apply", function($scope, $state, $timeout, appData, $sce, $http) {
	localStorage.licenseNumber = appData.licenseNumber;
	localStorage.licenseName = appData.licenseName;
	$scope.info = {
		type: "1",
		idCard: appData.licenseNumber,
		credit_code: "",
		ca_code: "",
		name: appData.licenseName
	}
	console.log($scope.info);
	var httpConfig = {
		jsonpCallback: "JSON_CALLBACK",
		data: encodeURIComponent(JSON.stringify($scope.info))
	}
	$scope.encryptDataByRSA = function() {
		$http.jsonp('http://hengshui.5uban.com/xhac/aci/workPlatform/util/encryptDataByRSA.do', {
			params: httpConfig
		}).success(function(dataJson) {
			console.log(dataJson.result);
			$scope.url = "https://zwdtuser.sh.gov.cn/uc/naturalUser/jumpUcRedirect.do?app_id=535984a5&data=" + encodeURIComponent(dataJson.result) + "&redirect_uri=";
			console.log($scope.url + encodeURIComponent(appData.url));
			$scope.address = "";
			switch(appData.type) {
				case "ylj":
					$scope.address = "https://zwdt.sh.gov.cn/smzy/shspace/loginsso/in?callback=https://zwdt.sh.gov.cn/smzy/shell_terminal/goveroment_ywtb/goldage_ywtb";
					break;
				case "gjj":
					$scope.address = "https://zwdt.sh.gov.cn/smzy/shspace/loginsso/in?callback=https://zwdt.sh.gov.cn/smzy/shell_terminal/goveroment_ywtb/gsss_ywtb";
					break;
				case "ybj":
					$scope.address = "https://zwdt.sh.gov.cn/smzy/shspace/loginsso/in?callback=https://zwdt.sh.gov.cn/smzy/shell_terminal/goveroment_ywtb/gmedicalInsurance_ywtb";
					break;
				case "jjxx":
					$scope.address = "https://zwdt.sh.gov.cn/smzy/shspace/loginsso/in?callback=https://zwdt.sh.gov.cn/smzy/shell_terminal/goveroment_ywtb/traffic";
					break;
			}
			console.log($scope.url + encodeURIComponent($scope.address));
			if($scope.address.indexOf("http") != -1) {
				window.location.href = $scope.url + $scope.address;
			} else {
				window.location.href = $scope.url + $scope.address;
			}
		}).error(function(err) {
			console.log('encryptDataByRSA err');
		});

	}
	$scope.encryptDataByRSA();
	$scope.prevStep = function() {
		$state.go('main');
	}
});
app.controller("synthesizeIframe", function($scope, $state, $timeout, appData, $sce) {
	$scope.address = $sce.trustAsResourceUrl(appData.address);
	window.external.URL_OPEN(200, 180, 1500, 700, appData.address);
	$scope.prevStep = function() {
		$state.go('main');
	}
});
app.controller("print", function($scope, $state, $timeout, appData, $sce, $location) {
	$scope.licenseName = localStorage.licenseName;
	$scope.licenseNumber =localStorage.licenseNumber;
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
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/accumulationFund/supplementAccumulationFundDetailInfo.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				name: $scope.licenseName,
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
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/accumulationFund/basicAccumulationFundDetailInfo.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				name: $scope.licenseName,
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
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/accumulationFund/accumulationFundInfo.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				name: $scope.licenseName,
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
		$scope.imgBase64 = $.getConfigMsg.preUrl+"/aci/selfWorkbench-Data/infoSearch/image/logo.png";

		function changeTrim(str) {
			str = str.trim();
			if(str == undefined || str == "") {
				return '—';
			} else {
				return str;
			}
		}
		var LODOP = $.device.printGetLodop();
		var date = new Date();
		var month = date.getMonth() + 1;

		function print2() {
			var table1 = "<style>table {font-size: 13px;border-collapse: collapse;border-bottom: 1px solid;border-right: 1px solid;text-align: center;width: 700px;}table td {height:30px;border-left: 1px solid;border-top: 1px solid;}</style>" +
				"<table><tr><td>个人账号</td><td>姓名</td><td>身份证号</td><td>开户日期</td><td>销户日期</td><td>账户状态</td></tr>" +
				"<tr><td>" + $scope.basicInfo.bpri_account + "</td><td>" + $scope.basicInfo.name + "</td><td>" + $scope.basicInfo.id_card_num + "</td><td>" + $scope.basicInfo.bopen_date + "</td><td>" + changeTrim($scope.basicInfo.bclose_date) + "</td><td>" + $scope.basicInfo.bstate + "</td></tr>" +
				"<tr><td>当前余额</td><td>末次汇缴月份</td><td>缴存网点</td><td>累计缴存</td><td>累计支取</td><td>当前月缴额</td></tr>" +
				"<tr><td>" + $scope.basicInfo.bsurplus + "</td><td>" + $scope.basicInfo.blast_pay_month + "</td><td>" + $scope.basicInfo.bcbrunch_name + "</td><td>" + $scope.basicInfo.btotal_pay + "</td><td>" + $scope.basicInfo.btotal_draw + "</td><td>" + $scope.basicInfo.bmonth_pay + "</td</tr><tr><td colspan='2'>当前单位账号</td><td colspan='4'>当前所在单位名称</td></tr><tr><td colspan='2'>" + $scope.basicInfo.bunit_code + "</td><td colspan='4'>" + $scope.basicInfo.bunit_name + "</td></tr></table>";
			var table2 = "<style>table {font-size: 13px;border-collapse: collapse;border-bottom: 1px solid;border-right: 1px solid;text-align: center;width: 700px;}table td {height:30px;border-left: 1px solid;border-top: 1px solid;}tr td:nth-child(1),tr td:nth-child(4){width:72px;}</style>" +
				"<table><tr><td>发生日期</td><td>业务类型</td><td>业务发生原因</td><td>汇缴月份</td><td>发生金额</td><td>当前余额</td><td>银行利息</td><td>单位账号</td><td>单位名称</td></tr>";
			var str = "";
			var length = $scope.supplementDetailInfo.length;
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
					tableList[j - 1] = "<style>table {font-size: 13px;border-collapse: collapse;border-bottom: 1px solid;border-right: 1px solid;text-align: center;width: 700px;}table td {height:30px;border-left: 1px solid;border-top: 1px solid;}tr td:nth-child(1),tr td:nth-child(4){width:72px;}</style>" +
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
			LODOP.NewPage();
			$scope.imgBase64 = $.getConfigMsg.preUrl+"/aci/selfWorkbench-Data/infoSearch/image/logo.png";
			LODOP.ADD_PRINT_IMAGE(22, 295, 35, 35, "<img style='width:30px;height:30px;' border='0' src='" + $scope.imgBase64 + "' />");
			LODOP.ADD_PRINT_TEXT(32, 335, 184, 29, "个人住房公积金查询单");
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 12);
			LODOP.SET_PRINT_STYLEA(0, "Bold", 1);
			LODOP.ADD_PRINT_TEXT(100, 55, 188, 20, "渠道：自助终端");
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(100, 312, 262, 20, "网点：临汾路街道社区事务受理服务中心");
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(121, 55, 188, 20, "公积金类型：补充住房公积金");
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(100, 576, 141, 20, "打印日期：" + date.getFullYear() + month + date.getDate());
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(120, 312, 231, 20, "查询时间段：" + $scope.startDate + "至" + $scope.endDate);
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(120, 576, 100, 20, "第" + ($scope.basicPageSum + 2) + "页共" + $scope.page + "页");
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TABLE(140, 30, 1300, 200, table1);
			LODOP.ADD_PRINT_TEXT(336, 352, 200, 20, "资金动态明细");
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TABLE(362, 30, 1300, 1400, table2);
			if(tableList != undefined) {
				if(tableList.length > 0) {
					for(var i = 0; i < tableList.length; i++) {
						LODOP.NewPage();
						LODOP.ADD_PRINT_TEXT(80, 576, 100, 20, "第" + ($scope.basicPageSum + i + 2) + "页共" + $scope.page + "页");
						LODOP.ADD_PRINT_TABLE(100, 30, 1300, 1400, tableList[i]);
					}
				}
			}
			LODOP.PRINT();
		}

		function print1() {
			var table1 = "<style>table {font-size: 13px;border-collapse: collapse;border-bottom: 1px solid;border-right: 1px solid;text-align: center;width: 700px;}table td {height:30px;border-left: 1px solid;border-top: 1px solid;}</style>" +
				"<table><tr><td>个人账号</td><td>姓名</td><td>身份证号</td><td>开户日期</td><td>销户日期</td><td>账户状态</td></tr>" +
				"<tr><td>" + $scope.basicInfo.pri_account + "</td><td>" + $scope.basicInfo.name + "</td><td>" + $scope.basicInfo.id_card_num + "</td><td>" + $scope.basicInfo.jopen_date + "</td><td>" + changeTrim($scope.basicInfo.jclose_date) + "</td><td>" + $scope.basicInfo.jstate + "</td></tr>" +
				"<tr><td>当前余额</td><td>末次汇缴月份</td><td>缴存网点</td><td>累计缴存</td><td>累计支取</td><td>当前月缴额</td></tr>" +
				"<tr><td>" + $scope.basicInfo.jsurplus + "</td><td>" + $scope.basicInfo.jlast_pay_month + "</td><td>" + $scope.basicInfo.brunch_name + "</td><td>" + $scope.basicInfo.jtotal_pay + "</td><td>" + $scope.basicInfo.jtotal_draw + "</td><td>" + $scope.basicInfo.jmonth_pay + "</td</tr><tr><td colspan='2'>当前单位账号</td><td colspan='4'>当前所在单位名称</td></tr><tr><td colspan='2'>" + $scope.basicInfo.unit_code + "</td><td colspan='4'>" + $scope.basicInfo.unit_name + "</td></tr></table>";
			var table2 = "<style>table {font-size: 13px;border-collapse: collapse;border-bottom: 1px solid;border-right: 1px solid;text-align: center;width: 700px;}table td {height:30px;border-left: 1px solid;border-top: 1px solid;}tr td:nth-child(1),tr td:nth-child(4){width:72px;}</style>" +
				"<table><tr><td>发生日期</td><td>业务类型</td><td>业务发生原因</td><td>汇缴月份</td><td>发生金额</td><td>当前余额</td><td>银行利息</td><td>单位账号</td><td>单位名称</td></tr>";
			var str = "";
			var table3 = "";
			var length = $scope.basicDetailInfo.length;
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
					tableList[j - 1] = "<style>table {font-size: 13px;border-collapse: collapse;border-bottom: 1px solid;border-right: 1px solid;text-align: center;width: 700px;}table td {height:30px;border-left: 1px solid;border-top: 1px solid;}tr td:nth-child(1),tr td:nth-child(4){width:72px;}</style>" +
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
			$scope.imgBase64 = $.getConfigMsg.preUrl+"/aci/selfWorkbench-Data/infoSearch/image/logo.png";
			LODOP.ADD_PRINT_IMAGE(22, 295, 35, 35, "<img style='width:30px;height:30px;' border='0' src='" + $scope.imgBase64 + "' />");
			LODOP.ADD_PRINT_TEXT(32, 335, 184, 29, "个人住房公积金查询单");
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 12);
			LODOP.SET_PRINT_STYLEA(0, "Bold", 1);
			LODOP.ADD_PRINT_TEXT(100, 55, 188, 20, "渠道：自助终端");
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(100, 312, 262, 20, "网点：临汾路街道社区事务受理服务中心");
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(121, 55, 188, 20, "公积金类型：住房公积金");
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(100, 576, 141, 20, "打印日期：" + date.getFullYear() + month + date.getDate());
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(120, 312, 231, 20, "查询时间段：" + $scope.startDate + "至" + $scope.endDate);
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(120, 576, 100, 20, "第1页共" + $scope.page + "页");
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TABLE(140, 30, 1300, 200, table1);
			LODOP.ADD_PRINT_TEXT(336, 352, 200, 20, "资金动态明细");
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TABLE(362, 30, 1300, 1400, table2);
			if(tableList != undefined) {
				if(tableList.length > 0) {
					$scope.basicPageSum = tableList.length;
					for(var i = 0; i < tableList.length; i++) {
						LODOP.NewPage();
						LODOP.ADD_PRINT_TEXT(80, 576, 100, 20, "第" + (i + 2) + "页共" + $scope.page + "页");
						LODOP.ADD_PRINT_TABLE(100, 30, 1300, 1400, tableList[i]);
					}
				}
			}
			if($scope.supplementDetailInfo.length <= 1) {
				LODOP.PRINT();
			} else {
				print2();
			}
		}
		print1();
		$timeout(function() {
			$state.go("main");
		}, 3000);
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