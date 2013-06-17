<%--
  Created by IntelliJ IDEA.
  User: russellf
  Date: 6/13/13
  Time: 10:50 AM
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
    <a href="/browse">Browse Recipes</a>
    <a href="/myrecipes">My Recipes</a>
    ${logInLink}

</div>
<div id="section_main">


    <h1>My Recipes</h1>

Are you sure you want to delete the recipe: <b><u><em>${param.title}</em></u></b>?<br>
    <form class="float_me" action="${pageContext.request.contextPath}/delete" method="post">
        <input type="hidden" name="id" value="${param.id}">
        <input type="submit" value="delete">
    </form>
    <a class="float_me" href="${pageContext.request.contextPath}/myrecipes">cancel</a>

</div>


</body>
</html>