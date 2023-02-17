app.controller("mainController", function ($scope,data,$location) {
	console.log($location.search().type);
	if($location.search().type == 'v'){
		 $('#info').attr('src',"https://zwdt.sh.gov.cn/govPortals/ftl/publicServiceList?code=SHSWSH");
		// window.external.URL_OPEN(125,232,1670,800, "https://zwdt.sh.gov.cn/govPortals/ftl/publicServiceList?code=SHSWSH");
	}else{
		$('#info').attr('src',"http://swj.sh.gov.cn/xxgk/");
	}
	
    // 初始化滚动条
    angular.element(document).ready(function() {
		$(".inner-box").mCustomScrollbar({
			theme: "dark-thin",
			scrollInertia: 400
		});
	});
    //滚动条上移
	$scope.moveUp = function() {
		$(".inner-box").mCustomScrollbar("scrollTo", "+=" + $scope.rollingSpeed);
	};
	//滚动条下移
	$scope.moveDown = function() {
		$(".inner-box").mCustomScrollbar("scrollTo", "-=" + $scope.rollingSpeed);
	}
	// 返回问题列表
	$scope.returnQ = function(){
		$scope.haveList = true;
	}
});