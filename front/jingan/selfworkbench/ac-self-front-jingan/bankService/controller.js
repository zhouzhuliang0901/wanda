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
app.controller("mainController", function($scope, $state, appData) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.operation = "银行服务";
	$scope.choiceType = function(choiceType) {
		if(choiceType == 'huiDongNi') {
			$state.go('huiDongNi');
		} else if(choiceType == 'laborHarbor') {
			$state.go('laborHarbor');
		} else if(choiceType == 'JABranchBankOperateNetwork') {
			$state.go('JABranchBankOperateNetwork');
		}
	}
});
app.controller('huiDongNiController', function($state, $scope, appData) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.operation = "惠懂你";
	$scope.choiceType = function(choiceType) {
		if(choiceType == 'productDescription') {
			$state.go('productDescription')
		} else if(choiceType == 'funcHighlights') {
			$state.go('funcHighlights');
		} else if(choiceType == 'loanService') {
			$state.go('loanService');
		}
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller('productDescriptionController', function($scope, $http, $state, appData, appFactory) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.operation = "产品介绍";
	$scope.prevStep = function() {
		$state.go("huiDongNi");
	}
})
app.controller('funcHighlightsController', function($scope, $http, $state, appData, appFactory) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.operation = "功能亮点";
	$scope.prevStep = function() {
		$state.go("huiDongNi");
	}
})
app.controller('loanServiceController', function($scope, $http, $state, appData, appFactory) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.operation = "建行小微快贷业";
	$scope.prevStep = function() {
		$state.go("huiDongNi");
	}
})
app.controller('laborHarborController', function($scope, $http, $state, appData, appFactory) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.operation = "劳动者港湾";
	var image = new Viewer(document.getElementById('laborHarborJpg'),{
	    url: 'data-original',
	    toolbar: false,
	    title: false,
	    navbar: false,
	});
	$scope.prevStep = function() {
		$state.go("main");
	}
})
app.controller('JABranchBankOperateNetworkController', function($scope, $http, $state, appData, appFactory) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.operation = "静安支行营业网点";
	var image = new Viewer(document.getElementById('JA_branch_bank_operate_network_jpg'),{
	    url: 'data-original',
	    toolbar: false,
	    title: false,
	    navbar: false,
	});
	$scope.prevStep = function() {
		$state.go("main");
	}
})

