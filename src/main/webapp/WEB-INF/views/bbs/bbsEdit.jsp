<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
	<h1>글 상세정보</h1>
	<form action="${pageContext.request.contextPath}/bbs/edit.do" method="post">
	<input type="hidden" name="bbsNo" value="${bbsVo.bbsNo}" />
		<table>
			<tbody>
				<tr>
					<td>제목</td>
					<td><input type="text" name="bbsTitle" value="${bbsVo.bbsTitle}" ${ (loginUser.memId != bbsVo.bbsWriter) ? 'disabled' : ' '} /></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea rows="30" cols="70" name="bbsContent" ${ (loginUser.memId != bbsVo.bbsWriter) ? 'disabled' : ' '}>${bbsVo.bbsContent}</textarea></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td><input type="text" name="bbsWriter" value="${bbsVo.bbsWriter}"  disabled="disabled"/></td>
				</tr>
				<!--  -->
					<c:forEach items="${bbsVo.attList}" var="avo" varStatus="stat">
					<tr><td>첨부파일${stat.count}</td><td><a href="${pageContext.request.contextPath}/bbs/down.do?attNo=${avo.attNo}"> ${avo.attOrgName}</a></td></tr>
					</c:forEach>
			</tbody>
		</table>
		<c:if test="${loginUser.memId != bbsVo.bbsWriter}">
		<input type="submit"  value="저장"/>
		<a href="${pageContext.request.contextPath}/bbs/list.do"><input type="button"  value="취소"/></a>
		<a href="${pageContext.request.contextPath}/bbs/del.do?bbsNo=${bbsVo.bbsNo}"><input type="button"  value="삭제"/></a>
	</c:if>
	</form>
	<hr />
	<form action=""  id="repForm">
	<textarea rows="5" cols="50" name="repContent"></textarea>
	<input type="button" value="저장"  id="repAddBtn" />
	<input type="hidden" value="${bbsVo.bbsNo}" name="repBbsNo">
	</form>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.js"></script>
	<script type="text/javascript">
	$('#repAddBtn').on('click',function(){
	 $.ajax({
         url : '${pageContext.request.contextPath}/reply/add.do', //요청주소
         method : 'POST', //요청방식 (GET, POST, PUT, DELETE ...)
         data : 'repBbsNo='+$('[name="repBbsNo"]').val()+'&repContent='+$('[name="repContent"]').val()+'&repWriter='+$('[name="repWriter"]').val()
         dataType : 'json', //요청의 응답으로 받을 데이터의 타입 (text, html, json, xml ...)
     })
     .done(function(data, status, jqXHR){ //요청을 보내고 성공적으로 응답을 받았을 때 실행될 함수
             //date: 응답으로 받은 데이터, status: 응답의 상태, jqXHR: 요청객체
             console.log('요청성공 :');
             console.log(data);
         }).fail(function(jqXHR, status, error){
             //실패 했을 때 실행될 함수 jqXHR: 요청객체, status: 응답 상태, error: 발생한 오류
             console.log('요청실패: ',status);
         })
	})
	</script>
</body>
</html>