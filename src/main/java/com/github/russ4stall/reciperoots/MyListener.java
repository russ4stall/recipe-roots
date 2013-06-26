package com.github.russ4stall.reciperoots;

import com.github.russ4stall.reciperoots.recipes.Recipe;
import com.github.russ4stall.reciperoots.recipes.dao.RecipesDao;
import com.github.russ4stall.reciperoots.recipes.dao.RecipesDaoImpl;
import com.github.russ4stall.reciperoots.users.User;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.*;
import org.apache.lucene.util.Version;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.IOException;
import java.lang.String;
import java.util.List;

/**
 * Date: 6/26/13
 * Time: 10:42 AM
 *
 * @author Russ Forstall
 */
public class MyListener implements ServletContextListener {

    private static void addDoc(IndexWriter w, Recipe recipe) throws IOException {
        Document doc = new Document();
        User user = recipe.getUser();
        doc.add(new TextField("recipe", recipe.getRecipe(), Field.Store.YES));
        doc.add(new TextField("recipeTitle", recipe.getTitle(), Field.Store.YES));
        doc.add(new IntField("recipeId", recipe.getId(), Field.Store.YES));
        doc.add(new TextField("recipeAuthor", user.getName(), Field.Store.YES));
        w.addDocument(doc);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);

            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_43, analyzer);

            File file = new File("C:\\Users\\russellf\\Documents\\recipe-roots-index");
            SimpleFSDirectory index = new SimpleFSDirectory(file);

            RecipesDao recipesDao = new RecipesDaoImpl();
            List<Recipe> recipes = recipesDao.getAllRecipes();
            IndexWriter w = new IndexWriter(index , config);

            for(Recipe recipe : recipes){
                addDoc(w, recipe);
            }

            w.close();




        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
