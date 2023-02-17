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
		服务管理<span class="c-gray en">&gt;</span>服务接入申请 <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
	</nav>
	<div class="page-container">
		
		<div class="text-c" style="text-align: center;">
			&nbsp;&nbsp;&nbsp; 申请单位：<input type="text" name="" id="searchOrganName" placeholder="申请单位" style="width:250px"
				class="input-text">
		   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 申请状态：<select type="text" name="" id="searchNameStatus" placeholder="申请状态" style="width:250px"
				class="input-text">
					<option></option>
					<option value=0>未提交</option>
					<option value=1>待审核</option>
					<option value=2>已完成</option>
					<option value=3>部分待审核</option>
				</select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				创建日期： <input type="text"
				onfocus="WdatePicker()"
				id="startDate" class="input-text Wdate" style="width:250px;" autocomplete="off">
				
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
	
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
					<a class="btn btn-primary radius" onclick="device_add('服务申请添加','/serverApply/selmServerApply/addServerApply.do')" href="javascript:;">
					     <i class="Hui-iconfont">&#xe600;</i>发起申请</a> 
		    </span>
		</div>
		
		<div class="mt-20">
			<table width="100%"
				class="table table-border table-bordered table-bg table-hover table-sort" id="typeList">
				<thead>
					<tr class="text-c">
						<th width="5%" ><input name="" type="checkbox" ></th>
						<th width="15%">申请单号</th>
						<th width="10%">申请单位名称</th>
						<th width="8%">联系人</th>
						<th width="9%">手机</th>
						<th width="13%">电子邮箱</th>
						<th width="10%">计划上线时间</th>
						<th width="10%">创建时间</th>
						<th width="10%">状态</th>
						<th width="10%">操作</th>
					</tr>
				</thead> 
				<tbody>
				</tbody>
			</table>
		</div>
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
    "order": [[ 8, "desc" ]],
    "processing" : true,  
    "serverSide" : true,  
    "searching": false,
    "paginationType":"full_numbers",
    "aoColumns": [
                {
                 "sClass": "text-center",
                 "data": "stApplyId",
                 "render": function (data, type, full, meta) {
                     return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
                    },
                  "bSortable": false
                },
                { "data": "stApplyId",
                   "render":function(data, type, full, meta){
                       return subData(data);
                       //return setNullData(data);
                }},
                { "data": "stApplyOrganName",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stServerUserName",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stServerUserPhone",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stServerUserEmail",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                
                 { "data": "dtUpCreate",
                   "render":function(data, type, full, meta){
                       return setDateFormat(data);
                }},
                { "data": "dtCreate",
                "render":function (data, type, full, meta){
                       return setDateTime(data);
                }},
                { "data": "nmStatus",
                "render":function (data, type, full, meta){
                       return setNullDataStatus(data);
                }},
                {
                 "data": "stApplyId",
                 "render": function (data, type, full, meta) {
                  		 	if(full.nmStatus==0){
	                  		 	var returnInfo = "<a id="+data+" style=\"text-decoration:none;color:blue;\" class=\"ml-5\"  onClick=\"serverApply_submit(this,'"+data+"')\" href=\"javascript:;\" title=\"提交申请\"><u>提交</u>&nbsp;</a>";
	                            returnInfo += "<a id="+data+"eidt"+" style=\"text-decoration:none;color:blue;\" class=\"ml-5\" onClick=\"serverApply_edit('"+full.stApplyOrganName+"->编辑','/serverApply/selmServerApply/edit.do','"+data+"')\" href=\"javascript:;\" title=\"编辑\"><u>编辑</u>&nbsp;</a>";
	                            returnInfo += "<a style=\"text-decoration:none;color:red;\" class=\"ml-5\" onClick=\"serverApply_del(this,'"+data+"')\" href=\"javascript:;\" title=\"删除\"><u>删除</u></a>";
	                  		 }else{
                  				var returnInfo = "<a style=\"text-decoration:none;color:blue;\" class=\"ml-5\" onClick=\"serverApply_info('"+full.stApplyOrganName+"->查看','/serverApply/selmServerApply/info.do','"+data+"')\" href=\"javascript:;\" title=\"查看\"><u>查看</u></a>";
	                  		 }
                            
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
                    $(row).children('td').eq(10).attr('style', 'text-align: center;');
                    $(row).children('td').eq(11).attr('style', 'text-align: center;');
                    $(row).children('td').eq(12).attr('style', 'text-align: center;');
                    $(row).children('td').eq(13).attr('style', 'text-align: center;');
                    $(row).children('td').eq(14).attr('style', 'text-align: center;');
                    $(row).children('td').eq(15).attr('style', 'text-align: center;');
                    $(row).children('td').eq(16).attr('style', 'text-align: center;');
                         
                },
      "bPaginate": true, //翻页功能
      "bLengthChange": true ,//改变每页显示数据数量
       "ajax": {
		    url:webRoot+'/serverApply/selmServerApply/list.do',
		    type:"POST",
		    data: function(d){
		   	 var stServerOragnName = $("#searchOrganName").val();
		      d.stServerOragnName = stServerOragnName;
		       var searchNameStatus = $("#searchNameStatus").val();
		      d.searchNameStatus = searchNameStatus;
	          var startDate = $("#startDate").val();
	          d.startDate = startDate;
              
		    },
		    error:function(){
		       table.api().ajax.reload();
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

//重置
function reset () {
	$("#searchOrganName").val('');
	$("#searchNameStatus").val('');
	$("#startDate").val('');
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
    
    deviceAddress_del('',idArray);
}
// 添加
function device_add(title,url){
	sessionStorage.clear();
	removeSession();
    var index = layer.open({
        type: 2,
        title: title,
         content: webRoot+url
    });
    layer.full(index);
}

// 查看
function serverApply_info(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_APPLY_ID='+id
    });
    layer.full(index);
}
// 编辑
function serverApply_edit(title,url,id){
	$.ajax({
			type:"POST",
			url:webRoot+"/serverApply/selmServerApply/edit.do?stServerApplyIdSession=ff",
			error:function(){
			},
			success:function(){
			}
		});
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_APPLY_ID='+id
    });
    layer.full(index);
}
// 提交
function serverApply_submit(obj,id){
    layer.confirm('确认要提交吗？',function(index){
         $.ajax({
            type : 'POST',
            url : webRoot+'/serverApply/selmServerApply/saveSubmit.do',
            dataType:'json',
            data:{'stApplyId':id},
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
// 删除
function serverApply_del(obj,id){
    layer.confirm('确认要删除吗？',function(index){
         $.ajax({
            type : 'POST',
            url : webRoot+'/serverApply/selmServerApply/remove.do',
            dataType:'json',
            data:{'stApplyId':id},
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

function setNullDataStatus(data){
//console.log(data);
    if(data == '0'){
        return '未提交';
    } else if(data == '1') {
        return '待审核';
    }else if(data == '2') {
        return '审核完成';
    }else if(data == '3') {
        return '部分待审核';
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
   
  //申请说明     
function subData(data){
	if(data==''){
		return data
	}else{
		if(data.length>8){
			var data = data.substring(0,8);
			//return data+"...";
			return data;
		}
	}
	return data;
}

function removeSession(){
		$.ajax({
			type:"POST",
			url:webRoot+"/serverApply/selmServerApply/addServerApply.do?stServerApplyIdSession=ff",
			error:function(){
			},
			success:function(){
			}
		});
	}
</script>
</body>
</html>