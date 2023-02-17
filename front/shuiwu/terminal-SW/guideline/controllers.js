app.controller("listController", function($scope, $route, $location, $http, data, $timeout) {
	data.isUpload = [];
	data.listImg = [];
	data.fileName = [];
	data.currentIndex = 0;
	$scope.currentPage = 1; // 当前页
	$scope.totalPages = null; // 总页数
	$scope.pageSize = 8; // 每页显示数量
	$scope.nativeList = false; // 是否是水务局本地列表
	$scope.organCode = null;
	$scope.matterVal = '';
	$scope.getSearchMatter = function(current) { // 查询事项
		$scope.currentPage = current || 1;
		$scope.organCode = 'search';
		var vConfig = {
			pageSize: $scope.pageSize,
			currentPage: $scope.currentPage,
			itemName: $scope.matterVal,
			jsonpCallback: "JSON_CALLBACK",
		};
		if($scope.matterVal) {
			$http.jsonp(urlHost + '/aci/declare/getCNItemListByItemNameForPage.do', {
				params: vConfig
			}).success(function(dataJson) {
				$('.sub-index').find('a:first').addClass('active').siblings().removeClass('active');
				$scope.totalPages = dataJson.totalPageCount;
				$scope.itemList = checkList(dataJson.itemSetList);
			}).error(function() {
				console.log('getOrganListForDeclarePage error')
			})
		} else {
			layer.msg('请输入事项名称');
		}
	};
	
	$scope.getMatter = function(organCode, index, current) {
		$scope.matterVal = '';
		$scope.organCode = organCode;
		$scope.currentPage = current || 1;
		$scope.active = index; // 动态添加class元素
		$scope.getOrganList(organCode);
	};
	$scope.getMatterDetail = function(itemName, itemId) {
		data.itemName = itemName;
		data.itemIdArr = itemId;
		$location.path("/matter");
	};

	// 水务局本地流程
	$scope.getOrgan = function() {
		$scope.matterVal = '';
		$scope.organList = [{	"organId": "",
								"organCode": "SHSWSH",
								"description": "上海市水务局(上海市海洋局)",
								"organName": "上海市水务局(上海市海洋局)"}];
	};
	$scope.getOrgan();
	$scope.getOrganList = function() {
		$scope.nativeList = true;
		$scope.totalPages = (itemList.length%8) == 0 ? itemList.length/8 : parseInt(itemList.length/8) + 1;
		$scope.itemList = itemList.slice(0,8);
	};
	$scope.getOrganList();
	
	$scope.nextListPage = function() {
		if($scope.totalPages == 1) {
			return;
		}
		$scope.currentPage = ($scope.currentPage >= $scope.totalPages) ? $scope.totalPages : ++$scope.currentPage;
		$scope.start = ($scope.currentPage - 1) * 8;
		$scope.end = $scope.currentPage * 8 >= itemList.length ? itemList.length : $scope.currentPage * 8
		$scope.itemList = itemList.slice($scope.start,$scope.end);
	}
	$scope.preListPage = function() {
		if($scope.totalPages == 1) {
			return;
		}
		$scope.currentPage = $scope.currentPage <= 1 ? 1 : --$scope.currentPage;
		$scope.start = ($scope.currentPage - 1) * 8 <= 0 ? 0 : ($scope.currentPage - 1) * 8;
		$scope.end = $scope.currentPage * 8;
		$scope.itemList = itemList.slice($scope.start,$scope.end);
	}
});
app.controller("matterController", function($scope, $route, $http, $location, data, $timeout) {
	$scope.allName = data.itemName;
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.pageSize = 6;
	$scope.currentPage = 1;
	$scope.totalPages = 1;
	$scope.itemList = data.itemIdArr;
	$scope.getSubItem = function(id,name) {
		data.itemId = id;
		data.statusName = name;
		$location.path("/guideline");
	};
});
app.controller("guidelineController", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	$scope.pageSize = 8;
	$scope.currentPage = 1;
	$scope.totalPages = null;
	if(name.length > 15) {
		name = name.slice(0, 15) + '...'
	}
	$scope.guideTitle = name + " -- " + data.statusName;
	$scope.itemName = name;
	//设置滚动条速度
	$scope.rollingSpeed = 500;
	$scope.getGuieInfo = function() {
		var oConfig = {
			stZhallId: data.itemId,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/declare/getCNGuideInfoByZhallId.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.guideInfo = dataJson.guide;
			$scope.clRange = dataJson.guide.clRange;
			$scope.guideInfo.clApprovalCert = JSON.stringify($scope.guideInfo.clApprovalCert).replace(/nbsp&/ig, "").replace(/"/ig, "");
		}).error(function() {
			console.log('getGuideInfoByZhallId error')
		});
	};
	$scope.getGuieInfo();
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});