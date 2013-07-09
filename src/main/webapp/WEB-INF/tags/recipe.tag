<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rrl" uri="rrTagLibrary" %>
<%@ tag body-content="empty" %>

<%@ attribute name="recipe" required="true" type="com.github.russ4stall.reciperoots.recipes.Recipe" %>

<div id="recipe_display">
    <div id="recipe_description">
        <div id="recipe_pic"><img src="/images/recipeimages/dishpic_${recipe.id}.jpg" alt=""></div>
        <p>${recipe.description}</p>
    </div>
    <a href="${pageContext.request.contextPath}/viewrecipe?id=${recipe.id}">
    <h1>${recipe.title}</h1></a>
    <h4>Authored by: <a href="mailto:${recipe.user.email}">${recipe.user.name}</a></h4>
    <h2>Time</h2>
    Prep Time: ${recipe.prepTime} minutes<br>
    Cook Time: ${recipe.cookTime} minutes<br>

    <div id="recipe_ingredients">
        <h2>Ingredients</h2>
        <table>
        <c:forEach items="${recipe.ingredients}" var="ingredient">
         <tr>
             <td><input type="checkbox"></td>
             <td> ${rrl:toMixedNumber(ingredient.quantity)}
              ${ingredient.measurementLabel} ${ingredient.ingredient}</td>
         </tr>
        </c:forEach>
        </table>
    </div>

    <div id="recipe_instructions">
        <h2>Preparation</h2>
        <p>${recipe.instructions}</p>
    </div>
</div>
