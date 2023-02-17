app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName = "公交卡余额查询";
	$scope.isLoading = false;
	$scope.result = false;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.queryBalance = function(cardNo) {
		var reg = /^[0-9]*$/;
		console.log(reg.test(cardNo));
		if(!reg.test(cardNo)) {
			$scope.isAlert = true;
			$scope.msg = "首字母无需填写，仅填写数字部分即可";
			$scope.alertConfirm = function() {
				$scope.isAlert = false;
			}
		} else {
			$.ajax({
				type: "get",
				url: $.getConfigMsg.preUrlSelf + "/selfapi/transportationCard/queryBalance.do",
				dataType: "json",
				jsonp: "jsonpCallback",
				data: {
					cardNo: cardNo
				},
				success: function(dataJson) {
					console.log(dataJson);
					if(dataJson.state == "00") {
						$scope.result = true;
						$scope.list = dataJson;
						$scope.lastdate = dataJson.lastdate.substring(0, 4) + "-" + dataJson.lastdate.substring(4, 6) + "-" + dataJson.lastdate.substring(6, 8);
						console.log($scope.lastdate);
					} else if(dataJson.state == "FF") {
						$scope.isAlert = true;
						$scope.msg = dataJson.content;
						$scope.alertConfirm = function() {
							$scope.isAlert = false;
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
		}
		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: appData.funName,
			}
		}
		recordUsingHistory('交通委服务', '查询', appData.funName, "", "", '', '', JSON.stringify($scope.jsonStr));
		//行为分析(查询)
		trackEventForQuery(appData.funName, "", "查询", "上海市交通委员会", "","", "");
	}
	$scope.currentSchoolInfo = function(name) {
		$scope.funName = name + "详细信息"
	};

	$scope.prevStep = function() {
		$.device.GoHome();
		//window.location.href = "../JTW_allItem/index.html";
	}
});