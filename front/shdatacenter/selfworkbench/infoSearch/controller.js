app.controller("synthesizeMain", function($scope, $state, appData) {
	$scope.choice = function(type) {
		appData.type = type;
		console.log(appData.type);
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
		$state.go("main");
	}
});
app.controller('infoLogin', function($scope, $http, $state, appData, appFactory, $timeout) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.alertConfirm = function() {

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
	//$scope.goToApp();
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
});
app.controller("synthesizeIframe", function($scope, $state, $timeout, appData, $sce) {
	$scope.address = $sce.trustAsResourceUrl(appData.address);
	window.external.URL_OPEN(50, 160, 1800, 800, appData.address);
});
app.controller("print", function($scope, $state, $timeout, appData, $sce, $location) {
	appData.licenseName = localStorage.licenseName;
	appData.licenseNumber = localStorage.licenseNumber;
	$scope.isAlert = true;
	$scope.msg = "正在打印中。。。";
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$state.go("main");
		$scope.isAlert = false;
	}
	//
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
				name: appData.licenseName,
				identNo: appData.licenseNumber
			},
			success: function(dataJsonp) {
				if(dataJsonp.head.rst.buscode == "000000") {
					$scope.supplementDetailInfo = dataJsonp.body.datas;
				} else {
					$scope.msg = "未查询到信息";
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
				name: appData.licenseName,
				identNo: appData.licenseNumber
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
	//1、个人住房公积金帐户基本信息查询接口
	$scope.accumulationFundInfo = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/accumulationFund/accumulationFundInfo.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				name: appData.licenseName,
				identNo: appData.licenseNumber
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
		function dataChange(str) {
			var str1 = str.substring(0, 4) + "年" + str.substring(4, 6) + "月";
			return str1;
		}
		console.info($scope.basicInfo);
		console.info(dataChange($scope.basicInfo.jlast_pay_month));
		console.info($scope.basicDetailInfo);
		console.info($scope.supplementDetailInfo);
		var LODOP = $.device.printGetLodop();
		var date = new Date();
		var month = date.getMonth() + 1;

		function print1() {
			var table1 = "<style>table {font-size: 13px;border-collapse: collapse;border-bottom: 1px solid;border-right: 1px solid;text-align: center;width: 700px;}table td {height:30px;border-left: 1px solid;border-top: 1px solid;}</style>" +
				"<table><tr><td>个人账号</td><td>姓名</td><td>身份证号</td><td>开户日期</td><td>销户日期</td><td>账户状态</td></tr>" +
				"<tr><td>" + $scope.basicInfo.pri_account + "</td><td>" + $scope.basicInfo.name + "</td><td>" + $scope.basicInfo.id_card_num + "</td><td>" + dataChange($scope.basicInfo.jopen_date) + "</td><td>" + $scope.basicInfo.jclose_date + "</td><td>" + $scope.basicInfo.jstate + "</td></tr>" +
				"<tr><td>当前余额</td><td>末次汇缴月份</td><td>缴存网点</td><td>累计缴存</td><td>累计支取</td><td>当前月缴额</td></tr>" +
				"<tr><td>" + $scope.basicInfo.jsurplus + "</td><td>" + dataChange($scope.basicInfo.jlast_pay_month) + "</td><td>" + $scope.basicInfo.brunch_name + "</td><td>" + $scope.basicInfo.jtotal_pay + "</td><td>" + $scope.basicInfo.jtotal_draw + "</td><td>" + $scope.basicInfo.jmonth_pay + "</td</tr><tr><td colspan='2'>当前单位账号</td><td colspan='4'>当前所在单位名称</td></tr><tr><td colspan='2'>" + $scope.basicInfo.unit_code + "</td><td colspan='4'>" + $scope.basicInfo.unit_name + "</td></tr></table>";
			var table2 = "<style>table {font-size: 13px;border-collapse: collapse;border-bottom: 1px solid;border-right: 1px solid;text-align: center;width: 700px;}table td {height:30px;border-left: 1px solid;border-top: 1px solid;}tr td:nth-child(1),tr td:nth-child(4){width:72px;}</style>" +
				"<table><tr><td>发生日期</td><td>业务类型</td><td>业务发生原因</td><td>汇缴月份</td><td>发生金额</td><td>当前余额</td><td>银行利息</td><td>单位账号</td><td>单位名称</td></tr>";
			var str = "";
			for(var i = 0; i < $scope.basicDetailInfo.length; i++) {
				str = "<tr><td>" + dataChange($scope.basicDetailInfo[i].jtime) +
					"</td><td>" + $scope.basicDetailInfo[i].jsummary + "</td><td>" + $scope.basicDetailInfo[i].jreason_code +
					"</td><td>" + dataChange($scope.basicDetailInfo[i].pay_month) + "</td><td>" + $scope.basicDetailInfo[i].jsurplus_hp +
					"</td><td>" + $scope.basicDetailInfo[i].jcur_surplus + "</td><td>" + $scope.basicDetailInfo[i].jyhlx +
					"</td><td>" + $scope.basicDetailInfo[i].junit_code + "</td><td>" + $scope.basicDetailInfo[i].junit_name + "</td></tr>";
				table2 = table2 + str;
			}
			table2 = table2 + "</table>";
			console.info(table2);
			LODOP.ADD_PRINT_TEXT(32, 250, 430, 50, "个人住房公积金查询单");
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 20);
			LODOP.ADD_PRINT_TEXT(100, 88, 186, 20, "公积金类型：住房公积金");
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(121, 88, 188, 20, "网点：" + $scope.basicInfo.brunch_name);
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(100, 433, 269, 20, "查询时间段：" + dataChange($scope.basicDetailInfo[0].jtime) + "至" + dataChange($scope.basicDetailInfo[12].jtime));
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(120, 433, 231, 20, "打印日期：" + date.getFullYear() + "年" + month + "月" + date.getDate() + "日");
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TABLE(140, 30, 1300, 200, table1);
			LODOP.ADD_PRINT_TEXT(350, 326, 200, 30, "资金动态明细");
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 15);
			LODOP.ADD_PRINT_TABLE(390, 30, 1300, 1400, table2);
		}

		function print2() {
			var table1 = "<style>table {font-size: 13px;border-collapse: collapse;border-bottom: 1px solid;border-right: 1px solid;text-align: center;width: 700px;}table td {height:30px;border-left: 1px solid;border-top: 1px solid;}</style>" +
				"<table><tr><td>个人账号</td><td>姓名</td><td>身份证号</td><td>开户日期</td><td>销户日期</td><td>账户状态</td></tr>" +
				"<tr><td>" + $scope.basicInfo.bpri_account + "</td><td>" + $scope.basicInfo.name + "</td><td>" + $scope.basicInfo.id_card_num + "</td><td>" + dataChange($scope.basicInfo.bopen_date) + "</td><td>" + $scope.basicInfo.bclose_date + "</td><td>" + $scope.basicInfo.bstate + "</td></tr>" +
				"<tr><td>当前余额</td><td>末次汇缴月份</td><td>缴存网点</td><td>累计缴存</td><td>累计支取</td><td>当前月缴额</td></tr>" +
				"<tr><td>" + $scope.basicInfo.bsurplus + "</td><td>" + dataChange($scope.basicInfo.blast_pay_month) + "</td><td>" + $scope.basicInfo.bcbrunch_name + "</td><td>" + $scope.basicInfo.btotal_pay + "</td><td>" + $scope.basicInfo.btotal_draw + "</td><td>" + $scope.basicInfo.bmonth_pay + "</td</tr><tr><td colspan='2'>当前单位账号</td><td colspan='4'>当前所在单位名称</td></tr><tr><td colspan='2'>" + $scope.basicInfo.bunit_code + "</td><td colspan='4'>" + $scope.basicInfo.bunit_name + "</td></tr></table>";
			var table2 = "<style>table {font-size: 13px;border-collapse: collapse;border-bottom: 1px solid;border-right: 1px solid;text-align: center;width: 700px;}table td {height:30px;border-left: 1px solid;border-top: 1px solid;}tr td:nth-child(1),tr td:nth-child(4){width:72px;}</style>" +
				"<table><tr><td>发生日期</td><td>业务类型</td><td>业务发生原因</td><td>汇缴月份</td><td>发生金额</td><td>当前余额</td><td>银行利息</td><td>单位账号</td><td>单位名称</td></tr>";
			var str = "";
			for(var i = 0; i < $scope.supplementDetailInfo.length; i++) {
				str = "<tr><td>" + dataChange($scope.supplementDetailInfo[i].btime) +
					"</td><td>" + $scope.supplementDetailInfo[i].bsummary + "</td><td>" + $scope.supplementDetailInfo[i].breason_code +
					"</td><td>" + dataChange($scope.supplementDetailInfo[i].pay_month) + "</td><td>" + $scope.supplementDetailInfo[i].bsurplus_hp +
					"</td><td>" + $scope.supplementDetailInfo[i].bcur_surplus + "</td><td>" + $scope.supplementDetailInfo[i].byhlx +
					"</td><td>" + $scope.supplementDetailInfo[i].bunit_code + "</td><td>" + $scope.supplementDetailInfo[i].bunit_name + "</td></tr>";
				table2 = table2 + str;
			}
			table2 = table2 + "</table>";
			console.info(table2);
			LODOP.NewPage();
			LODOP.ADD_PRINT_TEXT(32, 250, 430, 50, "个人住房公积金查询单");
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 20);
			LODOP.ADD_PRINT_TEXT(100, 88, 186, 20, "公积金类型：补充住房公积金");
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(121, 88, 188, 20, "网点：" + $scope.basicInfo.bcbrunch_name);
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(100, 433, 269, 20, "查询时间段：" + dataChange($scope.supplementDetailInfo[0].btime) + "至" + dataChange($scope.supplementDetailInfo[12].btime));
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(120, 433, 231, 20, "打印日期：" + date.getFullYear() + "年" + month + "月" + date.getDate() + "日");
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TABLE(140, 30, 1300, 200, table1);
			LODOP.ADD_PRINT_TEXT(350, 326, 200, 30, "资金动态明细");
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 15);
			LODOP.ADD_PRINT_TABLE(390, 30, 1300, 1400, table2);
		}
		print1();
		print2();
		LODOP.PRINT();
	}
	$scope.accumulationFundInfo();
	$timeout(function() {
		$scope.print();
		$timeout(function() {
			$.device.GoHome();
		}, 3000);
	}, 2000)
});