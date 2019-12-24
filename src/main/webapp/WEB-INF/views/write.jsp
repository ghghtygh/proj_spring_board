<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
			
        	alert("글자수가 4000 이하로 제한됩니다.");
            $("#summernote").val($("#summernote").val().substring(0, 4000));

        }
		
		
		var result=confirm("게시글을 작성하시겠습니까 ?");
		
		if (result){
			
			var formObj = $("#frm");
			formObj.attr("action","/write");
			formObj.attr("method","post");
			formObj.submit();
			
		}
		
	});
	
	
	
	
	
	/*===============   테스트   ===============*/

	$("#sbmt2").click(function(){
		
		if($("#summernote").val().length > 4000) {
			
        	alert("글자수가 4000 이하로 제한됩니다.");
            $("#summernote").val($("#summernote").val().substring(0, 4000));

        }
		
		
		var result=confirm("게시글을 작성하시겠습니까 ?");
		
		if (result){
			
			var filesChk = $("input[name='files[0]'").val();
			
			if (filesChk== ""){
				$("input[name='files[0]'").remove;
			}
			
			$("#frm").ajaxForm({
				url		: "/write2",
				enctype	: "multipart/form-data",
				cache	: false,
				async	: true,
				type	: "POST",
				success	: function(obj){
					insertBoardCallback(obj);
				},
				error	: function(xhr,status,error){alert("fail")}
				
				
			}).submit();
			
		}
		
	});
	
	
	
	function insertBoardCallback(obj){
		
		if(obj!=null){
			var result = obj.result;
			
			if(result=="SUCCESS"){
				alert('게시글 등록 성공');
				
			}else{
				alert("게시글 등록 실패");
			}
		}
	}
	/*========================================*/
	
	
	
	
	
	
	
});

/*
$(document).ready(function() {
	$('#summernote').summernote({
	  	placeholder: 'content',
		minHeight: 370,
		maxHeight: null,
		focus: true, 
		lang : 'ko-KR',
		callbacks: {
		    onImageUpload: function(files, editor, welEditable) {
		      for (var i = files.length - 1; i >= 0; i--) {
		        sendFile(files[i], this);
		      }
		    }
		}
	});
});

function sendFile(file, el) {
  var form_data = new FormData();
  form_data.append('file', file);
	$.ajax({
		data: form_data,
		type: "POST",
		url: '/image',
		cache: false,
		contentType: false,
		enctype: 'multipart/form-data',
		processData: false,
		success: function(url) {
		  $(el).summernote('editor.insertImage', url);
		  $('#imageBoard > ul').append('<li><img src="'+url+'" width="480" height="auto"/></li>');
		}
	});
}
*/
</script>


</head>
<body>

<form name="frm" id="frm">
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
					<label class="col-sm-3 col-form-label">제목</label>
					<input type="text" class="form-control" maxlength =50 name="title" aria-describedby="emailHelp" placeholder="제목 없음">
				</div>
				
				<hr>
			
				<div class="input-group mb-3">
					<label class="col-sm-3 col-form-label">
						내용
					</label>
					<textarea id="summernote" name="content"></textarea>
				</div>
					
				
						
			</div>
			
			<div>
				<input type="file" class="" id="files[0]" name="files[0]" style="border:0px solid black;"/>
			</div>

			<div align = "right">
				<input type="hidden" name="writer" value="${user.userNum}">
				<input type="button" class="btn btn-primary" id="sbmt" value="등록">
				<!-- 
				<input type="button" class="btn btn-primary" id="sbmt2" value="테스트">
				-->
				<button type="button" class="btn btn-secondary" onClick="location.href='/'">취소</button>
				
			</div>
		</div>
	</div>
</form>
</body>
</html>