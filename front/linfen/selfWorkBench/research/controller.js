app.controller("main", function($scope, $state, appData, $timeout) {
	$scope.funName = '使用调研';
	$scope.isAlert = false;
	$scope.msg = "提交成功";
	$scope.concel = "false";
	//公用单选
	function PublicChoiceById(PcId) {
		$("#" + PcId + " a").click(function() {
			if($(this).attr('class') == 'in') {
				$(this).removeClass("in");
			} else {
				$("#" + PcId + " a").removeClass("in");
				$(this).addClass("in");
			}
		})
	}
	$scope.keybordArr = [1, 2, 3, 4, 5];
	$timeout(function() {
		PublicChoiceById('keybord1');
		PublicChoiceById('keybord2');
		PublicChoiceById('keybord3');
		PublicChoiceById('keybord4');
	}, 1000);
//	//模块使用记录
//	recordUsingHistory('用户评价', '评价', '使用调研', '', '', '', '', '');
//	//行为分析(查询)
//	trackEventForQuery("使用调研", "", "评价", "", "", "", "");
	$scope.nextStep = function() {
		$scope.isAlert = true;
		$scope.alertConfirm = function() {
			$.device.GoHome();
		}
	}
});