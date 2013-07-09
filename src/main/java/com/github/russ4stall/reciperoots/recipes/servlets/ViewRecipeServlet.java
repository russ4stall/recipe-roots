package com.github.russ4stall.reciperoots.recipes.servlets;

import com.github.russ4stall.reciperoots.comments.dao.CommentsDao;
import com.github.russ4stall.reciperoots.comments.dao.CommentsDaoImpl;
import com.github.russ4stall.reciperoots.recipes.Recipe;
import com.github.russ4stall.reciperoots.recipes.dao.RecipesDao;
import com.github.russ4stall.reciperoots.recipes.dao.RecipesDaoImpl;
import com.github.russ4stall.reciperoots.users.User;
import com.github.russ4stall.reciperoots.utilities.Formatter;
import com.github.russ4stall.reciperoots.utilities.ParameterHelper;
import org.apache.commons.lang3.math.Fraction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Date: 6/12/13
 * Time: 1:36 PM
 *
 * @author Russ Forstall
 */
public class ViewRecipeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterHelper helper = new ParameterHelper(req);
        Integer recipeId = helper.getParameterAsInteger("id");
        RecipesDao recipesDao = new RecipesDaoImpl();
        Recipe recipe = recipesDao.getRecipe(recipeId);
        CommentsDao commentsDao = new CommentsDaoImpl();
        List commentList = commentsDao.getAllComments(recipeId);
        req.setAttribute("commentList", commentList);
        req.setAttribute("recipe", recipe);


        req.getRequestDispatcher("/WEB-INF/jsp/viewrecipe.jsp").forward(req, resp);
    }
}
