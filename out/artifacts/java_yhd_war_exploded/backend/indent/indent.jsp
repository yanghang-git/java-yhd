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
					<th scope="col" style="width: 20%">编号</th>
					<th scope="col" style="width: 10%">用户</th>
					<th scope="col" style="width: 20%">地址</th>
					<th scope="col" style="width: 20%">时间</th>
					<th scope="col">操作</th>
				</tr>
				</thead><tbody>
				</tbody>
			</table>
			<%@include file="../../commonJSP/paging.jsp"%>

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
					<div class="show id">` + this.id + `</div>
				</td>
				<td>` + this.userId + `</td>
				<td>
					<div">` + getAddressDetailByAddressId(this.addressId) + `</div>
				</td>
				<td>
						<div class="show">` + this.orderTime + `</div>
				</td>
				<td>
					<form action="` + urlPath + `" method="post" style="display: inline-block">
						<input type="hidden" name="` + '${ContentConstant.CONTENT_METHOD_NAME}' + `" value="showIndentInfo"/>
						<input type="hidden" value="`+ this.id + `" name="id"/>
						<button type="submit" class="btn btn-outline-dark show-info">详情</button>
					</form>
					<button class="btn btn-outline-dark ` + (isShipment(this.statusId) ? "" : "disabled") + ` shipments">发货</button>

				</td>
			</tr>`);
		});


	}


	$('body').on("click", ".table tbody .shipments", function () {
		if($(this).hasClass('disabled')) {
			return;
		}
		$.ajax({
			url: urlPath,
			method: 'post',
			data: {
				'${ContentConstant.CONTENT_METHOD_NAME}' : "shipments",
				id: $(this).parent().parent().children().eq(0).children('div.id').text()
			},
			success: (data) => {
				mySuccess(data);
				pagingAjax();
			},
			error: () => myError()
		});
	});

	function isShipment (status) {
		let contains = indentStatusCache.get(parseInt(status));
		return contains === "已付款";
	}

	let addressCache = new Map();
	function getAddressDetailByAddressId(addressId) { // 1
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

</script>
</body>
</html>
