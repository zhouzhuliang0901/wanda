app.directive("listBox", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/listBox.html",
		scope: {
			result: "&",
			items: "=",
			prop: "@",
			rows: "@",
			pages: "@",
			current: "@",
			total: "@",
			next: "&",
			prev: "&"
		},
		controller: function($scope, $location, $state, $rootScope, $interval, $timeout) {
			$scope.current = 0;
			$scope.dataList = $scope.items;
			$scope.showProp = false;
			$scope.row = $scope.rows || 1;
			$scope.currentPage = Math.floor($scope.current) || 1;
			$scope.totalPages = Math.floor($scope.total) || 1;
			$scope.ownControl = false;
			$scope.isScroll = function() {

				new iScroll("wrapper", {
					vScrollbar: true, //是否显示垂直滚动条
					hScrollbar: false, //是否显示水平滚动条
					hideScrollbar: false, //是否隐藏滚动条
					bounce: true, //是否超过实际位置反弹，false是不反弹
					checkDOMChanges: true
				});
			};
			$scope.isScroll();
			$scope.$watch("items", function(newVal, oldVal) {
				$scope.dataList = newVal;
				if($scope.rows && $scope.rows > 1 && ($scope.dataList.length % $scope.rows) != 0) { //添加空元素排列元素
					var fakeLength = (Math.floor($scope.dataList.length / $scope.rows) + 1) * $scope.rows
					for(var i = $scope.dataList.length; i < fakeLength; i++) {
						$scope.dataList.push('')
					}
				}
				//分列 1列宽度为100% 2列为45% 3 (1%val)-1
				if($scope.dataList && ($scope.dataList[0] instanceof Object)) {
					$scope.showProp = true;
					if($scope.dataList[$scope.current] == '') { //current伪元素则重置
						$scope.current = 0;
					}
					if($scope.localProperty === undefined) {
						console.log("prop is undefined!");
					}
				}
			});
			$scope.localProperty = $scope.prop;
			$scope.$watch("prop", function(newVal, oldVal) {
				$scope.localProperty = newVal;
			});
			$scope.$watch("total", function(newVal) {
				if($scope.totalPages > 1 && ($scope.row % 1 === 0)) {
					$scope.ownControl = true;
				}
			})
			$scope.styleObj = {
				width: "100%"
			};
			$scope.$watch("rows", function(newVal) {
				if($scope.row > 1 && ($scope.row % 1 === 0)) {
					$scope.styleObj.width = (1 / $scope.row * 100 - 1) + "%";
				}
			})

			$scope.nextStep = function() {
				$scope.next();
				if($scope.currentPage < $scope.totalPages) {
					++$scope.currentPage;
				}
			};
			$scope.prevStep = function() {
				$scope.prev();
				if($scope.currentPage > 1) {
					--$scope.currentPage;
				}
			};
			$scope.choice = function(i, obj) {
				if(obj == '') {
					return;
				}
				$scope.current = i;
				$scope.result({
					item: obj
				})
			}
		}
	}
});
//<list-box items="itemList" style-obj="{'width':'1600px'}" property="''" result="check(item)"></list-box> 调用示例