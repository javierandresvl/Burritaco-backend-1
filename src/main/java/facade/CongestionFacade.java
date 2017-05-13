package facade;

import java.util.List;

import javax.ejb.Local;

import model.Congestion;

@Local
public interface CongestionFacade 
{
    public void create(Congestion entity);

    public void edit(Congestion entity);

    public void remove(Congestion entity);

    public Congestion find(Object id);

    public List<Congestion> findAll();

    public List<Congestion> findRange(int[] range);

    public int count();
    
}
