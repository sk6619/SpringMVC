<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   msg:${msg}
   <br>
   post请求：<form action="second41.do" method="post">
    			<input type="text" name="点我" />
    			<input type="submit" value="提交"/>
   			</form>
</body>
</html>