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
				<li class="breadcrumb-item active">activity - slide show</li>
			</ol>
		</nav>


		<div class="container">

			<div class="row">
				<div class="col">
					<blockquote class="blockquote">
						<p class="mb-0">对主页的轮播图进行管理。</p>
						<footer class="blockquote-footer">manage the rotations of the home page</footer>
					</blockquote>
				</div>
				<div class="col-1" style="margin-top: 30px">
					<button class="btn btn-outline-dark add" data-toggle="modal"  data-target="#updateSlideShow">添加</button>
				</div>
			</div>

			<table class="table">
				<thead class="thead-light">
				<tr>
					<th scope="col">顺序</th>
					<th scope="col">图片</th>
					<th scope="col">文字</th>
					<th scope="col">URL</th>
					<th scope="col">操作</th>
				</tr>
				</thead>
				<tbody></tbody>
			</table>

			<div class="row  justify-content-end">
				<footer class="blockquote-footer">轮播图数量不可过多</footer>
			</div>
		</div>
	</div>
</div>


	<!-- hidden of update box -->
	<div class="modal fade" id="updateSlideShow">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Slide Show</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label class="col-form-label" for="slideShowId">顺序:</label>
							<input type="text" class="form-control id" id="slideShowId">
						</div>
						<div class="form-group">
							<label for="slideShowImageUrl" class="col-form-label">图片路径：</label>
							<input type="url" class="form-control" id="slideShowImageUrl" >
						</div>
						<div class="form-group">
							<label for="slideShowFont" class="col-form-label">文字：</label>
							<input type="text" class="form-control" id="slideShowFont" >
						</div>
						<div class="form-group">
							<label for="slideShowUrl" class="col-form-label">网址路径：</label>
							<input type="url" class="form-control" id="slideShowUrl" >
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

	<button data-toggle="modal"  data-target="#checkRemove"></button>
	<%@ include file="../../commonJSP/hint.jsp"%>
</body>
<script>

	const urlPath = "${pageContext.request.contextPath}/back/activity/slideshow";

	$('tbody').on('click', '.remove', function () {
		let slideShowId = $(this).parent().parent().children('.slideShowId').text();
		if (!window.confirm("确定要删除序号为:" + slideShowId + "的记录吗")) return;

		$.ajax({
			url: urlPath,
			method: 'post',
			data: {
				'${ContentConstant.CONTENT_METHOD_NAME}' : 'removeSlideshow',
				id: slideShowId,
			},
			success: (data) => {
				mySuccess(data);
				loadAllSlideShowData();
			},
			error: () => myError()
		})
	});

	$('tbody').on('click', '.update', function () {
		let $parent = $(this).parent().parent();
		$('#slideShowId').val($parent.children('.slideShowId').text()).attr('disabled', 'disabled');
		$('#slideShowImageUrl').val($parent.children().children('.slideShowImageUrl').attr('href'));
		$('#slideShowFont').val($parent.children('.slideShowFont').text());
		$('#slideShowUrl').val($parent.children().children('.slideShowUrl').attr('href'));
	});

	$('body').on('click', '.add', function () {
		$('#slideShowId').removeAttr('disabled').val('');
		$('#slideShowImageUrl').val('');
		$('#slideShowFont').val('');
		$('#slideShowUrl').val('');
	});

	$('body').on('click', '.save', function () {
		if($('#slideShowImageUrl').val() === ''|| $('#slideShowFont').val() === '' || $('#slideShowUrl').val() === '') {
			showHint("系统提示", "请填写完整的信息。");
			return;
		}
		$('#slideShowId').attr('disabled') === 'disabled' ? update() : add();
	});

	function add() {
		let slideShowId = $('#slideShowId').val();
		if (!/^[0-9]+$/.test(slideShowId)) {
			showHint("系统提示", "序号不能为空或非数字，请改完后再保存。");
			return;
		}
		$.ajax({
			url: urlPath,
			method: 'post',
			data: {
				'${ContentConstant.CONTENT_METHOD_NAME}' : 'addSlideshow',
				id: slideShowId,
				image: $('#slideShowImageUrl').val(),
				font: $('#slideShowFont').val(),
				url: $('#slideShowUrl').val()
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
				'${ContentConstant.CONTENT_METHOD_NAME}' : 'updateSlideshow',
				id: $('#slideShowId').removeAttr('disabled').val(),
				image: $('#slideShowImageUrl').val(),
				font: $('#slideShowFont').val(),
				url: $('#slideShowUrl').val()
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
							<th scope="row" class="slideShowId">` + this.id + `</th>
							<td><a class="slideShowImageUrl" href="` + this.image + `" target="_blank" >查看</a></td>
							<td class="slideShowFont">` + this.font + `</td>
							<td><a class="slideShowUrl" href="` + this.url + `" target="_blank" >打开</a></td>
							<td>
								<button class="btn update btn-outline-dark" data-toggle="modal"  data-target="#updateSlideShow" >修改</button>
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
