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
<title>?????????????????? </title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> ?????? <span class="c-gray en">&gt;</span>
		????????????<span class="c-gray en">&gt;</span>?????????????????? <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="??????"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
	</nav>
	<div class="page-container">
		
		<div class="text-c" style="text-align: center;">
			&nbsp;&nbsp;&nbsp; ?????????????????????<input type="text" name="" id="searchOrganName" placeholder="??????????????????" style="width:250px"
				class="input-text">
		   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ???????????????
		   <select id="applyState" placeholder="????????????" style="width:250px" class="input-text">
		   		<option value=""></option>
		   		<option value=0>?????????</option>
		   		<option value=1>?????????</option>
		   		<option value=3>????????????</option>
		   		<option value=2>?????????</option>
		   </select>
				
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;???????????????<input type="text"
				onfocus="WdatePicker()"
				id="startDate" class="input-text Wdate" style="width:250px;" autocomplete="off">
			<div class="text-c" style="text-align: center;"><br>
			&nbsp;&nbsp;&nbsp;
			
			<button name="" id="search" class="btn btn-primary radius" onclick="search()" >
				<i class="Hui-iconfont">&#xe665;</i> &nbsp;??????&nbsp;
			</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button name="reset" id="reset" class="btn btn-default radius" onclick="reset()" >
				<i class="Hui-iconfont">&#xe68f;</i> &nbsp;??????&nbsp;
			</button></div>
			
		</div>
		<br><hr>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
					&nbsp;&nbsp;<a class="btn btn-primary radius" onclick="device_add('??????????????????','/serverApply/selmDeviceApply/addDeviceApply.do')" href="javascript:;">
					     <i class="Hui-iconfont">&#xe600;</i> ????????????</a> 
					  
		    </span>
		</div>
		
		<div class="mt-20">
			<table width="100%"
				class="table table-border table-bordered table-bg table-hover table-sort" id="typeList">
				<thead>
					<tr class="text-c">
						<th width="6%" ><input name="" type="checkbox" ></th>
						<th width="16%">????????????</th>
						<th width="11%">????????????</th>
						<th width="11%">?????????</th>
						<th width="11%">????????????</th>					
						<th width="11%">??????????????????</th>
						<th width="11%">????????????</th>
						<th width="11%">????????????</th>
						<th width="11%">??????</th>
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
var user= "<%= (String)request.getSession().getAttribute("loginName")%>";
var permission = "<%= (Object)request.getSession().getAttribute("??????")%>";
var table = $('#typeList').dataTable({
     "aoColumnDefs": [
      {"orderable":false,"aTargets":[0]}// ????????????????????????
    ],
    "order": [[ 7, "desc" ]],
    "processing" : true,  
    "serverSide" : true,  
    "searching": false,
    "paginationType":"full_numbers",
    "aoColumns": [
                {
                 "sClass": "text-center",
                 "data": "stDeviceApplyId",
                 "render": function (data, type, full, meta) {
                     return '<input type="checkbox"  class="checkchild" name="checks" value="' + data + '" />';
                    },
                  "bSortable": false
                },
                { "data": "stDeviceApplyId",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stApplyOrganName",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stApplyUserName",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stApplyUserPhone",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "dtPlanCreate",
                   "render":function(data, type, full, meta){
                       return setDateFormat(data);
                }},
                 { "data": "dtCreate",
                   "render":function(data, type, full, meta){
                       return setDateTime(data);
                }},
                { "data": "nmStatus",
                "render":function (data, type, full, meta){
                       return setNullDataStatus(data);
                }},
                {
                 "data": "stDeviceApplyId",
                 "render": function (data, type, full, meta) {
                         if(full.nmStatus==0){
	                  		 	var returnInfo = "<a id="+data+" style=\"text-decoration:none; color:blue;\" class=\"ml-5\"  onClick=\"serverApply_submit(this,'"+data+"')\" href=\"javascript:;\" title=\"????????????\"><u>??????</u>&nbsp;</a>";
	                            returnInfo += "<a id="+data+"eidt"+" style=\"text-decoration:none; color:blue;\" class=\"ml-5\" onClick=\"serverApply_edit('"+full.stApplyOrganName+"->??????','/serverApply/selmDeviceApply/edit.do','"+data+"')\" href=\"javascript:;\" title=\"????????????\"><u>??????</u>&nbsp;</a>";
	                            returnInfo += "<a style=\"text-decoration:none; color:blue;\" class=\"ml-5\" onClick=\"serverApply_del(this,'"+data+"')\" href=\"javascript:;\" title=\"??????\"><u>??????</u>&nbsp;</a>";
                  		 }else{
                 				var returnInfo = "<a style=\"text-decoration:none; color:blue; \" class=\"ml-5\" onClick=\"serverApply_info('"+full.stApplyOrganName+"->??????','/serverApply/selmDeviceApply/info.do','"+data+"')\" href=\"javascript:;\" title=\"??????\"><u>??????</u></a>";
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
                   
                         
                },
      "bPaginate": true, //????????????
      "bLengthChange": true ,//??????????????????????????????
       "ajax": {
		    url:webRoot+'/serverApply/selmDeviceApply/list.do',
		    type:"POST",
		    data: function(d){
		   	 var stDeviceOragnName = $("#searchOrganName").val();
		      d.stDeviceOragnName = stDeviceOragnName;
		       var stDeviceNmstatus = $("#applyState").val();
		      d.stDeviceNmstatus = stDeviceNmstatus;
	          var startDate = $("#startDate").val();
	          d.startDate = startDate;
              d.type = 'apply';
              d.permission = permission;
              d.user = user;
              d.areaId = areaId;
              
		    },
		    error:function(){
		      window.parent.location.reload();
		    }
	    }
}); 

$(function(){
    $("#typeList").removeAttr("style"); 
});

// ??????
function search () {
    table.api().ajax.reload();
}

// ??????
function reset () {
	$("#searchOrganName").val("");
    $("#applyState").val("");
    $("#startDate").val(""); 
    table.api().ajax.reload();
}

// ????????????
function delbatchData () {
    var selectList = $(".checkchild:checked");
    var idArray = [];
    // ??????????????????
    if (selectList.length == 0) {
        layer.msg('??????????????????????????????',{icon:2,time:1000});
        return;
    }
    for (var i = 0; i< selectList.length; i++){
        idArray.push(selectList[i].value);
    } 
    
    project_del('',idArray);
}

/*??????*/
function project_del(obj,id){
    layer.confirm('??????????????????????????????',function(index){
         $.ajax({
            type : "POST",
            url : webRoot+'/serverApply/selmDeviceApply/batchDelete.do',
            dataType:"json",
            data:{"stDeviceApplyId":id},
            success : function(myObject) {
                 layer.msg(myObject.msg,{icon:1,time:1000});
             },
            error : function() {
                layer.msg(myObject.msg,{icon:2,time:1000});
            }
        });
       table.api().ajax.reload();
    });
}

// ??????
function device_add(title,url){
	removeSession();
	sessionStorage.clear();
    var index = layer.open({
        type: 2,
        title: title,
         content: webRoot+url
    });
    layer.full(index);
}

function removeSession(){
		$.ajax({
			type:"POST",
			url:webRoot+"/serverApply/selmDeviceApply/addDeviceApply.do?stDeviceApplyIdSession=ff",
			error:function(){
			},
			success:function(){
			}
		});
	}
// ??????
function serverApply_info(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_DEVICE_APPLY_ID='+id
    });
    layer.full(index);
}


// ??????
function serverApply_edit(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_DEVICE_APPLY_ID='+id
    });
    layer.full(index);
}

// ??????
function serverApply_submit(obj,id){
    layer.confirm('?????????????????????',function(index){
         $.ajax({
            type : 'POST',
            url : webRoot+'/serverApply/selmDeviceApply/saveSubmit.do',
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
// ??????
function serverApply_del(obj,id){
    layer.confirm('?????????????????????',function(index){
         $.ajax({
            type : 'POST',
            url : webRoot+'/serverApply/selmDeviceApply/remove.do',
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
// ???????????????
function setNullData(data){
    if(data == ''){
        return '<span class=\"label label-danger radius\"></span>';
    } else {
        return data;
    }
}
// ???????????????
function setNullDataStatus(data){
    if(data == '0'){
        return '?????????';
    } else if(data == '1') {
        return '?????????';
    }else if(data == '2') {
        return '????????????';
    }else if(data == '3') {
        return '????????????';
    }else{
    	return '';
    }
}
function setDateFormat(data){
	if(data != undefined){
 		return (new Date(parseFloat(data.time))).format("yyyy-MM-dd");
	}else{
		return "";
	}
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
</script>
</body>
</html>