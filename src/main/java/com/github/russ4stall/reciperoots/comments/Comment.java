package com.github.russ4stall.reciperoots.comments;

import com.github.russ4stall.reciperoots.users.User;

import java.util.Date;

/**
 * Date: 6/21/13
 * Time: 10:52 AM
 *
 * @author Russ Forstall
 */
public class Comment {
    private User user;
    private String comment;
    private Date createdOn;
    private int id;
    private int recipeId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
}
