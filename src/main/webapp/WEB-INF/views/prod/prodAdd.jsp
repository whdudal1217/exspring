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
	<h1>상품추가</h1>
	<form action="${pageContext.request.contextPath}/prod/add.do" method="post">
		<table>
			<tbody>
				<tr>
					<td>상품명</td>
					<td><input type="text" name="prodName" /></td>
				</tr>
				<tr>
					<td>상품가격</td>
					<td><input type="text" name="prodPrice" /></td>
				</tr>
			</tbody>
		</table>
		<input type="submit"  value="상품추가"/>
		<a href="${pageContext.request.contextPath}/prod/list.do"><input type="button"  value="취소"/></a>
	</form>
</body>
</html>