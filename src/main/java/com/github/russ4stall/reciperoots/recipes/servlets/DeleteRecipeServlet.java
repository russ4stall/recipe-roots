package com.github.russ4stall.reciperoots.recipes.servlets;

import com.github.russ4stall.reciperoots.recipes.dao.RecipesDao;
import com.github.russ4stall.reciperoots.recipes.dao.RecipesDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        if(session == null || session.getAttribute("user") == null){
            resp.sendRedirect("/login?validUser=true");
            return;

        }else{
            logInLink = "<a href=\"/logout\">Log Out</a>";
        }
        req.setAttribute("logInLink", logInLink);

        req.getRequestDispatcher("/WEB-INF/jsp/delete.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sRecipeId = req.getParameter("id");
        int recipeId = Integer.valueOf(sRecipeId);
        RecipesDao recipesDao = new RecipesDaoImpl();
        recipesDao.deleteRecipe(recipeId);
        resp.sendRedirect("/myrecipes");
    }
}
