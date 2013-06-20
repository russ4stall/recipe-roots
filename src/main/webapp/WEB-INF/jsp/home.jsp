<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="rr" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="/recipe_roots_styles.css">
    <title>Recipe Roots</title>
</head>
<body>
<div id="banner">Recipe Roots</div>
<rr:navigation></rr:navigation>
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