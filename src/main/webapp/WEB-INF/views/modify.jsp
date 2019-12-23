<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


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



<title>Modify</title>


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


</head>
<body>
<form>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<div style="margin-left:10px;">
			<a class="navbar-brand" href="/">home</a>
		</div>
	</nav>
		

	<div class="container">
		<div style="margin-top:50px;">
				
			<div class="col-lg-8 col-md-7 col-sm-6">
			</div>
			
		</div>
		<div class="jumbotron">
				<h3>Modify</h3>
				<hr>
				
			<div>
				
				<div class="input-group mb-3">
					<label class="col-sm-3 col-form-label">제목</label>
					<input type="text" class="form-control-plaintext" name="title" value="${postVO.title}">
				</div>
				<div class="input-group mb-3">
					<label class="col-sm-5 col-form-label">
						작성자 : ${postVO.wrtId}<br>
						작성일자 : ${postVO.date}
					</label>
				</div>
				<hr>
			
				<div class="input-group mb-3">
					<label class="col-sm-3 col-form-label">
						내용
					</label>
					<textarea id="summernote" name="content">${postVO.content}</textarea>
				</div>
					
				
						
			</div>
			
			<div>
			</div>
			
			<div align = "right">
				<input type="hidden" name="postNum" value="${postVO.postNum}">
				<button type="submit" class="btn btn-primary" formaction="/modify" formmethod="post">수정</button>
				<button type="button" class="btn btn-secondary" onClick="location.href='/read?num=${postVO.postNum}&page=${page}'">취소</button>
			</div>
		</div>
	</div>

</form>
</body>
</html>