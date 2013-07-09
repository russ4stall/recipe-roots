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
    <div id="section_main">


        <h1>My Recipes</h1>

        Are you sure you want to delete the recipe: <b><u>${param.title}</u></b>?<br>

        <form class="float_me" action="${pageContext.request.contextPath}/delete" method="post">
            <input type="hidden" name="id" value="${param.id}">
            <input type="submit" value="delete">
        </form>
        <a class="float_me" href="${pageContext.request.contextPath}/myrecipes">cancel</a>

    </div>
</div>
<rr:aside user="${user}"></rr:aside>
</body>
</html>