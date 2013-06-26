package com.github.russ4stall.reciperoots.recipes.servlets;

import com.github.russ4stall.reciperoots.recipes.Recipe;
import com.github.russ4stall.reciperoots.recipes.dao.RecipesDAO;
import com.github.russ4stall.reciperoots.recipes.dao.RecipesDaoImpl;
import com.github.russ4stall.reciperoots.users.User;

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
        String logInLink = null;
        HttpSession session = req.getSession(false);

        String sRecipeId = req.getParameter("id");
        int recipeId = Integer.valueOf(sRecipeId);

        RecipesDAO recipesDao = new RecipesDaoImpl();
        Recipe recipe = recipesDao.getRecipe(recipeId);

        User user;

        if(session == null || session.getAttribute("user") == null){
            resp.sendRedirect("/login?validUser=true");
            return;

        }else{
            user = (User) session.getAttribute("user");
            User recipeUser = recipe.getUser();
            String nameDisplay = "Logged in as " + user.getName();
            req.setAttribute("nameDisplay", nameDisplay);
            logInLink = "<a href=\"/logout\">Log Out</a>";

            String loggedInAs = "Logged in as " + user.getName();
            req.setAttribute("loggedInAs", loggedInAs);

            if(recipeUser.getId()!=user.getId()){
                resp.sendRedirect("/myrecipes");
                return;
            }
        }
        req.setAttribute("logInLink", logInLink);

        req.getRequestDispatcher("/WEB-INF/jsp/delete.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sRecipeId = req.getParameter("id");
        int recipeId = Integer.valueOf(sRecipeId);
        RecipesDAO recipesDao = new RecipesDaoImpl();
        recipesDao.deleteRecipe(recipeId);
        resp.sendRedirect("/myrecipes");
    }
}
