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
		统计分析<span class="c-gray en">&gt;</span>办理分析 <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
	</nav>
	
	<div class="page-container">
		<div class="text-x" style="text-align: left;">
			服务选择：
		   <select name="searchTypeId" id="deviceTypeSelect" class="input-text" style="width:250px">
		  
		   </select>
		 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 	事项选择：
		 	<select name="" id="searchAreaName" class="input-text" style="width:250px"class="input-text">
				
			</select>
			<br><br>
			<div class="text-x" style="text-align: left;">
				日期选择： 
				<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'startDate\')||\'%y-%M-%d \'}'})"
				id="startDate" class="input-text Wdate" style="width:250px;" autocomplete="off">
				
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button name="" id="search" class="btn btn-success" onclick="search()" >
					<i class="Hui-iconfont">&#xe665;</i>搜索
				</button>
			</div>
				
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l"></span>
			</div>
			
		</div>
		
		<div class="mt-20">
			<!-- 年龄 -->
			<div id="" style="width: 45%;height:420px;float: left;position:relative; left:50px;top:20px;border-style: solid;border-width: thin;border-radius:1em;border-color:#d9d6c3;">
				<div id="age" style="width: 600px;height:400px;margin:20px 0px 0px 20px;"></div>
			</div>
			<!-- 办理量 -->
			<div id="" style="width: 45%;height:420px; float: left; position:relative;left:90px;top:20px;border-style: solid;border-width: thin;border-radius:1em;border-color:#d9d6c3;">
				<div id="item" style="width: 600px;height:400px;margin:20px 0px 0px 20px;"></div>
			</div>
		</div>
		
	</div>
		
<script type="text/javascript"
	src="<%=webRoot%>/resources/My97DatePicker/WdatePicker.js"></script>	
<script src="js/controller.js"></script>
<script type="text/javascript">
// 基于准备好的dom，初始化echarts实例
var ageChart = echarts.init(document.getElementById('age'));
// 指定图表的配置项和数据
var ageOption = {
    	title: {
  			text: '年龄分布',
  			subtext: '---',
  			left: 'center'
				},
		tooltip: {
    		trigger: 'item'
				},
		legend: {
   			orient: 'vertical',
    		left: 'left',
				},
		series: [{
	        name: '访问来源',
	        type: 'pie',
	        radius: '50%',
	        data: [
	            {value: 1048, name: '25岁以下'},
	            {value: 735, name: '26-35岁'},
	            {value: 580, name: '36-45岁'},
	            {value: 484, name: '46-60岁'},
	            {value: 300, name: '60岁以上'}
	        ],
        	emphasis: {
	            itemStyle: {
	                shadowBlur: 10,
	                shadowOffsetX: 0,
	                shadowColor: 'rgba(0, 0, 0, 0.5)'
	            }
        	}
    	}]
};
// 使用刚指定的配置项和数据显示图表。
ageChart.setOption(ageOption);


var itemChartDom = document.getElementById('item');
var itemChart = echarts.init(itemChartDom);
var itemoption = {
    title: {
        text: '办理沙漏',
        subtext: '---'
    },
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c}%"
    },
    toolbox: {
        feature: {
            dataView: {readOnly: false},
            restore: {},
            saveAsImage: {}
        }
    },
    legend: {
        data: ['访问量','办理量','访问','咨询','订单']
    },

	calculable: true,
    series: [
        {
            name:'办理沙漏',
            type:'funnel',
            left: '10%',
            top: 60,
            //x2: 80,
            bottom: 60,
            width: '80%',
            // height: {totalHeight} - y - y2,
            min: 0,
            max: 100,
            minSize: '0%',
            maxSize: '100%',
            sort: 'descending',
            gap: 2,
            label: {
                show: true,
                position: 'inside'
            },
            labelLine: {
                length: 10,
                lineStyle: {
                    width: 1,
                    type: 'solid'
                }
            },
            itemStyle: {
                borderColor: '#fff',
                borderWidth: 1
            },
            emphasis: {
                label: {
                    fontSize: 20
                }
            },
            data: [
                /* {value: 60, name: '访问'},
                {value: 40, name: '咨询'},
                {value: 20, name: '订单'}, */
                {value: 50, name: '办理量'},
                {value: 100, name: '访问量'}
            ]
        }
    ]
};

itemChart.setOption(itemoption);


</script>
</body>
</html>