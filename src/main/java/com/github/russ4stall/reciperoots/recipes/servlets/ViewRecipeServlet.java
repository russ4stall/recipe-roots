package com.github.russ4stall.reciperoots.recipes.servlets;

import com.github.russ4stall.reciperoots.comments.dao.CommentsDao;
import com.github.russ4stall.reciperoots.comments.dao.CommentsDaoImpl;
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
        String logInLink = null;
        HttpSession session = req.getSession(false);
        boolean isUserIdSame = false;
        boolean isLoggedIn = false;
        String sRecipeId = req.getParameter("id");
        int recipeId = Integer.valueOf(sRecipeId);
        RecipesDAO recipesDao = new RecipesDaoImpl();
        Recipe recipe = recipesDao.getRecipe(recipeId);
        CommentsDao commentsDao = new CommentsDaoImpl();
        List commentList = commentsDao.getAllComments(recipeId);
        if (commentList.size() < 1){
            boolean noComments = true;
            req.setAttribute("noComments", noComments);
        }
        User user = new User();

        if(session == null || session.getAttribute("user") == null){
            logInLink = "<a href=\"/login?validUser=true\">Log In</a>";
            String signUpLink = "<a href=\"/register?passMatch=true&emailMatch=true\">Sign Up</a>";
            req.setAttribute("signUpLink", signUpLink);
        }else{
            user = (User) session.getAttribute("user");
            User recipeUser = recipe.getUser();
            String nameDisplay = "Logged in as " + user.getName();
            req.setAttribute("nameDisplay", nameDisplay);
            isUserIdSame = (recipeUser.getId()==user.getId());
            logInLink = "<a href=\"/logout\">Log Out</a>";
            isLoggedIn = true;
            String loggedInAs = "Logged in as " + user.getName();
            req.setAttribute("loggedInAs", loggedInAs);

        }



        req.setAttribute("commentList", commentList);
        req.setAttribute("logInLink", logInLink);
        req.setAttribute("recipe", recipe);
        req.setAttribute("isLoggedIn", isLoggedIn);
        req.setAttribute("user", user);
        req.setAttribute("isUserIdSame", isUserIdSame);

        req.getRequestDispatcher("/WEB-INF/jsp/viewrecipe.jsp").forward(req, resp);
    }
}
