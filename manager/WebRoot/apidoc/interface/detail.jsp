<%@page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.api.bean.ApidocInterface"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%@page import="wfc.facility.tool.autocode.Transformer4Request"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%
	String webRoot = AppContext.webRootPath;
	ApidocInterface apidocInterface = (ApidocInterface) request.getAttribute(ApidocInterface.APIDOC_INTERFACE);
	if (apidocInterface == null)
		apidocInterface = new ApidocInterface();
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
<title>????????????</title>
</head>
<body>
<div class="page-container">
	<form class="form form-horizontal" id="form-interface-add">
		<input id="stInterfaceId" type="hidden" name="<%=ApidocInterface.ST_INTERFACE_ID %>" value="<%=apidocInterface.stInterfaceId2Html() %>" /> 
		<input name="ST_MODULE_ID" id='categoryId' type="hidden"  value="<%=apidocInterface.stModuleId2Html() %>" />
		<input name="method" id='method' type="hidden"  value="<%=apidocInterface.stMethod2Html() %>" />
		
		<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                                                 ???????????????</label>
                <div class="formControls col-xs-3 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=apidocInterface.stInterfaceName2Html() %>" 
                        name="<%=ApidocInterface.ST_INTERFACE_NAME %>">
                </div>
                <label class="form-label col-xs-3 col-sm-2">
                                                    ???????????????</label>
                <div class="formControls  col-xs-3 col-sm-4">
					<span class="select-box" > <select
						class="select" size="1" id="stMethod"
						name="<%=ApidocInterface.ST_METHOD %>">
						<option value='GET'>GET</option>
						<option value='POST'>POST</option>
						<option value='PUT'>PUT</option>
						<option value='HEAD'>HEAD</option>
						<option value='DELETE'>DELETE</option>
						<option value='TRACE'>TRACE</option>
						<option value='CONNECT'>CONNECT</option>
						<option value='OPTIONS'>OPTIONS</option>
					</select> </span>
				</div>
           </div>
		<div class="row cl">
                <label class="form-label col-xs-3 col-sm-2">
                                                 ????????????</label>
                <div class="formControls col-xs-3 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=apidocInterface.nmOrder2Html(0) %>" 
                        name="<%=ApidocInterface.NM_ORDER %>" >
                </div>
               <label class="form-label col-xs-3 col-sm-2">
                                                 ????????????</label>
                <div class="formControls col-xs-3 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=apidocInterface.nmVersion2Html(0)%>" 
                        name="<%=ApidocInterface.NM_VERSION %>" >
                </div>
           </div>
           <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">
                	????????????URL???</label>
                <div class="formControls col-xs-8 col-sm-10">
                    <input type="text" class="input-text"
                        value="<%=apidocInterface.stUrl2Html() %>" 
                        name="<%=ApidocInterface.ST_URL %>" >
                </div>
           </div>
           <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">
                	?????????????????????</label>
                <div class="formControls col-xs-8 col-sm-10">
			       <input type="text" class="input-text" class="select" id="categorySelect"
			       		 value="<%=apidocInterface.stExt12Html() %>"
			             name="<%=ApidocInterface.ST_EXT1 %>" onclick="showMenu()" 
			             readonly required>
				</div>
           </div>
           <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">
                	???????????????</label>
                <div class="formControls col-xs-8 col-sm-10">
                    <textarea name="CL_REMARK" cols="" rows="" style="overflow-x:hidden"
                    class="textarea"><%=apidocInterface.clRemark2Html() %></textarea>
                </div>
           </div>
           <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">???????????????<br><br>
                <button type="button" class="btn btn-primary radius fr ml8"
                onClick="editerParam('eparam','param','editParamTable','requestParam')">  
               		????????????</button></label>
				<div id="eparam" class="col-xs-8 col-sm-10" style="display:none">
					<table class="table table-border table-bordered table-hover" id="editParamTable">
						<thead>
							<tr >
								<th>?????????</th>
								<th>????????????</th>
								<th>??????</th>
								<th>??????</th>
							</tr>
						</thead>
						<tbody id=requestParam>
						</tbody>
					</table>
					<div class="row cl">
	                	<div class="col-xs-4 col-sm-4 col-xs-offset-6 col-sm-offset-8">
							<button type="button"
								onclick="modifyParam('eparam','param','editParamTable')"
								class="btn btn-warning fr ml10">????????????</button>
							<button type="button" onclick="addRow('requestParam')"
								class="btn btn-success fr ml10">??????</button>
							<button type="button" onclick="changeDisplay('param','eparam')"
							class="btn btn-primary fr">??????</button>
						</div>
					</div>
				</div>
				<div class="formControls col-xs-8 col-sm-10" id="param">
					<textarea id="request" name="<%=ApidocInterface.CL_REQUEST_PARAM %>" cols="" rows="" 
					style="overflow-x:hidden" class="textarea" ><%=apidocInterface.clRequestParam2Html()%></textarea>
				</div>
           </div>
           
           <div class="row cl">
               <label class="form-label col-xs-4 col-sm-2">???????????????<br><br>
                <button type="button" class="btn btn-primary radius fr ml8"
                onClick="editerParam('rparam','param1','editResponseParamTable','responseParam')">  
               		????????????</button></label>
				<div id="rparam" class="col-xs-8 col-sm-10" style="display:none">
					<table class="table table-border table-bordered table-hover" id="editResponseParamTable">
						<thead>
							<tr >
								<th>?????????</th>
								<th>??????</th>
								<th>??????</th>
							</tr>
						</thead>
						<tbody id=responseParam>
						</tbody>
					</table>
					<div class="row cl">
	                	<div class="col-xs-4 col-sm-4 col-xs-offset-6 col-sm-offset-8">
							<button type="button"
								onclick="modifyParam('rparam','param1','editResponseParamTable')"
								class="btn btn-warning fr ml10">????????????</button>
							<button type="button" onclick="addRow('responseParam')"
								class="btn btn-success fr ml10">??????</button>
							<button type="button" onclick="changeDisplay('param1','rparam')"
								class="btn btn-primary fr">??????</button>
						</div>
					</div>
				</div>
				<div class="formControls col-xs-8 col-sm-10" id="param1">
					<textarea id="response" name="<%=ApidocInterface.CL_RESPONSE_PARAM %>" cols="" rows="" 
					style="overflow-x:hidden" class="textarea" ><%=apidocInterface.clResponseParam2Html()%></textarea>
				</div>
           </div>
           
           <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">?????????????????????<br><br>
                <button type="button" class="btn btn-primary radius fr ml8"
                onClick="format('requestExam',true)">  
               		???????????????</button></label>
                <div class="formControls col-xs-8 col-sm-10" >
                    <textarea  id="requestExam" name="CL_REQUEST_EXAM" cols="4" rows="4" 
                    style="overflow-x:hidden" placeholder="?????????json??????..." class="textarea"><%=apidocInterface.clRequestExam2Html()%></textarea>
                </div>
           </div>
           <div class="row cl">
           		<label class="form-label col-xs-4 col-sm-2">?????????????????????<br><br>
                <button type="button" class="btn btn-primary radius fr ml8"
                onClick="format('responseExam',true)">  
               		???????????????</button></label>
                <div class="formControls col-xs-8 col-sm-10" >
                    <textarea id="responseExam" name="CL_RESPONSE_EXAM" cols="4" rows="4" 
                    style="overflow-x:hidden" placeholder="?????????json??????..." class="textarea"><%=apidocInterface.clResponseExam2Html()%></textarea>
                </div>
           </div>
           <div class="row cl">
                <div class="col-xs-4 col-sm-4 col-xs-offset-4 col-sm-offset-6">
                    <button type="submit"
                        class="btn btn-primary radius" type="button">
                        <i class="Hui-iconfont">&#xe632;</i> ??????
                    </button>
                    <button onClick="layer_close();" class="btn btn-default radius"
                        type="button">&nbsp;&nbsp;??????&nbsp;&nbsp;</button>
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
    src="<%=webRoot%>/sms/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 
<script type="text/javascript" 
    src="<%=webRoot%>/sms/lib/zTree/v3/js/jquery.ztree.core-3.5.min.js"></script> 
<script type="text/javascript" 
    src="<%=webRoot%>/sms/lib/zTree/v3/js/jquery.ztree.excheck-3.5.min.js"></script> 
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
<script type="text/javascript" >
 $(function() {
 	getStMethod();
	// ????????????
	$("#form-interface-add").validate();
	$.ajax({
        type : "POST",
        url : webRoot+'/apidoc/module/list.do',
        dataType:"json",
        success : function(data) {
                // ?????????ID
                var categoryId = $('#categoryId').val();
                console.log(categoryId);
                var arr = new Array();  
                arr = categoryId.split(",");
                
                 for (var i=0; i< data.data.length; i++) {
                    // ?????????
                    data.data[i].open = true;
                     for(var key in arr){
                        // ??????????????????
                        if (arr[key] == data.data[i].stModuleId) {
                            data.data[i].checked = true;
                        }
                     }
                 }
           // ????????????
           $.fn.zTree.init($("#treeDemo"), setting, data.data);
         },
        error : function() {
        }
    });
});

// ????????????     
$.validator.setDefaults({
	submitHandler : function() {
		// ????????????
		article_save_submit();
	}
});

//???????????????????????????
function getStMethod(){
	var stMethod = $("#method").val();
	var i = 0;
 	if(stMethod != null){
 		for(i;i < 8;i++){
 			if($("#stMethod").get(0).options[i].value == stMethod){
 				$("#stMethod").get(0).options[i].selected = true;  
                break;
 			}
 		}
 	} 
}
// ????????????
function article_save_submit() {
	$.ajax({
		type : "POST",
		url : webRoot + "/apidoc/interface/save.do",
		data : $('#form-interface-add').serialize(),// formid
		error : function(request) {
			layer.msg("?????????????????????", {
				icon : 2,
				time : 1000
			});
		},
		success : function(data) {
			layer.msg("?????????????????????", {
				icon : 1,
				time : 1000
			});
			parent.search();
			layer_close();
		}
	});
}

//????????????
function editerParam(editerId,targetId,tableId,tableBodyId){
	if(tableId=="editParamTable"){
		var str = $("#request").val();
		var html = "";
		if(str){
			var item = eval("("+str+")");
			
			$("#"+tableBodyId).html("");
			for(var i = 0;i < item.length;i++){
				html += "<tr id="+i+"><td><input type='text' class='input-text radius size-S' name='name' value="+item[i].name+"></td>";
				if("???" == item[i].necessary){
					html+= "<td><select class='select' size='1' name='necessary'><option value='???' selected>???</option><option value='???'>???</option></select></td>";
				}else{
					html+= "<td><select class='select' size='1' name='necessary'><option value='???' selected>???</option><option value='???'>???</option></select></td>";
				}
				html+= "<td><input type='text' class='input-text radius size-S' name='state' value="+item[i].state+"></td>" + 
					"<td class='text-c'><a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"deleteRow(this)\" href=\"javascript:;\" title=\"??????\"><i class='Hui-iconfont' >&#xe6e2;</i></td></tr>" ;
				/* "<td><input class='form-control' type='text' name='necessary' value="+item[i].necessary+"></td>";*/
			}
		}
		$("#"+tableBodyId).html(html);
	}else if(tableId="editResponseParamTable"){
		var str = $("#response").val();
		var html = "";
		if(str){
			var item = eval("("+str+")");
			$("#"+tableBodyId).html("");
			for(var i = 0;i < item.length;i++){
				html += "<tr id="+i+"><td><input type='text' class='input-text radius size-S' name='name' value="+item[i].name+"></td>" +
					"<td><input type='text' class='input-text radius size-S' name='state' value="+item[i].state+"></td>" + 
					"<td class='text-c'><a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"deleteRow(this)\" href=\"javascript:;\" title=\"??????\"><i class='Hui-iconfont' >&#xe6e2;</i></td></tr>";
			}
		}
		$("#"+tableBodyId).html(html);
	}
	$("#"+editerId).removeAttr("style");
	$("#"+targetId).attr("style","display:none");
}

//?????????
function addRow(tableBodyId){
	var html = "";
	var tr_id = $("#"+tableBodyId).find("tr:last").attr("id");
	if(tr_id && tableBodyId == "requestParam"){
		tr_id++;
		html += "<tr id= "+tr_id+"><td><input type='text' class='input-text radius size-S' name='name' value='' ></td>" +
			"<td><select class='select' size='1' name='necessary'><option value='???' selected>???</option><option value='???'>???</option></select></td>" + 
			"<td><input type='text' class='input-text radius size-S' name='state' value='' ></td>" + 
			"<td class='text-c'><a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"deleteRow(this)\" href=\"javascript:;\" title=\"??????\"><i class='Hui-iconfont' >&#xe6e2;</i></td></tr>";
	}else if(!tr_id && tableBodyId == "requestParam"){
		var id = 0;
		html += "<tr id= "+ id +"><td><input type='text' class='input-text radius size-S' name='name' value='' ></td>" +
			"<td><select class='select' size='1' name='necessary'><option value='???' selected>???</option><option value='???'>???</option></select></td>" + 
			"<td><input type='text' class='input-text radius size-S' name='state' value='' ></td>" + 
			"<td class='text-c'><a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"deleteRow(this)\" href=\"javascript:;\" title=\"??????\"><i class='Hui-iconfont' >&#xe6e2;</i></td></tr>";
	}else if(tr_id && tableBodyId == "responseParam"){
		tr_id++;
		html += "<tr id= "+tr_id+"><td><input type='text' class='input-text radius size-S' name='name' value='' ></td>" +
			"<td><input type='text' class='input-text radius size-S' name='state' value='' ></td>" + 
			"<td class='text-c'><a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"deleteRow(this)\" href=\"javascript:;\" title=\"??????\"><i class='Hui-iconfont' >&#xe6e2;</i></td></tr>";
	}else if(!tr_id && tableBodyId == "responseParam"){
		var id = 0;
		html += "<tr id= "+id+"><td><input type='text' class='input-text radius size-S' name='name' value='' ></td>" +
			"<td><input type='text' class='input-text radius size-S' name='state' value='' ></td>" + 
			"<td class='text-c'><a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"deleteRow(this)\" href=\"javascript:;\" title=\"??????\"><i class='Hui-iconfont' >&#xe6e2;</i></td></tr>";
	}
	$("#"+tableBodyId).append(html);
} 

//???????????????
function deleteRow(object){
	$(object).parent("td").parent("tr").remove();
}

//???????????????div????????????
function changeDisplay(editerId,targetId){
	$("#"+editerId).removeAttr("style");
	$("#"+targetId).attr("style","display:none");
} 

//??????????????????
function modifyParam(editerId,targetId,tableId) {

	var json = getParamFromTable(tableId);
	try{
	 eval("("+json+")");
	}catch(e){
		alert("???????????????json???????????????"+e);
		return;
	}
	if(tableId =="editParamTable"){
		$("#request").text(json);	
	}else if(tableId == "editResponseParamTable"){
		$("#response").text(json);
	}	
   	$("#"+targetId).removeAttr("style");
	$("#"+editerId).attr("style","display:none");
}

//?????????JSON??????(???????????????JSON??????) 
function format(id, tiperror) {
	var txt = $("#"+id).val();
	if (/^\s*$/.test(txt)) {
		if (tiperror)
			layer.alert('????????????,???????????????! ');
		return;
	}
	try {
		var data = eval('(' + txt + ')');
	} catch (e) {
		if (tiperror)
			layer.alert('?????????????????????,???????????????! ????????????: ' + e.description, 'err');
		return;
	}
	var myText = JSON.stringify(data, null, "\t");
	$("#"+id).val(myText);
}

//????????????????????????Json
function getParamFromTable(tableId) {
	var json = "[";
	var i = 0;
	var j = 0;
	$("#" + tableId).find("tbody").find("tr").each(function() {
		i = i + 1;
		j = 0;
		if (i != 1)
			json += ",";
		json += "{";
		$(this).find("td").find("input").each(function(i, val) {
			j = j + 1;
			if (j != 1)
				json += ",";
			json += "\"" + val.name + "\":\"" + replaceAll(val.value,'"','\\"') + "\"";
		});
		$(this).find("td").find('select').each(function(i, val) {
			j = j + 1;
			if (j != 1)
				json += ",";
			json += "\"" + val.name + "\":\"" + replaceAll(val.value,'"','\\"') + "\"";
		}); 
		json += "}";
	});
	json += "]";
	return json;
}
 
//?????????????????????????????????
function replaceAll(originalStr,oldStr,newStr){
	var regExp = new RegExp(oldStr,"gm");
	return originalStr.replace(regExp,newStr);
}

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
        name:"stModuleName"
     },
     simpleData: {
         enable:true,
         idKey: "stModuleId",
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
    v = "", id = "";
    
    for (var i=0, l=nodes.length; i<l; i++) {
        // ???????????????
        if (nodes[i].isParent) {
            continue;
        }
        id += nodes[i].stModuleId + ",";
        v += nodes[i].stModuleName + ",";
    }
    if (v.length > 0 ) v = v.substring(0, v.length-1);
    // ??????ID
    $("#categoryId").attr("value",id);
    // ?????????
    $("#categorySelect").attr("value", v);
}

function showMenu() {
    var cityObj = $("#categorySelect");
    var cityOffset = $("#categorySelect").offset();
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

</script> 
</body>
</html>