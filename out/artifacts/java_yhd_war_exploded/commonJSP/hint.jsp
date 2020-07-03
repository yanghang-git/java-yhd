<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
</head>
<body>

<div class="hint-box" style="background: #fff;width: 300px;position: fixed;right: 0;bottom: 0;border-radius: 6px;box-shadow: 0 0 8px rgba(0, 0, 0, 0.4);display: none">
	<div class="hint-title row" style="height: 30px; padding: 5px;">
		<div class="title-name"  style="position: absolute;left: 10px;font-size: 15px;"></div>
		<div style="position: absolute;right: 10px;font-size: 12px;">WebHint</div>
	</div>
	<div class="hint-value" style="text-indent: 2em;border-top: 1px solid #999;font-size: 14px; padding: 9px 20px;"></div>
	<button class="btn btn-primary" style="float: left;margin:7px;padding:2px 10px;margin-top: 0;">OK</button>
</div>
<script>
	let hint = '${requestScope[ContentConstant.PAGE_HINT]}';
	if(hint.length !== 0) {
		hint = JSON.parse(hint);
		$('.hint-box .hint-value').text(hint.value);
		$('.hint-title .title-name').text(hint.title);
		$('.hint-box').slideDown(500);
	}

	function mySuccess(data) {
		data = JSON.parse(data);
		showHint(data.title, data.value);
	}

	function myError() {
		showHint("系统提示", "网络异常");
	}

	function showHint(title, value, time) {
		$('.hint-title .title-name').text(title);
		$('.hint-box .hint-value').text(value);
		$('.hint-box').slideDown(time);
	}

	$(".hint-box button").click(function () {
		$('.hint-box').slideUp(500);
	});
</script>
</body>
</html>
