app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName = "民办教育机构查询";
	$scope.isLoading = false;
	appData.bankName = '';
	$scope.concel = 'false';
	$scope.isDetial = true;
	$scope.querySchoolsInfo = function(schoolName) {
		if(schoolName == undefined){
			schoolName = '学';
		}
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/educationCommission/queryCivilianSchool.do",
			dataType: "json",
			data: {				
				schoolName:encodeURI(schoolName),
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				if(!dataJson[0]){
					$scope.isAlert = true;
					$scope.msg = "暂无任何数据";
				}else{
					$scope.queryList = dataJson;
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
		trackEventForQuery(appData.funName, "", "查询", "上海市教育委员会", "","", "");	
	}
	$scope.querySchoolsInfo();
	
	$scope.alertConfirm = function(){
		$scope.isAlert = false;
	}
	
	$scope.currentSchoolInfo = function(name){
		$scope.isLoading = true;
		$scope.isDetial = false;
		$scope.funName = name+"详细信息";
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/educationCommission/queryCivilianSchool.do",
			dataType: "json",
			data: {				
				schoolName:encodeURI(name),
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				$scope.currentList = dataJson;
			},
			error: function(err) {
				$scope.isLoading = false;
				console.log(err);
			}
		});
	};
	
	$scope.prevStep = function() {
		if($scope.isDetial){
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