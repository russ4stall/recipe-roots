<%--@elvariable id="recipe" type="com.github.russ4stall.reciperoots.recipes.Recipe"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rr" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recipe Roots</title>
    <link rel="stylesheet" type="text/css" href="/recipe_roots_styles.css">
    <script>
        function submitMyForm(){
        document.forms["deleteComment"].submit();
    }</script>
</head>
<body>

<div id="banner">


    <div id="title">Recipe Roots</div>
    <rr:navigation></rr:navigation>
    <div id="search">
        <form action="/search" method="get"><input type="submit" value="Search"><input type="text" size="15" name="search" value="Search Recipes"></form>
    </div>
</div>


<div class="featured_recipes">

    <div class="recipe_display">
        <h1>${recipe.title}</h1>

        <h3>Created by: <a href="mailto:${recipe.user.email}">${recipe.user.name}</a></h3>

        <p>
            ${recipe.recipe}
        </p>

<c:if test="${isUserIdSame}">
    <span class="options">
        <a href="${pageContext.request.contextPath}/edit?isNew=false&id=${recipe.id}">edit</a> .
        <a href="${pageContext.request.contextPath}/delete?id=${recipe.id}&title=${recipe.title}">delete</a>
    </span>
</c:if>
    </div>
    </div>


<div id="divider"></div>
<div id="comments">
    <h3>Comments:</h3>
    <div id="comment_form">
        <c:if test="${isLoggedIn}">
            <form action="/addcomment" method="post">
                <textarea cols="40" rows="6" name="comment">Hey ${user.name}! Leave a comment...</textarea>
                <br>
                <input type="hidden" name="recipeId" value="${recipe.id}">
                <input type="submit" value="Post">
            </form>
        </c:if>
            <c:if test="${noComments}">
                There are no comments for this recipe.
            </c:if>
    </div>
        <c:forEach items="${commentList}" var="comment">

            <div id="comment">
                <small style="color: darkblue"><em>${comment.createdOn}</em></small>
                <div style="margin-top: 10px;">${comment.comment}</div>
                <br>
                <small> Comment by: </small><a href="mailto:${comment.user.email}" style="color:darkblue;">${comment.user.name}</a>

                <c:if test="${comment.user.id == user.id}">
                    <form id="deleteComment" action="/deletecomment" method="post">
                        <input type="hidden" name="commentId" value="${comment.id}">
                        <input type="hidden" name="recipeId" value="${recipe.id}">
                    </form>
                <a style="padding-left: 180px;" href="#" onclick="submitMyForm()">delete</a>
                </c:if>
            </div>
        </c:forEach>



    </div>

</body>
</html>