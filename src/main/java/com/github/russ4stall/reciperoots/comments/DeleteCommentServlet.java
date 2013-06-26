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
 * Date: 6/26/13
 * Time: 9:12 AM
 *
 * @author Russ Forstall
 */
public class DeleteCommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");

        String sRecipeId = req.getParameter("recipeId");
        int recipeId = Integer.valueOf(sRecipeId);

        System.out.println(recipeId);

        String sCommentId = req.getParameter("commentId");
        int commentId = Integer.valueOf(sCommentId);

        System.out.println(commentId);


        CommentsDao commentsDao = new CommentsDaoImpl();
        commentsDao.deleteComment(commentId);

        resp.sendRedirect("/viewrecipe?id=" + recipeId);
    }



}
