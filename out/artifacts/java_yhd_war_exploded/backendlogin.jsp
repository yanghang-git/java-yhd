<%--
  Created by IntelliJ IDEA.
  User: 北方
  Date: 2020/6/29
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.yhd.util.ContentConstant" %>
<html>
<head>
	<title>Title</title>
	<link rel="stylesheet" href="./css/bootstrap.min.css">
	<script src="./js/jquery.min.js"></script>
	<style>
		body{
			background-color: #f8f9fa;
		}

		form {
			width: 20%;
			margin: 10% auto 0;
		}

		form>input {
			margin-bottom: 15px;
		}

		form>div>a.login {
			margin-top: 8px;
			margin-right: 12px;
		}
		form>div{
			margin: 0 !important;
		}
	</style>
</head>
<%@ include file="commonJSP/hint.jsp"%>
<body class="text-center">
<form action="${pageContext.request.contextPath}/admin" method="post">
	<h1 class="h3 mb-3 font-weight-normal">YHD Back-End</h1>
	<input type="hidden" name="${ContentConstant.CONTENT_METHOD_NAME}" value="login"/>
	<label for="inputUserName" class="sr-only">Username</label>
	<input type="text" id="inputUserName" class="form-control" required="required" placeholder="Username" name="username">
	<label for="inputPassword" class="sr-only">Password</label>
	<input type="password" id="inputPassword" class="form-control" required="required" placeholder="Password" name="password">
	<button type="submit" class="btn btn-primary btn-block col-md" >Login</button>
	<p class="mt-5 mb-3 text-muted">@ 2017-2020</p>
</form>
</body>
</html>
