package com.github.russ4stall.reciperoots.recipes.servlets;

import com.github.russ4stall.reciperoots.recipes.dao.RecipesDao;
import com.github.russ4stall.reciperoots.recipes.dao.RecipesDaoImpl;
import com.github.russ4stall.reciperoots.users.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Date: 6/13/13
 * Time: 3:38 PM
 *
 * @author Russ Forstall
 */
public class BrowseRecipesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RecipesDao recipesDao = new RecipesDaoImpl();
        List recipes = recipesDao.getAllRecipes();
        req.setAttribute("recipes", recipes);
        req.getRequestDispatcher("/WEB-INF/jsp/browse.jsp").forward(req, resp);
    }


}
