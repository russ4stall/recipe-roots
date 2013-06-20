<%@ tag body-content="empty" %>

<%@ attribute name="recipe" required="true" type="com.github.russ4stall.reciperoots.recipes.Recipe" %>

<div class="recipe_display">
    <h3>${recipe.title}</h3>
    <h4>Created by: <a href="mailto:${recipe.user.email}">${recipe.user.name}</a></h4>
    <p>${recipe.recipe}</p>
</div>
