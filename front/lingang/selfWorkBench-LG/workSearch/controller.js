app.controller("searchMain", function($scope, appData, $state) {
	$scope.operation = "请选择查询方式";
	$scope.choiceType = function(type) {
		appData.inputType = type;
		$state.go("input");
	}
});
app.controller("searchInput", function($scope, appData, $state, appFactory) {
	$scope.operation = "请刷身份证";
	$scope.inputType = appData.inputType;
	$scope.applyObj = "";
	$scope.applySerial = "";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.serialSearch = function() {
		if($scope.applyObj == '') {
			$scope.isAlert = true;
			//$scope.msg = "申请对象不能为空!";
		} else if($scope.applySerial == '') {
			$scope.isAlert = true;
			$scope.msg = "办件编码不能为空!";
		}
		appFactory.pro_fetch("forward.do", {
			fmd: 'aci-eventquery',
			fdo: 'getWorkApplyInfo',
			type: '0',
			stApplyNo: $scope.applySerial,
			name: $scope.applyObj
		}, function(data) {
			if(!data) {
				$scope.isAlert = true;
				$scope.msg = "没有该办件信息!";
				return;
			}
			console.log(data)
			appData.detail = data;
			$state.go("detail", {
				prevRoute: "input"
			});
		})
	};
	$scope.codeSearch = function() {
		$.device.qrCodeOpen(function(code) {
			if(code.indexOf("display") != -1) {
				appData.codeForOther = code;
				$state.go("detailForOther", {
					prevRoute: "input"
				});
			} else {
				appFactory.pro_fetch("forward.do", {
					fmd: 'aci-eventquery',
					fdo: 'getWorkApplyInfo', //getApplyInfoByStApplyNo
					type: '0',
					stApplyNo: code
				}, function(data) {
					if(!data) {
						$scope.isAlert = true;
						$scope.msg = "没有该办件信息!";
						return;
					}
					appData.detail = data;
					$state.go("detail", {
						prevRoute: "input"
					});
				})
			}
		});
	};
	$scope.idcardSearch = function(data) {
		appFactory.pro_fetch("forward.do", {
			fmd: 'aci-eventquery',
			fdo: 'getWorkApplyInfo', //getApplyInfoByStIdCard
			type: '1',
			stApplyNo: data.Number
			//stIdCard: data.Number
		}, function(data) {
			if(!data) {
				$scope.isAlert = true;
				$scope.msg = "没有该办件信息!";
				return;
			}
			appData.details = data;
			$state.go("list");
		})
	}
	switch($scope.inputType) {
		case "idcard":
			$scope.operation = "请刷身份证";
			break;
		case "serial":
			$scope.operation = "请输入申请对象名称和办件编码";

			break;
		case "code":
			$scope.operation = "请扫描二维码";
			$scope.codeSearch();
			break;
	}
});
app.controller("searchList", function($scope, $state, appData) {
	$scope.operation = "办件列表:请选择一个办件";
	$scope.incidentList = appData.details;
	$scope.current = 0;
	$scope.choiceIncidentList = function(i, item) {
		$scope.current = i;
		appData.detail = $scope.incidentList[i];
	};
	$scope.nextStep = function() {
		appData.detail = $scope.incidentList[$scope.current]
		$state.go("detail", {
			prevRoute: "list"
		});
	}
});
app.controller("searchDetail", function($scope, $state, appData, $stateParams) {
	$scope.operation = "办件详情";
	$scope.nextTxt = "111";
	$scope.prevRoute = $stateParams.prevRoute;
	$scope.incidentDetail = appData.detail;

	$scope.nextStep = function() {
		$state.go($scope.prevRoute);
	};
});
app.controller("detailForOther", function($scope, $state, appData, $stateParams) {
	$scope.operation = "办件详情";
	$scope.prevRoute = $stateParams.prevRoute;
	$scope.incidentDetail = appData.detail;
	$scope.itemDetail = [];
	$scope.show = false;
	//appData.codeForOther= "http://222.66.73.172/views/othing/qrcode/display?code=VYlwmUCOnruWwHtE&userId=cXXbweIPYFKGSAKFswCV&time=20191105101934";
	$scope.isLoding = false;
	$scope.getMassage = function() {
		$.ajax({
			url: appData.codeForOther,
			type: "post",
			async: true,
			dataType: 'json',
			data: {
				type: "machine"
			},
			success: function(dataJson) {
				if(dataJson.success == true) {
					$scope.show = true;
					$scope.isLoding = true;
					$scope.name = dataJson.othingIns.OI_THING_NAME;
					$scope.itemList = dataJson.othingIns.stageInsList; //SI_STAGE_NAME阶段
					for(var i = 0; i < $scope.itemList.length; i++) {
						if($scope.itemList[i].OTSI_STATUS == "-1") {
							$scope.itemList[i].OTSI_STATUS = "作废";
						} else if($scope.itemList[i].OTSI_STATUS == "0") {
							$scope.itemList[i].OTSI_STATUS = "待办";
						} else if($scope.itemList[i].OTSI_STATUS == "1") {
							$scope.itemList[i].OTSI_STATUS = "办理中";
						} else if($scope.itemList[i].OTSI_STATUS == "2") {
							$scope.itemList[i].OTSI_STATUS = "已办";
						} else if($scope.itemList[i].OTSI_STATUS == undefined || $scope.itemList[i].OTSI_STATUS == null) {
							$scope.itemList[i].OTSI_STATUS = "暂未办理";
						}
						if($scope.itemList[i].itemInsList == '') {
							$scope.itemDetail.push({
								itemStage: $scope.itemList[i].SI_STAGE_NAME,
								status: $scope.itemList[i].OTSI_STATUS,
								item1: ""
							});
						}
						if($scope.itemList[i].itemInsList != null) {
							for(var j = 0; j < $scope.itemList[i].itemInsList.length; j++) {
								$scope.itemListName = $scope.itemList[i].itemInsList[j].II_ITEM_NAME;
								if($scope.itemList[i].itemInsList[j].OTII_STATUS == "0") {
									$scope.itemList[i].itemInsList[j].OTII_STATUS = "作废";
								} else if($scope.itemList[i].itemInsList[j].OTII_STATUS == "1") {
									$scope.itemList[i].itemInsList[j].OTII_STATUS = "待办";
								} else if($scope.itemList[i].itemInsList[j].OTII_STATUS == "2") {
									$scope.itemList[i].itemInsList[j].OTII_STATUS = "办理中";
								} else if($scope.itemList[i].itemInsList[j].OTII_STATUS == undefined || $scope.itemList[i].itemInsList[j].OTII_STATUS == null) {
									$scope.itemList[i].itemInsList[j].OTII_STATUS = "暂未办理";
								}
								$scope.itemDetail.push({
									itemStage: $scope.itemList[i].SI_STAGE_NAME,
									status: $scope.itemList[i].OTSI_STATUS,
									item1: $scope.itemList[i].itemInsList[j].II_ITEM_NAME,
								});
							}
						} else {
							$scope.itemDetail.push({
								itemStage: $scope.itemList[i].SI_STAGE_NAME,
								status: $scope.itemList[i].OTSI_STATUS,
								item1: ""
							});
						}

					}
				} else {
					$scope.show = true;
					$scope.isLoding = true;
					alert("办件编号有误 请重新扫描");
				}
			},
			error: function(err) {
				console.log("响应失败！")
			}
		});
	};
	$scope.getMassage();

	$scope.nextStep = function() {
		$state.go($scope.prevRoute);
	};
});