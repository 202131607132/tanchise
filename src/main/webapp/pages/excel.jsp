<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>search page</title>
		<link href="../css/search.css" rel="stylesheet" type="text/css" />
		<script src="../js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="../js/search_student.js" type="text/javascript"></script>
		<script src="../js/search.js" type="text/javascript"></script>
    <meta charset="utf-8" />
    <title>excel导入</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    
    <script type="text/javascript" src="https://unpkg.com/xlsx@0.15.1/dist/xlsx.full.min.js"></script> 
</head>
<body>
<center>
    <div class="container">
        <!-- <h2 class="text-center mt-4 mb-4">Convert Excel to HTML Table using JavaScript</h2> -->
        <div>
            <div class="card-header"><br /><b>请选择Excel文件</b><a href="/Student/upload/Student.xlsx">  点击下载模板</a><br></br></div>
            <div class="card-body">
                
                
        <form action="/Student/XLSFile?ac=a" enctype="multipart/form-data" method="post">
            <input type="file" name="file"  accept=".xlsx,.xls;" />
            <input type="submit" value="上传Excel">
        </form>
        
            </div>
        </div>
    </div>
    <div>
    <c:if test="${not empty stuList}">
     成功导入${num}条，失败${stuList.size()-num }条，请<a href = "/Student/XLSFile?ac=aaaa">点击下载</a>返回结果后核对后重新上传！
    <table width="600"  border="1" style="border-collapse: collapse;">
				<thead>
					<tr>
					<th>编号</th>
					<th>学号</th>
					<th>姓名</th>
					<th>用户名</th>
					<th>密码</th>
					<th>性别</th>
					<th>生日</th>
					<th>班级</th>
					<th>照片名</th>
					
					</tr>
				</thead>
				<tbody align="center" >
				<c:forEach  varStatus="i" var="u" items="${stuList }">
					<tr>
						<td>${i.index+1}</td>
						<td>${u.getNo()}</td>
						<td>${u.getName()}</td>
						<td>${u.getOperator().getName()}</td>
						<td>${u.getOperator().getPwd()}</td>
						<td>${u.getSex()}</td>
						<td>${u.getBirth()}</td>
						<td>${u.getClasses().getName()}</td>
						<td>${u.getPic()}</td>
						<td>${u.getZt()}</td>
						
						
					</tr>
				</c:forEach>
				
				</tbody>
			</table>
			</c:if>
			</div>
			</center>
</body>
</html>

