<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>


	<header class="header" role="banner">
		<div class="header_inner">
			<a href="/" class="logo">
				<h1 class="blind">Jupo Cafe</h1>
			</a>
		</div>
	</header>
	<div id="container" class="container">
        <div class="login_wrap" align="center">
			<div class="card login_card">
				<h5 class="card-header">ID 로그인</h5>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">
						<div class="card-body">
							<div class="input-group mb-3">
								<c:choose>
									<c:when test="${empty userId||userId==''}">
										<input type="text" autofocus name="loginId" class="form-control-plaintext" aria-describedby="emailHelp" placeholder="아이디" maxlength =30>
									</c:when>

									<c:otherwise>
										<input type="text" autofocus name="loginId" value="${loginId}" class="form-control-plaintext" maxlength =30>
									</c:otherwise>

								</c:choose>
							</div>
							<div class="input-group mb-3">
								<input type="password" class="form-control-plaintext" name="password" placeholder="비밀번호"  maxlength =32>
							</div>
							<div align="center" class="text-danger">
								&nbsp;
								<c:if test="${!(empty loginId||loginId=='')}">
									아이디 비밀번호 불일치
								</c:if>
							</div>
                            <div>
								<input type="checkbox" id="loginSaveChk"/><label for="loginSaveChk">&nbsp;아이디 저장</label>
							</div>
							<div>
								<button type="submit" class="btn btn-primary" formaction="signin" formmethod="post">로그인</button>
							</div>
						</div>

					</li>
				</ul>
			</div>
		</div>
		<a href="#" class="">비밀번호 찾기</a> |
		<a href="#" class="">아이디 찾기</a> |
		<a href="<c:url value="signup"/>" class="">회원가입</a>
	</div>

