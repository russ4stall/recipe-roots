<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: russellf
  Date: 6/12/13
  Time: 9:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Recipes</title>
    <link rel="stylesheet" type="text/css" href="/recipe_roots_styles.css">
</head>
<body>
<div id="banner">Recipe Roots</div>
<div id="nav">
    <form>&nbsp Search Recipes:<input type="text" size="15" name="search"></form>
    <a href="/home">Home</a>
    <a href="#">Browse Recipes</a>
    <a href="/myrecipes">My Recipes</a>
    <a href="/login">Log In</a>

</div>
<div id="section_main">


    <h1>My Recipes</h1>

    <c:forEach items="${recipes}" var="recipe">
    <div class="recipe">${recipe.title} (${recipe.createdOn})
        <span class="options">
        <a href="${pageContext.request.contextPath}/viewrecipe?id=${recipe.id}">view</a> .
        <a href="#">edit</a> .
        <a href="#">delete</a>
        </span>
    </div>
    </c:forEach>

    <div id="recipes_toolbar">
        <a href="#">add </a>
    </div>




   <!-- <table>

        <tr><td><h3>My Recipes</h3></td></tr>
        <tr><td>Honey Brew Root Beer</td>
            <td><a href="#">view</a> .</td>
            <td><a href="#">edit</a> .</td>
            <td><a href="#">delete</a></td>
        </tr>
        <tr><td>Tahitian Vanilla Cream Soda</td>
            <td><a href="#">view</a> .</td>
            <td><a href="#">edit</a> .</td>
            <td><a href="#">delete</a></td>
        </tr>
        <tr><td>West Mexican Explosion</td>
            <td><a href="#">view</a> .</td>
            <td><a href="#">edit</a> .</td>
            <td><a href="#">delete</a></td>
        </tr>
        <tr><td>Butter Brew Cream Soda</td>
            <td><a href="#">view</a> .</td>
            <td><a href="#">edit</a> .</td>
            <td><a href="#">delete</a></td>
        </tr>
    </table>
-->

</div>


</body>
</html>