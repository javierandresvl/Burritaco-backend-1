package Classes;


public class Communes 
{
    //Atributos
    private String name;
    private int communes_id;
    
    public void Communes()
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
