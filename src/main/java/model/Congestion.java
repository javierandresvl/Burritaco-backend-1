package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="congestions")
@NamedQuery(name="Congestion.findAll", query="SELECT cg FROM Congestion cg")
public class Congestion implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id_congestions", unique=true, nullable=false)
    private int congestionId;
    
    @Id
    @Column(name="communes_id_communes", unique=true, nullable=true)
    private int communeId;
    
    @Column(name="congestions_street", nullable=true, length=50)
    private String congestionStreet;
    
    @Column(name="congestions_hour", nullable=true)
    private int congestionHour;
    
    @Column(name="congestions_day", nullable=true)
    private int congestionDay;
    
    @Column(name="congestions_month", nullable=true)
    private int congestionMonth;
    
    @Column(name="congestions_year", nullable=true)
    private int congestionYear;
    
    public int getCongestionId() 
    {
        return this.congestionId;
    }
    
    public int getCommuneId() 
    {
        return this.communeId;
    }
    
    public String getCongestionStreet()
    {
        return this.congestionStreet;
    }
    
    public int getCongestionHour()
    {
        return this.congestionHour;
    }
    
    public int getCongestionDay()
    {
        return this.congestionDay;
    }
    
    public int getCongestionMonth()
    {
        return this.congestionMonth;
    }
    
    public int getCongestionYear()
    {
        return this.congestionYear;
    }
    
    public void setCongestionId(int id)
    {
        this.congestionId = id;
    }
    
    public void setCommuneId(int id)
    {
        this.communeId= id;
    }
    
    public void setCongestionStreet(String street)
    {
        this.congestionStreet = street;
    }
    
    public void setCongestionHour(int when)
    {
        this.congestionHour = when;
    }
    
    public void setCongestionDay(int dia)
    {
        this.congestionDay = dia;
    }
    
    public void setCongestionMonth(int mes)
    {
        this.congestionMonth = mes;
    }
    
    public void setCongestionYear(int year)
    {
        this.congestionYear = year;
    }
    
    
    
}

/*
public class Congestion 
{
    //Atributos
    private String congestion_street;
    private String congestion_when;
    private int congestion_id;
    
    public void Tweets()
    {}
    
    //Setters
    public void setId(int id)
    {
        this.congestion_id=id;
    }
    
    public void setName(String calle)
    {
        this.congestion_street = calle;
    }
    
     public void setDate(String fecha)
    {
        this.congestion_when = fecha;
    }
    
    //Getters
    public String getName()
    {
        return this.congestion_street;
    }
    
    public String getDate()
    {
        return this.congestion_when;
    }
    
    public int getId()
    {
        return this.congestion_id;
    }
}
*/