app.controller("pwaitController", function ($scope, $route, $location, $timeout) {
	var strPath = window.document.location.pathname;
	var path = strPath.substring(strPath.indexOf("/", 2), strPath.length);
	$scope.basePath = "../";
	if (path.indexOf("ext") > 0) {
		$scope.basePath = "../../../";
	}
	$scope.printQuantity = $location.search().printQuantity; //获取打印份数
	var options = {
		settings: {
			paperName: 'a4',
			orientation: 2,
			topMargin: 1,
			leftMargin: 1,
			bottomMargin: 1,
			rightMargin: 1,
			copies: $scope.printQuantity //打印次数
		}
	};
	angular.element(document).ready(function () {
		if (!$scope.printQuantity) { //如果没有传参就不进行打印
			$timeout(function () { //等待设置时间之后回到某处
				$.device.GoHome();
			}, 10000);
		} else {
			//旋转打印图片
			$.jatools.init();
			$.jatools.print(options);

			$timeout(function () { //等待设置时间之后回到某处
				$.device.GoHome();
			}, 10000 * $scope.printQuantity);

			if ($.getConfigMsg.isPiwikLog) {
				trackEvent($.getLog.MaterialCopyPrintNameSuccess, $.getLog.MaterialCopyPrintType, $.getLog.MaterialCopyPrintNumName, $scope.printNums);
			}
		}

	});
});