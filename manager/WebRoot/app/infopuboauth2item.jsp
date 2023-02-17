<%@page import="com.wondersgroup.app.bean.Oauth2Client"%>
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
	InfopubDeviceInfo infopubDeviceInfo = (InfopubDeviceInfo) request
			.getAttribute(InfopubDeviceInfo.ST_DEVICE_ID);
	if (infopubDeviceInfo == null)
		infopubDeviceInfo = new InfopubDeviceInfo();
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

<link rel="stylesheet" type="text/css"
	href="https://at.alicdn.com/t/font_1743964_q4n8sjtxs3.css" />

<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>设备管理</title>
</head>
<body>
	<div id="s1" style="width: 50%;float: left;">
		<div class="page-container">

			<div class="text-x" style="text-align: left;">
				<!-- 事项编码:<input type="text" name="" id="searchCode" placeholder=" 事项编码"
					style="width:250px" class="input-text"> -->
					事项名称: <input type="text" name="" id="searchName"
					placeholder=" 事项名称" style="width:250px" class="input-text">
			</div>
			<br>
			<div>
				事项编码: <input type="text" name="" id="searchCode" placeholder=" 事项编码"
					style="width:250px" class="input-text">
				<button name="" id="search" class="btn btn-success"
					onclick="search()">
					<i class="Hui-iconfont">&#xe665;</i> 搜索
				</button>
			</div>
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l">
				<a href="javascript:;" onclick="deviceAdd()"
					class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
						批量授权</a>&nbsp;&nbsp;&nbsp; 
				</span>
						
				<span class="l">
		 		<a href="javascript:;" onclick="groupItem('分组事项','/app/selmDeviceitem/groupAdd.do')"
						class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
						按事项分组选择</a>&nbsp;&nbsp;&nbsp; <a style="color:red"></a>
				</span>
			</div> 
			
			 
			<div class="mt-20">
				<table width="100%"
					class="table table-border table-bordered table-bg table-hover table-sort"
					id="itemList">
					<thead>
						<tr class="text-c">
							<th width="15"><input name="" type="checkbox"></th>
							<th width="100">事项编码</th>
							<th width="100">事项名称</th>
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
											"data" : "stMainName",
											"render" : function(data, type,
													full, meta) {
												return setNullData(data);
											}
										},
										/* {
											"data" : "deviceinfo",
											"render" : function(data, type,
													full, meta) {
												return setNullData(data);
											}
										}, */
										{
						                 "data": "stItemId",
						                 "render": function (data, type, full, meta) {
						                        var returnInfo = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"item_add('"+full.stMainName+"->授权','/app/selmDeviceItem/save.do','"+data+"')\" href=\"javascript:;\" title=\"授权\">授权</a>";
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
								},
								"bPaginate" : true, //翻页功能
								"bLengthChange" : true,//改变每页显示数据数量
								"ajax" : {
									url : webRoot + '/app/oauth2Client/nodeviceOCItemlist.do',
									type : "POST",
									data : function(d) {
										var stItemNo = $("#searchCode").val();
										d.stItemNo = stItemNo;
										var stMainName = $("#searchName").val();
										d.stMainName = stMainName;
										var stDeviceId = '<%=infopubDeviceInfo.stDeviceId2Html()%>';
										d.stDeviceId = stDeviceId;
									},
									/* success : function() {
									}, */
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
        			layer.msg('请选择需要授权的数据',{icon:2,time:1000});
       			 return;
    		}
    		for (var i = 0; i< selectList.length; i++){
        		idArray.push(selectList[i].value);
    			} 
   			 item_add('','/app/selmDeviceItem/save.do',idArray);
			}
			
			// 添加
			function item_add(title,url,id){
			    $.ajax({
						url : webRoot + url,
						type : "POST",
						//dataType : "json",
						data : {
							stDeviceId : '<%=infopubDeviceInfo.stDeviceId2Html()%>',
							stItemId : id
						},
						success : function(result) {
							$("#searchName1").val('');
							$("#searchCode1").val('');
							table.api().ajax.reload();
							table1.api().ajax.reload();
							
						},
						error : function() {
							window.parent.location.reload();
						}
					});
			}
			
			//按事项分组选择
			function groupItem(title,url){
				var stDeviceId = '<%=infopubDeviceInfo.stDeviceId2Html()%>';
			    var index = layer.open({
			        type: 2,
			        title: title,
			        content: webRoot+url+'?ST_DEVICE_ID='+stDeviceId,
			    });
			    layer.full(index);
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
	<div id="s2" style="width: 50%;float: right;">
		<div class="page-container">
			<div class="text-x" style="text-align: left;">
				<!-- 事项编码:<input type="text" name="" id="searchCode" placeholder=" 事项编码"
					style="width:250px" class="input-text"> -->
					事项名称: <input type="text" name="" id="searchName1"
					placeholder=" 事项名称" style="width:250px" class="input-text">
			</div>
			<br>
			<div>
				事项编码: <input type="text" name="" id="searchCode1" placeholder=" 事项编码"
					style="width:250px" class="input-text">
				<button name="" id="search" class="btn btn-success"
					onclick="search1()">
					<i class="Hui-iconfont">&#xe665;</i> 搜索
				</button>
			</div>
			<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
			<a href="javascript:;" onclick="deviceRemove()"
				class="btn btn-primary radius"><i class="Hui-iconfont">&#xe6e2;</i>
					批量移除</a>
					</span>
			</div>
			<div class="mt-20">
				<table width="100%"
					class="table table-border table-bordered table-bg table-hover table-sort"
					id="itemList1">
					<thead>
						<tr class="text-c">
							<th width="15"><input id='question' name="" type="checkbox">
							</th>
							<th width="100">事项编码</th>
							<th width="100">事项名称</th>
							<th width="40">操作</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
		<script type="text/javascript">
var table1 = $('#itemList1').dataTable({
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
                 "data": "stItemId",
                 "render": function (data, type, full, meta) {
                     return '<input type="checkbox"  class="checkchild1"  value="' + data + '" />';
                    },
                  "bSortable": false
                },
                { "data": "stItemNo",
                   "render":function(data, type, full, meta){
                      return  setNullData1(data);
                }},
                { "data": "stMainName",
                   "render":function(data, type, full, meta){
                       return setNullData1(data) ;
                }},
                {
                 "data": "stItemId",
                 "render": function (data, type, full, meta) {
                        var returnInfo1 = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"item_remove('"+full.stMainName+"->移除','/app/selmDeviceItem/remove.do','"+data+"')\" href=\"javascript:;\" title=\"移除\">移除</a>";
                        return returnInfo1;
                    },
                  "bSortable": false
                }
            ],
       "createdRow": function( row, data, dataIndex ) {
                    $(row).children('td').eq(0).attr('style', 'text-align: center;');
                    $(row).children('td').eq(1).attr('style', 'text-align: center;');
                    $(row).children('td').eq(2).attr('style', 'text-align: center;');
                    $(row).children('td').eq(3).attr('style', 'text-align: center;');
                },
      "bPaginate": true, //翻页功能
      "bLengthChange": true ,//改变每页显示数据数量
       "ajax": {
		    url:webRoot+'/app/oauth2Client/deviceOCItemlist.do',
		    type:"POST",
		    data: function(d){
		      var stItemNo1 = $("#searchCode1").val();
		      d.stItemNo1 = stItemNo1;
		      var stMainName1 = $("#searchName1").val();
		      d.stMainName1 = stMainName1;
             var stDeviceId1 = '<%=infopubDeviceInfo.stDeviceId2Html()%>';
				d.stDeviceId1 = stDeviceId1;
		    },
		    error:function(){
		      window.parent.location.reload();
		    }
	    }
}); 

$(function(){
    $("#itemList1").removeAttr("style"); 
});

// 查找
function search1() {
    table1.api().ajax.reload();
}
	function deviceRemove () {
    var selectList = $(".checkchild1:checked");
    var idArray = [];
    // 未选择的场合
    if (selectList.length == 0) {
        layer.msg('请选择需要授权的数据',{icon:2,time:1000});
       		return;
    }
    for (var i = 0; i< selectList.length; i++){
        idArray.push(selectList[i].value);
    		} 
   		item_remove('','/app/selmDeviceItem/remove.do',idArray);
			}
// 移除
function item_remove(title,url,id){
    $.ajax({
			url : webRoot + url,
			type : "POST",
			dataType : "json",
			data : {
				stDeviceId : '<%=infopubDeviceInfo.stDeviceId2Html()%>',
				stItemId : id
			},
			success : function(result) {
				$("#searchName1").val('');
				$("#searchCode1").val('');
				table1.api().ajax.reload();
				table.api().ajax.reload();
			},
			error : function() {
				window.parent.location.reload();
			}
		});
}

// 未配置数据
function setNullData1(data){
    if(data == ''){
        return '<span class=\"label label-danger radius\"></span>';
    } else {
        return data;
    }
}
</script>
	</div>
</body>