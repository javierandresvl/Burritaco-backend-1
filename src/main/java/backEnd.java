
import java.io.IOException;
import org.apache.lucene.queryparser.classic.ParseException;

public class backEnd {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ParseException {
        
        System.out.println("Soy Backend\n");
        
        Searcher s = new Searcher();
        s.createIndex();
        
        //busqueda de prueba
        s.search("South");
        
    }
}
