//主控制器
app.controller("mainController", function ($scope, $route, $location, $http) {
    //获取部门数据
    $scope.departments = null;
    $scope.container = null;
    $scope.currentPage = 1;
    $scope.totalPages = 1;
    $scope.getDepartment = function () {
        PROMISE_METHOD.fetchGet('/aci/autoterminal/reservation/getOrgans.do', {
                fmd: 'aci-reservation',
                fdo: 'getAllOrgans',
                dept: ''
            })
            .then(function (result) {
                var data = EXTPaging({
                    data: result,
                    quantity: 10
                });
                $scope.container = data;
                $scope.departments = $scope.container.data[0];
                $scope.totalPages = $scope.container.pages;
                console.log($scope.container)
                $scope.$apply();
            })
            .catch(function (err) {
                console.log(err);
            })
    };
    $scope.prev = function () {
        if ($scope.currentPage > 1) {
            $scope.currentPage--;
            $scope.departments = $scope.container.data[$scope.currentPage - 1];
        }
    };
    $scope.next = function () {
        if ($scope.currentPage < $scope.totalPages) {
            $scope.currentPage++;
            $scope.departments = $scope.container.data[$scope.currentPage - 1];
        }
    };
    $scope.matter = function (code, name) {
        $location.path("/list").search({
            code: code,
            Name: name
        })
    }
    $scope.getDepartment();
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
    data.windowCode = $scope.listId;
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
    $scope.searchMatter = function (matter) { //首字母和关键字搜索算法 业务逻辑无关
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
            if (isNaN(parseInt(_index) === true)) {
                console.log("_index 不是有效的索引");
                return;
            };
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
        PROMISE_METHOD.fetchGet("/aci/autoterminal/forward.do", {
                fmd: 'aci-reservation',
                fdo: 'getAllItemsByOrganId',
                organId: $scope.listId,
                pageSize: 10,
                currentPage: undefined
            })
            .then(function (result) {
                if (!result) {
                    layer.alert('对不起没有找到数据，请返回首页', {
                        skin: 'layui-layer-lan',
                        closeBtn: 0,
                        anim: 4 //动画类型
                    });
                    return;
                }
                $scope.searchDataContainer = result;

                $scope.searchMatter();
                $scope.$apply();
            })
            .catch(function (err) {
                throw Error("promise error!")
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
    $scope.getMatterDetail = function (stItemNo, stPlaceId, stItemName) {
        data.stItemNo = stItemNo;
        data.stPlaceId = stPlaceId;
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
    $scope.goPrevCatalog = function () {
        $location.path("/list").search({
            code: data.windowCode,
            Name: $scope.windowName
        });
    };
    $scope.timeQuantum = function (quantum) {
        try {
            data.stDetailId = $scope.appointmentInfo[quantum].stDetailId;
        } catch (error) {
            data.stDetailId = null;
            alert("当前时段没有预约！");
            return;
        }
        $scope.Quantum = quantum;
    };
    $scope.getOrderDate = function () {
        PROMISE_METHOD.fetchGet("/aci/autoterminal/forward.do", {
                fmd: 'aci-reservation',
                fdo: 'getReservationAllDay',
                placeId: data.stPlaceId,
                itemNo: data.stItemNo
            })
            .then(function (result) {
                $scope.dataList = [];
                $scope.lastDate = $scope.dataList[$scope.dataList.length - 1];
                $scope.dataList = angular.fromJson(result);
                WdatePicker({ //日期插件
                    eCont: 'datepicker',
                    doubleCalendar: true,
                    dateFmt: 'yyyy-MM-dd',
                    minDate: '%y-%M-{%d+1}',
                    opposite: true,
                    disabledDates: $scope.dataList,
                    onpicked: function (dp) {
                        checkedDate = dp.cal.getDateStr();
                        data.date = checkedDate; //日期
                        $scope.orderTimes(checkedDate);
                    }
                });
            })

    };
    $scope.orderTimes = function (_date) {
        PROMISE_METHOD.fetchGet("/aci/autoterminal/forward.do", {
                fmd: 'aci-reservation',
                fdo: 'getReservationAllTime',
                itemNo: data.stItemNo,
                placeId: data.stPlaceId,
                date: _date
            })
            .then(function (result) {
                if (result.length >= 1) {
                    $scope.selectTimer = true;
                    $scope.Quantum = null;
                    $scope.appointmentInfo = angular.copy(result);
                    $scope.$apply();
                }
            })
    };
    $scope.confirmAppointment = function () { //确认预约日期
        if ($scope.Quantum !== null && data.stDetailId !== null) {
            $location.path("/idcard");
        } else {
            alert("请正确选择时间段！");
        };
    };
    $scope.getOrderDate();
});
//刷身份证
app.controller("idcardController", function ($scope, $route, $location, $http, data, $timeout) {
    $scope.base = isExtModule();
    OcxControl.idCardRead(function (dataObj) {
        var _data = JSON.parse(dataObj.identityInfo);
        data.idCardName = _data.Name;
        data.idCardNum = _data.Code;
        $timeout(function () {
            $location.path("/input");
        }, 10)

    }, function (err) {
        console.log("err: " + err);
    });

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
            PROMISE_METHOD.fetchGet("/aci/autoterminal/forward.do", {
                    fmd: 'aci-reservation',
                    fdo: 'saveReservationInfo',
                    itemNo: data.stItemNo,
                    placeId: data.stPlaceId,
                    detailId: data.stDetailId,
                    date: data.date,
                    certNo: data.idCardNum,
                    userName: data.idCardName,
                    userId: '',
                    mobile: $scope.phoneNumber,
                    identityType: '1',
                    reservationSource: '3',
                    business: '',
                    unit: '',
                    unified: ''
                })
                .then(function (result) {
                    data.statusCode = result;
                    $location.path("/hint");
                    $scope.$apply();
                })
        } else if ($scope.testMobile($scope.phoneNumber) === false) {
            //手机号码格式有误
            alert("请你输入正确的手机号码!")
        };
    };
});
app.controller("hintController", function ($scope, $route, data, $timeout) {
    $scope.dataNum = parseInt(data.statusCode);
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