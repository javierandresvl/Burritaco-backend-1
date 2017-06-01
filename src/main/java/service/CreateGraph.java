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
        //Esto te lo debe pasar Javier -> names, como objetos tipo Twittero
        for(Usuario user:usuarios)
        {
            //Si la persona no existe dentro de la base de datos de grafos
            if(!session.run("MATCH (tom {name:"+user.getNombre()+"}) RETURN tom").hasNext())
            {
                session.run( "CREATE (a:Person {name:'"+user.getNombre()+"'})");
            }
            
            createMatch(user.getNombre(),user.getcomuna());
        }
    }

    private void createMatch(String persona, String comuna) {
        
        //Si la comuna no existe dentro de la base de datos de grafos
        if(!session.run("MATCH (tom {name:'"+comuna+"'}) RETURN tom").hasNext())
        {
            session.run( "CREATE (a:Commune {name:'"+comuna+"'})");
            System.out.println("Se ha creado: "+comuna+"\n");
        }
        
        session.run("match (a:Person) where a.name='"+persona+"' "
        + "  match (b:Commune) where b.name='"+comuna+"' "
        + "  create (a)-[r:Tweeted {reason:'Uno de los twitteros mas influyentes en relacion a la congestion en esta comuna.'}]->(b)");
        
    }
    
}
