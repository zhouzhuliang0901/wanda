<%@page import="com.wondersgroup.app.bean.SelmItemType"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%
  String webRoot = AppContext.webRootPath;
    SelmItemType selmItemType = (SelmItemType) request.getAttribute(SelmItemType.SELM_ITEM_TYPE);
    if (selmItemType == null)
        selmItemType = new SelmItemType();
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
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>查看</title>
</head>
<body>
	<div class="page-container">
		<form class="form form-horizontal" id="form-item-add">
			<input type="hidden" name="<%=SelmItemType.ST_ITEM_TYPE_ID%>"
				id="stSampleId" value="<%=selmItemType.stItemTypeId2Html()%>" />
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">事项分组名称：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmItemType.stItemTypeName2Html() %>"
						 name="<%=SelmItemType.ST_ITEM_TYPE_NAME%>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">排序：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmItemType.nmSort2Html(0)%>"
						name="<%=SelmItemType.NM_SORT%>" disabled="disabled">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">备注1：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmItemType.stExt12Html()%>"
						name="<%=SelmItemType.ST_EXT1%>" disabled="disabled">
				</div>
				<%-- <label class="form-label col-xs-4 col-sm-2">备注2：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmItemType.stExt22Html()%>"
						name="<%=SelmItemType.ST_EXT2%>" disabled="disabled">
				</div> --%>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button onClick="layer_close();" class="btn btn-default radius"
						type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>

		</form>
	</div>
	<!--  <div class="page-container">

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
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
		</div>
		</div>-->
	<script type="text/javascript"
		src="<%=webRoot%>/resources/jquery/jquery-1.11.1.min.js"></script>
	<script type="text/javascript"
		src="<%=webRoot%>/resources/layer/2.1/layer.js"></script>
	<script type="text/javascript"
		src="<%=webRoot%>/sms/lib/h-ui/js/H-ui.js"></script>
	<script type="text/javascript"
		src="<%=webRoot%>/sms/lib/h-ui.admin/js/H-ui.admin.js"></script>
	<script type="text/javascript"
	src="<%=webRoot%>/sms/lib/common/common.js"></script>
	<script type="text/javascript"
	src="<%=webRoot%>/resources/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript"
	src="<%=webRoot%>/sms/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript">
var table = $('#itemList').dataTable({
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
				"data" : "stItemId",
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
				"data" : "stMainName",
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
		url : webRoot + '/app/selmItemLink/itemLinkList.do',
		type : "POST",
		data : function(d) {
			var stItemNo = $("#searchCode").val();
			d.stItemNo = stItemNo;
			var stMainName = $("#searchName").val();
			d.stMainName = stMainName;
			var stOrganId = $("#searchOrgan").val();
			d.stOrganId = stOrganId;
			var stItemTypeId = '<%=selmItemType.getStItemTypeId()%>';
			/* var stApplyId = 'a3cdacd4-4005-44f4-a91f-1f0a9faac283'; */
			d.stItemTypeId = stItemTypeId;
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

// 未配置数据
function setNullData(data) {
	if (data == '') {
		return '<span class=\"label label-danger radius\"></span>';
	} else {
		return data;
	}
}
</script>
</body>
</html>