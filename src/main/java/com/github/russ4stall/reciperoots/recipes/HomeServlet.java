package com.github.russ4stall.reciperoots.recipes;

import com.github.russ4stall.reciperoots.recipes.dao.RecipesDao;
import com.github.russ4stall.reciperoots.recipes.dao.RecipesDaoImpl;
import com.github.russ4stall.reciperoots.users.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Date: 6/12/13
 * Time: 12:13 PM
 *
 * @author Russ Forstall
 */
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RecipesDao recipesDao = new RecipesDaoImpl();
        Recipe randomRecipe = recipesDao.getRandomRecipe();
        Recipe latestRecipe = recipesDao.getLatestRecipe();
        Recipe sampleRecipe = recipesDao.getRecipe(77);

        req.setAttribute("sampleRecipe", sampleRecipe);
        req.setAttribute("randomRecipe", randomRecipe);
        req.setAttribute("latestRecipe", latestRecipe);


        req.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
    }
}
