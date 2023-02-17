app.controller("idcardMain", function ($scope, $state, appData) {
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
            $state.go("choose");
        }
    }
    
    $scope.prevStep = function () {
        $state.go("../copy/index.html")
    }

    $scope.nextStep = function () {
        $scope.over = true;
    }
});
app.controller("idcardChoose", function ($scope, appData, $timeout, $state) {
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
                LODOP_PRINT.idcardPrint(appData.idcardImg.frontImg, appData.idcardImg.backImg, $scope.printQuantity);
            });
            $timeout(function () {
                $.device.GoHome();

            }, 3000);
        })
    }
    $scope.prevStep = function () {
        $state.go("main");
    }
});