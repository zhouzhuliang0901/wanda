app.directive("takePhoto", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/takePhoto.html",
		scope: {
			result: "&",
			type: "@",
			size: '='
		},
		controller: function($scope, $location, $state, $rootScope, $interval, $timeout) {
			$.device.audioPPlay("Realtek High Definition Audio", $.device.currentPath() + "\\resources\\audio\\cmCapture.wav");
			$scope.camera = true;
			$scope.imageData = null;
			$scope.imageUrl = null;
			$scope.captureType = $scope.type || "idcard"; //printIdcard tipsPrint
			$scope.tipsPrint = "../libs/common/images/tips/printIdcard.png";
			if($scope.captureType == 'idcard') {
				$scope.tipsPrint = "../libs/common/images/tips/printIdcard.png";
			} else if($scope.captureType == 'material') {
				$scope.tipsPrint = "../libs/common/images/tips/material.png";
			}
			// 高拍仪宽高和坐标
			$scope.Axis = $scope.size || {
				width: 550,
				height: 370,
				x: 335,
				y: 285
			};
			$scope.$on("$destroy", function() {
				$.device.cmCaptureHide();
			});
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
				$.device.audioStop();
				$scope.imageData = "data:image/png;base64," + $.device.cmCaptureCaptureBase64();
				$scope.imageUrl = $.device.cmCaptureCaptureUrl();
				if($scope.imageData != null && $scope.imageData != "data:image/png;base64," && $scope.imageData != "data:image/png;base64,undefined") {
					$.device.cmCaptureHide();
					$scope.camera = false;
				} else {
					//alert($scope.imageData)
					layer.msg('请放置文件进行拍照！');
					//					alert("请将要复印的文件放置拍照区域");
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