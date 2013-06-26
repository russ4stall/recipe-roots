package com.github.russ4stall.reciperoots.recipes;

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
 * Date: 6/12/13
 * Time: 12:13 PM
 *
 * @author Russ Forstall
 */
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get recipes from the db to post on the Home page
        String logInLink = null;
        HttpSession session = req.getSession(false);
        User user;
        if(session == null || session.getAttribute("user") == null){
            logInLink = "<a href=\"/login?validUser=true\">Log In</a>";
            String signUpLink = "<a href=\"/register?passMatch=true&emailMatch=true\">Sign Up</a>";
            req.setAttribute("signUpLink", signUpLink);
        }else{
            user = (User) session.getAttribute("user");
            String nameDisplay = "Logged in as " + user.getName();
            req.setAttribute("nameDisplay", nameDisplay);
            logInLink = "<a href=\"/logout\">Log Out</a>";

            String loggedInAs = "Logged in as " + user.getName();
            req.setAttribute("loggedInAs", loggedInAs);
        }
        req.setAttribute("logInLink", logInLink);
        RecipesDAO recipesDao = new RecipesDaoImpl();

        Recipe randomRecipe = recipesDao.getRandomRecipe();
        Recipe latestRecipe = recipesDao.getLatestRecipe();
        req.setAttribute("randomRecipe", randomRecipe);
        req.setAttribute("latestRecipe", latestRecipe);


        req.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
    }
}
