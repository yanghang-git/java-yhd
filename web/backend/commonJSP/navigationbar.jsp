<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 北方
  Date: 2020/6/30
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.yhd.util.ContentConstant" %>
<html>
<head>
	<title>Title</title>
	<base href="<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"%>">
	<script src="js/jquery.min.js"></script>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	<style>
		.row {
			max-width: 100%;
			margin: 0;
			padding: 0;
		}
		.bar {
			padding: 0;
		}
		.row>.col-2{
			padding: 0;
		}
		.navigation {
			background: #20222A !important;
			color: #fff;
			font-size: 17px;
			width: 16.6%;
			max-width: 16.6%;
			position: fixed;
			max-height: 100%;
			height: 100%;
			overflow: auto;
		}

		/* bing查询 */
		.navigation::-webkit-scrollbar {/*滚动条整体样式*/
			width: 6px;     /*高宽分别对应横竖滚动条的尺寸*/
		}
		.navigation::-webkit-scrollbar-thumb {/*滚动条里面小方块*/
			-webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
			background: #535353;

		}
		.navigation::-webkit-scrollbar-track {/*滚动条里面轨道*/
			-webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
			background: #EDEDED;
		}
		.navigation .row .title {
			width: 100%;
		}
		.navigation .row .title,
		.navigation .username {
			line-height: 50px;
		}
		.navigation a {
			color: #fff;
		}
		.navigation .nav {
			padding: 0;
			width: 100%;
		}
		.navigation>* {
			border-bottom:1px solid #999;
			cursor: pointer;
		}
		.navigation>*:last-of-type {
			border-bottom: none;
		}

		.navigation .username {
			text-align: center;
		}

		.navigation>.row>.nav {
			font-size: 15px;
			background-color: #5a6268;
			display: none;
		}

		.navigation>.row>.row.title>.col-2{
			padding-left: 15px;
			padding-top: 17px;
		}

		tbody a,
		tbody a:hover {
			color: #212529;
		}
	</style>
</head>
<body>

	<!-- navigation bar -->
	<div class="navigation">
		<!-- admin username show box -->
		<div class="username">
			<a href="backend/index.jsp">Admin pro</a>
		</div>

		<c:if test="${sessionScope[ContentConstant.SESSION_ADMIN].userPower}">
			<div class="row user">
				<div class="row title">
					<div class="col-2">
						<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-people" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
							<path fill-rule="evenodd" d="M15 14s1 0 1-1-1-4-5-4-5 3-5 4 1 1 1 1h8zm-7.995-.944v-.002.002zM7.022 13h7.956a.274.274 0 0 0 .014-.002l.008-.002c-.002-.264-.167-1.03-.76-1.72C13.688 10.629 12.718 10 11 10c-1.717 0-2.687.63-3.24 1.276-.593.69-.759 1.457-.76 1.72a1.05 1.05 0 0 0 .022.004zm7.973.056v-.002.002zM11 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4zm3-2a3 3 0 1 1-6 0 3 3 0 0 1 6 0zM6.936 9.28a5.88 5.88 0 0 0-1.23-.247A7.35 7.35 0 0 0 5 9c-4 0-5 3-5 4 0 .667.333 1 1 1h4.216A2.238 2.238 0 0 1 5 13c0-1.01.377-2.042 1.09-2.904.243-.294.526-.569.846-.816zM4.92 10c-1.668.02-2.615.64-3.16 1.276C1.163 11.97 1 12.739 1 13h3c0-1.045.323-2.086.92-3zM1.5 5.5a3 3 0 1 1 6 0 3 3 0 0 1-6 0zm3-2a2 2 0 1 0 0 4 2 2 0 0 0 0-4z"></path>
						</svg>
					</div>
					<div class="col">用户管理</div>
				</div>
				<div class="nav flex-column">
					<a href="backend/user/freezemanage.jsp" class="nav-link">冻结 / 解冻</a>
				</div>
			</div>
		</c:if>

		<c:if test="${sessionScope[ContentConstant.SESSION_ADMIN].activityPower}">
			<div class="row activity">
				<div class="row title">
					<div class="col-2">
						<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-badge-hd" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
							<path fill-rule="evenodd" d="M14 3H2a1 1 0 0 0-1 1v8a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4a1 1 0 0 0-1-1zM2 2a2 2 0 0 0-2 2v8a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2H2z"></path>
							<path d="M7.396 11V5.001H6.209v2.44H3.687V5H2.5v6h1.187V8.43h2.522V11h1.187zM8.5 5.001V11h2.188c1.811 0 2.685-1.107 2.685-3.015 0-1.894-.86-2.984-2.684-2.984H8.5zm1.187.967h.843c1.112 0 1.622.686 1.622 2.04 0 1.353-.505 2.02-1.622 2.02h-.843v-4.06z"></path>
						</svg>
					</div>
					<div class="col">活动管理</div>
				</div>

				<div class="nav flex-column">
					<a href="backend/activity/slideshow.jsp" class="nav-link">轮播图</a>
				</div>
			</div>
		</c:if>

		<c:if test="${sessionScope[ContentConstant.SESSION_ADMIN].indentPower}">
			<div class="row">
				<div class="row title">
					<div class="col-2">
						<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-layout-text-sidebar" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
							<path fill-rule="evenodd" d="M14 1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"></path>
							<path fill-rule="evenodd" d="M11 15V1h1v14h-1zM3 3.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm0 3a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm0 3a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm0 3a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5z"></path>
						</svg>
					</div>
					<div class="col">订单管理</div>
				</div>

				<div class="nav flex-column">
					<a class="nav-link" href="backend/indent/indentstatus.jsp">订单状态</a>
					<a class="nav-link" href="backend/indent/indent.jsp">订单详情</a>
				</div>
			</div>
		</c:if>

		<c:if test="${sessionScope[ContentConstant.SESSION_ADMIN].goodsPower}">
			<div class="row">
				<div class="row title">
					<div class="col-2">
						<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-aspect-ratio" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
							<path fill-rule="evenodd" d="M0 3.5A1.5 1.5 0 0 1 1.5 2h13A1.5 1.5 0 0 1 16 3.5v9a1.5 1.5 0 0 1-1.5 1.5h-13A1.5 1.5 0 0 1 0 12.5v-9zM1.5 3a.5.5 0 0 0-.5.5v9a.5.5 0 0 0 .5.5h13a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5h-13z"></path>
							<path fill-rule="evenodd" d="M2 4.5a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 0 1H3v2.5a.5.5 0 0 1-1 0v-3zm12 7a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1 0-1H13V8.5a.5.5 0 0 1 1 0v3z"></path>
						</svg>
					</div>
					<div class="col">商品管理</div>
				</div>

				<div class="nav flex-column">
					<a class="nav-link" href="backend/goods/catalog.jsp">商品目录</a>
					<a class="nav-link" href="backend/goods/goods.jsp">商品详情</a>
				</div>
			</div>
		</c:if>

		<c:if test="${sessionScope[ContentConstant.SESSION_ADMIN].addressPower}">
			<div class="row">
				<div class="row title">
					<div class="col-2">

						<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-cursor" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
							<path fill-rule="evenodd" d="M14.082 2.182a.5.5 0 0 1 .103.557L8.528 15.467a.5.5 0 0 1-.917-.007L5.57 10.694.803 8.652a.5.5 0 0 1-.006-.916l12.728-5.657a.5.5 0 0 1 .556.103zM2.25 8.184l3.897 1.67a.5.5 0 0 1 .262.263l1.67 3.897L12.743 3.52 2.25 8.184z"></path>
						</svg>
					</div>
					<div class="col">地址管理</div>
				</div>

				<div class="nav flex-column">
					<a class="nav-link" href="backend/address/catalog.jsp">地址目录</a>
				</div>
			</div>
		</c:if>

		<c:if test="${sessionScope[ContentConstant.SESSION_ADMIN].adminPower}">
			<div class="row">
				<div class="row title">
					<div class="col-2">
						<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-heart" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
							<path fill-rule="evenodd" d="M8 2.748l-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
						</svg>
					</div>
					<div class="col">管理员</div>
				</div>

				<div class="nav flex-column">
					<a class="nav-link" href="backend/admin/register.jsp">注册</a>
				</div>
			</div>
		</c:if>


	</div>


	<script>
	$('.navigation>.row>.title').click(function () {
		$(this).next().slideToggle();
	});
</script>
</body>
</html>
