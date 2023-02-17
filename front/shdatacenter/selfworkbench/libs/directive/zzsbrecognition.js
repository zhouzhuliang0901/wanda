app.directive("zzsbrecognition", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/zzsbrecognition.html",
		scope: {
			result: "&",
			faceImage: "@",
			cameraPos: "="
		},
		controller: function($scope, $location, $rootScope, $interval, $timeout) {
			$scope.showImage = "../libs/common/images/read1.png";
			$scope.__cameraPos = $scope.cameraPos || {
				width: 700,
				height: 480,
				x: 620,
				y: 340
			}
			 //初始化摄像头
			$.device.Camera_Init($scope.__cameraPos.width, $scope.__cameraPos.height, $scope.__cameraPos.x, $scope.__cameraPos.y); //初始化摄像头
			var camera = window.external.GetConfig('camera');
			var index = window.external.GetConfig('resolution') || 1;
			$.device.Camera_Link(camera, index); //初始化摄像头
			$.device.Camera_Show();
			$scope.tipsText = "请看摄像头";
			$scope.showImage = "../libs/common/images/read1.png";
			$scope.recognitionOver = false;
			$scope.$on("$destroy", function() {
				$.device.Camera_Hide();
			});
			$scope.recognition = function(face, photograph) { //人证数据对比
				var idCardPhoto = face
				var capturePhoto = photograph;
				$scope.status = true;
				$scope.confirm = false;
				$.ajax({
					url: "http://hengshui.5uban.com/ac-product-ext/ext/aci/autoterminal/compare2PhotoOld.do",
					type: "post",
					dataType: "json",
					data: {
						idCardPhoto: idCardPhoto,
						capturePhoto: capturePhoto
					},
					success: function(res) {
						var n = res.Result;
						$scope.recognitionOver = true;
						if(n==0) {
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
						$scope.showImage = "../libs/common/images/failed.png";
						$scope.tipsText = "核验错误";
					}
				})
			};
			$scope.reRecognition = function() {
				$.device.Camera_Show();
				$scope.tipsText = "请看摄像头";
				$scope.showImage = "../libs/common/images/read1.png";
				$scope.recognitionOver = false;
			};
			$scope.success = function() {
				if($scope.tipsText === "核验通过") {
					$scope.result({
						img: $scope.capturePhoto
					});
				} else {
					$rootScope.goAppHistoryBack()
				}

			}
			$scope.capture = function() { //拍照
				$scope.showImage = "../libs/common/images/recognition.png";
				$scope.tipsText = "正在核验...";
				$scope.capturePhoto = $.device.Camera_Base64();
				$.device.Camera_Hide();

				$scope.recognition($scope.faceImage, $scope.capturePhoto);
			};
		}
	}
});

