package com.github.russ4stall.reciperoots.recipes.servlets;

import com.github.russ4stall.reciperoots.IndexOperations;
import com.github.russ4stall.reciperoots.IndexOperationsImpl;
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

/**
 * Date: 6/13/13
 * Time: 10:55 AM
 *
 * @author Russ Forstall
 */
public class DeleteRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterHelper helper = new ParameterHelper(req);
        Integer recipeId = helper.getParameterAsInteger("id");
        RecipesDao recipesDao = new RecipesDaoImpl();
        Recipe recipe = recipesDao.getRecipe(recipeId);
        User user = (User) req.getAttribute("user");
        User recipeUser = recipe.getUser();
        if (recipeUser.getId() != user.getId()) {
            resp.sendRedirect("/myrecipes");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/jsp/delete.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterHelper helper = new ParameterHelper(req);
        Integer recipeId = helper.getParameterAsInteger("id");
        RecipesDao recipesDao = new RecipesDaoImpl();
        recipesDao.deleteRecipe(recipeId);
        IndexOperations indexOperations = new IndexOperationsImpl();
        indexOperations.deleteFromIndex(recipeId);
        resp.sendRedirect("/myrecipes");
    }
}
