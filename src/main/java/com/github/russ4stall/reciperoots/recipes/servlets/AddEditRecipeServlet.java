package com.github.russ4stall.reciperoots.recipes.servlets;

import com.github.russ4stall.reciperoots.IndexOperations;
import com.github.russ4stall.reciperoots.IndexOperationsImpl;
import com.github.russ4stall.reciperoots.recipes.Ingredient;
import com.github.russ4stall.reciperoots.recipes.MeasurementType;
import com.github.russ4stall.reciperoots.recipes.Recipe;
import com.github.russ4stall.reciperoots.recipes.dao.RecipesDao;
import com.github.russ4stall.reciperoots.recipes.dao.RecipesDaoImpl;
import com.github.russ4stall.reciperoots.users.User;
import com.github.russ4stall.reciperoots.utilities.ParameterHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isEmpty;
//import org.apache.commons.lang3.StringUtils

/**
 * Date: 6/13/13
 * Time: 9:18 AM
 *
 * @author Russ Forstall
 */
public class AddEditRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getAttribute("user");
        RecipesDao recipesDao = new RecipesDaoImpl();

        boolean isNew = Boolean.parseBoolean(req.getParameter("isNew"));

        if (!isNew) {
            int recipeId = Integer.valueOf(req.getParameter("id"));
            Recipe recipe = recipesDao.getRecipe(recipeId);
            req.setAttribute("recipeTitle", recipe.getTitle());
            req.setAttribute("recipeDescription", recipe.getDescription());
            req.setAttribute("recipeInstructions", recipe.getInstructions());
            req.setAttribute("ingredients", recipe.getIngredients());
          //req.setAttribute("recipeIngredient", recipe.getIngredients());

            req.setAttribute("prepTime", recipe.getPrepTime());
            req.setAttribute("cookTime", recipe.getCookTime());
            req.setAttribute("recipeTags", recipe.getTags());
            User recipeUser = recipe.getUser();
            if (user.getId() != recipeUser.getId()) {
                resp.sendRedirect("/myrecipes");
                return;
            }
        }

        req.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getAttribute("user");
        RecipesDao recipesDao = new RecipesDaoImpl();
        ParameterHelper helper = new ParameterHelper(req);
        Boolean isNew = helper.getParameterAsBoolean("isNew");

        List<Ingredient> ingredientsList = new ArrayList<Ingredient>();

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setIngredient(req.getParameter("ingredient1"));
        ingredient1.setQuantity(Double.valueOf(req.getParameter("quantity1")));
        ingredient1.setMeasurementType((MeasurementType.valueOf(req.getParameter("measurement1"))));
        ingredientsList.add(ingredient1);

        for (int i = 2; i < 1000; i++) {
            if (!isEmpty(req.getParameter("ingredient" + i))) {
                Ingredient ingredient = new Ingredient();
                ingredient.setIngredient(req.getParameter("ingredient" + i));
                ingredient.setQuantity(Double.valueOf(req.getParameter("quantity" + i)));
                ingredient.setMeasurementType((MeasurementType.valueOf(req.getParameter("measurement" + i))));
                ingredientsList.add(ingredient);
            }
        }

        System.out.println("==============================================");
        for (Ingredient ingredient : ingredientsList){
            System.out.println(ingredient.getIngredient());
            System.out.println(ingredient.getQuantity());
            System.out.println(ingredient.getMeasurementLabel());

        }


        Map<String, String> errorMessages = new HashMap<String, String>();

        if (isEmpty(req.getParameter("recipeInstructions"))){
            errorMessages.put("instructionsEmpty", "Must have instructions");
        }else{
            req.setAttribute("recipeInstructions", req.getParameter("recipeInstructions"));
        }
        if (isEmpty(req.getParameter("recipeDescription"))){
            errorMessages.put("descriptionEmpty", "Must have a description");
        }else{
            req.setAttribute("recipeDescription", req.getParameter("recipeDescription"));
        }
        if (isEmpty(req.getParameter("ingredient1"))){
            errorMessages.put("ingredientEmpty", "Must have at least one ingredient");
        }else{
            req.setAttribute("ingredient1", (req.getParameter("ingredient1")));
        }
        if (req.getParameter("cookTime").isEmpty() || 0 > Integer.valueOf(req.getParameter("cookTime"))){
            errorMessages.put("cookTimeEmpty", "Must insert valid cook time");
        }else{
            req.setAttribute("cookTime", Integer.valueOf(req.getParameter("cookTime")));
        }
        if (req.getParameter("prepTime").isEmpty() || 0 > Integer.valueOf(req.getParameter("prepTime"))){
            errorMessages.put("prepTimeEmpty", "Must insert valid prep time");
        }else{
            req.setAttribute("prepTime", Integer.valueOf(req.getParameter("prepTime")));
        }
        if (isEmpty(req.getParameter("recipeTitle"))){
            errorMessages.put("titleEmpty", "Must have a title");
        }else{
            req.setAttribute("recipeTitle", req.getParameter("recipeTitle"));
        }
        if (!isEmpty(req.getParameter("recipeTags"))){
            req.setAttribute("recipeTags", req.getParameter("recipeTags"));
        }
        if (!errorMessages.isEmpty()){
            req.setAttribute("errorMessages", errorMessages);
            req.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(req, resp);
            return;
        }


        Recipe recipe = new Recipe();
        recipe.setTitle(req.getParameter("recipeTitle"));
        recipe.setUser(user);
        recipe.setIngredients(ingredientsList);
        recipe.setDescription(req.getParameter("recipeDescription"));
        recipe.setInstructions(req.getParameter("recipeInstructions"));
        recipe.setPrepTime(Integer.valueOf(req.getParameter("prepTime")));
        recipe.setCookTime(Integer.valueOf(req.getParameter("cookTime")));
        recipe.setTags(req.getParameter("recipeTags"));

        IndexOperations indexOperations = new IndexOperationsImpl();

        if (isNew) {
            int recipeId = recipesDao.addRecipe(recipe);
            recipe = recipesDao.getRecipe(recipeId);
            indexOperations.addToIndex(recipe);
        } else {
            int id = Integer.valueOf(req.getParameter("id"));
            recipe.setId(id);
            indexOperations.deleteFromIndex(id);
            recipesDao.updateRecipe(recipe);
            recipe = recipesDao.getRecipe(id);
            indexOperations.addToIndex(recipe);
        }

        resp.sendRedirect("/myrecipes");
    }
}
