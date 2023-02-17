var app = angular.module("declareApp", ["ng", "ngRoute"]);
app.factory('data', function() {
	return {
		isUpload: []
	};
});
app.filter('to_trusted', ['$sce', function($sce) {
	return function(text) {
		return $sce.trustAsHtml(text);
	};
}]);
//自定义指令repeatFinish---防止scroll的过程中触发点击事件
app.directive('repeatFinish',function(){
    return {
        link: function(scope,element,attr){
            if(scope.$last == true){
             var scroll = new BScroll('.wrapper', {
     scrollX:false,
     scrollY: true,
     scrollbar:true,
     click: true,
     tap:true
    })
    $('.wrapper').find("a").on('tap', function() {
    });
            }
        }
    }
});
app.factory("appFactory", function($http, $rootScope,$interval,$timeout) {
	var product = function(data, code,archivescode,affairscode,archivesname,needflag ,callback, complete,callback1,error) {
		var queryLicense = $.ajax({
			url: $.getConfigMsg.preUrl + "/aci/autoterminal/dzzz/queryCertBaseData.do",
			type: "post",
			dataType: "jsonp",
			timeout:5000,
			jsonp: "jsonpCallback",
			data: {
				certNo: data, //"340881199303145313" 
				type: "0", //licenseType ,// 
				catMainCode: code, //"310196646654500"//
				machineId: $.config.get('uniqueId') || "",
				itemName: "",
				itemCode: "",
				businessCode: "",
				name: "",
				startDay: "",
				endDay: ""
			},
			success: function(dataJsonp) {
				try{
					$.ajax({
						url: $.getConfigMsg.preUrl +'/aci/workPlatform/elderlyCard/uploadArchiveInfo.do',
						type: "post",
						dataType: "json",
						data: {
							archivescode: archivescode,
							affairscode: affairscode,
							archivesname: archivesname,
							needflag: needflag,
							img: $.getConfigMsg.preUrl + dataJsonp[0].pictureUrl
						},
						success: function(dataJsonp1) {
							$.log.debug("success:" + JSON.stringify(dataJsonp1))
							callback1 && callback1(dataJsonp1);
						},
						error: function(err) {
							$.log.debug("err:" + JSON.stringify(err))
						}
					});
				}catch(e){}
				callback && callback(dataJsonp);
			},
			error: function(err) {
				$.log.debug("err:" + JSON.stringify(err))
			},complete : function(XMLHttpRequest,status){ //请求完成后最终执行参数
		　　　　if(status=='timeout'){//超时,status还有success,error等值的情况
		 　　　　　queryLicense .abort();
		　　　　}
				complete && complete(status);
		　　}
		});
	}
	
	return {
		pro_fetch: product
	}
})