app.directive("appHeader", function () {
    return {
        restrict: "E",
        templateUrl: "../libs/views/header.html",
        scope: {
            goBack: "&",
            home: "&",
            customHome: "@",
            funName: "@",
        },
        controller: function ($scope, $location, $state, $rootScope, $interval, $timeout) {
            //$scope.customHome为true，在功能子页面里。 并传入$scope.home 则执行$scope.home
            $scope.moduleName = $scope.funName;
            $scope.isFunction = $location.$$path;
            $scope.isCustom = $scope.customHome || undefined;

            $scope.$on("isCustom", function (val) {
                if (val) {
                    $scope.isCustom = val;
                }
            });

            $scope.goHome = function () {
                $.device.Camera_Hide();
                $.device.cmCaptureHide();
                $.device.idCardClose();
                $.device.qrCodeClose();
                $.device.officeClose();
                try {
                    window.external.URL_CLOSE();
                } catch (e) {
                    //TODO handle the exception
                }
                $.device.GoHome();
            };
            $scope.maxCountDown = 600;
            $scope.minTime = 10;
            $scope.timer = null;
            $scope.timeCount = function () {
                $interval.cancel($scope.timer);
                $scope.timer = $interval(function () {
                    $scope.maxCountDown--;
                    if ($scope.maxCountDown < 1) {
                        //$rootScope.SisAlert = false;
                        //广播事件
                        //						$rootScope.$broadcast('changeModel','false');
                        $interval.cancel($scope.timer);
                        $scope.isAlert = true;
                        $scope.msg = "是否返回首页？";
                        $scope.alertConfirm = function () {
                            window.external.URL_CLOSE();
                            $.device.cmCaptureHide();
                            $.device.Camera_Hide();
                            $.device.qrCodeClose();
                            $.device.idCardClose();
                            $.device.officeClose();
                            $.device.GoHome();
                        }
                        $scope.alertCancel = function () {
                            $scope.isAlert = false;
                            $scope.resetCountDown();
                        }
                        $scope.minCount = function () {
                            $interval.cancel($scope.timer);
                            $scope.timer = $interval(function () {
                                $scope.minTime--;
                                if ($scope.minTime < 1) {
                                    try {
                                        window.external.URL_CLOSE();
                                    } catch (e) { }
                                    $.device.cmCaptureHide();
                                    $.device.Camera_Hide();
                                    $.device.qrCodeClose();
                                    $.device.idCardClose();
                                    $.device.officeClose();
                                    $.device.GoHome();
                                }
                            }, 1000);
                        }
                        $scope.minCount();
                    }
                }, 1000);
            }
            $scope.timeCount();
            $scope.resetCountDown = function () {
                $interval.cancel($scope.timer);
                $timeout(function () {
                    $scope.maxCountDown = 600;
                });
                $timeout(function () {
                    $scope.timeCount();
                }, 5000);
            };
            window.addEventListener("click", $scope.resetCountDown);
            window.addEventListener("touchstart", $scope.resetCountDown);
            window.addEventListener("input", $scope.resetCountDown);
            $rootScope.$on("$viewContentLoaded", function () {
                if (!$scope.moduleName) {
                    try {
                        $scope.moduleName = $state.$current.data.title;
                    } catch (e) {

                    }
                }
            })

            //最小化窗口
            $scope.proMinimizes = function(){
            	console.log(1);
            	$.device.proMinimizes();
            }
        }
    }
});

app.directive("apptbHeader", function () {
    return {
        restrict: "E",
        templateUrl: "../libs/views/header.html",
        scope: {
            funName: "@",
        },
        controller: function ($scope, $rootScope) {
            //$scope.customHome为true，在功能子页面里。 并传入$scope.home 则执行$scope.home
            $scope.moduleName = $scope.funName;
//          $scope.isFunction = $location.$$path;
//          $scope.isCustom = $scope.customHome || undefined;
//
//          $scope.$on("isCustom", function (val) {
//              if (val) {
//                  $scope.isCustom = val;
//              }
//          });

            $rootScope.$on("$viewContentLoaded", function () {
                if (!$scope.moduleName) {
                    try {
                        $scope.moduleName = $state.$current.data.title;
                    } catch (e) {

                    }
                }
            })
            //最小化窗口
            $scope.proMinimizes = function(){
            	console.log(1);
            	$.device.proMinimizes();
            }
        }
    }
});