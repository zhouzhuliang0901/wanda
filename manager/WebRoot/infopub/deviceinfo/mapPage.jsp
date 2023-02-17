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
<html ng-app="myApp">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<%-- <link rel="stylesheet" type="text/css" href="https://at.alicdn.com/t/font_1700164_scb7ybg4fd.css" />--%>
 <title>自助终端管理系统</title>
		<link rel="stylesheet" href="css/style.css" />
		<link rel="stylesheet" href="css/loading.css" />
		<script type="text/javascript" src="js/mapKey.js"></script>
		<script type="text/javascript" src="js/TextIconOverly.js"></script>
		<script type="text/javascript" src="js/MarkerClusterer.js"></script>
		<script src="js/jquery-3.5.1.min.js"></script>
		<script src="./js/angular.js"></script>
		<style>
			.orange,
			.gray,
			.green {
				position: absolute;
				z-index: -2;
				left: 0%;
				top: -100%;
			}
			
			#slider {
				background-color: #fff;
			}
			
			.BMapLabel {
				top: -4px !important;
				left: -24px !important;
				border-radius: 4px;
			}
			
			body,
			html {
				width: 100%;
				height: 100%;
				margin: 0;
			}
			
			#allmap {
				width: 100%;
				height: 100%;
			}
			
			.BMap_pop>div:nth-child(9) {
				top: -24px !important;
				left: -5px !important;
				width: 480px !important;
				height: 460px !important;
				border-radius: 15px;
			}
			
			.BMap_pop>div:nth-child(1),
			.BMap_pop>div:nth-child(2),
			.BMap_pop>div:nth-child(3),
			.BMap_pop>div:nth-child(5),
			.BMap_pop>div:nth-child(6),
			.BMap_pop>div:nth-child(7),
			.BMap_pop>div:nth-child(8),
			.BMap_pop>img,
			.BMap_center {
				width: 0 !important;
				height: 0 !important;
			}
			.anchorBL {
			display: none;
		}
		dl{
          
           display: none;
           margin-top: -20px;
           font-size:10px;
           text-align: center;
       		}
       	#type{
       	background-color:#fff;
       	position: absolute;
       	margin-left: 45rem;
    	width: 10rem;
    	text-align: center;
    	top: 27px;
   		box-shadow: 6px 4px 4px 0px rgba(0, 0, 0, 0.2);
       	}
        dt:hover{
        color: #30A6E6;
        }
        #type:hover dl{
           display: block;
       }
		</style>
	</head>

	<body ng-controller="devicesCtrl" id="loading-content">
		<div id="allmap"></div>
		<div class="navSearch">
			<section class="nav">
				<div class="navs">
					<div ng-click="getOnlineOrNot()"><img src="img/all.png" alt="">&nbsp全部<span  class="qb" style="color: #5e9bff;">&nbsp{{qbNum}}</span></div>
					<div ng-click="getOnlineOrNot(1)"><img src="img/zaixian.png" alt="">&nbsp在线<span  class="zx" style="color: #20a564;">&nbsp{{zxNum}}</span></div>
					<div ng-click="getWarnDeviceInfo(1)"><img src="img/yichang.png" alt="">&nbsp异常<span  class="ych" style="color: #f60;">&nbsp{{ycNum}}</span></div>
					<div ng-click="getOnlineOrNot(0)"><img src="img/lixian.png" alt="">&nbsp离线<span  class="lx" style="color: #777b91;">&nbsp{{lxNum}}</span></div>
					<div id="type"><img src="img/lexing.png" alt="">类型
						<dl ng-repeat="x in ItemType" >
							<dt data-ng-click="getDeviceInfo('',x.stTypeId)">{{x.stTypeName}}</dt>
						</dl>
					</div>
				</div>
			</section>
			<section class="search">
				<input type="text" placeholder="请输入位置" ng-model="inputPosition">
				<button class="btnSearch" ng-click="getDeviceInfo(inputPosition)"><img src="img/btnSearch.png" alt=""></button>
			</section>
		</div>
		<!--异常的设备-->
		<div class="orange">
			<!--遮罩层-->
			<div class="bgPop2"></div>
			<!--弹出框-->
			<div class="pop2">
				<!-- </div> -->
				<div class="pop-content">
					<span class="pop-close2" id="test"><img src="img/close.png" alt=""></span>
					<div id="slider">
						<ul class="slider_list">
							<li class="li" ng-repeat="item in Item">
								<div class="bar orangeBar">
									<table class="barInfo">
										<tbody>
											<tr>
												<th colspan="2">设备编号<span>{{item.stDeviceCode}}</span><strong class="yichang">异常</strong></th>
												<th rowspan="4"><img ng-src={{item.stConfigId}} alt=""></th>
											</tr>
											<tr>
												<th><img src="img/th1.png" alt=""></th>
												<td>{{item.stDeviceName}}</td>
											</tr>
											<tr>
												<th><img src="img/th2.png" alt=""></th>
												<td>{{item.stDeviceAddress}}</td>
											</tr>
											<tr>
												<th><img src="img/th3.png" alt=""></th>
												<td>可办事项数<span>{{item.nmOrder}}</span>项</td>
											</tr>
										</tbody>
									</table>
								</div>
								<p class="jumpSubTit">外设状况</p>
								<div class="conditions">
									<p ng-if="item.outDevice[0] != 2" ng-class="{'ych':item.outDevice[0] != '0'}"><img src="img/wsh1.png" alt="身份证读卡器" title="身份证读卡器">{{item.outDevice[0] == '0'?'正常':'异常'}}</p>
									<p ng-if="item.outDevice[1] != 2" ng-class="{'ych':item.outDevice[1] != '0'}"><img src="img/wsh2.png" alt="高拍仪" title="高拍仪">{{item.outDevice[1] == '0'?'正常':'异常'}}</p>
									<p ng-if="item.outDevice[2] != 2" ng-class="{'ych':item.outDevice[2] != '0'}"><img src="img/wsh3.png" alt="二维码扫码器" title="二维码扫码器">{{item.outDevice[2] == '0'?'正常':'异常'}}</p>
									<p ng-if="item.outDevice[3] != 2" ng-class="{'ych':item.outDevice[3] != '0'}"><img src="img/wsh4.png" alt="就医记录册打印机" title="就医记录册打印机">{{item.outDevice[3] == '0'?'正常':'异常'}}</p>
									<p ng-if="item.outDevice[4] != 2" ng-class="{'ych':item.outDevice[4] != '0'}"><img src="img/wsh5.png" alt="A4打印机" title="A4打印机">{{item.outDevice[4] == '0'?'正常':'异常'}}</p>
									<p ng-if="item.outDevice[5] != 2" ng-class="{'ych':item.outDevice[5] != '0'}"><img src="img/wsh6.png" alt="居住证签注机" title="居住证签注机">{{item.outDevice[5] == '0'?'正常':'异常'}}</p>
								</div>
								<div class="conditions" ng-if="item.outDevice[0] == 2 && item.outDevice[1] == 2 && item.outDevice[2] == 2 && item.outDevice[3] == 2 && item.outDevice[4] == 2 && item.outDevice[5] == 2">
									<p>暂无外设</p>
								</div>
								<p ng-if="item.outDevice[3] != 2 || item.outDevice[4] != 2" class="jumpSubTit">耗材情况</p>
								<table class="condition2">
									<tbody>
										<tr ng-if="item.outDevice[3] != 2">
											<th><img src="img/jiuyijiluce.png" alt=""></th>
											<th>就医记录册</th>
											<td ng-if="item.outDevice[3] == '0'">剩余<span>{{item.outDevice[6] || 0}}</span>册</td>
											<td ng-if="item.outDevice[3] == '0'">
												<div>
													<p ng-style="{width:item.outDevice[9]}"></p>
												</div>
											</td>
											<td ng-if="item.outDevice[3] == '1'" class="ych">剩余<span>{{item.outDevice[6] || 0}}</span>册</td>
											<td ng-if="item.outDevice[3] == '1'" class="ych">
												<div>
													<p ng-style="{width:item.outDevice[9]}"></p>
												</div>
											</td>
										</tr>
										<tr ng-if="item.outDevice[4] != 2">
											<th><img src="img/dayinzhizhang.png" alt=""></th>
											<th>打印纸张</th>
											<td ng-if="item.outDevice[4] == '0'">剩余<span>{{item.outDevice[7] || 0}}</span>张</td>
											<td ng-if="item.outDevice[4] == '0'">
												<div>
													<p ng-style="{width:item.outDevice[8]}"></p>
												</div>
											</td>
											<td ng-if="item.outDevice[4] == '1'" class="ych">剩余<span>{{item.outDevice[7] || 0}}</span>张</td>
											<td ng-if="item.outDevice[4] == '1'" class="ych">
												<div>
													<p ng-style="{width:item.outDevice[8]}"></p>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</li>
						</ul>
						<div class="slider_icon">
							<i class="currentPage">1</i>
							<i> / </i>
							<i class="totalPage">{{totalPage}}</i>
						</div>
						<a href="javascript:;" class="arrow prve" id="prev">
							<span class="slider_left">&lt;上一台</span>
						</a>
						<a href="javascript:;" class="arrow next" id="next">
							<span class="slider_right">下一台&gt;</span>
						</a>
					</div>
					<!--<div class="roll"></div> -->
				</div>
			</div>
		</div>
		<!--全部的设备-->
		<div class="gray">
			<!--遮罩层-->
			<div class="bgPop3"></div>
			<!--弹出框-->
			<div class="pop3">
				<!-- </div> -->
				<div class="pop-content">
					<span class="pop-close3" id="test"><img src="img/close.png" alt=""></span>
					<div id="slider">
						<ul class="slider_list">
							<li ng-repeat="item in Item">
								<div class="bar" ng-class="{'greenBar':item.nmOnline == '1','grayBar':item.nmOnline == '0','orangeBar':item.nmOnline == '2'}">
									<table class="barInfo">
										<tbody>
											<tr>
												<th colspan="2">设备编号<span>{{item.stDeviceCode}}</span>
													<strong ng-if="item.nmOnline == '0'" class="lixian">离线</strong>
													<strong ng-if="item.nmOnline == '1'">在线</strong>
													<strong ng-if="item.nmOnline == '2'" class="yichang">异常</strong>
												</th>
												<th rowspan="4"><img ng-src={{item.stConfigId}} alt=""></th>
											</tr>
											<tr>
												<th><img src="img/th1.png" alt=""></th>
												<td>{{item.stDeviceName}}</td>
											</tr>
											<tr>
												<th><img src="img/th2.png" alt=""></th>
												<td>{{item.stDeviceAddress}}</td>
											</tr>
											<tr>
												<th><img src="img/th3.png" alt=""></th>
												<td>可办事项数<span>{{item.nmOrder}}</span>项</td>
											</tr>
										</tbody>
									</table>
								</div>
								<p class="jumpSubTit">外设状况</p>
								<div class="conditions" ng-if="item.nmOnline != 2">
									<p ng-if="item.statusList[0] != 3"><img src="img/wsh1.png" alt="身份证读卡器" title="身份证读卡器">{{item.nmOnline == '1'?'正常':'离线'}}</p>
									<p ng-if="item.statusList[1] != 3"><img src="img/wsh2.png" alt="高拍仪" title="高拍仪">{{item.nmOnline == '1'?'正常':'离线'}}</p>
									<p ng-if="item.statusList[2] != 3"><img src="img/wsh3.png" alt="二维码扫码器" title="二维码扫码器">{{item.nmOnline == '1'?'正常':'离线'}}</p>
									<p ng-if="item.statusList[3] != 3"><img src="img/wsh4.png" alt="就医记录册打印机" title="就医记录册打印机">{{item.nmOnline == '1'?'正常':'离线'}}</p>
									<p ng-if="item.statusList[4] != 3"><img src="img/wsh5.png" alt="A4打印机" title="A4打印机">{{item.nmOnline == '1'?'正常':'离线'}}</p>
									<p ng-if="item.statusList[5] != 3"><img src="img/wsh6.png" alt="居住证签注机" title="居住证签注机">{{item.nmOnline == '1'?'正常':'离线'}}</p>
								</div>
								<div class="conditions" ng-if="item.statusList[0] == 3 && item.statusList[1] == 3 && item.statusList[2] == 3 && item.statusList[3] == 3 && item.statusList[4] == 3 && item.statusList[5] == 3">
									<p>暂无外设</p>
								</div>
								<div class="conditions" ng-if="item.nmOnline == 2">
									<p ng-if="item.statusList[0] != 3" ng-class="{'ych':item.statusList[0] == 2}"><img src="img/wsh1.png" alt="身份证读卡器" title="身份证读卡器">{{item.statusList[0] != '2'?'正常':'异常'}}</p>
									<p ng-if="item.statusList[1] != 3" ng-class="{'ych':item.statusList[1] == 2}"><img src="img/wsh2.png" alt="高拍仪" title="高拍仪">{{item.statusList[1] != '2'?'正常':'异常'}}</p>
									<p ng-if="item.statusList[2] != 3" ng-class="{'ych':item.statusList[2] == 2}"><img src="img/wsh3.png" alt="二维码扫码器" title="二维码扫码器">{{item.statusList[2] != '2'?'正常':'异常'}}</p>
									<p ng-if="item.statusList[3] != 3" ng-class="{'ych':item.statusList[3] == 2}"><img src="img/wsh4.png" alt="就医记录册打印机" title="就医记录册打印机">{{item.statusList[3] != '2'?'正常':'异常'}}</p>
									<p ng-if="item.statusList[4] != 3" ng-class="{'ych':item.statusList[4] == 2}"><img src="img/wsh5.png" alt="A4打印机" title="A4打印机">{{item.statusList[4] != '2'?'正常':'异常'}}</p>
									<p ng-if="item.statusList[5] != 3" ng-class="{'ych':item.statusList[5] == 2}"><img src="img/wsh6.png" alt="居住证签注机" title="居住证签注机">{{item.statusList[5] != '2'?'正常':'异常'}}</p>
								</div>
								<p ng-if="item.statusList[3] != 3 || item.statusList[4] != 3" class="jumpSubTit">耗材情况</p>
								<table class="condition2">
									<tbody>
										<tr ng-if="item.statusList[3] != 3">
											<th><img src="img/jiuyijiluce.png" alt=""></th>
											<th>就医记录册</th>
											<td ng-if="item.nmOnline == '0'" class="lixian">离线</td>
											<td ng-if="item.nmOnline == '1'">剩余<span>{{item.statusList[8] || 0}}</span>册</td>
											<!-- <td ng-if="item.nmOnline == '1'">剩余<span id="ybju"></span>册</td> -->
											<td ng-if="item.nmOnline == '1'">
												<div>
													<p ng-style="{width:item.statusList[9]}"></p>
												</div>
											</td>
											<td ng-if="item.nmOnline == '2' && item.statusList[3] == '1'">剩余<span>{{item.statusList[8] || 0}}</span>册</td>
											<td ng-if="item.nmOnline == '2' && item.statusList[3] == '1'">
												<div>
													<p ng-style="{width:item.statusList[9]}"></p>
												</div>
											</td>
											<td ng-if="item.nmOnline == '2' && item.statusList[3] == '2'" class="ych">剩余<span>{{item.statusList[8] || 0}}</span>册</td>
											<td ng-if="item.nmOnline == '2' && item.statusList[3] == '2'" class="ych">
												<div>
													<p ng-style="{width:item.statusList[9]}"></p>
												</div>
											</td>
										</tr>
										<tr ng-if="item.statusList[4] != 3">
											<th><img src="img/dayinzhizhang.png" alt=""></th>
											<th>打印纸张</th>
											<td ng-if="item.nmOnline == '0'" class="lixian">离线</td>
											<td ng-if="item.nmOnline == '1'">剩余<span>{{item.statusList[6] || 0}}</span>张</td>
											<!-- <td ng-if="item.nmOnline == '1'">剩余<span id="zz"></span>张</td> -->
											<td ng-if="item.nmOnline == '1'">
												<div>
													<p ng-style="{width:item.statusList[7]}"></p>
												</div>
											</td>
											<td ng-if="item.nmOnline == '2' && item.statusList[4] == '1'">剩余<span>{{item.statusList[6] || 0}}</span>张</td>
											<td ng-if="item.nmOnline == '2' && item.statusList[4] == '1'">
												<div>
													<p ng-style="{width:item.statusList[7]}"></p>
												</div>
											</td>
											<td ng-if="item.nmOnline == '2' && item.statusList[4] == '2'" class="ych">剩余<span>{{item.statusList[6] || 0}}</span>张</td>
											<td ng-if="item.nmOnline == '2' && item.statusList[4] == '2'" class="ych">
												<div>
													<p ng-style="{width:item.statusList[7]}"></p>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</li>
						</ul>
						<div class="slider_icon">
							<i class="currentPage">1</i>
							<i> / </i>
							<i class="totalPage">{{totalPage}}</i>
						</div>
						<a href="javascript:;" class="arrow prve" id="prev">
							<span class="slider_left">&lt;上一台</span>
						</a>
						<a href="javascript:;" class="arrow next" id="next">
							<span class="slider_right">下一台&gt;</span>
						</a>
					</div>
					<!--<div class="roll"></div> -->
				</div>
			</div>
		</div>

		<div class="warning" ng-if="openWarn">
			<div class="warn" ng-repeat="warn in Warn">
				<div>
					<img src="img/warning.png" alt="">
				</div>
				<div ng-click="getWarnDeviceInfo(0,warn[0].stDeviceMac)">
					<p>异常报警</p>
					<span>编号{{warn[0].stDeviceCode}}设备异常</span>&nbsp;&nbsp;
					<a href="javascript:;">详情>></a>
				</div>
			</div>
		</div>
		<script src="js/base.js"></script>
		<script src="js/loading.js"></script>
		<script src="js/controller1.js"></script>
		<script type="text/javascript">
			var areaId = "<%= (Object)request.getSession().getAttribute("smsUserStAreaId")%>";
			var user= "<%= (String)request.getSession().getAttribute("loginName")%>";
			/* var v1 = Math.floor(Math.random()*(30-15+1))+15;
			document.getElementById("ybju").innerText = v1;
			var v2 = Math.floor(Math.random()*(150-80+1)+80);
			document.getElementById("zz").innerText = v2;
			console.log('v1:'+v1);
			console.log('v2:'+v2); */
		</script>
	</body>

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 </html>
