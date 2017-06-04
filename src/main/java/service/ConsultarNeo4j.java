/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import model.Usuario;
import org.apache.lucene.queryparser.classic.ParseException;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

@Path("/neo4j")
public class ConsultarNeo4j {
    
    
    @GET
    @Path("/nodes")
    @Produces({"text/plain", "application/json"})
    public String find() {
        //se genera la conexion a neo4j
        Driver driver = GraphDatabase.driver( "bolt://localhost", AuthTokens.basic( "neo4j", "root" ) );
        Session session = driver.session();
        int i,id1,id2;
        //se consula por los nodos de tipo persona
        StatementResult result = session.run( "MATCH (a:Person) RETURN a.name as name,a.weight as peso");
        //se crea un array de string para guardar los nombres de todos los nodos
        List<String> nombres = new ArrayList<String>();
        //se crea un array de string para guardar el nombre de los nodos de tipo persona
        List<String> usr = new ArrayList<String>();
        //arreglo con los pesos de los que realizan tweets
        List<Integer> peso = new ArrayList<Integer>();
        //se crea un JsonObject para agregar valores de tipo Json
        JsonObject object = null;
        //se crean 2 arrays Json, uno para los nodos y otro para las relaciones(edges)
        JsonArray arrayNodes = new JsonArray();
        JsonArray arrayEdges = new JsonArray();
        id1=0;//id que tendran los nodos
        id2=0;//id que tendra las relaciones
        while (result.hasNext())//mientras la consulta tenga datos
        {
            Record record = result.next();
            usr.add(record.get("name").asString());//se agrega el nombre del nodo a usr
            peso.add(record.get("peso").asInt());
        }
        
        while(usr.size()>100){//mientras los usuarios sean mayor a 100
            int aux=peso.get(0);
            int j=0;
            for(i=1;i<usr.size();i++){//se busca el usuario menos influyente
                if(aux>peso.get(i)){
                    aux=peso.get(i);
                    j=i;
                }
            }
            usr.remove(j); //se elimina el usuario menos influyente
            peso.remove(j);
        }
        
        for(i=0;i<usr.size();i++){
            object =new JsonObject();
            object.addProperty("id", "n"+id1);//se crea un id para el nodo
            id1++;//aumentamos id del nodo
            object.addProperty("label", usr.get(i)); //se agrega el nombre
            object.addProperty("size", 1); //se agrega porte del nodo
            object.addProperty("type","person"); //se agrega el tipo
            object.addProperty("weight", peso.get(i)); //se agrega el peso
            nombres.add(usr.get(i));  //se agrega el nombre del nodo a nombres
            arrayNodes.add(object); //se agrega el JsonObject creado al array de nodos
        }
        
        
        
        
        //se consulta por las comunas
        result = session.run( "MATCH (c:Commune) RETURN c.name as name");
        while (result.hasNext())
        {
            Record record = result.next();
            object =new JsonObject();
            object.addProperty("id", "n"+id1);
            id1++;
            object.addProperty("label", record.get("name").asString());
            object.addProperty("size", 2);
            object.addProperty("type", "Commune");
            nombres.add(record.get("name").asString());
            arrayNodes.add(object);
        }


        //para los nombres de nodos tipo persona, se deben encontrar las relaciones que estos tengan co las comunas
        for(i=0;i<usr.size();i++){
            String str = usr.get(i);
            //consulta para saber a que nodo se relaciona la persona i
            StatementResult result2 = session.run( "MATCH (a:Person) where a.name='"+str+"' match(a)-[r]->(b) return b.name as name,type(r) as re");
            while (result2.hasNext())
            {
                //se crea el JsonObject respectivo y se agrega al array de relaciones
                Record record = result2.next();
                object = new JsonObject();
                System.out.println(str+"->"+record.get("name").asString());
                object.addProperty("id", "e"+id2);
                object.addProperty("source", "n"+nombres.indexOf(str));
                object.addProperty("target", "n"+nombres.indexOf(record.get("name").asString()));
                object.addProperty("reason", record.get("re").asString());
                arrayEdges.add(object);
                id2++;
            }
        }

        //se cierra la conexion
        session.close();
        driver.close();
        //se crea el JsonObject que entrega el Json final con la informacion necesaria para crear el grafo
        object = new JsonObject();
        object.add("nodes", arrayNodes);
        object.add("edges", arrayEdges);

        return object.toString();

    }
    
}
