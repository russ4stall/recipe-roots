package com.github.russ4stall.reciperoots.recipes.servlets;

import com.github.russ4stall.reciperoots.recipes.Recipe;
import com.github.russ4stall.reciperoots.recipes.dao.RecipesDao;
import com.github.russ4stall.reciperoots.recipes.dao.RecipesDaoImpl;
import com.github.russ4stall.reciperoots.users.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

/**
 * Date: 6/13/13
 * Time: 9:18 AM
 *
 * @author Russ Forstall
 */
public class AddEditRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logInLink = null;
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            resp.sendRedirect("/login?validUser=true");
            return;
        }else{
            logInLink = "<a href=\"/logout\">Log Out</a>";
        }
        req.setAttribute("logInLink", logInLink);
        User user = (User) session.getAttribute("user");
        String sIsNew = req.getParameter("isNew");
        boolean isNew = Boolean.parseBoolean(sIsNew);

        String recipeField;
        RecipesDao recipesDao = new RecipesDaoImpl();
        if(!isNew){
            String sRecipeId = req.getParameter("id");
            int recipeId = Integer.valueOf(sRecipeId);
            Recipe recipe = new Recipe();
            recipe = recipesDao.getRecipe(recipeId);
            req.setAttribute("recipeTitle", recipe.getTitle());
            recipeField = recipe.getRecipe();
        } else {
            recipeField = "<p>This is where the description your recipes goes.</p> \n" +
                    "<p>List of ingredients:</p>\n" +
                    "<li>ingredient</li>\n<li>ingredient</li>\n<li>ingredient</li>\n<li>ingredient</li>\n" +
                    "<li>ingredient</li>\n<li>ingredient</li>\n<p>Anything else you wanna add.</p>";
        }

        req.setAttribute("recipeField", recipeField);
        req.setAttribute("user", user);

        req.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RecipesDao recipesDao = new RecipesDaoImpl();
        String sIsNew = req.getParameter("isNew");
        boolean isNew = Boolean.parseBoolean(sIsNew);

        String recipeText = req.getParameter("recipe");
        String recipeTitle = req.getParameter("title");



        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");

            if(isNew){
                recipesDao.addRecipe(recipeTitle, recipeText, user.getId());

            }else {
               String sId = req.getParameter("id");
               int id = Integer.valueOf(sId);
               recipesDao.updateRecipe(id, recipeTitle, recipeText);
            }

        resp.sendRedirect("/myrecipes");
    }
}
