app.directive("fromtakePhoto", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/fromtakePhoto.html",
		scope: {
			result: "&",
			type: "@",
			size: '='
		},
		controller: function($scope, $location, $state, $rootScope, $interval, $timeout) {
			$scope.camera = true;
			$scope.imageData = null;
			$scope.imageUrl = null;
			$scope.captureType = $scope.type || "idcard";//printIdcard tipsPrint
			$scope.tipsPrint = "../libs/common/images/idcard-small.png";
			if($scope.captureType != 'idcard'){
				$scope.tipsPrint = "../libs/common/images/tips/material.png";
			}
			$scope.Axis = $scope.size || {
				width: 800,
				height: 460,
				x: 226,
				y: 244
			};
			$scope.$on("$destroy", function() {
				$.device.cmCaptureHide();
			});
			$scope.alertConfirm = function(){
				$scope.isAlert = false;
			}
			/*
			 打开高拍仪
			 * */
			$scope.$watch("captureType", function(val) {
				if(val === 'idcard') {
					$.device.cmCaptureShow($scope.Axis.width, $scope.Axis.height, $scope.Axis.x, $scope.Axis.y);
					 $.device.cmCaptureSelectRect(0, 0, 100, 100);
					//$.device.cmCaptureSelectRect(0, 0, 1920, 1920);

				} else {
					$.device.cmCaptureShow($scope.Axis.width, $scope.Axis.height, $scope.Axis.x, $scope.Axis.y);
					$.device.cmCaptureSelectRect(0, 0, 1920, 1920);
				}
			});
			$scope.$watch("camera", function(val) {
				if(val === true) {
					if($scope.captureType === 'idcard') {
						$.device.cmCaptureShow($scope.Axis.width, $scope.Axis.height, $scope.Axis.x, $scope.Axis.y);
						 $.device.cmCaptureSelectRect(0, 0, 100, 100);						
						//$.device.cmCaptureSelectRect(0, 0, 1920, 1920);
					} else {
						$.device.cmCaptureShow($scope.Axis.width, $scope.Axis.height, $scope.Axis.x, $scope.Axis.y);
						$.device.cmCaptureSelectRect(0, 0, 1920, 1920);
					}
				}
			});

			$scope.capture = function() {
				$scope.imageData = "data:image/png;base64," + $.device.cmCaptureCaptureBase64();
				$scope.imageUrl = $.device.cmCaptureCaptureUrl();
				if($scope.imageData != null && $scope.imageData != "data:image/png;base64," && $scope.imageData != "data:image/png;base64,undefined") {
					$.device.cmCaptureHide();
					$scope.camera = false;
				} else {
					//alert($scope.imageData)
					alert("请将要复印的文件放置拍照区域");
				}
			};
			$scope.reCapture = function() {
				$scope.camera = true;
				$scope.imageData = null;
			};
			$scope.confirm = function() {
				$scope.result({ //返回图片数据
					img: $scope.imageData,
					url: $scope.imageUrl
				});
				$scope.camera = true;
				$scope.imageData = null;
			};
		}
	}
});