<%@page import="org.springframework.web.context.request.SessionScope"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>메뉴</title>
</head>
<body>
	<div>
	<!-- 로그인하지 않은 경우 -->
	<c:if test="${loginUser == null}">
	<a href='${pageContext.request.contextPath}/member/login.do'>로그인</a>
	</c:if>
	<!-- 로그인한 경우 -->
	<c:if test="${loginUser != null}">
	    <a href='${pageContext.request.contextPath}/member/list.do'>회원관리</a> 
		| <a href='${pageContext.request.contextPath}/bbs/list.do'>게시판</a> 
		| <a href='${pageContext.request.contextPath}/prod/list.do'>상품관리</a>
		[ ${sessionScope.loginUser.memName }님  | <a href='${pageContext.request.contextPath}/member/logout.do'>로그아웃</a> ]
	</c:if>
		<hr />
	</div>
</body>
</html>