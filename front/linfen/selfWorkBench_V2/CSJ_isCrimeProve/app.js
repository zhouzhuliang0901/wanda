var app = angular.module("terminalApp", ["ngAnimate", "ui.router"]);
app.value("appData", {});
app.filter('to_trusted', ['$sce', function ($sce) {
    return function (text) {
        return $sce.trustAsHtml(text);
    };
}]);
app.directive('myTextarea', function() {
	return {
		require: 'ngModel',
		link: function(scope, ele, attrs, modelController) {
			let text = attrs.myTextarea;
			let placeholder = attrs.placeholder;
			let alltext = text + '' + placeholder;
//			ele.attr('placeholder', alltext);
			ele.on('focus', function() {
				if(!modelController.$modelValue) {
					setVal(text);
				}
			});
			ele.on('blur', function() {
				if(modelController.$modelValue === text) {
					setVal('');
				}
			});
			function setVal(v) {
				modelController.$setViewValue(v);
				modelController.$render();
			}
		}
	}
});
app.run(function($rootScope, $log, $location, $state) {
	$rootScope.$on("$viewContentLoaded", function(event, toState) {
		$rootScope.goAppHistoryBack = function() {
			$.device.Camera_Hide();
			$.device.idCardClose();
			$.device.qrCodeClose();
			if($state.$current.name=="main") {
				$.device.GoHome();
			} else {
				$location.path("/main");
			}
		};
	});
});
app.factory("appFactory", function($http, $rootScope) {
	var product = function(tokenSNO, callback, error) {
		$.ajax({
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getAccessToken.do",
			type: "post",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				tokenSNO: tokenSNO,
			},
			success: function(res) {
				callback && callback(res);
			},
			error: function(err) {},
		})
	}
	var uploadFile = function(idCard, name, end, start, code, applyNo, callback, callback1) {
		$.ajax({
			url: $.getConfigMsg.preUrlSelf + "/selfapi/dzzz/queryCertBaseDatas.do",
			type: "post",
			dataType: "json",
			timeout: 5000,
			jsonp: "jsonpCallback",
			data: {
				certNo: idCard, //"340881199303145313"
				name: encodeURI(name),
				type: "0", //licenseType ,//
				catMainCode: code, //"310196646654500"//
				machineId: $.config.get("uniqueId") || "HPZX001",
				itemName: encodeURI("户籍证明开具"),
				itemCode: "0105105000-07-04",
				businessCode: "",
				startDay: start,
				endDay: end,
			},
			success: function(dataJsonp) {
				try {
					// let formdata = new FormData();
					// formdata.append("applyNo", applyNo);
					// formdata.append("stuffCode", "stuff011");
					// formdata.append("stuffId", "");
					// formdata.append("FileData", dataJsonp.data[0].str);
					var formdata = {
						"applyNo":applyNo,
						"stuffCode":"stuff011",
						"stuffId":"",
						"FileData":dataJsonp.data[0].str,
					};
					$.ajax({
						url: $.getConfigMsg.preUrlSelf + '/selfapi/selfDeclareForCSJ/uploadStuffs.do',
						type: "post",
						dataType: "json",
						data: formdata,
						// cache: false, // 不缓存
						// processData: false, // jQuery不要去处理发送的数据
						// contentType: false,
						success: function(dataJsonp1) {
							$.log.debug("success:" + JSON.stringify(dataJsonp1))
							callback1 && callback1(dataJsonp1);
						},
						error: function(err) {
							$.log.debug("err:" + JSON.stringify(err))
						}
					});
				} catch(e) {}
				callback && callback(dataJsonp);
			},
			error: function(err) {
				$.log.debug("err:" + JSON.stringify(err))
			}
		})
	}
	
	//字典项
	var getDinctionary = function(rootCode,parentCode,callback){
//		$.getConfigMsg.preUrlSelf = "http://180.169.7.194:8081/ac-self";
		$.ajax({
			url: $.getConfigMsg.preUrlSelf + "/selfapi/changjiangDelta/getDinctionary.do",
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				rootCode: rootCode,
				parentCode:parentCode
			},
			success: function(res) {
				callback && callback(res);
			},
			error: function(err) {},
		})
	}
	getDinctionary('hjDepart','',function(res){
		$rootScope.zjslDepartList =res
	})
	getDinctionary('yjDepart','',function(res){
		$rootScope.zjyjAddressList =res
	})
	getDinctionary('ahhjDepart','',function(res){
		$rootScope.ahslDepartList =res
	})
	return {
		pro_fetch: product,
		upload_file: uploadFile,
		get_dinctionary:getDinctionary
	}
})