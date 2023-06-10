<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<style>
	.person {
		float: left;
		margin: 10px;
		text-align: center;
	}

	.clear {
		clear: both;
	}
</style>

</head>
<body>

		

			<div>
				<b >请选择照片，可以选择多张照片</b>
			</div>

			<form action="/Student/IMGFile" enctype="multipart/form-data"
				method="post">
				<input type="file" name="file" accept="image/*" multiple /> <input
					type="submit" value="上传">
			</form>



			<c:if test="${not empty imgMap}">
				<div><b style="color: red;">只有匹配成功的才上传到服务器</b></div>
					<c:forEach var="u" items="${imgMap }">
						<div class="person">
							<img src="./images/${u.getKey()}" border="0" width="100px"
								height="100px" />
							<p>${u.getKey()}<br>${u.getValue()}</p></p>
						</div>
						<c:if test="${(imgMap_index + 1) % 4 == 0}">
							<div class="clear"></div>
						</c:if>
					</c:forEach>
					<div class="clear"></div>
			
			</c:if>

</body>
</html>
