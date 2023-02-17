function removeAnimate(ele) {}

function addAnimate(ele) {
	$(ele).css({
		'margin-top': '300px',
		'opacity': '0'
	});
	$(ele).animate({
		marginTop: '0',
		opacity: '1'
	}, 1000);
}
app.controller("main", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.isLoading = true;
	$scope.weather = function() {
		$scope.changeDataWeatherList = function(obj) {
			let result = obj.slice();
			result.map(function(n) {
				let dateString = n.forecastdate.substring(0, 10);
				console.log(dateString);
				let dateArray = dateString.split("-");
				date = new Date(dateArray[0], parseInt(dateArray[1] - 1), dateArray[2]);
				$scope.weatherImg = screen(n.dayweather);
				n['week'] = "星期" + "日一二三四五六".charAt(date.getDay());
				n['today'] = dateArray[1] + "/" + dateArray[2];
				n['weatherImg'] = $scope.weatherImg;
				return n
			})
			$scope.dataWeatherList2 = result.reverse().slice(2,9);
			console.log($scope.dataWeatherList2)
		}

		//获取当天年月日
		let date = new Date();
		let month = date.getMonth() + 1;
		let day = date.getDate();
		if(month >= 1 && month <= 9) {
			month = "0" + month;
		}
		if(day >= 0 && day <= 9) {
			day = "0" + day;
		}
		let today = date.getFullYear() + "-" + month + "-" + day;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/meteorological/weatherOnThe10th.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			success: function(dataJson) {
				$scope.isLoading = false;
				if(dataJson.data.Code == 0 && dataJson.success == true && dataJson.data.Data) {
					$scope.dataWeatherList = dataJson.data.Data;
					console.log($scope.dataWeatherList);
					$scope.changeDataWeatherList($scope.dataWeatherList);
					$scope.todayWeather = fuzzyQuery($scope.dataWeatherList, today, "forecastdate")[0];
					$scope.weatherImg = screen($scope.todayWeather.dayweather);
				}
			},
			err: function(err) {}
		});
//		$scope.warningImg = "libs/common/images/warning/coldWave-blue.png";
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/meteorological/severeWeatherWarning.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			success: function(dataJson) {
				$scope.isLoading = false;
				if(dataJson.Code == 0 && dataJson.Success == true) {
					$scope.dataWarningList = dataJson.Data;
					if($scope.dataWarningList[0].state == "发布" && dataJson.Data) {
						$scope.warningImg = filterByInfo(warningList, $scope.dataWarningList[0].grade + $scope.dataWarningList[0].category, "name");
					}
				}
			},
			err: function(err) {}
		});
	}
	$scope.weather();
});