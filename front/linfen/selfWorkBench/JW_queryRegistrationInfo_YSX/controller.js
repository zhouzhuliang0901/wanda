app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.funName = "信息查询";
	appData.funName = "幼升小报名信息查询(公办、民办)";
	$scope.isLoading = false;
	$scope.concel = "false";
	$scope.typeList = ["居民身份证", "港澳居民身份证", "港澳居民来往内地通行证", "台湾居民居住证", "台湾居民来往大陆通行证", "护照", "中华人民共和国外国人永久居住证", "其他"]; // 证照类型
	// 查询信息
	$scope.nextStep = function() {
		 var idNumber = $(".idNumber").val();
		 var studentName = $(".studentName").val();
		 var licenseType = $(".licenseType").val();
		 var licenseNumber = $(".licenseNumber").val();
		if(isArrayBlank([idNumber,studentName,licenseType,licenseNumber])){
			$scope.isAlert = true;
			$scope.msg = "请填写完整的信息";
			return;
		}
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/educationCommission/queryRegistrationInformation.do",
			dataType: "json",
			data: {
				applyNo: idNumber,
				username: encodeURI(studentName),
				licenseType: encodeURI(licenseType),
				licenseNo: licenseNumber
			},
			success: function(dataJson) {
				console.log(dataJson);
				$scope.isLoading = false;
				if(dataJson.type != 'nodata') {
					$scope.isShow = true;
					$scope.suggestion = dataJson.suggestion;
				} else {
					$scope.isShow = true;
					$scope.suggestion = "未查到相关结果，请确认信息是否填写正确。如有疑问，请拨打电话021-962066";
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
	$scope.prevStep = function() {
		window.location.href = "../JW_allItem/index.html"
	}
	$scope.alertConfirm = function(){
		$scope.isAlert = false;
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