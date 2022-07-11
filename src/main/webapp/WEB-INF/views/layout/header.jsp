
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/taglib.jsp"%>

<div id="top-area">
    <a class="link_cafe" href="/">
        <img src="${resourcesPath}/image/logo/cafe_logo2.png" width="70" height="17" alt="Jupo Cafe"/>
    </a>

    <div class="top-menu">
        <p class="top-h">
            <c:choose>
                <c:when test="${empty loginVO}">
                    <a href="<c:url value='signin'/>">로그인</a> |
                </c:when>
                <c:otherwise>
                    <a href="#;"><c:out value="${loginVO.nickname(loginVO.loginId)}"/></a> |
                    <a href="#;">로그아웃</a> |
                </c:otherwise>
            </c:choose>
            <a href="#;">카페홈</a>
        </p>
    </div>
</div>

<div id="front-img">
    <div id="front-cafe">
        <a name="cafeUrlLink" href="/">
            <img src="${resourcesPath}/image/sample/banner/banner1.jpg" width="1080" alt="Banner Sample"/>
        </a>
    </div>
</div>