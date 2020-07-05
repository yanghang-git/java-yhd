<%--
  Created by IntelliJ IDEA.
  User: 北方
  Date: 2020/7/4
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>YHD BackEnd</title>
</head>
<style>
	.table tbody tr td button {
		font-size: 8px;
		padding: 6px;
	}
	.font-exceed {
		position: relative;
		cursor: pointer;
	}
	.font-exceed>.font {
		width: 112px;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
	}
	.font-exceed>.show {
		background: #ffffff;
		position: absolute;
		display: none;
		white-space: nowrap;
	}
	.font-exceed:hover .show {
		display: block;
	}
	.table tbody tr>td {
		width: 11%;
	}
	.form-inline input[type='text'] {
		width: 160px;
	}
	.form-group {
		padding: 0 !important;
	}


	.import-value {
		position: absolute;
		z-index: 1050;
		top: 15%;
		background: #ffffff;
		border-radius: 5px;
		border: 1px solid #0b2e13;
		width: 400px;
		padding: 20px;
		left: 40%;
		display: none;
	}
	.import-value button {
		margin: 0 9px;
	}
</style>
<body>
<div class="row">
	<!-- navigation bar -->
	<div class="col-2">
		<%@ include file="../commonJSP/navigationbar.jsp"%>
	</div>

	<!-- page value -->
	<div class="col">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="backend/index.jsp">Home</a></li>
				<li class="breadcrumb-item active">indent</li>
			</ol>
		</nav>


		<div class="container">
			<blockquote class="blockquote">
				<p class="mb-0">对订单进行管理。</p>
				<footer class="blockquote-footer">manage orders</footer>
			</blockquote>

			<div class="row  justify-content-end">
				<form class="form-inline">
					<div class="form-group mb-2">
						<label for="statusName">订单状态：</label>
					</div>

					<div class="form-group mx-sm-3 mb-2">
						<select id="statusName" class="custom-select">
							<option value='0'>全部</option>
						</select>
					</div>

					<div class="form-group mb-2">
						<label for="userId">用户名称：</label>
					</div>
					<div class="form-group mx-sm-3 mb-2">
						<input type="text" class="form-control" placeholder="用户名称"  id="userId">
					</div>
					<div class="form-group mb-2">
						<label for="goodsName">商品名称：</label>
					</div>
					<div class="form-group mx-sm-3 mb-2">
						<input type="text" class="form-control" placeholder="商品名称"  id="goodsName">
					</div>
					<div class="form-group mb-2">
						<label for="statusId">订单编号：</label>
					</div>
					<div class="form-group mx-sm-3 mb-2">
						<input type="text" class="form-control" placeholder="订单编号"  id="statusId">
					</div>
					<div class="form-group mb-2">
						<button type="button" class="btn search btn-primary">Search</button>
					</div>
				</form>
			</div>
			<table class="table">
				<thead class="thead-light">
				<tr>
					<th scope="col">编号</th>
					<th scope="col">用户</th>
					<th scope="col">商品</th>
					<th scope="col">款式</th>
					<th scope="col">数量</th>
					<th scope="col">总价</th>
					<th scope="col">地址</th>
					<th scope="col">时间</th>
					<th scope="col">操作</th>
				</tr>
				</thead><tbody>
				</tbody>
			</table>
			<%@include file="../../commonJSP/paging.jsp"%>

		</div>
	</div>
</div>
<div class="import-value">
	<div class="form-group">
		<label>请输入密令：</label>
		<input type="password" class=" pwd form-group form-control col" >
	</div>
	<div class="form-group">
		<button type="button" class="btn btn-primary import-save float-right">Save</button>
	</div>
	<div class="form-group">
		<button type="button" class="btn btn-secondary import-close float-right">Close</button>
	</div>
</div>

<!-- hidden of box -->
<div class="modal" id="updateIndent">
	<button class="btn btn-outline-dark updateIndent"  data-toggle="modal" style="display: none" data-target="#updateIndent"></button>
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Indent</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form>
					<input type="hidden" id="indent-id">
					<div class="row">
						<div class="col form-group" style="margin-right: 5px">
							<label for="indent-style" class="col-form-label">款式：</label>
							<select class="custom-select" id="indent-style"></select>
						</div>
						<div class="col form-group" style="margin-left: 5px">
							<label for="indent-kind" class="col-form-label">种类：</label>
							<select class="custom-select" id="indent-kind"></select>
						</div>
					</div>
					<div class="row">
						<div class="col form-group" style="margin-right: 5px">
							<label for="indent-number" class="col-form-label">数量：</label>
							<input type="number" class="form-control" id="indent-number">
						</div>
						<div class="col form-group" style="margin-left: 5px">
							<label for="indent-price" class="col-form-label">价格：</label>
							<input type="number" class="form-control" id="indent-price">
						</div>
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
	const urlPath = '${pageContext.request.contextPath}/back/indent/indent';
	let indentStatusCache = new Map();
	$.ajax({
		url: '${pageContext.request.contextPath}/back/indent/status',
		method: 'post',
		data: {
			'${ContentConstant.CONTENT_METHOD_NAME}': 'getAll'
		},
		success: (data) => {
			$(JSON.parse(data)).each(function () {
				indentStatusCache.set(this.id, this.statusName);
				$('#statusName').append($(`<option value="` + this.id + `">` + this.statusName + `</option>`));
			});
		}
	});
	$('body').on('click', '.update', function () {
		let statusName = indentStatusCache.get(parseInt($(this).parent().parent().attr('statusId')));
		if (statusName !== '确认订单' && statusName !== '未发货') {
			showHint("系统提示",statusName === undefined ? "订单状态异常" :  "当前订单状态为：" + statusName + "，不可进行更改");
			return;
		}
		indentStatusCache.get();
		let goods = goodsCache.get(parseInt($(this).parent().parent().attr('goodsId')));
		$('#indent-style').append($(getOptionText(goods.style)));
		$('#indent-kind').append($(getOptionText(goods.kind)));
		$('#indent-number').val($(this).parent().parent().children('.number').text());
		$('#indent-price').val($(this).parent().parent().children('.price').text());
		$('#indent-id').val($(this).parent().parent().children('td').children('.font-exceed').children('.id').text());
		$('.updateIndent').click();
	});

	$('body').on('click', '.remove', function () {
		$('.import-value .pwd').val('');
		$('.import-value').attr('indentId', $(this).parent().parent().children('td').children('.font-exceed').children('.id').text()).show();
	});
	
	$('.import-save').click(function () {
		if($('.import-value .pwd').val() === '') {
			showHint('系统提示', "密令错误");
			$('.import-value').hide();
			return;
		}
		$.ajax({
			url: urlPath,
			method: 'post',
			data: {
				'${ContentConstant.CONTENT_METHOD_NAME}': 'removeIndentById',
				indentId: $('.import-value').attr('indentId')
			},
			success: (data) => {
				mySuccess(data);
				pagingAjax();
			},
			error:() => myError()
		});
		$('.import-value').hide();
	});

	$('.import-close').click(function () {
		$('.import-value').hide();
	});

	$('.save').click(function () {
		let indentNumber = $('#indent-number').val();
		console.log(/^\d+$/.test(indentNumber))
		let indentPrice = $('#indent-price').val();
		if (indentNumber === '' || indentPrice === '') {
			showHint('系统提示', '请输入完整的信息');
			return;
		}
		if (!/^\d+$/.test(indentNumber)) {
			showHint('系统提示', '购买数量必须为整数');
			return;
		}
		$.ajax({
			url: urlPath,
			method: 'post',
			data: {
				'${ContentConstant.CONTENT_METHOD_NAME}': 'updateIndentGoodsTypeAndBuyNumberAndTotalPriceById',
				goodsType: $('#indent-style').val() + "/" + $('#indent-kind').val(),
				buyNumber: indentNumber,
				totalPrice: indentPrice,
				indentId: $('#indent-id').val()
			},
			success: (data) => {
				mySuccess(data);
				pagingAjax();
			},
			error: myError()
		});
		$('.closeBox').click();
	});

	function getOptionText(str) {
		let resultText = "";
		$(str.split(',')).each(function () {
			resultText += "<option>" + this + "</option>";
		});
		return resultText;
	}

	$('.search')[0].onclick = pagingAjax;

	pagingAjax();
	function pagingAjax() {
		$.ajax({
			url: urlPath,
			method: 'post',
			data: {
				'${ContentConstant.CONTENT_METHOD_NAME}' : "getListByStatusAndGoodsIdAndUserIdAndId",
				currPageNo: pageNo,
				statusId: $('#statusName').val(),
				goodsName: $('#goodsName').val(),
				userId: $('#userId').val(),
				indentId: $('#statusId').val()
			},
			success: (data) => {
				let pageData = loadPaging(JSON.parse(data));
				loadIndent(pageData);
			},
			error: () => myError()
		});
	}
	function loadIndent(data) {
		$(".table tbody").empty();
		$(data).each(function(){
			$(".table tbody").append(`<tr statusId="` + this.statusId + `" goodsId="` + this.goodsId + `">
				<td>
					<div class="font-exceed">
						<div class="font">` + this.id + `</div>
						<div class="show id">` + this.id + `</div>
					</div>
				</td>
				<td>` + this.userId + `</td>
				<td>
					<div class="font-exceed">
						<div class="font">` + getGoodsNameByGoodsId(this.goodsId) + `</div>
						<div class="show">` + getGoodsNameByGoodsId(this.goodsId) + `</div>
					</div>
				</td>
				<td>
					<div class="font-exceed">
						<div class="font">` + this.type + `</div>
						<div class="show">` + this.type + `</div>
					</div>
				  </td>
				<td class="number">` + this.buyNumber + `</td>
				<td class="price">` + this.totalPrices + `</td>
				<td>
					<div class="font-exceed">
						<div class="font">` + getAddressDetailByAddressId(this.addressId) + `</div>
						<div class="show address">` + getAddressDetailByAddressId(this.addressId) + `</div>
					</div>
				</td>
				<td>
					<div class="font-exceed">
						<div class="font">` + strTransformMM_dd(this.orderTime)  + `</div>
						<div class="show">` + this.orderTime + `</div>
					</div>
				</td>
				<td>
					<button class="btn btn-outline-dark update">修改</button>
					<button class="btn btn-outline-dark remove">删除</button>
				</td>
			</tr>`);
		});
	}


	let goodsCache = new Map();
	function getGoodsNameByGoodsId(goodsId) {
		goodsId = parseInt(goodsId);
		if (goodsCache.has(goodsId)) return goodsCache.get(goodsId).name;
		let parse;
		$.ajax({
			url: '${pageContext.request.contextPath}/back/goods/goods',
			method: 'post',
			async: false,
			data: {
				'${ContentConstant.CONTENT_METHOD_NAME}' : "getGoodsById",
				goodsId: goodsId
			},
			success: (data) => {
				parse = JSON.parse(data);
			},
			error: () => myError()
		});
		goodsCache.set(goodsId, parse);
		return parse.name;
	}
	let addressCache = new Map();
	function getAddressDetailByAddressId(addressId) {
		addressId = parseInt(addressId);
		if (addressCache.has(addressId)) return addressCache.get(addressId);
		let address;
		$.ajax({
			url: urlPath,
			method: 'post',
			async: false,
			data: {
				'${ContentConstant.CONTENT_METHOD_NAME}' : "getAddressDetailByAddressId",
				addressId: addressId
			},
			success: (data) => {
				address = data;
			},
			error: () => myError()
		});
		addressCache.set(addressId, address);
		return address;
	}

	function strTransformMM_dd(str) {
		str = str.split(' ')[0];
		return  str.substring(str.indexOf('-') + 1);
	}

</script>
</body>
</html>
