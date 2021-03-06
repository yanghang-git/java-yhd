<%--
  Created by IntelliJ IDEA.
  User: 北方
  Date: 2020/6/30
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.yhd.util.ContentConstant" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<li class="breadcrumb-item active">user freeze or unfreeze</li>
			</ol>
		</nav>



		<div class="container">
			<blockquote class="blockquote">
				<p class="mb-0">对用户进行冻结 / 解冻。</p>
				<footer class="blockquote-footer">to user proceed freeze / unfreeze.</footer>
			</blockquote>

			<div class="row  justify-content-end">
				<form class="form-inline">
					<div class="form-group mb-2">
						<label for="account">用户账号：</label>
					</div>
					<div class="form-group mx-sm-3 mb-2">
						<input type="text" class="form-control" id="account" placeholder="Account">
					</div>
					<button type="button" class="btn btn-primary mb-2">Search</button>
				</form>
			</div>

			<table class="table">
				<thead class="thead-light">
				<tr>
					<th scope="col">账号</th>
					<th scope="col">手机号</th>
					<th scope="col">是否冻结</th>
					<th scope="col">操作</th>
				</tr>
				</thead><tbody>
			</table>
			<%@ include file="../../commonJSP/paging.jsp"%>
		</div>
	</div>
</div>
<%@include file="../../commonJSP/hint.jsp"%>

</body>
<script>
	// request network address
	const urlPath = '${pageContext.request.contextPath}/back/user';


	$('button[type="button"]').on('click', () => {
		pageNo = 1;
		pagingAjax();
	});

	function pagingAjax() {
		$.ajax({
			url: urlPath,
			method:'post',
			data: {
				'${ContentConstant.CONTENT_METHOD_NAME}': 'getAllByIdList',
				account: $('#account').val(),
				currPageNo:pageNo
			},
			success: (data) => {
				let pageData = loadPaging(JSON.parse(data));
				$('.table tbody').empty();
				$(pageData).each(function () {

					$('.table tbody').append($('<tr> <th scope="row">' + this.id + '</th> <td>' + this.phone + '</td> ' +
						'<td>' + (this.freeze ? "Freeze" : "notFreeze") + '</td> <td> <button ' + (this.freeze ? "disabled" : "" + '') +
						' class="btn freeze btn-outline-dark">冻结</button> <button ' + (this.freeze ? "" : "disabled") + ' class="btn ' +
						'unfreeze btn-outline-dark">解冻</button> </td> </tr>'));
				});

			},
			error: () => myError()
		})
	}

	pagingAjax();


	$('.table tbody').on('click', '.freeze', function () {
		$.ajax({
			url: urlPath,
			method:'post',
			data: {
				'${ContentConstant.CONTENT_METHOD_NAME}': 'freeze',
				'account': $(this).parent().parent().children('th').text()
			},
			success: (data) => {
				$(this).attr('disabled', 'disabled');
				$(this).next().removeAttr('disabled');
				mySuccess(data);
			},
			error: myError()
		});
	});


	$('.table tbody').on('click', '.unfreeze', function () {
		$.ajax({
			url: urlPath,
			method:'post',
			data: {
				'${ContentConstant.CONTENT_METHOD_NAME}': 'unfreeze',
				'account': $(this).parent().parent().children('th').text()
			},
			success: (data) => {
				$(this).attr('disabled', 'disabled');
				$(this).prev().removeAttr('disabled');
				mySuccess(data);
			},
			error: myError()
		});
	});
</script>
</html>
