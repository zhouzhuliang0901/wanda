function removeAnimate(ele) {
	$(ele).css({
		"transform": "translateY(0px)",
		"top": 0
	}).removeClass('transformto')
}

function addAnimate(ele) {
	$(ele).addClass('transformto').on("animationend", function() {
		$(ele).removeClass('transformto')
	})
}
app.controller('loginType', function($state, $scope, appData, $rootScope, appFactory) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	// $.getConfigMsg.preUrlSelf = "http://10.2.14.143:8081/ac-self";
	appData.funName = $(".headName").text();
	$scope.operation = "请选择登录方式";
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller('login', function($scope, $http, $state, appData, $rootScope, appFactory) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.concel = "false";
	switch ($scope.loginType) {
		case "idcard":
			$scope.operation = "身份证登录";
			break;
		case "ukey":
			$scope.loginBtn = true;
			$scope.operation = "ukey登录";
			break;
		case "cloud":
			$scope.operation = "随申办登录";
			break;
	}

	//资格校验
	$scope.qualificationVerification = function() {
		$.customAjax.get($.getConfigMsg.preUrlSelf +
			'/selfapi/outpatientMasonic/qualificationVerification.do', {
				name: encodeURI(appData.licenseName),
				identityNo: appData.licenseNumber,
				identityType: '1'
			},
			function(res) {
				console.log(res);
				if (res.code == '0000') {
					if (res.data.zgbz == '1') {
						$state.go("info");
					} else {
						$scope.isAlert = true;
						$scope.msg = res.data.btgyy;
						$scope.alertConfirm = function() {
							$scope.isAlert = false;
							$state.go('loginType')
						}
					}
				} else {
					$scope.isAlert = true;
					$scope.msg = '校验接口异常';
					$scope.alertConfirm = function() {
						$scope.isAlert = false;
						$state.go('loginType')
					}
				}
			},
			function(err) {
				$scope.isAlert = true;
				$scope.msg = '校验接口异常';
				$scope.alertConfirm = function() {
					$scope.isAlert = false;
					$state.go('loginType')
				}
			});
	}

	//跳转页面
	$scope.nextStep = function() {
		$scope.tokenType = "token";
		$scope.token = function() {
			$scope.qualificationVerification();
		}
	}

	// $scope.idcardLogin = function() {
	// 	appData.licenseNumber = '310106194910230433';
	// 	appData.licenseName = '王鸣';
	// 	$state.go("info");
	// 	// $scope.qualificationVerification();
	// }
	// $scope.idcardLogin();
	$scope.idcardLogin = function(info, images) {
		if (info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
			appData.VALIDENDDAY = info.ValidtermOfEnd;
			appData.VALIDSTARTDAY = info.ValidtermOfStart;
		} else {
			layer.msg("没有获取到")
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		$scope.qualificationVerification();
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}

	$scope.citizenLogin = function(info) {
		function ClearBr(key) {
			key = key.replace(/\+/g, "-");
			key = key.replace(/\#/g, ",");
			return key;
		}
		if (appData.qrCodeType == "suishenma") {
			appData.licenseName = info.zwdtsw_name;
			appData.licenseNumber = info.zwdtsw_cert_id;
			appData.encrypt_identity = ClearBr(info.encrypt_identity);
			$scope.nextStep();
		} else {
			var idcardInfo = info.result.data;
			appData.licenseName = idcardInfo.realname;
			appData.VALIDENDDAY = idcardInfo.VALIDENDDAY;
			appData.VALIDSTARTDAY = idcardInfo.VALIDSTARTDAY;
			$scope.nextStep();
		}
	}
})
app.controller('ssacrd', function($scope, $http, $state, appData, $rootScope, appFactory) {
	$scope.operation = "社保卡刷卡添加信息";
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.sscardLogin = function(info) {
		if(info) {
			if(info.Ssn != "" && info.Ssn != null && info.Ssn != undefined) {
				appData.licenseNumber = info.Ssn;
				appData.licenseName = info.PeopleName;
				appData.licenseIccId = info.IccId;
			} else {
				$scope.isAlert = true;
				$scope.msg = "未读取到您的社保卡信息,请重试";
			}
	
		} else {
			layer.msg("没有获取到")
		}
	}
	$scope.prevStep = function() {
		$state.go("info");
	}
})
app.controller('info', function($state, $scope, appData, appFactory, $rootScope, $timeout, $http) {
	$scope.operation = "请填写基本信息";
	$scope.nextText = "提交";
	$scope.isAlert = false;
	$scope.concel = "false";
	appData.SwipeType = 'sbCard';
	$scope.sscardShow = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$scope.isLoading = false;
	}
	$scope.stName = appData.licenseName;
	$scope.stIdCard = appData.licenseNumber;
	$scope.per_reltel = '身份证';
	$scope.current = 1;


	$('body').delegate('.bt-delete', 'click', function() {
		console.log(1234);
		$(this).parent().parent().remove();
	});

	$scope.getSsCardInfo = function(){
		$scope.sscardShow = true;
		if($scope.sscardShow){
			$scope.sscardLogin = function(info) {
				if(info) {
					if(info.Ssn != "" && info.Ssn != null && info.Ssn != undefined) {
						appData.gjNumber = info.Ssn;
						appData.gjName = info.PeopleName;
						appData.gjIccId = info.IccId;
						$scope.sscardShow = false;
						$('.scrollBox2').append('<div><table class="infoTable" id="infoTable' + $scope.current +'"><div class="infoTitle"><p>共济成员信息' + $scope.current +'</p><div class="bt-delete">删除</div></div>' +
							'<tr><th><b class="red">*</b>姓名：</th><td><input class="input" value="' + $scope.gjName + '" type="text" id="name' + $scope.current + '"></td>' +
							'<th><b class="red">*</b>与组件人关系：</th><td><input class="input" type="text" id="relationship' +$scope.current + '"></td></tr>' +
							'<tr><th><b class="red">*</b>证件类型：</th><td><input class="input" type="text" value="身份证" readonly id="cardType' +$scope.current + '"></td>' +
							'<th><b class="red">*</b>身份证号码：</th><td><input class="input" value="' + $scope.gjNumber + '" type="text" id="idCard' +$scope.current + '"></td></tr>' +
							'<tr><th><b class="red">*</b>社（医）保卡号：</th><td><input class="input" value="' + $scope.gjIccId + '" type="text" id="cardNo' +$scope.current + '"></td><th></th></tr></table></div>')
						$scope.current++;
						$.device.ssCardClose();
					} else { 
						$scope.isAlert = true;
						$scope.msg = "未读取到您的社保卡信息,请重试";
					}
			
				} else {
					layer.msg("没有获取到")
				}
			}
		}
	}
	$scope.getAuthorize = function() {
		$('.scrollBox2').append('<div><table class="infoTable" id="infoTable' + $scope.current +'"><div class="infoTitle"><p>共济成员信息' + $scope.current +'</p><div class="bt-delete">删除</div></div>' +
			'<tr><th><b class="red">*</b>姓名：</th><td><input class="input" type="text" id="name' + $scope.current + '"></td>' +
			'<th><b class="red">*</b>与组件人关系：</th><td><input class="input" type="text" id="relationship' +$scope.current + '"></td></tr>' +
			'<tr><th><b class="red">*</b>证件类型：</th><td><input class="input" type="text" value="身份证" readonly id="cardType' +$scope.current + '"></td>' +
			'<th><b class="red">*</b>身份证号码：</th><td><input class="input" type="text" id="idCard' +$scope.current + '"></td></tr>' +
			'<tr><th><b class="red">*</b>社（医）保卡号：</th><td><input class="input" type="text" id="cardNo' +$scope.current + '"></td><th></th></tr></table></div>')
		$scope.current++;
	}


	//提交
	$scope.nextStep = function() {
		var condFlag = false;
		var dataList = []
		//循环组网人参数
		for (var i = 0; i < $scope.current; i++) {
			if (!isBlank($("#name" + i).val())) {
				dataList.push({
					czrxm: $scope.stName,
					czrzjhm: $scope.stIdCard,
					czrzjlx: '1',
					zwrxm: $("#name" + i).val(),
					zwrzjhm: $("#idCard" + i).val(),
					zwrzjlx: '1',
					ywlx: '1',
					yczrgx: $("#relationship" + i).val(),
					ywlsh: '',
				})
			}
		}
		console.log(dataList);
		do {

		} while (condFlag);
		//组网关系
		$scope.params = {
			dataList: dataList
		}
		appData.dataList = dataList;
		$.customAjax.postJson($.getConfigMsg.preUrlSelf + '/selfapi/outpatientMasonic/zw.do', 
			JSON.stringify($scope.params),
			function(res) {
				console.log(res);
				if(res.code == '0000'){
					if(res.data.length == 0){
						$state.go('signature');
					}else{
						var list = res.data;
						var msg = '';
						for(var i=0; i<list.length;i++){
							if(list[i].zwjg == '0'){
								msg += '<p>'+list[i].xm+ '--'+ list[i].zwsbyy+'</p>'
							}
						}
						$scope.isAlert = true;
						$scope.msg = msg
					}
				}else{
					
				}
			},
			function(err) {});
		//提交参数集合
		condFlag = true;
	}
});
app.controller('signature', function($state, $scope, appData) {
	$scope.$watch('$viewContentLoaded', function(newValue, oldValue) {
		$scope.funName = appData.funName;
		$scope.signature = null;
		$scope.signatureFlag = false;
		$scope.SignatureBoardPlug = new SignatureBoardPlug({
			canvas: "#signature",
			clearBtn: ".clearRect",
			getSigntrue: ".saveImg",
			color: "black"
		});
	});
	
	$scope.isSignature = function() {
		$scope.signatureFlag = true;
	};
	$scope.notSignature = function() {
		$scope.signatureFlag = false;
	}
	$scope.saveSignature = function() {
		$scope.signature = $scope.SignatureBoardPlug.Signatrue;
		if($scope.signatureFlag === false) {
			alert("请先在屏幕上签名!");
			return;
		}
		appData.picStr = $scope.signature.split(",")[1];
		console.log(appData.picStr);
		$.customAjax.post($.getConfigMsg.preUrlSelf + '/selfapi/outpatientMasonic/signPDF.do',
			{
				name:encodeURI(appData.licenseName),
				idCard:appData.licenseNumber,
				phone:'',
				seal:encodeURI(appData.picStr),
				memberList:encodeURI(JSON.stringify(appData.dataList))
				
			},
			function(res) {
				console.log(res);
				appData.documentId = res.documentId;
				$state.go("submit");
			},
			function(err) {});
		
	};
});
app.controller('submit', function($state, $scope, appData) {
	$scope.funName = appData.funName;
	$scope.applyNo = appData.applyNo;
	$scope.nextText = "返回首页";
	$.customAjax.post($.getConfigMsg.preUrlSelf + '/selfapi/outpatientMasonic/download.do',{
			documentId:appData.documentId,
		},
		function(res) {
			console.log(res);
			appData.uploadFile = res.data;
		},
		function(err) {});
	$scope.goHome = function() {
		$.device.GoHome();
	}
	$scope.infoParams = {};
	$scope.submit = function(){
		$.customAjax.post($.getConfigMsg.preUrlSelf + '/selfapi/deal/work/saveApplyInfo.do',{
			"itemCode":'0105632000',
			"taskHandleItem": '11310000MB2F30661Y231200356400001',
			"itemName": encodeURI('组建家庭共济关系网（门诊共济使用）'),
			"targetType": encodeURI('个人'),
			"targetName": encodeURI(appData.licenseName),
			"targetNo": appData.licenseNumber,
			"userId": "",
			"username": encodeURI(appData.licenseName),
			"licenseType": "身份证",
			"licenseNo": appData.licenseNumber,
			"mobile": '',
			"departCode": "",
			"departName": "",
			"source": "网上申请",
			"content": "",
			"opTime": "",
			"districtCode": "",
			"info": JSON.stringify($scope.infoParams)
		},
		function(res) {
			console.log(res);
			$scope.applyNo = res.data.applyNo;
			//MATERIAL22D091016
			$.customAjax.post($.getConfigMsg.preUrlSelf + '/selfapi/deal/work/uploadApplyStuffs.do',{
				stuffId:'',
				applyNo:res.data.applyNo,
				stuffCode:'MATERIAL22D091016',
				stuffName:'组建家庭共济网承诺书',
				stuffType:'0',
				stuffStatus:'0',
				stuffSource:'',
				file:appData.uploadFile
			},
			function(res) {
				console.log(res);
			},
			function(err) {});
		},
		function(err) {});
	}
	$scope.submit();
});
