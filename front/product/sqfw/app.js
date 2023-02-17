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
