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
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>??????????????????</title>
</head>
<body>
	<div class="page-container">
    <form action="" method="post" class="form form-horizontal" id="form-user-link-add">
        <input type="hidden" name="<%=SmsUser.ST_USER_ID%>"  value="<%=smsUser.stUserId2Html() %>" /> 
        <input type="hidden" name="menuId" id="menuId" /> 
       
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">????????????:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="<%=smsUser.stUserName2Html() %>" 
                readonly="readonly" style="background-color:#828282;" >
            </div>
        </div>
        
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">????????????:</label>
            <div class="formControls col-xs-8 col-sm-9">
                 <textarea  id="menuSelect"  class="textarea" onclick="showMenu()">
                 </textarea>                
            </div>
        </div>
        
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">????????????:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <dl class="permission-list">
                    <dt><label>????????????</label></dt>
                 <dd id="roleList">
                    <!--  <script id="role" type="text/html">
                        {{each roleList}}
                            <label ><input type="radio" value="{{$value.stRoleId}}" name="roles[]">
                                {{$value.stRoleName}}</label>
                         {{/each}}
                    </script> -->
                    <script id="role" type="text/html">
                        {{each roleList}}
                            <label ><input type="radio" value="{{$value.stRoleId}}" name="roles[]">
                                {{$value.stRoleName}}</label>
                         {{/each}}
                    </script>                        
                 </dd>
                </dl>
            </div>
        </div>
        
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">????????????:</label>
            <div class="formControls col-xs-8 col-sm-9">
                <dl class="permission-list">
                    <dt><label>????????????</label></dt>
                 <dd id="groupList">
                    <script id="group" type="text/html">
                        {{each groupList}}
                            <label ><input type="radio" value="{{$value.stGroupId}}" name="groupId[]">
                                {{$value.stGroupName}}</label>
                         {{/each}}
                    </script>
                 </dd>
                </dl>
            </div>
        </div>        
        
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <button onClick="article_save_submit();"
                    class="btn btn-primary radius" type="button">
                    <i class="Hui-iconfont">&#xe632;</i> ??????
                </button>
                <button onClick="layer_close();" class="btn btn-default radius"
                    type="button">&nbsp;&nbsp;??????&nbsp;&nbsp;</button>
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
    src="<%=webRoot%>/sms/lib/icheck/jquery.icheck.min.js"></script>
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
    src="<%=webRoot%>/sms/lib/viewer/js/template.js"></script>    
<script type="text/javascript">
// ?????????????????????
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
    // ??????ID
    $("#menuId").attr("value",id);
    // ?????????
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
       $.ajax({
         type : "POST",
         url : webRoot+'/sms/user/queryUserLink.do',
         data:{"ST_USER_ID":"<%=smsUser.stUserId2Html()%>"},
         dataType:"json",
         success : function(request) {
            var data = request.data;
            // ????????????
            var roleList = template('role', data);
            $("#roleList").html(roleList);
            // ?????????
            var groupList = template('group', data);
            $("#groupList").html(groupList);
            
            // ??????????????????ID
            var menuIdSelected = "";
            // ????????????????????????
            var menuNameSelected = "";
            // ????????????????????????
            for (var i in data.menuList) {
                data.menuList[i].open = true;
                for (var j in data.userMenuList) {
                    // ??????????????????
                    if (data.userMenuList[j].stMenuId ==
                         data.menuList[i].stMenuId) {
                         // ????????????????????????ID
                         menuIdSelected += data.menuList[i].stMenuId + ",";
                         // ??????????????????????????????
                         menuNameSelected += data.menuList[i].stMenuName +",";
                         // ??????
                         data.menuList[i].checked = true;
                    }
                }
            }
            // ????????????????????????ID
            $("#menuId").val(menuIdSelected);
            // ??????????????????????????????
            $("#menuSelect").val(menuNameSelected);
            
            // ????????????????????????
             $("input:checkbox").each(function(){ 
                  for (var i in data.userRoleList) {
                      if ($(this).attr('value') == data.userRoleList[i].stRoleId ){
                        $(this).prop("checked",true);
                      }
                  }
                  
                 for (var i in data.userGroupList) {
                      if ($(this).attr('value') == data.userGroupList[i].stGroupId ){
                        $(this).prop("checked",true);
                      }
                  }
             
              }); 
           
            // ????????????
            $.fn.zTree.init($("#treeDemo"), setting, data.menuList);
          },
         error : function() {
         }
     }); 
 });

 
 // ????????????
function article_save_submit() {
    $.ajax({
        type : "POST",
        url : webRoot + "/sms/user/addUserLink.do",
        data : $('#form-user-link-add').serialize(),// ??????formid
        error : function(request) {
            layer.msg("????????????????????????", {icon : 2, time : 1000});
        },
        success : function(data) {
            layer.msg("????????????????????????", {icon : 1, time : 1000});
        }
    });
    window.parent.location.reload();
}
</script>
</body>
</html>