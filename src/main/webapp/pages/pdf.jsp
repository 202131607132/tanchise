<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>search page</title>
<script src="../js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.0/jspdf.umd.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.0/html2canvas.min.js"></script>
<style>
table {
	border-collapse: collapse;
	width: 90%;
}

th, td {
	border: 1px solid black;
	padding: 8px;
	text-align: center;
	height: 30px;
}

th {
	background-color: #f2f2f2;
}
</style>
<script>
    // 获取当前日期
    var currentDate = new Date();

    // 获取年、月、日
    var year = currentDate.getFullYear();
    var month = currentDate.getMonth() + 1; // 月份从 0 开始，需要加 1
    var day = currentDate.getDate();

    // 格式化日期字符串
    var formattedDate = year + '-' + month + '-' + day;

    // 将日期显示在指定位置
    var dateElement = document.getElementById('date');
    dateElement.textContent = formattedDate;
  </script>
</head>
<body>
	
		
			<center><h4 style="color: red;">请按"转换为PDF并下载"按钮下载成绩单！</h4></center>
		<div style="border: 1px solid black; margin-left: 20%;margin-right: 20%;">
		<div id="content" class="searchbox tit" style="">
		<h6 style="color: blue;">&nbsp ${rol}端打印</h6>
<center>
			<h2>踏马逐尘成绩单</h2>
			<table>
				<thead>
					<tr>
						<th width="80">编号</th>
						<th width="80">学号</th>
						<th width="100">姓名</th>
						<th width="120">科目</th>
						<th width="100">平时分</th>
						<th width="100">考试分</th>
						<th width="100">总分</th>
					</tr>
				</thead>
				<tbody>
				<%int j=0; %>
				<c:forEach items="${list_score}" var="s" varStatus="i">
					<tr>
						<td>${i.count }</td>
						<td>${s.student.no}</td>
						<td>${s.student.name}</td>
						<td>${s.subject.name}</td>
						<td>${s.daily}</td>
						<td>${s.exam}</td>
						<td>${s.count}</td>
					</tr>
					<%j++; %>
				</c:forEach>
				<% for(int i =0;i<30-j;i++ ) { %>
				<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				<% } %>
				</tbody>
			</table>
			</center><h6 style="margin-left: 15%; ">主任签字：
			<% for(int i =0;i<50;i++ ) { %>
			&nbsp
			<% } %>
			踏马逐尘（盖章）</h6>
			<h6 id="date" style="margin-left: 72%;"></h6>
			<script>
    // 获取当前日期
    var currentDate = new Date();

    // 获取年、月、日
    var year = currentDate.getFullYear();
    var month = currentDate.getMonth() + 1; // 月份从 0 开始，需要加 1
    var day = currentDate.getDate();

    // 格式化日期字符串
    var formattedDate = year + '-' + month + '-' + day;

    // 将日期显示在指定位置
    var dateElement = document.getElementById('date');
    dateElement.textContent = formattedDate;
  </script>
		</div>
		</div>
		<center><button onclick="convertToPDF()">转换为PDF并下载</button></center>
		<script>
    function convertToPDF() {
      const content = document.getElementById('content');
    
      const pdf = new jspdf.jsPDF('p', 'mm', 'a4');

      html2canvas(content, { scale: 3 })
        .then(canvas => {
          const imgData = canvas.toDataURL('image/png');
          pdf.addImage(imgData, 'PNG', 10, 1, 190, 0);
          pdf.save('成绩单.pdf');
        });
    }
  </script>
		
	



</body>