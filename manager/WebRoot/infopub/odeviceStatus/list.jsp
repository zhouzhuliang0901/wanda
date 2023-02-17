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
<title>设备管理 </title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		设备管理 <span class="c-gray en">&gt;</span>外设状态 <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
	</nav>
	<div class="page-container">
		
		<div class="text-c">
			<!-- 日期范围： <input type="text"
				onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'startDate\')||\'%y-%M-%d \'}'})"
				id="startDate" class="input-text Wdate" style="width:120px;" autocomplete="off"> -
			<input type="text"
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'endDate\')}',maxDate:'%y-%M-%d'})"
				id="endDate" class="input-text Wdate" style="width:120px;" autocomplete="off"> -->
		    <input type="text" name="" id="searchName" placeholder=" 外设标识" style="width:250px"
				class="input-text">
		   <!--  <input type="text" name="" id="searchID" placeholder=" 设备ID" style="width:250px"
				class="input-text"> -->
			<button name="" id="search" class="btn btn-success" onclick="search()" >
				<i class="Hui-iconfont">&#xe665;</i> 搜索
			</button>
		</div>
		
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
					<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius">
					     <i class="Hui-iconfont">&#xe6e2;</i>
							批量删除</a>
					<a class="btn btn-primary radius" onclick="device_add('添加外设','edit.jsp')" href="javascript:;">
					     <i class="Hui-iconfont">&#xe600;</i> 添加</a> 
		    </span>
		</div>
		
		<div class="mt-20">
			<table width="100%"
				class="table table-border table-bordered table-bg table-hover table-sort" id="typeList">
				<thead>
					<tr class="text-c">
						<th width="15" ><input name="" type="checkbox" ></th>
						<th width="80">设备名称</th>
						<th width="80">外设名称</th>
						<th width="80">外设标识</th>
						<!-- <th width="40">是否异常</th> -->
						<!-- <th width="80">异常原因</th>
						<th width="40">是否已通知</th>
						<th width="40">当前总量</th>
						<th width="40">当前剩余量</th>
						<th width="40">历史总量</th>
						<th width="40">当前成功次数</th>
						<th width="40">当前失败次数</th>
						<th width="40">更新时间</th> -->
						<th width="60">信息操作</th>										
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
var table = $('#typeList').dataTable({
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
                 "data": "stOutDeviceResultId",
                 "render": function (data, type, full, meta) {
                     return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
                    },
                  "bSortable": false
                },
                { "data": "stDeviceId",
                "render":function(data, type, full, meta){
                      return  setNullData(data);
                  }},
                { "data": "stExt1",
                   "render":function(data, type, full, meta){
                      return  setNullData(data);
                }},
                { "data": "stOutDeviceCode",
                   "render":function(data, type, full, meta){
                       return setNullData(data);;
                }},
               /*  { "data": "nmException",
                   "render":function(data, type, full, meta){
                       return setData(data) ;
                }}, */
              /*   { "data": "stCause",
                   "render":function(data, type, full, meta){
                       return setCause(data);
                }},
                { "data": "nmNotice",
                   "render":function(data, type, full, meta){
                       return setnmNotice(data);
                }},
                { "data": "nmTotal",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "nmRemain",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "nmHisTotal",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "nmHisStotal",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "nmHisFtotal",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "dtUpdate",
                "render":function (data, type, full, meta){
                       return setDateTime(data);
                }}, */
                {
                 "data": "stOutDeviceResultId",
                 "render": function (data, type, full, meta) {
                        var returnInfo = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_info('"+full.stOutDeviceCode+"->查看','/infopub/odeviceStatus/info.do','"+data+"')\" href=\"javascript:;\" title=\"查看\"><i class=\"icon iconfont icon-chakan\"></i></a>";
                            returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_edit('"+full.stOutDeviceCode+"->编辑','/infopub/odeviceStatus/edit.do','"+data+"')\" href=\"javascript:;\" title=\"编辑\"><i class=\"icon iconfont icon-edit\"></i></a>";
                            returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_del(this,'"+data+"')\" href=\"javascript:;\" title=\"删除\"><i class=\"icon iconfont icon-shanchu-copy\"></i></a>";
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
                    /* $(row).children('td').eq(6).attr('style', 'text-align: center;');
                    $(row).children('td').eq(7).attr('style', 'text-align: center;');
                    $(row).children('td').eq(8).attr('style', 'text-align: center;');
                    $(row).children('td').eq(9).attr('style', 'text-align: center;');
                    $(row).children('td').eq(10).attr('style', 'text-align: center;');
                    $(row).children('td').eq(11).attr('style', 'text-align: center;');
                    $(row).children('td').eq(12).attr('style', 'text-align: center;'); */
                },
      "bPaginate": true, //翻页功能
      "bLengthChange": true ,//改变每页显示数据数量
       "ajax": {
		    url:webRoot+'/infopub/odeviceStatus/list.do',
		    type:"POST",
		    data: function(d){
		      var stOutDeviceCode = $("#searchName").val();
		      d.stOutDeviceCode = stOutDeviceCode;
		      var stDeviceId = $("#searchID").val();
		      d.stDeviceId = stDeviceId;
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
    $("#typeList").removeAttr("style"); 
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
    
    device_del('',idArray);
}
// 添加
function device_add(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}

// 查看
function device_info(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_OUT_DEVICE_RESULT_ID='+id
    });
    layer.full(index);
}
// 编辑
function device_edit(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_OUT_DEVICE_RESULT_ID='+id
    });
    layer.full(index);
}
// 删除
function device_del(obj,id){
    layer.confirm('确认要删除吗？',function(index){
         $.ajax({
            type : 'POST',
            url : webRoot+'/infopub/odeviceStatus/remove.do',
            dataType:'json',
            data:{'stOutDeviceResultId':id},
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
        return '<span class=\"label label-danger radius\"></span>';
    } else {
        return data;
    }
}
//是否异常
function setData(data){
    if(data == '0'){
    	return '<span style="color:green">正常</span>';
        //return '<i class=\"iconfont icon-yichang\"></i>';
    } else {
    	return '<span class=\"label label-danger radius\">异常</span>';
        //return '<i class=\"iconfont icon-yichang\" style="color:red"></i>';
    }
}
//是否通知
function setnmNotice(data){
    if(data == '0'){
        return '<span>未通知</span>';
    } else {
        return '<span class=\"label label-danger radius\">已通知</span>';
    }
}
//是否通知
function setCause(data){
    if(data == ''){
        //return '<span>无</span>';
        return '<span class=\"label label-danger radius\">无</span>';
    } else {
    	return data;
        //return '<span class=\"label label-danger radius\"></span>';
    }
}
</script>
</body>
</html>