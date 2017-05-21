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
  
  //servicio que entrega los datos para el grafico comparativo de las comunas
  @GET
  @Path("/communes")
  @Produces({"text/plain", "application/json"})
    public String findAllCommunes(){
    List<Commune> comunas = communesFacadeEJB.findAll();
    List<Congestion> congestiones= congestionsFacadeEJB.findAll(); //todas las congestiones
    int cantComunas = comunas.size();       //cantidad de comunas
    int cantCongestiones = congestiones.size();
    int[] comunasId=new int[cantComunas];   //aca se guardan los ids de las comunas
    int[] congestionComuna=new int[cantComunas]; //aca se guardara la cantidad de congestion de cada comuna
    int i=0, aux=0, j=0, menorMes=12,mayorMes=1,dia;
    String str=null,str2=null;
    JsonArray array = new JsonArray();      //un arreglo de tipo Json
    JsonObject object = new JsonObject();
    for(i=0;i<cantComunas;i++){
        comunasId[i]=comunas.get(i).getCommuneId(); //se guardan los ids de las comunas
    }
    for(i=0;i<cantComunas;i++){
       for(j=0;j<congestiones.size();j++){
           if(congestiones.get(j).getCommuneId() == comunasId[i]){  //si la comuna de congestion es igual a la ingresada
               aux++;                                                //se aumenta el contador de congestion en la comuna i
           }
       }
       congestionComuna[i]=aux;                               //se guardar el valor final del contador en el arreglo
       aux=0;                                                   //se vuelve a poner el contador en 0
    }
    //con el siguiente for se agregan los datos obtenidos al objeto Json creado.
    for(i=0;i<cantComunas;i++){           
        str="id"+comunasId[i];
        object.addProperty(str,congestionComuna[i]);  
    }
    
    //con el siguiente for se obtiene el menor y mayor mes de los datos recolectados de twitter
    for(i=0;i<cantCongestiones;i++){
        j=congestiones.get(i).getCongestionMonth(); //se obtiene el mes de la congestion i
        if(j<menorMes){
            menorMes=j;                             //menor mes
        }
        
        if(j>mayorMes){
            mayorMes=j;                             //mayor mes
        }
    }
    
    //si menor y mayor mes son iguales, solo se debe buscar el menor y maximo dia 
    //para fecha inicial y final
    if(menorMes == mayorMes){
            dia=1;
            for(i=0;i<cantCongestiones;i++){
                j=congestiones.get(i).getCongestionDay();
                if(j > dia){
                    dia=j;
                }
            }
            str2=dia+"/"+menorMes+"/"+2017;
            
            dia=31;
            for(i=0;i<cantCongestiones;i++){
                j=congestiones.get(i).getCongestionDay();
                if(j < dia){
                    dia=j;
                }
            }
            str=dia+"/"+menorMes+"/"+2017;
        }
    //en caso contrario, para la fecha inicial se debe buscar el menor dia del menor mes
    //y para fecha final, el mayor dia del mayor mes
    else{
        dia=1;
        for(i=0;i<cantCongestiones;i++){
            j=congestiones.get(i).getCongestionDay();
            if(j < dia && congestiones.get(i).getCongestionMonth()==menorMes){
                dia=j;
            }
        }
        
        str=dia+"/"+menorMes+"/"+2017;    
        dia=31;
        
        for(i=0;i<cantCongestiones;i++){
            j=congestiones.get(i).getCongestionDay();
            if(j > dia && congestiones.get(i).getCongestionMonth()==mayorMes){
                dia=j;
            }
        }
        
        str2=dia+"/"+mayorMes+"/"+2017;
    }
 
    object.addProperty("fecha inicial", str);
    object.addProperty("fecha termino", str2);
    return  object.toString();
  }
}
