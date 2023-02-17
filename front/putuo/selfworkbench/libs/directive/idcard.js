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
			$scope.showImage = "../libs/common/images/dicon/" + acBridgeMac.imagePath() + "/idCard.gif";
			$scope.idcardInfo = null;
			$scope.needImg = $scope.hasImg || 'yes';
			//						$timeout(function(){				
			//							$scope.read({
			//								info: 1,
			//								images: 2
			//							}); //回调controller方法
			//						},2000)
			$scope.$watch("hasImg", function(newVal) {
				if(newVal) {
					$scope.needImg = newVal;
				}
			})
			$scope.readIdCard = function() {
				$.device.idCardOpen(function(list) {
					var data = JSON.parse(list);
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
							data.People,
							data.Address
						);
					}

				})
			};
			//获取上传的身份证base64
			$scope.getImgbaseStr = function(idCardNo, name, imgUrl) {
				console.log(imgUrl);
				console.log($.device.fileBase64(imgUrl));
				$scope.read({
					info: $scope.idcardInfo,
					images: $.device.fileBase64(imgUrl)
				}); //回调controller方法
			};
			$scope.readIdCard();

		}
	}
});