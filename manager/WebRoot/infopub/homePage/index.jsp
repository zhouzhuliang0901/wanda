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
<!DOCTYPE html>
<html ng-app="myApp">
	<head>
		<meta charset="utf-8" />
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/home.css"/>
		<script src="js/jquery-3.5.1.min.js"></script>
		<script src="../../sms/login/js/scrollbar.min.js" type="text/javascript"></script>
		<script src="../../sms/login/js/workstation.js" type="text/javascript"></script>
		<script type="text/javascript">var webRoot = '<%=StringEscapeUtils.escapeJavaScript(webRoot) %>';</script>
		<script src="js/angular.js"></script>
		<script src="js/controller.js"></script>
	</head>
	<body ng-controller="devicesCtrl">
		<!--顶部-->
		<div class="top">
			<div class="left">
				<div class="content">
					<img src="img/top_1.png" alt="" />
					<span>&nbsp;已接入终端&nbsp;{{deviceSum}}&nbsp;台</span>
				</div>
				<div class="content">
					<img src="img/top_2.png" alt="" />
					<span>&nbsp;政务终端&nbsp;{{govSum}}&nbsp;台</span>
				</div>
				<div class="content">
					<img src="img/top_3.png" alt="" />
					<span>&nbsp;社会化终端&nbsp;{{socialSum}}&nbsp;台</span>
				</div>
			</div>
			<div class="right shell-map">
				<img src="img/top_4.png" alt="" />
				<span ng-click='map()' id="pagemap">&nbsp;设备地图</span>
				<!-- <a target="mainIframe" _href="/ac-self-manager/infopub/deviceinfo/mapPage.jsp" data-title="设备地图" index="ifillform_device_placeShow">设备地图
				</a> -->
			</div>
		</div>
		<!--总量-->
		<div class="totalNum">
			<div class="zx">
				<div>
					<p>终端在线</p>
					<p>{{online}}</p>
				</div>
				<img src="img/zx.png" alt="" />
			</div>
			<div class="lx">
				<div>
					<p>终端离线</p>
					<p>{{noOnLine}}</p>
				</div>
				<img src="img/lx.png" alt="" />
			</div>
			<div class="jr">
				<div>
					<p>已接入事项</p>
					<p>{{selmItemSum}}</p>
				</div>
				<img src="img/jr.png" alt="" />
			</div>
			<div class="mz">
				<div>
					<p>民政接入事项</p>
					<p>102</p>
				</div>
				<img src="img/mz.png" alt="" />
			</div>
		</div>
		<!--目前量-->
		<div class="currentNum">
			<div class="content">
				<img src="img/wh.png" alt="" />
				<span>&nbsp;维护事项数&emsp;0</span>
			</div>
			<div class="content">
				<img src="img/yc.png" alt="" />
				<span>&nbsp;终端异常数&emsp;{{Excount}}</span>
			</div>
			<div class="content">
				<img src="img/today.png" alt="" />
				<span>&nbsp;当天办件量&emsp;{{SelmQueryDaySum}}</span>
			</div>
			<div class="content">
				<img src="img/month.png" alt="" />
				<span>&nbsp;本月办件量&emsp;{{SelmQuerySum}}</span>
			</div>
		</div>
		<!--详情-->
		<div class="detail">
			<!--区30天-->
			<div class="area" style="overflow:auto;">
				<div class="nav">
					<img src="img/list.png" alt="" />
					<span>各区30天使用量<span class="small">&emsp;(排名不分先后)</span></span>
				</div>
				<div class="content" >
					<div ng-repeat="item in AreaInfo">
						<span>{{item.stDistrict}}</span>
						<div class="useNum" style="width: {{item.stLabel}};"></div>&nbsp;&nbsp;{{item.stStreet}}
					</div>
				</div>
			</div>
			<!--top20-->
			<div class="top20" style="overflow:auto;">
				<div class="nav">
					<img src="img/list.png" alt="" />
					<span>热门事项</span><span class="red">&emsp;TOP20</span>
				</div>
				<div class="content">
					<table>
						<thead>
							<tr>
								<th>序号</th>
								<th>事项名称</th>
								<th>办件量</th>
							</tr>
						</thead>
						<tbody >
							<tr ng-repeat="item in InfoQuery">
								<td>{{$index+1}}</td>
								<td>{{item.stItemName}}</td>
								<td>{{item.stExt1}}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!--新接入-->
			<div class="new">
				<div class="nav">
					<img src="img/list.png" alt="" />
					<span>新接入申请</span>
				</div>
				<div class="content">
					<table >
						<thead>
							<tr>
								<th>序号</th>
								<th>标题</th>
								<th>提交人</th>
							</tr>
						</thead>
						
						<tbody  id="table" ng-if="!show">
							<tr ng-repeat="item in ApplyInfo">
								<td style="pointer-events: none;">{{$index+1}}</td>
								<td>{{item.stApplyTitle}}</td>
								<td style="pointer-events: none;">{{item.stApplyUserName}}</td>
							</tr>
							<tbody ng-if="show">
							<tr>
								<td colspan="3">暂无新接入申请</td>
							</tr>
							<tbody>
						</tbody>
					</table>
				</div>
			</div>
			<!--警报-->
			<div class="warn">
				<div class="nav">
					<img src="img/list.png" alt="" />
					<span>维护事项查看</span>
				</div>
				<div class="content">
					<table>
						<thead>
							<tr>
								<th>序号</th>
								<th>事项名称</th>
								<th>故障时间</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="3">暂无维护事项</td>
								<!-- <td></td>
								<td></td>  -->
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</body>
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
</html>
