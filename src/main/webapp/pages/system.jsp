<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>search page</title>
		<link href="../css/search.css" rel="stylesheet" type="text/css" />
		<script src="../js/jquery-1.8.3.min.js" type="text/javascript">
</script>
		

	</head>
	<body>
		<center>
			<div class="window">
				
					<h1>系统管理：</h1>
					<form action="/Student/SytemServlet" method="post">
					
					<table align="center">
						<tr><br></br></tr>
						
						<tr>
							<td >教师端：</td>
							<td>
									<select  name="forbitc" style="width: 90px;">
									<option value="0" >开放</option>
									<option value="1" ${sy.forbitc eq "1"?'selected':'' }>禁止</option>
								</select>
							</td>
						</tr>
						<tr>
							<td width="110px"> &nbsp;教师端通知:</td>
							<td><textarea  name="informtc" rows="15" cols="50">${sy.informtc }</textarea></td>
						</tr>
						
						<tr>
							<td>学生端：</td>
							<td>
									<select id="student" name="forbist" style="width: 90px;">
									<option value="0" >开放</option>
									<option value="1" ${sy.forbist eq "1"?'selected':'' }>禁止</option>
								</select>
							</td>
						</tr>
						
						
						
						<tr>
							<td>&nbsp;学生端通知:</td>
							<td><textarea  name="informst" rows="15" cols="50">${sy.informst }</textarea></td>
						</tr>
					</table>
					
					<div align="center">
						<input type="submit" value="保存"/ >&nbsp;
						<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
					</div>
					
					</form>
					<div align="center">
						<font id="error" color="red">${errorr }</font>
					</div>
					
				
				
			</div>
		</center>
	</body>
</html>