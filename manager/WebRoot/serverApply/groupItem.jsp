<%@page import="wfc.service.config.Config"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubDeviceInfo"%>
<%@page import="com.wondersgroup.serverApply.bean.SelmServerApply"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="coral.base.app.AppContext"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="permission" uri="/sms/permission"%>
<%
	String webRoot = AppContext.webRootPath;
	 SelmServerApply selmServerApply = (SelmServerApply) request.getAttribute(SelmServerApply.SELM_SERVER_APPLY);
    if (selmServerApply == null)
        selmServerApply = new SelmServerApply(); 
        
    InfopubDeviceInfo infopubDeviceInfo = (InfopubDeviceInfo) request
			.getAttribute(InfopubDeviceInfo.INFOPUB_DEVICE_INFO);
	if (infopubDeviceInfo == null)
		infopubDeviceInfo = new InfopubDeviceInfo();
		
	String stDeviceArray = (String)request.getAttribute("stDeviceArray");
	if (stDeviceArray == null)
		stDeviceArray = "";
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

<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>设备管理</title>
</head>
<body>
<div class="page-container">

	<div class="text-x" style="text-align: center;">
		<div>
			分组名称: <input type="text" name="" id="OrganName" placeholder="分组名称"
							style="width:250px" class="input-text">
	
		
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
		<button name="" id="search" class="btn btn-primary radius"
		onclick="search()">
				<i class="Hui-iconfont">&#xe665;</i> 搜索
			</button>
			
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
	 		
		</span>
	</div>	

	<div class="mt-20" >
		<table width="100%"
			class="table table-border table-bordered table-bg table-hover table-sort"
			id="itemList">
			<thead>
				<tr class="text-c">
					<th width="15"><input id="firstTable" name="" type="checkbox"></th>
					<th width="80">序号</th>
					<th width="80">分组名称</th>
					<th width="80">事项数</th>
					<th width="50">操作</th>
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
var i = 1;
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
						"data" : "stItemTypeId",
						"render" : function(data, type,
								full, meta) {
							return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
						},
						"bSortable" : false
					},
					{
						"data" : "stOrganId",
						"render" : function(data, type,
								full, meta) {
							return setNumber();
						}
					},
					{
						"data" : "stItemTypeName",
						"render" : function(data, type,
								full, meta) {
							return setNullData(data);
						}
					},
					{
						"data" : "stExt2",
						"render" : function(data, type,
								full, meta) {
							return setNullData(data);
						}
					},
					{
						"data" : "stItemTypeId",
						"render" : function(data, type,
								full, meta) {
								//var returnInfo = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"item_add('"+full.stMainName+"->授权','/app/selmDeviceItem/save.do','"+data+"')\" href=\"javascript:;\" title=\"授权\">授权</a>";
								var returnInfo = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"item_add('"+full.stItemTypeName+"->授权','/serverApply/selmServerItem/savePowerByGroup.do','"+data+"')\" href=\"javascript:;\" title=\"添加事项\"><i class=\"Hui-iconfont\">&#xe600;</i></a>";
							return returnInfo;
						},
						"bSortable": false
						
					},
			],
			"createdRow" : function(row, data, dataIndex) {
				$(row).children('td').eq(0).attr('style',
						'text-align: center;');
				$(row).children('td').eq(1).attr('style',
						'text-align: center;');
				$(row).children('td').eq(2).attr('style',
						'text-align: center;');
				$(row).children('td').eq(3).attr('style',
						'text-align: center;');
				$(row).children('td').eq(4).attr('style',
						'text-align: center;');
				
			},
			"bPaginate" : true, //翻页功能
			"bLengthChange" : true,//改变每页显示数据数量
			"ajax" : {
				url : webRoot + '/serverApply/selmServerItem/groupData.do',
				type : "POST",
				data : function(d) {
					var OrganName = $("#OrganName").val();
					d.OrganName = OrganName;
					<%-- var stDeviceId = '<%=infopubDeviceInfo.stDeviceId2Html()%>';
					d.stDeviceId = stDeviceId;
					var stDeviceArray = '<%=stDeviceArray%>';
					d.stDeviceArray = stDeviceArray; --%>
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

/* 
function deviceAdd () {
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
	 	item_add('','/serverApply/selmServerItem/savePowerByGroup.do',idArray);
} */
			
// 添加
function item_add(title,url,id){
		$.ajax({
			url : webRoot + url,
			type : "POST",
			//dataType : "json",
			data : {
				stDeviceId : '<%=infopubDeviceInfo.stDeviceId2Html()%>',
				stItemTypeId : id,
				stApplyId : '<%=selmServerApply.stApplyId2Html()%>',
				stDeviceArray : '<%=stDeviceArray%>',
			},
			success : function(myObject) {
				layer.msg("添加成功", {
					icon : 1,
					time : 1000
				});
				
			},
			error : function(myObject) {
				layer.msg("添加失败", {
					icon : 1,
					time : 1000
				});
				
			}
		});
		setTimeout(function() {
			window.parent.location.reload();
		}, 1000);
}


// 未配置数据
function setNullData(data) {
	if (data == '') {
		return '<span class=\"label label-danger radius\"></span>';
	} else {
		return data;
	}
}

function setNumber(){
	return i++;
}

</script>
</body>
</html>