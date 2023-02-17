var app = angular.module("myApp", []);
var mapUrl = webRoot;
app.controller("devicesCtrl", function($scope) {
	// 首页头部
	$scope.getSelmQuertNum = function() {
		$.ajax({
			//url: mapUrl + "/infopub/home/leading.do",
			url: mapUrl + "/selmBigscreenCache/info.do",
			type: "post",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {fCode:'infopub',sCode:'home',tCode:'leading.do'},
			success: function(dataJson) {
				$scope.deviceSum = dataJson.deviceSum;//总设备数
				$scope.online = dataJson.online;//在线数
				$scope.noOnLine = dataJson.noOnLine;//离线数
				$scope.socialSum = dataJson.socialSum;//社会终端
				$scope.govSum = dataJson.govSum;//政务终端
				$scope.Excount = dataJson.Excount;//异常设备
				$scope.selmItemSum = dataJson.selmItemSum;//事项数
				$scope.SelmQuerySum = dataJson.SelmQuerySum;//30天办件量
				$scope.SelmQueryDaySum = dataJson.SelmQueryDaySum;//当日办件量
				$scope.$apply();
			},
			error: function(err){
				console.log(err)
			}
		});

	}
	$scope.getSelmQuertNum();
	
	// 新接入申请
	$scope.getApplyInfo = function() {
		$.ajax({
			type: 'get',
			dataType: 'jsonp',
			jsonp: 'jsonpCallback',
			url: mapUrl + "/business/selmAccessApply/NoApplylist.do",
			data: {},
			success: function(dataJson) {
				$scope.ApplyInfo = dataJson.data;
				if($scope.ApplyInfo.length==0){
					$scope.show=true;
				}else{
					$scope.show=false;
				}
				
				$('#table').on('click', 'td', function(e) {
					var tdSeq = $(this).parent().find("td").index($(this)[0]); //列号
					var trSeq = $(this).parent().parent().find("tr").index($(this).parent()[0]); //行号
					var trName = document .getElementById ("table").rows [trSeq].cells[0].innerHTML;
					var tdName = document .getElementById ("table").rows [1].cells[tdSeq].innerHTML;
					console.log(trName+"列名");
					var applyId = $scope.ApplyInfo[trName-1].stAccessApplyId;
					console.log($scope.ApplyInfo[trName-1].stAccessApplyId)
					//console.log(tdName+"行名");
					//trName = encodeURI(encodeURI(trName));
					var index = layer.open({
							type: 2,
							title: '接入申请',
							content: webRoot+'/business/selmAccessApply/noApply.do?stAccessApplyId='+applyId,
						});
							layer.full(index);
				});
				$scope.$apply();
			},
			error: function(err) {
				console.log(err)
			}
		});
	}
	$scope.getApplyInfo();

	// 办件数量top20
	//business/selmQueryHis/selmQuertTop.do
	$scope.getSelmQueryInfo = function() {
		$.ajax({
			type: 'get',
			dataType: 'jsonp',
			jsonp: 'jsonpCallback',
			url: mapUrl + "/selmBigscreenCache/info.do",
			//url: mapUrl + "/business/selmQueryHis/selmQuertTop.do",
			data: {fCode:'business',sCode:'selmQueryHis',tCode:'selmQuertTop.do'},
			success: function(dataJson) {
				console.log('---'+dataJson);
				$scope.InfoQuery = dataJson.data;
				$scope.$apply();
			},
			error: function(err) {
				console.log(err)
			}
		});
	}
	$scope.getSelmQueryInfo();


//各区30天使用量
	//url: mapUrl + "/business/selmQueryHis/areaQueryHis.do",
	$scope.getAreaInfo = function() {
		$.ajax({
			type: 'post',
			dataType: 'jsonp',
			jsonp: 'jsonpCallback',
			url: mapUrl + "/selmBigscreenCache/info.do",
			//url: mapUrl + "/business/selmQueryHis/areaQueryHis.do",
			data: {fCode:'business',sCode:'selmQueryHis',tCode:'areaQueryHis.do'},
			success: function(dataJson) {
				//console.log(dataJson)
				$scope.AreaInfo = dataJson.data;
				//console.log($scope.AreaInfo)
				var arr=[];
				for (var i = 0; i < $scope.AreaInfo.length; i++) {
					arr.push(parseInt($scope.AreaInfo[i].stLabel));
				}
				var sum =0 
				for(var i=0;i<arr.length;i++){
					sum=sum+arr[i]
				}
				 //百分比占量
				for (var i = 0; i < $scope.AreaInfo.length; i++) {
					$scope.AreaInfo[i].stLabel = ((parseInt($scope.AreaInfo[i].stLabel)/sum)*100)+"%";
				}
				$scope.$apply();
			},
			error: function(err) {
				console.log(err)
			}
		});
	}
	$scope.getAreaInfo();
	
	
	$scope.map = function() {
		var index = layer.open({
			type: 2,
			title: '设备地图',
			content: webRoot+'/infopub/deviceinfo/mapPage.jsp',
		});
			layer.full(index);
	}
});


