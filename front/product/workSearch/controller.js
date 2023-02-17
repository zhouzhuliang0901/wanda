function removeAnimate(ele){
	$(ele).css({"transform":"translateY(0px)","top":0}).removeClass('transformto')
}
function addAnimate(ele){
	$(ele).addClass('transformto').on("animationend",function(){
		$(ele).removeClass('transformto')
	})
}

app.controller("searchMain", function($scope, appData, $state, $rootScope) {
	removeAnimate($('.linkBox1'))
	$scope.operation = "请选择查询方式";
	addAnimate($('.linkBox1'))
	$scope.choiceType = function(type) {
		appData.inputType = type;
		$state.go("input");
	}

	$scope.prevStep = function() {
		$.device.idCardClose();
		$.device.qrCodeClose();
		$.device.GoHome();
	}
});
app.controller("searchInput", function($scope, appData, $state, appFactory, $http, $rootScope) {
	$scope.operation = "请刷身份证";
	$scope.inputType = appData.inputType;
	$scope.applyObj = "";
	$scope.applySerial = "";
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.serialSearch = function() {
		if($scope.applySerial == '') {
			$scope.isAlert = true;
			$scope.msg = "办件编码不能为空!";
		}
//		appFactory.pro_fetch("forward.do", {
		appFactory.r_pro_fetch("/progressquery/applyInfoDetail", {
//			fmd: 'aci-eventquery',
//			fdo: 'getApplyInfoByStApplyNo',
			stApplyNo: $scope.applySerial,
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
			code = code.replace(/\r\n/g, "");
			appFactory.pro_fetch("forward.do", {
				fmd: 'aci-eventquery',
				fdo: 'getApplyInfoByStApplyNo',
				stApplyNo: code,
			}, function(data) {
				$.log.debug("data:" + JSON.stringify(data))
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
			});
			//			appFactory.pro_fetch("forward.do", {
			//				fmd: 'aci-eventquery',
			//				fdo: 'getApplyInfoByStApplyNo',
			//				stApplyNo: code
			//			}, function(data) {
			//				if(!data) {
			//					$scope.isAlert = true;
			//					$scope.msg = "没有该办件信息!";
			//					return;
			//				}
			//				appData.detail = data;
			//				$state.go("detail", {
			//					prevRoute: "input"
			//				});
			//			})
		});
	};
	$scope.idcardSearch = function(data) {
//		appFactory.pro_fetch("forward.do", {
		appFactory.r_pro_fetch("/progressquery/applyList", {
//			fmd: 'aci-eventquery',
//			fdo: 'getApplyInfoByStIdCard',
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
			$scope.operation = "请扫描二维码";
			$scope.codeSearch();
			break;
	}

	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller("searchList", function($scope, $state, appData) {
	$scope.operation = "办件列表 : 请选择一个办件";
	$scope.incidentList = appData.details;
	/*$scope.incidentList = [{stApplyNo:"1234567891234",stApplyStr:"2019-08-14 09:26:25",stItemName:"进行特殊设备维护信息信息信息信息你鑫"},
	  					   {stApplyNo:"1234569781234",stApplyStr:"2019-08-14 09:26:25",stItemName:"进行特殊设备维护信息"},
	  					   {stApplyNo:"1234569781235",stApplyStr:"2019-08-14 09:26:25",stItemName:"进行特殊设备维护信息"},
	  					   {stApplyNo:"1234569781235",stApplyStr:"2019-08-14 09:26:25",stItemName:"进行特殊设备维护信息"},
	  					   {stApplyNo:"1234569781235",stApplyStr:"2019-08-14 09:26:25",stItemName:"进行特殊设备维护信息"},
	  					   {stApplyNo:"1234569781235",stApplyStr:"2019-08-14 09:26:25",stItemName:"进行特殊设备维护信息"},
	  					   {stApplyNo:"1234569781235",stApplyStr:"2019-08-14 09:26:25",stItemName:"进行特殊设备维护信息"},
	  					   {stApplyNo:"1234569781235",stApplyStr:"2019-08-14 09:26:25",stItemName:"进行特殊设备维护信息"},
	  					   {stApplyNo:"1234569781235",stApplyStr:"2019-08-14 09:26:25",stItemName:"进行特殊设备维护信息"},
	  					   {stApplyNo:"1234569781235",stApplyStr:"2019-08-14 09:26:25",stItemName:"进行特殊设备维护信息"},
	  					   {stApplyNo:"1234569781235",stApplyStr:"2019-08-14 09:26:25",stItemName:"进行特殊设备维护信息"},
	  					   {stApplyNo:"1234569781235",stApplyStr:"2019-08-14 09:26:25",stItemName:"进行特殊设备维护信息"}
	  					   ];*/
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
	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
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