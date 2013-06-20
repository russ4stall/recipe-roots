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
 * Date: 6/12/13
 * Time: 1:36 PM
 *
 * @author Russ Forstall
 */
public class ViewRecipeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logInLink = null;
        HttpSession session = req.getSession(false);
        boolean isUserIdSame = false;
        String sRecipeId = req.getParameter("id");
        int recipeId = Integer.valueOf(sRecipeId);
        RecipesDao recipesDao = new RecipesDaoImpl();
        Recipe recipe = recipesDao.getRecipe(recipeId);
        User user = new User();

        if(session == null || session.getAttribute("user") == null){
            logInLink = "<a href=\"/login?validUser=true\">Log In</a>";
        }else{
            user = (User) session.getAttribute("user");
            User recipeUser = recipe.getUser();
            isUserIdSame = (recipeUser.getId()==user.getId());
            logInLink = "<a href=\"/logout\">Log Out</a>";
        }
        req.setAttribute("logInLink", logInLink);

        req.setAttribute("recipe", recipe);

        req.setAttribute("isUserIdSame", isUserIdSame);

        req.getRequestDispatcher("/WEB-INF/jsp/viewrecipe.jsp").forward(req, resp);
    }
}
