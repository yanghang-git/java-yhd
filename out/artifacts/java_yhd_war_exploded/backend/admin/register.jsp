<%--
  Created by IntelliJ IDEA.
  User: 北方
  Date: 2020/7/11
  Time: 20:10
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
		<%@ include file="../commonJSP/navigationbar.jsp"%>
	</div>
	<!-- page value -->
	<div class="col bar">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="backend/index.jsp">Home</a></li>
				<li class="breadcrumb-item active">admin - add</li>
			</ol>
		</nav>


		<div class="container">

			<div class="row">
				<div class="col">
					<blockquote class="blockquote">
						<p class="mb-0">添加管理员。</p>
						<footer class="blockquote-footer">add admin</footer>
					</blockquote>
				</div>
			</div>

			<form  style="width: 60%;margin: 15px auto 0;">
				<input type="hidden" name="${ContentConstant.CONTENT_METHOD_NAME}" value="addAdmin">
				<div class="form-group">
					<label for="admin-account">账号</label>
					<input type="text" class="form-control" name="id" id="admin-account" aria-describedby="emailHelp">
				</div>
				<div class="form-group">
					<label for="admin-password">密码</label>
					<input type="password" name="password" class="form-control" id="admin-password">
				</div>
				<div class="form-group">
					<label>权限</label>
					<div class="row" style="margin-left: 15px;">
						<div class="form-check form-check-inline">
							<input class="form-check-input" id="power-user" name="power" type="checkbox" value="user">
							<label class="form-check-label" for="power-user">用户权限</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" id="power-activity" name="power" type="checkbox" value="activity">
							<label class="form-check-label" for="power-activity">活动权限</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" id="power-indent" name="power" type="checkbox" value="indent">
							<label class="form-check-label" for="power-indent">订单权限</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" id="power-goods" name="power" type="checkbox" value="goods">
							<label class="form-check-label" for="power-goods">商品权限</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" id="power-address" name="power" type="checkbox" value="address">
							<label class="form-check-label" for="power-address">地址权限</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<button type="button" class="btn btn-primary save">保存</button>
				</div>
			</form>
		</div>
	</div>
</div>
<%@ include file="../../commonJSP/hint.jsp"%>
<script>
	const urlPath = '${pageContext.request.contextPath}/admin';

	let accountIfExist = false;
	$('#admin-account').blur(function () {
		$.ajax({
			url: urlPath,
			method: 'post',
			data: {
				'${ContentConstant.CONTENT_METHOD_NAME}' : "containsId",
				id: $('#admin-account').val()
			},
			success: (data) => {
				if (data !== '') {
					mySuccess(data);
					accountIfExist = true;
				} else
					accountIfExist = false;
			},
			error: () => myError()
		});
	});

	$('.save').click(function () {
		if ($('#admin-account').val() === '' || $('#admin-password').val() === '') {
			showHint("系统提示", "请输入完整的信息");
			return;
		}
		if (accountIfExist) {
			showHint("系统提示", "账号已存在无法进行注册，请修改账号。");
			return;
		}
		$.ajax({
			url: urlPath,
			method: 'post',
			data: $.param($('form input').serializeArray()),
			success: (data) => {
				mySuccess(data)
			},
			error: () => myError()
		});
		$('#admin-account').val('');
		$('#admin-password').val('');
		$('[name="power"]').prop('checked', false);
	});
</script>
</body>
</html>
