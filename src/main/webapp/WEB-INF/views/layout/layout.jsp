<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>
<c:set var="resourcesPath" value="${CONTEXT_PATH}/resources" scope="application"/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script type="text/javascript">
        var contextPath = "${contextPath}";
        var resourcesPath = "${resourcesPath}";
    </script>

    <link href="${resourcesPath}/css/bootstrap.css' />" rel="stylesheet">
    <link href="${resourcesPath}/css/_bootswatch.scss' />" rel="stylesheet">
    <link href="${resourcesPath}/css/_variables.scss' />" rel="stylesheet">

    <link rel="stylesheet" href="${resourcesPath}/css/common.css">
    <link rel="stylesheet" href="${resourcesPath}/css/layout.css">

    <title><tiles:insertAttribute name="title"/></title>
</head>

<body>
    <div class="">
        <tiles:insertAttribute name="header"/>

        <div class="layout-body">
            <tiles:insertAttribute name="left"/>
            <div class="">
                <tiles:insertAttribute name="body"/>
            </div>
        </div>
        <tiles:insertAttribute name="footer"/>
    </div>

</body>

</html>