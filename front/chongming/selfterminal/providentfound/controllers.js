app.controller("mainController", function ($scope, $route, $location, $http, data) {
    //通过刷身份证获取公积金信息
    $scope.readIDCard = function () {
        $.device.idCardOpen(function (value) {
            var list = eval('(' + value + ')');
            $scope.idCardNum = list.Number;
            $scope.idCardName = list.Name;
            var httpConfig = {
                parmas: {
                    username: "yun",
                    password: "04b34557c2110962",
                    id_card_num: list.Number,
                    id_card_name: list.Number,
                    jsonpprifx: "JSON_CALLBACK"
                }
            }
            $http.jsonp("http://31.0.178.111:8080/autoTeminalyz/aci/app/getGjjxx.do", httpConfig)
                .success(function (dataJsonp) {
                    data.info = dataJsonp;
                    $location.path("/infoList");
                });
        });
    }
    $scope.readIDCard();
});
app.controller("infoListController", function ($scope, $route, $http, $location, data, $timeout) {
    $basicFound = false;
    $supplementFound = false;
    $noramlTable = true;
    $selectType = function(){
        
    };
    $printTable = function(){

    };
});
app.controller("printImgController", function () {

});