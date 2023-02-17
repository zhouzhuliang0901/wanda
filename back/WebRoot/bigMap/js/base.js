// 百度地图API功能	
	var map = new BMap.Map("allmap");
	map.centerAndZoom(new BMap.Point(121.417428, 31.27923), 12); //创建地图中心和展示级别
	map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
	map.enableKeyboard();
	map.enableDragging(true);
	map.enableDoubleClickZoom();
	// 地图自己的样式
	var mapStyle = {
		style: "grayscale"
	};
	map.setMapStyle(mapStyle);
	// 设置图标
	var icon = './img/lgreen.png';
	var opts = {
		width: 480, // 信息窗口宽度
		height: 420, // 信息窗口高度
		enableMessage: true //设置允许信息窗发送短息
	};

	function addClickHandler(infoWindow, marker) {
		marker.addEventListener("click", function(e) {
			openInfo(infoWindow, e);
			$(".currentPage").html('1');
		});
	}
	
	var currentPage = 1;
	function openInfo(infoWindow, e) {
		currentPage = 1;
		$(".currentPage").html('1');
		var p = e.target;
		var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat); //根据经纬度
		var infoWindow = new BMap.InfoWindow(infoWindow, opts); // 创建信息窗口对象 
		map.openInfoWindow(infoWindow, point); //开启信息窗口
		if (!infoWindow.isOpen()) {
                //如果没有打开，则监听打开事件，获取按钮，添加事件
			infoWindow.addEventListener("open", function () {
				document.getElementById("test").onclick = function (e) {
					map.closeInfoWindow(infoWindow,point);
					currentPage = 1;
				}
				document.getElementById("next").onclick = function (e) {
					var totalPage = parseInt($(".totalPage").html());
					if(currentPage < totalPage){
						$("#slider").children(".slider_list").children("li").eq(currentPage-1).css("display","none");
						$("#slider").children(".slider_list").children("li").eq(currentPage).css("display","block");
						currentPage++;
						$(".currentPage").html(currentPage);
					}
					
				}
				document.getElementById("prev").onclick = function(e){
					var totalPage = parseInt($(".totalPage").html());
					if(currentPage > 1){
						$("#slider").children(".slider_list").children("li").eq(currentPage-1).css("display","none");
						$("#slider").children(".slider_list").children("li").eq(currentPage-2).css("display","block");
						currentPage--;
						$(".currentPage").html(currentPage);
					}
				}
			})
		} else {//如果已经打开，直接获取按钮，添加事件
			document.getElementById("test").onclick = function (e) {
					map.closeInfoWindow(infoWindow,point);
					currentPage = 1;
				}
			document.getElementById("next").onclick = function (e) {
					var totalPage = parseInt($(".totalPage").html());
					if(currentPage < totalPage){
						$("#slider").children(".slider_list").children("li").eq(currentPage-1).css("display","none");
						$("#slider").children(".slider_list").children("li").eq(currentPage).css("display","block");
						currentPage++;
						$(".currentPage").html(currentPage);
					}
					
				}
				document.getElementById("prev").onclick = function(e){
					alert(12)
					var totalPage = parseInt($(".totalPage").html());
					if(currentPage > 1){
						$("#slider").children(".slider_list").children("li").eq(currentPage-1).css("display","none");
						$("#slider").children(".slider_list").children("li").eq(currentPage-2).css("display","block");
						currentPage--;
						$(".currentPage").html(currentPage);
					}
				}
		}
	}
	
