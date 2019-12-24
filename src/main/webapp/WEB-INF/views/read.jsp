<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>Read</title>

<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet">
<link href="<c:url value='/resources/css/_bootswatch.scss' />" rel="stylesheet">
<link href="<c:url value='/resources/css/_variables.scss' />" rel="stylesheet">


<!-- include summernote css/js-->

<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">

<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>


<!-- include summernote-ko-KR -->

<script src="/resources/js/summernote-ko-KR.js"></script>

<script>
$(document).ready(function() {
	$('#summernote').summernote({
	   	placeholder: 'content',
	      minHeight: 370,
	      maxHeight: null,
	      focus: true, 
	      lang : 'ko-KR'
	});
	  
	
	$('#delete').click(function(){
		
		var result = confirm("글을 삭제하시겠습니까 ?");
		
		if(result){
			
			var formObj=$('#frm');
			formObj.attr("action","/delete");
			formObj.attr("method","post");
			formObj.submit();
			
			
		}
		
	});
	  
	  
	  
	  
	  
});
</script>

<style>
	html, body, form {

    margin: 0;

    height: 100%;
	}
</style>
</head>
<form id="frm">
<body>


	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<div style="margin-left:10px;">
			<a class="navbar-brand" href="/">home</a>
		</div>
	</nav>
	
	<div class="container" style="height:100%;">
		<div style="margin-top:20px;">
				
			<div class="col-lg-8 col-md-7 col-sm-6">
			</div>
			
		</div>
		<div class="jumbotron" style="min-height:50%;">
			<div>
				<div>
					<h4 style="word-break:break-all;">제목 : ${postVO.title}</h4>
				</div>
				<div style="font-size:90%;margin-top:30px;">
					<p class="text-muted" style="">
						<div align="right">
							<div style="width:40%;text-align:left;">
								<div style="margin-top:5px;">작성자 : ${postVO.wrtId} | 조회수 : ${postVO.viewCnt}</div>
								<div style="margin-top:5px;">작성일 : <fmt:formatDate value="${postVO.wrtDt}" pattern="yyyy.MM.dd kk:mm"/></div>
								<div style="margin-top:5px;">
									<c:if test="${postVO.wrtDt ne postVO.reDt}">
										수정일 : <fmt:formatDate value="${postVO.reDt}" pattern="yyyy.MM.dd kk:mm"/>
									</c:if>
								</div>
								<div style="margin-top:5px;">
									<c:if test="${!(postVO.fileName eq ''||empty postVO.fileName)}">
										첨부파일 : ${postVO.fileName}
									</c:if>
								</div>
							</div>
						
						
					</p>
				</div>
				<hr>
			</div>
			<div style="min-height:300px;word-break:break-all;">
				<c:out value="${postVO.content}" escapeXml="false" />
			</div>
			<hr>
			<div style="margin-top:20px;">
				<div align="right">
					<c:if test="${not empty user and (user.userId=='admin' or user.userNum==postVO.writer)}">
						
						<input type="hidden" name="num" value="${postVO.postNum}">
						<input type="hidden" name="page" value="${page}">
						
						<button type="submit" class="btn btn-secondary" formaction="/doModify" formmethod="post">수정</button>
						
						<input type="button" class="btn btn-secondary" id="delete" value="삭제">
					
					</c:if>
				</div>
				<div>
				<c:choose>
					<c:when test="${empty keyword}">
						<a class="text-muted" href="/?page=${page}" ><b>돌아가기</b></a>
					</c:when>
					<c:otherwise>
						<a class="text-muted" href="/?page=${page}&searchOption=${searchOption}&keyword=${keyword}" ><b>돌아가기</b></a>
					</c:otherwise>
				</c:choose>
				</div>
			</div>
		</div>
	</div>
</form>
</body>
</html>