<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.sms.role.bean.SmsRole"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%
    String webRoot = AppContext.webRootPath;
    SmsRole smsRole = (SmsRole) request.getAttribute(SmsRole.SMS_ROLE);
    // 角色ID
    String menuId = (String) request.getAttribute("menuId");
    // 菜单名称
    String menuName = (String) request.getAttribute("menuName");
    
    if (menuName == null)
        menuName = "";
     
    if (smsRole == null)
        smsRole = new SmsRole();
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
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>角色编辑</title>
</head>
<body>
	<div class="page-container">
		<form class="form form-horizontal" id="form-role-add">
			<input type="hidden" name="<%=SmsRole.ST_ROLE_ID %>" value="<%=smsRole.stRoleId2Html() %>" /> 
			<input type="hidden" name="menuId" id="menuId" value="<%=menuId%>"/> 
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
				            角色代码：</label>
				<div class="formControls  col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=smsRole.stRoleCode2Html() %>" 
						name="<%=SmsRole.ST_ROLE_CODE %>" required>
				</div>
			</div>
           
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                                                        角色名称：</label>
                <div class="formControls  col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=smsRole.stRoleName2Html() %>"
                        name="<%=SmsRole.ST_ROLE_NAME%>" required >
                </div>
            </div>
            
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                                                角色菜单：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <textarea  id="menuSelect"  class="textarea" onclick="showMenu()" required
                        ><%=menuName %></textarea>
                </div>
            </div> 
                        
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">角色描述：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <textarea name="<%=SmsRole.ST_DESC %>"  cols="" rows="" class="textarea"
                        placeholder="说点什么...最少输入10个字符" datatype="*10-100" dragonfly="true"
                        nullmsg="备注不能为空！" onKeyUp="textarealength(this,100)"><%=smsRole.stDesc2Html()%></textarea>
                    <p class="textarea-numberbar">
                        <em class="textarea-length">0</em>/100
                    </p>                   
                </div>
            </div> 
            
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">排序字段：</label>
                <div class="formControls  col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=smsRole.nmOrder2Html(0) %>" digits="true"
                        name="<%=SmsRole.NM_ORDER %>" >
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
	
	<div id="menuContent" class="menuContent" style="display:none; position: absolute; background-color:rgba(255,255,255,0.7)">
        <ul id="treeDemo" class="ztree"></ul>
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
    src="<%=webRoot%>/sms/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 
<script type="text/javascript" 
    src="<%=webRoot%>/sms/lib/zTree/v3/js/jquery.ztree.core-3.5.min.js"></script> 
<script type="text/javascript" 
    src="<%=webRoot%>/sms/lib/zTree/v3/js/jquery.ztree.excheck-3.5.min.js"></script>  	
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/jquery.validate.min.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/messages_zh.min.js"></script>      	
<script type="text/javascript">
// 下拉选择树控件
var setting = {
    check: {
        enable: true,
        chkboxType: {"Y":"ps", "N":"ps"}
    },
    view: {
        dblClickExpand: false
    },
    data: {
     key:{
        name:"stMenuName"
     },
     simpleData: {
         enable:true,
         idKey: "stMenuId",
         pIdKey: "stParentId",
         rootPId: ""
     }
    },
    callback: {
        beforeClick: beforeClick,
        onCheck: onCheck
    }
};

function beforeClick(treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    zTree.checkNode(treeNode, !treeNode.checked, null, true);
    return false;
}

function onCheck(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
    nodes = zTree.getCheckedNodes(true),
    v = "", id="";
    
    for (var i=0, l=nodes.length; i<l; i++) {
        id += nodes[i].stMenuId + ",";
        v += nodes[i].stMenuName + ",";
    }
    if (v.length > 0 ) v = v.substring(0, v.length-1);
    // 目录ID
    $("#menuId").attr("value",id);
    // 选择值
    $("#menuSelect").val(v);
}

function showMenu() {
    var cityObj = $("#menuSelect");
    var cityOffset = $("#menuSelect").offset();
    $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "categorySelect" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
        hideMenu();
    }
}
     
$(function(){
   // 数据校验
   $("#form-role-add").validate();
   
     $.ajax({
        type : "POST",
        url : webRoot+'/sms/menu/list.do',
        dataType:"json",
        success : function(data) {
          // 已选中ID
          var menuId = $('#menuId').val();
          
          var arr = new Array();  
          arr = menuId.split(",");
          
           for (var i=0; i< data.data.length; i++) {
              // 树展开
              data.data[i].open = true;
               for(var key in arr){
                  // 已选择的场合
                  if (arr[key] == data.data[i].stMenuId) {
                      data.data[i].checked = true;
                  }
               }
           }
           // 初始化树
           $.fn.zTree.init($("#treeDemo"), setting, data.data);
         },
        error : function() {
        }
    }); 
});
      
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
		url : webRoot + "/sms/role/save.do",
		data : $('#form-role-add').serialize(),// 你的formid
		error : function(request) {
            layer.msg("角色信息添加失败", {icon : 2, time : 1000});
		},
		success : function(data) {
			layer.msg("角色信息添加成功", {icon : 1, time : 1000});
		}
	});
	window.parent.location.reload();
}
</script>
</body>
</html>