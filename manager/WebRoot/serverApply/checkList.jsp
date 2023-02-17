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
<%--    href="https://at.alicdn.com/t/font_1743964_iij8bsmpiw.css" />--%>
    
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>服务开通申请 </title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		服务管理<span class="c-gray en">&gt;</span>服务接入审核 <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
	</nav>
	<div class="page-container">
		
		<div class="text-c" style="text-align: center;">
			事项名称：<input type="text" name="" id="searchItemName" placeholder="事项名称" style="width:250px"
				class="input-text">
		   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 事项编码：<input type="text" name="" id="searchItemCode" placeholder="事项编码" style="width:250px"
				class="input-text">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span id="organ">
				 部门名称：<input type="text" name="" id="searchOrganName" placeholder="部门名称" style="width:250px"
				class="input-text">
				</span>
				<div class="text-c" style="text-align: center;"><br>
			&nbsp;&nbsp;&nbsp;<button name="" id="search" class="btn btn-primary radius" onclick="search()" >
				<i class="Hui-iconfont">&#xe665;</i> &nbsp;搜索&nbsp;
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button name="reset" id="search" class="btn btn-default radius" onclick="reset()" >
				<i class="Hui-iconfont">&#xe68f;</i>&nbsp;重置&nbsp;
			</button></div>
			
		</div>
		<br><hr>
		
		
		<div class="mt-20">
			<table width="100%"
				class="table table-border table-bordered table-bg table-hover table-sort" id="typeList">
				<thead>
					<tr class="text-c">
						<th width="15" ><input name="" type="checkbox" ></th>
						<!-- <th width="80">设备ID</th> -->
						<th width="40">申请单位名称</th>
						<th width="80">事项名称</th>
						<th width="80">事项编码</th>
						<th width="40">申请设备数</th>
						<th width="40">未审核设备数</th>
						<th width="40">更新时间</th>
						<!-- <th width="40">计划上线时间</th>
						<th width="60">创建时间</th> -->
						<!-- <th width="80">状态</th> -->
						<th width="40">操作</th>
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
var user= "<%= (String)request.getSession().getAttribute("loginName")%>";
var userAreaId = '<%= (Object)request.getSession().getAttribute("smsUserStAreaId")%>';
var table = $('#typeList').dataTable({
     "aoColumnDefs": [
      {"orderable":false,"aTargets":[0]}// 制定列不参与排序
    ],
    "order": [[ 6, "desc" ]],
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
                { "data": "stExt1",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stMainName",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stItemNo",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stExt2",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stExt3",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "dtUpdate",
                   "render":function(data, type, full, meta){
                      return setDateTime(data);
                }},
                {
                 "data": "stItemId",
                 "render": function (data, type, full, meta) {
                        var returnInfo = "<a style=\"text-decoration:none;color:blue;\" class=\"ml-5\" onClick=\"server_info('设备审批','/serverApply/selmServerApply/checkItem.do','"+data+"')\" href=\"javascript:;\" title=\"设备审批\"><u>审核</u>&nbsp;</a>";                   
                        	returnInfo += "<a style=\"text-decoration:none;color:blue;\" class=\"ml-5\" onClick=\"server_info('审批记录','/serverApply/selmServerApply/checkRecords.do','"+data+"')\" href=\"javascript:;\" title=\"审核记录\"><u>审核记录</u></a>";                   
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
                    $(row).children('td').eq(5).attr('style', 'text-align: center;');
                    $(row).children('td').eq(6).attr('style', 'text-align: center;');
                    $(row).children('td').eq(7).attr('style', 'text-align: center;');
                    $(row).children('td').eq(8).attr('style', 'text-align: center;');
                    $(row).children('td').eq(9).attr('style', 'text-align: center;');
                },
      "bPaginate": true, //翻页功能
      "bLengthChange": true ,//改变每页显示数据数量
       "ajax": {
		   	url:webRoot+'/serverApply/selmServerApply/applyItemlist.do',
		    type:"POST",
		    data: function(d){
		     	var searchItemName = $("#searchItemName").val();
		      	d.searchItemName = searchItemName;
		       	var searchItemCode = $("#searchItemCode").val();
		      	d.searchItemCode = searchItemCode;
		      	var searchOrganName = $("#searchOrganName").val();
		      	d.searchOrganName = searchOrganName;
		      	d.user = user;
		     	
		    },
		   /*  success:function(){
		    	$("#searchItemName").val('');
		    	$("#searchItemCode").val('');
		    	$("#searchOrganName").val('');
		    }, */
		    error:function(){
		      window.parent.location.reload();
		    }
	    }
}); 

$(function(){
    $("#typeList").removeAttr("style"); 
});

$(function(){
	if(user=='admin'){
		$("#organ").show();
	}else{
		$("#organ").hide();
	}
});

// 查找
function search () {
    table.api().ajax.reload();
}

// 查看
function server_info(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_ITEM_ID='+id,
    });
    layer.full(index);
}
function server_down(url1,id){
var url = webRoot+"/serverApply/selmServerApply/download.do?stAttachId="+id;
location.href=url;
}

// 未配置数据
function setNullData(data){
    if(data == ''){
        return '<span class=\"label label-danger radius\"></span>';
    } else {
        return data;
    }
}
// 未配置数据
function setNullDataStatus(data){
    if(data == '0'){
        return '已保存';
    } else if(data == '1') {
        return '全部通过';
    }else if(data == '2') {
        return '部分通过';
    }else if(data == '3') {
        return '全部不通过';
    }else{
    	return '';
    }
}
function setDateFormat(data){
 return (new Date(parseFloat(data.time))).format("yyyy-MM-dd");
} 
Date.prototype.format = function(format) {
            var o = {
                "M+" : this.getMonth() + 1,// month
                "d+" : this.getDate(),// day
                "h+" : this.getHours(),// hour
                "m+" : this.getMinutes(),// minute
                "s+" : this.getSeconds(),// second
                "q+" : Math.floor((this.getMonth() + 3) / 3),// quarter
                "S" : this.getMilliseconds()
            // millisecond
            };
            if (/(y+)/.test(format) || /(Y+)/.test(format)) {
                format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            }
            for ( var k in o) {
                if (new RegExp("(" + k + ")").test(format)) {
                    format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
                }
            }
            return format;
        };
 function subData(data){
	if(data==''){
		return data
	}else{
		if(data.length>8){
			var data = data.substring(0,8);
			return data+"...";
		}
	}
	return data;
}

//重置
function reset () {
	$("#searchItemName").val('');
	$("#searchItemCode").val('');
	$("#searchOrganName").val('');
    table.api().ajax.reload();
}
</script>
</body>
</html>