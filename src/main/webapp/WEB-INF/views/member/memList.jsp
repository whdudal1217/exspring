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
	<form id="sform" action="${pageContext.request.contextPath}/member/list.do">
		<select name="searchType">
			<option value="title">번호</option>
			<option value="content">이름</option>
			<option value="total">번호+이름</option>
		</select> 
		<input type="text" name="searchWord" value="${memSearchInfo.searchWord}" />
		<input type="hidden" name="page" value="1" />
		<input type="submit" value="검색" />
	</form>
	<script>
		//searchType select 의 기본값을 설정하기 위해서
		if('${searchInfo.searchType}' ) //searchType 값이 존재하는 경우에만 (자바스크립트에서 빈문자열은 false취급) 
		document.querySelector('[name="searchType"]').value = '${searchInfo.searchType}'
	</script>
	 <c:forEach items="${memberList}" var="vo">
	 
	 <!-- 로그인 성공 -->
	 <c:if test="${loginUser.memId ==vo.memId }">
	 <a href="${pageContext.request.contextPath }/member/edit.do?memId=${vo.memId}">${vo.memId}:${vo.memName }</a> 
	 </c:if>
	 
	 <!-- 로그인 실패 -->
	 <c:if test="${loginUser.memId !=vo.memId }">
	 ${vo.memId}:${vo.memName } <br />
	 </c:if>
	 </c:forEach>
	 <br />
	${memSearchInfo.pageHtml}
	
	
		<script>
	 	function goPage(no) { 
			document.querySelector('[name="page"]').value =no; 
			document.querySelector('#sform').submit();
		} 
		</script>
	 <hr />
	 <a href="${pageContext.request.contextPath }/member/add.do">회원가입</a>
</body>
</html>