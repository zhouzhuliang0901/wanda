<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.statistics.bean.SelmStatistics"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="permission" uri="/sms/permission"%>
<%
	String webRoot = AppContext.webRootPath;
	 SelmStatistics selmStatistics = (SelmStatistics) request.getAttribute(SelmStatistics.SELM_STATISTICS);
    if (selmStatistics == null)
        selmStatistics = new SelmStatistics(); 
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
<title>访问量(按天)</title>
</head>
<style type="text/css">
	
		th,td {
			width: 6% !important;
    		text-align: center !important;
    		cursor: pointer;
		}
		td:hover{
			color:#06c;
		}
	
</style>
<body ng-app="lesson" ng-controller="oneCtrl">
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		统计分析 <span class="c-gray en">&gt;</span>设备统计 <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
	</nav>
	<div class="page-container">

		<div class="text-c" style="text-align: left;">
			<!-- 日期范围： <input type="text"
				onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'startDate\')||\'%y-%M-%d \'}'})"
				id="startDate" class="input-text Wdate" style="width:120px;" autocomplete="off"> -
			<input type="text"
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'endDate\')}',maxDate:'%y-%M-%d'})"
				id="endDate" class="input-text Wdate" style="width:120px;" autocomplete="off">-->
			<input type="text" name="" id="searchName" placeholder="所属区划"
				style="width:250px" class="input-text" ng-model="inputPosition">
			<button name="" id="search" class="btn btn-success"
				ng-click="getAreaInfo(inputPosition)">
				<i class="Hui-iconfont">&#xe665;</i> 搜索
			</button>
		</div>

		<!-- <div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
					<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius">
					     <i class="Hui-iconfont">&#xe6e2;</i>
							批量删除</a>
		    </span>
		</div> -->

		<div class="mt-20">
			<table width="100%"
				class="table table-border table-bordered table-bg table-hover table-sort"
				id="table">
				<tbody>
					<tr class="text-c" style="background-color: aliceblue;">
						<th>区划</th>
						<th colspan={{zlength}} style="text-align: center;">政务终端</th>
						<th colspan={{slength}} style="text-align: center;">社会化终端</th>
						<th  style="pointer-events: none;">合计</th>
					</tr>
					<tr>
						<td style="pointer-events: none;"></td>
						<td style="pointer-events: none;" ng-repeat="name in Name">{{name.typeName}}</td>
						<td style="pointer-events: none;"></td>
					</tr>
					<tr ng-repeat="item in Item">
						<td>{{item.area}}</td>
						<td style="pointer-events: none;" ng-repeat="type in item.type">{{type.code}}</td>
						<td style="pointer-events: none;">{{item.sum}}</td>
					</tr>
					<tr>
						<td id="zongji" style="pointer-events: none;">总计</td>
						<td ng-repeat="name in Name" style="pointer-events: none;">{{name.count}}</td>
						<td style="pointer-events: none;">{{allSum}}</td>
					</tr>
				</tbody>
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
		src="<%=webRoot%>/infopub/deviceinfo/js/angular.js"></script>
	<script type="text/javascript">
var app=angular.module("lesson",[]);
var areaId = "<%= (Object)request.getSession().getAttribute("smsUserStAreaId")%>";
var permission = "<%= (Object)request.getSession().getAttribute("权限")%>";
app.controller("oneCtrl",function($scope){
	
  	$scope.getAreaInfo = function(stAddressName) {
  	stAddressName = encodeURI(stAddressName);
  	var url = "";
  	if(stAddressName=="undefined"){
  		stAddressName='';
  		url = "/selmBigscreenCache/info.do";
  	}else{
  		url = "/statistics/selmStatistics/addresslistTypeDevice.do"
  	}
 
  	$.ajax({
  		type: 'get',
  		//dataType: 'jsonp',
  		jsonp: 'jsonpCallback',
  		url: webRoot+url,
  		data: {
  			stAddressName:stAddressName || '',
  			stPermission:permission || '',
  			stAreaId:areaId || '',
  			fCode:'statistics',
  			sCode:'selmStatistics',
  			tCode:'addresslistTypeDevice.do'
  		},
  		success: function(dataJson) {
			var itemName = JSON.parse(dataJson);
			$scope.zlength = itemName.data[0].nmType[0].type.length;
			$scope.slength = itemName.data[1].nmType[0].type.length;
			$scope.Name = itemName.data[0].nmType[0].type.concat(itemName.data[1].nmType[0].type);
			for(var i = 0; i < itemName.data[1].nmType.length; i++){
				itemName.data[0].nmType[i].type = itemName.data[0].nmType[i].type.concat(itemName.data[1].nmType[i].type);
			}
			$scope.Item = itemName.data[0].nmType;
			//合计
			var allSum = 0;
			for (var i = 0; i < $scope.Item.length; i++) {
				//console.log($scope.Item[i]);
				var sum = 0;
				for (var j = 0; j < $scope.Item[i].type.length; j++) {
					sum += $scope.Item[i].type[j].code
				}
				$scope.Item[i].sum=sum;
				allSum +=sum;
			}
			$scope.allSum= allSum;
						//总计
				for (var i = 0; i < $scope.Item[0].type.length; i++) {
					var count = 0;
					for (var j = 0; j < $scope.Item.length; j++) {
						count += $scope.Item[j].type[i].code
					}
					$scope.Name[i].count=count;
			}
  			$scope.$apply();
  		},
  		error: function(err) {
  			console.log(err)
  		}
  	});
  }
  $scope.getAreaInfo();
 });
  		$('#table tbody').on('click', 'td', function(e) {
				var tdSeq = $(this).parent().find("td").index($(this)[0]); //列号
				var trSeq = $(this).parent().parent().find("tr").index($(this).parent()[0]); //行号
				var trName = document .getElementById ("table").rows [trSeq].cells[0].innerHTML;
				var tdName = document .getElementById ("table").rows [1].cells[tdSeq].innerHTML;
				console.log(trName+"列名");
				console.log(tdName+"行名");
			if(tdName==""){
				trName = encodeURI(encodeURI(trName));
				var index = layer.open({
        				type: 2,
        				title: '街道统计',
        				content: webRoot+'/statistics/statistics/streetInfo.do?areaName='+trName,
    				});
    					layer.full(index);
			}/* else{
				trName = encodeURI(encodeURI(trName));
				tdName = encodeURI(encodeURI(tdName));
				 var index = layer.open({
        				type: 2,
        				title: '设备信息',
        				content: webRoot+'/infopub/deviceinfo/addressdeviceInfo.do?areaName='+trName+'&typeName='+tdName,
    				});
    					layer.full(index);
				}  */
				
				
	});
</script>
</body>
</html>