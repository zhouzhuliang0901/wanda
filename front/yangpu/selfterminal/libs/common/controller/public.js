/**
 * 		公共控制器(打印图片、身份证、收费) 公共函数(市民云扫描)
 * 		
 * 		打印数量选择 chooseController
* 									|____________
* 	  											 |----接收参数 imageType 打印图片类型类型 idcard身份证  licensePrint自助亮证  materials自助材料(img)打印
* 	  											 |----接收参数 printType 打印方式 html打印html文件  img直接打印图片 默认打印html
													  |---如果 printType为img  增加一个参数 imgfilepath需要打印图片的路径 	
* 	  											 |----接收参数 payParams 是否收费类型 1收费 0不收费  默认不收费0
 *		支付		payController
								|____________
											 |-----支付控制器  接收参数   payMoneyQuantity 打印数量
											 |-----支付控制器  接收参数   payParams 是否收费
 *  	打印		printController
 * 								  |____________
 * 		 						 			   |-----接收 printType 打印方式 html 打印html文件  img 直接打印图片 默认参数为html
 * 													 |---如果 printType为img  增加一个参数 imgfilepath需要打印图片的路径 	
 * 	  										   |-----接收参数  打印图片类型类型 idcard身份证  licensePrint自助亮证  materials自助材料(img)打印 没有默认值必须传正确的参数
 * 	
 */
function isExtModule() {
	var strPath = window.document.location.pathname;
	var path = strPath.substring(strPath.indexOf("/", 2), strPath.length);
	var basePath = "../";
	if (path.indexOf("/ext/") > 0) {
		basePath = "../../../";
	}
	return basePath;
}
app.controller("publicIdCard", function ($scope, $rootScope, $http, $log, $location, $timeout) {
	$scope.base = isExtModule();
	$scope.hasCitizenCloud = $.getConfigMsg.openCitizenCloudLogin;
	$scope.currentLogin = 'idcard';
	$scope.verificationType = "身份证";
	$scope.tipsInfo = "插入身份证";
	$rootScope.$on("$viewContentLoaded", function (evt, next, current) {
		$timeout(function () {
			if (document.getElementsByClassName("idcardLogin").length > 0) {
				document.getElementsByClassName("idcardLogin")[0].addEventListener("click", function () {
					$scope.currentLogin = 'idcard';
					$scope.verificationType = "身份证";
					$scope.tipsInfo = "插入身份证";
					try {
						OcxControl.BarcodeClose();
					} catch (error) {
						console.log(error);
					}
					$scope.$apply();
				});
			} else {
				console.log("没有获取到身份证按钮")
			}

			if (document.getElementsByClassName("cloudLogin").length > 0) {
				document.getElementsByClassName("cloudLogin")[0].addEventListener("click", function () {
					$scope.currentLogin = 'cloud';
					$scope.verificationType = "市民云扫码";
					$scope.tipsInfo = "扫描市民云亮证二维码";
					try {
						OcxControl.idCardClose();

					} catch (error) {
						console.log(error)
					}
					$scope.$apply();
				});
			} else {
				console.log("没有获取二维码按钮")
			}
		}, 10);
	});
});
app.controller("chooseController", function ($scope, $rootScope, $route, $location) {
	var strPath = window.document.location.pathname;
	var path = strPath.substring(strPath.indexOf("/", 2), strPath.length);
	$scope.basePath = "../";
	if (path.indexOf("ext") > 0) {
		$scope.basePath = "../../../";
	};
	$scope.printNums = 1;

	$rootScope.PRINT_IMAGE_TYPE = $location.search().imageType //打印图片类型
	$rootScope.PRINT_TYPE = $location.search().printType //打方式型
	$rootScope.PRINT_IMGFILEPATH = $location.search().imgfilepath //图片路径
	$scope.payParams = $location.search().payParams || '0'; //是否需要支付 需要1 不需要0
	$scope.add = function () {
		$scope.printNums < 5 ? $scope.printNums++ : 5;
	};
	$scope.minus = function () {
		$scope.printNums > 1 ? $scope.printNums-- : 1;
	};
	$scope.isCharge = $.getConfigMsg.isCharge;
	$scope.payJudge = function () {
		if ($scope.payParams == 1 && $scope.isCharge) {
			$location.path("/pay").search({
				printQuantity: $scope.printNums
			});
		} else if ($scope.payParams == 0) {
			$location.path("/pwait").search({
				printQuantity: $scope.printNums
			})
		}
	}
});
app.controller("payController", function ($scope, $route, $rootScope, $location, $http, $timeout, $interval) {

	$scope.basePath = isExtModule();
	/**
	 * 支付单独模块需要在前置程序中路由到这需要传一个参数 例如: $location.path("/pay").search({payMoneyQuantity:"1"})
	 * $location.search().payMoneyQuantity
	 * 
	 * (打印的份数,金额固定是0.5一份)
	 * 
	 * 支付接口
	 *        类型类型  methodType  //两种支付方式  微信 "wechat" ,  支付宝 "alipay"
	 *        支付数量  number      //数字
	 *        商品     默认参数就为1
	 * 支付类型默认为微信
	 */
	$scope.$on("$locationChangeStart", function () {
		$interval.cancel($scope.queryPayStatus);
	});
	$scope.codeType = 'wechat';
	$scope.payMoneyQuantity = $location.search().printQuantity || 1; //打印的份数
	$scope.payType = "wechat"; //支付类型
	$scope.payCode = $scope.basePath + "libs/common/images/wechat.jpg";
	$scope.payStatus = true;
	$scope.payUrl = $.getConfigMsg.urlHostPay;
	$scope.unitPrice = 0.01;
	$scope.payStApplyNo = null;
	$scope.queryPayStatus = null;

	$scope.getPayCode = function () {
		$scope.payStatus = false;
		if (($scope.payMoneyQuantity - 0) > 0) { //判断支付数量数据正确性
			var httpConfig = {
				jsonpCallback: "JSON_CALLBACK",
				ST_COMMODITY_DESC: "自助服务机",
				paySource: "selfTerminal",
				ST_PAY_TOOL: "wechat",
				MONEY: $scope.payMoneyQuantity * $scope.unitPrice,
			}
			$http.jsonp($scope.payUrl + "/aci/pay/newPayOrder.do", {
					params: httpConfig
				})
				.success(function (dataJsonp) {
					$scope.payStApplyNo = dataJsonp.stOrderId;
					$scope.payCode = $scope.payUrl + '/aci/pay/getPayQrcodeByNewScanOrderId.do?ST_ORDER_ID=' + $scope.payStApplyNo + '&NM_TIMES=6&_t=' + new Date().getTime();
					$scope.queryPayStatus = $interval($scope.getPayStatus, 1500, 100); //轮询支付状态
				})
				.error(function () {
					layer.alert('请重新选择支付方式', {
						skin: 'layui-layer-lan',
						closeBtn: 0,
						anim: 4 //动画类型
					});
				});
		} else {
			layer.alert('金额无效！', {
				skin: 'layui-layer-lan',
				closeBtn: 0,
				anim: 4 //动画类型
			});
		}
	};
	$scope.changePayType = function (type) { //改变支付方式
		$scope.codeType = type;
		var httpConfig = {
			jsonpCallback: "JSON_CALLBACK",
			stOrderId: $scope.payStApplyNo,
			methodType: type
		}
		$http.jsonp($scope.payUrl + "/aci/pay/updatePayOrder.do", {
				params: httpConfig
			})
			.success(function (dataJsonp) {
				console.log(dataJsonp)
				$scope.payCode = $scope.payUrl + '/aci/pay/getPayQrcodeByNewScanOrderId.do?ST_ORDER_ID=' + $scope.payStApplyNo + '&NM_TIMES=6&_t=' + new Date().getTime();
				$interval.cancel($scope.queryPayStatus);
				$scope.queryPayStatus = $interval($scope.getPayStatus, 1500, 100);
			})
			.error(function () {
				layer.alert('请重新选择支付方式', {
					skin: 'layui-layer-lan',
					closeBtn: 0,
					anim: 4 //动画类型
				});
			});
	};
	$scope.getPayStatus = function () { //参数applyNo是支付接口返回的支付编号
		var httpConfig = {
			jsonpCallback: "JSON_CALLBACK",
			ST_ORDER_ID: $scope.payStApplyNo
		};
		$http.jsonp($scope.payUrl + "/aci/pay/queryOrder.do", {
				params: httpConfig
			})
			.success(function (dataJsonp) {
				if (dataJsonp.nmIsPaid == "1") {
					$interval.cancel($scope.queryPayStatus);
					//支付成功
					$location.path("/pwait").search({
						printQuantity: $scope.payMoneyQuantity
					});
					console.log("支付成功!");
				} else if (dataJsonp.result === "error") {
					//支付失败
				} else {
					//暂无状态
					console.log("query")
				}
			})
			.error(function (err) {
				console.log(err)
			});
	}
	$scope.getPayCode($scope.payType);
});
app.controller("printController", function ($scope, $route, $location, $rootScope, $timeout) {
	var strPath = window.document.location.pathname;
	$scope.basePath = "../";
	if (strPath.indexOf("ext") > 0) {
		$scope.basePath = "../../../";
	}
	$scope.PRINT_TYPE = $location.search().printType || $rootScope.PRINT_TYPE; //获取打印类型 
	$scope.PRINT_IMGAGE_TYPE = $location.search().imageType || $rootScope.PRINT_IMAGE_TYPE; //获取图片打印类型

	$scope.PRINT_IMGFILEPATH = $location.search().imgfilepath || $rootScope.PRINT_IMGFILEPATH; //获取图片路径
	$scope.printQuantity = $location.search().printQuantity; //获取打印份数

	if ($scope.PRINT_TYPE == undefined && $scope.PRINT_IMGAGE_TYPE == undefined && $scope.PRINT_IMGFILEPATH === undefined) {
		$timeout(function () {
			window.external.GoHome();
		}, 4000);
	} else {
		if ($scope.PRINT_TYPE === undefined || $scope.PRINT_TYPE == "html") { //打印html文件
			OcxControl.laserPrint($scope.PRINT_IMGAGE_TYPE, $scope.printQuantity || 1, function (res) {
				window.external.GoHome();
				console.log("print success!");
			}, function (err) {
				console.log("err: " + err);
			});
		} else if ($scope.PRINT_TYPE === "img") { //直接打印图片
			OcxControl.nativePrint($scope.PRINT_IMGFILEPATH, $scope.printQuantity, "gray", function (res) {
				$timeout(function () {
					window.external.GoHome();
				}, 2000);
			});
		} else { //不打印
			$timeout(function () {
				window.external.GoHome();
			}, 4000);
		}
	}
});
app.directive("ngImgError", function () {
	return {
		link: function (scope, element, attrs) {
			scope.strPath = window.document.location.pathname;
			scope.locationOrigin = window.document.location.origin;
			scope.basePath = "../";
			if (scope.strPath.indexOf("/ext/") > 0) {
				scope.basePath = "../../../";
			}
			scope.failedShowImg = scope.basePath + 'libs/common/images/failToLoad.png';
			scope.loadingShowImg = scope.basePath + 'libs/common/images/loading-img.gif';
			scope.MaxfailedCount = 30;
			scope.imgLoadMethod = function (url, callback) {
				var img = new Image();
				img.src = url;
				img.onload = function (e) {
					callback && callback(e.target.src);
				}
				img.onerror = function (e) {
					--scope.MaxfailedCount;
					if (scope.MaxfailedCount < 1) {
						callback && callback(scope.failedShowImg)
						return;
					}
					scope.imgLoadMethod(e.target.src, callback);
				}
			}
			element.bind('error', function (e) {
				scope.imgLoadMethod(e.target.src, function (src) {
					e.target.src = src;
				});
			});
			element.bind('click', function (e) {
				if (e.target.src.indexOf('libs/common/images/failToLoad.png') > -1) {
					e.target.src = scope.loadingShowImg;
					e.target.className += " imgLoading";
					scope.imgLoadMethod(e.target.src, function (src) {
						e.target.src = element[0].getAttribute("ng-src");
						e.target.className = e.target.className.replace(/ imgLoading/, '');
					});
				}

			});
		}
	}
});
app.directive("ngImgError", function () {
	return {
		link: function (scope, element, attrs) {
			scope.strPath = window.document.location.pathname;
			scope.locationOrigin = window.document.location.origin;
			scope.basePath = "../";
			if (scope.strPath.indexOf("/ext/") > 0) {
				scope.basePath = "../../../";
			}
			scope.failedShowImg = scope.basePath + 'libs/common/images/failToLoad.png';
			scope.loadingShowImg = scope.basePath + 'libs/common/images/loading-img.gif';
			scope.MaxfailedCount = 130;
			scope.imgLoadMethod = function (url, callback) {
				var img = new Image();
				img.src = url;
				img.onload = function (e) {
					callback && callback(e.target.src);
				}
				img.onerror = function (e) {
					--scope.MaxfailedCount;
					if (scope.MaxfailedCount < 1) {
						callback && callback(scope.failedShowImg)
						return;
					}
					scope.imgLoadMethod(e.target.src, callback);
				}
			}
			element.bind('error', function (e) {
				scope.imgLoadMethod(e.target.src, function (src) {
					e.target.src = src;
				});
			});
			element.bind('click', function (e) {
				if (e.target.src.indexOf('libs/common/images/failToLoad.png') > -1) {
					e.target.src = scope.loadingShowImg;
					e.target.className += " imgLoading";
					scope.imgLoadMethod(e.target.src, function (src) {
						e.target.src = element[0].getAttribute("ng-src");
						e.target.className = e.target.className.replace(/ imgLoading/, '');
					});
				}

			});
		}
	}
});