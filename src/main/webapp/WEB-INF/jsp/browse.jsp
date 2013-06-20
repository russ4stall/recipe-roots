<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rr" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Browse</title>
    <link rel="stylesheet" type="text/css" href="/recipe_roots_styles.css">
</head>
<body>
<div id="banner">Recipe Roots</div>
<rr:navigation></rr:navigation>
<div id="section_main">


    <h1>All Recipes</h1>

    <c:forEach items="${recipes}" var="recipe">
        <div class="recipe"><strong>${recipe.title}</strong> <small>By: ${recipe.user.name} <i>(${recipe.createdOn})</i></small>
        <span class="options">
        <a href="${pageContext.request.contextPath}/viewrecipe?id=${recipe.id}">view</a>
        </span>
        </div>
    </c:forEach>

    <div id="recipes_toolbar">
        <a href="${pageContext.request.contextPath}/edit?isNew=true&id=${recipe.id}">add</a>
    </div>
</div>


</body>
</html>