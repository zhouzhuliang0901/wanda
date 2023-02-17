app.controller('main', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.operation = "公积金贷款试算";
	$scope.choiceType = function(type) {
		$state.go('info');
	}
})
app.controller('info', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.funName = "公积金贷款试算";
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.nextText = "查询";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//表单信息
	$scope.sexList = sex;
	$scope.jtdkList = jtdk;
	$scope.houseTypeList = houseType;
	$scope.tsrdList = tsrd;
	$scope.houseGenreList = houseGenre;
	$scope.changeSex = function(id, index) {
		$scope.currentSex = index;
		$scope.sexId = id;
	}
	$scope.changeJtdk = function(id, index) {
		$scope.currentJtdk = index;
		$scope.jtdkId = id;
	}
	$scope.changeHouseType = function(id, index) {
		$scope.currentHouseType = index;
		$scope.houseTypeId = id;
		if(id == "2") {
			$scope.isShow = true;
			$timeout(function() {
				$(".form_datetime").datetimepicker({
					format: "yyyy", //显示日期格式
					autoclose: true,
					startView: 4,
					minView: 4,
					language: 'zh-CN',
					pickerPosition: 'bottom-left',
				});
			}, 100)
		} else {
			$scope.isShow = false;
		}
	}
	$scope.changeHouseGenre = function(id, index) {
		$scope.currentHouseGenre = index;
		$scope.houseGenreId = id;
	}
	$scope.changeTsrd = function(id, index) {
		$scope.currentTsrd = index;
		$scope.tsrdId = id;
	}
	$scope.prevStep = function() {
		//		window.location.href = "../housingConstruction/index.html"
		$state.go('main');
	}
	$scope.nextStep = function() {
		$scope.stYear = $('#stYear').val();
		var condFlag = false;
		do {
			if(isBlank($scope.zdrgjj)) {
				$scope.isAlert = true;
				$scope.msg = "请输入主贷人公积金余额！";
				return;
			}
			if(isBlank($scope.zdrzfgjj)) {
				$scope.isAlert = true;
				$scope.msg = "请选择主贷人住房公积金缴存基数！";
				return;
			}
			if(isBlank($scope.stAge)) {
				$scope.isAlert = true;
				$scope.msg = "请输入主贷人年龄！";
				return;
			}
			if(isBlank($scope.sexId)) {
				$scope.isAlert = true;
				$scope.msg = "请选择主贷人性别！";
				return;
			}
			if(isBlank($scope.jtdkId)) {
				$scope.isAlert = true;
				$scope.msg = "请选择家庭贷款次数！";
				return;
			}
			if(isBlank($scope.houseTypeId)) {
				$scope.isAlert = true;
				$scope.msg = "请选择房屋类型！";
				return;
			}
			if(isBlank($scope.housePrice)) {
				$scope.isAlert = true;
				$scope.msg = "请输入房屋价格！";
				return;
			}
			if(isBlank($scope.houseArea)) {
				$scope.isAlert = true;
				$scope.msg = "请输入房屋面积！";
				return;
			}
			if($scope.isShow == true && isBlank($scope.stYear)) {
				$scope.isAlert = true;
				$scope.msg = "请选择二手房竣工年份！";
				return;
			}
			if(isBlank($scope.tsrdId)) {
				$scope.isAlert = true;
				$scope.msg = "请选择套数认定！";
				return;
			}
			if(isBlank($scope.houseGenreId)) {
				$scope.isAlert = true;
				$scope.msg = "请选择房屋类别！";
				return;
			}
		} while (condFlag);
		//提交参数集合
		condFlag = true;
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/loanAndRepayment/loanTrialBalance.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				accFundHost: $scope.zdrgjj, //主贷人公积金余额
				accFundHostSupply: $scope.zdrbcgjj, //主贷人补充公积金余额
				accFundHostBase: $scope.zdrzfgjj, //主贷人住房公积金缴存基数
				hostAge: $scope.stAge, //主贷人年龄
				hostSex: $scope.sexId, //主贷人性别 男为0 女为 1
				accFundSecondary: $scope.cdrgjj, //从贷人公积金余额
				accFundSecondarySupply: $scope.cdrbcgjj, //参贷人补充公积金余额
				accFundSecondaryBase: $scope.cdrzfgjj, //参贷人住房公积金缴存基数
				housePrice: $scope.housePrice, //房屋价格
				loanNumb: $scope.jtdkId, //家庭贷款次数， 家庭贷款次数为0次为0、 1 次为1、 2 次及以上为2
				houseType: $scope.houseTypeId, //房屋类型 房屋类型为一手房 1、 房屋类型为二手房 2
				houseArea: $scope.houseArea, //房屋面积
				numberIdentification: $scope.tsrdId, //套数认定 0 为首套、 1 为第二套改善型、 2 为第二套非改善型
				houseGenre: $scope.houseGenreId, //房屋类别 //1为普通住房 2为非普通住房
				endYear: $scope.stYear || "", //二手房竣工年数 输入格式为“ 2012”“ yyyy” 格式
			},
			success: function(dataJson) {
				console.log(dataJson);
				$scope.isLoading = false;
				if(dataJson.state == 1001) {
					appData.queryList = dataJson.obj;
					$state.go("queryList");
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
app.controller("queryList", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.funName = appData.funName='公积金贷款试算';
	$scope.queryList = appData.queryList;
	$scope.nextText = '返回首页';
	$scope.prevStep = function() {
		$state.go('info');
	}
	$scope.nextStep = function() {
		$state.go('main');
	}
	//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: appData.funName,
		}
	}
	recordUsingHistory('住建服务', '查询', appData.funName, "", "", '', '', JSON.stringify($scope.jsonStr));
	trackEventForQuery(appData.funName, '', '查询', '上海市住房和城乡建设委员会', '', '', '');
});