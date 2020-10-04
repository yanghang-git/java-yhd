$(function () {
    let nowDate = new Date();
    $('.main .panel-top div.col-3 .date .time').text(nowDate.getFullYear() + '-' + (nowDate.getMonth() + 1) + '-' + nowDate.getDate());
    $('.main .panel-top div.col-3 .date .week').text('星期' + (nowDate.getDay() + 1));

	let param = getHrefParam(window.location.href);


	$('.header-top .nav-masthead .enter>a').text(param.get('username')[0]).attr('href','#');
	$('.header-top .nav-masthead .enter .data .data-top .name').text(param.get('username')[0]);
	$('.main .panel-top .name').text('Hi！ ' + param.get('username')[0]);

	let href = $('.panel-value .value-top .right-top a').attr('href');
	$('.panel-value .value-top .right-top a').attr('href', href+"?username=" + param.get('username')[0]);

	$.ajax({
		url: "/personalCenter/indent",
		method: "post",
		data: {
			'CONTENT_METHOD_NAME': "getRecentlyIndentByUserName",
			'id': param.get('username')[0]
		},
		success: function(data) {
			let parse = JSON.parse(data);

			if (data === 'null') {
				$('.main .panel-value .value-main .value').css('display', 'none');
				$('.main .panel-value .value-main .empty').css('display', 'block');
			} else {
				$('.main .panel-value .value-main .value').css('display', 'block');
				$('.main .panel-value .value-main .empty').css('display', 'none');
				$('.main .panel-value .value-main .value').append(orderFromDom(JSON.parse(data)));
			}
		}
	});



    // 退出登录
    $('.header-top .nav-masthead .enter .data .data-top .exit').click(function(){
        sessionStorage.removeItem('user');
    });





    function orderFromDom(orderFrom){
        return $(`<div class="item row">
                        <div class="col-4">
                            <p>配送方式</p>
                            <p class="box">第三方配送</p>
                            <p class="font-color">时间：${orderFrom.orderTime}</p>
                            <p>工作日、双休日与节假日均可发货</p>
                        </div>
                        <div class="col">
                            <p class="title" style="padding-top: 13px;">商家：yhd</p>
                            <div class="row">
                                <div class="col-2 img"><img src="#" alt=""></div>
                                <div class="col main">
                                    <div class="main-value">订单: ${orderFrom.id}</div>
                                    <p>支持7天无理由退货</p>
                                </div>
                                <div class="col-2 money">总价:${orderFrom.totalPrices}</div>
                                <div class="col-2 state">${getStatusNameById(orderFrom.statusId)}</div>
                                <div class="show-indent-info" style="padding-left: 471px;"><a href="indentinfo.html?indentid=${orderFrom.id}">查看详情</a></div>
                            </div>
                        </div>
                    </div>`);
    }

    function getStatusNameById(id) {
    	let temp;
		$.ajax({
			url: '/personalCenter/indentstatus',
			method: 'post',
			async: false,
			data: {
				"CONTENT_METHOD_NAME" : "getIndentStatusById",
				statusId: id
			},
			success: (data) => {
				temp = data;
			},
			error: () => myError()
		});
		return temp;
	}


});