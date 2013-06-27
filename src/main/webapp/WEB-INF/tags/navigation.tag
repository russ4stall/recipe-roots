<%@ tag body-content="empty" %>

<div id="nav">

    <a href="${pageContext.request.contextPath}/home">Home</a>
    <a href="${pageContext.request.contextPath}/browse">Browse Recipes</a>
    <a href="${pageContext.request.contextPath}/search">Search</a>
    ${myRecipesLink}
    ${logInLink}
    ${signUpLink}
    <small>${loggedInAs}</small>

</div>