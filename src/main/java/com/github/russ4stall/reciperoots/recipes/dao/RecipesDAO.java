package com.github.russ4stall.reciperoots.recipes.dao;

import com.github.russ4stall.reciperoots.recipes.Recipe;

import java.util.List;

/**
 * Date: 6/17/13
 * Time: 3:49 PM
 *
 * @author Russ Forstall
 */
public interface RecipesDAO {
    /**
     * updates recipe in database
     *
     * @param title  title of recipe
     * @param recipe recipe text
     */
    void updateRecipe(int id, String title, String recipe);

    /**
     * adds new recipe to database
     *
     * @param title  title of recipe
     * @param recipe recipe text
     * @param userId numerical id of user
     */
    void addRecipe(String title, String recipe, int userId);

    /**
     * deletes recipe from database
     *
     * @param id numerical id of recipe to be deleted
     */
    void deleteRecipe(int id);

    List getMyRecipes(int userId);

    List getAllRecipes();

    Recipe getRecipe(int id);

    Recipe getRandomRecipe();

    Recipe getLatestRecipe();

}
