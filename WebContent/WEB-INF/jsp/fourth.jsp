<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- basepath=http://localhost:8080/上下文路径 -->
<% String BathPath=request.getScheme()+"://"+request.getServerName()+":"
	+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html>
<html>
<script type="text/javascript" src="js/jquery.min.js"></script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 项目跟路径 -->
<base href="<%=BathPath %>" />
</head>
<body>
	姓名：
	<input type="text" value="${xString}"> ${xString}
	<br> =================================
	<br>
	<br>
	<form action="" method="post">
		姓名：<input id="input1" type="text" name="name" /><br> 
		年龄：<input id="input2" type="text" name="age" /><br> 
		<input type="button" value="提交表单" />
	</form>
	<br>
	<br> 发送ajax请求获取user：
	<input type="button" id="input" value="点我获取user填到表单里" />
</body>
<script type="text/javascript">
	$("#input").click(function () {
		$.ajax({
			url:"getuser.do",
			data:{
				"name":"邵洁",
				"age":25
			},
			type:"post",
			dataType:"json",
			success:function(user){
				//将获取的值填到表单里面
				$("#input1").val(user.name),
				$("#input2").val(user.age)
			},
			error:function(){
				alert("失败")
			}
		})
	})
</script>
</html>