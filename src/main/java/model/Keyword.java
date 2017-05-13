package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="keywords")
@NamedQuery(name="Keyword.findAll", query="SELECT k FROM Keyword k")
public class Keyword implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id_keywords", unique=true, nullable=false)
    private int keywordId;
    
    @Column(name="keywords_word", nullable=true, length=45)
    private String keywordWord;
    
    public int getKeywordId() 
    {
        return this.keywordId;
    }
    
    public String getKeywordWord()
    {
        return this.keywordWord;
    }
    
    public void setKeywordId(int keywordsId)
    {
        this.keywordId = keywordsId;
    }
    
    public void setKeywordWord(String keywordWords)
    {
        this.keywordWord = keywordWords;
    }
    
    
    
}



/*
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
*/