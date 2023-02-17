app.controller("waiter", function ($scope, $state, appData,$sce) {
//店小二
 	window.external.URL_OPEN(200,180,1500,700, "https://zwdxe.shhuangpu.gov.cn/epoint-web-zwdt/hpzwdtcasefour/theme_service.html?accesstokeen=c0430129-65cb-4028-b1ba-81b9af37de06");
 	$scope.address = $sce.trustAsResourceUrl("https://zwdxe.shhuangpu.gov.cn/epoint-web-zwdt/hpzwdtcasefour/theme_service.html?accesstokeen=c0430129-65cb-4028-b1ba-81b9af37de06");	
	 //http://58.246.149.51:8085/epoint-web-zwdt/hpzwdtcasefour/theme_service
//https://zwdxe.shhuangpu.gov.cn/epoint-web-zwdt/hpzwdtcasefour/theme_service.html?accesstokeen=c0430129-65cb-4028-b1ba-81b9af37de06
 //	window.external.URL_OPEN(50,160,1800,800, "http://zwdthp.sh.gov.cn/zwdtSW/smart/zwdtSW/workGuide/typeGuide.jsp");
 //	$scope.address = $sce.trustAsResourceUrl("http://zwdthp.sh.gov.cn/zwdtSW/smart/zwdtSW/workGuide/typeGuide.jsp");
});
