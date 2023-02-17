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
app.controller("main", function($scope, $state, appData, $sce) {
	appData.address = "https://www.shpt.gov.cn/shpt/ywtb-qsthjj/index.html";
	$scope.address = $sce.trustAsResourceUrl(appData.address);
	try{
		window.external.URL_OPEN(200,180,1500,700,appData.address);
	}catch(e){}
});