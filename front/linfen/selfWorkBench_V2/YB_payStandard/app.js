var app = angular.module("terminalApp", ["ngAnimate", "ui.router"]);
app.value("appData", {});
app.filter('to_trusted', ['$sce', function($sce) {
	return function(text) {
		return $sce.trustAsHtml(text);
	};
}]);
app.run(function($rootScope, $log, $location, $state) {
	$rootScope.$on("$viewContentLoaded", function(event, toState) {
		$(function() {
			$(".tabBotbox1inner1").perfectScrollbar();
			$(".main2").perfectScrollbar();
		});
		$rootScope.goAppHistoryBack = function() {
			$.device.Camera_Hide();
			$.device.idCardClose();
			$.device.qrCodeClose();
			try {
				window.external.URL_CLOSE();
			} catch(e) {
				//TODO handle the exception
			}
			if($state.$current.name == "main") {
				$.device.GoHome();
			} else {
				$location.path("/main");
			}
		};
	});
});
app.factory("appFactory", function($http, $rootScope) {
	var getMedicines = function(drugType, itemType, callback, error) {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/MedicalInstitution/getDrugClassificationDictionaries.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				drugType: drugType,
				drugClassification: itemType
			},
			success: function(dataJson) {
				callback && callback(dataJson);
			},
			error: function(err) {
				callback && callback(err);
				$.log.debug("err:" + JSON.stringify(err))
			}
		});
	}
	var all = function() {
		getMedicines("chineseMedicine", 1,
			function(dataJson) {
				$rootScope.CBType = dataJson;
			}
		);
		getMedicines("chineseMedicine", 2,
			function(dataJson) {
				$rootScope.CMType = dataJson;
			}
		);
		getMedicines("chineseMedicine", 3,
			function(dataJson) {
				$rootScope.CSType = dataJson;
			}
		)
		getMedicines("westernMedicine", 1,
			function(dataJson) {
				$rootScope.WBType = dataJson;
			}
		)
		getMedicines("westernMedicine", 2,
			function(dataJson) {
				$rootScope.WMType = dataJson;
			}
		)
		getMedicines("westernMedicine", 3,
			function(dataJson) {
				$rootScope.WSType = dataJson;
			}
		)
	}
	all();
	return {
		pro_fetch: getMedicines
	}
})