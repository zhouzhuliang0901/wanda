<%@page import="com.wondersgroup.app.bean.SelmItem"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib prefix="permission" uri="/sms/permission"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String webRoot = AppContext.webRootPath;
    SelmItem selmItem = (SelmItem) request.getAttribute(SelmItem.SELM_ITEM);
    if (selmItem == null)
        selmItem = new SelmItem();
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
<link href="<%=webRoot%>/sms/lib/jquery-ui-1.12.1/jquery-ui.min.css"
    rel="stylesheet" />
<link href="<%=webRoot%>/sms/lib/jQuery-Timepicker/jquery-ui-timepicker-addon.min.css"
    rel="stylesheet" />	
<link href="<%=webRoot%>/sms/lib/webuploader/0.1.5/webuploader.css"
    rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>??????</title>
</head>
<body>
	<div class="page-container">
		<form class="form form-horizontal" id="form-item-add">
			<input type="hidden" name="<%=SelmItem.ST_ITEM_ID%>"
				id="stSampleId" value="<%=selmItem.stItemId2Html()%>" />
				<input type="hidden" value="<%=selmItem.stOrganId2Html()%>"
				id="organType" /> 
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmItem.stMainName2Html() %>"
						name="<%=SelmItem.ST_MAIN_NAME%>" required>
				</div>
				<label class="form-label col-xs-4 col-sm-2">???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmItem.stItemNo2Html() %>"
						name="<%=SelmItem.ST_ITEM_NO %>" required>
				</div>
				
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">?????????????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmItem.stWorkUrl2Html() %>"
						name="<%=SelmItem.ST_WORK_URL%>">
				</div>
				
				<label class="form-label col-xs-4 col-sm-2">?????????</label>
				<div class="formControls col-xs-4 col-sm-4">
				<span class="select-box"> 
                       <select name="<%=SelmItem.ST_WORK_TYPE%>" class="select">
                       		<c:set var="swt_value" scope="request" value="<%=selmItem.stWorkType2Html()%>"/>
                            <option value="??????" <c:if test="${swt_value == '??????'}">selected="selected" </c:if>>??????</option>
                            <option value="??????" <c:if test="${swt_value == '??????'}">selected="selected" </c:if>>??????</option>
                            <option value="??????" <c:if test="${swt_value == '??????'}">selected="selected" </c:if>>??????</option>
                            <option value="????????????" <c:if test="${swt_value == '????????????'}">selected="selected" </c:if>>????????????</option>
                            <option value="??????" <c:if test="${swt_value == '??????'}">selected="selected" </c:if>>??????</option>
                       </select> 
                    </span>       
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
				<span class="select-box">
					<select id ="sit" name="<%=SelmItem.ST_ITEM_TYPE%>" class="select" >
							<c:set var="sit_value" scope="request" value="<%=selmItem.stItemType2Html()%>"/>
                            <option value="1" <c:if test="${sit_value == 1}">selected="selected"</c:if>>?????????</option>
                            <option value="2" <c:if test="${sit_value == 2}">selected="selected"</c:if>>?????????</option>
                    </select>
                </span> 
				</div>
				
				<label class="form-label col-xs-4 col-sm-2">???????????????</label>
                <div id="organ" class="formControls col-xs-4 col-sm-4" >
					<span class="select-box" > 
						 <select name="<%=SelmItem.ST_ORGAN_ID %>" class="select" id="organListSelect">
								<script id="organList" type="text/html">
                                   		{{each data}}
                                            <option value="{{$value.stOrganId}}" {{if typeId == $value.stOrganId}} selected='selected' {{/if}}>
                                                    {{$value.stOrganName}}</option>
                                        {{/each}} 
                            </script>
						</select>
					</span>
				</div>
			</div>
           <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">?????????????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<span class="select-box"> 
                       <select name="<%=SelmItem.ST_EXT2%>" class="select">
                       		<c:set var="se_calue" scope="request" value="<%=selmItem.stExt22Html() %>"/>
                            <option value="???" <c:if test="${se_calue == '???'}">selected="selected"</c:if>>???</option>
                            <option value="???" <c:if test="${se_calue == '???'}">selected="selected"</c:if>>???</option>
                           
                       </select> 
                    </span>   
				</div>
				<label class="form-label col-xs-4 col-sm-2">?????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmItem.nmSort2Html(0) %>"
						name="<%=SelmItem.NM_SORT %>" >
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">?????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmItem.stExt12Html() %>"
						name="<%=SelmItem.ST_EXT1 %>">
				</div>
			</div>
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
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
<script type="text/javascript"
	src="<%=webRoot%>/resources/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/resources/layer/2.1/layer.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/jquery.validate.min.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/messages_zh.min.js"></script>       
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/h-ui/js/H-ui.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/h-ui.admin/js/H-ui.admin.js"></script>    
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/viewer/js/template.js"></script>      
<script type="text/javascript"
     src="<%=webRoot%>/sms/lib/jquery-ui-1.12.1/jquery-ui.min.js"></script>  
<script src="<%=webRoot%>/sms/lib/jQuery-Timepicker/jquery-ui-timepicker-addon.min.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jQuery-Timepicker/i18n/jquery-ui-timepicker-zh-CN.js"></script>   
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jQuery-Timepicker/init.js"></script>            
	<!--?????????????????????????????????????????????-->
<script type="text/javascript">
/* var isAdmin = false;
// ????????????????????????
<shiro:hasRole name="admin">
   isAdmin = true;
</shiro:hasRole> */
$(function(){
   // ????????????
   $("#form-item-add").validate();
   initDataorgan();
 
});

// ????????????     
$.validator.setDefaults({
    submitHandler: function() {
      // ????????????
      article_save_submit();
    }
});
// ????????????
function article_save_submit() {
	$.ajax({
		type : "POST",
		url : webRoot + '/app/selmItem/save.do',
		data : $('#form-item-add').serialize(),// ??????formid
		error : function(request) {
                  layer.msg("????????????????????????", {icon : 1, time : 1000});
		},
		success : function(data) {
			layer.msg("????????????????????????", {icon : 1, time : 1000});
		}
	});
	setTimeout(function() {
		window.parent.location.reload();
	}, 1000);
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

/* function organInit(){
	var stItemType = $("#sit").val();
	if( 1 == stItemType ){
		$("#organ").empty();
		$("#organ").append('<span class=\"select-box\" >'+
								'<select name=\"<\%=SelmItem.ST_ORGAN_ID %>\" class=\"select\" id=\"organListSelect\">'+ 
									'<script id=\"organList\" type=\"text/html\">'+
                                   		'{{each data}}'+
                                   		'<option value=\"{{$value.stOrganId}}\" {{if typeId == $value.stOrganId}} selected=\'selected\' {{/if}}>'+
                                        '{{$value.stOrganName}}</option>'+  
                                        '{{/each}}' +         
                              		'<\/script>'+
								'</select>'+
							'</span>'          
                           );
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
	if( 2 == stItemType ){
		$("#organ").empty();
		$("#organ").append('<input type=\"text\" class=\"input-text\" value=\"\"'+
						'name=\"<\%=SelmItem.ST_ORGAN_ID %>\">');
	}
	
} */

</script>
</body>
</html>