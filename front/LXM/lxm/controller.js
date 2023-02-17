app.controller("main", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.vaccinated = false;
	$scope.startScan = function(callback) {
		try {
			//			window.Config.startScan();
			window.scanCallBack = function(data) {
				callback(data);
			}
		} catch(e) {}
	}
	//	$scope.startScan = function() {
	//		try {
	//			window.Config.endScan()
	//		} catch(e) {}
	//	}
	//姓名脱敏
	function noPassByName(str) {
		if(null != str && str != undefined) {
			if(str.length < 3) {
				return '*' + str.substring(1, str.length);
			} else if(str.length >= 3 && str.length <= 6) {
				return '**' + str.substring(2, str.length)
			}
		} else {
			return ""
		}
	}

	$scope.startScan(function(res) {
		res = JSON.parse(res);
		var code = res.SCAN_BARCODE1.replace(/[\r\n]/g, "");
		$.ajax({
			type: "post",
			url: "http://180.169.7.194:8081/ac-self/selfapi/offlineCode/checkOfflineCodeStatus.do",
			dataType: 'json',
			data: {
				userId: "95b9420e-931d-458f-aae4-a2bb92461ba8",
				userName: encodeURI("肖邦"),
				idCard: "429004199312101138",
				phone: "13545161135",
				codeUrl: encodeURIComponent(code)
			},
			success: function(res) {
				if(res.code == '0') {
					$scope.xm = noPassByName(res.data.xm);
					$scope.zjhm = res.data.zjhm;
					$scope.photo = 'data:image/png;base64,' + res.data.photo[0].img;
					$scope.overduetime = res.data.overduetime;
					$scope.checktime = res.data.checktime;
					$scope.type = res.data.type;
					switch($scope.type) {
						case '00':
							$scope.typePng = 'img/codeGreen.png';
							break;
						case '01':
							$scope.typePng = 'img/codeYellow.png';
							break;
						case '10':
							$scope.typePng = 'img/codeRed.png';
							break;
					}
					$scope.$apply();
				} else {
					alert(res.message)
				}
			},
			error: function(err) {
				alert("ERROR" + JSON.stringify(err));
			}
		});
	});

	$scope.getTemperature = function() {
		try {
			$scope.temperature = window.Config.getTemperature().toFixed(1);
			if($scope.temperature > 37) {
				$('.red').css('color', 'red');
			}else{
				$('.red').css('color', '#14d97f');
			}
		} catch(e) {}
	}

});