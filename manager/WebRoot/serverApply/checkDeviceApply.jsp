<%@page import="com.wondersgroup.serverApply.bean.SelmDeviceApply"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubDeviceType"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib prefix="permission" uri="/sms/permission"%>
<%
	 String webRoot = AppContext.webRootPath;
    SelmDeviceApply selmDeviceApply = (SelmDeviceApply) request.getAttribute(SelmDeviceApply.SELM_DEVICE_APPLY);
    if (selmDeviceApply == null)
        selmDeviceApply = new SelmDeviceApply(); 
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
<script type="text/javascript">
    var webRoot = '<%=StringEscapeUtils.escapeJavaScript(webRoot)%>';
</script>
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
<link href="<%=webRoot%>/sms/lib/jquery-ui-1.12.1/jquery-ui.min.css"
    rel="stylesheet" />
<link href="<%=webRoot%>/sms/lib/jQuery-Timepicker/jquery-ui-timepicker-addon.min.css"
    rel="stylesheet" />	
<link href="<%=webRoot%>/sms/lib/webuploader/0.1.5/webuploader.css"
    rel="stylesheet" type="text/css" />
    <script type="text/javascript"
	src="<%=webRoot%>/resources/jquery/jquery-1.11.1.min.js"></script>  
<link  href="<%=webRoot%>/infopub/deviceinfo/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" 
	src="<%=webRoot%>/infopub/deviceinfo/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=webRoot%>/infopub/deviceinfo/css/bootstrap-select.css">
<%-- <link rel="stylesheet" href="<%=webRoot%>/serverApply/css/jquery.dataTables.min.css" /> --%>
<%-- <link rel="stylesheet" type="text/css" href="<%=webRoot%>/resources/bootstrap-3.3.5/css/bootstrap-select.css"> --%>
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>查看审批状态</title>
</head>
<body>
<div style="padding-top: 10px;font-size: 18px;padding-left: 95px;font-weight: 600;margin-bottom: -14px;"">基本信息</div>
<hr style="width: 90%;margin-left: 96px;font-weight: 600;margin-bottom: -5px;border-color: #a9a9a9;""/>
	<div class="page-container">
		<form class="form form-horizontal" id="form-device-add">
		  <input type="hidden" name="<%=SelmDeviceApply.ST_DEVICE_APPLY_ID%>"
		   id="stSampleId" value="<%=selmDeviceApply.stDeviceApplyId2Html()%>" />
          <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>申请单位：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmDeviceApply.stApplyOrganName2Html()%>"
						name="<%=SelmDeviceApply.ST_APPLY_ORGAN_NAME%>">
				</div>
				
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>保障部门：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmDeviceApply.stMainOrgName2Html()%>"
						name="<%=SelmDeviceApply.ST_MAIN_ORG_NAME%>" >
				</div>
				
			</div>
			
		  <div class="row cl">
			 	<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>联系人：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmDeviceApply.stApplyUserName2Html()%>"
						name="<%=SelmDeviceApply.ST_APPLY_USER_NAME%>" >
				</div>
				
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>手机：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmDeviceApply.stApplyUserPhone2Html()%>"
						name="<%=SelmDeviceApply.ST_APPLY_USER_PHONE%>">
				</div>				
			</div>
			
		  <div class="row cl">
			 	<label class="form-label col-xs-4 col-sm-2">
				固定电话：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmDeviceApply.stApplyUserMobile2Html()%>"
						name="<%=SelmDeviceApply.ST_APPLY_USER_MOBILE%>" >
				</div>
				<label class="form-label col-xs-4 col-sm-2">
				电子邮箱：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmDeviceApply.stApplyUserEmail2Html()%>"
						name="<%=SelmDeviceApply.ST_APPLY_USER_EMAIL%>" >
				</div>				
			</div>
			
		  <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>计划接入时间：</label>
				<div class="formControls col-xs-4 col-sm-4">
				 <input type="text"
				onfocus="WdatePicker()"
				id="startDate" class="input-text Wdate" style="width:100%;" autocomplete="off" 
				value="<%=selmDeviceApply.dtPlanCreate2Html("yyyy-MM-dd")%>"
						name="<%=SelmDeviceApply.DT_PLAN_CREATE%>" >
				</div>
			</div>
			
		  <div class="row cl">
			
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>申请情况说明：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<textarea rows="10" style="width:260%;height:100px;padding-top:1px;font-size:14px;" type="textarea" class="input-text"
						value="<%=selmDeviceApply.stDesc2Html()%>"
						name="<%=SelmDeviceApply.ST_DESC%>" placeholder="申请情况说明"><%=selmDeviceApply.stDesc2Html()%></textarea>
				</div>
			</div>
			
		  <div style="padding-top: 10px;font-size: 18px;padding-left: 79px;font-weight: 600;margin-bottom: -14px;"">设备信息</div>
		  <hr style="width: 93%;margin-left: 78px;font-weight: 600;border-color: #a9a9a9;"/>
			
		 <div class="cl pd-5 bg-1 bk-gray mt-20" style="width: 94%; margin-left: 74px;">
			<span class="l">
					<a class="btn btn-success radius" onclick="batchPass()" href="javascript:;">
					     <i class="Hui-iconfont">&#xe600;&nbsp;</i>批量通过选择</a> 
					  &nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:;" onclick="batchNoPass()" class="btn btn-danger radius">
					     <i class="Hui-iconfont">&#xe6e2;</i>
							批量不通过选择</a>
					
		    </span>
		 </div>
			
			<div class="mt-20" style="width: 94%; margin-left: 74px;">
				
				<table width="100%"
				class="table table-border table-bordered table-bg table-hover table-sort" id="typeList">
				<thead>
					<tr class="text-c">
						<th width="15" ><input name="" type="checkbox" ></th>
						<th width="40">网点名称</th>
						<th width="40">所在区</th>
						<th width="40">所在街道</th>
						<th width="40">具体地址</th>					
						<th width="40">MAC</th>
						<th width="40">IP</th>
						<th width="40">状态</th>
						<th width="40">操作</th>
					</tr>
				</thead> 
				<tbody>
				</tbody>
			</table>
				
			</div>
			
                                         
		</form>
	</div>
<script type="text/javascript"
	src="<%=webRoot%>/resources/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/resources/jquery/jquery-1.11.1.min.js"></script>  
<script type="text/javascript"
	src="<%=webRoot%>/sms/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<%-- <script type="text/javascript" 
	src="<%=webRoot%>/infopub/deviceinfo/js/bootstrap-select.js"></script> 
<script type="text/javascript" 
	src="<%=webRoot%>/infopub/deviceinfo/js/bootstrap.min.js"></script> --%>
<script type="text/javascript"
	src="<%=webRoot%>/resources/layer/2.1/layer.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/jquery.validate.min.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/messages_zh.min.js"></script>       
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/h-ui/js/H-ui.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/h-ui.admin/js/H-ui.admin.js"></script>    
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/viewer/js/template.js"></script>      
<script type="text/javascript"
     src="<%=webRoot%>/sms/lib/jquery-ui-1.12.1/jquery-ui.min.js"></script>  
<script src="<%=webRoot%>/sms/lib/jQuery-Timepicker/jquery-ui-timepicker-addon.min.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jQuery-Timepicker/i18n/jquery-ui-timepicker-zh-CN.js"></script>   
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jQuery-Timepicker/init.js"></script> 
          
	<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
var stapplyId = "<%=selmDeviceApply.stDeviceApplyId2Html()%>";
//console.log(stapplyId);
var isAdmin = false;
// 管理员权限的判定
<shiro:hasRole name="admin">
   isAdmin = true;
</shiro:hasRole>
$(function(){
	//initData();
   // 数据校验
   $("#form-device-add").validate();
   
});



//添加设备信息
function add(title, url, id){
	var index = layer.open({
		type:2,
		title:title,
		content:webRoot+url+'?stDeviceApplyId='+id
	});
	layer.full(index);
}



var table = $('#typeList').DataTable({
     "aoColumnDefs": [
      {"orderable":false,"aTargets":[0]}// 制定列不参与排序
    ],
    "order": [[ 7, "desc" ]],
    "processing" : true,  
    "serverSide" : true,  
    "searching": false,
    "paginationType":"full_numbers",
    "aoColumns": [
                {
                 "sClass": "text-center",
                 "data": "stMachineId",
                 "render": function (data, type, full, meta) {
                     return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
                    },
                  "bSortable": false
                },
                { "data": "stDeviceName",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stAreaId",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stAddressId",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stDeviceAddress",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stDeviceMac",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                 { "data": "stDeviceIp",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stMachineId",
                   "render":function(data, type, full, meta){
                       return setStatusData(full);
                }},
                {
                 "data": "stMachineId",
                 "render": function (data, type, full, meta) {
               			var returnInfo = "<a style=\"text-decoration:none;color:blue;\" class=\"ml-5\" onClick=\"deviceInfo('设备信息','/serverApply/selmDeviceAlink/info.do','"+data+"')\" href=\"javascript:;\" title=\"查看\"><u>查看</u>&nbsp;</a>";
               			if(full.nmStatus == 1){
                		 	returnInfo += "<a style=\"text-decoration:none;color:green\" class=\"ml-5\" onClick=\"devicePass('通过','/serverApply/selmDeviceAlink/devicePass.do','"+data+"')\" href=\"javascript:;\" title=\"通过\"><u>通过</>&nbsp;</a>";
                		 	returnInfo += "<a style=\"text-decoration:none;color:red\" class=\"ml-5\" onClick=\"noPassReason('不通过','/serverApply/selmDeviceApply/noPassReason.do','"+data+"')\" href=\"javascript:;\" title=\"不通过\"><u>不通过</u></a>";
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
      "bPaginate": true, //翻页功能
      "bLengthChange": true ,//改变每页显示数据数量
       "ajax": {
		    url:webRoot+'/serverApply/selmDeviceAlink/list.do',
		    type:"POST",
		    data: function(d){
		   	 var stDeviceApplyId = stapplyId;
		      d.stDeviceApplyId = stDeviceApplyId;
		      
		    },
		    error:function(){
		      window.parent.location.reload();
		    }
	    }
});

function setStatusData(full){
	var returnInfo = "";
   	if(full.nmStatus ==2 ){
	 	returnInfo += "<a style=\"text-decoration:none;color:green\" class=\"ml-5\"  href=\"javascript:;\" title=\"通过\">通过&nbsp;&nbsp;</a>";
	}else if(full.nmStatus ==3){
	 	returnInfo += "<a style=\"text-decoration:none;color:red\" class=\"ml-5\"  href=\"javascript:;\" title=\"不通过\">不通过</a>";
	}else{
		returnInfo += "<a style=\"text-decoration:none;color:blue\" class=\"ml-5\"  href=\"javascript:;\" title=\"待审核\">待审核</a>";
	}
	return returnInfo;
}


function setNullData(data){
    if(data == ''){
        return '<span class=\"label label-danger radius\"></span>';
    } else {
        return data;
    }
}

$(function(){
    $("#typeList").removeAttr("style"); 
});

// 数据提交     
$.validator.setDefaults({
	submitHandler : function() {
		// 提交数据
		article_save_submit();
		//removeSession();
	}
});

// 提交内容
function article_save_submit() {
	var formData = new FormData($("#form-device-add")[0]);
	$.ajax({
		type : "POST",
		url : webRoot + "/serverApply/selmDeviceApply/save.do?stDeviceApplyId="+stapplyId+"&type=apply",
		data: $('#form-device-add').serialize(),
		error : function(request) {
			layer.msg("服务申请开通添加失败", {
				icon : 1,
				time : 1000
			});
		},
		success : function(data) {
			layer.msg("服务申请开通添加成功", {
				icon : 1,
				time : 1000
			});
		}
	});
	setTimeout(function() {
		window.parent.location.reload();
	}, 1000);
}
	
	


//删除设备
function removeDevice(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?stMachineId='+id+'&stApplyId='+stapplyId
    });
    layer.full(index);
}

//查看设备
function deviceInfo(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?stMachineId='+id+'&stApplyId='+stapplyId
    });
    layer.full(index);
}

function devicePass(title,url,id){
		$.ajax({
			type:"POST",
			url:webRoot+url+'?stMachineId='+id+'&stDeviceApplyId='+stapplyId,
			dataType:'json',
			success : function(myObject) {
                 layer.msg(myObject.msg,{icon:1,time:1000});
                 table.ajax.reload();
             },
            error : function() {
                layer.msg(myObject.msg,{icon:1,time:1000});
                table.ajax.reload();
            }
		});
		
}

//不通过原因
function noPassReason(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?stMachineId='+id+'&stDeviceApplyId='+stapplyId,
    });
    layer.full(index);
}

function batchPass() {
		var selectList = $(".checkchild:checked");
		var idArray = [];
		// 未选择的场合
		if (selectList.length == 0) {
   			layer.msg('请选择需要授权的数据',{icon:2,time:1000});
  			 return;
 		}
 		for (var i = 0; i< selectList.length; i++){
   			idArray.push(selectList[i].value);
		} 
		console.log('idArray:'+idArray);
	 	device_check('','/serverApply/selmDeviceAlink/batchPass.do',idArray);
}

function batchNoPass() {
		var selectList = $(".checkchild:checked");
		var idArray = [];
		// 未选择的场合
		if (selectList.length == 0) {
   			layer.msg('请选择需要授权的数据',{icon:2,time:1000});
  			 return;
 		}
 		for (var i = 0; i< selectList.length; i++){
   			idArray.push(selectList[i].value);
		} 
	 	device_check('','/serverApply/selmDeviceAlink/batchNoPass.do',idArray);
}
			
function device_check(title,url,id){
		$.ajax({
			url : webRoot + url,
			type : "POST",
			data : {
				stDeviceApplyId : stapplyId,
				stMachineIdArray : id,
			},
			success : function(result) {
				layer.msg("审核成功", {
					icon : 1,
					time : 1000
				});
			},
			error : function() {
				layer.msg("审核失败", {
					icon : 1,
					time : 1000
				});
				
				
			}
		});
		setTimeout(function() {
			table.ajax.reload();
		}, 1000);
		
}
</script>
</body>
</html>