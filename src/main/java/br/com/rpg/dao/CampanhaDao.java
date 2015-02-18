package br.com.rpg.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.rpg.component.grid.Gridy;
import br.com.rpg.model.Campanha;
import br.com.rpg.model.SystemUser;
import br.com.rpg.session.UserSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * @author Erick Lima
 */
@Component
public class CampanhaDao extends Dao<Campanha> {

	private UserSession userSession;
	
	public CampanhaDao(Session session, UserSession userSession) {
		super(session, Campanha.class);
		this.userSession = userSession;
	}

	@Override
	public Gridy listar(String search, int page, String sortName, String sortOrder, String find, int rows) {
		SystemUser u = userSession.getSystemUser();
		addCriterion(Restrictions.eq("mestre", u));
		return super.listar(search, page, sortName, sortOrder, find, rows);
	}
}