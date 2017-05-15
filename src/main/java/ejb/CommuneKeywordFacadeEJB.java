package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.CommuneKeywordFacade;
import model.CommuneKeyword;


@Stateless
public class CommuneKeywordFacadeEJB extends AbstractFacade<CommuneKeyword> implements CommuneKeywordFacade {
	
	
	@PersistenceContext(unitName = "burritacoPU")
	private EntityManager em;
	
	public CommuneKeywordFacadeEJB() {
		super(CommuneKeyword.class);
	}
		
	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}
