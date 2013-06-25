<%@ taglib prefix="rr" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Recipe</title>
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
    <h1>Edit your recipe</h1>
    <form action="/edit" method="post">
        Recipe Title: <input type="text" name="title" value="${recipeTitle}"><br>
        <br>
        User: ${user.name}<br>
        <br>
        <textarea cols="40" rows="16" name="recipe">${recipeField}</textarea>
        <br>
        <input type="hidden" name="id" value="${param.id}">
        <input type="hidden" name="isNew" value="${param.isNew}">
        <input type="submit" value="Save">

    </form>


</body>
</html>