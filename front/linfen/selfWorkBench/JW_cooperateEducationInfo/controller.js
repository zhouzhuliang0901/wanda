app.controller("choose", function($scope, $state, appData, $sce, $location) {
	$scope.funName = appData.funName = "中外合作办学信息查询";
	$scope.isLoading = false;
	appData.bankName = '';
	$scope.concel = 'false';
	$scope.querySchoolsInfo = function(schoolName) {
		$scope.isLoading = true;
		if(schoolName == undefined || schoolName == '') {
			$scope.isAlert = true;
			$scope.msg = "请输入机构名称";
			return;
		}
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/educationCommission/queryCooperationSchool.do",
			dataType: "json",
			data: {
				name: encodeURI(schoolName),
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				if(dataJson.data.total == 0) {
					$scope.isAlert = true;
					$scope.msg = "暂无任何数据";
				} else {
					$scope.queryList = dataJson.data.list;
				}
				console.log(dataJson);
			},
			error: function(err) {
				$scope.isLoading = false;
				console.log(err);
			}
		});
		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: appData.funName,
			}
		}
		recordUsingHistory('教委服务', '查询', appData.funName, "", "", '', '', JSON.stringify($scope.jsonStr));
		//行为分析(查询)
		trackEventForQuery(appData.funName, "", "查询", "上海市教育委员会", "", "", "");
	}
	$scope.querySchoolsInfo('学');

	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}

	$scope.currentSchoolInfo = function(name) {
			appData.currentName = name;
		if(name.length >= 15) {
			appData.currentName = name.substring(0,15)+"...";
		}
		$location.path("/detailInfo")
	};

	$scope.prevStep = function() {
		if($scope.isDetial) {
			window.location.href = "../JW_allItem/index.html"
		} else {
			$scope.isDetial = true;
			$scope.funName = "民办教育机构查询";
		}
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});
app.controller("detailInfo", function($scope, $state, appData, $sce, $location) {
	$scope.funName = appData.currentName + "---详细信息";
	$scope.isLoading = false;
	$scope.concel = 'false';
	$scope.isDetial = true;
	let info = [];
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	
	$scope.currentSchoolInfo = function(name) {
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/educationCommission/queryCooperationSchool.do",
			dataType: "json",
			data: {
				name: encodeURI(name),
			},
			success: function(dataJson) {
				console.log(dataJson)
				$scope.isLoading = false;
				info = dataJson.data.list[0];
				$scope.Item = [
								{"firstName":"机构名称","firstValue":info.JGMC,"secondName":"法人单位登记机关","secondValue":info.FRDWDJJG},
								{"firstName":"法定办学注册地址","firstValue":info.FDBXZCDZ,"secondName":"邮编","secondValue":info.YB},
								{"firstName":"网址","firstValue":info.WZ,"secondName":"法定代表人","secondValue":info.FDFBR},
								{"firstName":"身份证号码","firstValue":info.SFZHM,"secondName":"联系电话","secondValue":info.LXDH},
								{"firstName":"电子邮件","firstValue":info.DZYJ,"secondName":"机构许可证编号","secondValue":info.JGXKZBH},
								{"firstName":"发证机关","firstValue":info.FZJG,"secondName":"证书有效期至","secondValue":info.ZSYXQZ},
								{"firstName":"办学规模","firstValue":info.BXGM,"secondName":"招生起止年份","secondValue":info.ZSQZNF},
								{"firstName":"机构属性","firstValue":info.JGSX,"secondName":"招生方式","secondValue":info.ZSFS},
								{"firstName":"国家地区","firstValue":info.GJDQ,"secondName":"校长或主要行政负责人","secondValue":info.XZ},
								{"firstName":"许可证备注","firstValue":info.XKZBZ,"secondName":"——","secondValue":"——"},
							]
			},
			error: function(err) {
				$scope.isLoading = false;
				console.log(err);
			}
		});
	};
	$scope.currentSchoolInfo(appData.currentName.substring(0,10));
	$scope.prevStep = function() {
		$location.path("/choose");
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});