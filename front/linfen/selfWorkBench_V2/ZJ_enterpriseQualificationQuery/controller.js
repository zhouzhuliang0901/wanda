app.controller('main', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.operation = '企业资质类查询事项';
	$scope.choiceType = function () {
		$state.go('info');
	}
});
// 处理日期格式2015-11-11 00:00:00 return 2015-11-11
function nyrAddtimeReturnNYR(h){
	return /\d{4}-\d{1,2}-\d{1,2}/g.exec(h)[0];
}
app.controller('info', function($state, $scope, appData, $timeout, $rootScope) {
	$scope.funName = "企业资质类查询事项";
	appData.funName=$scope.funName;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.nextText = "查询";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}	
	$scope.prevStep = function() {
		$state.go('main');
	}
	$scope.queryQualificationData={};
	$scope.showQueryQualificationData=false;
	$scope.STcreditCode='';
	$scope.STcompanyName='';
	$scope.nextStep = function() {
		if ($scope.STcompanyName==''){
			$scope.isAlert = true;
			$scope.msg = "企业名称格式不正确";
			return;
		}
		if ($scope.STcreditCode==''){
			$scope.isAlert = true;
			$scope.msg = "统一社会信用代码格式不正确";
			return;
		}
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/selfapi/enterpriseQualification/queryQualification.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				creditCode:$scope.STcreditCode,//'91310230791480804Y'
				companyName:encodeURI($scope.STcompanyName),//'上海协北市政工程有限公司'
			},
			success: function(dataJson) {
				try{
					$scope.isLoading = false;
					if(dataJson&&dataJson.code==0){
						if(JSON.parse(dataJson.data).length!=0){
							$scope.queryQualificationData=JSON.parse(dataJson.data)[0];
							if($scope.queryQualificationData.fzrq){
								$scope.queryQualificationData.fzrq=nyrAddtimeReturnNYR($scope.queryQualificationData.fzrq);
							}
							if($scope.queryQualificationData.yxrq){
								$scope.queryQualificationData.yxrq=nyrAddtimeReturnNYR($scope.queryQualificationData.yxrq)
							}
							console.log($scope.queryQualificationData);
							$scope.showQueryQualificationData=true;
						}else{
							$scope.isAlert = true;
							$scope.msg = "查询数据出错,请确认查询信息!";
							$scope.queryQualificationData={};
							return;
						}
					}else{
						$scope.isAlert = true;
						$scope.msg = "查询数据出错,请确认查询信息!";
						$scope.queryQualificationData={};
						return;
					}
				}catch(e){
					$scope.isAlert = true;
					$scope.msg = "查询数据出错,请确认查询信息!";
					$scope.queryQualificationData={};
					return;
				}
			},
			error: function(err) {
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
		recordUsingHistory('住建服务', '查询', appData.funName, "", "", '', '', JSON.stringify($scope.jsonStr));
		trackEventForQuery(appData.funName,'','查询','企业资质类查询事项','','','');
	}
});