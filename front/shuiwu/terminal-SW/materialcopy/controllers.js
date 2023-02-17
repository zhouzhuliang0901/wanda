app.controller("mainController", function ($scope, $route, $location) {
    $scope.photograph = null; //base容器
    $scope.savedPhoto = false; //过程状态   false是准备拍照状态  true保存状态
    //显示高拍仪
    angular.element(document).ready(function () {
        OcxControl.scanOpen({
            left: 850,
            top: 310,
            height: 525,
            width: 742.5
        },function(data){
            console.log(data)
        },function(err){
            console.log(err)
        })
    });
    $scope.photo = function () { //拍照
        OcxControl.scanSave(function (base64) {
            $scope.photograph = "data:image/jpg;base64," + base64;
            $scope.hint = "请预览下方照片，您是否确认保存";
            $scope.savedPhoto = true;
            $scope.$apply();
        });
    };
    $scope.hint = "请将您的材料对准屏幕下方高拍仪";
    $scope.reload = function () { //重拍
        //刷新页面的方法
        $route.reload();
    };
    $scope.confirm = function () { //确认
        $location.path("/pwait").search({
            imageType:"materials",
        });
    }
});