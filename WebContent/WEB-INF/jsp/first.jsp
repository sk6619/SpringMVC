<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
  msg:${msg}
  <br>
  <c:forEach items="${list}" var="string">
  ${string}
  
  <br>
  </c:forEach>
</body>
</html>