package facade;

import java.util.List;

import javax.ejb.Local;

import model.Commune;

@Local
public interface CommuneFacade 
{
    public void create(Commune entity);

    public void edit(Commune entity);

    public void remove(Commune entity);

    public Commune find(Object id);

    public List<Commune> findAll();

    public List<Commune> findRange(int[] range);

    public int count();
}
