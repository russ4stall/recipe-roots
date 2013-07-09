<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag body-content="empty" %>
<%@ attribute name="user" required="true" type="com.github.russ4stall.reciperoots.users.User" %>
<%@ taglib prefix="rr" tagdir="/WEB-INF/tags" %>

<div id="aside">

    <c:if test="${user == null}">
        <div id="aside_login">
            <form action="${pageContext.request.contextPath}/login" method="post">
                <table>
                    <tr><td>
                        <input type="text" name="email" placeholder="Email">
                    </td></tr>
                    <tr><td>
                        <input type="password" name="password" placeholder="Password">
                    </td></tr>
                    <tr><td>
                        <input type="submit" value="Log In"> or <a href="/register?passMatch=true&emailMatch=true"> Sign Up</a>
                    </td></tr>

                </table>
            </form>
        </div>
    </c:if>
    <c:if test="${user != null}">
        <div id="user_tools_head">
            <em>Logged in as ${user.name}</em><br>
        </div>
        <div id="user_tools">
            <a href="${pageContext.request.contextPath}/myrecipes"> <img src="/images/book_icon.png"> My Recipes</a>
            <a href="${pageContext.request.contextPath}/edit?isNew=true"><img src="/images/plus_icon.png">  New Recipe</a>
            <a href="#"><img src="/images/star_icon.png">  Favorites</a>
            <br>
            <a href="${pageContext.request.contextPath}/logout">Log Out</a>

        </div>
    </c:if>
<div id="aside_recipes">

    <rr:recipethumb recipe="${sampleRecipe}"></rr:recipethumb>
    <rr:recipethumb recipe="${randomRecipe}"></rr:recipethumb>
    <rr:recipethumb recipe="${recipe}"></rr:recipethumb>

</div>





</div>