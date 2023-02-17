app.controller("mainController", function ($scope, $route, $location, $http, data, $timeout) {

	$scope.readIdCard = function () {
		PROMISE_METHOD.getIdCardInfo()
			.then(function (Obj) {
				var identityInfo = JSON.parse(Obj.identityInfo);
				data.idCardName = identityInfo.Name;
				data.idCardNum = identityInfo.Code;
				data.idCardInfo = identityInfo;
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
				$.log.debug(res);
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
			url: $.getConfigMsg.preUrl+"/aci/autoterminal/facecompare.do",
			type: "post",
			dataType: "json",
			data: {
				idCardPhoto: idCardPhoto,
				capturePhoto: capturePhoto
			},
			success: function (res) {
				var n = res.similarity;
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
				layer.msg("核验失败，请稍后重试");
				$location.path("/main");
			}
		})
	};
	//流水号以及过期时间

	$scope.SerialNumber = function() {
		var ArrNum = '000000'.split('');
		if(localStorage.getItem('SerialTimer') == null) { //第一次打印注入时间戳
			localStorage.setItem('SerialTimer', Date.parse(new Date()))
		}
		if(localStorage.getItem('SerialTimer') != null && Date.parse(new Date()) - localStorage.getItem('SerialTimer') > 1000 * 3600 * 12) { //时间戳存在并已经过12个小时，则清空流水号以及时间戳
			localStorage.removeItem("SerialTimer");
			localStorage.removeItem("SerialNumber");
			localStorage.setItem('SerialTimer', Date.parse(new Date())) //更新时间戳
		}
		if(localStorage.getItem('SerialNumber') == null) { //流水号是否为空
			localStorage.setItem('SerialNumber', '000001');
		} else { //流水号保有原格式递增
			var num = parseInt(localStorage.getItem('SerialNumber')) + 1 + '';
			for(var index = 0; index < num.length; index++) {
				ArrNum.push(num[index]);
			}
			var temporary1 = ArrNum.reverse();
			temporary1.length = 6;
			ArrNum = temporary1.reverse();
			localStorage.setItem('SerialNumber', ArrNum.join(''))
		}
	}
	$scope.printBtn = function() {
		var D = new Date();
		var NowDate = D.getFullYear() + '' + (D.getMonth() + 1) + '' + D.getDate();
		var dataTime = D.getFullYear() + '年' + (D.getMonth() + 1) + '月' + D.getDate() + '日';

		$scope.SerialNumber(); //流水号		

		$scope.LODOP = $.device.printGetLodop();
		$scope.LODOP.PRINT_INIT("打印插件功能演示_$scope.LODOP功能_BASE64编码串打印图片");
		$scope.LODOP.SET_PRINT_STYLE("FontSize", 18);
		$scope.LODOP.ADD_PRINT_TEXT(130, 320, 200, 80, "人证核验结果单");
		$scope.LODOP.SET_PRINT_STYLE("FontSize", 12);

		$scope.LODOP.ADD_PRINT_TEXT(50, 500, 200, 80, "流水号:");
		$scope.LODOP.ADD_PRINT_TEXT(50, 560, 200, 80, NowDate + localStorage.getItem('SerialNumber'));

		$scope.LODOP.ADD_PRINT_TEXT(170, 100, 200, 80, "姓名:");
		$scope.LODOP.ADD_PRINT_TEXT(170, 150, 200, 80, data.idCardInfo.Name);

		$scope.LODOP.ADD_PRINT_TEXT(200, 100, 200, 80, "性别：");
		$scope.LODOP.ADD_PRINT_TEXT(200, 150, 200, 80, data.idCardInfo.Sex);

		$scope.LODOP.ADD_PRINT_TEXT(200, 300, 200, 80, "民族：");
		$scope.LODOP.ADD_PRINT_TEXT(200, 350, 200, 80, data.idCardInfo.National);

		$scope.LODOP.ADD_PRINT_TEXT(230, 100, 200, 80, "身份证号：");
		$scope.LODOP.ADD_PRINT_TEXT(230, 180, 200, 80, data.idCardInfo.Code);

		$scope.LODOP.ADD_PRINT_TEXT(260, 100, 200, 80, "有效期限：");
		$scope.LODOP.ADD_PRINT_TEXT(260, 180, 250, 80, data.idCardInfo.ValidPeriod);

		$scope.LODOP.ADD_PRINT_TEXT(290, 100, 200, 80, "核验结果：");
		$scope.LODOP.ADD_PRINT_TEXT(290, 180, 200, 80, $scope.tipsText);

		$scope.LODOP.ADD_PRINT_TEXT(320, 100, 200, 80, "核验时间：");
		$scope.LODOP.ADD_PRINT_TEXT(320, 180, 200, 80, dataTime);

		$scope.LODOP.ADD_PRINT_IMAGE(500, 100, "100%", "100%", "<img border='0' style='width:350px;height:256px;' src='d:/cameraPhotograph.png'>"); //核验照片
		$scope.LODOP.ADD_PRINT_IMAGE(500, 500, "100%", "100%", 'c:/weirong/atap/bin/Front_head.bmp'); //身份证头像
		//水印
		$scope.LODOP.ADD_PRINT_TEXT(0, 0, 400, 500, "本结果只用于政务服务核验身份");
		$scope.LODOP.SET_PRINT_STYLEA(0, "FontSize", 20);
		$scope.LODOP.SET_PRINT_STYLEA(0, "FontColor", "#ccc");
		$scope.LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
		$scope.LODOP.SET_PRINT_STYLEA(0, "Angle", 50);
		$scope.LODOP.SET_PRINT_STYLEA(0, "Repeat", true);
		$scope.LODOP.PRINT();
		$location.path("/pwait");
	}
	$scope.goToHome = function() {
		if($scope.tipsText == '对比通过') {
			$scope.printBtn();
		} else {
			window.external.GoHome();
		}
	}
	$scope.backTo = function () {
		data.isReRecognition = true;
		$location.path("/main");
	}
});