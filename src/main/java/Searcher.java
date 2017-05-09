/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author javier
 */

import java.io.IOException;
import org.apache.lucene.analysis.standard.StandardAnalyzer;//ESTE
//import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;//ESTE
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;
import com.mongodb.DBObject;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.store.FSDirectory;

import org.json.JSONObject;

public class Searcher 
{
    public Searcher()
    {
        
    }
    
    public void createIndex() throws IOException, ParseException
    {
        
        StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
        
        // 1. create the index
        
        //**** ESTO ES EN EL CASO DE MI PC *******
        Directory directory = FSDirectory.open( new File("/home/javier/Desktop/INDICE"));
        Directory index = new RAMDirectory();
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_43, analyzer);
        IndexWriter w = new IndexWriter(directory, config);
                
        // 2. Open MongoDB conection
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("Burritaco");
        MongoCollection<Document> collection = database.getCollection("PROBANDO");
        
        // 3. Obtain Documents and add them.
        MongoCursor<Document> cursor = collection.find().iterator();
        try 
        {
            while (cursor.hasNext()) 
            {   
                Document d = cursor.next();
                
                System.out.println(d.toJson()); 
                
                String tweetActual = d.get("tweet").toString();
                String tweetId = d.get("_id").toString();
                String hour = d.get("hora").toString();
                
                org.apache.lucene.document.Document docLucene = new org.apache.lucene.document.Document();
                docLucene.add(new TextField("tweet", tweetActual, Field.Store.YES));
                docLucene.add(new StringField("id", tweetId, Field.Store.YES));
                docLucene.add(new StringField("hora",hour,Field.Store.YES));
                w.addDocument(docLucene);
                
            }
        } finally 
        {
            cursor.close();
        }
        
        w.close();
        
    }
    
    public List<org.apache.lucene.document.Document> search(String query) throws IOException, ParseException
    {   
        //**** ESTO ES EN EL CASO DE MI PC *******
        File indexDir = new File("/home/javier/Desktop/INDICE");
        Directory directory = FSDirectory.open(indexDir);
        
        IndexReader  indexReader  = DirectoryReader.open(directory);        
        IndexSearcher searcher = new IndexSearcher(indexReader);
        StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
        //QueryBuilder builder = new QueryBuilder(analyzer);
        TopScoreDocCollector collector = TopScoreDocCollector.create(10, true);

        Query q = new QueryParser(Version.LUCENE_43, "tweet", analyzer).parse(query);
        searcher.search(q, collector);
        ScoreDoc[] hits = collector.topDocs().scoreDocs;
        
        List<org.apache.lucene.document.Document> listDocuments = new ArrayList<org.apache.lucene.document.Document>();
        
        System.out.println("Found " + hits.length + " hits.");
        for(int i=0;i<hits.length;++i) 
        {
            int docId = hits[i].doc;
            org.apache.lucene.document.Document d = searcher.doc(docId);
            listDocuments.add(d);
            System.out.println("Hora: "+ d.get("hora") + "\t " + d.get("id") + "\t" + d.get("tweet"));
        }
        
        indexReader.close();
        
        return listDocuments;
        
    }
    
}
