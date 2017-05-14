package service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.document.Document;

@Path("/main")
@ApplicationPath("/")
public class backEnd {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ParseException {
        
        System.out.println("Soy Backend\n");
        
        
        Searcher s = new Searcher();
        s.createIndex();
        
        //busqueda de prueba
        List<Document> resultado = s.search("South");
        
        
    }
    
    @GET
    @Produces("text/plain")
    public String getClichedMessage() throws IOException, ParseException
    {   
        Searcher s = new Searcher();
        s.createIndex();
        return "SE HA CREADO EL INDICE INVERTIDO CON LOS TWEETS DE MONGO";
    }
}
