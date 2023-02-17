app.controller('choose', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.funName = "交通卡服务网点查询";
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.serviceTypeList = serviceType;
	$scope.cardTypeList = cardType;
	$scope.queryList = resultList;
	$scope.check = function() {
		for(let i = 0; i < $scope.queryList.length; i++) {
			if(!isBlank($scope.queryList[i].jiaotongka)) {
				$scope.queryList[i].jiaotongka = $scope.queryList[i].jiaotongka.toString();
			} else {
				$scope.queryList[i].jiaotongka = "";
			}
			if(!isBlank($scope.queryList[i].hutongka)) {
				$scope.queryList[i].hutongka = $scope.queryList[i].hutongka.toString();
			} else {
				$scope.queryList[i].hutongka = "";
			}
			if(!isBlank($scope.queryList[i].lvyouka)) {
				$scope.queryList[i].lvyouka = $scope.queryList[i].lvyouka.toString();
			} else {
				$scope.queryList[i].lvyouka = "";
			}
		}
	}
	$scope.check();
	$scope.prevStep = function() {
		window.location.href = "../JTW_allItem/index.html"
	}
	Array.prototype.remove = function(val) {
		var index = this.indexOf(val);
		if(index > -1) {
			this.splice(index, 1);
		}
	};
	let service = [];
	let card = [];
	$scope.query = function() {
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf+"/selfapi/transportationCardServicePlace/queryServicePlace.do",
			async: true,
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				serviceType: service.toString(),
				cardType: card.toString(),
				keyword: encodeURI($scope.keyWord || "")
			},
			success: function(dataJsonp) {
				console.log(dataJsonp);
				$scope.isLoading = false;
				if(!isBlank(dataJsonp)) {
					$scope.queryList = dataJsonp;
					$scope.check();
				} else {
					$scope.isAlert = true;
					$scope.msg = "未查询到信息"
				}
			},
			error: function(err) {
				console.log(err);
			}
		});
		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: $scope.funName,
				Number: "",
			}
		}
		recordUsingHistory('交通委服务', '查询', $scope.funName, '', '', '', '', JSON.stringify($scope.jsonStr));
		//行为分析(查询)
		trackEventForQuery($scope.funName, '', "查询", "上海市交通委员会", '', '', "");
	}
	$scope.change = function(type, event, id) {
		if($(event.target).attr('class') == 'ng-binding ng-scope in') {
			$(event.target).removeClass("in");
			if(type == "service") {
				service.remove(id);
			} else if(type == "card") {
				card.remove(id);
			}
		} else {
			$(event.target).addClass("in");
			if(type == "service") {
				service.push(id);
			} else if(type == "card") {
				card.push(id);
			}
		}
		$scope.query();
	}
});