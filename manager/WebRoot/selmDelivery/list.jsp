<%@page import="wfc.service.config.Config"%>
<%@page import="com.wondersgroup.delivery.bean.SelmDelivery"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="coral.base.app.AppContext"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="permission" uri="/sms/permission"%>
<%
	 String webRoot = AppContext.webRootPath;
    SelmDelivery selmDelivery = (SelmDelivery) request.getAttribute(SelmDelivery.SELM_DELIVERY);
    if (selmDelivery == null)
        selmDelivery = new SelmDelivery();  
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
<title>???????????? </title>
</head>
<body>
	<!-- <nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> ?????? <span class="c-gray en">&gt;</span>
		???????????? <span class="c-gray en">&gt;</span>?????????<a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="??????"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
	</nav> -->
	<div class="page-container">
		
		<div class="text-c" style="text-align: left;">
			??????????????? &nbsp;&nbsp;<input type="text"
				onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'startDate\')||\'%y-%M-%d \'}'})"
				id="startDate" class="input-text Wdate" style="width:120px;" autocomplete="off"> -
			<input type="text"
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'endDate\')}',maxDate:'%y-%M-%d'})"
				id="endDate" class="input-text Wdate" style="width:120px;" autocomplete="off">
		  &nbsp;&nbsp;??????????????????<input type="text" name="" id="searchSenderName" placeholder="???????????????" style="width:250px"
				class="input-text">
				</div>	
		<br>
		<div class="text-x" style="text-align: left;">
		 ??????????????????<input type="text" name="" id="searchRecriverName" placeholder="???????????????" style="width:250px"
				class="input-text">
		&nbsp;&nbsp;????????????????????????<input type="text" name="" id="searchRecriverIdcard" placeholder="?????????????????????" style="width:250px"
				class="input-text">
			&nbsp;&nbsp;&nbsp;<button name="" id="search" class="btn btn-success" onclick="search()" >
				<i class="Hui-iconfont">&#xe665;</i> ??????
			</button>
		</div>
		
		<%-- <div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
					<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius">
					     <i class="Hui-iconfont">&#xe6e2;</i>
							????????????</a>
					<a class="btn btn-primary radius" onclick="selmDelivery_edit_add('???????????????','/delivery/selmDelivery/add.do','<%=selmDelivery.getStMachineId()%>')" href="javascript:;">
					     <i class="Hui-iconfont">&#xe600;</i> ??????</a> 
		    </span>
		</div> --%>
		
		<div class="mt-20">
			<table width="100%"
				class="table table-border table-bordered table-bg table-hover table-sort" id="typeList">
				<thead>
					<tr class="text-c">
						<th width="15" ><input name="" type="checkbox" ></th>
						<!-- <th width="80">??????ID</th> -->
						<th width="40">????????????</th>
						<th width="40">???????????????</th>
						<th width="40">??????????????????</th>
						<th width="40">???????????????</th>
						<th width="40">?????????????????????</th>
						<th width="40">??????????????????</th>
						<th width="40">??????</th>
						<th width="40">????????????</th>
						<th width="40">????????????</th>
						<th width="40">????????????</th>
						<th width="20">??????</th>
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
    "order": [[ 8, "desc" ]],
    "processing" : true,  
    "serverSide" : true,  
    "searching": false,
    "paginationType":"full_numbers",
    "aoColumns": [
                {
                 "sClass": "text-center",
                 "data": "stDeliveryId",
                 "render": function (data, type, full, meta) {
                     return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
                    },
                  "bSortable": false
                },
                { "data": "stCabinetNo",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stReceiverName",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stReceiverPhone",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stSenderName",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stSenderId",
                   "render":function(data, type, full, meta){
                       return setidCardData(data);
                }},
                { "data": "stSenderPhone",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "nmStatus",
                   "render":function(data, type, full, meta){
                      return  setNullStatus(data);
                }},
                { "data": "dtCreate",
                "render":function (data, type, full, meta){
                       return setDateTime(data);
                }},
                { "data": "dtStore",
                "render":function (data, type, full, meta){
                       return setDateTime(data);
                }},
                 { "data": "dtTake",
                "render":function (data, type, full, meta){
                       return setDateTime(data);
                }},
                {
                 "data": "stDeliveryId",
                 "render": function (data, type, full, meta) {
                        var returnInfo = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"selmDelivery_info('"+full.stCabinetNo+"->??????','/delivery/selmDelivery/info.do','"+data+"')\" href=\"javascript:;\" title=\"??????\"><i class=\"icon iconfont icon-chakan\"></i></a>";
                            //returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"selmDelivery_edit('"+full.stCabinetNo+"->??????','/delivery/selmDelivery/edit.do','"+data+"')\" href=\"javascript:;\" title=\"??????\"><i class=\"icon iconfont icon-edit\"></i></a>";
                            //returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"selmDelivery_del(this,'"+data+"')\" href=\"javascript:;\" title=\"??????\"><i class=\"icon iconfont icon-shanchu-copy\"></i></a>";
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
                    $(row).children('td').eq(11).attr('style', 'text-align: center;');
                    $(row).children('td').eq(12).attr('style', 'text-align: center;');
                    $(row).children('td').eq(13).attr('style', 'text-align: center;');
                    $(row).children('td').eq(14).attr('style', 'text-align: center;');
                    $(row).children('td').eq(15).attr('style', 'text-align: center;');
                    $(row).children('td').eq(16).attr('style', 'text-align: center;');
                },
      "bPaginate": true, //????????????
      "bLengthChange": true ,//??????????????????????????????
       "ajax": {
		    url:webRoot+'/delivery/selmDelivery/list.do',
		    type:"POST",
		    data: function(d){
		      var stMachineId = '<%=selmDelivery.getStMachineId()%>';
		      d.stMachineId = stMachineId;
		      var stReceiverName = $("#searchRecriverName").val();
		      d.stReceiverName = stReceiverName;
		      var stReceiverIdcard = $("#searchRecriverIdcard").val();
		      d.stReceiverIdcard = stReceiverIdcard;
		      var stSenderName = $("#searchSenderName").val();
		      d.stSenderName = stSenderName;
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
    $("#typeList").removeAttr("style"); 
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
    
    selmDelivery_del('',idArray);
}
// ??????
function device_add(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}

// ??????
function selmDelivery_info(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_DELIVERY_ID='+id
    });
    layer.full(index);
}
// ??????
function selmDelivery_edit(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_DELIVERY_ID='+id
    });
    layer.full(index);
}
//??????
function selmDelivery_edit_add(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_MACHINE_ID='+id
    });
    layer.full(index);
}
// ??????
function selmDelivery_del(obj,id){
console.log(id)
    layer.confirm('?????????????????????',function(index){
         $.ajax({
            type : 'POST',
            url : webRoot+'/delivery/selmDelivery/remove.do',
            dataType:'json',
            data:{'stDeliveryId':id},
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
function setNullType(data){
    if(data == '0'){
        return '??????';
    } else {
        return '??????';
    }
}
function setNullStatus(data){
    if(data == '0'){
        return '??????';
    } else if(data == '1') {
        return '??????';
    }else{
     	return '??????';
    }
}

// ????????????
function setMobileData(data){
    if(data == ''){
        return '';
    } else {
    	return data.replace(/^(.{3})(?:\w+)(.{4})$/, "\$1****\$2");
    }
}

// ????????????
function setidCardData(data){
    if(data == ''){
        return '';
    } else {
    	return data.replace(/^(.{1})(?:\w+)(.{1})$/, "\$1****************\$2");
    }
}
</script>
</body>
</html>