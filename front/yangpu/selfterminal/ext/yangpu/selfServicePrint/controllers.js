app.controller("mainController", function ($scope, $http, $location, data) {
    $scope.departments = [];
    $scope.keywords = '';
    data.keywords = '';
    $scope.fetchDepartments = function () {
        $http.jsonp($.getConfigMsg.preUrl + "/aci/autoterminal/forward.do", {
                params: {
                    jsonpCallback: "JSON_CALLBACK",
                    fmd: 'aci-selfFilling',
                    fdo: 'getSelfmFormCatList',
                    selfmFormCatId: "12"
                }
            })
            .success(function (data) {
                $scope.departments = data;
            });
    }
    $scope.fetchDepartments();
    $scope.matter = function (id) {
        $location.path("/matter").search({
            id: id
        })
    };
    $scope.searchForm = function () {
        if (!$scope.keywords) {
            alert("请输入关键字!");
            return;
        }
        $http.jsonp($.getConfigMsg.preUrl + "/aci/autoterminal/forward.do", {
                params: {
                    jsonpCallback: "JSON_CALLBACK",
                    fmd: 'aci-selfFilling',
                    fdo: 'getNewSelfmFillFormListForName',
                    pageSize: "8",
                    currentPage: '1',
                    itemName: $scope.keywords
                }
            })
            .success(function (result) {
                if (!result) {
                    alert("没有关于" + $scope.keywords + "的表格!");
                } else {
                    data.formContainer = result;
                    data.keywords = $scope.keywords;
                    $location.path("/forms");
                }
            });
    };
});
app.controller("matterController", function ($scope, $http, $location,data) {
    $scope.sectionCode = $location.search().id;
    $scope.matters = [];
    $scope.matterForms = function (id) {
        data.matterId = id;
        $location.path("/forms");
    }
    $http.jsonp($.getConfigMsg.preUrl + "/aci/autoterminal/forward.do", {
            params: {
                jsonpCallback: "JSON_CALLBACK",
                fmd: 'aci-selfFilling',
                fdo: 'getSelfmFormCatList',
                selfmFormCatId: $scope.sectionCode
            }
        })
        .success(function (data) {
            $scope.matters = data;
        });
});
app.controller("formsController", function ($scope, $http, $location, data) {
    $scope.matterCode = data.matterId;
    $scope.dataSource = (data.keywords) ? '1' : '0'; //0代表层级关系进来  1代表搜索关键字进来
    $scope.currentPage = 1;
    $scope.totalPages = 1;
    $scope.matterForms = [];
    $scope.fetchUrl = $.getConfigMsg.preUrl;
    $scope.previewForm = function (name, img) {
        data.previewImg = img;
        $location.path("/preview").search({
            formName: escape(name)
        })
    };
    $scope.fetchForm = function () {
        $http.jsonp($.getConfigMsg.preUrl + "/aci/autoterminal/forward.do", {
                params: {
                    jsonpCallback: "JSON_CALLBACK",
                    fmd: 'aci-selfFilling',
                    fdo: 'getNewSelfmFillForms',
                    selfmFormCatId: $scope.matterCode,
                    pageSize: '8',
                    currentPage: $scope.currentPage
                }
            })
            .success(function (data) {
                $scope.matterForms = data;
                $scope.totalPages = data[0].totalPage;
            });
    };
    $scope.formSearchForm = function () {
        $http.jsonp($.getConfigMsg.preUrl + "/aci/autoterminal/forward.do", {
                params: {
                    jsonpCallback: "JSON_CALLBACK",
                    fmd: 'aci-selfFilling',
                    fdo: 'getNewSelfmFillFormListForName',
                    pageSize: "8",
                    currentPage: $scope.currentPage,
                    itemName: data.keywords
                }
            })
            .success(function (data) {
                $scope.matterForms = data;
                $scope.totalPages = data[0].totalPage;
            });
    };
    if ($scope.dataSource === '1') {
        $scope.matterForms = data.formContainer;
        $scope.totalPages = $scope.matterForms[0].totalPage;
    } else if ($scope.dataSource === '0') {
        $scope.fetchForm();
    }
    $scope.next = function () {
        if ($scope.currentPage < $scope.totalPages) {
            $scope.currentPage++;
            if ($scope.dataSource === '1') {
                $scope.formSearchForm();
            } else if ($scope.dataSource === '0') {
                $scope.fetchForm();

            }
        }
    }
    $scope.prev = function () {
        if ($scope.currentPage > 1) {
            $scope.currentPage--;
            if ($scope.dataSource === '1') {
                $scope.formSearchForm();
            } else if ($scope.dataSource === '0') {
                $scope.fetchForm();
            }
        }
    }
});
app.controller("previewController", function ($scope, $http, $location, data,$timeout) {
    $scope.formName = unescape($location.search().formName);
    $scope.formImage = data.previewImg;
    $timeout(function(){
        $.device.officeOpenRelative($scope.formName);
        $.device.officeShow(700, 800, 200, 230); //显示文档  测试使用
        $.device.officeReadOnly(true);
    },10)
    $scope.confirmTake = function () {
        $.device.officePrint();
        $location.path("/pwait").search({});
    };
    $scope.back = function () {
        $location.path("/forms");
    };
});