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
<title>项目信息</title>
<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
</style>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
        设备管理 <span class="c-gray en">&gt;</span> 管理单位
<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<table class="table">
    <tr>
        <td width="15%" class="va-t">
		        <a class="btn btn-primary radius" onclick="addRootTree()" href="javascript:;">
		            <i class="Hui-iconfont">&#xe600;</i>添加根节点</a>
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
		       name:"stAreaName"
		    },
		    simpleData: {
		        enable:true,
		        idKey: "stAreaId",
		        pIdKey: "stParentAreaId",
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

// 点击树节点
function beforeClick(treeId, treeNode){
     var zTree = $.fn.zTree.getZTreeObj("tree");
     demoIframe.attr("src",webRoot+"/infopub/area/edit.do?ST_AREA_ID="+
           treeNode.stAreaId+"&ST_PARENT_Area_ID="+treeNode.stParentAreaId);
     return true;
}

// 禁止拖拽
function beforDrag() {
    return false;
}

// 更新目录名
function onRename (event, treeId, treeNode, isCancel) {
     //询问框
     layer.confirm('确认修改目录名为:'+treeNode.stAreaName, { btn: ['确认','取消'] }
      ,function(){
          $.ajax({
              type : "POST",
              url : webRoot+'/infopub/area/save.do',
              dataType:"json",
              data:{"ST_AREA_ID":treeNode.stAreaId,
                    "ST_AREA_NAME":treeNode.stAreaName},
              success : function(data) {
                   layer.msg('修改成功',{icon:1,time:1000});
               },
              error : function() {
                   
              }
          }); 
     },function(){
          // 重新加载数据
          initTree ();
     });
}

// 删除目录
function beforeRemove(treeId, treeNode) {
    // 根目录的场合、禁止删除
    if (treeNode.stAreaId == "infopub_area_root") {
        layer.msg('当前目录禁止删除',{icon:2,time:1000});
        return false;
    }

    // 删除数据的ID
    var paramsArray = new Array();
    paramsArray.push(treeNode.stAreaId);
    // 父节点的场合
    if(treeNode.isParent){
         var zTree = $.fn.zTree.getZTreeObj("treeDemo");
         var childNodes = zTree.removeChildNodes(treeNode);
         for(var i = 0; i < childNodes.length; i++){
            // 根目录的场合、禁止删除
		    if (childNodes[i].stAreaId == "infopub_area_root") {
		        layer.msg('当前目录禁止删除',{icon:2,time:1000});
		        return false;
		    }
             paramsArray.push(childNodes[i].stAreaId);
         }
     }
     //询问框
     layer.confirm('确认删除该级目录和目录下的样表？', { btn: ['确认','取消'] }
      ,function(){
	       $.ajax({
	          type : "POST",
	          url : webRoot+'/infopub/area/remove.do',
	          dataType:"json",
	          data:{
	                "stAreaId":paramsArray},
	          success : function(data) {
	               layer.msg('删除成功',{icon:1,time:1000});
	           },
	          error : function() {
	               
	          }
	      }); 
     },function(){
            // 重新加载数据
            initTree ();
     });
}

// 更新目录名前
function beforeRename(treeId, treeNode, newName, isCancel) {
    if (newName.length == 0) {
        setTimeout(function() {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            zTree.cancelEditName();
            layer.msg('目录名不能为空',{icon:2,time:1000});
        }, 0);
        return false;
    }
    return true;
}

// 添加节点
function addHoverDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
        + "' title='添加子节点' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_"+treeNode.tId);
    if (btn) btn.bind("click", function(){
        // 父节的场合
        if (treeNode.isParent) {
			layer.confirm('请选择所要添加的节点', {
			   btn: ['同级节点','字节点','取消'] 
			 }, function(){
			     var parentNode = treeNode.getParentNode();
                 var pId =null;
                 if(parentNode != null){
                     pId = parentNode.id;
                 }
                  // 添加同级节点
                  addCategory (treeNode, pId);
			 }, function(){
			     // 添加子节点
                addCategory (treeNode, treeNode.stAreaId);
			 }, function(){
			    layer.msg('取消', {icon: 1});
			 });
        } else {
           // 添加子节点
           addCategory (treeNode, treeNode.stAreaId);
        }
        return false;
    });
};

function removeHoverDom(treeId, treeNode) {
    $("#addBtn_"+treeNode.tId).unbind().remove();
};

// 添加目录
function addCategory (treeNode, parentId) {
   var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    $.ajax({
          type : "POST",
          url : webRoot+'/infopub/area/save.do',
          dataType:"json",
          data:{ "ST_PARENT_AREA_ID":parentId,
                 "ST_AREA_NAME":"请输入目录名称"},
          success : function(data) {
              // 添加目录
              zTree.addNodes(treeNode, {stAreaId: data.InfopubArea.stAreaId,
               stParentAreaId: parentId, stAreaName:"请输入目录名称"});
                layer.msg(data.result,{icon:1,time:1000});
           },
          error : function() {
          }
      }); 
}

// 初始化列表树
function initTree () {
    $.ajax({
        type : "POST",
        url : webRoot+'/infopub/area/list.do',
        dataType:"json",
        success : function(data) {
           // 初始化树
           $.fn.zTree.init($("#treeDemo"), setting, data.data);
         },
        error : function() {
        }
    }); 
}

// 添加根节点
function addRootTree() {
      $.ajax({
        type : "POST",
        url : webRoot+'/infopub/area/save.do',
        dataType:"json",
        data:{
              "ST_PARENT_AREA_ID": null,
              "ST_AREA_NAME":"请输入目录名称"},
        success : function(data) {
             // 初始化树
            initTree ();
         },
        error : function() {
        }
    }); 
}
 
$(document).ready(function(){
    // 初始化树
    initTree ();
    demoIframe = $("#dl-iframe");
});
</script>
</body>
</html>