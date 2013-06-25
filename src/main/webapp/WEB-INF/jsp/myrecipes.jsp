<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rr" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Recipes</title>
    <link rel="stylesheet" type="text/css" href="/recipe_roots_styles.css">
</head>
<body>
<div id="banner">


    <div id="title">Recipe Roots</div>
    <rr:navigation></rr:navigation>
    <div id="search">
        <form action="/search" method="get"><input type="submit" value="Search"><input type="text" size="15" name="search" value="Search Recipes"></form>
    </div>
</div>
<div id="section_main">


    <h1>My Recipes</h1>

    <c:forEach items="${recipes}" var="recipe">
    <div class="recipe">
            <a id="recipe_title_link" href="${pageContext.request.contextPath}/viewrecipe?id=${recipe.id}">
            ${recipe.title}</a><small><i>(last updated: ${recipe.createdOn})</i></small>
        <span class="options">
        <a href="${pageContext.request.contextPath}/viewrecipe?id=${recipe.id}">view</a> .
        <a href="${pageContext.request.contextPath}/edit?isNew=false&id=${recipe.id}">edit</a> .
        <a href="${pageContext.request.contextPath}/delete?id=${recipe.id}&title=${recipe.title}">delete</a>
        </span>
    </div>
    </c:forEach>

    <div id="recipes_toolbar">
        <a href="${pageContext.request.contextPath}/edit?isNew=true&id=${recipe.id}">add</a>
    </div>
</div>


</body>
</html>