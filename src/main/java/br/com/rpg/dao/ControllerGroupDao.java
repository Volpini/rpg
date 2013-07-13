package br.com.rpg.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.rpg.model.ControllerGroup;
import org.hibernate.Session;

/**
 *
 * @author Bruno Volpini
 */
@Component
public class ControllerGroupDao extends Dao<ControllerGroup> {

	public ControllerGroupDao(Session session) {
		super(session, ControllerGroup.class);
	}
}