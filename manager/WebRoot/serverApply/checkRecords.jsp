<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="coral.base.app.AppContext"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="permission" uri="/sms/permission"%>
<%
	String webRoot = AppContext.webRootPath;
	String stItemId = (String)request.getAttribute("stItemId");
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
<title>已审批事项</title>
</head>
<body>
	
	<br>
	<div style="padding-top: 10px;font-size: 18px;padding-left: 20px;font-weight: 600;margin-bottom: -14px;">已审批清单</div>
	<br>
	<div class="page-container">
		<div class="text-x" style="text-align: left;">
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    网点：<input type="text" name="" id="searchDeviceName" placeholder="网点" style="width:250px"
				class="input-text">
				
		 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 地址：<input type="text" name="" id="searchAddress" placeholder="地址" style="width:250px"
				class="input-text">
				
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		MAC：<input name="" id="searchMac" type="text" class="input-text" style="width:250px" placeholder="MAC">
			
		</div>
		
		<br>
		<div class="text-x" style="text-align: left;">
			
			 &nbsp;
			 	申请日期：<input type="text"
				onfocus="WdatePicker()" placeholder="申请日期"
				id="startDate" class="input-text Wdate" style="width:250px;" autocomplete="off">
			
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			审核日期：<input type="text"
				onfocus="WdatePicker()" placeholder="审核日期"
				id="checkDate" class="input-text Wdate" style="width:250px;" autocomplete="off">
			&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button name="" id="search" class="btn btn-success" onclick="search()" >
				<i class="Hui-iconfont">&#xe665;</i>搜索
			</button>
			
		</div>
		   
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
					
					
		    </span>
		</div>
		<%-- <%} %> --%>
		<div class="mt-20">
			<table width="100%"
				class="table table-border table-bordered table-bg table-hover table-sort" id="deviceList">
				<thead>
					<tr class="text-c">
						<th width="15" ><input name="" type="checkbox" ></th>
						<th width="80">网点</th>
						<th width="100">详细地址</th>
						<th width="80">MAC</th>
						<th width="60">审核日期</th>
						<th width="50">是否绑定证书</th>
						<th width="40">医保制册机</th>
						<th width="40">居住证签注机</th>
						<th width="40">居住证制卡机</th>
						<th width="40">审核信息</th>
						<th width="20">申请表</th>
						
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
var user= "<%= (String)request.getSession().getAttribute("loginName")%>";
<%-- var companyId = "<%= (Object)request.getSession().getAttribute("companyCodeId")%>"; --%>
var table = $('#deviceList').dataTable({
     "aoColumnDefs": [
      {"orderable":false,"aTargets":[0]}// 制定列不参与排序
    ],
    "order": [[ 4, "desc" ]],
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
                
                { "data": "stDeviceName",
                "render":function(data, type, full, meta){
                      return  setNullData(data);
                  }},
                { "data": "stDeviceAddress",
                   "render":function(data, type, full, meta){
                      return  setNullData(data);
                }},
                { "data": "stDeviceMac",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                  { "data": "dtUpdate",
                "render":function(data, type, full, meta){
                       return setDateTime(data);
                  }},
                { "data": "stMachineId",
                "render":function (data, type, full, meta){
                      return  "暂无";
                }},
                { "data": "stCertKey",
                   "render":function(data, type, full, meta){
                       return setNullDataStatus(data);
                }},
                 { "data": "stChannel",
                "render":function(data, type, full, meta){
                      return setNullDataStatus(data);
                  }},
                   { "data": "stConfigId",
                "render":function(data, type, full, meta){
                      return setNullDataStatus(data);
                  }},
                   
                {
                 "data": "nmOrder",
                 "render": function (data, type, full, meta) {
                        if(data == 2 ){
		                   	var returnInfo = "<a style=\"text-decoration:none;color:green\" class=\"ml-5\" href=\"javascript:;\" title=\"通过\">通过</a>";
		                 	return returnInfo;
	                 	}else if(data==3){
		                  	var returnInfo = "<a style=\"text-decoration:none; color:red\" class=\"ml-5\" href=\"javascript:;\" title=\"不通过\">不通过</a>";
		                	return returnInfo;
	                    }
	               },
                  "bSortable": false
                },
                 {
                 "data": "stDeviceId",
                 "render": function (data, type, full, meta) {
		                 	var returnInfo = "<a style=\"text-decoration:none;color:blue;\" class=\"ml-5\" onClick=\"applyinfo('查看申请表','/serverApply/selmServerItem/applyInfo.do','"+data+"')\" href=\"javascript:;\" title=\"查看申请表\"><u>查看</u></a>";
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
                     $(row).children('td').eq(9).attr('style', 'text-align: center;');
                     $(row).children('td').eq(10).attr('style', 'text-align: center;');
                },
      "bPaginate": true, //翻页功能
      "bLengthChange": true ,//改变每页显示数据数量
       "ajax": {
		    url:webRoot+'/serverApply/selmServerApply/checkRecordsList.do',
		    type:"POST",
		    data: function(d){
		   	 d.stItemId = '<%=stItemId%>';
              var searchDeviceName = $("#searchDeviceName").val();
              d.searchDeviceName = searchDeviceName;
              var searchAddress = $("#searchAddress").val();
              d.searchAddress = searchAddress;
              var searchMac = $("#searchMac").val();
              d.searchMac = searchMac;
              var startDate = $("#startDate").val();
              d.startDate = startDate;
              var checkDate = $("#checkDate").val();
              d.checkDate = checkDate;
		    },
		    error:function(){
		      window.parent.location.reload();
		    }
	    }
}); 

$(function(){
    $("#deviceList").removeAttr("style"); 
});


// 查找
function search () {
    table.api().ajax.reload();
}


// 未配置数据
function setNullData(data){
    if(data == ''){
        return "";
    } else {
        return data;
    }
}

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

function setNullDataStatus(data){
	    if(data == 1) {
	        return '是';
	    }else if(data == 0){
	    	return '否';
	    }
	}

 function setDateFormat(data){
	 if(data != null){
	 	return (new Date(parseFloat(data.time))).format("yyyy-MM-dd");
	 }else{
	 	return '';
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
        
        
        
// 查看申请表
function applyinfo(title,url,id){
//console.log('id:'+id);
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_DEVICE_ID='+id
    });
    layer.full(index);
}


</script>
</body>
</html>