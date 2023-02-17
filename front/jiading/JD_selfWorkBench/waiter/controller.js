function removeAnimate(ele){
	$(ele).css({"transform":"translateY(0px)","top":0}).removeClass('transformto')
}
function addAnimate(ele){
	$(ele).addClass('transformto').on("animationend",function(){
		$(ele).removeClass('transformto')
	})
}

app.controller("waiter", function ($scope, $state, appData,$sce) {
	addAnimate($('.container'))
//店小二
// 	window.external.URL_OPEN(50,160,1800,800, "http://tzjc.scjgj.sh.gov.cn/secms/loginout.jsp");
// 	$scope.address = $sce.trustAsResourceUrl("http://tzjc.scjgj.sh.gov.cn/secms/loginout.jsp");	
var flag = false;
	 $scope.chooseTwo = function(){
	 	flag = true;
	 	window.external.URL_OPEN(200,180,1500,700, "http://tzjc.scjgj.sh.gov.cn/secms/loginout.jsp");
   		$scope.address = $sce.trustAsResourceUrl("http://tzjc.scjgj.sh.gov.cn/secms/loginout.jsp");	
	 }
	 $scope.chooseOne = function(){
	 	flag = true;
	 	window.external.URL_OPEN(200,180,1500,700, "http://xuke.smda.sh.cn/AppRoveManage/SQ0106CatalogController/itemDepartment");
   		$scope.address = $sce.trustAsResourceUrl("http://xuke.smda.sh.cn/AppRoveManage/SQ0106CatalogController/itemDepartment");	
	 }
	 $scope.prevStepa = function(){
		if(flag){
		 	flag = false;
		 	window.external.URL_CLOSE();
		 	$location.path("/main")
		}else{
			window.external.URL_CLOSE();
		}
	 }

 //	window.external.URL_OPEN(50,160,1800,800, "http://zwdthp.sh.gov.cn/zwdtSW/smart/zwdtSW/workGuide/typeGuide.jsp");
 //	$scope.address = $sce.trustAsResourceUrl("http://zwdthp.sh.gov.cn/zwdtSW/smart/zwdtSW/workGuide/typeGuide.jsp");
});
