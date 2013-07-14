package br.com.rpg.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.rpg.model.Talento;
import org.hibernate.Session;

/**
 * @author Bruno Volpini
 */
@Component
public class TalentoDao extends Dao<Talento> {

	public TalentoDao(Session session) {
		super(session, Talento.class);
	}
}