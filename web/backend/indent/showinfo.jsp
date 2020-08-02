<%--
  Created by IntelliJ IDEA.
  User: 北方
  Date: 2020/7/28
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
	<base href="<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"%>">
	<script src="js/jquery.min.js"></script>
	<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h1 class="mt-5">订单详情</h1>
		<div class="lead">
			<p>编号：${requestScope.indent.id}</p>
			<p>用户：${requestScope.indent.userId}</p>
			<p>商品：${requestScope.indent.goodsId}</p>
			<p>款式：${requestScope.indent.type}</p>
			<p>数量：${requestScope.indent.buyNumber}</p>
			<p>总价：${requestScope.indent.totalPrices}</p>
			<p>地址：${requestScope.indent.addressId}</p>
			<p>时间：${requestScope.indent.orderTime}</p>
		</div>
		<p>Use <a href="/docs/examples/sticky-footer-navbar/">the sticky footer with a fixed navbar</a> if need be, too.</p>
	</div>






</body>
</html>
