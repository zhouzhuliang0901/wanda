<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.sms.user.bean.SmsUser"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib prefix="permission" uri="/sms/permission"%>
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

<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>修改密码</title>
</head>
<body>
	<div class="page-container">
		<form class="form form-horizontal"  id="user-password-edit">
			<input type="hidden" name="<%=SmsUser.ST_USER_ID %>" id="userId" value="${sessionScope.session_user.stUserId }" />
			<input type="hidden" name="changePd" value="true" />  
		
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				            登录名：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input type="text" class="input-text"
						value="${sessionScope.session_user.stLoginName}" readonly="readonly">
				</div>
			</div>
           
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">
                                                      姓名：</label>
                <div class="formControls col-xs-8 col-sm-8">
                    <input type="text" class="input-text"
                        value="${sessionScope.session_user.stUserName}" readonly="readonly">
                </div>
            </div>
            
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                                                原密码：</label>
                <div class="formControls col-xs-8 col-sm-8">
                    <input type="password" name="ST_OLD_PASSWORD" id="oldPassword" class="input-text checkPassWord" >
                </div>  
            </div>  
            
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                                                    新密码：</label>
                <div class="formControls col-xs-8 col-sm-8">
                    <input type="password" name="ST_PASSWORD" id="password" class="input-text">
                </div>  
            </div>   
            
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                                                确认密码：</label>
                <div class="formControls col-xs-8 col-sm-8">
                    <input type="password" name="confirm_password"  class="input-text">
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
<script src="<%=webRoot%>/sms/login/js/crypto-js.js" type="text/javascript"></script>
<script src="<%=webRoot%>/sms/login/js/aes.js" type="text/javascript"></script>  	
<script type="text/javascript">
var key = "ax7x90.3k_10li5u";
//密钥偏移量 (需要前端和后端保持一致)十六位作为密钥偏移量
var iv = "a0.l954b_107x90l";
$(function(){
  // 数据校验
  $("#user-password-edit").validate({
        rules: {
          ST_PASSWORD: {
            required: true,
            minlength: 5,
            maxlength:20
          },
          confirm_password: {
            required: true,
            minlength: 5,
            equalTo: "#password"
          }
        },
        messages: {
          ST_PASSWORD: {
            required: "请输入密码",
            minlength: "密码长度不能小于 5 个字母",
            maxlength: "密码长度不能超过20个字母"
          },
          confirm_password: {
            required: "请输入密码",
            minlength: "密码长度不能小于 5 个字母",
            equalTo: "两次密码输入不一致"
          }
        }
    });
   
    // 用户初始密码检查
    $.validator.addMethod("checkPassWord", function(value, element) { 
      var flag = false;
      $.ajax({
          type : "POST",
          url : webRoot + "/sms/user/checkPassWord.do",
          dataType : "json",
          async: false,
          data:{"passWord":aesMinEncrypt(key,iv,value),"ST_USER_ID":$("#userId").val()},
          error : function(request) {
          },
          success : function(result) {
            flag = result.data;
          }
      }); 
      return flag;
    }, "密码错误");
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
		url : webRoot + "/sms/user/savePassWord.do",
		//data : $('#user-password-edit').serialize(),// 你的formid
		 data:{
		 "ST_PASSWORD":aesMinEncrypt(key,iv,$("#password").val()),
		 "ST_USER_ID":$("#userId").val(),
		 "ST_OLD_PASSWORD":aesMinEncrypt(key,iv,$("#oldPassword").val())
		 /*  "ST_PASSWORD":$("#password").val(),
		 "ST_USER_ID":$("#userId").val(),
		 "ST_OLD_PASSWORD":$("#oldPassword").val() */
		 },
		success : function(data) {
		    //aler("funck you ");
			layer.msg("密码修改成功" , {icon : 1, time : 5000});
		},
		error : function(request) {
      		//aler("funck");
            layer.msg("密码修改失败", {icon : 2, time : 5000});
		}
		
	});
	setTimeout(function() {
		window.parent.location.reload();
	}, 3000);
}
function aesMinEncrypt(key, iv, word){
    	    var _word = CryptoJS.enc.Utf8.parse(word),
    	        _key = CryptoJS.enc.Utf8.parse(key),
    			_iv = CryptoJS.enc.Utf8.parse(iv);
    	    var encrypted = CryptoJS.AES.encrypt(_word, _key, {
    					iv: _iv,
    	                mode: CryptoJS.mode.CBC,
    	                padding: CryptoJS.pad.Pkcs7
    	        });
    	    return encrypted.toString();
    	}
</script>
</body>
</html>