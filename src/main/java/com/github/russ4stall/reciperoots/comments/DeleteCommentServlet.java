package com.github.russ4stall.reciperoots.comments;

import com.github.russ4stall.reciperoots.comments.dao.CommentsDao;
import com.github.russ4stall.reciperoots.comments.dao.CommentsDaoImpl;
import com.github.russ4stall.reciperoots.users.User;
import com.github.russ4stall.reciperoots.utilities.ParameterHelper;

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

        User user = (User) req.getAttribute("user");

        ParameterHelper helper = new ParameterHelper(req);
        int recipeId = helper.getParameterAsInteger("recipeId");
        int commentId = helper.getParameterAsInteger("commentId");

        CommentsDao commentsDao = new CommentsDaoImpl();
        commentsDao.deleteComment(commentId);

        resp.sendRedirect("/viewrecipe?id=" + recipeId);
    }



}
