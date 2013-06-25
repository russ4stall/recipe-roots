<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="rr" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="/recipe_roots_styles.css">
    <title>Recipe Roots</title>
</head>
<body>
<div id="banner">
    <div id="title">Recipe Roots</div>

    <rr:navigation></rr:navigation>
    <div id="search">
    <form action="/search" method="get"><input type="submit" value="Search"><input type="text" size="15" name="search" value="Search Recipes"></form>
    </div>
</div>

<div id="about">Need a safe place to store your recipes? Want to share your recipes with the world and discover completely new
recipes? Recipe Roots is for everyone. It's simple, effective and extremely easy to use. Get started by clicking the
Sign Up link above. If you don't want to sign up you can still search and browse the collection of recipes submitted by our users. </div>

<div class="featured_recipes">
    <h1>Featured Recipe</h1>
<rr:recipe recipe="${randomRecipe}"></rr:recipe>

</div>
<div class="featured_recipes">
    <h1>Most Recent Recipe</h1>
<rr:recipe recipe="${latestRecipe}"></rr:recipe>


</div>

</body>
</html>