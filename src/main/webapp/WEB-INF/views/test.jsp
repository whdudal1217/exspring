<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test JSP</title>
</head>
<body>
	<h1>테스트 화면</h1>
	
	<form action="/hit/test/b.do"> <!-- hit/test/b.do?x=10 -->
		<input type="text" name="x" /> 
		<input type="text" name="y" /> 
		<input type="submit" />
	</form>
</body>
</html>