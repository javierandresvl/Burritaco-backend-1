package service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import facade.CommuneFacade;
import facade.CommuneKeywordFacade;
import facade.CongestionFacade;
import facade.KeywordFacade;
import java.io.IOException;
import model.Commune;
import model.CommuneKeyword;
import model.Congestion;
import model.Keyword;
import model.Usuario;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;

@Path("/communes")
public class CommuneService 
{
    @EJB 
    CommuneFacade communeFacadeEJB;
    
    @EJB
    CommuneKeywordFacade communeKeywordFacadeEJB;
    
    @EJB
    KeywordFacade keywordFacadeEJB;
    
    @EJB
    CongestionFacade congestionFacadeEJB;
	
    Logger logger = Logger.getLogger(CommuneService.class.getName());
    
    @GET
    @Produces({"application/xml", "application/json"})
    public List<Commune> findAll()
    {   
        System.out.println(logger);
        return communeFacadeEJB.findAll();
    }
    
    @GET
    @Path("/create/{id}")
    @Produces({"application/xml", "application/json"})
    public List<Keyword> find(@PathParam("id") Integer id) throws IOException, ParseException {
        
        /*  
        * recibo como parametro el id de una comuna, asi que debo
        * buscar todas las calles de esta comuna.
        */
        
        List<CommuneKeyword> todas = communeKeywordFacadeEJB.findAll();
        List<Keyword> todasCalles = keywordFacadeEJB.findAll();
        List<Keyword> coincidencias = new ArrayList<Keyword>();
        
        
        CommuneKeyword ckActual = null;
        Keyword calleActual = null;
        int idCkActual;
        int idCalleActual;
        
        
        /*
        * recorro la tabla intermedia para encontrar el id 
        * de las calles de la comuna pedida.
        
        * por cada coincidencia, se busca la calle
        * de la tabla keywords, y se agrega a una
        * lista de coincidencias
        */
        int largoTodas = todas.size();
        int largoTodasCalles = todasCalles.size();
        int keyId;
        
        for(int i = 0; i < largoTodas; i++)
        {
            ckActual = todas.get(i);
            idCkActual = ckActual.getCommuneId();
            if(id == idCkActual)
            {
                //coincidencias.add(ckActual);
                
                /*
                * ahora busco en todas las calles con el id
                * obtenido de la coincidencia anterior.
                */
                
                keyId = ckActual.getKeywordId();
                calleActual = keywordFacadeEJB.find(keyId);
                coincidencias.add(calleActual);
                /*
                for(int j = 0; j < largoTodasCalles; j++)
                {
                    calleActual = todasCalles.get(j);
                    idCalleActual = calleActual.getKeywordId();
                    if(idCalleActual == keyId)
                    {
                        coincidencias.add(calleActual);
                    }
                }
                */
            }
        }
        
        //System.out.println("ENCONTRE LAS COINCIDENCIAS BAKAN\n");
        
        int largoCalles = coincidencias.size();
        Keyword calle;
        
        Searcher s = new Searcher();
        String nombreCalle = "";
        List<Document> listaTweets;
        Document tweet;
        int when;
        Congestion c = null;
        
        for(int p = 0; p < largoCalles ; p++)
        {
            calle = coincidencias.get(p);
            nombreCalle = calle.getKeywordWord();
            
            listaTweets = s.search(nombreCalle);
            
            //System.out.println("HICE UNA BUSQUEDA CON LUCENE CACHAI\n");
            
            for(int q = 0; q < listaTweets.size() ; q++ )
            {   
                //System.out.println("ENTRE AL SEGUNDO FOR ME CACHAI \n\n");
                tweet = listaTweets.get(q);
                int hour = Integer.parseInt(tweet.get("hora"));
                int day = Integer.parseInt(tweet.get("day"));
                int month = Integer.parseInt(tweet.get("month"));
                int year = Integer.parseInt(tweet.get("year"));
                //c = congestionFacadeEJB.create(Congestion);
                c = new Congestion();
                c.setCommuneId(id);
                c.setCongestionHour(hour);
                c.setCongestionDay(day);
                c.setCongestionMonth(month);
                c.setCongestionYear(year);
                c.setCongestionStreet(nombreCalle);
                congestionFacadeEJB.create(c);
                
                
            }
            
        }
                    
        return coincidencias;
    }
    
    public List<Usuario> crearLista(int id) throws IOException, ParseException
    {
        /*  
        * recibo como parametro el id de una comuna, asi que debo
        * buscar todas las calles de esta comuna.
        */

        List<CommuneKeyword> todas = communeKeywordFacadeEJB.findAll();

        List<Keyword> todasCalles = keywordFacadeEJB.findAll();

        List<Keyword> coincidencias = new ArrayList<Keyword>();

        String communeName = communeFacadeEJB.find(id).getCommuneName();

        CommuneKeyword ckActual = null;
        Keyword calleActual = null;
        int idCkActual;
        int idCalleActual;
        


        /*
        * recorro la tabla intermedia para encontrar el id 
        * de las calles de la comuna pedida.
        
        * por cada coincidencia, se busca la calle
        * de la tabla keywords, y se agrega a una
        * lista de coincidencias
        */
        int largoTodas = todas.size();
        int largoTodasCalles = todasCalles.size();
        int keyId;
        
        for(int i = 0; i < largoTodas; i++)
        {
            ckActual = todas.get(i);
            idCkActual = ckActual.getCommuneId();
            if(id == idCkActual)
            {   
                /*
                * ahora busco en todas las calles con el id
                * obtenido de la coincidencia anterior.
                */
                
                keyId = ckActual.getKeywordId();
                calleActual = keywordFacadeEJB.find(keyId);
                coincidencias.add(calleActual);
            }
        }
        

        
        int largoCalles = coincidencias.size();
        Keyword calle;
        
        Searcher s = new Searcher();
        String nombreCalle = "";
        List<Document> listaTweets;
        Document tweet;
        int when;
        List<Usuario> listaU = new ArrayList<Usuario>();
        Usuario u = null;
        
        for(int p = 0; p < largoCalles ; p++)
        {
            calle = coincidencias.get(p);
            nombreCalle = calle.getKeywordWord();
            
            listaTweets = s.search(nombreCalle);
            

            
            for(int q = 0; q < listaTweets.size() ; q++ )
            {   

                tweet = listaTweets.get(q);
                when = Integer.parseInt(tweet.get("hora"));
                //c = congestionFacadeEJB.create(Congestion);
                
                if(tweet.get("isRetweet").equals("true"))
                {
                    for(int n = 0; n < listaU.size(); n++)
                    {
                        Usuario uActual = listaU.get(n);
                        if(tweet.get("originalUser").equals(uActual.getNombre()))
                        {
                            uActual.setPopularidad(uActual.getPopularidad()+1);
                        }
                    }
                }
                else
                {
                    boolean flag = true;
                    for(int n = 0; n < listaU.size(); n++)
                    {
                        Usuario uActual = listaU.get(n);
                        if(uActual.getNombre().equals(tweet.get("user"))){
                            flag = false;
                        }
                    }
                    
                    if(flag)
                    {
                        u = new Usuario();
                        u.setComuna(communeName);
                        u.setPopularidad(1);
                        u.setNombre(tweet.get("user"));
                        listaU.add(u);
                    }                   
                }
            }            
        }
        
        return listaU;
    }
    
     @GET
    @Path("/create/nodes")
    @Produces({"text/plain", "application/json"})
    public String consultarComunas() throws IOException, ParseException
    {
        int n = 1;
        
        List<Usuario> listaTotal = new ArrayList<Usuario>();
        List<Usuario> listaFinal = new ArrayList<Usuario>();
        

        listaFinal = crearLista(1);

        for(n = 3; n <= 53; n++)
        {
            listaTotal = crearLista(n);

            listaFinal.addAll(listaTotal);
        }

        CreateGraph cg = new CreateGraph(listaFinal);
        
        String resultado = "";
        Usuario u = null;

        for(int i = 0; i < listaFinal.size(); i++)
        {
            u = listaFinal.get(i);
            resultado = resultado + "nombre: " + u.getNombre() + " popularidad: " + u.getPopularidad() + " comuna: " + u.getcomuna() + "\n";
        }

        return resultado;
    }
}
