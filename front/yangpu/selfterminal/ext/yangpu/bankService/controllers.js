app.controller("icbcController", function ($scope, $location) {
    $scope.bank_show_info = {
        xmbc: [{
            img: "../../../libs/common/images/bankService/xmbc/service_code.jpg",
            tips: "请用微信扫描上方二维码办理预约开户"
        }],
        icbc: [{
                img: "../../../libs/common/images/bankService/icbc/预约开户-01.png",
                tips: "预约开户",
                letter: "yykh"
            },
            {
                img: "../../../libs/common/images/bankService/icbc/外币预约-02.png",
                tips: "外币预约",
                letter: "wbyy"
            },
            {
                img: "../../../libs/common/images/bankService/icbc/在线缴费-e缴费-03.png",
                tips: "在线缴费-e缴费",
                letter: "zxjf"
            }, {
                img: "../../../libs/common/images/bankService/icbc/代发工资-04.png",
                tips: "代发工资",
                letter: 'dfgz'
            },
            {
                img: "../../../libs/common/images/bankService/icbc/代理货物贸易名录登记-05.png",
                tips: "代理货物贸易名录登记",
                letter: "dlhw"
            },
            {
                img: "../../../libs/common/images/bankService/icbc/代理直接投资外汇登记-06.png",
                tips: "代理直接投资外汇登记",
                letter: "dlzh"
            },
            {
                img: "../../../libs/common/images/bankService/icbc/小微金融服务平台-07.png",
                tips: "小微金融服务平台",
                letter: "xwjr"
            },
            {
                img: "../../../libs/common/images/bankService/icbc/经营快贷-08.png",
                tips: "经营快贷",
                letter: "jykd"
            },
            {
                img: "../../../libs/common/images/bankService/icbc/网贷通-09.png",
                tips: "网贷通",
                letter: "wdt"
            },
        ],
        bbc: [{
                img: "../../../libs/common/images/bankService/bbc/惠懂你下载.jpg",
                tips: "惠懂你下载"
            },
            {
                img: "../../../libs/common/images/bankService/bbc/惠懂你详情.jpg",
                tips: "惠懂你详情"
            }
        ]
    }
    $scope.goDetail = function (code) {
        $location.path("/detail").search({
            code: code
        })
    };
});
app.controller("detailController", function ($scope, $location) {
    $scope.bank_show_detail = {
        icbc: {
            yykh: [{
                img: "../../../libs/common/images/bankService/icbc/预约开户.png",
                tips: "预约开户",
            }],
            wbyy: [{
                img: "../../../libs/common/images/bankService/icbc/外币预约.png",
                tips: "外币预约",
            }],
            zxjf: [{
                    img: "../../../libs/common/images/bankService/icbc/e缴费/e缴费-电信手机充值.jpg",
                    tips: "电信手机充值"
                },
                {
                    img: "../../../libs/common/images/bankService/icbc/e缴费/e缴费-东方有线电视缴费.jpg",
                    tips: "东方有线电视缴费"
                },
                {
                    img: "../../../libs/common/images/bankService/icbc/e缴费/e缴费-联通手机充值.jpg",
                    tips: "联通手机充值"
                },
                {
                    img: "../../../libs/common/images/bankService/icbc/e缴费/e缴费-浦东威丽亚自来水缴费.jpg",
                    tips: "浦东威丽亚自来水缴费"
                },
                {
                    img: "../../../libs/common/images/bankService/icbc/e缴费/e缴费-上海城投水务缴费.jpg",
                    tips: "上海城投水务缴费"
                },
                {
                    img: "../../../libs/common/images/bankService/icbc/e缴费/e缴费-上海电信固话宽带缴费.jpg",
                    tips: "上海电信固话宽带缴费"
                },
                {
                    img: "../../../libs/common/images/bankService/icbc/e缴费/e缴费-上海交警违章缴费.jpg",
                    tips: "上海交警违章缴费"
                },
                {
                    img: "../../../libs/common/images/bankService/icbc/e缴费/e缴费-上海燃气缴费.jpg",
                    tips: "上海燃气缴费"
                },
                {
                    img: "../../../libs/common/images/bankService/icbc/e缴费/e缴费-移动手机充值.jpg",
                    tips: "移动手机充值"
                }
            ],
            dfgz: [{
                img: "../../../libs/common/images/bankService/icbc/代发工资.jpg",
                tips: "代发工资",
            }],
            dlhw: [{
                img: "../../../libs/common/images/bankService/icbc/代理货物贸易名录登记.jpg",
                tips: "代理货物贸易名录登记",
            }],
            dlzh: [{
                img: "../../../libs/common/images/bankService/icbc/代理直接投资外汇登记.jpg",
                tips: "代理直接投资外汇登记",
            }],
            xwjr: [{
                img: "../../../libs/common/images/bankService/icbc/小微金融服务平台.jpg",
                tips: "小微金融服务平台",
            }],
            jykd: [{
                img: "../../../libs/common/images/bankService/icbc/经营快贷.jpg",
                tips: "经营快贷",
            }],
            wdt: [{
                img: "../../../libs/common/images/bankService/icbc/网贷通.jpg",
                tips: "网贷通",
            }],
        },
        xmbc: {},
        bbc: {}
    };
    $scope.detailData = $scope.bank_show_detail.icbc[$location.search().code];
    $scope.detailCode = $location.search().code;
    $scope.largeImg = "../../../libs/common/images/bankService/icbc/经营快贷.jpg";
    $scope.largeTips = "";
    $scope.closeFlag = true;
    $scope.imgShow = function (imgUrl,tips) {
        $scope.largeImg = imgUrl;
        $scope.largeTips = tips;
        $scope.closeFlag = !$scope.closeFlag;
    }
});

app.controller("ccbController", function () {

});
app.controller("xmbcController", function () {

});