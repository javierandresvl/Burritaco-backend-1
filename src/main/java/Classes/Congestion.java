package Classes;


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
