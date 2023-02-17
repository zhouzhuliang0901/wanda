app.controller("synthesizeMain", function($scope, $state, appData) {
	$scope.funName = "世界城市日";
	$scope.active = function() {
		appData.funName = "近期活动";
		$state.go("choice");
	};
	$scope.center = function(){
		appData.funName = "中心介绍";
		$state.go("iframe");
	};
});
app.controller("choiceType",function($scope, $state, appData){
	$scope.funName = "近期活动";
	$scope.intern = function(){
		appData.funName = "实习生招募";
		$state.go("iframe");
	}
	$scope.italy = function(){
		appData.funName = "意大利IP节";
		$state.go("iframe");
	}
	$scope.prevStep = function(){
		appData.funName = "近期活动";
		$state.go("main");
	}
	
});
app.controller("synthesizeIframe", function($scope, $state,$timeout, appData,$sce) {
	$scope.funName = appData.funName;
	if($scope.funName == "中心介绍"){
		$scope.name = "世界城市日介绍.doc";
		$.device.officeOpenRelative($scope.name);
		$timeout(function() {
			$.device.officeShow(900, 800, 360, 170);
		}, 4000);
	}else if($scope.funName == "实习生招募"){
		$scope.address = $sce.trustAsResourceUrl("https://mp.weixin.qq.com/s?__biz=MzI4NTI3MzUxMw==&mid=2247485243&idx=1&sn=0f8616c4eca84e288e8368c88fa82233&chksm=ebeffbfddc9872eb998f83cb732d414c029ddeb6e5dd561e22b121a6430378c50560a2a3a0fc&mpshare=1&scene=1&srcid=&key=729816feb00e845f4a67f9d3a8ba500f3420ada03230686d8e90610e2d635b75f5d6e08dd714393d6a99eb21c533d1bcd822278cc3635d3b16e130e2ddb342e17df7f0faac3d369baf4aaedacf467838&ascene=1&uin=MTA4Mzg0MTgwMg%3D%3D&devicetype=Windows+10&version=62060833&lang=zh_CN&pass_ticket=pwP4WfjT5KHo7D%2FHUweJnVujCrExmZdZz%2BtZigGJxjUaJ5%2BtkS6Jed4Sx%2FgNjypf");	 
		window.external.URL_OPEN(50,160,1800,800,"https://mp.weixin.qq.com/s?__biz=MzI4NTI3MzUxMw==&mid=2247485243&idx=1&sn=0f8616c4eca84e288e8368c88fa82233&chksm=ebeffbfddc9872eb998f83cb732d414c029ddeb6e5dd561e22b121a6430378c50560a2a3a0fc&mpshare=1&scene=1&srcid=&key=729816feb00e845f4a67f9d3a8ba500f3420ada03230686d8e90610e2d635b75f5d6e08dd714393d6a99eb21c533d1bcd822278cc3635d3b16e130e2ddb342e17df7f0faac3d369baf4aaedacf467838&ascene=1&uin=MTA4Mzg0MTgwMg%3D%3D&devicetype=Windows+10&version=62060833&lang=zh_CN&pass_ticket=pwP4WfjT5KHo7D%2FHUweJnVujCrExmZdZz%2BtZigGJxjUaJ5%2BtkS6Jed4Sx%2FgNjypf");
	}else if($scope.funName == "意大利IP节"){
		$scope.address = $sce.trustAsResourceUrl("https://mp.weixin.qq.com/s?__biz=MzI4NTI3MzUxMw==&mid=2247485634&idx=1&sn=ee15ef4885cfbc3bd9e4f2040f6d844b&chksm=ebeff404dc987d12d9e92682aafebe21430cb7c6793bf827eeab3466b0650dad3013e8603f99&scene=0&xtrack=1&key=949cb254edaca2764bb3ee7043feb9fa0a68ac998d242a87078d2cfef7a9b5c48faa1f77f67bac071cc414ddebd3dcb64c79697307277a99693fa05fdfec128be7daa575c3bf29a67573c3cd49a1df44&ascene=1&uin=MTMwMDU5MjMzOA%3D%3D&devicetype=Windows+10&version=62060833&lang=zh_CN&pass_ticket=qJSGQ%2FunadaDUu7oX4VYpxQidsaWa1gjJcusfNZ6EKs32G8Xy%2B%2F6Xk%2Fw1C6%2FZHXW");	 
		window.external.URL_OPEN(50,160,1800,800,"https://mp.weixin.qq.com/s?__biz=MzI4NTI3MzUxMw==&mid=2247485634&idx=1&sn=ee15ef4885cfbc3bd9e4f2040f6d844b&chksm=ebeff404dc987d12d9e92682aafebe21430cb7c6793bf827eeab3466b0650dad3013e8603f99&scene=0&xtrack=1&key=949cb254edaca2764bb3ee7043feb9fa0a68ac998d242a87078d2cfef7a9b5c48faa1f77f67bac071cc414ddebd3dcb64c79697307277a99693fa05fdfec128be7daa575c3bf29a67573c3cd49a1df44&ascene=1&uin=MTMwMDU5MjMzOA%3D%3D&devicetype=Windows+10&version=62060833&lang=zh_CN&pass_ticket=qJSGQ%2FunadaDUu7oX4VYpxQidsaWa1gjJcusfNZ6EKs32G8Xy%2B%2F6Xk%2Fw1C6%2FZHXW");

	}
});
