package model;

public class Usuario {
    
    public Usuario(){}
    
    String nombreUsuario;
    int popularidad;
    String comuna;
    
    //Setters
    public void setNombre(String nombre)
    {
        this.nombreUsuario = nombre;
    }
    
    public void setComuna(String commune)
    {
        this.comuna = commune;
    }
    
    public void setPopularidad(int popularity)
    {
        this.popularidad = popularity;
    }
    
    //Getters
    public String getNombre()
    {
        return this.nombreUsuario;
    }
    
    public String getcomuna()
    {
        return this.comuna;
    }
    
    public int getPopularidad()
    {
        return this.popularidad;
    }
    
}
