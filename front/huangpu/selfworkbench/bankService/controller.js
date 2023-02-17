app.controller("type", function($scope, $state, appData) {
	$scope.goToApp = function(address) {
		if(address.indexOf("http") != -1) {
			window.location.href = address;
		} else {
			window.location.href = address;
		}
	};
})
app.controller("bankMain", function($scope, $state, appData, $stateParams, $location) {
	$scope.bankType = $location.search().name;
	$scope.read = false;
	appData.bankType = $scope.bankType;
	$scope.funName = $scope.bankType;
	$scope.consult_title = "企业开户请详询客户经理:";
	$scope.consult1 = "邹经理13816672927";
	$scope.consult2 = "吴经理13818890511";
	$scope.consult3 = "王经理13564229535";

	switch($scope.bankType) {
		case 'abc':
			$scope.funName = '农业银行';
			break;
		case 'icbc':
			$scope.funName = '黄浦支行';
			break;
		case 'luwan':
			$scope.funName = '卢湾支行';
			break;
		case 'xmbc':
			$scope.funName = '厦门银行';
			break;
		case 'ccb':
			$scope.funName = '建设银行';
			break;
		case 'pfbc':
			$scope.funName = '浦发银行';
			$scope.read = true;
			break;
		case 'boc':
			$scope.funName = '中国银行';
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
				img: "../libs/common/images/bankService/bbc/yykh.jpg",
				tips: "预约开户"
			},{
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
		],
		boc: [{
				img: "../libs/common/images/bankService/boc/37℃.jpg",
				tips: "37℃中小微企业服务平台",
				letter: "qyfw"
			},
			{
				img: "../libs/common/images/bankService/boc/yykh.jpg",
				tips: "在线开户预约",
				letter: "yykh"
			},
			{
				img: "../libs/common/images/bankService/boc/zyed.jpg",
				tips: "中银e企贷",
				letter: "zyed"
			},
			{
				img: "../libs/common/images/bankService/boc/zyzhf.jpg",
				tips: "中银智慧付",
				letter: "zyzhf"
			}
		],
		luwan: [{
				img: "../libs/common/images/bankService/luwan/E抵快贷.png",
				tips: "E抵快贷",
				letter: "edkd"
			},
			{
				img: "../libs/common/images/bankService/luwan/经营快贷.png",
				tips: "经营快贷",
				letter: "jykd"
			},
			{
				img: "../libs/common/images/bankService/luwan/网贷通.png",
				tips: "网贷通",
				letter: "wdt"
			},
			{
				img: "../libs/common/images/bankService/luwan/小企业经营型物业贷款.png",
				tips: "小企业经营型物业贷款",
				letter: "wydk"
			},
			{
				img: "../libs/common/images/bankService/luwan/小企业周转贷款.png",
				tips: "小企业周转贷款",
				letter: "zzdk"
			},
			{
				img: "../libs/common/images/bankService/luwan/中小微担保贷款.png",
				tips: "中小微担保贷款",
				letter: "dbdk"
			}
		],
		abc: [{
				img: "../libs/common/images/bankService/abc/预约开户.png",
				tips: "预约开户",
				letter: "yykhabc"
			},
			{
				img: "../libs/common/images/bankService/abc/抵押E贷.png",
				tips: "抵押E贷",
				letter: "dyed"
			},
			{
				img: "../libs/common/images/bankService/abc/银保贷.png",
				tips: "银保贷",
				letter: "ybd"
			}, {
				img: "../libs/common/images/bankService/abc/微捷贷.png",
				tips: "微捷贷",
				letter: 'wjd'
			},
			{
				img: "../libs/common/images/bankService/abc/跨境“惠”企.png",
				tips: "跨境“惠”企",
				letter: "kjhq"
			},
			{
				img: "../libs/common/images/bankService/abc/网捷贷.png",
				tips: "个人网捷贷",
				letter: "grwjd"
			},
			{
				img: "../libs/common/images/bankService/abc/个人掌银.png",
				tips: "个人掌银",
				letter: "grwy"
			},
			{
				img: "../libs/common/images/bankService/abc/信用卡.png",
				tips: "信用卡",
				letter: "xyk"
			},
			{
				img: "../libs/common/images/bankService/abc/基金宝.png",
				tips: "基金宝",
				letter: "jjb"
			},
		]

	}
	$scope.currentShow = $scope.bank_show_info[$scope.bankType];
	//alert($scope.currentShow[2].tips)
	//alert($scope.bankType)
	$scope.choiceMatter = function(matter) {
		if(matter.letter) {
			appData.matterType = matter.letter;
			$state.go("details");
		}
	}
	$scope.goHome = function() {
		$.device.GoHome();
	}
});
app.controller("bankChoice", function($scope, $state, $stateParams, appData, $location) {
	$scope.huangpu = function() {
		$location.path("/main").search({
			name: "icbc"
		});
	}
	$scope.luwan = function() {
		$location.path("/main").search({
			name: "luwan"
		});
	}
	$scope.prevStep = function(){
		$state.go("type");
	}
});
app.controller("bankDetail", function($scope, $state, $stateParams, appData, $location) {
	$scope.matterType = appData.matterType;
	$scope.funName = appData.bankType;
	switch(appData.bankType) {
		case 'abc':
			$scope.funName = '农业银行';
			break;
		case 'icbc':
			$scope.funName = '黄浦支行';
			break;
		case 'luwan':
			$scope.funName = '卢湾支行';
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
		case 'boc':
			$scope.funName = '中国银行';
			break;
	}
	$scope.prevStep = function() {
		$location.path("/main").search({
			name: appData.bankType
		});
		console.info($scope.funName);
	};
	$scope.isLarge = false;
	$scope.currentObj = null;
	$scope.largeImg = function(index, item) {
		$scope.currentObj = item;
		$scope.isLarge = true;

	};
	$scope.close = function() {

		$scope.isLarge = false;
	}
	$scope.bank_show_detail = {
		icbc: {
			yykh: [{
				img: "../libs/common/images/bankService/icbc/预约开户.jpg",
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
		bbc: {},
		boc: {
			qyfw: [{
				img: "../libs/common/images/bankService/boc/37℃中小微企业服务平台.jpg",
				tips: "37℃中小微企业服务平台",
			}],
			yykh: [{
				img: "../libs/common/images/bankService/boc/在线开户预约.jpg",
				tips: "在线预约开户",
			}],
			zyed: [{
				img: "../libs/common/images/bankService/boc/中银e企贷.jpg",
				tips: "中银e企贷",
			}],
			zyzhf: [{
				img: "../libs/common/images/bankService/boc/中银智慧付.jpg",
				tips: "中银智慧付",
			}],
		},
		luwan: {
			edkd: [{
				img: "../libs/common/images/bankService/luwan/E抵快贷.jpg",
				tips: "E抵快贷",
			}],
			jykd: [{
				img: "../libs/common/images/bankService/luwan/经营快贷.jpg",
				tips: "经营快贷",
			}],
			wdt: [{
				img: "../libs/common/images/bankService/luwan/网贷通.jpg",
				tips: "网贷通",
			}],
			wydk: [{
				img: "../libs/common/images/bankService/luwan/小企业经营型物业贷款.jpg",
				tips: "小企业经营型物业贷款",
			}],
			zzdk: [{
				img: "../libs/common/images/bankService/luwan/小企业周转贷款.jpg",
				tips: "小企业周转贷款",
			}],
			dbdk: [{
				img: "../libs/common/images/bankService/luwan/中小微担保贷款.jpg",
				tips: "中小微担保贷款",
			}],
		},
		abc: {
			yykhabc: [{
				img: "../libs/common/images/bankService/abc/黄浦预约开户.jpg",
				tips: "黄浦预约开户",
			}],
			dyed: [{
				img: "../libs/common/images/bankService/abc/抵押E贷.jpg",
				tips: "抵押E贷",
			}],
			ybd: [{
					img: "../libs/common/images/bankService/abc/银保贷.jpg",
				tips: "银保贷",
			}],
			wjd: [{
				img: "../libs/common/images/bankService/abc/微捷贷.jpg",
				tips: "微捷贷",
			}],
			kjhq: [{
				img: "../libs/common/images/bankService/abc/跨境惠企.png",
				tips: "跨境惠企",
			}],
			grwjd: [{
				img: "../libs/common/images/bankService/abc/网捷贷.jpg",
				tips: "个人网捷贷",
			}],
			grwy: [{
				img: "../libs/common/images/bankService/abc/个人掌银.jpg",
				tips: "个人掌银",
			}],
			xyk: [{
				img: "../libs/common/images/bankService/abc/信用卡.jpg",
				tips: "信用卡",
			}],
			jjb: [{
				img: "../libs/common/images/bankService/abc/基金宝.jpg",
				tips: "基金宝",
			}],
		},
	};
	$scope.showMatter = $scope.bank_show_detail[appData.bankType][$scope.matterType];
})