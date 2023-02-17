app.controller("mainController", function ($scope, $route, $location, $http, data, $timeout) {

	$scope.readIdCard = function () {
		PROMISE_METHOD.getIdCardInfo()
			.then(function (Obj) {
				var identityInfo = JSON.parse(Obj.identityInfo);
				data.idCardName = identityInfo.Name;
				data.idCardNumber = identityInfo.Code;
				data.HeadImg = Obj.portrait;
				$location.path("/capture");
				$scope.$apply();
			})
			.catch(function (err) {
				console.log(err);
			})
	}
	$scope.readIdCard();
});
app.controller("captureController", function ($scope, $route, $http, $location, data, $timeout) {
	$scope.captureImage = null; //拍照照片

	OcxControl.cameraOpen({
			left: 910,
			top: 310,
			height: 520,
			width: 740
		},
		function (res) {},
		function (err) {

		});
	$scope.captureImage = null; //拍照照片
	$scope.status = false; //拍照OR核验
	$scope.complete = false; //核验完成
	$scope.confirm = true; //确认拍照图片
	$scope.btnInfo = "拍照";
	$scope.tipsText = "对比通过"; //核验完成提示

	$scope.capture = function () {

		OcxControl.cameraPhotograph(function (res) {
			$timeout(function () {
				$scope.captureImage = res;
				$scope.recognition()
			}, 100);
		}, function (err) {})
	}
	$scope.recognition = function () { //人证数据对比
		var idCardPhoto = data.HeadImg;
		var capturePhoto = $scope.captureImage;
		$scope.status = true;
		$scope.confirm = false;
		$.ajax({
			url: "http://31.0.178.10/ac/aci/witnesscheck/pairVerify.do",
			type: "post",
			dataType: "json",
			data: {
//				idCardPhoto: idCardPhoto,
//				capturePhoto: capturePhoto,
				
				account: "cuihuibin",
				password: "qwer",
				ST_IDCARD_NAME: data.idCardName,
				ST_IDCARD_NO: data.idCardNumber,
				idCard: idCardPhoto,
				photo: capturePhoto
			},
			success: function (res) {
				console.log(JSON.stringify(res))

				var n = res.pair_verify_similarity;
				$scope.status = false;
				$scope.complete = true;

				if (n > 60) {
					//核验通过
				} else {
					//人证不符
					$scope.tipsText = "人证不符";
				}
				$scope.$apply();
			},
			error: function (err) {
				layer.msg("err: " + err);
			}
		})

	};
	$scope.goToHome = function () {
		window.external.GoHome();
	}
	$scope.backTo = function () {
		data.isReRecognition = true;
		$location.path("/main");
	}
});