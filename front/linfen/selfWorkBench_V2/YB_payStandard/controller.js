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
	$scope.stuffName = perjsonStr;
	$scope.choiceType = function(type, name, ywlx) {
		logSheetAdd(name);
		appData.funName = name;
		if(type == 'idcard'){
			$state.go("searchMedicines");
		} else {
			$state.go("guideline");
		}
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			bounce: true,
			click: true,
			taps: true,
			hScroll: false,
		});
	};
	$scope.isScroll();
});
app.controller('guideline', function($state, $scope, appData, $http, $timeout) {
	removeAnimate($('.scrollBox2'))
	$scope.funName = "床位费医保支付标准";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.guideline = "<p>定点医疗机构普通病房床位费医保支付标准按C等病房床位费收费标准支付，超出C等病房床位费标准的费用由参保人员自负。护理医院病房床位费医保支付标准按4人间收费标准支付，超出4人间收费标准的费用由参保人员自负。</p>";
	$scope.guideline1 = "<p>本市医疗机构普通病房床位分等定价与医保分类给付表</p>";
	$scope.Item = payjsonStr;
	addAnimate($('.scrollBox2'))
	$scope.prevStep = function() {
		$state.go("main");
	}
	//模块使用记录
		$scope.jsonStr = {
		  	SUCCESS: "true",
		  	data: {
			   	name: '药品及床位费支付标准查询',
			  	Number: "",
			  	FL1: appData.FL1
		  	}
		}
		recordUsingHistory('医保服务', '查询', '药品及床位费支付标准查询', "","","", "", JSON.stringify($scope.jsonStr));
		trackEventForQuery('药品及床位费支付标准查询','','查询','上海市医疗保障局','','','');
	$scope.$watch('$viewContentLoaded', function(newValue, oldValue) {
		$scope.isScroll = function() {
			new iScroll("wrapper", {
				vScrollbar: true,
				hScrollbar: false,
				hideScrollbar: false,
				bounce: true,
				hScroll: false,
				checkDOMChanges: true,
				preventDefault: false,
			});
		};
		$scope.isScroll();
 	});

});
app.controller("searchMedicines", function($sce,$scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.isAlert = false;
	$scope.nextText = "返回首页";
	$scope.funName = "医保范围药品查询"
	$scope.concel = "false";
	$scope.medicineType = medicineType; // 医药分类
	appData.flag = 0; // 记录点击的是中药还是西药
	$scope.choice = false;
	$scope.showResult = false; // 是否显示结果
	$timeout(function() {
		$scope.$watch("medicinetype", function(val) {
			if(val){
				$scope.choice = true;
				if(val.id == 01) {
					//西药
					appData.flag = 0;
					appData.drugType = '1';
					$scope.bigType = filterByName($rootScope.WBType,val.key);
				} else {
					appData.flag = 1;
					appData.drugType = '2';
					$scope.bigType = filterByName($rootScope.CBType,val.key);
				}
			}
		});
		$scope.$watch("bigtype", function(val) {
			if(val){
				if(!appData.flag) {
					appData.FL1 = val.key;
					$scope.middleType = filterByName($rootScope.WMType,val.key);
				} else {
					appData.FL1 = val.key;
					$scope.middleType = filterByName($rootScope.CMType,val.key);
				}
			}
		});
		$scope.$watch("middletype", function(val) {
			if(val){
				if(!appData.flag) {
					appData.FL2 = val.key;
					$scope.smallType = filterByName($rootScope.WSType,val.key);
				} else {
					appData.FL2 = val.key;
					$scope.smallType = filterByName($rootScope.CSType,val.key);
				}
			}
		});
		$scope.$watch("smalltype", function(val) {
			if(val){
				appData.FL3 = val.key;
			}
		});
		$scope.$watch("drugName", function(val) {
			if(val){
				appData.drugName = $scope.drugName;
				console.log(appData.drugName);
			}
		});
	});

	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go('main');
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
	$scope.nextStep = function() {
		if($scope.choice){
			$state.go("medicinesInfo");
		} else {
			$scope.isAlert = true;
			$scope.msg = "请选择药品分类";
		}
	}
	$scope.alertConfirm = function(){
		$scope.isAlert = false;
	}
	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});
app.controller("medicinesInfo", function($sce,$scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.isAlert = false;
	$scope.nextText = "返回首页";
	$scope.funName = "医保范围药品查询"
	$scope.concel = "false";
	// 搜索
	/*$scope.queryList = [
					{"name":"华法林华法林华法林华法林华法林","type":"口服常释剂型","jiayi":"甲","pay":"甲类","other":"口服常释剂型口服常释剂型口服常释剂型口服常释剂型口服常释剂型口服常释剂型口服常释剂型口服常释剂型口服常释剂型口服常释剂型口服常释剂型口服常释剂型无"},
					{"name":"华法林","type":"口服常释剂型华法林华法林","jiayi":"甲","pay":"甲类","other":"无"},
					{"name":"华法林","type":"口服常释剂型","jiayi":"甲","pay":"甲类","other":"无"},
					{"name":"华法林","type":"口服常释剂型","jiayi":"甲","pay":"甲类","other":"无"},
					{"name":"华法林","type":"口服常释剂型","jiayi":"甲","pay":"甲类","other":"无"},
					{"name":"华法林","type":"口服常释剂型","jiayi":"甲","pay":"甲类","other":"无"},
					{"name":"华法林","type":"口服常释剂型","jiayi":"甲","pay":"甲类","other":"无"},
				];
	$scope.listLength = $scope.queryList.length;*/
	$scope.search = function(){
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/MedicalInstitution/queryDrugInfo.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				drugType:appData.drugType,
				drugName: encodeURI(appData.drugName || ""),
				FL1: replacePoint(appData.FL1)||"",
				FL2: replacePoint(appData.FL2)||"",
				FL3: replacePoint(appData.FL3)||"",
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				if(dataJson) {
					$scope.queryList = dataJson[0].ybfwypcxs[0].ybfwypcx;
					$scope.listLength = $scope.queryList.length;
				} else {
					$scope.isAlert = true;
					$scope.msg = "无查询结果";
				}
				//console.log(dataJson);
			},
			error: function(err) {
				$scope.isAlert = true;
				$scope.msg = "访问失败";
				$.log.debug("err:" + JSON.stringify(err))
			}
		});

		//模块使用记录
		$scope.jsonStr = {
		  	SUCCESS: "true",
		  	data: {
			   	name: '药品及床位费支付标准查询',
			  	Number: "",
			  	FL1: appData.FL1
		  	}
		}
		recordUsingHistory('医保服务', '查询', '药品及床位费支付标准查询', "","","", "", JSON.stringify($scope.jsonStr));
		trackEventForQuery('药品及床位费支付标准查询','','查询','上海市医疗保障局','','','');
	}
	$scope.search();
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go('searchMedicines');
	}
	$scope.prevStep = function() {
		$state.go("searchMedicines");
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: true,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
})
