<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
</head>
<body>
<%@ include file="/WEB-INF/views/comm/menu.jsp" %>
	<h1>회원가입</h1>
	<form enctype="multipart/form-data"  action="${pageContext.request.contextPath}/member/add.do" method="post"> 
	ID : <input type="text" name="memId"/> <br /> <!-- 파라미터명과 변수명이 같으면 자동으로 스프링이 받음 고로 name은 memberVo의 변수명과 같아야함 -->
	PassWord : <input type="password" name="memPass"/> <br />
	Name : <input type="text" name="memName"/> <br /> 
	Point : <input type="text" name="memPoint"/> <br /> 
	Profile : <input type="file" name="memImgFile"/> <br />
	<input type="submit" />
	</form>
	 <hr />
	 <a href="${pageContext.request.contextPath }/member/list.do">회원목록</a>
</body>
</html>