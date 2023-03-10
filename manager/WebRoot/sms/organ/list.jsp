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
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
    content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<script type="text/javascript">var webRoot = '<%=StringEscapeUtils.escapeJavaScript(webRoot) %>';</script>
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
<title>????????????</title>
<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
</style>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> ?????? <span class="c-gray en">&gt;</span>
        ???????????? <span class="c-gray en">&gt;</span> ????????????
<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="??????" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<table class="table">
    <tr>
        <td width="15%" class="va-t">
                <a class="btn btn-primary radius" onclick="addRootTree()" href="javascript:;">
                    <i class="Hui-iconfont">&#xe600;</i>???????????????</a>
          <ul id="treeDemo" class="ztree"></ul></td>
        <td class="va-t" width="85%" >
            <iframe id="dl-iframe" width=100% height=530px  FRAMEBORDER=0 SCROLLING=AUTO ></iframe>
        </td>
    </tr>
</table>    

<script type="text/javascript"
    src="<%=webRoot%>/resources/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/resources/layer/2.1/layer.js"></script>
<script type="text/javascript" 
    src="<%=webRoot%>/sms/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/h-ui/js/H-ui.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript">
var setting = {
      view: {
          addHoverDom: addHoverDom,
          removeHoverDom: removeHoverDom,
          selectedMulti: false
      },
      edit: {
          enable: true,
          editNameSelectAll: true
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
          beforeClick: beforeClick,
          beforeRemove: beforeRemove,
          beforeRename: beforeRename,
          onRename: onRename,
          beforeDrag: beforDrag
      }
};

// ???????????????
function beforeClick(treeId, treeNode){
     var zTree = $.fn.zTree.getZTreeObj("tree");
     demoIframe.attr("src",webRoot+"/sms/organ/edit.do?ST_ORGAN_ID="+
           treeNode.stOrganId);
     return true;
}

// ????????????
function beforDrag() {
    return false;
}

// ???????????????
function onRename (event, treeId, treeNode, isCancel) {
     //?????????
     layer.confirm('????????????????????????:'+treeNode.stOrganName, { btn: ['??????','??????'] }
      ,function(){
          $.ajax({
              type : "POST",
              url : webRoot+'/sms/organ/save.do',
              dataType:"json",
              data:{"ST_ORGAN_ID":treeNode.stOrganId,
                    "ST_ORGAN_NAME":treeNode.stOrganName},
              success : function(data) {
                   layer.msg('????????????',{icon:1,time:1000});
               },
              error : function() {
                   layer.msg('????????????',{icon:1,time:1000});
              }
          }); 
     },function(){
          // ??????????????????
          initTree ();
     });
}

// ????????????
function beforeRemove(treeId, treeNode) {
    // ???????????????ID
    var paramsArray = new Array();
    paramsArray.push(treeNode.stOrganId);
    // ??????????????????
    if(treeNode.isParent){
         var zTree = $.fn.zTree.getZTreeObj("treeDemo");
         var childNodes = zTree.removeChildNodes(treeNode);
         for(var i = 0; i < childNodes.length; i++){
             paramsArray.push(childNodes[i].stOrganId);
         }
     }
     //?????????
     layer.confirm('????????????????????????????????????????????????', { btn: ['??????','??????'] }
      ,function(){
           $.ajax({
              type : "POST",
              url : webRoot+'/sms/organ/remove.do',
              dataType:"json",
              data:{
                    "stOrganId":paramsArray},
              success : function(data) {
                   layer.msg('????????????',{icon:1,time:1000});
               },
              error : function() {
                   
              }
          }); 
     },function(){
            // ??????????????????
            initTree ();
     });
}

// ??????????????????
function beforeRename(treeId, treeNode, newName, isCancel) {
    if (newName.length == 0) {
        setTimeout(function() {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            zTree.cancelEditName();
            layer.msg('?????????????????????',{icon:2,time:1000});
        }, 0);
        return false;
    }
    return true;
}

// ????????????
function addHoverDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
        + "' title='???????????????' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_"+treeNode.tId);
    if (btn) btn.bind("click", function(){
        // ???????????????
        if (treeNode.isParent) {
            layer.confirm('??????????????????????????????', {
               btn: ['????????????','?????????','??????'] 
             }, function(){
                 var parentNode = treeNode.getParentNode();
                 var pId =null;
                 if(parentNode != null){
                     pId = parentNode.id;
                 }
                  // ??????????????????
                  addCategory (treeNode, pId);
             }, function(){
                 // ???????????????
                addCategory (treeNode, treeNode.stOrganId);
             }, function(){
                layer.msg('??????', {icon: 1});
             });
        } else {
           // ???????????????
           addCategory (treeNode, treeNode.stOrganId);
        }
        return false;
    });
};

function removeHoverDom(treeId, treeNode) {
    $("#addBtn_"+treeNode.tId).unbind().remove();
};

// ????????????
function addCategory (treeNode, parentId) {
   var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    $.ajax({
          type : "POST",
          url : webRoot+'/sms/organ/save.do',
          dataType:"json",
          data:{ "ST_PARENT_ID":parentId,
                 "ST_ORGAN_NAME":"?????????????????????"},
          success : function(data) {
              // ????????????
              zTree.addNodes(treeNode, {stOrganId: data.SmsOrgan.stOrganId,
               stParentId: parentId, stOrganName:"?????????????????????"});
                layer.msg(data.result,{icon:1,time:1000});
           },
          error : function() {
          }
      }); 
}

// ??????????????????
function initTree () {
    $.ajax({
        type : "POST",
        url : webRoot+'/sms/organ/list.do',
        dataType:"json",
        success : function(data) {
           // ????????????
           $.fn.zTree.init($("#treeDemo"), setting, data.data);
         },
        error : function() {
        }
    }); 
}

// ???????????????
function addRootTree () {
      $.ajax({
        type : "POST",
        url : webRoot+'/sms/organ/save.do',
        dataType:"json",
        data:{
              "ST_PARENT_ID": null,
              "ST_ORGAN_NAME":"?????????????????????"},
        success : function(data) {
             // ????????????
            initTree ();
         },
        error : function() {
        }
    }); 
}
 
$(document).ready(function(){
    // ????????????
    initTree ();
    demoIframe = $("#dl-iframe");
});
</script>
</body>
</html>