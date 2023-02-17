app.directive("citizen", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/citizen.html",
		terminal: true,
		scope: {
			result: "&?",
		},
		controller: function($scope, $location, $rootScope, $interval, $timeout, data) {
			$scope.isScan = false;
			data.tokenSNO = "";
			data.accessToken = "";
			data.idCardInfo = "";
			$scope.getUserInfoByAccessToken = function(accessToken) {
				$.ajax({
					type: "get",
					url: $.getConfigMsg.declareUrl + "/aci/workPlatform/getUserInfoByAccessToken.do",
//					url: "http://180.169.7.194:8080/ac-product/aci/workPlatform/getUserInfoByAccessToken.do",
					dataType: "jsonp",
					jsonp: "jsonpCallback",
					data: {
						accessToken: accessToken
					},
					success: function(dataJson) {
						$scope.status = false;
						$scope.completeTips = true;
						if(dataJson != undefined && dataJson != null && dataJson != "") {
							data.idCardInfo = dataJson;
							data.idCardName = dataJson.zwdtsw_name;
							data.idCardNum = dataJson.zwdtsw_cert_id;
							$scope.tipsText = "核验成功,即将跳转...";
							$timeout(function(){
								$scope.result();
							},2000)
							$scope.$apply();
						} else {
							$scope.tipsText = "核验失败，请重试";
							$timeout(function(){
								$location.path("/select");
							},2000)
							$scope.$apply();
						}
					},
					error: function(err) {
						$scope.status = false;
						$scope.tipsText = "核验失败，请重试";
						$timeout(function(){
							$location.path("/select");
						},2000)
					}
				});
			}
			$scope.getAccessToken = function(tokenSNO) {
				$.ajax({
					url: $.getConfigMsg.declareUrl + '/aci/workPlatform/getAccessToken.do',
					type: "post",
					dataType: "jsonp",
					jsonp: "jsonpCallback",
					data: {
						tokenSNO: tokenSNO
					},
					success: function(res) {
						data.accessToken = res.accessToken;
						$scope.getUserInfoByAccessToken(data.accessToken);
					},
					error: function(e) {
						alert("没有获取到access_token");
					}
				})
			}
			// 扫描二维码
			OcxControl.Barcode(function(code) {
				OcxControl.Light.qrcodeLightClose();
				OCX_Barcode.CloseConnection();
				$scope.isScan = true;
				$scope.status = true;
				$scope.$apply();
				if(code.indexOf("http") != -1) { // 随申码
					code = code.replace(/[\r\n]/g, "");
					$.ajax({
						url: $.getConfigMsg.declareUrl + "/selfapi/loginService/getTokenSNOByQrCode.do",
						dataType: 'jsonp',
						jsonp: "jsonpCallback",
						data: {
							certQrCode: encodeURIComponent(code),
							pos: "",
							use: "",
						},
						success: function(dataJonsp) {
							if(dataJonsp != null && dataJonsp != undefined && dataJonsp.encrypted == true) {
								data.tokenSNO = dataJonsp.biz_response.tokenSNO;
								$scope.getAccessToken(data.tokenSNO);
							} else {
								alert("扫码失败,请重试")
								$location.path("/select");
							}
						},
						error: function(err) {
							alert("扫码失败,请重试")
							$location.path("/select");
						}
					})
				} else { //市民云
					function ClearBr(key) {
						key = key.replace(/[\r\n]/g, "");
						return key;
					}
					var __code = ClearBr(code);
					$.ajax({
						type: "get",
						url: $.getConfigMsg.declareUrl + "/aci/window/getQrCodeInfoByElectronicCert.do",
						dataType: 'jsonp',
						jsonp: "jsonpCallback",
						data: {
							codeParam: code,
						},
						success: function(dataJsonp) {
							$scope.status = false;
							$scope.completeTips = true;
							if(dataJsonp != null && dataJsonp != undefined && dataJsonp.result.success == "true") {
								var idcardInfo = dataJsonp.result.data;
								data.idCardInfo = idcardInfo;
								data.idCardName = idcardInfo.realname;
								data.idCardNum = idcardInfo.idcard;
								$scope.tipsText = "核验成功,即将跳转...";
								$timeout(function(){
									$scope.result();
								},2000)
								$scope.$apply();
							} else {
								$scope.tipsText = "核验失败，请重试";
								$timeout(function(){
									$location.path("/select");
								},1000)
								$scope.$apply();
							}
						},
						error: function(err) {
							alert("扫码失败,请重试")
							$location.path("/select");
						}
					})
				}
			});
		}
	}
});