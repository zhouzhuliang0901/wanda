app.controller("choose", function($scope, $state, appData, $sce, $location) {
	$scope.funName = appData.funName = "3岁以下幼儿托育服务机构查询";
	$scope.isLoading = false;
	$scope.concel = 'false';
	$scope.querySchoolsInfo = function(schoolName) {
		if(schoolName == undefined || schoolName == '' || schoolName == null) {
			schoolName = '上海';
		}
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/educationCommission/queryChildCareInstitution.do",
			dataType: "json",
			data: {
				name: encodeURI(schoolName),
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				if(dataJson.code != 200) {
					$scope.isAlert = true;
					$scope.msg = "接口异常";
				} else if(dataJson.data.total != 0){
					$scope.queryList = dataJson.data.list; // 给当前页作展示
					appData.resultList = dataJson.data.list; // 给下一页数据做展示
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

	$scope.currentSchoolInfo = function(name,index) {
		appData.currentName = name;
		appData.currentIndex = index;
		$location.path("/detailInfo")
	};

	$scope.prevStep = function() {
		if($scope.isDetial) {
			window.location.href = "../JW_allItem/index.html"
		} else {
			$scope.isDetial = true;
			$scope.funName = "3岁以下幼儿托育服务机构查询";
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
	$scope.funName = appData.currentName + "详细信息";
	$scope.isLoading = true;
	$scope.concel = 'false';
	var info = [];
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.currentSchoolInfo = function(index) {
		$scope.isLoading = false;
		if(index < 0) {
			$scope.isAlert = true;
			$scope.msg = "数据异常";
		} else {
			info = appData.resultList[index];
			$scope.Item = [
							{"firstName":"机构名称","firstValue":info.SNAME,"secondName":"机构编号","secondValue":info.LICNO},
							{"firstName":"服务形式","firstValue":info.SJGLX,"secondName":"区业务主管单位","secondValue":info.SDEPT},
							{"firstName":"机构负责人","firstValue":info.CAPTAIN,"secondName":"机构联系电话","secondValue":info.SFZRDH},
							{"firstName":"举办者","firstValue":info.ISSUEBY,"secondName":"地址","secondValue":info.ADDRESS},
							{"firstName":"收费标准","firstValue":info.DSFBZ,"secondName":"供餐情况","secondValue":info.NGCQK},
						]
		}
	};
	$scope.currentSchoolInfo(appData.currentIndex);
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