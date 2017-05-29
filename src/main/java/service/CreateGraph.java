package service;


import java.util.List;
import model.Usuario;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;


public class CreateGraph {
    Driver driver = GraphDatabase.driver( "bolt://localhost", AuthTokens.basic( "neo4j", "neo4j" ) );
    Session session = driver.session();
    
    
    public CreateGraph(List<Usuario> names)
    {
        session.run("match (a)-[r]->(b) delete r");
        session.run("match (n) delete n");
        
        createNodes(names);
        
        session.close();
        driver.close();
    }

    private void createNodes(List<Usuario> usuarios) {
        
        //session.run( "CREATE (a:Commune {name:'"+names.+"'})");
        
        
        //Esto te lo debe pasar Javier -> names, como objetos tipo Twittero
        
        for(Usuario users:usuarios)
        {
            session.run( "CREATE (a:Person {name:'"+users.getNombre()+"'})");
        }
        
        createMatch(usuarios);
        
    }

    private void createMatch(List<Usuario> usuarios) {
        
        for(Usuario users : usuarios)
        {
            session.run("match (a:Person) where a.name='"+users.getNombre()+"' "
                //+ "  match (b:Commune) where b.name='"+commune+"' "
                + "  create (a)-[r:Tweeted {reason:'Uno de los twitteros mas influyentes en relacion a la congestion en esta comuna.'}]->(b)");
        }
    }
    
    
    
}
