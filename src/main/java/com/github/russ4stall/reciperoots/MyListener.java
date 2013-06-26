package com.github.russ4stall.reciperoots;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.*;
import org.apache.lucene.util.Version;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.IOException;
import java.lang.String;
/**
 * Date: 6/26/13
 * Time: 10:42 AM
 *
 * @author Russ Forstall
 */
public class MyListener implements ServletContextListener {

    private static void addDoc(IndexWriter w, String title, String isbn) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("title", title, Field.Store.YES));
        doc.add(new StringField("isbn", isbn, Field.Store.YES));
        w.addDocument(doc);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);

            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_43, analyzer);

            File file = new File("C:\\Users\\russellf\\Documents\\recipe-roots-index");
            SimpleFSDirectory index = new SimpleFSDirectory(file);

            IndexWriter w = new IndexWriter(index , config);
            addDoc(w, "Lucene in action", "321321321");
            addDoc(w, "Banana Nuts", "780489");
            addDoc(w, "Wooly Booly", "987654321");
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
