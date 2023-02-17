app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName = "宠物诊疗机构目录查询";
	$scope.isLoading = false;
	$scope.concel = 'false';
	$scope.isDetial = true;
	$scope.getAnimalCertList = function(){
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/AnimalTreatmentLicense/getInformation.do",
			//  url: "http://localhost:8080/ac-self/selfapi/AnimalTreatmentLicense/getInformation.do",
			 dataType: "json",
			success: function(dataJson) {
				$scope.animalCertList = dataJson.record.animalCert;
				
				console.log($scope.animalCertList)
			},
			error: function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "接口异常";
			}
		});
		
		//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: "宠物诊疗机构查询",
			Number: "",
		}
	}
	recordUsingHistory('农委服务', '查询', "宠物诊疗机构查询", "", "", '', '', JSON.stringify($scope.jsonStr));
	//行为分析(查询)
	trackEventForQuery("宠物诊疗机构查询", '', "查询", "上海市农业农村委员会", "", "", "");
	};
	$scope.getAnimalCertList();
	$scope.selectPetName = function(petName){
		var arr = [];
		var list = $scope.animalCertList;
		for (var i = 0; i < list.length; i++) {
		  if (list[i].sName.indexOf(petName) >= 0) {
			arr.push(list[i]);
		  }
		}
		if(arr.length==0){
			$scope.isAlert = true;
			$scope.msg = "暂无任何数据";
			$scope.isDetial = false;
		}else{
			$scope.list2 = arr;
			$scope.isDetial = false;
		}
	};
	$scope.alertConfirm = function(){
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		if($scope.isDetial){
			$.device.GoHome();
		} else {	
			$scope.isDetial = true;	
		}
	}
  
	$scope.isScroll = function() {
		var myiScroll = new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true,
			onScrollMove: function() {	
			}
		});
	};
	$scope.isScroll();
	
});