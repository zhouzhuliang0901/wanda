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
            <input type="hidden" value="<%=infopubGroup.stAreaId2Html()%>" id="area"  />
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                            分组名称：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubGroup.stGroupName2Html()%>"
                        name="<%=InfopubGroup.ST_GROUP_NAME %>" disabled="disabled">
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
                            <label ><input type="checkbox" value="{{$value.stDeviceId}}" name="deviceId[]" disabled="disabled">
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
                        name="<%=InfopubGroup.ST_DESC %>" disabled="disabled">
                </div>
            </div>
           
            <div class="row cl">
                <shiro:hasRole name="admin">
                    <div class="row cl">
                         <label class="form-label col-xs-4 col-sm-2">所属区域id：</label>
                         <div class="formControls col-xs-4 col-sm-4">
		                     <input type="text" class="input-text"
		                         value="<%=infopubGroup.stAreaId2Html()%>"
		                         name="<%=InfopubGroup.ST_AREA_ID %>" disabled="disabled">
		                 </div>
                     </div>
                 </shiro:hasRole>
            </div>                                  
            
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
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
    src="<%=webRoot%>/sms/lib/viewer/js/template.js"></script>    
<script type="text/javascript">
var user= "<%= (String)request.getSession().getAttribute("loginName")%>";
var isAdmin = false;
// 管理员权限的判定
<shiro:hasRole name="admin">
   isAdmin = true;
</shiro:hasRole>
$(function(){
   // 展示选择列表
   checkGroupSelect();
   //initUserSelect();
   /* // 管理员的场合
    if (isAdmin) {
        $.ajax({
         url:webRoot+'/infopub/area/list.do',
         type:"POST",
         dataType:"json",
         success : function(result) {
             var areaId = $("#area").val();
             var select = "";
             for (var i=0;i<result.data.length;i++) {
                if(result.data[i].stareaId == areaId){
                   select += "<option value="+result.data[i].stareaId+" selected='selected'>"+result.data[i].stareaName+"</option>";
                } else {
                   select += "<option value="+result.data[i].stareaId+">"+result.data[i].stareaName+"</option>";
                }
             }
             $("#areaSelect").html(select);
          },
         error : function() {
         }
     }); 
    }  */
});
   
// 展示选择列表
function initUserSelect() {
  $.ajax({
          type : "POST",
          url : webRoot + "/infopub/deviceinfo/list.do",
          dataType : "json",
          data: function(d){
          	var username = user;
		      d.userName = username;
          },
          error : function(request) {
          },
          success : function(result) {
            var deviceList = template('device', result);
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
        	var deviceList = template('device', result);
            $("#deviceList").html(deviceList);
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
</script>
</body>
</html>