<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.wondersgroup.delivery.bean.SelmDelivery"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="coral.base.util.StringUtil"%>
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
<%--	href="https://at.alicdn.com/t/font_1700164_scb7ybg4fd.css" />--%>

<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>证照柜</title>
</head>
<style type="text/css">
		th,td {
			width: 18% !important;
    		text-align: center !important;
    		cursor: pointer;
    		/* border-left: 1px solid #ddd !important;
    		border-right: 1px solid #ddd !important; */
    		border: 1px solid #ddd !important;
		}
		table{
		border:none !important; 
		}
		td:hover{
			color:#06c;
		}
		td{
    	text-align:center;
    	word-wrap:break-word;
    	word-break:break-all;
    	white-space:normal;
    	/* max-width:100px; */
		}
	
</style>
<body ng-app="lesson" ng-controller="oneCtrl">
	<div class="page-container">
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
					<a class="btn btn-primary radius" onclick="selmDelivery_edit_add()" href="javascript:;">
					     <i class="Hui-iconfont">&#xe600;</i> 添加</a> 
		    </span>
		</div>

		<div class="mt-20">
			<table width="100%"
				class="table table-border "
				id="table" border="2">
				<tbody>
					<tr style="width: 100%;display: flex;flex-wrap: wrap;">
						<td ng-repeat="item in ItemLength">{{item.stCabinetNo}}</td>
						<!-- <span id="{{item.stCabinetNo}}" style="display:none">{{item.stDeliveryId}}</span> -->
					</tr>
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
		src="<%=webRoot%>/infopub/deviceinfo/js/angular.js"></script>
	<script type="text/javascript">
var app=angular.module("lesson",[]);
//var machineId = '<%=selmDelivery.getStMachineId()%>';

var machineId = '00-E2-69-1C-8F-17';
app.controller("oneCtrl",function($scope){
  $scope.getAreaInfo = function() {
  	$.ajax({
  		type: 'get',
  		//dataType: 'jsonp',
  		jsonp: 'jsonpCallback',
  		url: webRoot+"/delivery/selmDelivery/list.do",
  		data: {
  			//stMachineId:machineId || '',
  			stMachineId:machineId || '',
  		},
  		success: function(dataJson) {
  			var itemName = JSON.parse(dataJson);
  			$scope.ItemLength = itemName.data
  			$scope.$apply();
  		},
  		error: function(err) {
  			console.log(err)
  		}
  	});
  	$('#table tbody').on('click', 'td', function(e) {
				var tdSeq = $(this).parent().find("td").index($(this)[0]); //列号
				//var trName = document.getElementById ("table").rows [trSeq].cells[0].innerHTML;
				var tdName = document.getElementById ("table").rows [0].cells[tdSeq].innerHTML;
				console.log(tdName+"列名");
				var stDeliveryId = $scope.ItemLength[tdSeq].stDeliveryId;
				console.log(stDeliveryId);
				var index = layer.open({
        				type: 2,
        				title: '查看',
        				content: webRoot+'/delivery/selmDelivery/edit.do?ST_DELIVERY_ID='+stDeliveryId,
    				});
    					layer.full(index);
				
				
	});
	console.log($scope.ItemLength);
  }
  $scope.getAreaInfo();
 });
 //添加
function selmDelivery_edit_add(){
    var index = layer.open({
        type: 2,
        title: "快递柜添加",
        content: webRoot+'/delivery/selmDelivery/add.do?ST_MACHINE_ID='+machineId
    });
    layer.full(index);
}
</script>
</body>
</html>