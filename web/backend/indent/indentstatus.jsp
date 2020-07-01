<%--
  Created by IntelliJ IDEA.
  User: 北方
  Date: 2020/7/1
  Time: 13:38
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
				<li class="breadcrumb-item"><a href="../index.jsp">Home</a></li>
				<li class="breadcrumb-item active">indent - status</li>
			</ol>
		</nav>


		<div class="container">

			<div class="row">
				<div class="col">
					<blockquote class="blockquote">
						<p class="mb-0">对订单的状态进行管理。</p>
						<footer class="blockquote-footer">manage the indent of status the</footer>
					</blockquote>
				</div>
				<div class="col-1" style="margin-top: 30px">
					<button class="btn btn-outline-dark add" data-toggle="modal"  data-target="#updateStatus">添加</button>
				</div>
			</div>

			<table class="table">
				<thead class="thead-light">
				<tr>
					<th scope="col">#</th>
					<th scope="col">名称</th>
					<th scope="col">操作</th>
				</tr>
				</thead>
				<tbody>

				<tr>
					<th scope="row" class="statusId">1</th>
					<td class="statusName">Otto</td>
					<td>
						<button class="btn update btn-outline-dark" data-toggle="modal"  data-target="#updateStatus" >修改</button>
						<button class="btn remove btn-outline-dark">删除</button>
					</td>
				</tr>

				</tbody>
			</table>

			<div class="row  justify-content-end">
				<footer class="blockquote-footer">请慎重操作订单状态</footer>
			</div>
		</div>
	</div>
</div>


<!-- hidden of update box -->
<div class="modal fade" id="updateStatus">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Update Slide Show</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form>
					<div class="form-group">
						<label class="col-form-label" for="statusId">编号:</label>
						<input disabled="disabled" type="text" class="form-control id" id="statusId">
					</div>
					<div class="form-group">
						<label for="statusName" class="col-form-label">状态名称：</label>
						<input type="url" class="form-control" id="statusName" >
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn save btn-primary">Save changes</button>
			</div>
		</div>
	</div>
</div>


<%@ include file="../../commonJSP/hint.jsp"%>
</body>
<script>

	const urlPath = "${pageContext.request.contextPath}/back/indent/status";

	let isAdd = false;
	$('tbody').on('click', '.remove', function () {
		if (!window.confirm("确定要删除 " + $(this).parent().parent().children('.statusName').text() + "的状态吗？")) {
			return;
		}
		$.ajax({
			url: urlPath,
			method: 'post',
			data: {
				'${ContentConstant.CONTENT_METHOD_NAME}' : 'removeIndentStatus',
				id: $(this).parent().parent().children('.statusId').text(),
			},
			success: (data) => {
				mySuccess(data);
				loadAllSlideShowData();
			},
			error: () => myError()
		})
	});

	$('tbody').on('click', '.update', function () {
		isAdd = false;
		let $parent = $(this).parent().parent();
		$('#statusId').val($parent.children('.statusId').attr('disabled', 'disabled').text());
		$('#statusName').val($parent.children('.statusName').text());
	});

	$('body').on('click', '.add', function () {
		isAdd = true;
		$('#statusId').attr('disabled', 'disabled').val('');
		$('#statusName').val('');
	});

	$('body').on('click', '.save', function () {
		if($('#statusName').val() === '') {
			showHint("系统提示", "请填写完整的信息。");
			return;
		}
		isAdd ? add() : update();
	});

	function add() {
		$.ajax({
			url: urlPath,
			method: 'post',
			data: {
				'${ContentConstant.CONTENT_METHOD_NAME}' : 'addIndentStatus',
				name: $('#statusName').val()
			},
			success: (data) => {
				mySuccess(data);
				loadAllSlideShowData();
			},
			error: () => myError()
		});
		$('.save').prev().click();
	}

	function update() {
		$.ajax({
			url: urlPath,
			method: 'post',
			data: {
				'${ContentConstant.CONTENT_METHOD_NAME}' : 'updateIndentStatus',
				id: $('#statusId').removeAttr('disabled').val(),
				name: $('#statusName').val(),
			},
			success: (data) => {
				mySuccess(data);
				loadAllSlideShowData();
			},
			error: () => myError()
		});
		$('.save').prev().click();
	}

	function loadAllSlideShowData() {
		$.ajax({
			url: urlPath,
			method:'post',
			data: {
				'${ContentConstant.CONTENT_METHOD_NAME}' : 'getAll'
			},
			success : (data) => {
				$('.table tbody').empty();
				$(JSON.parse(data)).each(function () {
					$('.table tbody').append($(`<tr>
							<th scope="row" class="statusId">` + this.id + `</th>
							<td class="statusName">` + this.statusName + `</td>
							<td>
								<button class="btn update btn-outline-dark" data-toggle="modal" data-target="#updateStatus" >修改</button>
								<button class="btn remove btn-outline-dark">删除</button>
							</td>
						</tr>`));

				});

			},
			error: () => myError()
		})
	}

	loadAllSlideShowData();


</script>
</html>
