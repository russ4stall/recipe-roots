package com.github.russ4stall.reciperoots;

import com.github.russ4stall.reciperoots.recipes.Recipe;

/**
 * Date: 6/27/13
 * Time: 9:05 AM
 *
 * @author Russ Forstall
 */
public interface IndexOperations {

    void addToIndex(Recipe recipe);

    void deleteFromIndex(int recipeId);
}
