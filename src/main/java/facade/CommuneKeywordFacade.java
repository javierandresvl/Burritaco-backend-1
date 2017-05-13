package facade;

import java.util.List;

import javax.ejb.Local;

import model.CommuneKeyword;

@Local
public interface CommuneKeywordFacade
{
    public void create(CommuneKeyword entity);

    public void edit(CommuneKeyword entity);

    public void remove(CommuneKeyword entity);

    public CommuneKeyword find(Object id);

    public List<CommuneKeyword> findAll();

    public List<CommuneKeyword> findRange(int[] range);

    public int count();
}
