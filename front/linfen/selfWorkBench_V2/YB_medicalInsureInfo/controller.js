app.controller('main', function($state, $scope, appData, $location) {
	$scope.operation = "请选择登录方式";
	$scope.funName = appData.funName = "医保个人信息查询";
	//显示社保卡登录选项
	$scope.ShowSscard = jQuery.getConfigMsg.isShowSscard;
	if (appData.type == "" || appData.type == undefined) {
		appData.type = $location.search().type;
	}
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		appData.SwipeType = "sbCard";
		$state.go("login");
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory) {
	$scope.funName = appData.funName;
	$scope.isAlert = false;
	$scope.msg = "";
	$scope.nextLink = ""; // 下一步标识符
	if (appData.loginType == 'idcard') {
		$scope.operation = "身份证登录";
	} else if (appData.loginType == 'cloud') {
		$scope.operation = "随申办登录";
	} else if (appData.loginType == 'medical') {
		$scope.operation = "社保卡登录";
	}
	$scope.loginType = appData.loginType;
	$scope.nextStep = function() {
		$scope.tokenType = "token";
		$scope.token = function() {
			$state.go("infoChoose");
		}
	};
	
	$scope.idcardLogin = function(info, images) {
		if (info) {
			$scope.faceImage = images;
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
			$scope.loginType = 'recognition';
		} else {
			layer.msg("很抱歉，未获取到相关信息，请重试。");
		}
	}
	
	$scope.sscardLogin = function(info) {
		if (info) {
			// 存储社保卡信息
			appData.licenseNumber = info.Ssn;
			appData.licenseName = info.PeopleName;
			$.device.ssCardClose();
			$scope.nextStep();
		} else {
			layer.msg("很抱歉，未获取到相关信息，请重试。")
		}
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.alertCancel = function() {
		$scope.isAlert = false;
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		$state.go("infoChoose");
	}
	$scope.prevStep = function() {
		$state.go("main");
	}

	$scope.citizenLogin = function(info) {
		function ClearBr(key) {
			key = key.replace(/\+/g, "-");
			key = key.replace(/\#/g, ",");
			return key;
		}
		if (appData.qrCodeType == "suishenma") {
			appData.licenseName = info.zwdtsw_name;
			appData.licenseNumber = info.zwdtsw_cert_id;
			appData.zwdtsw_user_id = info.zwdtsw_user_id;
			appData.zwdtsw_link_phone = info.zwdtsw_link_phone;
			appData.encrypt_identity = ClearBr(info.encrypt_identity);
			$scope.nextStep();
		} else {
			var idcardInfo = info.result.data;
			appData.licenseName = idcardInfo.realname;
			appData.licenseNumber = idcardInfo.idcard;
			appData.VALIDENDDAY = idcardInfo.VALIDENDDAY;
			appData.VALIDSTARTDAY = idcardInfo.VALIDSTARTDAY;
			$scope.nextStep();
		}
	}
});


//个人信息查询 ---选择项
app.controller('infoChoose', function($scope, $state, appData, $sce) {
	var date = new Date();
	var tMonth = date.getMonth() + 1;
	var tYear = date.getFullYear();
	if (tMonth > 6) {
		$scope.year = tYear;
	} else {
		$scope.year = tYear - 1;
	}
	$scope.show = false;
	PublicchoiceById('ybbf');
	PublicchoiceById('year');
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.count = "请输入";
	$('#ybbf a').eq(0).addClass('in');
	$('#year a').eq(0).addClass('in');
	$scope.keybordArr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 'X', 0, '删除'];
	$scope.keyboardInput = function(e) { //键盘输入
		var keycode = window.event ? e.keyCode : e.which;
		if (keycode >= 48 && keycode <= 57) {} else if (keycode == 8) {} else {
			console.log(e);
			$scope.count = $scope.count.substring(0, $scope.count.length - 1);
		}
	}
	$scope.phoneInput = function(e) { //键盘输入
		var keycode = window.event ? e.keyCode : e.which;
		if (keycode >= 48 && keycode <= 57) {} else if (keycode == 8) {} else {
			console.log(e);
			$scope.phone = $scope.phone.substring(0, $scope.phone.length - 1);
		}
	}
	$scope.inputNumber = function(item) { //软键盘输入
		if ($scope.count == "请输入") {
			$scope.count = "";
		} else if (item === '删除') {
			$scope.count = $scope.count.substring(0, $scope.count.length - 1);
		} else {
			$scope.count += item;
			console.log($scope.count);
		}
	}
	$scope.inputPhone = function(item) { //软键盘输入
		if ($scope.phone == "请输入") {
			$scope.phone = "";
		} else if (item === '删除') {
			$scope.phone = $scope.phone.substring(0, $scope.phone.length - 1);
		} else {
			$scope.phone += item;
			console.log($scope.phone);
		}
	}
	$scope.prevStep = function() {
		$state.go('main');
	}
	$scope.nextStep = function() {
		if ($('#ybbf a.in li').text() == undefined || $('#ybbf a.in li').text() == "") {
			$scope.isAlert = true;
			$scope.msg = "请选择医保办法";
			return;
		}
		if ($('#year a.in li').text() == undefined || $('#year a.in li').text() == "") {
			$scope.isAlert = true;
			$scope.msg = "请选择年份";
			return;
		}
		if ($scope.count == undefined || $scope.count == "") {
			$scope.isAlert = true;
			$scope.msg = "请输入手机号";
			return;
		}
		appData.infoyear = $('#year a.in li').text().split('年')[0];
		appData.ybbf = ($('#ybbf a.in li').text() == "职工保险") ? "0" : "1";
		appData.mobile = $scope.count;
		$state.go('info');
	}
});

//医保个人信息
app.controller("info", function($scope, $state, appData, $sce, appFactory) {
	//获取网点信息
	$scope.qutletsMsg = '';
	console.log(jQuery.getConfigMsg.districtAndCountyVal);
	$scope.nextShowname = jQuery.getConfigMsg.isYHShowHtmlPrintButton == true ? '1' : '0';
	$scope.GetConfigLoads = function() {
		$.config.load('Outlets', function(datas) {
			$scope.qutletsMsg = decodeURI(datas);
			console.log($scope.qutletsMsg);
		});
	}
	$scope.GetConfigLoads();
	$scope.nextText = "打印";
	$scope.isLoding = false;
	var date = new Date();
	var tMonth = date.getMonth() + 1;
	var tYear = date.getFullYear();
	if (tMonth > 6) {
		$scope.year = tYear;
	} else {
		$scope.year = tYear - 1;
	}
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go("infoChoose");
	}
	appFactory.pro_fetch(appData.licenseNumber, appData.licenseName, appData.token, function(data) {
		appData.zhh = data;
		appFactory.getOrganByAccount(appData.zhh, function(dataJson) {
			appData.dwbm = dataJson[0].dwbm;
			appData.dwmc = dataJson[0].dwmc;
			$scope.dwbm = appData.dwbm;
			$scope.dwmc = appData.dwmc;
		});
		$scope.medicalInfo();
	}, function(err) {
		$scope.isAlert = true;
		$scope.msg = "未查询到信息 ，请重试"
	});

	$scope.medicalInfo = function(zhh) {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/medicalInsurance/urbanInsurance.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				zhh: appData.zhh,
				ndbz: Math.abs(appData.infoyear - $scope.year),
				ybbf: appData.ybbf,
				mkbz: 3
			},
			success: function(dataJson) {
				console.log(dataJson[0]);
				var info = dataJson[0];
				$scope.isLoding = true;
				if (dataJson.length > 0) {
					$scope.xm = info.xm;
					$scope.xb = ((parseInt(appData.licenseNumber.substring(16, 17)) % 2) == 0) ?
						"女" : "男";
					$scope.zhh = info.zhh;
					$scope.sfzh = info.sfzh;
					$scope.zhzt = info.zhztsm;
					$scope.bjdx = info.zhbzsm[1];
					$scope.fcqk = info.zhbzsm[4];
					$scope.rylb = info.rylbsm;
					$scope.tsry = info.zhbzsm[9];
					$scope.gwyqk = info.zhbzsm[2];
					$scope.ztqk = info.zhbzsm[0];
					$scope.ybbf = info.zhbzsm[11];
					$scope.zrzhzjze = toDecimal2(info.zrzhzjze);
					$scope.dnzhye = toDecimal2(info.dnzhye);
					$scope.zhlx = toDecimal2(info.zhlx);
					$scope.lnzhye = toDecimal2(info.lnzhye);
					$scope.dnzh_lj = toDecimal2(info.dnzh_lj);
					$scope.lnzh_lj = toDecimal2(info.lnzh_lj);
					$scope.xj_lj = toDecimal2(info.xj_lj);
					$scope.fj_lj = toDecimal2(info.fj_lj);
					$scope.tc_lj = toDecimal2(info.tc_lj);
					$scope.qfxs_fdxx_fy_lj = toDecimal2(info.qfxs_fdxx_fy_lj);
					appData.tableInfo =
						"<style>table{font-size:14px;border-collapse: collapse; width:680px} table td{border:1px solid} table th{text-align:right;border:1px solid}</style><table>" +
						"<tr><th>注入资金总额：</th><td>" + toDecimal2(info.zrzhzjze) +
						"</td><th>账户利息：</th><td>" + toDecimal2(info.zhlx) + "</td></tr>" +
						"<tr><th>当年账户余额：</th><td>" + toDecimal2(info.dnzhye) +
						"</td><th>历年账户余额：</th><td>" + toDecimal2(info.lnzhye) + "</td></tr>" +
						"<tr><th>当年账户累计：</th><td>" + toDecimal2(info.dnzh_lj) +
						"</td><th>历年账户累计：</th><td>" + toDecimal2(info.lnzh_lj) + "</td></tr>" +
						"<tr><th>现金支付累计：</th><td>" + toDecimal2(info.xj_lj) +
						"</td><th>统筹支付累计：</th><td>" + toDecimal2(info.tc_lj) + "</td></tr>" +
						"<tr><th>地方附加支付累计：</th><td>" + toDecimal2(info.fj_lj) +
						"</td><th>起付线上封顶线下医疗费用累计：</th><td>" + toDecimal2(info.qfxs_fdxx_fy_lj) +
						"</td></tr>" +
						"<tr><th>门急诊当年账户支付累计：</th><td>" + toDecimal2(info.mjz_dnzh_lj) +
						"</td><th>商业保险历年账户支付金额：</th><td>" + toDecimal2(info.sybx_qnzh_zf) +
						"</td></tr>" +
						"<tr><th>门急诊及药店购药自负段累计：</th><td>" + toDecimal2(info.mjz_zfdxj_lj) +
						"</td><th>门急诊历年账户支付累计：</th><td>" + toDecimal2(info.mjz_lnzh_lj) +
						"</td></tr>" +
						"<tr><th>门急诊自负段历年账户支付累计：</th><td>" + toDecimal2(info.mjz_zfdlnzh_lj) +
						"</td><th>门急诊分类自负段累计：</th><td>" + toDecimal2(info.mjz_flzf_lj) +
						"</td></tr>" +
						"<tr><th>门急诊附加段现金累计：</th><td>" + toDecimal2(info.mjz_fjdxj_lj) +
						"</td><th>门急诊附加段历年账户支付累计：</th><td>" + toDecimal2(info.mjz_fjdlnzh_lj) +
						"</td></tr>" +
						"<tr><th>门急诊统筹支付累计：</th><td>" + toDecimal2(info.mjz_tc_lj) +
						"</td><th>门急诊附加支付累计：</th><td>" + toDecimal2(info.mjz_fj_lj) +
						"</td></tr>" +
						"<tr><th>门诊大病统筹段历年账户支付累计：</th><td>" + toDecimal2(info.db_tcdlnzh_lj) +
						"</td><th>门诊大病统筹段现金支付累计：</th><td>" + toDecimal2(info.db_tcdxj_lj) +
						"</td></tr>" +
						"<tr><th>门诊大病统筹支付累计：</th><td>" + toDecimal2(info.db_tc_lj) +
						"</td><th>门诊大病附加段历年账户支付累计：</th><td>" + toDecimal2(info.db_fjdlnzh_lj) +
						"</td></tr>" +
						"<tr><th>门诊大病附加段现金支付累计：</th><td>" + toDecimal2(info.db_fjdxj_lj) +
						"</td><th>门诊大病附加支付累计：</th><td>" + toDecimal2(info.db_fj_lj) +
						"</td></tr>" +
						"<tr><th>门诊大病分类自负累计：</th><td>" + toDecimal2(info.db_flzf_lj) +
						"</td><th>住院天数：</th><td>" + toDecimal2(info.zyts) + "</td></tr>" +
						"<tr><th>住院起付段历年账户支付累计：</th><td>" + toDecimal2(info.zy_qfdlnzh_lj) +
						"</td><th>家床起付段历年账户支付累计：</th><td>" + toDecimal2(info.jc_qfdlnzh_lj) +
						"</td></tr>" +
						"<tr><th>住院起付段现金支付累计：</th><td>" + toDecimal2(info.zy_qfdxj_lj) +
						"</td><th>家床起付段现金支付累计：</th><td>" + toDecimal2(info.jc_qfdxj_lj) +
						"</td></tr>" +
						"<tr><th>住院统筹段历年账户支付累计：</th><td>" + toDecimal2(info.zy_tcdlnzh_lj) +
						"</td><th>家床统筹段历年账户支付累计：</th><td>" + toDecimal2(info.jc_tcdlnzh_lj) +
						"</td></tr>" +
						"<tr><th>住院统筹段现金支付累计：</th><td>" + toDecimal2(info.zy_tcdxj_lj) +
						"</td><th>家床统筹段现金支付累计：</th><td>" + toDecimal2(info.jc_tcdxj_lj) +
						"</td></tr>" +
						"<tr><th>住院统筹支付累计：</th><td>" + toDecimal2(info.zy_tc_lj) +
						"</td><th>家床统筹支付累计：</th><td>" + toDecimal2(info.jc_tc_lj) +
						"</td></tr>" +
						"<tr><th>住院附加段历年账户支付累计：</th><td>" + toDecimal2(info.zy_fjdlnzh_lj) +
						"</td><th>家床附加段历年账户支付累计：</th><td>" + toDecimal2(info.jc_fjdlnzh_lj) +
						"</td></tr>" +
						"<tr><th>住院附加段现金支付累计：</th><td>" + toDecimal2(info.zy_fjdxj_lj) +
						"</td><th>家床附加段现金支付累计：</th><td>" + toDecimal2(info.jc_fjdxj_lj) +
						"</td></tr>" +
						"<tr><th>住院附加支付累计：</th><td>" + toDecimal2(info.zy_fj_lj) +
						"</td><th>家床附加支付累计：</th><td>" + toDecimal2(info.jc_fj_lj) +
						"</td></tr>" +
						"<tr><th>住院分类自负累计：</th><td>" + toDecimal2(info.zy_flzf_lj) +
						"</td><th>家床分类自负累计：</th><td>" + toDecimal2(info.jc_flzf_lj) +
						"</td></tr>" +
						"<tr><th>急观起付段历年账户支付累计：</th><td>" + toDecimal2(info.jg_qfdlnzh_lj) +
						"</td><th>药店购药当年账户支付累计：</th><td>" + toDecimal2(info.yd_dnzh_lj) +
						"</td></tr>" +
						"<tr><th>急观起付段现金支付累计：</th><td>" + toDecimal2(info.jg_qfdxj_lj) +
						"</td><th>药店购药历年账户支付累计：</th><td>" + toDecimal2(info.yd_lnzh_lj) +
						"</td></tr>" +
						"<tr><th>急观统筹段历年账户支付累计：</th><td>" + toDecimal2(info.jg_tcdlnzh_lj) +
						"</td><th>药店购药自负段现金支付累计：</th><td>" + toDecimal2(info.gy_zfdxj_lj) +
						"</td></tr>" +
						"<tr><th>急观统筹段现金支付累计：</th><td>" + toDecimal2(info.jg_tcdxj_lj) +
						"</td><th>药店购药分类自负累计：</th><td>" + toDecimal2(info.gy_flzf_lj) +
						"</td></tr>" +
						"<tr><th>急观统筹支付累计：</th><td>" + toDecimal2(info.jg_tc_lj) +
						"</td><th>高价药门急诊当年账户支付累计：</th><td>" + toDecimal2(info.gjy_mjz_dnzh_lj) +
						"</td></tr>" +
						"<tr><th>急观附加段历年账户支付累计：</th><td>" + toDecimal2(info.jg_fjdlnzh_lj) +
						"</td><th>高价药门急诊历年支付累计：</th><td>" + toDecimal2(info.gjy_mjz_lnzh_lj) +
						"</td></tr>" +
						"<tr><th>急观附加段现金支付累计：</th><td>" + toDecimal2(info.jg_fjdxj_lj) +
						"</td><th>高价药门急诊自负段历年账户支付累计：</th><td>" + toDecimal2(info
							.gjy_mjz_zfdlnzh_lj) + "</td></tr>" +
						"<tr><th>急观附加支付累计：</th><td>" + toDecimal2(info.jg_fj_lj) +
						"</td><th>高价药门急诊自负段现金支付累计：</th><td>" + toDecimal2(info
						.gjy_mjz_zfdxj_lj) + "</td></tr>" +
						"<tr><th>急观分类自负累计：</th><td>" + toDecimal2(info.jg_flzf_lj) +
						"</td><th>高价药门急诊附加段历年账户支付累计：</th><td>" + toDecimal2(info
							.gjy_mjz_fjdlnzh_lj) + "</td></tr>" +
						"<tr><th>高价药住院起付段历年账户支付累计：</th><td>" + toDecimal2(info
							.gjy_zy_qfdlnzh_lj) + "</td><th>高价药门急诊附加段现金支付累计：</th><td>" +
						toDecimal2(info.gjy_mjz_fjdxj_lj) + "</td></tr>" +
						"<tr><th>高价药住院起付段现金支付累计：</th><td>" + toDecimal2(info.gjy_zy_qfdxj_lj) +
						"</td><th>高价药门急诊附加累计：</th><td>" + toDecimal2(info.gjy_mjz_fj_lj) +
						"</td></tr>" +
						"<tr><th>高价药住院统筹段历年账户支付累计：</th><td>" + toDecimal2(info
							.gjy_zy_tcdlnzh_lj) + "</td><th>高价药门急诊统筹段现金支付累计：</th><td>" +
						toDecimal2(info.gjy_mjz_tcdxj_lj) + "</td></tr>" +
						"<tr><th>高价药住院统筹段现金支付累计：</th><td>" + toDecimal2(info.gjy_zy_tcdxj_lj) +
						"</td><th>高价药门急诊统筹累计：</th><td>" + toDecimal2(info.gjy_mjz_tc_lj) +
						"</td></tr>" +
						"<tr><th>高价药住院统筹累计：</th><td>" + toDecimal2(info.gjy_zy_tc_lj) +
						"</td><th>高价药住院附加段现金支付累计：</th><td>" + toDecimal2(info.gjy_zy_fjdxj_lj) +
						"</td></tr>" +
						"<tr><th>高价药住院附加段历年账户支付累计：</th><td>" + toDecimal2(info
							.gjy_zy_fjdlnzh_lj) + "</td><th>高价药住院附加累计：</th><td>" + toDecimal2(
							info.gjy_zy_fj_lj) + "</td></tr></table>";
				}

				//模块使用记录
				$scope.jsonStr = {
					SUCCESS: "true",
					data: {
						name: '医保个人信息查询',
					}
				}
				recordUsingHistory('医保服务', '查询+打印', '医保个人信息查询', appData.licenseName, appData
					.licenseNumber, '', '', JSON.stringify($scope.jsonStr));
				trackEventForQuery("医保个人信息查询", "", "打印", "上海市医疗保障局", appData.licenseName,
					appData.licenseNumber, "");
			},
			error: function(err) {
				$scope.isLoding = true;
				console.log(err);
			}
		});
	}

	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
	$scope.prevStep = function() {
		$state.go("main");
	}
	$scope.print = function() {
		$scope.isShowPrint = "show";
		setTimeout(function() {
			var LODOP = $.device.printGetLodop('');
			var date = new Date();
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			if (year == appData.infoyear) {
				LODOP.ADD_PRINT_TEXT(32, 250, 430, 50, "个人账户信息查询表(" + appData.infoyear + ")");
			} else {
				LODOP.ADD_PRINT_TEXT(32, 250, 430, 50, "个人历史账户信息查询表(" + appData.infoyear + ")");
			}
			LODOP.SET_PRINT_STYLE("BOLD", 1);
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 20);
			//		LODOP.ADD_PRINT_TEXT(83, 43, 154, 20, "卡号：" + $scope.zhh);
			//		LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(83, 43, 115, 20, "医保办法：" + $scope.ybbf);
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(83, 207, 142, 20, "姓名：" + $scope.xm);
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(83, 371, 226, 20, "身份证号：" + appData.licenseNumber);
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(83, 598, 111, 20, "人员类别：" + $scope.rylb);
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(105, 43, 154, 20, "卡状态：" + $scope.zhzt);
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(105, 207, 142, 20, "账户状态：" + $scope.zhzt);
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(105, 371, 221, 20, "保健对象：" + $scope.bjdx);
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(105, 597, 154, 20, "特殊人员：" + $scope.tsry);
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(125, 43, 154, 20, "封存情况：" + $scope.fcqk);
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			//			LODOP.ADD_PRINT_TEXT(125, 207, 162, 20, "公务员情况：" + $scope.gwyqk);
			//			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(125, 371, 221, 20, "职退情况：" + $scope.ztqk);
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(125, 597, 154, 20, "单位码：" + appData.dwbm);
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(145, 42, 427, 20, "单位名称：" + appData.dwmc);
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(905, 43, 200, 22, "区县：" + jQuery.getConfigMsg
			.districtAndCountyVal);
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(905, 260, 270, 22, "渠道：“一网通办”智能终端");
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(905, 530, 309, 22, "打印时间：" + date.getFullYear() + "年" + month +
				"月" + date.getDate() + "日");
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(930, 43, 427, 20, "网点:" + $scope.qutletsMsg);
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TEXT(1000, 55, 200, 20, "*本查询仅做信息查询使用");
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
			LODOP.ADD_PRINT_TABLE(170, 43, 1300, 1400, appData.tableInfo);
			LODOP.PRINT();
		}, 10);
		setTimeout(function() {
			$state.go("main");
		}, 5000);
	}
});
