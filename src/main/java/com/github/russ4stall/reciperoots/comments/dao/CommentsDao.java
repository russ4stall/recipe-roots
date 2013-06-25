package com.github.russ4stall.reciperoots.comments.dao;

import com.github.russ4stall.reciperoots.comments.Comment;
import com.github.russ4stall.reciperoots.users.User;

import java.util.List;

/**
 * Date: 6/21/13
 * Time: 11:03 AM
 *
 * @author Russ Forstall
 */
public interface CommentsDao {

    void addComment(String comment, int userId, int recipeId);
    void deleteComment(int id);
    List getAllComments(int recipeId);

}
