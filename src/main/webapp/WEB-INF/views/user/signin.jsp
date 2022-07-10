<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>



<html>
<head>
	<meta charset="UTF-8">

	<link rel="shortcut icon" type="image/x-icon" href="${resourcesPath}/image/favicon.ico" />

	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<script type="text/javascript">
		var contextPath = "${contextPath}";
		var resourcesPath = "${resourcesPath}";
	</script>

	<link href="${resourcesPath}/css/bootstrap.css" rel="stylesheet">
	<link href="${resourcesPath}/css/_bootswatch.scss" rel="stylesheet">
	<link href="${resourcesPath}/css/_variables.scss" rel="stylesheet">

	<link rel="stylesheet" href="${resourcesPath}/css/common.css">
	<link rel="stylesheet" href="${resourcesPath}/css/layout.css">

	<title>Jupo Cafe</title>

	<%@ include file="/WEB-INF/views/layout/scripts.jsp"%>
</head>
<form>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<div style="margin-left:10px;">
			<a class="navbar-brand" href="/">home</a>
		</div>
	</nav>
	<div class="container">
		<div style="margin-top:50px;">
				
			<div class="card mb-3">
			
				<h3 class="card-header">ID 로그인</h3>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">
					
						<div class="card-body">
							<div class="input-group mb-3">
								<label class="col-sm-3 col-form-label">아이디</label>
								<c:choose>
									<c:when test="${empty userId||userId==''}">
										    
										<input type="text" autofocus name="userId" class="form-control-plaintext" aria-describedby="emailHelp" placeholder="Enter ID" maxlength =30>
							
									</c:when>
										    
								    <c:otherwise>
										<input type="text" autofocus name="userId" value="${userId}" class="form-control-plaintext" maxlength =30>							
								    </c:otherwise>
										    
								</c:choose>
							</div>
							<div class="input-group mb-3">
							
								<label class="col-sm-3 col-form-label">비밀번호</label>
								<input type="password" class="form-control-plaintext" name="userPw" placeholder="Password"  maxlength =32>
							
							</div>
							<div align="center" class="text-danger">
								&nbsp;
								<c:if test="${!(empty userId||userId=='')}">
									아이디 비밀번호 불일치
								</c:if>
							</div>
							<div align="right">
								<button type="submit" class="btn btn-primary" formaction="signin" formmethod="post">로그인</button>
							
								<button type="submit" class="btn btn-secondary" formaction="/doSignup" formmethod="post">회원가입</button>
							</div>
						</div>
						
					</li>
				</ul>
				<div align="right" class="card-footer">
					<a class="text-muted" href="/" ><b>돌아가기</b></a>
				</div>
				
			</div>
			
		</div>
	</div>

</body>
</form>
</html>
