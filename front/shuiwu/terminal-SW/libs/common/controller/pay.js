app.controller("payController", function ($scope, $route, $location, $http, $timeout) {
    var strPath = window.document.location.pathname;
    var path = strPath.substring(strPath.indexOf("/", 2), strPath.length);
    $scope.basePath = "../";
    if (path.indexOf("ext") > 0) {
        $scope.basePath = "../../../";
    };
    /**
     * 支付单独模块需要在前置程序中路由到这需要传一个参数 例如: $location.path("/pay").search({payMoneyQuantity:"1"})
     * $location.search().payMoneyQuantity
     * 
     * (打印的份数,金额固定是0.5一份)
     * 
     * 支付接口
     *        类型类型  methodType  //两种支付方式  微信 "weixin" ,  支付宝 "alipay"
     *        支付数量  number      //数字
     *        商品     默认参数就为1
     * 支付类型默认为微信
     */
    $scope.payMoneyQuantity = $location.search().payMoneyQuantity || 1; //打印的份数
    $scope.payType = "weixin"; //支付类型
    $scope.payCode = $scope.basePath + "libs/common/images/code.jpg";
    $scope.getPayCode = function (type) {
        if (($scope.payMoneyQuantity - 0) > 0) { //判断支付数量数据正确性
            var httpConfig = {
                number: $scope.payMoneyQuantity,
                methodType: type,
                commodity_id: "1",
                jsonpprifx: "JSON_CALLBACK"
            }
            $http.jsonp($.getConfigMsg.preUrl + "/aci/app/getpay2dcode.do", {
                    params: httpConfig
                })
                .success(function (dataJsonp) {
                    console.log(dataJsonp)
                    $scope.payCode = dataJsonp.qrcodeAddress;
                    $scope.getPayStatus(dataJsonp.stApplyNo); //轮询支付状态
                })
                .error(function () {
                    layer.alert('请重新选择支付方式', {
                        skin: 'layui-layer-lan',
                        closeBtn: 0,
                        anim: 4 //动画类型
                    });
                });
        } else {
            layer.alert('支付操作有误，请重新操作', {
                skin: 'layui-layer-lan',
                closeBtn: 0,
                anim: 4 //动画类型
            });
        }
    }
    $scope.getPayStatus = function (applyNo) { //参数applyNo是支付接口返回的支付编号
        var httpConfig = {
            jsonpprifx: "JSON_CALLBACK",
            stApplyNo: applyNo
        };
        $http.jsonp($.getConfigMsg.preUrl + "/aci/app/isPay.do", {
                params: httpConfig
            })
            .success(function (dataJsonp) {
                if (dataJsonp.result === "ok") {
                    //支付成功
                    $location.path("/pwait").search({
                        printQuantity: $scope.payMoneyQuantity
                    });
                    alert("支付成功!")
                } else if (dataJsonp.result === "error") {
                    //支付失败
                } else {
                    //暂无状态
                    $timeout(function () {
                        $scope.getPayStatus(applyNo);   
                    }, 300);
                }
            })
            .error(function (err) {
                console.log(err)
            });
    }
    $scope.getPayCode($scope.payType);

});