<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag body-content="empty" %>

<div id="nav">

    <a href="${pageContext.request.contextPath}/home">Home</a>
    <a href="${pageContext.request.contextPath}/browse">Browse Recipes</a>
    <a href="${pageContext.request.contextPath}/search">Search</a>
    <c:if test="${user == null}">
       <a href="${pageContext.request.contextPath}/login?validUser=true">Log In</a>
       <a href="${pageContext.request.contextPath}/register?passMatch=true&emailMatch=true">Sign Up</a>
    </c:if>

    <div id="search">
        <form action="/search" method="get"><input type="submit" value="Search">
            <input type="hidden" name="byType" value="searchAll">
            <input type="text" size="15" name="search" placeholder="Search Recipes"></form>
    </div>
</div>