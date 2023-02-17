let bldList = [{
	bldCode: "330101-040",
	bldName: "黄浦区行政服务中心",
	bldAddress: "巨鹿路139号"
}, {
	bldCode: "330106-040",
	bldName: "静安区行政服务中心",
	bldAddress: "秣陵路38号"
}, {
	bldCode: "330113-044-001",
	bldName: "宝山区行政服务中心",
	bldAddress: "龙山路555号"
}, {
	bldCode: "330120-057-002",
	bldName: "奉贤区行政服务中心",
	bldAddress: "望园南路1529弄A-C幢"
}]
//1
app.controller('onecontroller', function($scope, $location, instance, appFactory, $timeout, $http, $state) {
	$scope.isLoading = false;

	function filterByName(dataJsonp, condition) {
		let result = [];
		for(var i = 0; i < dataJsonp.length; i++) {
			if(dataJsonp[i]['bldCode'] == condition) {
				result.push(dataJsonp[i]);
			}
		}
		return result;
	}
	//获取办理点code debug配置项
	instance.bldCode = $scope.bldCode = $location.search().bldCode || "330101-040";
	instance.bld = filterByName(bldList, instance.bldCode);
	console.log(instance.bld);
	$scope.list = [],
		$scope.itemList = [],
		$scope.detaTo = function() {
			// $location.path('/tow');
		}
	$scope.prevStep = function() {
		//		window.location.href = "../index.html";
		//		$state.go('../index.html');
		window.history.go(-1);
	}
	$scope.getDepartment = function() { //获取部
		$timeout(function() {
			appFactory.pro_fetch("getItemOrganByWindowCode.do", {
				"stWindowCode": $scope.bldCode
			}, function(data) {
				console.log(data);
				$scope.isLoading = true;
				$scope.itemList = data.data;
				//处理取到的数据 获取部门
				var namek = '';
				for(var i = 0; i < $scope.itemList.length; i++) {
					namek = $scope.itemList[i].stItemOrganName;
					//console.log(namek);
					$scope.list.push(namek);
				}
			})
		})
	}
	$scope.getDepartment();
	$scope.onclick = function(x) {
		var item = $scope.list[x]; //获取指定元素的值
		console.log(item);
		//上传到自定义服务中 instance.name = item;
		instance.name = item;
		instance.organId = $scope.itemList[x].stItemOrganCode;
		setTimeout(function() {
			console.info('a');
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
			appFactory.pro_fetch("searchItemLikeName.do", {
				"stWindowCode": instance.bldCode,
				"stTermName": "",
				"stItemOrganCode": $scope.itemId,
				"pageNum": 1,
				"pageSize": 20
			}, function(data) {
				$scope.isLoading = true;
				$scope.list2 = data.data;
			})
		})
	};
	$scope.getMatter();
	$scope.searchs = function(val) {
		console.log(val);
		$timeout(function() {
			appFactory.pro_fetch("searchItemLikeName.do", {
				"stWindowCode": instance.bldCode,
				"stTermName": encodeURI(val),
				"stItemOrganCode": $scope.itemId,
				"pageNum": 1,
				"pageSize": 20
			}, function(data) {
				$scope.isLoading = true;
				$scope.list2 = data.data
				//console.info($scope.list2);
			})
		})
	}
	$scope.towclick = function(x) {
		var item1 = $scope.list2[x].stTermName;
		var item2 = $scope.list2[x].stTermCode;
		var item3 = $scope.list2[x].stTermId;
		// 接受上传的数据=>数据共享 $scope.name = instance.name;
		$scope.name = instance.name;
		instance.stItemName = item1;
		instance.stItemNo = item2;
		instance.stPlaceId = item3;
		console.log($scope.name + "==>" + item1);
		$location.path('/three');
		setTimeout(function() {
			console.info('a');
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
	$scope.stItemNo = instance.stItemNo;
	$scope.stPlaceId = instance.stPlaceId;
	//定义数组接受可以预约的请求的日期
	$scope.arraydata = [];
	$scope.array = [];
	$scope.days = [];
	//定义数组渲染预约日期
	$scope.showDataMake = [];
	$scope.appointmentInfo = '';
	$scope.surplusCount = '';
	$scope.totalCount = "";
	$scope.stResourceId = '';
	$scope.stResourceId1 = '';
	//获取当天日期后15天内可预约日期
	$scope.getReservationDate = function() {
		function fun_date(aa) {
			var date1 = new Date();
			time1 = date1.getFullYear() + "-" + (date1.getMonth() + 1) + "-" + date1.getDate(); //time1表示当前时间
			var date2 = new Date(date1);
			date2.setDate(date1.getDate() + aa);
			let date = "";
			let month = "";
			if(date2.getMonth()<10){
				month = "0"+(date2.getMonth()+1);
			}else{
				month = date2.getMonth()+1;
			}
			if(date2.getDate()<10){
				date = "0"+date2.getDate();
			}else{
				date = date2.getDate();
			}
			var time2 = date2.getFullYear() + "-" + (month) + "-" + date;
			if(date2.getDay() == 0 || date2.getDay() == 6) {
				return "";
			} else {
				return time2;
			}
		}
		for(let i = 1; i <= 15; i++) {
			let date = fun_date(i);
			if(date != "") {
				$scope.arraydata.push(date);
			}
			$scope.isLoading = true;
		}
		console.log($scope.arraydata);
		$scope.days.forEach(function(d) {
			d.show = false;
			for(var m = 0; m < $scope.arraydata.length; m++) {
				if($scope.arraydata[m] == d.fullDay) {
					d.show = true;
					break;
				}
			}
		})
	}
	$scope.getReservationDate();
	$scope.orderTimes = function(date) { //获取预约时段
		instance.date = date;
		appFactory.pro_fetch("getResourceList.do", {
			"stWindowCode": instance.bldCode,
			"stTermCode": instance.stItemNo,
			"stDay": date
		}, function(result) {
			$scope.isLoading = true;
			$scope.currentCheck = null;
			$scope.showDataMake = result.data;
			console.log($scope.showDataMake);
			for(var j = 0; j < $scope.showDataMake.length; j++) {
				let date = $scope.showDataMake[j].stPeriod.split('~')[1];
				console.log(date);
				$scope.SX = date.match(/(\S*):30/)[1];
				console.log($scope.SX);
				if($scope.SX > 12) {
					$scope.showDataMake[j].sxm = "下午";
				} else {
					$scope.showDataMake[j].sxm = "上午";
				}
			}
			//预约的时间
			$scope.stResourceId = $scope.showDataMake[0].stResourceId;
			$scope.stResourceId1 = $scope.showDataMake[1].stResourceId;
			console.log($scope.appointmentInfo)
		})
	}
	$scope.prevStep = function() {
		$location.path('/tow')
	}
	$scope.goHome = function() {
		$location.path('/');
	}
	$scope.timezbtn = function(t, time) {
		instance.stResourceId = t;
		instance.time = time
				$location.path('/four');
//		$location.path('/five');
		setTimeout(function() {
			console.info('a');
			$(".four").addClass("transformto");
		})
		setTimeout(function() {
			$(".four").removeClass("transformto");
		}, 1003);
	}
	$scope.detaTo = function() {
//		 $location.path('/four')
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
			$scope.nian = select_year;
			if(select_month < 10){
				$scope.yu = "0"+select_month
			}else{
				$scope.yu = select_month
			}

				$scope.active_day = day,
				//console.info($scope.active_day)
				$scope.riqi = "",
				$scope.riqi = $scope.nian + '-' + $scope.yu + "-" + (($scope.active_day.day<10)?("0"+$scope.active_day.day):$scope.active_day.day),
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
			$scope.operation = "第二步：请将随身办中随申码或身份证二维码放置扫码区";
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
		appData.licenseNumber = data.result.data.code;
		appData.licenseName = data.result.data.realname;
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
app.controller('fivecontroller', function($scope, $location, instance, appData, $state, appFactory, $timeout) {
	$scope.isPersonal = false; // false 为法人  true 为个人
	$scope.isPhone = true; //true 输入框为手机号   false 输入框为统一社会信用代码
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.stItemNo = instance.stItemNo;
	$scope.stPlaceId = instance.stPlaceId;
	$scope.stResourceId = instance.stResourceId;
	$scope.selectDate = instance.selectDate;
	$scope.idcardName = appData.licenseName;
	$scope.idcardNumber = appData.licenseNumber;
	console.info($scope.idcardNumber + $scope.idcardName);
	console.info($scope.stItemNo + $scope.stPlaceId + $scope.stResourceId + $scope.selectDate);
	$scope.phone2 = [];
	$scope.prevStep = function() {
			$location.path('/four')
		},
		$scope.goHome = function() {
			$location.path('/');
		}
	$timeout(function() {
		if($scope.isPersonal == false) {
			console.log(1);
			document.getElementById("businessCode").onfocus = function() {
				console.log(2);
				$scope.isPhone = false;
			};
			document.getElementById("name").onfocus = function() {
				console.log(2);
				$scope.isPhone = true;
			};
		}
	}, 1000)
	$scope.confirm = function() {
		if(!(/^1(3|4|5|6|7|8|9)\d{9}$/.test($scope.name))) {
			$scope.isAlert = true;
			$scope.msg = "请输入正确的手机号码！";
			return;
		} else if((/^1(3|4|5|6|7|8|9)\d{9}$/.test($scope.name))) {
			$scope.phoneNumber = $scope.name;
			console.log($scope.name);
			appFactory.pro_fetch("saveRecordAndSendMsg.do", { //发送预约
				"stUsername": encodeURI($scope.idcardName),
				"stIdCardNo": $scope.idcardNumber,
				"stTermId": $scope.stPlaceId,
				"stResourceId": $scope.stResourceId,
				"stMobile": $scope.name,
				"stCorpName": encodeURI($scope.businessName || ""),
				"stCorpCreditCode": $scope.businessCode || ""
			}, function(result) {
				if(result.code == 200) {
					//alert('预约成功！！！');
					appData.zctepn = 0;
					$state.go("six")
				} else if(result.code == 455) {
					$scope.isAlert = true;
					$scope.msg = result.msg;
					$scope.alertConfirm = function() {
						$scope.isAlert = false;
						return;
					}
				} else {
					instance.defeated = result.msg;
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
	$scope.phone = [1, 2, 3, 4, 5, 6, 7, 8, 9];
	$scope.name = "";
	$scope.businessCode = "";
	$scope.sunph = '';
	var sunph = 0;
	$scope.phoneclick = function(x) {
		sunph = x + 1;
		if($scope.isPhone == true) {
			$scope.name = $scope.name + sunph;
		} else if($scope.isPhone == false) {
			$scope.businessCode = $scope.businessCode + sunph;
		}
	}
	//点击清空input输入框=>绑定字符串清空
	$scope.empty = function() {
		if($scope.isPhone == true) {
			$scope.name = "";
		} else if($scope.isPhone == false) {
			$scope.businessCode = "";
		}
	}
	//点击截取字符串最后一位=>清除输入的最后一位数字
	$scope.clearzpp = function() {
		if($scope.isPhone == true) {
			let b = $scope.name.substring(0, $scope.name.length - 1);
			$scope.name = b;
		} else if($scope.isPhone == false) {
			let b = $scope.businessCode.substring(0, $scope.businessCode.length - 1);
			$scope.businessCode = b;
		}
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.alertCancel = function() {
		$scope.isAlert = false;
	}
});
//6
app.controller('sixcontroller', function($scope, $location, $timeout, appData, instance) {
	//上一步
	$scope.prevStep = function() {
		$location.path('/')
	}
	$scope.goHome = function() {
		$location.path('/');
	}
	console.log(instance.bld);
	if(appData.zctepn === 0) {
		$scope.operation = "您已预约成功";
		//		$scope.completeTips = "我们已将预约号发送至您的手机上！";
		$scope.completeTips = "您已成功预约" + instance.stItemName + "事项";
		$scope.completeTips1 = "请于" + instance.date + " " + instance.time + " 前往" + instance.bld[0].bldName + "办理";
		$scope.completeTips2 = "地址：" + instance.bld[0].bldAddress;

	} else if(appData.zctepn === 1) {
		$scope.operation = "您预约失败";
		$scope.completeTips = instance.defeated;
	}
});