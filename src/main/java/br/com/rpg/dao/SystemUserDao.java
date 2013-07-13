package br.com.rpg.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.rpg.model.SystemUser;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Guilherme Felipe de C. G. da Silva
 */
@Component
public class SystemUserDao extends Dao<SystemUser> {

	private final Session session;
	
	public SystemUserDao(Session session) {
		super(session, SystemUser.class);
		this.session = session;
	}
	
	public SystemUser buscarPorEmail(String email) {
		Criteria c = this.session.createCriteria(SystemUser.class);
		c.add(Restrictions.eq("email", email));
		return (SystemUser) c.uniqueResult();
	}
}