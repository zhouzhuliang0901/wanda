var app = angular.module("myApp", []);
//var mapUrl = "http://10.2.104.44:8080/ac-self-api"; // fairy
var mapUrl = "http://10.81.16.56:8090/ac-self"; // 测试环境
app.controller("devicesCtrl", function($scope, $interval) {

	angular.element(window).bind('load', function() {
		$(".pop").css("display", "none");
		$(".pop_sb").css("display", "none");
		$scope.raelTime();
		$scope.getAreaInfo();
	});
	// 人名脱敏处理
	$scope.reName = function(str) {
		if(null != str && str != undefined) {
			var len = str.length;
			var leftStr = str.substring(0, 1);
			var rightStr = "*";
			if(len > 2) {
				rightStr = str.substring(len - 1, len);
			}
			var str = ''
			var i = 0;
			try {
				for(i = 0; i < len - 2; i++) {
					str = str + '*';
				}
			} catch(error) {

			}
			str = leftStr + str + rightStr;
			return str;
		} else {
			return "";
		}
	}
	// 自主事项总量
	$scope.getSelmQuertNum = function() {
		$.ajax({
			url: mapUrl + "/infopub/selmQueryHis/selmQuertNum.do",
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {},
			success: function(dataJson) {
				$scope.zcNum = dataJson.data;
			},
			error: function(err) {
				console.log(err)
			}
		});

	}
	$scope.getSelmQuertNum();

	// 区域终端设备数
	$scope.getAreaInfo = function() {
		$.ajax({
			type: 'get',
			dataType: 'jsonp',
			jsonp: 'jsonpCallback',
			url: mapUrl + "/infopub/selmStatistics/addresslistTypeDevice.do",
			data: {},
			success: function(dataJson) {
				$scope.AreaInfo = dataJson.data;
				let arr = [];
				for(i = 0; i < $scope.AreaInfo.length - 1; i++) {
					arr.push($scope.AreaInfo[i].stStreet)
				}
				let max = Math.max.apply(null, arr);
				if(max > 0 && max <= 20) {
					for(i = 0; i < $scope.AreaInfo.length - 1; i++) {
						if($scope.AreaInfo[i].stStreet == 0) {
							$scope.AreaInfo[i].stStreet = 40;
						} else {
							$scope.AreaInfo[i].stStreet = parseInt($scope.AreaInfo[i].stStreet) * 20
						}
					}
				} else if(max > 20 && max <= 50) {
					for(i = 0; i < $scope.AreaInfo.length - 1; i++) {
						if($scope.AreaInfo[i].stStreet <= 10) {
							$scope.AreaInfo[i].stStreet = 40;
						} else {
							$scope.AreaInfo[i].stStreet = parseInt($scope.AreaInfo[i].stStreet) * 8;
						}
					}
				} else if(max > 50 && max <= 100) {
					for(i = 0; i < $scope.AreaInfo.length - 1; i++) {
						if($scope.AreaInfo[i].stStreet <= 10) {
							$scope.AreaInfo[i].stStreet = 40;
						} else {
							$scope.AreaInfo[i].stStreet = parseInt($scope.AreaInfo[i].stStreet) * 4
						}
					}
				} else if(max > 100 && max <= 500) {
					for(i = 0; i < $scope.AreaInfo.length - 1; i++) {
						if($scope.AreaInfo[i].stStreet <= 10) {
							$scope.AreaInfo[i].stStreet = 40;
						} else {
							$scope.AreaInfo[i].stStreet = parseInt($scope.AreaInfo[i].stStreet)
						}
					}
				} else if(max > 500 && max <= 1000) {
					for(i = 0; i < $scope.AreaInfo.length - 1; i++) {
						if($scope.AreaInfo[i].stStreet <= 10) {
							$scope.AreaInfo[i].stStreet = 40;
						} else {
							$scope.AreaInfo[i].stStreet = parseInt($scope.AreaInfo[i].stStreet) / 2
						}
					}
				} else if(max > 1000 && max <= 5000) {
					for(i = 0; i < $scope.AreaInfo.length - 1; i++) {
						if($scope.AreaInfo[i].stStreet <= 10) {
							$scope.AreaInfo[i].stStreet = 40;
						} else {
							$scope.AreaInfo[i].stStreet = parseInt($scope.AreaInfo[i].stStreet) / 11
						}
					}
				} else if(max > 5000 && max <= 10000) {
					for(i = 0; i < $scope.AreaInfo.length - 1; i++) {
						if($scope.AreaInfo[i].stStreet <= 10) {
							$scope.AreaInfo[i].stStreet = 40;
						} else {
							$scope.AreaInfo[i].stStreet = parseInt($scope.AreaInfo[i].stStreet) / 18;
						}
					}
				} else if(max > 10000) {
					for(i = 0; i < $scope.AreaInfo.length - 1; i++) {
						if($scope.AreaInfo[i].stStreet <= 10) {
							$scope.AreaInfo[i].stStreet = 40;
						} else {
							$scope.AreaInfo[i].stStreet = parseInt($scope.AreaInfo[i].stStreet) / 26;
						}
					}
				}
				$scope.qbNum = parseInt(dataJson.data[16].nmLat) + parseInt(dataJson.data[16].nmLng) + parseInt(dataJson.data[16].stAddress);
				$scope.ycNum = parseInt($scope.AreaInfo[16].stLabel);
				$scope.$apply();
			},
			error: function(err) {
				console.log(err)
			}
		});
	}
	$interval($scope.getAreaInfo,30000);
	// 办件数量top20
	$scope.getSelmQueryInfo = function() {
		$.ajax({
			type: 'get',
			dataType: 'jsonp',
			jsonp: 'jsonpCallback',
			url: mapUrl + "/infopub/selmQueryHis/selmQuertTop.do",
			data: {},
			success: function(dataJson) {
				$scope.InfoQuery = dataJson.data;
				$scope.$apply();
			},
			error: function(err) {
				console.log(err)
			}
		});
	}
	$scope.getSelmQueryInfo();

	// 实时办件  定时查询
	$scope.raelTime = function() {
		$.ajax({
			type: 'get',
			dataType: 'jsonp',
			jsonp: 'jsonpCallback',
			url: mapUrl + "/infopub/selmQueryHis/selmQuerTow.do",
			data: {},
			success: function(dataJson) {
				$scope.raelTime = dataJson.data;
				$scope.firstName = $scope.reName($scope.raelTime[0].stName);
				$scope.secondName = $scope.reName($scope.raelTime[1].stName);
				$scope.$apply();
			},
			error: function(err) {
				console.log(err)
			}
		});
	}
	$interval($scope.raelTime,30000);
	let myChart_sb = echarts.init(document.getElementById('main_sb'));
	let myChart = echarts.init(document.getElementById('main'));
	// 办件弹窗数据
	$scope.openPop = function(Area){
		$scope.areaName = Area;
		let streetSInfo = [];
		// top10
		$.ajax({
			type: 'get',
			dataType: 'jsonp',
			jsonp: 'jsonpCallback',
			url: mapUrl + "/infopub/selmQueryHis/areaSelmQuerTop.do",
			data: {
				stDistrictName: Area
			},
			success: function(dataJson) {
				$scope.topTen = dataJson.data;
				$scope.$apply();
			},
			error: function(err) {
				$scope.topTen = [{
					"stItemName": "暂无数据",
					"stExt1": "0"
				}]
				console.log(err)
			}
		});
		// 各街道办件
		$.ajax({
			type: 'get',
			dataType: 'jsonp',
			jsonp: 'jsonpCallback',
			url: mapUrl + "/infopub/selmQueryHis/areaSelmQuer.do",
			data: {
				stDistrictName: Area
			},
			success: function(dataJson) {
				streetSInfo = dataJson.data;
				$(".pop").css("display", "block");
				let barHeight = 50;
				let option = {
					title: {},
					grid: {},
					polar: {
						center: ['48%', '50%'],
						radius: ['0%', '64%'], //半径大小
						startAngle: 45,
					},
					angleAxis: {
						type: 'category',
						data: streetSInfo.map(function(d) {
							return d.stStreet;
						}),
						axisTick: {
							show: true,
							　　
						},
						axisLine: {
							show: true,
							lineStyle: {
								color: '#fff',
								width: 1,
								type: 'solid'
							}
						},
						axisLabel: {
							show: true,
							interval: 0,
							formatter: function(value, index) {
								let n = streetSInfo.length
								let len = value.length;
								if(value.length >= 3 && value.indexOf("镇") != -1) {
									value = value.substring(0, len - 1);
								} else if(value.length >= 3 && value.indexOf("街道") != -1) {
									value = value.substring(0, len - 2);
								}
								if (value.length > 5) {
									value = value.substring(0,5) + "\n" + value.substring(5,len);
								} else {
									value = value;
								}
								if(n <= 10) {
									return value + " " + streetSInfo[index].nmLat;
								} else if(n % 2 == 0) {
									if(index == n / 2) {
										return "\n" + value + " " + streetSInfo[index].nmLat;
									} else if(index == n - 1) {
										return value + " " + streetSInfo[index].nmLat + "\n";
									} else {
										return value + " " + streetSInfo[index].nmLat;
									}
								} else {
									if(index == n - 1) {
										return value + " " + streetSInfo[index].nmLat + "\n";
									} else {
										return value + " " + streetSInfo[index].nmLat;
									}
								}
							},
							margin: 6,
							textStyle: {
								color: '#fff',
								fontSize: 17
							}
						}
					},
					radiusAxis: {
						axisLine: {
							lineStyle: {
								color: '#fff',
								width: 1,
								type: 'solid'
							}
						},
						axisLabel: {
							rotate: 45,
							textStyle: {
								color: '#fff',
								fontSize: 15
							}
						}
					},
					series: [{
						type: 'bar',
						itemStyle: {
							color: 'transparent'
						},
						data: streetSInfo.map(function(d) {
							return 0;
						}),
						coordinateSystem: 'polar',
						stack: '最大最小值',
						silent: true
					}, {
						type: 'bar',
						data: streetSInfo.map(function(d) {
							return d.nmLat;
						}),
						coordinateSystem: 'polar',
						name: '价格范围',
						stack: '最大最小值'
					}, {
						type: 'bar',
						itemStyle: {
							color: 'transparent'
						},
						data: streetSInfo.map(function(d) {
							return 0;
						}),
						coordinateSystem: 'polar',
						stack: '均值',
						silent: true,
						z: 10
					}, {
						type: 'bar',
						data: streetSInfo.map(function(d) {
							return 0;
						}),
						coordinateSystem: 'polar',
						name: '均值',
						stack: '均值',
						barGap: '-100%',
						z: 10
					}]
				};
				let pdOption = {
					title: {},
					grid: {},
					polar: {
						center: ['50%', '50%'],
						radius: ['0%', '64%'] //半径大小
					},
					angleAxis: {
						type: 'category',
						data: streetSInfo.map(function(d) {
							return d.stStreet;
						}),
						axisTick: {
							show: true,
							　　
						},
						axisLine: {
							show: true,
							lineStyle: {
								color: '#fff',
								width: 1,
								type: 'solid'
							}
						},
						axisLabel: {
							show: true,
							interval: 0,
							formatter: function(value, index) {
								let len = value.length;
								let n = streetSInfo.length
								if(value.length >= 3 && value.indexOf("镇") != -1) {
									value = value.substring(0, len - 1);
								} else if(value.length >= 3 && value.indexOf("街道") != -1) {
									value = value.substring(0, len - 2);
								}
								if(index == n - 1) {
									return value + " " + streetSInfo[index].nmLat + "\n\n";
								} else if(index == n - 2) {
									return value + " " + streetSInfo[index].nmLat + "\n";
								} else if(index == 1) {
									return "    " + value + " " + streetSInfo[index].nmLat + "\n\n";
								} else if(index == 17) {
									return "\n\n\n" + value + " " + streetSInfo[index].nmLat;
								} else if(index == 19) {
									return "\n\n" + value + " " + streetSInfo[index].nmLat;
								} else {
									return value + " " + streetSInfo[index].nmLat;
								}
							},
							margin: 8,
							textStyle: {
								color: '#fff',
								fontSize: 16
							}
						}
					},
					radiusAxis: {
						axisLine: {
							lineStyle: {
								color: '#fff',
								width: 1,
								type: 'solid'
							}
						},
						axisLabel: {
							rotate: 45,
							textStyle: {
								color: '#fff',
								fontSize: 12
							}
						}
					},
					series: [{
						type: 'bar',
						itemStyle: {
							color: 'transparent'
						},
						data: streetSInfo.map(function(d) {
							return 0;
						}),
						coordinateSystem: 'polar',
						stack: '最大最小值',
						silent: true
					}, {
						type: 'bar',
						data: streetSInfo.map(function(d) {
							return d.nmLat;
						}),
						coordinateSystem: 'polar',
						name: '价格范围',
						stack: '最大最小值'
					}, {
						type: 'bar',
						itemStyle: {
							color: 'transparent'
						},
						data: streetSInfo.map(function(d) {
							return 0;
						}),
						coordinateSystem: 'polar',
						stack: '均值',
						silent: true,
						z: 10
					}, {
						type: 'bar',
						data: streetSInfo.map(function(d) {
							return 0;
						}),
						coordinateSystem: 'polar',
						name: '均值',
						stack: '均值',
						barGap: '-100%',
						z: 10
					}]
				};

				if(Area == "浦东新区") {
					myChart.setOption(pdOption);
				} else {
					myChart.setOption(option);
				}

				$scope.$apply();
			},
			error: function(err) {
				console.log(err)
			}
		});
	}
	// 设备弹窗数据
	$scope.openPopSb = function(Area) {
		if(Area =="总计"){
			return;
		}
		$scope.areaName = Area;
		let streetSInfo = [];
		// 各街道设备数
		$.ajax({
			type: 'get',
			dataType: 'jsonp',
			jsonp: 'jsonpCallback',
			url: mapUrl + "/infopub/deviceinfo/areaDeviceInfo.do",
			data: {
				stDistrictName: Area
			},
			success: function(dataJson) {
				streetSInfo = dataJson.data;
				$(".pop_sb").css("display", "block");
				let barHeight = 50;
				let option = {
					title: {},
					grid: {},
					polar: {
						center: ['44%', '50%'],
						radius: ['0%', '64%'], //半径大小
					},
					angleAxis: {
						type: 'category',
						triggerEvent: true,
						data: streetSInfo.map(function(d) {
							return d.stStreet;
						}),
						axisTick: {
							show: true,
							　　
						},
						axisLine: {
							show: true,
							lineStyle: {
								color: '#fff',
								width: 1,
								type: 'solid'
							}
						},
						axisLabel: {
							show: true,
							interval: 0,
							formatter: function(value, index) {
								let n = streetSInfo.length
								let len = value.length;
								if(value.length >= 3 && value.indexOf("镇") != -1) {
									value = value.substring(0, len - 1);
								} else if(value.length >= 3 && value.indexOf("街道") != -1) {
									value = value.substring(0, len - 2);
								} 
								if (value.length > 5) {
									value = value.substring(0,5) + "\n" + value.substring(5,len);
								} else {
									value = value;
								}
								if(n <= 10) {
									return value + " " + streetSInfo[index].nmLat;
								} else if(n % 2 == 0) {
									if(index == n / 2) {
										return "\n" + value + " " + streetSInfo[index].nmLat;
									} else if(index == n - 1) {
										return value + " " + streetSInfo[index].nmLat + "\n";
									} else {
										return value + " " + streetSInfo[index].nmLat;
									}
								} else {
									if(index == n - 1) {
										return value + " " + streetSInfo[index].nmLat + "\n";
									} else {
										return value + " " + streetSInfo[index].nmLat;
									}
								}
							},
							margin: 6,
							textStyle: {
								color: '#fff',
								fontSize: 16
							}
						}
					},
					radiusAxis: {
						axisLine: {
							lineStyle: {
								color: '#fff',
								width: 1,
								type: 'solid'
							}
						},
						axisLabel: {
							rotate: 45,
							textStyle: {
								color: '#fff',
								fontSize: 14
							}
						}
					},
					series: [{
						type: 'bar',
						itemStyle: {
							color: 'transparent'
						},
						data: streetSInfo.map(function(d) {
							return 0;
						}),
						coordinateSystem: 'polar',
						stack: '最大最小值',
						silent: true
					}, {
						type: 'bar',
						data: streetSInfo.map(function(d) {
							return d.nmLat;
						}),
						coordinateSystem: 'polar',
						name: '价格范围',
						stack: '最大最小值'
					}, {
						type: 'bar',
						itemStyle: {
							color: 'transparent'
						},
						data: streetSInfo.map(function(d) {
							return 0;
						}),
						coordinateSystem: 'polar',
						stack: '均值',
						silent: true,
						z: 10
					}, {
						type: 'bar',
						data: streetSInfo.map(function(d) {
							return 0;
						}),
						coordinateSystem: 'polar',
						name: '均值',
						stack: '均值',
						barGap: '-100%',
						z: 10
					}]
				};
				let pdOption = {
					title: {},
					grid: {},
					polar: {
						center: ['44%', '50%'],
						radius: ['0%', '64%'] //半径大小
					},
					angleAxis: {
						type: 'category',
						triggerEvent: true,
						data: streetSInfo.map(function(d) {
							return d.stStreet;
						}),
						axisTick: {
							show: true,
							　　
						},
						axisLine: {
							show: true,
							lineStyle: {
								color: '#fff',
								width: 1,
								type: 'solid'
							}
						},
						axisLabel: {
							show: true,
							interval: 0,
							formatter: function(value, index) {
								let len = value.length;
								let n = streetSInfo.length
								if(value.length >= 3 && value.indexOf("镇") != -1) {
									value = value.substring(0, len - 1);
								} else if(value.length >= 3 && value.indexOf("街道") != -1) {
									value = value.substring(0, len - 2);
								}
								if(index == n - 1) {
									return value + " " + streetSInfo[index].nmLat + "\n\n";
								} else if(index == n - 2) {
									return value + " " + streetSInfo[index].nmLat + "\n";
								} else if(index == 1) {
									return "    " + value + " " + streetSInfo[index].nmLat + "\n\n";
								} else if(index == 17) {
									return "\n\n\n" + value + " " + streetSInfo[index].nmLat;
								} else if(index == 19) {
									return "\n\n" + value + " " + streetSInfo[index].nmLat;
								} else {
									return value + " " + streetSInfo[index].nmLat;
								}
							},
							margin: 12,
							textStyle: {
								color: '#fff',
								fontSize: 14
							}
						}
					},
					radiusAxis: {
						axisLine: {
							lineStyle: {
								color: '#fff',
								width: 1,
								type: 'solid'
							}
						},
						axisLabel: {
							rotate: 45,
							textStyle: {
								color: '#fff',
								fontSize: 14
							}
						}
					},
					series: [{
						type: 'bar',
						itemStyle: {
							color: 'transparent'
						},
						data: streetSInfo.map(function(d) {
							return 0;
						}),
						coordinateSystem: 'polar',
						stack: '最大最小值',
						silent: true
					}, {
						type: 'bar',
						data: streetSInfo.map(function(d) {
							return d.nmLat;
						}),
						clickable: true,
						coordinateSystem: 'polar',
						name: '价格范围',
						stack: '最大最小值'
					}, {
						type: 'bar',
						itemStyle: {
							color: 'transparent'
						},
						data: streetSInfo.map(function(d) {
							return 0;
						}),
						coordinateSystem: 'polar',
						stack: '均值',
						silent: true,
						z: 10
					}, {
						type: 'bar',
						data: streetSInfo.map(function(d) {
							return 0;
						}),
						coordinateSystem: 'polar',
						name: '均值',
						stack: '均值',
						barGap: '-100%',
						z: 10
					}]
				};

				if(Area == "浦东新区") {
					myChart_sb.setOption(pdOption);
				} else {
					myChart_sb.setOption(option);
				}
				$scope.streetName = streetSInfo[0].stStreet;
				$(".currentPage").html('1');
				// 街道设备初始化
				$.ajax({
					type: 'get',
					dataType: 'jsonp',
					jsonp: 'jsonpCallback',
					url: mapUrl + "/infopub/deviceinfo/streetDeviceList.do",
					data: {
						stStreetName: streetSInfo[0].stStreet
					},
					success: function(dataJson) {
						
						if(dataJson.data.length > 0) {
							$scope.Item = dataJson.data;
							$scope.totalPage = dataJson.data.length; //总数
						}else {
							$scope.Item = [{"nmNotification":"","nmInterval":"","stDeviceCode":"","stDeviceName":"","nmOrder":"","nmIsHost":"","nmRecover":""}];
							$scope.totalPage = "";
						}
						var nmNotification = 0;
						var banjian = 0;
						$scope.Item.forEach(item => {
							$scope.stDeviceAddress = item.stDeviceAddress;
							nmNotification += item.nmNotification;
							banjian += item.nmInterval;
							var medicalNum = Number((item.nmIsHost / 50) * 100).toFixed(0);
							medicalNum += "%";
							item.medicaljindu = medicalNum; //医保剩余量进度条
		
							var printNum = Number((item.nmRecover / 500) * 100).toFixed(0);
							printNum += "%";
							item.printjindu = printNum; //A4打印纸剩余量进度条
						});
						console.log($scope.Item);
						$scope.yichang = nmNotification; //异常数
						$scope.zchang = $scope.totalPage - nmNotification; //正常数
						$scope.banjian = banjian; //办件数
						$scope.$apply();
					},
					error: function(err) {
						$scope.topTen = [{
							"stItemName": "暂无数据",
							"stExt1": "0"
						}]
						console.log(err)
					}
				});
				$scope.$apply();

			},
			error: function(err) {
				console.log(err)
			}
		});
	}
	// 关闭弹窗
	$scope.closePop = function() {
		$(".pop").css("display", "none");
		$(".pop_sb").css("display", "none");
	}

	myChart_sb.on('click', function(params) {
		var name = params.value;
		if(isNaN(params.value)){
			name = params.value;
		} else {
			name = params.name;
		}
		$(".currentPage").html('1');
		$scope.streetName = name;
		$scope.Item = [{"nmNotification":"","nmInterval":"","stDeviceCode":"","stDeviceName":"","nmOrder":"","nmIsHost":"","nmRecover":""}];
		$.ajax({
			type: 'get',
			dataType: 'jsonp',
			jsonp: 'jsonpCallback',
			url: mapUrl + "/infopub/deviceinfo/streetDeviceList.do",
			data: {
				stStreetName: name
			},
			success: function(dataJson) {
				if(dataJson.data.length > 0) {
					$scope.Item = dataJson.data;
					$scope.totalPage = dataJson.data.length; //总数
				}else {
					$scope.totalPage = "";
				}
				var nmNotification = 0;
				var banjian = 0;
				$scope.Item.forEach(item => {
					$scope.stDeviceAddress = item.stDeviceAddress;
					nmNotification += item.nmNotification;
					banjian += item.nmInterval;
					var medicalNum = Number((item.nmIsHost / 50) * 100).toFixed(0);
					medicalNum += "%";
					item.medicaljindu = medicalNum; //医保剩余量进度条

					var printNum = Number((item.nmRecover / 500) * 100).toFixed(0);
					printNum += "%";
					item.printjindu = printNum; //A4打印纸剩余量进度条
				});
				$scope.yichang = nmNotification; //异常数
				$scope.zchang = $scope.totalPage - nmNotification; //正常数
				$scope.banjian = banjian; //办件数
				$scope.$apply();
			},
			error: function(err) {
				console.log(err)
			}
		});
	});

	// 弹窗翻页
	$scope.next = function() {
		var currentPage = $(".currentPage").html();
		var totalPage = $scope.totalPage;
		if(currentPage < totalPage) {
			$("#slider").children(".slider_list").children("li").eq(currentPage - 1).css("display", "none");
			$("#slider").children(".slider_list").children("li").eq(currentPage).css("display", "block");
			currentPage++;
			$(".currentPage").html(currentPage);
		}

	}
	$scope.prev = function() {
		var currentPage = $(".currentPage").html();
		var totalPage = $scope.totalPage;
		if(currentPage > 1) {
			$("#slider").children(".slider_list").children("li").eq(currentPage - 1).css("display", "none");
			$("#slider").children(".slider_list").children("li").eq(currentPage - 2).css("display", "block");
			currentPage--;
			$(".currentPage").html(currentPage);
		}
	}
});