app.directive("recognization", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/recognization.html",
		terminal: true,
		scope: {
			result: "&?",
			idCardInfo: "="
		},
		controller: function($scope, $location, $rootScope, $interval, $timeout, data) {
			var tokenSNO = 0; // 认证对比返回的tokenSNO
			var accessToken = 0; // access_token值
			$scope.captureImage = null; //拍照照片

			//	 打开前置摄像头
			try {
				OcxControl.cameraOpen({
					height: "520",
					width: "740",
					left: "865",
					top: "320"
				}, function(data) {
					console.log(data)
				}, function(err) {
					console.log(err)
				});
			} catch(e) {}

			$scope.status = false; //拍照OR核验
			$scope.complete = false; //核验完成
			$scope.completeTips = false; // 核验结果信息
			$scope.completeError = false; // 核验失败 重新核验按钮
			$scope.confirm = true; //确认拍照图片
			$scope.tipsText = "对比通过"; //核验完成提示
			$scope.HeadImg = $.device.fileBase64("C:/weirong/atap/bin/Front_head.bmp");
			$scope.capture = function() {
				$scope.status = true;
				OcxControl.cameraPhotograph(function(res) {
					$scope.captureImage = $.device.fileBase64("D:/cameraPhotograph.png");
					$timeout(function() {
						$scope.captureImage = res;
						$scope.compressedImg();
					}, 100);
				}, function(err) {})
			}

			$scope.data = {
				name: data.idCardName,
				idCard: data.idCardNum,
				copyIDPhoto: data.HeadImg
			};
			$scope.dataString = JSON.stringify($scope.data);
			// 走token之后的人证数据对比
			$scope.tokenRecognition = function() {
//				console.log("getTokenSNO.do========进来了");
//				console.log("getTokenSNO.do========$scope.idCardInfo.idCardName"+$scope.idCardInfo.idCardName);
//				console.log("getTokenSNO.do========$scope.idCardInfo.idCardNum"+$scope.idCardInfo.idCardNum);
//				console.log("getTokenSNO.do========$scope.compressedImage"+$scope.compressedImage);
//				console.log("getTokenSNO.do====json====$scope.HeadImg"+JSON.stringify($scope.HeadImg));
//				$.log.debug($scope.compressedImage);
				$scope.status = true;
				$scope.confirm = false;
				// 人证对比接口返回tokenSNO
				$.ajax({
					url: $.getConfigMsg.declareUrl + "/aci/workPlatform/getTokenSNO.do",
//					url: "http://180.169.7.194:8080/ac-product/aci/workPlatform/getTokenSNO.do",
					type: "post",
					dataType: "json",
					data: {
						name: $scope.idCardInfo.idCardName || data.idCardName,
						idCard: $scope.idCardInfo.idCardNum || data.idCardNum,
						facePhoto: $scope.compressedImage,
						copyIDPhoto: encodeURI($scope.HeadImg),
					},
					success: function(res) {
						console.log("getTokenSNO.do========success");
						tokenSNO = res.tokenSNO;
						console.log("getTokenSNO.do=====res-==="+JSON.stringify(res))
						$timeout(function() {
							$scope.status = false;
							if(res.verify == 1) {
								//对比通过,去获取access_token
								$scope.getAccessToken();
								$scope.completeTips = true;
								$scope.complete = true;

							} else {
//								//人证不符
								$scope.tipsText = "人证不符";
								$scope.completeTips = true;
								$scope.complete = false;
								$scope.completeError = true;
							}
						}, 10);
					},
					error: function(err) {
						console.log("getTokenSNO.do========error");
						$timeout(function() {
							$scope.tipsText = "人证不符";
						}, 10);
					}
				});
			};

			$scope.compressedImg = function() {
				console.log("CompressedImg.do========进来了");
				$.ajax({
					url: $.getConfigMsg.declareUrl + "/aci/workPlatform/util/CompressedImg.do",
//					url: "http://180.169.7.194:8080/ac-product/aci/workPlatform/util/CompressedImg.do",
					type: "post",
					dataType: "text",
					data: {
						imgFile: encodeURI($scope.captureImage),
					},
					success: function(res) {
						console.log("CompressedImg.do========success");
						$scope.compressedImage = res.replace(/[\r\n]/g, "");
						$scope.tokenRecognition();
					},
					error: function(err) {
						console.log("CompressedImg.do========error");
						$scope.compressedImage = "";
						$scope.tokenRecognition();
					}
				});
			};

			// 调用接口获取access_token
			$scope.getAccessToken = function() {
				console.log("getAccessToken.do========进来了")
				$.ajax({
					url: $.getConfigMsg.declareUrl + '/aci/workPlatform/getAccessToken.do',
//					url: 'http://180.169.7.194:8080/ac-product/aci/workPlatform/getAccessToken.do',
					type: "post",
					dataType: "jsonp",
					jsonp: "jsonpCallback",
					data: {
						tokenSNO: tokenSNO
					},
					success: function(res) {
						console.log("getAccessToken.do========success");
						// 得到access_token
						accessToken = res.accessToken;
					},
					error: function(e) {
						console.log("getAccessToken.do=======success");
						alert("没有获取到access_oken");
					}
				})
			}

			$scope.finish = function() { // 核验完成
				if($scope.result) {
					$scope.result()
				} else {
					$location.path(getRoute(data.statusIndex));
				}
			};
			$scope.confirm = function() {
				$.device.GoHome();
			};
			$scope.backTo = function() { // 重新核验
				$scope.status = false;
				$scope.confirm = true;
				$scope.completeTips = false;
				$scope.completeError = false;
				OcxControl.cameraOpen({
					height: "520",
					width: "740",
					left: "865",
					top: "320"
				}, function(data) {
					console.log(data)
				}, function(err) {
					console.log(err)
				});
			}
		}
	}
});