<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rr" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Browse</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/recipe_roots_styles.css">
</head>
<body>
<div id="banner"></div>
    <rr:navigation></rr:navigation>
<div id="wrapper">
<div id="recipe_list">
    <h1>All Recipes</h1>
    <table>
        <tr>
            <th style="background-color: #FFFDF0">Title</th>
            <th style="background-color: #FFFDF0">User</th>
            <th style="background-color: #FFFDF0">Posted</th>
        </tr>
    <c:forEach items="${recipes}" var="recipe">
                <tr>
                    <td>
                        <a id="recipe_title_link" href="${pageContext.request.contextPath}/viewrecipe?id=${recipe.id}">${recipe.title} </a>
                    </td>
                    <td>
                         ${recipe.user.name}
                    </td>
                    <td>
                        ${recipe.createdOn}
                    </td>
                </tr>
    </c:forEach>
    </table>

</div>
</div>
<rr:aside user="${user}"></rr:aside>
</body>

</html>