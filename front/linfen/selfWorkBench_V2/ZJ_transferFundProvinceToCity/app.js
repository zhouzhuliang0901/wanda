var app = angular.module("terminalApp", ["ngAnimate", "ui.router"]);
app.value("appData", {});
app.filter('myFilter', function() {
	return function(value,collection) {
		var result = "";
		angular.forEach(collection, function(item) {
			//过滤数组中值与指定值相同的元素
			if(item['id'] == value) {
				result = item.name
			}
		});
		return result;
	}
})
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
app.directive('selectSearch', function($compile) {
    return {
        restrict: 'E',
        scope: {
            datas: '=',
            loading: '&',
            stopLoading: '&',
        },
        template: '<input type = "text" class="input" ng-change="changeKeyValue(searchField)" name="fundCenter" ng-model="searchField"' +
            'ng-click = "hidden=!hidden" value="{{searchField}}"/></input>' +
            '<div ng-hide="hidden" style = "position: absolute; width: 100%">' +
            ' <select style="width: 100%; font-size: 24px" ng-change="change(x)" ng-model="x" multiple>' +
            '  <option ng-repeat="data in datas" value={{data}}>{{data.ZXMC}}</option>' +
            ' </select>' +
            '</div>',
        link: function($scope, elem, attr, ctrl) {
            $scope.tempdatas = $scope.datas; //下拉框选项副本
            $scope.hidden = true; //选择框是否隐藏
            $scope.searchField = ''; //文本框数据
            $scope.change = function(x) {
                var result = JSON.parse(x[0])
                $scope.searchField = result.ZXMC;
                $scope.$emit('test',result.ZXDM);
                $scope.hidden = true;
            }
            $scope.changeKeyValue = function(placeName) {
                $scope.loading();
                $.ajax({
                    type: "post",
                    url: $.getConfigMsg.preUrlSelf + "/selfapi/accumulationFundChange/otherProvincesQuery.do",
                    dataType: "json",
                    jsonp: "jsonpCallback",
                    data: {
                        placeName: encodeURI(placeName)
                    },
                    success: function(res) {
                        $scope.stopLoading();
                        $scope.datas = res.dataList;
                        $scope.hidden = false;
                    },
                    error: function(err) {
                        $scope.stopLoading();
                        console.log(err)
                    },
                });
                if($scope.datas.length == 0 || '' == placeName) {
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