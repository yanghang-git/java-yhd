<%--
  Created by IntelliJ IDEA.
  User: 北方
  Date: 2020/6/29
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>YHD BackEnd</title>
</head>
<body>

<div class="row">
	<!-- navigation bar -->
	<div class="col-2">
		<%@ include file="commonJSP/navigationbar.jsp"%>
	</div>

	<!-- page value -->
	<div class="col">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item active" aria-current="page">Home</li>
			</ol>
		</nav>

		<div class="jumbotron">
			<h1 class="display-4">YHD BACKEND!</h1>
			<p class="lead">Here is the background of Yihaodian, which can manage the data of the whole Yihaodian.Such as activities/products/catalogues and so on...le component for calling extra attention to featured content or information.</p>
			<p class="lead">I believe you will love this backstage.</p>
			<hr class="my-4">
			<p>这里是一号店后台，在此可以管理整个一号店的数据。比如活动/商品/目录等等...</p>
			<p>相信你会爱上这个后台。</p>
		</div>
	</div>
</div>


</body>
</html>
