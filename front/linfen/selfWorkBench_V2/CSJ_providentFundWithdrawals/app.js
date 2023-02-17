var app = angular.module("terminalApp", ["ngAnimate", "ui.router"]);
app.value("appData", {});
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
			if($state.$current.name == "main") {
				$.device.GoHome();
			} else {
				$location.path("/main");
			}
		};
	});
});
app.factory("appFactory", function($http, $rootScope) {
	//字典项
	var getDinctionary = function(rootCode,callback){
		$.ajax({
			url: $.getConfigMsg.preUrlSelf + "/selfapi/withdrawalOfProvidentFund/dictionary.do",
			type: "get",
			dataType: "json",
			// jsonp: "jsonpCallback",
			data: {
				code: rootCode,
			},
			success: function(res) {
				callback && callback(res);
			},
			error: function(err) {},
		})
	}
	return {
		get_dinctionary:getDinctionary
	}
})