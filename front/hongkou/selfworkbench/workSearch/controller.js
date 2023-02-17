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
	$scope.isLoding = true;
	$scope.alertConfirm = function(){
		$scope.isAlert = false;
	}
	$scope.serialSearch = function() {
		if($scope.applyObj == '') {
			$scope.isAlert = true;
			$scope.msg = "申请对象不能为空!";
		} else if($scope.applySerial == '') {
			$scope.isAlert = true;
			$scope.msg = "办件编码不能为空!";
		}
		appFactory.pro_fetch("forward.do", {
			fmd: 'aci-eventquery',
			fdo: 'getWorkApplyInfo',
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
			$scope.isLoding = false;
			code= code.replace(/\r\n/g,"");
			$.log.debug(code);
			appFactory.pro_fetch("forward.do", {
				fmd: 'aci-eventquery',
				fdo: 'getApplyInfoByStApplyNo',
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
		});
	};
	$scope.idcardSearch = function(data) {
		appFactory.pro_fetch("forward.do", {
			fmd: 'aci-eventquery',
			fdo: 'getApplyInfoByStIdCard',
			stIdCard: data.Number
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
			$scope.operation = "请扫描条形码";
			$scope.codeSearch();
			break;
	}
	$scope.prevStep = function(){
		$state.go("main");
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