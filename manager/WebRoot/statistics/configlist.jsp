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
<title>设备管理 </title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		统计分析 <span class="c-gray en">&gt;</span>业务配置<a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
	</nav>
	<div class="page-container">
		
		<div class="text-c">
			<!-- 日期范围： <input type="text"
				onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'startDate\')||\'%y-%M-%d \'}'})"
				id="startDate" class="input-text Wdate" style="width:120px;"> -
			<input type="text"
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'endDate\')}',maxDate:'%y-%M-%d'})"
				id="endDate" class="input-text Wdate" style="width:120px;"> -->
		    <input type="text" name="" id="searchNetFlag" placeholder=" 业务标识" style="width:250px"
				class="input-text">
		    <input type="text" name="" id="searchstNetSubFlag" placeholder=" 业务子标识" style="width:250px"
				class="input-text">
		    <input type="text" name="" id="searchName" placeholder=" 业务名称" style="width:250px"
				class="input-text">
			<button name="" id="search" class="btn btn-success" onclick="search()" >
				<i class="Hui-iconfont">&#xe665;</i> 搜索
			</button>
		</div>
		
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
					<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius">
					     <i class="Hui-iconfont">&#xe6e2;</i>
							批量删除</a>
					<a class="btn btn-primary radius" onclick="add('添加','edit.jsp')" href="javascript:;">
					     <i class="Hui-iconfont">&#xe600;</i> 添加</a> 
					<!-- <a href="javascript:;" onclick="import()" class="btn btn-danger radius">
					     <i class="Hui-iconfont">&#xe6e2;</i>
							导入历史数据</a>  -->
		    </span>
		</div>
		
		<div class="mt-20">
			<table width="100%"
				class="table table-border table-bordered table-bg table-hover table-sort" id="statisticsList">
				<thead>
					<tr class="text-c">
						<th width="15" ><input name="" type="checkbox" ></th>
						<th width="100">业务标识</th>
						<th width="100">业务子标识</th>
						<th width="100">业务名称</th>
					<!-- 	<th width="90">业务总数</th> -->
						<th width="90">信息操作</th>
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
var table = $('#statisticsList').dataTable({
     "aoColumnDefs": [
      {"orderable":false,"aTargets":[0]}// 制定列不参与排序
    ],
    "order": [[ 2, "desc" ]],
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
                { "data": "stNetFlag",
                "render":function(data, type, full, meta){
                      return  setNullData(data);
                  },
                  "bSortable": false},
                { "data": "stNetSubFlag",
                   "render":function(data, type, full, meta){
                      return  setNullData(data);
                }},
                { "data": "stName",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
               /*  { "data": "nmCount",
                "render":function (data, type, full, meta){
                       return setNullData(data);
                }}, */
                {
                 "data": "stStatisticsId",
                 "render": function (data, type, full, meta) {
                        var returnInfo = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"statistics_info('"+full.stNetFlag+"->查看','/statistics/selmStatistics/info.do','"+data+"')\" href=\"javascript:;\" title=\"查看\"><i class=\"icon iconfont icon-chakan\"></i></a>";
                            returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"statistics_edit('"+full.stNetFlag+"->编辑','/statistics/selmStatistics/edit.do','"+data+"')\" href=\"javascript:;\" title=\"编辑\"><i class=\"icon iconfont icon-edit\"></i></a>";
                            returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"statistics_del(this,'"+data+"')\" href=\"javascript:;\" title=\"删除\"><i class=\"icon iconfont icon-shanchu-copy\"></i></a>";
                           // returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"update('/selmStatistics/update.do','"+data+"')\" href=\"javascript:;\" title=\"更新\"><i class=\"icon iconfont icon-edit\"></i></a>";
                        return returnInfo;
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
                },
      "bPaginate": true, //翻页功能
      "bLengthChange": true ,//改变每页显示数据数量
       "ajax": {
		    url:webRoot+'/statistics/selmStatistics/configlist.do',
		    type:"POST",
		    data: function(d){
		      var stNetFlag = $("#searchNetFlag").val();
		      d.stNetFlag = stNetFlag;
		      var stNetSubFlag = $("#searchstNetSubFlag").val();
		      d.stNetSubFlag = stNetSubFlag;
		      var stName = $("#searchName").val();
		      d.stName = stName;
		    },
		    error:function(){
		      window.parent.location.reload();
		    }
	    }
}); 

$(function(){
    $("#statisticsList").removeAttr("style"); 
});

// 查找
function search () {
    table.api().ajax.reload();
}

// 批量删除
function datadel () {
    var selectList = $(".checkchild:checked");
    var idArray = [];
    // 未选择的场合
    if (selectList.length == 0) {
        layer.msg('请选择需要删除的数据',{icon:2,time:1000});
        return;
    }
    for (var i = 0; i< selectList.length; i++){
        idArray.push(selectList[i].value);
    } 
    
    statistics_del('',idArray);
}


//添加
function add(title,url){
   var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}


// 导入历史数据
/* function import () {
    $.ajax({
            type : 'POST',
            url : webRoot+'/statistics/selmStatistics/import.do',
            dataType:'json',
            data:{'stStatisticsId':id},
            success : function(myObject) {
                 layer.msg(myObject.msg,{icon:1,time:1000});
             },
            error : function() {
                layer.msg(myObject.msg,{icon:1,time:1000});
            }
        });
     table.api().ajax.reload();
} */

// 查看
function statistics_info(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_STATISTICS_ID='+id
    });
    layer.full(index);
}
// 编辑
function statistics_edit(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_STATISTICS_ID='+id
    });
    layer.full(index);
}
// 删除
function statistics_del(obj,id){
    layer.confirm('确认要删除吗？',function(index){
         $.ajax({
            type : 'POST',
            url : webRoot+'/statistics/selmStatistics/remove.do',
            dataType:'json',
            data:{'stStatisticsId':id},
            success : function(myObject) {
                 layer.msg(myObject.msg,{icon:1,time:1000});
             },
            error : function() {
                layer.msg(myObject.msg,{icon:1,time:1000});
            }
        });
       table.api().ajax.reload();
    });
}

// 未配置数据
function setNullData(data){
    if(data == ''){
        return '<span class=\"label label-danger radius\">0</span>';
    } else {
        return data;
    }
}
</script>
</body>
</html>