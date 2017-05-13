package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.CongestionFacade;
import model.Congestion;


@Stateless
public class CongestionFacadeEJB extends AbstractFacade<Congestion> implements CongestionFacade {
	
	
	@PersistenceContext(unitName = "burritacoPU")
	private EntityManager em;
	
	public CongestionFacadeEJB() {
		super(Congestion.class);
	}
		
	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}
