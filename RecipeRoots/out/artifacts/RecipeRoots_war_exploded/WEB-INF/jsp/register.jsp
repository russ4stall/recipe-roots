<%--
  Created by IntelliJ IDEA.
  User: russellf
  Date: 6/11/13
  Time: 4:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
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

<form id="login_register" action="${pageContext.request.contextPath}/register" method="post">
    <table>

        <tr><td>&nbsp</td><td><h1>Register</h1></td></tr>
        <tr><td>Name: </td><td><input type="text" name="email"></td></tr>
        <tr><td>Email: </td><td><input type="text" name="email"></td></tr>
        <tr><td>Password: </td><td><input type="password" name="password"></td></tr>
        <tr><td>Confirm Password: </td><td><input type="password" name="password"></td></tr>
        <tr><td>&nbsp</td><td><input type="submit" value="Submit"></td></tr>
    </table>
</form>

</body>
</html>