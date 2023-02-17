<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%@page import="com.wondersgroup.api.bean.ApidocModule"%>
<%
	String webRoot = AppContext.webRootPath;
	ApidocModule apidocModule = (ApidocModule) request.getAttribute(ApidocModule.APIDOC_MODULE);
	if (apidocModule == null)
		apidocModule = new ApidocModule();
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
<script type="text/javascript">
    var webRoot = '<%=StringEscapeUtils.escapeJavaScript(webRoot)%>';
</script>
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

<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>项目管理</title>
</head>
<body>
<div class="page-container">
	<form class="form form-horizontal" id="form-workspace-add">
		<input id="stId" type="hidden" name="<%=ApidocModule.ST_MODULE_ID %>" value="<%=apidocModule.stModuleId2Html() %>" /> 
		<input id="stParentId" type="hidden" name="<%=ApidocModule.ST_PARENT_ID %>" value="<%=apidocModule.stParentId2Html() %>" />  
		<input id="stProjectId" type="hidden" name="<%=ApidocModule.ST_PROJECT_ID %>" value="<%=apidocModule.stProjectId2Html() %>" disabled/>  
		
		<div class="row cl">
			<label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>名称：</label>
			<div class="formControls col-xs-6 col-sm-6">
				<input type="text" class="input-text"
					value="<%=apidocModule.stModuleName2Html()%>" name="<%=ApidocModule.ST_MODULE_NAME %>" >
			</div>
		</div>
		<%-- <div class="row cl" id="project">
				<label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>所属项目：</label>
				<div class="formControls  col-xs-6 col-sm-6">
					<span class="select-box" > <select
						class="select" size="1" id="projectId"
						name="<%=ApidocModule.ST_PROJECT_ID%>">
					</select> </span>
				</div>
			</div> --%>
		<div class="row cl">
			<label class="form-label col-xs-2 col-sm-2">说明：</label>
			<div class="formControls col-xs-6 col-sm-6">
				<textarea name="<%=ApidocModule.ST_REMARK %>" cols="" rows="" class="textarea"
					style="overflow-x:hidden" placeholder="模块说明..."  dragonfly="true"
					onKeyUp="textarealength(this,200)"><%=apidocModule.stRemark2Html()%></textarea>
				<p class="textarea-numberbar">
					<em class="textarea-length">0</em>/200
				</p>
			</div>
		</div>
		<div class="row cl">
				<label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span> 排序号：</label>
				<div class="formControls  col-xs-6 col-sm-6">
					<input type="text" class="input-text" digits="true"
						value="<%=apidocModule.nmOrder2Html(0)%>"
						name="<%=ApidocModule.NM_ORDER%>">
				</div>
			</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-3 col-sm-offset-3">
				<button class="btn btn-primary radius size-M" type="submit">
					<i class="Hui-iconfont">&#xe632;</i> 保存
				</button>
				<button onClick="lookDoc('/apidoc/interface/interfaceDetail.html');" class="btn btn-success radius size-M"
					type="button">&nbsp;&nbsp;文档查看&nbsp;&nbsp;</button> 
			</div>
		</div>
	</form>
</div>
<script type="text/javascript"
	src="<%=webRoot%>/resources/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/resources/layer/2.1/layer.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/sms/lib/h-ui/js/H-ui.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/sms/lib/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/messages_zh.min.js"></script>
<script type="text/javascript">
$(function() {
	//得到项目列表
	//getList();
	// 数据校验
	$("#form-workspace-add").validate();
	
	
});

// 数据提交     
$.validator.setDefaults({
	submitHandler : function() {
		// 提交数据
		article_save_submit();
	}
});

// 提交内容
function article_save_submit() {
	$.ajax({
		type : "POST",
		url : webRoot + "/apidoc/module/save.do",
		dataType:"json",
		data : $('#form-workspace-add').serialize(),// 你的formid
		error : function(request) {
			layer.msg("信息保存失败！", {
				icon : 2,
				time : 1000
			});
		},
		success : function(data) {
			var zTree = parent.$.fn.zTree
							.getZTreeObj("treeDemo"), nodes = zTree
							.getSelectedNodes(), treeNode = nodes[0];
					treeNode.stModuleName = data.apidocMenu.stModuleName;
					zTree.updateNode(treeNode);
					layer.msg(data.result, {
						icon : 1,
						time : 1000
					});
		}
	});
}

//得到模板消息
function getList() {
	var templateObj = $("#projectId");
	templateObj.html("");
	$.ajax({
		url : webRoot + "/apidoc/module/getProject.do",
		dataType : "JSON",
		type : "POST",
		success : function(data) {
			var item = data.projectList;
			var stProjectId = $("#stProjectId").val();
			 var msg = "";
			msg += "<option value=''>---请选择---</option>";
			for ( var i = 0; i < item.length; i++) {
				if(item[i].stProjectId == stProjectId){
					msg += "<option value='"+item[i].stProjectId+"' selected ='selected'>" +item[i].stProjectName+ "</option>";
				}else{
					msg += "<option value='"+item[i].stProjectId+"'>" + item[i].stProjectName + "</option>";
				}
			}
			templateObj.html(msg); 
		}
	});
}

//文档模板渲染
function lookDoc(url){
	var str = getRequest();
	window.open(webRoot+url+"?ST_MODULE_ID="+str[1]);
}
//获取query string parameters
function getRequest() {
	//获取url中"?"符后的字串
	var url = location.search; 
	//判断是否有参数
	if (url.indexOf("?") != -1) {   
	//从第一个字符开始 因为第0个是?号 获取所有除问号的所有符串
	var str = url.substr(1);
	//用等号进行分隔 （因为知道只有一个参数 所以直接用等号进分隔 如果有多个参数 要用&号分隔 再用等号进行分隔） 
	strs = str.split(/[&=]/);
	//直接返回第一个参数 （如果有多个参数 还要进行循环的）   
	return strs;         
  }
  return null;
} 
</script>
</body>
</html>
