<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rr" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Recipes</title>
    <link rel="stylesheet" type="text/css" href="/recipe_roots_styles.css">
</head>
<body>
<div id="banner"></div>
<rr:navigation></rr:navigation>
<div id="wrapper">
<div id="recipe_list">
  <h1>My Recipes</h1>
    <div id="recipes_toolbar">
        <a style="float: right" href="${pageContext.request.contextPath}/edit?isNew=true&id=${recipe.id}">New Recipe</a>
    </div>
    <table>
        <c:forEach items="${recipes}" var="recipe">
            <tr>
                <td>
                    <a id="recipe_title_link" href="${pageContext.request.contextPath}/viewrecipe?id=${recipe.id}">${recipe.title} </a>
                </td>
                <td>
                        Posted: ${recipe.createdOn}
                </td>
                <td>
                    <span class="options">
                        <a href="${pageContext.request.contextPath}/viewrecipe?id=${recipe.id}">view</a> .
                        <a href="${pageContext.request.contextPath}/edit?isNew=false&id=${recipe.id}">edit</a> .
                        <a href="${pageContext.request.contextPath}/delete?id=${recipe.id}&title=${recipe.title}">delete</a>
                    </span>
                </td>
            </tr>
        </c:forEach>
    </table>


</div>

</div>
<rr:aside user="${user}"></rr:aside>
</body>
</html>