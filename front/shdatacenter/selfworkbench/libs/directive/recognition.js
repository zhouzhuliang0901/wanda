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
			$scope.__cameraPos = $scope.cameraPos || {
				width: 640,
				height: 480,
				x: 640,
				y: 240
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
				var rec = $.ajax({
					url: "http://hengshui.5uban.com/ac-product-ext/ext/aci/autoterminal/compare2PhotoOld.do",
					type: "post",
					dataType: "json",
					timeout:10000,
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
					},complete : function(XMLHttpRequest,status){ //请求完成后最终执行参数
				　　　　if(status=='timeout'){//超时,status还有success,error等值的情况
					 　　　　rec .abort();
					　　　　$scope.showImage = "../libs/common/images/failed.png";
							$scope.tipsText = "核验超时";
				　　　　}
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