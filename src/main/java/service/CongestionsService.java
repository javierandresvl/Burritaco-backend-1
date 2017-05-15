package service;

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
import com.google.gson.*;

import facade.CongestionFacade;
import model.Congestion;

import facade.CommuneFacade;
import model.Commune;

@Path("/congestions")
public class CongestionsService {

  @EJB
	CongestionFacade congestionsFacadeEJB;

  @EJB
	CommuneFacade communesFacadeEJB;

	Logger logger = Logger.getLogger(CongestionsService.class.getName());

  //funcion que entrega el json para frontend
  //entrega una lista con la cantidad de congestion por horas
  @GET
  @Path("{id}")
  @Produces({"text/plain", "application/json"})
    public String findCommune(@PathParam("id") int id){
    int[] horarios=new int[24]; //arreglo con cada hora (0 a 23hrs)
    int i, hora;
    Gson gson=new GsonBuilder().create();
    List<Congestion> congestiones= congestionsFacadeEJB.findAll(); //todas las congestiones
    for(i=0;i<congestiones.size();i++){
        if(id == congestiones.get(i).getCommuneId()){ //si el id de la comuna es igual al ingresado
            hora=congestiones.get(i).getCongestionHour(); //se guarda la hora
            /* PARA EL CASO DEL HORARIO LOCAL
            if(hora == 0){
              horarios[23]++;
            }
            else{
              horarios[hora-1]++;
            }*/
            //PARA EL CASO DEL HORARIO DEL VPS
            if(hora < 4){
              horarios[hora+20]++;
            }
            else{
              horarios[hora-4]++;
            }
        }
    }
    return  gson.toJson(horarios);
  }
}
