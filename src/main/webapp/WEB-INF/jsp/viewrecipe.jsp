<%--@elvariable id="recipe" type="com.github.russ4stall.reciperoots.recipes.Recipe"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rr" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recipe Roots</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/recipe_roots_styles.css">
    <script>
        function submitMyForm(){
        document.forms["deleteComment"].submit();
    }</script>
</head>
<body>

<div id="banner"></div>
<rr:navigation></rr:navigation>
<div id="wrapper">
    <rr:recipe recipe="${recipe}"></rr:recipe>


<c:if test="${user.id == recipe.user.id}">
    <span class="options">
        <a href="${pageContext.request.contextPath}/edit?isNew=false&id=${recipe.id}">edit</a> .
        <a href="${pageContext.request.contextPath}/delete?id=${recipe.id}&title=${recipe.title}">delete</a>
    </span>
</c:if>




<div id="comments">
    <h3>Comments:</h3>
    <div id="comment_form">
        <c:if test="${user != null}">
            <form action="/addcomment" method="post">
                <textarea cols="40" rows="6" name="comment">Hey ${user.name}! Leave a comment...</textarea>
                <br>
                <input type="hidden" name="recipeId" value="${recipe.id}">
                <input type="submit" value="Post">
            </form>
        </c:if>
            <c:if test="${empty commentList}">
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
                    <form id="deleteComment" action="${pageContext.request.contextPath}/deletecomment" method="post">
                        <input type="hidden" name="commentId" value="${comment.id}">
                        <input type="hidden" name="recipeId" value="${recipe.id}">
                    </form>
                <a style="padding-left: 180px;" href="#" onclick="submitMyForm()">delete</a>
                </c:if>
            </div>
        </c:forEach>


    </div>
</div>

<rr:aside user="${user}"></rr:aside>
</body>
</html>