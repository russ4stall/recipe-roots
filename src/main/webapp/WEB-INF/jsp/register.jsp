<%@ taglib prefix="rr" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="/recipe_roots_styles.css">
</head>
<body>
<div id="banner">Recipe Roots</div>
<rr:navigation></rr:navigation>

<form id="login_register" action="${pageContext.request.contextPath}/register" method="post">
    <table>

        <tr><td colspan="2" style="text-align:center"><h1>Register</h1></td></tr>
        <tr><td style="text-align:right">Name: </td><td><input type="text" name="name" value="${param.name}"></td></tr>
        <tr><td style="text-align:right">Email: </td><td><input type="text" name="email" value="${param.email}"></td></tr>
        <tr><td style="text-align:right">Confirm Email: </td><td><input type="text" name="email2" value="${param.email2}"></td></tr>
        <tr><td></td><td style="color:red"><small>${emailError}</small></td></tr>
        <tr><td style="text-align:right;">Password: </td><td><input type="password" name="password"></td></tr>
        <tr><td style="text-align:right;">Confirm Password: </td><td><input type="password" name="password2"></td></tr>
        <tr><td></td><td style="color:red"><small>${passMatchError}</small></td></tr>
        <tr><td colspan="2" style="text-align:center;"><input type="submit" value="Submit"></td></tr>
    </table>
</form>

</body>
</html>