<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.sms.user.bean.SmsUser"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%
	String webRoot = AppContext.webRootPath;
    SmsUser smsUser = (SmsUser) request.getAttribute(SmsUser.SMS_USER);
    if (smsUser == null)
        smsUser = new SmsUser();
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
<link rel="stylesheet" 
    href="<%=webRoot%>/sms/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" >
<link href="<%=webRoot%>/sms/lib/jquery-ui-1.12.1/jquery-ui.min.css"
	rel="stylesheet" />
<link
	href="<%=webRoot%>/sms/lib/jQuery-Timepicker/jquery-ui-timepicker-addon.min.css"
	rel="stylesheet" />
<link href="<%=webRoot%>/sms/lib/webuploader/0.1.5/webuploader.css"
	rel="stylesheet" type="text/css" />

<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>用户管理</title>
</head>
<body>
	<div class="page-container">
		<form class="form form-horizontal" id="form-user-add">
			<input type="hidden" name="<%=SmsUser.ST_USER_ID%>"  value="<%=smsUser.stUserId2Html()%>" /> 
			<input type="hidden" value="<%=smsUser.stAreaId2Html()%>"
				id="deviceType" /> 
			<input type="hidden" value="<%=smsUser.stOrganId2Html()%>"
				id="organType" /> 
		
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
				            登录名：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=smsUser.stLoginName2Html()%>" 
						name="<%=SmsUser.ST_LOGIN_NAME%>" required>
				</div>
               <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                                                    工号：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=smsUser.stUserCode2Html()%>"
                        name="<%=SmsUser.ST_USER_CODE%>"  required>
                </div>
			</div>
           
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                                                      姓名：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=smsUser.stUserName2Html()%>"
                        name="<%=SmsUser.ST_USER_NAME%>" required>
                </div>
                <label class="form-label col-xs-4 col-sm-2">拼音：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=smsUser.stPinyin2Html()%>"
                        name="<%=SmsUser.ST_PINYIN%>" >
                </div>               
            </div>
            
            <div class="row cl">
               <%--  <label class="form-label col-xs-4 col-sm-2">界面主题：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=smsUser.stThemeName2Html() %>"
                        name="<%=SmsUser.ST_THEME_NAME %>"  >
                </div>   --%>   
                
                <label class="form-label col-xs-4 col-sm-2">所属部门：</label>
                <div class="formControls col-xs-4 col-sm-4">
					<span class="select-box"> <select
						name="<%=SmsUser.ST_ORGAN_ID%>" class="select"
						id="organListSelect">
							<script id="organList" type="text/html">
                                         {{each data}}
                                            <option value="{{$value.stOrganId}}" {{if typeId == $value.stOrganId}} selected='selected' {{/if}}>
                                                    {{$value.stOrganName}}</option>
                                        {{/each}} 
                            </script>
					</select> </span>
				</div>
                 
                <label class="form-label col-xs-4 col-sm-2">所属项目：</label>
                <div class="formControls col-xs-4 col-sm-4">
					<span class="select-box"> <select
						name="<%=SmsUser.ST_AREA_ID%>" class="select"
						id="deviceTypeSelect">
							<script id="typeList" type="text/html">
                                        {{each data}}
                                            <option value="{{$value.stAreaId}}" {{if typeId == $value.stAreaId}} selected='selected' {{/if}}>
                                                    {{$value.stAreaName}}</option>
                                        {{/each}} 
                            </script>
					</select> </span>
				</div>
                <%-- <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text" id="organMsg"
                        value="<%=smsUser.stOrganId2Html() %>" onclick="showMenu();"
                        name="<%=SmsUser.ST_ORGAN_ID %>"  >
                </div> --%>                
            </div>  
            
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">邮箱：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=smsUser.stEmail2Html()%>"
                        name="<%=SmsUser.ST_EMAIL%>"  email="true">
                </div>
                <label class="form-label col-xs-4 col-sm-2">手机：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=smsUser.stMobile2Html()%>"
                        name="<%=SmsUser.ST_MOBILE%>"  maxlength="11">
                </div>                
            </div>   
            
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">是否接收系统邮件：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <span class="select-box"> 
                       <select name="<%=SmsUser.NM_RECEIVE_EMAIL%>" class="select">
                            <option value="1">是</option>
                            <option value="0">否 </option>
                       </select> 
                    </span>                        
                </div>
                <label class="form-label col-xs-4 col-sm-2">账号是否被锁定：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <span class="select-box"> 
                       <select name="<%=SmsUser.NM_LOCKED%>" class="select">
                            <option value="1">是</option>
                            <option value="0">否</option>
                       </select> 
                    </span>                          
                </div>          
            </div>    
            
           <%if (smsUser.getStUserId() != null){ %>
                <div class="row cl">
                    <label class="form-label col-xs-4 col-sm-2">密码修改：</label>
                    <div class="formControls col-xs-4 col-sm-4">
                      <label class=""><input type="checkbox" value="resetPD" name="resetPD" >
                                                   重置登录密码</label>
                    </div>
                </div>      
            <%} %>
            
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
		
	    <div id="menuContent" class="menuContent" style="display:none; position: absolute; background-color:rgba(255,255,255,0.7)">
	        <ul id="treeDemo" class="ztree"></ul>
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
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/jquery.validate.min.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/messages_zh.min.js"></script>    
<script type="text/javascript" 
     src="<%=webRoot%>/sms/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 
<script type="text/javascript" 
    src="<%=webRoot%>/sms/lib/zTree/v3/js/jquery.ztree.core-3.5.min.js"></script> 
<script type="text/javascript" 
    src="<%=webRoot%>/sms/lib/zTree/v3/js/jquery.ztree.excheck-3.5.min.js"></script>    
<script type="text/javascript"
		src="<%=webRoot%>/sms/lib/viewer/js/template.js"></script>     	
<script type="text/javascript">
// 下拉选择树控件
var setting = {
      check: {
          enable: true,
          chkStyle: "radio",
          radioType: "all"
      },
      view: {
          dblClickExpand: false
      },
      data: {
          key:{
             name:"stOrganName"
          },
          simpleData: {
              enable:true,
              idKey: "stOrganId",
              pIdKey: "stParentId",
              rootPId: ""
          }
      },
      callback: {
          onClick: onClick,
          onCheck: onCheck
      }
};

function onClick(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    zTree.checkNode(treeNode, !treeNode.checked, null, true);
    return false;
}

function onCheck(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
    nodes = zTree.getCheckedNodes(true),
    v = "";
    for (var i=0, l=nodes.length; i<l; i++) {
        v += nodes[i].stOrganName + ",";
    }
    if (v.length > 0 ) v = v.substring(0, v.length-1);
    var cityObj = $("#organMsg");
    cityObj.attr("value", v);
}

function showMenu() {
    var cityObj = $("#organMsg");
    var cityOffset = $("#organMsg").offset();
    $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "organMsg" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
        hideMenu();
    }
}

$(function(){
 initDataorgan()
	initData();
   // 数据校验
   $("#form-user-add").validate();
   
   // 初始化树
   initTree();
});

//
function initData(){
 $.ajax({
         url:webRoot+'/sms/organ/init.do',
         type:"POST",
         dataType:"json",
         success : function(result) {
             var devicetype = result.area;
             devicetype.typeId = $("#deviceType").val();
             $("#deviceTypeSelect").html(template('typeList', devicetype));
             
          },
         error : function() {
         }
     }); 
}

// 初始化树
function initTree() {
    $.ajax({
        type : "POST",
        url : webRoot+'/sms/organ/list.do',
        dataType:"json",
        success : function(data) {
           // 已选部门的场合
           var organ = $("#organMsg").val();
            
           // 展开列表
           for (var i in data.data) {
               // 全部展开
               data.data[i].open = true;
               // 默认数据
               if (data.data[i].stOrganName == organ) {
                    data.data[i].checked = true;
               }
           }
        
           // 初始化树
           $.fn.zTree.init($("#treeDemo"), setting, data.data);
         },
        error : function() {
        }
    }); 
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
		url : webRoot + "/sms/user/save.do",
		data : $('#form-user-add').serialize(),// 你的formid
		error : function(request) {
                  layer.msg("空间信息添加失败", {icon : 2, time : 1000});
		},
		success : function(data) {
			layer.msg("空间信息添加成功", {icon : 1, time : 1000});
		}
	});
	window.parent.location.reload();
}

function initDataorgan(){
 $.ajax({
         url:webRoot+'/sms/organ/organList.do',
         type:"POST",
         dataType:"json",
         success : function(result) {
             var organ = result.organ;
              organ.typeId = $("#organType").val();
             $("#organListSelect").html(template('organList', organ));
             
          },
         error : function() {
         }
     }); 
}
</script>
</body>
</html>