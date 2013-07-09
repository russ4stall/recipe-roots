package com.github.russ4stall.reciperoots;

import com.github.russ4stall.reciperoots.recipes.Ingredient;
import com.github.russ4stall.reciperoots.recipes.Recipe;
import com.github.russ4stall.reciperoots.recipes.dao.RecipesDao;
import com.github.russ4stall.reciperoots.recipes.dao.RecipesDaoImpl;
import com.github.russ4stall.reciperoots.users.User;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 6/27/13
 * Time: 9:19 AM
 *
 * @author Russ Forstall
 */
public class IndexOperationsImpl implements IndexOperations{

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


    @Override
    public void addToIndex(Recipe recipe) {

        try {
            StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_43, analyzer);
            File file = new File("C:\\Users\\russellf\\Documents\\recipe-roots-index");
            SimpleFSDirectory index = new SimpleFSDirectory(file);
            IndexWriter w = new IndexWriter(index , config);
            addDoc(w, recipe);

            w.close();

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    @Override
    public void deleteFromIndex(int recipeId) {
        try {
            StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_43, analyzer);
            config.setOpenMode(IndexWriterConfig.OpenMode.APPEND);
            File file = new File("C:\\Users\\russellf\\Documents\\recipe-roots-index");
            SimpleFSDirectory index = new SimpleFSDirectory(file);
            IndexWriter w = new IndexWriter(index , config);
           // String sId = String.valueOf(recipeId);
          //  Query query = new TermQuery(new Term("recipeTitle", title));

            w.deleteDocuments(new Term("recipeId", String.valueOf(recipeId)));

            w.close();

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }
}
