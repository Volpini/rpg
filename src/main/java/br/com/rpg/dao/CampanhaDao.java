package br.com.rpg.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.rpg.model.Campanha;
import org.hibernate.Session;

/**
 * @author Erick Lima
 */
@Component
public class CampanhaDao extends Dao<Campanha> {

	public CampanhaDao(Session session) {
		super(session, Campanha.class);
	}
}