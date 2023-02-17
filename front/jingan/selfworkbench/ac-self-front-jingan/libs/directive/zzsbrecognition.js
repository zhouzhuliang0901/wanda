app.directive("zzsbrecognition", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/zzsbrecognition.html",
		scope: {
			result: "&",
			faceImage: "@",
			cameraPos: "="
		},
		controller: function($scope, $location, $rootScope, $interval, $timeout,appData) {
			$scope.__cameraPos = $scope.cameraPos || {
				width: 600,
				height: 400,
				x: 680,
				y: 400
			}
			$.getConfigMsg.preUrl = "http://xzfwzx.jingan.gov.cn:8080/ac-self-sq"
			
			$scope.tipsText = "请看摄像头";
			$scope.recognitionOver = false;
			$scope.istakePhoto = true;
			$scope.$on("$destroy", function() {
				$.device.Camera_Hide();
			});
			
			$scope.getUserInfoByAccessToken = function() {
				$.ajax({
					type: "get",
					url: $.getConfigMsg.preUrl + "/selfapi/workPlatform/getUserInfoByAccessToken.do",
					dataType: "jsonp",
					jsonp: "jsonpCallback",
					data: {
						accessToken: appData.token
					},
					success: function(dataJson) {
						console.log(dataJson);
						if(dataJson != undefined && dataJson != null && dataJson != "") {
							$scope.tipsText = "正在加载数据，请稍候...";
							appData.zwdtsw_user_id = dataJson.zwdtsw_user_id
							appData.encrypt_identity = dataJson.encrypt_identity;
						}
					},
					error: function(err) {
					}
				});
			}
			
			//获取token ------2、比对成功后，根据tokenSNO获取access_token
			$scope.getAccessToken = function(tokenSNO) {
				var rec = $.ajax({
					url: $.getConfigMsg.preUrl + "/selfapi/workPlatform/getAccessToken.do",
					type: "post",
					dataType: "json",
					//					jsonp: "jsonpCallback",
					timeout: 5000,
					data: {
						tokenSNO: tokenSNO,
					},
					success: function(res) {
						console.log(res);
						if(res.SUCCESS === true) {
							appData.token = res.accessToken;
							$scope.getUserInfoByAccessToken();
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
			
			$scope.getTokenSNO = function(face, photograph) { //人证数据对比
				var idCardPhoto = face
				var capturePhoto = photograph;
				$scope.status = true;
				$scope.confirm = false;
				$scope.showImage = "../libs/common/images/recognition.png";
				$.ajax({
					url: $.getConfigMsg.preUrl + "/selfapi/workPlatform/getTokenSNO.do",
					type: "post",
					dataType: "json",
					data: {
						name: encodeURI(appData.licenseName || appData.archivesName),
						idCard: appData.licenseNumber || appData.archivesNumber,
						facePhoto: capturePhoto,
						copyIDPhoto: idCardPhoto,
						certStartTime:appData.VALIDSTARTDAY || "",
						certEndTime:encodeURI(appData.VALIDENDDAY || "")
					},
					success: function(res) {
						var n = res.Result;
						$scope.recognitionOver = true;
						if(res.SUCCESS == true) {
							//核验通过
							$scope.tipsText = $scope.tipsText1 = "核验通过";
							$scope.showImage = "../libs/common/images/success.png";
							appData.tokenSNO = res.tokenSNO;
							$scope.getAccessToken(res.tokenSNO);
						} else {
							//人证不符
							$scope.showImage = "../libs/common/images/failed.png";
							$scope.tipsText = $scope.tipsText1 = "人证不符";

						}
						$scope.$apply();
					},
					error: function(err) {
						$scope.showImage = "../libs/common/images/failed.png";
						$scope.tipsText = $scope.tipsText1 = "核验错误";
					}
				})
			};
			//初始化摄像头
			$scope.capture = function() { //拍照
				$.device.Face_Show($scope.__cameraPos.width, $scope.__cameraPos.height, $scope.__cameraPos.x, $scope.__cameraPos.y, function(info) {
					if(info) {
						$scope.showImage = "../libs/common/images/recognition.png";
						$scope.tipsText = "正在核验...";
						$scope.istakePhoto = false;
						$scope.capturePhoto = info;
						$.device.Face_Close();
						$scope.getTokenSNO($scope.faceImage, $scope.capturePhoto);
					} else if(info.Success === false) {
						$scope.tipsText = "活体检测失败";
						$.device.Face_Close();
						$scope.capture();
					}
				})
			};
			$scope.capture();
			$scope.reRecognition = function() {
				$.device.Face_Close();
				$scope.tipsText = "请看摄像头";
				$scope.recognitionOver = false;
				$scope.istakePhoto = true;
				$scope.capture();
			};
			$scope.success = function() {
				if($scope.tipsText1 === "核验通过") {
					$scope.result({
						img: $scope.capturePhoto
					});
				} else {
					$rootScope.goAppHistoryBack()
				}

			}
		}
	}
});

