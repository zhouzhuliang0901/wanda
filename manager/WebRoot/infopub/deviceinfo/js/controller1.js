var app = angular.module("myApp", []);
//var dataURL = 'http://10.81.16.56:8088/ac-self-manager/';//正式
var dataURL = 'http://localhost:8080/ac-self-manager'; // 测试
console.log("dataURL:"+dataURL);
app.controller("devicesCtrl", function($scope) {
    $scope.online = 0; // 是否在线
    $scope.marker = ''; // 标注
    $scope.infoWindow = ''; //信窗口内容
    $scope.Item = []; // 同纬度设备的信息
    var myIcon = ''; // 图标状态
    var markerClusterer = '';
    var markers = [];
    $scope.zxData = []; // 在线数据
    $scope.lxData = []; // 离线数据
    $scope.allData = []; // 全部数据
    $scope.openWarn = true; // 警报窗口
    clearInterval();
    var load = new Loading(); // 实例化一个加载中
    load.init({
            target: "#loading-content"
    });
    // 去拿异常的外设数据
    $scope.getWarnDeviceInfo = function(flag, id) {
        //清除聚合点
        markerClusterer.clearMarkers(markers);
        map.clearOverlays();
        load.start();
        newArr = [];
        var markers = [];
        $.ajax({
            url: dataURL + "/infopub/odeviceStatus/listCount.do",
            type: "post",
            dataType: "jsonp",
            jsonp: "jsonpCallback",
            data: {
                stDeviceId: id || ''
            },
            success: function(json) {
                $scope.info = json.data;
                for(var i = 0; i < $scope.info.length; i++) {
                    myIcon = new BMap.Icon('./img/markers.png', new BMap.Size(100, 80), {
                        imageSize: new BMap.Size(35, 25), // 设置图标大小
                    })
                    // 将标注添加到地图中
                    if(flag != 2) {
                        $scope.marker = new BMap.Marker(new BMap.Point($scope.info[i][0].nmLng, $scope.info[i][0].nmLat));
                        map.addOverlay($scope.marker);
                    }
                    // 1.判断同纬度的设备nmLat
                    var newArr = [];
                    var arr = [];
                    var arr1 = [];
                    var outerDevice = [2, 2, 2, 2, 2, 2, 50, 500];
                    var temp = $scope.info[i][0].nmLat;
                    var count = 0;
                    for(var j = 0; j < $scope.info.length; j++) {
                        outerDevice = [2, 2, 2, 2, 2, 2, 50, 500];
                        if($scope.info[j][0].nmLat == temp) {
                            count++;
                            arr.push($scope.info[j][0]); //,JSON.stringify($scope.info[j].stDesc)
                            arr.map(((item, index) => {
                                if(item.stDesc && item.nmLat != -1) {
                                    var state = JSON.parse(item.stDesc).data;
                                    var exception = 0; // 无异常
                                    for(var m = 0; m < state.length; m++) {
                                        if(state[m].stOutDeviceCode == "IdCard") {
                                            outerDevice[0] = state[m].nmException;
                                        } else if(state[m].stOutDeviceCode == "CmCapture") {
                                            outerDevice[1] = state[m].nmException;
                                        } else if(state[m].stOutDeviceCode == "QrScanner") {
                                            outerDevice[2] = state[m].nmException;
                                        } else if(state[m].stOutDeviceCode == "MedicalInsuranc") {
                                            outerDevice[3] = state[m].nmException;
                                            outerDevice[6] = state[m].nmRemain;
                                            var medicalNum = Number((state[m].nmRemain / 50) * 100).toFixed(0);
                                            medicalNum += "%";
                                            outerDevice[9] = medicalNum;
                                        } else if(state[m].stOutDeviceCode == "A4Printer") {
                                            outerDevice[4] = state[m].nmException;
                                            outerDevice[7] = state[m].nmRemain;
                                            var printNum = Number((state[m].nmRemain / 500) * 100).toFixed(0);
                                            printNum += "%";
                                            outerDevice[8] = printNum;
                                        } else if(state[m].stOutDeviceCode == "ResidenceErased") {
                                            outerDevice[5] = state[m].nmException;
                                        }
                                    }
                                    arr1.push(Object.assign({}, item, {
                                        state: JSON.parse(item.stDesc).data,
                                        nmOnline: '2',
                                        outDevice: outerDevice
                                    }))
                                } else if(item.nmLat != -1) {
                                    arr1.push(Object.assign({}, item, {
                                        state: '0'
                                    }))
                                }
                            }))
                            $scope.info[j][0].nmLat = -1;
                        }
                    }
                    if(temp != -1) {
                        newArr.push(temp, count, arr, arr1)
                    }
                    // 2.放入Item
                    if(newArr.length != 0) {
                        $scope.Item = newArr[3];
                        //console.log($scope.Item);
                        $scope.totalPage = newArr[1];
                        // 添加文本框
                        var label = new BMap.Label($scope.totalPage, {
                            offset: new BMap.Size(20, -10)
                        });
                        label.setStyle({
                            //color:'rgba(0,0,0,0)',
                            color: '#fff',
                            fontSize: '12px',
                            height: '30px',
                            width: '66px',
                            /*height: '29px',
								    width: '19px',*/
                            lineHeight: '27px',
                            border: '0px !important',
                            borderRadius: '4px',
                            textAlign: 'center',
                            fontFamily: '微软雅黑',
                            backgroundColor: 'rgba(0,0,0,0)',
                            //display:'none',
                        });
                        $scope.marker.setLabel(label);
                        /*$scope.marker.addEventListener("mouseover",function(e){
		            			// 得到当前得label
		            			var currentLabel = this.getLabel();
		            			currentLabel.setStyle({
									color : '#fff',
								});
		            		});
		            		$scope.marker.addEventListener("mouseout",function(e){ 
		            			// 鼠标移出事件
		            			var currentLabel = this.getLabel();
		            			currentLabel.setStyle({
									color : '#f00',
								});
		            		});*/
                        $scope.$apply();
                    }
                    // 添加到信息窗口的展示内容
                    $scope.infoWindow = $(".pop2").html();
                    // 给标注点添加点击事件
                    addClickHandler($scope.infoWindow, $scope.marker);
                }
            },
            error: function(err) {
                console.log(err);
            }
        });
    }
    /*$scope.getWarnDeviceInfo(2);*/
    // 创建地图的点
    $scope.createPoint = function(infoArr) {
        markers = [];
        if(infoArr.length == 0) {
            alert("检测无设备信息");
			load.stop();
            return;
        }
        for(var i = 0; i < infoArr.length; i++) {
            var item = infoArr[i];
            $scope.Item = item.MAP;
            $scope.totalPage = item.COUNT;
            $scope.$apply();
            let marker = new BMap.Marker(new BMap.Point(item.LNG, item.LAT));
            var infoWindow = $(".gray").html();
            addClickHandler(infoWindow, marker);
            markers.push(marker);
        }
        markerClusterer = new BMapLib.MarkerClusterer(map, {
            markers: markers
        });
        load.stop();
    }

    // 请求全部后台设备信息
    $scope.getDeviceInfo = function(address,stTypeId) {
		//清除聚合点
        if(markers.length != 0){            
            markerClusterer.clearMarkers(markers);
        }
        map.clearOverlays();
        load.start();
        $.ajax({
            url: dataURL + "/infopub/deviceinfo/infopubMap.do",
            type: "post",
            dataType: "jsonp",
            jsonp: "jsonpCallback",
            data: {
                jsonpCallback: "JSON_CALLBACK",
                stAreaId: '123' || '',
                stPermission: 'project_admin' || '',
                address: address || '',
				stTypeId:stTypeId || '',
				userName:user,
            },
            success: function(dataJson) {
				$scope.zxData = []; // 在线数据
				$scope.lxData = []; // 离线数据
				$scope.allData = []; // 全部数据
                $scope.allData = dataJson.data;
				
				var num = 0;
				dataJson.data.map(function(item, index) {
					num = num + item.COUNT;
				})
				console.log(num);
              //if(!address){
                    dataJson.data.map(function(item, index) {
                        var zx = item.MAP.filter(function(point) {
                            return point.nmOnline == 1 || point.nmOnline == 2;
                        })
                        $scope.zxData = $scope.zxData.concat(zx);
                        var lx = item.MAP.filter(function(point) {
                            return point.nmOnline == 0;
                        })
                        $scope.lxData = $scope.lxData.concat(lx)
                    })
                    $scope.zxNum = $scope.zxData.length;
                    $scope.lxNum = $scope.lxData.length;
                    $scope.qbNum = $scope.zxData.length + $scope.lxData.length;
					
              //}
				
                $scope.createPoint($scope.allData);
            },
            error: function(err) {
                console.log(err)
            }
        });
    }
    $scope.getDeviceInfo();
    
    // 请求在线离线数据
    $scope.getOnlineOrNot = function(online) {
        //清除聚合点
        markerClusterer.clearMarkers(markers);
        map.clearOverlays();
        load.start();
        $.ajax({
            url: dataURL + "/infopub/deviceinfo/infopubMap.do",
            type: "post",
            dataType: "jsonp",
            jsonp: "jsonpCallback",
            data: {
                jsonpCallback: "JSON_CALLBACK",
                stAreaId: '123' || '',
                stPermission: 'project_admin' || '',
                address: '',
                online: online || '',
                userName:user,
            },
            success: function(dataJson) {
                $scope.currentData = dataJson.data;
                $scope.createPoint($scope.currentData);
            },
            error: function(err) {
                console.log(err)
            }
        });
    }
    
    // 监测异常设备信息
    $scope.listenWarnDevice = function() {
        $.ajax({
            url: dataURL + "/infopub/odeviceStatus/listCount.do",
            type: "post",
            dataType: "jsonp",
            jsonp: "jsonpCallback",
            data: {},
            success: function(json) {
                $scope.ycNum = json.data.length;
                $scope.$apply();
                $scope.openWarn = true;
                $scope.$apply();
                $scope.Warn = json.data;
                setTimeout(function() {
                    $scope.openWarn = false;
                    $scope.$apply();
                }, 6000)
            },
            error: function(error) {
                console.log(error);
            }
        });
    }
	
		// 设备类型选择
	$scope.infopubType = function(){
		$.ajax({
			url: dataURL + "/infopub/deviceinfotype/optionList.do",
			type: "post",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {userName:'admin'},
			success: function(dataJson) {
				$scope.ItemType = JSON.parse(dataJson).data;
				$scope.$apply();
			},
			error: function(error){
				console.log(error);
			}
		});
	}
	$scope.infopubType();
	
    $scope.listenWarnDevice();
    //	setInterval(function(){
    //		clearInterval();
    //		$scope.listenWarnDevice();
    //	},36000)
});