package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="communes_has_keywords")
@NamedQuery(name="CommuneKeyword.findAll", query="SELECT ck FROM CommuneKeyword ck")
public class CommuneKeyword implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="communes_id_communes", nullable=false)
    private int communeId;
    
    @Id
    @Column(name="keywords_id_keywords", nullable=true)
    private int keywordId;
    
    public int getCommuneId()
    {
        return this.communeId;
    }
    
    public int getKeywordId() 
    {
        return this.keywordId;
    }
    
    public void setKeywordId(int keywordsId)
    {
        this.keywordId = keywordsId;
    }
    
    public void setCommuneId(int communeId)
    {
        this.communeId = communeId;
    }
    
}
