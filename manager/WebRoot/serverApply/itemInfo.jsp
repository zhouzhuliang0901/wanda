<%@page import="com.wondersgroup.serverApply.bean.SelmServerApply"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubDeviceInfo"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="coral.base.app.AppContext"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="permission" uri="/sms/permission"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	 String webRoot = AppContext.webRootPath;
    SelmServerApply selmServerApply = (SelmServerApply) request.getAttribute(SelmServerApply.SELM_SERVER_APPLY);
    if (selmServerApply == null)
        selmServerApply = new SelmServerApply(); 
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
<script type="text/javascript">var webRoot = '<%=StringEscapeUtils.escapeJavaScript(webRoot)%>';
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
<link rel="stylesheet" type="text/css"
	href="<%=webRoot%>/sms/lib/iconfont/iconfont.css" />

<%--<link rel="stylesheet" type="text/css"--%>
<%--	href="https://at.alicdn.com/t/font_1743964_q4n8sjtxs3.css" />--%>

<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>设备管理</title>
</head>
<body>
	<div style="padding-top: 10px;font-size: 18px;padding-left: 95px;font-weight: 600;">基本信息</div>
<hr style="width: 90%;margin-left: 96px;font-weight: 600;"/>
<div class="page-container">
		<form class="form form-horizontal" id="form-device-add">
		 <input type="hidden" name="<%=SelmServerApply.ST_APPLY_ID%>"
		   id="stSampleId" value="<%=selmServerApply.stApplyId2Html()%>" />    
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				申请单位：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stApplyOrganName2Html() %>"
						name="<%=SelmServerApply.ST_APPLY_ORGAN_NAME%>" disabled="disabled">
				</div>
					<label class="form-label col-xs-4 col-sm-2">
				联系人：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stServerUserName2Html() %>"
						name="<%=SelmServerApply.ST_SERVER_USER_NAME%>" disabled="disabled">
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				手机：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stServerUserPhone2Html() %>"
						name="<%=SelmServerApply.ST_SERVER_USER_PHONE%>" disabled="disabled">
				</div>
					<label class="form-label col-xs-4 col-sm-2">
				固定电话：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stServerUserMobile2Html() %>"
						name="<%=SelmServerApply.ST_SERVER_USER_MOBILE%>" disabled="disabled">
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				电子邮箱：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stServerUserEmail2Html() %>"
						name="<%=SelmServerApply.ST_SERVER_USER_EMAIL%>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">
				计划上线时间：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.dtUpCreate2Html("yyyy-MM-dd")%>"
						name="<%=SelmServerApply.DT_UP_CREATE%>" disabled="disabled">
				</div>
					
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				申请情况说明：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<textarea rows="10" style="width:100%;height:100px;padding-top:1px;font-size:14px;" type="text" class="input-text"
						value="<%=selmServerApply.stServerContent2Html() %>"
						name="<%=SelmServerApply.ST_SERVER_CONTENT%>" disabled="disabled"><%=selmServerApply.stServerContent2Html() %></textarea>
				</div>
					<label class="form-label col-xs-4 col-sm-2">
				备注：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<textarea rows="10" style="width:100%;height:100px;padding-top:1px;font-size:14px;" type="text" class="input-text"
						value="<%=selmServerApply.stExt12Html() %>"
						name="<%=SelmServerApply.ST_EXT1%>" disabled="disabled"><%=selmServerApply.stExt12Html() %></textarea>
				</div>
				</div>
		</form>
	</div>
	<!-- <div style="text-align: center;font-size: 18px;">地址信息</div> -->
	<div style="font-size: 18px;padding-left: 95px;font-weight: 600;">设备信息</div>
<hr style="width: 90%;margin-left: 96px;font-weight: 600;"/>
	<div class="page-container">
		<form class="form form-horizontal" id="form-device-add">
		 <input type="hidden" name="<%=SelmServerApply.ST_APPLY_ID%>"
		   id="stSampleId" value="<%=selmServerApply.stApplyId2Html()%>" />    
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				所在区：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stServerDestrict2Html() %>"
						name="<%=SelmServerApply.ST_SERVER_DESTRICT%>" disabled="disabled">
				</div>
					<label class="form-label col-xs-4 col-sm-2">
				点位名称：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stPointName2Html() %>"
						name="<%=SelmServerApply.ST_POINT_NAME%>" disabled="disabled">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				摆放地址：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stPutAddress2Html() %>"
						name="<%=SelmServerApply.ST_PUT_ADDRESS%>" disabled="disabled">
				</div>
					<label class="form-label col-xs-4 col-sm-2">
				承建厂商：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stBuildCompany2Html() %>"
						name="<%=SelmServerApply.ST_BUILD_COMPANY%>" disabled="disabled">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				预计摆放台数：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stPutNumber2Html() %>"
						name="<%=SelmServerApply.ST_PUT_NUMBER%>" disabled="disabled">
				</div>
					<label class="form-label col-xs-4 col-sm-2">
				现场网络环境：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
                    <%if (selmServerApply.getNmNetwork().intValue()==0) {%>
                        value="政务外网" <%}else{ %>
                        value="互联网"<%}%>
                        name="<%=SelmServerApply.NM_NETWORK%>" disabled="disabled">
				</div>
			</div>
			 <div class="row cl">
			 <label class="form-label col-xs-4 col-sm-2">
				现场有无值守：</label>
				<div class="formControls col-xs-4 col-sm-4">
						<input type="text" class="input-text"
                    <%if (selmServerApply.getStWatchOver().intValue()==0) {%>
                        value="有" <%}else{ %>
                        value="无"<%}%>
                        name="<%=SelmServerApply.ST_WATCH_OVER%>" disabled="disabled">
				</div>
			</div>
		</form>
	</div>
	<div style="font-size: 18px;padding-left: 95px;font-weight: 600;">服务审核</div>
	<hr style="width: 90%;margin-left: 96px;font-weight: 600;"/>
		<div class="page-container">

			<div class="text-x" style="text-align: left;">
				
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;事项编码: <input type="text" name="" id="searchCode" placeholder=" 事项编码"
					style="width:250px" class="input-text">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;事项名称: <input type="text" name="" id="searchName" placeholder=" 事项名称"
					style="width:250px" class="input-text">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;事项部门: <input type="text" name="" id="searchOrgan" placeholder=" 事项部门"
					style="width:250px" class="input-text">
				<button name="" id="search" class="btn btn-success"
					onclick="search()">
					<i class="Hui-iconfont">&#xe665;</i> 搜索
				</button>
			</div>
			<!-- <div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l"> <a href="javascript:;" onclick="deviceAdd()"
					class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
						确定</a> </span>
			</div> -->
			<div class="mt-20" style="width: 94%; margin-left: 64px;">
				<table width="100%"
					class="table table-border table-bordered table-bg table-hover table-sort"
					id="itemList">
					<thead>
						<tr class="text-c">
							<th width="15"><input name="" type="checkbox">
							</th>
							<th width="80">事项编码</th>
							<th width="80">事项名称</th>
							<th width="80">所属部门</th>
							<th width="80">审核人</th>
							<th width="80">审核时间</th>
							<th width="40">状态</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
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
var table = $('#itemList')
.dataTable(
		{
			"aoColumnDefs" : [ {
				"orderable" : false,
				"aTargets" : [ 0 ]
			} // 制定列不参与排序
			],
			"order" : [ [ 1, "desc" ] ],
			"processing" : true,
			"serverSide" : true,
			"searching" : false,
			"paginationType" : "full_numbers",
			"aoColumns" : [
					{
						"sClass" : "text-center",
						"data" : "stLinksId",
						"render" : function(data, type,
								full, meta) {
							return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
						},
						"bSortable" : false
					},
					{
						"data" : "stItemNo",
						"render" : function(data, type,
								full, meta) {
							return setNullData(data);
						}
					},
					{
						"data" : "stItemName",
						"render" : function(data, type,
								full, meta) {
							return setNullData(data);
						}
					},
					{
						"data" : "stOrganId",
						"render" : function(data, type,
								full, meta) {
							return setNullData(data);
						}
					},
					{
						"data" : "stAuditUserName",
						"render" : function(data, type,
								full, meta) {
							return setNullData(data);
						}
					},
					{
						"data" : "dtAudit",
						"render" : function(data, type,
								full, meta) {
							 return setDateTime(data);
						}
					},
					 {
                 "data": "nmPass",
                 "render": function (data, type, full, meta) {
                 if(data == 1 ){
                   	var returnInfo = "<a style=\"text-decoration:none;color:green\" class=\"ml-5\" href=\"javascript:;\" title=\"通过\">通过</a>";
                 	 return returnInfo;
                 }else if(data==0){
                  	var returnInfo = "<a style=\"text-decoration:none; color:red\" class=\"ml-5\" href=\"javascript:;\" title=\"不通过\">不通过</a>";
                	 return returnInfo;
                 }else if(data==3){
                 var returnInfo = "<a style=\"text-decoration:none;\" class=\"ml-5\" href=\"javascript:;\" title=\"未审核\">未审核</a>";
                	 return returnInfo;
                	}
                }/* ,
                  "bSortable": false */
                }
			],
			"createdRow" : function(row, data, dataIndex) {
			$(row).children('td').eq(0).attr('style','text-align: center;');
			$(row).children('td').eq(1).attr('style','text-align: center;');
			$(row).children('td').eq(2).attr('style','text-align: center;');
			$(row).children('td').eq(3).attr('style','text-align: center;');
			$(row).children('td').eq(4).attr('style','text-align: center;');
			$(row).children('td').eq(5).attr('style','text-align: center;');
			$(row).children('td').eq(6).attr('style','text-align: center;');
			},
			"bPaginate" : true, //翻页功能
			"bLengthChange" : true,//改变每页显示数据数量
			"ajax" : {
				url : webRoot + '/serverApply/selmServerItem/list.do',
				type : "POST",
				data : function(d) {
					var stItemNo = $("#searchCode").val();
					d.stItemNo = stItemNo;
					var stMainName = $("#searchName").val();
					d.stMainName = stMainName;
					var stOrganId = $("#searchOrgan").val();
					d.stOrganId = stOrganId;
					var stApplyId = '<%=selmServerApply.getStApplyId()%>';
					/* var stApplyId = 'a3cdacd4-4005-44f4-a91f-1f0a9faac283'; */
					d.stApplyId = stApplyId;
				},
				error : function() {
					window.parent.location.reload();
				}
			}
		});

$(function() {
	$("#itemList").removeAttr("style");
});

// 查找
function search() {
	table.api().ajax.reload();
}

// 审核
function serverApply_checkItem(url,id,content){
    $.ajax({
			url : webRoot + url,
			type : "POST",
			data : {
				stLinksId : id,
				stUserId : smsUserId,
				nmPass:content
			},
			success : function(result) {
			if(content==1){
			$('#'+id).parent().html("<span style=\"color:green\">通过<span>")
			}else{
			$('#'+id+'noPass').parent().html("<span style=\"color:red\">不通过<span>")
			}
			layer.msg("操作成功", {
					icon : 1,
					time : 1000
				});
			},
			error : function() {
			layer.msg("操作成功", {
					icon : 1,
					time : 1000
				});
				window.parent.location.reload();
			}
		});
}
// 未配置数据
function setNullData(data) {
	if (data == '') {
		return '<span class=\"label label-danger radius\"></span>';
	} else {
		return data;
	}
}

</script>
	</div>
	</div>
</body>