<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String bathPath = request.getScheme()+"://"+request.getServerName()
	+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html>
<html>
<head>
<base href=<%=bathPath %>>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   msg:${msg}
   <br>
   post请求：<form action="some.do" method="post">
    			<input type="text" name="name" />
    			<input type="text" name="age" />
    			<input type="submit" value="提交"/>
   			</form>
 =========================  			
   			${user.name}
   			${user.age}
   			
</body>
</html>