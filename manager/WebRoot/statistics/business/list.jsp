<%@page import="java.text.SimpleDateFormat"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="coral.base.app.AppContext"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="permission" uri="/sms/permission"%>
<%
	String webRoot = AppContext.webRootPath;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String date = (String)format.format(new Date());
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
<title>???????????? </title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> ?????? <span class="c-gray en">&gt;</span>
		????????????<span class="c-gray en">&gt;</span>???????????? <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="??????"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
	</nav>
	<div class="page-container">
		<div class="text-x" style="text-align: left;">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;??????????????? <input type="text"
				onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'startDate\')||\'%y-%M-%d \'}'})"
				id="startDate" class="input-text Wdate" style="width:120px;" autocomplete="off" > -
			<input type="text"
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'endDate\')}',maxDate:'%y-%M-%d'})"
				id="endDate" class="input-text Wdate" style="width:120px;" autocomplete="off" >
		    <!-- <input type="text" name="" id="searchDeviceName" placeholder=" ????????????" style="width:250px"
				class="input-text"> -->
		    <!-- &nbsp;&nbsp;&nbsp;??????????????? <input type="text" name="" id="searchModuleName" placeholder=" ????????????" style="width:250px"
				class="input-text"> -->
		    &nbsp;&nbsp;&nbsp;??????????????? <input type="text" name="" id="searchItemName" placeholder=" ????????????" style="width:250px"
				class="input-text">
				<%if(request.getSession().getAttribute("??????").equals("project_admin")){ %>
				&nbsp;&nbsp;&nbsp;??????????????? <input type="text" name="" id="searchAreaName" placeholder=" ????????????" style="width:250px"
					class="input-text"><%} %>
					&nbsp;&nbsp;&nbsp;<button name="" id="search" class="btn btn-success" onclick="search()" >
						<i class="Hui-iconfont">&#xe665;</i> ??????
					</button>
		</div>
		
		<div class="mt-20">
			<table width="100%"
				class="table table-border table-bordered table-bg table-hover table-sort" id="businessList">
				<thead>
					<tr class="text-c">
						<!-- <th width="15" ><input name="" type="checkbox" ></th> -->
						<th width="40%" >????????????</th>
						<th width="30%" >?????????</th>
						<th width="30%" >????????????</th>
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
var permission = "<%= (Object)request.getSession().getAttribute("??????")%>";
var table = $('#businessList').dataTable({
     "aoColumnDefs": [
      {"orderable":false,"aTargets":[0]}// ????????????????????????
    ],
    "order": [[ 1, "desc" ]],
    "processing" : true,  
    "serverSide" : true,  
    "searching": false,
    "paginationType":"full_numbers",
    "aoColumns": [
                /*  {
                 "sClass": "text-center",
                 "data": "stQueryHisId",
                 "render": function (data, type, full, meta) {
                     return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
                    },
                  "bSortable": false
                },  */
                { "data": "stItemName",
                   "render":function(data, type, full, meta){
                   var returnInfo = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"business_info('"+full.stItemName+"->?????????????????????','/statistics/statistics/itemAreaQuery.do','"+data+"')\" href=\"javascript:;\" title=\"??????\">"+data+"</a>";
                       return returnInfo;
                    //return  setNullData(data);
                }},
                { "data": "stExt1",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                {
                 "data": "stItemName",
                 "render": function (data, type, full, meta) {
                        var returnInfo = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"business_info('??????????????????','/business/selmQueryHis/statisticsInfo.do','"+data+"')\" href=\"javascript:;\" title=\"????????????\">?????????????????????</a>";
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
                },
      "bPaginate": true, //????????????
      "bLengthChange": true ,//??????????????????????????????
       "ajax": {
		    url:webRoot+'/business/selmQueryHis/statistics.do',
		    type:"POST",
		    data: function(d){
		    var stAreaId = areaId;
		      d.stAreaId = stAreaId; 
		      var stPermission = permission;
		      d.stPermission = stPermission;  
		      var stItemName = $("#searchItemName").val();
		      d.stItemName = stItemName;
		      var stAreaName = $("#searchAreaName").val();
		      d.stAreaName = stAreaName;
		      console.log($("#startDate").val())
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
    $("#businessList").removeAttr("style"); 
});

// ??????
function search () {
    table.api().ajax.reload();
}


// ????????????
function datadel () {
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
    
    device_del('',idArray);
}

// ??????
function business_info(title,url,name){
	name = encodeURI(encodeURI(name));
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_ITEM_NAME='+name+'&stDate='+$("#startDate").val()+'&edDate='+$("#endDate").val()
    });
    layer.full(index);
}


// ??????
function business_del(obj,id){
    layer.confirm('?????????????????????',function(index){
         $.ajax({
            type : 'POST',
            url : webRoot+'/business/selmQueryHis/remove.do',
            dataType:'json',
            data:{'stQueryHisId':id},
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

</script>
</body>
</html>