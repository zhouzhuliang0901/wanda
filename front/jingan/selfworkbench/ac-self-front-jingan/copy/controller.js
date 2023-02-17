function removeAnimate(ele){
	$(ele).css({"transform":"translateY(0px)","top":0}).removeClass('transformto')
}
function addAnimate(ele){
	$(ele).addClass('transformto').on("animationend",function(){
		$(ele).removeClass('transformto')
	})
}

app.controller('copyMain', function ($state, $scope, appData, $http) {
	removeAnimate($('.linkBox1'))
	$scope.operation = '请选择复印类型';
	$scope.choiceCopyType = function (copyType) {
		if (copyType == 'idcardCopy') {
			$state.go("idcardCopy");
			
		} else if (copyType == 'materialsCopy') {
			$state.go("materialsCopy");
		}
	};
	addAnimate($('.linkBox1'))
});

app.controller("idcardCopy", function ($scope, $state, appData, $location) {
    appData.idcardImg = {};
    $scope.operation = "请拍摄身份证正面";
    $scope.over = false;
    $scope.getImg = function (img, url) {
        if (appData.idcardImg.frontImg == undefined) {
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
    
    $scope.prevStep = function () {
//      $state.go("../main")
		$location.path('../index.html')
//		window.location.href = '../index.html';
        $scope.$apply();
    }

    $scope.nextStep = function () {
        $scope.over = true;
    }
});

app.controller("idcardPrint", function ($scope, appData, $timeout, $state) {
    $scope.frontImg = appData.idcardImg.frontImg;
    $scope.backImg = appData.idcardImg.backImg;
    $scope.printQuantity = 1;
    $scope.isPrint = false;
    $scope.minus = function () {
        if ($scope.printQuantity > 1) {
            --$scope.printQuantity;
        }
    };
    $scope.plus = function () {
        if ($scope.printQuantity < 5) {
        	++$scope.printQuantity;
        }
    };
    $scope.print = function () {
        $timeout(function () {
            $scope.isPrint = 'show';
            $timeout(function () {
                LODOP_PRINT.idcardPrint(
                	appData.idcardImg.frontImg, 
                	appData.idcardImg.backImg, 
                	$scope.printQuantity
                );
            });
            $timeout(function () {
                $.device.GoHome();

            }, 3000);
        })
    }
    $scope.prevStep = function () {
        $state.go("idcardCopy");
    }
});

app.controller("materialsCopy", function($scope, $timeout, $state, appData, $rootScope) {
	$scope.operation = '材料复印';
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
		$scope.printShow = 'show';
		$timeout(function() {
			LODOP_PRINT.materialPrint(appData.materialImg, $scope.printQuantity);
		})
		$timeout(function() {
			$.device.GoHome();
		}, 3000);
	}
	$scope.prevStep = function() {
		$state.go("materialsCopy");
	}
})

