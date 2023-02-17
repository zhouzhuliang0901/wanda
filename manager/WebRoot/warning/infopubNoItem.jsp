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
<%--    href="https://at.alicdn.com/t/font_1700164_scb7ybg4fd.css" />--%>
<%--<link rel="stylesheet" type="text/css"--%>
<%--    href="https://at.alicdn.com/t/font_1743964_q4n8sjtxs3.css" />--%>
    
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>预警管理 </title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		预警管理 <span class="c-gray en">&gt;</span>未赋能业务预警 <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
	</nav>
	<div class="page-container">
		
		<div class="text-x" style="text-align: left;">
			<!-- 日期范围： <input type="text"
				onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'startDate\')||\'%y-%M-%d \'}'})"
				id="startDate" class="input-text Wdate" style="width:120px;" autocomplete="off"> -
			<input type="text"
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'endDate\')}',maxDate:'%y-%M-%d'})"
				id="endDate" class="input-text Wdate" style="width:120px;" autocomplete="off"> -->
		    设备编码：<input type="text" name="" id="searchDeviceName" placeholder=" 设备编码" style="width:250px"
				class="input-text">
		   <!-- &nbsp;&nbsp;&nbsp;设备分类：<input type="text" name="" id="searchTypeName" placeholder=" 设备分类" style="width:250px"
				class="input-text"> -->
			
		  <!--   &nbsp;&nbsp;&nbsp;设备分类：
		   <select name="searchTypeId" id="deviceTypeSelect" class="input-text" style="width:250px">
		   		<script id="typeList" type="text/html">
                     {{each data}}
                        <option value="{{$value.stTypeId}}" {{if typeId == $value.stTypeId}} selected='selected' {{/if}}>
                            {{$value.stTypeName}}</option>
                     {{/each}} 
                </script>
		   
		   </select>-->
		  &nbsp;&nbsp;&nbsp;&nbsp;<button name="" id="search" class="btn btn-success" onclick="search()" >
				<i class="Hui-iconfont">&#xe665;</i> 搜索
			</button>
		</div>	
		<div class="mt-20">
			<table width="100%"
				class="table table-border table-bordered table-bg table-hover table-sort" id="deviceList">
				<thead>
					<tr class="text-c">
						<th width="15" ><input name="" type="checkbox" ></th>
						<!-- <th width="100">设备名称</th> -->
						<th width="40">设备编码</th>
						<th width="100">详细地址</th>
						<!-- <th width="80">设备MAC</th> -->
						<!-- <th width="40">设备类型</th> -->
						<!-- <th width="80">创建时间</th>
						<th width="20">是否在线</th> -->
						<th width="40">设备MAC</th>
						<th width="40">设备事项</th>
						<!-- <th width="10">信息操作</th> -->
<!-- 						<th width="20">发布</th> -->
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
<script type="text/javascript"
		src="<%=webRoot%>/sms/lib/viewer/js/template.js"></script>
<script type="text/javascript">
var areaId = "<%= (Object)request.getSession().getAttribute("smsUserStAreaId")%>";
var permission = "<%= (Object)request.getSession().getAttribute("权限")%>";
var table = $('#deviceList').dataTable({
     "aoColumnDefs": [
      {"orderable":false,"aTargets":[0]}// 制定列不参与排序
    ],
    "order": [[ 3, "desc" ]],
    "processing" : true,  
    "serverSide" : true,  
    "searching": false,
    "paginationType":"full_numbers",
    "aoColumns": [
                {
                 "sClass": "text-center",
                 "data": "stDeviceId",
                 "render": function (data, type, full, meta) {
                     return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
                    },
                  "bSortable": false
                },
                
                { "data": "stDeviceCode",
                "render":function(data, type, full, meta){
                      return  setNullData(data);
                  }},
                { "data": "stDeviceAddress",
                   "render":function(data, type, full, meta){
                      return  setNullData(data);
                }},
               /*  { "data": "stTypeId",
                "render":function(data, type, full, meta){
                      return  setNullData(data);
                  }}, */
                   { "data": "stDeviceMac",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                 { "data": "stDesc",
                   "render":function(data, type, full, meta){
                      var returnInfo = "<a style=\"text-decoration:none;color:red\" class=\"ml-5\" onClick=\"device_Noitem('"+full.stDeviceCode+"->事项详情','/business/selmQueryHis/deviceNoItemList.do','"+full.stDeviceMac+"')\" href=\"javascript:;\" title=\"查看\">"+data+"</a>";
               		   return returnInfo;
                       
                },
                  "bSortable": false}
               /*  {
                 "data": "stDeviceId",
                 "render": function (data, type, full, meta) {
                        var returnInfo = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"oauth2item_add('授权事项','/app/selmDeviceitem/add.do','"+data+"')\" href=\"javascript:;\" title=\"授权\"><i class=\"Hui-iconfont\">&#xe63c;</i></a>";
                            //returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_edit('"+full.stDeviceName+"->编辑','/infopub/deviceinfo/edit.do','"+data+"')\" href=\"javascript:;\" title=\"编辑设备\"><i class=\"Hui-iconfont\">&#xe647;</i></a>";
                            returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_info('查看','/app/selmDeviceitem/deviceinfo.do','"+data+"')\" href=\"javascript:;\" title=\"查看\"><i class=\"Hui-iconfont\">&#xe725;</i></a>";
                            returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_del(this,'"+data+"')\" href=\"javascript:;\" title=\"删除设备\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
                        return returnInfo;
                    },
                  "bSortable": false
                } */
                

            ],
       "createdRow": function( row, data, dataIndex ) {
                    $(row).children('td').eq(0).attr('style', 'text-align: center;');
                    $(row).children('td').eq(1).attr('style', 'text-align: center;');
                    $(row).children('td').eq(2).attr('style', 'text-align: center;');
                    $(row).children('td').eq(3).attr('style', 'text-align: center;');
                    $(row).children('td').eq(4).attr('style', 'text-align: center;');
                    $(row).children('td').eq(5).attr('style', 'text-align: center;');
                    $(row).children('td').eq(6).attr('style', 'text-align: center;');
                   //$(row).children('td').eq(6).attr('style', 'padding:10px 5px ');
                   // $(row).children('td').eq(6).attr('style', 'right-padding: 0px;');
                   /*  $(row).children('td').eq(7).attr('style', 'text-align: center;');
                    $(row).children('td').eq(8).attr('style', 'text-align: center;'); */
                },
      "bPaginate": true, //翻页功能
      "bLengthChange": true ,//改变每页显示数据数量
       "ajax": {
		    url:webRoot+'/infopub/deviceinfo/ItemDeviceList.do',
		    type:"POST",
		    data: function(d){
		      var stAreaId = areaId;
		      d.stAreaId = stAreaId; 
		      var stPermission = permission;
		      d.stPermission = stPermission;  
		      var codeName = $("#searchDeviceName").val();
		      d.codeName = codeName;
		      var typeId = $("#deviceTypeSelect").val();
		      d.typeId = typeId;
		      var groupId = $("#deviceGroupSelect").val();
		      d.groupId = groupId;
		      /* var areaName = $("#searchAreaName").val();
		      d.areaName = areaName; */
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
    $("#deviceList").removeAttr("style"); 
});

$(function(){
	initType();
});

/*授权*/
function oauth2item_add(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+"?ST_DEVICE_ID="+id
    });
    layer.full(index);
}

/*授权*/
function device_Noitem(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+"?ST_MACHINE_ID="+id
    });
    layer.full(index);
}


// 分类查询
function initType() {
			$.ajax({
				url : webRoot + '/infopub/devicetype/init.do',
				type : "POST",
				dataType : "json",
				data:{userName:user},
				success : function(result) {
					var devicetype = result.devicetype;
					devicetype.typeId = '';
					$("#deviceTypeSelect").html(
							template('typeList',devicetype));

				},
				error : function() {
				}
			});
}



$("#deviceGroupSelect").on('click', function() {
	initType();
});


// 查找
function search () {
    table.api().ajax.reload();
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
        content: webRoot+url+'?ST_DEVICE_ID='+id
    });
    layer.full(index);
}





// 未配置数据
function setNullData(data){
    if(data == ''){
        return '<span class=\"label label-danger radius\">信息未配置</span>';
    } else {
        return data;
    }
}
// 配置是否在线
function setOnLine(data){
    if(data=='1'){
    	return '<span class=\"label label-success radius\">在线</span>'
    	//return '<i class=\"iconfont icon-zaixian\" style=\"color:green\"></i>';
    }else if(data=='0'){
    	return '<span class=\"label label-danger radius\">离线</span>'
    	//return '<i class=\"iconfont icon-buzaixian\"></i>';
    }
}
</script>
</body>
</html>