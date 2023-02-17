app.controller('main', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.operation = '公积金还款计算';
	$scope.choiceType = function () {
		$state.go('choose');
	}
})
app.controller('choose', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.funName = "公积金还款计算";
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//表单信息
	$scope.hkWayList = hkWay;
	$scope.dkTypeList = dkType;
	$scope.change = function(name, index, id) {
		$scope.current = index;
		$scope.hkWayName = name;
		appData.hkWayId = id;
	}
	$scope.change1 = function(name, index, id) {
		$scope.current1 = index;
		$scope.dkTypeName = name;
		appData.dkTypeId = id;
	}
	$scope.prevStep = function() {
		$state.go('main');
	}
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.hkWayName)) {
				$scope.isAlert = true;
				$scope.msg = "请选择还款方式！";
				return;
			}
			if(isBlank($scope.dkTypeName)) {
				$scope.isAlert = true;
				$scope.msg = "请选择贷款类型！";
				return;
			}
		} while (condFlag);
		//提交参数集合
		condFlag = true;
		$scope.isLoading = true;
		$state.go("info");
	}
});
app.controller('info', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.funName = "公积金还款计算";
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.current = appData.dkTypeId;
	console.log($scope.current);
	$scope.nextText = "查询";
	//表单信息
	$scope.stGJJDkLv = "2.75%";
	$scope.stBusinessDkLv = "4.9%";
	$scope.listShow = false;
	$scope.dkWayList = dkWay;
	$scope.interestRateList = interestRate;
	$timeout(function() {
		$scope.$watch("stDkWay", function(val) {
			if(val) {
				if(val.id == "0") {
					$scope.isShow = false;
				} else {
					$scope.isShow = true;
				}
			}
		});
		$scope.changeRate = function(val) {
			console.log(val);
			$scope.stBusinessDkLv = val.id;
		}
		$scope.$watch("stYear", function(val) {
			if(val) {
				var sum = parseInt(val);
				if(sum <= 5) {
					$scope.stGJJDkLv = "2.75%";
				} else {
					$scope.stGJJDkLv = "3.25%";
				}
			}
		});
	}, 100)
	$scope.nextStep = function() {
		var condFlag = false;
		console.log($("#gjjdk").val());
		do {
			if(isBlank($scope.stDkWay)) {
				$scope.isAlert = true;
				$scope.msg = "请选择贷款方式！";
				return;
			}
			if(isBlank($scope.stYear)) {
				$scope.isAlert = true;
				$scope.msg = "请输入按揭年数（1年12期）！";
				return;
			}
			if($scope.isShow == true && isBlank($scope.houseSum)) {
				$scope.isAlert = true;
				$scope.msg = "请输入房屋总价（万元）！";
				return;
			}
			if($scope.isShow == true && isBlank($scope.ajch)) {
				$scope.isAlert = true;
				$scope.msg = "请输入按揭成数（成）！";
				return;
			}
			if(($scope.current == '2' || $scope.current == '12') && isBlank($("#sydk").val())) {
				$scope.isAlert = true;
				$scope.msg = "请输入商业贷款（万元）！";
				return;
			}
			if(($scope.current == '1' || $scope.current == '12') && isBlank($("#gjjdk").val())) {
				$scope.isAlert = true;
				$scope.msg = "请输入公积金贷款（万元）！";
				return;
			}
			if(isBlank($scope.stBusinessDkLv)) {
				$scope.isAlert = true;
				$scope.msg = "请选择利率！";
				return;
			}
		} while (condFlag);
		//提交参数集合
		condFlag = true;
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/loanAndRepayment/repaymentTrialBalance.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				loanType: appData.dkTypeId,
				type: $scope.stDkWay.id,
				repaymentType: appData.hkWayId,
				year: $('#stYear').val(),
				fundSum: $('#gjjdk').val() || $scope.dksum,
				commercialSum: $('#sydk').val() || $scope.dksum,
				housePrice: $scope.houseSum,
				fundRate: $scope.stGJJDkLv.split("%")[0],
				commercialRate: $scope.stBusinessDkLv.split("%")[0],
				number: $scope.ajch,
			},
			success: function(dataJson) {
				console.log(dataJson);
				$scope.isLoading = false;
				if(dataJson.state == 1) {
					$scope.listShow = true;
					$scope.queryList = dataJson.obj;
					$scope.monthList = $scope.queryList.monthList;
					$('#mouthList tr').remove();
					if($scope.monthList&&$scope.monthList.length > 0) {
						$scope.monthListShow = true;
						$timeout(function() {
							for(var i = 0; i < $scope.monthList.length; i = i + 3) {
								console.log(document.getElementById('mouthList'));
								var tr = document.createElement("tr");
								tr.innerHTML = '<td>' + $scope.monthList[i] + '</td><td>' + $scope.monthList[i + 1] + '</td><td>' + $scope.monthList[i + 2] + '</td>'
								document.getElementById('mouthList').append(tr);
							}
						}, 1000)
					} else {
						$scope.monthListShow = false;
					}
				} else {
					$scope.isAlert = true;
					$scope.msg = "暂未查询出试算金额";
				}
			},
			error: function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "暂未查询出试算金额";
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
		trackEventForQuery(appData.funName,'','查询','上海市住房和城乡建设委员会','','','');
	}
	$scope.prevStep = function() {
		$state.go("choose");
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: true,
			taps: true,
			preventDefault: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});