<%--
  Created by IntelliJ IDEA.
  User: 北方
  Date: 2020/7/28
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.yhd.util.ContentConstant" %>
<html>
<style>
	.address,
	.date,
	.status {
		padding-left: 50px;
	}

	.msg-top {
		text-align: right;
	}
	.msg-top .id {
		padding-right: 50px;
	}

	.goods-msg-top,
	.goods-msg-main {
		border: 1px solid rgba(0, 0, 0, 0.1); border-radius: 4px; padding:5px;
		text-align: center;
		font-size: 13px;
	}
	.goods-msg-main {
		margin-bottom: 15px;
	}

	.goods-msg-top {
		margin: 15px 0;
		color: #999;
	}

	.row {
		width: 100%;
	}
	.goods-msg-main .image {
		max-width: 73px;
	}
	.goods-msg-main .goods-item {
		height: 100px;
		padding-top: 20px;
		margin-top: 10px;
		margin-left: 0;
	}

	.goods-msg-main .border-bottom {
		border-bottom: rgba(0, 0, 0, 0.1) 1px solid;
	}

	.goods-msg-main .col-5 p{
		max-width: 100%;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
	}

	.goback {
		padding: 3px 7px !important;
		font-size: 13px;
		float: right;
		margin-right: 14px;
	}
</style>
<head>
	<title>Title</title>
	<base href="<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"%>">
	<script src="js/jquery.min.js"></script>
	<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h1 class="mt-5">订单详情</h1>
		<div class="msg-top">
			<span class="id">编号：${requestScope.indent.id}</span>
			<span>用户：${requestScope.indent.userId}</span>
		</div>
		<div class="goods-msg-top row">
			<span class="col">主图</span>
			<span class="col-5">商品信息</span>
			<span class="col">单价(元)</span>
			<span class="col">数量</span>
			<span class="col">小计</span>
		</div>
		<div class="goods-msg-main row"></div>
		<div>
			<span>总价：${requestScope.indent.totalPrices}元</span>
			<span class="address"></span>
			<span class="date">时间：${requestScope.indent.orderTime}</span>
			<span class="status">状态：${requestScope.indent.statusId}</span>
			<button class="btn btn-outline-dark goback">返回</button>
		</div>
	</div>
</body>
<script>
	$.ajax({
		url: '${pageContext.request.contextPath}/back/indent/indent',
		method: 'post',
		async: false,
		data: {
			'${ContentConstant.CONTENT_METHOD_NAME}' : "getAddressDetailByAddressId",
			addressId: ${requestScope.indent.addressId}
		},
		success: (data) => {
			$('.address').text("地址:    " + data);
		},
		error: () => myError()
	});
	console.log('${requestScope.indent.statusId}');
	$.ajax({
		url: '${pageContext.request.contextPath}/back/indent/status',
		method: 'post',
		data: {
			'${ContentConstant.CONTENT_METHOD_NAME}' : "getIndentStatusById",
			statusId: ${requestScope.indent.statusId}
		},
		success: (data) => {
			$('.status').text("状态:    " + JSON.parse(data).statusName);
		},
		error: () => myError()
	});
	let idList = '${requestScope.indent.goodsId}'.split("&");
	let typeList = '${requestScope.indent.type}'.split("&");
	let numberList = '${requestScope.indent.buyNumber}'.split("&");
	for (let i in idList) {
		$.ajax({
			url: '${pageContext.request.contextPath}/back/goods/goods',
			method: 'post',
			data: {
				'${ContentConstant.CONTENT_METHOD_NAME}': 'getGoodsById',
				goodsId: idList[i]
			},
			success: (data) => {
				let goods = JSON.parse(data);
				$(".goods-msg-main").append(`
				<div class="row goods-item ` + (idList.length -1 == i ? "" : "border-bottom") + `">
					<span class="col"><img class="image" src="` + goods.imagePrimary + `"></span>
					<span class="col-5">
						<p>` + goods.name + `</p>
						<p>` + typeList[i] + `</p>
					</span>
					<span class="col">` + goods.price + `</span>
					<span class="col">` + numberList[i] +  `</span>
					<b class="col">` + (parseInt(numberList[i]) * goods.price) + `</span>
				</div>`);
			},
			error: () => myError()
		});
	}

	$(".goback").click(function () {
		history.back();
	});

</script>
</html>
