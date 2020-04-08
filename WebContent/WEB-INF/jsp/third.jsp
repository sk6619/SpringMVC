<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- basepath=http://localhost:8080/上下文路径 -->
<% String basePath = request.getScheme()+"://"+request.getServerName()+":"
   +request.getServerPort()+request.getContextPath()+"/"; %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<base href="<%=basePath %>">
</head>
<body>
	<p>获取参数</p>
	paramId:${paramId }
	<br>
	<br>
	paramName:${paramName}
	<br>
	<br>     
		<form action="param/pathParam1.do" method="post">
			 paramId:<input type="text" name="paramId"/>
			 <br>
			 <br>
			 paramName:<input type="text" name="paramName"/>
			 <br>
			 <br>
			 <input type="submit" value="提交表单"/>
		</form>
</body>
</html>