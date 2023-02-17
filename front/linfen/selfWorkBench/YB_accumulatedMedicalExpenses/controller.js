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
app.controller('loginType', function($state, $scope, appData, $http) {
	removeAnimate($('.scrollBox2'));
	$scope.operation = appData.operation;
	appData.a = 1;
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	addAnimate($('.scrollBox2'))
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		appData.SwipeType ='sbCard';
		$state.go('login');
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory, $timeout, $rootScope) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.isLoding = true;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$.state.go("main");
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
		case "sscard":
			$scope.operation = "社保卡登录";
			break;
	}
	//跳转页面
	$scope.nextStep = function() {
		$scope.tokenType = "token";
		$scope.token = function() {
			$state.go("info");
		}
	}
	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.licenseName = info.Name;
			appData.licenseNumber = info.Number;
		} else {
			layer.msg("很抱歉,没有获取到您的信息,请重试")
		}
	}

	$scope.getResult = function(img) {
		$scope.img = img;
		appData.img = img;
		$state.go("info");
	}

	$scope.prevStep = function() {
		$.device.Face_Close();
	}
	$scope.citizenLogin = function(info) {
		if(info) {
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
	}
	$scope.sscardLogin = function(info) {
		if(info) {
			if(info.Ssn != "" && info.Ssn != null && info.Ssn != undefined) {
				appData.licenseNumber = info.Ssn;
				appData.licenseName = info.PeopleName;
				appData.CardNo = info.CardNo;
				$.device.ssCardClose();
				$scope.nextStep();
			} else {
				$scope.isAlert = true;
				$scope.msg = "未读取到您的社保卡信息,请重试";
			}
		} else {
			layer.msg("没有获取到")
		}
	}
})
app.controller("info", function($scope, $state, appData, $sce, appFactory) {
	$scope.caption = "查询结果"
	$scope.searchType = ["总累计", "门急诊", "住院", "急诊观察室", "门诊大病", "家庭病床", "购药"];
	$scope.funName = appData.funName;
	var date = new Date();
	$scope.currentTime = date;
	$scope.year = date.getFullYear();
	$scope.isLoding = false;
	$scope.concel = "false";
	var info = []; // 记录拿到的所有数据
	$scope.isAlert = false;
	$scope.show = true;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go('loginType');
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}
	appFactory.pro_fetch(appData.licenseNumber, appData.licenseName, appData.token, function(data) {
		if (data == undefined) {
			$scope.isAlert = true;
			$scope.msg = "未查询到信息 ，请重试"
		} else {			
			appData.zhh = data;
		}
		$scope.queryMedicalDetails();
	}, function(err) {
		$scope.isAlert = true;
		$scope.msg = "未查询到信息 ，请重试"
	});
	
	$scope.queryMedicalDetails = function(type) {
		$scope.isLoding = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/medicalInsurance/urbanInsurance.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				zhh: appData.zhh,
				ndbz: 0,
				ybbf: 0,
				mkbz: "000"
			},
			success: function(dataJson) {
				console.log(dataJson);
				info = dataJson[0];
				appData.flzf_lj = parseFloat(info.mjz_flzf_lj)+parseFloat(info.zy_flzf_lj)+parseFloat(info.jg_flzf_lj)+parseFloat(info.db_flzf_lj)+parseFloat(info.jc_flzf_lj)+parseFloat(info.gy_flzf_lj);
				console.log(appData.flzf_lj);
				$scope.getMatterCon(0,"总累计");
				$scope.isLoding = false;
			},
			error: function(err) {
				$scope.isLoding = false;
				$scope.isAlert = true;
				$scope.msg = "未查询到您的信息";
				console.log(err);
			}
		});
	}
//	$scope.queryMedicalDetails();
	// 选择项
	$scope.getMatterCon = function(index, type) {
		$scope.current = index;
		switch(type) {
			case "总累计":
				console.log(info.dnzh_lj)
				$scope.Item = [
					{"firstName":"当年账户支付累计","firstValue":info.dnzh_lj,"secondName":"历年账户支付累计","secondValue":info.lnzh_lj},
					{"firstName":"现金支付累计","firstValue":info.xj_lj,"secondName":"分类自付累计","secondValue":appData.flzf_lj},
					{"firstName":"统筹支付累计","firstValue":info.tc_lj,"secondName":"附加支付累计","secondValue":info.fj_lj},
					{"firstName":"起付线上封顶线下累计","firstValue":info.qfxs_fdxx_fy_lj,"secondName":"----","secondValue":""},
				]
				break;
			case "门急诊":
				$scope.Item = [
					{"firstName":"当年账户支付累计","firstValue":info.mjz_dnzh_lj,"secondName":"历年账户支付累计","secondValue":info.mjz_lnzh_lj},
					{"firstName":"自负段现金支付","firstValue":info.mjz_zfdxj_lj,"secondName":"附加段现金支付","secondValue":info.mjz_fjdxj_lj},
					{"firstName":"附加支付","firstValue":info.mjz_fj_lj,"secondName":"统筹支付","secondValue":info.mjz_tc_lj},
					{"firstName":"分类自负","firstValue":info.mjz_flzf_lj,"secondName":"----","secondValue":""},
				]
				break;
			case "住院":
				$scope.Item = [
					{"firstName":"起付段历年账户支付","firstValue":info.zy_qfdlnzh_lj,"secondName":"起付段现金支付","secondValue":info.zy_qfdxj_lj},
					{"firstName":"统筹段历年账户支付","firstValue":info.zy_tcdlnzh_lj,"secondName":"统筹段现金支付","secondValue":info.zy_tcdxj_lj},
					{"firstName":"统筹支付","firstValue":info.zy_tc_lj,"secondName":"附加段历年账户支付","secondValue":info.zy_fjdlnzh_lj},
					{"firstName":"附加段现金支付","firstValue":info.zy_fjdxj_lj,"secondName":"附加支付","secondValue":info.zy_fj_lj},
					{"firstName":"分类自负","firstValue":info.zy_flzf_lj,"secondName":"----","secondValue":""},
				]
				break;
			case "急诊观察室":
				$scope.Item = [
					{"firstName":"起付段历年账户支付","firstValue":info.jg_qfdlnzh_lj,"secondName":"起付段现金支付","secondValue":info.jg_qfdxj_lj},
					{"firstName":"统筹段历年账户支付","firstValue":info.jg_tcdlnzh_lj,"secondName":"统筹段现金支付","secondValue":info.jg_tcdxj_lj},
					{"firstName":"统筹支付","firstValue":info.jg_tc_lj,"secondName":"附加段历年账户支付","secondValue":info.jg_fjdlnzh_lj},
					{"firstName":"附加段现金支付","firstValue":info.jg_fjdxj_lj,"secondName":"附加支付","secondValue":info.jg_fj_lj},
					{"firstName":"分类自负","firstValue":info.jg_flzf_lj,"secondName":"----","secondValue":""},
				]
				break;
			case "门诊大病":
				$scope.Item = [
					{"firstName":"统筹段历年账户支付","firstValue":info.db_tcdlnzh_lj,"secondName":"统筹段现金支付","secondValue":info.db_tcdxj_lj},
					{"firstName":"统筹支付","firstValue":info.db_tc_lj,"secondName":"附加段历年账户支付","secondValue":info.db_fjdlnzh_lj},
					{"firstName":"附加段现金支付","firstValue":info.db_fjdxj_lj,"secondName":"附加支付","secondValue":info.db_fj_lj},
					{"firstName":"分类自负","firstValue":info.db_flzf_lj,"secondName":"----","secondValue":""},
				]
				break;
			case "家庭病床":
				$scope.Item = [
					{"firstName":"统筹段历年账户支付","firstValue":info.jc_tcdlnzh_lj,"secondName":"统筹段现金支付","secondValue":info.jc_tcdxj_lj},
					{"firstName":"统筹支付","firstValue":info.jc_tc_lj,"secondName":"附加段历年账户支付","secondValue":info.jc_fjdlnzh_lj},
					{"firstName":"附加段现金支付","firstValue":info.jc_fjdxj_lj,"secondName":"附加支付","secondValue":info.jc_fj_lj},
					{"firstName":"分类自负","firstValue":info.jc_flzf_lj,"secondName":"----","secondValue":""},
				]
				break;
			case "购药":
				$scope.Item = [
					{"firstName":"当年账户支付","firstValue":info.yd_dnzh_lj,"secondName":"历年账户支付","secondValue":info.yd_lnzh_lj},
					{"firstName":"分类自负","firstValue":info.gy_flzf_lj,"secondName":"----","secondValue":""},
				]
				break;
			default:
		}
	};
//	$scope.getMatterCon(0,"总累计");
	
	
	//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: '年度累计医疗费用信息',
		}
	}
	recordUsingHistory('医保服务', '查询', '年度累计医疗费用信息', appData.licenseName, appData.licenseNumber, '', '', JSON.stringify($scope.jsonStr));
	trackEventForQuery('年度累计医疗费用信息','','查询','上海市医疗保障局',appData.licenseName,appData.licenseNumber,'');
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
		$.device.GoHome();
	}
});