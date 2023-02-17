function removeAnimate(ele) {
	//	$(ele).css({
	//		"transform": "translateY(0px)",
	//		"top": 0
	//	}).removeClass('transformto')
}

function addAnimate(ele) {
	$(ele).css({
		'margin-top': '300px',
		'opacity': '0'
	});
	$(ele).animate({
		marginTop: '0',
		opacity: '1'
	}, 1000);
}
app.controller("firstMenu", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	appData.funName = "政府信息公开";
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.isGuide = (appData.isGuide == false) ? false : true;
	$scope.current = appData.current || "guide"
	$scope.choice = function(type) {
		$scope.current = type;
		if(type == 'guide') {
			$scope.isGuide = true;
		} else {
			$scope.isGuide = false;
		}
	}
	$scope.secondMenuList = secondMenu;
	$scope.choiceSecondMenu = function(item) {
		appData.selectedMenuName = item.name;
		appData.isGuide = $scope.isGuide;
		appData.current = $scope.current;
		$state.go("secondMenu");
	}
	//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: '政府信息公开',
		}
	}
	recordUsingHistory('政府信息公开', '查询', '政府信息公开', "", "", '', '', JSON.stringify($scope.jsonStr));
	$scope.prevStep = function() {
		window.location.href = '../CSJ_allItem/index.html';
	}
	addAnimate($('.main2'))
});
app.controller("secondMenu", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.funName = appData.funName;
	$scope.menuList = secondMenu;
	$scope.isLoading = false;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.current = getIndex(secondMenu, appData.selectedMenuName);
	$scope.secondMenuList = secondMenuChild[getIndex(secondMenuChild, appData.selectedMenuName)].value;
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.prevStep = function() {
		$state.go("firstMenu")
	}
	$scope.choiceMenu = function(index, item) {
		$scope.current = index;
		appData.selectedMenuName = item.name;
		$scope.secondMenuList = secondMenuChild[getIndex(secondMenuChild, item.name)].value;
	}

	$scope.chooseItem = function(item) {
		$scope.isLoading = true;
		appData.secondName = item.name;
		if(appData.selectedMenuName == "政府信息公开制度") {
			appData.showYear = false;
			appData.yearList = [{
				"id": item.id
			}];
			$state.go("choice")
		} else if(appData.selectedMenuName == "市政府公报") {
			appData.channelId = item.id;
			$state.go("noticeChoice")
		} else if(appData.selectedMenuName == "政府信息公开年报" && item.name == "市政府办公厅") {
			appData.txt = content;
			appData.txtName = "市政府办公厅";
			$state.go("info")
		} else if(item.name != "市政府规章" && appData.selectedMenuName == "法定主动公开内容") {
			appData.channelId = item.id;
			$state.go(item.path);
		} else {
			appData.showYear = true;
			appFactory.jsonp("/selfapi/publicGovInfo/getchannelTree.do", {
				channelId: item.id,
			}, function(res) {
				$scope.isLoading = false;
				if(res.code == 0) {
					appData.yearList = res.data;
					$state.go("choice");
				} else {
					$scope.isAlert = true;
					$scope.msg = "暂未查询到信息,请重试";
					return;
				}
			}, function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "查询接口异常,请稍候再试";
			})
		}
	}
	addAnimate($('.main2'))
});
app.controller("choice", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.firstName = appData.selectedMenuName;
	$scope.secondName = appData.secondName;
	$scope.isShowYear = appData.showYear;
	$scope.isLoading = false;
	$scope.isAlert = false;
	$scope.concel = "false";
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.current = 0; // 默认选中当前年份
	if($scope.isShowYear) {
		$(".infoList").css("margin", "0 auto");
		$(".wrapper").css("height", "56%")
		appData.yearList.sort(function(a, b) {
			return(a.priority - b.priority)
		})
	} else {
		$(".infoList").css("margin", "8% auto");
		$(".wrapper").css("height", "80%")
	}
	$scope.yearsList = appData.yearList;
	$scope.prevStep = function() {
		$state.go("secondMenu")
	}

	//获取稿件列表
	$scope.getContentPage = function(id) {
		$scope.isLoading = true;
		appFactory.jsonp("/selfapi/publicGovInfo/getContentPage.do", {
			channelId: id,
			orderField: "display", //display（发布日期）；create（创建日期）；top_display（置顶-发布日期）；top_create（置顶-创建日期）
			order: 'desc' //asc（升序）；desc（降序）
		}, function(res) {
			console.log(res)
			$scope.isLoading = false;
			if(res.code == 0) {
				$scope.infoList = res.data.records;
			} else {
				$scope.isAlert = true;
				$scope.msg = "暂未查询到信息,请重试";
				return;
			}
		}, function(err) {
			$scope.isLoading = false;
			$scope.isAlert = true;
			$scope.msg = "查询接口异常,请稍候再试";
		})
	}
	//默认选中查询第一个
	try {
		$scope.getContentPage($scope.yearsList[0].id);
	} catch(e) {}

	$scope.chooseYear = function(index, item) {
		$scope.current = index;
		$scope.getContentPage(item.id);
	}

	$scope.choiceItemDetail = function(item) {
		appData.titleName = item.title;
		appData.contentId = item.id;
		$state.go("info");
	}

	addAnimate($('.main2'))
});
app.controller('info', function($state, $scope, appData, $sce, $timeout, appFactory, $http) {
	$scope.operation = "请选择查询条件";
	$scope.funName = appData.titleName;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoading = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$scope.isLoading = false;
	}
	$scope.prevStep = function() {
		window.history.go(-1);
	}

	//获取稿件详情
	$scope.getContentDetail = function() {
		$scope.isLoading = true;
		appFactory.jsonp("/selfapi/publicGovInfo/getContentDetail.do", {
			contentId: appData.contentId
		}, function(res) {
			$scope.isLoading = false;
			if(res.code == 0) {
				$scope.content = $sce.trustAsHtml(res.data.txt);
			}
		}, function(err) {
			$scope.isLoading = false;
			console.log(err);
		})
	}
	if(appData.selectedMenuName == "政府信息公开年报" && appData.txtName == "市政府办公厅") {
		$scope.funName = "2020年上海市政府信息公开工作年度报告";
		$scope.content = $sce.trustAsHtml(appData.txt);
	} else {
		$scope.getContentDetail();
	}

});
app.controller('leaderPhotoes', function($state, $scope, appData, $sce, $timeout, appFactory, $http) {
	$scope.firstName = appData.selectedMenuName || "法定主动公开内容";
	$scope.secondName = appData.secondName || "市政府领导";
	console.log(appData.channelId)
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoading = false;
	$scope.PhotoList = [];
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$scope.isLoading = false;
	}
	$scope.prevStep = function() {
		$state.go("secondMenu")
	}

	// 获取领导名字id列表
	appFactory.jsonp("/selfapi/publicGovInfo/getchannelTree.do", {
		channelId: appData.channelId,
	}, function(res) {
		$scope.leaderList = res.data;
		for(var i = 0; i < $scope.leaderList.length; i++) {
			$scope.getContentDetail($scope.leaderList[i].id, $scope.leaderList[i].priority,i,$scope.leaderList.length)
		}
	}, function(err) {
		console.log(err);
	})

	//获取领导稿件详情
	$scope.getContentDetailContent = function(contentId, priority,index,size) {
		$scope.isLoading = true;
		appFactory.jsonp("/selfapi/publicGovInfo/getContentDetail.do", {
			contentId: contentId
		}, function(res) {
			$scope.PhotoList.push({
				"url": res.data.titleImage,
				"leaderName": res.data.title,
				"id": res.data.channel.id,
				"content": res.data.txt,
				'divideWork': res.data.summary,
				'priority': priority
			})
			//排序
			$scope.PhotoList.sort(function(a, b) {
				return a.priority - b.priority
			})
			//市长信息
			$scope.mayor = $scope.PhotoList.slice(0, 1)[0];
			//其余信息
			$scope.other = $scope.PhotoList.slice(1, $scope.PhotoList.length);
			console.log(index+','+size)
			if(index==(size-1)){
				$scope.isLoading = false;
			}
		}, function(err) {
			$scope.isLoading = false;
			console.log(err);
		})
	}

	//获取领导稿件列表
	$scope.getContentDetail = function(channelId, priority,index,size) {
		$scope.isLoading = true;
		// 获取稿件列表
		appFactory.jsonp("/selfapi/publicGovInfo/getContentPage.do", {
			channelId: channelId
		}, function(res) {
			$scope.contentId = res.data.records[0].id;
			$scope.getContentDetailContent($scope.contentId, priority,index,size);
		}, function(err) {
			console.log(err);
		})
	}

	$scope.chooseLeader = function(item, flag) {
		appData.lenderInfo = item;
		appData.flag = flag;
		console.log(appData.lenderInfo);
		$state.go('leaderInfo');
	}
});
app.controller('leaderInfo', function($state, $scope, appData, $sce, $timeout, appFactory, $http) {
	$scope.firstName = "法定主动公开内容";
	$scope.secondName = "市政府领导";
	console.log(appData.flag)
	$scope.leaderInfo1 = (appData.flag == 1) ? true : false;
	$scope.leaderInfo2 = (appData.flag == 0) ? true : false;
	$scope.isAlert = false;
	$scope.concel = "false";
	//	$scope.isLoading = true;
	$scope.workList = false;
	$scope.current = 0; // 初始化阅签
	$scope.imageUrl = appData.lenderInfo.url;
	$scope.leaderName = appData.lenderInfo.leaderName;
	$scope.content = $sce.trustAsHtml(appData.lenderInfo.content);
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$scope.isLoading = false;
	}
	$scope.prevStep = function() {
		$state.go("leaderPhotoes")
	}
	$scope.work = work;

	// 选择阅签
	$scope.choice = function(index, name) {
		$scope.current = index;
		if(index == 1) {
			$scope.workList = false;
			$scope.content = $sce.trustAsHtml(appData.lenderInfo.divideWork);
		} else if(index == 0) {
			$scope.workList = false;
			$scope.content = $sce.trustAsHtml(appData.lenderInfo.content);
		} else if(index == 2) {
			$scope.workList = true;
		}
	}

	//我的工作  列表
	$scope.getContentDetailList = function(id) {
		$scope.isLoading = true;
		appFactory.jsonp("/selfapi/publicGovInfo/getchannelTree.do", {
			channelId: id,
		}, function(res) {
			console.log(res);
			for(var i=0;i<res.data.length;i++){
				if(res.data[i].name == "我的工作"){
					// 获取稿件列表
					appFactory.jsonp("/selfapi/publicGovInfo/getContentPage.do", {
						channelId: res.data[i].id
					}, function(res) {
						$scope.isLoading = false;
						$scope.contentList = res.data.records;
					}, function(err) {
						$scope.isLoading = false;
						console.log(err);
					})
				}
			}
		}, function(err) {
			console.log(err);
		})
	}

	if(appData.flag == 1) {
		$scope.getContentDetailList(appData.lenderInfo.id)
	} else {
		$scope.isLoading = false;
		$scope.leaderInfo2 = true;
	}

	$scope.choiceItemDetail = function(item) {
		appData.titleName = item.title;
		appData.contentId = item.id;
		$state.go('info');
	}
});
//工作机构
app.controller('workOrganization', function($state, $scope, appData, $sce, $timeout, appFactory, $http) {
	$scope.firstName = appData.selectedMenuName;
	$scope.secondName = appData.secondName;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoading = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$scope.isLoading = false;
	}
	$scope.prevStep = function() {
		$state.go("secondMenu")
	}
	//获取稿件列表
	$scope.getContentPage = function() {
		$scope.isLoading = true;
		appFactory.jsonp("/selfapi/publicGovInfo/getContentPage.do", {
			channelId: appData.channelId,
			orderField: "display", //display（发布日期）；create（创建日期）；top_display（置顶-发布日期）；top_create（置顶-创建日期）
			order: 'desc' //asc（升序）；desc（降序）
		}, function(res) {
			console.log(res)
			$scope.isLoading = false;
			if(res.code == 0) {
				$scope.infoList = res.data.records;
			} else {
				$scope.isAlert = true;
				$scope.msg = "暂未查询到信息,请重试";
				return;
			}
		}, function(err) {
			$scope.isLoading = false;
			$scope.isAlert = true;
			$scope.msg = "查询接口异常,请稍候再试";
		})
	}
	$scope.getContentPage();
});
//规划信息
app.controller('planInfo', function($state, $scope, appData, $sce, $timeout, appFactory, $http) {
	$scope.firstName = appData.selectedMenuName;
	$scope.secondName = appData.secondName;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoading = false;
	$scope.menuList = planInfoList;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$scope.isLoading = false;
	}
	$scope.prevStep = function() {
		$state.go("secondMenu")
	}
	//获取稿件列表
	$scope.getContentPage = function(id, sign) {
		$scope.isLoading = true;
		appFactory.jsonp("/selfapi/publicGovInfo/getContentPage.do", {
			channelId: id,
			orderField: "display", //display（发布日期）；create（创建日期）；top_display（置顶-发布日期）；top_create（置顶-创建日期）
			order: 'desc' //asc（升序）；desc（降序）
		}, function(res) {
			console.log(res)
			$scope.isLoading = false;
			if(res.code == 0) {
				if(sign == 1) {
					$scope.contentTitle = res.data.records[0];
				} else {
					$scope.infoList = res.data.records;
				}
			} else {
				$scope.isAlert = true;
				$scope.msg = "暂未查询到信息,请重试";
				return;
			}
		}, function(err) {
			$scope.isLoading = false;
			$scope.isAlert = true;
			$scope.msg = "查询接口异常,请稍候再试";
		})
	}

	$scope.current = 0;
	$scope.getContentPage(planInfoList[0].id, 1);
	$scope.getContentPage(planInfoList[0].childId, '');

	$scope.choiceMenu = function(index, item) {
		$scope.current = index;
		if(index == 0 || index == 3) {
			$scope.getContentPage(item.id, 1);
			//查纲要
			$scope.getContentPage(item.childId, '');
		} else {
			$scope.getContentPage(item.id, '');
		}
	}
	$scope.getDetail = function(id, title) {
		appData.titleName = title;
		appData.contentId = id;
		$state.go('info');
	}
});

//预算决算
app.controller('budgetChoice', function($state, $scope, appData, $sce, $timeout, appFactory, $http) {
	$scope.firstName = appData.selectedMenuName;
	$scope.secondName = appData.secondName;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoading = false;
	$scope.current = 0;
	$scope.menuList = budget;
	$scope.current2 = 0;
	$scope.current3 = 0;
	$scope.prevStep = function() {
		$state.go("secondMenu")
	}
	//获取栏目树
	$scope.getchannelTree = function(id, success) {
		$scope.isLoading = true;
		appFactory.jsonp("/selfapi/publicGovInfo/getchannelTree.do", {
			channelId: id,
		}, function(res) {
			$scope.isLoading = false;
			if(res.code == 0) {
				success(res.data);
			} else {
				$scope.isAlert = true;
				$scope.msg = "暂未查询到信息,请重试";
				return;
			}
		}, function(err) {
			$scope.isLoading = false;
			$scope.isAlert = true;
			$scope.msg = "查询接口异常,请稍候再试";
		})
	}

	//获取稿件列表
	$scope.choiceMenu = function(index, item) {
		$scope.getchannelTree(item.id, function(res) {
			//			$scope.yearList = res.slice(0,3);
			$scope.yearList = res.filter(function(n) {
				n = parseInt(n.name);
				return n >= 2018;
			})
			if(isBlank($scope.current)) {
				$scope.chooseYear(0, $scope.yearList[0]);
			}
			$scope.current = index;
		})

	}
	$scope.choiceMenu(0, $scope.menuList[0]);
	//选择年份
	$scope.chooseYear = function(index, item) {
		$scope.getchannelTree(item.id, function(res) {
			$scope.departList = res;
			if(isBlank($scope.current2)) {
				$scope.chooseDepart(0, $scope.departList[0]);
			}
			$scope.current2 = index;
		})
	}

	//选择部门
	$scope.chooseDepart = function(index, item) {
		$scope.current3 = index;
		$scope.isLoading = true;
		appFactory.jsonp("/selfapi/publicGovInfo/getContentPage.do", {
			channelId: item.id,
			orderField: 'display',
			order: 'desc',
			recursion: 'true'
		}, function(res) {
			$scope.isLoading = false;
			if(res.code == 0) {
				$scope.infoList = res.data.records;
			} else {
				$scope.isAlert = true;
				$scope.msg = "暂未查询到信息,请重试";
				return;
			}
		}, function(err) {
			$scope.isLoading = false;
			$scope.isAlert = true;
			$scope.msg = "查询接口异常,请稍候再试";
		})
	}

	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$scope.isLoading = false;
	}
	$scope.getDetail = function(item) {
		$scope.isLoading = true;
		appFactory.jsonp("/selfapi/publicGovInfo/getContentDetail.do", {
			contentId: item.id
		}, function(res) {
			$scope.isLoading = false;
			appData.address = res.data.attaches[0].path;
			$state.go('budgetIframe');
		}, function(err) {
			$scope.isLoading = false;
			console.log(err);
		})
	}
});
app.controller("budgetIframe", function($scope, $state, $timeout, appData, $sce, $location) {
	$scope.prevStep = function() {
		if($.device.vendor() == "wonders") {
			window.external.URL_CLOSE();
		}
		window.history.go(-1);
	}
	console.log(appData.address);
	if($.device.vendor() == "wonders") {
		window.external.URL_OPEN(200, 180, 1500, 700, appData.address);
	} else {
		window.open(appData.address, "_blank", "scrollbars=yes,resizable=1,modal=false,alwaysRaised=yes");

	}
	//window.innerWidth <= 1600
	//window.external.URL_OPEN(124,180,1030,600,appData.address);
});
// 收费清单
app.controller('chargeList', function($state, $scope, appData, $sce, $timeout, appFactory, $http) {
	$scope.firstName = appData.selectedMenuName;
	$scope.secondName = appData.secondName;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoading = false;
	$scope.menuList = planInfoList;
	$scope.prevStep = function() {
		$state.go("secondMenu")
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$scope.isLoading = false;
	}
});
// 政府工作报告
app.controller('workReport', function($state, $scope, appData, $sce, $timeout, appFactory, $http) {
	$scope.firstName = appData.selectedMenuName;
	$scope.secondName = appData.secondName;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoading = false;
	$scope.menuList = planInfoList;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$scope.isLoading = false;
	}
	$scope.prevStep = function() {
		$state.go("secondMenu")
	}
	//获取稿件列表
	$scope.getContentPage = function() {
		$scope.isLoading = true;
		appFactory.jsonp("/selfapi/publicGovInfo/getContentPage.do", {
			channelId: appData.channelId,
			orderField: "display", //display（发布日期）；create（创建日期）；top_display（置顶-发布日期）；top_create（置顶-创建日期）
			order: 'desc' //asc（升序）；desc（降序）
		}, function(res) {
			console.log(res)
			$scope.isLoading = false;
			if(res.code == 0) {
				$scope.infoList = res.data.records;
			} else {
				$scope.isAlert = true;
				$scope.msg = "暂未查询到信息,请重试";
				return;
			}
		}, function(err) {
			$scope.isLoading = false;
			$scope.isAlert = true;
			$scope.msg = "查询接口异常,请稍候再试";
		})
	}
	$scope.getContentPage();
	$scope.choiceItemDetail = function(item) {
		appData.titleName = item.title;
		appData.contentId = item.id;
		$state.go("workReportInfo")
	}
});
app.controller('workReportInfo', function($state, $scope, appData, $sce, $timeout, appFactory, $http) {
	$scope.funName = appData.titleName;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoading = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$scope.isLoading = false;
	}
	$scope.prevStep = function() {
		window.history.go(-1);
	}

	//获取稿件详情
	$scope.getContentDetail = function() {
		$scope.isLoading = true;
		appFactory.jsonp("/selfapi/publicGovInfo/getContentDetail.do", {
			contentId: appData.contentId
		}, function(res) {
			$scope.isLoading = false;
			if(res.code == 0) {
				$scope.content = $sce.trustAsHtml(res.data.txt);
			}
		}, function(err) {
			$scope.isLoading = false;
			console.log(err);
		})
	}
	$scope.getContentDetail();

	//  //模块使用记录
	//  $scope.jsonStr = {
	//      SUCCESS: "true",
	//      data: {
	//          name: appData.funName,
	//          Number: "",
	//      }
	//  }
	//  recordUsingHistory('长三角服务', '查询', appData.funName, '', '', '', '', JSON.stringify($scope.jsonStr));
	//  //行为分析(查询)
	//  trackEventForQuery(appData.funName, "", "查询", "上海市档案局", "", "", "");

	$scope.getDetail = function(id, title) {
		appData.titleName = title;
		appData.contentId = id;
		$state.go('info');
	}
});
// 公共服务事项
app.controller('publicService', function($state, $scope, appData, $sce, $timeout, appFactory, $http) {
	$scope.firstName = appData.selectedMenuName;
	$scope.secondName = appData.secondName;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoading = false;
	$scope.current = 0; // 初始化默认第一个选中
	$scope.serviceList = serviceList;
	// 获取稿件详情
	$scope.chooseService = function(index, item) {
		$scope.current = index;
		$scope.isLoading = true;
		appFactory.jsonp("/selfapi/publicGovInfo/getContentDetail.do", {
			contentId: item.id
		}, function(res) {
			$scope.isLoading = false;
			if(res.code == 0) {
				$scope.content = $sce.trustAsHtml(res.data.txt);
			}
		}, function(err) {
			$scope.isLoading = false;
			console.log(err);
		})
	}
	$scope.chooseService(0, $scope.serviceList[0])
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$scope.isLoading = false;
	}
	$scope.prevStep = function() {
		window.history.go(-1);
	}
	//模块使用记录
	//  $scope.jsonStr = {
	//      SUCCESS: "true",
	//      data: {
	//          name: appData.funName,
	//          Number: "",
	//      }
	//  }
	//  recordUsingHistory('长三角服务', '查询', appData.funName, '', '', '', '', JSON.stringify($scope.jsonStr));
	//  //行为分析(查询)
	//  trackEventForQuery(appData.funName, "", "查询", "上海市档案局", "", "", "");
});

//5.	市政府公报
app.controller("noticeChoice", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.firstName = appData.selectedMenuName;
	$scope.secondName = appData.secondName;
	//政府公报栏目显示
	$scope.listId = "a7035a86553b4e57949f22e882c6cecf";
	appData.channelId = "0001-2404";
	$scope.isLoading = false;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.infoList = [];
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.current = 0; // 默认选中当前年份
	$scope.prevStep = function() {
		$state.go("secondMenu")
	}

	//获取栏目列表
	/*
	 * type : 0 / 1
	 * 0: 查询年份列表    1:查询所有期刊列表
	 */
	$scope.getchannelTree = function(id, type) {
		$scope.isLoading = true;
		appFactory.jsonp("/selfapi/publicGovInfo/getchannelTree.do", {
			channelId: id,
		}, function(res) {
			console.log(res)
			$scope.isLoading = false;
			if(res.code == 0) {
				if(type == 0) {
					$scope.yearsList = res.data;
					$scope.yearsList.reverse();
					$scope.getContentPage($scope.yearsList[0].id, 0);
				} else if(type == 1) {
					$scope.allList = res.data;
				}
			} else {
				$scope.isAlert = true;
				$scope.msg = "暂未查询到信息,请重试";
				return;
			}
		}, function(err) {
			$scope.isLoading = false;
			$scope.isAlert = true;
			$scope.msg = "查询接口异常,请稍候再试";
		})
	}

	//

	$scope.getchannelTree($scope.listId, 0);
	$scope.getchannelTree(appData.channelId, 1);
	//查询稿件列表
	/*
	 * type  0 / 1
	 * 0:查询此年份下的所有期刊    1： 查询期刊下的所有稿件
	 */
	$scope.getContentPage = function(id, type) {
		$scope.isLoading = true;
		appFactory.jsonp("/selfapi/publicGovInfo/getContentPage.do", {
			channelId: id,
			orderField: "display",
			order: "desc",
			recursion: type == 1 ? true : false
		}, function(res) {
			console.log(res)
			$scope.isLoading = false;
			if(res.code == 0) {
				if(type == 0) {
					$scope.periodicalList = res.data.records;
				} else if(type == 1) {
					$scope.infoList = res.data.records;
				}

			} else {
				$scope.isAlert = true;
				$scope.msg = "暂未查询到信息,请重试";
				return;
			}
		}, function(err) {
			$scope.isLoading = false;
			$scope.isAlert = true;
			$scope.msg = "查询接口异常,请稍候再试";
		})
	}

	$scope.$watch('periodicalTitle', function(val) {
		if(val) {
			console.log(val.title.trim());
			console.log($scope.allList[1].name.trim());
			try {
				let id = filterByInfo($scope.allList, val.title, 'name')[0].id;
				$scope.getContentPage(id, 1);
			} catch(e) {
				$scope.infoList = [];
			}
		}
	})

	$scope.chooseYear = function(index, item) {
		$scope.current = index;
		$scope.getContentPage(item.id, 0);
	}

	$scope.choiceItemDetail = function(item) {
		appData.titleName = item.title;
		appData.contentId = item.id;
		$state.go("info");
	}

	addAnimate($('.main2'))
});