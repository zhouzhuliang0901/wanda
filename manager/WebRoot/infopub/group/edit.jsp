<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubGroup"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib prefix="permission" uri="/sms/permission"%>
<%
    String webRoot = AppContext.webRootPath;
    InfopubGroup infopubGroup = (InfopubGroup) 
        request.getAttribute(InfopubGroup.INFOPUB_GROUP);
    if (infopubGroup == null)
        infopubGroup = new InfopubGroup();
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
		<form class="form form-horizontal" id="form-devicegroup-add">
			<input type="hidden" name="<%=InfopubGroup.ST_GROUP_ID %>"  id="groupId" value="<%=infopubGroup.stGroupId2Html() %>" />
			<input type="hidden" name="<%=InfopubGroup.ST_AREA_ID %>" value="<%= (Object)request.getSession().getAttribute("smsUserStAreaId")%>" id="area"  />
			<input type="hidden" name="stPermission" value="<%= (Object)request.getSession().getAttribute("权限")%>" id="area"  />
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
				            分组名称：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubGroup.stGroupName2Html()%>"
						name="<%=InfopubGroup.ST_GROUP_NAME %>" required>
				</div>
			</div>
            
	        <div class="row cl">
	            <label class="form-label col-xs-4 col-sm-2">添加设备：</label>
	            <div class="formControls col-xs-4 col-sm-4">
	                <dl class="permission-list">
	                    <dt><label>设备名称</label></dt>
	                 <dd id="deviceList">
	                    <script id="device" type="text/html">
                        {{each data}}
                            <label ><input type="checkbox" value="{{$value.stDeviceId}}" name="deviceId[]">
                                {{$value.stDeviceName}}</label>
                         {{/each}}
                    </script>                    
	                 </dd>
	                </dl>
	            </div>
	        </div>
	        
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">
                                               备注：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubGroup.stDesc2Html()%>"
                        name="<%=InfopubGroup.ST_DESC %>" >
                </div>
            </div>
           
                <%-- <shiro:hasRole name="admin">
                    <div class="row cl">
                         <label class="form-label col-xs-4 col-sm-2">所属区域(Id)：</label>
                         <div class="formControls col-xs-4 col-sm-4">
                             <span class="select-box"> 
                                <select name="<%=InfopubGroup.ST_AREA_ID %>" class="select" id="areaSelect">
                                    <script id="areaList" type="text/html">
                                        {{each data}}
                                            <option value="{{$value.stAreaId}}" {{if area == $value.stAreaId}} selected='selected' {{/if}}>
                                                    {{$value.stAreaName}}</option>
                                        {{/each}} 
                                    </script>  
                                
                                </select> 
                             </span>
                         </div>
                     </div>
                 </shiro:hasRole> --%>
			
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
var areaId = "<%= (Object)request.getSession().getAttribute("smsUserStAreaId")%>";
var user= "<%= (String)request.getSession().getAttribute("loginName")%>";
var permission = "<%= (Object)request.getSession().getAttribute("权限")%>";
var isAdmin = false;
// 管理员权限的判定
<shiro:hasRole name="admin">
   isAdmin = true;
</shiro:hasRole>
var areaId = "<%= (Object)request.getSession().getAttribute("smsUserStAreaId")%>";
var permission = "<%= (Object)request.getSession().getAttribute("权限")%>";
$(function(){
   // 展示选择列表
   initData();
    
   // 数据校验
   $("#form-devicegroup-add").validate();
});
   
// 展示选择列表
function initData() {
  $.ajax({
          type : "POST",
          url : webRoot + "/infopub/deviceinfo/init.do",
          dataType : "json",
          data : {
          	'stAreaId':areaId,
          	'stPermission':permission,
          	userName:user
          },
          error : function(request) {
          },
          success : function(result) {
             /* if (isAdmin) {
                 var area = result.area;
                 area.area = $("#area").val();
                 $("#areaSelect").html(template('areaList', area));
             } */
          
            var deviceList = template('device',  result.deviceinfo);
            $("#deviceList").html(deviceList);
            checkGroupSelect();
          }
      });
}

// 检查用户选择
function checkGroupSelect(groupId){
    var groupId = $("#groupId").val();
  // 空间ID存在的场合
  if (groupId != null) {
    $.ajax({
        type : "POST",
        url : webRoot + "/infopub/devicegroup/deviceGroupSelect.do",
        data:{"ST_GROUP_ID":groupId},
        dataType : "json",
        error : function(request) {
        },
        success : function(result) {
            // 勾选已选择的数据
           $("input:checkbox").each(function(){ 
                for (var i in result.data) {
                    if ($(this).attr('value') == result.data[i].stDeviceId ){
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
		url : webRoot + "/infopub/devicegroup/save.do",
		data : $('#form-devicegroup-add').serialize(),// 你的formid
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