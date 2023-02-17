app.controller("main", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择服务内容";
	if($.config.get('isResidenceRegister') == 'Y') {
		perjsonStr.push({
			"stuffName": "居住证签注",
			"type": "reduce",
			"img": "../libs/common/images/newIcon/GA.png",
			"url": "../GA_residenceRegister/index.html"
		})
	}
	$scope.stuffName = perjsonStr;
	$scope.choiceType = function(name,url) {
		appData.funName = name;
		window.location.href = url
	}
});