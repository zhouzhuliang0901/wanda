<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="coral.base.app.AppContext"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="permission" uri="/sms/permission"%>
<%
	String webRoot = AppContext.webRootPath;
	String stDeviceId = (String)request.getAttribute("stDeviceId");
	String stApplyId = (String)request.getAttribute("stApplyId");
	String itemCount = (String)request.getAttribute("itemCount");
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
<%--    href="https://at.alicdn.com/t/font_1743964_iij8bsmpiw.css" />--%>
    
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>?????????????????? </title>
</head>
<body>
	
	<div class="page-container">
		
		<div class="text-c" style="text-align: left;">
			&nbsp;&nbsp;???????????????<input type="text" name="" id="searchOrganName" placeholder="????????????" style="width:250px"
				class="input-text">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			???????????????<input type="text" name="" id="searchItemName" placeholder="????????????" style="width:250px"
				class="input-text">
				
		</div>
		<br>
		<div class="text-c" style="text-align: left;">
		   
				&nbsp;&nbsp; ???????????????<input type="text" name="" id="searchItemCode" placeholder="????????????" style="width:250px"
				class="input-text">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 ???????????????<select type="text" name="searchStatus" id="searchStatus" placeholder="????????????" style="width:250px"
				class="input-text">
					<option></option>
					<option value=0>?????????</option>
					<option value=1>?????????</option>
					<option value=2>??????</option>
					<option value=3>?????????</option>
				</select>
				
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button name="" id="search" class="btn btn-primary radius" onclick="search()" >
					<i class="Hui-iconfont">&#xe665;</i> &nbsp;??????&nbsp;
				</button>
		</div>		
		
		<div class="text-c" style="text-align: left;">
			
		</div>
		
		<br><hr>
		
		
		<div class="mt-20">
			<table width="100%"
				class="table table-border table-bordered table-bg table-hover table-sort" id="typeList">
				<thead>
					<tr class="text-c">
						
						<th width="15"><input id="" name="" type="checkbox"></th>
						<th width="40">????????????</th>
						<th width="80">????????????</th>
						<th width="80">????????????</th>
						<th width="40">????????????</th>
						<th width="40">????????????</th>
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

var table = $('#typeList').dataTable({
     "aoColumnDefs": [
      {"orderable":false,"aTargets":[0]}// ????????????????????????
    ],
    "order": [[ 5, "desc" ]],
    "processing" : true,  
    "serverSide" : true,  
    "searching": false,
    "paginationType":"full_numbers",
    "aoColumns": [
                {
                 "sClass": "text-center",
                 "data": "stItemId",
                 "render": function (data, type, full, meta) {
                     return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
                    },
                  "bSortable": false
                },
                { "data": "stOrganId",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stItemName",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stItemNo",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "dtAudit",
                   "render":function(data, type, full, meta){
                       return setDateTime(data);
                }},
                {
                 "data": "nmStatus",
                 "render": function (data, type, full, meta) {
                 		if(data == 2){
                       		var returnInfo = "<a style=\"text-decoration:none;color:green\" \" href=\"javascript:;\" title=\"??????\">??????</a>";
                 		}else if(data == 3){
                            var returnInfo = "<a style=\"text-decoration:none;color:red\" class=\"ml-5\" \" href=\"javascript:;\" title=\"?????????\">?????????&nbsp;&nbsp;</a>";
                            	returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"reason_info('???????????????','/serverApply/selmServerApply/checkReason.do','"+full.stItemId+"')\" href=\"javascript:;\" title=\"???????????????\"><i class=\"Hui-iconfont\">???????????????</i></a>";  
                 		}else if(data == 0){
                            var returnInfo = "<a style=\"text-decoration:none;color:blue\" class=\"ml-5\"  href=\"javascript:;\" title=\"?????????\">?????????</a>";
                 		}else{
                 			var returnInfo = "<a style=\"text-decoration:none;color:blue\" class=\"ml-5\"  href=\"javascript:;\" title=\"?????????\">?????????</a>";
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
                    
                },
      "bPaginate": true, //????????????
      "bLengthChange": true ,//??????????????????????????????
       "ajax": {
		   	url:webRoot+'/serverApply/selmServerApply/checkDeviceWithItem.do',
		    type:"get",
		    data: function(d){
		     var searchItemName = $("#searchItemName").val();
		      d.searchItemName = searchItemName;
		       var searchItemCode = $("#searchItemCode").val();
		      d.searchItemCode = searchItemCode;
		      var searchStatus = $("#searchStatus").val();
		      d.searchStatus = searchStatus;
		      var searchOrganName = $("#searchOrganName").val();
		      d.searchOrganName = searchOrganName;
		      d.stDeviceId = '<%=stDeviceId%>';
		      d.stApplyId = '<%=stApplyId%>';
		     
		    },
		    error:function(){
		      window.parent.location.reload();
		    }
	    }
}); 

$(function(){
    $("#typeList").removeAttr("style"); 
});



// ??????
function search () {
    table.api().ajax.reload();
}

// ??????
function server_info(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_ITEM_ID='+id,
    });
    layer.full(index);
}

function reason_info(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?stItemId='+id+'&stDeviceId='+'<%=stDeviceId%>'+'&stApplyId='+'<%=stApplyId%>',
    });
    layer.full(index);
}
function server_down(url1,id){
var url = webRoot+"/serverApply/selmServerApply/download.do?stAttachId="+id;
location.href=url;
}

// ???????????????
function setNullData(data){
    if(data == ''){
        return '<span class=\"label label-danger radius\"></span>';
    } else {
        return data;
    }
}

function setDateFormat(data){
 return (new Date(parseFloat(data.time))).format("yyyy-MM-dd");
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

//??????
function reset () {
	$("#searchItemName").val('');
	$("#searchItemCode").val('');
	$("#searchOrganName").val('');
    table.api().ajax.reload();
}
</script>
</body>
</html>