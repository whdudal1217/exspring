<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<%-- <%@ include file="/WEB-INF/views/comm/menu.jsp" %> --%>
	<h1>로그인</h1>
	<form action="${pageContext.request.contextPath}/member/login.do" method="post"> 
	ID : <input type="text" name="memId"/> <br /> <!-- 파라미터명과 변수명이 같으면 자동으로 스프링이 받음 고로 name은 memberVo의 변수명과 같아야함 -->
	PassWord : <input type="password" name="memPass"/> <br />
	<input type="submit" value="로그인" />
	</form>
	 <hr />
	 <a href="${pageContext.request.contextPath}/member/add.do">회원가입</a>
</body>
</html>