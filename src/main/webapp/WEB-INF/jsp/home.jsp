<%--
  Created by IntelliJ IDEA.
  User: russellf
  Date: 6/12/13
  Time: 11:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/recipe_roots_styles.css">
    <title>Recipe Roots</title>
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
    <h1>Featured Recipe</h1>
    <div class="recipe_display">
        <h3>${recipe1.title}</h3>
        <h4>Created by: <a href="mailto:${recipe1.user.email}">${recipe1.user.name}</a></h4>
        <p>
            ${recipe1.recipe}
        </p>
    </div>
</div>
<div class="featured_recipes">
    <h1>Most Recent Recipe</h1>
    <div class="recipe_display">
        <h3>${recipe2.title}</h3>
        <h4>Created by: <a href="mailto:${recipe2.user.email}">${recipe2.user.name}</a></h3>
            <p>
                ${recipe2.recipe}
            </p>
    </div>
</div>

</body>
</html>