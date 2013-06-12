package com.github.russ4stall.reciperoots;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Date: 6/12/13
 * Time: 1:36 PM
 *
 * @author Russ Forstall
 */
public class ViewRecipeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //gets the requested Recipe from the db
        User user = new User();
        user.setId(1);
        user.setEmail("thisguy@thisplace.com");
        user.setPassword("fluffybunny");
        user.setRegisteredOn(new Date());
        user.setName("This Guy");


        Recipe recipe = new Recipe();
        recipe.setId(1);
        recipe.setTitle("Honey Brew");
        recipe.setCreatedOn(new Date());
        recipe.setUser(user);
        recipe.setRecipe("This is where the recipe goes. The description, the ingredients, the instructions. Everything you need to know about making Honey brew Root Beer is right here in this section. Boy, I sure love Root Beer. In fact, I could go for some now.");

        req.setAttribute("recipe", recipe);


        req.getRequestDispatcher("/WEB-INF/jsp/viewrecipe.jsp").forward(req, resp);
    }
}
