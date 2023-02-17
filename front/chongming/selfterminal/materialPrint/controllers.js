app.controller("mainController", function ($scope, $route, $location, data, $timeout) {
    PROMISE_METHOD.getQrCodeInfo()
        .then(function (code) {
            if (code.indexOf("http") <0) {
                layer.msg("没有获取到材料!");
                $route.reload();
                throw Error("未识别到证照信息！");
            }
            return PROMISE_METHOD.httpDownImage(code, "d:/networkImg.jpg");
        })
        .then(function (fileName) {
            return PROMISE_METHOD.imageReSaveGray(fileName);
        })
        .then(function () {
            $location.path("/pwait").search({
                imageType: "selfPrint",
                payParams: "1"
            });
            $scope.$apply();
        })
        .catch(function (err) {
            console.log(err)
        })
});