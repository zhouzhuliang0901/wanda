var app = angular.module("declareApp", ["ng", "ngRoute"]);
app.factory('data', function() {
    return {};
});
app.filter('to_trusted', ['$sce', function ($sce) {
    return function (text) {
        return $sce.trustAsHtml(text);
    };
}]);
//自定义指令repeatFinish
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
//自动上传材料
app.factory("appFactory", function($http, $rootScope,$interval,$timeout) {
	var product = function(data, code,applyId,stuffId,itemId,stuffName ,callback, callback1,complete,error) {
		var machineId = $.config.get('uniqueId');
		var queryLicense = $.ajax({
			url: "http://180.169.7.194:8080/ac-product/aci/autoterminal/dzzz/queryCertBaseData.do",
			type: "post",
			dataType: "jsonp",
			timeout:5000,
			jsonp: "jsonpCallback",
			data: {
				machineId:machineId || '',
				itemName:data.itemName || '',
				itemCode:data.itemId || '',
				businessCode:data.applyId || '',
				certNo: data, //"340881199303145313" 
				type: "0", //licenseType ,// 
				catMainCode: code //"310196646654500"//
			},
			success: function(dataJsonp) {
				alert('查询电子证照成功')
				try{
					
					var jsonData = {
						applyId: applyId,
						/*"205fae3a-beee-492e-826e-7dd86513b2a8",*/
						stuffId: stuffId,
						reset: '',
						fileName: "waitUploadImg.jpg",
						type: "2",
						itemId: itemId,
						stuffName: stuffName,
					};
					jsonData = JSON.stringify(jsonData);
					try{
						$.device.httpDownload(
							'http://180.169.7.194:8080/ac-product'+dataJsonp[0].pictureUrlForBytes,
							"C:\\waitUploadImg.jpg",
							function(bytesCopied, totalBytes) {
								console.log(bytesCopied + "," + totalBytes);
							},
							function(result) {
								alert('下载成功')
								$.device.httpUpload('http://218.202.254.222/aci/materialUp/uploadFile.do', "fileInput", "C:/waitUploadImg.jpg",
									jsonData,
									function(result) {
										alert('上传成功')
										$.log.debug("success:" + JSON.stringify(dataJsonp1))
										callback1 && callback1(dataJsonp1);
									},
									function(webexception) {
										$.log.debug("error:" + JSON.stringify(webexception))
									});
							},
							function(webexception) {
								alert("下载文档失败");
							}
						);
					}catch(e){
						$.log.debug('下载上传--'+e)
					}
					
//					$.ajax({
//						url: $.getConfigMsg.preUrl +'/aci/workPlatform/elderlyCard/uploadArchiveInfo.do',
//						type: "post",
//						dataType: "json",
//						data: {
//							archivescode: archivescode,
//							affairscode: affairscode,
//							archivesname: archivesname,
//							needflag: needflag,
//							img: $.getConfigMsg.preUrl + dataJsonp[0].pictureUrl
//						},
//						success: function(dataJsonp1) {
//							$.log.debug("success:" + JSON.stringify(dataJsonp1))
//							callback1 && callback1(dataJsonp1);
//						},
//						error: function(err) {
//							$.log.debug("err:" + JSON.stringify(err))
//						}
//					});
				}catch(e){}
				callback && callback(dataJsonp);
			},
			error: function(err) {
				$.log.debug("err:" + JSON.stringify(err))
			},
//			complete : function(XMLHttpRequest,status){ //请求完成后最终执行参数
//		　　　　if(status=='timeout'){//超时,status还有success,error等值的情况
//		 　　　　　queryLicense .abort();
//		　　　　}
//				complete && complete(status);
//		　　}
		});
	}
	
	return {
		pro_fetch: product
	}
})