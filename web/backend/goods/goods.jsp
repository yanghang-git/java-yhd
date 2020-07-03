<%--
  Created by IntelliJ IDEA.
  User: 北方
  Date: 2020/7/3
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.yhd.util.ContentConstant" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
<style>
	.table tbody tr td img{
		max-width: 36px;
		max-height: 36px;
	}
	.table tbody tr td select {
		width: 117px;
	}
	.table tbody tr>td {
		width: 13%;
	}
	.table tbody tr {
		position: relative;
	}

	.table tbody tr {
		border-top: none;
	}


	.table tbody  tr .oper button {
		font-size: 8px;
		padding: 6px;
	}

	#updateCatalog label {
		padding-top: 8px;
	}

	#updateCatalog .col {
		padding: 0;
	}
	#updateCatalog .row button {
		margin: 0 10px;
		padding: 3px;
		width: 36px;
		height: 35px;
		font-size: 10px;
	}
	#updateCatalog .import-value {
		position: absolute;
		z-index: 1050;
		top: 10%;
		background: #ffffff;
		border-radius: 5px;
		border: 1px solid #0b2e13;
		width: 400px;
		padding: 20px;
		left: 11%;
		display: none;
	}
	#updateCatalog .import-value button {
		margin: 0 9px;
	}
	.goods-name-exceed {
		width: 165px;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
	}
</style>
<div class="row">
	<!-- navigation bar -->
	<div class="col-2">
		<%@ include file="../commonJSP/navigationbar.jsp"%>
	</div>

	<!-- page value -->
	<div class="col">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item active" aria-current="page">Home</li>
			</ol>
		</nav>

		<div class="container">
			<blockquote class="blockquote">
				<p class="mb-0">对商品进行管理。</p>
				<footer class="blockquote-footer">manage the merchandise</footer>
			</blockquote>

			<div class="row  justify-content-end">
				<form class="form-inline">
					<div class="form-group mb-2">
						<label for="catalogOneLevel">一级目录：</label>
					</div>
					<div class="form-group mx-sm-3 mb-2">
						<select name="goodsCatalogOneLevelId" id="catalogOneLevel" class="custom-select selectOneLevel"></select>
					</div>
					<div class="form-group mb-2">
						<label for="catalogTwoLevel">二级目录：</label>
					</div>

					<div class="form-group mx-sm-3 mb-2">
						<select name="goodsCatalogTwoLevelId" id="catalogTwoLevel" class="custom-select selectTwoLevel">
							<option value='-1'>全部</option>
						</select>
					</div>
					<div class="form-group mb-2">
						<label for="catalogName">商品名称：</label>
					</div>
					<div class="form-group mx-sm-3 mb-2">
						<input type="text" class="form-control" placeholder="商品名称"  id="catalogName">
					</div>
					<div class="form-group mb-2">
						<button type="button" class="btn search btn-primary">Search</button>
					</div>
					<div class="form-group mx-sm-3 mb-2" >
						<button class="btn btn-outline-dark add" type="button" data-toggle="modal"  data-target="#updateCatalog">添加</button>
					</div>
				</form>
			</div>
			<table class="table">
				<thead class="thead-light">
				<tr>
					<th scope="col">主图</th>
					<th scope="col">名称</th>
					<th scope="col">价格</th>
					<th scope="col">款式</th>
					<th scope="col">种类</th>
					<th scope="col">数量</th>
					<th scope="col">目录</th>
					<th scope="col">操作</th>
				</tr>
				</thead>
				<tbody></tbody>
			</table>


		</div>
	</div>
</div>

<div class="modal" id="updateCatalog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" style="padding: 0">
				<h5 class="modal-title" id="exampleModalLabel" style="padding: 13px 20px 0;">Goods</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">

				<div class="import-value">
					<div class="form-group">
						<label>请输入：</label>
						<input type="text" class="form-group form-control col" >
					</div>
					<div class="form-group">
						<button type="button" class="btn btn-primary import-save float-right">Save</button>
					</div>
					<div class="form-group">
						<button type="button" class="btn btn-secondary import-close float-right">Close</button>
					</div>
				</div>

				<form>
					<div class="row">
						<label for="goods-primary-image">主图：</label>
						<input type="text" class="form-group form-control col" id="goods-primary-image">
					</div>

					<div class="row">
						<label for="goods-name">名称：</label>
						<input type="text" class="form-group form-control col" id="goods-name" >
					</div>

					<div class="row">
						<label>目录：</label>
						<div class="selectOne col">
							<select class="form-group custom-select" id="goods-level-one"></select>
						</div>
						<div class="selectTwo col"  style="padding-left: 5px">
							<select class="form-group custom-select" id="goods-level-two"></select>
						</div>
					</div>


					<div class="row">
						<label for="goods-number">数量：</label>
						<div class="form-group selectOne col">
							<input type="number" class="form-control col" id="goods-number" >
						</div>
						<label for="goods-price" style="padding-left: 5px">价格：</label>
						<div class="form-group selectTwo col">
							<input type="number" class="form-control col" id='goods-price'>
						</div>
					</div>

					<div class="row">
						<label for="goods-style">款式：</label>
						<select class="custom-select form-group col" id="goods-style"></select>
						<button type="button" class="btn goods-style-add btn-outline-dark">添加</button>
						<button type="button" class="btn goods-style-update btn-outline-dark">修改</button>
						<button type="button" class="btn goods-style-remove btn-outline-dark">删除</button>
					</div>

					<div class="row">
						<label for="goods-kind">种类：</label>
						<select class="custom-select form-group col" id="goods-kind"></select>
						<button type="button" class="btn goods-kind-add btn-outline-dark">添加</button>
						<button type="button" class="btn goods-kind-update btn-outline-dark">修改</button>
						<button type="button" class="btn goods-kind-remove btn-outline-dark">删除</button>
					</div>

					<div class="row">
						<label for="goods-detail-image">分图：</label>
						<select class="custom-select form-group col" id="goods-detail-image"></select>
						<button type="button" class="btn goods-detail-image-add btn-outline-dark">添加</button>
						<button type="button" class="btn goods-detail-image-update btn-outline-dark">修改</button>
						<button type="button" class="btn goods-detail-image-remove btn-outline-dark">删除</button>
					</div>

					<div class="form-group">
						<label>详细介绍(html源代码)：</label>
						<textarea class="form-control" rows="3" id="goods-detail-text"></textarea>
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

<%@include file="../../commonJSP/hint.jsp"%>
<%@include file="../../commonJSP/paging.jsp"%>

<script>
	let urlCatalogPath = "${pageContext.request.contextPath}/back/goods/catalog";
	let urlGoodsPath = "${pageContext.request.contextPath}/back/goods/goods";


	let goodsCatalogLevelMap = new Map();
	let goodsCatalogMap = new Map();

	$.ajax({
		url: urlCatalogPath,
		method: 'post',
		async: false,
		data: {
			'${ContentConstant.CONTENT_METHOD_NAME}' : "getCatalogAllByUpId",
			upId: 0
		},
		success: (data) => {
			data = JSON.parse(data);
			initGoodsCatalogMap(data, goodsCatalogLevelMap);
			$('.selectOneLevel').empty().append(`<option value='0'>全部</option>`);
			goodsCatalogLevelMap.forEach(function (value, key) {
				$('.selectOneLevel').append(`<option value="` + key + `">` + value.name + `</option>`);
				$('.selectOne select').append(`<option value="` + key + `">` + value.name + `</option>`);
			});
			loadSelectTwoOption();
		},
		error: () => myError()
	});

	function initGoodsCatalogMap(arr, gather) {
		$(arr).each(function () {
			goodsCatalogMap.set(this.id, this);
			let tempCatalog = this.catalogs;
			this.catalogs = new Map();
			gather.set(this.id, this);
			if (tempCatalog.length > 0) {
				initGoodsCatalogMap(tempCatalog, gather.get(this.id).catalogs);
			}
		});
	}

	$('.import-close').click(function () {
		$('.import-value').hide();
	});



	$('.add').click(function () {
		$('.import-value').hide();
		$('#goods-primary-image').val('');
		$('#goods-name').val('');
		$('#goods-number').val('');
		$('#goods-price').val('');
		$('#goods-detail-text').val('');
		$('#goods-style').empty();
		$('#goods-kind').empty();
		$('#goods-detail-image').empty();


	});


	$('.save').click(function () {
		if ($('#goods-detail-image').val() === '' || $('#goods-kind').val() === '' || $('#goods-style').val() === '' || $('#goods-detail-text').val() === ''
			|| $('#goods-number').val() === '' || $('#goods-name').val() === '' || $('#goods-price').val() === '' || $('#goods-primary-image').val() === '') {
			showHint("系统提示", "请输入完整的信息");
			return;
		}
		addGoods();
		$(this).prev().click();
	});

	function addGoods() {
		$.ajax({
			url: urlGoodsPath,
			method: 'post',
			data: {
				'${ContentConstant.CONTENT_METHOD_NAME}': 'addGoods',
				primaryImage: $('#goods-primary-image').val(),
				name: $('#goods-name').val(),
				catalogId: $('#goods-level-two').val(),
				number: parseInt($('#goods-number').val()),
				price: $('#goods-price').val(),
				style: getOptionTextBySelect($('#goods-style option')),
				kind: getOptionTextBySelect($('#goods-kind option')),
				detailImage: getOptionTextBySelect($('#goods-detail-image option')),
				detailText: $('#goods-detail-text').val()
			},
			success: (data) => {
				mySuccess(data);
				loadGoodsData();
			},
			error: () => myError()
		});
	}

	$('.goods-style-add').click(function () {
		showImportValue('goods-style-add');
	});
	$('.goods-kind-add').click(function () {
		showImportValue('goods-kind-add')
	});
	$('.goods-detail-image-add').click(function () {
		showImportValue('goods-detail-image-add')
	});

	$('.goods-style-update').click(function () {
		showImportValue('goods-style-update', $('#goods-style').val());
	});
	$('.goods-kind-update').click(function () {
		showImportValue('goods-kind-update', $('#goods-kind').val())
	});
	$('.goods-detail-image-update').click(function () {
		showImportValue('goods-detail-image-update', $("#goods-detail-image").val())
	});

	$('.goods-style-remove').click(function () {
		$('#goods-style option:selected').remove();
	});
	$('.goods-detail-image-remove').click(function () {
		$('#goods-detail-image option:selected').remove();
	});
	$('.goods-kind-remove').click(function () {
		$('#goods-kind option:selected').remove();
	});

	function getOptionTextBySelect(targer, separator) {
		let a = '';
		separator = separator || ',';
		targer.each(function (index) {
			a += $(this).text();
			if(targer.length - 1 !== index) {
				a += separator;
			}
		});
		return a;
	}
	
	function showImportValue(target, value){
		$('.import-value').attr('target', target).show();
		$('.import-value input').val(value || '');
	}
	$('.import-save').click(function () {
		let value = $('.import-value input').val();
		if (value === '') {
			showHint("系统提示", "信息不能为空");
			return;
		}
		let target = $('.import-value').attr('target');
		if (target === 'goods-style-add') $('#goods-style').prepend(`<option>` + value + `</option>`).val(value);
		else if (target === 'goods-kind-add') $('#goods-kind').prepend(`<option>` + value + `</option>`).val(value);
		else if (target === 'goods-detail-image-add') $('#goods-detail-image').prepend(`<option>` + value + `</option>`).val(value);
		else if (target === 'goods-style-update') $('#goods-style option:selected').text(value);
		else if (target === 'goods-style-update') $('#goods-kind option:selected').text(value);
		else if (target === 'goods-detail-image-update') $('#goods-detail-image option:selected').text(value);
		$('.import-value').hide();
	});

	function loadSelectTwoOption() {
		$('.selectTwo select').empty();
		goodsCatalogLevelMap.get(parseInt($('.selectOne select').val())).catalogs.forEach(function (value, key) {
			$('.selectTwo select').append(`<option value="` + key + `">` + value.name + `</option>`);
		})
	}
	$('.selectOne select')[0].onchange = loadSelectTwoOption;


	$('.selectOneLevel ').change(function () {
		let levelId = parseInt($('.selectOneLevel').val());
		$('.selectTwoLevel').empty().append(`<option value='-1'>全部</option>`);
		if (levelId === 0) return;
		goodsCatalogLevelMap.get(levelId).catalogs.forEach(function (value, key) {
			$('.selectTwoLevel').append(`<option value="` + key + `">` + value.name + `</option>`);
		})
	});


	function pagingAjax() {
		pageNo = 1;
		let upId = $('#catalogTwoLevel').val();
		if (upId === '-1') upId = $('#catalogOneLevel').val();
		$.ajax({
			url: urlGoodsPath,
			method: 'post',
			data: {
				'${ContentConstant.CONTENT_METHOD_NAME}' : "getGoodsAllByCatalogIdAndGoodsName",
				catalogUpId: upId,
				currPageNo: pageNo,
				goodsName: $('#catalogName').val()
			},
			success: (data) => {
				let pageData = loadPaging(JSON.parse(data));
				$('.table tbody').empty();
				loadGoodsDataValueOfPage(pageData);
			},
			error: () => myError()
		});
	}

	function loadGoodsDataValueOfPage(arr) {
		$(arr).each(function () {
			$('.table tbody').append(`<tr>
				<td scope="row">
					<img src="` + this.imagePrimary + `" alt="">
				</td>
				<td><div class="goods-name-exceed">` + this.name + `</div></td>
				<td>` + this.price + `</td>
				<td class="style">
					<select class="custom-select">
						` + getOptionText(this.style) + `
					</select>
				</td>
				<td class="kind">
					<select class="custom-select">
						` + getOptionText(this.kind) + `
					</select>
				</td>
				<td>` + this.number + `</td>
				<td>` + goodsCatalogMap.get(parseInt(this.catalogId)).name + `</td>
				<td class="oper">
					<button class="btn btn-outline-dark update">修改</button>
					<button class="btn btn-outline-dark remove">删除</button>
				</td>
			</tr>`);
		});
	}


	function getOptionText(str) {
		let resultText = "";
		$(str.split(',')).each(function () {
			resultText += "<option>" + this + "</option>";
		});
		return resultText;
	}

	pagingAjax();
	$('.search')[0].onclick = pagingAjax;
</script>


</body>
</html>
