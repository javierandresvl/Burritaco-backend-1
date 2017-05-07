package Classes;


public class Tweets 
{
    //Atributos
    private String text;
    private int tweets_id;
    
    public void Tweets()
    {}
    
    //Setters
    public void setId(int id)
    {
        this.tweets_id=id;
    }
    
    public void setText(String texto)
    {
        this.text = texto;
    }
    
    //Getters
    public String getText()
    {
        return this.text;
    }
    
    public int getId()
    {
        return this.tweets_id;
    }
    
}
