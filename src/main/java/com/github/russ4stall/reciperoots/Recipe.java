package com.github.russ4stall.reciperoots;

import java.util.Date;

/**
 * Date: 6/12/13
 * Time: 1:53 PM
 *
 * @author Russ Forstall
 */
public class Recipe {
    private int id;
    private User user;
    private String title;
    private String recipe;
    private Date createdOn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
