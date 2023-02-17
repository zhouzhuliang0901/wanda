<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubWorkspace"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%
    String webRoot = AppContext.webRootPath;
    InfopubWorkspace infopubWorkspace = (InfopubWorkspace) 
        request.getAttribute(InfopubWorkspace.INFOPUB_WORKSPACE);
    if (infopubWorkspace == null)
        infopubWorkspace = new InfopubWorkspace();
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
<title>样表管理</title>
</head>
<body>
	<div class="page-container">
		<form class="form form-horizontal" id="form-workspace-add">
			<input type="hidden" id="wordSpaceId" name="<%=InfopubWorkspace.ST_WORKSPACE_ID %>" value="<%=infopubWorkspace.stWorkspaceId2Html() %>" /> 
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
				            空间名称：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubWorkspace.stWorkspaceName2Html()%>"
						name="<%=InfopubWorkspace.ST_WORKSPACE_NAME %>" required>
				</div>
			</div>
           
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                                                唯一代码：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubWorkspace.stWorkspaceCode2Html()%>"
                        name="<%=InfopubWorkspace.ST_WORKSPACE_CODE %>" required>
                </div>
            </div>
            
	        <div class="row cl">
	            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>所属用户：</label>
	            <div class="formControls col-xs-4 col-sm-4">
	                <dl class="permission-list">
	                    <dt><label>请选择所属用户</label></dt>
	                 <dd id="userList">
	                    <script id="user" type="text/html">
                        {{each data}}
                            <label ><input type="checkbox" value="{{$value.stUserId}}" name="userId[]">
                                {{$value.stUserName}}</label>
                         {{/each}}
                    </script>                    
	                 </dd>
	                </dl>
	            </div>
	        </div>
            
           
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                                                    空间总共大小：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubWorkspace.nmTotal2Html(0) %>" digits="true"
                        name="<%=InfopubWorkspace.NM_TOTAL %>" required>
                </div>
            </div>
            
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                                                    已经使用：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubWorkspace.nmUsed2Html(0)%>" digits="true"
                        name="<%=InfopubWorkspace.NM_USED %>" required>
                </div>
            </div>                        			
			
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button class="btn btn-primary radius" type="submit">
						<i class="Hui-iconfont">&#xe632;</i> 保存
					</button>
					<button onClick="layer_close();" class="btn btn-default radius"
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
	src="<%=webRoot%>/sms/lib/h-ui/js/H-ui.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/sms/lib/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/jquery.validate.min.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/messages_zh.min.js"></script>     
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/viewer/js/template.js"></script>    
<script type="text/javascript">
$(function(){
   // 展示选择列表
   initUserSelect();
    
   // 数据校验
   $("#form-workspace-add").validate();
});
   
// 展示选择列表
function initUserSelect() {
  $.ajax({
          type : "POST",
          url : webRoot + "/sms/user/list.do",
          dataType : "json",
          error : function(request) {
          },
          success : function(result) {
            // 角色菜单
            var roleList = template('user', result);
            $("#userList").html(roleList);
           checkUserSelect();
          }
      });
}

// 检查用户选择
function checkUserSelect(){
  var workSpaceId = $("#wordSpaceId").val();
  // 空间ID存在的场合
  if (workSpaceId != null) {
    $.ajax({
        type : "POST",
        url : webRoot + "/infopub/workspace/checkUserSelect.do",
        data:{"workSpaceId":workSpaceId},
        dataType : "json",
        error : function(request) {
        },
        success : function(result) {
            // 勾选已选择的数据
           $("input:checkbox").each(function(){ 
                for (var i in result.data) {
                    if ($(this).attr('value') == result.data[i].stUserId ){
                      $(this).prop("checked",true);
                    }
                }
           }); 
        }
    });
  }
}

// 数据提交     
$.validator.setDefaults({
    submitHandler: function() {
      // 提交数据
      article_save_submit();
    }
});

// 提交内容
function article_save_submit() {
	$.ajax({
		type : "POST",
		url : webRoot + "/infopub/workspace/save.do",
		data : $('#form-workspace-add').serialize(),// 你的formid
		error : function(request) {
                  layer.msg("空间信息添加失败", {icon : 2, time : 1000});
		},
		success : function(data) {
			layer.msg("空间信息添加成功", {icon : 1, time : 1000});
		}
	});
	window.parent.location.reload();
}
</script>
</body>
</html>