<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: russellf
  Date: 6/13/13
  Time: 3:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Browse</title>
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


    <h1>All Recipes</h1>

    <c:forEach items="${recipes}" var="recipe">
        <div class="recipe"><strong>${recipe.title}</strong> <small>By: ${recipe.user.name} <i>(${recipe.createdOn})</i></small>
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