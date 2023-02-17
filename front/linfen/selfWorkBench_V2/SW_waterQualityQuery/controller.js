app.controller("choose", function($scope, $state, appData, $sce, $timeout) {
	$scope.funName = appData.funName = "中心城区供水水质查询";
	$scope.isLoading = false;
	$scope.concel = 'false';
	$scope.isShow = false;
	$(".form_datetime").datetimepicker({
		format: "yyyy-mm", //显示日期格式
		autoclose: true,
		startView: 3,
		minView: 3,
		language: 'zh-CN',
		pickerPosition: 'bottom-left',
	});
	//监听日期控件 变化
	$timeout(function() {
		$('.form_datetime')
			.datetimepicker()
			.on('changeDate', function(ev) {
				$scope.isLoading = true;
				$scope.dateTime = $('.input').val().split('-')[0] + "年" + $('.input').val().split('-')[1];
				$scope.time = formatDateCustom(ev.date.valueOf()).substring(0,7);
				$scope.$apply();
				$.ajax({
					type:"post",
					url:$.getConfigMsg.preUrlSelf+"/selfapi/saterAuthority/queryDataDetail.do",
					dataType:'json',
					data:{
						itemcode:'312002467000',
						time:$scope.time
					},
					success:function(res){
						console.log(res);
						$scope.isLoading = false;
						if(res== null || res == undefined || res == ""){
							$scope.result = [];
							$scope.isAlert = true;
							$scope.msg = "未查到数据，请先发布！"
							$scope.alertConfirm = function(){
								$scope.isAlert = false;
							}
						} else if(res.code == 0){
							$scope.result = res.data[0];
							$scope.img1 = $.getConfigMsg.preUrlSelf + "/selfapi/saterAuthority/getAppendix.do?fileName=" +encodeURI($scope.result.CGSZZB)+"&filePath="+$scope.result.CGSZZBFILEPATH;
							$scope.img2 = $.getConfigMsg.preUrlSelf + "/selfapi/saterAuthority/getAppendix.do?fileName=" +encodeURI($scope.result.GSSZHGL)+"&filePath="+$scope.result.GSSZHGLFILEPATH;
							$scope.img3 = $.getConfigMsg.preUrlSelf + "/selfapi/saterAuthority/getAppendix.do?fileName=" +encodeURI($scope.result.QXSZZB)+"&filePath="+$scope.result.QXSZZBFILEPATH;
						}else{
							$scope.result = [];
							$scope.isAlert = true;
							$scope.msg = "未查到数据，请先发布！"
							$scope.alertConfirm = function(){
								$scope.isAlert = false;
							}
						}
					},
					error:function(err){
						$scope.isLoading = false;
						console.log(err);
					}
				});
			})
	}, 100)
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}

	//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: appData.funName,
			Number: "",
		}
	}
	recordUsingHistory('水务服务', '查询', appData.funName, '', '', '', '', JSON.stringify($scope.jsonStr));
	//行为分析(查询)
	trackEventForQuery(appData.funName, '', "查询", "上海市水务局", '', '', "");

	$scope.prevStep = function() {
		window.location.href = "../SCJG_allItem/index.html"
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});