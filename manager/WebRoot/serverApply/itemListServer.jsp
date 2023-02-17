<%@page import="com.wondersgroup.serverApply.bean.SelmServerApply"%>
<%@page import="net.sf.json.JSONArray"%>
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
<div style="width:100%;">
<div id="s1" style="width: 50%;float: left;">
<div class="page-container">

	<div class="text-x" style="text-align: left;">
		<div>
			事项部门: <input type="text" name="" id="searchOrgan" placeholder=" 事项部门"
							style="width:250px" class="input-text">
		</div>
		<br>
		<div>
			事项名称: <input type="text" name="" id="searchName" placeholder=" 事项名称"
							style="width:250px" class="input-text">
		</div>
		<br>
			事项编码: <input type="text" name="" id="searchCode" placeholder=" 事项编码"
							style="width:250px" class="input-text">
		
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button name="" id="search" class="btn btn-primary radius"
		onclick="search()">
				<i class="Hui-iconfont">&#xe665;</i> 搜索
			</button>
			
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
	 		<a href="javascript:;" onclick="deviceAdd()"
					class="btn btn-success radius"><i class="Hui-iconfont">&#xe600;</i>
					批量添加勾选</a>&nbsp;&nbsp;&nbsp; <a style="color:red"></a>
		</span>
		<span class="l">
	 		<a href="javascript:;" onclick="groupItem('分组事项','/serverApply/selmServerItem/groupItem.do')"
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
					<th width="15"><input id="firstTable" name="" type="checkbox"></th>
					<th width="80">事项编码</th>
					<th width="80">事项名称</th>
					<th width="60">所属部门</th>
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
					{
						"data" : "stExt1",
						"render" : function(data, type,
								full, meta) {
							return setNullData(data);
						}
					},
					{
						"data" : "stItemId",
						"render" : function(data, type, full, meta) {
								var returnInfo = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"item_add('"+full.stMainName+"->授权','/serverApply/selmServerItem/savePower.do','"+data+"')\" href=\"javascript:;\" title=\"添加事项\"><i class=\"Hui-iconfont\">&#xe600;</i></a>";
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
				url : webRoot + '/serverApply/selmServerItem/nodeviceOCItemlist.do',
				type : "POST",
				data : function(d) {
					var stItemNo = $("#searchCode").val();
					d.stItemNo = $.trim(stItemNo);
					var stMainName = $("#searchName").val();
					d.stMainName = $.trim(stMainName);
					var stOrganName = $("#searchOrgan").val();
					d.stOrganName = $.trim(stOrganName);
					var stDeviceId = '<%=infopubDeviceInfo.stDeviceId2Html()%>';
					d.stDeviceId = stDeviceId;
					var stDeviceArray = '<%=stDeviceArray%>';
					d.stDeviceArray = stDeviceArray;
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
   			layer.msg('请选择需要授权的数据',{icon:2,time:1000});
  			 return;
 		}
 		for (var i = 0; i< selectList.length; i++){
   			idArray.push(selectList[i].value);
		} 
	 	item_add('','/serverApply/selmServerItem/savePower.do',idArray);
}
			
			// 添加
function item_add(title,url,id){
		$.ajax({
			url : webRoot + url,
			type : "POST",
			//dataType : "json",
			data : {
				stDeviceId : '<%=infopubDeviceInfo.stDeviceId2Html()%>',
				stItemId : id,
				stApplyId : '<%=selmServerApply.stApplyId2Html()%>',
				stDeviceArray : '<%=stDeviceArray%>',
			},
			success : function(result) {
				$("#searchName").val('');
				$("#searchCode").val('');
				$("#searchOrgan").val('');
				table.api().ajax.reload();
				table1.api().ajax.reload();
				
			},
			error : function() {
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



function groupItem(title,url){
		var stDeviceId = '<%=infopubDeviceInfo.stDeviceId2Html()%>';
		var stApplyId = '<%=selmServerApply.stApplyId2Html()%>';
		var stDeviceArray = '<%=stDeviceArray%>';
	    var index = layer.open({
	        type: 2,
	        title: title,
	        content: webRoot+url+'?stDeviceId='+stDeviceId+'&stDeviceArray='+stDeviceArray+'&stApplyId='+stApplyId,
	    });
	    layer.full(index);
	}

</script>
	</div>
	<div id="s2" style="width: 50%;float: right;">
		<div class="page-container">
			<div class="text-x" style="text-align: left;">
				<div>
					事项部门: <input type="text" name="" id="searchOrgan1" placeholder=" 事项部门"
						style="width:250px" class="input-text">

				</div>
				<br>
				<div>
					事项名称: <input type="text" name="" id="searchName1" placeholder=" 事项名称"
						style="width:250px" class="input-text">
				</div>
				<br>
					事项编码: <input type="text" name="" id="searchCode1" placeholder=" 事项编码"
						style="width:250px" class="input-text">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button name="" id="search" class="btn btn-primary radius" 
				onclick="search1()">
						<i class="Hui-iconfont">&#xe665;</i> 搜索
				</button>
			</div>
 			<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
			<a href="javascript:;" onclick="deviceRemove()"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					批量移除勾选</a>&nbsp;&nbsp;&nbsp;<a style="color:red"></a>
					</span>
			</div>
			<div  class="mt-20">
				<table width="100%"
					class="table table-border table-bordered table-bg table-hover table-sort"
					id="itemList1">
					<thead>
						<tr class="text-c">
							<th width="15"><input id='question'  type="checkbox"></th>
							<th width="80">事项编码</th>
							<th width="80">事项名称</th>
							<th width="60">所属部门</th>
							<th width="50">操作</th>
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
						"sClass" : "text-center",
						"data" : "stItemId",
						"render" : function(data, type,
								full, meta) {
							return '<input type="checkbox"  class="checkchild1"  value="' + data + '" />';
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
						"data" : "stItemId",
						"render" : function(data, type,full, meta) {
								var returnInfo = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"item_remove('"+full.stMainName+"->移除','/serverApply/selmServerItem/removePower.do','"+data+"')\" href=\"javascript:;\" title=\"移除事项\"><i class=\"Hui-iconfont\">&#xe609;</i></a>";
							return returnInfo;
						},
						 "bSortable": false
					},
					
            ],
       "createdRow": function( row, data, dataIndex ) {
                    $(row).children('td').eq(0).attr('style', 'text-align: center;');
                    $(row).children('td').eq(1).attr('style', 'text-align: center;');
                    $(row).children('td').eq(2).attr('style', 'text-align: center;');
                    $(row).children('td').eq(3).attr('style', 'text-align: center;');
                    $(row).children('td').eq(4).attr('style', 'text-align: center;');
                },
      "bPaginate": true, //翻页功能
      "bLengthChange": true ,//改变每页显示数据数量
       "ajax": {
		    url:webRoot+'/serverApply/selmServerItem/deviceOCItemlist.do',
		    type:"POST",
		    data: function(d){
		     		var stItemNo1 = $("#searchCode1").val();
					d.stItemNo1 = $.trim(stItemNo1);
					var stMainName1 = $("#searchName1").val();
					d.stMainName1 = $.trim(stMainName1);
					var stOrganName1 = $("#searchOrgan1").val();
					d.stOrganName1 = $.trim(stOrganName1);
					var stDeviceId1 ='<%=infopubDeviceInfo.getStDeviceId()%>';
					d.stDeviceId1 = stDeviceId1;
					var stDeviceArray1 = '<%=stDeviceArray%>';
					d.stDeviceArray1 = stDeviceArray1;
					d.stApplyId1='<%=selmServerApply.stApplyId2Html()%>';
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
function deviceRemove() {
    var selectList = $(".checkchild1:checked");
    var idArray = [];
    // 未选择的场合
    if (selectList.length == 0) {
        layer.msg('请选择需要移除的数据',{icon:2,time:1000});
       		return;
    }
    for (var i = 0; i< selectList.length; i++){
        idArray.push(selectList[i].value);
    		} 
   		item_remove('','/serverApply/selmServerItem/removePower.do',idArray);
			}
// 移除
function item_remove(title,url,id){
    $.ajax({
			url : webRoot + url,
			type : "POST",
			dataType : "json",
			data : {
				stDeviceId : '<%=infopubDeviceInfo.stDeviceId2Html()%>',
				stItemId : id,
				stApplyId : '<%=selmServerApply.stApplyId2Html()%>',
				stDeviceArray : '<%=stDeviceArray%>',
			},
			success : function(result) {
				$("#searchName1").val('');
				$("#searchCode1").val('');
				$("#searchOrgan1").val('');
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
</div>
<div style="width:100%;height:80px;">
	<div class="row cl">
          <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-5" style="width:100%;height:80px;position:relative;top:30px;left:30px;">
              <button onclick="sub()" type="button" class="btn btn-primary radius" >
                  <i class="Hui-iconfont">&#xe632;</i> 保存
              </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <button onclick="exit();" class="btn btn-danger radius"
                  type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
          </div>
    </div>
	<script type="text/javascript">
		function sub() {
			setTimeout(function() {
				window.parent.location.reload();
			}, 400);
		}
		function exit() {
			setTimeout(function() {
				window.parent.location.reload();
			}, 400);
		}
		
	</script> 
</div>
</body>