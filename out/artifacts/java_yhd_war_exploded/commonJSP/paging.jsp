<%--
  Created by IntelliJ IDEA.
  User: 北方
  Date: 2020/7/1
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
	<div class="container paging">
		<nav aria-label="Page navigation example">
			<ul class="pagination  justify-content-center"></ul>
		</nav>
	</div>
	<script>

		let pageNo = 1;

		function initPageNo() {
			pageNo = 1;
			pagingAjax();
		}

		function loadPaging(page) {
			if (page.newsList === null) {
				initPageNo();
			}
			$('.paging ul').empty();
			$('.paging ul').append($(`<li class="page-item"><a class="page-link" href="#" style="font-size: 11px;">Previous</a></li>
                <li class="page-item"><a class="page-link" href="#" style="font-size: 11px;">Next</a></li>`));

			for (let i = 0, j = page.pageStart; i < page.maxPagination; i++,j++) {
				let dom = $(`<li class="page-item page-number"><a class="page-link" href="#" style="font-size: 11px;">` + j + `</a></li>`);
				if(j === page.currPageNo){
					dom.addClass('active');
				}
				$('.paging .page-item :contains("Next")').parent().before(dom);
			}
			if(page.currPageNo === 1) {
				$('.paging .page-item :contains("Previous")').parent().addClass('disabled');
			}
			if(page.currPageNo === page.totalPageCount) {
				$('.paging .page-item :contains("Next")').parent().addClass('disabled');
			}
			if (page.newsList != null && page.newsList.length === 0) {
				$('.paging .page-item :contains("Previous")').parent().addClass('disabled');
				$('.paging .page-item :contains("Next")').parent().addClass('disabled');
			}
			return page.newsList;
		}


		// 上一页
		$('.paging').on('click', '.page-item:contains("Next")', function () {
			if(!$(this).prev().hasClass('active')) {
				++pageNo;
				pagingAjax();
			}
			return false;
		});

		// 下一页
		$('.paging').on('click', '.page-item:contains("Previous")', function () {
			if(!$(this).next().hasClass('active')){
				--pageNo;
				pagingAjax();
			}
			return false;
		});

		// 明确指定分页
		$('.paging').on('click', '.page-number', function () {
			pageNo = $(this).text();
			pagingAjax();
			return false;
		});


	</script>
</body>
</html>
