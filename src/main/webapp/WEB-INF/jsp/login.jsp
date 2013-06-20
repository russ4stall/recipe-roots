<%@ taglib prefix="rr" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log In</title>
    <link rel="stylesheet" type="text/css" href="/recipe_roots_styles.css">
</head>
<body>
<div id="banner">Recipe Roots</div>
<rr:navigation></rr:navigation>

<form id="login_register" action="${pageContext.request.contextPath}/login" method="post">
    <table>
        <tr><td colspan="2" style="text-align:center"><h1>Log In</h1></td></tr>
        <tr><td colspan="2" style="color:red; text-align:center"><small>${loginError}</small></td></tr>
        <tr><td>Email: </td><td><input type="text" name="email" value="${email}"></td></tr>
        <tr><td>Password: </td><td><input type="password" name="password"></td></tr>
        <tr><td colspan="2" style="text-align:center"><input type="submit" value="Log In"></td></tr>
        <tr><td>&nbsp</td></tr>
        <tr><td colspan="2" style="text-align:center">Not a member? <a href="/register?passMatch=true&emailMatch=true">Sign Up</a></td></tr>
    </table>
${sessionScope.user}

</form>

</body>
</html>