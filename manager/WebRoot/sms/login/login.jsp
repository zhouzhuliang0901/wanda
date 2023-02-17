<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="coral.base.app.AppContext"%>
<%
	String webRoot = AppContext.webRootPath;
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<link href="css/reset.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
<style>
	html,body{
		position: relative;
		height: 100%;
	}
</style>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/crypto-js.js" type="text/javascript"></script>
<script src="js/aes.js" type="text/javascript"></script>
<script src="js/workstation.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript">
$(function (){
/* 登录页整体大小 */
	loginResize();
});
</script>
</head>

<body onkeydown="keyLogin()" class="login-bg">

	<div class="login-view">
		<form id="loginform">
			<h3></h3>
			<input class="icon-sign1" id="ST_LOGIN_NAME" name="ST_LOGIN_NAME" type="text" value="" placeholder="账户名" />
			<input class="icon-sign2" id="ST_PASSWORD" name="ST_PASSWORD" type="password" value="" placeholder="密码" />
			<input class="icon-sign3" type="text"
								id="ST_VALIDATE_CODE" name="ST_VALIDATE_CODE" placeholder="验证码" value="">
								<!-- style="width:100%;"> -->
			<div class="code">
				<img id="validateCode" onclick="document.getElementById('validateCode').src='<%=webRoot%>/sms/login/validateCode.do?t='+new Date().getTime()" src="<%=webRoot%>/sms/login/validateCode.do"> 
<!-- 				<a href="javascript:void(0);" onclick="document.getElementById('validateCode').src='<%=webRoot%>/sms/login/validateCode.do?t='+new Date().getTime()">看不清，换一张</a> -->
			</div>
								 
			<!-- <label>
				<input type="checkbox" />使我保持登录状态
			</label> -->
			<div class="confirm">
				<button type="button" onclick="loginSubmit()">登录</button>
				<button type="button">取消</button>
			</div>
		</form>
	</div>
<script type="text/javascript"
		src="<%=webRoot%>/resources/jquery/jquery-1.11.1.min.js"></script>
	<script type="text/javascript"
		src="<%=webRoot%>/sms/lib/h-ui/js/H-ui.js"></script>
	<script type="text/javascript"
		src="<%=webRoot%>/resources/layer/2.1/layer.js"></script>
	<script type="text/javascript">
			var key = "ax7x90.3k_10li5u";
    		//密钥偏移量 (需要前端和后端保持一致)十六位作为密钥偏移量
    		var iv = "a0.l954b_107x90l";
    		//获取加密后的密码
    		/* var password = aesMinEncrypt(key,iv,'admin');
    		alert("加密后的密码为："+password); */
		function loginSubmit() {
			$.ajax({
				type : "POST",
				url : "<%=webRoot%>/sms/login/login.do",
				//data : $("#loginform").serialize(),
				data:{
					'ST_LOGIN_NAME':$("#ST_LOGIN_NAME").val(),
					'ST_PASSWORD':aesMinEncrypt(key,iv,$("#ST_PASSWORD").val()),
					'ST_VALIDATE_CODE':$("#ST_VALIDATE_CODE").val()
					},
					
				dataType : "json",
				success : function(data) {
					if(data.isSuccess){
						window.location = "<%=webRoot%>/sms/frame/frame.do";
					} else {
						layer.msg(data.errorMsg, {
							time : 2000
						});
						document.getElementById('validateCode').src='<%=webRoot%>/sms/login/validateCode.do?t='+new Date().getTime();
					}
				},
				error : function() {
					layer.msg("登录异常，请重新登录", {
						time : 2000
					});
					document.getElementById('validateCode').src='<%=webRoot%>/sms/login/validateCode.do?t=' + new Date().getTime();
						}
					});
		}

		function keyLogin() {
			if (event.keyCode == 13) {
				loginSubmit();
			}
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




