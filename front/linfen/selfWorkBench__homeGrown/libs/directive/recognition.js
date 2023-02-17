app.directive("recognition", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/recognition.html",
		scope: {
			result: "&",
			faceImage: "@",
			cameraPos: "="
		},
		controller: function($scope, $location, $rootScope, $interval, $timeout, appData) {
			$scope.showImage = "../libs/common/images/recognition-shexiangtou.png";
			$scope.isAlert = false;
			$scope.concel = "false";
			$scope.__cameraPos = $scope.cameraPos || {
				width: 640,
				height: 480,
				x: 640,
				y: 240
			}

			//初始化摄像头
			//			$.device.Camera_Init($scope.__cameraPos.width, $scope.__cameraPos.height, $scope.__cameraPos.x, $scope.__cameraPos.y); //初始化摄像头
			//			var camera = window.external.GetConfig('camera');
			//			var index = window.external.GetConfig('resolution') || 1;
			//			$.device.Camera_Link(camera, index); //初始化摄像头
			//			$.device.Camera_Show();

			// 检测屏幕宽高, 调整摄像头宽高和坐标
			if(window.innerWidth <= 1600) {
				$scope.__cameraPos.height = 450;
				$scope.__cameraPos.x = 350;
			}
			if(window.innerWidth == 1366&&acBridgeMac.vendor() == 'wonders'){
				$scope.__cameraPos.y = 180;
			}
			$scope.capture = function() { //拍照
				setTimeout(function(){
					$scope.showImage = "../libs/common/images/recognition.png";
				},12000)
				$.device.Face_Show($scope.__cameraPos.width, $scope.__cameraPos.height, $scope.__cameraPos.x, $scope.__cameraPos.y, function(info) {
					if(info) {
						$scope.showImage = "../libs/common/images/recognition.png";
						$scope.tipsText = "正在核验...";
						$scope.istakePhoto = false;
						$scope.capturePhoto = info;
						$.device.Face_Close();
						//				if(appData.sign == "token") {
						$scope.getTokenSNO($scope.faceImage, $scope.capturePhoto);
						//				} else {
						//					$scope.recognition($scope.faceImage, $scope.capturePhoto);
						//				}
					} else if(info.Success === false) {
						$scope.tipsText = "活体检测失败";
						$.device.Face_Close();
						$scope.capture();
					}
				})
			};
			$scope.capture();
			$scope.tipsText = "请看摄像头";
			//$scope.showImage = "../libs/common/images/read1.png";
			$scope.recognitionOver = false;
			$scope.istakePhoto = true;
			$scope.$on("$destroy", function() {
				$.device.Camera_Hide();
			});
			//获取token ------2、比对成功后，根据tokenSNO获取access_token
			$scope.getAccessToken = function(tokenSNO) {
				var rec = $.ajax({
					url: $.getConfigMsg.preUrl + "/aci/workPlatform/getAccessToken.do",
					type: "post",
					dataType: "jsonp",
					jsonp: "jsonpCallback",
					timeout: 5000,
					data: {
						tokenSNO: tokenSNO,
					},
					success: function(res) {
						console.log(res);
						if(res.SUCCESS === true) {
							appData.token = res.accessToken;
						} else {
							rec.abort();
						}
					},
					error: function(err) {
						console.log(err);
					},
					complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
						　　　　
						if(status === 'timeout' || status === 'error') { //超时,status还有success,error等值的情况
							　　　　
							rec.abort();　　　　
						}　　
					}
				})
			}
			//获取token ------1、两照对比获取tokenSNO
			$scope.getTokenSNO = function(face, photograph) {
				var idCardPhoto = face;
				if(appData.licenseType == "rs") {
					var capturePhoto = photograph;
				} else {
					var capturePhoto = photograph;
				}
				$scope.status = true;
				$scope.confirm = false;
				$scope.showImage = "../libs/common/images/recognition.png";
				$.ajax({
					url: $.getConfigMsg.preUrl + "/aci/workPlatform/getTokenSNO.do",
					type: "post",
					dataType: "json",
					//jsonp: "jsonpCallback",
					data: {
						name: appData.licenseName || appData.archivesName,
						idCard: appData.licenseNumber || appData.archivesNumber,
						facePhoto: capturePhoto,
						copyIDPhoto: idCardPhoto
					},
					success: function(res) {
						$.device.Face_Close();
						console.log(res);
						$scope.recognitionOver = true;
						if(res.SUCCESS === true && res.verify === 1) {
							//核验通过
							$scope.showImage = "../libs/common/images/success.png";
							$scope.tipsText = "核验通过";
							appData.tokenSNO = res.tokenSNO;
							$rootScope.tokenSNO = res.tokenSNO;
							$scope.getAccessToken(res.tokenSNO);
						} else if(res.SUCCESS === false && res.verify === 1) {
							$scope.isAlert = true;
							$scope.msg = "该人员无实名认证，请至“市民网上实名认证”认证后再继续办理。";
							$scope.alertConfirm = function() {
								//$state.go("loginType");
								$.device.GoHome();
							}
						}else {
							//人证不符
							$scope.showImage = "../libs/common/images/failed.png";
							$scope.tipsText = "人证不符";
						}
					},
					error: function(err) {
						$scope.showImage = "../libs/common/images/failed.png";
						$scope.tipsText = "核验错误";
						console.log(err);
					}
				})
			}
			$scope.reRecognition = function() {
				$.device.Face_Close();
				$scope.tipsText = "请看摄像头";
				$scope.showImage = "../libs/common/images/read1.png";
				$scope.recognitionOver = false;
				$scope.istakePhoto = true;
				$scope.capture();
			};
			$scope.success = function() {
				$.device.Camera_Hide();
				if($scope.tipsText === "核验通过") {
					$scope.result({
						img: $scope.capturePhoto
					});
				} else {
					$rootScope.goAppHistoryBack()
				}

			}
			$scope.$on("$destroy", function() {
				$.device.Face_Close();
				console.log("已离开当前页面");	
			});

		}
	}
});