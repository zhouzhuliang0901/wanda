app.controller("choose", function($scope, $state, appData, $timeout, $sce) {
	$scope.funName = appData.funName = "地质档案查询";
	$scope.isLoading = false;
	$scope.concel = 'false';
	$scope.animalCertList = [];
	$scope.isDetial = true;
	$scope.selectSpace = function(spaceName) {
		if(spaceName != "" && spaceName != null) {
			$scope.isLoading = true
			$scope.applicationInfo = {
				"selectV": spaceName,
				"beginNum": "1",
				"countNum": "999"
			}
			$.ajax({
				type: "post",
				url: $.getConfigMsg.preUrlSelf + "/selfapi/geology/getInformationList.do",
				//  url: "http://localhost:8080/ac-self/selfapi/geology/getInformationList.do",
				dataType: "json",
				// jsonp: "jsonpCallback",
				data: {
					json: JSON.stringify($scope.applicationInfo)
				},
				success: function(dataJson) {
					console.log(dataJson.tableV)
					$scope.isLoading = false
					$scope.isDetial = true
					$timeout(function() {
						$scope.animalCertList = dataJson.tableV; //我就想延时一秒 没理由
						$scope.isLoading = false;
					}, 1000)
				},
				error: function(err) {
					$scope.isLoading = false;
					$scope.isAlert = true;
					$scope.msg = "接口异常";
				}
			});
		} else {
			$scope.isLoading = false;
			$scope.isAlert = true;
			$scope.msg = "请输入关键字！";
		}

	};

	$scope.spaceInfo = function(codeV) {
		appData.spaceCode = codeV;
		console.log("档案号是" + codeV)
		$scope.applicationInfo = {
			"codeV": codeV
		}
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/geology/getInformationDetail.do",
			//  url: "http://localhost:8080/ac-self/selfapi/geology/getInformationDetail.do",
			dataType: "json",
			// jsonp: "jsonpCallback",
			data: {
				json: JSON.stringify($scope.applicationInfo)
			},
			success: function(dataJson) {
				appData.spaceInfoList = dataJson
				$state.go('info');
			},
			error: function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "接口异常";
			}
		});

	};
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		if($scope.isDetial) {
			$.device.GoHome();
		} else {
			$scope.isDetial = true;
		}
	}

	$scope.isScroll = function() {
		myiScroll = new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true,
			onScrollMove: function() {
				if(this.y <= this.maxScrollY) {
					console.log("到底了")
				}
			}
		});
	};
	$scope.isScroll();

});
app.controller("info", function($scope, $state, appData, $sce) {
	$scope.isLoading = false;
	$scope.concel = 'false';
	$scope.isDetial = true;
	// $scope.spaceInfoList = spaceInfoList;
	$scope.spaceInfoList = appData.spaceInfoList; //当前codeV对应的档案详情
	$scope.querySchoolsInfo = function(schoolName) {
		if(schoolName == undefined) {
			schoolName = '学';
		}
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/educationCommission/queryCivilianSchool.do",
			dataType: "json",
			data: {
				schoolName: encodeURI(schoolName),
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				if(!dataJson[0]) {
					$scope.isAlert = true;
					$scope.msg = "暂无任何数据";
				} else {
					$scope.queryList = dataJson;
				}
				console.log(dataJson);
			},
			error: function(err) {
				$scope.isLoading = false;
				console.log(err);
			}
		});
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		$state.go('choose');
	}
	//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: appData.funName,
		}
	}
	recordUsingHistory('规划资源局服务', '查询', appData.funName, "", "", '', '', JSON.stringify($scope.jsonStr));
	//行为分析(查询)
	trackEventForQuery(appData.funName, "", "查询", "上海市规划和自然资源局", "", "", "");
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