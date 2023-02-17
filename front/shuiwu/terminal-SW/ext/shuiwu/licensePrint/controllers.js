app.controller("mainController", function ($scope, $route, $location, data, $http, $timeout) {
    PROMISE_METHOD.getQrCodeInfo()
        .then(function (code) {
            return new Promise(function (resolve, reject) {
                var httpConfig = {
                    codeParam: code,
                    jsonpCallback: "JSON_CALLBACK"
                };
                $http.jsonp("http://31.0.178.111/autoTeminal/aci/window/getInfoByCodeTest.do", {
                        params: httpConfig
                    })
                    .success(function (dataJsonp) {
                        resolve(dataJsonp);
                    })
                    .error(function (err) {
                        reject("接口出错：" + err);
                    });
            })
        })
        .then(function (data) {
            if (data.url == "") {
                layer.msg("未识别到证照信息！");
                $route.reload();
                throw Error("未识别到证照信息！");
            }
            return PROMISE_METHOD.httpDownImage($.getConfigMsg.preUrl + data.url, "d:/licenseImg.jpg")
        })
        .then(function (fileName) {
            return PROMISE_METHOD.imageReSaveGray(fileName);
        })
        .then(function () {
            $location.path("/choose").search({
                imageType: "licensePrint",
                payParams: "1"
            });
            $scope.$apply();
        })
        .catch(function (err) {
            console.log(err);
        })
});