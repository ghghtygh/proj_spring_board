<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>Write</title>

<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.2/jquery.form.min.js"
 integrity="sha384-FzT3vTVGXqf7wRfy8k4BiyzvbNfeYjK+frTVqZeNDFl8woCbF0CYG6g2fMEFFo/i" crossorigin="anonymous"></script>
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
	
	
	$("#sbmt").click(function(){
		
		if($("#summernote").val().length > 4000) {
			
        	var result=confirm("글자수 초과\n4000자 이하로 글자수가 제한됩니다.");
        	
			if (result){
				
				var result=confirm("게시글을 작성하시겠습니까 ?");
				
				if (result){
					
					$("#summernote").val($("#summernote").val().substring(0, 4000));
					
					
					var formObj = $("#frm");
					
					formObj.attr("action","/write");
					
					formObj.attr("method","post");
					
					formObj.submit();
					
					
				}
			
			}
			
        }else{
		
		
			var result=confirm("게시글을 작성하시겠습니까 ?");
			
			if (result){
			
				
				var formObj = $("#frm");
				formObj.attr("action","/write");
				formObj.attr("method","post");
				formObj.submit();
				
			}
        }
		
		
		
		
	});
	
	
	
	
	
});

var fileNum = 0;

function fn_add(){
	fileNum+=1;
	
	
	// 클릭된 태그 제거
	$("#fileAdd").detach();
	
	
	
		//새로 만들기
	$("#fileParent").append("<input type=file name='file"+fileNum+"'><a href='#' id='fileAdd' onclick='fn_add();return false;'>파일추가</a><br>");
	if(fileNum>5){
		$("#fileAdd").detach();
	}
}

</script>


</head>
<body>

<form name="frm" id="frm" enctype="multipart/form-data">
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
				<h3>Write</h3>
				<hr>
				
			<div>
				
				<div class="input-group mb-3">
					<label class="col-sm-2 col-form-label" style="text-align:right;margin-right:20px;">
						제목
					</label>
					<div style="width:70%;">
						<input type="text" class="form-control" maxlength =50 name="title" aria-describedby="emailHelp" placeholder="제목 없음">
					</div>
				</div>
				
				<hr>
			
				<div class="input-group mb-3">
					<label class="col-sm-2 col-form-label" style="text-align:right;margin-right:20px;">
						내용
					</label>
					<div style="width:70%;">
						<textarea id="summernote" name="content"></textarea>
					</div>
				</div>
					
				<div class="input-group mb-3">
					<label class="col-sm-2 col-form-label" style="text-align:right;margin-right:20px;">
						첨부파일
					</label>
					<div style="width:70%;padding:5px;" id="fileParent">
						<input type="file" class="" id="fileId[0]" name="fileName[0]" style="border:0px solid black;"/>
						 <a href="#" id="fileAdd" onclick="fn_add();return false;">파일추가</a><br>
					</div>
						
				</div>
			
			</div>
			<div align = "right" style="margin-right:20px;">
				<input type="hidden" name="writer" value="${user.userNum}">
				<input type="button" class="btn btn-primary" id="sbmt" value="등록">
				
				<button type="button" class="btn btn-secondary" onClick="location.href='/'">취소</button>
				
			</div>
		</div>
	</div>
</form>
</body>
</html>