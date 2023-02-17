var app = angular.module("declareApp", ["ng", "ngRoute"]);
app.factory('data', function() {
    return {};
});
app.filter('to_trusted', ['$sce', function($sce) {
	return function(text) {
		if(text!=undefined&&text!=""&&text!=null){
			text = text+"";
			text = text.replace(/nbsp&/ig, "");
			return $sce.trustAsHtml(text);
		}
	};
}]);
