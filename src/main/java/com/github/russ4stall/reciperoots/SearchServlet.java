package com.github.russ4stall.reciperoots;

import com.github.russ4stall.reciperoots.recipes.Recipe;
import com.github.russ4stall.reciperoots.recipes.dao.RecipesDao;
import com.github.russ4stall.reciperoots.recipes.dao.RecipesDaoImpl;
import com.github.russ4stall.reciperoots.users.User;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 6/26/13
 * Time: 1:33 PM
 *
 * @author Russ Forstall
 */
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logInLink = null;
        HttpSession session = req.getSession(false);
        User user;
        if(session == null || session.getAttribute("user") == null){
            logInLink = "<a href=\"/login?validUser=true\">Log In</a>";
            String signUpLink = "<a href=\"/register?passMatch=true&emailMatch=true\">Sign Up</a>";
            req.setAttribute("signUpLink", signUpLink);
        }else{
            user = (User) session.getAttribute("user");
            String nameDisplay = "Logged in as " + user.getName();
            req.setAttribute("nameDisplay", nameDisplay);
            logInLink = "<a href=\"/logout\">Log Out</a>";
            String myRecipesLink = "<a href=\"/myrecipes\">My Recipes</a>";
            req.setAttribute("myRecipesLink", myRecipesLink);
            String loggedInAs = "Logged in as " + user.getName();
            req.setAttribute("loggedInAs", loggedInAs);
        }
        req.setAttribute("logInLink", logInLink);

        String searchTerm = req.getParameter("search");
        if(searchTerm!=null && !searchTerm.isEmpty()){
            StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);

            File file = new File("C:\\Users\\russellf\\Documents\\recipe-roots-index");
            SimpleFSDirectory index = new SimpleFSDirectory(file);
            String byType = req.getParameter("byType");
            Query query = null;
            try {
                query = new QueryParser(Version.LUCENE_43, byType ,analyzer).parse(searchTerm);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int hitsPerPage = 30;
            IndexReader reader = DirectoryReader.open(index);
            IndexSearcher searcher = new IndexSearcher(reader);
            TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
            searcher.search(query, collector);
            ScoreDoc[] hits = collector.topDocs().scoreDocs;
            List<Recipe> recipes = new ArrayList<Recipe>();
            RecipesDao recipesDao = new RecipesDaoImpl();

            System.out.println("Found " + hits.length + " hits.");
            for(int i=0;i<hits.length;i++){
                int docId = hits[i].doc;
                Document d = searcher.doc(docId);
                System.out.println((i+1) + ". " + d.get("recipeId") +  "\t" + d.get("recipeTitle") + "\t" + d.get("recipeAuthor"));

                recipes.add(recipesDao.getRecipe(Integer.valueOf(d.get("recipeId"))));

            }
            reader.close();
            req.setAttribute("recipes", recipes);
        }


        req.getRequestDispatcher("/WEB-INF/jsp/search.jsp").forward(req, resp);
    }

}
