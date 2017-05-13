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

import facade.KeywordFacade;
import model.Keyword;

@Path("/keywords")
public class KeywordService
{
    @EJB 
    KeywordFacade keywordFacadeEJB;
	
    Logger logger = Logger.getLogger(KeywordService.class.getName());
    
    @GET
    @Produces({"application/xml", "application/json"})
    public List<Keyword> findAll()
    {
        return keywordFacadeEJB.findAll();
    }
    
    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Keyword entity) {
        keywordFacadeEJB.create(entity);
    }
}
