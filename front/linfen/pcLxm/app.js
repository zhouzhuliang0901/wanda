var app = angular.module("terminalApp", ["ngAnimate", "ui.router"]);
app.value("appData", {});
app.run(function($rootScope, $log, $location, $state) {
	$rootScope.$on("$viewContentLoaded", function(event, toState) {
		$rootScope.goHome = function(){
			$state.go('loginType');
		}
		$rootScope.proMinimizes = function(){
			console.log("最小化");
			try{
				console.log("最小化");
				window.external.ProMinimizes();
			}catch(e){}
		}
	});
});
