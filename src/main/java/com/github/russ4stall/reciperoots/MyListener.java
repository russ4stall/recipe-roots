package com.github.russ4stall.reciperoots;

import com.github.russ4stall.reciperoots.recipes.Ingredient;
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
import java.util.ArrayList;
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
        List<Ingredient> ingredients = recipe.getIngredients();
        String sIngredients = "";

        String searchAll = recipe.getTags() + recipe.getTitle() + recipe.getInstructions() + recipe.getDescription();

        if(!ingredients.isEmpty()){
            for(Ingredient ingredient: ingredients){
                sIngredients = sIngredients + " " + ingredient.getIngredient();
            }
            searchAll = searchAll + " " + sIngredients;
            doc.add(new TextField("recipeIngredients", sIngredients, Field.Store.YES));
        }

        doc.add(new TextField("searchAll", searchAll, Field.Store.YES));

        doc.add(new TextField("recipeTitle", recipe.getTitle(), Field.Store.YES));
        doc.add(new TextField("recipeId", String.valueOf(recipe.getId()), Field.Store.YES));
        doc.add(new TextField("recipeAuthor", user.getName(), Field.Store.YES));
        doc.add(new TextField("recipeTags", recipe.getTags(), Field.Store.YES));
        w.addDocument(doc);
    }

    private static void deleteEntireIndex(File file) throws IOException{
            if(file.isDirectory()){
                if(file.list().length==0){
                    file.delete();
                    System.out.println("deleted directory: " + file.getAbsolutePath());
                }else{
                    String files[] = file.list();
                    for (String temp : files) {
                        File fileDelete = new File(file, temp);

                        //recursive delete...WTF?
                        deleteEntireIndex(fileDelete);
                    }
                    if(file.list().length==0){
                        file.delete();
                        System.out.println("deleted directory: " + file.getAbsolutePath());
                    }
                }
            }else{
                file.delete();
                System.out.println("deleted file: " + file.getAbsolutePath());
            }
    }



    @Override
    public void contextInitialized(ServletContextEvent sce) {
        File file = new File("C:\\Users\\russellf\\Documents\\recipe-roots-index");
       if (file.exists()){
           System.out.println("file exists");
           try{
               deleteEntireIndex(file);
           }catch (IOException e) {
               e.printStackTrace();
           }
       }
        try {
            StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);

            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_43, analyzer);
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

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
