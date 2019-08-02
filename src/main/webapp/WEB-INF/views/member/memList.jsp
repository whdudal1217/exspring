<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
</head>
<body>
<!-- 다른 JSP 파일의 내용 삽입 -->
<%-- <%@ include file="/WEB-INF/views/comm/menu.jsp" %> --%>
<!-- ﻿이 코드는 서블릿을 컴파일하기 전에 외부 jsp 파일의 내용을 현재 위치에 복사해서 넣은 후 현재 jsp 파일과 합쳐져서 하나의 jsp파일로 컴파일이 됩니다. -->
<%-- <jsp:include page="/WEB-INF/views/comm/menu.jsp"></jsp:include>
<c:import url="/WEB-INF/views/comm/menu.jsp"></c:import> --%>
	<h1>회원목록</h1>
	
	
	 <c:forEach items="${memberList}" var="vo">
	 
	 <!-- 로그인 성공 -->
	 <c:if test="${loginUser.memId ==vo.memId }">
	 <a href="${pageContext.request.contextPath }/member/edit.do?memId=${vo.memId}">${vo.memId}</a> 
	 </c:if>
	 
	 <!-- 로그인 실패 -->
	 <c:if test="${loginUser.memId !=vo.memId }">
	 : ${vo.memName} <br />
	 </c:if>
	 </c:forEach>
	 
	 
	 <hr />
	 <a href="${pageContext.request.contextPath }/member/add.do">회원가입</a>
</body>
</html>