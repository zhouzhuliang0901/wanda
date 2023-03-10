<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="coral.base.app.AppContext"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="permission" uri="/sms/permission"%>
<%
	String webRoot = AppContext.webRootPath;
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<script type="text/javascript">var webRoot = '<%=StringEscapeUtils.escapeJavaScript(webRoot)%>';</script>
<!--[if lt IE 9]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/html5.js"></script>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/respond.min.js"></script>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/PIE_IE678.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="<%=webRoot%>/sms/lib/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=webRoot%>/sms/lib/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="<%=webRoot%>/sms/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="<%=webRoot%>/sms/lib/icheck/icheck.css" />
<link rel="stylesheet" type="text/css"
	href="<%=webRoot%>/sms/lib/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="<%=webRoot%>/sms/lib/h-ui.admin/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="<%=webRoot%>/sms/lib/iconfont/iconfont.css" />

<%--<link rel="stylesheet" type="text/css"--%>
<%--	href="https://at.alicdn.com/t/font_1700164_scb7ybg4fd.css" />--%>

<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->

<title>????????????</title>
<style type="text/css">
	body, html{width: 100%;height: 100%;margin:0;font-family:"????????????";}
	* {
		margin: 0;
		padding: 0;
	}
	#allmap {
		width:100%;
		height:100%;
	}
	#info p {
		font-size:15px;
		margin-top: 4px;
		width: 300px;
		margin-left: 16px;
	}
	#info h4 {
		margin-left:16px;
		padding-top: 10px;
	}
	#info {
		margin-top: 10px;
		width: 300px;
		height: 154px;
		z-index: 99;
		background-color: rgb(84, 94, 250);
		color: #fff;
		border-radius: 8px;
	}
	.anchorBL{
        display:none;
    }
    .BMap_bubble_title{
        color:rgb(132, 133, 135);
        font-size:13px;
        font-weight: bold;
        text-align:center;
        margin-top: -6px;
        margin-bottom: -8px;
    }
    .BMap_pop div:nth-child(1){
        border-radius:8px 0 0 0;
    }
    .BMap_pop div:nth-child(3){
        border-radius:0 8px 0 0;background:#ABABAB;;
        background: #ABABAB;
        width:23px;
        width:0px;height;0px;
    }
    .BMap_pop div:nth-child(3) div{
        border-radius:8px;
    }
    .BMap_pop div:nth-child(5){
        border-radius:0 0 0 8px;
    }
    .BMap_pop div:nth-child(5) div{
        border-radius:8px;
    }
    .BMap_pop div:nth-child(7){
        border-radius:0 0 8px 0;
    }
    .BMap_pop div:nth-child div(7){
        border-radius:8px ;
	}
</style>
<script type="text/javascript"
	src="https://api.map.baidu.com/getscript?v=3.0&ak=9HcHj4OyHByYFKuc7gFlbnNj7OIpkGnU"></script>
<script type="text/javascript"
	src="<%=webRoot%>/sms/lib/common/jquery-1.12.4.js"></script>
</head>
<body>
	<div id="allmap"></div>
</body>
<script type="text/javascript">
var areaId = "<%= (Object)request.getSession().getAttribute("smsUserStAreaId")%>";
var permission = "<%= (Object)request.getSession().getAttribute("??????")%>";

	$(function(){
		map = new BMap.Map("allmap");
		map.centerAndZoom(new BMap.Point(121.417428, 31.20923), 11); //?????????????????????????????????
		map.enableScrollWheelZoom(true);     //????????????????????????
//		map.setMapStyleV2({     
//		  styleId: '13f5606fa7e21e90e63f21be90ad37ac'
//		}); // ????????????????????????
		map.setMapStyle({ style: "grayscale" }); // ???????????????????????????
		
		var data_info =	''; // ????????????	
		// ??????????????????
		$.ajax({
			url: webRoot + '/infopub/deviceinfo/placeshow.do',
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				'stAreaId':areaId,
				'stPermission':permission,
				jsonpCallback: "JSON_CALLBACK"
			},
			success: function(json) {
				data_info = json.data;
				// ???????????????????????????????????????
				for(var i=0;i<data_info.length;i++){
					console.log(data_info[i].stDeviceName);
					var marker = new BMap.Marker(new BMap.Point(data_info[i].nmLng,data_info[i].nmLat),{icon: myIcon});  // ????????????			
					map.addOverlay(marker);   
				
					// tips1.?????????????????????????????????
					var content = "<div id=${'info'}>"+
										"<h4>????????????:"+data_info[i].stDeviceName+"</h4>"+
										"<p>????????????:"+data_info[i].stDeviceAddress+"</p>"+
										/* "<p>????????????:"+data_info[i].stDeviceId+"</p>"+ */
										"<p>????????????:"+data_info[i].stDeviceCode+"</p>"+
										"<p>??????IP:"+data_info[i].stDeviceIp+"</p>"+
										"<p>??????MAC:"+data_info[i].stDeviceMac+"</p>"+
									"</div>";
							
					// tips2.??????????????????????????????
					addClickHandler(content,marker);
				}
			},
			error: function(err) {
				console.log(err)
			}
		});
		
		var opts = {
					width: 300,     // ??????????????????
					height: 180,     // ??????????????????
					title : "????????????????????????" , // ??????????????????
					enableMessage:true//?????????????????????????????????
				  };
				  
		// ????????????????????????
	    var myIcon = new BMap.Icon('../../sms/lib/sms/image/device.png', new BMap.Size(70, 85), {
	        imageSize: new BMap.Size(55, 65), // ??????????????????
	    });
	    
		function addClickHandler(content,marker){
			marker.addEventListener("click",function(e){
				openInfo(content,e);
			});
		}
		function openInfo(content,e){
			var p = e.target;
			var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat); //???????????????
			var infoWindow = new BMap.InfoWindow(content,opts);  // ???????????????????????? 
			map.openInfoWindow(infoWindow,point); //??????????????????
		}
	});
</script>
</html>