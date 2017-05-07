package Classes;


public class Keyword {
    //Atributos
    private String word;
    private int keyword_id;
    
    public void Keyword()
    {}
    
    //Setters
    public void setId(int id)
    {
        this.keyword_id=id;
    }
    
    public void setWord(String letra)
    {
        this.word = letra;
    }
    
    //Getters
    public String getWord()
    {
        return this.word;
    }
    
    public int getId()
    {
        return this.keyword_id;
    }
}
