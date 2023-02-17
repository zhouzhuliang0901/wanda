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
	$scope.operation = "请选择部门";
	let curWwwPath = window.document.location.hostname;
	if($.config.get('isDataCenter') == 'Y') {
		perjsonStr.push({
			"name": "大数据中心",
			"url": "../DC_allItem/index.html",
		})
	}
	if(curWwwPath == "183.194.250.112" || curWwwPath == "zzzd.sh.gov.cn") {
		perjsonStr.splice(2, 1);
		$scope.stuffName = perjsonStr;
	} else if(curWwwPath == "10.81.16.56") {
		$scope.stuffName = perjsonStr;
	} else {
		perjsonStr.splice(2, 1);
		$scope.stuffName = perjsonStr;

	}
	$scope.choiceType = function(url, name) {
		window.location.href = url;
	}
});
app.controller("main2", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择部门";
	let curWwwPath = window.document.location.hostname;
	if(curWwwPath == "183.194.250.112") {
		perjsonStr.splice(2, 1);
		$scope.stuffName = perjsonStr;
	} else if(curWwwPath == "10.81.16.56") {
		$scope.stuffName = perjsonStr;
	} else {
		perjsonStr.splice(2, 1);
		$scope.stuffName = perjsonStr;

	}
	$scope.choiceType = function(url, name) {
		window.location.href = url;
	}
});