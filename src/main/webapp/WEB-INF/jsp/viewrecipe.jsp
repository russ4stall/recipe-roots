<%--@elvariable id="recipe" type="com.github.russ4stall.reciperoots.Recipe"--%>
<%--
  Created by IntelliJ IDEA.
  User: russellf
  Date: 6/12/13
  Time: 1:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recipe Roots</title>
    <link rel="stylesheet" type="text/css" href="/recipe_roots_styles.css">
</head>
<body>
<div id="banner">Recipe Roots</div>

<div id="nav">
    <form>&nbsp Search Recipes:<input type="text" size="15" name="search"></form>
    <a href="/home">Home</a>
    <a href="/browse">Browse Recipes</a>
    <a href="/myrecipes">My Recipes</a>
    ${logInLink}
</div>
<div class="featured_recipes">
    <h1>${recipe.title}</h1>
    <div class="recipe_display">
        <h3>Created by: <a href="mailto:${recipe.user.email}">${recipe.user.name}</a></h3>
        <p>
           ${recipe.recipe}
        </p>
    </div>

    <span class="options">
        <a href="${pageContext.request.contextPath}/edit?isNew=false&id=${recipe.id}">edit</a> .
        <a href="${pageContext.request.contextPath}/delete?id=${recipe.id}&title=${recipe.title}">delete</a>
        </span>
</div>


</body>
</html>