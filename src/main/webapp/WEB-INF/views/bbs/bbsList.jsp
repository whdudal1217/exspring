<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 날짜나 숫자 데이터의 파싱/포맷팅을 담당하는 jstl 태그 라이브러리 사용 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- EL표현식 내에서 사용 가능한 함수들을 제공해주는 JSTL 태그 라이브러리 사용 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>                
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
<%@ include file="/WEB-INF/views/comm/menu.jsp" %>
	<h1>게시글 목록</h1>
	
		<table>
		<thead>
		<tr><th> 번호 </th><th> 제목 </th><th> 작성자 </th><th> 작성일 </th></tr>
		</thead>
			<tbody>
			<c:forEach items="${bbsList}" var="vo">
				<tr>
					<td>${vo.bbsNo}</td>
					<td>
						<%-- <a href="${pageContext.request.contextPath}/bbs/edit.do?bbsNo=${vo.bbsNo}">${vo.bbsTitle}</a> --%>
						<a
						href="${pageContext.request.contextPath}/bbs/edit.do?bbsNo=${vo.bbsNo}">
							<c:out value="${vo.bbsTitle}" escapeXml="true" /> <%-- ${fn:escapeXml(vo.bbsTitle)} --%>
					</a> <%-- <c:out value="${xx}">또는 ${fn:escapeXml(xx)} 를 이용해서 출력하면 기본적으로 태그를 특수문자로 변환하여 출력합니다  --%>
					</td>
					<td><c:out value="${vo.bbsWriter}" escapeXml="true" /></td>
					<td><fmt:formatDate value="${vo.bbsRegDate}"
							pattern="yyyy년 MM월 dd일  HH시 mm분 ss초" /></td>
				</tr>
			</c:forEach>
			
		</tbody>
		</table>
		
		${pageInfo.pageHtml}
		<script >
		/* 페이지 링크 클릭시 실행될 goPage() 함수를 재정의할 필요가 있을 경우, 다시 선언  */
	/* 	function goPage(no) {
			location.href = location.pathname + '?page=' + no;
		} */
		</script>
		<hr />
		<a href="${pageContext.request.contextPath}/bbs/add.do"> <button>글쓰기</button></a>
</body>
</html>