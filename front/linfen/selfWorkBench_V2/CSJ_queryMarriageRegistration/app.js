var app = angular.module("terminalApp", ["ngAnimate", "ui.router"]);
app.value("appData", {});
app.filter('to_trusted', ['$sce', function($sce) {
    return function(text) {
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
            //          ele.attr('placeholder', alltext);
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
    var getAddressInfo = function(provinceName, cityId, callback) {
        $.ajax({
            type: "get",
            url:  $.getConfigMsg.preUrlSelf + "/selfapi/archivesForCSJ/getArchivesArea.do",
            dataType: "json",
            jsonp: "jsonpCallback",
            data: {
                provinceName: encodeURI(provinceName),
                cityId: cityId
            },
            success: function(res) {
                callback && callback(res);
            },
            error: function(err) {},
        });
    }
    return {
        pro_fetch: product,
        getAddressInfo: getAddressInfo
    }
})