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
		<c:if test="${loginUser.memId == bbsVo.bbsWriter}">
		<input type="submit"  value="저장"/>
		<a href="${pageContext.request.contextPath}/bbs/list.do"><input type="button"  value="취소"/></a>
		<a href="${pageContext.request.contextPath}/bbs/del.do?bbsNo=${bbsVo.bbsNo}"><input type="button"  value="삭제"/></a>
	</c:if>
	</form>
	<div id="repDiv">
		<!-- <hr />
		<div>
			<span>rep_writer</span> <span>rep_date</span>
			<div>
			rep_content
			</div>
		</div> -->
	</div>
	<hr />
	<form action=""  id="repForm">
	<textarea rows="5" cols="50" name="repContent"></textarea>
	<input type="button" value="저장"  id="repAddBtn" />
	<input type="hidden" value="${bbsVo.bbsNo}" name="repBbsNo">
	</form>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.js"></script>
	<script type="text/javascript">
	/* 
	1. 현재 보고 있는 게시글의 댓글들만 받아와서 화면에 출력하도록 변경
		- 댓글 목록 조회시 파라미터로 부모글 번호를 전송
		- 컨트롤러에서 파라미터를 받아서 Mapper까지 전달하여 sql문에 적용
	2. 댓글을 추가하려면, 저장 성공시 다시 댓글 목록을 받아와서 화면에 출력
	3. 로그인한 사용자가 작성한 댓글에는 삭제 버튼을 추가하여 화면에 출력
		-삭제버튼을 클릭하면 로그인한 사용자가 작성한 댓글만 삭제되도록 구현
		-삭제 성공시 다시 댓글 목록을 받아와서 화면에 출력
	*/
	function r() {
		$.ajax({
	         url : '${pageContext.request.contextPath}/reply/list.do', 
	         method : 'GET',
	         data :{ repBbsNo : $('[name="repBbsNo"]').val()},
			 dataType : 'json'
	     })
	     .done(function(data, status, jqXHR){ 
	             console.log('요청성공 :');
	             console.log(data);
	             var repList = data;
	             //기존댓글을 화면에서 삭제함
	             var $repDiv = $('#repDiv').empty();
	             for (var i = 0; i < repList.length; i++) {
					var rep = repList[i];
					$('<hr>').appendTo( $repDiv);
					var $div1 = $('<div>').appendTo( $repDiv);
					$('<span>').html(rep.repWriter).appendTo($div1);
					$div1.append('  ');
					$('<span>').html(rep.repDate).appendTo($div1); 
					if(rep.repWriter === '${sessionScope.loginUser.memId}' ){ //댓글의 작성자 아이디 == 로그인한 사용자의 아이디
					$('<button>').html('삭제').appendTo($div1).addClass('delRepBtn').attr('data-no',rep.repNo);						
					}
					$('<div>').html(rep.repContent).appendTo( $repDiv);
				}
	         }).fail(function(jqXHR, status, error){
	             console.log('요청실패: ',status);
	         })
        }
	r();
         
	$('#repAddBtn').on('click',function(){
	 $.ajax({
         url : '${pageContext.request.contextPath}/reply/add.do', //요청주소
         method : 'POST', //요청방식 (GET, POST, PUT, DELETE ...)
         //(1) "x=1&y=2"와 같은 형식의 파라미터 문자열 직접 생성하여 설정하는 방식
		 //data : 'repBbsNo='+$('[name="repBbsNo"]').val()+'&repContent='+$('[name="repContent"]').val()+'&repWriter='+$('[name="repWriter"]').val(),
         //(2) 파라미터들을 {파라미터명:파라미터값 , 파라미터명:파라미터값 ... } 객체로 설정하는 방식 
		 //객체의 형태로 = -> : 으로 & -> , 로
         //data :{ repBbsNo : +$('[name="repBbsNo"]').val(), repContent:$('[name="repContent"]').val(),repWriter:$('[name="repWriter"]').val()},
		 //(3) 폼 또는 입력엘리먼트들을 찾아서 serialize()를 실행하면 입력엘리먼트들을 파라미터 문자열로 자동 생성
		 data : $('#repForm').serialize(),
		 dataType : 'json' //요청의 응답으로 받을 데이터의 타입 (text, html, json, xml ...)
     })
     .done(function(data, status, jqXHR){ //요청을 보내고 성공적으로 응답을 받았을 때 실행될 함수
             //date: 응답으로 받은 데이터, status: 응답의 상태, jqXHR: 요청객체
             console.log('요청성공 :');
             console.log(data);
             if(data.result == 1){ //{result : 1} 이렇게 객체로 돌아옴
             r();               
             alert('댓글성공');            	 
             }else{
            	 alert('댓글실패')
             }
         }).fail(function(jqXHR, status, error){
             //실패 했을 때 실행될 함수 jqXHR: 요청객체, status: 응답 상태, error: 발생한 오류
             console.log('요청실패: ',status);
         	 alert('댓글에 저장 실패')
         }).always(function(jqXHR,status){
             console.log('요청 처리 종료');
         });
	})
	
	$('#repDiv').on('click','.delRepBtn', function () {
		//삭제 요청
		$.ajax({
         url : '${pageContext.request.contextPath}/reply/del.do', //요청주소
         method : 'GET', 
         data :{ repNo : $(this).attr('data-no')},
		 dataType : 'json' 
     })
     .done(function(data, status, jqXHR){ 
             console.log('요청성공 :');
             console.log(data);
             if(data.result == 1){ //{result : 1} 이렇게 객체로 돌아옴
             r();               
             alert('댓글삭제성공');            	 
             }else{
            	 alert('댓글삭제실패')
             }
         }).fail(function(jqXHR, status, error){
             console.log('요청실패: ',status);
         	 alert('댓글에 삭제 실패')
         })
		alert('삭제요청');
	});
	</script>
</body>
</html>