app.directive("ca", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/ca.html",
		scope: {
			result: "&",
			login:"="
		},
		controller: function($scope, $location, $state, $rootScope, $interval, $timeout) {
			$.device.audioPPlay("Realtek High Definition Audio", $.device.currentPath() + "\\resources\\audio\\ca.wav");
			console.log(SafeEngineCtl);
			$scope.tipsText = "请插入Ukey，输入密码。";
			$scope.tipsImage = "../libs/common/images/tips/ca.png";
			$scope.password = "";
			$scope.$watch("login",function(val){
				if(val==='login'){
					$scope.__login();
				}
			})
			$scope.__login = function() {
				if(!$scope.password) {
					layer.msg("密码不能为空！");
					return;
				}

				/* Change the path and password below */
				$scope.strpripath = "com1";
				$scope.strcertpath = "com1";
				$scope.strcertchainpath = "com1";
				// 请输入证书验证参数
				$scope.ConfigurationNum = parseInt("1");
				// 请输入USB设备参数
				$scope.DevNumber = parseInt("10");

				//初始化函数
				SafeEngineCtl.SEH_InitialSession($scope.DevNumber, $scope.strpripath, $scope.password, 0, $scope.DevNumber, $scope.strpripath, "");

				if(SafeEngineCtl.ErrorCode != 0) {
					alert("验证不通过，请重新输入验证密码或者插入key");
					return;
				}

				//配置参数
				SafeEngineCtl.SEH_SetConfiguration($scope.ConfigurationNum);
				if(SafeEngineCtl.ErrorCode != 0) {
					alert("验证不通过，请重新输入验证密码或者插入key");
					SafeEngineCtl.SEH_ClearSession();
					return;
				}

				//获取证书内容
				$scope.strCert = SafeEngineCtl.SEH_GetSelfCertificate($scope.DevNumber, $scope.strcertpath, "");
				if(SafeEngineCtl.ErrorCode != 0) {
					alert("验证不通过，请重新输入验证密码或者插入key");
					return;
				}

				//获取证书细目	14
				//获取证书中的企业名称
				$scope.companyName = SafeEngineCtl.SEH_GetCertDetail($scope.strCert, 14);
				//获取证书中的commoncode
				$scope.commoncode = SafeEngineCtl.SEH_GetCertInfoByOID($scope.strCert, "1.2.156.112570.11.205");;
				console.log('commoncode'+$scope.commoncode);
				//获取证书中企业编码
				$scope.stIdNo = SafeEngineCtl.SEH_GetCertInfoByOID($scope.strCert, "1.2.156.112570.11.210");
				console.log($scope.stIdNo);
				if($scope.stIdNo == null || $scope.stIdNo == undefined || $scope.stIdNo == ""){
					$scope.stIdNo = SafeEngineCtl.SEH_GetCertInfoByOID($scope.strCert, "1.2.156.1.8888.148");
				}
				SafeEngineCtl.SEH_ClearSession();
				if($scope.companyName != undefined && $scope.companyName != null && $scope.companyName != "" && $scope.companyName != " ") {
					//企业名称 $scope.companyName;
					//企业信用代码$scope.stIdNo;
					$.log.debug("qymc: "+ $scope.companyName);
					$.log.debug("no: "+ $scope.stIdNo);
					console.log("qymc: "+ $scope.companyName);
					console.log("no: "+ $scope.stIdNo);
					$scope.result({
						companyName: $scope.companyName,
						companyNo: $scope.stIdNo,
						commoncode:$scope.commoncode
					})
				}
			};
		}
	}
});