<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="permission" uri="/sms/permission"%>
<%@page import="coral.base.app.AppContext"%>
<%
	String webRoot = AppContext.webRootPath;
	pageContext.setAttribute("webRoot", webRoot);
%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<style>
.nav-link {
	  position: relative;
	  padding: 0 14px;
	  line-height: 34px;
	  font-size: 10px;
	  color: #555;
	  text-decoration: none;
	}
	.nav-link:hover {
	  color: #333;
	  text-decoration: none;
	}

	.nav-counter {
	  position: absolute;
	  top: 20px;
	  right: 1px;
	  min-width: 8px;
	  height: 13px;
	  line-height: 13px;
	  margin-top: -11px;
	  padding: 0 6px;
	  font-weight: normal;
	  color: white;
	  text-align: center;
	  text-shadow: 0 1px rgba(0, 0, 0, 0.2);
	  background: #e23442;
	  border: 1px solid #911f28;
	  border-radius: 11px;
	  background-image: -webkit-linear-gradient(top, #e8616c, #dd202f);
	  background-image: -moz-linear-gradient(top, #e8616c, #dd202f);
	  background-image: -o-linear-gradient(top, #e8616c, #dd202f);
	  background-image: linear-gradient(to bottom, #e8616c, #dd202f);
	  -webkit-box-shadow: inset 0 0 1px 1px rgba(255, 255, 255, 0.1), 0 1px rgba(0, 0, 0, 0.12);
	  box-shadow: inset 0 0 1px 1px rgba(255, 255, 255, 0.1), 0 1px rgba(0, 0, 0, 0.12);
	}
	#jrdb:after{
		display:none;
	}

</style>
<title>一网通办自助运行管理平台</title>
<link href="../login/css/reset.css" rel="stylesheet" type="text/css">
<link href="../login/css/style.css" rel="stylesheet" type="text/css">
<link href="../login/css/scrollbar.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=webRoot%>/sms/lib/Hui-iconfont/1.0.7/iconfont.css" /> 
<script src="../login/js/jquery.min.js" type="text/javascript"></script>

<script type="text/javascript" language="javascript">
$(function (){
var webRoot = '<%=webRoot%>';
//模拟滚动条
   $("#iframe_box").perfectScrollbar();
   $("#top").perfectScrollbar();
//menu 下拉 - menu 收起
    menuTool();
//多开栏操作
    openPaging();
//系统操作下拉
    vdrop();
    
//初始化 - 实际可能不需要以下代码
	//默认激活菜单中第一个链接
	$(".shell-m a[index='infopub_home']").eq(0).addClass("active");
	//展开相应菜单
	$(".shell-m a.active").parents("li").addClass("active");
	//多开栏添加相应标签
	//var tAds = webRoot+'/infopub/deviceinfo/mapPage.jsp';
	//var tSymbol = 'ifillform_device_placeShow';
	var tAds = webRoot+'/infopub/homePage/index.jsp';
	var tSymbol = 'infopub_home';
	var tText = $(".shell-m a.active").html();
	if(tText!=undefined){
	$(".open-paging").append("<li class='active'><a href='"+tAds+"' index='"+tSymbol+"' target='mainIframe'>"+tText+"</a><i>&times;</i></li>"); 
	$("#top").css("display","block");
	$("#iframe_box").css("display","none");
	$("#mainIframe").attr('src',tAds)
	}
	
});
</script>
</head>

<body>

	<div class="shell">
		<!--左侧-->
		<div class="sl">
		
			<!--左侧	logo-->
			<div class="shell-logo"></div>

			<!--左侧	底部折叠按钮-->
			<div class="shell-col"></div>

			<!--左侧	菜单-->
			<div  class="shell-menu-bar">
				<!--menu中a链接的index值唯一即可，用于多开栏检索-->
				<aside class="Hui-aside shell-m">
					<div class="menu_dropdown bk_2">
						<c:forEach var="menu" items="${menuList}">
							<permission:hasPermission menuCode="${menu.stMenuCode}">
								<dl id="<c:out value="${menu.stMenuCode}"></c:out>">
									<dt>
										<h4>
											<i class="Hui-iconfont">&#x${menu.stImage};</i>
											<c:out value="${menu.stMenuName}"></c:out>
											<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
										</h4>
									</dt>
									<dd style="display:none">
										<ul>
											<c:forEach var="secondMenu" items="${menu.childrenList}">
												<permission:hasPermission
													menuCode="${secondMenu.stMenuCode}">
													<li>
														<a 
														target="mainIframe"
														_href="${empty secondMenu.stUrl ? '' : webRoot}${secondMenu.stUrl}"
														data-title="${secondMenu.stMenuName}"
														index="${secondMenu.stMenuCode}"
														href="javascript:void(0)">${secondMenu.stMenuName}
														</a>
													</li>
												</permission:hasPermission>
											</c:forEach>
										</ul>
									</dd>
								</dl>
							</permission:hasPermission>
						</c:forEach>
					</div>
				</aside>
			</div>
		</div>

		<!--顶部右侧用户-->
		<div class="sr">
			<div class="top-tool">
				<ul class="open-paging"></ul>
				<ul class="info-view">
					<li><span>尊敬的用户，您好!</span></li>
					<!-- <li id=l2>
						<div class="vdrop">
							<h4 id="jrdb"><a href="#" class="nav-link">今日待办</a></h4>
							<div class="vdmask"></div>
							<div class="vdown">
								<a id="sbgj" href="javascript:void(0);"
									onclick="sendWarn('设备告警','/infopub/devicewarn/sendWarn.jsp','','')">设备告警</a>
							</div>
						</div>
					
					</li> -->
					<li>
						<div class="vdrop">
							<h4>${sessionScope.session_user.stUserName }</h4>
							<div class="vdmask"></div>
							<div class="vdown">
								<!-- <a href="javascript:void(0);"
									onclick="user_changPd('修改密码','/sms/user/user_password_edit.jsp','','510')">修改密码</a> -->
								<a href="javascript:void(0);"
									onclick="window.location.href='<%=webRoot%>/sms/login/logout.do'">切换账户</a>
								<a href="javascript:void(0);"
									onclick="window.location.href='<%=webRoot%>/sms/login/logout.do'">退出系统</a>
							</div>
						</div>
					</li>
					<!-- <li id="Hui-skin" class="dropDown right dropDown_hover"><a
						href="javascript:;" class="dropDown_A" title="换肤"><i
							class="Hui-iconfont" style="font-size:18px">&#xe62a;</i> </a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" data-val="default" title="默认">默认</a>
                            </li>
							<li><a href="javascript:;" data-val="black" title="黑色">黑色</a>
							</li>
							<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a>
							</li>
							<li><a href="javascript:;" data-val="green" title="绿色">绿色</a>
							</li>
							<li><a href="javascript:;" data-val="red" title="红色">红色</a>
							</li>
							<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a>
							</li>
							<li><a href="javascript:;" data-val="orange" title="绿色">橙色</a>
							</li>
						</ul></li> -->
				</ul>
			</div>
			<!-- 右侧展示栏 -->
			<div id="iframe_box" class="main-view" style="top: 70px; left: 18px; display:block;">			
				<div class="show_iframe">
					<div style="display:none" class="loading"></div>
					<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
						<div class="Hui-tabNav-wp">
							<ul id="min_title_list" class="acrossTab cl">
								<li class="active" style="display:none" >
								   <span title="我的桌面" data-href="welcome.html">我的桌面</span>
								<em></em>
								</li>
							</ul>
						</div>
						<div class="Hui-tabNav-more btn-group">
							<a id="js-tabNav-prev" class="btn radius btn-default size-S"
								href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i> </a><a
								id="js-tabNav-next" class="btn radius btn-default size-S"
								href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i> </a>
						</div>
					</div>
				</div>			
			</div>
			<div class="main-view" id="top" style="display:none;top: 70px; left: 18px;">
				<iframe id="mainIframe" name="mainIframe" frameborder="0" width="100%" height="100%"></iframe>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=webRoot%>/resources/jquery/jquery-1.11.1.min.js"></script>
	<script type="text/javascript"
		src="<%=webRoot%>/resources/layer/2.1/layer.js"></script>
	<script type="text/javascript"
		src="<%=webRoot%>/sms/lib/h-ui/js/H-ui.js"></script>
	<script type="text/javascript"
		src="<%=webRoot%>/sms/lib/h-ui.admin/js/H-ui.admin.js"></script>
	<script type="text/javascript">
		$(function(){
			$.ajax({
				url:"<%=webRoot%>"+'/infopub/deviceWarnInfo/count.do',
				type:'post',
				dataType:'json',
				success:function(data){
					var total = data.count;
					//console.log(total);
					if(total > 0){
						$(".nav-link").append('<div class="nav-counter">'+total+'</div>');
						$("#sbgj").append('<div class="nav-counter">'+total+'</div>');
					}
				},
				error:function(){
				}
			});
		});
		/*用户-添加*/
		function user_changPd(title, url, w, h) {
			layer_show(title, "<%=webRoot%>"+url, w, h);
		}
		function sendWarn(title, url, w, h) {
			layer_show(title,"<%=webRoot%>"+url,w,h);
		}
	</script>
	<script src="../login/js/scrollbar.min.js" type="text/javascript"></script>
	<script src="../login/js/workstation.js" type="text/javascript"></script>
</body>
</html>




