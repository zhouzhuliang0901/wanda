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

app.controller('copyMain', function($state, $scope, appData, $http) {
	removeAnimate($('.linkBox1'))
	$scope.operation = '请选择复印类型';
	$scope.choiceCopyType = function(copyType) {
		if(copyType == 'idcardCopy') {
			$state.go("idcardCopy");

		} else if(copyType == 'materialsCopy') {
			$state.go("materialsCopy");
		}
	};
	addAnimate($('.linkBox1'))
});

app.controller("idcardCopy", function($scope, $state, appData) {
	appData.idcardImg = {};
	$scope.operation = "请拍摄身份证正面";
	$scope.over = false;
	$scope.getImg = function(img, url) {
		if(appData.idcardImg.frontImg == undefined) {
			appData.idcardImg.frontImg = img;
			appData.idcardImg.frontUrl = url;
			$scope.operation = '请拍摄身份证反面';
		} else {
			$scope.over = true;
			appData.idcardImg.backImg = img;
			appData.idcardImg.backUrl = url;
			$scope.operation = '请拍摄身份证正面';
			$state.go("idcardPrint");
		}
	}

	$scope.prevStep = function() {
		$state.go("main")
	}

	$scope.nextStep = function() {
		$scope.over = true;
	}
});

app.controller("idcardPrint", function($scope, appData, $timeout, $state) {
	$scope.frontImg = appData.idcardImg.frontImg;
	$scope.backImg = appData.idcardImg.backImg;
	$scope.printQuantity = 1;
	$scope.isPrint = false;
	$scope.minus = function() {
		if($scope.printQuantity > 1) {
			--$scope.printQuantity;
		}
	};
	$scope.plus = function() {
		if($scope.printQuantity < 5) {
			++$scope.printQuantity;
		}
	};
	$scope.print = function() {
		//模块使用记录
		recordUsingHistory('身份证复印', '打印', '身份证复印', '', '', '', '', '');
		//行为分析(查询)
		trackEventForQuery("身份证复印", "", "打印", "", "", "", "");
		$timeout(function() {
			$scope.isPrint = 'show';
			$timeout(function() {
				LODOP_PRINT.idcardPrint(
					appData.idcardImg.frontImg,
					appData.idcardImg.backImg,
					$scope.printQuantity
				);
			});
			saveDeviceStatus("A4Printer", 0, "正常", 0, 0, 0, 1);

			$timeout(function() {
				$.device.GoHome();

			}, 3000);
		})
	}
	$scope.prevStep = function() {
		$state.go("idcardCopy");
	}
});

app.controller("materialsCopy", function($scope, $timeout, $state, appData, $rootScope) {
	$scope.isPrint = false;
	$scope.printShow = null;
	$scope.materialData = null;
	$scope.getImg = function(img, url) {
		if(img) {
			$scope.materialData = img;
			appData.materialImg = img;
			appData.material = url;
			$scope.isPrint = true;
			$state.go("materialsPrint");
		}
	};
});

app.controller("materialsPrint", function($scope, $timeout, $state, appData, $rootScope) {
	$scope.materialImg = appData.materialImg;
	$scope.printQuantity = 1;
	$scope.minus = function() {
		if($scope.printQuantity > 1) {
			--$scope.printQuantity;
		}
	};
	$scope.plus = function() {
		if($scope.printQuantity < 5) {
			++$scope.printQuantity;
		}

	};
	$scope.print = function() {
		//模块使用记录
		recordUsingHistory('材料复印', '打印', '材料复印', '', '', '', '', '');
		//行为分析(查询)
		trackEventForQuery("材料复印", "", "打印", "", "", "", "");
		$scope.printShow = 'show';
		$timeout(function() {
			LODOP_PRINT.materialPrint(appData.materialImg, $scope.printQuantity);
			saveDeviceStatus("A4Printer", 0, "正常", 0, 0, 0, 1);
		})
		$timeout(function() {
			$.device.GoHome();
		}, 3000);
	}
	$scope.prevStep = function() {
		$state.go("materialsCopy");
	}
})