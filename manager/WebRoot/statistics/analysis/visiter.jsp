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
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<script type="text/javascript">var webRoot = '<%=StringEscapeUtils.escapeJavaScript(webRoot) %>';</script>
<script src="<%=webRoot%>/statistics/analysis/js/echarts.min.js"></script> 
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
<link href="<%=webRoot%>/sms/lib/jquery-ui-1.12.1/jquery-ui.min.css"
    rel="stylesheet" />
<link href="<%=webRoot%>/sms/lib/jQuery-Timepicker/jquery-ui-timepicker-addon.min.css"
    rel="stylesheet" />	
<link href="<%=webRoot%>/sms/lib/webuploader/0.1.5/webuploader.css"
    rel="stylesheet" type="text/css" />

<title>办理分析 </title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		行为分析<span class="c-gray en">&gt;</span>关键行为分析 <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
	</nav>
	
	<div class="page-container">
		<div class="text-x" style="text-align: left;">
			<!-- 办理日期： <input type="text"
				onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'startDate\')||\'%y-%M-%d \'}'})"
				id="startDate" class="input-text Wdate" style="width:120px;" autocomplete="off"> -
			<input type="text"
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'endDate\')}',maxDate:'%y-%M-%d'})"
				id="endDate" class="input-text Wdate" style="width:120px;" autocomplete="off">
		 
		 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
		 	<br>
		 	用户名称：
		 	<input name="" id="searchName" class="input-text" style="width:250px"class="input-text">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button name="" id="search" class="btn btn-success" onclick="search()" >
				<i class="Hui-iconfont">&#xe665;</i>搜索
			</button>
			<br><br>
				
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l"></span>
			</div>
			
		</div>
		
		<div class="mt-20">
			<div id="" style="width: 100%;height:50px; float: left; position:relative;left:10px;top:5px;">
				<div style="width: 25%;float: left; position:relative;left:10px;top:5px;">
					<p id="p1"><strong id="stro1">服务点击总量：</strong></p>
				</div>
				<div style="width:25%;float: left; position:relative;left:10px;top:5px;">
					<p id="p1"><strong id="stro2">服务点击领率：</strong></p>
				</div>
				
			</div>
			<div id="item" style="width: 100%;height:460px; float: left; position:relative;left:10px;top:5px;"></div>
		</div>
		
	</div>
<script type="text/javascript"
	src="<%=webRoot%>/resources/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/resources/layer/2.1/layer.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/resources/My97DatePicker/WdatePicker.js"></script>	
<script type="text/javascript">
// 查找
function search () {
 	var searchName = $("#searchName").val();
 	loadEChart(searchName);
}

$(function(){
    loadEChart("");
});

function display(itemCount,rate){
   	$("#stro1").html("服务点击总量："+itemCount);
	$("#stro2").html("服务点击领率："+rate+" 次/天");
}

//柱状图渲染
function loadEChart(vName){
	var nums1 = [];
	var nums2 = [];
	$.ajax({
		type:'get',
		async:true,
		dataType:'json',
		url:webRoot+'/statistics/selmStatistics/itemVisiter.do',
		data: {
                visiterName: vName
       	},
       	success:function(dataJson){
       		if(dataJson.data){
       			for(var i=0; i<dataJson.data.length; i++){
       				nums1.push(dataJson.data[i].itemName);
       				nums2.push(dataJson.data[i].count);
       			}
       		}
       		//渲染柱状图
       		itemChart.setOption({
			    title: {
			        text: '用户点击Top20服务数量',
			        subtext: '用户：'+vName
			    },
       			yAxis: {
			        type: 'category',
			        inverse: true,//倒叙
			        data: nums1
			    },
			    series: [
			        {
			            name: '点击量',
			            type: 'bar',
			            data: nums2
			        }]
       		
       		})
       		//点击量和点击频率
       		display(dataJson.itemCount,dataJson.rate);
       	},
       	error:function(){
       		layer.msg("查无此人",{icon:1,time:1000});
       	}
       	
	});
}

var itemDom = document.getElementById('item');
var itemChart = echarts.init(itemDom);
var itemOption = {
    title: {
        text: '用户点击Top20服务数量',
        subtext: '用户:'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    legend: {
        data: ['事项']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: {
        type: 'value',
        boundaryGap: [0, 0.01]
    },
    yAxis: {
        type: 'category',
        data: ['', '', '', '', '', '', '', '', '', '']
    },
    series: [
        {
            name: '点击量',
            type: 'bar',
            data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        }]
};

itemChart.setOption(itemOption);

</script>
</body>
</html>