app.controller("synthesizeMain", function($scope, $state, appData) {
	$scope.choice = function(address) {
		appData.address = address;
		$state.go("iframe");
	};

	$scope.bankService = function() {
		$state.go("list");
	}

	$scope.goToApp = function(address) {

		if(address.indexOf("http") != -1) {
			window.location.href = address;
		} else {
			window.location.href = address;
		}
	};
});
app.controller("list", function($scope, $state, appData) {
	$scope.currentShow = [{
		"img": "images/zxjf.png",
		"tips": "在线缴费",
		"url": "https://fee.icbc.com.cn/index.jsp"
	}, {
		"img": "images/wbyy.png",
		"tips": "外币预约",
		"url": "http://corp.sh.icbc.com.cn/terminal/moneyOrder.html"
	}, {
		"img": "images/yykh.png",
		"tips": "预约开户",
		"url": "http://corp.sh.icbc.com.cn/terminal/orderAccount.html"

	}, {
		"img": "images/dfgz.png",
		"tips": "代发工资",
		"url": "http://corp.sh.icbc.com.cn/terminal/salary.html"

	}, {
		"img": "images/xwjrfw.png",
		"tips": "小微金融服务",
		"url": "http://corp.sh.icbc.com.cn/terminal/service.html"

	}, {
		"img": "images/jykd.png",
		"tips": "经营快贷",
		"url": "http://corp.sh.icbc.com.cn/terminal/manageLoan.html"

	}, {
		"img": "images/e-dkd.png",
		"tips": "e抵快贷",
		"url": "http://corp.sh.icbc.com.cn/terminal/eloan.html"

	}]
	$scope.choiceMatter = function(item) {
		appData.address = item.url;
		$state.go("iframe");
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller("synthesizeIframe", function($scope, $state, $timeout, appData, $sce) {
	$scope.prevStep = function() {
		$state.go("main");
	}
	$scope.address = $sce.trustAsResourceUrl(appData.address);
	window.external.URL_OPEN(200, 180, 1500, 700, appData.address);
});