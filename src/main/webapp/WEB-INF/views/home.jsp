<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Home</title>
<!-- 
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
 -->
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

<link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet">
<link href="<c:url value='/resources/css/_bootswatch.scss' />" rel="stylesheet">
<link href="<c:url value='/resources/css/_variables.scss' />" rel="stylesheet">


<script>


	var kw = "${keyword}";
	var so = "${searchOption}";
	
	function fn_paging(nowPage) {
		
		var url="/?page="+nowPage;
		
		
		if (kw!=""){
			url+="&searchOption=";
			url+=so;
			url+="&keyword=";
			url+=kw;
		}
		location.href=url;
		
	}
	
	function goHome(){
		
		location.href="/";
	}
	

</script>
</head>
<body>
<form id="form1">
	
		<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
			<div style="margin-left:10px;">
				<a class="navbar-brand" href="/">home</a>
			</div>
		</nav>
		
		<div class="container">
		
			<div>
				<div style="margin-top:50px;">
				
					<div class="col-lg-8 col-md-7 col-sm-6">
					
						<h3>게시판</h3>
					</div>
					
				</div>
				<div align="right">
				    <div class="btn-group btn-group-toggle" >
					 	<c:catch>
						    <c:choose>
						        <c:when test="${empty user }">
						        
							        <input type="button" class="btn btn-primary" onClick="location.href='/signin'" value="로그인">
							        <input type="button" class="btn btn-primary" onClick="location.href='/signup'" value="회원가입">
							        
						        </c:when>
						        <c:when test="${user.userNum eq 1 }">
				                       <p class="btn btn-primary disabled">관리자 ${user.userId}</p>
				                       <input type="button" class="btn btn-primary" onClick="location.href='/signout'" value="로그아웃">
							        
				                </c:when>
				                
				                <c:otherwise>
				                       <p class="btn btn-primary disabled">${user.userId}</p>
				                       <input type="button" class="btn btn-primary" onClick="location.href='/signout'" value="로그아웃">
							        
				                </c:otherwise>
						                
						    </c:choose>
						</c:catch>
				
				 	</div>
			 	</div>
		 	</div>
	 		<div align="right" style="margin-top:20px;margin-bottom:20px;">
	 			<c:choose>
	 				<c:when test="${!(keyword==''||empty keyword)}">
	 					검색결과 : ${pager.listCnt}개 |
	 					<a href="/">전체 보기</a>
	 					
	 				</c:when>
	 				<c:otherwise>
	 					총 게시글 : ${pager.listCnt}개
	 				</c:otherwise>
	 			</c:choose>
				
			</div>
		 	<div>
				<div>
				    <table class="table table-hover"  width="100%" style="font-size:100%;text-align:center">
				        <thead>
				        	<colgroup>
				        		<col width="10%"/>
				        		<col width="*"/>
				        		<col width="10%"/>
				        		<col width="10%"/>
				        		<col width="10%"/>
				        		<col width="10%"/>
				        	</colgroup>
				            <tr class="table-primary">
				            	<td>글번호</td>
				                <td>제목</td>
				                <td>작성자</td>
				                <td>날짜</td>
				                <td>시간</td>
				                <td>조회수</td>
				            </tr>
				        </thead>
				        <tbody>
				            <c:forEach items="${postList}" var="post">
				                <tr class="">
				                	<td>${post.postNum}</td>
				                    <td>
				                    	
				                    	<a class="" href='/read?num=${post.postNum}&page=${pager.nowPage}'>
				                    	<p class="mb-0">
				                    			${post.title}
				                    	</p>
				                    	</a>
				                    </td>
				                    <td>${post.wrtId}</td>
				                    <td>${post.date}</td>
				                    <td>${post.time}</td>
				                    <td>${post.viewCnt}</td>
				                </tr>
				            </c:forEach>
				        </tbody>
				     </table>
				     
				</div>
				
				<div align="center">
				  	<div class="btn-group mr-2">
				  		<c:choose>
						  	<c:when test="${pager.nowPage ne 1 }">
						  	
						  		<a href='#' class="btn btn-primary" onClick="fn_paging(1)">처음</a>
								
								
							</c:when>
							<c:otherwise>
						  		<a class="btn btn-primary disabled">처음</a>
							</c:otherwise>
					  	</c:choose>
				  
					  	<c:choose>
						  	<c:when test="${pager.nowPage ne 1 }">
							    <a href="#" class="btn btn-primary" onClick="fn_paging('${pager.prevPage}')">&laquo;</a>
							</c:when>
							<c:otherwise>
							    <a class="btn btn-primary disabled">&laquo;</a>
							</c:otherwise>
					  	</c:choose>
				  	
					  	<c:forEach begin="${pager.startPage}" end="${pager.endPage}" var="pageNum">
							        	
			        		<c:choose>
			        		
			        			<c:when test="${pageNum eq pager.nowPage}">
			        				<a href="#" class="btn btn-primary active" onClick="fn_paging('${pageNum}')">${pageNum }</a>
			        				
			        			</c:when>
			        			
			        			<c:otherwise>
			        				<a href="#" class="btn btn-primary" onClick="fn_paging('${pageNum}')">${pageNum}</a>
			        			
			        			</c:otherwise>
			        			
							</c:choose>     
							   	
			        	</c:forEach>
			        	
			        	
			        	<c:choose>
						  	<c:when test="${pager.nowPage ne pager.pageCnt && pager.pageCnt > 0 }">
						  		
						  		<a class="btn btn-primary" href="#" onClick="fn_paging('${pager.nextPage}')">&raquo;</a>
								
								
							</c:when>
							<c:otherwise>
								
								<a class="btn btn-primary disabled">&raquo;</a>
								
							</c:otherwise>
					  	</c:choose>
					  	
			        	<c:choose>
						  	<c:when test="${pager.nowRange ne pager.rangeCnt && pager.rangeCnt>0 }">
						  		
						  		<a class="btn btn-primary" href="#" onClick="fn_paging('${pager.pageCnt}')">끝</a>
								
								
							</c:when>
							<c:otherwise>
								
								<a class="btn btn-primary disabled">끝</a>
								
							</c:otherwise>
					  	</c:choose>
					</div>
				</div>
				<div style="margin-top:20px;margin-bottom:20px;">
					<div class ="form-inline form-group" align="center">
							
						<select class="form-control" style="width:auto; margin-right: 5px;" name="searchOption">
							
							<option value='all' selected>전체</option>
							<option value='content'>내용</option>
							<option value='title'>제목</option>
							<option value='userId'>작성자</option>
						
						
						</select>
				
						<input type="text" class="form-control" style="margin-right: 5px;"id="kw" name="keyword">
						
						<button type="submit" class="btn btn-secondary" style="margin-right: 5px;" formaction="/">검색</button>
				
						<input type="button" class="btn btn-primary" style="margin-right: 5px;" onClick="location.href='/write'" value="게시글 작성">
					
						
					</div>
				</div>
			</div>
    	</div>
    </form>
</body>
</html>