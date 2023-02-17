var JA_Extranet_urlHost = 'http://xzfwzx.jingan.gov.cn:8080/ac'; // 静安外网地址
//1
app.controller('onecontroller', function($scope, $location, instance, appFactory, $timeout, $http, $state) {
	$scope.isLoading = false;
	$scope.list = [],
		$scope.itemList = [],
		$scope.detaTo = function() {
			// $location.path('/tow');
		}
	$scope.prevStep = function() {
		window.location.href = "../index.html";
		//		$state.go('../index.html');
		//		window.history.go(-1);
	}
	$scope.getDepartment = function() { //获取部
		$timeout(function() {
			appFactory.pro_fetch("forward.do", {
				fmd: 'aci-reservation',
				fdo: 'getOrgans',
				dept: ''
			}, function(data) {
				console.log(data);
				$scope.isLoading = true;
				$scope.itemList = data;
				//处理取到的数据 获取部门
				var namek = '';
				for(var i = 0; i < $scope.itemList.length; i++) {
					namek = $scope.itemList[i].description;
					//console.log(namek);
					$scope.list.push(namek);
				}
			})
		})
//		$.ajax({
//			url: 'http://xzfwzx.jingan.gov.cn:8080/ac/aci/autoterminal/forward.do',
//			type: "get",
//			dataType: "jsonp",
//			jsonp: "jsonpCallback",
//			data: {
//				fmd: 'aci-reservation',
//				fdo: 'getOrgans'
//			},
//			success: function(data) {
//				console.log(data);
//				$scope.isLoading = true;
//				$scope.itemList = data;
//				//处理取到的数据 获取部门
//				var namek = '';
//				for(var i = 0; i < $scope.itemList.length; i++) {
//					namek = $scope.itemList[i].description;
//					//console.log(namek);
//					$scope.list.push(namek);
//				}
//			},
//			error: function(err) {
//				console.log(err);
//				$.log.debug("err:" + JSON.stringify(err));
//			}
//		})

	}
	$scope.getDepartment();
	$scope.onclick = function(x) {
		var item = $scope.list[x]; //获取指定元素的值 
		console.log(item);
		//上传到自定义服务中 instance.name = item;
		instance.name = item;
		instance.organId = $scope.itemList[x].organId;
		setTimeout(function() {
			$(".tow").addClass("transformto");
		}, 50)
		setTimeout(function() {
			$(".tow").removeClass("transformto");
		}, 1053);
		$location.path('/tow');
	}
});
//2
app.controller('towcontroller', function($scope, $location, instance, appFactory, $timeout, $http) {
	$scope.isLoading = false;
	$scope.matterList = [],
		$scope.list2 = [],
		$scope.itemId = instance.organId,
		//上一步
		$scope.prevStep = function() {
			$location.path('/')
		}
	$scope.goHome = function() {
		$location.path('/');
	}
	//下一步
	$scope.detaTo = function() {
		// $location.path('/three')
	}
	$scope.getMatter = function(organId) {
		$timeout(function() {
			appFactory.pro_fetch("forward.do", {
				fmd: 'aci-reservation',
				fdo: 'getAllItemsByOrganId',
				organId: $scope.itemId,
				pageSize: 10,
				currentPage: undefined
			}, function(data) {
				$scope.isLoading = true;
				$scope.list2 = data;
			})
		})
	};
	$scope.getMatter();
	$scope.searchs = function(val) {
		console.log(val);
		$timeout(function() {
			appFactory.pro_fetch("forward.do", {
				fmd: 'aci-reservation',
				fdo: 'getAllItemsByItemName',
				stItemName: val
			}, function(data) {
				$scope.isLoading = true;
				$scope.list2 = data
				//console.info($scope.list2);		
			})
		})
	}
	$scope.towclick = function(x) {
		var item1 = $scope.list2[x].stItemName;
		var item2 = $scope.list2[x].stItemNo;
		var item3 = $scope.list2[x].stPlaceId;
		// 接受上传的数据=>数据共享 $scope.name = instance.name;
		$scope.name = instance.name;
		instance.stItemNo = item2;
		instance.stPlaceId = item3;
		console.log($scope.name + "==>" + item1);
		$location.path('/three');
		setTimeout(function() {
			$(".three").addClass("transformto");
		})
		setTimeout(function() {
			$(".three").removeClass("transformto");
		}, 1003);
	}
});
//3
app.controller('threecontroller', function($scope, $location, $state, instance, $timeout, appFactory) {
	$scope.isLoading = false;
	$scope.stItemNo = instance.stItemNo,
		$scope.stPlaceId = instance.stPlaceId,
		//定义数组接受可以预约的请求的日期
		$scope.arraydata = [];
	$scope.array = [];
	$scope.days = [];
	$scope.appointmentInfo = '',
		$scope.surplusCount = '',
		$scope.totalCount = "",
		$scope.stDetailId = '',
		$scope.stDetailId1 = '',
		$timeout(function() {
			appFactory.pro_fetch("forward.do", { //获取可预约日期
				fmd: 'aci-reservation',
				fdo: 'getReservationAllDay',
				placeId: $scope.stPlaceId,
				itemNo: $scope.stItemNo
			}, function(data) {
				$scope.isLoading = true;
				//console.log(data[1]); //取出可以预约的日期
				$scope.arraydata = data;
				//console.log($scope.arraydata)
				$scope.days.forEach(function(d) {
					//					console.info(d);
					d.show = false;
					for(var m = 0; m < $scope.arraydata.length; m++) {
						if($scope.arraydata[m] == d.fullDay) {
							d.show = true;
							break;
						}
					}
				});
			});
		});
	$scope.orderTimes = function(date) { //获取预约时段
		appFactory.pro_fetch("forward.do", {
			fmd: 'aci-reservation',
			fdo: 'getReservationAllTime',
			itemNo: $scope.stItemNo,
			placeId: $scope.stPlaceId,
			date: date
		}, function(result) {
			$scope.isLoading = true;
			$scope.currentCheck = null;
			//预约的时间
			$scope.appointmentInfo = result[0].stShow;
			//余剩的
			$scope.surplusCount = result[0].surplusCount;
			//总共几位
			$scope.totalCount = result[0].totalCount;
			$scope.appointmentInfo1 = result[1].stShow;
			//余剩的
			$scope.surplusCount1 = result[1].surplusCount;
			//总共几位
			$scope.totalCount1 = result[1].totalCount;
			$scope.stDetailId = result[0].stDetailId;
			$scope.stDetailId1 = result[1].stDetailId;
			console.log($scope.appointmentInfo)
		})
	}
	$scope.prevStep = function() {
		$location.path('/tow')
	}
	$scope.goHome = function() {
		$location.path('/');
	}
	$scope.timezbtn = function(t) {
		console.info(t)
		var ti = '9:00~11:00'
		if(t === ti) {
			console.info('ok')
			instance.stDetailId = $scope.stDetailId
		} else {
			instance.stDetailId = $scope.stDetailId1
		}
		$location.path('/four');
		setTimeout(function() {
			console.info('a');
			$(".four").addClass("transformto");
		})
		setTimeout(function() {
			$(".four").removeClass("transformto");
		}, 1003);
	}
	$scope.detaTo = function() {
		// $location.path('/four')
	}
	//赋值点击周几
	$scope.severalweeks = '',
		//  创建日历
		$scope.all_year = [];
	$scope.all_month = [];
	$scope.showTime = function() {
		$scope.show_now();
	}
	$scope.sundata = function(data) {
		$scope.select_month = 1;
		$scope.showDays(data + 1, $scope.select_month)
		$scope.select_year = $scope.select_year + 1;
	}
	$scope.monthdata = function(data) {
		if(data < 12) {
			$scope.showDays($scope.select_year, data + 1);
			$scope.select_month = $scope.select_month + 1;
		} else if(data === 12) {
			data = 1;
			$scope.select_year = $scope.select_year + 1;
			$scope.showDays($scope.select_year, data);
			$scope.select_month = 1;
		}

	}
	$scope.sundata_ = function(data) {
		$scope.showDays(data - 1, $scope.select_month)
		$scope.select_year = $scope.select_year - 1;
	}
	$scope.monthdata_ = function(data) {
		if(1 < data) {
			$scope.showDays($scope.select_year, data - 1);
			$scope.select_month = $scope.select_month - 1;
		} else if(data === 1) {
			data = 12;
			$scope.select_year = $scope.select_year - 1;
			$scope.showDays($scope.select_year, data)
			$scope.select_month = 12;
		}
	}
	//返回指定的月份的天数 月份1-12
	$scope.calDays = function(year, month) {
		return new Date(year, month, 0).getDate();
	}
	//展示指定的年和月的所有日期
	$scope.showDays = function(year, month) {
		$scope.days = [];
		//得到表示指定年和月的1日的那个时间对象
		var date = new Date(year, month - 1, 1);
		//1.先添加响应的空白的li:这个月1号是星期几，就添加几个空白的li
		var dayOfWeek = date.getDay(); //得到1日是星期几
		for(var i = 0; i < dayOfWeek; i++) {
			$scope.days.push({});
			// {day:'2019-08-23',show:true,id:''}
		}
		//计算一个月有多少天
		var daysOfMonth = $scope.calDays(year, month);
		//2. 从1号开始添加li
		for(var i = 1; i <= daysOfMonth; i++) {
			if(i < 10 && month < 10) {
				var fd = $scope.select_year + "-0" + month + "-0" + i;
			} else if(i < 10 && month >= 10) {
				var fd = $scope.select_year + "-" + month + "-0" + i;
			} else if(i >= 10 && month < 10) {
				var fd = $scope.select_year + "-0" + month + "-" + i;
			} else if(i >= 10 && month >= 10) {
				var fd = $scope.select_year + "-" + month + "-" + i;
			} else {
				console.info("日期添加序号错误！请检查选项！");
			}
			$scope.days.push({
				day: i,
				show: false,
				fullDay: fd,
				id: ''
			})
		}
		$scope.days.forEach(function(d) {
			d.show = false;
			for(var m = 0; m < $scope.arraydata.length; m++) {
				if($scope.arraydata[m] == d.fullDay) {
					d.show = true;
					break;
				}
			}
		});
		//console.info($scope.days)
	}
	$scope.select_year = '',
		$scope.select_month = '',
		$scope.active_day = '',
		// $scope.timej=29,
		$scope.now = new Date();
	$scope.timej = 0;
	//初始化显示 当前年和月
	$scope.show_now = function() {
		var now = new Date();
		$scope.active = now.getDate();
		$scope.select_year = now.getFullYear();
		$scope.select_month = now.getMonth() + 1;
		$scope.showDays($scope.select_year, $scope.select_month)
	}
	$scope.change_day = function(day, select_year, select_month, show) {
		console.info(show);
		if(show == true) {
			$scope.nian = select_year,
				$scope.yu = select_month,
				$scope.active_day = day,
				//console.info($scope.active_day)
				$scope.riqi = "",
				$scope.riqi = $scope.nian + '-' + $scope.yu + "-" + $scope.active_day.day,
				$scope.orderTimes($scope.riqi);
			instance.selectDate = $scope.riqi,
				console.info($scope.riqi);

			$(".sidetoadd").css({
				"visibility": "visible"
			});
			$(".sidetoadd").addClass("transform");
			//2秒后删除添加的transform
			setTimeout(function() {
				$(".sidetoadd").removeClass("transform");
			}, 501);
			var date1 = new Date($scope.nian, $scope.yu - 1, $scope.active_day.day);
			var dayOfWeek1 = date1.getDay(); //得到点击的是星期几
			switch(dayOfWeek1) {
				case 0:
					$scope.severalweeks = "日";
					break;
				case 1:
					$scope.severalweeks = "一";
					break;
				case 2:
					$scope.severalweeks = "二";
					break;
				case 3:
					$scope.severalweeks = "三";
					break;
				case 4:
					$scope.severalweeks = "四";
					break;
				case 5:
					$scope.severalweeks = "五";
					break;
				case 6:
					$scope.severalweeks = "六";
					break;
			}
		} else {
			console.info('得不到日期！');
		}
	}
	// 以上是创建日历
});
//4
app.controller('fourcontroller', function($scope, $location, instance, $state, appData) {
	$scope.operation = "第一步：请选择登陆方式";
	$scope.loginType = null;
	$scope.choiceType = function(type) {
		if(type == 'idcard') {
			$scope.operation = "第二步：请将身份证放置身份证读卡区";
			setTimeout(function() {
				console.info('a');
				$("idcard").addClass("transformto");
			})
			setTimeout(function() {
				$("idcard").removeClass("transformto");
			}, 1003);
			// $state.go(".first");
		} else if(type == 'cloud') {
			$scope.operation = "第二步：请将市民云中身份证二维码放置扫码区";
			// $state.go(".second");
			setTimeout(function() {
				console.info('a');
				$("citizen-cloud").addClass("transformto");
			})
			setTimeout(function() {
				$("citizen-cloud").removeClass("transformto");
			}, 1003);
		}
		$scope.loginType = type;
	};
	$scope.readCloud = function(data) {
		$.log.debug();
		// appData.idcardNumber = data.result.data.realname;
		// appData.idcardName = data.result.data.code;
		appData.licenseNumber = data.result.data.realname;
		appData.licenseName = data.result.data.code;
		$state.go("five")
	};
	// $scope.readIdcard = function(data, images) {
	// 	appData.idcardNumber = data.Number;
	// 	appData.idcardName = data.Name;
	// 	//$state.go("five");
	// 	$scope.$apply();
	// 	$location.path('/five');	
	// 	// console.info(appData.idcardNumber);
	// 	// console.info(appData.idcardName);
	// }
	$scope.readIdcard = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
			$state.go("five");
		} else {
			// layer.msg("没有获取到")；
			alert("沒有获取到");
		}
	}
	$scope.prevStep = function() {
		$location.path('/three')
	}
	$scope.goHome = function() {
		$location.path('/');
	}
	$scope.detaTo = function() {
		$state.go("five");
	}
});
//ng-model添加手机号数字   第五个
app.controller('fivecontroller', function($scope, $location, instance, appData, $state, appFactory) {
	$scope.stItemNo = instance.stItemNo,
		$scope.stPlaceId = instance.stPlaceId,
		$scope.stDetailId = instance.stDetailId,
		$scope.selectDate = instance.selectDate,
		$scope.idcardName = appData.licenseName,
		$scope.idcardNumber = appData.licenseNumber,
		console.info($scope.idcardNumber + $scope.idcardName)
	console.info($scope.stItemNo + $scope.stPlaceId + $scope.stDetailId + $scope.selectDate)
	$scope.phone2 = [],
		$scope.prevStep = function() {
			$location.path('/four')
		},
		$scope.goHome = function() {
			$location.path('/');
		}
	$scope.confirm = function() {
		if(!(/^1(3|4|5|6|7|8|9)\d{9}$/.test($scope.name))) {
			$scope.isAlert = true;
			$scope.msg = "请输入正确的手机号码！";
			return;
		} else if((/^1(3|4|5|6|7|8|9)\d{9}$/.test($scope.name))) {
			//$scope.phone2.push($scope.name);
			$scope.phoneNumber = $scope.name,
				console.log($scope.phoneNumber);
			//$location.path('/six');
			appFactory.pro_fetch("forward.do", { //发送预约
				fmd: 'aci-reservation',
				fdo: 'saveReservationInfo',
				itemNo: $scope.stItemNo,
				placeId: $scope.stPlaceId,
				detailId: $scope.stDetailId,
				date: $scope.selectDate, //点击的那一天
				certNo: $scope.idcardNumber,
				userName: $scope.idcardName,
				userId: '',
				mobile: $scope.phoneNumber,
				identityType: '1',
				reservationSource: '3',
				business: '',
				unit: '',
				unified: ''
			}, function(result) {
				if(result.success == true) {
					//alert('预约成功！！！');
					appData.zctepn = 0;
					$state.go("six")
				} else {
					$scope.defeated = result.value;
					$scope.isAlert = true;
					$scope.msg = '预约失败！';
					appData.zctepn = 1;
					// alert($scope.defeated);
					$state.go("six")
				}
				setTimeout(function() {
					console.info('a');
					$(".six").addClass("transformto");
				})
				setTimeout(function() {
					$(".six").removeClass("transformto");
				}, 1003);
				console.info(result)
			}, function(params) {
				alert(params)
			})
			//以上是短信预约
		}
	}
	$scope.phone = [1, 2, 3, 4, 5, 6, 7, 8, 9],
		$scope.name = "";
	$scope.sunph = '';
	var sunph = 0;
	$scope.phoneclick = function(x) {
		sunph = x + 1;
		$scope.name = $scope.name + sunph;
	}
	//点击清空input输入框=>绑定字符串清空
	$scope.empty = function() {
		$scope.name = "";
	}
	//点击截取字符串最后一位=>清除输入的最后一位数字
	$scope.clearzpp = function() {
		var b = $scope.name.substring(0, $scope.name.length - 1);
		$scope.name = b;
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.alertCancel = function() {
		$scope.isAlert = false;
	}
});
//6
app.controller('sixcontroller', function($scope, $location, $timeout, appData) {
	//上一步
	$scope.prevStep = function() {
		$location.path('/')
	}
	$scope.goHome = function() {
		$location.path('/');
	}
	if(appData.zctepn === 0) {
		$scope.operation = "您已预约成功";
		$scope.completeTips = "我们已将预约号发送至您的手机上！";
	} else if(appData.zctepn === 1) {
		$scope.operation = "您预约失败";
		$scope.completeTips = "对不起，您预约失败，请您重新进行预约！";
	}
});