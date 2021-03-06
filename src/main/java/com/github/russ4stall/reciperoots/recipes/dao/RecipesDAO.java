package com.github.russ4stall.reciperoots.recipes.dao;

import com.github.russ4stall.reciperoots.recipes.Recipe;

import java.util.List;

/**
 * Date: 6/17/13
 * Time: 3:49 PM
 *
 * @author Russ Forstall
 */
public interface RecipesDao {
    /**
     * updates recipe in database
     *
     * @param recipe recipe object
     */
    void updateRecipe(Recipe recipe);

    /**
     * adds new recipe to database
     *
     *
     * @param recipe recipe object
     *
     */
    int addRecipe(Recipe recipe);

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
