//主控制器
app.controller("mainController", function ($scope, $route, $location, $http) {
    //获取部门数据
    $scope.matter = function (code, Name) {
        $location.path("/list").search({
            code: code,
            Name: Name
        });
    };
});
//主列表控制器
app.controller("listController", function ($scope, $route, $http, $location, data, $timeout, $routeParams, $jsonp) {
    $scope.listId = $location.search().code; //获取路由参数code
    $scope.itemListContainer = null; //数据容器
    $scope.itemList = null; //展示数据
    $scope.totalPages = null; //总页数
    $scope.currentPage = 1;
    $scope.searchDataContainer = null;
    $scope.matterVal = null;
    $scope.emptyTips = false;
    $scope.isLetter = /^[a-zA-Z]+$/g; //全字母验证
    $scope.isChinese = /^[\u4e00-\u9fa5]+$/g; //全中文验证
    $scope.windowName = $location.search().Name; //窗口名称
    data.windowName = $scope.windowName;
    /**
     * 首字母搜索
     */
    $scope.autoSearch = null;
    $scope.$watch('matterVal', function (n, o) {
        $timeout.cancel($scope.autoSearch);
        if (n === o) {
            return;
        }
        $scope.autoSearch = $timeout(function () {
                $scope.searchMatter(n);
        }, 600);
    });
    $scope.searchMatter = function (matter) {
        var searchDataContainer = $scope.searchDataContainer; //全部事项
        var result = []; //搜索结果
        if (matter === null || matter === '' || matter === undefined) {
            $scope.itemListContainer = EXTPaging({ //分页
                data: searchDataContainer,
                quantity: 8
            });
        } else {
            var matter = matter.toLowerCase() || '';
            if ($scope.isLetter.test(matter) === true) { //如果是首字母进行搜索
                $scope.isLetter.lastIndex = 0;
                for (var i = 0; i < matter.length; i++) { //遍历 搜索字母
                    result[i] = [];
                    if (result.length > 1) { //多个
                        for (var k = 0; k < result[i - 1].length; k++) {
                            var currentItem = result[i - 1][k];
                            var currentItemSearchIndex = currentItem.searchIndex;
                            if (matter[i] === hasSpell(currentItem.stItemName, currentItemSearchIndex).initial) { //如果
                                currentItem.searchIndex = hasSpell(currentItem.stItemName, currentItem.searchIndex).index + 1;
                                result[result.length - 1].push(currentItem);
                            }
                        }
                        if (result[i].length < 1) {
                            break;
                        }
                    } else { //单个
                        for (var j = i; j < searchDataContainer.length; j++) {
                            if (matter[i] === hasSpell(searchDataContainer[j].stItemName).initial) { //如果
                                var item = searchDataContainer[j];
                                item.searchIndex = hasSpell(searchDataContainer[j].stItemName).index + 1;
                                result[result.length - 1].push(item);
                            }
                        }
                        if (result[i].length < 1) { //如果第一个字母匹配不上直接return
                            break;
                        }
                    }
                }
            } else if ($scope.isChinese.test(matter) === true) {  
                $scope.isChinese.lastIndex = 0;
                result[0] = [];
                for (var i = 0; i < searchDataContainer.length; i++) {
                    if (searchDataContainer[i].stItemName.search(matter) !== -1) {
                        result[0].push(searchDataContainer[i]);
                    }
                }
            } else {
                layer.alert("请输入字母或者关键字进行搜索！");
            }
            $scope.itemListContainer = EXTPaging({ //分页
                data: result[result.length - 1],
                quantity: 8
            });
        };
       
        $scope.totalPages = $scope.itemListContainer.pages;
        $scope.itemList = $scope.itemListContainer.data[0];
        if (String($scope.itemList) === '' || !$scope.itemList) {
            $scope.emptyTips = true;
        } else {
            $scope.emptyTips = false;
        }

        function hasSpell(str, _index) { //搜索字符串里第一个中文的拼音
            if (_index === undefined || _index === null) {
                _index = 0;
            };
            for (var i = _index; i < str.length; i++) {
                if (getSpell(str[i]) !== false) {
                    return {
                        initial: getSpell(str[i])[0],
                        index: i
                    };
                };
            };
            return false;
        };
    };
    //获取部门列表数据
    $scope.getListMsg = function (currentPage) {

        $.ajax({
            url: $.getConfigMsg.preUrl + "/aci/app/getAllItemListById.do",
            dataType: "jsonp",
            jsonp: "jsonpprifx",
            data: {
                username: "yun",
                password: "04b34557c2110962",
                id: $scope.listId,
                p: undefined
            },
            success: function (dataJsonp) {
                $scope.searchDataContainer = dataJsonp;
                $scope.searchMatter();

                $scope.$apply();
            },
            error: function (err) {
                layer.alert('对不起没有找到数据，请返回首页', {
                    skin: 'layui-layer-lan',
                    closeBtn: 0,
                    anim: 4 //动画类型
                });
            }
        })
    }
    $scope.nextPage = function () { //下一页
        if ($scope.currentPage < $scope.totalPages) {
            ++$scope.currentPage;
            $scope.itemList = $scope.itemListContainer.data[$scope.currentPage - 1];
        }
    };
    $scope.previousPage = function () { //上一页
        if ($scope.currentPage > 1) {
            --$scope.currentPage;
            $scope.itemList = $scope.itemListContainer.data[$scope.currentPage - 1];
        }
    };
    $scope.getMatterDetail = function (stItemId, stGroupId, stItemName) {
        data.stItemId = stItemId;
        data.stGroupId = stGroupId;
        data.stItemName = stItemName;
        $location.path("/date");
    };
    $scope.getListMsg($scope.currentPage);
});
//选择预约时间控制器
app.controller("dateController", function ($scope, $route, $location, $http, data, $timeout, $jsonp) {
    //获取可以预约的日期
    $scope.appointmentInfo = {};
    $scope.selectTimer = false;
    $scope.Quantum = null;
    $scope.matterTitle = data.stItemName;
    $scope.windowName = data.windowName;
    $scope.timeQuantum = function (quantum) {
        if (quantum === "am") { //存储详情id
            try {
                data.stDetailId = $scope.appointmentInfo[0].stDetailId;
            } catch (error) {
                layer.msg("上午没有预约！");
                return;
            }
        } else if (quantum === "pm") {
            try {
                data.stDetailId = $scope.appointmentInfo[1].stDetailId;
            } catch (error) {
                layer.msg("下午没有预约！");
                return;
            }
        }
        $scope.Quantum = quantum;
    };
    $scope.getOrderDate = function () {
        $jsonp.get("/aci/app/getReservationAllDay.do", {
            stItemId: data.stItemId,
            stGroupId: data.stGroupId
        }, function (dataJsonp) {
            $scope.dataList = [];
            for (var i = 0; i < dataJsonp.length; i++) {
                var element = dataJsonp[i].day;
                $scope.dataList.push(element)
            }
            $scope.lastDate = $scope.dataList[$scope.dataList.length - 1];
            $scope.readOnlyDate = angular.fromJson(dataJsonp.fullList);
            WdatePicker({ //日期插件
                eCont: 'datepicker',
                doubleCalendar: true,
                dateFmt: 'yyyy-MM-dd',
                minDate: '%y-%M-{%d+1}',
                opposite: true,
                isShowToday: false,
                disabledDates: $scope.dataList,
                onpicked: function (dp) {
                    checkedDate = dp.cal.getDateStr();
                    data.date = checkedDate; //日期
                    $scope.orderTimes(checkedDate);
                }
            });
        })
    };
    $scope.orderTimes = function (date) {
        $jsonp.get("/aci/app/getReservationAllTime.do", {
            stItemId: data.stItemId,
            stGroupId: data.stGroupId,
            date: date
        }, function (dataJsonp) {
            if (dataJsonp.length >= 1) {
                $scope.selectTimer = true;
                $scope.Quantum = null;
                $scope.appointmentInfo = angular.copy(dataJsonp);
                $scope.$apply();
            }
        })
    };
    $scope.confirmAppointment = function () { //确认预约日期
        if ($scope.Quantum !== null) {
            $location.path("/idcard");
        } else {
            layer.msg("请选择时间段！");
        };
    };
    $scope.getOrderDate();
});
//刷身份证
app.controller("idcardController", function ($scope, $route, $location, $http, data, $timeout) {
    PROMISE_METHOD.getIdCardInfo()
        .then(function(dataObj){
            var _data = JSON.parse(dataObj.identityInfo);
            data.idCardName = _data.Name;
            data.idCardNum = _data.Code; 
            $location.path("/input");
            $scope.$apply();
        })
        .catch(function(err){
            console.log(err)
        })
});
//输入身份证号跟手机号控制器
app.controller("inputController", function ($scope, $route, $http, $location, data, $jsonp) {
    $scope.phoneNumber = "";
    //检测手机号码是否符合规范
    $scope.testMobile = function (string) {
        var standard = /^1[34578]\d{9}$/;
        if (standard.test(string)) {
            return true;
        }
        return false;
    };
    $scope.sendAppointment = function () {
        if ($scope.testMobile($scope.phoneNumber) === true) { //检测手机号码格式
            $jsonp.get("/aci/app/saveReservationInfo.do", {
                certNo: data.idCardNum,
                stUsername: data.idCardName,
                itemId: data.stItemId,
                stDetailId: data.stDetailId,
                stMobile: $scope.phoneNumber,
                date: data.date,
                stIdentityType: "1",
                token: '',
                name: '',
                id: '',
                deviceType: "3",
            }, function (dataJsonp) {
                data.errorCode = dataJsonp;
                $location.path("/hint");
            })
        } else if ($scope.testMobile($scope.phoneNumber) === false) {
            //手机号码格式有误
            layer.msg("请输入正确的手机号码!")
        };
    };
});
app.controller("hintController", function ($scope, $route, data, $timeout) {
    $scope.dataNum = parseInt(data.errorCode);
    $scope.appointmentStatus = "预约成功";
    switch ($scope.dataNum) {
        case -1: //预约失败
            $scope.appointmentStatus = "预约失败";
            break;
        case -2: //预约已满
            $scope.appointmentStatus = "预约已满";
            break;
        case -3: //已经预约过了
            $scope.appointmentStatus = "已经预约过了";
            break;
        default: //预约成功
            $scope.appointmentStatus = "预约成功";
    };
    $timeout(function () {
        $.device.GoHome();
    }, 5000);
});