app.controller("choose", function($scope, $state, appData, $sce, $location) {
	$scope.funName = appData.funName = "民办非学历培训机构查询";
	$scope.isLoading = false;
	$scope.concel = 'false';
	$scope.querySchoolsInfo = function(schoolName) {
		if(schoolName == undefined || schoolName == '' || schoolName == null) {
			schoolName = '上海';
		}
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/educationCommission/queryTrainingInstitution.do",
//			url: "http://10.2.104.131:8080/ac-self/selfapi/educationCommission/queryTrainingInstitution.do",//10.2.104.131
			dataType: "json",
			data: {
				name: encodeURI(schoolName),
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				if(dataJson.code != 200) {
					$scope.isAlert = true;
					$scope.msg = "暂无任何数据";
				} else if(dataJson.data.total != 0){
					$scope.queryList = dataJson.data.list;
				} else {
					$scope.isAlert = true;
					$scope.msg = "暂无任何数据";
				}
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
	$scope.querySchoolsInfo();

	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}

	$scope.currentSchoolInfo = function(name) {
		appData.currentName = name;
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
app.controller("detailInfo", function($scope, $state, appData, $sce) {
	$scope.funName = appData.currentName + "详细信息";
	$scope.isLoading = false;
	$scope.concel = 'false';
	var info = [];
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	
	$scope.currentSchoolInfo = function(name) {
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/educationCommission/queryTrainingInstitution.do",
			dataType: "json",
			data: {
				name: encodeURI(name),
			},
			success: function(dataJson) {
				console.log(dataJson);
				$scope.isLoading = false;
				if(dataJson.code == 500){
					$scope.isAlert = true;
					$scope.msg = "接口错误";
				} else {
					info = dataJson.data.list[0];
					$scope.Item = [
									{"firstName":"机构名称","firstValue":info.NAME,"secondName":"许可证","secondValue":info.LICNO},	
									{"firstName":"校长","firstValue":info.CAPTAIN,"secondName":"举办者","secondValue":info.ISSUEBY},
									{"firstName":"办学内容","firstValue":info.SCONTENT,"secondName":"主管部门","secondValue":info.SDEPT},
									{"firstName":"地址","firstValue":info.ADDRESS,"secondName":"有效期限","secondValue":info.VALIDDATE},
								]
				}
			},
			error: function(err) {
				$scope.isLoading = false;
				console.log(err);
			}
		});
	};
	$scope.currentSchoolInfo(appData.currentName);
	$scope.prevStep = function() {
		$state.go("choose");
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