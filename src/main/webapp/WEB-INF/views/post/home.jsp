<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>

<script>
	
	$(document).ready(function() {
		
		$("#chk_all").on("mouseover",function(){
			
			$("#nohover_tr").css("color","white");
		});
		
		$("#chk_all").on("click",function(){
			
			console.log($("input[name='chk_all']").prop('checked'));
			console.log($("input[name='chk_all']").attr('checked'));
			
			if($("#chk_all").prop('checked')){
				 //console.log("체크됨");
				 $("input[name='postNoList']").prop('checked',true);
			}else{
				//console.log("체크안댐");
				$("input[name='postNoList']").prop('checked',false);
			}
		});
		
		$("input[name='postNoList']").on("click",function(){
			
			$("input[name='chk_all']").prop('checked',false);
		});
		
		
		$("#del_sbmt").click(function(){
			
			var result = false;
			var count = $("input[name='postNoList']:checked").length;
			if (count<=0){
				
				alert("선택된 게시글이 없습니다");
				
				return;
				
			}else if (confirm("총 "+count+"개의 게시글이 선택되었습니다.\n삭제하시겠습니까 ?")){
				//var formObj = $("#frm");
				//formObj.attr("action","/deletePostList");
				//formObj.attr("method","post");
				//formObj.submit();

				$.ajax({
					url			: "/deletePostList",
					data		: $("#frm").serialize(),
					type		: "POST",
					dataType	: "json",
					success		: function(response){
						var result = response.result;
						if(result == "success"){
							alert("정상적으로 삭제되었습니다.");
							location.href("/home");
						}else{
							alert("삭제에 실패하였습니다.");
						}
					},
					error		: function(){
						alert("에러가 발생하였습니다.");
					}
				});
			}
			
		});
		
	});
	
	var kw = "${searchVO.keyword}";
	var so = "${searchVO.searchOption}";
	
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
<form id="frm">
		<div class="">
			<div>
				<div class="mt-50">
					<div class="col-lg-8 col-md-7 col-sm-6">
						<h3>게시판</h3>
					</div>
				</div>
		 	</div>
		 	<div class="mt-20">
				<div class="fl">
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

				<div class="fl">
	 			
		 			<div class="form-inline form-group" align="right">
		 			
						<select class="form-control" style="width:auto; margin-left:20px; margin-right: 5px;" name="searchOption">
							
							<option value='all' selected>전체</option>
							<option value='content'>내용</option>
							<option value='title'>제목</option>
							<option value='userId'>작성자</option>

						</select>
				
						
						<input type="text" class="form-control mr-5" id="kw" name="keyword">
						
						<button type="submit" class="btn btn-secondary mr-5" formaction="/">검색</button>
					
					</div>
				</div>
			</div>
			
	 		
		 	<div>
				<div align="center">
				
				    <table class="table table-hover" style="width:100%; max-width: 1200px; font-size:100%; text-align:center; table-layout:fixed; word-break:break-all;">
				        <thead>
				        	<colgroup>
				        		<col width="2%"/>
				        		<col width="8%"/>
				        		<col width="37%"/>
				        		<col width="15%"/>
				        		<col width="29%"/>
				        		<col width="9%"/>
				        	</colgroup>
				        	
				            
				        </thead>
				        <tbody>
				        	<tr class="table-primary" id="nohover_tr">
				            
								<td id="nohover_td" style="pointer-events:none;">
									<c:if test="${!(empty user.userId)}">
										<input style="pointer-events: all;" type="checkbox" name="chk_all" id="chk_all">
									</c:if>
				            	</td>
				            	<td style="pointer-events:none;"></td>
				                <td style="pointer-events:none;">제목</td>
				                <td style="pointer-events:none;">작성자</td>
				                <td style="pointer-events:none;">작성일</td>
				                <td style="pointer-events:none;">조회</td>
				            </tr>
				            
				        	<c:if test="${pager.listCnt<=0}">
				        		<tr>
				        			<td colspan="6">
				        				<c:choose>
							        		<c:when test="${(keyword==''||empty keyword)}">
							        			게시글이 없습니다.
							        		</c:when>
							        		<c:otherwise>
							        			검색결과가 없습니다.
							        		</c:otherwise>
							        	</c:choose>
				        			</td>
				        		</tr>
				        	</c:if>
				            <c:forEach items="${postList}" var="post">
				                <tr class="table-light">
				                	<%--<td>
										<input type="checkbox" name="postNoList" value="${post.postNo}">
				                	</td>--%>
				                	<td>
				                		${post.postNo}
				                	</td>
				                    <td style="">
				                    	<div style="width:100%;">
					                    	<div style="width:90%;float:left;text-align:left;text-overflow:ellipsis; overflow:hidden;">
						                    	<nobr>
						                    	<c:choose>
							                    	<c:when test="${keyword eq ''||empty keyword}">
							                    		<a class="" href='/read?num=${post.postNo}&page=${pager.nowPage}' title="${post.title}"
								                    	 style="color:#2C3E50;font-weight:600;">
								                    		${post.title}
								                    	</a>					                    	
							                    	</c:when>
							                    	
							                    	<c:otherwise>
							                    		<a class="" href='/read?num=${post.postNo}&page=${pager.nowPage}&selectOption=${selectOption}&keyword=${keyword}' title="${post.title}"
								                    	 style="color:#2C3E50;font-weight:600;">
								                    		${post.title}
								                    	</a>
							                    	</c:otherwise>
						                    	
						                    	</c:choose>
						                    	
						                    	</nobr>
					                    	</div>
					                    	<div align="right" style="width:10%;float:right">
					                    		<c:if test="${post.fileYn eq 'Y'}">
					                    			<img src='/resources/image/disk.ico' style="width:auto;height:15px;">
					                    		</c:if>
					                    	</div>
				                    	</div>
				                    </td>
				                    <td>${post.loginId}</td>
				                    
				                    <td>
				                    	${fn:substring(post.frstRegtDt, 0, 16) }
				                    </td>
				                    <td style="text-align:right">${post.viewCnt}</td>
				                    
				                     
				                </tr>
				            </c:forEach>
				        </tbody>
				     </table>
				     
				</div>

				<%-- 페이징 --%>
				<%@include file="/WEB-INF/views/common/pagination.jsp"%>

				<div style="left:0;top:0;position:absolute;text-align:right;width:100%;">

					<c:if test="${!(empty user)}">
						<input type="button" class="btn btn-secondary" style="margin-right: 5px;" id="del_sbmt" value="게시글 삭제">
					</c:if>
					<input type="button" class="btn btn-primary" style="margin-right: 5px;" onClick="location.href='/write'" value="게시글 작성">
				</div>

			</div>
    	</div>
    </form>

