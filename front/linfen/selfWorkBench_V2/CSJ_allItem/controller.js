function removeAnimate(ele) {
	$(ele).css({
		"transform": "translateY(0px)",
		"top": 0
	}).removeClass('transformto')
}

function addAnimate(ele) {
	$(ele).addClass('transformto').on("animationend", function() {
		$(ele).removeClass('transformto')
	})
}
app.controller("main", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.nameList = perjsonStr;
	$scope.showChoice = false;
////	if(jQuery.getConfigMsg.isCommunity != "N") {
//
////	}else{
		$scope.archivesList = archives;
		$scope.handleList = handles;
		$scope.otherList = other;
	// }
	$scope.isAlert = false;
	$scope.concel = "false";
	//弹出层所用 展示
	$scope.currentPro = null;
	$scope.currentCity = null;
	$scope.currentStreet = null;
	$scope.proviceList = [];
	$scope.cityList = [];
	$scope.streetList = [];
	$scope.falg = true;
	$scope.isLoading = false;

	$scope.choiceType = function(type, name, url) {
		if($scope.falg) {
			trackEvent(name);
			if(type == "iframe") {
				appData.address = url;
				$state.go("iframe");
			} else if(type == "choice") {
				appData.type = url;
				appData.funName = name;
				$state.go("choice");
			} else if(type == "choice-iframe") {
				//要查询的事项参数
				$scope.showChoice = true;
				$scope.isLoading = true;
				$scope.itemName = name;
				$scope.queryItemName = url;
				$scope.queryItemDetail($scope.queryItemName);
			} else {
				window.location.href = url;
			}
			$scope.falg = false;
		}
	}

	/*
	 * 弹出层
	 */

	//列表查询
	$scope.queryItemDetail = function(queryItemName) {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf+"/selfapi/changjiangDelta/queryItemDetail.do?",
			dataType: 'jsonp',
			jsonp: 'jsonpCallback',
			data: {
				itemName: queryItemName,
			},
			success: function(res) {
				$scope.isLoading = false;
				if(res.success == true) {
					$scope.proviceList = res.data.areas;
				}
			},
			error: function(err) {
				$scope.isLoading = false;
				console.log(err)
			}
		});
	}
	//省/市  选择
	$scope.choiceProvice = function(index, item) {
		$scope.currentPro = index;
		$scope.currentCity = null;
		$scope.currentStreet = null;
		$scope.cityList = [];
		$scope.streetList = [];
		if(item.items.length > 0 && item.areas.length == 0) {
			appData.address = item.items[0].ljbl;
			$state.go("iframe");
		} else if(item.items.length > 0 && item.areas.length > 0) {
			let List = item.areas;
			List.unshift({
				name: item.name,
				areas: [],
				items: item.items
			})
			$scope.cityList = List;
		} else if(item.items.length == 0 && item.areas.length > 0) {
			$scope.cityList = item.areas;
		}
	}
	//市/区  选择
	$scope.choiceCity = function(index, item) {
		$scope.currentCity = index;
		$scope.currentStreet = null;
		$scope.streetList = [];
		let List = [];
		if(item.items.length > 0 && item.areas.length == 0) {
			appData.address = item.items[0].ljbl;
			$state.go("iframe");
		} else if(item.items.length > 0 && item.areas.length > 0) {
			List = item.areas.map(function(n){
				return n;
			});
			List.unshift({
				name: item.name,
				areas: [],
				items: item.items
			})
			$scope.streetList = List;
		} else if(item.items.length == 0 && item.areas.length > 0) {
			$scope.streetList = item.areas;
		}
	}
	//街道/市/区/县 选择
	$scope.choiceStreet = function(index, item) {
		$scope.currentStreet = index;
		appData.address = item.items[0].ljbl;
		$state.go("iframe");
	}
	//关闭
	$scope.close = function() {
		$scope.showChoice = false;
		//初始化所有数据
		$scope.currentPro = null;
		$scope.currentCity = null;
		$scope.currentStreet = null;
		$scope.proviceList = [];
		$scope.cityList = [];
		$scope.streetList = [];
		$scope.falg = true;
	}
});
app.controller('choice', function($state, $scope, appData, $timeout, appFactory, $http) {
	$scope.funName = appData.funName;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.type = appData.type;
	$scope.choiceType = function(provice) {
		appData.address = filterByInfo(choiceList, $scope.type, provice, 'type', 'provice')[0].url;
		$state.go("iframe");
	}
	$scope.prevStep = function() {
		$state.go("choiceMode");
	}
});
app.controller("iframe", function($scope, $state, $timeout, appData, $sce, $location) {
	$scope.prevStep = function() {
		window.history.go(-1);
	}
	console.log(appData.address);
	window.external.URL_OPEN(200,180,1500,700,appData.address);
});