app.controller("mainController", function ($scope, $route, $location, $http, data) {
	//选择查询方式
	$scope.searchType = function (cursel) {
		$location.path("/input").search({
			type: cursel
		})
	};
});
app.controller("inputController", function ($scope, $route, $location, $http, data, $timeout) {
	$scope.loading = "";
	$scope.searchType = $location.search().type;
	$scope.tips = null;
	if ($scope.searchType == "身份证") {
		$scope.tips = "请插入身份证";
		PROMISE_METHOD.getIdCardInfo()
			.then(function (result) {
				return PROMISE_METHOD.fetchGet("/aci/autoterminal/forward.do", {
					fmd: 'aci-eventquery',
					fdo: 'getApplyInfoByStIdCardForPage',
					stIdCard: JSON.parse(result.identityInfo).Code
				})
			})
			.then(function (result) {
				data.details = result;
				data.detail = undefined;
				layer.close(loading_box);
				if (!result) {
					alert("暂无您的办件信息！");
					return;
				};
				if (result) {
					$location.path("/list");
				} else {
					$location.path('/hint');
				}
				$scope.$apply();
			})
			.catch(function (err) {
				console.log(err)
			})
	} else if ($scope.searchType == "办件名称和编号") {
		$scope.tips = "请输入办件编号";
		$scope.name = '';
		$scope.number = '';
		$scope.eventquerySearch = function () {
// 			if ($scope.name == '' || $scope.name == undefined) {
// 				alert("请输入申请对象名称！");
// 				return;
// 			};
			if ($scope.number.length <= 4) {
				alert("请输入正确的办事编码！");
				return;
			};
			var loading_box = layer.load(2);
			PROMISE_METHOD.fetchGet("/aci/autoterminal/forward.do", {
					fmd: 'aci-eventquery',
					fdo: 'getWorkApplyInfo',
					stApplyNo: $scope.number,
				})
				.then(function (result) {
					layer.close(loading_box);
					
					if (!result) {
						alert("此记录没有对应信息！");
						return;
					};

					data.detail = result;
					data.details = undefined;
					if (result) {
						$location.path('/detail');
					} else {
						$location.path('/hint');
					}
					$scope.$apply();
				})
				.catch(function (err) {
					console.log(err)
				})
		};
	} else if ($scope.searchType == "二维码") {
		$scope.tips = "请在扫描口扫描二维码";
		var loading_box = null;



		PROMISE_METHOD.getQrCodeInfo()
			.then(function (result) {
				loading_box = layer.load(2);
				var applyNo = getCodeApplyNo(result,'bid');

				return PROMISE_METHOD.fetchGet('/aci/autoterminal/forward.do', {
					fmd: 'aci-eventquery',
					fdo: 'getApplyInfoByStApplyNo',
					stApplyNo: applyNo
				})
			})
			.then(function (result) {
				data.details = result;
				layer.close(loading_box);
				if (result) {
					$location.path('/detail/:');
				} else {
					$location.path('/hint');
				}
				$scope.$apply();
			})
			.catch(function (err) {
				console.log(err);
			})

	}

})
//身份证获取列表
app.controller("listController", function ($scope, $route, $location, $http, data) {

	$scope.totalLength = data.details.length; //办件个数
	$scope.total_page = Math.ceil($scope.totalLength / 3); //办件页数
	$scope.now_page = 1;
	$scope.arr = []; //办件个数数组
	$scope.arr2 = []; //办件个数分页数组

	//初始化
	for (var i = 0; i < $scope.totalLength; i++) {
		var detail_id = data.details[i].stApplyNo;
		$scope.arr[i] = {
			"No": i,
			"stApplyNo": data.details[i].stApplyNo,
			"stItemName": data.details[i].stItemName,
			"stFinishStr": data.details[i].stFinishStr
		};
	};
	$scope.change = function (arr, n) {
		for (var i = 0; i < $scope.total_page; i++) {
			$scope.arr2[i] = arr.slice(i * n, i * n + n);
		}
		return $scope.arr2;
	};
	$scope.change($scope.arr, 3);
	$scope.list = $scope.arr2[0];

	$scope.right = function () {
		if ($scope.now_page < $scope.total_page) {
			$scope.now_page += 1;
			$scope.list = $scope.arr2[$scope.now_page - 1];
		}
	};
	$scope.left = function () {
		if ($scope.now_page > 1) {
			$scope.now_page -= 1;
			$scope.list = $scope.arr2[$scope.now_page - 1];
		}
	};
	$scope.back = function () {
		$location.path("/main");
	}
});
//身份证详情&二维码&办件详情
app.controller("detailController", function ($scope, $route, $location, $http, data, $routeParams, $rootScope) {
	if (data.details != undefined) {
		for (i in data.details) {
			if (data.details[i].stApplyNo == $routeParams.detailID) {
				$scope.list = data.details[i];
			}
		}
	}
	if (data.detail != undefined) {
		$scope.list = data.detail;
	}
	console.log($scope.list)
	$scope.back = function () {
		$location.path("/input")
	}
});
//错误页面控制器
app.controller("hintController", function ($scope, $route, $location) {
	$scope.backIndex = function () {
		$location.path("/main");
	}
});