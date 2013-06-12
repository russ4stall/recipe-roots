<%--
  Created by IntelliJ IDEA.
  User: russellf
  Date: 6/12/13
  Time: 11:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/recipe_roots_styles.css">
    <title>Recipe Roots</title>
</head>
<body>
<div id="banner">Recipe Roots</div>
<div id="nav">
    <form>&nbsp Search Recipes:<input type="text" size="15" name="search"></form>
    <a href="#">Home</a>
    <a href="#">Browse Recipes</a>
    <a href="/myrecipes">My Recipes</a>
    <a href="/login">Log In</a>

</div>

<div class="featured_recipes">
    <h1>Featured Recipes</h1>
    <div class="recipe_display">
        <h3>Honey Brew Root Beer</h3>
        <p>
        This is where the recipe goes. The description, the ingredients, the instructions.
            Everything you need to know about making Honey brew Root Beer is right here
            in this section. Boy, I sure love Root Beer. In fact, I could go for some now.
        </p>
    </div>
</div>
<div class="featured_recipes">
    <h1>Recent Recipes</h1>
    <div class="recipe_display">
        <h3>West Mexican Explosion</h3>
        <p>
            This is where the recipe goes. The description, the ingredients, the instructions.
            Everything you need to know about making a Baja Blast clone is right here
            in this section. Boy, I sure love Baja Blast. In fact, I think Taco Bell is extremely selfish
            in the way they hoard all of the Baja Blast. That is why I need to crack the formula!
        </p>
    </div>
</div>

</body>
</html>