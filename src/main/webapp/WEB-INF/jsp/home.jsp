<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="rr" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="/recipe_roots_styles.css">
    <title>Recipe Roots</title>
</head>
<body>


<div id="banner"></div>
<rr:navigation></rr:navigation>
<div id="wrapper">
<!--<div id="about">
    <h1>About Us</h1>
    <p>
    Need a safe place to store your recipes? Want to share your recipes with the world and discover completely new
    recipes? Recipe Roots is for everyone. It's simple, effective and extremely easy to use. Get started by clicking the
    Sign Up link above. If you don't want to sign up you can still search and browse the collection of recipes submitted by our users.
    </p>
</div> -->

    <rr:recipe recipe="${sampleRecipe}"></rr:recipe>
    <rr:recipethumb recipe="${randomRecipe}"></rr:recipethumb>
    <rr:recipethumb recipe="${sampleRecipe}"></rr:recipethumb>
    <rr:recipethumb recipe="${latestRecipe}"></rr:recipethumb>
</div>
<rr:aside user="${user}"></rr:aside>
</body>
</html>