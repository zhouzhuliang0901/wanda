<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.statistics.bean.SelmStatistics"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="permission" uri="/sms/permission"%>
<%
	String webRoot = AppContext.webRootPath;
	 SelmStatistics selmStatistics = (SelmStatistics) request.getAttribute(SelmStatistics.SELM_STATISTICS);
    if (selmStatistics == null)
        selmStatistics = new SelmStatistics(); 
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
<script type="text/javascript">var webRoot = '<%=StringEscapeUtils.escapeJavaScript(webRoot) %>';</script>
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
<%--    href="https://at.alicdn.com/t/font_1700164_scb7ybg4fd.css" />--%>
    
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>访问量(按天) </title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		统计分析 <span class="c-gray en">&gt;</span>区域统计 <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
	</nav>
	<div class="page-container">
		
		<div class="text-c" style="text-align: left;">
			日期范围： <input type="text"
				onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'startDate\')||\'%y-%M-%d \'}'})"
				id="startDate" class="input-text Wdate" style="width:120px;" autocomplete="off"> -
			<input type="text"
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'endDate\')}',maxDate:'%y-%M-%d'})"
				id="endDate" class="input-text Wdate" style="width:120px;" autocomplete="off">
		    <input type="text" name="" id="searchName" placeholder="所属区域" style="width:250px"
				class="input-text">
			<button name="" id="search" class="btn btn-success" onclick="search()" >
				<i class="Hui-iconfont">&#xe665;</i> 搜索
			</button>
		</div>
		
		<!-- <div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
					<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius">
					     <i class="Hui-iconfont">&#xe6e2;</i>
							批量删除</a>
		    </span>
		</div> -->
		
		<div class="mt-20">
			<table width="100%"
				class="table table-border table-bordered table-bg table-hover table-sort" id="statisticsDayList">
				<thead>
					<tr class="text-c">
						<th width="15" ><input name="" type="checkbox" ></th>
						<th width="130">所在区</th>
						<th width="100">政务服务自助终端数量</th>
						<th width="100">社会化自助终端数量</th>
						<th width="100">办件量</th>
						<!-- <th width="100">访问量（点击量）</th> -->
						<!-- <th width="130">信息操作</th> -->
					</tr>
				</thead> 
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
<script type="text/javascript"
	src="<%=webRoot%>/resources/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/resources/layer/2.1/layer.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/resources/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/sms/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/sms/lib/h-ui/js/H-ui.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/sms/lib/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/common/common.js"></script>
<script type="text/javascript">
var areaId = "<%= (Object)request.getSession().getAttribute("smsUserStAreaId")%>";
var permission = "<%= (Object)request.getSession().getAttribute("权限")%>";
var table = $('#statisticsDayList').dataTable({
     "aoColumnDefs": [
      {"orderable":false,"aTargets":[0]}// 制定列不参与排序
    ],
    "order": [[ 1, "desc" ]],
    "processing" : true,  
    "serverSide" : true,  
    "searching": false,
    "paginationType":"full_numbers",
    "aoColumns": [
                {
                 "sClass": "text-center",
                 "data": "stStatisticsId",
                 "render": function (data, type, full, meta) {
                     return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
                    },
                  "bSortable": false
                },
                { "data": "stName",
                "render":function(data, type, full, meta){
                	var returnInfo = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"group_day('"+full.stName+"->街道统计','/statistics/statistics/addressStreet.do','"+data+"')\" href=\"javascript:;\" title=\"查看\">"+data+"</a>";
                      //return  setNullData(data);
                     return returnInfo;
                  }},
                  { "data": "stExt1",
                "render":function(data, type, full, meta){
                      return  setNullData(data);
                  },
                  "bSortable": false},
                  { "data": "stExt2",
                "render":function(data, type, full, meta){
                      return  setNullData(data);
                  },
                  "bSortable": false},
                  { "data": "nmSort",
                "render":function(data, type, full, meta){
                      return  setNullData(data);
                  },
                  "bSortable": false}
                /* { "data": "nmCount",
                   "render":function(data, type, full, meta){
                      return  setNullData(data);
                }} */
              
            ],
       "createdRow": function( row, data, dataIndex ) {
                    $(row).children('td').eq(0).attr('style', 'text-align: center;');
                    $(row).children('td').eq(1).attr('style', 'text-align: center;');
                    $(row).children('td').eq(2).attr('style', 'text-align: center;');
                    $(row).children('td').eq(3).attr('style', 'text-align: center;');
                    $(row).children('td').eq(4).attr('style', 'text-align: center;');
                    $(row).children('td').eq(5).attr('style', 'text-align: center;');
                    //$(row).children('td').eq(6).attr('style', 'text-align: center;');
                },
      "bPaginate": false, //翻页功能
      "bLengthChange": false ,//改变每页显示数据数量
       "ajax": {
		    url:webRoot+'/statistics/selmStatistics/addresslistType.do',
		    type:"POST",
		    data: function(d){
		    var stAreaId = areaId;
		      d.stAreaId = stAreaId; 
		      var stPermission = permission;
		      d.stPermission = stPermission; 
              var stAddressName = $("#searchName").val();
		      d.stAddressName = stAddressName;
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
    $("#statisticsDayList").removeAttr("style"); 
});

// 查找
function search () {
    table.api().ajax.reload();
}

// 查看
function group_day(title,url,name){
	name = encodeURI(encodeURI(name));
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_NAME='+name
    });
    layer.full(index);
} 

// 未配置数据
function setNullData(data){
    if(data == ''){
        return '<span>0</span>';
    } else {
        return data;
    }
}
</script>
</body>
</html>