package com.github.russ4stall.reciperoots.comments;

import com.github.russ4stall.reciperoots.comments.dao.CommentsDao;
import com.github.russ4stall.reciperoots.comments.dao.CommentsDaoImpl;
import com.github.russ4stall.reciperoots.users.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Date: 6/21/13
 * Time: 11:21 AM
 *
 * @author Russ Forstall
 */
public class AddCommentServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String comment = req.getParameter("comment");

        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        String sRecipeId = req.getParameter("recipeId");
        int recipeId = Integer.valueOf(sRecipeId);


        CommentsDao commentsDao = new CommentsDaoImpl();
        commentsDao.addComment(comment, user.getId(), recipeId);

        resp.sendRedirect("/viewrecipe?id=" + recipeId);
    }
}
