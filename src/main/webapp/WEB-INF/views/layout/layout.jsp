<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
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

    <title><tiles:insertAttribute name="title"/></title>

    <tiles:insertAttribute name="scripts"/>
</head>

<body>
    <div class="layout-body">
        <tiles:insertAttribute name="header"/>

        <div id="content-area">
            <tiles:insertAttribute name="left"/>
            <div id="main-area" class="fr">
                <tiles:insertAttribute name="body"/>
            </div>
        </div>
        <tiles:insertAttribute name="footer"/>
    </div>

</body>

</html>