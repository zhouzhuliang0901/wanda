app.controller("appointmentMain", function($scope, $state, appFactory, appData, $timeout, $http) {
	$scope.itemList = [];
	$scope.operation = "请选择部门事项:";
	$scope.itemProperty = "description";
	$scope.getDepartment = function() { //获取部门
		$timeout(function() {
			appFactory.pro_fetch("reservation/getOrgans.do", {
				fmd: 'aci-reservation',
				fdo: 'getAllOrgans',
				dept: ''
			}, function(data) {
				$scope.itemList = data;
			})
		})
	}
	$scope.getDepartment();
	$scope.check = function(item) {
		appData.organId = item.organId;
		$state.go("matter");
	};
});
app.controller("appointmentMatter", function($scope, $state, $timeout, appFactory, appData, $timeout) {
	$scope.operation = "请选择办理事项";
	$scope.matterList = [];
	$scope.itemId = appData.organId || 136;
	$scope.itemProperty = "stItemName";
	$scope.nextStep = function() {
		console.log('下一步')
	}
	$scope.prev = function() {
		console.log(1)
	}
	$scope.getMatter = function(organId) {
		$timeout(function() {
			appFactory.pro_fetch("forward.do", {
				fmd: 'aci-reservation',
				fdo: 'getAllItemsByOrganId',
				organId: $scope.itemId,
				pageSize: 10,
				currentPage: undefined
			}, function(data) {
				$scope.matterList = data;
			})
		})
	};
	$scope.getMatter();
	$scope.search = function(val) {
		console.log(val);
	}
	$scope.check = function(item) {
		appData.stItemNo = item.stItemNo;
		appData.stPlaceId = item.stPlaceId;
		$state.go("date");
	};
});
app.controller("appointmentDate", function($scope, appFactory, appData, $state,$timeout) {
	$scope.operation = "请选择您要预约的日期:";
	$scope.currentCheck = null;
	$scope.msg = '';
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		console.log($scope.isAlert)
	};
	$scope.choiceTime = function(i, item) { //选择预约时段
		
		if(item && item.surplusCount >= 1) {
			appData.stDetailId = item.stDetailId;
			$scope.currentCheck = i;
		} else {
			$scope.msg = "当前时间段预约已满!";
			$scope.isAlert = true;
		}
	};
	$scope.nextStep = function() {
		if($scope.currentCheck === null) {
			$scope.msg = "请选择预约时间段";
			$scope.isAlert = true;
			console.log($scope.isAlert)
			return;
		}
		$state.go("authentication");
	};
	$timeout(function() {
		appFactory.pro_fetch("forward.do", { //获取可预约日期
			fmd: 'aci-reservation',
			fdo: 'getReservationAllDay',
			placeId: appData.stPlaceId,
			itemNo: appData.stItemNo
		}, function(data) {
			WdatePicker({ //日期插件
				eCont: 'datepicker',
				doubleCalendar: true,
				dateFmt: 'yyyy-MM-dd',
				minDate: '%y-%M-{%d+1}',
				opposite: true,
				disabledDates: data,
				onpicked: function(dp) {
					$scope.orderTimes(dp.cal.getDateStr())
					appData.selectDate = dp.cal.getDateStr();
				}
			});
		});
	});

	$scope.orderTimes = function(date) { //获取预约时段
		appFactory.pro_fetch("forward.do", {
			fmd: 'aci-reservation',
			fdo: 'getReservationAllTime',
			itemNo: appData.stItemNo,
			placeId: appData.stPlaceId,
			date: date
		}, function(result) {
			$scope.currentCheck = null;
			$scope.appointmentInfo = result;
			console.log(result)
		})
	}
});
app.controller("appointmentAuthentication", function($scope, $state, appData, appFactory) {
	$scope.operation = "第一步：请选择登陆方式";
	$scope.loginType= null;
	$scope.choiceType = function(type){
		if(type=='idcard'){
			$scope.operation = "第二步：请将身份证放置身份证读卡区";
		}else{
			$scope.operation = "第二步：请将市民云中身份证二维码放置扫码区";
		}
		$scope.loginType = type;
	};
	$scope.readCloud = function(data){
		appData.idcardNumber = data.result.data.realname;
		appData.idcardName = data.result.data.code;
		$state.go("input");
	};
	$scope.readIdcard = function(data) {

		appData.idcardNumber = data.Number;
		appData.idcardName = data.Name;
		$state.go("input");
	}
})
app.controller("appointmentInput", function($scope, $state, appData, appFactory, $http) {

	$scope.operation = "第二步：请输入您的手机号码"
	$scope.phoneNumber = "请输入手机号码";
	$scope.keybordArr = [1, 2, 3, 4, 5, 6, 7, 8, 9, '重输', 0, '删除'];
	$scope.testMobile = function(string) { //检测手机号正确性
		var standard = /^1[34578]\d{9}$/;
		if(standard.test(string)) {
			return true;
		}
		return false;
	};
	$scope.isAlert = false;
	$scope.alertConfirm = function(){
		$scope.isAlert = false;
	}
	$scope.sendAppointment = function() {
		if($scope.testMobile($scope.phoneNumber) === true) { //检测手机号码格式
			appFactory.pro_fetch("forward.do", { //发送预约
				fmd: 'aci-reservation',
				fdo: 'saveReservationInfo',
				itemNo: appData.stItemNo,
				placeId: appData.stPlaceId,
				detailId: appData.stDetailId,
				date: appData.selectDate,
				certNo: appData.idcardNumber,
				userName: appData.idcardName,
				userId: '',
				mobile: $scope.phoneNumber,
				identityType: '1',
				reservationSource: '3',
				business: '',
				unit: '',
				unified: ''
			}, function(result) {
				if(result.success == true) {
					$state.go("complete")
				} else {
					$scope.isAlert = true;
					$scope.msg = '预约失败！';
					
				}
			}, function(params) {
				alert(params)
			})
		} else {
			//手机号码格式有误
			$scope.isAlert = true;
			$scope.msg = '请输入正确手机号码';
		};
	};
	
	$scope.keyboardInput = function(e) { //键盘输入
		var keycode = window.event ? e.keyCode : e.which;
		if(keycode >= 48 && keycode <= 57) {} else if(keycode == 8) {} else {
			$scope.phoneNumber = $scope.phoneNumber.substring(0, $scope.phoneNumber.length - 1);
		}
	}
	$scope.inputNumber = function(item) { //软键盘输入
		if($scope.phoneNumber === "请输入手机号码") {
			return;
		}
		if(item === '重输') {
			$scope.phoneNumber = "";
		} else if(item === '删除') {
			$scope.phoneNumber = $scope.phoneNumber.substring(0, $scope.phoneNumber.length - 1);
		} else {
			$scope.phoneNumber += item;
		}
	}
});
app.controller("appointmentComplete", function($scope, appData) {
	$scope.operation = "您已预约成功";
	$scope.completeTips = "我们已将预约号发送至您的手机上";
})