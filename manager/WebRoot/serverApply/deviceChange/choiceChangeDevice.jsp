<%@page import="wfc.service.config.Config"%>
<%@page import="com.wondersgroup.serverApply.bean.SelmDeviceApply"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="coral.base.app.AppContext"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="permission" uri="/sms/permission"%>
<%
	String webRoot = AppContext.webRootPath;
	String deviceApplyId = (String)request.getAttribute("deviceApplyId");
	SelmDeviceApply selmDeviceApply = (SelmDeviceApply) request.getAttribute(SelmDeviceApply.SELM_DEVICE_APPLY);
    if (selmDeviceApply == null)
        selmDeviceApply = new SelmDeviceApply(); 
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
<title>设备添加</title>
</head>
<body>
	
	<div class="page-container">
		
		<div class="text-c" style="text-align: center;">
			&nbsp;&nbsp;&nbsp; MAC：<input type="text" name="" id="devocemac" placeholder="MAC地址" style="width:250px"
				class="input-text">
		   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		   	地址 ：<input type="text" name="" id="deviceaddress" placeholder="地址" style="width:250px" class="input-text" >
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
		   	区域 ：<input type="text" name="" id="devicearea" placeholder="区域" style="width:250px" class="input-text" >
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		
			<button name="" id="search" class="btn btn-primary radius" onclick="search()" >
				<i class="Hui-iconfont">&#xe665;</i> &nbsp;搜索&nbsp;
			</button>
			
			
		</div>
		<br><hr>
	
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
				<div class="btn-group">
				  <span id="a1" class="btn btn-success radius"><a style="text-decoration:none;color:black;" onclick="choiceDevice('已赋能设备','/infopub/deviceinfo/checkItemLinkDevice.do?para=yes')"  href="javascript:;">
					     已赋能设备</a></span>
				  <span id="a2" class="btn radius"><a style="text-decoration:none;color:black;" onclick="choiceDevice('未赋能设备','/infopub/deviceinfo/checkItemLinkDevice.do?para=no')" href="javascript:;">
					     未赋能设备</a></span>
				</div>
		    </span>
		</div>
		<form class="form form-horizontal" id="form-device-add">
		<div class="mt-20">
			<table width="100%"
				class="table table-border table-bordered table-bg table-hover table-sort" id="typeList">
				<thead>
					<tr class="text-c">
						<th width="15" ><input name="" type="checkbox" ></th>
						<th width="40">设备编号</th>
						<th width="40">详细地址</th>
						<th width="40">MAC</th>
						<th width="40">设备类型</th>
						<th width="40">证书信息</th>

					</tr>
				</thead> 
				<tbody>
				</tbody>
			</table>
		</div>
		
		 <div class="row cl" style="text-align: center;">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                    <button onclick="deviceAdd();" type="submit"
                        class="btn btn-primary radius" type="button">
                        <i class="Hui-iconfont">&#xe632;</i>添加已勾选
                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button onClick="layer_close();" class="btn btn-danger radius"
                        type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
                </div>
            </div>
		</form>
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
<%-- var stApplyServerId = "<%=selmServerApply.getStApplyId()%>"; --%>
var user= "<%= (String)request.getSession().getAttribute("loginName")%>";
var userAreaId = '<%= (Object)request.getSession().getAttribute("smsUserStAreaId")%>';
var deviceApplyId = "<%=deviceApplyId%>";
console.log(deviceApplyId);
var table = $('#typeList').DataTable({
     "aoColumnDefs": [
      {"orderable":false,"aTargets":[0]}// 制定列不参与排序
    ],
    "order": [[ 5, "desc" ]],
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
                       return setNullData(data);
                }},
                { "data": "stDeviceAddress",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stDeviceMac",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stDesc",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stDeviceName",
                   "render":function(data, type, full, meta){
                       return "暂无";
                }}
                
            ],
       "createdRow": function( row, data, dataIndex ) {
                    $(row).children('td').eq(0).attr('style', 'text-align: center;');
                    $(row).children('td').eq(1).attr('style', 'text-align: center;');
                    $(row).children('td').eq(2).attr('style', 'text-align: center;');
                    $(row).children('td').eq(3).attr('style', 'text-align: center;');
                    $(row).children('td').eq(4).attr('style', 'text-align: center;');
                    $(row).children('td').eq(5).attr('style', 'text-align: center;');
                   
                         
                },
      "bPaginate": true, //翻页功能
      "bLengthChange": true ,//改变每页显示数据数量
       "ajax": {
		    url:webRoot+'/infopub/deviceinfo/checkItemLinkDevice.do',
		    type:"POST",
		    data: function(d){
		      d.para = 'yes';
		      var deviceMac = $("#devocemac").val();
		      d.deviecMac = deviceMac;
		      var deviceAddress = $("#deviceaddress").val();
		      d.deviceAddress = deviceAddress;
		      var deviceArea = $("#devicearea").val();
		      d.deviceArea = deviceArea;
		      d.user = user;
		      d.userAreaId = userAreaId;
		    },
		    error:function(){
		      window.parent.location.reload();
		    }
	    }
}); 

$(function(){
    $("#typeList").removeAttr("style"); 
});

//查看已赋能或者未赋能的设备
function choiceDevice(title,url1) {
	$("#devocemac").val("");
	$("#deviceaddress").val("");
	table.ajax.url(webRoot+url1).load();
}


// 查找
function search () {
    table.ajax.reload();
}


// 批量添加
function deviceAdd () {
    var selectList = $(".checkchild:checked");
    var idArray = [];
    // 未选择的场合
    if (selectList.length == 0) {
        layer.msg('请选择需要添加的设备',{icon:2,time:1000});
        return;
    }
    for (var i = 0; i< selectList.length; i++){
        idArray.push(selectList[i].value);
    } 
    //console.log("1:"+idArray);
    deviceAddress_add(idArray);  
}

function deviceAddress_add(idArray){
	//console.log("stApplyServerId:"+stApplyServerId);
	//console.log("2:"+idArray);
	 $.ajax({
            type : 'get',
            url : webRoot+'/serverApply/selmDeviceAlink/deviceChangeSave.do',
            dataType:'json',
            data:{'stDeviceIdList':JSON.stringify(idArray),
            	  'deviceApplyId':deviceApplyId},
            success : function(myObject) {
                 layer.msg('所选择的设备已添加成功',{icon:1,time:1000});
            },
            error : function() {
               layer.msg('所选择的设备未添加成功',{icon:1,time:1000});
            }
        });
        window.parent.location.reload();
}


// 未配置数据
function setNullData(data){
    if(data == ''){
        return '<span class=\"label label-danger radius\"></span>';
    } else {
        return data;
    }
}

$("#a1").click(function(){
	$("#a1").addClass("btn-success");	
	$("#a2").removeClass("btn-success");	
});
$("#a2").click(function(){
	$("#a2").addClass("btn-success");	
	$("#a1").removeClass("btn-success");	
});
</script>
</body>
</html>