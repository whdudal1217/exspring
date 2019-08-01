<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
<style type="text/css">
img.profile{
width:100px;
height:100px;
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/views/comm/menu.jsp" %>
	<h1>회원정보수정</h1>
	   <c:choose>
			<c:when test="${memberVo.memImg == null }">
				<img class="profile" alt="기본이미지" src="${pageContext.request.contextPath}/resources/img/user.png"><br />
			</c:when>
			<c:otherwise>
				<img class="profile" alt="${memVo.memId}의 사진" src="${pageContext.request.contextPath}/member/down.do?memId=${memberVo.memId}"> <br />
			</c:otherwise>
		</c:choose>
	<form action="${pageContext.request.contextPath}/member/edit.do" method="post"> 
	ID : <input type="text" name="memId" value="${memberVo.memId}" readonly="readonly"/> <br /> <!-- 파라미터명과 변수명이 같으면 자동으로 스프링이 받음 고로 name은 memberVo의 변수명과 같아야함 -->
	PassWord : <input type="text" name="memPass" value="${memberVo.memPass} "/> <br />
	Name : <input type="text" name="memName" value="${memberVo.memName}"/> <br /> 
	Point : <input type="text" name="memPoint" value="${memberVo.memPoint}"/> <br /> 
		<input type="submit" value="수정"/>
	<a href="${pageContext.request.contextPath}/member/list.do"><input type="button" value="취소" /></a><a href="${pageContext.request.contextPath}/member/del.do?memId=${memberVo.memId}"><input type="button" value="삭제" /></a>
	</form>
</body>
</html>