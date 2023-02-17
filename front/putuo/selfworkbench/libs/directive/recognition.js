app.directive("recognition", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/recognition.html",
		scope: {
			result: "&",
			faceImage: "@",
			cameraPos: "="
		},
		controller: function($scope, $location, $rootScope, $interval, $timeout) {
			$scope.showImage = "../libs/common/images/read1.png";
			$scope.recognitionOver = false;
			$scope.recognition = function(face, photograph) { //人证数据对比
				var idCardPhoto = face
				var capturePhoto = photograph;

				$scope.status = true;
				$scope.confirm = false;
				$scope.showImage = "../libs/common/images/recognition.png";
				$.ajax({
					url: "http://180.169.7.194:8080/ac-product-ext/ext/aci/autoterminal/facecompare.do",
					type: "post",
					dataType: "json",
					data: {
						idCardPhoto: idCardPhoto,
						capturePhoto: capturePhoto
					},
					success: function(res) {
						var n = res.similarity;
						$scope.recognitionOver = true;
						if(n > 60) {
							//核验通过
							$scope.showImage = "../libs/common/images/success.png";
							$scope.tipsText = "核验通过";
						} else {
							//人证不符
							$scope.showImage = "../libs/common/images/failed.png";
							$scope.tipsText = "人证不符";

						}
						$scope.$apply();
					},
					error: function(err) {
						$scope.recognitionOver = true;
						$scope.showImage = "../libs/common/images/failed.png";
						$scope.tipsText = "核验错误";
					}
				})
			};
			$scope.capture = function() { //拍照
				$.device.Face_Show(640, 480, 640, 240, function(info) {
					if(window.external.GetConfig('liveDetection') == 'N') {
						if(info) {
							$scope.showImage = "../libs/common/images/recognition.png";
							$scope.tipsText = "正在核验...";
							$scope.istakePhoto = false;
							$scope.capturePhoto = info;
							$scope.recognition($scope.faceImage, $scope.capturePhoto);
							$.device.Face_Close();
						} else {
							$scope.tipsText = "活体检测失败";
							$.device.Face_Close();
							$scope.capture();
						}
					} else {
						$timeout(function() {
							info = JSON.parse(info);
							if(info.Success === true) {
								$scope.showImage = "../libs/common/images/recognition.png";
								$scope.tipsText = "正在核验...";
								$scope.istakePhoto = false;
								$scope.capturePhoto = $.device.fileBase64(info.Data.ImageUrl);
								$scope.getTokenSNO($scope.faceImage, $scope.capturePhoto);
								$.device.Face_Close();
							} else if(info.Success === false) {
								$scope.tipsText = "活体检测失败";
								$.device.Face_Close();
								$scope.capture();
							}
						}, 1000)
					}
				})
			};
			$scope.capture();
			$scope.tipsText = "请看摄像头";
			$scope.showImage = "../libs/common/images/read1.png";
			$scope.$on("$destroy", function() {
				$.device.Face_Close();
			});
			$scope.reRecognition = function() {
//				$.device.Camera_Show();
				$scope.tipsText = "请看摄像头";
				$scope.showImage = "../libs/common/images/read1.png";
				$scope.recognitionOver = false;
				$scope.capture();
			};
			$scope.success = function() {
				$.device.Camera_UnLink();
				if($scope.tipsText === "核验通过") {
					$scope.result({
						img: $scope.capturePhoto
					});
				} else {
					$rootScope.goAppHistoryBack()
				}

			}
//			$scope.capture = function() { //拍照
//				$scope.showImage = "../libs/common/images/recognition.png";
//				$scope.tipsText = "正在核验...";
//				$scope.capturePhoto = $.device.Camera_Base64();
//				$.device.Camera_Hide();
//
//				$scope.recognition($scope.faceImage, $scope.capturePhoto);
//			};
		}
	}
});