var app = angular.module("guidelineApp", ["ng", "ngRoute"]);
app.factory('data', function() {
    return {};
});
app.filter('to_trusted', ['$sce', function ($sce) {
    return function (text) {
        return $sce.trustAsHtml(text);
    };
}]);
