app.controller("bankMain", function($scope, $state, appData, $stateParams,$location) {
	$scope.bankType =$location.search().name;
	appData.bankType= $scope.bankType;
	$scope.funName = '工商银行';
$scope.consult_title="企业开户请详询客户经理:";
$scope.consult1="邹经理13816672927";
$scope.consult2="吴经理13818890511";
$scope.consult3="王经理13564229535";

	switch($scope.bankType){
		case 'icbc':
			$scope.funName = '工商银行';
			break;
		case 'xmbc':
			$scope.funName = '厦门银行';
			break;
		case 'ccb':
			$scope.funName = '建设银行';
			break;
		case 'pfbc':
			$scope.funName = '浦发银行';
			break;
	}
	$scope.bank_show_info = {
		xmbc: [{
			img: "../libs/common/images/bankService/xmbc/service_code.jpg",
			tips: "请用微信扫描上方二维码办理预约开户"
		}],
		icbc: [{
				img: "../libs/common/images/bankService/icbc/预约开户-01.png",
				tips: "预约开户",
				letter: "yykh"
			},
			{
				img: "../libs/common/images/bankService/icbc/外币预约-02.png",
				tips: "外币预约",
				letter: "wbyy"
			},
			{
				img: "../libs/common/images/bankService/icbc/在线缴费-e缴费-03.png",
				tips: "在线缴费-e缴费",
				letter: "zxjf"
			}, {
				img: "../libs/common/images/bankService/icbc/代发工资-04.png",
				tips: "代发工资",
				letter: 'dfgz'
			},
			{
				img: "../libs/common/images/bankService/icbc/代理货物贸易名录登记-05.png",
				tips: "代理货物贸易名录登记",
				letter: "dlhw"
			},
			{
				img: "../libs/common/images/bankService/icbc/代理直接投资外汇登记-06.png",
				tips: "代理直接投资外汇登记",
				letter: "dlzh"
			},
			{
				img: "../libs/common/images/bankService/icbc/小微金融服务平台-07.png",
				tips: "小微金融服务平台",
				letter: "xwjr"
			},
			{
				img: "../libs/common/images/bankService/icbc/经营快贷-08.png",
				tips: "经营快贷",
				letter: "jykd"
			},
			{
				img: "../libs/common/images/bankService/icbc/网贷通-09.png",
				tips: "网贷通",
				letter: "wdt"
			},
		],
		ccb: [{
				img: "../libs/common/images/bankService/bbc/惠懂你下载.JPG",
				tips: "惠懂你下载"
			},
			{
				img: "../libs/common/images/bankService/bbc/惠懂你详情.JPG",
				tips: "惠懂你详情"
			}
		],
		pfbc: [{
				img: "../libs/common/images/bankService/pfbc/kh.jpg",
				tips: "浦发黄浦支行开户"
			},
			{
				img: "../libs/common/images/bankService/pfbc/yy.jpg",
				tips: "浦发黄浦支行预约取现"
			},
			{
				img: "../libs/common/images/bankService/pfbc/sq.png",
				tips: "信用卡申请"
			},
			{
				img: "../libs/common/images/bankService/pfbc/xcx.jpg",
				tips: "个人贷款小程序-黄浦支行"
			}
		]

	}
	$scope.currentShow = $scope.bank_show_info[$scope.bankType];
	//alert($scope.currentShow[2].tips)
	//alert($scope.bankType)
	$scope.choiceMatter = function(matter){
		if(matter.letter){
			appData.matterType=matter.letter;
			$state.go("details");
		}
	}
});
app.controller("bankDetail", function($scope,$state,$stateParams,appData,$location) {
	$scope.matterType = appData.matterType;
	$scope.funName = '工商银行';
	switch(appData.bankType){
		case 'icbc':
			$scope.funName = '工商银行';
			break;
		case 'xmbc':
			$scope.funName = '厦门银行';
			break;
		case 'ccb':
			$scope.funName = '建设银行';
			break;
		case 'pfbc':
			$scope.funName = '浦发银行';
			break;
	}
	$scope.home = function(){
		$location.path("/main").search({
			name:appData.bankType
		});
	};
	$scope.isLarge = false;
	$scope.currentObj = null;
	$scope.largeImg = function(index,item){
		$scope.currentObj = item;
		$scope.isLarge= true;

	};
	$scope.close = function(){
		
		$scope.isLarge= false;
	}
	$scope.bank_show_detail = {
		icbc: {
			yykh: [{
				img: "../libs/common/images/bankService/icbc/预约开户.png",
				tips: "预约开户",
			}],
			wbyy: [{
				img: "../libs/common/images/bankService/icbc/外币预约.png",
				tips: "预约开户",
			}],
			zxjf: [{
					img: "../libs/common/images/bankService/icbc/e缴费/e缴费-电信手机充值.jpg",
					tips: "电信手机充值"
				},
				{
					img: "../libs/common/images/bankService/icbc/e缴费/e缴费-东方有线电视缴费.jpg",
					tips: "东方有线电视缴费"
				},
				{
					img: "../libs/common/images/bankService/icbc/e缴费/e缴费-联通手机充值.jpg",
					tips: "联通手机充值"
				},
				{
					img: "../libs/common/images/bankService/icbc/e缴费/e缴费-浦东威丽亚自来水缴费.jpg",
					tips: "浦东威丽亚自来水缴费"
				},
				{
					img: "../libs/common/images/bankService/icbc/e缴费/e缴费-上海城投水务缴费.jpg",
					tips: "上海城投水务缴费"
				},
				{
					img: "../libs/common/images/bankService/icbc/e缴费/e缴费-上海电信固话宽带缴费.jpg",
					tips: "上海电信固话宽带缴费"
				},
				{
					img: "../libs/common/images/bankService/icbc/e缴费/e缴费-上海交警违章缴费.jpg",
					tips: "上海交警违章缴费"
				},
				{
					img: "../libs/common/images/bankService/icbc/e缴费/e缴费-上海燃气缴费.jpg",
					tips: "上海燃气缴费"
				},
				{
					img: "../libs/common/images/bankService/icbc/e缴费/e缴费-移动手机充值.jpg",
					tips: "移动手机充值"
				}
			],
			dfgz: [{
				img: "../libs/common/images/bankService/icbc/代发工资.jpg",
				tips: "代发工资",
			}],
			dlhw: [{
				img: "../libs/common/images/bankService/icbc/代理货物贸易名录登记.jpg",
				tips: "代理货物贸易名录登记",
			}],
			dlzh: [{
				img: "../libs/common/images/bankService/icbc/代理直接投资外汇登记.jpg",
				tips: "代理直接投资外汇登记",
			}],
			xwjr: [{
				img: "../libs/common/images/bankService/icbc/小微金融服务平台.jpg",
				tips: "小微金融服务平台",
			}],
			jykd: [{
				img: "../libs/common/images/bankService/icbc/经营快贷.jpg",
				tips: "经营快贷",
			}],
			wdt: [{
				img: "../libs/common/images/bankService/icbc/网贷通.jpg",
				tips: "网贷通",
			}],
		},
		xmbc: {},
		bbc: {}
	};
	$scope.showMatter = $scope.bank_show_detail[appData.bankType][$scope.matterType];
})