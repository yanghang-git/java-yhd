<%--
  Created by IntelliJ IDEA.
  User: 北方
  Date: 2020/7/2
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.yhd.util.ConnectionFactory" %>
<html>
<head>
	<title>YHD BackEnd</title>
	<style>
		.catalog .cValue  button {
			padding: 4px 10px !important;
		}
		.catalog .cValue  .catalog-oper {
			position: absolute;
			right: 6%;
		}
		.catalog .cValue .catalog-item {
			padding: 8px 0;
		}
		.catalog .cValue>ul {
			padding-bottom: 20px;
		}
	</style>
</head>
<body>

<div class="row">
	<!-- navigation bar -->
	<div class="col-2">
		<%@ include file="../commonJSP/navigationbar.jsp"%>
	</div>

	<div class="col bar">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="backend/index.jsp">Home</a></li>
				<li class="breadcrumb-item active">goods - catalog</li>
			</ol>
		</nav>

		<div class="container">

			<blockquote class="blockquote">
				<p class="mb-0">对商品目录进行管理。</p>
				<footer class="blockquote-footer">To manage the catalogue of goods</footer>
			</blockquote>



			<div class="row  justify-content-end">
				<form class="form-inline">
					<div class="form-group mb-2">
						<label for="catalog">目录名称：</label>
					</div>
					<div class="form-group mx-sm-3 mb-2">
						<input type="text" id="catalog" placeholder="目录名称"  class="form-control catalogName">
					</div>
					<div class="form-group mb-2">
						<button type="button" class="btn search btn-primary">Search</button>
					</div>
					<div class="form-group mx-sm-3 mb-2" >
						<button class="btn btn-outline-dark add" type="button" data-toggle="modal"  data-target="#updateCatalog">添加</button>
					</div>
				</form>
			</div>

			<div class="catalog">

				<table class="table">
					<thead class="thead-light">
					<tr>
						<th scope="col">商品目录</th>
						<th scope="col" class="text-right" >操作</th>
					</tr>
				</table>
				<div class="cValue">

				</div>
			</div>

		</div>
	</div>

</div>
<!-- hidden of box -->
<div class="modal" id="updateCatalog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Goods Catalog</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form>
					<div class="isHide">
						<div class="form-group">
							<span>请选择添加目录:</span>
						</div>
						<div class="form-group row">
							<div class="col">
								<label class="col-form-label" for="levelOne">一级目录：</label>
								<input name="level" checked type="radio" class="custom-radio" id="levelOne">
							</div>
							<div class="col">
								<label class="col-form-label" for="levelTwo">二级目录：</label>
								<input name="level" type="radio" class="custom-radio" id="levelTwo">
							</div>
						</div>

						<div class="form-group selectOne">
							<select class="custom-select" name="oneLevel">
							</select>
						</div>
					</div>

					<div class="form-group">
						<label for="catalogName" class="col-form-label">商品目录名称：</label>
						<input type="text" class="form-control" id="catalogName" >
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn closeBox btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn save btn-primary">Save changes</button>
			</div>
		</div>
	</div>
</div>

<%@include file="../../commonJSP/hint.jsp"%>
<script>
	const urlPath = "${pageContext.request.contextPath}/back/goods/catalog";


	function updateCatalog() {
		$.ajax({
			url: urlPath,
			method: 'post',
			data: {
				'${ContentConstant.CONTENT_METHOD_NAME}' : "updateCatalogNameById",
				catalogId: updateCatalogId,
				catalogName: $('#catalogName').val()
			},
			success: (data) => {
				mySuccess(data);
				loadGoodsCatalogByNameOrUpId();
			},
			error: () => myError()
		});
		$('.closeBox').click();
	}

	function addCatalog() {
		let catalogUpId = 0;
		if ($('#levelTwo').is(":checked")) {
			catalogUpId = $('.selectOne select').val();
		}
		if ($('#levelThree').is(":checked")) {
			catalogUpId = $('.selectTwo select').val();
		}
		if(catalogUpId == null) {
			showHint("系统提示", "请选择正确的目录");
			return;
		}
		$.ajax({
			url: urlPath,
			method: 'post',
			data: {
				'${ContentConstant.CONTENT_METHOD_NAME}' : "addCatalog",
				catalogName: $('#catalogName').val(),
				catalogUpId: catalogUpId
			},
			success: (data) => {
				mySuccess(data);
				loadGoodsCatalogByNameOrUpId();
			},
			error: () => myError()
		});
		$('.closeBox').click();
	}

	$('.save').click(function () {
		if ($('#catalogName').val() === '') {
			showHint("系统提示", "商品目录名称不允许为空");
			return;
		}
		updateCatalogId === undefined ? addCatalog() : updateCatalog();
	});

	$('.add').click(function () {
		updateCatalogId = undefined;
		$('.isHide').show();
	});
	let updateCatalogId;
	$('body').on('click', '.update', (function () {
		updateCatalogId = getCatalogId(this);
		$('#catalogName').val(getCatalogName(this));
		$('.isHide').hide();
	}));

	$('body').on('click', '.remove', (function () {
		if (!window.confirm("你确定要删除" + getCatalogName(this) + "商品目录吗?")) return;
		$.ajax({
			url: urlPath,
			method: 'post',
			data: {
				'${ContentConstant.CONTENT_METHOD_NAME}' : "removeCatalogById",
				catalogId: getCatalogId(this)
			},
			success: (data) => {
				mySuccess(data);
				loadGoodsCatalogByNameOrUpId();
			},
			error: () => myError()
		})
	}));


	function getCatalogId(element) {
		return $(element).parent().parent().attr('catalogid');
	}
	function getCatalogName(element) {
		return $(element).parent().parent().children('.catalog-name').text();
	}
	function getCatalogUpId(element) {
		return $(element).parent().parent().parent().parent().parent().children('.catalog-item').children('.catalog-name').text();
	}

	$('.selectTwo').hide();
	$('.selectOne').hide();

	$('#levelOne').change(function () {
		$('.selectOne').hide();
		$('.selectTwo').hide();
	});
	$('#levelTwo').change(function () {
		$('.selectOne').show();
		$('.selectTwo').hide();
		loadLevelOne();
	});

	function loadLevelOne() {
		$.ajax({
			url: urlPath,
			method: 'post',
			async : false,
			data: {
				'${ContentConstant.CONTENT_METHOD_NAME}' : "getCatalogByUpId",
				upId: 0
			},
			success: (data) => {
				$('.selectOne select').empty();
				$(JSON.parse(data)).each(function(){
					$('.selectOne select').append($(`<option value="` + this.id + `">` + this.name + `</option>`));
				})
			},
			error: () => myError()
		})
	}


	$('.search').click(() => {
		loadGoodsCatalogByNameOrUpId();
	});

	function loadGoodsCatalogByNameOrUpId() {
		let catalogName = $('.catalogName').val();
		if(catalogName === "/" || catalogName === " ") {
			showHint("系统提示", "请输入正确的查询名称");
			return;
		}
		$.ajax({
			url: urlPath,
			method: 'post',
			data: {
				'${ContentConstant.CONTENT_METHOD_NAME}' : "getCatalogAllByName",
				catalogName: catalogName
			},
			success: (data) => {
				$('.catalog .cValue').empty().append(catalogName === "" ? $(loadAddressCatalogHtmlOfText(JSON.parse(data))) : $(loadAddressCatalogHtmlOfText(JSON.parse(data)).replace(new RegExp(catalogName, "g"), `<span style="background: red">` + catalogName + `</span>`)));
			},
			error: () => myError()
		})
	}

	loadGoodsCatalogByNameOrUpId();
	function loadAddressCatalogHtmlOfText(target) {
		let result = "";
		for (let i = 0; i < target.length; i++) {
			result += `<ul>
				<li>
				<div class="row catalog-item" catalogId="` + target[i].id + `">
					<div class="catalog-name">` + target[i].name + `</div>
					<div class="catalog-oper">
						<button class="btn update btn-outline-dark" data-toggle="modal" data-target="#updateCatalog">修改</button>
						<button class="btn remove btn-outline-dark">删除</button>
					</div>
				</div>
				` + (target[i].catalogs !== null && target[i].catalogs.length > 0 ?  loadAddressCatalogHtmlOfText(target[i].catalogs)  : "") + `
				</li>
				</ul>`;
		}
		return result;
	}
</script>
</body>
</html>
