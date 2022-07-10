<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="pagination">
    <span class="pg-prev">
        <c:if test="${pager.startPage > 1}">
            <a href="#" class="" onclick="fn_paging('${pager.startPage-1}')">이전</a>
        </c:if>
    </span>

    <c:forEach begin="${pager.startPage}" end="${pager.endPage}" var="pageNum">
        <a href="#" class="${pageNum eq pager.nowPage ? 'on' : ''}" onclick="fn_paging('${pageNum}')">${pageNum}</a>
    </c:forEach>

    <span class="pg-next">
        <c:if test="${pager.endPage+1 < pager.pageCnt}">
            <a href="#" class="" onclick="fn_paging('${pager.endPage+1}')">다음</a>
        </c:if>
    </span>
</div>