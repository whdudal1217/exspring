<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>상품</title>
</head>
<body>
<%@ include file="/WEB-INF/views/comm/menu.jsp" %>
	<h1>상품 상세정보</h1>
	<form action="${pageContext.request.contextPath}/prod/edit.do" method="post">
	<input type="hidden" name="prodNo" value="${prodVo.prodNo}" />
		<table>
			<tbody>
				<tr>
					<td>상품번호</td>
					<td><input type="text" name="prodNo" value="${prodVo.prodNo}" /></td>
				</tr>
				<tr>
					<td>상품명</td>
					<td><input type="text" name="prodName" value="${prodVo.prodName}" /></td>
				</tr>
				<tr>
					<td>상품가격</td>
					<td><input type="text" name="prodPrice" value="${prodVo.prodPrice}" /></td>
				</tr>
			</tbody>
		</table>
		<input type="submit"  value="저장"/>
		<a href="${pageContext.request.contextPath}/prod/list.do"><input type="button"  value="취소"/></a>
		<a href="${pageContext.request.contextPath}/prod/del.do?prodNo=${prodVo.prodNo}"><input type="button"  value="삭제"/></a>
	</form>
</body>
</html>