<%@ taglib prefix="rrl" uri="rrTagLibrary" %>
<%@ taglib prefix="rr" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Edit Recipe</title>
    <link rel="stylesheet" type="text/css" href="/recipe_roots_styles.css">
    <link rel="stylesheet" type="text/css" href="/widgEditor.css">
    <script src="/js/widgEditor.js" type="text/javascript"></script>
    <script type="text/javascript">

        var ingredientCount = ${fn:length(ingredients)};
        function add() {
            ingredientCount++;

            var fieldContainer = document.createElement("div");
            (document.getElementById("ingredientsForm")).appendChild(fieldContainer);

            var ingredientField = document.createElement("input");
            ingredientField.setAttribute("type", "text");
            ingredientField.setAttribute("name", "ingredient" + ingredientCount);
            ingredientField.setAttribute("placeholder", "sugar");
            fieldContainer.appendChild(ingredientField);

            var quantityField = document.createElement("input");
            quantityField.setAttribute("type", "text");
            quantityField.setAttribute("name", "quantity" + ingredientCount);
            quantityField.setAttribute("placeholder", "2.5");
            quantityField.setAttribute("size", "1");
            fieldContainer.appendChild(quantityField);

            var measurementField = document.createElement("select");
            measurementField.setAttribute("name", "measurement" + ingredientCount);
            fieldContainer.appendChild(measurementField);

            var optionNone = document.createElement("option");
            optionNone.setAttribute("value", "NONE");
            optionNone.appendChild(document.createTextNode(""));
            measurementField.appendChild(optionNone);

            var optionTsp = document.createElement("option");
            optionTsp.setAttribute("value", "TEASPOON");
            optionTsp.appendChild(document.createTextNode("teaspoons"));
            measurementField.appendChild(optionTsp);

            var optionTablespoon = document.createElement("option");
            optionTablespoon.setAttribute("value", "TABLESPOON");
            optionTablespoon.appendChild(document.createTextNode("tablespoons"));
            measurementField.appendChild(optionTablespoon);

            var optionCup = document.createElement("option");
            optionCup.setAttribute("value", "CUP");
            optionCup.appendChild(document.createTextNode("cups"));
            measurementField.appendChild(optionCup);

            var optionPint = document.createElement("option");
            optionPint.setAttribute("value", "PINT");
            optionPint.appendChild(document.createTextNode("pints"));
            measurementField.appendChild(optionPint);

            var optionQuart = document.createElement("option");
            optionQuart.setAttribute("value", "QUART");
            optionQuart.appendChild(document.createTextNode("quarts"));
            measurementField.appendChild(optionQuart);

            var optionLiter = document.createElement("option");
            optionLiter.setAttribute("value", "LITER");
            optionLiter.appendChild(document.createTextNode("liters"));
            measurementField.appendChild(optionLiter);

            var optionGallon = document.createElement("option");
            optionGallon.setAttribute("value", "GALLON");
            optionGallon.appendChild(document.createTextNode("gallons"));
            measurementField.appendChild(optionGallon);

            var optionOunce = document.createElement("option");
            optionOunce.setAttribute("value", "OUNCE");
            optionOunce.appendChild(document.createTextNode("ounces"));
            measurementField.appendChild(optionOunce);

            var optionPound = document.createElement("option");
            optionPound.setAttribute("value", "POUND");
            optionPound.appendChild(document.createTextNode("pounds"));
            measurementField.appendChild(optionPound);


        }

    </script>


</head>
<body>
<div id="banner"></div>
<rr:navigation></rr:navigation>
<div id="wrapper">
    <div id="section_main">
        <h1>Edit your recipe</h1>


        <br>

        <form action="/edit" method="post">
            <h3>Title:</h3> <input type="text" name="recipeTitle" placeholder="Recipe Title" value="${recipeTitle}">
            <c:if test="${not empty errorMessages['titleEmpty']}">
                <em style="color: red">*${errorMessages['titleEmpty']}</em>
            </c:if>
            <br><br>

            <h3>Prep Time:</h3>(in minutes) <input type="number" name="prepTime" value="${prepTime}" >
            <c:if test="${not empty errorMessages['prepTimeEmpty']}">
                <em style="color: red">*${errorMessages['prepTimeEmpty']}</em>
            </c:if>
            <br>

            <h3>Cook Time:</h3>(in minutes) <input type="number" name="cookTime" value="${cookTime}" >
            <c:if test="${not empty errorMessages['cookTimeEmpty']}">
                <em style="color: red">*${errorMessages['cookTimeEmpty']}</em>
            </c:if>
            <br><br><br>

            <h3>Ingredients:</h3>
            <c:if test="${not empty errorMessages['ingredientEmpty']}">
                <em style="color: red">*${errorMessages['ingredientEmpty']}</em>
            </c:if>
            <input type="button" value="Add Ingredient" onclick="add();">
            <!--<a href="#" onclick="add()" style="color: black;"> <img src="/images/plus_icon.png"> Add Ingredient</a>-->
            <br>
            <br>
            Ingredient &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp Qty. &nbsp Measurement<br>
            <c:set var="counter" value="0"/>
            <c:forEach items="${ingredients}" var="ingredient">
                <div>
                    <c:set var="counter" value="${counter + 1}"/>
                    <input type="text" name="ingredient${counter}" value="${ingredient.ingredient}"><input type="text" name="quantity${counter}" size="1" value="${ingredient.quantity}"><select name="measurement${counter}">
                        <option value="${ingredient.measurementType}">${ingredient.measurementLabel}</option>
                        <option value="NONE"></option>
                        <option value="TEASPOON">teaspoons</option>
                        <option value="TABLESPOON">tablespoons</option>
                        <option value="CUP">cups</option>
                        <option value="PINT">pints</option>
                        <option value="QUART">quarts</option>
                        <option value="LITER">liters</option>
                        <option value="GALLON">gallons</option>
                        <option value="OUNCE">ounces</option>
                        <option value="POUND">pounds</option>
                    </select>
                </div>
            </c:forEach>
            <div id="ingredientsForm"></div>
            <script>add();</script>
            <br><br><br>

            <h3>Description:</h3>
            <c:if test="${not empty errorMessages['descriptionEmpty']}">
                <em style="color: red">*${errorMessages['descriptionEmpty']}</em>
            </c:if>
            <br>
            <textarea cols="50" rows="10" name="recipeDescription">${recipeDescription}</textarea>
            <br><br><br><br>

            <h3>Instructions for Preparation:</h3>
            <c:if test="${not empty errorMessages['instructionsEmpty']}">
                <em style="color: red">*${errorMessages['instructionsEmpty']}</em>
            </c:if>
            <br>
            <textarea class="widgEditor" cols="60" rows="30" name="recipeInstructions">${recipeInstructions}</textarea>
            <br><br><br><br>

            <h3>Tags:</h3>(separated by commas)<br>
            <input type="text" name="recipeTags" size="50" placeholder="example: chicken, parmesan, cheese, pasta"
                   value="${recipeTags}">
            <br><br>
            <input type="hidden" name="id" value="${param.id}">
            <input type="hidden" name="isNew" value="${param.isNew}">
            <input type="submit" value="Save">


        </form>

    </div>
</div>
<rr:aside user="${user}"></rr:aside>
</body>
</html>