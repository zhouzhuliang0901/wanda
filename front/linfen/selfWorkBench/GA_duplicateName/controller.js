app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	trackEvent($(".headName").text());
	$scope.funName = appData.funName = "新生儿重名查询";
	$scope.isLoading = false;
	appData.bankName = '';
	$scope.result = false;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.querySchoolsInfo = function(schoolName) {
		console.log(schoolName);
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/duplicateName/queryDuplicateName.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				name: encodeURI(schoolName)
			},
			success: function(dataJson) {
				if(dataJson.code == "0") {
					$scope.result = true;
					if(dataJson.data.length > 0 && dataJson.data != "[]") {
						$scope.xm = dataJson.data[0].xm;
						$scope.num = dataJson.data[0].syrklbdm - 1;
					} else {
						$scope.isAlert = true;
						$scope.msg = "暂未查询到信息";
						$scope.alertConfirm = function() {
							$scope.isAlert = false;
						}
					}
				} else {
					$scope.isAlert = true;
					$scope.msg = "暂未查询到信息";
					$scope.alertConfirm = function() {
						$scope.isAlert = false;
					}
				}
			},
			error: function(err) {
				$scope.isAlert = true;
				$scope.msg = "接口异常请稍候再试";
				$scope.alertConfirm = function() {
					$scope.isAlert = false;
				}
			}
		});
		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: appData.funName,
			}
		}
		recordUsingHistory('公安服务', '查询', appData.funName, "", "", '', '', JSON.stringify($scope.jsonStr));
		//行为分析(查询)
		trackEventForQuery(appData.funName, "", "查询", "上海市公安局", "", "", "");
	}
	//	$scope.querySchoolsInfo();
	$scope.currentSchoolInfo = function(name) {
		$scope.funName = name + "详细信息"
	};

	$scope.prevStep = function() {
		window.location.href = "../publicSecurity/index.html";
	}
});