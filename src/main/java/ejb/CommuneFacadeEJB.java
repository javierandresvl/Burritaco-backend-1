package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.CommuneFacade;
import model.Commune;


@Stateless
public class CommuneFacadeEJB extends AbstractFacade<Commune> implements CommuneFacade {
	
	
	@PersistenceContext(unitName = "burritacoPU")
	private EntityManager em;
	
	public CommuneFacadeEJB() {
		super(Commune.class);
	}
		
	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}
