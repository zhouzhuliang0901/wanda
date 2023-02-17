app.controller('choose', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.funName = "价格公示";
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.projectTypeList = projectType;
	$scope.change = function(name, index, id) {
		$scope.current = index;
		$scope.projectTypeName = name;
		appData.projectTypeId = id;
	}

	document.getElementById("lowPrice").onfocus = function() {
		console.log(2);
		$scope.show = true;
	};
	document.getElementById("highPrice").onfocus = function() {
		console.log(1);
		$scope.show = false;
	};
	$scope.lowPrice = "";
	$scope.highPrice = "";
	$scope.keybordArr = [1, 2, 3, 4, 5, 6, 7, 8, 9, '', 0, '删除'];
	$scope.inputLowPrice = function(item) { //软键盘输入
		if($scope.lowPrice == "请输入") {
			$scope.lowPrice = "";
		} else if(item === '删除') {
			$scope.lowPrice = $scope.lowPrice.substring(0, $scope.lowPrice.length - 1);
		} else {
			$scope.lowPrice += item;
		}
	}
	$scope.inputHighPrice = function(item) { //软键盘输入
		if($scope.highPrice == "请输入") {
			$scope.highPrice = "";
		} else if(item === '删除') {
			$scope.highPrice = $scope.highPrice.substring(0, $scope.highPrice.length - 1);
		} else {
			$scope.highPrice += item;
		}
	}
	$scope.prevStep = function() {
		window.location.href = "../SKYL_allItem/index.html"
	}
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.projectTypeName)) {
				$scope.isAlert = true;
				$scope.msg = "请选择作用科室！";
				return;
			}
			if(isBlank($scope.lowPrice)) {
				$scope.isAlert = true;
				$scope.msg = "请输入低价！";
				return;
			}
			if(isBlank($scope.highPrice)) {
				$scope.isAlert = true;
				$scope.msg = "请输入高价！";
				return;
			}
		} while (condFlag);
		//提交参数集合
		condFlag = true;
		appData.lowPrice = $scope.lowPrice;
		appData.highPrice = $scope.highPrice;
		appData.keyWord = $scope.keyWord;
		$scope.isLoading = true;
		$state.go("info");
	}
});
app.controller('info', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.funName = "价格公示";
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}

	$scope.chargingProjectsQuery = function(){
		$scope.isLoading = true;
		$.ajax({
			type:"get",
			url:$.getConfigMsg.preUrlSelf+"/selfapi/priceOfPublic/chargingProjectsQuery.do",
			async:true,
			dataType:"json",
			//jsonp:"jsonpCallback",
			data:{
				category:appData.projectTypeId,
				keyWord:encodeURI(appData.keyWord||""),
				minPrice:appData.lowPrice,
				maxPrice:appData.highPrice,
			},success:function(dataJsonp){
				$scope.isLoading = false;
				console.log(dataJsonp);
				if(dataJsonp.MessageHeader.code=="1"){
					$scope.queryList = dataJsonp.ResponseData.data;
				}else{
					$scope.isAlert = true;
					$scope.msg = "未查询到信息";
				}
			},error:function(err){
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
		recordUsingHistory('申康医联服务', '查询',$scope.funName, '', '', '', '', JSON.stringify($scope.jsonStr));
		//行为分析(查询)
		trackEventForQuery($scope.funName, '', "查询", "上海申康医联发展中心", '', '', "");
	}
	$scope.chargingProjectsQuery();
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