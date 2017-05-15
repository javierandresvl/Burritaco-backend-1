package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="communes")
@NamedQuery(name="Commune.findAll", query="SELECT c FROM Commune c")
public class Commune implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id_communes", unique=true, nullable=false)
    private int communeId;
    
    @Column(name="commune_name", nullable=true, length=45)
    private String communeName;
    
    public Commune(){}
    
    public int getCommuneId() 
    {
        return this.communeId;
    }

    public String getCommuneName() 
    {
        return this.communeName;
    }
    
    public void setCommuneId(int communeId)
    {
        this.communeId = communeId;
    }
    
    public void setCommuneName(String communeName)
    {
        this.communeName = communeName;
    }
    
}


/*
public class Commune 
{
    //Atributos
    private String name;
    private int communes_id;
    
    public void Commune()
    {}
    
    //Setters
    public void setId(int id)
    {
        this.communes_id=id;
    }
    
    public void setName(String nombre)
    {
        this.name = nombre;
    }
    
    //Getters
    public String getName()
    {
        return this.name;
    }
    
    public int getId()
    {
        return this.communes_id;
    }
    
}
*/