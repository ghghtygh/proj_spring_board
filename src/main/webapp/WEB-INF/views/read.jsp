<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




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
	});
</script>

<style>
	html, body, form {

    margin: 0;

    height: 100%;
	}



출처: https://offbyone.tistory.com/341 [쉬고 싶은 개발자]
</style>
</head>
<form>
<body>


	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<div style="margin-left:10px;">
			<a class="navbar-brand" href="/">home</a>
		</div>
	</nav>
	
	<div class="container" style="height:100%;">
		<div style="margin-top:50px;">
				
			<div class="col-lg-8 col-md-7 col-sm-6">
			</div>
			
		</div>
		<div class="jumbotron" style="min-height:50%;">
			<div>
				<h4>제목 : ${postVO.title}</h4>
				<p class="text-muted">
					<div>작성자 : ${postVO.wrtId} | 조회수 : ${postVO.viewCnt}</div>
					<div>작성일자 : ${postVO.date}</div>
				</p>
				<hr>
			</div>
			<div style="min-height:300px;">
				<c:out value="${postVO.content}" escapeXml="false" />
			</div>
			<hr>
			<div>
				<div align="right">
					<c:if test="${not empty user and (user.userId=='admin' or user.userNum==postVO.writer)}">
						
						<input type="hidden" name="num" value="${postVO.postNum}">
						<input type="hidden" name="page" value="${page}">
						
						<button type="submit" class="btn btn-secondary" formaction="/doModify" formmethod="post">수정</button>
						
						<button type="submit" class="btn btn-secondary" formaction="/delete" formmethod="post">삭제</button>
					
					</c:if>
				</div>
				<a class="text-muted" href="/?page=${page}" >돌아가기</a>
			</div>
		</div>
	</div>
</form>
</body>
</html>