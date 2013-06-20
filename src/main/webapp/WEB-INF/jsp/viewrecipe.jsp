<%--@elvariable id="recipe" type="com.github.russ4stall.reciperoots.recipes.Recipe"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rr" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recipe Roots</title>
    <link rel="stylesheet" type="text/css" href="/recipe_roots_styles.css">
</head>
<body>
<div id="banner">Recipe Roots</div>

<rr:navigation></rr:navigation>
<div class="featured_recipes">
    <h1>${recipe.title}</h1>

    <div class="recipe_display">
        <h3>Created by: <a href="mailto:${recipe.user.email}">${recipe.user.name}</a></h3>

        <p>
            ${recipe.recipe}
        </p>
    </div>
<c:if test="${isUserIdSame}">
    <span class="options">
        <a href="${pageContext.request.contextPath}/edit?isNew=false&id=${recipe.id}">edit</a> .
        <a href="${pageContext.request.contextPath}/delete?id=${recipe.id}&title=${recipe.title}">delete</a>
    </span>
</c:if>
</div>


</body>
</html>