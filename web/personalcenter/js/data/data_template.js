
$(function () {
	let param = getHrefParam(window.location.href);
	$('iframe').attr('src', ('orderForm.html?username=' + param.get('username')[0]));

	$('.header-top .nav-masthead .enter>a').text(param.get("username")[0]).attr('href','#');
	$('.header-top .nav-masthead .enter .data .data-top .name').text(param.get("username")[0]);
	let $href = $('.catalog ul li a');
	$.each($href, function (i) {
		$($href[i]).attr('href', $($href[i]).attr('href') + "?username=" + param.get('username')[0]);
	});

    // 退出登录
    // $('.header-top .nav-masthead .enter .data .data-top .exit').click(function(){
    //     sessionStorage.removeItem('user');
    // });


});