<%--
  Created by IntelliJ IDEA.
  User: russellf
  Date: 6/13/13
  Time: 9:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Recipe</title>
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