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
app.directive('selectSearch', function($compile) {
    return {
        restrict: 'AE',
        scope: {
            datas: '=',
        },
        template: '<input type = "text" class="input" ng-change="changeKeyValue(searchField)" name="{{name}}" ng-model="searchField"' +
                    'ng-click = "hidden=!hidden" value="{{searchField}}" placeholder="请输入关键字搜索"/></input>' +
                    '<div ng-hide="hidden" style = "position: absolute; width: 100%">' +
                    ' <select style="width: 100%; font-size: 24px;" ng-change="change(x)" ng-model="x" multiple>' +
                    '  <option ng-repeat="data in datas" >{{data.dkdzxmc}}</option>' +
                    ' </select>' +
                    '</div>',
        link: function($scope, elem, attr, ctrl,$rootScope) {
            $scope.tempdatas = $scope.datas; //下拉框选项副本
            $scope.hidden = true; //选择框是否隐藏
            $scope.searchField = ''; //文本框数据
            $scope.name = attr.name;
            $scope.change = function(x) {
                $scope.searchField = x;
                $scope.$emit('fundCenter',$scope.searchField[0])
                $scope.hidden = true;
            }
            $scope.changeKeyValue = function(v) {
                var newDate = []; //临时下拉选副本
                angular.forEach($scope.datas, function(data, index, array) {
                    if(data.dkdzxmc.indexOf(v) >= 0) {
                        newDate.unshift(data);
                    }
                });
                $scope.datas = newDate;
                $scope.hidden = false;
                if($scope.datas.length == 0 || '' == v) {
                    $scope.datas = $scope.tempdatas;
                }
            }
        }
    };
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
        $rootScope.allList.push.apply($rootScope.allList, [{
            "parentCode": "000000",
            "code": "319900",
            "name": "上海市"
        }])
//      console.log($rootScope.allList)
    }
    all();
    return {
        getAreaCode: getAreaCode
    }
})