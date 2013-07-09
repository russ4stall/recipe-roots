<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="rr" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="/recipe_roots_styles.css">
    <title>Recipe Roots</title>
</head>
<body>
<div id="banner"></div>
<rr:navigation></rr:navigation>
<div id="wrapper">
<div id="search_form">
    <h1>Search</h1>
        <p>Search for recipes by ingredient, title or user.</p>
    <div>
        <form action="/search" method="get">
            <input type="text" size="20" name="search" placeholder="search"><br><br>
            <input id="searchAll" type="radio" name="byType" value="searchAll" checked><label for="searchAll"> Search All</label><br>
            <input id="ingredient" type="radio" name="byType" value="recipeIngredients"><label for="ingredient"> By Ingredient</label><br>
            <input id="byTitle" type="radio" name="byType" value="recipeTitle"><label for="byTitle"> By Title</label><br>
            <input id="user" type="radio" name="byType" value="recipeAuthor"><label for="user"> By User</label><br>
            <br>
            <input type="submit" value="Search"> <img src="/images/search_icon.png">
        </form>
    </div>
</div>
<p>Search Results:</p>
<c:choose>
    <c:when test="${not empty recipes}">
        <c:forEach items="${recipes}" var="recipe">
            <div class="recipe">
                <a id="recipe_title_link" href="${pageContext.request.contextPath}/viewrecipe?id=${recipe.id}">
                        ${recipe.title} </a><small> By: ${recipe.user.name}<i>(${recipe.createdOn})</i></small>
        <span class="options">
        <a href="${pageContext.request.contextPath}/viewrecipe?id=${recipe.id}">view</a>
        </span>
            </div>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <div style="background-color: floralwhite; width: 150;"> No results found!</div>
    </c:otherwise>
</c:choose>

</div>
<rr:aside user="${user}"></rr:aside>
</body>
</html>