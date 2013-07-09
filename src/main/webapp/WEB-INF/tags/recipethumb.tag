<%@ tag body-content="empty" %>
<%@ taglib prefix="rrl" uri="rrTagLibrary" %>


<%@ attribute name="recipe" required="true" type="com.github.russ4stall.reciperoots.recipes.Recipe" %>

<a href="${pageContext.request.contextPath}/viewrecipe?id=${recipe.id}" id="recipe_thumb" style="background-image: url(${rrl:getPicture(recipe.id)})">
    <div id="recipe_thumb_info">
       <b>${recipe.title}</b><br>
        Ready in ${rrl:toHourMixedNumber(recipe.prepTime, recipe.cookTime)}
    </div>
</a>
