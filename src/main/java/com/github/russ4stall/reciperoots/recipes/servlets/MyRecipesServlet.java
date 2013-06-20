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
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 6/12/13
 * Time: 9:38 AM
 *
 * @author Russ Forstall
 */
public class MyRecipesServlet extends HttpServlet {
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

        RecipesDao recipesDao = new RecipesDaoImpl();
        List recipes = recipesDao.getMyRecipes(user.getId());
        req.setAttribute("recipes", recipes);

        req.getRequestDispatcher("/WEB-INF/jsp/myrecipes.jsp").forward(req, resp);
    }


}
