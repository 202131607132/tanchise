// 全局变量，总页数，当前页数
var countPages = 1;
var currentPage = 1;

// AJAX异步查询班级
$(function() {
	$("#score_tj").click(
			function() {
				
				window.location = "/Student/Score?action=tj&id="
				+ $("#search_type").val();
				
			});
});
