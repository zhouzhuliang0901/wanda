app.controller("main", function($scope, $state, appData, $timeout) {
	$scope.funName = '使用调研';
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function(){
		$scope.isAlert = false;
		$.device.GoHome();
	}
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

	$scope.choice1 = function(item){
		$scope.nmMachine = item;
	}

	$scope.choice2 = function(item){
		$scope.nmAppearance = item;
	}

	$scope.choice3 = function(item){
		$scope.nmOperation = item;
	}

	$scope.choice4 = function(item){
		$scope.nmScreen = item;
	}
	//模块使用记录
	//recordUsingHistory('用户评价', '评价', '使用调研', '', '', '', '', '');
	//行为分析(查询)
	//trackEventForQuery("使用调研", "", "评价", "", "", "", "");
	$scope.nextStep = function() {
		$.ajax({
			type:"get",
			url:$.getConfigMsg.preUrlSelf+"/selfapi/satisfactionEvaluation/saveSatisfaction.do",
			dataType:'json',
			data:{
				machineMAC:$.config.get('uniqueId')||'12-12-12-12-12',
				nmMachine:$scope.nmMachine,
				nmAppearance:$scope.nmAppearance,
				nmOperation:$scope.nmOperation,
				nmScreen:$scope.nmScreen,
				context:encodeURI($scope.context||""),
				name:"",
				idCard:"",
				phone:$scope.phone,
			},success:function(res){
				console.log(res);
				if(res.success == true){
					$scope.isAlert = true;
					$scope.msg = res.msg;
				}else{
					$scope.isAlert = true;
					$scope.msg = res.msg;
				}
			},error:function(err){
				$scope.isAlert = true;
				$scope.msg ='保存接口异常，请重试';
			}
		});
	}
});