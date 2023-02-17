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
		行为分析<span class="c-gray en">&gt;</span>办理分析 <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
	</nav>
	
	<div class="page-container">
		<br>
		<div class="text-x" style="text-align: left;">
			<!-- 办理日期： <input type="text"
				onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'startDate\')||\'%y-%M-%d \'}'})"
				id="startDate" class="input-text Wdate" style="width:120px;" autocomplete="off"> -
			<input type="text"
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'endDate\')}',maxDate:'%y-%M-%d'})"
				id="endDate" class="input-text Wdate" style="width:120px;" autocomplete="off">
		 
		 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
		 	事项名称：
		 	<input name="" id="searchItemName" class="input-text" style="width:250px"class="input-text">
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
			
			<!-- 办理量 -->
			<div id="" style="width: 61%;height:420px; float: left; position:relative;left:10px;top:5px;">
				<table width="100%"
				class="table table-border table-bordered table-bg table-hover table-sort" id="itemList">
					<thead>
						<tr class="text-c">
							<th width="15" ><input name="" type="checkbox" ></th>
							<th width="100">业务编码</th>
							<th width="100">业务名称</th>
							<th width="100">所属部门</th>
							<!-- <th width="100">所属服务</th> -->
							<th width="50">办理量</th>
							<th width="50">数据分布</th>
													
						</tr>
					</thead> 
					<tbody>
					</tbody>
				</table>
			</div>
			<!-- 年龄 -->
			<div id="" style="width: 35%;height:420px;float: left;position:relative; left:80px;top:20px;">
				<div id="age" style="width: 500px;height:360px;margin:20px 0px 0px 20px;"></div>
				<div id="" style="width: 500px;height:60px;margin:10px 0px 0px 20px;">
					<p id="p1"><strong id="stro1">办理量：</strong></p>
					<p id="p2"><strong id="stro2">办理量/访问量：</strong></p>
					<div class="progress radius" style="width: 500px;"><div class="progress-bar"><span id="speed" class="sr-only" style="width:0%"></span></div></div>
	
				</div>
			</div>
			
			
			
		</div>
		
	</div>
<script type="text/javascript"
	src="<%=webRoot%>/resources/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/sms/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>		
<script type="text/javascript"
	src="<%=webRoot%>/resources/My97DatePicker/WdatePicker.js"></script>	
<script type="text/javascript">
var table = $('#itemList').dataTable({
     "aoColumnDefs": [
      {"orderable":false,"aTargets":[0]}// 制定列不参与排序
    ],
    "order": [[ 4, "desc" ]],
    "processing" : true,  
    "serverSide" : true,  
    "searching": false,
    "paginationType":"full_numbers",
    "aoColumns": [
                {
                 "sClass": "text-center",
                 "data": "stItemId",
                 "render": function (data, type, full, meta) {
                     return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
                    },
                  "bSortable": false
                },
                { "data": "stItemNo",
                   "render":function(data, type, full, meta){
                      return  setNullData(data);
                }},
                { "data": "stMainName",
                   "render":function(data, type, full, meta){
                       return setNullData(data) ;
                }},
                 { "data": "stOrganId",
                   "render":function(data, type, full, meta){
                       return setNullData(data) ;
                }},
                 { "data": "stExt1",
                   "render":function(data, type, full, meta){
                       return parseInt(data) ;
                }},
                {
                 "data": "stMainName",
                 "render": function (data, type, full, meta) {
                          return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"display('"+data+"','"+full.stExt1+"','"+full.stExt2+"')\" href=\"javascript:void(0);\" title=\"年龄分布\"><i class=\"Hui-iconfont\">&#xe61a;</i></a>";
                 				 
                    },
                  "bSortable": false
                }      
                
            ],
       "createdRow": function( row, data, dataIndex ) {
                    $(row).children('td').eq(0).attr('style', 'text-align: center;');
                    $(row).children('td').eq(1).attr('style', 'text-align: center;');
                    $(row).children('td').eq(2).attr('style', 'text-align: center;');
                    $(row).children('td').eq(3).attr('style', 'text-align: center;');
                    $(row).children('td').eq(4).attr('style', 'text-align: center;');
                    $(row).children('td').eq(5).attr('style', 'text-align: center;');
                },
      "bPaginate": true, //翻页功能
      "bLengthChange": true ,//改变每页显示数据数量
       "ajax": {
		    url:webRoot+'/statistics/selmStatistics/analysisItemList.do',
		    type:"POST",
		    data: function(d){
		      var stMainName = $("#searchItemName").val();
		      d.stMainName = stMainName;
	          var startDate = $("#startDate").val();
	          d.startDate = startDate;
              var endDate = $("#endDate").val();
              d.endDate= endDate;
		    },
		    error:function(){
		      window.parent.location.reload();
		    }
	    }
}); 


$(function(){
    $("#itemList").removeAttr("style"); 
});
function setNullData(data){
    if(data == ''){
        return '<span class=\"label label-danger radius\"></span>';
    } else {
        return data;
    }
}

// 查找
function search () {
    table.api().ajax.reload();
}

//stExt1办理量，stExt2访问量
function display(itemName,stExt1,stExt2){
	var t1 = parseInt(stExt1);
	var t2 = parseInt(stExt2);
	var scale = ((t1/t2)*100).toFixed(1);
    if(stExt2 == 0){
    	$("#speed").width("100%");
    	$("#stro1").html(itemName+"办理量："+stExt1);
		$("#stro2").html(itemName+"办理量/访问量：100%");
    }else{
	    $("#speed").width(scale+"%");
	    $("#stro1").html(itemName+"办理量："+stExt1);
		$("#stro2").html(itemName+"办理量/访问量："+scale+"%");
	}
	
	loadEChart(itemName);
	
}

//饼图渲染
function loadEChart(itemName){
	var nums = [];
	$.ajax({
		type:'get',
		async:true,
		dataType:'json',
		url:webRoot+'/statistics/selmStatistics/itemPeNumber.do',
		data: {
                stItemName: itemName
       	},
       	success:function(dataJson){
       		if(dataJson.data.length > 0){
       			for(var i=0; i<dataJson.data.length; i++){
   					nums.push(dataJson.data[i]);
   				}
       		}else{
       			nums = [
	            {value: 0, name: '25岁以下'},
	            {value: 0, name: '26-35岁'},
	            {value: 0, name: '36-45岁'},
	            {value: 0, name: '46-60岁'},
	            {value: 0, name: '60岁以上'}
	        	]
       		}
   			
  			ageChart.setOption({
	   			title: {
					text: dataJson.itemName,
					subtext: '办理人年龄分布',
					left: 'center'
				},
				   			series:[{
				   				name: dataJson.itemName,
				       type: 'pie',
				       radius: '50%',
	   				data:nums
	   			}]
  			})
       		
       	},
       	error:function(){
       	}
       	
	});
}

// 基于准备好的dom，初始化echarts实例
var ageChart = echarts.init(document.getElementById('age'));
// 指定图表的配置项和数据
var ageOption = {
    	title: {
  			text: '各类事项',
  			subtext: '办理人年龄分布',
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
	        name: '事项',
	        type: 'pie',
	        radius: '50%',
	        data: [
	            {value: 0, name: '25岁以下'},
	            {value: 0, name: '26-35岁'},
	            {value: 0, name: '36-45岁'},
	            {value: 0, name: '46-60岁'},
	            {value: 0, name: '60岁以上'}
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


</script>
</body>
</html>