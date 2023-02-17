<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="coral.base.app.AppContext"%>
<%
	String webRoot = AppContext.webRootPath;
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include
	page="${webRoot}/resources/header/resource_bootstrap_import.jsp"></jsp:include>
<title>403错误</title>
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h1 align="center">403错误</h1>
		</div>
		<div>
			<h1 align="center">
				<button type="button" class="btn btn-success btn-lg"
					disabled="disabled">您无权访问该系统！</button>
			</h1>
		</div>
	</div>
</body>
</html>