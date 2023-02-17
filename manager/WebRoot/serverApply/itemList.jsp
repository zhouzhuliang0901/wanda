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
<title>事项管理</title>
</head>
<body>
		<div class="page-container">

			<div class="text-x" style="text-align: left;">
				
			事项编码: <input type="text" name="" id="searchCode" placeholder=" 事项编码"
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
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius">
					     <i class="Hui-iconfont">&#xe6e2;</i>
							批量删除</a></span>
			</div>
			<div class="mt-20">
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
							<th width="20">操作</th>
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
                 "data": "stItemId",
                 "render": function (data, type, full, meta) {
                           var returnInfo = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"item_del(this,'"+data+"')\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
                        return returnInfo;
                    },
                  "bSortable": false
                }
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
				url : webRoot + '/serverApply/selmServerItem/list.do',
				type : "POST",
				data : function(d) {
					var stItemNo = $("#searchCode").val();
					d.stItemNo = stItemNo;
					var stMainName = $("#searchName").val();
					d.stMainName = stMainName;
					var stOrganId = $("#searchOrgan").val();
					d.stOrganId = stOrganId;
					
					//var stApplyId = '6ebe1476-a031-48c0-baed-d68c0bf50943';
					var stApplyId = '<%=selmServerApply.getStApplyId()%>';
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
function deviceAdd () {
 			var selectList = $(".checkchild:checked");
 			var idArray = [];
 			// 未选择的场合
 			if (selectList.length == 0) {
     			layer.msg('请选择需要绑定的数据',{icon:2,time:1000});
    			 return;
 		}
 		for (var i = 0; i< selectList.length; i++){
     		idArray.push(selectList[i].value);
 			} 
			 item_add('','/serverApply/selmServerItem/save.do',idArray);
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
    
   item_del('',idArray);
}

// 删除
function item_del(obj,id){
    layer.confirm('确认要删除吗？',function(index){
         $.ajax({
            type : 'POST',
            url : webRoot+'/serverApply/selmServerItem/remove.do',
            dataType:'json',
            data:{
            	 /* stApplyId :'6ebe1476-a031-48c0-baed-d68c0bf50943', */
            	stApplyId : '<%=selmServerApply.getStApplyId()%>',
				stItemId : id
				},
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