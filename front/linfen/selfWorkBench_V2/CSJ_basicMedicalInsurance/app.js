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
	const getAreaCode = function(provinceCode) {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/medicalInsuranceTransfer/getAreaCode.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				provinceCode: provinceCode
			},
			success: function(res) {
				$rootScope.allList.push.apply($rootScope.allList, res);
			},
			error: function(err) {
				console.log(err)
			},
		});
	}
	const all = function() {
		$rootScope.allList = [];
		getAreaCode('330000');
		getAreaCode('320000');
		getAreaCode('340000');
		$rootScope.allList.push.apply($rootScope.allList, [{
			"parentCode": "000000",
			"code": "319900",
			"name": "上海市"
		}])
		console.log($rootScope.allList)
	}
	all();
	return {
		getAreaCode: getAreaCode
	}
})