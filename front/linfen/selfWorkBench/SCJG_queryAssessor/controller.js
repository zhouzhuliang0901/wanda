app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName = "法定计量检定机构考评员查询";
	$scope.isLoading = false;
	appData.bankName = '';
	$scope.concel = 'false';
	$scope.isShow = false;
	$scope.firstIn = 0; // 记录是否是第一次进入 
	$scope.queryAssessor = function(assessorName, companyName) {
		$scope.firstIn++;
		if((assessorName == undefined || assessorName == '') && ($scope.firstIn != 1)) {
			$scope.isAlert = true;
			$scope.msg = "请输入姓名"
			return;
		} 
		if((companyName == undefined || companyName == '') && ($scope.firstIn != 1)) {
			$scope.isAlert = true;
			$scope.msg = "请输入单位名称"
			return;
		}
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/measuring/queryAssessor.do",
//			url: "http://10.2.104.131:8080/ac-self/selfapi/measuring/queryAssessor.do",
			dataType: "json",
			data: {
				name: encodeURI(assessorName),
				unit: encodeURI(companyName),
				type: 2
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				$scope.Item = dataJson;;
				$scope.isShow = true;
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
		recordUsingHistory('市场监管服务', '查询', appData.funName, "", "", '', '', JSON.stringify($scope.jsonStr));
		//行为分析(查询)
		trackEventForQuery(appData.funName, "", "查询", "上海市市场监督管理局", "", "", "");
	}
	$scope.queryAssessor('','');
	$scope.reset = function() {
		$scope.assessorName = '';
		$scope.companyName = '';
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		if($scope.isDetial) {
			window.location.href = "../SCJG_allItem/index.html"
		} else {
			$scope.isDetial = true;
			$scope.funName = "法定计量检定机构考评员查询";
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