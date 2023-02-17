app.directive("idcard", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/idcard.html",
		terminal: true,
		scope: {
			read: "&",
			hasImg: "@"
		},
		controller: function($scope, $location, $rootScope, $interval, $timeout) {
			$scope.showImage = "../libs/common/images/tips/idcard.png";
			$scope.idcardInfo = null;
			$scope.needImg = $scope.hasImg || 'yes';
//						$timeout(function(){				
//							$scope.read({
//								info: 1,
//								images: 2
//							}); //回调controller方法
//						},2000)
			$scope.$watch("hasImg",function(newVal){
				if(newVal){
					$scope.needImg = newVal;
				}
			})
			$scope.readIdCard = function() {
				$.device.idCardOpen(function(list) {
					var data = JSON.parse(list);
					$.log.debug(list);
					$scope.idcardInfo = data;
					if($scope.needImg == "no") {
						$scope.read({
							info: $scope.idcardInfo,
							images: ""
						}); //回调controller方法
					} else {
						$scope.getImgbaseStr(
							data.Number,
							data.Name,
							data.CardImagePath,
							data.People
						);
					}

				})
			};
			//获取上传的身份证base64
			$scope.getImgbaseStr = function(idCardNo, name, imgUrl) {
				$.ajax({
					type: "post",
					url: "http://10.237.16.72/aci/autoterminal/archives/getLicenseStuffList.do",
					data: {
						stIdNo: idCardNo,
						type: 0,
						baseType: "WITNESS_CONTRAST"
					},
					dataType: "jsonp",
					jsonp: "jsonpCallback",
					success: function(dataJsonp) {
						if(dataJsonp.length > 0) {
							$scope.ImageStr = dataJsonp[0].imageStr; //人证核验照片数据
							$scope.read({
								info: $scope.idcardInfo,
								images: $scope.ImageStr
							}); //回调controller方法
							return;
						} else {
							$scope.uploadIdCardImg(idCardNo, name, imgUrl);
						}
					},
					error: function() {}
				});
			};
			//上传身份证
			$scope.uploadIdCardImg = function(idCardNo, name, imgUrl) {
				var jsonData = {
					type: "0",
					stShareCode: "WITNESS_CONTRAST",
					stName: name,
					stIdNo: idCardNo
				};
				$.device.httpUpload(
					"http://10.237.16.72/aci/autoterminal/archives/saveLicenseStuff.do",
					"FileData",
					imgUrl,
					JSON.stringify(jsonData),
					function(result) {
						$scope.getImgbaseStr(idCardNo, name, imgUrl);
					},
					function(webexception) {
						alert("网络异常");
					}
				);
			};
			$scope.readIdCard();

		}
	}
});