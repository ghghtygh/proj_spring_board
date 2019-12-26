<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


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
	  $("#sbmt").click(function(){
			
			if($("#summernote").val().length > 4000) {
				
				var result=confirm("4000자 이하로 글자수가 제한됩니다.");
				
				if (result){
					
					var result=confirm("게시글을 수정하시겠습니까 ?");
					
					if (result){
						
						$("#summernote").val($("#summernote").val().substring(0, 4000));
						
						var formObj = $("#frm");
						
						formObj.attr("action","/modify");
						
						formObj.attr("method","post");
						formObj.submit();
						
					}
				}
	        }else{
			
				var result=confirm("게시글을 수정하시겠습니까 ?");
				
				if (result){
					
					var formObj = $("#frm");
					formObj.attr("action","/modify");
					formObj.attr("method","post");
					formObj.submit();
				}
	        }
			
		});
	  
	});

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
				<h3>Modify</h3>
				<hr>
				
			<div>
				
				<div class="input-group mb-3">
					<label class="col-sm-2 col-form-label" style="text-align:right;margin-right:20px;">
						제목
					</label>
					<div style="width:70%;">
						<input type="text" class="form-control" maxlength =50 name="title" value="${postVO.title}"aria-describedby="emailHelp" placeholder="제목 없음">
					</div>
				</div>

				<div style="font-size:90%;margin-top:30px;width:90%;">
					<p class="text-muted" style="">
						<div align="right">
							<div style="width:200px;text-align:left;">
								<div style="margin-top:5px;">작성자 : ${postVO.wrtId} | 조회수 : ${postVO.viewCnt}</div>
								<div style="margin-top:5px;">작성일 : ${fn:substring(postVO.wrtDt,0,16) }</div>
								<div style="margin-top:5px;">
									<c:if test="${postVO.wrtDt ne postVO.reDt}">
										수정일 : ${fn:substring(postVO.reDt,0,16) }
									</c:if>
								</div>
							</div>
						</div>
					</p>
				</div>

				<hr>
			
				<div class="input-group mb-3">
					<label class="col-sm-2 col-form-label" style="text-align:right;margin-right:20px;">
						내용
					</label>
					<div style="width:70%;">
						<textarea id="summernote" name="content" >${postVO.content}</textarea>
					</div>
				</div>
				<div class="input-group mb-3">
					<label class="col-sm-2 col-form-label" style="text-align:right;margin-right:20px;">
						첨부파일
					</label>	
					<div>
						<c:forEach items="${postVO.fileNames}" var="files">
							<a href="#" name="file">${files.ORIGINAL_NAME}(${files.FILE_SIZE}KB)</a><br>
						</c:forEach>
					</div>
					
				</div>
						
			</div>
			
			<div>
			</div>
			
			<div align = "right">
				<input type="hidden" name="postNum" value="${postVO.postNum}">
				<input type="button" class="btn btn-primary" id="sbmt" value="수정">
				<button type="button" class="btn btn-secondary" onClick="location.href='/read?num=${postVO.postNum}&page=${page}'">취소</button>
			</div>
		</div>
	</div>

</form>
</body>
</html>